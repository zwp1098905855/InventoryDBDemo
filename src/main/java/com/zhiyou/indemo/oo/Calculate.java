/**
 * 
 */
package com.zhiyou.indemo.oo;

/**
 * @author longWH
 *��������:  ������������ȫһ��,���Է���ֵ,�������η�.
 *1.����������ͬ
 *		�������������ͬ: ����Ҫ->�������Ͳ�ͬ �� ����˳��ͬ
 *
 *2.�������Ͳ�ͬ
 *
 *3.����˳��ͬ
 *
 * ע��: �������һ��,˳��(��ͬ�Ĳ������͵�˳��)��ͬ�Ļ�Ҳ��������
 */
public class Calculate {
	// 1.    ����������ͬ,�������Ͳ�ͬ
	
	//1.0 ��1.1 ��������
	public int add(int num, int num1) {
		return num + num1;
	}
	//1.1
	/*public int add(int num1, int num) {
		return num1 + num;
	}*/
	
	//1.2 �� 1.3 ���ڲ�ͬ�������͵�˳��ͬ,����Ҳ�����ع�ϵ
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
	
	//1.5  ����������ͬ ,���������ع�ϵ
	public double add(int num, double num1,long l) {
		return num+num1+l;
	}
	
	//2.0 �̳и���,������Ҫ��д����ķ���
	
	//2.1 ʵ�ֽӿ�,��ʵ�ֵ��� ʵ���˽ӿ�,��Ҫ��д����
	
	//2.1 ������� 2.0 һ��

}
