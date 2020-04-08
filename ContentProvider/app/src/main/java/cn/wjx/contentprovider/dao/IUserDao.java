package cn.wjx.contentprovider.dao;

import java.util.List;

import cn.wjx.contentprovider.pojo.User;

/**
 * @author WuChangJian
 * @date 2020/4/2 14:34
 */
public interface IUserDao {
    long addUser(User user);
    int delUser(int id);
    int updateUser(User user);
    List<User> selectAllUsers();
    User selectUserById(int id);
}
