#!/bin/sh

#################### Datos de Ejecucion #######################
# Funcion:
# Periocidad:
validarArgumentoFecha (){
	local ERROR=0
	if [ -z $1 ]; then
		MENSAJE_1="Debe ingresar la Fecha en formato dd/mm/aaaa. Fecha ingresada: '$1'"
		echo ${MENSAJE_1}
		echo ${MENSAJE_1} >>$LOG_ERROR
		ERROR=1
	else
		PARTES=( $(echo $1 |tr "/" " ") )
		if (( ${#PARTES[@]} == 3 )); then
			ANIO=${PARTES[2]}
			MES=${PARTES[1]}
			DIA=${PARTES[0]}
			
			if (( $ANIO > 2000 && $ANIO < 3001 && (10#$MES) > 0 &&  (10#$MES) <= 12 && (10#$DIA) > 0 && (10#$DIA) <= 31 )); then
			
				# Find out number of days in given month
				case $((10#$MES)) in 
					1) days=31;;
					2) days=28 ;;
					3) days=31 ;;
					4) days=30 ;;
					5) days=31 ;;
					6) days=30 ;;
					7) days=31 ;;
					8) days=31 ;;
					9) days=30 ;;
					10) days=31 ;;
					11) days=30 ;;
					12) days=31 ;;
					*) days=-1;;
				esac
				 
				# find out if it is a leap year or not
				 
				if [ $((10#$MES)) -eq 2 ]; # if it is feb month then only check of leap year
				then
					if [ $(($ANIO % 4)) -ne 0 ] ; then
					   : #  not a leap year : means do nothing and use old value of days
					elif [ $(($ANIO % 400)) -eq 0 ] ; then
					   # yes, it's a leap year
					   days=29
					elif [ $(($ANIO % 100)) -eq 0 ] ; then
					   : # not a leap year do nothing and use old value of days
					else
					   # it is a leap year
					   days=29
					fi
				fi
				
				if (((10#$DIA) > $days));then
					MENSAJE_2="Dia ingresado es mayor al maximo dia del mes. Maximo dia: $days Fecha ingresada: '$1'"
					echo ${MENSAJE_2}
					echo ${MENSAJE_2} >>$LOG_ERROR
					ERROR=1
				fi
			else
				MENSAJE_3="Fecha no valida. Fecha ingresada: '$1'"
				echo ${MENSAJE_3}
				echo ${MENSAJE_3} >>$LOG_ERROR
				ERROR=1
			fi
		else
			MENSAJE_4="La fecha ingresada no contiene 3 partes separadas por una barra (/). Fecha ingresada: '$1'"
			echo ${MENSAJE_4}
			echo ${MENSAJE_4} >>$LOG_ERROR
			ERROR=1
		fi
	fi
	return $ERROR
}

#################### Validacion de fecha mes/anio  #######################
# Funcion:
# Periocidad:
validarArgumentoFechaMesAnio (){
	local ERROR=0
	if [ -z $1 ]; then
		MENSAJE_1="Debe ingresar la Fecha en formato mm/aaaa. Fecha ingresada: '$1'"
		echo ${MENSAJE_1}
		echo ${MENSAJE_1} >>$LOG_ERROR
		ERROR=1
	else
		PARTES=( $(echo $1 |tr "/" " ") )
		if (( ${#PARTES[@]} == 2 )); then
			ANIO=${PARTES[1]}
			MES=${PARTES[0]}
			
			if (( $ANIO > 2000 && $ANIO < 3001 && (10#$MES) > 0 &&  (10#$MES) <= 12 )); then
				#La fecha es valida , do nothing
				:
			else
				MENSAJE_2="Fecha no valida. Fecha ingresada: '$1'"
				echo ${MENSAJE_2}
				echo ${MENSAJE_2} >>$LOG_ERROR
				ERROR=1
			fi
		else
			MENSAJE_3="La fecha ingresada no contiene 2 partes separadas por una barra (/). Fecha ingresada: '$1'"
			echo ${MENSAJE_3}
			echo ${MENSAJE_3} >>$LOG_ERROR
			ERROR=1
		fi
	fi
	return $ERROR
}

#################### Validacion del dia de la semana igual a sabado  #######################
# Funcion:
# Periocidad:
validarArgumentoSabado(){
	validarArgumentoFecha $1
	RESULTADO=$?
	
	local ERROR=0
	if [ $RESULTADO -lt 1 ]; then
		PARTES=( $(echo $1 |tr "/" " ") )
		ANIO=${PARTES[2]}
		MES=${PARTES[1]}
		DIA=${PARTES[0]}
		DIA_CODIGO=$( date -d ${MES}/${DIA}/${ANIO} +%u )
		#El codigo 6 es el sabado
		if [ $DIA_CODIGO -lt 6 ] || [ $DIA_CODIGO -gt 6 ]; then
			MENSAJE_1="La Fecha ingresada no cae sabado. Este proceso espera que ingrese una fecha que sea sabado. Fecha ingresada: '$1'"
			echo ${MENSAJE_1}
			echo ${MENSAJE_1} >>$LOG_ERROR
			ERROR=1
		fi
	else
		#Continua con el error de la fecha
		ERROR=1
	fi
	return $ERROR
}