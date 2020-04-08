package cn.wjx34t0504;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * @author WuChangJian
 * @date 2020/4/7 20:48
 */
public class RegisterActivity extends Activity {
    private EditText mId;
    private EditText mPassword;
    private EditText mEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        setListener();
    }
    private void setListener() {
        findViewById(R.id.bt_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = mId.getText().toString();
                String password = mPassword.getText().toString();
                String email = mEmail.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("id", id);
                intent.putExtra("password", password);
                intent.putExtra("email", email);
                setResult(0, intent);
                finish();
            }
        });
    }

    private void initView() {
        mId = findViewById(R.id.ev_id_register);
        mPassword = findViewById(R.id.ev_password_register);
        mEmail = findViewById(R.id.ev_email_register);
    }
}
