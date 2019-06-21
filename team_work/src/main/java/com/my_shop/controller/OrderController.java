package com.my_shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.my_shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/user/order/orderGeneration.action")
    @ResponseBody
    public JSONObject orderGeneration(Integer[] cart_ids,Integer address_id,HttpServletRequest request){
        Integer id = (Integer) request.getAttribute("id");

        JSONObject result = null;
        return result;
    }

}
