# ThinkLink
BTC Monitoring Project

# Description
it is a a web app for monitoring BTC price and storing data into DB

## Technology Stack
Framework: Spring-Boot
Build Tool: Maven 
Language: Java 8
Databse: Sqlite DB 
mailing Service: Mailtrap SMTP service

### How To Use
Following are the steps to Run this Project

1. Clone Project using git clone https://github.com/nitin0939/ThinkLink.git
2. cd ThinkLink/
3. Run Project using command: docker compose up 

a scheduler is running to fetch BTC Data and inserting into SqliteDB Table named: data

4. Following is the Curl API for fetching Pagination Result from table data
 curl --location --request GET 'http://localhost:3030/api/prices/btc?date=01-04-2022&offset=5&limit=10' \
--header 'Cookie: JSESSIONID.ab03fc61=node05oa9ntpvhw5eld1glc4sp1uw0.node0'

 5. a min and max value is defined in .env file 
 6. a mail is triggered when BTC price goes below the min value or goes above the max value
 7. a key name 'TO'(email receiver) and 'FROM'(email sender) is used in .env file
 8. if any value is changed in .env file then re build and Re-Run the project using following Command
 to stop the container: docker compose down
 to rebuild the Peoject: docker compose up --build --force-recreate


