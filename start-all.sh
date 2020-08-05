#!/bin/bash -e
VERSION=1.0

SERVICES[0]="eureka-service"
SERVICES[1]="authserver"
SERVICES[2]="image-service"
SERVICES[3]="gallery-service"
SERVICES[4]="zuul-server"

mkdir -p log
for SERVICE in "${SERVICES[@]}"
do
  echo "Starting $SERVICE..."
  java -jar $SERVICE/target/$SERVICE-$VERSION.jar > log/$SERVICE.log 2>&1 &
done

sleep 20

printf "\nRunning services...\n"
printf "Service\t\tPID\tPort\n"
for SERVICE in "${SERVICES[@]}"
do
  PID=$(pgrep -f "$SERVICE")
  printf "%s\t%d\t" $SERVICE $PID
  lsof -Pan -p "$PID" -i | grep LISTEN | awk '{print $9}'
done
