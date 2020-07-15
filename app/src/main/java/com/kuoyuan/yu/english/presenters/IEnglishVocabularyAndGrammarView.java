package com.kuoyuan.yu.english.presenters;

import com.kuoyuan.yu.common.presenters.IBaseView;
import com.kuoyuan.yu.compute.beans.ComputerSingleCheckListBean;

import java.util.List;

/**
 * Created on 2020/7/15
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
public interface IEnglishVocabularyAndGrammarView extends IBaseView {
    /**
     * 初始化数据到界面
     *
     * @param data 数据
     */
    void initData2View(List<ComputerSingleCheckListBean.ComputerSingleDataBean> data);

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
