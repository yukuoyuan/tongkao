package com.kuoyuan.yu.common.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.kuoyuan.yu.common.presenters.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created on 2020/6/15
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {
    public View mContextView;
    private Unbinder unbinder;
    /**
     * 逻辑层
     */
    private P presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mContextView = inflater.inflate(getLayoutResId(), container, false);
        unbinder = ButterKnife.bind(this, this.mContextView);
        return this.mContextView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
        unBindPresenter();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*
         * 绑定逻辑层
         */
        bindPresenter();
        /*
         * 初始化数据
         */
        initData(getArguments());
        /*
         * 初始化监听
         */
        initListener();
    }

    /**
     * 初始化监听
     */
    private void initListener() {

    }

    /**
     * 绑定逻辑层
     */
    public void bindPresenter() {
        if (null == presenter) {
            presenter = createPresenter();
        }
    }

    /**
     * 解绑逻辑层
     */
    public void unBindPresenter() {
        if (null != presenter) {
            presenter.unBindPresenter();
        }
    }

    /**
     * 创建一个逻辑层
     *
     * @return 逻辑层
     */
    protected P createPresenter() {
        return null;
    }

    /**
     * 获取一个逻辑层
     *
     * @return 逻辑层
     */
    protected P getPresenter() {
        return presenter;
    }

    /**
     * 初始化数据
     *
     * @param extras 传递的数据
     */
    protected abstract void initData(Bundle extras);

    /**
     * 获取布局id
     *
     * @return 布局id
     */
    protected abstract int getLayoutResId();

    public void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = this.getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.show(fragment);
        transaction.commit();
    }

    public void hideFragment(Fragment fragment) {
        FragmentManager fragmentManager = this.getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(fragment);
        transaction.commit();
    }

    public void addFragment(int containerViewId, Fragment fragment, @Nullable String tag) {
        FragmentManager fragmentManager = this.getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(containerViewId, fragment, tag);
        transaction.commit();
    }

    public void replaceFragment(int containerViewId, Fragment fragment) {
        FragmentManager fragmentManager = this.getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerViewId, fragment);
        transaction.commit();
    }

    public void intent2Activity(Class clazz) {
        Intent intent = new Intent(this.getContext(), clazz);
        this.getContext().startActivity(intent);
    }

    public void intent2Activity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this.getContext(), clazz);
        intent.putExtras(bundle);
        this.getContext().startActivity(intent);
    }

    public void intent2Activity(Class clazz, int requestCode) {
        Intent intent = new Intent(this.getContext(), clazz);
        this.startActivityForResult(intent, requestCode);
    }

    public void intent2Activity(Class clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this.getContext(), clazz);
        intent.putExtras(bundle);
        this.startActivityForResult(intent, requestCode);
    }
}
