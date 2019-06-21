package com.my_shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.my_shop.entity.Address;
import com.my_shop.mapper.AddressMapper;
import com.my_shop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    /**
     * 得到用户地址
     * @param id
     * @return
     */
    @Override
    public JSONObject getCustomerAddress(Integer id) {
        JSONObject result = new JSONObject();
        if(id !=null){
            List<Address> addresses = addressMapper.selectByCustomerId(id);
            result.put("code",0);
            result.put("msg","请求成功");
            result.put("data",addresses);
        }else {
            result.put("code",1);
            result.put("msg","请登录");
        }
        return result;
    }
}
