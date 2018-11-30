=================================		PROBLEM STATEMENT		======================================



Each kingdom has an animal emblem and Shan needs to send a message with the animal in the message to win them over.
LAND emblem - Panda, WATER emblem - Octopus, ICE emblem - Mammoth, AIR emblem - Owl, FIRE emblem - Dragon.

Your coding challenge is to have King Shan send secret message to each kingdom and win them over.
Once he wins 3 more kingdoms, he is the ruler! The secret message needs to contain the letters of the animal in their
emblem. For example, secret message to the Land kingdom (emblem: Panda) needs to have the letter 'p','n','d' at-
least once and 'a' at-least twice. If he sends "a1d22n333a4444p" to the Land kingdom, he will win them over.

problem 1- sample input output

Who is the ruler of Southeros?
Ouput: None
Allies of Ruler?
Output: None

Input Messages to kingdoms from King Shan:
Input: Air, “oaaawaala”
Input: Land, “a1d22n333a4444p”
Input: Ice, “zmzmzmzaztzozh”

Who is the ruler of Southeros?
Output: King Shan
Allies of Ruler?
Output: Air, Land, Ice

=================================================

Who is the ruler of Southeros?
Output: None
Allies of King Shan?
Output: None

Input Messages to kingdoms from King Shan:
Input: Air, “Let’s swing the sword together”
Input: Land, “Die or play the tame of thrones”
Input: Ice, “Ahoy! Fight for me with men and money”
Input: Water, “Summer is coming”
Input: Fire, “Drag on Martin!”

Who is the ruler of Southeros?
Output: King Shan
Allies of King Shan?
Output: Air, Land, Ice, Fire

======================================================

=================================		SOLUTION		======================================


** How to build it?
This project needs *Java 8* for building and running. The project uses Maven. There are no dependencies on the project 
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

