#!/bin/bash
FILE=video.mp4

MICRONAUT_PATH=micronaut/src/main/resources/org/example
mkdir -p $MICRONAUT_PATH
cp -f $FILE $MICRONAUT_PATH

NGINX_PATH=nginx/content
mkdir -p $NGINX_PATH
cp -f $FILE $NGINX_PATH

SPRING_PATH=spring/src/main/resources/org/example
mkdir -p $SPRING_PATH
cp -f $FILE $SPRING_PATH