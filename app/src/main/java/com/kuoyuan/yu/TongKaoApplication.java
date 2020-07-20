package com.kuoyuan.yu;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.facebook.stetho.Stetho;

/**
 * Created on 2020/7/19
 *
 * @author yukuoyuan
 * @link github https://github.com/yukuoyuan
 */
public class TongKaoApplication extends Application {
    private static Handler mainHandler;
    private static Context instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mainHandler = new Handler();
        Stetho.initializeWithDefaults(this);
    }

    public static Context getInstance() {
        return instance;
    }

    public static Handler getMainHandler() {
        return mainHandler;
    }
}
