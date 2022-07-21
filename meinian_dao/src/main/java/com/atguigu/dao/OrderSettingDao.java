package com.atguigu.dao;

import com.atguigu.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author QiuShiyuan
 * @date 2022/5/8 - 16:19
 */
public interface OrderSettingDao {
    void add(OrderSetting orderSetting);

    int findOrderSettingByOrderDate(Date orderDate);

    void edit(OrderSetting orderSetting);

    List<OrderSetting> getOrderSettingByMonth(Map param);
}
