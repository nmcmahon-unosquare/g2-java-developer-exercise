FROM maven:3.5.2-jdk-8-alpine
WORKDIR ./project
VOLUME bank-server-maven-repo
COPY src src/
COPY pom.xml pom.xml
COPY commands.sh commands.sh
RUN chmod 777 commands.sh
ENTRYPOINT ["./commands.sh"]