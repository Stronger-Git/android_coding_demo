package cn.wcj.recyclerview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import cn.wcj.recyclerview.R;
import cn.wcj.recyclerview.bean.ItemBean;

/**
 * @author WuChangJian
 * @date 2020/3/12 9:03
 * @description v1 继承BaseAdapter重构代码重构后的代码简洁，搞懂为什么能行
 * v2 重构代码使其实现上拉刷新功能,这时就要重写BaseAdapter的许多方法了，因为要显示多种布局类型
 */
public class ListViewAdapter extends BaseAdapter{
    public static final String TAG = "ListViewAdapter";
    // 0 正常加载普通条目布局 1 加载正在加载条目布局
    public static final int LOADING_TYPE = 1;
    public static final int NORMAL_TYPE = 0;
    public ListViewAdapter(List<ItemBean> data) {
        super(data);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = getView(viewGroup, i);
        if (i == NORMAL_TYPE) {
            Log.d(TAG, "create InnerHolder");
            return new InnerHolder(view);
        } else {
            Log.d(TAG, "create LoadMoreHolder");
            return new LoadMoreHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof InnerHolder) {
            Log.d(TAG, "Bind InnerHolder...");
            ((InnerHolder)viewHolder).setData(i);
        } else {
            Log.d(TAG, "Bind LoadMoreHolder");
            ((LoadMoreHolder)viewHolder).update(LoadMoreHolder.IS_LOADING);
        }
    }

    @Override
    public View getView(ViewGroup viewGroup, int viewType) {
        View view;
        if (viewType == NORMAL_TYPE) {
            view = View.inflate(viewGroup.getContext(), R.layout.item_list_view, null);
            return view;
        } else {
            view = View.inflate(viewGroup.getContext(), R.layout.item_list_load_more, null);
            return view;
        }
    }


    /**
     * 首先你要明白，RecyclerView itemViw的渲染流程
     * getItemCount -> getItemViewType -> createViewHolder -> bindViewHolder
     * 判断是否是最后一个条目，若是则显示正在加载的布局
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {

        if (getItemCount() - 1 == position) {
            return LOADING_TYPE;
        }
        return NORMAL_TYPE;
    }

    /**
     * 回调方法 暴露接口
     */
    public OnRefreshListener mOnRefreshListener;
    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        mOnRefreshListener = onRefreshListener;
    }
    public interface OnRefreshListener {
        void onRefresh(LoadMoreHolder loadMoreHolder);
    }

    /**
     * 加载更多，两种状态，正在加载 other 加载失败请重试
     */
    public class LoadMoreHolder extends RecyclerView.ViewHolder {
        public static final int IS_LOADING = 0;
        public static final int FAIL_LOADING = 1;
        public static final int NORMAL = 2;
        private RelativeLayout mLoading;
        private TextView mReload;

        public LoadMoreHolder(@NonNull View itemView) {
            super(itemView);
            mLoading = itemView.findViewById(R.id.loading);
            mReload = itemView.findViewById(R.id.reload);
        }

        public void update(int state) {
            Log.d(TAG, "update state:" + state + " item countTotal:" + getItemCount());
            if (state == IS_LOADING) {
                mLoading.setVisibility(View.VISIBLE);
                if (mOnRefreshListener != null) {
                    mOnRefreshListener.onRefresh(this);
                }
            } else if (state == FAIL_LOADING) {
                mReload.setVisibility(View.VISIBLE);
            } else if (state == NORMAL){
                mLoading.setVisibility(View.GONE);
                mReload.setVisibility(View.GONE);
            }
        }

    }
}
