文章目录
* [ContentProvider概述](#p1)
* [自定义ContentProvider](#p2)
* [ContentProvider的应用](#p3)
	* [案例一获取日历事件，向日历中添加提醒事件](#p301)
	* [案例二获取联系人相应信息](#p302)
	* [案例三读取短信验证码](#p303)   


### <span id="p1">ContentProvider概述</span>
1. ContentProvider是不同应用程序间共享数据的一个组件。
2. 为什么使用？通过数据持久化，.db或者SP(SharedPreferences)中的数据的仅限于被创建的应用所访问，无法做到应用程序间的数据交互和共享。而内容提供者就相当于对外部提供了一个操作当前应用数据库表的API，通过UriMatcher匹配相应的uri，达到对数据库中不同表CRUD的操作。  
![图片来自菜鸟教程](https://imgconvert.csdnimg.cn/aHR0cHM6Ly93d3cucnVub29iLmNvbS93cC1jb250ZW50L3VwbG9hZHMvMjAxNS8wNi9jb250ZW50LmpwZw?x-oss-process=image/format,png)  

### <span id="p2">自定义ContentProvider</span>
1. 四大组件模式大同小异，首先编写一个类，XXXService继承Service，XXXActivity继承自Activity，内容提供者当然继承自ContentProvider,这里以UserProvider为例，实现对user表数据的CRUD。  
**URI：** 统一资源标识符(Uniform Resource Identifier), 它和URL(统一资源定位符)什么关系呢，其实URI和URL都可以标识web主机上的资源，但URL是一种具体的URI，它是URI的一个子集，它不仅唯一标识资源，而且还提供了定位该资源的信息。而URI是对资源的抽象，实际应用还是URI居多。  
URI分为系统预制(已经定义好了相应的约束)和自定义,下面以自定义作为讲解。     
![参考csdn](https://imgconvert.csdnimg.cn/aHR0cDovL3VwbG9hZC1pbWFnZXMuamlhbnNodS5pby91cGxvYWRfaW1hZ2VzLzk0NDM2NS05NjAxOWEyMDU0ZWIyN2NmLnBuZz9pbWFnZU1vZ3IyL2F1dG8tb3JpZW50L3N0cmlwJTdDaW1hZ2VWaWV3Mi8yL3cvMTI0MA)
**uriMatcher**：外部若想对当前应用进行访问，必须能够匹配uriMatcher中的uri。两个主要方法：
    * ``UriMatcher.addURI(String authorities, String path, int code)``每个参数 的含义下面都已经有所标明。
    * ``UriMatcher.match(Uri uri)`` 对第三方传递的uri进行匹配。
    ```
    public class UserProvider extends ContentProvider {
        public static final String TAG = "UserProvider";
        private static UriMatcher sUriMatcher = null;
        private static final int MATH_CODE = 1;
    
        static {
            sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
            /**
             * addURI(String authority, String path, int code);
             * authority：添加匹配规则，对应Manifest中的authorities
             * path：一般标识表名 和intent-filter中的schema有点类似
             * code：dangURI被匹配之后会返回后面的matchCode
             */
            sUriMatcher.addURI("cn.wjx.contentprovider", "user", MATH_CODE);
        }
    
        private UserDatabaseHelper mUserDatabaseHelper;
    
        @Override
        public boolean onCreate() {
            // 这里面就可以获取上下文Context，然后初始化操作数据库的Help对象
            mUserDatabaseHelper = new UserDatabaseHelper(getContext());
            return false;
        }
    
        @Override
        public Cursor query( Uri uri,  String[] projection,  String selection,  String[] selectionArgs,  String sortOrder) {
            int match = sUriMatcher.match(uri);
            // 若匹配成功
            if (match == MATH_CODE) {
                SQLiteDatabase sqLiteDatabase = mUserDatabaseHelper.getReadableDatabase();
                return sqLiteDatabase.query(Constants.TB_NAME, projection, selection, selectionArgs, null, null, sortOrder);
            } else {
                throw new IllegalArgumentException("Uri not match!");
            }
        }
    
        
        @Override
        public String getType( Uri uri) {
            return null;
        }
    
        
        @Override
        public Uri insert( Uri uri,  ContentValues values) {
            int match = sUriMatcher.match(uri);
            // 若匹配成功
            if (match == MATH_CODE) {
                SQLiteDatabase sqLiteDatabase = mUserDatabaseHelper.getReadableDatabase();
                long id = sqLiteDatabase.insert(Constants.TB_NAME, null, values);
                Uri uri0 = Uri.parse("content://cn.wjx.contentprovider/user/"+id);
                Log.d(TAG, "insert: user id-->"+id);
                // 插入数据成功，通知其他应用（谁监听通知谁）
                getContext().getContentResolver().notifyChange(uri0, null);
                return uri0;
            } else {
                throw new IllegalArgumentException("Uri not match!");
            }
        }
    
        @Override
        public int delete( Uri uri,  String selection,  String[] selectionArgs) {
            return 0;
        }
    
        @Override
        public int update( Uri uri,  ContentValues values,  String selection,  String[] selectionArgs) {
            return 0;
        }
    }
    ```


2. 清单文件中进行注册，authorities相当于令牌，外部应用如果想要操作当前app，必须通过设置uri，然后UserProvider类中的UriMathcer会匹配外部app传递过来的uri来查看是否有权限操作当前app中的数据。  
**authorities**可以是多个，以";"进行分隔。
    ```
    <provider
            android:authorities="cn.wjx.contentprovider"
            android:name=".provider.UserProvider"
            android:exported="true"/>
    ```
3. 编写另一APP进行对自定义的ContentProvider进行访问，下面有案例演示，按钮添加点击事件，布局就不再贴代码了，主要贴一些核心代码。  
    **ContetResovler**：我把它叫做内容解析者，通过它和内容提供者的对接，我们才可以去对内容提供者中的数据进行操纵。通过调用上下文对象中的方法**getContentResolver( )** 获取ContentResovler对象，里面提供对内容提供者中数据库操作的API。  
    3.1） 获取数据
    ```
    //一般情况下，我们很少自己去写内容提供者，一般使用其他app的内容提供者，但通过
        // 手写内容提供者我们能更好地理解ContentProvider的内部原理，为接下来使用内容提供者
        // 奠定了基础
        ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse("content://cn.wjx.contentprovider/user");
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String username = cursor.getString(1);
            String password = cursor.getString(3);
            Log.d(TAG, "user-->id:" + id + " username:" + username + " password:" + password);
        }
    ```
    3.2) 插入数据,执行insert之后返回的是Uri对象，一般为表名+标识ID
    ```
        ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse("content://cn.wjx.contentprovider/user");
        ContentValues values = new ContentValues();
        ContentValues cv = new ContentValues();
        cv.put(FIELD_USERNAME, "track");
        cv.put(FIELD_PASSWORD, "xcn");
        cv.put(FIELD_SEX, "female");
        cv.put(FIELD_AGE, 22);
        Uri insert = contentResolver.insert(uri, cv);
        Log.d(TAG, "uri："+insert);
    ```
    ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200408195513522.gif)

* 总结：自定义内容提供者所需要  
    1) 继承ContentProvider
    2) 清单文件中注册
    3) 通过getContentResovler获取内容解析者ContentResovler调用相应API操作  
  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在UserProvider类中当调用insert方法时，对数据库中改变的数据进行了实时反馈，主要是通过``getContext().getContentResolver().notifyChange(uri0, null);``，第三方主要是通过注册内容观察者ContentObserver进行监听，这里例子不是很明显，下面会有短信验证码的例子进行讲解。

### <span id="p3">ContentProvider的应用</span>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;前面代码中我有所说道，一般情况下我们不会自定义内容提供者，谁会白白的访问一个没有任何用途的app去操纵数据库中的数据，相反我们往往会使用系统提供的内容提供者，比如说**日历联系人短信**等对这些数据库中的表进行数据的操作。通过前面使用自定义的内容提供者，接下里我们可以使用系统内容提供者，使用步骤相似。  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在下面使用系统提供的内容提供者，操作步骤无非就是看上层应用源码，**Manifest中找authorities，Provider类中找url和path，注册权限，对表进行操作** ！因为下面的案例都需要动态获取权限，我没有贴相应权限的代码，关于权限的动态获取，[请转到Android6.0+应用权限](https://blog.csdn.net/qq_30591155/article/details/105384703)

#### <span id="p301">案例一获取日历事件，向日历中添加提醒事件</span>
日历内容提供者的相关信息，都可以以从[android开发平台](https://developer.android.google.cn/guide/topics/providers/calendar-provider)查到。  

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200408195557636.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMwNTkxMTU1,size_16,color_FFFFFF,t_70)

常用表：红线标出  
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200408195615107.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMwNTkxMTU1,size_16,color_FFFFFF,t_70)

**1) Calendars表**  
```
    ContentResolver contentResolver = getContentResolver();
    // 1.获取日历相关的Uri，content://authorities/path，从android日历源码Manifest中查找authorities
    //Uri uri = Uri.parse("content://" + CalendarContract.AUTHORITY + "/calendars");
    Uri uri = CalendarContract.Calendars.CONTENT_URI;
    Cursor cursor = contentResolver.query(uri,null,null,null,null);
    String[] columnNames = cursor.getColumnNames();
    while(cursor.moveToNext()) {
        Log.d(TAG,"=============================================");
        for(String columnName : columnNames) {
            Log.d(TAG,"field --- > " + columnName + "value -- > " + cursor.getString(cursor.getColumnIndex(columnName)));
        }
        Log.d(TAG,"=============================================");
    }
    cursor.close();
```
查询到的都是日历表相关的信息，比如日历所属id，日历账户名称...  

**2) Events表**  
获取日历中相关事件，这里我们查询所有，只需替换上文Calendars代码中uri  
``Uri uri = CalendarContract.Events.CONTENT_URI;``
查询事件结果如下：  
```
D/MainActivity: =============================================
D/MainActivity: field --- > originalAllDay;  value -- > null
D/MainActivity: field --- > account_type;  value -- > com.android.huawei
D/MainActivity: field --- > exrule;  value -- > null
D/MainActivity: field --- > mutators;  value -- > com.android.calendar
D/MainActivity: field --- > originalInstanceTime;  value -- > null
D/MainActivity: field --- > allDay;  value -- > 0
D/MainActivity: field --- > allowedReminders;  value -- > 0,1
D/MainActivity: field --- > rrule;  value -- > null
D/MainActivity: field --- > location_longitude;  value -- > c37d19942d3d
D/MainActivity: field --- > canOrganizerRespond;  value -- > 1
D/MainActivity: field --- > lastDate;  value -- > 1582896600000
D/MainActivity: field --- > visible;  value -- > 1
D/MainActivity: field --- > calendar_id;  value -- > 1
D/MainActivity: field --- > hasExtendedProperties;  value -- > 0
D/MainActivity: field --- > calendar_access_level;  value -- > 700
D/MainActivity: field --- > selfAttendeeStatus;  value -- > 0
D/MainActivity: field --- > version;  value -- > 2
D/MainActivity: field --- > allowedAvailability;  value -- > 0,1
D/MainActivity: field --- > eventColor_index;  value -- > null
D/MainActivity: field --- > isOrganizer;  value -- > 1
D/MainActivity: field --- > _sync_id;  value -- > null
D/MainActivity: field --- > event_image_type;  value -- > null
D/MainActivity: field --- > calendar_color_index;  value -- > null
D/MainActivity: field --- > _id;  value -- > 199
D/MainActivity: field --- > guestsCanInviteOthers;  value -- > 1
D/MainActivity: field --- > allowedAttendeeTypes;  value -- > 0,1,2
D/MainActivity: field --- > dtstart;  value -- > 1582893000000
D/MainActivity: field --- > guestsCanSeeGuests;  value -- > 1

D/MainActivity: field --- > eventTimezone;  value -- > Asia/Shanghai
D/MainActivity: field --- > availability;  value -- > 0
D/MainActivity: field --- > title;  value -- > 创新创业项目
D/MainActivity: field --- > ownerAccount;  value -- > Phone

D/MainActivity: field --- > duration;  value -- > null
D/MainActivity: field --- > event_calendar_type;  value -- > 0
D/MainActivity: field --- > lastSynced;  value -- > 0
D/MainActivity: field --- > guestsCanModify;  value -- > 0
D/MainActivity: field --- > rdate;  value -- > null
D/MainActivity: field --- > maxReminders;  value -- > 5
D/MainActivity: field --- > account_name;  value -- > Phone
D/MainActivity: field --- > calendar_color;  value -- > -16733458
D/MainActivity: field --- > cal_sync9;  value -- > null
D/MainActivity: field --- > cal_sync8;  value -- > null
D/MainActivity: field --- > dirty;  value -- > 1
D/MainActivity: field --- > calendar_timezone;  value -- > Asia/Shanghai
D/MainActivity: field --- > accessLevel;  value -- > 0
D/MainActivity: field --- > eventLocation;  value -- > null
D/MainActivity: field --- > location_latitude;  value -- > c37d19942d3df
D/MainActivity: field --- > hasAlarm;  value -- > 1
D/MainActivity: field --- > uid2445;  value -- > null
D/MainActivity: field --- > deleted;  value -- > 0
D/MainActivity: field --- > eventColor;  value -- > null
D/MainActivity: field --- > organizer;  value -- > Phone
D/MainActivity: field --- > eventStatus;  value -- > 1
D/MainActivity: field --- > customAppUri;  value -- > null
D/MainActivity: field --- > canModifyTimeZone;  value -- > 1
D/MainActivity: field --- > eventEndTimezone;  value -- > null
D/MainActivity: field --- > customAppPackage;  value -- > null
D/MainActivity: field --- > original_sync_id;  value -- > null
D/MainActivity: field --- > hasAttendeeData;  value -- > 1
D/MainActivity: field --- > displayColor;  value -- > -16733458
D/MainActivity: field --- > dtend;  value -- > 1582896600000
D/MainActivity: field --- > original_id;  value -- > null
D/MainActivity: field --- > sync_data10;  value -- > null
D/MainActivity: field --- > calendar_displayName;  value -- > Phone
D/MainActivity: =============================================
```
向日历中插入提醒事件，这里结合Reminders表进行插入带有提醒的事件  

