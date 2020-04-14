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
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.wjx.contentprovider_mediaprovider.R;
import cn.wjx.contentprovider_mediaprovider.model.PhotoItem;
import cn.wjx.contentprovider_mediaprovider.util.SizeUtil;


/**
 * @author WuChangJian
 * @date 2020/4/11 19:28
 */
public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.InnerHolder> {
    public static final String TAG = "ImageListAdapter";
    private List<PhotoItem> mDatas = new ArrayList<>();
    private List<PhotoItem> selectedImages = new ArrayList<>(); // 已经选择的图片集合


    public List<PhotoItem> getSelectedImages() {
        return selectedImages;
    }
    private int selectMaxImages;


    public void setSelectMaxImages(int selectMaxImages) {
        this.selectMaxImages = selectMaxImages;
    }

    @NonNull
    @Override
    public ImageListAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder...");
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_photo, viewGroup, false);
        // 在没有下面几步操作之前，一屏只显示水平三个,这里我们通过获取屏幕宽度，使其一行平分三个
        Point point = SizeUtil.getScreenSize(itemView.getContext());
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(point.x/3, point.x/3);
        itemView.setLayoutParams(layoutParams);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageListAdapter.InnerHolder innerHolder, final int i) {
        Log.d(TAG, "onBindViewHolder...");
        View itemView = innerHolder.itemView;
        ImageView imageItem = itemView.findViewById(R.id.iv_images);
        final CheckBox checkBox = itemView.findViewById(R.id.bt_is_selected);
        final View coverView = itemView.findViewById(R.id.image_cover);
        // 1. 实现Glide图片加载框架加载图片
        Glide.with(imageItem.getContext()).load(mDatas.get(i).getPath()).into(imageItem);
        // 2. 实现图片选中和未选中状态的改变
        // 2.1 解决复用出现的错乱问题，什么意思比如我选择了1、2两个条目，当往下滑动的时候会发现也相应
        // 选择了两个相邻条目

        if (mDatas.get(i).isSelected()) {
            checkBox.setButtonDrawable(R.drawable.ic_selected);
            coverView.setVisibility(View.VISIBLE);
        } else {
            coverView.setVisibility(View.GONE);
            checkBox.setButtonDrawable(R.drawable.ic_no_select);
        }
        imageItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 图片点击后，若当前图片被选择，则应移除被选择图片集合，然后恢复图层
                if (mDatas.get(i).isSelected()) {
                    mDatas.get(i).setSelected(false);
                    selectedImages.remove(mDatas.get(i));
                    coverView.setVisibility(View.GONE);
                    checkBox.setButtonDrawable(R.drawable.ic_no_select);
                    // 3.2 回调
                    if (mItemSelectChanged != null) {
                        mItemSelectChanged.changeSelectedCount(selectedImages);
                    }
                } else {
                    // 3.1 判断已选择的是否大于最大数量
                    if (selectedImages.size() >= selectMaxImages) {
                        Toast.makeText(checkBox.getContext(), "你最多只能选择" + selectMaxImages + "个图片或视频", Toast.LENGTH_SHORT).show();
                        // 结束
                        return;
                    }
                    // 若当前图片没有选择，则应被选择，然后设置图层
                    mDatas.get(i).setSelected(true);
                    selectedImages.add(mDatas.get(i));
                    // 3.2 回调
                    if (mItemSelectChanged != null) {
                        mItemSelectChanged.changeSelectedCount(selectedImages);
                    }
                    checkBox.setButtonDrawable(R.drawable.ic_selected);
                    coverView.setVisibility(View.VISIBLE);
                }
            }
        });
        // 3.实现挑选照片时右上角数目的动态变化 外部设置UI

    }

    private OnItemSelectChanged mItemSelectChanged;
    // 暴露接口给外部
    public void setOnItemSelectChanged(OnItemSelectChanged itemSelectChanged) {
        this.mItemSelectChanged = itemSelectChanged;
    }

    public void release() {
        selectedImages.clear();
    }

    public interface OnItemSelectChanged {
        void changeSelectedCount(List<PhotoItem> selectedImages);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setData(List<PhotoItem> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
