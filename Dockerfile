# Base image
FROM openjdk:19-jdk-alpine

# JAR 파일을 복사하여 컨테이너 내의 /app 디렉토리에 추가
COPY build/libs/ref-monolithic-server-0.0.1-SNAPSHOT.jar app.jar

# 컨테이너 내부에서 실행할 명령어 설정
CMD ["java", "-jar", "app.jar"]
