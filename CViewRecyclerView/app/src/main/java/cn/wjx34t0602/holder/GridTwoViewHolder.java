package cn.wjx34t0602.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cn.wjx34t0602.R;
import cn.wjx34t0602.util.RectImageView;

/**
 * @author WuChangJian
 * @date 2020/4/25 7:59
 */
public class GridTwoViewHolder extends RecyclerView.ViewHolder {

    public RectImageView ivIcon;
    public TextView tvContent;

    public GridTwoViewHolder(@NonNull View itemView) {
        super(itemView);
        ivIcon = itemView.findViewById(R.id.iv_icon);
        tvContent = itemView.findViewById(R.id.tv_content);
    }

}
