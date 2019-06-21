package com.my_shop.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.my_shop.entity.Commodity;
import com.my_shop.entity.ShoppingCart;
import com.my_shop.mapper.CommodityMapper;
import com.my_shop.mapper.ShoppingCartMapper;
import com.my_shop.service.ShoppingCartService;
import com.my_shop.vo.ShowCartCommodity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private CommodityMapper commodityMapper;

    /**
     * 加入购物车
     * @param shoppingCart
     * @return
     */
    @Override
    public JSONObject addToCart(ShoppingCart shoppingCart) {
        JSONObject jsonObject = new JSONObject();
        int insert = shoppingCartMapper.insert(shoppingCart);
        if(insert!=0){
            jsonObject.put("code",0);
            jsonObject.put("msg","加入购物车成功");
        }else {
            jsonObject.put("code",1);
            jsonObject.put("msg","加入购物车失败");
        }
        return jsonObject;
    }

    /**
     * 获取用户购物车
     * @param id
     * @return
     */
    @Override
    public JSONObject getCartList(Integer id) {
        List<ShowCartCommodity> showCartCommodities = shoppingCartMapper.selectCartByCustomer(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","用户购物车");
        jsonObject.put("data",showCartCommodities);
        return jsonObject;
    }

    /**
     * 删除购物车
     * @param ids
     * @return
     */
    @Override
    public JSONObject deleteCart(Integer[] id) {
        JSONObject jsonObject = new JSONObject();
        int sum = 0;
        for (int i =0; i<id.length;i++){
            shoppingCartMapper.deleteByPrimaryKey(id[i]);
            sum++;
        }
        if(sum==id.length){
            jsonObject.put("code",0);
            jsonObject.put("msg","删除成功");
        }else {
            jsonObject.put("code",1);
            jsonObject.put("msg","删除失败");
        }
        return jsonObject;
    }

    /**
     * 更新购物车
     * @param id
     * @param commodityNumber
     * @return
     */
    @Override
    public JSONObject updateCart(Integer id, Integer commodityNumber) {
        JSONObject jsonObject = new JSONObject();
        ShoppingCart shoppingCart = shoppingCartMapper.selectByPrimaryKey(id);
        Commodity commodity = commodityMapper.selectByPrimaryKey(shoppingCart.getCommodityId());
        if(commodity.getInStock()<commodityNumber){
            jsonObject.put("code",2);
            jsonObject.put("msg","数量更新失败库存不足");
        }else{
            shoppingCart.setCommodityNumber(commodityNumber);
            int i = shoppingCartMapper.updateByPrimaryKeySelective(shoppingCart);
            if(i!=0){
                jsonObject.put("code",0);
                jsonObject.put("msg","数量更新成功");
            }else {
                jsonObject.put("code",1);
                jsonObject.put("msg","数量更新失败");
            }
        }
        return jsonObject;
    }
}
