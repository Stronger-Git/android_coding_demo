package cn.wjx34t0801to0802.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import cn.wjx34t0801to0802.BitmapCache;
import cn.wjx34t0801to0802.LruBitmapCache;
import cn.wjx34t0801to0802.R;
import cn.wjx34t0801to0802.model.GsonMovie;
import cn.wjx34t0801to0802.model.Movie;

/**
 * @author WuChangJian
 * @date 2020/5/15 21:27
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.InnerHodler> {

    public static final String TAG = "ListAdapter";
//    private List<Movie> mMovies = new ArrayList<>();
    private List<GsonMovie> mMovies = new ArrayList<>();

    private Context mContext;

    public ListAdapter() {}

    public ListAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public InnerHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(viewGroup.getContext(), R.layout.item_movie, null);
        return new InnerHodler(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHodler innerHodler, int i) {
        View view = innerHodler.itemView;
        ImageView movieImage = view.findViewById(R.id.movie_image);
        TextView movieTitle = view.findViewById(R.id.movie_title);
        movieTitle.setText(mMovies.get(i).getTitle());
        loadCacheImage(mMovies.get(i).getImages().getMedium(), movieImage);

    }

    private void loadCacheImage(String url, final ImageView movieImage){


        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                Log.d(TAG, "onResponse: ");
                movieImage.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.FIT_XY, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error);
            }
        });
        requestQueue.add(imageRequest);

    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public void setData(List<GsonMovie> movies) {
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    public class InnerHodler extends RecyclerView.ViewHolder {
        public InnerHodler(@NonNull View itemView) {
            super(itemView);
        }
    }
}
