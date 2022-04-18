function wait_for_server() {
  # shellcheck disable=SC2091
  until $($JBOSS_HOME/bin/jboss-cli.sh -c ":read-attribute(name=server-state)" 2>null | grep -q running); do
    sleep 1
  done
}

echo "=> Starting to config wildfly server"

echo "=> Waiting for the server to boot"
wait_for_server

echo "=> Executing the jboss-cli.sh commands"
$JBOSS_HOME/bin/jboss-cli.sh -c << EOF
batch
# Mark the commands below to be run as a batch

# Add the datasource
data-source add --name=$DATASOURCE_NAME --driver-name=$DRIVER_NAME --jndi-name=java:/$DATASOURCE_NAME --connection-url=jdbc:mysql://$DATABASE_HOST/$DATABASE_NAME --user-name=$DATABASE_USER --password=$DATABASE_PASSWORD --use-ccm=false --max-pool-size=25 --blocking-timeout-wait-millis=5000 --enabled=true

# Execute the batch
run-batch
EOF

echo "=> Add wildfly root"
$JBOSS_HOME/bin/add-user.sh "$SERVER_USER" "$SERVER_PASSWORD" --silent

echo "=> Deploy war file"
cp /opt/jboss/wildfly/standalone/online_test_deployments/onlinetest.war /opt/jboss/wildfly/standalone/deployments

echo "=> Config wildfly server successfully"