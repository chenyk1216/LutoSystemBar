package com.chenyk.lutobarlib.bean;

import com.chenyk.lutobarlib.enums.StatusBarType;

/**
 * Created by chenyk on 2017/9/7.
 * 系统栏相关配置参数数据模型
 */

public class SystemBarConfigBean {
    public StatusBarType statusBarType = StatusBarType.PURECOLOR;//状态栏类型
    public int systemBarColor = -1;//系统栏整体颜色(状态栏+导航栏)
    public int statusBarColor = -1;//状态栏颜色,优先级高于SystemBarColor
    public int navigationBarColor = -1;//导航栏颜色,优先级高于SystemBarColor
    public int systemBarAlpha = -1;//系统栏整体透明度
    public int statusBarAlpha = -1;//状态栏透明度,优先级高于barAlpha
    public int navigationBarAlpha = -1;//导航栏透明度,优先级高于barAlpha
    //    public boolean translucentStatusBar;//是否半透明状态栏,默认开启
    public boolean translucentNavigationBar;//是否半透明导航栏
    public boolean isTranslucentForImageView;//是否为图片浸入状态栏或导航栏效果的情况设置半透明效果
    public boolean changeStatusColorForImageView;//图片侵入状态栏时，是否可修改状态栏颜色
}
