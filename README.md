### If you want to start the application - just follow these steps:
1. Execute the maven task "install" for creating the local starter dependency
2. Execute the gradle task "bootJar" for creating the jar file for these modules: eureka, item-service
3. Open the deploy directory and use the command "docker-compose up"
4. start main-service from IDEA
5. enjoy
### Next steps you can use for testing the application:
1. Open the test directory and "scratch.http" file into it
2. Start all the requests (sometimes you need to wait for correct registration services into Eureka)
3. enjoy
### For checking the services you can follow these links:
1. http://localhost:5776/ - Eureka link. Here you can check the service registration
2. http://localhost:16686/ - Jaeger UI. Here you can check the traces
3. http://localhost:8983/solr/#/item/query - Solr UI for the "item" core
