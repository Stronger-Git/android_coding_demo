package cn.wcj.service_demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * @author WuChangJian
 * @date 2020/3/20 15:44
 */
public class ThirdService extends Service {
    public static final String TAG = "ThirdService";
    private class CommunicateBinder extends Binder implements IServiceControl{
        @Override
        public void callInnerMethod() {
            innerMethod();
        }
    }

    private void innerMethod() {
        Log.d(TAG, "innerMethod..");
    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "Service onBind...");
        return new CommunicateBinder();
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


    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "Service onUnbind...");
        return super.onUnbind(intent);
    }
}
