package cn.wcj.service_demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * @author WuChangJian
 * @date 2020/3/19 20:20
 */
public class FirstService extends Service {
    public static final String TAG = "FirstService";
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Service onCreate...");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Service onStartCommand...");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Service onDestroy...");
    }

    public void innerMethod() {
        Log.d(TAG, "innerMethod...");
        Toast.makeText(this, "say hello!", Toast.LENGTH_SHORT).show();
    }
}
