package com.qa.ims.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.utils.DBUtils;
import com.qa.ims.utils.Utils;

/**
 * Takes in order details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

		public static final Logger LOGGER = LogManager.getLogger();

		private OrderDAO orderDAO;
		private CustomerDAO customerDAO;
		private CustomerController customerController;
		private Utils utils;

		public OrderController(OrderDAO orderDAO, Utils utils) {
			super();
			this.orderDAO = orderDAO;
			this.utils = utils;
		}

		/**
		 * Reads all orders to the logger
		 */
		@Override
		public List<Order> readAll() {
			List<Order> orders = orderDAO.readAll();
			for (Order order : orders) {
				LOGGER.info(order);
			}
			return orders;
		}

		/**
		 * Creates an order by taking in user input
		 */
		@Override
		public Order create() {
			// Will display a list of valid customers of the user to select

			LOGGER.info("Please select a customer by entering the customer id");

			HashMap<Long, Customer> customers = getCustomers();
			Long customerId = validateCustomerId(customers);
			
			HashMap<Long, Item> items = getItems();
			Long itemId = validateItemId(items);
			

			LOGGER.info("Please enter a year");
			String year = utils.getString();
			LOGGER.info("Please enter a number for a month (i.e. 1 for January, 2 for February etc.)");
			String month = utils.getString();
			LOGGER.info("Please enter a day");
			String day = utils.getString();
			String date = year + "-" + month + "-" + day;
			LOGGER.info("Please enter a total");
			float total = Float.valueOf(utils.getString()).floatValue();
			Order order = orderDAO.create(new Order(customerId, itemId, date, total));
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime dt = LocalDateTime.parse(order.getDate(), formatter);
			formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
			String dat = dt.format(formatter);
			
			
			LOGGER.info("Order created");
			LOGGER.info("This is Your new Order - ID:" + order.getId() + " CUSTOMER:" + customers.get(customerId).getName() + " ITEM: " + items.get(itemId).getName() + " DATE: " + dat + " TOTAL: £" + String.format("%6.2f", Float.valueOf(order.getTotal())));
			return order;
		}

		/**
		 * Updates an existing order by taking in user input
		 */
		@Override
		public Order update() {
			LOGGER.info("Please enter the id of the Order you would like to update");
			Long id = utils.getLong();
			
			HashMap<Long, Customer> customers = new HashMap<>();
			HashMap<Long, Item> items = new HashMap<>();
			Long customerId = 0l;
			Long itemId = 0l;
			

			
			LOGGER.info("What field would you like to update?");
			LOGGER.info("Enter d (for date), t (for total), c (for customer) of i (for item)");
			String response = utils.getString().toLowerCase().substring(0,1);
			String upd = "";
			String date = "";
			float total = 0;
			
			String[] validInput = {"d", "t", "c", "i"};
			
			if ( Arrays.asList(validInput).contains(response) )
			{
				if (response.equals("c"))
				{
					upd = "c";
					customers = getCustomers();
					customerId = validateCustomerId(customers);
					
					System.out.println("After validation customer id");

				}
				else if (response.equals("i"))
				{
					upd = "i";
					
					items = getItems();
					itemId = validateItemId(items);
				}
				else if (response.equals("d"))
				{
					upd = "d";
					LOGGER.info("Please enter a year");
					String year = utils.getString();
					LOGGER.info("Please enter a number for a month (i.e. 1-January, 2-February etc.)");
					String month = utils.getString();
					LOGGER.info("Please enter a day");
					String day = utils.getString();
					date = year + "-" + month + "-" + day;				
				}
				else
				{
					upd = "t";
					LOGGER.info("Please enter a total");
					total = Float.valueOf(utils.getString()).floatValue();
					
				}
			}
			else
			{
				LOGGER.info("Plese enter a valid value");
			}

			Order o = new Order(id, customerId, itemId, date, total);
			o.setUpdate(upd);
			Order order = orderDAO.update(o);	
			
			System.out.println("After setting update");
						
			String customerName = getCustomerName(id);
			String itemName = getItemName(id);
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime dt = LocalDateTime.parse(order.getDate(), formatter);
			formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
			String dat = dt.format(formatter);
			

			
			LOGGER.info("Order Updated");
			LOGGER.info("New Order - ID: " + order.getId() + " CUSTOMER: " + customerName + " ITEM: " + itemName + " DATE: " + dat + " TOTAL: £" + String.format("%6.2f", Float.valueOf(order.getTotal())));

			return order;
		}

		/**
		 * Deletes an existing order using the order id
		 * 
		 * @return
		 */
		@Override
		public int delete() {
			LOGGER.info("Please enter the id of the order you would like to delete");
			Long id = utils.getLong();
			return orderDAO.delete(id);
		}
		
		/**
		 * Get a list of customers from the customer table
		 * 
		 * @return
		 */
		public HashMap<Long, Customer> getCustomers() 
		{
			HashMap<Long, Customer> customers = new HashMap<>();
		
			try (Connection connection = DBUtils.getInstance().getConnection();
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");) {
				while (resultSet.next()) {
					Long id = resultSet.getLong("id");
					String firstName = resultSet.getString("first_name");
					String surname = resultSet.getString("surname");
					customers.put(id, new Customer(id, firstName, surname));
				}
			} catch (SQLException e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}
			
			return customers;
		}
		
		/**
		 * Get customer id from the user and then validate the id
		 * 
		 * @return
		 */
		
		public Long validateCustomerId(HashMap<Long, Customer> customers)
		{
			Boolean selectingCustomer = true;
			int id = 0;
			int customerTotal = customers.size();
			
			while (selectingCustomer)
			{
				
				for (HashMap.Entry<Long, Customer> customer : customers.entrySet()) 
				{
					LOGGER.info("id: " + customer.getKey() + " Name: " + customer.getValue().getName());
				}
				
				String response = utils.getString();
				Boolean responseNotNumeric = false;
				
		        for (int i = 0; i < response.length(); i++) 
		        {
		            if (!Character.isDigit(response.charAt(i)))
		            {
		            	responseNotNumeric = true;
		                break;
		            }
		        }
		        
		        if (responseNotNumeric)
		        {
		        	LOGGER.info("Please enter a valid customer id");
		        }
		        else
		        {
		        	id = Integer.parseInt(response);
		        	
					if (id <= 0 || id > customerTotal)
					{
						LOGGER.info("Please enter a valid customer id");
					}
					else
					{
						selectingCustomer = false;
					}
		        }
			}
			
			return Long.valueOf(id);
		}
		
		/**
		 * Get a list of items from the items table
		 * 
		 * @return
		 */
		
		public HashMap<Long, Item> getItems() 
		{
			HashMap<Long, Item> items = new HashMap<>();
		
			try (Connection connection = DBUtils.getInstance().getConnection();
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("SELECT * FROM items");) {
				while (resultSet.next()) {
					Long id = resultSet.getLong("id");
					String name = resultSet.getString("name");
					String descr = resultSet.getString("descr");
					float price = resultSet.getFloat("price");
					int stock = resultSet.getInt("stock");
					items.put(id, new Item(id, name, descr, price, stock));
				}
			} catch (SQLException e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}
			
			return items;
		}
		
		/**
		 * Get item id from the user and then validate the id
		 * 
		 * @return
		 */
		
		public Long validateItemId(HashMap<Long, Item> items)
		{
			List<Long> ids = new ArrayList<>();
			Boolean selectingItem = true;
			Long id = 0l;
			
			while (selectingItem)
			{
				
				for (HashMap.Entry<Long, Item> item : items.entrySet()) 
				{
					if (item.getValue().getStock() != 0)
					{	
						ids.add(item.getKey());
						LOGGER.info("id: " + item.getKey() + " Name: " + item.getValue().getName() + " Price: " + item.getValue().getPrice());
					}
				}
				
				String response = utils.getString();
				Boolean responseNotNumeric = false;
				
		        for (int i = 0; i < response.length(); i++) 
		        {
		            if (!Character.isDigit(response.charAt(i)))
		            {
		            	responseNotNumeric = true;
		                break;
		            }
		        }
		        
		        if (responseNotNumeric)
		        {
		        	LOGGER.info("Please enter a valid item id");
		        }
		        else
		        {
		        	id = Long.parseLong(response);
		        	
		        	if (ids.contains(id))
		        	{
		        		selectingItem = false;
		        	}
		        	else
		        	{
		        		LOGGER.info("Please enter a valid item id");
		        	}
		        }
			}
			
			return Long.valueOf(id);
		}
		
		/**
		 * Get the customer name using the customer id from the orders table
		 * The input parameter is the order id
		 * 
		 * @return
		 */
		
		public String getCustomerName(Long id) 
		{
			String name = "";
			
			try (Connection connection = DBUtils.getInstance().getConnection();
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("SELECT concat(c.first_name, ' ', c.surname) name FROM orders o, customers c where o.id = " + id + " and c.id = o.customer;");) {
				while (resultSet.next()) {
					name = resultSet.getString("name");
				}
			} catch (SQLException e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}
			
			return name;
		}
		
		/**
		 * Get the item name using the item id from the orders table
		 * The input parameter is the order id
		 * 
		 * @return
		 */
		
		public String getItemName(Long id) 
		{
			String name = "";
			
			try (Connection connection = DBUtils.getInstance().getConnection();
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("SELECT i.name name FROM orders o, items i where o.id = " + id + " and i.id = o.item;");) {
				while (resultSet.next()) {
					name = resultSet.getString("name");
				}
			} catch (SQLException e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}
			return name;
		}
}
