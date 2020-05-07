package cn.wjx.wechatui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import cn.wjx.wechatui.adapter.ViewPagerAdapter;
import cn.wjx.wechatui.fragment.ContactsFragment;
import cn.wjx.wechatui.fragment.ExploreFragment;
import cn.wjx.wechatui.fragment.MeFragment;
import cn.wjx.wechatui.fragment.WeChatFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,WeChatFragment.OnMsgItemClickListener {
    public static final String TAG = "MainActivity";
    private static final int FRAGMENT_INDEX_WECHAT = 0;
    private static final int FRAGMENT_INDEX_CONTACTS = 1;
    private static final int FRAGMENT_INDEX_EXPLORE = 2;
    private static final int FRAGMENT_INDEX_ME = 3;
    private List<Fragment> mFragments = new ArrayList<>();
    private ViewPager mViewPager;
    private RadioButton mTabWechat;
    private RadioButton mTabContacts;
    private RadioButton mTabExplore;
    private RadioButton mTabMe;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 实现沉浸式状态栏
        setStatusBar();
        initView();
        setDefaultFragment();
    }


    /**
     * 浸入式状态栏实现同时取消5.0以上的阴影
     */
    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        //修改字体颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        // 添加Menu必须 this.setSupportActionBar(toolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
    }

    private void setDefaultFragment() {
        WeChatFragment weChatFragment = new WeChatFragment();
        ContactsFragment contactsFragment = new ContactsFragment();
        ExploreFragment exploreFragment = new ExploreFragment();
        MeFragment meFragment = new MeFragment();
        mFragments.add(weChatFragment);
        mFragments.add(contactsFragment);
        mFragments.add(exploreFragment);
        mFragments.add(meFragment);
        FragmentManager fm = getSupportFragmentManager();
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(fm);
        pagerAdapter.setData(mFragments);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(FRAGMENT_INDEX_WECHAT);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                Log.d(TAG, "onPageSelected: "+i);
                mToolbar.setBackground(getDrawable(R.color.toolbar_bg_default));
                switch (i) {
                    case FRAGMENT_INDEX_WECHAT:
                        mTabWechat.setChecked(true);
                        mToolbar.setTitle("微信(182)");
                        break;
                    case FRAGMENT_INDEX_CONTACTS:
                        mTabContacts.setChecked(true);
                        mToolbar.setTitle("通讯录");
                        break;
                    case FRAGMENT_INDEX_EXPLORE:
                        mTabExplore.setChecked(true);
                        mToolbar.setTitle("朋友圈");
                        break;
                    case FRAGMENT_INDEX_ME:
                        mToolbar.setTitle("");
                        mToolbar.setBackgroundColor(Color.WHITE);
                        mTabMe.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initView() {
        mTabWechat = findViewById(R.id.tab_webchat);
        mTabContacts = findViewById(R.id.tab_contacts);
        mTabExplore = findViewById(R.id.tab_explore);
        mTabMe = findViewById(R.id.tab_me);
        mToolbar = findViewById(R.id.toolbar);
        mTabWechat.setOnClickListener(this);
        mTabContacts.setOnClickListener(this);
        mTabExplore.setOnClickListener(this);
        mTabMe.setOnClickListener(this);
        mViewPager = findViewById(R.id.view_pager_container);
    }


    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: "+mTabContacts.isChecked());
        mToolbar.setBackground(getDrawable(R.color.toolbar_bg_default));
        switch (v.getId()) {
            case R.id.tab_webchat:
                mViewPager.setCurrentItem(FRAGMENT_INDEX_WECHAT);
                mToolbar.setTitle("微信(182)");
                break;
            case R.id.tab_contacts:
                mToolbar.setTitle("通讯录");
                mViewPager.setCurrentItem(FRAGMENT_INDEX_CONTACTS);

                break;
            case R.id.tab_explore:
                mToolbar.setTitle("朋友圈");
                mViewPager.setCurrentItem(FRAGMENT_INDEX_EXPLORE);
                break;
            case R.id.tab_me:
                mToolbar.setTitle("");
                mToolbar.setBackgroundColor(Color.WHITE);
                mViewPager.setCurrentItem(FRAGMENT_INDEX_ME);
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu: ");
        getMenuInflater().inflate(R.menu.right_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    /**
     * 开启一个新的activity，模拟聊天界面
     * @param pos
     * @param user
     */
    @Override
    public void onMsgItemClick(int pos, String user) {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);

    }
}
