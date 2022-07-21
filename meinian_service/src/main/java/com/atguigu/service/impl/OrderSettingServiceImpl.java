package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.OrderSettingDao;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author QiuShiyuan
 * @date 2022/5/8 - 16:18
 */
@Service(interfaceClass = OrderSettingService.class )
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    OrderSettingDao orderSettingDao;

    @Override
    public void addBatch(List<OrderSetting> listData) {
        for (OrderSetting orderSetting : listData) {
            //如果日期对应的设置存在，就修改，否则就添加
            int count = orderSettingDao.findOrderSettingByOrderDate(orderSetting.getOrderDate());
            if(count>0){//数据存在
                orderSettingDao.edit(orderSetting);
            }else{
                orderSettingDao.add(orderSetting);
            }

        }
    }

    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        String startDate = date+"-1";
        String endDate = date+"-31";
        Map param = new HashMap();
        param.put("startDate",startDate);
        param.put("endDate",endDate);
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(param);
        List<Map> listData = new ArrayList<>();
        for (OrderSetting orderSetting : list) {
            Map map = new HashMap();
            map.put("date",orderSetting.getOrderDate());
            map.put("number",orderSetting.getNumber());
            map.put("reservations",orderSetting.getReservations());
            listData.add(map);
        }

        return listData;
    }
}
