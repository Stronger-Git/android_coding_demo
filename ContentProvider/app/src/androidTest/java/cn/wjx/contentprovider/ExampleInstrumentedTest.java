package cn.wjx.contentprovider;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import cn.wjx.contentprovider.dao.IUserDao;
import cn.wjx.contentprovider.dao.impl.UserDaoImpl;
import cn.wjx.contentprovider.pojo.User;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("cn.wjx.contentprovider", appContext.getPackageName());
    }

    @Test
    public void addUser() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        IUserDao iUserDao = UserDaoImpl.getInstance(appContext);
        for (int i = 0; i < 5; i++) {
            long l = iUserDao.addUser(new User("user_"+i, "123", "male", 18));
        }
    }

    @Test
    public void listAll() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        IUserDao iUserDao = UserDaoImpl.getInstance(appContext);
        List<User> users = iUserDao.selectAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void getById() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        IUserDao iUserDao = UserDaoImpl.getInstance(appContext);
        User user = iUserDao.selectUserById(1);
        System.out.println(user);
    }

    @Test
    public void delUser() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        IUserDao iUserDao = UserDaoImpl.getInstance(appContext);

    }
}
