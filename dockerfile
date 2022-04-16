FROM quay.io/wildfly/wildfly
COPY ./target/onlinetest.war /opt/jboss/wildfly/standalone/deployments
RUN /opt/jboss/wildfly/bin/add-user.sh root sunflower@102 --silent
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]