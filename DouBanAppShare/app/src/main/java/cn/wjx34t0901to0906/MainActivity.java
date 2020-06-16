package cn.wjx34t0901to0906;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;


import cn.wjx34t0901to0906.enums.CommonEnum;
import cn.wjx34t0901to0906.model.Movie;
import cn.wjx34t0901to0906.util.Constant;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "MainActivity";
    private FragmentManager mFragmentManager;
    private Fragment curFragment;// 保存当前Fragment


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 请求网络获取数据
                OkHttpClient httpClient = new OkHttpClient.Builder()
                        .connectTimeout(1000, TimeUnit.MILLISECONDS)
                        .build();
                Request request = new Request.Builder()
                        .url(Constant.URL_HOTTING)
                        .get()
                        .build();
                Call call = httpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        ResponseBody body = response.body();
                        JSONObject jsonObject;
                        JSONArray subjects;
                        Gson gson = new Gson();
                        try {
                            // 注意这里调用的是ResponseBody.string()方法，而不是toString
                            jsonObject = new JSONObject(body.string());
                            Log.d(TAG, "onResponse: " + jsonObject.toString());
                            subjects = jsonObject.getJSONArray("subjects");
                            Type typeOfT = new TypeToken<List<Movie>>(){}.getType();
                            List<Movie> movies = gson.fromJson(subjects.toString(), typeOfT);
                            Log.d(TAG, "subjects -->" + subjects.toString());
                            Snackbar snackbar = Snackbar.make(view, "电影名称:" + movies.get(0).getTitle()
                                    + " 上映日期:" + movies.get(0).getYear()
                                    + " 导演:" + movies.get(0).getDirectors().get(0).getName_en()
                                    + " 影片类型:" + movies.get(0).getGenres(), Snackbar.LENGTH_LONG);
                            View v = snackbar.getView();
                            TextView textView = v.findViewById(R.id.snackbar_text);
                            textView.setTextColor(Color.WHITE);
                            snackbar.show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        mFragmentManager = getSupportFragmentManager();
        // 设置默认的Fragment
        mFragmentManager.beginTransaction().add(R.id.relative_main, CommonEnum.fragmentOfId(R.id.nav_home), CommonEnum.NAV_HOME.getMsg()).commit();
        curFragment = CommonEnum.fragmentOfId(R.id.nav_home);
        this.getSupportActionBar().setTitle(CommonEnum.NAV_HOME.getMsg());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        // 新建枚举类，将字符串封装到到里面，实现共性需求
        switchFragment(id);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void switchFragment(int id) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        Fragment fragmentByTag = mFragmentManager.findFragmentByTag(CommonEnum.valueOfId(id));
        transaction.hide(curFragment);
        if (fragmentByTag != null) {
            // fragmentManger管理的实例中存在该引用
            transaction.show(fragmentByTag);
            curFragment = fragmentByTag;
        } else {
            // fragmentManger没有该实例，将其加入
            transaction.add(R.id.relative_main, CommonEnum.fragmentOfId(id), CommonEnum.valueOfId(id));
            curFragment = CommonEnum.fragmentOfId(id);
        }
        transaction.commit();
        this.getSupportActionBar().setTitle(CommonEnum.valueOfId(id));
    }
}
