package com.permission.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.permission.common.orm.PageInfo;
import com.permission.pojo.Category;

public interface CategoryMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);
    
    List<Category>  LoadCategoryListPage(@Param("page")PageInfo page);
       
    List<Category>  LoadInOrgListPage(@Param("page")PageInfo page,@Param("ids")List<Integer> orgIds) ;

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}