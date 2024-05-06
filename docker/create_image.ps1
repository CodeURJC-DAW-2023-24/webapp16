#!/bin/bash
docker login
docker build -t nicohht/webapp16final2 -f ./docker/Dockerfile .
docker push nicohht/webapp16final2
