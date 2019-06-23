package com.my_shop.test;

import com.alibaba.fastjson.JSONObject;
import com.my_shop.entity.Address;
import com.my_shop.entity.Order;
import com.my_shop.mapper.AddressMapper;
import com.my_shop.mapper.OrderMapper;
import com.my_shop.service.AddressService;
import com.my_shop.service.OrderService;
import com.my_shop.service.impl.OrderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class OrderTest {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService service;
    @Test
    public void fun1(){
        Integer [] ids ={4,5};
        int customerId = 1;
        int address = 1;
        service.handlerGenerationOrder(ids,1,1);

  /*  Order order = new Order();
    order.setCustomerId(1);
    order.setAddressId(2);
    order.setTotalPrice(5000.0);
    order.setTotalQuantity(5);
    order.setStatus(0);
    int i = orderMapper.insert(order);
    System.out.println(order.getId());*/
    }
}
