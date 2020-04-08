package cn.wjx.contentprovider.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import cn.wjx.contentprovider.db.UserDatabaseHelper;
import cn.wjx.contentprovider.utils.Constants;

/**
 * @author WuChangJian
 * @date 2020/4/4 15:07
 */
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
