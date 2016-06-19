package com.permission.service;

import java.util.List;

import com.permission.model.bo.UserBO;
import com.permission.model.vo.UserView;

public interface IUserManagerService {

	public int GetUserCntInOrg(int orgId);

	public UserBO Load(int orgId, int pageindex, int pagesize);

	public List<Integer> GetSubOrgIds(int orgId);

	public UserView Find(int id);

	public void Delete(int id);

	public void AddOrUpdate(UserView view);
}
