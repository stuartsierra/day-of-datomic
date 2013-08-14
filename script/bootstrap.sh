#!/usr/bin/env bash

cd "$(dirname "$0")"/..

lein deps

# No idea how old Clojure versions sneak in...
for ver in 1.2 1.3 1.4; do
    rm -rf repository/org/clojure/clojure/${ver}*
done

WINDOWS_JARS=`find repository -type f -name '*.jar' -print0 | tr '\000' ';'`
UNIX_JARS=`find repository -type f -name '*.jar' -print0 | tr '\000' ':'`
sed -e "s|REPLACE_WITH_WINDOWS_JARS|$WINDOWS_JARS|" \
    -e "s|REPLACE_WITH_UNIX_JARS|$UNIX_JARS|" \
    script/clojure.sh.template > clojure.sh
sed -e "s|REPLACE_WITH_WINDOWS_JARS|$WINDOWS_JARS|" \
    script/clojure.cmd.template > clojure.cmd

chmod 0755 clojure.sh

