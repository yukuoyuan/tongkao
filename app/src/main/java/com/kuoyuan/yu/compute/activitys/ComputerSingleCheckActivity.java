package com.kuoyuan.yu.compute.activitys;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.kuoyuan.yu.R;
import com.kuoyuan.yu.common.activitys.BaseActivity;
import com.kuoyuan.yu.compute.adapters.ComputerSingleCheckPagerAdapter;
import com.kuoyuan.yu.compute.beans.ComputerSingleCheckListBean;
import com.kuoyuan.yu.compute.presenters.ComputerSingleCheckPresenter;
import com.kuoyuan.yu.compute.presenters.IComputerSingleCheckView;
import com.kuoyuan.yu.compute.views.SingCheckBottomDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnPageChange;

/**
 * Created on 2020/6/15
 * 这是计算机单选题的界面
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
public class ComputerSingleCheckActivity extends BaseActivity<ComputerSingleCheckPresenter> implements IComputerSingleCheckView, SingCheckBottomDialog.OnSingleCheckBottomDialogListener {
    @BindView(R.id.vp_computer_single_check)
    ViewPager vpComputerSingleCheck;
    @BindView(R.id.tv_computer_single_check_more)
    TextView tvComputerSingleCheckMore;
    /**
     * 总数量
     */
    private int mTotalCount;
    /**
     * 数据源
     */
    private List<ComputerSingleCheckListBean.ComputerSingleDataBean> listData;

    @Override
    protected ComputerSingleCheckPresenter createPresenter() {
        return new ComputerSingleCheckPresenter(this, this);
    }

    @Override
    protected void initData(Bundle extras) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("单选题");
        getPresenter().getListData();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_computer_single_check;
    }

    @Override
    public void initData2View(List<ComputerSingleCheckListBean.ComputerSingleDataBean> listData) {
        mTotalCount = listData.size();
        this.listData = listData;
        showBottomCountTip(0);
        ComputerSingleCheckPagerAdapter computerSingleCheckPagerAdapter = new ComputerSingleCheckPagerAdapter(getSupportFragmentManager());
        vpComputerSingleCheck.setAdapter(computerSingleCheckPagerAdapter);
        computerSingleCheckPagerAdapter.setData(listData);
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
        tvComputerSingleCheckMore.setText(String.format("%d/%d", position, mTotalCount));
    }

    @OnPageChange(R.id.vp_computer_single_check)
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

    @OnClick({R.id.tv_computer_single_check_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_computer_single_check_more:
                showSingleCheckBottomDialog();
                break;
            default:
                break;
        }
    }

    @Override
    public void onSingleCheckBottomDialogCheck(int checkPosition) {
        vpComputerSingleCheck.setCurrentItem(checkPosition);
    }
}
