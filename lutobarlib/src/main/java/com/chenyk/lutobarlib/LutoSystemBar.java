package com.chenyk.lutobarlib;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


import com.chenyk.lutobarlib.bean.SystemBarConfigBean;
import com.chenyk.lutobarlib.enums.StatusBarType;
import com.chenyk.lutobarlib.utils.BarConversionUtil;
import com.chenyk.lutobarlib.utils.BarResUtil;
import com.chenyk.lutobarlib.utils.BarViewUtil;
import com.chenyk.lutobarlib.widget.StatusBarView;

import java.lang.ref.WeakReference;

/**
 * Created by chenyk on 2017/9/7.
 * 系统栏操作管理类
 */

public final class LutoSystemBar {
    private static final String TAG = LutoSystemBar.class.getSimpleName();

    private static final int TAG_MOVE_DOWN_VIEW = R.id.view_move_down;//下移视图tag
    private Activity mActivity;
    private Window mWindow;//当前窗口实例
    private SystemBarConfigBean mSystemBarConfigBean;//配置窗口实例
    private boolean mIsConfigForStatusBar;//状态栏窗口是否已配置

    private static WeakReference<LutoSystemBar> mWeakReferenceBar;//当前类对象的弱引用

    /**
     * 构造函数
     *
     * @param activity
     */
    private LutoSystemBar(Activity activity) {
        mActivity = activity;
        init();
    }

    /**
     * 初始化操作
     */
    private void init() {
        mSystemBarConfigBean = new SystemBarConfigBean();
        mWindow = mActivity.getWindow();
    }


    /**
     * 创建实例
     *
     * @param activity
     * @return
     */
    public static LutoSystemBar Builder(@NonNull Activity activity) {

        mWeakReferenceBar = new WeakReference<>(new LutoSystemBar(activity));
        return mWeakReferenceBar.get();
    }

//    /**
//     * 获取当前类实例，可重新配置参数
//     *
//     * @return
//     */
//    public static LutoSystemBar newBuilder() {
//        if (mWeakReference == null) {
//            throw new IllegalArgumentException("请先调用Builder(Activity)对LuSystemBar实例化");
//        }
//        return mWeakReference.get();
//    }


    /**
     * 状态栏类型设置
     *
     * @param statusBarType
     * @return
     */
    public LutoSystemBar setStatusBarType(StatusBarType statusBarType) {
        mSystemBarConfigBean.statusBarType = statusBarType;
        return this;
    }

    /**
     * 系统栏颜色设置
     *
     * @param systemBarColor 接收的值有：Color.parseColor("#dc143c")、Color.rgb(220, 20, 60)、0xffdc143c
     * @return
     */
    public LutoSystemBar setSystemBarColor(@ColorInt int systemBarColor) {
        mSystemBarConfigBean.systemBarColor = systemBarColor;
        return this;
    }

    /**
     * 系统栏颜色设置
     *
     * @param systemBarColor 接收的值有：R.color.red
     * @return
     */
    public LutoSystemBar setSystemBarColorRes(@ColorRes int systemBarColor) {
        mSystemBarConfigBean.systemBarColor = systemBarColor;
        return this;
    }

    /**
     * 状态栏颜色设置
     *
     * @param statusBarColor
     * @return
     */
    public LutoSystemBar setStatusBarColor(@ColorInt int statusBarColor) {
        mSystemBarConfigBean.statusBarColor = statusBarColor;
        return this;
    }

    /**
     * 状态栏颜色设置
     *
     * @param statusBarColor
     * @return
     */
    public LutoSystemBar setStatusBarColorRes(@ColorRes int statusBarColor) {
        mSystemBarConfigBean.statusBarColor = statusBarColor;
        return this;
    }

    /**
     * 导航栏颜色设置
     *
     * @param navigationBarColor
     * @return
     */
    public LutoSystemBar setNavigationBarColor(@ColorInt int navigationBarColor) {
        mSystemBarConfigBean.navigationBarColor = navigationBarColor;
        return this;
    }

    /**
     * 导航栏颜色设置
     *
     * @param navigationBarColor
     * @return
     */
    public LutoSystemBar setNavigationBarColorRes(@ColorRes int navigationBarColor) {
        mSystemBarConfigBean.navigationBarColor = navigationBarColor;
        return this;
    }

