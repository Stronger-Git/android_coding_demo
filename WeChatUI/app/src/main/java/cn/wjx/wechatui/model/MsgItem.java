package cn.wjx.wechatui.model;

/**
 * @author WuChangJian
 * @date 2020/5/4 10:08
 */
public class MsgItem {
    private int icon;
    private String title;
    private String content;
    private String date;

    public MsgItem() {
    }

    public MsgItem(int icon, String title, String content, String date) {
        this.icon = icon;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
