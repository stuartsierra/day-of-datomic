#!/usr/bin/env bash

# Run this script on a UNIX-like system to download Leiningen and all
# dependencies for this project.

cd "$(dirname "$0")"/..

export LEIN_HOME=script/lein-home

script/lein deps

script/lein classpath > .lein-classpath

WINDOWS_JARS=`sed -e 's/:/;/g' < .lein-classpath`
UNIX_JARS=`cat .lein-classpath`
sed -e "s|REPLACE_WITH_WINDOWS_JARS|$WINDOWS_JARS|" \
    -e "s|REPLACE_WITH_UNIX_JARS|$UNIX_JARS|" \
    script/clojure.sh.template > script/clojure.sh
sed -e "s|REPLACE_WITH_WINDOWS_JARS|$WINDOWS_JARS|" \
    script/clojure.cmd.template > script/clojure.cmd

chmod 0755 script/clojure.sh

