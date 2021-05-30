#!/usr/bin/env bash

run_in_docker() {
  if [[ "$(docker images -q markhobson/maven-chrome:jdk-16 2> /dev/null)" == "" ]]; then
    docker pull markhobson/maven-chrome:jdk-16
  fi
  docker run --rm -e DOCKER=true -it -v "$PWD":/usr/src -w /usr/src markhobson/maven-chrome:jdk-16 mvn clean test;
}

# Check if this script is being executed from base project dir
if [[ "$(pwd)" != *"/MiroTakeHomeTest" ]]; then
    echo "$(pwd)"
    echo "Please execute this file from inside of base project directory only..."
    exit 1
fi

echo "How do you prefer to run these tests?:"
echo "1. Locally with UI"
echo "2. Locally but headless"
echo "3. Docker"
echo "Your selection: "; read n

case $n in
  1) export DOCKER=false; export HEADLESS=false; mvn clean test; ;;
  2) export DOCKER=false; export HEADLESS=true; mvn clean test; ;;
  3) run_in_docker;;
  *) echo "invalid option";;
esac


