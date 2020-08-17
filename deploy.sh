#!/bin/bash
version=$(mvn help:evaluate -Dexpression=project.version | grep -Ev '(^\[|Download\w+:)')
# Only deploy if:
# - this is a tagged version with a proper version number (for example 0.1.0)
# - this is a snapshot version
if { [ -z "$TRAVIS_TAG" ] && [[ $version =~ ^[0-9]+.[0-9]+.[0-9]+$ ]]; } || [[ $version =~ ^[0-9]+.[0-9]+.[0-9]+-SNAPSHOT$ ]]
then
  gpg --fast-import .travis/gpg.asc
  mvn deploy -P publish -DskipTests=true --settings ".travis/maven-settings.xml"
fi
