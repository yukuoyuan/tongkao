package com.kuoyuan.yu.common.presenters;

import android.content.Context;

import androidx.annotation.NonNull;

/**
 * Created on 2020/6/15
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
public abstract class BasePresenter<T extends IBaseView> {
    /**
     * 上下文
     */
    public Context context;

    private T baseView;
    /**
     * 标签名字
     */
    public String tagName;
    /**
     * 登录失效
     */
    private final int LOGIN_INVALID = 1010;

    public BasePresenter(@NonNull Context context, T baseView, String tagName) {
        this.context = context;
        this.baseView = baseView;
        this.tagName = tagName;
    }

    /**
     * 解绑presenter
     */
    public void unBindPresenter() {
        context = null;
        baseView = null;
    }

    public T getBaseView() {
        return baseView;
    }
}
