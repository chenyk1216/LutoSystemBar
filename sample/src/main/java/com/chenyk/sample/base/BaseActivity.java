package com.chenyk.sample.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by chenyk on 2017/9/13.
 * 页面简单基类
 */

public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getExtras() != null) {
            getIntenData(getIntent());
        }
        setContentView(getLayoutResId());
        this.initView();
        this.initListener();
    }

    /**
     * intent数据获取操作
     *
     * @param intent
     */
    protected void getIntenData(Intent intent) {
        //空操作
    }

    /**
     * 视图初始化
     */
    protected void initView() {
        //空操作
    }

    /**
     * 监听器初始化
     */
    protected void initListener() {
        //空操作
    }

    /**
     * 获取布局资源id
     *
     * @return
     */
    public abstract int getLayoutResId();
}
