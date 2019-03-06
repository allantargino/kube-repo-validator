#!/bin/sh

set -e

docker build -t kube-repo-validator .
docker images
docker tag kube-repo-validator $DOCKER_REGISTRY/kube-repo-validator
echo "$DOCKER_PASSWORD" | docker login --username "$DOCKER_USERNAME" --password-stdin $DOCKER_REGISTRY
docker push $DOCKER_REGISTRY/kube-repo-validator
