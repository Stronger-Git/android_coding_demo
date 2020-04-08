package cn.wjx.contentprovider.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cn.wjx.contentprovider.utils.Constants;

/**
 * @author WuChangJian
 * @date 2020/4/2 14:35
 */
public class UserDatabaseHelper extends SQLiteOpenHelper {

    public UserDatabaseHelper(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + Constants.TB_NAME + "(" + Constants.FIELD_ID + " integer primary key autoincrement," +
                Constants.FIELD_USERNAME + " varchar(30)," +
                Constants.FIELD_PASSWORD + " varchar(30)," +
                Constants.FIELD_SEX + " varchar(6)," +
                Constants.FIELD_AGE + " integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 数据库升级时调用
    }
}
