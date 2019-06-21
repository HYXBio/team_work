package com.my_shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.my_shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/user/order/orderGeneration.action")
    @ResponseBody
    public JSONObject orderGeneration(Integer[] cart_ids, Integer address_id, HttpSession session){
        Integer id = (Integer) session.getAttribute("id");
        orderService.handlerGenerationOrder(cart_ids,address_id,id);
        JSONObject result = null;
        return result;
    }

}
