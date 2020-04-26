package cn.wjx.networkonandroid;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

/**
 * @author WuChangJian
 * @date 2020/4/19 16:26
 */
public class PictureActivity extends Activity {

    public static final String TAG = "PictureActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
    }

    public void requestImage(View view) throws IOException {
        compressBitmap();
    }


    public void compressBitmap() {
        ImageView imageView = findViewById(R.id.image_view);
        int height = imageView.getMeasuredHeight();
        int width = imageView.getMeasuredWidth();
        Log.d(TAG, "控件的宽高 " + "宽：" + width + " 高：" + height);
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 图片不加载进内存，只解析图片宽高等信息 --> options
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.large, options);
        int inSampleSize = 1;
        int scaleX,scaleY;//缩放比
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        Log.d(TAG, "原始图片大小 " + "宽：" + outWidth + " 高：" + outHeight);
        if (outWidth > width || outHeight > height) {
            scaleX = outWidth / width;
            scaleY = outHeight / height;
            inSampleSize = scaleX > scaleY ? scaleX : scaleY;
            // 设置新的缩放比
            options.inSampleSize = inSampleSize;
        }
        Log.d(TAG, "缩放比：" + inSampleSize);
        // 将接下来的图片加载内存
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.large, options);
        imageView.setImageBitmap(bitmap);

        // log --->
        // 控件的宽高 宽：1080 高：788
        // 原始图片大小 宽：4032 高：3024
        // 缩放比：3
    }
}
