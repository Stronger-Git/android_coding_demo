package cn.wjx34t0601.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.wjx34t0601.R;
import cn.wjx34t0601.model.Contact;

/**
 * @author WuChangJian
 * @date 2020/4/9 11:07
 */
public class ContactAdapter extends BaseAdapter {
    private Context mContext;
    private List<Contact> mDatas;

    public ContactAdapter(){}
    public ContactAdapter(Context context, List<Contact> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public int getCount() {
        if (mDatas != null)
            return mDatas.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mDatas != null)
            return mDatas.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    /**
     *
     * @param position
     * @param convertView ** convertView的含义
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_contacts, null);
            viewHolder.contactIcon = convertView.findViewById(R.id.contact_icon);
            viewHolder.contactName = convertView.findViewById(R.id.contact_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.contactIcon.setImageResource(mDatas.get(position).getContactIcon());
        viewHolder.contactName.setText(mDatas.get(position).getContactName());
        return convertView;
    }

    // 静态内部类，通过ViewHolder优化
    static class ViewHolder{
        public ImageView contactIcon;
        public TextView contactName;
    }

}
