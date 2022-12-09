# Simple CRUD APP

### Info
This APP handleS CRUD (Create-Read-Update-Delete) of a smoothies catalog


### How to test
You may use a rest client (e.g. Insomnia) and/or check the expose API on the following URL 

[swagger 2.0](http://localhost:9090/v2/api-docs)

Provided that app is up and running

### Coverage
After mvn clean package a folder is created under 

`target\site\jacoco`

Sited there you will find the [Jacoco](https://www.eclemma.org/jacoco/) code coverage report (index.html)



# Dockerized

### Tools and frameworks of solution

* Spring boot : a simple app for crud 

* Kafka, Kafka-connect, Debezium

* Redis : Used for caching retrieved money rates results 

* MySql : Basic stuff, just store when rates are retrieved (not fetched from cache)

* FluentD : Used for collecting and persist logs of application

* Elastic/Kibana: View logs

* Java: Spring boot app developed in java 11

* docker: The whole solution is "grouped" as images that can be started with docker-compose

* Maven: In order to build app

## DB 

App has data some smoothe items along with their details in dockerized image.

In case you need to test to another MySql DB check create.sql under db folder.

### How to build

1) cd  to MoneyExchange

```
mvn clean package
```

2) `docker build -t ex-currency .`

3) cd to docker

```
docker-compose -f elastic.yml up
docker-compose -f services.yml up
```

> In case you prefer to have app run via command line use the follwoing

1. run :  `docker-compose -f services_only.yml up`

2. run :  `env.bat` 

3. run :  `java -jar target\app.jar`

> app runs on port 9090, in case you have another service running on this port use another port with

```
java -jar target\app.jar --server.port={YOUR_FREE_PORT}
```

### How to see logs

1) Open [kibana](http://localhost:5601/)

2) Click on Discover in the left-hand navigation menu:

You should see the following configuration window:

![plot](./kibana_discover.png)

3) This allows you to define the Elasticsearch indices you’d like to explore in Kibana. To learn more, consult Defining your index patterns in the official Kibana docs. For now, we’ll just use the logstash-* wildcard pattern to capture all the log data in our Elasticsearch cluster. Enter logstash-* in the text box and click on Next step.

You’ll then be brought to the following page:
![plot](./kibana_index.png)

4) This allows you to configure which field Kibana will use to filter log data by time. In the dropdown, select the @timestamp field, and hit Create index pattern.

Now, hit Discover in the left hand navigation menu.

You should see a histogram graph and some recent log entries:

![plot](./kibana_logs.png)


## Improvements

* Api definition

* Stress/Load test

* Exception handling

* Validations

* caching strategy

* Encypt passwords

* API calls safeguard with anither tool like keycloak or an RBAC fmw

## Debezium 

We incorporate debezium with kafka and kafka-connect for outbox pattern

### check the status of the Kafka Connect service
```
curl --request GET \
  --url http://localhost:8083/ \
  --header 'Accept: application/json' 
```

### check available connectors
```
curl --request GET \
  --url http://localhost:8083/connectors/ \
  --header 'Accept: application/json'
```

### check connector state
```
curl --request GET \
  --url http://localhost:8083/connectors/{CONNECTOR_NAME}
  --header 'Accept: application/json' ]
```

### delete connector
```
curl --request DELETE \
  --url http://localhost:8083/connectors/shop-connector3 \
  --header 'Accept: application/json' 
```

### How to create new connector
```
curl --request POST \
  --url http://localhost:8083/connectors/ \
  --header 'Accept: application/json' \
  --header 'Content-Type: application/json' \
  --data '{
  "name": "shop-connector3",  
  "config": {  
    "connector.class": "io.debezium.connector.mysql.MySqlConnector",
    "tasks.max": "1",  
    "database.hostname": "db",  
    "database.port": "3306",
    "database.user": "root",
    "database.password": "root",
    "database.server.id": "112233",  
    "topic.prefix": "dbserver11",  
		"database.allowPublicKeyRetrieval":"true",
    "database.include.list": "shop",  
		"table.whitelist": "shop.user",
    "schema.history.internal.kafka.bootstrap.servers": "kafka:9092",  
    "schema.history.internal.kafka.topic": "schema-changes.shop"  
  }
}'
```
### List topics
```
docker exec -it kafka /kafka/bin/kafka-topics.sh  --bootstrap-server kafka:9092 --list
```
### Watch topic
```
docker exec -it  kafka /kafka/bin/kafka-console-consumer.sh  --bootstrap-server kafka:9092  --topic {TOPIC_NAME}
```

## SAMPLES

### authenticate
```
curl --request POST \
  --url http://localhost:9090/api/authenticate \
  --header 'Content-Type: application/json' \
  --data '{

			"username": "user1",
			"password": "pass"
}'
```
> BO LOGIN

```
curl --request POST \
  --url http://localhost:9090/api/authenticate \
  --header 'Content-Type: application/json' \
  --data '{

			"username": "bo1",
			"password": "pass"
}'
```
### Create user
```
curl --request POST \
  --url http://localhost:9090/api/authenticate \
  --header 'Content-Type: application/json' \
  --data '{

			"username": "user2221",
			"password": "pass"
}'
```
#### For the following use token from authenticate sample

### Delete details
```
curl --request DELETE \
  --url http://localhost:9090/api/product/1/details/2 \
  --header 'Authorization: Bearer  XXXXXXX' 
```
### Alter details
```
curl --request PATCH \
  --url http://localhost:9090/api/product/1/details/3 \
   --header 'Authorization: Bearer  XXXXXXX' \
  --header 'Content-Type: application/json' \
  --data '{ 
"description" : "description new",
"detaiInfo"  :"detaiInfo new22"
}'
```
### Get products
```
curl --request GET \
  --url 'http://localhost:9090/api/product/SMOOTHIES' \
  --header 'Authorization: Bearer  XXXXXXX' 
```
### Get product details
```
curl --request GET \
  --url http://localhost:9090/api/product/1/details \
  --header 'Authorization: Bearer  XXXXXXX' 
```
### Create order

```
curl --request POST \
  --url http://localhost:9090/api/order \
  --header 'Content-Type: application/json' \
  --header 'Authorization: Bearer  XXXXXXX' \
 --data '{
	"orderItemDtos": [
		{
			"productId": 1,
			"productDescription": "222",
			"quantity": 2,
			"price" : 11
		},
		{
			"productId": 122,
			"productDescription": "a2",
			"quantity": 12,
			"price" : 33
		}
	]
}'
```


