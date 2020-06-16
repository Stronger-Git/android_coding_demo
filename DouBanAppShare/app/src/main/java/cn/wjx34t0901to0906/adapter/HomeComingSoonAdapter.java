package cn.wjx34t0901to0906.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.wjx34t0901to0906.R;
import cn.wjx34t0901to0906.model.Movie;

/**
 * @author WuChangJian
 * @date 2020/6/9 16:01
 */
public class HomeComingSoonAdapter extends RecyclerView.Adapter<HomeComingSoonAdapter.InnerHolder> {

    private List<Movie> mMovies = new ArrayList<>();
    private Context mContext;

    public HomeComingSoonAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_home_tab_coming_soon, viewGroup, false);
        return new InnerHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder innerHolder, int i) {
        innerHolder.setData(i);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public void setData(List<Movie> movies) {
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }
    public class InnerHolder extends RecyclerView.ViewHolder {
        private ImageView mMovieImage;
        private TextView mMovieTitle;
        private RatingBar mMovieRating;
        private TextView mMovieRatingText;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            mMovieImage = itemView.findViewById(R.id.movie_image);
            mMovieTitle = itemView.findViewById(R.id.movie_title);
            mMovieRating = itemView.findViewById(R.id.movie_rating);
            mMovieRatingText = itemView.findViewById(R.id.movie_rating_text);
        }

        public void setData(int i) {
            mMovieTitle.setText(mMovies.get(i).getTitle());
            mMovieRating.setRating((float) (mMovies.get(i).getRating().getAverage()/2));
            mMovieRatingText.setText("" +  mMovies.get(i).getRating().getAverage());
            Glide.with(mContext).load(mMovies.get(i).getImages().getSmall()).into(mMovieImage);
        }
    }
}
