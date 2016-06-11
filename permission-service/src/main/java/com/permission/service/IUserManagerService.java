package com.permission.service;

import java.util.List;

import com.permission.model.bo.UserBO;
import com.permission.model.vo.UserView;

public interface IUserManagerService {

	public Integer GetUserCntInOrg(Integer orgId);

	public UserBO Load(Integer orgId, Integer pageindex, Integer pagesize);

	public List<Integer> GetSubOrgIds(Integer orgId);

	public UserView Find(Integer id);

	public void Delete(Integer id);

	public void AddOrUpdate(UserView view);
}
