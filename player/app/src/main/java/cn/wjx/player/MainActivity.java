package cn.wjx.player;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import cn.wjx.player.interfaces.IPlayerControl;
import cn.wjx.player.interfaces.IPlayerViewControl;
import cn.wjx.player.service.PlayerService;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    private SeekBar mSeekBar;
    private Button mBtPlayOrPause;
    private Button mBtRestart;
    private IPlayerControl mPlayerControl;
    private boolean isUserTouch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grantPrivilege();
        initView();
        initEvent();
    }

    private void grantPrivilege() {
        /**
         * 动态获取权限，Android 6.0+ 新特性，一些保护权限，除了要在AndroidManifest中声明权限，还要使用如下代码动态获取
         */
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                }
            }
        }
    }

    //初始化服务，因为需要跑在后台，所以需要start->bind结合
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, PlayerService.class);
        startService(intent);
        bindService(intent, mPlayerConnection, BIND_AUTO_CREATE);
        Log.d(TAG, "onStart: ");
    }
    private ServiceConnection mPlayerConnection = new ServiceConnection() {
        // 当服务的onBind方法返回null时，内部方法不会被调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected...");
            mPlayerControl = (IPlayerControl) service;
            // 注册视图控制器
            mPlayerControl.registerViewControl(mPlayerViewControl);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected...");
            mPlayerControl.stopPlay();
            mPlayerControl.unRegisterViewControl();
            mPlayerControl = null;
        }
    };
    private IPlayerViewControl mPlayerViewControl = new IPlayerViewControl() {
        @Override
        public void onPlayerStateChange(int state) {
            // 更新UI
            switch (state) {
                case IPlayerControl.PLAY_STATE_PLAY:
                    mBtPlayOrPause.setText("暂停");
                    break;
                case IPlayerControl.PLAY_STATE_PAUSE:
                case IPlayerControl.PLAY_STATE_STOP:
                    mBtPlayOrPause.setText("播放");
                    break;
            }
        }

        @Override
        public void onSeekChange(final int seek) {
            /**
             * 默认情况下主线程不允许更新UI，seekBar和surfaceView除外
             *
             */
            if (!isUserTouch) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mSeekBar.setProgress(seek);
                    }
                });

            }
        }
    };

    private void initEvent() {
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // 进度条发生改变时触发
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "onProgressChanged-->progress+" + progress);

            }
            // 开始触摸时触发
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isUserTouch = true;
            }
            // 停止触摸时触发
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isUserTouch = false;
                int touchProgress = seekBar.getProgress();
                Log.d(TAG, "stopTouchProgress-->"+touchProgress);
                if (mPlayerControl != null) {
                    mPlayerControl.seekTo(touchProgress);
                }
            }
        });

        mBtPlayOrPause.setOnClickListener(new View.OnClickListener() {
            // 播放或暂停
            @Override
            public void onClick(View v) {
                if (mPlayerControl != null) {
                    mPlayerControl.playOrPause();
                }
            }
        });
        mBtRestart.setOnClickListener(new View.OnClickListener() {
            // 停止播放
            @Override
            public void onClick(View v) {
                if (mPlayerControl != null) {
                    mPlayerControl.stopPlay();
                }
            }
        });
    }

    private void initView() {
        mSeekBar = findViewById(R.id.seekBar);
        mBtPlayOrPause = findViewById(R.id.play_or_pause);
        mBtRestart = findViewById(R.id.stop);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 解绑服务？不是要跑于后台吗？
        if (mPlayerConnection != null) {
            Log.d(TAG, "unBindService...");
            unbindService(mPlayerConnection);
        }
    }

}
