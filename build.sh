#!/bin/sh
set -eu
mkdir -p lib build/classes
if [ ! -f lib/gson-2.11.0.jar ]; then curl --fail --silent --show-error --location https://repo.maven.apache.org/maven2/com/google/code/gson/gson/2.11.0/gson-2.11.0.jar -o lib/gson-2.11.0.jar; fi
javac --release 17 -cp lib/gson-2.11.0.jar -d build/classes src/main/java/com/anyoauth/*.java test/conformance/Conformance.java
