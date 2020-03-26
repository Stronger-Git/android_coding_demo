package cn.wjx34t0302to0308;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 线性布局
        //setContentView(R.layout.activity_linear);
        // 相对布局
        setContentView(R.layout.activity_relative);
    }
}
