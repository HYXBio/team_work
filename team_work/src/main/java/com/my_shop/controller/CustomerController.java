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

    @RequestMapping(value = "/customer/customerLogin.action")
    @ResponseBody
    public HashMap customerLogin(Customer customer, HttpServletRequest request){
        HashMap<String,Object> result = customerService.customerLogin(customer.getName(),customer.getPassword());
        Integer code = (Integer) result.get("code");
        if(code== 0){
            Customer customer1 = (Customer) result.get("data");
            request.setAttribute("name",customer1.getName());
            request.setAttribute("id",customer1.getId());
        }
        return result;
    };
}
