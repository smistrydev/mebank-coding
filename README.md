# Transaction Record Balance Application

This application takes input of a parameter which are used to load a transaction file and then scan to print account balance and number of transaction valid for period,

The application is a Java 8 application build with Gradle. The source code for the application available on GitHub.

## Getting Started
The location of source code on GitHub: https://github.com/smistrydev/mebank-coding.git

### Prerequisites
In order to build this application, you will need Java 8 installed as well as Gradle. I will assume that you are well versed with these technologies and have them available on your command terminal. Or you may use your favourite IDE which is capable of download a Java/Gradle project. 

### Installing

A step by step series of examples that tell you how to download, build, test and run using command line. If you are using the IDE directly, you will need to replace the appropriate value mentioned below.

To download on command line at your development path execute below git command: 
```
git clone https://github.com/smistrydev/mebank-coding.git
```
The Gradle script file "gradlew" may need to be made executable by using below command
```
chmod +X gradlew
```
Below command will build, test and run the project.
```
/gradlew clean test run -PappArgs="src/main/resources/sample_input.csv,ACC334455,20/10/2018 12:00:00,20/10/2018 19:00:00"
```
The above is using input from '-PappArgs' parameter for the application to run on command line. The input values are in 4 parts with are separated by comma. 
```
part 1: location of input csv file. See sample file src/main/resources/sample_input.csv
part 2: AccountId used for scanning the file
part 3 & 4: Date FROM and TO parameters in the format 'dd/MM/yyyy HH:mm:ss'
```
The output will should look like below:
```
Initialization complete
Relative balance for the period is: -$25.00
Number of transactions included is: 1
```

## Running the tests

The application already comes with JUnit test which at located in the folder: src/test/java. They will get executed as part of the build process mentioned above,

In order to run your initialisation file and account scanning, you may adjust the input accordingly.

## Application Design

Below are my choices in the application design.

 - The domain object *TransactionRecord* represents the transaction record  from the csv file.
 - The domain object *AccountBalance* represents the scanning parameters and results.
 - The project structure is as follows:
	 - domain - this is where domain object are kept.
	 - process - this is individual features are kept.
	 - application - this is where features implementation are abstracted.
	 - util - commonly used utilities.
	 - run - this is where the main execution is started.
 - All the JUnit test classes are in folder: src/test/java
 - When the application is initialised, the csv file is stream as line by line and each line is mapped to the *TransactionRecord* and cached in as a list for later scanning.
 - The scanning is done in steps described below:
	 - the list is scan and filtered for *accountId*  in either the *fromAccountId* or *toAccountId* fields.
	 - if the *accountId* is in *fromAccountId* field, then the amount is made negative.
	 - the filtered list is scanned again get all reversed payment transactionId.
	 - the filtered list is re-filtered will only payment record minus the reversed records.
	 - the amounts are summed and total records updated in *AccountBalance*
 - Resulting *AccountBalance* values is used for printing. 

The application software is not using any persistence or advanced framework for keeping the input file in memory. Therefore below are some of the disadvantages of this solutions.

 - large input file can result in slow performance and also could result in out of memory.
 - for every scan required the application has to be re-initialised resulting in unnecessary IOs.
 - the scanning and filtering is being done in few steps, this could be reduce with using advanced frameworks which may allow complex joint scans.
 - the software is not able to use data relationships to optimise scanning.  

The application has been designed such that with minimal work, this application can be upgraded to expose as API by exposing *TransactionBalanceApplication*functionality with rest controller.    

## Authors

* **Sanjay Mistry** 
