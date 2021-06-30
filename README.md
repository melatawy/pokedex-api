TrueLayer Take Home Assignment
---------

### Prerequisites
- [JRE 8](https://www.java.com/en/download/)
- [Docker](https://www.docker.com/get-started) Choose Docker Desktop for quick run 

### Installation
 ```shell script
$ cd pokedex
$ ./mvnw package && java -jar target/pokedex-0.0.1-SNAPSHOT.jar
$ docker build -t springio/pokedex-docker .
$ docker run -p 5000:5000 springio/pokedex-docker 
```
  
### user Guide
Now from the browser, you can call the two available endpoints
- http://localhost:5000/pokemon/<pokemon name>
- http://localhost:5000/pokemon/translated/<pokemon name>

And they should follow the requirements attached.

### Future work
- Since those values are static, caching will work just fine here not to overload our downstreams with unnecessary requests.
- Better error handling for various HTTP codes returned from our downstreams. Currently to request any pokemon (such as unknown pokemon name) will result in 500.
- Security is well handled so far as in the Dockerfile, a new user is created for this specific app with very limited permission.
- Rate limiting on our API
