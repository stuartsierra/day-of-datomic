#!/usr/bin/env bash

cd "$(dirname "$0")"/..

lein deps

lein classpath > .lein-classpath

WINDOWS_JARS=`sed -e 's/:/;/g' < .lein-classpath`
UNIX_JARS=`cat .lein-classpath`
sed -e "s|REPLACE_WITH_WINDOWS_JARS|$WINDOWS_JARS|" \
    -e "s|REPLACE_WITH_UNIX_JARS|$UNIX_JARS|" \
    script/clojure.sh.template > clojure.sh
sed -e "s|REPLACE_WITH_WINDOWS_JARS|$WINDOWS_JARS|" \
    script/clojure.cmd.template > clojure.cmd

chmod 0755 clojure.sh

