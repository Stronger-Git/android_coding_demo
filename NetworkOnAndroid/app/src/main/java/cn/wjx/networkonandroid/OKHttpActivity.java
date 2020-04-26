package cn.wjx.networkonandroid;

import android.app.Activity;
import android.app.usage.ExternalStorageStats;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import cn.wjx.networkonandroid.domain.CommentItem;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author WuChangJian
 * @date 2020/4/23 15:45
 */
public class OKHttpActivity extends Activity {

    public static final String TAG = "OKHttpActivity";
    public static final String BASE_URL = "http://10.0.2.2:9102";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
    }

    /**
     * Get请求
     * okhttp3版本是'com.squareup.okhttp3:okhttp:3.14.6'
     * @param view
     */
    public void requestGet(View view) {



        // 1. 实例化请求客户端
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(1000, TimeUnit.MILLISECONDS)
                .build();
        // 2. 创建一个请求
        Request request = new Request.Builder()
                .url(BASE_URL + "/get/text")
                .get()//默认get请求，可以省略
                .build();
        // 3. 客户端发起请求实例化Call对象，执行请求任务
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure...");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "异步 response code --> " + response.code());
                Log.d(TAG, "异步 response body-->" + response.body().string());
            }
        });
        // 异步get请求
        synchronizeGet();

    }

    public void synchronizeGet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request req = new Request.Builder()
                        .url(BASE_URL)
                        .get()
                        .build();
                Call call = client.newCall(req);
                Response response = null;
                try {
                    response = call.execute();

                if (response != null) {
                    Log.d(TAG, "同步 response code --> " + response.code());
                    Log.d(TAG, "同步 response body-->" + response.body().string());
                }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    /**
     * post提交字符串
     * @param view
     */
    public void postString(View view) {

        OkHttpClient client = new OkHttpClient();
        // POST请求需要RequestBody对象,RequestBody抽象类，借助RequestBody.create()实例化
        MediaType mediaType = MediaType.get("application/json;charset=UTF-8");// MediaType 不就是MIME类型吗
        CommentItem commentItem = new CommentItem("11211", "顶你一下！");
        Gson gson = new Gson();
        String content = gson.toJson(commentItem);
        RequestBody requestBody = RequestBody.create(mediaType, content);

        Request request = new Request.Builder()
                .url(BASE_URL + "/post/comment")
                .post(requestBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, response.protocol() + " " + response.code() + " " + response.message());
                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    Log.d(TAG, headers.name(i) + ":" + headers.value(i));
                }

                Log.d(TAG, "response body-->" + response.body().string());
            }
        });
        // 响应内容
        // http/1.1 200
        // Content-Type:application/json;charset=UTF-8
        // Transfer-Encoding:chunked
        // Date:Thu, 23 Apr 2020 09:26:36 GMT
        // response body-->{"success":true,"code":10000,"message":"评论成功:顶你一下！","data":null}

    }

    /**
     * post 单文件上传
     * @param view
     */
    public void postFile(View view) {
        String boundary = "----WebKitFormBoundarys32hvc4bU2hQzCHa";
        File file1 = new File("/sdcard/Download/ac3.png");
//        File file2 = new File("/sdcard/Download/test.doc");
        File file2 = new File("/sdcard/Download/hello.txt");


        OkHttpClient client = new OkHttpClient();

        // 这里爆出的疑问点：Content-Type到底需要自己设置吗？我boundary不加还能添加成功
        // 解答：不需要了，框架已经帮我们做好了！
//        MultipartBody.FORM
//        MediaType.get("multipart/form-data; boundary=" + boundary)
        //1. 第一种
        RequestBody requestBody1 = RequestBody.create(MultipartBody.FORM, file1);
        RequestBody requestBody2 = RequestBody.create(MultipartBody.FORM, file2);
        MultipartBody multiPartRequest = new MultipartBody.Builder()
                .addFormDataPart("files", file1.getName(), requestBody1)
                .addFormDataPart("files", file2.getName(), requestBody2)
                .build();
        Log.d(TAG, "postFile:" + multiPartRequest.boundary());
        /*MultipartBody multiPartRequest = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addPart(Headers.of("Content-Disposition: form-data; name=\"file\"; filename=\""+file.getName()+"\""),
                        RequestBody.create(MediaType.parse("image/png"), file))
                .build();*/

        Request request = new Request.Builder()
                .url(BASE_URL + "/files/upload")
                .post(multiPartRequest)
                .build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "异步 response code --> " + response.code());
                Log.d(TAG, "异步 response body-->" + response.body().string());
            }
        });


    }




    /**
     * post 多文件上传
     * @param view
     */
    public void postFiles(View view) {
        File file1 = new File("/sdcard/Download/ac3.png");
        File file2 = new File("/sdcard/Download/hello.txt");

        OkHttpClient client = new OkHttpClient();
        RequestBody body1 = RequestBody.create(MultipartBody.FORM, file1);
        RequestBody body2 = RequestBody.create(MultipartBody.FORM, file2);

        MultipartBody multipartBody = new MultipartBody.Builder()
                .addFormDataPart("files", file1.getName(), body1)
                .addFormDataPart("files", file2.getName(), body2)
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "/files/upload")
                .post(multipartBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "异步 response code --> " + response.code());
                Log.d(TAG, "异步 response body-->" + response.body().string());
            }
        });
    }


    /**
     * post提交表单数据
     * 后台没有相应的API，这里只是模拟
     * @param view
     */
    public void postFormData(View view) {
        // 后台没有相应的API，这里只是模拟
        OkHttpClient client = new OkHttpClient();

        // 上传表单数据（不包括文件） 默认Content-Type = application/x-www-form-urlencoded
        // 键值对形式
        /*FormBody formBody = new FormBody.Builder()
                .add("key", "value")
                .build();

        // 上传表单数据的同时包含文件 必须设置
        Content-Type = multipart/form-data
        new MultipartBody.Builder()
                .addFormDataPart("key1", "value1")
                .addFormDataPart("key2", "value2")
                ...
                .addFormDataPart("fileKey", "filename", RequestBody)
                .setType(MultipartBody.FORM)
                .build();*/

        // Content-Type = application/json

        FormBody formBody = new FormBody.Builder()
                .add("key", "value")
                .build();
        Request request = new Request.Builder()
                .url(BASE_URL + "/post/comment")
                .addHeader("Content-Type","application/x-www-form-urlencoded")
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "response code --> " + response.code());
                Log.d(TAG, "response body-->" + response.body().string());
            }
        });
    }

    public void downloadFiles(View view) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BASE_URL + "/download/3")
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "异步 response code --> " + response.code());
                Headers headers = response.headers();
                String filename = null;
                for (int i = 0; i < headers.size(); i++) {
                    Log.d(TAG, headers.name(i) + "---" + headers.value(i));
                    if ("Content-disposition".equals(headers.name(i))) {
                        Log.d(TAG, "value -- >" + headers.value(i));
                        filename = headers.value(i).substring(headers.value(i).lastIndexOf("=") + 1);
                        Log.d(TAG, "filename -- > " + filename);
                    }

                }

                // 从ResponseBody获取流
                ResponseBody responseBody = response.body();
                InputStream in = responseBody.byteStream();
                File externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                if (!externalFilesDir.exists()) {
                    externalFilesDir.mkdirs();
                }
                File downFile = new File(externalFilesDir + File.separator + filename);
                if (!downFile.exists()) {
                    downFile.createNewFile();
                }
                FileOutputStream fos = new FileOutputStream(downFile);
                byte[] buff = new byte[1024];
                int len;
                while ((len = in.read(buff, 0, buff.length)) != -1) {
                    fos.write(buff, 0 ,len);
                }

                fos.close();
                in.close();
                Log.d(TAG, "download path -->" + downFile.getPath());
                Log.d(TAG, "download success!");

            }
        });

    }
}
