FROM icr.io/appcafe/open-liberty:kernel-slim-java17-openj9-ubi

COPY --chown=1001:0 /src/main/liberty/config /config

RUN features.sh

COPY --chown=1001:0 target/*.war /config/apps
COPY --chown=1001:0 src/main/resources/libs/*.jar /usr/local/lib

RUN configure.sh
