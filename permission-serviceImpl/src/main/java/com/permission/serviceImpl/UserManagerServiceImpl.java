package com.permission.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import com.permission.common.orm.PageInfo;
import com.permission.mapping.UserMapper;
import com.permission.mapping.OrgMapper;
import com.permission.model.bo.UserBO;
import com.permission.model.vo.UserView;
import com.permission.pojo.User;
import com.permission.pojo.Org;
import com.permission.service.IUserManagerService;

@Service
public class UserManagerServiceImpl implements IUserManagerService {

	@Resource
	UserMapper _UserDao;
	@Resource
	OrgMapper _orgDao;

	Mapper _mapper ;

	private  UserManagerServiceImpl() {

		_mapper=new DozerBeanMapper();	
	}

	@Override
	public UserBO Load(Integer orgId, Integer pageindex,
			Integer pagesize) {
		// TODO Auto-generated method stub
		if (pageindex < 1) pageindex = 1;
		List<User> users = null;
		Integer total = 0;
		if (orgId == 0){
			PageInfo page=new PageInfo(pageindex,pagesize);
			users = _UserDao.LoadInOrgListPage(page,null);
			total = page.getTotalPage();
		}
		else{
			PageInfo page=new PageInfo(pageindex,pagesize);
			users = _UserDao.LoadInOrgListPage(page, GetSubOrgIds(orgId));
			total = page.getTotalPage();
		}
		List<UserView> userViews =new ArrayList<UserView>();
		for (User user : users) {
			UserView userView =  (UserView) _mapper.map(user,UserView.class);
			userViews.add(userView);
		}				
		for (UserView userView : userViews) {
			List<Org> orgs = _orgDao.LoadByUser(userView.getId());
			List<String> orgNames = new ArrayList<String>();
			for (Org org : orgs) {
				orgNames.add(org.getName());
			}
			userView.setOrganizationIds(StringUtils.join(orgNames, ","));
		}
		UserBO UserBO=new UserBO();
		UserBO.setTotal(total);
		UserBO.setList(userViews);
		UserBO.setPageCurrent(pageindex);
		return UserBO;
	}

	@Override
	public List<Integer> GetSubOrgIds(Integer orgId) {
		// TODO Auto-generated method stub
		Org org = _orgDao.selectByPrimaryKey(orgId);
		return _orgDao.GetSubOrgIds(org.getCascadeid(), 0);
	}

	@Override
	public UserView Find(Integer id) {
		// TODO Auto-generated method stub
		User model = _UserDao.selectByPrimaryKey(id);
		return (UserView) _mapper.map(model,UserView.class);
	}

	@Override
	public void Delete(Integer id) {
		// TODO Auto-generated method stub
		_UserDao.deleteByPrimaryKey(id);
	}

	@Override
	public void AddOrUpdate(UserView userView){
		// TODO Auto-generated method stub
		User model= (User) _mapper.map(userView,User.class);
		if (model.getId() == 0) {
			_UserDao.insert(model);
		}
		else {
			_UserDao.updateByPrimaryKey(model);
		}
	}

	@Override
	public Integer GetUserCntInOrg(Integer orgId) {
		// TODO Auto-generated method stub
		if (orgId == 0){
			return _UserDao.GetUserCntInOrg(null);
		}
		else{
			return _UserDao.GetUserCntInOrg(GetSubOrgIds(orgId));
		}
	}

}
