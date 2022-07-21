package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.service.OrderSettingService;
import com.atguigu.util.POIUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author QiuShiyuan
 * @date 2022/5/8 - 16:15
 */

@RestController
@RequestMapping("/orderSetting")
public class OrderSettingController {

    @Reference
    OrderSettingService orderSettingService;

    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date){
        List<Map> list = orderSettingService.getOrderSettingByMonth(date);
        return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,list);
    }

    @RequestMapping("/upload")
    public Result upload(MultipartFile excelFile){
        try {
            List<String[]> list = POIUtils.readExcel(excelFile);
            List<OrderSetting> listData = new ArrayList<>();
            for (String[] str : list) {
                OrderSetting orderSetting = new OrderSetting();
                orderSetting.setOrderDate(new Date(str[0]));
                orderSetting.setNumber(Integer.parseInt(str[1]));
//                orderSetting.setReservations(0);
                listData.add(orderSetting);
            }
            orderSettingService.addBatch(listData);
            return new Result(true, MessageConstant.UPLOAD_SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.UPLOAD_FAIL);
        }
    }
}
