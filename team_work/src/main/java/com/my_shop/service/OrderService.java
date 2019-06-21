package com.my_shop.service;

import com.alibaba.fastjson.JSONObject;

public interface OrderService {
    public JSONObject orderGeneration(Integer[] ids,Integer address_id);
}
