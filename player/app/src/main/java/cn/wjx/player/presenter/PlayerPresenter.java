package cn.wjx.player.presenter;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.util.Log;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import cn.wjx.player.interfaces.IPlayerControl;
import cn.wjx.player.interfaces.IPlayerViewControl;



/**
 * @author WuChangJian
 * @date 2020/4/1 9:20
 */
public class PlayerPresenter extends Binder implements IPlayerControl {

    private static final String TAG = "PlayerPresenter";
    private int currentPlayerState = PLAY_STATE_STOP;

    private IPlayerViewControl mPlayerViewControl;
    private MediaPlayer mMediaPlayer;
    private Timer mTimer;
    private SeekTimeTask mSeekTimeTask;

    @Override
    public void registerViewControl(IPlayerViewControl playerViewControl) {
        this.mPlayerViewControl = playerViewControl;
    }

    @Override
    public void unRegisterViewControl() {
        mPlayerViewControl = null;
    }

    @Override
    public void playOrPause() {
        Log.d(TAG, "playOrPause: ");

        if (currentPlayerState == PLAY_STATE_STOP) {
            // 创建一个播放器
            initMediaPlayer();
            // 设置数据源
            try {
                mMediaPlayer.setDataSource("/mnt/sdcard/song.mp3");
                mMediaPlayer.prepare();
                mMediaPlayer.start();
                currentPlayerState = PLAY_STATE_PLAY;
                startTimer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (currentPlayerState == PLAY_STATE_PLAY) {
            mMediaPlayer.pause();
            stopTimer();
            currentPlayerState = PLAY_STATE_PAUSE;
        } else if (currentPlayerState == PLAY_STATE_PAUSE) {
            mMediaPlayer.start();
            startTimer();
            currentPlayerState = PLAY_STATE_PLAY;
        }
        if (mPlayerViewControl != null) {
            mPlayerViewControl.onPlayerStateChange(currentPlayerState);
        }

    }

    private void initMediaPlayer() {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    @Override
    public void stopPlay() {
        stopTimer();
        currentPlayerState = PLAY_STATE_STOP;
        // 释放资源
        Log.d(TAG, "stopPlay: ");
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
            if (mPlayerViewControl != null) {
                mPlayerViewControl.onPlayerStateChange(currentPlayerState);
                mPlayerViewControl.onSeekChange(0);
            }
        }
    }

    @Override
    public void seekTo(int progress) {
        Log.d(TAG, "seekTo: " + progress);
        if (mMediaPlayer != null) {
            int tarSeek = (int) (progress*1.0f/100*mMediaPlayer.getDuration());
            Log.d(TAG, "tarSeek: "+tarSeek);
            mMediaPlayer.seekTo(tarSeek);
        }
    }

    /**
     * 开启一个timerTask
     */
    private void startTimer() {
        if (mTimer == null) {
            mTimer = new Timer();
        }
        if (mSeekTimeTask == null) {
            mSeekTimeTask = new SeekTimeTask();
        }
        mTimer.schedule(mSeekTimeTask, 0, 500);
    }

    private void stopTimer() {
        if (mSeekTimeTask != null) {
            mSeekTimeTask.cancel();
            mSeekTimeTask = null;
        }
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    private class SeekTimeTask extends TimerTask {

        @Override
        public void run() {
            // 获取当前的播放进度
            if (mMediaPlayer != null) {
                int currentPosition = mMediaPlayer.getCurrentPosition();
                Log.d(TAG, "current play position -- >" + currentPosition);
                Log.d(TAG, "duration -->" + mMediaPlayer.getDuration());
                // 将音频转化为进度条范围内的值 以进度条的进度值去改变进度
                int curPosition =(int) (currentPosition*1.0f / mMediaPlayer.getDuration()*100);
                Log.d(TAG, "current / duration -->" + curPosition);
                if (mPlayerViewControl != null) {
                    mPlayerViewControl.onSeekChange(curPosition);
                }
            }
         }
    }
}
