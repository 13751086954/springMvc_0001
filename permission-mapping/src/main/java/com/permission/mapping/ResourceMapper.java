package com.permission.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.permission.common.orm.PageInfo;
import com.permission.pojo.Resource;

public interface ResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer id);
    
    List<Resource> LoadResourceListPage(@Param("page")PageInfo page);
    
    List<Resource> LoadInOrgListPage(@Param("page")PageInfo page,@Param("orgIds")List<Integer> orgIds) ;
    
    List<Resource> Find(List<Integer> resourceIds);
    
    List<Resource> FindAll();

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKeyWithBLOBs(Resource record);

    int updateByPrimaryKey(Resource record);
}