FROM openjdk:11
VOLUME /tmp
EXPOSE 8113
ADD ./target/ms-transaction-credit-0.0.1-SNAPSHOT.jar ms-transaction-credit.jar
ENTRYPOINT ["java","-jar","ms-transaction-credit.jar"]