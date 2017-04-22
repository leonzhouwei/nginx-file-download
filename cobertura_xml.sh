#!/bin/bash

######################################################################
base_path=$(cd `dirname $0`; pwd)

cd ${base_path}

# Build the project, run the unit tests and generate the Cobertura report in XML
# format:
mvn clean compile
mvn cobertura:cobertura -Dcobertura.report.format=xml

exit 0
