package cn.wjx34t0901to0906.util;


/**
 * @author WuChangJian
 * @date 2020/5/21 19:55
 */
public class Constant {

    // 豆瓣电影API参考
    // https://blog.csdn.net/qq_34231078/article/details/106104174?utm_medium=distribute.pc_relevant.none-task-blog-baidujs-2
    public static String[] TITLES = new String[]{"正在热映", "即将上映", "北美票房"};

    public static final String URL_HOTTING = "https://api.douban.com/v2/movie/in_theaters?apikey=0df993c66c0c636e29ecbb5344252a4a";
    public static final String URL_HOTTING_LIMIT = "https://api.douban.com/v2/movie/in_theaters?apikey=0df993c66c0c636e29ecbb5344252a4a&start=0&count=2";
    public static final String URL_COMING_SOON = "https://api.douban.com/v2/movie/coming_soon?apikey=0df993c66c0c636e29ecbb5344252a4a";
    public static final String URL_COMING_SOON_LIMIT = "https://api.douban.com/v2/movie/coming_soon?apikey=0df993c66c0c636e29ecbb5344252a4a&start=0&count=2";
    public static final int RESPONSE_SUCCESS = 0;
    public static final int RESPONSE_ERROR = -1;
    public static int totalCount = 0;

    public static String getComingSoonLimitURL(int start) {
        return URL_COMING_SOON + "&count=2&start=" + start;
    }
    public static String getHottingLimitURL(int start) {
        return URL_HOTTING + "&count=2&start=" + start;
    }
}
