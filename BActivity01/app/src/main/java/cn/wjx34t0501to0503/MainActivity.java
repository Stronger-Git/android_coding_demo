package cn.wjx34t0501to0503;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSecond(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putCharSequence("name", "wuchangjian");
        bundle.putInt("age", 22);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }

    public void startBrowser(View view) {
        Uri uri = Uri.parse("http://www.baidu.com");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public static final int REQUEST_CODE = 0;
    public void startOther(View view) {
        Intent intent = new Intent(this, OtherActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if (data != null) {
                    String result = data.getStringExtra("content");
                    TextView tvResult = findViewById(R.id.tv_result);
                    tvResult.setText(result);
                }
                break;
            default:
                break;
        }


    }
}
