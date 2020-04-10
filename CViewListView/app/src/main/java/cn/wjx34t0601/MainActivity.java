package cn.wjx34t0601;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.wjx34t0601.adapter.ContactAdapter;
import cn.wjx34t0601.model.Contact;
import cn.wjx34t0601.util.Constant;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private ListView mListView;
    private List<Contact> mDatas;
    private ContactAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        setAdapter();
        setListener();
    }

    // 2.设置适配器
    private void setAdapter() {
        mAdapter = new ContactAdapter(this, mDatas);
        mListView.setAdapter(mAdapter);
    }
    // 3.设置监听
    private boolean isRefreshRoot;// 是否滑动到底部，滑动到底部再去更新
    private void setListener() {
        // 3.1 为条目添加点击事件监听
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = mDatas.get(position);
                Toast.makeText(MainActivity.this, "您点击了第"+position+"个条目，姓名为："+contact.getContactName(), Toast.LENGTH_SHORT).show();
            }
        });
        // 3.2 为条目添加滚动事件监听
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {

            // 滚动有三种状态，看源码可得知
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 停止滚动--新增一条数据
                if (scrollState == SCROLL_STATE_IDLE && isRefreshRoot) {
                    isRefreshRoot = false;
                    mDatas.add(new Contact(R.drawable.shape_image, "新增数据"));
                    mAdapter.notifyDataSetChanged();
                }
            }


            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.d(TAG, "onScroll: firstVisibleItem-->" + firstVisibleItem + " visibleItemCount-->" + visibleItemCount + " totalItemCount-->" + totalItemCount);
                if (firstVisibleItem + visibleItemCount == totalItemCount)
                    isRefreshRoot = true;
                else
                    isRefreshRoot = false;

            }
        });

    }


    // 1.初始化数据
    private void initData() {

        mDatas = new ArrayList<>();
        for (int i = 0; i < Constant.icons.length; i++) {
            Contact contact = new Contact(Constant.icons[i], Constant.names[i]);
            mDatas.add(contact);
        }
    }

    private void initView() {
        mListView = findViewById(R.id.lit_view);
    }
}
