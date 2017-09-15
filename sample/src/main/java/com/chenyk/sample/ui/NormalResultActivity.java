package com.chenyk.sample.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.chenyk.lutobarlib.LutoSystemBar;
import com.chenyk.lutobarlib.enums.StatusBarType;
import com.chenyk.sample.R;
import com.chenyk.sample.base.BaseActivity;

/**
 * Created by chenyk on 2017/4/12.
 * 普通效果页面
 */

public class NormalResultActivity extends BaseActivity {
    public static final String EXTRA_STATUSBAR_TYPE_KEY = "extraStatusBarTypeKey";//状态栏类型key
    private static final String EXTRA_NAVBAR_TRANS_KEY = "extraNavBarTransKey";

    View viewStatusbarColor, viewNavbarColor;
    Button btnStatusbarChangecolor, btnNavbarChangecolor;
    TextView tvStatusbarAlpha, tvNavbarAlpha, tvTip;
    SeekBar seekbarStatusbar, seekbarNavbar;
    LinearLayout llNavbarColor, llNavbarAlpha;

    private StatusBarType mStatusBarType;
    boolean mIsNavBarTrans;
    int mColorResPosition;
    private LutoSystemBar mLutoSystemBar;
    int colorRes[] = {Color.parseColor("#FF4500"), Color.parseColor("#F4A460"), Color.parseColor("#EEEE00"),
            Color.parseColor("#A2CD5A"), Color.parseColor("#98FB98"), Color.parseColor("#97FFFF"),
            Color.parseColor("#9932CC"), Color.parseColor("#006400"), Color.parseColor("#6A5ACD"),
            Color.parseColor("#8B6508"), Color.parseColor("#B03060"), Color.parseColor("#FF0000")};

    /**
     * 快捷启动当前页面
     */
    public static void startCurrentActivity(Activity activity, boolean isNavBarTrans, StatusBarType statusBarType) {
        Intent intent = new Intent(activity, NormalResultActivity.class);
        intent.putExtra(EXTRA_NAVBAR_TRANS_KEY, isNavBarTrans);
        intent.putExtra(EXTRA_STATUSBAR_TYPE_KEY, statusBarType);
        activity.startActivity(intent);
    }

    @Override
    protected void getIntenData(Intent intent) {
        super.getIntenData(intent);
        mStatusBarType = (StatusBarType) intent.getSerializableExtra(EXTRA_STATUSBAR_TYPE_KEY);
        mIsNavBarTrans = intent.getBooleanExtra(EXTRA_NAVBAR_TRANS_KEY, false);
    }

    @Override

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(EXTRA_STATUSBAR_TYPE_KEY, mStatusBarType);
        outState.putBoolean(EXTRA_NAVBAR_TRANS_KEY, mIsNavBarTrans);
    }

    @Override
    protected void initView() {
        super.initView();
        viewStatusbarColor = findViewById(R.id.view_statusbar_color);
        viewNavbarColor = findViewById(R.id.view_navbar_color);
        btnStatusbarChangecolor = (Button) findViewById(R.id.btn_statusbar_changecolor);
        btnNavbarChangecolor = (Button) findViewById(R.id.btn_navbar_changecolor);
        seekbarStatusbar = (SeekBar) findViewById(R.id.seekbar_statusbar);
        seekbarNavbar = (SeekBar) findViewById(R.id.seekbar_navbar);
        tvStatusbarAlpha = (TextView) findViewById(R.id.tv_statusbar_alpha);
        tvNavbarAlpha = (TextView) findViewById(R.id.tv_navbar_alpha);
        tvTip = (TextView) findViewById(R.id.tv_tip);
        llNavbarColor = (LinearLayout) findViewById(R.id.ll_navbar_color);
        llNavbarAlpha = (LinearLayout) findViewById(R.id.ll_navbar_alpha);

        viewStatusbarColor.setBackgroundColor(colorRes[mColorResPosition]);
        mLutoSystemBar = LutoSystemBar.Builder(this)
                .setStatusBarType(mStatusBarType)
                .setSystemBarColorRes(colorRes[mColorResPosition])
                .setSystemBarAlpha(seekbarStatusbar.getProgress());
        if (mIsNavBarTrans) {
            llNavbarColor.setVisibility(View.VISIBLE);
            llNavbarAlpha.setVisibility(View.VISIBLE);
            tvTip.setVisibility(View.VISIBLE);
            mLutoSystemBar.translucentNavigationBar();
        }
        mLutoSystemBar.build();
    }

    @Override
    protected void initListener() {
        super.initListener();
        btnStatusbarChangecolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mColorResPosition = (int) (Math.random() * colorRes.length - 1);
                viewStatusbarColor.setBackgroundColor(colorRes[mColorResPosition]);
                mLutoSystemBar.setStatusBarColor(colorRes[mColorResPosition]).build();
            }
        });

        btnNavbarChangecolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mColorResPosition = (int) (Math.random() * colorRes.length - 1);
                viewNavbarColor.setBackgroundColor(colorRes[mColorResPosition]);
                mLutoSystemBar.setNavigationBarColor(colorRes[mColorResPosition]).build();
            }
        });

        seekbarStatusbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvStatusbarAlpha.setText(String.valueOf(progress));
                mLutoSystemBar.setStatusBarAlpha(progress).build();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekbarNavbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvNavbarAlpha.setText(String.valueOf(progress));
                mLutoSystemBar.setNavigationBarAlpha(progress).build();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @Override
    public int getLayoutResId() {
        return R.layout.act_normal_result;
    }

}
