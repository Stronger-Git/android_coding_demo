package cn.wjx34t0602.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.wjx34t0602.R;
import cn.wjx34t0602.model.Music;

/**
 * @author WuChangJian
 * @date 2020/4/9 21:23
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.InnerHolder> {
    private List<Music> mDatas;
    private Context mContext;

    public ListAdapter(List<Music> mdatas, Context context) {
        this.mDatas = mdatas;
        mContext = context;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.item_music, null);
        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder innerHolder, int i) {
        innerHolder.setData(i);
    }

    @Override
    public int getItemCount() {
        if (mDatas != null)
            return mDatas.size();
        return 0;
    }

    private OnItemClickListener mListener;
    public interface OnItemClickListener{
        void onItemClick(int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }
    public class InnerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView icon;
        private TextView singer;
        private TextView music;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.music_icon);
            singer = itemView.findViewById(R.id.singer);
            music = itemView.findViewById(R.id.music_name);
        }


        public void setData(final int i) {
            icon.setImageResource(mDatas.get(i).getIcon());
            singer.setText("("+mDatas.get(i).getSinger()+")");
            music.setText(mDatas.get(i).getMusicName());
            singer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(i);
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
}
