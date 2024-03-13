# simple alpine docker with Oracle java 8
# FROM dockette/jdk8 as docky
# CMD sh
# ------------------

FROM openjdk:11

RUN apt update

RUN apt install -y wget
RUN wget https://mirrors.estointernet.in/apache/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
RUN tar -xvf apache-maven-3.6.3-bin.tar.gz
RUN mv apache-maven-3.6.3 /opt/
RUN rm -fr apache-maven-3.6.3-bin.tar.gz
ENV M2_HOME="/opt/apache-maven-3.6.3"
RUN ln -s "$M2_HOME/bin/"* "/usr/bin"

CMD bash
