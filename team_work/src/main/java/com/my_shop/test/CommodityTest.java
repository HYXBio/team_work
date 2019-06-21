package com.my_shop.test;

import com.alibaba.fastjson.JSONObject;
import com.my_shop.entity.Commodity;
import com.my_shop.mapper.CommodityMapper;
import com.my_shop.service.CommdityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CommodityTest {
    @Autowired
    private CommdityService commdityService;

    @Autowired
    private CommodityMapper commodityMapper;

    @Test
    public void fun1(){
        JSONObject commdityList = commdityService.getCommdityList(3);
        JSONObject commdityDetial = commdityService.getCommdityDetial(5);
        System.out.println(commdityList);
    }

    @Test
    public void fun2(){
        List<Commodity> commodityList = commodityMapper.selectCommdityBySummary("dd");
        System.out.println(commodityList.size());
    }
}
