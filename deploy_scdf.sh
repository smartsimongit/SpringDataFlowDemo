#!/bin/sh
#chmod +x *.sh

filename=`basename "$0"`

echo "INFO: running $filename "$@""

export HOST_MOUNT_PATH=/home/smartsimon/SpringDataFlowDemo/jars

export DATAFLOW_VERSION=2.7.0

export SKIPPER_VERSION=2.6.0

docker-compose -f ./docker-compose.yml -f ./docker-compose-prometheus.yml up