package cn.wcj.recyclerview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.wcj.recyclerview.R;
import cn.wcj.recyclerview.bean.ItemBean;

/**
 * @author WuChangJian
 * @date 2020/3/13 20:50
 * @description 对适配器代码进行重构，创造基类Adapter
 */
public abstract class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ItemBean> mData;
    private OnItemClickListener mItemClickListener;

    public BaseAdapter(List<ItemBean> data) {
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // 其他部分都可以在当前类进行重构，但布局的加载需要交给子类去实现,改为抽象类,创建一个得到View对象的抽象方法
        View view = getView(viewGroup, i);
        return new InnerHolder(view);
    }

    public abstract View getView(ViewGroup viewGroup, int viewType);

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder innerHolder, int i) {
        ((InnerHolder) innerHolder).setData(i);
    }

    @Override
    public int getItemCount() {
        if (mData != null)
            return mData.size();
        return 0;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    /**
     * 自定义内部类实现监听事件的绑定及回调
     * 回调步骤
     * 1. 定义接口
     * 2. 接口内定义方法
     * 3. 将接口对象作为方法形参
     * 4. 点击事件引起回调
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView mIcon;
        private TextView mTitle;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            mIcon = itemView.findViewById(R.id.iv_icon);
            mTitle = itemView.findViewById(R.id.tv_title);

        }

        public void setData(final int position) {
            mIcon.setImageResource(mData.get(position).getIcon());
            mTitle.setText(mData.get(position).getTitle());
            mIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(position);
                }
            });
        }
    }
}
