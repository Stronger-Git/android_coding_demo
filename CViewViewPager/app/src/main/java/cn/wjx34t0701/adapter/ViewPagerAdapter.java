package cn.wjx34t0701.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuChangJian
 * @date 2020/4/26 22:12
 * @description 必须重写的方法destroyItem  instantiateItem isViewFromObject getCount
 */
public class ViewPagerAdapter extends PagerAdapter {
    private List<View> mViews = new ArrayList<>();

    public ViewPagerAdapter() {

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = mViews.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mViews.get(position));
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    public void setViews(List<View> views) {
        mViews.clear();
        mViews.addAll(views);
        notifyDataSetChanged();
    }
}
