FROM ubuntu:17.10
MAINTAINER Morten Bertelsen <mnb@kmd.dk>

# Environment variables required for this build (do NOT change)
# -------------------------------------------------------------
ARG user=dev


# Java version
ENV JAVA_VERSION 8

# Maven version
ENV MAVEN_VERSION 3.3.9

RUN echo 'deb http://archive.ubuntu.com/ubuntu xenial main restricted universe multiverse' >> /etc/apt/sources.list && \
		echo 'deb-src http://archive.ubuntu.com/ubuntu xenial main restricted universe multiverse' >> /etc/apt/sources.list && \		
		echo 'deb http://archive.ubuntu.com/ubuntu xenial-updates main restricted universe multiverse' >> /etc/apt/sources.list && \		
		echo 'deb-src http://archive.ubuntu.com/ubuntu xenial-updates main restricted universe multiverse' >> /etc/apt/sources.list && \		
		echo 'deb http://archive.ubuntu.com/ubuntu xenial-backports main restricted universe multiverse' >> /etc/apt/sources.list && \		
		echo 'deb-src http://archive.ubuntu.com/ubuntu xenial-backports main restricted universe multiverse' >> /etc/apt/sources.list && \		
		echo 'deb http://archive.ubuntu.com/ubuntu xenial-security main restricted universe multiverse' >> /etc/apt/sources.list && \		
		echo 'deb-src http://archive.ubuntu.com/ubuntu xenial-security main restricted universe multiverse' >> /etc/apt/sources.list && \		
		echo '#deb http://archive.ubuntu.com/ubuntu xenial-proposed restricted main universe multiverse' >> /etc/apt/sources.list && \		
		echo '#deb-src http://archive.ubuntu.com/ubuntu xenial-proposed restricted main universe multiverse' >> /etc/apt/sources.list && \		
		echo 'deb http://archive.canonical.com/ubuntu xenial partner' >> /etc/apt/sources.list && \		
		echo 'deb-src http://archive.canonical.com/ubuntu xenial partner' >> /etc/apt/sources.list 

RUN	apt-get clean && apt-get update && apt -y dist-upgrade \
	&& apt-get install -y --no-install-recommends \
	curl \
	wget \
	zip \
	bzip2 \
	unzip \
	fontconfig \
	vim \
	less \
	dirmngr \
	debconf-utils \
	git \
	sudo \
	ssh \
	procps \
	ca-certificates \
	libltdl-dev \
	rpm \
	&& rm -rf /var/lib/apt/lists/*

RUN ln -s /bin/gzip gzip

# Install java8
RUN echo 'deb http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main' >> /etc/apt/sources.list && \
        echo 'deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main' >> /etc/apt/sources.list && \
        apt-key adv --keyserver keyserver.ubuntu.com --recv-keys C2518248EEA14886 && \
        apt-get update && \
        echo oracle-java${JAVA_VERSION}-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections && \
        apt-get install -y --allow-unauthenticated --no-install-recommends oracle-java${JAVA_VERSION}-installer oracle-java${JAVA_VERSION}-set-default



# Install maven
RUN curl -fsSL http://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar xzf - -C /usr/share \
    && mv /usr/share/apache-maven-$MAVEN_VERSION /usr/share/maven \
    && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn


# Configure the "dev" user
RUN groupadd dev && useradd dev -s /bin/bash -m -g dev -G dev && adduser dev sudo
RUN echo 'dev:dev' | chpasswd
RUN mkdir -p /home/dev/projects
RUN cd /home/dev && chown -R dev:dev /home/dev/projects

# Clean
RUN apt-get clean && \
    rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/* /var/cache/oracle-jdk${JAVA_VERSION}-installer

#Configure Maven repos settings
RUN mkdir -p /home/dev/.m2
#COPY settings.xml /home/dev/.m2/settings.xml
RUN sudo chown -R dev:dev /home/dev

# Building continuous integration (CI) tools
RUN mkdir -p /home/dev/projects/devops
RUN git clone https://github.com/melias77/devops.git /home/dev/projects/devops
RUN cd /home/dev/devops && mvn clean install

# CMD    ["echo", "Done......."]
