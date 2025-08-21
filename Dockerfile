
# ---------- Build stage ----------
FROM maven:3.9.8-eclipse-temurin-17 AS build

# 워킹 디렉터리
WORKDIR /workspace

# 의존성 캐시를 위해 먼저 POM만 복사 후 다운로드
COPY pom.xml .
RUN mvn -B -q dependency:go-offline

# 소스 복사 & 패키징,  테스트는 CI에서 하고, 컨테이너 빌드는 속도 위해 생략 권장
COPY src ./src
RUN mvn -B -q -DskipTests package

# ---------- Runtime stage ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

# 부트 fat-jar만 복사( .original 제외 )
COPY --from=build /workspace/target/*.jar /app/
RUN set -eux; \
	ls -l /app; \
    JAR="$(ls /app/*.jar | grep -v '.original' | head -n1)"; \
    mv "$JAR" /app/app.jar; \
    rm -f /app/*.original || true

# Render가 주는 $PORT로 리슨 (기본 8080) 문서화용(필수 아님)
ENV PORT=8080
EXPOSE 8080


# 필요 시 JVM 옵션을 JAVA_OPTS로 주입 가능 (Render의 환경변수로 설정 권장)
# 예: -Xms256m -Xmx512m -XX:+UseG1GC 등
# disableShutdownHook ShutdownHook중복으로인한 오류발생 방지
# 디버그를 위해 시작 시 몇 가지 찍고 실행
ENTRYPOINT ["sh","-c","set -eux; java -version; ls -l /app; echo \"JAVA_OPTS=$JAVA_OPTS\"; exec java $JAVA_OPTS -jar /app/app.jar"]
ENV JAVA_OPTS="-Dlogback.disableShutdownHook=true -Xms256m -Xmx512m"

# 컨텍스트 루트를 "/"로 쓰려면 ROOT.war에 배치
# 빌드 산출물 이름이 바뀔 수 있으니 와일드카드로 매칭
#COPY --from=build /workspace/target/*.war /usr/local/tomcat/webapps/ROOT.war

#CMD ["catalina.sh", "run"]
