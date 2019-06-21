package com.my_shop.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.my_shop.service.CommdityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommodityController {
    @Autowired
    private CommdityService commdityService;

    /**
     * 返回商品列表
     * @param page
     * @return
     */
    @RequestMapping("/commdity/getCommidtyList.action")
    @ResponseBody
    public JSONObject getCommidtyList(Integer page){
        JSONObject commdityList = commdityService.getCommdityList(page);
        return commdityList;
    }

    @RequestMapping("/commdity/getCommidtyDetial.action")
    @ResponseBody
    public JSONObject getCommidtyDetial(Integer id){
        JSONObject commdityList = commdityService.getCommdityDetial(id);
        return commdityList;
    }
}
