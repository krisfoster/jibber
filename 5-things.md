# Five Ways to Super-charge Your Application with GraalVM

GraalVM?

* Fast, Efficent Java Runtime
* Application -> Native Binary

Available as Free (OSS) / Supported (Enterprise Edition)

Generate nonsense verse with a Spring Boot App
* Using Spring Native and GraalVM

---

## 1 : Use GraalVM & the GraalVM Native Build Tools

Install GraalVM [https://github.com/graalvm/graalvm-jdk-downloader/tree/dev](https://github.com/graalvm/graalvm-jdk-downloader/tree/dev):

```shell
# Get a token to use EE
bash <(curl -sL https://get.graalvm.org/ee-token)
# Install GraalVM
bash <(curl -sL https://get.graalvm.org/jdk)
```

* Super-charge your apps start-up time and footprint
* Make sure to use `-Ob` when developing, fast mode

Build a JAR and run it:
```shell
# You can build it with : ./mvnw package
java -jar target/...
```

Build a native image and run it:
```shell
./mvnw clean package -DskipTests -Pnative
cp target/jibber .
./jibber &
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
./mvnw -Pnative clean test
ls -l target
target/native-tests
```

The test generate a binary that you can run anytime.
---
## 3 : Use Monitoring
* Spring Boot Actuator works out of the box with Native Image, use it

```shell
./jibber &
curl http://localhost:8080/actuator/prometheus | bat
```

* Here's a nice example showing this working, oh and look at the memory usage of a native binary!
  * [http://129.153.91.63:3000/d/qA79viunk/jibber-api?orgId=1](http://129.153.91.63:3000/d/qA79viunk/jibber-api?orgId=1)

---
## 4 : Use JFR in Your Application
* JFR works, but not everything is working yet
* Create a custom event, save a snapshot, take a look
* `--install-exit-handlers`
* 22.2	: `-H:+AllowVMInspection` / 22.3 : `--enable-monitoring=jfr`

```java
    /**
     * Custom JFR Event
     */
    @Label("JibberwockyEvent")
    @Description("Used for recording events in our Jaberwocky Controller")
    static class JabberwockydEvent extends Event {
        @Label("Message")
        String message;
    }

    @Autowired
    Jabberwocky j;

    @RequestMapping
    ResponseEntity<String> jibber() {
        JabberwockydEvent event = new JabberwockydEvent();
        event.message = "Poem being generated";
        event.commit();
        return ResponseEntity.ok(j.generate());
    }
```

---
## 5 : Statically Link you Application & Make a Small Container
* Static linking makes small containers, with nothing in them, other than what is essential
* Less in the container, the more secure it can be

Build a Java container:
```shell
make docker
docker images | head -n3
```

Build a native container, with statically linked binary:
```shell
make dockerNativeStaticOSX
docker images | head -n3
```

Take a look inside the "static" container:
```shell
dive jibber:static
```

The containers are small and have nothing in them, other than the binary.
Also support, `Mostly Static`
---
## 6 : Use Github Actions (The Encore)
* Native Image building uses less memory. We can easily build within **7 GB**, which is a free runner on  Github Actions

```yaml
name: GraalVM Jibber Github Actions Pipeline (EE)
on: [push, pull_request]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - uses: graalvm/setup-graalvm@v1
        with:
          version: 'latest'
          java-version: '17'
          components: 'native-image'
          github-token: ${{ secrets.GITHUB_TOKEN }}
      - name: Build and Test Java Code
        run: |
          ./mvnw --no-transfer-progress -Pnative test
```
* [https://github.com/krisfoster/jibber/actions](https://github.com/krisfoster/jibber/actions)
* [https://github.com/marketplace/actions/github-action-for-graalvm](https://github.com/marketplace/actions/github-action-for-graalvm)
---
## Grab the Code for This Demo

Thanks for listening!

[https://github.com/krisfoster/jibber](https://github.com/krisfoster/jibber)

To learn more about GraalVM:
[https://www.graalvm.org](https://www.graalvm.org)

Online Labs (search for: GraalVM):
[https://luna.oracle.com](https://luna.oracle.com)

Workshops:
[https://github.com/graalvm/workshops](https://github.com/graalvm/workshops)

Demos:
[https://github.com/graalvm/graalvm-demos](https://github.com/graalvm/graalvm-demos)