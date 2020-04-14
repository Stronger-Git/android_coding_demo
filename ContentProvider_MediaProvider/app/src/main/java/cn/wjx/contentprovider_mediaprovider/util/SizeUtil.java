package cn.wjx.contentprovider_mediaprovider.util;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

/**
 * @author WuChangJian
 * @date 2020/4/11 20:33
 */
public class SizeUtil {
    public static Point getScreenSize(Context context) {
        Point point = new Point();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getSize(point);
        return point;
    }
}

