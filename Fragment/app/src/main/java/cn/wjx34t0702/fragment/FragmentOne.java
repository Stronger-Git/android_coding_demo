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

import cn.wjx34t0702.MainActivity;
import cn.wjx34t0702.R;

/**
 * @author WuChangJian
 * @date 2020/5/1 8:55
 */
public class FragmentOne extends Fragment implements View.OnClickListener {

    public static final String TAG = "FragmentOne";

    public interface FOneBtnClickListener {
        void onFOneBtnClick(String msg);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            Log.d(TAG, "arguments "+arguments.get("content"));
        }
        // attachToRoot false，若为true会导致重复添加
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        Button btTwo = view.findViewById(R.id.go_two);
        btTwo.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {

        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).onFOneBtnClick("fragment_one send a msg");
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
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
