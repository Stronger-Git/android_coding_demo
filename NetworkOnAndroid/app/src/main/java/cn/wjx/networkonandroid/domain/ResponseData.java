package cn.wjx.networkonandroid.domain;

import java.util.List;

/**
 * @author WuChangJian
 * @date 2020/4/19 13:12
 */
public class ResponseData {
    /**
     * success : true
     * code : 10000
     * message : 获取成功
     * data : {"content":[{"_id":"1192789820909293568","content":"重要消息宣布，今天周五了！","type":0,"thumbUpCount":1,"commentCount":0,"url":"","covers":"","subContent":null,"subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-08 21:03","thumbList":["1153952789488054272"],"subTitle":null,"position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":[]},{"_id":"1192744895400382464","content":"我发表了一篇文章，欢迎大家围观.!","type":1,"thumbUpCount":0,"commentCount":0,"url":"/a/1192343055798112256","covers":"http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV3Dz26AfeavAAC2NhyGGf4789.png","subContent":null,"subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-08 18:05","thumbList":[],"subTitle":"内容提供者-获取手机通讯里的联系人","position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":["http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV3Dz26AfeavAAC2NhyGGf4789.png"]},{"_id":"1192444573612249088","content":"我分享了以下内容，欢迎大家围观，起哄!","type":1,"thumbUpCount":0,"commentCount":0,"url":"/s/1192443373630263296","covers":"http://imgs.sunofbeaches.com/group1/M00/00/09/rBsADV3EJgmAD6zgAAeO5T7L8B8937.png","subContent":"这个截图软件挺好用的，可以截图，可以取色，可以滚动窗口截图，可以用快捷键，如果要破解码的话，在百度搜索一下就有。 用户：c1ikm 注册码：AXMQX-RMMMJ-DBHHF-WIHTV ","subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-07 22:11","thumbList":[],"subTitle":"推荐大家一个截图软件，PC端的。","position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":["http://imgs.sunofbeaches.com/group1/M00/00/09/rBsADV3EJgmAD6zgAAeO5T7L8B8937.png"]},{"_id":"1192382176147017728","content":"我发表了一篇文章，欢迎大家围观.!","type":1,"thumbUpCount":0,"commentCount":0,"url":"/a/1192351879502237696","covers":"http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV3D2YKAKtI8AACOJuyZd_c895.png","subContent":null,"subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-07 18:03","thumbList":[],"subTitle":"Android6.0+应用权限获取步骤","position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":["http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV3D2YKAKtI8AACOJuyZd_c895.png"]},{"_id":"1192096263626035200","content":"发现好多程序员都太单纯了。总是被别人为难，包括来自HR的，领导的，产品的。所以呀，广大的同学们，在搞技术的同时，还是要多读历史呢。","type":0,"thumbUpCount":1,"commentCount":1,"url":"","covers":"","subContent":null,"subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-06 23:07","thumbList":["1182220290395049984"],"subTitle":null,"position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":[]},{"_id":"1191727758569377792","content":"我发表了一篇文章，欢迎大家围观.!","type":1,"thumbUpCount":0,"commentCount":0,"url":"/a/1190181827806019584","covers":"http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV277mmAcBJaAAHkjQLq12A395.jpg","subContent":null,"subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-05 22:43","thumbList":[],"subTitle":"内容提供者-向日历中插入提醒事件","position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":["http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV277mmAcBJaAAHkjQLq12A395.jpg"]},{"_id":"1191415817351204864","content":"我发表了一篇文章，欢迎大家围观.!","type":1,"thumbUpCount":0,"commentCount":0,"url":"/a/1190831019729666048","covers":"http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV2-lQOALmxoAACaadO1-uc637.png","subContent":null,"subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-05 02:04","thumbList":[],"subTitle":"Android自定控件的步骤","position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":["http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV2-lQOALmxoAACaadO1-uc637.png"]},{"_id":"1190616003155902464","content":"我发表了一篇文章，欢迎大家围观.!","type":1,"thumbUpCount":0,"commentCount":0,"url":"/a/1190576828469788672","covers":"http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29XPGAIMVpAAD6npnTk5U318.png|http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29Y96AIbAMAAORNHe_uKo234.png|http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29X9-AUB0-AAKhp0fcT_c343.png","subContent":null,"subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-02 21:05","thumbList":[],"subTitle":"Android自定义控件的分类","position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":["http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29XPGAIMVpAAD6npnTk5U318.png","http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29Y96AIbAMAAORNHe_uKo234.png","http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29X9-AUB0-AAKhp0fcT_c343.png"]},{"_id":"1190615994150731776","content":"我发表了一篇文章，欢迎大家围观.!","type":1,"thumbUpCount":0,"commentCount":0,"url":"/a/1190567838235017216","covers":"http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29WBGASoJrAABAePEyahY198.png","subContent":null,"subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-02 21:05","thumbList":[],"subTitle":"Android-View体系","position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":["http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29WBGASoJrAABAePEyahY198.png"]},{"_id":"1190615986869420032","content":"我发表了一篇文章，欢迎大家围观.!","type":1,"thumbUpCount":0,"commentCount":0,"url":"/a/1190542284517588992","covers":"http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29PZiASNiMAABuH5hexlw958.png","subContent":null,"subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-02 21:05","thumbList":[],"subTitle":"自定义控件课程规划","position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":["http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29PZiASNiMAABuH5hexlw958.png"]},{"_id":"1190615929533284352","content":"我发表了一篇文章，欢迎大家围观.!","type":1,"thumbUpCount":0,"commentCount":0,"url":"/a/1190589291084955648","covers":"http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29fMmAaLwEAADC3C67Eqk492.png","subContent":null,"subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-02 21:05","thumbList":[],"subTitle":"Android开发RecyclerView的适配器notifyDataSetChange做了什么？","position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":["http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29fMmAaLwEAADC3C67Eqk492.png"]},{"_id":"1190535762181931008","content":"我分享了以下内容，欢迎大家围观，起哄!","type":1,"thumbUpCount":0,"commentCount":0,"url":"/s/1190534725836521472","covers":"http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29NF6AIveqAAD1OcdVhQM825.png","subContent":"AndroidStudio国内的下载地址，不用科学上网就可以下载AndroidStuido，这跟视频里的教程不一样，也比较简单。同学们可以直接通过这个网址去下载，这是google的官方网站。","subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-02 15:46","thumbList":[],"subTitle":"AndroidStudio国内的下载地址","position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":["http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29NF6AIveqAAD1OcdVhQM825.png"]},{"_id":"1190353628288827392","content":"我发表了一篇文章，欢迎大家围观.!","type":1,"thumbUpCount":0,"commentCount":0,"url":"/a/1190346983945060352","covers":"http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV28iiOAe_URAALakPmAXZ4080.png","subContent":null,"subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-02 03:43","thumbList":[],"subTitle":"程序员如何解决问题呢？","position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":["http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV28iiOAe_URAALakPmAXZ4080.png"]},{"_id":"1190170431722668032","content":"我发表了一篇文章，欢迎大家围观.!","type":1,"thumbUpCount":0,"commentCount":0,"url":"/a/1190105494002384896","covers":"http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV27yVaANbvLAAQyxklIDHk189.png","subContent":null,"subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-01 15:35","thumbList":[],"subTitle":"内容提供者-内容观察者","position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":["http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV27yVaANbvLAAQyxklIDHk189.png"]},{"_id":"1190120548722589696","content":"中午吃啥？slf4j","type":0,"thumbUpCount":0,"commentCount":0,"url":"","covers":"http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV27sa2ATf-lAAG77eSVups099.jpg","subContent":null,"subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-01 12:17","thumbList":[],"subTitle":null,"position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":["http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV27sa2ATf-lAAG77eSVups099.jpg"]}],"pageable":{"sort":{"sorted":true,"unsorted":false},"pageSize":15,"pageNumber":4,"offset":60,"paged":true,"unpaged":false},"totalElements":157,"last":false,"totalPages":11,"first":false,"sort":{"sorted":true,"unsorted":false},"numberOfElements":15,"size":15,"number":4}
     */

