#!/bin/bash

echo -e "\033[32m"============= NOTICE : OpenJDK17 environment =============="\033[0m"
if [ "$1" = "build" ]; then
    ./gradlew bootJar
    java -jar build/libs/*.jar

elif [ "$1" = "run" ]; then
    java -jar jar/ref-monolithic-server-0.0.1-SNAPSHOT.jar

elif [ "$1" = "info" ]; then
    echo -e "\033[31m"INFORMATION"\033[0m"
    echo "1. build : Build project and run"
    echo "2. run : Run previous jar"
    echo "3. info"
else
    echo -e "\033[31m"ERROR"\033[0m" : Invalid command, argument is build or run
fi