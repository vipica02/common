FROM harbor-registry.lienvietpostbank.com.vn:5443/uniform/maven-offline:3.8.3-openjdk-17 AS MAVEN_BUILDER
ARG UNIFORM_STARTER_VERSION
ARG ACTIVE_PROFILES

COPY ../../../../../.. /usr/src/myapp
WORKDIR /usr/src/myapp
RUN mvn deploy -s ci-settings.xml -Drevision=$UNIFORM_STARTER_VERSION
