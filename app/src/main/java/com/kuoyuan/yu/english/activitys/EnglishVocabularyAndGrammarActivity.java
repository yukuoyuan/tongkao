package com.kuoyuan.yu.english.activitys;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.kuoyuan.yu.R;
import com.kuoyuan.yu.common.activitys.BaseActivity;
import com.kuoyuan.yu.compute.adapters.ComputerSingleCheckPagerAdapter;
import com.kuoyuan.yu.compute.beans.ComputerSingleCheckListBean;
import com.kuoyuan.yu.compute.views.SingCheckBottomDialog;
import com.kuoyuan.yu.english.presenters.EnglishVocabularyAndGrammarPresenter;
import com.kuoyuan.yu.english.presenters.IEnglishVocabularyAndGrammarView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnPageChange;

/**
 * Created on 2020/7/15
 * 这是一个英语词汇和语法单选的界面
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
public class EnglishVocabularyAndGrammarActivity extends BaseActivity<EnglishVocabularyAndGrammarPresenter> implements IEnglishVocabularyAndGrammarView, SingCheckBottomDialog.OnSingleCheckBottomDialogListener {

    @BindView(R.id.vp_english_vocabulary_grammar)
    ViewPager vpEnglishVocabularyGrammar;
    @BindView(R.id.tv_english_vocabulary_grammar_more)
    TextView tvEnglishVocabularyGrammarMore;
    /**
     * 总数量
     */
    private int mTotalCount;
    /**
     * 数据源
     */
    private List<ComputerSingleCheckListBean.ComputerSingleDataBean> listData;

    @Override
    protected EnglishVocabularyAndGrammarPresenter createPresenter() {
        return new EnglishVocabularyAndGrammarPresenter(this, this);
    }

    @Override
    protected void initData(Bundle extras) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("词汇和语法");
        getPresenter().getListData();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_english_vocabulary_grammar;
    }

    @OnClick({R.id.tv_english_vocabulary_grammar_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_english_vocabulary_grammar_more:
                showSingleCheckBottomDialog();
                break;
            default:
                break;
        }
    }

    @OnPageChange(R.id.vp_english_vocabulary_grammar)
    public void onPageSelected(int position) {
        showBottomCountTip(position);
    }

    @Override
    public void initData2View(List<ComputerSingleCheckListBean.ComputerSingleDataBean> listData) {
        mTotalCount = listData.size();
        this.listData = listData;
        showBottomCountTip(0);
        ComputerSingleCheckPagerAdapter computerSingleCheckPagerAdapter = new ComputerSingleCheckPagerAdapter(getSupportFragmentManager());
        vpEnglishVocabularyGrammar.setAdapter(computerSingleCheckPagerAdapter);
        computerSingleCheckPagerAdapter.setData(listData);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showSingleCheckBottomDialog() {
        SingCheckBottomDialog checkSingCheckBottomDialog = new SingCheckBottomDialog(this);
        checkSingCheckBottomDialog.setData(listData);
        checkSingCheckBottomDialog.show();
        checkSingCheckBottomDialog.setOnSingleCheckBottomDialogListener(this);
    }

    @Override
    public void showBottomCountTip(int position) {
        /*
         * 设置显示
         */
        tvEnglishVocabularyGrammarMore.setText(String.format("%d/%d", position, mTotalCount));
    }

    @Override
    public void onSingleCheckBottomDialogCheck(int checkPosition) {
        vpEnglishVocabularyGrammar.setCurrentItem(checkPosition);
    }
}
