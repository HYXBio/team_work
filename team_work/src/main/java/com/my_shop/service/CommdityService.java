package com.my_shop.service;

import com.alibaba.fastjson.JSONObject;

public interface CommdityService {

    /**
     * 获取商品列表
     * @param page
     * @return
     */
    public JSONObject getCommdityList(Integer page);

    /**
     * 获取商品详情
     * @param id
     * @return
     */
    public JSONObject getCommdityDetial(Integer id);

    /**
     * 搜索
     * @param summary
     * @return
     */
    public JSONObject searchCommdity(String summary);

    /**
     * 按销量排序
     */
    public JSONObject orderByStock();
}
