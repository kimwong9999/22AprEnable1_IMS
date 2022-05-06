package com.qa.ims.persistence.domain;

public class Order {
	
	private long id;
	private long customer;
	private long item;
	private String date;
	private float total;
	private String update;
	
	public Order(long customer, long item, String date, float total) {
		this.setCustomer(customer);
		this.setItem(item);
		this.setDate(date);
		this.setTotal(total);
	}
	
	public Order(long id, long customer, long item, String date, float total) {
		this.setId(id);
		this.setCustomer(customer);
		this.setItem(item);
		this.setDate(date);
		this.setTotal(total);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCustomer() {
		return customer;
	}
	public void setCustomer(long customer) {
		this.customer = customer;
	}
	public long getItem() {
		return item;
	}
	public void setItem(long item) {
		this.item = item;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + ", item=" + item + ", date=" + date + ", total=" + total
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (customer ^ (customer >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (item ^ (item >>> 32));
		result = prime * result + Float.floatToIntBits(total);
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
		Order other = (Order) obj;
		if (customer != other.customer)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (item != other.item)
			return false;
		if (Float.floatToIntBits(total) != Float.floatToIntBits(other.total))
			return false;
		return true;
	}
	
	

}
