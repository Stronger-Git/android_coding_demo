package cn.wjx34t0504;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SIGN = 0;
    private static final int REQUEST_CODE_REGISTER = 1;
    private Button mBtRegister;
    private Button mBtSign;
    private TextView mTvStatus;
    private TextView mTvUserInfo;
    private TextView mTvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
    }

    private void setListener() {
        mBtSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SIGN);
            }
        });
        mBtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivityForResult(intent, REQUEST_CODE_REGISTER);
            }
        });
    }

    private void initView() {
        mBtRegister = findViewById(R.id.bt_register_main);
        mBtSign = findViewById(R.id.bt_sign_in_main);
        mTvStatus = findViewById(R.id.tv_status);
        mTvUserInfo = findViewById(R.id.tv_userInfo);
        mTvEmail = findViewById(R.id.tv_email);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == REQUEST_CODE_SIGN) {
                mTvStatus.setText("登录成功");
                mTvUserInfo.setText("您好，" + data.getStringExtra("id"));
            } else if (requestCode == REQUEST_CODE_REGISTER) {
                mTvStatus.setText("注册成功");
                mTvUserInfo.setText("ID：" + data.getStringExtra("id"));
                mTvEmail.setText("Email："+ data.getStringExtra("email"));
            }
        }
    }
}
