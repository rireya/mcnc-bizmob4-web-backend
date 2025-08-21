# mcnc-bizmob4-web-backend
bizMOB Web BE

#Render에서 서비스 만들기
 1)New + → Web Service
 2)Runtime: Docker
 3)Repository: (Dockerfile 포함된 Git 리포지토리)
 4)Auto-Deploy 설정완료.


Health Check Path: /health (GET)

#Environment Variables
SPRING_PROFILES_ACTIVE=dev

Render는 Dockerfile을 그대로 빌드. 별도 Build Command 입력 불필요(Docker 런타임 선택 시).

DB : supabase 사용
접속정보 :  properties 참고


#참고사항
포트 불일치: 앱이 8080만 듣고 PORT 무시 →  server.port=${PORT:8080} &  Render env 설정으로 해결
헬스체크 404/500: Actuator 미추가 or 경로 틀림 → /health 경로 확인
