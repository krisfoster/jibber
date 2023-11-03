# Jibber Benchmark

<img src="bit.ly_3Rp9H94.png" alt="short link" width="25%"/>

> ’Twas brillig, and the slithy toves
> Did gyre and gimble in the wabe:
> All mimsy were the borogoves,
> And the mome raths outgrabe.
> Beware the Jabberwock, my son!
> The jaws that bite, the claws that catch!

This project demonstrates a simple Spring Boot Application that can be used to benchmark the performance of GraalVM Native Images.

The application uses a Markow Model to model the poem, The Jabberwocky, and then generate a new, random poem that ressembles the original.
Useful, I know.

## To Test

```shell
./mvnw test
```

## To Build

To generate the Java application and run it:
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

## To Deploy to K8s



