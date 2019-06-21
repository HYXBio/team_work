package com.my_shop.service;

import com.alibaba.fastjson.JSONObject;
import com.my_shop.entity.Customer;

import java.util.HashMap;

public interface CustomerService {
    /**
     * 用户登录
     * @param name
     * @param pwd
     * @return
     */
    public JSONObject customerLogin(String name, String pwd);

    /**
     * 用户注册
     * @param customer
     * @return
     */
    public JSONObject customerRegion(Customer customer);

    /**
     * 得到用户信息
     * @param id
     * @return
     */
    public JSONObject getCustomerById(Integer id);


}
