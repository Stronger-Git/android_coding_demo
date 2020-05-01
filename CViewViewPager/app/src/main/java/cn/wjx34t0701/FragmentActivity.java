package cn.wjx34t0701;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.wjx34t0701.adapter.FragmentAdapter;
import cn.wjx34t0701.fragment.FragmentOne;
import cn.wjx34t0701.fragment.FragmentThree;
import cn.wjx34t0701.fragment.FragmentTwo;

/**
 * @author WuChangJian
 * @date 2020/4/29 22:19
 *
 * 1） 静态加载Fragment
 * 使用V4包下的Fragment，Activity需要继承自FragmentActivity|或者FragmentActivity的子类(AppCompatActivity)
 */
public class FragmentActivity extends android.support.v4.app.FragmentActivity implements View.OnClickListener {

    private TextView mTab3;
    private TextView mTab1;
    private TextView mTab2;

    private Fragment mFragment1;
    private Fragment mFragment2;
    private Fragment mFragment3;
    private ViewPager mViewPager;
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        initView();
        initData();
        setDefaultFragment();
    }

    private void initData() {
        mFragment1 = new FragmentOne();
        mFragment2 = new FragmentTwo();
        mFragment3 = new FragmentThree();
        mFragments.add(mFragment1);
        mFragments.add(mFragment2);
        mFragments.add(mFragment3);
        // 设置适配器
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.setData(mFragments);
        mViewPager.setAdapter(fragmentAdapter);
    }

    private void setDefaultFragment() {
        /*mFragment1 = new FragmentOne();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.add(R.id.view_pager_content, mFragment1);
        transaction.commit();*/
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                updateUI(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initView() {
        mViewPager = findViewById(R.id.view_pager_content);
        mTab1 = findViewById(R.id.tab1);
        mTab2 = findViewById(R.id.tab2);
        mTab3 = findViewById(R.id.tab3);
        mTab1.setOnClickListener(this);
        mTab2.setOnClickListener(this);
        mTab3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        /*FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();*/
        updateUI(v.getId());
//        transaction.commit();

    }

    private void updateUI(int id) {
        switch (id) {
            case R.id.tab1:
            case 0:
                mTab1.setEnabled(false);
                mTab2.setEnabled(true);
                mTab3.setEnabled(true);
                /*if (mFragment1 == null) {
                    mFragment1 = new FragmentOne();
                }
                transaction.replace(R.id.view_pager_content, mFragment1);*/
                mViewPager.setCurrentItem(0);
                break;
            case R.id.tab2:
            case 1:
                mTab2.setEnabled(false);
                mTab1.setEnabled(true);
                mTab3.setEnabled(true);
                /*if (mFragment2 == null) {
                    mFragment2 = new FragmentTwo();
                }
                transaction.replace(R.id.view_pager_content, mFragment2);*/
                mViewPager.setCurrentItem(1);
                break;
            case R.id.tab3:
            case 2:
                mTab3.setEnabled(false);
                mTab1.setEnabled(true);
                mTab2.setEnabled(true);
                /*if (mFragment3 == null) {
                    mFragment3 = new FragmentThree();
                }
                transaction.replace(R.id.view_pager_content, mFragment3);*/
                mViewPager.setCurrentItem(2);
                break;
        }
    }
}
