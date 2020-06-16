package cn.wjx34t0901to0906.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


import cn.wjx34t0901to0906.util.Constant;

/**
 * @author WuChangJian
 * @date 2020/5/21 21:43
 */
public class HomeAdapter extends FragmentPagerAdapter {
    public static final String TAG = "HomeAdapter";

    private List<Fragment> mFragments = new ArrayList<>();

    public HomeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public void setData(List<Fragment> fragments) {
        mFragments.clear();
        mFragments.addAll(fragments);
        notifyDataSetChanged();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        // TODO
        return Constant.TITLES[position];
    }
}
