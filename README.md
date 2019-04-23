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
		method = POST   url = http://localhost:8080/turvo/customers/


			### while creating customer we should provide request body with cusromer details  as follows
				{
					"customerid": "3",
					"name":"vikram",
					"phno": "931225779",
					"address":"hyderabad",
					"servicetype":"prime"
				}

	2. For Updaing Customer	
		method = PUT   url = http://localhost:8080/turvo/customers/{customerId}

	3. For Getting all Customers	
		method = GET   url = http://localhost:8080/turvo/customers 

	4. For Getting individual Customer information
		method = GET   url = http://localhost:8080/turvo/customers/{customerId}


## TokenController

	1. For creating Token
		method = POST   url = http://localhost:8080/turvo/tokens 


				### while assigning a token to customer we should provide request body with customerid and serviceid as follows

					{
						"customerid": "3",
						"serviceid": "2"
					}

	
	2. For Updating Token by marking it as complete or cancel
		method = PUT  url = http://localhost:8080/turvo/tokens/{tokenId}

				### while marking token as complete we should provide request body with tokenid and status as follows

					{
						"tokenid": "3",
						"status": "complete|cancel|progress"
					}
	
	4. For Getting All Tokens
		method = GET   url = http://localhost:8080/turvo/tokens 
	
	
	5. For Getting Individual Token information
		method = GET   url = http://localhost:8080/turvo/tokens/{tokenId} 

	 
## CounterController
	 
	1. For Getting All Counters information
		method = GET   url = http://localhost:8080/turvo/counters 

	2. For Getting Individual Counter information
		method = GET   url = http://localhost:8080/turvo/counters/{counterid}
		
## A Small Description how tokens will be assigned

First it will check counters based on the customer type and then check for the availability of the counters based on queue size and allocate the respective counter to the customer
I have limited each counter queue size as 5 . If Counter Queue full with 5 members then it will check the next repective counter and allocate to the customer

