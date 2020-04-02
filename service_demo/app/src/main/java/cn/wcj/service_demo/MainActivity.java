package cn.wcj.service_demo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cn.wcj.service_demo.service.FirstService;
import cn.wcj.service_demo.service.IServiceControl;
import cn.wcj.service_demo.service.SecondService;
import cn.wcj.service_demo.service.ThirdService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity";
    // 内部类私有化之后不能再访问，使用接口来更好地控制访问权限
    //private SecondService.CommunicateBinder mCommunicateBinder;
    private IServiceControl mServiceControl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.bt_start).setOnClickListener(this);
        findViewById(R.id.bt_stop).setOnClickListener(this);
        findViewById(R.id.bt_bind).setOnClickListener(this);
        findViewById(R.id.bt_unbind).setOnClickListener(this);
        findViewById(R.id.bt_call_method).setOnClickListener(this);
    }

    public void startService(View view) {
        Intent intent = new Intent(this, FirstService.class);
        startService(intent);
    }

    public void stopService(View view) {
        Intent intent = new Intent(this, FirstService.class);
        stopService(intent);
    }
    public void bindService(View view) {
        Intent intent = new Intent(this, SecondService.class);
        //第一个是参数是意图对象,第二个参数是回调,第三个参数是标记,这个是自动创建的意,如果服务没有start,那么会自己创建。
        // bindService(Intent service,ServiceConnection conn,int flags)
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected...");
            if (service instanceof IServiceControl) {
                mServiceControl = (IServiceControl) service;
            }
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected...");
        }
    };

    public void callInnerMethod(View view) {
        if (mServiceControl != null) {
            mServiceControl.callInnerMethod();
        }
    }

    public void unbindService(View view) {
        Intent intent = new Intent(this, FirstService.class);
        unbindService(mServiceConnection);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ThirdService.class);
        switch (v.getId()) {
            case R.id.bt_start:
                startService(intent);
                break;
            case R.id.bt_stop:
                stopService(intent);
                break;
            case R.id.bt_bind:
                bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.bt_unbind:
                unbindService(mServiceConnection);
                break;
            case R.id.bt_call_method:
                if (mServiceControl != null) {
                    mServiceControl.callInnerMethod();
                }
                break;
        }
    }

    public void finnerMethod(View view) {
        FirstService firstService = new FirstService();
        firstService.innerMethod();
    }
}
