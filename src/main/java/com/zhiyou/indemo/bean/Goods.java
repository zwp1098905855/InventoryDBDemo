/**
 * 
 */
package com.zhiyou.indemo.bean;

/**
 * @author longWH 产品类
 */
public class Goods {
	private String id;
	private String name;
	private float price;
	/**
	 * 产品数量
	 */
	private int amount;
	/**
	 * 品牌
	 */
	private String brand;
	
	/**
	 * 
	 */
	public Goods() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param price
	 * @param amount
	 * @param brand
	 */
	public Goods(String id, String name, float price, int amount, String brand) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.brand = brand;
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
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", price=" + price + ", amount=" + amount + ", brand=" + brand
				+ "]";
	}
	
	
	
}
