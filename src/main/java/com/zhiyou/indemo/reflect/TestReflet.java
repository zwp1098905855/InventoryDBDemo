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
		 * System.out.println("==========����==========="); showMethods();
		 */
		/* instance(); */

		// invokeMethod();
		// showFields();
		showConstructors();
	}

	private static void instance() {
		try {
			// ʵ����һ����Ķ���
			Person person = cls.newInstance();
			Field[] fields = cls.getDeclaredFields();
			Object[] values = { "����", "nan", "23", "��������" };
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
			System.out.println("���η�: " + constructor.getModifiers());
			Parameter[] parameters = constructor.getParameters();
			for (Parameter parameter : parameters) {
				System.out.println(consName + "�Ĳ�����: " + parameter + "  ��������: " + parameter.getName());
			}
			System.out.println("------------------");

		}
	}

	private static void showMethods() {
		Method[] declaredMethods = cls.getDeclaredMethods();
		for (Method method : declaredMethods) {
			String mName = method.getName();
			System.out.println("��������: " + mName);
			Parameter[] parameters = method.getParameters();
			for (Parameter parameter : parameters) {
				System.out.println("������:" + parameter);
			}
			System.out.println("------------------");
		}
	}

	private static void invokeMethod() {
		Method[] methods = cls.getMethods();
		// for (Method method : methods) {
		// System.out.println("������: "+method.getName());
		// Class<?>[] types = method.getParameterTypes();
		// for (Class<?> paType : types) {
		// System.out.println("��������: "+paType.getName());
		// }
		// }

		try {
			Person person = cls.newInstance();
			Method method = cls.getDeclaredMethod("show", String.class, Integer.class);
			method.setAccessible(true);// ������ȥ����
			// ����ǻ����������������ת��Ϊ��װ��
			method.invoke(person, "����", new Integer(1));// ����һ����,�������͵Ĳ���Ҫ�������ǵİ�װ��,
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
