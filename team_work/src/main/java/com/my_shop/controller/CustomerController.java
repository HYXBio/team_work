package com.my_shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.my_shop.entity.Customer;
import com.my_shop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    /**
     * 用户登录
     * @param customer
     * @return
     */
    @RequestMapping(value = "/customer/customerLogin.action")
    @ResponseBody
    public JSONObject customerLogin(Customer customer,HttpSession session){
        JSONObject result = customerService.customerLogin(customer.getName(),customer.getPassword());
        Integer code = (Integer) result.get("code");
        if(code== 0){
            Customer customer1 = (Customer) result.get("data");
            session.setAttribute("id",customer1.getId());
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
     * @return
     */
    @RequestMapping(value = "/user/customer/getCustomerMessage.action")
    @ResponseBody
    public JSONObject customerRegion(HttpSession session){
        Integer id = (Integer) session.getAttribute("id");
        JSONObject result = customerService.getCustomerById(id);
        return result;
    }



}
