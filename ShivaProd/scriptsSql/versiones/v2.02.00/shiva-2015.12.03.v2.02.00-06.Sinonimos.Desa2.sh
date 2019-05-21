FECHA_HORA=$(date +%y%m%d.%H%M%S)

NOMBRE=shiva-2015.12.03.v2.02.00-06.Sinonimos.Desa2
export LOG_FILE=$NOMBRE.$FECHA_HORA.log
export SQL_FILE=$NOMBRE.sql

echo "SQL_FILE:" $SQL_FILE
echo "HOSTNAME:" ${HOSTNAME}

case ${HOSTNAME} in
            "dlnx0162")
						DB_INSTANCE=DSHIDE01
                        ;;
            "dlnx0162.telecom.com.ar")
						DB_INSTANCE=DSHIDE01
                        ;;
			"dlnx0164")
						DB_INSTANCE=DSHIIN01
                        ;;
			"dlnx0164.telecom.com.ar")
						DB_INSTANCE=DSHIIN01
                        ;;
			"dlnx0166")
						DB_INSTANCE=DSHIPA01
                        ;;						
			"dlnx0166.telecom.com.ar")
						DB_INSTANCE=DSHIPA01
                        ;;						
            "plnx0271")
                        DB_INSTANCE=POSHIPR1
                        ;;
            "plnx0271.telecom.com.ar")
                        DB_INSTANCE=POSHIPR1
                        ;;
                      *)
                        FECHA_HORA=$(date +%y%m%d.%H%M%S)
						echo "$FECHA_HORA: SERVIDOR NO DECLARADO" >> $LOG_FILE
						echo "SERVIDOR NO DECLARADO"
                        exit 1
                        ;;
esac


if [ "$1" != "" ]; then
    echo "User: " $1
	USRTOT="$1"
	echo "Seteo de [user] OK"
else
    echo "$FECHA_HORA: ERROR --> el script debe ejecutarse con usuario y contraseña de BD ($DB_INSTANCE)" >> $LOG_FILE
	echo "$FECHA_HORA: ERROR --> Error de parámetros: el script debe ejecutarse con usuario y contraseña de BD ($DB_INSTANCE)"
	echo ">>>> Modo de uso: sh $REQ_CAMBIO.sh  [user] ['password']" >> $LOG_FILE
	echo ">>>> Modo de uso: sh $REQ_CAMBIO.sh  [user] ['password']"
	echo ">>>> NOTA: el password debe ingresarse encomillado. Ej: 'password'"
	echo "$FECHA_HORA: FIN NOK. REVISAR LOG --> $LOG_FILE"
	exit 1
fi

if [ "$2" != "" ]; then
    echo "Password: '"$2"'"
	PASSWORD="$2"
	echo "Seteo de [password] OK"
else
    echo "$FECHA_HORA: ERROR --> el script debe ejecutarse con usuario y contraseña de BD ($DB_INSTANCE). Solo se ha ingresado usuario" >> $LOG_FILE
	echo "$FECHA_HORA: ERROR --> Error de parámetros: el script debe ejecutarse con usuario y contraseña de BD ($DB_INSTANCE). Solo se ha ingresado usuario"
	echo ">>>> Modo de uso: sh $REQ_CAMBIO.sh  [user] ['password']" >> $LOG_FILE
	echo ">>>> Modo de uso: sh $REQ_CAMBIO.sh  [user] ['password']"
	echo ">>>> NOTA: el password debe ingresarse encomillado. Ej: 'password'"
	echo "$FECHA_HORA: FIN NOK. REVISAR LOG --> $LOG_FILE"
    exit 1
fi

echo "<<<<<<<<<<<< variables >>>>>>>>>>>>>>>" >> $LOG_FILE
echo "ORACLE_HOME: $ORACLE_HOME"  >> $LOG_FILE
echo "User: $USRTOT" >> $LOG_FILE
echo "Password: ******" >> $LOG_FILE

FECHA_HORA=$(date +%y%m%d.%H%M%S)
echo "$FECHA_HORA: Se validan parámetros - FIN OK" >> $LOG_FILE
echo "$FECHA_HORA: Se validan parámetros - FIN OK"

echo "----------------------------------------------------" >> $LOG_FILE
echo "----------------------------------------------------"

#INICIO EJECUCION SQL
FECHA_HORA=$(date +%y%m%d.%H%M%S)

echo "$FECHA_HORA: INICIO ---> $SQL_FILE" >> $LOG_FILE
LOGSQL=`${ORACLE_HOME}/bin/sqlplus -L $USRTOT/$PASSWORD@$DB_INSTANCE @$SQL_FILE  <<EOF
SET PAGESIZE 0 FEEDBACK OFF VERIFY OFF HEADING OFF ECHO OFF
EXIT;
EOF`
echo ${LOGSQL}
echo ${LOGSQL} >> $LOG_FILE


