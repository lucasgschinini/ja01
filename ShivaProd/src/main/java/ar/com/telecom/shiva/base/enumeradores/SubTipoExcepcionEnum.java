package ar.com.telecom.shiva.base.enumeradores;

import java.util.Arrays;
import java.util.List;

import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;


public enum SubTipoExcepcionEnum {
	COK("Cobro confirmado", 
		Arrays.asList(
			new Estado[] {
				Estado.COB_COBRADO
		})
	),
	CEP("Cobro en proceso", 
		Arrays.asList(
			new Estado[] {
				Estado.COB_PROCESO,
				Estado.COB_PEND_PROCESAR_MIC,
				Estado.COB_PENDIENTE_MIC,
				Estado.COB_PENDIENTE
		})
	),
	CCE("Cobro confirmado con error", Arrays.asList(
		new Estado[] {
			Estado.COB_CONFIRMADO_CON_ERROR
		})
	),
	CEC("Cobro en error de confirmación", Arrays.asList(
		new Estado[] {
			Estado.COB_ERROR_CONFIRMACION
		})	
	),
	ROK("Reversion de cobro confirmada", Arrays.asList(
		new Estado[] {
			Estado.DES_DESCOBRADO
		})
	),
	REP("Reversión de cobro en proceso", Arrays.asList(
		new Estado[] {
			Estado.DES_DESCOBRO_PROCESO,
			Estado.DES_PEND_PROCESAR_MIC,
			Estado.DES_PENDIENTE_MIC,
			Estado.DES_PENDIENTE
		})
	),
	RCE("Reversión de cobro en error", Arrays.asList(
		new Estado[] {
			Estado.DES_EN_ERROR
	}));

	String descripcion;
	List<Estado> estados;

	private SubTipoExcepcionEnum(String descripcion, List<Estado> estados) {
		this.descripcion = descripcion;
		this.estados = estados;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDescripcion() {
	    return this.descripcion;
	}

	public static SubTipoExcepcionEnum getEnumByName(String name) {
		for (SubTipoExcepcionEnum enumerado : SubTipoExcepcionEnum.values()) {
			if (enumerado.name().equalsIgnoreCase(name)) {
				return enumerado;
			}
		}
		return null;
	}
	public static SubTipoExcepcionEnum getEnumByEstadoCobro(Estado estado) {
		for (SubTipoExcepcionEnum enumerado : SubTipoExcepcionEnum.values()) {
			if (enumerado.estados.contains(estado)) {
				return enumerado;
			}
		}
		return null;
	}
}
