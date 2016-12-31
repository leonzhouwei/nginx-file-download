#!/bin/bash

base_path=$(cd `dirname $0`; pwd)
cd $base_path

(sh ./main.sh &)

exit 0
