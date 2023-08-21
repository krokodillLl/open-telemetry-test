### If you want to start the application - just follow these steps:
1. execute the maven task "install" for creating the local starter dependency
2. execute the gradle task "bootJar" for creating the jar file for these modules: eureka, main-service, item-service
3. open the deploy directory and use the command "docker-compose up"
4. enjoy
### Next steps you can use for testing the application:
1. open the test directory and "scratch.http" file into it
2. start all the requests (sometimes you need to wait for correctly registration services into Eureka)
3. enjoy
### For checking the services you can follow these links:
1. http://localhost:5776/ - Eureka link. Here you can check the services registration
2. http://localhost:16686/ - Jaeger UI. Here you can check the traces 