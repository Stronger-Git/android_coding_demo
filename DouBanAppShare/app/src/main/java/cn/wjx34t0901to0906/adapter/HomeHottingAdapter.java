package cn.wjx34t0901to0906.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.wjx34t0901to0906.R;
import cn.wjx34t0901to0906.model.Movie;
import cn.wjx34t0901to0906.util.Constant;

/**
 * @author WuChangJian
 * @date 2020/5/27 13:05
 */
public class HomeHottingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String TAG = "HomeHottingAdapter";
    private static final int NORMAL_TYPE = 0;
    private static final int LOADING_TYPE = 1;
    private List<Movie> mMovies = new ArrayList<>();
    private Context mContext;
    public boolean downRefresh;// 下拉刷新标识
    public HomeHottingAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == NORMAL_TYPE) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_tab_hotting, viewGroup, false);
            // 下面这种加载view的方法可能会导致视图显示不全，导致RecyclerView item match_parent失效
            // View view = View.inflate(viewGroup.getContext(), R.layout.item_home_tab_hotting, null);
            return new InnerHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.foot_load_tips, viewGroup, false);
            return new FootHolder(view);
        }
    }

    /**
     * 根据位置返回条目类型
     * @param position 索引，不是位置
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, "getItemViewType: position-->" + position);
        if (position < mMovies.size()) {
            return NORMAL_TYPE;
        } else {
            return LOADING_TYPE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(getItemViewType(position)==0) {
            ((InnerHolder) viewHolder).setData(position);
        }else {
            ((FootHolder) viewHolder).setData(position);
        }
    }


    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + (mMovies.size() + 1));
        return mMovies.size() + 1;
    }

    public void setData(List<Movie> movies) {
        if (downRefresh) {
            mMovies.addAll(movies);
        } else {
            movies.addAll(this.mMovies);
            mMovies.clear();
            mMovies.addAll(movies);
        }
        notifyDataSetChanged();
    }

    public class FootHolder extends RecyclerView.ViewHolder {
        private ProgressBar pbViewLoadTip;
        private TextView tvViewLoadTip;
        public FootHolder(View itemView) {
            super(itemView);
            pbViewLoadTip = itemView.findViewById(R.id.pb_view_load_tip);
            tvViewLoadTip = itemView.findViewById(R.id.tv_view_load_tip);
        }

        public void setData(int i) {
            if(mMovies.size() >= Constant.totalCount){
                pbViewLoadTip.setVisibility(View.GONE);
                tvViewLoadTip.setText("");
            }else{
                pbViewLoadTip.setVisibility(View.VISIBLE);
                tvViewLoadTip.setText("加载更多..");
            }
        }
    }
    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView mIvItemSimpleSubjectImage;
        private RatingBar mRbItemSimpleSubjectRating;
        private TextView mTvItemSimpleSubjectRating;
        private TextView mTvItemSimpleSubjectCount;
        private TextView mTvItemSimpleSubjectTitle;
        private TextView mTvItemSimpleSubjectOriginalTitle;
        private TextView mTvItemSimpleSubjectGenres;
        private TextView mTvItemSimpleSubjectDirector;
        private TextView mTvItemSimpleSubjectCast;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            // alt + shift 多光标编辑，配合findbyme插件，开发真香
            mIvItemSimpleSubjectImage =  itemView.findViewById(R.id.iv_item_simple_subject_image);
            mRbItemSimpleSubjectRating = itemView.findViewById(R.id.rb_item_simple_subject_rating);
            mTvItemSimpleSubjectRating = itemView.findViewById(R.id.tv_item_simple_subject_rating);
            mTvItemSimpleSubjectCount = itemView.findViewById(R.id.tv_item_simple_subject_count);
            mTvItemSimpleSubjectTitle = itemView.findViewById(R.id.tv_item_simple_subject_title);
            mTvItemSimpleSubjectOriginalTitle = itemView.findViewById(R.id.tv_item_simple_subject_original_title);
            mTvItemSimpleSubjectGenres = itemView.findViewById(R.id.tv_item_simple_subject_genres);
            mTvItemSimpleSubjectDirector = itemView.findViewById(R.id.tv_item_simple_subject_director);
            mTvItemSimpleSubjectCast = itemView.findViewById(R.id.tv_item_simple_subject_cast);
        }

        public void setData(int i) {
            mTvItemSimpleSubjectTitle.setText(mMovies.get(i).getTitle());
            mRbItemSimpleSubjectRating.setRating((float) (mMovies.get(i).getRating().getAverage()/2));
            mTvItemSimpleSubjectRating.setText("" +  mMovies.get(i).getRating().getAverage());
            mTvItemSimpleSubjectCount.setText("(" + mMovies.get(i).getCollect_count() + ")人评价");
            mTvItemSimpleSubjectOriginalTitle.setText(mMovies.get(i).getOriginal_title());
            StringBuffer genres=new StringBuffer();
            for (String g:mMovies.get(i).getGenres()) {
                genres.append(g+",");
            }
            mTvItemSimpleSubjectGenres.setText(genres);
            StringBuffer directors=new StringBuffer();
            for (Movie.DirectorsBean d:mMovies.get(i).getDirectors()) {
                directors.append(  d.getName()+",");
            }
            mTvItemSimpleSubjectDirector.setText(getSpannableString("导演:" , Color.GRAY));
            mTvItemSimpleSubjectDirector.append(directors.toString());
            StringBuffer casts=new StringBuffer();
            for (Movie.CastsBean d:mMovies.get(i).getCasts()) {
                casts.append(  d.getName()+",");
            }
            mTvItemSimpleSubjectCast.setText(getSpannableString("演员:" , Color.GRAY));
            mTvItemSimpleSubjectCast.append(casts.toString());
            Glide.with(mContext).load(mMovies.get(i).getImages().getSmall()).into(mIvItemSimpleSubjectImage);
        }
        public SpannableString getSpannableString(String str, int color) {
            SpannableString span = new SpannableString(str);
            span.setSpan(new ForegroundColorSpan(
                    color), 0, span.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return span;
        }
    }
}
