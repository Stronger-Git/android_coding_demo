package cn.wjx.contentprovider.pojo;

/**
 * @author WuChangJian
 * @date 2020/4/2 14:31
 */
public class User {
    private String _id;
    private String userName;
    private String password;
    private String sex;
    private int age;

    public User(){}
    public User(String userName, String password, String sex, int age) {
        this.userName = userName;
        this.password = password;
        this.sex = sex;
        this.age = age;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id='" + _id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
