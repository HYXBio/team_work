package com.my_shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.my_shop.entity.Address;
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

    /**
     * 获取用户地址
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/address/getCustomerAddress.action")
    @ResponseBody
    public JSONObject getCustomerAddress(HttpServletRequest request){
        Integer id = (Integer) request.getAttribute("id");
        JSONObject result = addressService.getCustomerAddress(id);
        return result;
    }

    /**
     * 添加或者更新地址信息
     * @param address
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/address/upDateOrAddAddress.action")
    @ResponseBody
    public JSONObject upDateOrAddAddress(Address address, HttpServletRequest request){
        Integer id = (Integer) request.getAttribute("id");
        address.setCustomerId(id);
        JSONObject result = addressService.upDateOrAddAddress(address);
        return result;
    }
}
