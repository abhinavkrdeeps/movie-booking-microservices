# This Repo Contains three different microservices
1) Movie-catalogue-service -  This service is used for getting information about a particular movie.
2) Booking-service - This service is used for booking a movie ticket for a particular user and a movie.
3) Payment-service - This service is used for payment related
 
 
Booking-Service (/movies/book/{id})

Api Gateway (http://localhost/9090)

Movie Catalogue Service (/movies/{id})

Payment Service (/movies/book/{userId}/{price}/payment)

![image](https://user-images.githubusercontent.com/31301292/140693409-2f78cd4b-4395-40d6-be7d-c930dd07cc49.png)

Microservices Pattern Used
1) Spring-cloud-config - Used a git repo as a centralized configuartion for all the microservices

2) Circuit-Breaker - Used Resielience4j For implementing curcuit breaker pattern( Used ratelimiter, fallback, retry )

3) Spring-cloud-api-gateway - Implements api-gateway ang global logging filter

4) Eureka Naming Server
