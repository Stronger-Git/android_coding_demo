package cn.wjx34t0602.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.wjx34t0602.R;
import cn.wjx34t0602.model.Contact;

/**
 * @author WuChangJian
 * @date 2020/4/24 15:01
 */
public class ContactsRecyclerAdatpter extends RecyclerView.Adapter<ContactsRecyclerAdatpter.InnerHolder> {

    private List<Contact> mDatas = new ArrayList<>();


    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_contacts, null, false);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder innerHolder, final int i) {
        View itemView = innerHolder.itemView;
        ImageView avatar = itemView.findViewById(R.id.contact_icon);
        TextView contact = itemView.findViewById(R.id.contact_name);
        avatar.setImageResource(mDatas.get(i).getContactIcon());
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(i);
            }
        });
        contact.setText(mDatas.get(i).getContactName());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setData(List<Contact> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    private OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