    /**
     * 系统栏透明度设置
     *
     * @param systemBarAlpha
     * @return
     */
    public LutoSystemBar setSystemBarAlpha(@IntRange(from = 0, to = 255) int systemBarAlpha) {
        mSystemBarConfigBean.systemBarAlpha = systemBarAlpha;
        return this;
    }

    /**
     * 状态栏透明度设置
     *
     * @param statusBarAlpha
     * @return
     */
    public LutoSystemBar setStatusBarAlpha(@IntRange(from = 0, to = 255) int statusBarAlpha) {
        mSystemBarConfigBean.statusBarAlpha = statusBarAlpha;
        return this;
    }

    /**
     * 导航栏透明度设置
     *
     * @param navigationBarAlpha
     * @return
     */
    public LutoSystemBar setNavigationBarAlpha(@IntRange(from = 0, to = 255) int navigationBarAlpha) {
        mSystemBarConfigBean.navigationBarAlpha = navigationBarAlpha;
        return this;
    }

    /**
     * 图片侵入状态栏时，可修改状态栏颜色
     *
     * @return
     */
    public LutoSystemBar changeStatusColorForImageView() {
        mSystemBarConfigBean.changeStatusColorForImageView = true;
        return this;
    }

    /**
     * 设置导航栏半透明效果
     *
     * @return
     */
    public LutoSystemBar translucentNavigationBar() {
        mSystemBarConfigBean.translucentNavigationBar = true;
        return this;
    }

