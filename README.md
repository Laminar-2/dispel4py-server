### Server Installation and Execution
Open a new terminal with the new `Laminar` directory.
```
/data/Laminar> 
```
Clone the server application repository.
```
/data/Laminar> git clone https://github.com/Laminar-2/dispel4py-server.git
```
Enter the server directory.
```
/data/Laminar> cd dispel4py-server
```
To manage permission issues, run:
```
/data/Laminar/dispel4py-server> chmod +x gradlew
```
To configure the required fields, navigate to `/src/main/resources/application.properties` and fill in the required variables

To run the server
```
/data/Laminar/dispel4py-server> ./gradlew bootRun
```
The server will now be running, and ready to receive requests. You should see this sample output
```
/data/Laminar/dispel4py-server>
...
com.dispel4py.rest.RestApplication: Started RestApplication in 66.905 seconds (JVM running for 69.911)
...
```

### Docker Execution 

To run the application in a docker container follow these intstructions 

Clone the server application repository.
```
/data/Laminar> git clone https://github.com/Laminar-2/dispel4py-server.git
```
Enter the server directory.
```
/data/Laminar> cd dispel4py-server
```
Run docker compose to load up the server and database
```
docker compose up
```
