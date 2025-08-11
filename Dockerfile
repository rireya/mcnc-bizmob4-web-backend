# ---------- Build Stage ----------
FROM maven:3.9.8-eclipse-temurin-17 AS build
WORKDIR /app

# 의존성 캐시 최적화
COPY pom.xml .
RUN mvn -q -B -DskipTests dependency:go-offline

# 소스 복사 & 패키징
COPY src ./src
RUN mvn -q -B -DskipTests package

# ---------- Runtime Stage ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

# 빌드 결과 복사 (jar 파일명에 맞춰 경로 조정)
COPY --from=build /app/target/*.jar /app/app.jar

# 컨테이너 내부 기준 기본 포트 (문서화 용도)
EXPOSE 8080

# PORT는 플랫폼이 주입, application.properties에서 매핑됨
ENTRYPOINT ["java","-XX:+UseG1GC","-XX:MaxRAMPercentage=75","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]
