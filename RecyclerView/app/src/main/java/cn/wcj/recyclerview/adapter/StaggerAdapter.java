package cn.wcj.recyclerview.adapter;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.wcj.recyclerview.R;
import cn.wcj.recyclerview.bean.ItemBean;

/**
 * @author WuChangJian
 * @date 2020/3/13 21:24
 */
public class StaggerAdapter extends BaseAdapter {

    public StaggerAdapter(List<ItemBean> data) {
        super(data);
    }

    @Override
    public View getView(ViewGroup viewGroup, int position) {
        return View.inflate(viewGroup.getContext(), R.layout.item_stagger_view, null);
    }
}
