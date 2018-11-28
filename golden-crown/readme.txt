** How to build it?
This project needs Java 8 for building and running. The project uses Maven. There are no dependencies on the project 
apart from JUnit and Mockito, both with scope "test". I am using the Maven Jar plugin to create an executable jar.
Use the below command to create an executable jar:

	mvn clean package
	
** How to run it?
Once the Maven build is successful, you can simply use the below command to start it:
	java -jar target/golden-crown-1.0.jar
	
Start typing on the command prompt

** Tests included
A number of Unit Tests are included in the standard src/test/java folder.
A single integration test GoldenCrownIntegrationTest is included. This reads the some given input file, 
simulating user input, captures the output and compares it with another text file. The test data is included in 
src/test/resources/goldencrown/testdata/*	

