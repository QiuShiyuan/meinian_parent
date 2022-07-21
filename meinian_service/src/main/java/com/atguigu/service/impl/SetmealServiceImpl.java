package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.constant.RedisConstant;
import com.atguigu.dao.SetmealDao;
import com.atguigu.entity.PageResult;
import com.atguigu.pojo.Setmeal;
import com.atguigu.service.SetmealService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

/**
 * @author QiuShiyuan
 * @date 2022/5/2 - 15:38
 */
@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;

    @Autowired
    JedisPool jedisPool;


    @Override
    public void add(Integer[] travelgroupIds, Setmeal setmeal) {
        //1.保存套餐
        setmealDao.add(setmeal);//主键回显
        //2.保存关联数据
        Integer setmealId = setmeal.getId();
        setSetmealAndTravelGroup(travelgroupIds,setmealId);
        //*************补充代码 解决垃圾图片的问题************************
        String pic = setmeal.getImg();
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,pic);
        //**********************************************
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page page = setmealDao.findPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    private void setSetmealAndTravelGroup(Integer[] travelgroupIds, Integer setmealId) {
        if(travelgroupIds!=null && travelgroupIds.length>0){
            for (Integer travelgroupId : travelgroupIds) {
                Map<String,Integer> map = new HashMap<>();
                map.put("travelgroupId",travelgroupId);
                map.put("setmealId",setmealId);
                setmealDao.addSetmealAndTravelGroup(map);
            }
        }
    }
}
