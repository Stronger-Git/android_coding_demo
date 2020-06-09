package cn.wjx34t0602.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.wjx34t0602.R;
import cn.wjx34t0602.holder.GridOneViewHolder;
import cn.wjx34t0602.holder.GridThreeViewHolder;
import cn.wjx34t0602.holder.GridTwoViewHolder;
import cn.wjx34t0602.holder.TitleViewHolder;
import cn.wjx34t0602.model.MusicBean;

/**
 * @author WuChangJian
 * @date 2020/4/25 7:58
 */
public class RecyclerAdapter extends RecyclerView.Adapter {

    public static final String TAG = "RecyclerAdapter";
    private List<MusicBean> mDatas = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;
        if (i == MusicBean.TYPE.TYPE_ONE) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_one, viewGroup, false);
            viewHolder = new GridOneViewHolder(view);
        } else if (i == MusicBean.TYPE.TYPE_GRID_TWO) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_two, viewGroup, false);
            viewHolder = new GridTwoViewHolder(view);
        } else if (i == MusicBean.TYPE.TYPE_GRID_THREE) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_three, viewGroup, false);
            viewHolder = new GridThreeViewHolder(view);
        } else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_title, viewGroup, false);
            viewHolder = new TitleViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (getItemViewType(position)) {
            case MusicBean.TYPE.TYPE_GRID_THREE:
                GridThreeViewHolder gHolder_three = (GridThreeViewHolder) viewHolder;
                gHolder_three.tvContent.setText(mDatas.get(position).title);
                gHolder_three.ivIcon.setImageResource(mDatas.get(position).imageId);
                gHolder_three.itemView.setTag(position);
                break;
            case MusicBean.TYPE.TYPE_GRID_TWO:
                GridTwoViewHolder gHolder_two = (GridTwoViewHolder) viewHolder;
                gHolder_two.tvContent.setText(mDatas.get(position).title);
                gHolder_two.ivIcon.setImageResource(mDatas.get(position).imageId);
                gHolder_two.itemView.setTag(position);
                break;
            case MusicBean.TYPE.TYPE_ONE:
                GridOneViewHolder lHolder = (GridOneViewHolder) viewHolder;
                lHolder.tvContent.setText(mDatas.get(position).title);
                lHolder.ivIcon.setImageResource(mDatas.get(position).imageId);
                lHolder.itemView.setTag(position);
                break;
            case MusicBean.TYPE.TYPE_TITLE:
                TitleViewHolder tHolder = (TitleViewHolder) viewHolder;
                tHolder.tv_content.setText(mDatas.get(position).title);
                tHolder.itemView.setTag(position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount---> " + mDatas.size());
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mDatas.get(position).type;
    }

    public void setData(List<MusicBean> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }
}
