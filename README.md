# IntelProj

To Run The Application:

   1-Clone or download the project

   2-Run ” mvn clean spring-boot:run ” inside “saleh” folder

   4-The application is secured by Spring Security so you need setup the credentials in application.proprties  
   
To Test The Application: 

   Use curl linux command to send requests

   1- curl -H 'Authorization:Basic base64(user:password)' -H "Content-Type: application/json" -X POST -d '{"searchText":["Duis", "Sed",        "Donec", "Augue", "Pellentesque", "123"]}'    http://localhost:8080/counter-api/search
   
   2- curl -H 'Authorization:Basic base64(user:password)' http://localhost:8080/counter-api/top/5  -H "Accept: text/csv"


Notes:

   1- This is a Spring boot 1.4.3.RELEASE application
   
   2-I assumed unit testing is not part of the requirements 

