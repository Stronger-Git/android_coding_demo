package cn.wjx.wechatui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.wjx.wechatui.R;
import cn.wjx.wechatui.model.MsgItem;

/**
 * @author WuChangJian
 * @date 2020/5/4 10:06
 */
public class MsgPagerAdapter extends RecyclerView.Adapter<MsgPagerAdapter.InnerHolder> {
    private List<MsgItem> mItems = new ArrayList<>();

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.item_msg, null);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder innerHolder, final int i) {
        View view = innerHolder.itemView;
        RelativeLayout msgItem = view.findViewById(R.id.layout_msg_item);
        ImageView icon = view.findViewById(R.id.icon_msg);
        TextView title = view.findViewById(R.id.txt_title);
        TextView context = view.findViewById(R.id.txt_context);
        TextView date = view.findViewById(R.id.date);
        icon.setImageResource(mItems.get(i).getIcon());
        title.setText(mItems.get(i).getTitle());
        context.setText(mItems.get(i).getContent());
        date.setText(mItems.get(i).getDate());
        msgItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(i);
            }
        });
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int pos);
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setData(List<MsgItem> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
