package cn.wjx34t0801to0802;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.wjx34t0801to0802.adapter.ListAdapter;
import cn.wjx34t0801to0802.model.GsonMovie;
import cn.wjx34t0801to0802.model.Movie;

import static cn.wjx34t0801to0802.MainActivity.TAG;

/**
 * @author WuChangJian
 * @date 2020/5/15 21:19
 */
public class DouBanActivity extends Activity {

//    private List<Movie> mMovies = new ArrayList<>();
    private List<GsonMovie> mMovies = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private ListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_douban);
        initView();
        initData();

    }

    private void initData() {
        String url = "https://api.douban.com/v2/movie/in_theaters?apikey=0df993c66c0c636e29ecbb5344252a4a&start=0&count=10";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray subjects = response.getJSONArray("subjects");
                    for (int i = 0; i < subjects.length(); i++) {
                        JSONObject jsonObject = subjects.getJSONObject(i);
                        /*String title = jsonObject.getString("title");
                        JSONObject images = jsonObject.getJSONObject("images");
                        String imageUrl = images.getString("medium");
                        Movie movie = new Movie(imageUrl, title);*/

                        // 2.使用GSON解析
                        Gson gson = new Gson();
                        GsonMovie gsonMovie = gson.fromJson(jsonObject.toString(), GsonMovie.class);
                        mMovies.add(gsonMovie);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "onResponse: " + mMovies);
                mListAdapter.setData(mMovies);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

    }



    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mListAdapter = new ListAdapter(DouBanActivity.this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mListAdapter);
    }
}
