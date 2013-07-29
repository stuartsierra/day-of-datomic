#!/usr/bin/env bash

# You can use this script to launch Clojure in any POSIX-compatible
# environment with a Bash shell such as Linux, Mac OS X, or Cygwin.

set -e

which java &>/dev/null || {
    echo "ERROR: Cannot find java executable."
    echo "Make sure the Java Develpoment Kit is installed"
    echo "and the java executable is on your PATH."
    exit 1
}

cd "$(dirname "$0")"

# Cygwin uses the Windows Java executable
if [[ "$OSTYPE" = "cygwin" ]]; then
    PATH_SEPARATOR="REPLACE_WITH_WINDOWS_CLASSPATH"
else
    PATH_SEPARATOR="REPLACE_WITH_UNIX_CLASSPATH"
fi

exec java -cp "$CLASSPATH" jline.ConsoleRunner clojure.main "$@"
