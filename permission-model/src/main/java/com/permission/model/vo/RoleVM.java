package com.permission.model.vo;

public class RoleVM {

	/**
	 *  流水号
	 */
    private int id;

    /**
   	 * 名称
   	 */
    private String name;
   
    /**
   	 * 所属部门节点语义ID
   	 */
    private String orgcascadeid;
    
    /**
   	 * 所属部门名称
   	 */
    private String orgname;
    
    
    /**
     * 是否属于某用户 
     */   
    public boolean isbelonguser;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    
    public String getOrgcascadeid() {
        return orgcascadeid;
    }

    public void setOrgcascadeid(String orgcascadeid) {
        this.orgcascadeid = orgcascadeid == null ? null : orgcascadeid.trim();
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname == null ? null : orgname.trim();
    }
    
    
    public Boolean getIsBelongUser() {
        return isbelonguser;
    }

    public void setIsBelongUser(Boolean isbelonguser) {
        this.isbelonguser = isbelonguser;
    }

}
