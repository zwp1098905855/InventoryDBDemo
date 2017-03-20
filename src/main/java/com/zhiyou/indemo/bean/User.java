/**
 * 
 */
package com.zhiyou.indemo.bean;

/**
 * @author longWH
 *
 */
public class User {
	private String id;
	private String name;
	private String pwd;
	private String gender;
	
	/**
	 * 
	 */
	public User() {
		super();
	}
	/**
	 * @param id
	 * @param name
	 * @param pwd
	 * @param gender
	 */
	public User(String id, String name, String pwd, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.gender = gender;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}
	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
