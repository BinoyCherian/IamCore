# IamCore

Built using servlets

The Project -
The project focusses on managing identities and is built surrounding the core idea to handle all operations to manipulate the list of Identities. Javadoc will be built into the project to increase the understandability and reusability. 

We have used the model, view and controller (MVC) model to structure the application. Possible operations to manipulate the Idenity are the create, read, update and delete. A seperate test class has been written in the test folder to test all the test methods. 

#Users -
The users are divided into two, The privileged users and the non-privileged users. The details of the both the types of users are maintained in different database tables. 

#Flow -
The privileged user needs to login to the application to be able to access, modify, delete or create new Identities. Hence the application is designed such that all operations are restricted only to the privileged users. The flow happens once the privileged user logs in, the control flows through the controller to the service and then to the dao layer to interact with the db and tip-toes back to the controller to make further judgements based on the value returned by the method.

#Logger -

The logger has been included into the project as a library in the name of logger-iam-core.

#Comments -

Every effort has been made to richly comment possibly everything in the project which is a boon and can be seen and confirmed in the javadocs.

#Things still left to be done- 

The backend operations have been tested. The project lacks a rich front end design. The flow needs to be tested once the front end is available.

Jmeter testing to see the performance of the application.

