# 6 Ways to Super-charge Your Application with GraalVM

## 1 : Use GraalVM

* Super-charge your apps start-up time and footprint
* Make sure to use `-Ob` when developing, fast mode

Build a JAR and run it:
```shell
./mvnw package
java -jar target/...
```

Build a native image and run it:
```shell
./mvnw clean package -Pnative
target/jibber &
```

Check out the startup time - we will look at memory footprint later

---

## 2 : Run your JUnit Tests Natively

* With Spring Boot it should, "Just Work"
* Use the agent, `-Dagent=true`, to generate tracing config. This will be injected into your tests automatically

Run your Unit tests on the JVM:
```shell
./mvnw test
```

Run them as Native Code:
```shell
./mvnw -Pnative test
ls -l target
target/native-tests
```
---

The test generate a binary that you can run anytime.

## 3 : Use Monitoring

* Spring Boot Actuator works out of the box with Native Image, use it
* CPU & Memory aren't supported - you need to use other things to get them (CAdvisor)
* Here's a nice example showing this working, oh and look at the memory usage of a native binary!
  * http://129.153.91.63:3000/d/qA79viunk/jibber-api?orgId=1

```shell
curl htp://localhost:8080/actuator/prometheus
```

---

## 4 : Use JFR in Your Application

* JFR works, but not everything working yet
* Creating a custom event, save a snapshot, take a look

---

## 5 : Statically Link you Application & Make a Small Container

* Static linking makes small containers, with nothing in them, other than what is essential
* Less in the container, the more secure it can be

Build a Java container:
```shell
make docker
docker images | head -n2
```

Build a native container, with statically linked binary:
```shell
make dockerNativeStaticOSX
docker images | head -n2
```

The containers are small adn have nothing in them, other than the binary.

---

## 6 : Use Github Actions

* Native Image building uses less memory. We can easily build within 7 GB, which is a free runner on  Github Actions

---
