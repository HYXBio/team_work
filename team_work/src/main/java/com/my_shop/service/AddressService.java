package com.my_shop.service;

import com.alibaba.fastjson.JSONObject;
import com.my_shop.entity.Address;

public interface AddressService {
    /**
     * 得到用户地址
     * @param id
     * @return
     */
    public JSONObject getCustomerAddress(Integer id);

    /**
     * 插入或更新用户地址
     * @param address
     * @return
     */
    public JSONObject upDateOrAddAddress(Address address);
}
