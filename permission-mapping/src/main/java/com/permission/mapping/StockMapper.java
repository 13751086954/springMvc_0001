package com.permission.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.permission.common.orm.PageInfo;
import com.permission.pojo.Stock;

public interface StockMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Stock record);

    int insertSelective(Stock record);

    Stock selectByPrimaryKey(Integer id);
    
    List<Stock>  LoadStocks(@Param("page")PageInfo page);
    
    List<Stock>  LoadInOrgs(@Param("page")PageInfo page,@Param("orgIds")List<Integer> orgIds) ;

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKeyWithBLOBs(Stock record);

    int updateByPrimaryKey(Stock record);
}