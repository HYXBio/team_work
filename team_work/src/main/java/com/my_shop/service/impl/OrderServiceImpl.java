package com.my_shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.my_shop.entity.Commodity;
import com.my_shop.entity.Order;
import com.my_shop.entity.OrderDetial;
import com.my_shop.entity.ShoppingCart;
import com.my_shop.mapper.CommodityMapper;
import com.my_shop.mapper.OrderDetialMapper;
import com.my_shop.mapper.OrderMapper;
import com.my_shop.mapper.ShoppingCartMapper;
import com.my_shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetialMapper orderDetialMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private CommodityMapper commodityMapper;

    /**
     * 处理订单
     * @param ids
     * @param address_id
     * @param customer_id
     * @return
     */
    @Override
    public JSONObject handlerGenerationOrder(Integer[] ids, Integer address_id,Integer customer_id) {
        Object[] par = getPar(ids,address_id,customer_id);
        JSONObject jsonObject = new JSONObject();
        Order order = generationOrder(par);
        for(int i = 0; i< ids.length;i++){
            ShoppingCart shoppingCart = shoppingCartMapper.selectByPrimaryKey(ids[i]);
            generationOrderDetial(order,shoppingCart);
        }
        jsonObject.put("code",0);
        jsonObject.put("msg","订单生成");
        jsonObject.put("data",order.getId());
        return jsonObject;
    }


    /**
     * 生成订单
     * @param par
     * @return
     */
    public Order generationOrder(Object[] par){
        Order order = new Order();
        order.setCustomerId((Integer) par[0]);
        order.setTotalQuantity((Integer) par[1]);
        order.setTotalPrice((Double) par[2]);
        order.setAddressId((Integer) par[3]);
        order.setStatus(1);
        orderMapper.insert(order);
        return order;
    }

    /**
     * 获取用户信息，产品信息,订单金额信息
     * @param ids
     * @param address_id
     * @param customer_id
     * @return
     */
    public Object[] getPar(Integer[] ids, Integer address_id,Integer customer_id){
        int sum = 0;
        double total_money=0;
        for(int i = 0; i< ids.length;i++){
            ShoppingCart shoppingCart = shoppingCartMapper.selectByPrimaryKey(ids[i]);
            Commodity commodity = commodityMapper.selectByPrimaryKey(shoppingCart.getCommodityId());
            sum += shoppingCart.getCommodityNumber();
            total_money +=shoppingCart.getCommodityNumber()*commodity.getPrice();
        }
        Object[] par = {customer_id,sum,total_money,address_id};
        return par;
    }

    /**
     * 生成订单详情,并处理购物车
     * @param order
     * @param shoppingCart
     */
    public void  generationOrderDetial (Order order,ShoppingCart shoppingCart){
        try {
            OrderDetial orderDetial= new OrderDetial();
            orderDetial.setOrderId(order.getId());
            orderDetial.setCommodityId(shoppingCart.getCommodityId());
            orderDetial.setCommodityQuantity(shoppingCart.getCommodityNumber());
            orderDetialMapper.insert(orderDetial);
            shoppingCartMapper.deleteByPrimaryKey(shoppingCart.getId());
        }catch (Exception e){
             e.printStackTrace();
        }
    }

}
