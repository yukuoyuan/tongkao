package com.kuoyuan.yu.common.views;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.kuoyuan.yu.R;

import butterknife.ButterKnife;

/**
 * @author yukuoyuan
 * @date 2020/6/21
 */
public abstract class BaseBottomDialog extends Dialog {
    protected Context context;

    public BaseBottomDialog(@NonNull Context context) {
        super(context,R.style.Dialog_Common_Style);
        init(context);
    }

    /**
     * 初始化
     *
     * @param context 上下文
     */
    private void init(Context context) {
        this.context = context;
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.Dialog_Bottom_Animation);
        /*
         * 设置自定义布局
         */
        setContentView(getContentLayoutId());
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        ButterKnife.bind(this);
        /*
         *初始化数据
         */
        initData();
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
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 获取布局资源id
     *
     * @return 布局资源id
     */
    protected abstract int getContentLayoutId();


}
