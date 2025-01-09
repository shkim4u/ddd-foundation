# Spring Dockerization > https://github.com/spring-guides/top-spring-boot-docker
# Amazon Public ECR Corretto 17: https://docs.aws.amazon.com/ko_kr/corretto/latest/corretto-17-ug/docker-install.html
# Base 이미지는 Docker Hub가 아닌 Amazon Public ECR로 설정합니다.
# 이유는?
# ARG BASE_REGISTRY="docker.io"
ARG BASE_REGISTRY="public.ecr.aws/docker/library"
ARG BASE_IMG_NAME="amazoncorretto"
ARG BASE_IMG_VERS="17"
FROM ${BASE_REGISTRY}/${BASE_IMG_NAME}:${BASE_IMG_VERS}

ARG APP_NAME="ddd-foundation"
ARG APP_VERSION="2.0"

COPY build/libs/${APP_NAME}-${APP_VERSION}.jar /app.jar
RUN chmod +x /app.jar

ENV LC_ALL=C.UTF-8

ENTRYPOINT ["java", "-jar", "/app.jar"]
