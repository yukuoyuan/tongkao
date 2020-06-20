package com.kuoyuan.yu.compute.presenters;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.kuoyuan.yu.common.presenters.BasePresenter;
import com.kuoyuan.yu.common.utils.AssetsUtils;
import com.kuoyuan.yu.common.utils.ListUtils;
import com.kuoyuan.yu.compute.beans.ComputerSingleCheckListBean;

/**
 * @author yukuoyuan
 * @date 2020/6/20
 */
public class ComputerSingleCheckPresenter extends BasePresenter<IComputerSingleCheckView> {

    public ComputerSingleCheckPresenter(@NonNull Context context, IComputerSingleCheckView baseView) {
        super(context, baseView);
    }

    /**
     * 获取列表数据
     */
    public void getListData() {
        String listData = AssetsUtils.getInstance().getJson("assets/computersinglecheck.json", context);
        ComputerSingleCheckListBean computerSingleCheckBean = new Gson().fromJson(listData, ComputerSingleCheckListBean.class);
        if (getBaseView() != null && computerSingleCheckBean != null && !ListUtils.isEmpty(computerSingleCheckBean.data)) {
            getBaseView().initData2View(computerSingleCheckBean.data);
        }
    }
}
