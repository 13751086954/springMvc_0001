package com.permission.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import com.permission.mapping.ModuleElementMapper;
import com.permission.mapping.ModuleMapper;
import com.permission.mapping.OrgMapper;
import com.permission.mapping.RelevanceMapper;
import com.permission.mapping.ResourceMapper;
import com.permission.mapping.RoleMapper;
import com.permission.mapping.UserMapper;
import com.permission.model.vo.LoginUserVM;
import com.permission.model.vo.ModuleView;
import com.permission.pojo.Module;
import com.permission.pojo.Role;
import com.permission.pojo.User;
import com.permission.service.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService  {

   @Resource
   private UserMapper _userDao;
   @Resource
   private ModuleMapper _moduleDao;
   @Resource
   private RelevanceMapper _relevanceDao;
   @Resource
   private ModuleElementMapper _moduleElementDao;  
   @Resource
   private ResourceMapper _resourceDao;
   @Resource
   private OrgMapper _orgDao;  
   @Resource
   private RoleMapper _roleDao;
	  
   Mapper _mapper ;
	  
	private  LoginServiceImpl() {
		
		_mapper=new DozerBeanMapper();	
	}
	
	public LoginUserVM Login(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		User user = _userDao.FindSingle(username);
		if (user == null){
            throw new Exception("用户帐号不存在");
        }
		
		CheckPassword(user.getPassword(),  password);
		
		LoginUserVM loginVM = new LoginUserVM();
		loginVM.setUser(user);
		 
		//用户角色
        List<Integer> userRoleIds = _relevanceDao.FindUserRoleIds(user.getId());

        //用户角色与自己分配到的模块ID
        List<Integer> moduleIds = _relevanceDao.FindModuleIds(user.getId(),userRoleIds);

        //用户角色与自己分配到的菜单ID
        List<Integer> elementIds = _relevanceDao.FindElementIds(user.getId(),userRoleIds);
        
        List<Role> roles= _roleDao.FindUserRoles(userRoleIds);
         
        loginVM.setRoles(roles);
            
        List<ModuleView> moduleViews=null;
        
        List<Module> modules = _moduleDao.Find(moduleIds);
         
        if (modules!=null) {
        	 moduleViews= new ArrayList<ModuleView>();
        	 for (Module module : modules) {
            	 ModuleView moduleView= (ModuleView) _mapper.map(module,ModuleView.class);
            	 moduleView .Elements= _moduleElementDao.Find(module.getId(), elementIds);
            	 moduleViews.add(moduleView);
             }
        	 
		}
       
        
        loginVM.setModules(moduleViews);
        
        //用户角色与自己分配到的资源ID
        List<Integer> resourceIds = _relevanceDao.FindResourceIds(user.getId(),userRoleIds);

        loginVM.setResources(_resourceDao.Find(resourceIds)); 

        //用户角色与自己分配到的机构ID
        List<Integer> orgids = _relevanceDao.FindOrgIds(user.getId(),userRoleIds);
        
        loginVM.setAccessedOrgs(_orgDao.Find(orgids)); 
                        
		return loginVM;
	}

	
    public void CheckPassword(String sqlpassword, String password) throws Exception{
        if (!sqlpassword.equals(password) ){
        	
            throw  new Exception("密码错误");
        }
    }

	
	/**
	 * 开发者登陆
	 */
	public LoginUserVM LoginByDev() {
		// TODO Auto-generated method stub
		LoginUserVM loginUser = new LoginUserVM();
		User user=new User();
		user.setAccount("System");
		user.setName("开发者账号");
		loginUser.setUser(user);
		
		List<Role> roles= _roleDao.FindAll();
        
		loginUser.setRoles(roles);
		
		List<Module> modules = _moduleDao.FindAll();
	        
	    List<ModuleView> moduleViews= null;
	    //模块包含的菜单     
	    if (modules!=null) {	    	
       	    moduleViews= new ArrayList<ModuleView>();	    
		    for (Module module : modules) {
		         ModuleView moduleView= (ModuleView) _mapper.map(module,ModuleView.class);
		         moduleView .Elements= _moduleElementDao.FindByModuleId(module.getId());
		         moduleViews.add(moduleView);
		    }
	    }
	        
	    loginUser.setModules(moduleViews);
	    
	    loginUser.setResources(_resourceDao.FindAll());
		
	    loginUser.setAccessedOrgs(_orgDao.FindAll());
	    
		return loginUser;
	}

	@Override
	public User FindSingle(String account) {
		// TODO Auto-generated method stub
		return _userDao.FindSingle(account);
	}

}
