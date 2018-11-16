FROM jboss/wildfly:14.0.1.Final

ENV DB_URI db:3306 ENV MYSQL_VERSION 6.0.6
ENV JBOSS_CLI /opt/jboss/wildfly/bin/jboss-cli.sh 
ENV DEPLOYMENT_DIR /opt/jboss/wildfly/standalone/deployments/

COPY . ${DEPLOYMENT_DIR}

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent


# Found in https://hub.docker.com/r/metz/wildfly-mysql/~/dockerfile/

# Configure Wildfly server RUN echo "=> Starting WildFly server" && \
      /bin/bash -c '$JBOSS_HOME/bin/standalone.sh &' && \
    echo "=> Waiting for the server to boot" && \
      /bin/bash -c 'until `$JBOSS_CLI -c ":read-attribute(name=server-state)" 2> /dev/null | grep -q running`; do echo `$JBOSS_CLI -c ":read-attribute(name=server-state)" 2> /dev/null`; sleep 1; done' && \
    echo "=> Adding MySQL module" && \
      $JBOSS_CLI --connect --command="module add --name=com.mysql --resources=/tmp/mysql-connector-java-${MYSQL_VERSION}.jar --dependencies=javax.api,javax.transaction.api" && \
    echo "=> Adding MySQL driver" && \
                                     #/subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql.driver,driver-class-name=com.mysql.jdbc.Driver)
$JBOSS_CLI --connect --command="/subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql-connector-java-5.1.47,driver-module-name=com.mysql,driver-xa-datasource-class-name=com.mysql.jdbc.jdbc2.optional.MysqlXADataSource)" && \
 echo "=> Creating a new datasource" && \ $JBOSS_CLI --connect --command="data-source add \
        --name=AMT \
        --jndi-name=java:AMT \
        --user-name=root \
        --password=toor \
        --driver-name=mysql \
        --connection-url=jdbc:mysql://${DB_URI}/AMT\
        --use-ccm=false \
        --max-pool-size=25 \
        --blocking-timeout-wait-millis=5000 \
        --enabled=true" && \
         echo "=> Shutting down WildFly and Cleaning up" && \ 
		$JBOSS_CLI --connect --command=":shutdown" && \ 
		rm -rf $JBOSS_HOME/standalone/configuration/standalone_xml_history/ $JBOSS_HOME/standalone/log/* && \ rm -f /tmp/*.jar


EXPOSE 8080:8080
EXPOSE 9990:9990


CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

