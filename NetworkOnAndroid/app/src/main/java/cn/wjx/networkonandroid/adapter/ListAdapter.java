package cn.wjx.networkonandroid.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.wjx.networkonandroid.R;
import cn.wjx.networkonandroid.domain.ResponseData;

/**
 * @author WuChangJian
 * @date 2020/4/19 14:56
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.InnerHolder> {

    // 实例化一个集合的原因是防止空指针异常
    private List<ResponseData.DataBean.ContentBean> mContentBeans = new ArrayList<>();

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new InnerHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder innerHolder, int i) {
        ImageView covers = innerHolder.itemView.findViewById(R.id.covers);
        TextView userName = innerHolder.itemView.findViewById(R.id.username);
        TextView content = innerHolder.itemView.findViewById(R.id.content);
        userName.setText(mContentBeans.get(i).getUserName());
        content.setText(mContentBeans.get(i).getContent());
        // 注意请求是http还是https
        Glide.with(innerHolder.itemView.getContext()).load(mContentBeans.get(i).getCovers()).into(covers);

    }

    @Override
    public int getItemCount() {
        return mContentBeans.size();
    }

    public void setData(List<ResponseData.DataBean.ContentBean> content) {
        mContentBeans.clear();
        mContentBeans.addAll(content);
        notifyDataSetChanged();

    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
