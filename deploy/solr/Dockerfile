FROM solr:8.11.2
#works
#FROM solr:9.0.0
#doesn't work
#FROM solr:9.1.1
#doesn't work
#FROM solr:9.2.1
#doesn't work
#FROM solr:9.3.0
#doesn't work

WORKDIR /trace

ADD https://github.com/microsoft/ApplicationInsights-Java/releases/download/3.4.17/applicationinsights-agent-3.4.17.jar .

USER root

RUN chmod -R 755 /trace

USER solr