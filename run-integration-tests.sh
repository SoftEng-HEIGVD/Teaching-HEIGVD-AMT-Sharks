#!/bin/bash

mvn liberty:stop

mvn clean package
mvn liberty:create liberty:install-feature liberty:deploy
mvn liberty:start
#mvn liberty:configure-arquillian

mvn verify
#mvn liberty:stop
