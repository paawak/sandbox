FROM openjdk:11.0.1-sid

MAINTAINER Palash Ray <paawak@gmail.com>

ADD target/address-store-0.0.1-SNAPSHOT-standalone.jar /address-store/app.jar

CMD ["java", "-Dconfig.file=prod/address_store_config.edn", "-jar", "/address-store/app.jar"]
