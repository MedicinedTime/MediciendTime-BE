# Stage 1: Build using OpenJDK 22 (Debian-based slim image) and manually installed Gradle 8.8 (or upgrade to a version that supports JDK22 properly)
FROM openjdk:22-jdk-slim AS build
WORKDIR /app

# 필요한 패키지 설치: wget, unzip
RUN apt-get update && apt-get install -y wget unzip

# Gradle 8.8 다운로드 및 압축 해제 (또는 다른 적절한 버전을 사용)
RUN wget https://services.gradle.org/distributions/gradle-8.8-bin.zip -O gradle.zip && \
    unzip gradle.zip && \
    rm gradle.zip

# Gradle 환경변수 설정
ENV GRADLE_HOME=/app/gradle-8.8
ENV PATH=$GRADLE_HOME/bin:$PATH

# 소스 코드 복사
COPY . .

# Gradle 빌드 실행 (도구체인 자동 감지 및 다운로드 비활성화)
RUN gradle -Dorg.gradle.java.installations.auto-detect=false -Dorg.gradle.java.toolchain.auto-download=false clean bootJar --no-daemon --stacktrace
