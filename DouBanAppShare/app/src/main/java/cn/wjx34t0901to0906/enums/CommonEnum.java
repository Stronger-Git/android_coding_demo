package cn.wjx34t0901to0906.enums;

import android.support.v4.app.Fragment;

import cn.wjx34t0901to0906.R;
import cn.wjx34t0901to0906.fragment.FavoriteFragment;
import cn.wjx34t0901to0906.fragment.HomeFragment;
import cn.wjx34t0901to0906.fragment.HotFragment;
import cn.wjx34t0901to0906.fragment.SettingFragment;

/**
 * @author WuChangJian
 * @date 2020/5/21 20:00
 */
public enum CommonEnum {

    NAV_HOME(new HomeFragment(), R.id.nav_home, "首页[吴吉祥]"),NAV_FAVORITE(new FavoriteFragment(), R.id.nav_collect, "收藏"),
    NAV_HOT(new HotFragment(), R.id.nav_hot, "热映"),NAV_SETTING(new SettingFragment(), R.id.nav_setting, "设置");

    private Fragment mFragment;
    private int rid;
    private String msg;

    CommonEnum(Fragment fragment, int rid, String msg) {
        mFragment = fragment;
        this.rid = rid;
        this.msg = msg;
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public void setFragment(Fragment fragment) {
        mFragment = fragment;
    }

    public static String valueOfId(int id) {
        for (CommonEnum value : values()) {
            if (value.getRid() == id)
                return value.getMsg();
        }
        return "";
    }

    public static Fragment fragmentOfId(int rid) {
        for (CommonEnum value : values()) {
            if (value.getRid() == rid)
                return value.getFragment();
        }
        return null;
    }
    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

}
