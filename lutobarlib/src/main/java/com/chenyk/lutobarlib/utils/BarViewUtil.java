package com.chenyk.lutobarlib.utils;

import android.app.Activity;

/**
 * Created by chenyk on 2017/9/7.
 * 视图工具类
 */

public class BarViewUtil {
    /**
     * 获取状态栏高度
     *
     * @param activity context
     * @return height of the status bar
     */
    public static int getStatusBarHeight(Activity activity) {
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return activity.getResources().getDimensionPixelSize(resourceId);
    }
}
