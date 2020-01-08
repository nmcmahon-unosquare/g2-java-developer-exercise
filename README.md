# Running the Server
## With Docker
From the project root, build the docker image for the server using the following command

`docker build bank-server`

Then run the server using docker by running the following:

`docker run -v bank-server-maven-repo:/root/.m2 -p 8080:8080 bank-server`


## With IDE

The java version used with this project is Java 8 and the build/dependency management system is Maven. This can be ran by running the main method in com.niall.bankserver.Application.

# Running the client

When the server is running, run the client using your IDE or java command line with the below commands from the project root

`cd bank-client`

`mvn clean install -Dmaven.skip.tests=true`

`java -jar target/bank-client-1.0-SNAPSHOT-jar-with-dependencies.jar`

The client is a basic command line interface used to demonstrate some of the core server functionality. It will allow the user to follow a series of basic menus to select options related to the users account. With the client you can:-
* Register a new account
* Log in with an existing account
* Get a statement from your account with account details and the last 5 transactions
* Make a deposit
* Make a withdrawal
* Log out