    /**
     * 为头部或背景为ImageView的页面设置状态栏或导航栏半透明效果
     *
     * @param moveDownView 为状态栏腾出空间的下移视图，如果不设置的话，页面的视图会在状态栏区域开始布局
     */
    public LutoSystemBar translucentForImageView(View moveDownView) {
        mSystemBarConfigBean.isTranslucentForImageView = true;
        if (moveDownView != null) {
            Object moveDownViewTag = moveDownView.getTag(TAG_MOVE_DOWN_VIEW);
            if (moveDownViewTag != null && (Boolean) moveDownViewTag) return this;
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) moveDownView.getLayoutParams();
            layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin + BarViewUtil.getStatusBarHeight(mActivity),
                    layoutParams.rightMargin, layoutParams.bottomMargin);
            moveDownView.setTag(TAG_MOVE_DOWN_VIEW, true);
        }
        return this;
    }

    /**
     * 获取状态栏颜色
     *
     * @return
     */
    private int getStatusBarColor() {
        if (mSystemBarConfigBean.statusBarColor == -1) {
            return mSystemBarConfigBean.systemBarColor == -1 ? Color.TRANSPARENT : mSystemBarConfigBean.systemBarColor;
        } else {
            return mSystemBarConfigBean.statusBarColor;
        }
    }

    /**
     * 获取导航栏颜色
     *
     * @return
     */
    private int getNavigationBarColor() {
        if (mSystemBarConfigBean.navigationBarColor == -1) {
            return mSystemBarConfigBean.systemBarColor == -1 ? Color.BLACK : mSystemBarConfigBean.systemBarColor;
        } else {
            return mSystemBarConfigBean.navigationBarColor;
        }
    }

    /**
     * 获取状态栏透明度
     *
     * @return
     */
    private int getStatusBarAlpha() {
        if (mSystemBarConfigBean.statusBarAlpha == -1) {
            return mSystemBarConfigBean.systemBarAlpha == -1 ? 0 : mSystemBarConfigBean.systemBarAlpha;
        } else {
            return mSystemBarConfigBean.statusBarAlpha;
        }
    }

    /**
     * 获取导航栏透明度
     *
     * @return
     */
    private int getNavigationBarAlpha() {
        if (mSystemBarConfigBean.navigationBarAlpha == -1) {
            return mSystemBarConfigBean.systemBarAlpha == -1 ? 0 : mSystemBarConfigBean.systemBarAlpha;
        } else {
            return mSystemBarConfigBean.navigationBarAlpha;
        }
    }

    /**
     * 最后调用该方法，以上配置才可生效
     *
     * @return
     */
    public LutoSystemBar build() {
        if (!mSystemBarConfigBean.isTranslucentForImageView) {
            if (!mIsConfigForStatusBar) {
                translucentStatusBarConfigForNarmal();
                setRootView();
                mIsConfigForStatusBar = true;
            }
            translucentNavBarConfig();
            int statusBarColor = BarConversionUtil.calculateColorWithAlpha(
                    BarResUtil.getColor(mActivity, getStatusBarColor()), getStatusBarAlpha());
            changeSystemBarColor(statusBarColor);
        } else {
            if (!mIsConfigForStatusBar) {
                translucentStatusBarConfigForImageview();
                mIsConfigForStatusBar = true;
            }
            translucentNavBarConfig();
            int statusBarColor = mSystemBarConfigBean.changeStatusColorForImageView ? BarConversionUtil.calculateColorWithAlpha(
                    BarResUtil.getColor(mActivity, getStatusBarColor()), getStatusBarAlpha()) : Color.argb(getStatusBarAlpha(), 0, 0, 0);
            changeSystemBarColor(statusBarColor);
        }
        return this;
    }

    /**
     * 修改系统栏颜色
     *
     * @param statusBarColor
     * @return
     */
    private LutoSystemBar changeSystemBarColor(int statusBarColor) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return this;
        int navigationBarColorResult = BarConversionUtil.calculateColorWithAlpha(
                BarResUtil.getColor(mActivity, getNavigationBarColor()), getNavigationBarAlpha());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//安卓6.0及以上
            if (StatusBarType.PURECOLOR == mSystemBarConfigBean.statusBarType) {
                mWindow.setStatusBarColor(statusBarColor);
            } else if (StatusBarType.GRADIENT == mSystemBarConfigBean.statusBarType) {
                StatusBarView.addStatusBarView(mActivity, statusBarColor);
            }
            if (mSystemBarConfigBean.translucentNavigationBar) {
                mWindow.setNavigationBarColor(navigationBarColorResult);
            }
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//安卓4.4~6.0
            StatusBarView.addStatusBarView(mActivity, statusBarColor);
        }
        return this;
    }

    /**
     * 为状态栏配置半透明效果
     */
    private void translucentStatusBarConfigForNarmal() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//安卓6.0及以上
            if (StatusBarType.PURECOLOR == mSystemBarConfigBean.statusBarType) {
                mWindow.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                mWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            } else if (StatusBarType.GRADIENT == mSystemBarConfigBean.statusBarType) {
                mWindow.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//安卓4.4~6.0
            mWindow.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 为实现图片浸入状态栏或导航栏效果的情况，设置相关窗口配置
     * 默认此情况下，状态栏颜色不可修改,如果非要修改，则需调用{@link #changeStatusColorForImageView()}开启功能
     */
    private void translucentStatusBarConfigForImageview() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mWindow.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M &&
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mWindow.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 配置导航栏半透明效果
     */
    private void translucentNavBarConfig() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//安卓6.0及以上
            if (mSystemBarConfigBean.translucentNavigationBar) {
                if (mSystemBarConfigBean.isTranslucentForImageView) {
                    //图片浸入导航栏时配置
                    mWindow.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                } else {
                    mWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                }
            }
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//安卓4.4~6.0
            if (mSystemBarConfigBean.translucentNavigationBar) {
                mWindow.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
        }
    }

    /**
     * 为4.4设置窗口配置
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void windowConfigForKitkat() {
        mWindow.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (mSystemBarConfigBean.translucentNavigationBar) {
            mWindow.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 根布局设置
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void setRootView() {
        try {
            ViewGroup rootViewGroup = (ViewGroup) ((ViewGroup) mActivity.findViewById(android.R.id.content)).getChildAt(0);
            rootViewGroup.setFitsSystemWindows(true);
            rootViewGroup.setClipToPadding(true);
            //用于4.4-6.0之间的导航栏变色
//            if (mSystemBarConfigBean.translucentNavigationBar) {
//                RelativeLayout layout = new RelativeLayout(mActivity);
//                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                layout.setLayoutParams(lp);
//                layout.setBackgroundColor(Color.WHITE);
//
//                rootViewGroup.setBackgroundColor(BarConversionUtil.calculateColorWithAlpha(BarResUtil.getColor(mActivity, getNavigationBarColor()), getNavigationBarAlpha()));
//                layout.addView(rootViewGroup);
//                rootViewGroup.removeAllViews();
//                rootViewGroup.addView(layout);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
