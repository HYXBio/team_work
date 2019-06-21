package com.my_shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.my_shop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AddressController {
    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/address/getCustomerAddress.action")
    @ResponseBody
    public JSONObject getCustomerAddress(HttpServletRequest request){
        Integer id = (Integer) request.getAttribute("id");
        JSONObject result = addressService.getCustomerAddress(id);
        return result;
    }
}
