package com.chenyk.sample.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.chenyk.lutobarlib.LutoSystemBar;
import com.chenyk.sample.R;
import com.chenyk.sample.base.BaseActivity;


/**
 * Created by chenyk on 2017/8/28.
 * 状态栏图片延伸 - 全图片背景效果
 */

public class FullImageViewActivity extends BaseActivity {
    private static final String EXTRA_ALPHA_KEY = "extraAlphaKey";
    private static final String EXTRA_NAVBAR_TRANS_KEY = "extraNavBarTransKey";
    TextView tvTitle;
    boolean mIsNavBarTrans;
    int mStatusAlpha;

    /**
     * 快捷启动当前页面
     *
     * @param activity
     * @param isNavBarTrans
     * @param statusAlpha
     */
    public static void startCurrentActivity(Activity activity, boolean isNavBarTrans, int statusAlpha) {
        Intent intent = new Intent(activity, FullImageViewActivity.class);
        intent.putExtra(EXTRA_ALPHA_KEY, statusAlpha);
        intent.putExtra(EXTRA_NAVBAR_TRANS_KEY, isNavBarTrans);
        activity.startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(EXTRA_ALPHA_KEY, mStatusAlpha);
        outState.putBoolean(EXTRA_NAVBAR_TRANS_KEY, mIsNavBarTrans);
    }

    @Override
    protected void getIntenData(Intent intent) {
        super.getIntenData(intent);
        mStatusAlpha = intent.getIntExtra(EXTRA_ALPHA_KEY, 120);
        mIsNavBarTrans = intent.getBooleanExtra(EXTRA_NAVBAR_TRANS_KEY, false);
    }

    @Override
    protected void initView() {
        super.initView();
        tvTitle = (TextView) findViewById(R.id.tv_title);

        LutoSystemBar lutoSystemBar = LutoSystemBar.Builder(this)
                .setStatusBarAlpha(mStatusAlpha)
                .translucentForImageView(tvTitle);
        if (mIsNavBarTrans) {
            lutoSystemBar.translucentNavigationBar();
        }
        lutoSystemBar.build();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.act_full_bg_imageview;
    }

}
