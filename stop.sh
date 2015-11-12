#!/bin/bash

base_path=$(cd `dirname $0`; pwd)
cd $base_path

kill `cat ./var/app.pid`

exit 0
