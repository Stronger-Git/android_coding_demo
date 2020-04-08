package cn.wjx.contentprovider.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.wjx.contentprovider.dao.IUserDao;
import cn.wjx.contentprovider.db.UserDatabaseHelper;
import cn.wjx.contentprovider.pojo.User;
import cn.wjx.contentprovider.utils.Constants;

/**
 * @author WuChangJian
 * @date 2020/4/2 14:53
 */
public class UserDaoImpl implements IUserDao {

    private UserDatabaseHelper mDatabaseHelper;
    private static IUserDao sInstance;
    /**
     * 对外封装,确保外面只能拿到单一实例
     * @param context
     */
    private UserDaoImpl(Context context) {
        mDatabaseHelper = new UserDatabaseHelper(context);
    }

    /**
     * 懒汉模式+local确保线程安全
     * @return
     */
    public static IUserDao getInstance(Context context) {
        if (sInstance == null) {
            synchronized (UserDaoImpl.class) {
                sInstance = new UserDaoImpl(context);
            }
        }
        return sInstance;
    }

    @Override
    public long addUser(User user) {
        long insert = 0;
        SQLiteDatabase sd = mDatabaseHelper.getWritableDatabase();
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.FIELD_USERNAME, user.getUserName());
            cv.put(Constants.FIELD_PASSWORD, user.getPassword());
            cv.put(Constants.FIELD_SEX, user.getSex());
            cv.put(Constants.FIELD_AGE, user.getAge());
            // 事务管理提高效率
            sd.beginTransaction();
            // 底层源码插入失败返回-1
            // @return the row ID of the newly inserted row, or -1 if an error occurred
            insert = sd.insert(Constants.TB_NAME, null, cv);
            sd.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sd.endTransaction();
            sd.close();
        }
        return insert;
    }

    @Override
    public int delUser(int id) {
        SQLiteDatabase sd = mDatabaseHelper.getWritableDatabase();
        int delete = 0;
        try {
            sd.beginTransaction();
            delete = sd.delete(Constants.TB_NAME, Constants.FIELD_ID+"=?", new String[]{String.valueOf(id)});
            sd.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sd.endTransaction();
            sd.close();
        }
        return delete;
    }

    @Override
    public int updateUser(User user) {
        SQLiteDatabase sd = mDatabaseHelper.getWritableDatabase();
        int update = 0;
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.FIELD_USERNAME, user.getUserName());
            cv.put(Constants.FIELD_PASSWORD, user.getPassword());
            cv.put(Constants.FIELD_SEX, user.getSex());
            cv.put(Constants.FIELD_AGE, user.getAge());
            sd.beginTransaction();
            update = sd.update(Constants.TB_NAME, cv, Constants.FIELD_ID + "=?", new String[]{String.valueOf(user.get_id())});
            sd.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sd.endTransaction();
            sd.close();
        }

        return update;
    }

    @Override
    public List<User> selectAllUsers() {
        SQLiteDatabase sd = mDatabaseHelper.getWritableDatabase();
        List<User> users = null;
        User user;
        try {
            sd.beginTransaction();
            Cursor cursor = sd.query(Constants.TB_NAME, null, null, null, null, null, null);
            users = new ArrayList<>();
            while (cursor.moveToNext()) {
                user = getUser(cursor);
                users.add(user);
            }
            sd.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sd.endTransaction();
            sd.close();
        }

        return users;
    }

    private User getUser(Cursor cursor) {
        User user;
        String userName = cursor.getString(1);
        String password = cursor.getString(2);
        String sex = cursor.getString(3);
        int age = cursor.getInt(4);
        user = new User(userName, password, sex, age);
        return user;
    }

    @Override
    public User selectUserById(int id) {
        SQLiteDatabase sd = mDatabaseHelper.getWritableDatabase();
        User user = null;
        try {
            sd.beginTransaction();
            Cursor cursor = sd.query(Constants.TB_NAME, null, Constants.FIELD_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
            if (cursor.moveToNext()) {
                user = getUser(cursor);
            }
            sd.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sd.endTransaction();
            sd.close();
        }

        return user;
    }
}
