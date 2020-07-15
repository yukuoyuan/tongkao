package com.kuoyuan.yu.english.presenters;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.kuoyuan.yu.common.presenters.BasePresenter;
import com.kuoyuan.yu.common.utils.AssetsUtils;
import com.kuoyuan.yu.common.utils.ListUtils;
import com.kuoyuan.yu.compute.beans.ComputerSingleCheckListBean;

/**
 * Created on 2020/7/15
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
public class EnglishVocabularyAndGrammarPresenter extends BasePresenter<IEnglishVocabularyAndGrammarView> {

    public EnglishVocabularyAndGrammarPresenter(@NonNull Context context, IEnglishVocabularyAndGrammarView baseView) {
        super(context, baseView);
    }
    /**
     * 获取数据
     */
    public void getListData() {
        String listData = AssetsUtils.getInstance().getJson("englishvocabularyandgrammar.json", context);
        ComputerSingleCheckListBean computerSingleCheckBean = new Gson().fromJson(listData, ComputerSingleCheckListBean.class);
        if (getBaseView() != null && computerSingleCheckBean != null && !ListUtils.isEmpty(computerSingleCheckBean.data)) {
            getBaseView().initData2View(computerSingleCheckBean.data);
        }
    }
}
