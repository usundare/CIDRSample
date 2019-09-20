# CIDRSample

Spring Boot Application with h2 database
Edit db path in application.properties 

REST APIs 

POST - To add cidr and populate
  http://localhost:8080/cidr
    request param: cidrAddress

GET - List of IPs
  http://localhost:8080/ipList
  
PUT - To accquire and release IP 
  http://localhost:8080/acquire
  http://localhost:8080/release
      request param: ipAddress
      
      
