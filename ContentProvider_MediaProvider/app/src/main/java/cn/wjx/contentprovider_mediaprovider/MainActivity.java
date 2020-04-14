package cn.wjx.contentprovider_mediaprovider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

import cn.wjx.contentprovider_mediaprovider.adapter.ResultListAdapter;
import cn.wjx.contentprovider_mediaprovider.model.PhotoItem;
import cn.wjx.contentprovider_mediaprovider.util.PickerConfig;

@RequiresApi(api = Build.VERSION_CODES.M)
public class MainActivity extends AppCompatActivity implements PickerConfig.OnImagesSelectFinishedListener {
    public static final String TAG = "MainActivity";
    private static final int REQUEST_READ_SD = 1;
    public static final int MAX_IMAGES = 9;// 最大图片数量
    private static final int SPAN_COUNTS = 3;
    private RecyclerView mRecyclerView;
    private ResultListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        initView();
        initConfig();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view_main);
        mAdapter = new ResultListAdapter();
        mRecyclerView.setAdapter(mAdapter);

    }

    private void initConfig() {
        PickerConfig pickerConfig = PickerConfig.getInstance();
        pickerConfig.setImageSelectCount(MAX_IMAGES);
        pickerConfig.setOnImagesSelectFinishedListener(this);
    }

    private void checkPermission() {
        int readExSD = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        if (readExSD != PackageManager.PERMISSION_GRANTED) {
            // 请求获取权限
            Log.d(TAG, "request permission...");
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_READ_SD);
        } else {
            Log.d(TAG, "permission has granted");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_READ_SD && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "request permission success");
        } else {
            Log.d(TAG, "request permission fail");
        }
    }

    public void pickImages(View view) {
        // 这里回传数据不采用onActivityResult方法，使用该方法传递的数据是有限的。
        // 通过设置接口，通过接口回传数据。
        startActivity(new Intent(this, ImagePickerActivity.class));

    }

    @Override
    public void onSelectFinished(List<PhotoItem> photoItems) {
        // 所选择的数据回传到这
        /*for (PhotoItem photoItem : photoItems) {
            Log.d(TAG, "onSelectFinished:" + photoItem);
        }*/
        // 根据图片数量设置布局管理器 如果选择一张，则显示一张的尺寸，大于三种则使用
        // recyclerView+grid列表展示
        GridLayoutManager layoutManager;
        if (photoItems.size() < SPAN_COUNTS) {
            layoutManager = new GridLayoutManager(this, photoItems.size());
        } else
            layoutManager = new GridLayoutManager(this, SPAN_COUNTS);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter.setData(photoItems, photoItems.size() < SPAN_COUNTS ? photoItems.size() : SPAN_COUNTS);
    }
}
