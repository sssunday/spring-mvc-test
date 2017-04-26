package com.springlearn.advice;

public class UserAspect {

	public void doBefore(){
		System.out.println("before init");
	}
	
	public void doAfter(){
		System.out.println("doAfter init");
	}
}
