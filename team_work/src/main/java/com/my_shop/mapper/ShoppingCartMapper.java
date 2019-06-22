package com.my_shop.mapper;

import com.my_shop.entity.ShoppingCart;
import com.my_shop.vo.ShowCartCommodity;

import java.util.List;

public interface ShoppingCartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShoppingCart record);

    int insertSelective(ShoppingCart record);

    ShoppingCart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShoppingCart record);

    int updateByPrimaryKey(ShoppingCart record);

    List<ShowCartCommodity> selectCartByCustomer(Integer customer_id);

    ShoppingCart selectByCustomerAndCommodity(ShoppingCart shoppingCart);

    ShowCartCommodity selectCartById(Integer id);
}