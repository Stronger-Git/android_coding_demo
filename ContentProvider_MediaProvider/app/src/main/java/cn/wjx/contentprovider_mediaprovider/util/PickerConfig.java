package cn.wjx.contentprovider_mediaprovider.util;

import java.util.List;

import cn.wjx.contentprovider_mediaprovider.model.PhotoItem;

/**
 * @author WuChangJian
 * @date 2020/4/12 8:55
 */
public class PickerConfig {

    private int imageSelectCount = 1;
    private OnImagesSelectFinishedListener mSelectFinishedListener;
    private static PickerConfig sPickerConfig;

    private PickerConfig() {}

    // 单例模式
    public static PickerConfig getInstance() {
        if (sPickerConfig == null) {
            sPickerConfig = new PickerConfig();
        }
        return sPickerConfig;
    }

    public interface OnImagesSelectFinishedListener {
        void onSelectFinished(List<PhotoItem> photoItems);
    }

    public void setOnImagesSelectFinishedListener(OnImagesSelectFinishedListener selectFinishedListener) {
        this.mSelectFinishedListener = selectFinishedListener;
    }

    public OnImagesSelectFinishedListener getSelectFinishedListener() {
        return mSelectFinishedListener;
    }

    public int getImageSelectCount() {
        return imageSelectCount;
    }

    public void setImageSelectCount(int imageSelectCount) {
        this.imageSelectCount = imageSelectCount;
    }
}
