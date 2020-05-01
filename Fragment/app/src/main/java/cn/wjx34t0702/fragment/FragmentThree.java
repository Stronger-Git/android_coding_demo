package cn.wjx34t0702.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.wjx34t0702.R;

/**
 * @author WuChangJian
 * @date 2020/5/1 8:55
 */
public class FragmentThree extends Fragment{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // attachToRoot false，若为true会导致重复添加
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        return view;
    }

}
