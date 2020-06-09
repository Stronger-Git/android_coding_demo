package cn.wjx34t0602.model;

/**
 * @author WuChangJian
 * @date 2020/4/9 10:58
 */
public class Contact {
    private int contactIcon;
    private String contactName;

    public Contact() {
    }

    public Contact(int contactIcon, String contactName) {
        this.contactIcon = contactIcon;
        this.contactName = contactName;
    }

    public int getContactIcon() {
        return contactIcon;
    }

    public void setContactIcon(int contactIcon) {
        this.contactIcon = contactIcon;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
}
