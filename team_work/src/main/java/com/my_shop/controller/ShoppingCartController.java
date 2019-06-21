package com.my_shop.controller;

import com.alibaba.fastjson.JSONObject;
import com.my_shop.entity.ShoppingCart;
import com.my_shop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 加入购物车
     * @param request
     * @param shoppingCart
     * @return
     */
    @RequestMapping(value = "/user/shopping_cart/addToShoppingCar.action")
    @ResponseBody
    public JSONObject addToShoppingCar(HttpSession session, ShoppingCart shoppingCart){
        Integer id = (Integer) session.getAttribute("id");
        shoppingCart.setCustomerId(id);
        JSONObject result = shoppingCartService.addToCart(shoppingCart);
        return result;
    }

    /**
     * 获取用户购物车
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/shopping_cart/getCustomerShoppingCar.action")
    @ResponseBody
    public JSONObject getCustomerShoppingCar(HttpSession session){
        Integer id = (Integer) session.getAttribute("id");
        JSONObject cartList = shoppingCartService.getCartList(id);
        return cartList;
    }

    /**
     * 批量删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/shopping_cart/deleteShoppingCar.action")
    @ResponseBody
    public JSONObject deleteShoppingCar(Integer[] id){
        JSONObject result = shoppingCartService.deleteCart(id);
        return result;
    }

    /**
     * 更新购物车
     * @param id
     * @param commodityNumber
     * @return
     */
    @RequestMapping(value = "/user/shopping_cart/updateShoppingCar.action")
    @ResponseBody
    public JSONObject updateShoppingCar(Integer id,Integer commodityNumber){
        JSONObject result = shoppingCartService.updateCart(id,commodityNumber);
        return result;
    }

}
