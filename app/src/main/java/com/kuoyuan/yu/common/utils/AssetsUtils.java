package com.kuoyuan.yu.common.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author yukuoyuan
 * @date 2020/6/20
 */
public class AssetsUtils {
    private static volatile AssetsUtils singleton = null;

    private AssetsUtils() {
    }

    public static AssetsUtils getInstance() {
        if (singleton == null) {
            synchronized (AssetsUtils.class) {
                if (singleton == null) {
                    singleton = new AssetsUtils();
                }
            }
        }
        return singleton;
    }

    /**
     * 获取json 文件内容
     *
     * @param fileName 文件路径
     * @param context  上下文
     * @return
     */
    public String getJson(String fileName, Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
