#/bin/bash

mvn clean package -DskipTests

docker compose down -v # detiene los contenedores y borra volúmenes
docker compose up --build
