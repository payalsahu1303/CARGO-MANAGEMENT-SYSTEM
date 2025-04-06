# Base image: Ubuntu 22.04
FROM ubuntu:22.04

# Set environment variables
ENV DEBIAN_FRONTEND=noninteractive
ENV CATALINA_HOME=/opt/tomcat
ENV PATH=$CATALINA_HOME/bin:$PATH

# Install dependencies: Java, wget, unzip, MySQL client
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk wget unzip curl mysql-client && \
    apt-get clean

# Install Apache Tomcat 9 manually
RUN wget https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.85/bin/apache-tomcat-9.0.85.tar.gz -O /tmp/tomcat.tar.gz && \
    mkdir -p /opt && \
    tar xzf /tmp/tomcat.tar.gz -C /opt && \
    mv /opt/apache-tomcat-9.0.85 /opt/tomcat && \
    rm /tmp/tomcat.tar.gz

# Copy WAR or project files into Tomcat's webapps directory
COPY webapps/cargo-management-system.war $CATALINA_HOME/webapps/ROOT.war

# Expose port 8000
EXPOSE 8000

# Start the Tomcat server and make sure it listens on 0.0.0.0
CMD ["sh", "-c", "$CATALINA_HOME/bin/catalina.sh run"]
