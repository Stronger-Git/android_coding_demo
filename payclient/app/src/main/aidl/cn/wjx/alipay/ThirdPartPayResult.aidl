// ThirdPartPayResult.aidl
package cn.wjx.alipay;
interface ThirdPartPayResult {
    void paySuccess();
    void payFailed(in int errorCode, in String errMsg);
}
