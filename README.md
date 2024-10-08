# Appium Hybrid Testing Framework with TestNG and Extent Reports

## Overview
This project utilizes Appium, TestNG, and Extent Reports to provide a robust automated testing framework for both Android and iOS platforms.

## Prerequisites
- Java 17 or higher
- Maven 3.6v or higher
- Appium 9.2.0v
- TestNG 7.5v or higher
- Selenium-support 4.22.0v or higher
- Extent reports 5.0v or higher
- Node.js: Required for Appium

## How to Run
In the pom.xml, we have configured 3 Maven profiles: regression, smoke, and failed-tests. These profiles allow you to execute specific sets of tests based on the scenario. You can run any of these profiles using Maven commands as described below.

### Build the Project
To build the project and download all dependencies, run the following Maven command:
```bash
mvn clean install

### To run the regression suite, use the following command:
```bash
mvn clean test -PRegression

### To run the smoke suite, use the following command:
```bash
mvn clean test -PSmoke

### To re-run failed tests, use the following command:
```bash
mvn clean test -PfailedTests

