/**
 * 
 */
package com.zhiyou.indemo.array;

/**
 * @author longWH 
 * 1.�����������
 *  2.������θ�ֵ 
 *  3.������α��� 
 *  4.��ά����
 */
public class TestArray {
	String[] array;
	String[] array2;

	public static void main(String[] args) {
		TestArray test  = new TestArray();
//		test.declare();
//		test.assign();
//		test.access();
//		test.twoDimesion();
		test.fourDimesion();
	}
	
	
	/**
	 * 1. ����������� 1. ����������ĳ���,����û�о����ֵ
	 */
	private void declare() {
		// 1.1 ����������ĳ���
		array = new String[10];

		// 1.2 û�ж�������ĳ���,������ֵ,����ֵ,���Ⱦ�����
		array2 = new String[] { "a", "b", "b", "b", "b" };

		// 1.3 ֱ�Ӹ�ֵ
		String[] array3 = { "a", "b", "b", "b", "b" };
	}

	private void assign() {
		for (int i = 0, len = array.length; i < len; i++) {
			array[i] = "" + i;
		}
	}
	
	/**
	 * ��������
	 */
	private void access() {
		/*for (String e : array) {
			System.out.println(e);
		}*/
		
		for (int i = 0,len =array.length ; i < len; i++) {
			array[i] = "adfdas";
			System.out.println(array[i]);
		}
	}
	
	/**
	 * ��ά����
	 */
	private void twoDimesion() {
		int[][] array = {
				{1,  2,  3,  4,   5},
				{6,  7,  8,  9,   10},
				{11, 12, 13, 14,  15}	
		};
		
		System.out.println(array[0][3]);
		System.out.println("========");
		for (int is : array[1]) {
			System.out.println(is);
		}
	}
	
	private void fourDimesion() {
		int[][][][] array = { //1. 
				{  //2  
					//============3=============
					{ 
						{ //4
							1,3,4,5,6
						},
						{ //4
							11,13,14,15,16
						}
					},
					//============3=============
                     { 
						{ //4
							1,3,4,5,6
						},
						{ //4
							11,13,14,15,16
						}
					}
				}	,
				//==========2=========
				{  //2  
					//============3=============
					{ 
						{ //4
							1,3,4,5,6
						},
						{ //4
							11,13,14,15,16
						}
					},
					//============3=============
                     { 
						{ //4
							1,3,4,5,6
						},
						{ //4
							11,13,14,15,16
						}
					}
					
				}	
		};
		
		for (int[][][] second : array) {
			for (int[][] third : second) {
				for (int[] forth : third) {
					System.out.println("==========");
					for (int e : forth) {
						System.out.println(e+", ");
					}
				}
			}
		}
	}
	
	// bad code
	//��һ���û���,��Ҫ�ж��Ƿ�Ϸ�:
	/*1. �û�������Ϊ��,�ǿ�,�򲻺Ϸ�
	 * 2. �û������ܴ��� 10λ
	 * 3. �û��������� "u"��ͷ 
	 * 4. �û������ܴ�"?"
	 * */
	private boolean badCodeCheckName(String name) {
		// bad code
		if (name != null) {
			if (name.length() <=10) {
				if (name.startsWith("u")) {
					if (!name.contains("?")) {
						return true;
					}else {
						return false;
					}
				}else {
					return false;
				}
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	/**�����Ż�     <�����ȫ> : �������Ż�
	 * @param name
	 * @return
	 */
	private boolean goodCodeCheckName(String name) {
		if (name == null) {//��������Ƚ��ر�,����Ҫ�ŵ���һλ
			return false;
		}
		
		if (!name.startsWith("u")) {
			return false;
		}
		
		if (name.contains("?")) {
			return false;
		}
		
		if (name.length() > 10) {
			return false;
		}
	
		return true;
	}

}
