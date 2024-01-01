gradlew build

docker build -t ref/monolithic-server:latest .

docker run -d -p 8080:8080 ref/monolithic-server