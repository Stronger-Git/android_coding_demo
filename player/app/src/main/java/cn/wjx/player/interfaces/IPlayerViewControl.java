package cn.wjx.player.interfaces;

/**
 * @author WuChangJian
 * @date 2020/4/1 8:40
 */
public interface IPlayerViewControl {
    /**
     * 播放状态的改变
     * @param state
     */
    void onPlayerStateChange(int state);

    /**
     * 播放进度的改变
     * @param seek
     */
    void onSeekChange(int seek);
}
