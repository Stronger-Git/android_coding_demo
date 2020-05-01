package cn.wjx34t0701;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.wjx34t0701.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private List<View> mViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化视图
        View view01 = getLayoutInflater().inflate(R.layout.pager01, null);
        View view02 = getLayoutInflater().inflate(R.layout.pager02, null);
        View view03 = getLayoutInflater().inflate(R.layout.pager03, null);
        mViews.add(view01);
        mViews.add(view02);
        mViews.add(view03);
        ViewPager viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter();
        adapter.setViews(mViews);
        viewPager.setAdapter(adapter);
    }


}
