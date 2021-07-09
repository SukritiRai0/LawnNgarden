# LawnNgarden

Syngenta intern project

## Environment

* JDK 11
* Maven
* Docker

## Config DB password

Replace placehold to DB password at line `5` of file `LawnNgarden\src\main\resources\application-dev.yaml`  


## Run local

```bash
java -jar demo-0.0.1-SNAPSHOT.jar
```

Access it [http://127.0.0.1:8080/](http://127.0.0.1:8080/)

## Run it in Docker

* Build the image

```bash

docker build -t lawnngarden .

```

* Run in container

```bash

docker run -p 8080:8080 -t lawnngarden

```

* Access it [http://127.0.0.1:8080/](http://127.0.0.1:8080/)

