package cn.wjx34t0901to0906.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import cn.wjx34t0901to0906.adapter.HomeComingSoonAdapter;
import cn.wjx34t0901to0906.adapter.HomeHottingAdapter;
import cn.wjx34t0901to0906.model.Movie;
import cn.wjx34t0901to0906.util.Constant;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * @author WuChangJian
 * @date 2020/6/9 15:48
 */
public class HomeTabComingSoon extends Fragment {

    public static final String TAG = "HomeTabHotting";

    private RecyclerView mRecyclerView;
    private List<Movie> mMovies = new ArrayList<>();
    private HomeComingSoonAdapter mHomeComingSoonAdapter;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Log.d(TAG, "handleMessage: currentThread-->" + Thread.currentThread() + "msg.what-->" + msg.what);


            if (msg.what == Constant.RESPONSE_SUCCESS) {
                mHomeComingSoonAdapter.setData((List<Movie>) msg.obj);
            }

            return false;
        }
    });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home_tab_coming_soon, container, false);
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
        Request request = new Request.Builder().url(Constant.URL_COMING_SOON_LIMIT).get().build();
        Call call = client.newCall(request);
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
                    jsonArray = jsonObject.getJSONArray("subjects");
                    Type typeOfT = new TypeToken<List<Movie>>(){}.getType();
                    mMovies = gson.fromJson(jsonArray.toString(), typeOfT);
                    Log.d(TAG, "onResponse: movies-->" + mMovies.toString());
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
        mHomeComingSoonAdapter = new HomeComingSoonAdapter(getActivity());
        mRecyclerView.setAdapter(mHomeComingSoonAdapter);
        // 网格布局管理器
        mRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 3));


    }
}
