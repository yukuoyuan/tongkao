package com.kuoyuan.yu.compute.activitys;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.kuoyuan.yu.R;
import com.kuoyuan.yu.common.activitys.BaseActivity;
import com.kuoyuan.yu.compute.adapters.ComputerSingleCheckPagerAdapter;
import com.kuoyuan.yu.compute.beans.ComputerSingleCheckListBean;
import com.kuoyuan.yu.compute.presenters.ComputerSingleCheckPresenter;
import com.kuoyuan.yu.compute.presenters.IComputerSingleCheckView;

import java.util.List;

import butterknife.BindView;

/**
 * Created on 2020/6/15
 * 这是计算机单选题的界面
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
public class ComputerSingleCheckActivity extends BaseActivity<ComputerSingleCheckPresenter> implements IComputerSingleCheckView {
    @BindView(R.id.vp_computer_single_check)
    ViewPager vpComputerSingleCheck;

    @Override
    protected ComputerSingleCheckPresenter createPresenter() {
        return new ComputerSingleCheckPresenter(this, this);
    }

    @Override
    protected void initData(Bundle extras) {
        getPresenter().getListData();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_computer_single_check;
    }

    @Override
    public void initData2View(List<ComputerSingleCheckListBean.ComputerSingleDataBean> listData) {
        ComputerSingleCheckPagerAdapter computerSingleCheckPagerAdapter = new ComputerSingleCheckPagerAdapter(getSupportFragmentManager());
        vpComputerSingleCheck.setAdapter(computerSingleCheckPagerAdapter);
        computerSingleCheckPagerAdapter.setData(listData);
    }

}
