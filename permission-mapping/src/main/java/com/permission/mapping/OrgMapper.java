package com.permission.mapping;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.permission.pojo.Org;

public interface OrgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Org record);

    int insertSelective(Org record);

    Org selectByPrimaryKey(Integer id);
    
    List<Org> LoadOrgs();
    
    List<Org> LoadByUser(Integer userId);
    
    List<Org> GetSubOrgs(@Param("cascadeId")String cascadeId,@Param("orgId")Integer orgId);
    
    List<Org> Find(List<Integer> orgIds);
    
    List<Org> FindAll();

    int updateByPrimaryKeySelective(Org record);

    int updateByPrimaryKeyWithBLOBs(Org record);

    int updateByPrimaryKey(Org record);
}