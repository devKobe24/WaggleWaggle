# 멀티스테이지 빌드를 사용하여 최적화
FROM eclipse-temurin:17-jdk-jammy AS builder

# 작업 디렉토리 설정
WORKDIR /app

# Gradle 래퍼와 빌드 스크립트 복사
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# 의존성 다운로드 (레이어 캐싱을 위해)
RUN ./gradlew dependencies --no-daemon

# 소스 코드 복사
COPY src src

# 애플리케이션 빌드
RUN ./gradlew bootJar --no-daemon

# 실행 단계
FROM eclipse-temurin:17-jdk-jammy

# 보안을 위한 비루트 사용자 생성
RUN groupadd -r waggle && useradd -r -g waggle waggle

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 JAR 파일 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 애플리케이션 사용자로 소유권 변경
RUN chown waggle:waggle app.jar

# 포트 노출
EXPOSE 9008

# 사용자 전환
USER waggle

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]