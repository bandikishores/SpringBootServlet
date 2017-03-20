FROM dockerhub.ops.inmobi.com/planet/indis-docker-image:20161020_24
MAINTAINER planet-dev-group@inmobi.com

COPY springboot-0.0.1-SNAPSHOT.jar /opt/inmobi/springboot-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","/opt/inmobi/springboot-0.0.1-SNAPSHOT.jar","--server.port=8745"]

