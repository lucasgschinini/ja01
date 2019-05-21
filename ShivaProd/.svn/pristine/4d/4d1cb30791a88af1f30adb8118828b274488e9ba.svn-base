#======================================================================================
#== PROCESO DE CREACION DE OBJETOS DE LA BASE DE DATOS DE MIGRACION SHIVA - TRUNCADO ==
#======================================================================================

export IN_USER=$1
export IN_PASS=$2
export IN_SID=$3

if [ "$IN_USER" = "" ]; then
	echo Es necesario ingresar el usuario
	echo .
	echo .
	echo Ejemplo:
	echo script.sh usuario password SID
	exit
fi

if [ "$IN_PASS" = "" ]; then
	echo Es necesario ingresar la constraseña
	echo .
	echo .
	echo Ejemplo:
	echo script.sh usuario password SID
	exit
fi

if [ "$IN_SID" = "" ]; then
	echo Es necesario ingresar el SID
	echo .
	echo .
	echo Ejemplo:
	echo script.sh usuario password SID
	exit
fi

# =====================================
# CREAR PROCEDURES
# =====================================
   sqlplus -s "${IN_USER}"/"${IN_PASS}"@"${IN_SID}" @F1_MIG_PROCEDURES.sql
