package com.permission.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.permission.common.orm.PageInfo;
import com.permission.common.utils.SessionService;
import com.permission.mapping.OrgMapper;
import com.permission.mapping.StockMapper;
import com.permission.model.bo.StockBO;
import com.permission.model.vo.LoginUserVM;
import com.permission.pojo.Org;
import com.permission.pojo.Stock;
import com.permission.service.IStockManagerService;

@Service
public class StockManagerServiceImpl implements IStockManagerService {

	@javax.annotation.Resource
	StockMapper _stockDao;
	@javax.annotation.Resource
	OrgMapper _orgDao;

	@Resource
	SessionService _sessionHelper;

	@Override
	public StockBO Load(int orgId, int pageindex, int pagesize) throws Exception {
		// TODO Auto-generated method stub
		List<Stock> Stocks = new ArrayList<Stock>();
		LoginUserVM user = (LoginUserVM)_sessionHelper.GetSessionUser();
		List<Org> orgs =user.getAccessedOrgs();
		List<Integer> loginOrgs = new ArrayList<Integer>();		
		for (Org org : orgs) {
			loginOrgs.add(org.getId());
		}
		int total;
		if (orgId == 0){          
			if (loginOrgs.size() == 0) { //改用户没有任何可见机构
				PageInfo page=new PageInfo(pageindex,pagesize);
				Stocks = _stockDao.LoadUserListPage(page,user.getUser().getAccount());
				total = page.getTotalPage();
			}
			else{
				PageInfo page=new PageInfo(pageindex,pagesize);
				Stocks = _stockDao.LoadInOrgListPage(page,loginOrgs);
				total = page.getTotalPage();
			}

		}
		else { //加载选择的机构及用户可访问的所有子机构 
			String cascadeId = "0.";
			if (orgId != 0){
				Org org = _orgDao.selectByPrimaryKey(orgId);
				if (org == null)
					throw new Exception("未能找到指定对象信息");
				cascadeId = org.getCascadeid();
			}
			List<Org> orgs1 = _orgDao.GetSubOrgs(cascadeId,orgId);
			List<Integer> orgIds = new ArrayList<Integer>();
			for (Org org : orgs1) {
				orgIds.add(org.getId());
			}
			orgIds.add(orgId);
			PageInfo page=new PageInfo(pageindex,pagesize);
			Stocks = _stockDao.LoadInOrgListPage(page,loginOrgs);
			total = page.getTotalPage();
		}
		StockBO stockBO=new StockBO();
		stockBO.setTotal(total);
		stockBO.setList(Stocks);
		stockBO.setPageCurrent(pageindex);
		return stockBO;
	}

	@Override
	public Stock Find(int id) {
		// TODO Auto-generated method stub
		Stock stock = _stockDao.selectByPrimaryKey(id);
		if (stock == null) {
			return new Stock();
		}
		return stock;
	}

	@Override
	public void Delete(int id) {
		// TODO Auto-generated method stub
		_stockDao.deleteByPrimaryKey(id);
	}

	@Override
	public void AddOrUpdate(Stock model) {
		// TODO Auto-generated method stub
		if (model.getId() == 0){
			_stockDao.insert(model);
		}
		else {
			_stockDao.updateByPrimaryKey(model);
		}
	}

}
