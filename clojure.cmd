@echo off
setlocal

:: You can use this script to launch Clojure on Windows.

java -cp REPLACE_WITH_WINDOWS_CLASSPATH jline.ConsoleRunner clojure.main %*

endlocal
