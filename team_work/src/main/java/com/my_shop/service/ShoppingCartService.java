package com.my_shop.service;

import com.alibaba.fastjson.JSONObject;
import com.my_shop.entity.ShoppingCart;

public interface ShoppingCartService {
    public JSONObject addToCart(ShoppingCart shoppingCart);

    public JSONObject getCartList(Integer id);

    JSONObject getOrderCartList(Integer[] ids);

    public  JSONObject deleteCart(Integer[] id);

    public  JSONObject updateCart(Integer id,Integer commodityNumber);
}
