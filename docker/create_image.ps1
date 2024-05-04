#!/bin/bash
docker login
docker build -t aadrisoriano03/webapp16 -f ./docker/Dockerfile .
docker push aadrisoriiano03/webapp16
