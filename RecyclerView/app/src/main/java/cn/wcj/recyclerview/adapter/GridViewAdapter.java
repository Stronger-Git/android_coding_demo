package cn.wcj.recyclerview.adapter;


import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import cn.wcj.recyclerview.R;
import cn.wcj.recyclerview.bean.ItemBean;

/**
 * @author WuChangJian
 * @date 2020/3/13 19:53
 */
public class GridViewAdapter extends BaseAdapter{


    public GridViewAdapter(List<ItemBean> datas) {
        super(datas);
    }

    @Override
    public View getView(ViewGroup viewGroup, int position) {
        return View.inflate(viewGroup.getContext(), R.layout.item_grid_view, null);
    }
}
