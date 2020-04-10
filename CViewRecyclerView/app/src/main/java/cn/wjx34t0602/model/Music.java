package cn.wjx34t0602.model;

/**
 * @author WuChangJian
 * @date 2020/4/9 21:20
 */
public class Music {
    private int icon;
    private String musicName;
    private String singer;

    public Music(){}
    public Music(int icon, String musicName, String singer) {
        this.icon = icon;
        this.musicName = musicName;
        this.singer = singer;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
