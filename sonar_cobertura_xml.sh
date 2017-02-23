#!/bin/bash

######################################################################
base_path=$(cd `dirname $0`; pwd)

cd ${base_path}

# Build the project, run the unit tests and generate the Cobertura report in XML
# format:
mvn clean compile
mvn cobertura:cobertura -Dcobertura.report.format=xml

#Analyze the project with SonarQube using Maven:
mvn sonar:sonar

exit 0
