#!/bin/bash
docker login
docker build -t nicohht/webapp16 -f docker/Dockerfile .
docker push nicohht/webapp16
