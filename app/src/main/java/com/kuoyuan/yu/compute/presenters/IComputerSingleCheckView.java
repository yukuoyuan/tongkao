package com.kuoyuan.yu.compute.presenters;

import com.kuoyuan.yu.common.presenters.IBaseView;
import com.kuoyuan.yu.compute.beans.SingleCheckListBean;

import java.util.List;

/**
 * @author yukuoyuan
 * @date 2020/6/20
 */
public interface IComputerSingleCheckView extends IBaseView {

    /**
     * 初始化数据到界面
     *
     * @param listData 数据
     */
    void initData2View(List<SingleCheckListBean.SingleDataBean> listData);

    /**
     * 展示底部的弹窗
     */
    void showSingleCheckBottomDialog();

    /**
     * 展示底部的提示
     *
     * @param position 选中的索引
     */
    void showBottomCountTip(int position);
}
