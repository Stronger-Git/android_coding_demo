package cn.wjx.player.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import cn.wjx.player.presenter.PlayerPresenter;


/**
 * @author WuChangJian
 * @date 2020/4/1 8:30
 */
public class PlayerService extends Service {
    public static final String TAG = "PlayerService";
    private PlayerPresenter mPlayerPresenter;


    // 将内部类抽取为表现层的PlayerPresenter


    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();
        if (mPlayerPresenter == null) {
            mPlayerPresenter = new PlayerPresenter();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mPlayerPresenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPlayerPresenter = null;
    }
}
