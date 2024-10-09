# Appium Hybrid Testing Framework with TestNG and Extent Reports

## Overview
This repository contains a hybrid automation framework built with Appium for testing Android and iOS applications. The framework integrates TestNG for test execution and Extent Reports for rich, interactive test reporting. It is designed to support both platforms efficiently while maintaining a modular and scalable architecture.
### Key Features
#### Cross-Platform Testing:
- Supports both Android and iOS platforms.
#### Supports Hybrid, Native, and Web Apps:
- The framework is flexible enough to test different types of applications across mobile platforms.
#### Parallel Execution:
- Runs multiple tests concurrently to reduce execution time.
#### TestNG Integration:
- Provides powerful test management, including annotations, grouping, and parallel execution.
#### Extent Reports:
- Generates visually appealing and detailed test reports with screenshots, logs, and results.
#### Page Object Model (POM):
- Ensures reusability and maintainability by structuring the code in a clear and modular format.
#### Data-Driven Testing:
- Supports parameterized tests with different data sets to enhance test coverage.

## Prerequisites
- Java 17 or higher
- Maven 3.6v or higher
- Appium 9.2.0v
- TestNG 7.5v or higher
- Selenium-support 4.22.0v or higher
- Extent reports 5.0v or higher
- Node.js: Required for Appium-Server
- Appium-Inspector
- Android Studio (for Android)
- Xcode (for iOS)

## How to run from cmd line
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

## Jenkins CI/CD

#### General Section:
 Select "This project is parameterized" and set below parameters.
1. Set 2 choice parameters and those are,
 - Profile
 - Browser Stack
2. For 1st parameter, set below details:
 - Name: Profile
 - Choices: Regression, Smoke, Failed-tests
 - Description:
3. For 2nd parameter set below details:
 - Name: Browser Stack
 - Choices: True, False
 - Description:
#### Build Section:
1. choose "add build step" as a "Invoke top-level maven targets"
2. Set Goals as below command:
```bash
BROWSERSTACK_AUTOMATION={$Browser_Stack) clean test -P{$Profile}
```
Note: Add project path in the custom workspace field and Also remainings as per requirements.
