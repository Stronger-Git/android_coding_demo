package cn.wjx.networkonandroid;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import cn.wjx.networkonandroid.domain.CommentItem;

import static android.os.Build.VERSION.SDK_INT;
import static java.net.HttpURLConnection.HTTP_OK;

/**
 * @author WuChangJian
 * @date 2020/4/21 14:44
 */
public class PostActivity extends Activity {
    public static final String TAG = "PostActivity";
    private static final int REQUEST_READ_SD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        if (SDK_INT > Build.VERSION_CODES.M) {
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
            if (checkSelfPermission(permissions[0]) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(permissions, REQUEST_READ_SD);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_READ_SD) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "onRequestPermissionsResult: success!");
            }
        }
    }

    /**
     * 使用POST发送一条数据
     * TODO
     * get请求没有请求体，post请求有请求体
     * fiddler 抓包测试 https://www.jianshu.com/p/724097741bdf
     *      * 可以抓到发起请求的包，但是响应502，有待调试
     * @param view
     */
    public void postData(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OutputStream out = null;
                InputStream in = null;
                try {
                    URL url = new URL("http://10.0.2.2:9102/post/comment");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    // POST /api/article/comment/ HTTP/1.1
                    // Host: api.sunofbeach.net
                    // Connection: keep-alive
                    // Content-Length: 82
                    // Accept: application/json, text/plain, */*
                    // Sec-Fetch-Dest: empty
                    // User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.106 Safari/537.36
                    // Content-Type: application/json;charset=UTF-8
                    // Origin: https://www.sunofbeach.net
                    // Sec-Fetch-Site: same-site
                    // Sec-Fetch-Mode: cors
                    // Referer: https://www.sunofbeach.net/a/1251487329449750528
                    // Accept-Encoding: gzip, deflate, br
                    // Accept-Language: zh-CN,zh;q=0.9
                    // Cookie: __gads=ID=1502bb44be3d9230:T=1587256011:S=ALNI_MY5cX_0qWKTt0BWR4lHTh5quFUf7g; Hm_lvt_6c04c2baa03e39986dbbefb4fa0e0c0b=1587367306,1587392558,1587427204,1587449943; sobtoken=b2b5d50d8e7a903874cb09d580a9d23a; Hm_lpvt_6c04c2baa03e39986dbbefb4fa0e0c0b=1587451583

                    // 设置必要的请求头信息
                    connection.setRequestMethod("POST");// POST必须大写
                    connection.setRequestProperty("Accept","application/json, text/plain, */*");
                    connection.setRequestProperty("Content-Type","application/json;charset=UTF-8");
                    connection.setConnectTimeout(2000);

                    CommentItem commentItem = new CommentItem("11211", "顶你一下！");
                    Gson gson = new Gson();
                    String jsonStr = gson.toJson(commentItem);
                    byte[] buff = jsonStr.getBytes();
                    // Content-Length:长度字节，非必须，但是 最好指定
                    // connection.setRequestProperty("Content-Length", String.valueOf(buff.length));
                    Log.d(TAG, "Content-Length -->" + buff.length);
                    // 获取输出流
                    out = connection.getOutputStream();
                    out.write(buff, 0, buff.length);
                    // 刷新并清空流中的数据
                    out.flush();

                    int responseCode = connection.getResponseCode();
                    Log.d(TAG, "responseCode-->" + responseCode);
                    if (responseCode == HTTP_OK) {
                        in = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        String line = reader.readLine();
                        Log.d(TAG, "result data -->" + line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                    if (in != null) {
                        Log.d(TAG, "InputStream closed");
                        in.close();
                    }
                    if (out != null)
                        Log.d(TAG, "OutputStream closed");
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 文件上传 [v1 ：单文件] [v2：单文件和多文件都支持]
     * 这里硬编码，上传一张手机SD卡中的图片
     * @param view
     */
    public void fileUpload(View view) {
        test();
    }

    /**
     * 文件上传（单文件上传）【v1版】
     */
    public void uploadFile() {
        String filenameKey = "file";
        String filename = "scope.jpeg";
        String fileType = "image/jpeg";// 文件类型(可以参考MIME)，实际也不应该写死
        InputStream in = null;
        OutputStream out = null;

        String boundary = "----WebKitFormBoundarys32hvc4bU2hQzCHa";//随机串
        //1.----WebKitFormBoundarys32hvc4bU2hQzCHa ContentType
        //2.------WebKitFormBoundarys32hvc4bU2hQzCHa 头
        //3.------WebKitFormBoundarys32hvc4bU2hQzCHa-- 尾
        try {
            // 多文件上传的API /files/upload
            URL url = new URL("http:10.0.2.2:9102/files/upload");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求头属性，如何设置，抓包测试 fiddler or postman
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept","application/json, text/plain, */*");
            connection.setRequestProperty("Accept-Encoding","gzip, deflate, br");
            connection.setRequestProperty("Content-Type","multipart/form-data; boundary="+boundary);

            // 将图片以流的形式发送给服务器，这里有硬性格式要求
            StringBuffer sbHeaderFormData = new StringBuffer();
            sbHeaderFormData.append("--" + boundary);// 开头多两个横杆
            sbHeaderFormData.append("\r\n");// 回车换行，兼容Linux和windows
            sbHeaderFormData.append("Content-Disposition: form-data; name=\""+filenameKey+"\"; filename=\""+filename+"\"");
            sbHeaderFormData.append("\r\n");
            sbHeaderFormData.append("Content-Type: "+fileType);
            sbHeaderFormData.append("\r\n");
            sbHeaderFormData.append("\r\n");
            // 打开流-->将文件头部信息写入
            out = connection.getOutputStream();
            out.write(sbHeaderFormData.toString().getBytes(StandardCharsets.UTF_8));

            // 图片流数据
            FileInputStream fis = new FileInputStream("/sdcard/Download/"+filename);
            byte[] buff = new byte[1024];
            int len = 0;
            // !!! 注意图片存储的是???
            while ((len = fis.read(buff)) != -1) {
                out.write(buff, 0, len);
            }
            fis.close();
            // 尾部
            StringBuffer sbFooterFormData = new StringBuffer();
            sbFooterFormData.append("\r\n");
            sbFooterFormData.append("--" + boundary + "--");
            sbFooterFormData.append("\r\n");
            out.write(sbFooterFormData.toString().getBytes(StandardCharsets.UTF_8));
            out.flush();
            // 获取响应
            int responseCode = connection.getResponseCode();
            if (responseCode == HTTP_OK) {
                in = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String res = reader.readLine();
                reader.close();
                Log.d(TAG, "result --> " + res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public void test() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String uploadUrl = "http:10.0.2.2:9102/files/upload";
                String fileKey = "files";
                File file1 = new File("/sdcard/Download/ac3.png");
                File file2 = new File("/sdcard/Download/kongfu.jpg");

                InputStream in = null;
                OutputStream out = null;
                String boundary = "----WebKitFormBoundarys32hvc4bU2hQzCHa";
                try {
                    URL url = new URL(uploadUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    // 设置请求头属性，如何设置，抓包测试 fiddler or postman
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Accept","application/json, text/plain, */*");
                    connection.setRequestProperty("Accept-Encoding","gzip, deflate, br");
                    connection.setRequestProperty("Content-Type","multipart/form-data; boundary="+boundary);

                    out = connection.getOutputStream();
                    uploadFiles(file1, out, false, boundary, fileKey);
                    uploadFiles(file2, out, true, boundary, fileKey);
                    out.flush();

                    // 获取响应
                    int responseCode = connection.getResponseCode();
                    if (responseCode == HTTP_OK) {
                        in = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        String res = reader.readLine();
                        reader.close();
                        Log.d(TAG, "result --> " + res);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (in != null) {
                            in.close();
                        }
                        if (out != null) {
                            out.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


            }
        }).start();

    }
    /**
     * 文件上传（多文件上传,同样支持单文件）【v2版】
     * 对代码进行了重构
     */

    public void uploadFiles(File file, OutputStream out, boolean isLast, String boundary, String fileKey) throws IOException {
        String filename = file.getName();
        String fileType = getMIME(filename.substring(filename.lastIndexOf(".") + 1));// 根据后缀确认->MIME
        Log.d(TAG, "filename -->" + filename + " fileType--> " + fileType);
        //头部
        StringBuffer sbHeaderFormData = new StringBuffer();
        sbHeaderFormData.append("--" + boundary);
        sbHeaderFormData.append("\r\n");
        sbHeaderFormData.append("Content-Disposition: form-data; name=\""+fileKey+"\"; filename=\""+filename+"\"");
        sbHeaderFormData.append("\r\n");
        sbHeaderFormData.append("Content-Type: "+fileType);
        sbHeaderFormData.append("\r\n");
        sbHeaderFormData.append("\r\n");
        out.write(sbHeaderFormData.toString().getBytes(StandardCharsets.UTF_8));

        // 图片流
        FileInputStream fis = new FileInputStream(file);
        byte[] buff = new byte[1024];
        int len;
        while ((len = fis.read(buff)) != -1) {
            out.write(buff, 0, len);
        }
        fis.close();

        // 尾部
        StringBuffer sbFooterFormData = new StringBuffer();
        sbFooterFormData.append("\r\n");
        if (isLast) {
            sbFooterFormData.append("--" + boundary + "--");
            sbFooterFormData.append("\r\n");
        }
        out.write(sbFooterFormData.toString().getBytes(StandardCharsets.UTF_8));
    }

    private String getMIME(String format) {
        // 只是简单的转化
        if ("jpeg".equals(format)||"jpg".equals(format))
            return "image/jpeg";
        if ("png".equals(format))
            return "image/png";
        return null;
    }

    /**
     * 文件下载
     * @param view
     */
    public void downloadFile(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String downloadUrl = "http:10.0.2.2:9102/download/1";//[1-16]

                InputStream in = null;
                OutputStream out = null;
                try {
                    URL url = new URL(downloadUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    // 设置请求头属性，如何设置，抓包测试 fiddler or postman
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(3000);
                    connection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.9");
                    connection.setRequestProperty("Accept","*/*");
                    connection.connect();
                    // 获取响应
                    int responseCode = connection.getResponseCode();
                    Log.d(TAG, "responseCode --> " + responseCode);
                    if (responseCode == HTTP_OK) {
                        // Response Header ：Content-disposition: attachment; filename=1.png
                        // 根据响应头获取文件名
                        String headerField = connection.getHeaderField("Content-disposition");
                        String fileName = headerField.replace("attachment; filename=", "");
                        Log.d(TAG, "headerField -- >" + headerField + " fileName-->" + fileName);
                        //getExternalFilesDir(Environment.DIRECTORY_PICTURES); 作用
                        File picFile = PostActivity.this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                        if (!picFile.exists())
                            picFile.mkdirs();
                        File file = new File(picFile + File.separator + fileName);
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        Log.d(TAG, "new file path-->" + file.getPath());
                        out = new FileOutputStream(file);
                        in = connection.getInputStream();
                        byte[] buff = new byte[1024];
                        int len;
                        while ((len = in.read(buff)) != -1) {
                            out.write(buff, 0 ,len);
                        }
                        out.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (in != null) {
                            in.close();
                        }
                        if (out != null) {
                            out.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }
}
