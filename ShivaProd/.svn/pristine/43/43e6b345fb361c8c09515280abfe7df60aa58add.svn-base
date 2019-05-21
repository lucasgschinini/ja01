package ar.com.telecom.shiva.base.enumeradores;

import java.util.ArrayList;
import java.util.List;



public enum EstadoTransaccionEnum {
	
	PENDIENTE("Pendiente",""),
	PENDIENTE_FINALIZACION_TRANSACCION("Pendiente de finalización de grupo anterior",""),
	APROPIADA("Apropiada",""),
	APROPIADA_SIN_COMP_SAP("Apropiada sin compensacion sap",""),
	ERROR_MEDIO_PAGO("Error en apropiación de medio de pago",""),
	ERROR_DEUDA("Error en apropiación de deuda",""),
	ERROR_MEDIO_PAGO_DEUDA("Error en apropiación de medio de pago y deuda",""),
	ERROR_TRATAMIENTO("Error en apropiación del tratamiento de diferencia",""),
	ERROR_CONFIRMACION("Error en confirmación",""),
	ERROR_DESAPROPIACION("Error en desapropiación",""),
	EN_PROCESO_APROPIACION("En Proceso de apropiación",""),
	EN_PROCESO_CONFIRMACION("En Proceso de confirmación",""),
	EN_PROCESO_DESAPROPIACION("En Proceso de desapropiación",""),
	CONFIRMADA("Confirmada",""),
	DESAPROPIADA("Desapropiada",""),
	DIFERENCIA_DE_CAMBIO_SIM("Diferencia de cambio simulacion",""),
	DIFERENCIA_DE_CAMBIO("Diferencia de cambio",""),
	
	/**
	 * nuevos estados sap4up
	 */
	PENDIENTE_CONFIRMAR_APL_MANUAL("Pendiente de Confirmar Aplicación Manual",""),
	APL_MANUAL_APROBADA("Aplicación Manual Aprobada",""),
	APL_MANUAL_APROBADA_SIN_COMP_SAP("Aplicación Manual Aprobada sin compensación sap",""),
	EN_PROCESO_DESAPROPIACION_APL_MANUAL_RECHAZADA("En proceso de Desapropiación por Aplicación Manual Rechazada",""),
	ERROR_APL_MANUAL_RECHAZADA("Error. Aplicación Manual Rechazada",""),
	SIN_PROCESAR_POR_ERROR_EN_GRUPO("Sin procesar por error en grupo",""),
	
	EN_PROCESO_DESAPROPIACION_POR_ERROR_DESAPROPIACION("En proceso de desapropiación por error en desapropiación",""),
	
	
	
	/*Estados utilizados para el proceso de descobro*/
	PENDIENTE_DESCOBRO("Pendiente de descobro",""),
	EN_PROCESO_DESCOBRO("En proceso de descobro",""),
	ERROR_DESCOBRO_DEUDA("Error en descobro de deuda","ERROR_DESCOBRO"),
	ERROR_DESCOBRO_MEDIO_PAGO("Error en descobro de medio de pago","ERROR_DESCOBRO"),
	ERROR_DESCOBRO_MEDIO_PAGO_DEUDA("Error en descobro de medio de pago y deuda","ERROR_DESCOBRO"),
	ERROR_DESCOBRO_CARGO("Error en descobro de cargo","ERROR_DESCOBRO"),
	DESCOBRO("Descobrada","");

	String descripcion;
	String tipoEstado;
	
	/**
	 * 
	 * @param descripcion
	 */
	private EstadoTransaccionEnum(String descripcion, String tipoEstado) {
	    this.descripcion = descripcion;
	    this.tipoEstado = tipoEstado;
	}
	
	/**
	 * 
	 * @return
	 */
	public String descripcion() {
	    return this.descripcion;
	}
	
	public String tipoEstado() {
		return this.tipoEstado;
	}

	/**
	 * retorna una lista con los estados "ERROR_DEUDA", "ERROR_MEDIO_PAGO", "ERROR_MEDIO_PAGO_DEUDA" y "ERROR_TRATAMIENTO".
	 * @return
	 */
	public static List<EstadoTransaccionEnum> listarEstadosError() {
		List<EstadoTransaccionEnum> listaEstadosError = new ArrayList<EstadoTransaccionEnum>();
		listaEstadosError.add(ERROR_DEUDA);
		listaEstadosError.add(ERROR_MEDIO_PAGO);
		listaEstadosError.add(ERROR_MEDIO_PAGO_DEUDA);
		listaEstadosError.add(ERROR_TRATAMIENTO);
		listaEstadosError.add(EN_PROCESO_DESAPROPIACION_APL_MANUAL_RECHAZADA);
		return listaEstadosError;
	}
	
