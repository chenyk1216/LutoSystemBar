package com.chenyk.sample.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.chenyk.lutobarlib.LutoSystemBar;
import com.chenyk.sample.R;
import com.chenyk.sample.base.BaseActivity;


/**
 * Created by chenyk on 2017/8/29.
 * 状态栏图片延伸 - 部分图片效果
 */

public class PartImageViewActivity extends BaseActivity {
    private static final String EXTRA_ALPHA_KEY = "extraAlphaKey";
    TextView tvTitle;
    int mStatusAlpha;

    /**
     * 快捷启动当前页面
     */
    public static void startCurrentActivity(Activity activity, int statusAlpha) {
        Intent intent = new Intent(activity, PartImageViewActivity.class);
        intent.putExtra(EXTRA_ALPHA_KEY, statusAlpha);
        activity.startActivity(intent);
    }

    @Override
    protected void getIntenData(Intent intent) {
        super.getIntenData(intent);
        mStatusAlpha = intent.getIntExtra(EXTRA_ALPHA_KEY, 120);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(EXTRA_ALPHA_KEY, mStatusAlpha);
    }

    @Override
    protected void initView() {
        super.initView();
        tvTitle = (TextView) findViewById(R.id.tv_title);

        LutoSystemBar.Builder(this)
                .setStatusBarAlpha(mStatusAlpha)
                .translucentForImageView(tvTitle)
                .build();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.act_part_imageview;
    }
}