**3) Reminders表**  
插入提醒事件，官方说明  
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200408195637204.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMwNTkxMTU1,size_16,color_FFFFFF,t_70)
```
 /**
     * 插入新事件时，必须包含以下字段：
     * dtstart
     * dtend如果事件不是重复发生
     * 事件持续发生的持续时间
     * rrule或rdate（如果事件重复发生）
     * eventTimezone
     * 一个calendar_id
     * @param view
     */
    public void addNewEvents(View view) {
        ContentResolver contentResolver = getContentResolver();
        Uri uri = CalendarContract.Events.CONTENT_URI;
        ContentValues values = new ContentValues();
        putValues(values);
        Uri insert = contentResolver.insert(uri, values);
        String eventId = insert.getLastPathSegment();
        Log.d(TAG, "event id -->" + eventId);
        addEventAlert(eventId);
        Log.d(TAG, "insert result --->" + insert);
    }

    private void putValues(ContentValues values) {
        // 日历ID 必填项，通过查询Events表，在log—cat下查看calendar_idvalue=1
        long calID = 1;
        // java.util下的Calendar，月份基准为0
        Calendar beginTime = Calendar.getInstance();
        // 2020 4-5 12:00
        beginTime.set(2020, 3, 5,12,00);
        Calendar endTime = Calendar.getInstance();
        endTime.set(2020, 3, 5, 13, 00);
        long startMillis = beginTime.getTimeInMillis();
        long endMills = endTime.getTimeInMillis();

        values.put(CalendarContract.Events.CALENDAR_ID, 1);
        // 开始时间
        values.put(CalendarContract.Events.DTSTART, startMillis);
        // 结束时间
        values.put(CalendarContract.Events.DTEND, endMills);
        // 标题
        values.put(CalendarContract.Events.TITLE, "QQ回复");
        // 描述
        values.put(CalendarContract.Events.DESCRIPTION, "QQ收到消息 ");
        // 时间时区
        String timeZone = TimeZone.getDefault().getID();
        Log.d(TAG, "EventTimeZone-->"+ timeZone);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, timeZone);
    }
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200408195707549.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMwNTkxMTU1,size_16,color_FFFFFF,t_70)

#### <span id="p302">案例二获取联系人相应信息</span>
在使用之前，我先在模拟器中新建了几个联系人，然后导出databases文件查看contacts2.db其中的表结构，然后在进行分析！  
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200408195733680.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMwNTkxMTU1,size_16,color_FFFFFF,t_70)

>raw_contacts：包含联系人数据摘要的行，针对特定用户帐户和类型。
mimetypes:数据类型描述表
data:数据表，存储号码，QQ，邮箱，昵称，图片。这些类型都在mimetypes里有。
所以我们要拿一个用户的数据，应该是从raw_contacts里拿到id,再到data拿出数据，根据mimetype来判断数据的类型。
但是现在的android系统不这么玩了。也不直接给你读取mimetypes里的数据了。  

但我们可以通过下面的uri获取
``content://com.android.contacts/data/phones``联系人信息  
```
ContentResolver contentResolver = getContentResolver();
        Uri contentUri = Uri.parse("content://com.android.contacts/raw_contacts");
        Cursor cursor = contentResolver.query(contentUri, null, null, null, null);
        List<Contact> contacts = new ArrayList<>();
        Contact contact = null;
        while (cursor.moveToNext()) {
            contact = new Contact();
            String id = cursor.getString(cursor.getColumnIndex("_id"));
            contact.setId(id);
            contact.setDisplayName(cursor.getString(cursor.getColumnIndex("display_name")));
            Log.d(TAG,"id -- > " + id);
            contacts.add(contact);
        }
        cursor.close();
        Uri phoneNumUri = Uri.parse("content://com.android.contacts/data/phones");
        for(Contact contact1 : contacts) {
            Cursor phoneCursor = contentResolver.query(phoneNumUri,new String[]{"data1"},"raw_contact_id=?",new String[]{contact1.getId()},null);
            if(phoneCursor.moveToNext()) {
                contact1.setPhoneNum(phoneCursor.getString(phoneCursor.getColumnIndex("data1")).replace("-",""));
            }
            phoneCursor.close();
        }

        for(Contact contact1 : contacts) {
            Log.d(TAG,contact1.getId() + " -- NAME --- > " + contact1.getDisplayName() + " --- PHONE -- > " + contact1.getPhoneNum());
        }
