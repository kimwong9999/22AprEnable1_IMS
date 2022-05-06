Coverage: 34%
# Project Title

Inventory Management System
An application that an end user can interact with via a CLI, involves creating three tables (Customer, order, Items) allowing the user to read, add, update and delete records from these tables

## Getting Started

We were supplied a repository which we forked into our own databases. This held the code for a working program which was accessed via Eclipse. 

### Prerequisites

A PC. A git Hub account, Eclipse, Java, MySQL, a Jira account.

### Installing

Once Git was installed on the PC and a Git Hub account established online it was possible through GitHub to fork the supplied repositary to the online Git Hub account. Then this respositary could be downloaded to the PC via the Git Hub command 'clone'. Then the latest Java realeased is downloaded ont the PC as well was the lastest MYSQL software downloaded onto the PC. The locations on the PC of Java and MySQL could be added to the PC's PATH variable. 


All Git commands can be done through the Git Bash Terminal. 

Once Eclipse has been downloaded to the PC, when running for the first time it would be pointed to the same directory where the cloned GIT repository resides on the PC.


## Running the tests

As the Customer file and the software to manupilate the table already existes then all that needed to be done is the items and orders table. In the case of the items table all that needed was to copy the existing customer files (Customer.java, CustomerDAO.java and CustomerContoller.java) and change Customer to Items int the file names and code (making sure that things like 'customer' was changed to 'items' and not 'Items'. Then it was a matter of replacing all references to the customer columns to the item columns, making sure that the data type matched i.e 

for example part of the customerDAO.java looked lied this

						.prepareStatement("INSERT INTO customers(first_name, surname) VALUES (?, ?)");) {
			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getSurname());
      statement.executeUpdate();
      
 so for the itemsDAO file it would need to be changed into this
 
 						.prepareStatement("INSERT INTO items (name, descr, price, stock) VALUES (?, ?, ?, ?)");) {
			statement.setString(1, item.getName());
			statement.setString(2, item.getDescr());
			statement.setFloat(3, item.getPrice());
			statement.setInt(4, item.getStock());
			statement.executeUpdate();
      
 so there are more columns on the items table and not all of them are strings.
 
 
Testing was quite simple, when runing in Eclipse and menu appears asking the user which table they want, in this case it was the items table and when this has been selected another menu asking if the user want to read, create, upadte or delete from the table. Unii testing would be simply checking that the data the user entered was actually on the table. 
 

 
### Unit Tests 

The unit testing for the Customer and Items table was the same. Once the user has selected the table then they would be asked if they want to create, read, update or delete data the to the table.

For the items table the first thing would be to select CREATE to add an item to the items table. Once selected the user will be asked to add the relevalent data, which would be inserted inot the table. If no error message appeard then the data had been inputted into the database successfully. If the user would then select READ, then will display the new data (along with any other data that are on the items table) will be displayed in the console.  

So it would be 

CREATE - READ to check data is on the database

READ - to choose the item id that will be changed
UPDATE - to add the uupdated data using that item id
READ - to check that the correct row has been updated

READ - to choose the id to be deleted
DELETE - to delete the row using the id
READ - to check that the record has been deleted

JUNIT - Mokita

Created a test file itemDAOTest.java based on the customerDAOTest.java and ran it, all test passed succefully 

### Integration Tests 

The order file, as this required the customers and items table

These two tests were done manually

1. Delete the customers or/and the items table. This should not be possible as the orders table has foreign keys from those two tables.
2. Add a row to the orders table that has an invalid customer id (or an invalid items id) and it should refuse to add them

The second one was done manually because the program was written so that the user could only choose a valid customer (or item) id.

Using the program when selected ORDERS

1. add a row using CREATE and then use READ to look at the row
2. change the customer using the UPDATE - 'c' option and then use READ to check the data
3. change the item using the UPDATE - 'i' option and then use READ to check the data
4. change the date using the UPDATE - 'd' option and then use READ to check the data
5. change the total using the UPDATE - 't' option and then use READ to check the data

Using the program when selected ITEMS

	try to delete an item from the items table that is on the orders table - will refuse to do this

Using the program when selected CUISTOMERS

	try to delete a customer from the customers table that is on the orders table - will refuse to do this



## Authors

Kim Wong 
Git Hub: https://github.com/kimwong9999/22AprEnable1_IMS   
Jira: https://kimwong.atlassian.net/

