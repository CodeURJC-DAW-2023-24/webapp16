#!/bin/bash
docker login
docker build -t nicohht/daw -f ./docker/Dockerfile .
docker push nicohht/daw
