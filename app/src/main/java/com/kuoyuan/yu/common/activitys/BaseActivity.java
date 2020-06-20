package com.kuoyuan.yu.common.activitys;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.kuoyuan.yu.common.presenters.BasePresenter;

import butterknife.ButterKnife;

/**
 * Created on 2020/6/15
 * 这是一个基础的界面
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    /**
     * 逻辑层
     */
    private P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
         * 设置布局
         */
        setContentView(getLayoutResId());
        /*
         * 绑定注解
         */
        ButterKnife.bind(this);
        /*
         * 绑定逻辑层
         */
        bindPresenter();
        /*
         * 初始化数据
         */
        initData(getIntent().getExtras());
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

    public void intent2Activity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        this.startActivity(intent);
    }

    public void intent2Activity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        this.startActivity(intent);
    }

    public void intent2Activity(Class clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        this.startActivityForResult(intent, requestCode);
    }

    public void intent2Activity(Class clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        this.startActivityForResult(intent, requestCode);
    }

    public void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.show(fragment);
        transaction.commit();
    }

    public void hideFragment(Fragment fragment) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(fragment);
        transaction.commit();
    }

    public void addFragment(int containerViewId, Fragment fragment, @Nullable String tag) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(containerViewId, fragment, tag);
        transaction.commit();
    }

    public void replaceFragment(int containerViewId, Fragment fragment) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerViewId, fragment);
        transaction.commit();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBindPresenter();
    }
}
