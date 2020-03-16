package cn.wcj.recyclerview.bean;

/**
 * @author WuChangJian
 * @date 2020/3/15 9:04
 */
public class MultiTypeBean {
    private int[] icon;
    private int itemType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public int[] getIcon() {
        return icon;
    }

    public void setIcon(int[] icon) {
        this.icon = icon;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }


}
