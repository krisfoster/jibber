# Jibber Benchmark Project

![short link](bit.ly_3Rp9H94.png)

This project demonstrates a simple Spring Boot Application that can be used to benchmark the performance of GraalVM Native Images.

## To Test

```shell
./mvnw test
```

## To Build

```shell
./mvnw package
java -jar target/benchmarks-0.0.1-SNAPSHOT-exec.jar &
curl http://localhost:8080/jibber
```

## To Run the Tests Natively

```shell
./mvnw -Pnative test
```

## To Build a Native Image

```shell
./mvnw -Pnative package
target/jibber &
curl http://localhost:8080/jibber
```



