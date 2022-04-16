mvn clean package && docker rm -f online_test_server & docker rmi online_test_backend_wildfly & docker-compose up -d

