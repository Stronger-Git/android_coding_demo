package cn.wjx.alipay;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * @author WuChangJian
 * @date 2020/3/29 15:16
 */
public class PayService extends Service {
    public static final String TAG = "PayService";
    private ThirdPartPayImpl mThirdPartPay;

    @Override
    public IBinder onBind(Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "onBind -- > action:" +action);
        if (action != null && "cn.wjx.alipay.THIRD_PART_PAY".equals(action)) {
            // 第三方应用调起支付服务
            mThirdPartPay = new ThirdPartPayImpl();
            return mThirdPartPay;
        }
        return new PayAction();
    }

    public class PayAction extends Binder {
        public void pay(float money) {
            Log.d(TAG, "pay--->" + money);
            if (mThirdPartPay != null) {
                try {
                    mThirdPartPay.callBack.paySuccess();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        public void onUserCancel() {
            Log.d(TAG, "onUserCancel");
            if (mThirdPartPay != null) {
                try {
                    mThirdPartPay.callBack.payFailed(0, "用户取消");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class ThirdPartPayImpl extends ThirdPartPayAction.Stub {
        private ThirdPartPayResult callBack;

        @Override
        public void requestPay(String orderInfo, float money, ThirdPartPayResult result) throws RemoteException {
            this.callBack = result;
            // 第三方应用发起请求打开一个支付界面
            Intent intent = new Intent(PayService.this, PayActivity.class);
            intent.putExtra(Constants.PAY_ORDER_INFO, orderInfo);
            intent.putExtra(Constants.PAY_MONEY, money);
            // 服务通过intent调用activity，分开任务栈
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
