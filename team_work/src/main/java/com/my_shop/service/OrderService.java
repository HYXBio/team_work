package com.my_shop.service;

import com.alibaba.fastjson.JSONObject;

public interface OrderService {
    public JSONObject handlerGenerationOrder(Integer[] ids,Integer address_id,Integer customer_id);
}