    private boolean success;
    private int code;
    private String message;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * content : [{"_id":"1192789820909293568","content":"重要消息宣布，今天周五了！","type":0,"thumbUpCount":1,"commentCount":0,"url":"","covers":"","subContent":null,"subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-08 21:03","thumbList":["1153952789488054272"],"subTitle":null,"position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":[]},{"_id":"1192744895400382464","content":"我发表了一篇文章，欢迎大家围观.!","type":1,"thumbUpCount":0,"commentCount":0,"url":"/a/1192343055798112256","covers":"http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV3Dz26AfeavAAC2NhyGGf4789.png","subContent":null,"subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-08 18:05","thumbList":[],"subTitle":"内容提供者-获取手机通讯里的联系人","position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":["http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV3Dz26AfeavAAC2NhyGGf4789.png"]},{"_id":"1192444573612249088","content":"我分享了以下内容，欢迎大家围观，起哄!","type":1,"thumbUpCount":0,"commentCount":0,"url":"/s/1192443373630263296","covers":"http://imgs.sunofbeaches.com/group1/M00/00/09/rBsADV3EJgmAD6zgAAeO5T7L8B8937.png","subContent":"这个截图软件挺好用的，可以截图，可以取色，可以滚动窗口截图，可以用快捷键，如果要破解码的话，在百度搜索一下就有。 用户：c1ikm 注册码：AXMQX-RMMMJ-DBHHF-WIHTV ","subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-07 22:11","thumbList":[],"subTitle":"推荐大家一个截图软件，PC端的。","position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":["http://imgs.sunofbeaches.com/group1/M00/00/09/rBsADV3EJgmAD6zgAAeO5T7L8B8937.png"]},{"_id":"1192382176147017728","content":"我发表了一篇文章，欢迎大家围观.!","type":1,"thumbUpCount":0,"commentCount":0,"url":"/a/1192351879502237696","covers":"http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV3D2YKAKtI8AACOJuyZd_c895.png","subContent":null,"subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-07 18:03","thumbList":[],"subTitle":"Android6.0+应用权限获取步骤","position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":["http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV3D2YKAKtI8AACOJuyZd_c895.png"]},{"_id":"1192096263626035200","content":"发现好多程序员都太单纯了。总是被别人为难，包括来自HR的，领导的，产品的。所以呀，广大的同学们，在搞技术的同时，还是要多读历史呢。","type":0,"thumbUpCount":1,"commentCount":1,"url":"","covers":"","subContent":null,"subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-06 23:07","thumbList":["1182220290395049984"],"subTitle":null,"position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":[]},{"_id":"1191727758569377792","content":"我发表了一篇文章，欢迎大家围观.!","type":1,"thumbUpCount":0,"commentCount":0,"url":"/a/1190181827806019584","covers":"http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV277mmAcBJaAAHkjQLq12A395.jpg","subContent":null,"subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-05 22:43","thumbList":[],"subTitle":"内容提供者-向日历中插入提醒事件","position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":["http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV277mmAcBJaAAHkjQLq12A395.jpg"]},{"_id":"1191415817351204864","content":"我发表了一篇文章，欢迎大家围观.!","type":1,"thumbUpCount":0,"commentCount":0,"url":"/a/1190831019729666048","covers":"http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV2-lQOALmxoAACaadO1-uc637.png","subContent":null,"subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-05 02:04","thumbList":[],"subTitle":"Android自定控件的步骤","position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":["http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV2-lQOALmxoAACaadO1-uc637.png"]},{"_id":"1190616003155902464","content":"我发表了一篇文章，欢迎大家围观.!","type":1,"thumbUpCount":0,"commentCount":0,"url":"/a/1190576828469788672","covers":"http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29XPGAIMVpAAD6npnTk5U318.png|http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29Y96AIbAMAAORNHe_uKo234.png|http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29X9-AUB0-AAKhp0fcT_c343.png","subContent":null,"subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-02 21:05","thumbList":[],"subTitle":"Android自定义控件的分类","position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":["http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29XPGAIMVpAAD6npnTk5U318.png","http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29Y96AIbAMAAORNHe_uKo234.png","http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29X9-AUB0-AAKhp0fcT_c343.png"]},{"_id":"1190615994150731776","content":"我发表了一篇文章，欢迎大家围观.!","type":1,"thumbUpCount":0,"commentCount":0,"url":"/a/1190567838235017216","covers":"http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29WBGASoJrAABAePEyahY198.png","subContent":null,"subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-02 21:05","thumbList":[],"subTitle":"Android-View体系","position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":["http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29WBGASoJrAABAePEyahY198.png"]},{"_id":"1190615986869420032","content":"我发表了一篇文章，欢迎大家围观.!","type":1,"thumbUpCount":0,"commentCount":0,"url":"/a/1190542284517588992","covers":"http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29PZiASNiMAABuH5hexlw958.png","subContent":null,"subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-02 21:05","thumbList":[],"subTitle":"自定义控件课程规划","position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":["http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29PZiASNiMAABuH5hexlw958.png"]},{"_id":"1190615929533284352","content":"我发表了一篇文章，欢迎大家围观.!","type":1,"thumbUpCount":0,"commentCount":0,"url":"/a/1190589291084955648","covers":"http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29fMmAaLwEAADC3C67Eqk492.png","subContent":null,"subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-02 21:05","thumbList":[],"subTitle":"Android开发RecyclerView的适配器notifyDataSetChange做了什么？","position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":["http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29fMmAaLwEAADC3C67Eqk492.png"]},{"_id":"1190535762181931008","content":"我分享了以下内容，欢迎大家围观，起哄!","type":1,"thumbUpCount":0,"commentCount":0,"url":"/s/1190534725836521472","covers":"http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29NF6AIveqAAD1OcdVhQM825.png","subContent":"AndroidStudio国内的下载地址，不用科学上网就可以下载AndroidStuido，这跟视频里的教程不一样，也比较简单。同学们可以直接通过这个网址去下载，这是google的官方网站。","subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-02 15:46","thumbList":[],"subTitle":"AndroidStudio国内的下载地址","position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":["http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV29NF6AIveqAAD1OcdVhQM825.png"]},{"_id":"1190353628288827392","content":"我发表了一篇文章，欢迎大家围观.!","type":1,"thumbUpCount":0,"commentCount":0,"url":"/a/1190346983945060352","covers":"http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV28iiOAe_URAALakPmAXZ4080.png","subContent":null,"subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-02 03:43","thumbList":[],"subTitle":"程序员如何解决问题呢？","position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":["http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV28iiOAe_URAALakPmAXZ4080.png"]},{"_id":"1190170431722668032","content":"我发表了一篇文章，欢迎大家围观.!","type":1,"thumbUpCount":0,"commentCount":0,"url":"/a/1190105494002384896","covers":"http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV27yVaANbvLAAQyxklIDHk189.png","subContent":null,"subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-01 15:35","thumbList":[],"subTitle":"内容提供者-内容观察者","position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":["http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV27yVaANbvLAAQyxklIDHk189.png"]},{"_id":"1190120548722589696","content":"中午吃啥？slf4j","type":0,"thumbUpCount":0,"commentCount":0,"url":"","covers":"http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV27sa2ATf-lAAG77eSVups099.jpg","subContent":null,"subUserName":null,"subUserAvatar":null,"subUserId":null,"userName":"拉大锯","userId":"1153952789488054272","userAvatar":"https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png","publishTime":"2019-11-01 12:17","thumbList":[],"subTitle":null,"position":"Java工程师","company":"阳光沙滩","hasThumbUp":false,"images":["http://imgs.sunofbeaches.com/group1/M00/00/08/rBsADV27sa2ATf-lAAG77eSVups099.jpg"]}]
         * pageable : {"sort":{"sorted":true,"unsorted":false},"pageSize":15,"pageNumber":4,"offset":60,"paged":true,"unpaged":false}
         * totalElements : 157
         * last : false
         * totalPages : 11
         * first : false
         * sort : {"sorted":true,"unsorted":false}
         * numberOfElements : 15
         * size : 15
         * number : 4
         */

