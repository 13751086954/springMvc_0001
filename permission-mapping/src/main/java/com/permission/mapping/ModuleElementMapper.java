package com.permission.mapping;

import java.util.List;
import java.util.Map;

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
    
    List<ModuleElement> Find(Map<String, Object> paramMap);
    
    List<ModuleElement> FindByModuleId(Integer moduleId);
}