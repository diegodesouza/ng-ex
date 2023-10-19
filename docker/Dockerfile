#FROM va33dlvdkr003.wellpoint.com/eto/eto_bld_middleware_libertybase:v0.0.7
FROM quay.apps.lz-np2.ent-ocp4-useast1.aws.internal.das/providerportal/eto_bld_middleware_libertybaseimage:v0.0.9
LABEL maintainer="karthik.ramachandran@anthem.com"
ENV APP_ENV_NAME=SIT
#COPY ./*.war /config/apps/

COPY /config/ /config/
#COPY config/resources/security/logs/ /wsapps/eca/sit/logs/
COPY 041800000P05.tar /opt/splunkforwarder/etc/apps/041800000P05.tar

RUN mkdir -p /logs /wslogs /wsapps 
RUN chgrp -R 0 /config /opt/splunkforwarder /opt/ibm/wlp /logs /wslogs /wsapps /IntroscopeAgent /IntroscopeAgent && \
        chmod -R g=u /config /opt/splunkforwarder /opt/ibm/wlp /logs /wslogs /wsapps /IntroscopeAgent /IntroscopeAgent

RUN cd /opt/splunkforwarder/etc/apps \
  && tar -xvf 041800000P05.tar -C ./ \
  && rm 041800000P05.tar
RUN cd /opt/splunkforwarder/etc/apps/041800000P05-Availity && mkdir -p metadata
RUN chgrp -R 0 /opt/splunkforwarder/etc/apps/041800000P05-Availity/local/app.conf /opt/splunkforwarder/etc/apps/041800000P05-Availity/local/ /opt/splunkforwarder/etc/apps/041800000P05-Availity/metadata && \
        chmod -R g=u /opt/splunkforwarder/etc/apps/041800000P05-Availity/local/app.conf /opt/splunkforwarder/etc/apps/041800000P05-Availity/local/ /opt/splunkforwarder/etc/apps/041800000P05-Availity/metadata
RUN cp /config/resources/security/bcprov-jdk16-1.46.jar $JAVA_HOME/jre/lib/ext
USER 1001 
