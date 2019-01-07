#REST Calls
## Country
### Create a new country
	curl -X POST -d "name=India&shortname=IN" "http://localhost:8080/country"
### List all countries	
	curl -i localhost:8080/country


# DB details
	
	postgres db name: druto-chithhi-address-store
	
## Logging into psql
	psql -h localhost druto-chithhi-address-store -U postgres	



## Getting Started

	java -Dconfig.file=dev/address_store_config.edn  -jar target/address-store-0.0.1-SNAPSHOT-standalone.jar 

1. Start the application: `lein run`
2. Go to [localhost:8080](http://localhost:8080/) to see: `Hello World!`
3. Read your app's source code at src/com.swayam.products.drutochithhi.addrstore/service.clj. Explore the docs of functions
   that define routes and responses.
4. Run your app's tests with `lein test`. Read the tests at test/com.swayam.products.drutochithhi.addrstore/service_test.clj.
5. Learn more! See the [Links section below](#links).


## Configuration

To configure logging see config/logback.xml. By default, the app logs to stdout and logs/.
To learn more about configuring Logback, read its [documentation](http://logback.qos.ch/documentation.html).


## Developing your service

1. Start a new REPL: `lein repl`
2. Start your service in dev-mode:
    (conf/load-configs)
    (mount/start)
    (def dev-serv (run-dev))
3. Connect your editor to the running REPL session.
   Re-evaluated code will be seen immediately in the service.

### [Docker](https://www.docker.com/) container support

1. Build an uberjar of your service: `lein uberjar`
2. Build a Docker image: `docker build -t paawak/address-store:0.0.1-SNAPSHOT .`
3. Run your Docker image: `docker run -p 8080:8080 paawak/address-store:0.0.1-SNAPSHOT`

### [OSv](http://osv.io/) unikernel support with [Capstan](http://osv.io/capstan/)

1. Build and run your image: `capstan run -f "8080:8080"`

Once the image it built, it's cached.  To delete the image and build a new one:

1. `capstan rmi address-store; capstan build`


## Links
* [Other examples](https://github.com/pedestal/samples)

