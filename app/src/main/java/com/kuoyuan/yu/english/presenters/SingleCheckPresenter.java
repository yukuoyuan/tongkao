package com.kuoyuan.yu.english.presenters;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.kuoyuan.yu.common.config.Constants;
import com.kuoyuan.yu.common.db.DbSingleBean;
import com.kuoyuan.yu.common.presenters.BasePresenter;
import com.kuoyuan.yu.common.utils.AssetsUtils;
import com.kuoyuan.yu.common.utils.DbHelper;
import com.kuoyuan.yu.common.utils.ListUtils;
import com.kuoyuan.yu.compute.beans.SingleCheckListBean;

import java.util.List;

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
        /*
         * 查看本地是否有数据,如果有就用db的,没有的话,用assets的数据
         */
        List<DbSingleBean> singleBeanList = DbHelper.getInstance().getSingleBeanList(pageTypeValue, false, false);
        if (ListUtils.isEmpty(singleBeanList)) {
            getAssetsData(pageTypeValue);
        } else {
            /*
             * 有数据,就直接填充界面
             */
            if (getBaseView()!=null){
                getBaseView().initData2View(singleBeanList);
            }
        }
    }

    /**
     * 获取数据
     *
     * @param pageTypeValue 页面类型
     */
    public void getAssetsData(int pageTypeValue) {
        String fileName = "";
        if (pageTypeValue == Constants.PAGE_TYPE_ENGLISH_B_SINGLE) {
            fileName = "englishsinglecheck.json";
        } else if (pageTypeValue == Constants.PAGE_TYPE_COMPUTER_SINGLE) {
            fileName = "computersinglecheck.json";
        } else if (pageTypeValue == Constants.PAGE_TYPE_ENGLISH_VOCABULARY_AND_GRAMMAR_SINGLE) {
            fileName = "englishvocabularyandgrammar.json";
        } else if (pageTypeValue == Constants.PAGE_TYPE_ENGLISH_ACADEMIC_DEGREE_SINGLE) {
            fileName = "englishacademicdegreesinglecheck.json";
        }
        String listData = AssetsUtils.getInstance().getJson(fileName, context);
        SingleCheckListBean singleCheckBean = new Gson().fromJson(listData, SingleCheckListBean.class);
        if (getBaseView() != null && singleCheckBean != null && !ListUtils.isEmpty(singleCheckBean.data)) {
            for (int i = 0; i < singleCheckBean.data.size(); i++) {
                SingleCheckListBean.SingleDataBean dataBean = singleCheckBean.data.get(i);
                DbSingleBean dbSingleBean = new DbSingleBean.Builder()
                        .id(pageTypeValue + i)
                        .title(dataBean.title)
                        .tip(dataBean.tip)
                        .answers(new Gson().toJson(dataBean.checkData))
                        .type(pageTypeValue)
                        .build();
                DbHelper.getInstance().addSingleBean(dbSingleBean);
            }
            getListData(pageTypeValue);
        }
    }
}
