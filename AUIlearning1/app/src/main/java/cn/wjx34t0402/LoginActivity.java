package cn.wjx34t0402;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


/**
 * @author WuChangJian
 * @date 2020/3/10 20:3
 */
public class LoginActivity extends Activity {

    private Button mBtRegister;

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
                        showToast("登录成功！");
                    }
                } else {
                    showToast("登录失败！");
                }
            }
        });
        mBtRegister = findViewById(R.id.bt_register);
        mBtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
//                showAlertDialog();
            }
        });
    }

    private void showCustomDialog() {
        CustomDialog customDialog = new CustomDialog(this, new CustomDialog.CustomDialogListener() {
            @Override
            public void customDialogClick(boolean isConfirm) {
                if (isConfirm) {
                    showToast("用户已授权");
                } else {
                    showToast("用户未授权");
                }
            }
        });
        customDialog.show();
    }

    // AlertDialog 警告提示框
    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("用户协议确认");
        builder.setMessage("用户同意所有注册协议条款并完成注册程序,才能成为本站的正式用户。");
        //AlertDialog 监听事件的绑定采用DialogInterface.OnClickListener
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showPopupWindow();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showToast("注册失败，用户未授权");
            }
        });
        builder.show();
    }

    private void showPopupWindow() {
        View contentView = getLayoutInflater().inflate(R.layout.popup_content, null);
        PopupWindow popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        // 指定在注册按钮下方弹出
//        popupWindow.showAsDropDown(mBtRegister, 0,0);
        // 指定在屏幕底部弹出
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

    }

    private void showToast(String msg) {
        //1.1) 使用Toast静态方法 显示提示框
        //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        //1.2) 自定义Toast的显示位置
        //Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        //toast.setGravity(Gravity.CENTER, 0, 0);
        //toast.show();
        //2.1)使用toast.getView方法得到View，为其自定义控件
        //Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        //LinearLayout view = (LinearLayout) toast.getView();
        //ImageView imageView = new ImageView(this);
        //imageView.setBackgroundResource(R.mipmap.ic_launcher);
        //view.addView(imageView);
        //toast.setGravity(Gravity.CENTER, 0, 0);
        //toast.show();
        //2.2)设置Toast加载自定义布局
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        View view = getLayoutInflater().inflate(R.layout.toast_layout, null);
        TextView tvTip = view.findViewById(R.id.tv_tip);
        tvTip.setText(msg);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(view);
        toast.show();

    }
}
