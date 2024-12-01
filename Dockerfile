FROM amazoncorretto:17.0.13

WORKDIR /ioDigital

COPY target/tedtalks-0.0.1-SNAPSHOT.jar tedtalks.jar

CMD java -jar tedtalks.jar