package cn.wjx.alipay;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author WuChangJian
 * @date 2020/3/29 15:34
 */
public class PayActivity extends Activity {
    public static final String TAG = "PayActivity";
    private Button mPayCommit;
    private TextView mPayOrderInfo;
    private TextView mPayMoney;
    private EditText mPayPassword;
    private PayService.PayAction mPayAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        initView();
        doPay();
    }
    private void doPay() {
        mPayCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPayAction != null) {
                    String password = mPayPassword.getText().toString();
                    if ("123".equals(password)) {
                        mPayAction.pay(100f);
                        Toast.makeText(PayActivity.this, "支付成功 ", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "pay finish");
                        finish();
                    } else {
                        Toast.makeText(PayActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mPayAction.onUserCancel();
        Toast.makeText(PayActivity.this, "取消支付", Toast.LENGTH_LONG).show();
    }
    /**
     * 初始化组件展示订单信息信息
     */
    private void initView() {
        Intent intent = getIntent();
        String payOrderInfo = intent.getStringExtra(Constants.PAY_ORDER_INFO);
        float payMoney = intent.getFloatExtra(Constants.PAY_MONEY, 0f);
        mPayCommit = findViewById(R.id.pay_commit);
        mPayOrderInfo = findViewById(R.id.pay_order_info);
        mPayOrderInfo.setText("账单：" + payOrderInfo);
        mPayMoney = findViewById(R.id.pay_money);
        mPayMoney.setText("支付金额：" + payMoney + "元");
        mPayPassword = findViewById(R.id.pay_password);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, PayService.class);
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if (service != null) {
                mPayAction = (PayService.PayAction) service;
            }
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    /**
     * 解绑服务释放资源
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mServiceConnection != null) {
            unbindService(mServiceConnection);
            mServiceConnection = null;
        }
    }
}
