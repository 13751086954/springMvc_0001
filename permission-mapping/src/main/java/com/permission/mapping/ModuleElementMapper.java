package com.permission.mapping;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.permission.pojo.ModuleElement;
import com.permission.pojo.ModuleElementWithBLOBs;

public interface ModuleElementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ModuleElementWithBLOBs record);

    int insertSelective(ModuleElementWithBLOBs record);

    ModuleElementWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ModuleElementWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ModuleElementWithBLOBs record);

    int updateByPrimaryKey(ModuleElement record);
    
    List<ModuleElement> Find(@Param("moduleId")Integer moduleId, @Param("ids")List<Integer> elementIds);
    
    List<ModuleElement> FindByModuleId(Integer moduleId);
}