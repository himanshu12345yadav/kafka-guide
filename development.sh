#!/bin/sh

# run the spring boot application

./gradlew bootRun -PskipDownload=true --args='--spring.profiles.active=dev'