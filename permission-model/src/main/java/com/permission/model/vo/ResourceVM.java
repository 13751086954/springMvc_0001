package com.permission.model.vo;

/**
 * 资源表视图模型
 * @author milanyangbo
 *
 */
public class ResourceVM {
	/**
	 * 资源表ID
	 */
    private int id;

  
    /**
   	 * 
   	 */
    private String key;
       
    /**
   	 * 名称
   	 */
    private String name;

  
    /**
   	 * 状态
   	 */
    private int status;
    
    private Boolean isbelonguser;


	private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Boolean getIsBelongUser() {
        return isbelonguser;
    }

    public void setIsBelongUser(Boolean isbelonguser) {
        this.isbelonguser = isbelonguser;
    }

    public String getDescription() {
		// TODO Auto-generated method stub
		return this.description;
	} 
    
	public void setDescription(String description) {
		// TODO Auto-generated method stub
		this.description = description;
	}
	
	

}
