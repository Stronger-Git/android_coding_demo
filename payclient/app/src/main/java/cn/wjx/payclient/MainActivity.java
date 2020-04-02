package cn.wjx.payclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cn.wjx.alipay.ThirdPartPayAction;
import cn.wjx.alipay.ThirdPartPayResult;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private ThirdPartPayAction mPayAction;
    private TextView mUserAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mUserAccount = findViewById(R.id.user_account);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent();
        // android5.0以上必须显示启动服务
        intent.setAction("cn.wjx.alipay.THIRD_PART_PAY");
        intent.setPackage("cn.wjx.alipay");
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }

    public void chargeBitCoin(View view) {
        try {
            mPayAction.requestPay("充值BitCoin", 9999F, new PayResult());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected...");
            mPayAction = ThirdPartPayAction.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private class PayResult extends ThirdPartPayResult.Stub {

        @Override
        public void paySuccess() throws RemoteException {
            // 支付成功 渲染界面 实际开发中是要请求服务器
            mUserAccount.setText("100BitCoin");
        }

        @Override
        public void payFailed(int errorCode, String errMsg) throws RemoteException {
            Log.d(TAG, "payFailed...");
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mServiceConnection != null) {
            unbindService(mServiceConnection);
            mServiceConnection = null;
        }
    }
}
