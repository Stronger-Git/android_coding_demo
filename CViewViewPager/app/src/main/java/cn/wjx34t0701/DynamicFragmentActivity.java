package cn.wjx34t0701;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import cn.wjx34t0701.fragment.FragmentTitle;
import cn.wjx34t0701.fragment.FriendContent;
import cn.wjx34t0701.fragment.WechatContent;

/**
 * @author WuChangJian
 * @date 2020/4/30 8:36
 *
 * 动态加载Fragment
 *
 * 这里继承了FragmentActivity的另一个子类AppCompatActivity
 */
public class DynamicFragmentActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "DynamicFragmentActivity";
    private TextView mTabWechat;
    private TextView mTabFriend;
    private WechatContent mWechatFragment;
    private FriendContent mFriendFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_fragment);
        Log.d(TAG, "onCreate: ");
        initView();
        // 设置初始化fragment
        setDefaultFragment();
    }

    private void initView() {
        mTabWechat = findViewById(R.id.tab_wechat);
        mTabFriend = findViewById(R.id.tab_friend);
        mTabWechat.setOnClickListener(this);
        mTabFriend.setOnClickListener(this);

    }

    private void setDefaultFragment() {
        // 获取Fragment的管理对象 FragmentManager：用来在Fragment和Activity之间交互的接口
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 获得事务对象，add、remove、replace等等操作都称为事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mWechatFragment = new WechatContent();
        transaction.replace(R.id.frame_content, mWechatFragment);
        /**
         * 其他常用事务
         * transaction.remove(Fragment);
         * transaction.hide(Fragment);
         * transaction.show(Fragment);
         */

        // 提交事务
        transaction.commit();

    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 开启fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.tab_wechat:
                if (mWechatFragment == null) {
                    mWechatFragment = new WechatContent();
                }
                transaction.replace(R.id.frame_content, mWechatFragment);
                break;
            case R.id.tab_friend:
                if (mFriendFragment == null) {
                    mFriendFragment = new FriendContent();
                }
                transaction.replace(R.id.frame_content, mFriendFragment);
                break;
        }
        // 提交事务
        transaction.commit();
    }
}
