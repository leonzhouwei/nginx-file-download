#!/bin/bash

######################################################################
base_path=$(cd `dirname $0`; pwd)

cd ${base_path}

# Prepare jacoco agent to allow coverage report generation, build the project, 
# and execute the unit tests:
mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install

# To get coverage per tests information, you will need to activate the profile 
# when running the instrumented tests:
mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install -Pcoverage-per-test

# Analyze the project with SonarQube using Maven:
mvn sonar:sonar

exit 0
