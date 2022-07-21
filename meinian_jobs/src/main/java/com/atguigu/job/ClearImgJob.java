package com.atguigu.job;

import com.atguigu.constant.RedisConstant;
import com.atguigu.util.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Iterator;
import java.util.Set;

/**
 * @author QiuShiyuan
 * @date 2022/5/7 - 22:17
 */
public class ClearImgJob {

    @Autowired
    JedisPool jedisPool;

    //清理图片的方法
    public void clearImg() {
        Set<String> set = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String pic = iterator.next();
            System.out.println("删除图片的名称是：" + pic);
            //删除图片服务器中的图片文件
            QiniuUtils.deleteFileFromQiniu(pic);
            //删除redis中的数据
            jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES, pic);

        }
    }
}
