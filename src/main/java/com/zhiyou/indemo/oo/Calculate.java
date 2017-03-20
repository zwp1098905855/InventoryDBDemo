/**
 * 
 */
package com.zhiyou.indemo.oo;

/**
 * @author longWH
 *方法重载:  方法名必须完全一致,忽略返回值,忽略修饰符.
 *1.参数数量不同
 *		如果参数数量相同: 则需要->参数类型不同 或 参数顺序不同
 *
 *2.参数类型不同
 *
 *3.参数顺序不同
 *
 * 注意: 如果类型一致,顺序(不同的参数类型的顺序)不同的话也不是重载
 */
public class Calculate {
	// 1.    参数数量相同,参数类型不同
	
	//1.0 和1.1 不是重载
	public int add(int num, int num1) {
		return num + num1;
	}
	//1.1
	/*public int add(int num1, int num) {
		return num1 + num;
	}*/
	
	//1.2 和 1.3 由于不同参数类型的顺序不同,所以也是重载关系
	public float add(int num, float num1) {
		return num+num1;
	}
	//1.3
	public float add(float num1,int num ) {
		return num+num1;
	}
	//1.4
	public double add(int num, double num1) {
		return num+num1;
	}
	
	//1.5  参数数量不同 ,所以是重载关系
	public double add(int num, double num1,long l) {
		return num+num1+l;
	}
	
	//2.0 继承父类,子类需要重写父类的方法
	
	//2.1 实现接口,被实现的类 实现了接口,需要重写方法
	
	//2.1 抽象类和 2.0 一致

}
