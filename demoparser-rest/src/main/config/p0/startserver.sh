#!/bin/sh
java -Xms1024m -Xmx3072m -Xss512k -jar target/demospringboot-rest.jar  --spring.config.location=classpath:/application.properties,file:target/config/local/config/application-override.properties 
