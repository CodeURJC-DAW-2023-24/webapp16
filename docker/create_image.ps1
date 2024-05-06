#!/bin/bash
docker login
docker build -t nicohht/final -f ./docker/Dockerfile .
docker push nicohht/final
