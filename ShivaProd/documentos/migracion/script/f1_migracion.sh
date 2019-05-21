#===================================================================================
#= CREACION DEL ARCHIVO: 21-03-2014 VERSION: 1.0 HECHO POR: DSB       =
#===================================================================================
# EL OBJETIVO DE ESTE ARCHIVO ES REALIZAR DE FORMA AUTOMATICA LA CARGA DE LAS TABLAS
# DE LA FASE 1 PARA MIGRACION DE VALORES
#===================================================================================

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

NOW=`date +%y%m%d%H%M%S`


#===================================================================================
#= Generar en el server Nuevos Archivos Corrigiendo diferencias en Caracteres unix =
#===================================================================================
#dos2ux ../load/data/REGISTRO_AVC_ARCHIVOS.txt > ../load/data/REGISTRO_AVC_ARCHIVOS.dat
#dos2ux ../load/data/REGISTRO_AVC_DEPOSITO_EFECTIVO.txt > ../load/data/REGISTRO_AVC_DEPOSITO_EFECTIVO.dat
#dos2ux ../load/data/REGISTRO_AVC_CHEQUE.txt > ../load/data/REGISTRO_AVC_CHEQUE.dat
#dos2ux ../load/data/REGISTRO_AVC_CHEQUE_DIFERIDO.txt > ../load/data/REGISTRO_AVC_CHEQUE_DIFERIDO.dat
#dos2ux ../load/data/REGISTRO_AVC_TRANSFERENCIA.txt > ../load/data/REGISTRO_AVC_TRANSFERENCIA.dat
#dos2ux ../load/data/BOLETA_SIN_VALOR.txt > ../load/data/BOLETA_SIN_VALOR.dat
#dos2ux ../load/data/BOLETA_DEPOSITO_CHEQUE.txt > ../load/data/BOLETA_DEPOSITO_CHEQUE.dat
#dos2ux ../load/data/BOLETA_DEPOSITO_CHEQUE_DIFERIDO.txt > ../load/data/BOLETA_DEPOSITO_CHEQUE_DIFERIDO.dat
#dos2ux ../load/data/BOLETA_DEPOSITO_EFECTIVO.txt > ../load/data/BOLETA_DEPOSITO_EFECTIVO.dat
#dos2ux ../load/data/VALOR_CHEQUE.txt > ../load/data/VALOR_CHEQUE.dat
#dos2ux ../load/data/VALOR_CHEQUE_DIFERIDO.txt > ../load/data/VALOR_CHEQUE_DIFERIDO.dat
#dos2ux ../load/data/VALOR_EFECTIVO.txt > ../load/data/VALOR_EFECTIVO.dat
#dos2ux ../load/data/VALOR_INTERDEPOSITO.txt > ../load/data/VALOR_INTERDEPOSITO.dat
#dos2ux ../load/data/VALOR_TRANSFERENCIA.txt > ../load/data/VALOR_TRANSFERENCIA.dat

