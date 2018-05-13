@echo off

set DIRNAME=%~dp0%
set ROOTDIR=%DIRNAME%\..
set TARGETDIR=%DIRNAME%target


rem initialization

:RUN_LOOP
    if "%1" == "jansi" goto :EXECUTE_JANSI
    if "%1" == "jna" goto :EXECUTE_JNA
    if "%1" == "ssh" goto :EXECUTE_SSH
    if "%1" == "debug" goto :EXECUTE_DEBUG
    if "%1" == "debugs" goto :EXECUTE_DEBUGS
    if "%1" == "verbose" goto :EXECUTE_VERBOSE
    if "%1" == "" goto :EXECUTE_MAIN
    set "opts=%opts% %~1"
    shift
    goto :RUN_LOOP

:EXECUTE_JANSI
    for %%G in (jansi-*.jar) do call:APPEND_TO_CLASSPATH %%G
    shift
    goto :RUN_LOOP

:EXECUTE_JNA
    for %%G in (jna-*.jar) do call:APPEND_TO_CLASSPATH %%G
    shift
    goto :RUN_LOOP

:EXECUTE_SSH
    for %%G in (sshd-core-*.jar) do call:APPEND_TO_CLASSPATH %%G
    for %%G in (slf4j-api-*.jar) do call:APPEND_TO_CLASSPATH %%G
    for %%G in (slf4j-jdk14-*.jar) do call:APPEND_TO_CLASSPATH %%G
    shift
    goto :RUN_LOOP

:EXECUTE_DEBUG
    set "opts=%opts% -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
    shift
    goto :RUN_LOOP

:EXECUTE_DEBUGS
    set "opts=%opts% -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005"
    shift
    goto :RUN_LOOP


:EXECUTE_MAIN
popd

rem Launch gogo shell
echo Launching Gogo JLine...
echo Classpath: %cp%
java -cp %TARGETDIR%\javaz-jar-with-dependencies.jar %opts%  pl.zut.zjava.Main

:END