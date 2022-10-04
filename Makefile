# Runs the Junit tests on the JVM
test:
	./mvnw clean test
.PHONY: test

# Builds the jar
jar:
	./mvnw clean package
.PHONY: jar

# Runs the native tests locally, i.e. not within a docker container
testNative:
	./mvnw -Pnative clean test
.PHONY: test

# Builds a native binary locally, i.e. not within a docker container
nativeLocal:
	./mvnw clean package -Pnative
.PHONY: nativeLocal

# Builds a JVM docker image of the application
docker:
	./mvnw package
	docker build -f Dockerfiles/Dockerfile.jvm \
		            --build-arg JAR_FILE=target/benchmarks-0.0.1-SNAPSHOT.jar \
					-t jibber:jdk \
					.
.PHONY: docker

# Runs the JVM Docker container
runJDKContainer:
	docker run --rm --name jibber-jdk -d -p 8080:8080 jibber:jdk
.PHONY: runJDKContainer

# Builds a docker container with the native binary in it. Builds the binary with docker, to
# work around the issue of building ocally on OSX and that obviously not being able to be run within a docker linux container
dockerNativeOSX:
	docker build -f Dockerfiles/Dockerfile.native.osx \
					-t jibber:native \
					.
.PHONY: dockerNativeOSX

dockerNativeLinux:
	docker build -f Dockerfiles/Dockerfile.native \
		            --build-arg APP_FILE=target/jibber \
					-t jibber:native \
					.
.PHONY: dockerNativeLinux

runNativeContainer:
	docker run --rm --name jibber-native -d -p 8080:8080 jibber:native
.PHONY: runJDKContainer

# Runs the application
runWithJFR:
	./jibber -XX:+FlightRecorder -XX:StartFlightRecording="filename=recording1.jfr,dumponexit=true" &
.PHONY: runWithJFR

visualvm:
	jvisualvm &
.PHONY: visualvm

dockerNativeStaticOSX:
	docker build --file Dockerfiles/Dockerfile.native.static.osx -t jibber:static .
.PHONY: dockerNativeStaticOSX

runNativeStaticContaner:
	docker run --rm --name jibber-static -d -p 8080:8080 jibber:static
.PHONY: runNativeStaticContaner
