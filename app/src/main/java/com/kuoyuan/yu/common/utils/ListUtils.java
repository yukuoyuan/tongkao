package com.kuoyuan.yu.common.utils;

import java.util.List;

/**
 * 这是一个list 工具类
 *
 * @author yukuoyuan
 */
public class ListUtils {

    /**
     * ；列表是否为空
     *
     * @param list 列表
     * @return 是否为空
     */
    public static <T> boolean isEmpty(List<T> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }
}
