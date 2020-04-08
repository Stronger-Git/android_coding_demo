package cn.wjx.contentprovider_test;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.UriMatcher;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author WuChangJian
 * @date 2020/4/8 14:49
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class VerifyCodeActivity extends Activity {

    public static final String TAG = "VerifyCodeActivity";
    private static final int REQUEST_READ_SMS = 1;
    private EditText mVerifyCode;
    private EditText mPhone;
    private Button mBtCode;
    private Button mBtVerify;


    // 利用CountDownTimer实现按钮的倒计时
    private CountDownTimer mCountDownTimer = new CountDownTimer(10*1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            // 防止用户重复点击
            mBtCode.setEnabled(false);
            mBtCode.setText("重新获取("+millisUntilFinished/1000+"s)");
        }

        @Override
        public void onFinish() {
            mBtCode.setEnabled(true);
            mBtCode.setFocusable(true);
            mBtCode.setText("获取验证码");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code);
        //权限校验，这里对权限进行了简单的处理
        checkSmsPermission();
        initView();
        setListener();
        //注册短信内容变化的观察者
        registerObserver();
    }

    private void registerObserver() {
        final ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse("content://sms/");
        ContentObserver observer = new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange, Uri uri) {
                // onChange会执行两次，第二次短信才会入库
                Log.d(TAG, "selfChange--->" + selfChange);
                Log.d(TAG, "uri--->" + uri);
                if (uri.toString().contains("content://sms/raw")) {
                    return;
                }
                Cursor cursor = contentResolver.query(uri, new String[]{"body"}, null, null, null);
                if (cursor != null) {
                    if (cursor.moveToNext()) {
                        String body = cursor.getString(cursor.getColumnIndex("body"));
                        Log.d(TAG, "sms body --> " + body);
                        handleMessage(body);
                    }
                    cursor.close();
                }
            }
        };
        // 通过内容解析器注册观察者
        contentResolver.registerContentObserver(uri, true, observer);
    }

    /**
     * 利用正则将短信内容的验证码提取出来
     * @param body
     */
    private void handleMessage(String body) {

        if (body != null && body.startsWith("【Github注册中心】")) {
            // 这里以六位验证码为例
            Pattern p = Pattern.compile("(?<![0-9])([0-9]{6})(?![0-9])");
            Matcher matcher = p.matcher(body);
            boolean contain = matcher.find();
            if (contain) {
                Log.d(TAG, "VerifyCode --> "+matcher.group());
                // 将验证码设置到UI控件上
                mVerifyCode.setText(matcher.group());
            }
        }
    }


    /**
     * 动态申请短信读权限[短信写权限已经被Google收回]；获取验证码，读权限已经满足需求
     */
    private void checkSmsPermission() {
        if (checkSelfPermission(Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_SMS}, REQUEST_READ_SMS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "onRequestPermissionsResult: 权限已授予");
        }

    }

    private void setListener() {
        // 获取验证码:正常的流程应当是向网络发起请求-->服务器向当前填写的手机号发送短信
        // 这里我们借助模拟器发送一条短信来模拟这个过程
        mBtCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = mPhone.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(VerifyCodeActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 向模拟器发送一条短信 人工操作
                // 按钮开始倒计时
                mCountDownTimer.start();
                //查看SMS表字段
                //getSMS();
            }
        });
        // 对验证码进行校验
        mBtVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = mPhone.getText().toString();
                String verifyCode = mVerifyCode.getText().toString();
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(verifyCode)) {
                    Toast.makeText(VerifyCodeActivity.this, "手机号或验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }


    /**
     * 读取短信表中的字段和值
     * 通过log可以看到body存放我们的短信内容
     */
    private void getSMS() {
        ContentResolver resolver = getContentResolver();
        Uri uri = Uri.parse("content://sms");
        Cursor cursor = resolver.query(uri, null, null, null, null);
        String[] columnNames = cursor.getColumnNames();
        while(cursor.moveToNext()) {
            Log.d(TAG,"=============================================");
            for(String columnName : columnNames) {
                Log.d(TAG,"field --- > " + columnName + ";  value -- > " + cursor.getString(cursor.getColumnIndex(columnName)));
            }
            Log.d(TAG,"=============================================");
        }
        cursor.close();
    }

    private void initView() {
        mPhone = findViewById(R.id.phone);
        mVerifyCode = findViewById(R.id.verify_code);
        mBtCode = findViewById(R.id.bt_phone);
        mBtVerify = findViewById(R.id.bt_verify_code);
    }
}