	/**
	 * 
	 * @return
	 */
	public static List<EstadoTransaccionEnum> listarEstadosErrorSinEnProcesoDesapropiacionAplManualRechazada() {
		List<EstadoTransaccionEnum> listaEstadosError = new ArrayList<EstadoTransaccionEnum>();
		listaEstadosError.add(ERROR_DEUDA);
		listaEstadosError.add(ERROR_MEDIO_PAGO);
		listaEstadosError.add(ERROR_MEDIO_PAGO_DEUDA);
		listaEstadosError.add(ERROR_TRATAMIENTO);
		return listaEstadosError;
	}
	
	/**
	 * retorna una lista con los estados de ERROR del descobro.
	 * @return
	 */
	public static List<EstadoTransaccionEnum> listarEstadosErrorDescobro() {
		List<EstadoTransaccionEnum> listaEstadosError = new ArrayList<EstadoTransaccionEnum>();
		listaEstadosError.add(ERROR_DESCOBRO_DEUDA);
		listaEstadosError.add(ERROR_DESCOBRO_MEDIO_PAGO);
		listaEstadosError.add(ERROR_DESCOBRO_MEDIO_PAGO_DEUDA);
		listaEstadosError.add(ERROR_DESCOBRO_CARGO);
		return listaEstadosError;
	}
	
	public static List<EstadoTransaccionEnum> listarEstadosDescobroNoDescobrado() {
		List<EstadoTransaccionEnum> listaEstadosError = new ArrayList<EstadoTransaccionEnum>();
		listaEstadosError.add(ERROR_DESCOBRO_DEUDA);
		listaEstadosError.add(ERROR_DESCOBRO_MEDIO_PAGO);
		listaEstadosError.add(ERROR_DESCOBRO_MEDIO_PAGO_DEUDA);
		listaEstadosError.add(ERROR_DESCOBRO_CARGO);
		listaEstadosError.add(PENDIENTE_DESCOBRO);
		listaEstadosError.add(EN_PROCESO_DESCOBRO);
		return listaEstadosError;
	}
	
	/**
	 * Se retorno los estados que se deberan descartar para evitar la simulacion/imputacion del proceso de reversion.-
	 * @return
	 */
	public static List<EstadoTransaccionEnum> listarEstadosErrorParaDescobro() {
		List<EstadoTransaccionEnum> listaEstadosError = new ArrayList<EstadoTransaccionEnum>();
		listaEstadosError.add(ERROR_DEUDA);
		listaEstadosError.add(ERROR_MEDIO_PAGO);
		listaEstadosError.add(ERROR_MEDIO_PAGO_DEUDA);
		listaEstadosError.add(ERROR_TRATAMIENTO);
		listaEstadosError.add(ERROR_CONFIRMACION);
		listaEstadosError.add(ERROR_DESAPROPIACION);
		listaEstadosError.add(DESAPROPIADA);
		listaEstadosError.add(ERROR_APL_MANUAL_RECHAZADA);
		listaEstadosError.add(SIN_PROCESAR_POR_ERROR_EN_GRUPO);
		
		return listaEstadosError;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static EstadoTransaccionEnum getEnumByName(String name) {
		for (EstadoTransaccionEnum estado : EstadoTransaccionEnum.values()) {
			if (estado.name().equalsIgnoreCase(name)) {
				return estado;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static List<EstadoTransaccionEnum> getEnumByTipoEstado(String tipoEstado) {
		
		List<EstadoTransaccionEnum> listaEstadosError = new ArrayList<EstadoTransaccionEnum>();
		for (EstadoTransaccionEnum estado : EstadoTransaccionEnum.values()) {
			if (estado.tipoEstado().equalsIgnoreCase(tipoEstado)) {
				listaEstadosError.add(estado);
			}
		}
		return listaEstadosError;
	}	
	
	/**
	 * 
	 * @param descripcion
	 * @return
	 */
	public static EstadoTransaccionEnum getEnumByDescripcion(String descripcion) {
		
		for (EstadoTransaccionEnum estado : EstadoTransaccionEnum.values()) {
			if (estado.descripcion().equalsIgnoreCase(descripcion)) {
				return estado;
			}
		}
		return null;
	}	
}
