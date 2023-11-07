# simple alpine docker with Oracle java 8
# FROM dockette/jdk8 as docky
# CMD sh
# ------------------

FROM ubuntu:20.04

ARG DEBIAN_FRONTEND=noninteractive

ENV JAVA_VERSION=8  \
    JAVA_UPDATE=181 \
    JAVA_BUILD=13 \
    JAVA_HOME="/usr/lib/jvm/default-jvm"

RUN apt update

RUN apt -y install curl libcurl4 mc

COPY jdk-8u321-linux-x64.tar.gz /tmp/jdk-8u321-linux-x64.tar.gz
RUN tar -xf /tmp/jdk-8u321-linux-x64.tar.gz -C /tmp
RUN mkdir -p "/usr/lib/jvm/"
RUN mv "/tmp/jdk1.8.0_321" "/usr/lib/jvm/java-8-oracle"
RUN ln -s "/usr/lib/jvm/java-8-oracle" "$JAVA_HOME"
RUN ln -s "$JAVA_HOME/bin/"* "/usr/bin/"

RUN rm -fr /tmp/jdk*

RUN apt install -y wget
RUN wget https://mirrors.estointernet.in/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
RUN tar -xvf apache-maven-3.6.3-bin.tar.gz
RUN mv apache-maven-3.6.3 /opt/
RUN rm -fr apache-maven-3.6.3-bin.tar.gz
ENV M2_HOME="/opt/apache-maven-3.6.3"
RUN ln -s "$M2_HOME/bin/"* "/usr/bin"

COPY repository /root/.m2/repository

RUN apt install afl++ afl++-clang gradle

CMD bash