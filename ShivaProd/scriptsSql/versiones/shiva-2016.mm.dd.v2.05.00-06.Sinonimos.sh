FECHA_HORA=$(date +%y%m%d.%H%M%S)

# NOMBRE=shiva-2016.mm.dd.v2.05.00-06.Sinonimos
NOMBRE="${BASH_SOURCE[0]/.sh/}"
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

case ${USRTOT} in
			# Desarrollo 1
            "A001022")
						DB_USER_A=A001022
						DB_USER_Z=Z000867
						DB_LOGIN_DESCRIPTION="Desarrollo 1 - Usuarioa A"
                        ;;
			# Desarrollo 1
            "Z000867")
						DB_USER_A=A001022
						DB_USER_Z=Z000867
						DB_LOGIN_DESCRIPTION="Desarrollo 1 - Usuarioa Z"
                        ;;
			# Desarrollo 2
            "A002003")
						DB_USER_A=A002003
						DB_USER_Z=Z001940
						DB_LOGIN_DESCRIPTION="Desarrollo 2 - Usuarioa A"
                        ;;
			# Desarrollo 2
            "Z001940")
						DB_USER_A=A002003
						DB_USER_Z=Z001940
						DB_LOGIN_DESCRIPTION="Desarrollo 2 - Usuarioa Z"
                        ;;
			# Desarrollo 3
            "A000656")
						DB_USER_A=A000656
						DB_USER_Z=Z000896
						DB_LOGIN_DESCRIPTION="Desarrollo 3 - Usuarioa A"
                        ;;
			# Desarrollo 3
            "Z000896")
						DB_USER_A=A000656
						DB_USER_Z=Z000896
						DB_LOGIN_DESCRIPTION="Desarrollo 3 - Usuarioa Z"
                        ;;
			# Desarrollo 4
            "A000657")
						DB_USER_A=A000657
						DB_USER_Z=Z000897
						DB_LOGIN_DESCRIPTION="Desarrollo 4 - Usuarioa A"
                        ;;
			# Desarrollo 4
            "Z000897")
						DB_USER_A=A000657
						DB_USER_Z=Z000897
						DB_LOGIN_DESCRIPTION="Desarrollo 4 - Usuarioa Z"
						;;
			# Integracion 1
            "A001033")
						DB_USER_A=A001033
						DB_USER_Z=Z000869
						DB_LOGIN_DESCRIPTION="Integracion 1 - Usuarioa A"
                        ;;
			# Integracion 1
            "Z000869")
						DB_USER_A=A001033
						DB_USER_Z=Z000869
						DB_LOGIN_DESCRIPTION="Integracion 1 - Usuarioa Z"
                        ;;
			# Integracion 2
            "A002004")
						DB_USER_A=A002004
						DB_USER_Z=Z001941
						DB_LOGIN_DESCRIPTION="Integracion 2 - Usuarioa A"
                        ;;
			# Integracion 2
            "Z001941")
						DB_USER_A=A002004
						DB_USER_Z=Z001941
						DB_LOGIN_DESCRIPTION="Integracion 2 - Usuarioa Z"
                        ;;
			# Integracion 3
            "A000658")
						DB_USER_A=A000658
						DB_USER_Z=Z000898
						DB_LOGIN_DESCRIPTION="Integracion 3 - Usuarioa A"
                        ;;
			# Integracion 3
            "Z000898")
						DB_USER_A=A000658
						DB_USER_Z=Z000898
						DB_LOGIN_DESCRIPTION="Integracion 3 - Usuarioa Z"
                        ;;
			# Integracion 4
            "A000659")
						DB_USER_A=A000659
						DB_USER_Z=Z000901
						DB_LOGIN_DESCRIPTION="Integracion 4 - Usuarioa A"
                        ;;
			# Integracion 4
            "Z000901")
						DB_USER_A=A000659
						DB_USER_Z=Z000901
						DB_LOGIN_DESCRIPTION="Integracion 4 - Usuarioa Z"
                        ;;
			# PAU 1
            "A001034")
						DB_USER_A=A001034
						DB_USER_Z=Z000866
						DB_LOGIN_DESCRIPTION="PAU 1 - Usuarioa A"
                        ;;
			# PAU 1
            "Z000866")
						DB_USER_A=A001034
						DB_USER_Z=Z000866
						DB_LOGIN_DESCRIPTION="PAU 1 - Usuarioa Z"
                        ;;
			# PAU 2
            "A000660")
						DB_USER_A=A000660
						DB_USER_Z=Z001006
						DB_LOGIN_DESCRIPTION="PAU 2 - Usuarioa A"
                        ;;
			# PAU 2
            "Z001006")
						DB_USER_A=A000660
						DB_USER_Z=Z001006
						DB_LOGIN_DESCRIPTION="PAU 2 - Usuarioa Z"
                        ;;
			# PAU 3
            "A000661")
						DB_USER_A=A000661
						DB_USER_Z=Z001008
						DB_LOGIN_DESCRIPTION="PAU 3 - Usuarioa A"
                        ;;
			# PAU 3
            "Z001008")
						DB_USER_A=A000661
						DB_USER_Z=Z001008
						DB_LOGIN_DESCRIPTION="PAU 3 - Usuarioa Z"
                        ;;
		    *)
			FECHA_HORA=$(date +%y%m%d.%H%M%S)
			echo "$FECHA_HORA: USUARIO NO DECLARADO" >> $LOG_FILE
			echo "USUARIO NO DECLARADO"
			exit 1
			;;
esac

echo 
echo "Instancia de ejecucion    : '"$DB_INSTANCE"'"
echo "Descripcion de logueo     : '"$DB_LOGIN_DESCRIPTION"'"
echo 
echo "Usuario A de este ambiente: '"$DB_USER_A"'"
echo "Usuario Z de este ambiente: '"$DB_USER_Z"'"
echo 

echo "<<<<<<<<<<<< variables >>>>>>>>>>>>>>>" >> $LOG_FILE
echo "ORACLE_HOME: $ORACLE_HOME"  >> $LOG_FILE
echo "User: $USRTOT" >> $LOG_FILE
echo "Password: ******" >> $LOG_FILE

echo                               >> $LOG_FILE
echo "Instancia de ejecucion    : '"$DB_INSTANCE"'" >> $LOG_FILE
echo "Descripcion de logueo     : '"$DB_LOGIN_DESCRIPTION"'" >> $LOG_FILE
echo                               >> $LOG_FILE
echo "Usuario A de este ambiente: '"$DB_USER_A"'" >> $LOG_FILE
echo "Usuario Z de este ambiente: '"$DB_USER_Z"'" >> $LOG_FILE
echo                               >> $LOG_FILE

FECHA_HORA=$(date +%y%m%d.%H%M%S)
echo "$FECHA_HORA: Se validan parámetros - FIN OK" >> $LOG_FILE
echo "$FECHA_HORA: Se validan parámetros - FIN OK"

echo "----------------------------------------------------" >> $LOG_FILE
echo "----------------------------------------------------"

#INICIO EJECUCION SQL
FECHA_HORA=$(date +%y%m%d.%H%M%S)

echo "$FECHA_HORA: INICIO ---> $SQL_FILE" >> $LOG_FILE
LOGSQL=`${ORACLE_HOME}/bin/sqlplus -L $USRTOT/$PASSWORD@$DB_INSTANCE @$SQL_FILE $DB_USER_A $DB_USER_Z <<EOF
SET PAGESIZE 0 FEEDBACK OFF VERIFY OFF HEADING OFF ECHO OFF
EXIT;
EOF`
echo ${LOGSQL}
echo ${LOGSQL} >> $LOG_FILE


