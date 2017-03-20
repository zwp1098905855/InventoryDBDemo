/**
 * 
 */
package com.zhiyou.indemo.test;

import com.zhiyou.indemo.bean.Goods;

/**
 * @author longWH
 *
 */
public class TestInstanceof {

	public static void main(String[] args) {
		TestInstanceof testInstanceof = new TestInstanceof();
		Goods goods = new Goods();
		// 判断一个对象是否是另一个类的实例
		//1. 1 true: 当对象是另一个类或子类时
//		if (goods instanceof IDao) { 
//			System.out.println("我是商品类的对象");
//		}
//		System.out.println("-=====");
		
		// 1.2 如果一个类实现了一个接口,则表达式: 
		//inner instanceof InnerInterface  返回true
		Inner inner = testInstanceof.new Inner();
		if (inner instanceof InnerInterface) {
			System.out.println("Inner ");
		}
		
		//1.3 实现多个接口,无论是抽象类还是普通的类,都和
		// 1.1 ,1.2 一致
	}
	
	interface InnerInterface{
		
	}
	
	class Inner implements InnerInterface{
		
	}
}
