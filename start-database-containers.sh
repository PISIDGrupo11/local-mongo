#!/bin/bash

docker-compose up -d --remove-orphans

sleep 10

docker exec mongo1 /scripts/rs-init.sh

sleep 10