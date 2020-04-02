// ThirdPartPayAction.aidl
package cn.wjx.alipay;
import cn.wjx.alipay.ThirdPartPayResult;
interface ThirdPartPayAction {
    void requestPay(String orderInfo,float money,ThirdPartPayResult result);
}

