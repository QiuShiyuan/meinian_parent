package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.pojo.Setmeal;

/**
 * @author QiuShiyuan
 * @date 2022/5/2 - 15:37
 */
public interface SetmealService {


    void add(Integer[] travelgroupIds, Setmeal setmeal);

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);
}
