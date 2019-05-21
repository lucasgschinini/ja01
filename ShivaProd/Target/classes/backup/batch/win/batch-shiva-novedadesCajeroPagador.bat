@echo off

if "%OS%"=="Windows_NT" @setlocal enableextensions enabledelayedexpansion

set PROJECT_NAME=shiva
set DEFAULT_SHIVA_HOME=%~dp0

if "%SHIVA_HOME%"=="" set SHIVA_HOME=%DEFAULT_SHIVA_HOME%%PROJECT_NAME%
set DEFAULT_SHIVA_HOME=
echo SHIVA_HOME=%SHIVA_HOME%

set PATH_LIB=
for %%f in (%SHIVA_HOME%/lib/*.jar) do (	 
     set PATH_LIB=!PATH_LIB!%SHIVA_HOME%\lib\%%~nxf;
)
echo PATH_LIB=%PATH_LIB%

set SHIVA_JAR=
for %%f in (%SHIVA_HOME%/%PROJECT_NAME%-*Batch.jar) do (	 
     set SHIVA_JAR=%~dp0%PROJECT_NAME%\%%~nxf
)
echo SHIVA_JAR=%SHIVA_JAR%

set CLASSPATH=%SHIVA_HOME%;%SHIVA_JAR%;%PATH_LIB%
echo CLASSPATH=%CLASSPATH%
echo JAVA_HOME=%JAVA_HOME%

java -cp %CLASSPATH% -Dentorno=desa -Dcom.ibm.msg.client.commonservices.ffst.suppress=-1 ar.com.telecom.shiva.batch.InformarNovedadesCajeroPagadorBatchRunner

if "%OS%"=="Windows_NT" @endlocal
