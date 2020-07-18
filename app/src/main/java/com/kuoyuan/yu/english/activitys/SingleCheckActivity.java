package com.kuoyuan.yu.english.activitys;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.kuoyuan.yu.R;
import com.kuoyuan.yu.common.activitys.BaseActivity;
import com.kuoyuan.yu.common.config.Constants;
import com.kuoyuan.yu.compute.adapters.SingleCheckPagerAdapter;
import com.kuoyuan.yu.compute.beans.SingleCheckListBean;
import com.kuoyuan.yu.compute.views.SingCheckBottomDialog;
import com.kuoyuan.yu.english.presenters.SingleCheckPresenter;
import com.kuoyuan.yu.english.presenters.IEnglishSingleCheckView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnPageChange;

/**
 * Created on 2020/6/15
 * 这是一个英语单选的界面
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
public class SingleCheckActivity extends BaseActivity<SingleCheckPresenter> implements IEnglishSingleCheckView, SingCheckBottomDialog.OnSingleCheckBottomDialogListener {


    @BindView(R.id.vp_english_single_check)
    ViewPager vpEnglishSingleCheck;
    @BindView(R.id.tv_english_single_check_more)
    TextView tvEnglishSingleCheckMore;
    /**
     * 总数量
     */
    private int mTotalCount;
    /**
     * 数据源
     */
    private List<SingleCheckListBean.SingleDataBean> listData;
    /**
     * 页面类型数据
     */
    private int mPageTypeValue;

    @Override
    protected void initData(Bundle extras) {
        if (extras != null) {
            mPageTypeValue = extras.getInt(Constants.PAGE_TYPE_KEY);
        }
        getSupportActionBar().setTitle("单选题");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getPresenter().getListData(mPageTypeValue);
    }

    @Override
    protected SingleCheckPresenter createPresenter() {
        return new SingleCheckPresenter(this, this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_english_single_check;
    }

    @OnClick({R.id.tv_english_single_check_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_english_single_check_more:
                showSingleCheckBottomDialog();
                break;
            default:
                break;
        }
    }

    @Override
    public void initData2View(List<SingleCheckListBean.SingleDataBean> listData) {
        mTotalCount = listData.size();
        this.listData = listData;
        showBottomCountTip(0);
        SingleCheckPagerAdapter singleCheckPagerAdapter = new SingleCheckPagerAdapter(getSupportFragmentManager());
        vpEnglishSingleCheck.setAdapter(singleCheckPagerAdapter);
        singleCheckPagerAdapter.setData(listData);
    }

    @Override
    public void showSingleCheckBottomDialog() {
        SingCheckBottomDialog checkSingCheckBottomDialog = new SingCheckBottomDialog(this);
        checkSingCheckBottomDialog.setData(listData);
        checkSingCheckBottomDialog.show();
        checkSingCheckBottomDialog.setOnSingleCheckBottomDialogListener(this);
    }

    @OnPageChange(R.id.vp_english_single_check)
    public void onPageSelected(int position) {
        showBottomCountTip(position);
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
    public void showBottomCountTip(int position) {
        /*
         * 设置显示
         */
        tvEnglishSingleCheckMore.setText(String.format("%d/%d", position, mTotalCount));
    }

    @Override
    public void onSingleCheckBottomDialogCheck(int checkPosition) {
        vpEnglishSingleCheck.setCurrentItem(checkPosition);
    }
}
