package com.my_shop.test;

import com.alibaba.fastjson.JSONObject;
import com.my_shop.mapper.AddressMapper;
import com.my_shop.service.AddressService;
import com.my_shop.service.impl.AddressServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AddressTest {

    @Autowired
    private AddressService addressService;

    @Test
    public void fun1(){
        JSONObject customerAddress = addressService.getCustomerAddress(1);

    }
}