        private PageableBean pageable;
        private int totalElements;
        private boolean last;
        private int totalPages;
        private boolean first;
        private SortBeanX sort;
        private int numberOfElements;
        private int size;
        private int number;
        private List<ContentBean> content;

        public PageableBean getPageable() {
            return pageable;
        }

        public void setPageable(PageableBean pageable) {
            this.pageable = pageable;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public SortBeanX getSort() {
            return sort;
        }

        public void setSort(SortBeanX sort) {
            this.sort = sort;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class PageableBean {
            /**
             * sort : {"sorted":true,"unsorted":false}
             * pageSize : 15
             * pageNumber : 4
             * offset : 60
             * paged : true
             * unpaged : false
             */

            private SortBean sort;
            private int pageSize;
            private int pageNumber;
            private int offset;
            private boolean paged;
            private boolean unpaged;

            public SortBean getSort() {
                return sort;
            }

            public void setSort(SortBean sort) {
                this.sort = sort;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getPageNumber() {
                return pageNumber;
            }

            public void setPageNumber(int pageNumber) {
                this.pageNumber = pageNumber;
            }

            public int getOffset() {
                return offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }

            public boolean isPaged() {
                return paged;
            }

            public void setPaged(boolean paged) {
                this.paged = paged;
            }

            public boolean isUnpaged() {
                return unpaged;
            }

            public void setUnpaged(boolean unpaged) {
                this.unpaged = unpaged;
            }

            public static class SortBean {
                /**
                 * sorted : true
                 * unsorted : false
                 */

                private boolean sorted;
                private boolean unsorted;

                public boolean isSorted() {
                    return sorted;
                }

                public void setSorted(boolean sorted) {
                    this.sorted = sorted;
                }

                public boolean isUnsorted() {
                    return unsorted;
                }

                public void setUnsorted(boolean unsorted) {
                    this.unsorted = unsorted;
                }
            }
        }

        public static class SortBeanX {
            /**
             * sorted : true
             * unsorted : false
             */

            private boolean sorted;
            private boolean unsorted;

            public boolean isSorted() {
                return sorted;
            }

            public void setSorted(boolean sorted) {
                this.sorted = sorted;
            }

            public boolean isUnsorted() {
                return unsorted;
            }

            public void setUnsorted(boolean unsorted) {
                this.unsorted = unsorted;
            }
        }

        public static class ContentBean {
            /**
             * _id : 1192789820909293568
             * content : 重要消息宣布，今天周五了！
             * type : 0
             * thumbUpCount : 1
             * commentCount : 0
             * url :
             * covers :
             * subContent : null
             * subUserName : null
             * subUserAvatar : null
             * subUserId : null
             * userName : 拉大锯
             * userId : 1153952789488054272
             * userAvatar : https://imgs.sunofbeaches.com/group1/M00/00/07/rBsADV22ZymAV8BwAABVL9XtNSU926.png
             * publishTime : 2019-11-08 21:03
             * thumbList : ["1153952789488054272"]
             * subTitle : null
             * position : Java工程师
             * company : 阳光沙滩
             * hasThumbUp : false
             * images : []
             */

            private String _id;
            private String content;
            private int type;
            private int thumbUpCount;
            private int commentCount;
            private String url;
            private String covers;
            private Object subContent;
            private Object subUserName;
            private Object subUserAvatar;
            private Object subUserId;
            private String userName;
            private String userId;
            private String userAvatar;
            private String publishTime;
            private Object subTitle;
            private String position;
            private String company;
            private boolean hasThumbUp;
            private List<String> thumbList;
            private List<?> images;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getThumbUpCount() {
                return thumbUpCount;
            }

            public void setThumbUpCount(int thumbUpCount) {
                this.thumbUpCount = thumbUpCount;
            }

            public int getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(int commentCount) {
                this.commentCount = commentCount;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getCovers() {
                return covers;
            }

            public void setCovers(String covers) {
                this.covers = covers;
            }

            public Object getSubContent() {
                return subContent;
            }

            public void setSubContent(Object subContent) {
                this.subContent = subContent;
            }

            public Object getSubUserName() {
                return subUserName;
            }

            public void setSubUserName(Object subUserName) {
                this.subUserName = subUserName;
            }

            public Object getSubUserAvatar() {
                return subUserAvatar;
            }

            public void setSubUserAvatar(Object subUserAvatar) {
                this.subUserAvatar = subUserAvatar;
            }

            public Object getSubUserId() {
                return subUserId;
            }

            public void setSubUserId(Object subUserId) {
                this.subUserId = subUserId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUserAvatar() {
                return userAvatar;
            }

            public void setUserAvatar(String userAvatar) {
                this.userAvatar = userAvatar;
            }

            public String getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(String publishTime) {
                this.publishTime = publishTime;
            }

            public Object getSubTitle() {
                return subTitle;
            }

            public void setSubTitle(Object subTitle) {
                this.subTitle = subTitle;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public boolean isHasThumbUp() {
                return hasThumbUp;
            }

            public void setHasThumbUp(boolean hasThumbUp) {
                this.hasThumbUp = hasThumbUp;
            }

            public List<String> getThumbList() {
                return thumbList;
            }

            public void setThumbList(List<String> thumbList) {
                this.thumbList = thumbList;
            }

            public List<?> getImages() {
                return images;
            }

            public void setImages(List<?> images) {
                this.images = images;
            }
        }
    }
}
