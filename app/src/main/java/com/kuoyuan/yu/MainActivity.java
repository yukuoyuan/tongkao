package com.kuoyuan.yu;

import android.os.Bundle;
import android.view.View;

import com.kuoyuan.yu.common.activitys.BaseActivity;
import com.kuoyuan.yu.compute.activitys.ComputerSingleCheckActivity;
import com.kuoyuan.yu.english.activitys.EnglishSingleCheckActivity;

import butterknife.OnClick;

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


    @OnClick({R.id.bt_computer_single_check, R.id.bt_english_single_check})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_computer_single_check:
                /*
                 * 跳转计算机单选界面
                 */
                intent2Activity(ComputerSingleCheckActivity.class);
                break;
            case R.id.bt_english_single_check:
                /*
                 * 跳转英语b单选题界面
                 */
                intent2Activity(EnglishSingleCheckActivity.class);
                break;
            default:
                break;
        }
    }
}