package cn.wjx34t0702;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import cn.wjx34t0702.fragment.FragmentOne;
import cn.wjx34t0702.fragment.FragmentTwo;

public class MainActivity extends AppCompatActivity implements FragmentOne.FOneBtnClickListener, FragmentTwo.FTwoBtnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = new Bundle();
        bundle.putCharSequence("content", "activity send a msg");
        FragmentOne fragmentOne = new FragmentOne();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        fragmentOne.setArguments(bundle);
        transaction.add(R.id.frame_container, fragmentOne, "ONE");
        transaction.commit();

    }


    @Override
    public void onFOneBtnClick(String msg) {
        Toast.makeText(this, "msg-->"+msg, Toast.LENGTH_SHORT).show();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTwo fmTwo = new FragmentTwo();
        fmTwo.setListener(this);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_container, fmTwo, "TWO");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onFTwoBtnClick(String msg) {
        Toast.makeText(this, "msg-->"+msg, Toast.LENGTH_SHORT).show();
    }
}
