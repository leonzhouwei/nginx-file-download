#!/bin/bash

make

cp src/redis* ./
rm *.c
rm *.o
mkdir -p ./var/run
mkdir log

exit 0

