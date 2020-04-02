// ThirdPartPayAction.aidl
package cn.wjx.alipay;
import cn.wjx.alipay.ThirdPartPayResult;
interface ThirdPartPayAction {
    void requestPay(in String orderInfo, in float money, in ThirdPartPayResult result);
}
