UI tests for the Stellar Burgers app.

Used technologies: Java 11.0.26, Maven 3.9.9, JUnit 4.13.2, Selenium 4.31.0, Allure 2.29.1.

Run tests:

````
mvn clean test 
````

Generate Allure report:

````
allure generate target/allure-results --clean -o target/allure-report
````