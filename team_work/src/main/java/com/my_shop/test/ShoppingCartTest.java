package com.my_shop.test;

import com.alibaba.fastjson.JSONObject;
import com.my_shop.entity.Commodity;
import com.my_shop.entity.ShoppingCart;
import com.my_shop.mapper.CommodityMapper;
import com.my_shop.mapper.ShoppingCartMapper;
import com.my_shop.service.ShoppingCartService;
import com.my_shop.vo.ShowCartCommodity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ShoppingCartTest {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private CommodityMapper commodityMapper;

    @Test
    public void fun1(){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCustomerId(1);
        shoppingCart.setCommodityId(5);
        shoppingCart.setCommodityNumber(5);
        JSONObject commdityDetial = shoppingCartService.addToCart(shoppingCart);
        System.out.println(commdityDetial);
    }

    @Test
    public void fun2(){
        List<ShowCartCommodity> shoppingCarts = shoppingCartMapper.selectCartByCustomer(1);
        for (ShowCartCommodity s : shoppingCarts){
            System.out.println(s.getCommodity());
        }
        System.out.println(shoppingCarts);

    }

    @Test
    public void fun3(){
        JSONObject j = shoppingCartService.getCartList(1);
        System.out.println(j);
    }

    @Test
    public void fun4(){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCustomerId(1);
        shoppingCart.setCommodityId(5);
        JSONObject j = shoppingCartService.addToCart(shoppingCart);
        System.out.println(j);
    }
}
