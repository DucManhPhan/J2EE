
################ Exercise-Related Database Setup Instructions  ############################

Be sure you have installed a MySQL Community Edition Server, and that you have done the following:

	1. The database server is up and running on port 3306
	2. There is a scheme named:  "spring-tdd"
	3. There is a user with proper privileges granted to the "spring-tdd" scheme.  The exercises assume a usernmae of "tddshonna".  Feel free to change the actual username to suit your preferences.
	4. The user's password is set to "booth" or whatever value you prefer.

Finally, modify the application.properties files to have the correct database connection URL with accurate schema name and port number; plus correct username and password values too, e.g.:

	spring.datasource.url = jdbc:mysql://localhost:3306/spring-tdd
	spring.datasource.username = tddshonna
	spring.datasource.password = booth  



################ Additional MySQL Setup Resources  ############################

Detailed instructions for installing, starting/stopping, and creating users for MySQL can be found at the following site:

	https://dev.mysql.com/doc/refman/5.7/en/installing.html


Detailed instructions for using the MySQL Workbench to create and setup your first schema can be found at the following site:

	https://dev.mysql.com/doc/workbench/en/workbench-faq.html#faq-workbench-create-database




