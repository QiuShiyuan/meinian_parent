package com.atguigu.dao;

import com.atguigu.pojo.Setmeal;
import com.github.pagehelper.Page;

import java.util.Map;

/**
 * @author QiuShiyuan
 * @date 2022/5/2 - 15:39
 */
public interface SetmealDao {
    void addSetmealAndTravelGroup(Map<String, Integer> map);

    void add(Setmeal setmeal);

    Page findPage(String queryString);
}
