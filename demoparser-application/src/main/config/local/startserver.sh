#!/bin/sh
java -Xms1024m -Xmx3072m -Xss512k -jar ./../demoparser-rest/target/demoparser-rest.jar  --spring.config.location=classpath:/application.properties,file:./../demoparser-application/target/config/local/config/application-override.properties 
