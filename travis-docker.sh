#!/usr/bin/env bash

set -e

docker build -t kube-repo-validator .
docker images
docker tag kube-repo-validator $DOCKER_USERNAME/kube-repo-validator
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
docker push $DOCKER_USERNAME/kube-repo-validator