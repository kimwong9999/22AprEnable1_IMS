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

### Integration Tests 
Explain what these tests test, why and how to run them

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

Kim Wong 
Git Hub: https://github.com/kimwong9999/22AprEnable1_IMS   
Jira: https://kimwong.atlassian.net/

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