```
Contact.java  
```
package cn.wjx.contentprovider_test.model;

/**
 * @author WuChangJian
 * @date 2020/4/5 13:08
 */
public class Contact {
    //ID
    private String id;
    //手机号
    private String phoneNum;
    //显示名称
    private String displayName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
```
篇幅有点长了，还有最后一个案例，fight！

#### <span id="p303">案例三读取短信验证码</span>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;前面自定义内容提供者的时候，我们通过注册ContentObserver,当我们向表中插入一条数据时，可以监听到数据库表的变化，并且可以获取插入后的Uri(table/id)；这就是通过注册内容观察者来实现的，接下来我们通过ContentObserver来监听SMS表中数据的变化，实现短信验证码的动态填充功能。
1. 我们想要了解短信相应表中有哪些字段为我们所用，最好的办法就是查询
    ```
    private void getSMS() {
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://sms");
        Cursor cursor = resolver.query(uri, null, null, null, null);
        String[] columnNames = cursor.getColumnNames();
        while(cursor.moveToNext()) {
            Log.d(TAG,"=============================================");
            for(String columnName : columnNames) {
                Log.d(TAG,"field --- > " + columnName + ";  value -- > " + cursor.getString(cursor.getColumnIndex(columnName)));
            }
            Log.d(TAG,"=============================================");
        }
        cursor.close();
    }
    ```
    从下图可以看到，短信的内容被一个body所索引，body字段是我们需要的一个字段  
   ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200408195814276.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMwNTkxMTU1,size_16,color_FFFFFF,t_70)
2. 注册内容观察者ContentObserver，实现对接受短信的监听  
    注意点，当observer监听到短信的变化之后，onChange会执行两次，第一次不被所需。
    ```
    final ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse("content://sms/");
        ContentObserver observer = new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange, Uri uri) {
                // onChange会执行两次，第二次短信才会入库
                Log.d(TAG, "selfChange--->" + selfChange);
                Log.d(TAG, "uri--->" + uri);
                if (uri.toString().contains("content://sms/raw")) {
                    return;
                }
                Cursor cursor = contentResolver.query(uri, new String[]{"body"}, null, null, null);
                if (cursor != null) {
                    if (cursor.moveToNext()) {
                        String body = cursor.getString(cursor.getColumnIndex("body"));
                        Log.d(TAG, "sms body --> " + body);
                        handleMessage(body);
                    }
                    cursor.close();
                }
            }
        };
        // 通过内容解析器注册观察者
        contentResolver.registerContentObserver(uri, true, observer);
    ```
3. 编码实现额外功能  
    * 倒计时的实现  
        ```
        // 利用CountDownTimer实现按钮的倒计时
        private CountDownTimer mCountDownTimer = new CountDownTimer(总时长毫秒, interval) {
            @Override
            public void onTick(long millisUntilFinished) {
                // 防止用户重复点击,可以将按钮置为禁用等状态
                ...
            }
    
            @Override
            public void onFinish() {
                // 倒计时结束后可做，按钮恢复状态
                ...
            }
        };
        ```
    * 正则表达式匹配短信内容的数字
        ```
        // 这里以六位验证码为例
            Pattern p = Pattern.compile("(?<![0-9])([0-9]{6})(?![0-9])");
            Matcher matcher = p.matcher(body);
            boolean contain = matcher.find();
            if (contain) {
                Log.d(TAG, "VerifyCode --> "+matcher.group());
                // 将验证码设置到UI控件上
                ...
            }
        ```
案例演示

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200408195836258.gif)
