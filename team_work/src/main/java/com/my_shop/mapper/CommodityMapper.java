package com.my_shop.mapper;

import com.my_shop.entity.Commodity;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

public interface CommodityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Commodity record);

    int insertSelective(Commodity record);

    Commodity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Commodity record);

    int updateByPrimaryKey(Commodity record);

    List<Commodity> selectCommdityList(Integer page);

    Integer selectCommdityCount();

    Commodity selectCommdityById(Integer id);

    List<Commodity> selectCommdityBySummary(String summary);

    List<Commodity> selectCommdityOrderBy();
}