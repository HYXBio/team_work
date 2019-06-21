package com.my_shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.my_shop.entity.Customer;
import com.my_shop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    /**
     * 用户登录
     * @param customer
     * @param request
     * @return
     */
    @RequestMapping(value = "/customer/customerLogin.action")
    @ResponseBody
    public JSONObject customerLogin(Customer customer, HttpServletRequest request){
        JSONObject result = customerService.customerLogin(customer.getName(),customer.getPassword());
        Integer code = (Integer) result.get("code");
        if(code== 0){
            Customer customer1 = (Customer) result.get("data");
            request.setAttribute("name",customer1.getName());
            request.setAttribute("id",customer1.getId());
        }
        return result;
    }

    /**
     * 用户注册
     * @param customer
     * @return
     */
    @RequestMapping(value = "/customer/customerRegion.action")
    @ResponseBody
    public JSONObject customerRegion(Customer customer){
        JSONObject result = customerService.customerRegion(customer);
        return result;
    }

    /**
     * 获取用户信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/customer/getCustomerMessage.action")
    @ResponseBody
    public JSONObject customerRegion(HttpServletRequest request){
        Integer id = (Integer) request.getAttribute("id");
        JSONObject result = customerService.getCustomerById(id);
        return result;
    }



}
