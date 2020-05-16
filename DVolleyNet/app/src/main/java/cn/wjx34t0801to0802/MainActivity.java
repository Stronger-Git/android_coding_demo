package cn.wjx34t0801to0802;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;



public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private ImageView mVolleyImage;
    private EditText mImageUrl;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVolleyImage = findViewById(R.id.volley_image);
        mImageUrl = findViewById(R.id.image_url);
        mRequestQueue = Volley.newRequestQueue(this);

    }

    public void noCacheClick(View view) {
        loadImage();
    }

    private void loadImage() {
        String url = mImageUrl.getText().toString();
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                Log.d(TAG, "onResponse: ");
                mVolleyImage.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.FIT_XY, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error);
            }
        });
        mRequestQueue.add(imageRequest);
    }

    public void cacheClick(View view) {
        loadCacheImage();
    }

    private void loadCacheImage() {
        String url = mImageUrl.getText().toString();
        ImageLoader imageLoader = new ImageLoader(mRequestQueue, new LruBitmapCache(this));
        imageLoader.get(url, ImageLoader.getImageListener(mVolleyImage, R.mipmap.ic_launcher,R.mipmap.ic_launcher_round));
    }

    public void dobanActivity(View view) {
        startActivity(new Intent(this, DouBanActivity.class));
    }
}
