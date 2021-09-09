## Prerequisites
Please make sure you have the following installed:

1. Maven 3.*
2. Java 11 installed.

## Starting the Servers
From the command line, run the following command to build the project and start the servers:
### `sh start-server.sh`

## Stopping the Servers
From the command line, run the following command to stop the servers:
### `sh stop-server.sh`


## Logging

1. The logs for the publishing server can be found in `publisher_logs.log`
2. The logs for the subscriber server can be found in `subscriber_logs.log` 


## API Endpoints

### 1. Create Topic

#### Endpoint: ```/create/{topic}```

#### Method: ```POST```

#### Sample Request:
```
curl -i -X POST -H 'Content-Type: application/json' http://localhost:8000/create/topic1
```

#### Sample Success Response:
```
{"message":"Topic topic1 created successfully","success":true}
```

#### Sample Error Response:
```
{"message":"Topic topic1 already exists","success":false}
```

### 2. Subscribe to a Topic

#### Endpoint: ```/subscribe/{topic}```

#### Method: ```POST```

#### Sample Request:
```
curl -i -X POST -H 'Content-Type: application/json' -d '{ "url": "http://localhost:9000/test1"}' http://localhost:8000/subscribe/topic1
```

#### Sample Success Response:
```
{"url":"http://localhost:9000/test1","topic":"topic1","message":null,"success":true}
```

#### Sample Error Response:
```
{"url":null,"topic":null,"message":"Topic does not exist","success":false}
```

### 3. Publish Message to a Topic

#### Endpoint: ```/publish/{topic}```

#### Method: ```POST```

#### Sample Request:
```
curl -i -X POST -H 'Content-Type: application/json' -d '{"message": "hello world", "version": 1}' http://localhost:8000/publish/topic1
```

#### Sample Success Response:
```
{"message":"Message published successfully","success":true}
```

#### Sample Error Response:
```
{"message":"Topic does not exist","success":false}
```