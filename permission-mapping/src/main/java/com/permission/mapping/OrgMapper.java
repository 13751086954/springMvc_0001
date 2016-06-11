package com.permission.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.permission.pojo.Category;
import com.permission.pojo.Org;

public interface OrgMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByCascadeId(@Param("cascadeId")String cascadeId);

    int insert(Org record);

    int insertSelective(Org record);

    Org selectByPrimaryKey(Integer id);
    
    List<Org> LoadOrgs();
    
    List<Org> LoadByUser(Integer userId);
    
    List<Org> LoadByParentId(Integer parentId);
    
    List<Org> GetSubOrgs(@Param("cascadeId")String cascadeId,@Param("orgId")Integer orgId);
    
    List<Integer> GetSubOrgIds(@Param("cascadeId")String cascadeId,@Param("orgId")Integer orgId);
    
    List<Org> Find(List<Integer> orgIds);
    
    List<Org> FindAll();

    int updateByPrimaryKeySelective(Org record);

    int updateByPrimaryKeyWithBLOBs(Org record);

    int updateByPrimaryKey(Org record);

	List<Category> SameLevels(Integer parentid, Integer id);
}