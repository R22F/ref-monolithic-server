gradlew build

docker build -t ref/monolithic-server:0.0.1 .

docker run -d -p 8080:8080 ref/monolithic-server