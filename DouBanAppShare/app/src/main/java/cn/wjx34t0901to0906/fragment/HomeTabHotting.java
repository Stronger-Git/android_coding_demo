package cn.wjx34t0901to0906.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.wjx34t0901to0906.R;
import cn.wjx34t0901to0906.adapter.HomeHottingAdapter;
import cn.wjx34t0901to0906.model.Movie;
import cn.wjx34t0901to0906.util.Constant;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * @author WuChangJian
 * @date 2020/5/23 7:38
 * 适配器-数据-item_layout
 */
public class HomeTabHotting extends Fragment {

    public static final String TAG = "HomeTabHotting";

    private RecyclerView mRecyclerView;
    private List<Movie> mMovies = new ArrayList<>();
    private HomeHottingAdapter mHomeHottingAdapter;
    private int start;  // 电影条目检索起始位置
    private int total;  // 检索某类型电影的总记录数
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Log.d(TAG, "handleMessage: currentThread-->" + Thread.currentThread() + "msg.what-->" + msg.what);


            if (msg.what == Constant.RESPONSE_SUCCESS) {
                mHomeHottingAdapter.setData((List<Movie>) msg.obj);
                mSwipeRefreshLayout.setRefreshing(false);
            }

            return false;
        }
    });
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home_tab_hotting, container, false);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }


    private void initData() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(Constant.getHottingLimitURL(start)).get().build();
        Log.d(TAG, "request url --> " + Constant.getHottingLimitURL(start));
        Call call = client.newCall(request);
        mSwipeRefreshLayout.setRefreshing(true);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = mHandler.obtainMessage();
                message.what = response.code()== HTTP_OK ? Constant.RESPONSE_SUCCESS : Constant.RESPONSE_ERROR;
                JSONObject jsonObject;
                JSONArray jsonArray;
                Gson gson = new Gson();
                try {
                    jsonObject = new JSONObject(response.body().string());
                    total = jsonObject.getInt("total");
                    Constant.totalCount = total;
                    jsonArray = jsonObject.getJSONArray("subjects");
                    Type typeOfT = new TypeToken<List<Movie>>(){}.getType();
                    mMovies = gson.fromJson(jsonArray.toString(), typeOfT);
                    start += jsonArray.length();
                    Log.d(TAG, "onResponse: movies-->" + mMovies.toString() + " start -->" + start);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //mHomeHottingAdapter.setData(mMovies);
                //抛出异常：非主线程不可更新UIOnly the original thread that created a view hierarchy can touch its views.
                //解决方法：利用Handler通信
                Log.d(TAG, "onResponse: currentThread-->" + Thread.currentThread());
                message.obj = mMovies;
                mHandler.sendMessage(message);
            }
        });

    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mHomeHottingAdapter = new HomeHottingAdapter(getActivity());
        mRecyclerView.setAdapter(mHomeHottingAdapter);
        // 线性布局管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        // 下拉刷新
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHomeHottingAdapter.downRefresh = false;
                initData();
            }
        });
        // 上拉刷新
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItem;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                mHomeHottingAdapter.downRefresh = true;
                // lastVisibleItem + 2 > mHomeHottingAdapter.getItemCount() 严谨
                // mooc上解释，最后一行数据已完全显示，并且FootView也被拉动超过底线一行，才可以更新数据
                // 有一个bug他没有控制好，就是底部一直会刷新，他的处理方式是将其隐藏！
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 2 > mHomeHottingAdapter.getItemCount()
                    && mHomeHottingAdapter.getItemCount() - 1 < total) {
                    initData();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
                lastVisibleItem = lm.findLastVisibleItemPosition();
            }
        });
    }
}
