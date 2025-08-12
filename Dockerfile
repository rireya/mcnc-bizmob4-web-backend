# ---------- Build stage ----------
FROM maven:3.9-eclipse-temurin-17 AS build

# 워킹 디렉터리
WORKDIR /workspace

# 의존성 캐시를 위해 먼저 POM만 복사 후 다운로드
COPY pom.xml .
RUN mvn -B -q dependency:go-offline

# 이후 소스 추가
COPY src ./src

# 테스트는 CI에서 하고, 컨테이너 빌드는 속도 위해 생략 권장
RUN mvn -B -q -DskipTests package

# ---------- Runtime stage ----------
# javax 기반: tomcat:9-jdk17-temurin
# jakarta 기반: tomcat:10.1-jdk17-temurin
FROM tomcat:9-jdk17-temurin

# 메모리 등 필요 시
ENV JAVA_OPTS="-Xms256m -Xmx512m"

# 컨텍스트 루트를 "/"로 쓰려면 ROOT.war에 배치
# 빌드 산출물 이름이 바뀔 수 있으니 와일드카드로 매칭
COPY --from=build /workspace/target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
