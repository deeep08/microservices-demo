#!/bin/bash -e

SERVICES[0]="eureka-service"
SERVICES[1]="authserver"
SERVICES[2]="image-service"
SERVICES[3]="gallery-service"
SERVICES[4]="zuul-server"

printf "Below services will be terminated...\n"
for SERVICE in "${SERVICES[@]}"
do
  echo "Stopping $SERVICE..."
  pgrep -f "$SERVICE" | xargs kill
done

sleep 5

printf "All processes stopped\n"
