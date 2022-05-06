package com.qa.ims.persistence.domain;

public class Item 
{
	private long id;
	private String name;
	private String descr;
	private float price;
	private int stock;
	
	public Item(String name, String descr, float price, int stock) {
		this.setName(name);
		this.setDescr(descr);
		this.setPrice(price);
		this.setStock(stock);
	}
	
	public Item(long id, String name, String descr, float price, int stock) {
		this.setId(id);
		this.setName(name);
		this.setDescr(descr);
		this.setPrice(price);
		this.setStock(stock);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return String.format("%-5s", id) + String.format("%-20s", name) + " " + String.format("%-40s", descr) + " £" + String.format("%7.2f", price) + " " + String.format("%5s", stock);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descr == null) ? 0 : descr.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + stock;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (descr == null) {
			if (other.descr != null)
				return false;
		} else if (!descr.equals(other.descr))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (stock != other.stock)
			return false;
		return true;
	}
	
}
