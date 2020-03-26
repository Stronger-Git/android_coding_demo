package cn.wjx34t0302;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


/**
 * @author WuChangJian
 * @date 2020/3/10 20:3
 */
public class LoginActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.bt_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etUsername = findViewById(R.id.username);
                EditText etPassword = findViewById(R.id.password);
                RadioButton rbNameToLogin = findViewById(R.id.rb_name_to_login);
                RadioButton rbEmailToLogin = findViewById(R.id.rb_email_to_login);
                TextView tvMsg = findViewById(R.id.tv_msg);
                if (rbNameToLogin.isChecked()) {
                    String username = etUsername.getText().toString();
                    String password = etPassword.getText().toString();
                    if ("stronger".equals(username) && "123".equals(password)) {
                        tvMsg.setText("登录成功！");
                    }
                } else {
                    tvMsg.setText("登录失败！");
                }
            }
        });
    }
}
