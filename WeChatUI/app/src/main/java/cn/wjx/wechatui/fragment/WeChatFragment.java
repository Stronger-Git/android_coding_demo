package cn.wjx.wechatui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.wjx.wechatui.R;
import cn.wjx.wechatui.adapter.MsgPagerAdapter;
import cn.wjx.wechatui.model.MsgItem;
import cn.wjx.wechatui.util.Constant;

/**
 * @author WuChangJian
 * @date 2020/5/3 15:27
 */
public class WeChatFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private MsgPagerAdapter mAdapter;
    private List<MsgItem> mItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wechat, container, false);
        initView(view);
        initData();
        setOnItemListener();
        return view;
    }

    public interface OnMsgItemClickListener {
        void onMsgItemClick(int pos, String user);
    }

    private void setOnItemListener() {
        mAdapter.setOnItemClickListener(new MsgPagerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                if (getActivity() instanceof OnMsgItemClickListener) {
                    OnMsgItemClickListener msgItemClickListener = (OnMsgItemClickListener) getActivity();
                    msgItemClickListener.onMsgItemClick(pos, mItems.get(pos).getTitle());
                }
                Toast.makeText(getActivity(), "你点击了第"+pos+"个条目", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        mItems = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            int rand = random.nextInt(7);
            MsgItem item = new MsgItem(Constant.icons[rand], Constant.titles[rand], Constant.contents[rand], Constant.dates[rand]);
            mItems.add(item);
        }
        mAdapter = new MsgPagerAdapter();
        mAdapter.setData(mItems);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.page_msg);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
    }
}
