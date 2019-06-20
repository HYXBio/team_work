package com.my_shop.service;

import java.util.HashMap;

public interface CustomerService {
    /**
     * 用户登录
     * @param name
     * @param pwd
     * @return
     */
    public HashMap customerLogin(String name, String pwd);
}
