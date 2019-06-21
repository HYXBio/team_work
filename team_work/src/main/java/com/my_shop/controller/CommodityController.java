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

    /**
     * 显示商品详情
     * @param id
     * @return
     */
    @RequestMapping("/commdity/getCommidtyDetial.action")
    @ResponseBody
    public JSONObject getCommidtyDetial(Integer id){
        JSONObject commdityList = commdityService.getCommdityDetial(id);
        return commdityList;
    }

    /**
     * 搜索
     * @param summary
     * @return
     */
    @RequestMapping("/commdity/searchCommidty.action")
    @ResponseBody
    public JSONObject searchCommidty(String summary){
        JSONObject jsonObject = commdityService.searchCommdity(summary);
        return jsonObject;
    }

    /**
     * 排序
     * @return
     */
    @RequestMapping("/commdity/orderByStock.action")
    @ResponseBody
    public JSONObject orderByStock(){
        JSONObject jsonObject = commdityService.orderByStock();
        return jsonObject;
    }



}
