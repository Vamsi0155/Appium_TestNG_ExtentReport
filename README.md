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
```

### To run the regression suite, use the following command:
```bash
mvn clean test -PRegression
```

### To run the smoke suite, use the following command:
```bash
mvn clean test -PSmoke
```

### To re-run failed tests, use the following command:
```bash
mvn clean test -PfailedTests
```
### To run any XML file, use the following command:
```bash
mvn clean test -DsuiteXmlFile=master.xml
```

## BrowserStack Integration
1. open A/c in BrowserStack (100min for free trial).
2. Install BrowserStack plug-in in the Eclipse/Intellij.
3. Important note,
 - Appium client must be up to date (latest version).
 - TestNG 7.0v or higher.
4. Convert to the BrowserStack compatible.
 - Right click on project
 - choose BrowserStack
 - select "Integrate with App Automate SDK"
 - enter project name & build name.
 - enter user name (find in BrowserStack dashboard)
 - enter access key (find in BrowserStack dashboard)
 - enter app (Initially, you can upload your app into the BrowserStack and will generate it. find in Browser stack dashboard).
 - click on "Integrate".
 - wait for Build success and refresh the project.
 - "browserstack.yml" file will be generated.
5. In YML file,
 - Verify user name, access key, and app path.
 - Go to Platforms section, you will get few devices by default. Add same way
 - Set "ParallelsPerPlatform"

### To run the tests on browser stack, use the following command:
```bash
mvn clean test -PRegression
```

### To run tests in local, use the following command:
Note: Set as false after converting the project into BrowserStack.
```bash
BROWSERSTACK_AUTOMATION=false mvn clean test -PRegression
```
