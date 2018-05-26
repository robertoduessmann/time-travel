# time-travel

[![Build Status](https://travis-ci.org/robertoduessmann/time-travel.svg?branch=master)](https://travis-ci.org/robertoduessmann/time-travel)

> Space-time travel machine

## Running

### Option 1: Running using maven plugin
```console
$ cd <project>
$ mvn spring-boot:run
```

### Option 2: Running as a packaged application
```console
$ cd <project>
$ mvn clean install
$ java -jar target/time-travel-0.0.1-SNAPSHOT.jar
```

### Option 3: Running on Docker
Build image from a Dockerfile:
```console
$ docker build -t time-travel:latest .
```
Run docker image:
```console
$ docker run -p 8080:8080 time-travel:latest
```

## Usage

### POST /travels
> Submit travel details
```console
$ curl -X POST \
    https://spacetimetravel.herokuapp.com/travels \
    -H 'content-type: application/json' \
    -d '{
        "personalGalacticIdentifier": "person1",
        "place": "London",
        "date": "2018-05-24T20:31:26.481Z"
    }'
```

### GET /travel/{id}
> Get details from a travel
```console
$ curl -X GET https://spacetimetravel.herokuapp.com/travels/618f8104-ce84-40ee-b703-3a716b078584
```
```json
{
   "id": "618f8104-ce84-40ee-b703-3a716b078584",
   "personalGalacticIdentifier": "person1",
   "place": "London",
   "date": "2018-05-24T20:31:26.481Z"
}
```

### GET /travels
> Get all travels
```console
$ curl -X GET https://spacetimetravel.herokuapp.com/travels
```
```json
[
   {
      "id": "618f8104-ce84-40ee-b703-3a716b078584",
      "personalGalacticIdentifier": "person1",
      "place": "London",
      "date": "2018-05-24T20:31:26.481Z"
   }
]
```