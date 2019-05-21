#!/bin/sh

#################### Datos de Ejecucion #######################
# Funcion:
# Periocidad:

NOMBRE_PROCESO=batch-shiva-operacionesMasivas

#################### Configuracion ############################
ENTORNO=$AMBIENTE
PROJECT_NAME=shiva
SHIVA_BATCH_HOME=$HOME/shiva_batch
DEFAULT_SHIVA_HOME=$SHIVA_BATCH_HOME/shiva

# date %j=muestra el numero del dia del año (001..366)
FECHA=$(date +"%j")

LOG_DIR_NOMBRE=xLogsScripts
LOG_DIR=${SHIVA_BATCH_HOME}/${LOG_DIR_NOMBRE}
LOG_ARCHIVO=${LOG_DIR}/${NOMBRE_PROCESO}-$FECHA.log
LOG_ERROR=${LOG_DIR}/${NOMBRE_PROCESO}-$FECHA.error

if [ -d ${LOG_DIR} ]
then
	echo --------------- Inicio  $(date +"%D") $(date +"%T") -------------- >>$LOG_ARCHIVO	
    echo "Directorio " ${LOG_DIR} " ya existe" >>$LOG_ARCHIVO
     
	#echo "Elimino los logs viejos" >>$LOG_ARCHIVO
    find ${LOG_DIR} -mtime +30 -type f -delete
	#EXPLANATIONS
	#	./my_dir your directory (replace with your own)
	#	-mtime +10 older than 10 days
	#	-type f only files
	# 	-delete no surprise. Remove it to test before
else
    mkdir ${LOG_DIR_NOMBRE} 
	echo --------------- Inicio  $(date +"%D") $(date +"%T") -------------- >>$LOG_ARCHIVO	
	echo "Directorio $LOG_DIR creado." >>$LOG_ARCHIVO
fi

echo "Borrando los directorios FFDC" >>$LOG_ARCHIVO
rm -rf ${HOME}/FFDC
rm -rf ${SHIVA_BATCH_HOME}/FFDC
rm -rf ${DEFAULT_SHIVA_HOME}/FFDC

echo ENTORNO=${ENTORNO} >>$LOG_ARCHIVO
echo PROJECT_NAME=${PROJECT_NAME} >>$LOG_ARCHIVO

if (test "${ENTORNO}" = "desa" || test "${ENTORNO}" = "desa2" || test "${ENTORNO}" = "desa3" || test "${ENTORNO}" = "desa4")
then
	#Solo Desarrollo
	export JAVA_HOME=/home/${USER}/jdk1.7.0_25
	PATH=/home/${USER}/jdk1.7.0_25/bin:${PATH}
	echo PATH=${PATH} >>$LOG_ARCHIVO
else
	# Solo para inte, pau y prod (no tocar)
	export JAVA_HOME=/usr/java/jdk1.7.0_25/
	PATH=/usr/java/jdk1.7.0_25/bin:${PATH}
	echo PATH=${PATH} >>$LOG_ARCHIVO
fi

cd ${DEFAULT_SHIVA_HOME}
echo DEFAULT_SHIVA_HOME=${DEFAULT_SHIVA_HOME} >>$LOG_ARCHIVO

PATH_LIB=
for i in `ls ./lib/*.jar`
do
  PATH_LIB=${PATH_LIB}:${i}
done 

SHIVA_JAR=
for i in `ls ${PROJECT_NAME}*Batch.jar`
do
  SHIVA_JAR=${SHIVA_JAR}:${i}
done

CLASSPATH=${DEFAULT_SHIVA_HOME}${SHIVA_JAR}${PATH_LIB}
echo CLASSPATH=${CLASSPATH} >>$LOG_ARCHIVO

######################### Ejecucion ###########################
if (test "${ENTORNO}" != "prod")
then
	TEMP=${DEFAULT_SHIVA_HOME}/tmp
	echo TEMP=${TEMP} >>$LOG_ARCHIVO

	java -cp ".:${CLASSPATH}" -Djava.io.tmpdir=${TEMP} \
		-Djavax.net.ssl.trustStore=${JAVA_HOME}/jre/lib/security/cacerts \
		-Dentorno=${ENTORNO} -Dconfiguracion=${SHIVA_CONFIG} -DnombreLog=${NOMBRE_PROCESO} \
   			ar.com.telecom.shiva.batch.OperacionesMasivasBatchRunner >>$LOG_ERROR >>$LOG_ARCHIVO 2>>$LOG_ERROR
else
	# Solo para produccion
	java -cp ".:${CLASSPATH}" -Dentorno=${ENTORNO} -Dconfiguracion=${SHIVA_CONFIG} -DnombreLog=${NOMBRE_PROCESO} \
   		ar.com.telecom.shiva.batch.OperacionesMasivasBatchRunner >>$LOG_ERROR >>$LOG_ARCHIVO 2>>$LOG_ERROR
fi

######################### Resultado ###########################
RESULTADO=$?
echo Resultado del proceso: ${RESULTADO} >>$LOG_ARCHIVO

if [ $RESULTADO -gt 0 ]
then 
	echo "Ha obtenido errores en su ejecución" >>$LOG_ARCHIVO
	echo ---------------- Fin  $(date +"%D") $(date +"%T") ---------------- >>$LOG_ARCHIVO
	exit $RESULTADO
else
	echo "Ejecucion OK" >>$LOG_ARCHIVO
	echo ---------------- Fin  $(date +"%D") $(date +"%T") ---------------- >>$LOG_ARCHIVO
	exit 0		
fi
############################ Fin del script ###################
   