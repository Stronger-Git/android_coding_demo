package cn.wjx34t0501to0503;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * @author WuChangJian
 * @date 2020/3/27 8:52
 */
public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        if (bundle != null) {
            String name = bundle.getString("name");
            int age = bundle.getInt("age");
            Toast.makeText(this, "姓名:" + name + " 年龄:" + age, Toast.LENGTH_LONG).show();
        }
    }


}
