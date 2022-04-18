FROM quay.io/wildfly/wildfly
COPY ./mysql-connector-java-8.0.28.jar /opt/jboss/wildfly/standalone/deployments/
COPY ./target/onlinetest.war /opt/jboss/wildfly/standalone/online_test_deployments/
COPY ./config_wildfly_server.sh /opt/jboss/
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]