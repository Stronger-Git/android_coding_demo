package cn.wjx34t0602;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.wjx34t0602.adapter.RecyclerAdapter;
import cn.wjx34t0602.model.MusicBean;

/**
 * @author WuChangJian
 * @date 2020/4/24 16:00
 */
public class MultiTypeActivity extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;

    private List<MusicBean> mDatas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        initView();
        initData();

    }

    private void initData() {
        mDatas = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            MusicBean music = new MusicBean();
            music.type = MusicBean.TYPE.TYPE_TITLE;
            music.title = "精品歌单";
            mDatas.add(music);
        }

        for (int i = 0; i < 6; i++) {
            MusicBean music = new MusicBean();
            music.type = MusicBean.TYPE.TYPE_GRID_THREE;
            music.imageId = R.drawable.wangyi;
            music.title = "当打之年，现场及原场合集";
            mDatas.add(music);
        }

        for (int i = 0; i < 1; i++) {
            MusicBean music = new MusicBean();
            music.type = MusicBean.TYPE.TYPE_TITLE;
            music.title = "盖世潮流";
            mDatas.add(music);
        }

        for (int i = 0; i < 4; i++) {
            MusicBean music = new MusicBean();
            music.type = MusicBean.TYPE.TYPE_GRID_TWO;
            music.imageId = R.drawable.wangyi1;
            music.title = "Perfect Day";
            mDatas.add(music);
        }

        for (int i = 0; i < 1; i++) {
            MusicBean music = new MusicBean();
            music.type = MusicBean.TYPE.TYPE_TITLE;
            music.title = "精选专栏";
            mDatas.add(music);
        }

        for (int i = 0; i < 3; i++) {
            MusicBean music = new MusicBean();
            music.type = MusicBean.TYPE.TYPE_ONE;
            music.imageId = R.drawable.wangyi;
            music.title = "民国忆事|莺啼陌上人归去，花外疏钟送夕阳";
            mDatas.add(music);
        }

        for (int i = 0; i < 1; i++) {
            MusicBean music = new MusicBean();
            music.type = MusicBean.TYPE.TYPE_TITLE;
            music.title = "最新音乐";
            mDatas.add(music);
        }

        for (int i = 0; i < 6; i++) {
            MusicBean music = new MusicBean();
            music.type = MusicBean.TYPE.TYPE_GRID_THREE;
            music.imageId = R.drawable.wangyi1;
            music.title = "[BGM]一定听过的神级背景配乐";
            mDatas.add(music);
        }

        mAdapter.setData(mDatas);
        mRecyclerView.setAdapter(mAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 6);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = mDatas.get(position).type;
                if (type == MusicBean.TYPE.TYPE_GRID_THREE) {
                    return 2;
                } else if (type == MusicBean.TYPE.TYPE_GRID_TWO) {
                    return 3;
                } else if (type == MusicBean.TYPE.TYPE_ONE) {
                    return 6;
                } else if (type == MusicBean.TYPE.TYPE_TITLE) {
                    return 6;
                }
                return 0;
            }
        });
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view_plus);
        mAdapter = new RecyclerAdapter();
    }
}
