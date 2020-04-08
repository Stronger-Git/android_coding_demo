package cn.wjx.contentprovider_test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import cn.wjx.contentprovider_test.model.Contact;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    public static String FIELD_USERNAME = "userName";
    public static String FIELD_PASSWORD = "password";
    public static String FIELD_SEX = "sex";
    public static String FIELD_AGE = "age";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkCalendarPermission();
        }
        registerContentObserver();
    }

    public static final int PERMISSION_CODE = 1;
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkCalendarPermission() {
        String[] permissions = {Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR,
                Manifest.permission.READ_CONTACTS,Manifest.permission.WRITE_CONTACTS};
        for (String permission : permissions) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "request permission...");
                requestPermissions(permissions, PERMISSION_CODE);
            } else {
                Log.d(TAG, "user has permit");
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 若请求码对应定义的权限码则执行
        if (requestCode == PERMISSION_CODE) {
            for (int grantResult : grantResults) {
                if (grantResult == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "permit success!");
                } else {
                    Log.d(TAG, "permit fail!");
                    finish();
                }
            }

        }
    }

    /**
     * 插入用户注册内容观察者，监听user表的变化
     */
    private void registerContentObserver() {
        ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse("content://cn.wjx.contentprovider/user");
        contentResolver.registerContentObserver(uri, true, new ContentObserver(new Handler()) {
            @Override
            public boolean deliverSelfNotifications() {
                return super.deliverSelfNotifications();
            }

            @Override
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                Log.d(TAG, "onChange: 用户数据发送改变");
            }

            @Override
            public void onChange(boolean selfChange, Uri uri) {
                super.onChange(selfChange, uri);
                Log.d(TAG, "onChange: 用户数据发送改变 uri"+uri.toString());
            }
        });
    }

    /**
     * 获取user表中的数据
     * @param view
     */
    public void getRemoteData(View view) {
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
    }

    /**
     * 向user表中插入一条记录
     * @param view
     */
    public void addUser(View view) {
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
        //注册监听Uri的变化，onCreate方法
    }

    /**
     * 获取日历表中相关字段
     * @param view
     */
    public void getCalendars(View view) {
        ContentResolver contentResolver = getContentResolver();
        // 1.获取日历相关的Uri，content://authorities/path，从android日历源码Manifest中查找authorities
        //Uri uri = Uri.parse("content://" + CalendarContract.AUTHORITY + "/calendars");
        Uri uri = CalendarContract.Events.CONTENT_URI;
        Cursor cursor = contentResolver.query(uri,null,null,null,null);
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

    /**
     * 插入【提醒】事件时，必须包含以下字段：
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
        beginTime.set(2020, 3, 6,15,30);
        Calendar endTime = Calendar.getInstance();
        endTime.set(2020, 3, 6, 16, 00);
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

    /**
     * Fields and helpers for accessing reminders for an event.
     * Each row of this table represents a single reminder for an event.
     * Calling query(android.content.ContentResolver, long, java.lang.String[]) will
     * return a list of reminders for the event with the given eventId. Both apps and sync adapters
     * may write to this table. There are three writable fields and all of them must be included when inserting a new reminder.
     * They are:
     *
     * CalendarContract.RemindersColumns.EVENT_ID
     * CalendarContract.RemindersColumns.MINUTES
     * CalendarContract.RemindersColumns.METHOD
     */
    public void addEventAlert(String eventId) {
        ContentResolver contentResolver = getContentResolver();
        Uri uri = CalendarContract.Reminders.CONTENT_URI;
        ContentValues values = new ContentValues();
        //(1) logcat events表得到event_id，不使用
        //(2) 因为插入在插入一条事件之后，通过调用
        values.put(CalendarContract.Reminders.EVENT_ID, eventId);
        // 20min前进行提醒
        values.put(CalendarContract.Reminders.MINUTES, 20);
        values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT );
        Uri insert = contentResolver.insert(uri, values);
        Log.d(TAG, "insert event alert result --->" + insert);
    }

    /**
     * 获取联系人数据
     * @param view
     */
    public void accessContacts(View view) {
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
    }

    /**
     * 读取短信验证码
     * @param view
     */
    public void readSms(View view) {
        startActivity(new Intent(this, VerifyCodeActivity.class));
    }
}
