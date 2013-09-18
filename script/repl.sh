#!/usr/bin/env bash

# Run this script on a UNIX-like system to launch a Clojure REPL using
# the copy of Leiningen downloaded by bootstrap.sh.

cd "$(dirname "$0")"/..

export LEIN_HOME=script/lein-home

exec script/lein repl
