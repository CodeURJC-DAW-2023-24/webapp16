#!/bin/bash
docker login
docker build -t aadrisoriiano03/webapp16 -f ./docker/Dockerfile .
docker push aadrisoriiano03/webapp16
