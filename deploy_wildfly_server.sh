#!/bin/bash
echo "=> Starting deploy wildfly server"

echo "=> Maven clean"
mvn clean

echo "=> Maven package"
mvn package

echo "=> Docker stop online_test_server"
docker-compose stop online_test_server

echo "=> Docker rm wildfly_server"
docker rm wildfly_server

echo "=> Docker rmi online_test_backend_online_test_server"
docker rmi online_test_backend_online_test_server

echo "=> Docker compose up"
docker-compose up -d

echo "=> Docker exec config_wildfly_server.sh"
docker exec -t wildfly_server sh config_wildfly_server.sh

echo "=> Deploy wildfly server successfully"
