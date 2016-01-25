#!/bin/bash

echo "Updating apt-get index"
apt-get update >> /tmp/provision.log 2>&1

echo "Upgrading all current packages"
apt-get upgrade -y >> /tmp/provision.log 2>&1

echo "Installing new packages"
# apt-get install git curl unzip ruby ruby-dev nodejs -y >> /tmp/provision.log 2>&1
apt-get install git curl unzip nodejs -y 2>&1 | tee /tmp/provision.log 2>&1


echo "Installing Ruby"
gpg --keyserver hkp://keys.gnupg.net --recv-keys 409B6B1796C275462A1703113804BB82D39DC0E3  >> /tmp/provision.log 2>&1
curl -sSL https://get.rvm.io | bash -s stable --ruby >> /tmp/provision.log 2>&1
source /usr/local/rvm/scripts/rvm

echo "Installing Ruby-2.2.1..."
rvm install ruby-2.2.1 >> /tmp/provision.log 2>&1
rvm use ruby-2.2.1 --default >> /tmp/provision.log 2>&1

echo "Installing Jekyll"
gem install jekyll kramdown jekyll-sitemap jekyll-coffeescript execjs 2>&1 >> /tmp/provision.log

echo " "
echo "Provisioning finished"
echo "run 'vagrant ssh' to enter the machine, then 'cd /vagrant'"
echo "finally run 'jekyll serve --force_polling'"
echo "Go to http://localhost:4000 for the website"

