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

# Accessing Server Endpoints
A full list of the server endpoints can be found by starting the server and navigating to http://localhost:8080/swagger-ui.html.

From you can you register a new account with the server using the /account/register endpoint and authenticate with the server using your newly created details.

You can register a new personal or business account. If you have a business account, you can use the transaction/createforaccount to submit debits and checks for accounts that are not yours.

To get a token, use the /authentication/signin endpoint, submit your account number and pin, and you will be returned a bearer token.

To authenticate to make use of all endpoints, click on the "Authorize" button at the top right of the Swagger UI and type "Bearer #token#" where #token# is the value of your bearer token e.g. Bearer eyJhbGciOiJIUzI1NiJ9...

When authenticated, you should be able to make use of all other endpoints. 
