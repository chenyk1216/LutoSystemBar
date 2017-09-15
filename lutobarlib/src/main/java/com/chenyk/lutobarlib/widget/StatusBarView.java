package com.chenyk.lutobarlib.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chenyk.lutobarlib.utils.BarViewUtil;

/**
 * Created by chenyk on 2017/9/7.
 * 系统栏视图（状态栏和导航栏）
 */

public class StatusBarView extends View {
    public StatusBarView(Context context) {
        super(context);
    }

    public StatusBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * 创建一个和状态栏高度一致的矩形，即伪状态栏
     *
     * @return 返回伪状态栏视图
     */
    private static StatusBarView createStatusBarView(Activity activity, int color) {
        StatusBarView statusBarView = new StatusBarView(activity);
        statusBarView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, BarViewUtil.getStatusBarHeight(activity)));
        statusBarView.setBackgroundColor(color);
        return statusBarView;
    }

    /**
     * 添加状态栏
     *
     * @param activity context
     */
    public static void addStatusBarView(Activity activity) {
        addStatusBarView(activity, Color.TRANSPARENT);
    }

    /**
     * 添加状态栏
     *
     * @param activity the context
     * @param color    status bar color
     */
    public static void addStatusBarView(Activity activity, int color) {
        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        StatusBarView statusBarView;
        if (decorView.getChildAt(0) instanceof StatusBarView) {
            statusBarView = (StatusBarView) decorView.getChildAt(0);
            if (statusBarView.getVisibility() == GONE) {
                statusBarView.setVisibility(VISIBLE);
            }
            statusBarView.setBackgroundColor(color);
        } else {
            statusBarView = createStatusBarView(activity, color);
            decorView.addView(statusBarView);
        }
    }

    /**
     * 隐藏状态栏
     *
     * @param activity
     */
    public static void hideStatusBarView(Activity activity) {
        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        if (decorView.getChildAt(0) instanceof StatusBarView) {
            StatusBarView statusBarView = (StatusBarView) decorView.getChildAt(0);
            if (statusBarView.getVisibility() == VISIBLE) {
                statusBarView.setVisibility(GONE);
            }
        }
    }
}
