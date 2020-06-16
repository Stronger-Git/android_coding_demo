package cn.wjx34t0901to0906.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.wjx34t0901to0906.R;
import cn.wjx34t0901to0906.adapter.HomeAdapter;

/**
 * @author WuChangJian
 * @date 2020/5/21 19:48
 */
public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTabLayout = view.findViewById(R.id.tab_layout);
        mViewPager = view.findViewById(R.id.vp);
        // 双向绑定
        mTabLayout.setupWithViewPager(mViewPager);
        initAdapter();
    }

    private void initAdapter() {
        // getFragmentManager()所得到的是所在fragment 的父容器的管理器，
        // getChildFragmentManager()所得到的是在fragment  里面子容器的管理器。
        HomeAdapter pagerAdapter = new HomeAdapter(getChildFragmentManager());
        List<Fragment> fragments = new ArrayList<>();
        // TODO
        fragments.add(new HomeTabHotting());
        fragments.add(new HomeTabComingSoon());
        fragments.add(new HomeTabHotting());
        pagerAdapter.setData(fragments);
        mViewPager.setAdapter(pagerAdapter);
    }
}
