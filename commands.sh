#!/bin/bash
mvn clean install -U -Dmaven.test.skip=true
java -Djava.security.egd=file:/dev/./urandom -jar ./target/*.jar