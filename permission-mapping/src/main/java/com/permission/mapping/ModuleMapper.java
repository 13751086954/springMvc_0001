package com.permission.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.permission.common.orm.PageInfo;
import com.permission.pojo.Module;

public interface ModuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Module record);

    int insertSelective(Module record);

    Module selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);
    
    List<Module> LoadModuleListPage(@Param("page")PageInfo page) ;

 	List<Module> LoadInOrgListPage(@Param("page")PageInfo page,@Param("ids")List<Integer> orgIds) ;
 	
 	List<Module> Find(@Param("ids")List<Integer> ids);
 	
 	List<Module> FindAll(); 	
 	
 	List<Module> LoadByParent(Integer parentId);
 	
 	List<Integer> FindOrgs(@Param("cascadeId")String cascadeId);
 	
 	List<Module> SameLevels(@Param("parentId")Integer parentId,@Param("id")Integer id);

}