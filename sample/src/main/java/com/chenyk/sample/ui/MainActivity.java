package com.chenyk.sample.ui;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.chenyk.lutobarlib.LutoSystemBar;
import com.chenyk.lutobarlib.enums.StatusBarType;
import com.chenyk.sample.R;
import com.chenyk.sample.base.BaseActivity;

public class MainActivity extends BaseActivity {
    //View
    RadioGroup rgRootType, rgStatusbarType, rgNavbarTranslucent, rgFullIvNavbarTranslucent;
    Button btnToViewResult;
    TextView tvPartIvStatusbarAlpha, tvFullIvStatusbarAlpha;
    SeekBar seekbarPartIvStatusbar, seekbarFullIvStatusbar;

    private String mCurrentRootType;
    private boolean mFullIvNavBarTrans;
    private boolean mNormalNavBarTrans;
    private StatusBarType mStatusBarType;

    @Override
    protected void initView() {
        super.initView();
        rgRootType = (RadioGroup) findViewById(R.id.rg_root_type);
        rgStatusbarType = (RadioGroup) findViewById(R.id.rg_statusbar_type);
        rgNavbarTranslucent = (RadioGroup) findViewById(R.id.rg_navbar_translucent);
        rgFullIvNavbarTranslucent = (RadioGroup) findViewById(R.id.rg_full_iv_navbar_translucent);
        tvPartIvStatusbarAlpha = (TextView) findViewById(R.id.tv_part_imageview_statusbar_alpha);
        tvFullIvStatusbarAlpha = (TextView) findViewById(R.id.tv_full_imageview_statusbar_alpha);
        seekbarPartIvStatusbar = (SeekBar) findViewById(R.id.seekbar_part_imageview_statusbar);
        seekbarFullIvStatusbar = (SeekBar) findViewById(R.id.seekbar_full_imageview_statusbar);
        btnToViewResult = (Button) findViewById(R.id.btn_to_view_result);
    }

    @Override
    protected void initListener() {
        super.initListener();
        rgRootType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(radioButtonId);
                mCurrentRootType = rb.getTag().toString();
            }
        });

        rgStatusbarType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(radioButtonId);
                mStatusBarType = "1".equals(rb.getTag().toString()) ? StatusBarType.PURECOLOR : StatusBarType.GRADIENT;
            }
        });

        rgNavbarTranslucent.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(radioButtonId);
                mNormalNavBarTrans = "1".equals(rb.getTag().toString());
            }
        });

        rgFullIvNavbarTranslucent.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(radioButtonId);
                mFullIvNavBarTrans = "1".equals(rb.getTag().toString());
            }
        });

        btnToViewResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSetForNormal()) {
                    NormalResultActivity.startCurrentActivity(MainActivity.this, mNormalNavBarTrans, mStatusBarType);
                } else if (isSetForPartImageView()) {
                    PartImageViewActivity.startCurrentActivity(MainActivity.this, Integer.valueOf(tvPartIvStatusbarAlpha.getText().toString()));
                } else if (isSetForFullImageView()) {
                    FullImageViewActivity.startCurrentActivity(MainActivity.this, mFullIvNavBarTrans, Integer.valueOf(tvFullIvStatusbarAlpha.getText().toString()));
                }
            }
        });

        seekbarPartIvStatusbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvPartIvStatusbarAlpha.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekbarFullIvStatusbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvFullIvStatusbarAlpha.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //默认选择
        rgRootType.check(R.id.rb_root_type_def);
        rgStatusbarType.check(R.id.rb_statusbar_type_def);
        rgNavbarTranslucent.check(R.id.rb_nav_trans_def);
        rgFullIvNavbarTranslucent.check(R.id.rb_full_iv_nav_trans_def);
    }


    private boolean isSetForNormal() {
        return "1".equals(mCurrentRootType);
    }

    private boolean isSetForPartImageView() {
        return "2".equals(mCurrentRootType);
    }

    private boolean isSetForFullImageView() {
        return "3".equals(mCurrentRootType);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.act_main;
    }
}
