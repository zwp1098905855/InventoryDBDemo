/**
 * 
 */
package com.zhiyou.indemo.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import com.sun.media.jfxmedia.Media;

/**
 * @author longWH
 *
 */
public class TestReflet {
	static Class<Person> cls = Person.class;

	public static void main(String[] args) {
		/*
		 * showFields(); System.out.println("=========="); showConstructors();
		 * System.out.println("==========方法==========="); showMethods();
		 */
		/* instance(); */

		// invokeMethod();
		// showFields();
		showConstructors();
	}

	private static void instance() {
		try {
			// 实例化一个类的对象
			Person person = cls.newInstance();
			Field[] fields = cls.getDeclaredFields();
			Object[] values = { "张三", "nan", "23", "黄土高坡" };
			for (int i = 0, len = fields.length; i < len; i++) {
				fields[i].setAccessible(true);
				fields[i].set(person, values[i]);
			}
			System.out.println(person.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void showFields() {
		try {
			Field[] fields = cls.getDeclaredFields();
			for (Field field : fields) {
				System.out.println(field.getName());
				System.out.println("-----");
				System.out.println(field.getType().getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void showConstructors() {
		Constructor<?>[] constructors = cls.getDeclaredConstructors();
		for (Constructor<?> constructor : constructors) {
			String consName = constructor.getName();
			System.out.println(consName);
			System.out.println("修饰符: " + constructor.getModifiers());
			Parameter[] parameters = constructor.getParameters();
			for (Parameter parameter : parameters) {
				System.out.println(consName + "的参数是: " + parameter + "  参数名是: " + parameter.getName());
			}
			System.out.println("------------------");

		}
	}

	private static void showMethods() {
		Method[] declaredMethods = cls.getDeclaredMethods();
		for (Method method : declaredMethods) {
			String mName = method.getName();
			System.out.println("方法名是: " + mName);
			Parameter[] parameters = method.getParameters();
			for (Parameter parameter : parameters) {
				System.out.println("参数是:" + parameter);
			}
			System.out.println("------------------");
		}
	}

	private static void invokeMethod() {
		Method[] methods = cls.getMethods();
		// for (Method method : methods) {
		// System.out.println("方法名: "+method.getName());
		// Class<?>[] types = method.getParameterTypes();
		// for (Class<?> paType : types) {
		// System.out.println("参数类型: "+paType.getName());
		// }
		// }

		try {
			Person person = cls.newInstance();
			Method method = cls.getDeclaredMethod("show", String.class, Integer.class);
			method.setAccessible(true);// 让他能去访问
			// 如果是基本类型则编译器会转换为包装类
			method.invoke(person, "张三", new Integer(1));// 这是一个坑,基本类型的参数要传入他们的包装类,
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
