#!/bin/bash
docker login
docker build -t nicohht/webapp16final -f ./docker/Dockerfile .
docker push nicohht/webapp16final
