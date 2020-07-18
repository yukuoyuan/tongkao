package com.kuoyuan.yu.english.presenters;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.kuoyuan.yu.common.config.Constants;
import com.kuoyuan.yu.common.presenters.BasePresenter;
import com.kuoyuan.yu.common.utils.AssetsUtils;
import com.kuoyuan.yu.common.utils.ListUtils;
import com.kuoyuan.yu.compute.beans.SingleCheckListBean;

/**
 * Created on 2020/7/14
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
public class SingleCheckPresenter extends BasePresenter<ISingleCheckView> {
    public SingleCheckPresenter(@NonNull Context context, ISingleCheckView baseView) {
        super(context, baseView);
    }

    /**
     * 获取数据
     *
     * @param pageTypeValue 页面类型
     */
    public void getListData(int pageTypeValue) {
        String fileName = "";
        if (pageTypeValue == Constants.PAGE_TYPE_ENGLISH_B_SINGLE) {
            fileName = "englishsinglecheck.json";
        } else if (pageTypeValue == Constants.PAGE_TYPE_COMPUTER_SINGLE) {
            fileName = "computersinglecheck.json";
        } else if (pageTypeValue == Constants.PAGE_TYPE_ENGLISH_VOCABULARY_AND_GRAMMAR_SINGLE) {
            fileName = "englishvocabularyandgrammar.json";
        }
        String listData = AssetsUtils.getInstance().getJson(fileName, context);
        SingleCheckListBean singleCheckBean = new Gson().fromJson(listData, SingleCheckListBean.class);
        if (getBaseView() != null && singleCheckBean != null && !ListUtils.isEmpty(singleCheckBean.data)) {
            getBaseView().initData2View(singleCheckBean.data);
        }
    }
}
