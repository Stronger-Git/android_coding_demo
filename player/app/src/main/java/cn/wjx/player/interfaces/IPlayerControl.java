package cn.wjx.player.interfaces;

/**
 * @author WuChangJian
 * @date 2020/4/1 8:29
 */
public interface IPlayerControl {
    // 播放状态
    int PLAY_STATE_PLAY = 1;
    int PLAY_STATE_PAUSE = 2;
    int PLAY_STATE_STOP = 3;

    /**
     * 把UI的控制接口设置给逻辑层
     */
    void registerViewControl(IPlayerViewControl playerViewControl);
    void unRegisterViewControl();
    void playOrPause();
    void stopPlay();
    // 调整播放进度
    void seekTo(int progress);
}
