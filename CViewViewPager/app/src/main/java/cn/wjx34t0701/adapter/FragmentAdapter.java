package cn.wjx34t0701.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChangJian
 * @date 2020/5/1 15:05
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments = new ArrayList<>();

    public FragmentAdapter(FragmentManager fm) {
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
}
