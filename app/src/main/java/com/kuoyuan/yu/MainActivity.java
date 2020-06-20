package com.kuoyuan.yu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kuoyuan.yu.common.activitys.BaseActivity;

/**
 * 这是一个首页界面
 *
 * @author yukuoyuan
 */
public class MainActivity extends BaseActivity {
    @Override
    protected void initData(Bundle extras) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }
}