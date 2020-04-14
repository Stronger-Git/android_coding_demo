package cn.wjx.contentprovider_mediaprovider.adapter;

import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.wjx.contentprovider_mediaprovider.R;
import cn.wjx.contentprovider_mediaprovider.model.PhotoItem;
import cn.wjx.contentprovider_mediaprovider.util.SizeUtil;

/**
 * @author WuChangJian
 * @date 2020/4/12 9:47
 */
public class ResultListAdapter extends RecyclerView.Adapter<ResultListAdapter.InnerHolder> {

    public static final String TAG = "ResultListAdapter";

    private List<PhotoItem> mPhotoItems = new ArrayList<>();
    private int spanCount;

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_photo, viewGroup, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder innerHolder, int i) {
        View itemView = innerHolder.itemView;
        // 因为RecyclerView复用的问题，当条目个数比较上一次小的时候，是不会走onCreateViewHolder的，但肯定会走
        // onBindViewHolder,所以把屏幕适配的代码挪到这里来
        Point point = SizeUtil.getScreenSize(itemView.getContext());
        Log.d(TAG, "screen width --> "+point.x);
        Log.d(TAG, "screen height --> "+point.y);
        Log.d(TAG, "span count --> " + spanCount);
        // 让单个条目宽高一致
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(point.x/spanCount, point.x/spanCount);
        itemView.setLayoutParams(layoutParams);

        ImageView imageItem = itemView.findViewById(R.id.iv_images);
        CheckBox checkBox = itemView.findViewById(R.id.bt_is_selected);
        checkBox.setVisibility(View.GONE);
        // 1. 实现Glide图片加载框架加载图片
        Glide.with(imageItem.getContext()).load(mPhotoItems.get(i).getPath()).into(imageItem);
    }


    @Override
    public int getItemCount() {
        return mPhotoItems.size();
    }

    public void setData(List<PhotoItem> photoItems, int spanCount) {
        this.spanCount = spanCount;
        mPhotoItems.clear();
        mPhotoItems.addAll(photoItems);
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
