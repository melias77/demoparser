#!/bin/bash

echo "Provisioning developer machine..."

echo "Reading the property file..."
. /vagrant/provision/provision.properties

echo "Installing utilities..."
sudo apt-get update
sudo apt-get -y install sudo subversion zip gzip tar bzip2 fontconfig
sudo apt-get -y install xauth
sudo apt-get -y install xorg
sudo apt-get -y install openbox
echo "Utilities installed"

echo "Installing Java..." 
echo 'deb http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main' >> /etc/apt/sources.list && \
echo 'deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main' >> /etc/apt/sources.list && \
apt-key adv --keyserver keyserver.ubuntu.com --recv-keys C2518248EEA14886 && \
apt-get update && \
echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections && \
apt-get install -y --force-yes --no-install-recommends oracle-java8-installer oracle-java8-set-default
sudo printf "export JAVA_HOME=/usr/lib/jvm/java-8-oracle\nexport PATH=\$PATH:\$JAVA_HOME/bin" > /etc/profile.d/jdk.sh
sudo chmod 755 /etc/profile.d/jdk.sh
. /etc/profile.d/jdk.sh
echo "Java installed"

echo "Installing Maven..."
curl -fsSL http://archive.apache.org/dist/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz | tar xzf - -C /usr/share \
&& mv /usr/share/apache-maven-3.3.9 /usr/share/maven \
&& ln -s /usr/share/maven/bin/mvn /usr/bin/mvn
echo "Maven installed"

echo "Installing ANT..."
echo "-----------------------------------------"
mkdir -p /usr/local/lib/ant
cd /usr/local/lib/ant
sudo wget -q http://archive.apache.org/dist/ant/binaries/apache-ant-1.9.7-bin.tar.gz
sudo tar xzf apache-ant-1.9.7-bin.tar.gz
sudo rm apache-ant-1.9.7-bin.tar.gz
sudo ln -s /usr/local/lib/ant/apache-ant-1.9.7/bin/ant /usr/local/bin/ant
sudo printf "export ANT_HOME=/usr/local/lib/ant/apache-ant-1.9.7\nexport PATH=\$PATH:\$ANT_HOME/bin" > /etc/profile.d/ant.sh
sudo chmod 755 /etc/profile.d/ant.sh
. /etc/profile.d/ant.sh
echo "Ant installed"


echo "Installing eclipse ..." 
echo -e "\n**** Grabbing eclipse  ****\n"
curl -fsSL http://ftp.osuosl.org/pub/eclipse/technology/epp/downloads/release/neon/3.RC2/eclipse-jee-neon-3-RC2-linux-gtk-x86_64.tar.gz  | tar xzf - -C /home/vagrant
echo "eclipse installed"

echo "cleaning up..."
apt-get clean
#&& \
#rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/* /var/cache/oracle-jdk8-installer