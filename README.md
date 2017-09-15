# LutoSystemBar

### 简单说明
***
> 为Android 4.4及以上提供状态栏和系统栏的半透明效果，也就是大部分人所说的沉浸式效果，同时支持图片可浸入状态栏和导航栏，通过链试调用的方式进行设置
### 效果图
***
* Android 4.4 效果
![Android 4.4 效果.jpg](http://upload-images.jianshu.io/upload_images/2369466-623c2d8c9fcf3b5f.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
* Android 5.1 效果
![Android 5.1 效果.jpg](http://upload-images.jianshu.io/upload_images/2369466-cc9c9ece7f4c53e8.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
* Android 6.0 效果
![Android 6.0.jpg](http://upload-images.jianshu.io/upload_images/2369466-1995bbeb26dc4813.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
### github地址
***
[https://github.com/chenykKits/LutoSystemBar](https://github.com/chenykKits/LutoSystemBar)
### apk下载
***
[V1.0.0](https://raw.githubusercontent.com/chenykKits/LutoSystemBar/master/apk/LutoSystemBar_Normal_V1.0.0.apk)

### 更新日志
***
##### V1.0.0 
* 为不同安卓版本适配沉浸式效果，仅对Android 4.4及以上有效
* 修改状态栏、导航栏颜色和透明度
* 图片可浸入状态栏和导航栏

### 用法
***
1. 下载配置
* Android studio用户，可在项目中的 build.gradle 文件中添加如下引用
```
dependencies {
    compile 'android.dev.chenyk:lutosystembar:1.0.0'
}
```
* Eclipse，将下载下来的aar包放入libs包中，[点我下载](https://github.com/chenykKits/LutoSystemBar/blob/master/jar/lutobarlib-release.aar)
2. 方法调用
```
LutoSystemBar.Builder(activity)
                .setStatusBarType(StatusBarType.PURECOLOR)//设置状态栏类型
                .setSystemBarColor(Color.parseColor("#FF4081"))//设置系统栏整体颜色
                .setSystemBarColorRes(R.color.colorPrimary)//设置系统栏整体颜色
                .setStatusBarColor(Color.parseColor("#FF4081"))//设置状态栏颜色,优先级高于SystemBarColor
                .setStatusBarColorRes(R.color.colorPrimary)//设置状态栏颜色,优先级高于SystemBarColor
                .translucentNavigationBar()//是否半透明状态栏
                .setNavigationBarColor(Color.parseColor("#FF4081"))//设置导航栏颜色，优先级高于SystemBarColor，必须调用translucentNavigationBar()方法之后才有效
                .setNavigationBarColorRes(R.color.colorPrimary)//设置导航栏颜色，优先级高于SystemBarColor，必须调用translucentNavigationBar()方法之后才有效
                .setSystemBarAlpha(80)//设置系统栏整体透明度
                .setStatusBarAlpha(80)//设置状态栏透明度，优先级高于SystemBarAlpha
                .setNavigationBarAlpha(80)//设置导航栏透明度，优先级高于SystemBarAlpha，必须调用translucentNavigationBar()方法之后才有效
                .translucentForImageView(titleView)//设置图片进入状态栏时，要下移的视图
                .build();//调用此方法参数配置才会生效
```
### 关键代码说明
***
* 针对不同Android版本将状态栏和导航栏透明
```
    /**
     * 为状态栏配置半透明效果
     */
    private void translucentStatusBarConfigForNarmal() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//安卓6.0及以上
            mWindow.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            mWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
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
            if (mSystemBarConfigBean.isTranslucentForImageView) {
                //图片浸入导航栏时配置
                mWindow.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            } else {
                mWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            }
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//安卓4.4~6.0
            mWindow.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
```
* 针对不同Android版本修改状态栏和导航栏的颜色和透明度
自Android 6.0以后，系统提供了状态栏和导航栏的颜色设置方法，只需调用设置即可；而对于6.0以下4.4以上的系统，则需要通过绘制一个跟状态栏高度一致的矩形条才能实现效果。
```
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
            mWindow.setStatusBarColor(statusBarColor);
            mWindow.setNavigationBarColor(navigationBarColorResult);
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//安卓4.4~6.0
            StatusBarView.addStatusBarView(mActivity, statusBarColor);
        }
        return this;
    }
```
* 普通模式下，需要配置根布局参数
```
    /**
     * 根布局设置
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void setRootView() {
        try {
            ViewGroup rootViewGroup = (ViewGroup) ((ViewGroup) mActivity.findViewById(android.R.id.content)).getChildAt(0);
            rootViewGroup.setFitsSystemWindows(true);
            rootViewGroup.setClipToPadding(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
```
