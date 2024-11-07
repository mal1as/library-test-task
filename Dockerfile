# temp container to build using gradle
FROM gradle:8.10.2-jdk-alpine AS temp_build_image
ENV APP_HOME=/app
WORKDIR $APP_HOME
COPY build.gradle settings.gradle $APP_HOME

COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle src $APP_HOME/src

RUN gradle clean bootJar

# main container
FROM openjdk:17-oracle

ENV APP_DIR /app
ENV JAVA_OPTS ""
ENV ARTIFACT_NAME library-test-task-1.0-SNAPSHOT.jar

# add gradle build jar task

COPY --from=temp_build_image ${APP_DIR}/build/libs/${ARTIFACT_NAME} ${APP_DIR}/app.jar

WORKDIR ${APP_DIR}

CMD exec java ${JAVA_OPTS} -jar ${APP_DIR}/app.jar