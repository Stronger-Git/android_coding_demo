package cn.wjx.networkonandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import cn.wjx.networkonandroid.adapter.ListAdapter;
import cn.wjx.networkonandroid.domain.ResponseData;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new ListAdapter();
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 使用Java API进行请求数据
     *
     * 注意点：
     * 1)声明网络权限
     * W/System.err: java.io.IOException: Cleartext HTTP traffic to api.sunofbeach.net not permitted
     * 处理：
     *      1.1) http请求
     *          1)降低API版本
     *          2)API27以上设置android:usesCleartextTraffic="true"(使用明文传输)
     *          3)添加网络安全配置
     *            xml->network_security_config.xml->Manifest.xml中
     *            声明android:networkSecurityConfig="@xml/network_security_config"
     *      1.2) https请求，默认就是加密传输，不需要设置android:usesCleartextTraffic="true"(使用明文传输)
     * 2)不能再主线程中请求数据
     * @param view
     */
    public void requestData(View view) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url;
                // 使用URLConnection的衍生类 HttpURLConnection
                HttpURLConnection connection;
                try {
                    //请求1)类url成功获得并不是json数据，301 Moved Permanently，不用关心这个，说明我们之前做的配置成功了
                    //1)url = new URL("http://api.sunofbeach.net/shop/api/discovery/categories");
                    //2)url = new URL("https://api.sunofbeach.net/shop/api/discovery/categories");
                    //https://www.sunofbeach.net/content/content/moment/list/1153952789488054272/1
                    url = new URL("https://www.sunofbeach.net/content/content/moment/list/1153952789488054272/1");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(1000);
                    connection.setRequestProperty("accept", "*/*");
                    connection.setRequestProperty("connection", "keep-alive");
                    connection.setRequestProperty("Accept-Language", "zh-CN,zh");
                    // 开始连接，也可以不设置，当获取流的时候，会建立连接
                    connection.connect();
                    // 获取响应码，HttpURLConnection特有方法
                    int responseCode = connection.getResponseCode();
                    Log.d(TAG, "responseCode-->" + responseCode);
                    if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_MOVED_PERM) {
                        InputStream in = connection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                        String res = bufferedReader.readLine();
                        Log.d(TAG, "requestData: "+res);
                        bufferedReader.close();
                        updateUI(res);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 子线程更新UI的方式
     *  1）runOnUiThread
     *  2）使用handler通信
     * @param res
     */
    private void updateUI(final String res) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 请求数据完成，装载数据,更新UI 需添加gson依赖
                Gson gson = new Gson();
                ResponseData responseData = gson.fromJson(res, ResponseData.class);
                // 适配器设置数据源
                List<ResponseData.DataBean.ContentBean> content = responseData.getData().getContent();
                mAdapter.setData(content);
            }
        });
    }
}
