package cn.wcj.recyclerview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.wcj.recyclerview.R;
import cn.wcj.recyclerview.bean.MultiTypeBean;

/**
 * @author WuChangJian
 * @date 2020/3/15 9:07
 * @description 实现多种条目类型
 */
public class MultiTypeAdapter extends RecyclerView.Adapter {

    public static final String TAG = "MultiTypeAdapter";
    private List<MultiTypeBean> mData;
    public static final int FULL_SCREEN_TYPE = 0;
    public static final int RIGHT_TYPE = 1;
    public static final int THREE_TYPE = 2;
    public MultiTypeAdapter(List<MultiTypeBean> data) {
        this.mData = data;
    }

    /**
     * 1. 因为要生成三种不同的条目类型，所以就必须用三个内部类extends ViewHolder
     * @param viewGroup
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Log.d(TAG, "onCreateViewHolder...");
        View view = null;
        if (viewType == FULL_SCREEN_TYPE) {
            view = View.inflate(viewGroup.getContext(), R.layout.item_full_screen_view, null);
            return new FullScreenType(view);
        } else if (viewType == RIGHT_TYPE) {
            view = View.inflate(viewGroup.getContext(), R.layout.item_right_view, null);
            return new RightItemType(view);
        } else {
            view = View.inflate(viewGroup.getContext(), R.layout.item_three_view, null);
            return new ThreeItemType(view);
        }
    }


    /**
     * 2. 重写父类的getItemViewType,因为onCreateViewHolder根据该方法判读需要创造什么类型的布局
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, "getItemViewType...");
        if (mData.get(position).getItemType() == FULL_SCREEN_TYPE)
            return FULL_SCREEN_TYPE;
        else if (mData.get(position).getItemType() == RIGHT_TYPE)
            return RIGHT_TYPE;
        else
            return THREE_TYPE;
    }

    /**
     * 绑定数据到适配器
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder...");
        if (viewHolder instanceof FullScreenType) {
            FullScreenType fullScreenType = (FullScreenType) viewHolder;
            fullScreenType.setData(i);
        } else if (viewHolder instanceof RightItemType) {
            RightItemType rightItemType = (RightItemType) viewHolder;
            rightItemType.setData(i);
        } else {
            ThreeItemType threeItemType = (ThreeItemType) viewHolder;
            threeItemType.setData(i);
        }
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount...");
        if (mData != null)
            return mData.size();
        return 0;
    }



    private class FullScreenType extends RecyclerView.ViewHolder {

        private ImageView mIcon;
        private TextView mTitle;
        public FullScreenType(@NonNull View itemView) {
            super(itemView);
            mIcon = itemView.findViewById(R.id.iv_icon);
            mTitle = itemView.findViewById(R.id.tv_title);
        }

        public void setData(final int position) {
            mIcon.setImageResource(mData.get(position).getIcon()[0]);
            mTitle.setText(mData.get(position).getTitle());
        }
    }

    private class RightItemType extends RecyclerView.ViewHolder {

        private ImageView mIcon;
        private TextView mTitle;
        public RightItemType(@NonNull View itemView) {
            super(itemView);
            mIcon = itemView.findViewById(R.id.iv_icon);
            mTitle = itemView.findViewById(R.id.tv_title);
        }

        public void setData(final int position) {
            mIcon.setImageResource(mData.get(position).getIcon()[0]);
            mTitle.setText(mData.get(position).getTitle());
        }
    }
    private class ThreeItemType extends RecyclerView.ViewHolder {
        private String TAG = "ThreeItemType";
        private ImageView[] mIcons = new ImageView[3];
        private TextView mTitle;
        public ThreeItemType(@NonNull View itemView) {
            super(itemView);
            mIcons[0] = itemView.findViewById(R.id.iv_icon_three_one);
            mIcons[1] = itemView.findViewById(R.id.iv_icon_three_two);
            mIcons[2] = itemView.findViewById(R.id.iv_icon_three_three);
            mTitle = itemView.findViewById(R.id.tv_title);
        }

        public void setData(final int position) {
            for (int i = 0; i < 3; i++) {
                Log.d(TAG, ""+mData.get(position).getIcon()[i]);
                mIcons[i].setImageResource(mData.get(position).getIcon()[i]);
            }
            mTitle.setText(mData.get(position).getTitle());
        }
    }

}
