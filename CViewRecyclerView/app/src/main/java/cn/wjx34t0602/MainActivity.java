package cn.wjx34t0602;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.wjx34t0602.adapter.ListAdapter;
import cn.wjx34t0602.model.Music;
import cn.wjx34t0602.util.Constant;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Music> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        setAdapter();
    }

    private void setAdapter() {
        ListAdapter adapter = new ListAdapter(mDatas, this);
        adapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Toast.makeText(MainActivity.this, "音乐名称："+mDatas.get(pos).getMusicName(), Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);

    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < Constant.icons.length; i++) {
            Music music = new Music(Constant.icons[i], Constant.music[i], Constant.singers[i]);
            mDatas.add(music);
        }
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
    }
}
