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

    /**
     * 插入或者更新用户地址
     * @param address
     * @return
     */
    @Override
    public JSONObject upDateOrAddAddress(Address address) {
        int i=0;
        JSONObject jsonObject = new JSONObject();
        if(address.getId()!=null){
             i = addressMapper.updateByPrimaryKeySelective(address);
        }else {
             i = addressMapper.insertSelective(address);
        }
        if(i!=0){
            jsonObject.put("code",0);
            jsonObject.put("msg","添加地址成功");
        }else {
            jsonObject.put("code",1);
            jsonObject.put("msg","添加地址失败");
        }
        return jsonObject;
    }

    /**
     * 地址删除
     * @param id
     * @return
     */
    @Override
    public JSONObject deleteAddressById(Integer id) {
        JSONObject jsonObject = new JSONObject();
        int i = addressMapper.deleteByPrimaryKey(id);
        if(i!=0){
            jsonObject.put("code",0);
            jsonObject.put("msg","删除地址成功");
        }
        return jsonObject;
    }
}
