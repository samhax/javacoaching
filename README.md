Java Coaching
==========

Spring project full of Java goodness 


This ---Spring+ MVC + Security + JDBC--- application was built using :
Spring Tool Suite 

Version: 3.7.0.RELEASE
Build Id: 201506290649
Platform: Eclipse Luna SR2 (4.4.2)


Please refer to POM.XML for dependencies. 

--------------------------------------------------------------
INSTALLATION INSTRUCTIONS
----

1. Install MYSQL Database. Please use: /script/mysql.sql  
This file contains queries to create required tables under the "test" database.

2. Insert records to mysql. Please use :  /script/mysql.sql  AND /
Sample inserts can be found. 
Additionally, I created a java main class that helped me create thousands of insert statements. 

3. Build and deploy the application to local tomcat: http://localhost:8080/SpringSecurity/

---------------------------------------------------------------

Missing Stuff
-----
Here is a list of things I had no time to implement:
1. More CSS stylings. Also my CSS is included in the jsp
2. JAVA DOC. Had no time to properly document code.
3. Proper exception handling.
4. Optimize code. 
5. All rest request methods are GETS. Had some problems with POST methods.
6. Field validations. Only added very few ones. 
