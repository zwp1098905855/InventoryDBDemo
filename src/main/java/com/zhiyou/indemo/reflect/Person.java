package com.zhiyou.indemo.reflect;
public class Person {
	
	private String name;
	private String gender;
	private String age;
	private String adress;
	
	/**
	 * 
	 */
	public Person() {
		super();
	}
	/**
	 * @param name
	 * @param gender
	 * @param age
	 * @param adress
	 */
	public Person(String age, String adress) {
		super();
		this.age = age;
		this.adress = adress;
	}
	/**
	 * @return the name
	 */
	private String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	private void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the gender
	 */
	private String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	private void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the age
	 */
	private String getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	private void setAge(String age) {
		this.age = age;
	}
	/**
	 * @return the adress
	 */
	private String getAdress() {
		return adress;
	}
	/**
	 * @param adress the adress to set
	 */
	private void setAdress(String adress) {
		this.adress = adress;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		show("", 1);
		show("", new Integer(1));
		return "Person [name=" + name + ", gender=" + gender + ", age=" + age + ", adress=" + adress + "]";
	}

	
	private void show(String string,int num) {
		System.out.println(string+" :  "+num);
	}
	private void show(String string, Integer num) {
		System.out.println("===========");
		System.out.println(string+" :   "+num);
	}
	
	
}
