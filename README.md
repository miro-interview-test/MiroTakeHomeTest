# MIRO TAKE HOME TEST
Contains Cucumber tests written using Java and JUnit, build using Maven and executed on Google Chrome browser. 

## Overview  

Behavior Driven scenarios for Miro Sticker Share functionality.    

Selenium-WebDriver is used as a backend tool for driving the browsers.     
While selenium supports a large plethora of browsers, this setup only supports Google Chrome by default.    

This setup runs following scenario:    
1. Login to Miro using pre-registered user1    
2. Delete any previously created boards    
3. Create new board     
4. rename it to "Board"   
5. Share it with a pre-registered user2    
6. Add a sticker from toolbar    
7. Take a screenshot for later comparison    
8. Logout from user1 and login to user2    
9. Open board "Board"    
10. Take screenshot of the board    
11. Perform a pixel to pixel comparison of 2 screenshots    
12. Test passes if 2 images has variance less than 0.5%    

## Requirements    
### Local Execution
1. Java 16 (can be verified using java -version)    
2. Maven 3.8.1 (can be verified using mvn -v)    
3. Google Chrome Browser
4. Chromedriver available on PATH (compatible with installed Chrome version)
    
**Note: To avoid installing all these above dependencies on your local machine, use docker based execution.**
    
### Docker execution    
1. Docker    
    
## Setup    
1. Install all above local execution or docker execution or both on your system   
2. Clone this repository    
..and that is it, we are good to go..    
All other dependencies will be fetched and installed by maven..     
       
## Execution        
There are 2 ways to execute the tests here. 
### Option 1 (recommended):    
```bash run.sh```    
and follow the steps in the file.    
It will ask you type of execution where you can interactively select an option.   
    
### Option 2   
This is where you can execute the tests using maven or docker commands.    
#### a) Local execution with UI
Here the tests will run with UI where Google Chrome browser will launch.    
You can see the execution real time.   
```
export DOCKER=false
export HEADLESS=false
mvn clean test
```
#### b) Local execution with UI
Here the tests will run on headless mode where Google Chrome browser will launch.    
You cannot see the execution real time as its running in the memory.   
```
export DOCKER=false
export HEADLESS=true
mvn clean test
```
#### c) Docker based execution
Here the tests will run on a docker container.    
You cannot see the execution real time as it is running inside a container and is headless.
```
docker pull markhobson/maven-chrome:jdk-16  #only needed once
docker run --rm -e DOCKER=true -it -v "$PWD":/usr/src -w /usr/src markhobson/maven-chrome:jdk-16 mvn clean test;
```    
    
## Reporting     
There are 2 reports created from this execution.        
### a) Default Cucumber HTML/JSON reports     
Cucumber provides an option to create basic html report when executing tests.     
These reports are by default created in the target directory.    
Filename for these reports are as follows:    
```
miro_report.html
miro_report.json
```     
The json file is not easily readable for obvious reasons but the html file can be opened in any browser.   
Additionally, if the test fails, a screenshot will be attached with the step where the test filed for efficient reporting and debugging.    
    
### b) Maven Cucumber Reporting     
This is an additional plugin/dependecy added into the pom file to create more readable reports for better readability.    
This is completely optional and tests can run without it too.    

**Note: There are other 3rd party cucumber reporting libraries which can be used depending on which give the tech and product team better readability and faster debugging, improving overall efficiency.**     

### c) Screenshots    
Apart from reports, the original screenshots that are taken to compare can be found in resources/screenshots folder.    
These are deleted before each run for a fresh pair to be created.    
    

