package com.permission.pojo;

import java.util.Date;

/**
 * 多对多关系集中映射
 * @author milanyangbo
 *
 */
public class Relevance {
	/**
	 * 流水号
	 */
    private int id;

    /**
	 * 第一个表主键ID
	 */
    private int firstid;

    /**
	 * 第二个表主键ID
	 */
    private int secondid;

    /**
   	 * 描述
   	 */
    private String description;

    /**
   	 * 
   	 */
    private String key;

    /**
   	 * 状态
   	 */
    private int status;

    /**
   	 * 授权时间
   	 */
    private Date operatetime;

    /**
   	 * 授权人
   	 */
    private int operatorid;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFirstid() {
        return firstid;
    }

    public void setFirstid(int firstid) {
        this.firstid = firstid;
    }

    public int getSecondid() {
        return secondid;
    }

    public void setSecondid(int secondid) {
        this.secondid = secondid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(Date operatetime) {
        this.operatetime = operatetime;
    }

    public int getOperatorid() {
        return operatorid;
    }

    public void setOperatorid(int operatorid) {
        this.operatorid = operatorid;
    }
}