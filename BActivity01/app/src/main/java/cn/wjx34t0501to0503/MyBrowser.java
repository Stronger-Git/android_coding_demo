package cn.wjx34t0501to0503;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author WuChangJian
 * @date 2020/3/27 9:12
 */
public class MyBrowser extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        TextView tvUri = findViewById(R.id.tv_url);
        String dataUri = getIntent().getDataString();
        tvUri.setText(dataUri);
    }
}
