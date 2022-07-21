package com.atguigu.service;

import com.atguigu.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * @author QiuShiyuan
 * @date 2022/5/8 - 16:18
 */
public interface OrderSettingService {
    void addBatch(List<OrderSetting> listData);

    List<Map> getOrderSettingByMonth(String date);
}
