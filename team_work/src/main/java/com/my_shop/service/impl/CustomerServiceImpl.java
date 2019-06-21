package com.my_shop.service.impl;

import com.my_shop.entity.Customer;
import com.my_shop.mapper.CustomerMapper;
import com.my_shop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    /**
     *用户登录
     * @param name
     * @param pwd
     * @return
     */
    @Override
    public HashMap customerLogin(String name, String pwd) {
        Customer customer = customerMapper.selectByName(name);
        HashMap<String,Object> jsonObject = new HashMap<>();
        if(customer!=null){
            if(customer.getPassword().equals(pwd)){
                jsonObject.put("code",0);
                jsonObject.put("msg","登录成功");
                jsonObject.put("data",customer);
            }else {
                jsonObject.put("code",1);
                jsonObject.put("msg","账户密码错误");
            }
        }else {
            jsonObject.put("code",1);
            jsonObject.put("msg","账户不存在");
        }
        return jsonObject;
    }
}
