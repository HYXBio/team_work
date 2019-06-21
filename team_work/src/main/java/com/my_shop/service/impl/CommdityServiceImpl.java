package com.my_shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.my_shop.entity.Commodity;
import com.my_shop.mapper.CommodityMapper;
import com.my_shop.service.CommdityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CommdityServiceImpl implements CommdityService {
    @Autowired
    private CommodityMapper commodityMapper;

    /**
     * 返回商品列表
     * @param page
     * @return
     */
    @Override
    public JSONObject getCommdityList(Integer page) {
        List<Commodity> commodityList = new ArrayList<>();
        JSONObject result = new JSONObject();
        Integer total = commodityMapper.selectCommdityCount();
        if(page ==null){
            commodityList = commodityMapper.selectCommdityList(0);
        }else {
            commodityList = commodityMapper.selectCommdityList(page*5);
        }
        JSONObject data = new JSONObject();
        data.put("total",total);
        data.put("list",commodityList);
        result.put("code",0);
        result.put("msg","商品数量");
        result.put("data",data);
        return result;
    }

    /**
     * 获取商品详情
     * @param id
     * @return
     */
    @Override
    public JSONObject getCommdityDetial(Integer id) {
        Commodity commodity = commodityMapper.selectByPrimaryKey(id);
        JSONObject result = new JSONObject();
        result.put("code",0);
        result.put("msg","商品详情");
        result.put("data",commodity);
        return result;
    }
}
