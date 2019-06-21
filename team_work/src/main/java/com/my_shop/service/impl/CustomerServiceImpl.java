package com.my_shop.service.impl;

import com.alibaba.fastjson.JSONObject;
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
    public JSONObject customerLogin(String name, String pwd) {
        Customer customer = customerMapper.selectByName(name);
        JSONObject result = new JSONObject();
        if(customer!=null){
            if(customer.getPassword().equals(pwd)){
                result.put("code",0);
                result.put("msg","登录成功");
                result.put("data",customer);
            }else {
                result.put("code",1);
                result.put("msg","账户密码错误");
            }
        }else {
            result.put("code",1);
            result.put("msg","账户不存在");
        }
        return result;
    }

    /**
     * 用户注册
     * @param
     * @param
     * @return
     */
    @Override
    public JSONObject customerRegion(Customer customer) {
        JSONObject result = new JSONObject();
        Customer judge = customerMapper.selectByName(customer.getName());
        if(judge!=null){
            result.put("code",2);
            result.put("msg","用户存在");
        }else{
            int insert = customerMapper.insert(customer);
            if(insert!=0){
                result.put("code",0);
                result.put("msg","注册成功");
            }else {
                result.put("code",1);
                result.put("msg","信息错误");
            }
        }
        return result;
    }

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    @Override
    public JSONObject getCustomerById(Integer id) {
        JSONObject result = new JSONObject();
        if(id==null){
            result.put("code",1);
            result.put("msg","用户不存在");
        }else {
            Customer customer = customerMapper.selectByPrimaryKey(id);
            result.put("code",0);
            result.put("msg","请求成功");
            customer.setPassword("null");
            result.put("data",customer);
        }
        return result;
    }



}
