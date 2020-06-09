package cn.wjx34t0602.model;

/**
 * @author WuChangJian
 * @date 2020/4/24 16:08
 */
public class MusicBean {

    public int type;
    public String title;
    // 后期可加入Glide加载网络图片Url
    public int imageId;

    public interface TYPE {
        int TYPE_GRID_THREE = 0x01;
        int TYPE_GRID_TWO = 0x02;
        int TYPE_ONE = 0x03;
        int TYPE_TITLE = 0x04;
    }
}