#=============================================================
#= Proceso de Carga para cada Tabla =
#=============================================================
#-> REGISTRO_AVC_ARCHIVOS
sqlldr "${IN_USER}"/"${IN_PASS}"@"${IN_SID}" control=../load/ctl/REGISTRO_AVC_ARCHIVOS.ctl data=../load/data/REGISTRO_AVC_ARCHIVOS.txt log=../load/log/REGISTRO_AVC_ARCHIVOS.log bad=../load/log/REGISTRO_AVC_ARCHIVOS.bad errors=850000
#-> REGISTRO_AVC_DEPOSITO_EFECTIVO
sqlldr "${IN_USER}"/"${IN_PASS}"@"${IN_SID}" control=../load/ctl/REGISTRO_AVC_DEPOSITO_EFECTIVO.ctl data=../load/data/REGISTRO_AVC_DEPOSITO_EFECTIVO.txt log=../load/log/REGISTRO_AVC_DEPOSITO_EFECTIVO.log bad=../load/log/REGISTRO_AVC_DEPOSITO_EFECTIVO.bad errors=850000
#-> REGISTRO_AVC_CHEQUE
sqlldr "${IN_USER}"/"${IN_PASS}"@"${IN_SID}" control=../load/ctl/REGISTRO_AVC_CHEQUE.ctl data=../load/data/REGISTRO_AVC_CHEQUE.txt log=../load/log/REGISTRO_AVC_CHEQUE.log bad=../load/log/REGISTRO_AVC_CHEQUE.bad errors=850000
#-> REGISTRO_AVC_CHEQUE_DIFERIDO
sqlldr "${IN_USER}"/"${IN_PASS}"@"${IN_SID}" control=../load/ctl/REGISTRO_AVC_CHEQUE_DIFERIDO.ctl data=../load/data/REGISTRO_AVC_CHEQUE_DIFERIDO.txt log=../load/log/REGISTRO_AVC_CHEQUE_DIFERIDO.log bad=../load/log/REGISTRO_AVC_CHEQUE_DIFERIDO.bad errors=850000
#-> REGISTRO_AVC_TRANSFERENCIA
sqlldr "${IN_USER}"/"${IN_PASS}"@"${IN_SID}" control=../load/ctl/REGISTRO_AVC_TRANSFERENCIA.ctl data=../load/data/REGISTRO_AVC_TRANSFERENCIA.txt log=../load/log/REGISTRO_AVC_TRANSFERENCIA.log bad=../load/log/REGISTRO_AVC_TRANSFERENCIA.bad errors=850000
#-> BOLETA_SIN_VALOR
sqlldr "${IN_USER}"/"${IN_PASS}"@"${IN_SID}" control=../load/ctl/BOLETA_SIN_VALOR.ctl data=../load/data/BOLETA_SIN_VALOR.txt log=../load/log/BOLETA_SIN_VALOR.log bad=../load/log/BOLETA_SIN_VALOR.bad errors=850000
#-> BOLETA_DEPOSITO_CHEQUE
sqlldr "${IN_USER}"/"${IN_PASS}"@"${IN_SID}" control=../load/ctl/BOLETA_DEPOSITO_CHEQUE.ctl data=../load/data/BOLETA_DEPOSITO_CHEQUE.txt log=../load/log/BOLETA_DEPOSITO_CHEQUE.log bad=../load/log/BOLETA_DEPOSITO_CHEQUE.bad errors=850000
#-> BOLETA_DEPOSITO_CHEQUE_DIFERIDO
sqlldr "${IN_USER}"/"${IN_PASS}"@"${IN_SID}" control=../load/ctl/BOLETA_DEPOSITO_CHEQUE_DIFERIDO.ctl data=../load/data/BOLETA_DEPOSITO_CHEQUE_DIFERIDO.txt log=../load/log/BOLETA_DEPOSITO_CHEQUE_DIFERIDO.log bad=../load/log/BOLETA_DEPOSITO_CHEQUE_DIFERIDO.bad errors=850000
#-> BOLETA_DEPOSITO_EFECTIVO
sqlldr "${IN_USER}"/"${IN_PASS}"@"${IN_SID}" control=../load/ctl/BOLETA_DEPOSITO_EFECTIVO.ctl data=../load/data/BOLETA_DEPOSITO_EFECTIVO.txt log=../load/log/BOLETA_DEPOSITO_EFECTIVO.log bad=../load/log/BOLETA_DEPOSITO_EFECTIVO.bad errors=850000
#-> VALOR_CHEQUE
sqlldr "${IN_USER}"/"${IN_PASS}"@"${IN_SID}" control=../load/ctl/VALOR_CHEQUE.ctl data=../load/data/VALOR_CHEQUE.txt log=../load/log/VALOR_CHEQUE.log bad=../load/log/VALOR_CHEQUE.bad errors=850000
#-> VALOR_CHEQUE_DIFERIDO
sqlldr "${IN_USER}"/"${IN_PASS}"@"${IN_SID}" control=../load/ctl/VALOR_CHEQUE_DIFERIDO.ctl data=../load/data/VALOR_CHEQUE_DIFERIDO.txt log=../load/log/VALOR_CHEQUE_DIFERIDO.log bad=../load/log/VALOR_CHEQUE_DIFERIDO.bad errors=850000
#-> VALOR_EFECTIVO
sqlldr "${IN_USER}"/"${IN_PASS}"@"${IN_SID}" control=../load/ctl/VALOR_EFECTIVO.ctl data=../load/data/VALOR_EFECTIVO.txt log=../load/log/VALOR_EFECTIVO.log bad=../load/log/VALOR_EFECTIVO.bad errors=850000
#-> VALOR_INTERDEPOSITO
sqlldr "${IN_USER}"/"${IN_PASS}"@"${IN_SID}" control=../load/ctl/VALOR_INTERDEPOSITO.ctl data=../load/data/VALOR_INTERDEPOSITO.txt log=../load/log/VALOR_INTERDEPOSITO.log bad=../load/log/VALOR_INTERDEPOSITO.bad errors=850000
#-> VALOR_TRANSFERENCIA
sqlldr "${IN_USER}"/"${IN_PASS}"@"${IN_SID}" control=../load/ctl/VALOR_TRANSFERENCIA.ctl data=../load/data/VALOR_TRANSFERENCIA.txt log=../load/log/VALOR_TRANSFERENCIA.log bad=../load/log/VALOR_TRANSFERENCIA.bad errors=850000