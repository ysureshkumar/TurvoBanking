# Turvo​Banking

# Prerequisites

	- Java 8
	- Maven > 3.0
	- MySQL

# Configurations

Open the `application.properties` file and set the MySQL configuration.

# Schema installation

-connect to mysql
-CREATE DATABASE `turvobanking` 
-Run folllowing command
-source /home/sureshy/Downloads/install_schema.sql

Run the file install_schema.sql present in src/main/resources to set up the database. Following tables would be created:

- customer
- counter
- service
- token
- user

#ER Diagram

work in progress

#Class Diagram

work in progress


# Importing  the Project (use Eclipse or any other compatibile Editor)

	- Import as *Existing Maven Project* and run it as *Spring Boot App*.


# How to Use

	- Run the application and go on http://localhost:8080/

# Controller

## HomeController
    	method = GET   url = http://localhost:8080/ 

## CustomerController

	1. For Creating Customer
		method = POST   url = http://localhost:8080/turvo/customers/createcustomer 


			### while creating customer we should provide request body with cusromer details  as follows
				{
					"customerid": "3",
					"name":"vikram",
					"phno": "931225779",
					"address":"hyderabad",
					"servicetype":"prime"
				}

	2. For Updaing Customer	
		method = POST   url = http://localhost:8080/turvo/customers/updatecustomer/{customerId}

	3. For Getting all Customers	
		method = GET   url = http://localhost:8080/turvo/customers/getallcustomers 

	4. For Getting one Customer information
		method = GET   url = http://localhost:8080/turvo/customers

## TokenController

	1. For creating Token
		method = POST   url = http://localhost:8080/turvo/tokens/createtoken 


				### while assigning a token to customer we should provide request body with customerid nad serviceid as follows

					{
						"customerid": "3",
						"serviceid": "2"
					}

	
	2. For Updating Token by marking it as complete
		method = PUT  url = http://localhost:8080/turvo/tokens/marktokenascomplete/{tokenId}
	
	3. For Updating Token by marking it as cancel
		method = PUT   url = http://localhost:8080/turvo/tokens/marktokenascancel/{tokenId} 
	
	4. For Getting All Tokens
		method = GET   url = http://localhost:8080/turvo/tokens/getalltokens 
	
	5. For Getting One Token information
		method = GET   url = http://localhost:8080/turvo/tokens/gettoken/{tokenId} 
	 
## CounterController
	1. For Getting One Counter information
		method = GET   url = http://localhost:8080/turvo/counters/getcounter/{counterid} 
	2. For Getting All Counters information
		method = GET   url = http://localhost:8080/turvo/counters/getallcounters 

## A Small Description how tokens will be assigned

First it will check counters based on the customer type and then check for the availability of the counters based on queue size and allot the respective counter to customer
I have limited each counter queue size as 5 . If Counter Queue full with 5 mins then it will check the next repective counter and allot the counter.

