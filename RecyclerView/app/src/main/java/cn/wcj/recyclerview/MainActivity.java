package cn.wcj.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


import cn.wcj.recyclerview.adapter.BaseAdapter;
import cn.wcj.recyclerview.adapter.GridViewAdapter;
import cn.wcj.recyclerview.adapter.ListViewAdapter;
import cn.wcj.recyclerview.adapter.StaggerAdapter;
import cn.wcj.recyclerview.bean.ItemBean;
import cn.wcj.recyclerview.util.Datas;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private List<ItemBean> mDatas;
    private BaseAdapter mAdapter;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 1. 找到控件 RecyclerView控件 SwipeRefreshLayout下拉刷新控件
        mRecyclerView = findViewById(R.id.recycler_view);
        mRefreshLayout = findViewById(R.id.swipeRefresh);
        // 2.初始化数据，一般我们的数据来源于后端//
        initData();
        // 4. 重构 默认显示垂直标准
        showList(true, false);
        // 6. 下拉刷新的实现
        scrollDownRefresh();
    }

    private void scrollDownRefresh() {
        mRefreshLayout.setEnabled(true);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 开发中一般请求后台从后台获取数据,主线程不能执行UI操作 这里模拟一条数据
                ItemBean item = new ItemBean();
                item.setIcon(R.mipmap.pic_01);
                item.setTitle("这是新增加的数据！");
                mDatas.add(0, item);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 更新数据到适配器中，停止刷新
                        mAdapter.notifyDataSetChanged();
                        mRefreshLayout.setRefreshing(false);
                    }
                }, 1000);


            }
        });
    }

    /**
     * 默认的RecyclerView是没有点击的监听事件，所以需要我们去自定义在Adapter中，
     * 因为数据都是放在了适配器中
     */
    private void initListener() {
        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d(TAG, "你点击了第" + position + "个条目");
            }
        });

        // 7. 上拉刷新
        if (mAdapter instanceof ListViewAdapter) {
            ((ListViewAdapter)mAdapter).setOnRefreshListener(new ListViewAdapter.OnRefreshListener() {
                @Override
                public void onRefresh(final ListViewAdapter.LoadMoreHolder loadMoreHolder) {
                    ItemBean item = new ItemBean();
                    item.setIcon(R.mipmap.pic_06);
                    item.setTitle("这是新增加的数据！");
                    mDatas.add(item);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // 更新数据到适配器中，停止刷新
                            mAdapter.notifyDataSetChanged();
                            loadMoreHolder.update(ListViewAdapter.LoadMoreHolder.NORMAL);
                        }
                    }, 1000);
                }
            });
        }
    }


    private void initData() {
        // 3. 创建List<Bean> -> Adapter -> RecyclerView
        mDatas = new ArrayList<>();
        ItemBean item = null;
        for (int i = 0; i < Datas.icons.length; i++) {
            item = new ItemBean();
            item.setIcon(Datas.icons[i]);
            item.setTitle("这是第" + i + "个条目");
            mDatas.add(item);
        }
    }

    // 加载菜单资源文件
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_res, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // 监听各个菜单项的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // ListView部分
            case R.id.list_view_vertical:
                Log.d(TAG, "list_view_vertical");
                showList(true, false);
                break;
            case R.id.list_view_vertical_reverse:
                Log.d(TAG, "list_view_vertical_reverse");
                showList(true, true);
                break;
            case R.id.list_view_horizontal:
                Log.d(TAG, "list_view_horizontal");
                showList(false, false);
                break;
            case R.id.list_view_horizontal_reverse:
                Log.d(TAG, "list_view_horizontal_reverse");
                showList(false, true);
                break;

            // GridView部分
            case R.id.grid_view_vertical:
                showGrid(true, false);
                break;
            case R.id.grid_view_vertical_reverse:
                showGrid(true, true);
                break;
            case R.id.grid_view_horizontal:
                showGrid(false, false);
                break;
            case R.id.grid_view_horizontal_reverse:
                showGrid(false, true);
                break;
            // 瀑布效果部分 stagger
            case R.id.waterfall_view_vertical:
                showStagger(true, false);
                break;
            case R.id.waterfall_view_vertical_reverse:
                showStagger(true, true);
                break;
            case R.id.waterfall_view_horizontal:
                showStagger(false, false);
                break;
            case R.id.waterfall_view_horizontal_reverse:
                showStagger(false, true);
                break;
            // 多条目类型
            case R.id.multi_type_item:
                Intent intent = new Intent(MainActivity.this, MultiTypeActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void showStagger(boolean isVertical, boolean isReverse) {
        mAdapter = new StaggerAdapter(mDatas);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, isVertical ? StaggeredGridLayoutManager.VERTICAL : StaggeredGridLayoutManager.HORIZONTAL);
        staggeredGridLayoutManager.setReverseLayout(isReverse);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        // 5. 处理条目的点击事件
        initListener();
    }

    private void showGrid(boolean isVertical, boolean isReverse) {
        mAdapter = new GridViewAdapter(mDatas);
        mRecyclerView.setAdapter(mAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setOrientation(isVertical ? GridLayoutManager.VERTICAL : GridLayoutManager.HORIZONTAL);
        layoutManager.setReverseLayout(isReverse);
        mRecyclerView.setLayoutManager(layoutManager);
        // 5. 处理条目的点击事件
        initListener();
    }

    private void showList(boolean isVertical, boolean isReverse) {
        mAdapter = new ListViewAdapter(mDatas);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        // 设置显示标准
        ((LinearLayoutManager) layoutManager).setOrientation(isVertical? LinearLayoutManager.VERTICAL: LinearLayoutManager.HORIZONTAL);
        ((LinearLayoutManager) layoutManager).setReverseLayout(isReverse);
        mRecyclerView.setLayoutManager(layoutManager);
        // 5. 处理条目的点击事件
        initListener();
    }

}
