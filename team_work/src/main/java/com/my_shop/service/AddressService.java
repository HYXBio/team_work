package com.my_shop.service;

import com.alibaba.fastjson.JSONObject;

public interface AddressService {
    /**
     * 得到用户地址
     * @param id
     * @return
     */
    public JSONObject getCustomerAddress(Integer id);
}
