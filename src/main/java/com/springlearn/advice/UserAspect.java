package com.springlearn.advice;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class UserAspect {

	public void doBefore(JoinPoint jp){
		System.out.println();
		System.out.println("doBefore init");
	}
	
	public void doAfter(JoinPoint jp){
		System.out.println();
		System.out.println("doAfter init");
	}
	
	/**
	 * 环绕
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
		//pjp.proceed 执行切点方法，可以在这句话的前后进行需要的操作
		Object obj = pjp.proceed();
		System.out.println();
		System.out.println("doAround init");
		return obj;
	}
	
	/**
	 * return
	 */
	public void doAfterReturning(){
		System.out.println();
		System.out.println("doReturn init");
	}
	
	/**
	 * 异常
	 */
	public void doAfterThrowing(Throwable ex){
		System.out.println();
		System.out.println("doThrowException init" + ex.getMessage());
	}
}
