package com.permission.controllers;

public class Test {

	public static void main(String[] args){
		String uri = "/user/getuser"; //获取的URI如：/user/getuser
		String finalURI = uri.substring(uri.lastIndexOf("/")+1); 
	    System.out.print(finalURI);  	
	}
}
