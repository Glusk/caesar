#!/bin/bash
version=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)
echo "Detected artifact version: $version"
echo "Detected git TAG: $TRAVIS_TAG"
# Only deploy if:
# - this is a tagged version with a proper version number (for example 0.1.0)
# - this is a snapshot version
if { [ -n "$TRAVIS_TAG" ] && [[ "$version" =~ ^[0-9]+.[0-9]+.[0-9]+$ ]]; } || { [ -z "$TRAVIS_TAG" ] && [[ "$version" =~ ^[0-9]+.[0-9]+.[0-9]+-SNAPSHOT$ ]]; }
then
  echo "Deploying..."
  gpg --fast-import .travis/gpg.asc
  mvn deploy -P publish -DskipTests=true --settings ".travis/maven-settings.xml"
fi
