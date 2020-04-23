#!/bin/bash

SERVERPROD="localhost"


echo "Hola soy $SERVERPROD"

curl --request POST 'http://localhost:8090/api/seguridad/oauth/token' \
          --header 'Content-Type: application/x-www-form-urlencoded' \
            --user 'webAngular:123456'  --data-urlencode 'grant_type=password' --data-urlencode 'username=admin' --data-urlencode 'password=12345' | python -mjson.tool
