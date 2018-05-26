FROM maven:3.5.3-jdk-8-alpine

COPY . /build
RUN cd /build && mvn clean install && mkdir /time-travel && cp /build/target/time-travel*.jar /time-travel/app.jar

ENTRYPOINT [ "sh", "-c", "java -jar /time-travel/app.jar" ]