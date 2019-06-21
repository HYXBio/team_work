package com.my_shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.my_shop.entity.ShoppingCart;
import com.my_shop.mapper.OrderDetialMapper;
import com.my_shop.mapper.ShoppingCartMapper;
import com.my_shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetialMapper orderDetialMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;


    @Override
    public JSONObject orderGeneration(Integer[] ids, Integer address_id) {
        List<ShoppingCart> count = new ArrayList<>();
        int sum = 0;
        double total_money=0;
        for(int i = 0; i< ids.length;i++){
            ShoppingCart shoppingCart = shoppingCartMapper.selectByPrimaryKey(ids[0]);

            sum += shoppingCart.getCommodityNumber();

            count.add(shoppingCart);
        }

        return null;
    }
}
