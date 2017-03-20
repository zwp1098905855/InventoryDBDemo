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
		// �ж�һ�������Ƿ�����һ�����ʵ��
		//1. 1 true: ����������һ���������ʱ
//		if (goods instanceof IDao) { 
//			System.out.println("������Ʒ��Ķ���");
//		}
//		System.out.println("-=====");
		
		// 1.2 ���һ����ʵ����һ���ӿ�,����ʽ: 
		//inner instanceof InnerInterface  ����true
		Inner inner = testInstanceof.new Inner();
		if (inner instanceof InnerInterface) {
			System.out.println("Inner ");
		}
		
		//1.3 ʵ�ֶ���ӿ�,�����ǳ����໹����ͨ����,����
		// 1.1 ,1.2 һ��
	}
	
	interface InnerInterface{
		
	}
	
	class Inner implements InnerInterface{
		
	}
}
