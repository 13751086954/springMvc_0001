package com.permission.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 出入库信息表
 * @author milanyangbo
 *
 */
public class Stock {
	
    public Stock(){
      this.id= 0;
      this.name= "";
      this.number= 0;
      this.price= new BigDecimal(0.00);
      this.status= 0;
      this.user= "";
      this.time= new Date(System.currentTimeMillis());
      this.orgid= 0;
    }
	/**
	 * 数据ID
	 */
    private int id;

    /**
	 * 产品数量
	 */
    private int number;

    /**
	 * 产品单价
	 */
    private BigDecimal price;

    /**
	 * 出库/入库
	 */
    private int status;

    /**
	 * 
	 */
    private String user;

    /**
	 * 操作时间
	 */
    private Date time;

    /**
	 * 组织ID
	 */
    private int orgid;

    /**
	 * 产品名称
	 */
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getOrgid() {
        return orgid;
    }

    public void setOrgid(int orgid) {
        this.orgid = orgid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}