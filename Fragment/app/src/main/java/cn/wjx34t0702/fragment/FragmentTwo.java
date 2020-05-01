package cn.wjx34t0702.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.wjx34t0702.R;

/**
 * @author WuChangJian
 * @date 2020/5/1 8:55
 */
public class FragmentTwo extends Fragment implements View.OnClickListener {
    public static final String TAG = "FragmentTwo";

    private FTwoBtnClickListener mListener;
    public interface FTwoBtnClickListener {
        void onFTwoBtnClick(String msg);
    }

    public void setListener(FTwoBtnClickListener listener) {
        mListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // attachToRoot false，若为true会导致重复添加
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        Button btTwo = view.findViewById(R.id.go_three);
        btTwo.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onFTwoBtnClick("fragment_two send a msg");
        }


       /* FragmentThree fmThree = new FragmentThree();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(this);
        transaction.add(R.id.frame_container, fmThree);
        // transaction.replace(R.id.frame_container, fmThree, "THREE");
        transaction.addToBackStack(null);
        transaction.commit();*/
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }
}
