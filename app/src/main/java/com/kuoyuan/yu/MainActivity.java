package com.kuoyuan.yu;

import android.os.Bundle;
import android.view.View;

import com.kuoyuan.yu.common.activitys.BaseActivity;
import com.kuoyuan.yu.common.config.Constants;
import com.kuoyuan.yu.english.activitys.SingleCheckActivity;

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


    @OnClick({R.id.bt_computer_single_check, R.id.bt_english_single_check,
            R.id.bt_english_vocabulary_and_grammar, R.id.bt_english_reading_comprehension,
            R.id.bt_english_academic_degree})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.bt_computer_single_check:
                /*
                 * 跳转计算机单选界面
                 */
                bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_COMPUTER_SINGLE);
                intent2Activity(SingleCheckActivity.class, bundle);
                break;
            case R.id.bt_english_single_check:
                /*
                 * 跳转英语b单选题界面
                 */
                bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_ENGLISH_B_SINGLE);
                intent2Activity(SingleCheckActivity.class, bundle);
                break;
            case R.id.bt_english_vocabulary_and_grammar:
                /*
                 * 词汇和语法
                 */
                bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_ENGLISH_VOCABULARY_AND_GRAMMAR_SINGLE);
                intent2Activity(SingleCheckActivity.class, bundle);
                break;
            case R.id.bt_english_reading_comprehension:
                /*
                 * 阅读理解
                 */
                break;
            case R.id.bt_english_academic_degree:
                /*
                 * 学位英语词汇练习
                 */
                bundle.putInt(Constants.PAGE_TYPE_KEY, Constants.PAGE_TYPE_ENGLISH_ACADEMIC_DEGREE_SINGLE);
                intent2Activity(SingleCheckActivity.class, bundle);
                break;
            default:
                break;
        }
    }

}