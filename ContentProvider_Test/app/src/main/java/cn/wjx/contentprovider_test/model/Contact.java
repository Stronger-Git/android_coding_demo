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
