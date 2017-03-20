/**
 * 
 */
package com.zhiyou.indemo.array;

/**
 * @author longWH 
 * 1.数组如何声明
 *  2.数组如何赋值 
 *  3.数组如何遍历 
 *  4.二维数组
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
	 * 1. 数组如何声明 1. 声明了数组的长度,但是没有具体的值
	 */
	private void declare() {
		// 1.1 声明了数组的长度
		array = new String[10];

		// 1.2 没有定义数组的长度,但是有值,有了值,长度就有了
		array2 = new String[] { "a", "b", "b", "b", "b" };

		// 1.3 直接赋值
		String[] array3 = { "a", "b", "b", "b", "b" };
	}

	private void assign() {
		for (int i = 0, len = array.length; i < len; i++) {
			array[i] = "" + i;
		}
	}
	
	/**
	 * 访问数组
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
	 * 二维数组
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
	//有一个用户名,需要判断是否合法:
	/*1. 用户名不能为空,是空,则不合法
	 * 2. 用户名不能大于 10位
	 * 3. 用户名必须以 "u"开头 
	 * 4. 用户名不能带"?"
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
	
	/**代码优化     <代码大全> : 讲代码优化
	 * @param name
	 * @return
	 */
	private boolean goodCodeCheckName(String name) {
		if (name == null) {//这个条件比较特别,所以要放到第一位
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
