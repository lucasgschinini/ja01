package ar.com.telecom.shiva.base.enumeradores;

import java.util.ArrayList;
import java.util.List;

public enum TipoCreditoEnum {
	/*
	01	CHEQUES PROPIOS
	02	CHEQUES DIFERIDOS
	03	BOLETA DE DEPOSITO
	05	TRANSF. BANCARIA
	06	COMPENSACION / INTERCOMPANY / LP
	07	IMP. MUN. SEG. HIG
	08	IMPUESTO AL SELLO
	09	RET SEG SEC
	10	RETENCION GANANCIAS
	11	RETENCION IVA
	12	RETENCION IIBB
	13	PROXIMA FACTURA (MISC)
	14	ENVIO A GANANCIAS
	15	REINTEGRO POR GIRO
	16	DESISTIMIENTO
	17	INTERDEPOSITOS
	19	REMA NO ACTUALIZABLE
	20	REMANEN. ACTUALIZADO
	21	PAGO CUENTA CALIPSO
	22	NOTA DE CREDITO
	23	IVA RG3349 (PNUD)
	24	IMPUESTO TASAS MUNICIPALES
	25	PLANPAGO
	26	REINTEGRO POR CHEQUE
	27	REINTEGRO CREDITO RED INTELIGENTE
	28	DEBITO PROXIMA FACTURA (CALIPSO)
	29	DEBITO PROXIMA FACTURA (MIC)
	30	REINTEGRO TRANSFERENCIA BANCARIA
	31	CREDITO PROXIMA FACTURA (CALIPSO)
	32	CREDITO PROXIMA FACTURA (MIC)
	33	OTRAS COMPENSACIONES
	34	COMPENSACION INTERCOMPANY
	35	COMPENSACION LIQUIDO PRODUCTO
	36	NOTA DE CREDITO PENDIENTE DE EMISION
	39  COMPENSACION IIBB
	40  COMPENSACION CESION CREDITOS
	41  COMPENSACION PROVEEDORES
	42	SALDO A PAGAR
	43	SALDO A COBRAR
	*/
	
	// SHIVA
	BOLETA_DEPOSITO_CHEQUE("01", "CHEQ", "Cheque", "2", "2,5"),
	BOLETA_DEPOSITO_CHEQUEDIF("02", "CHEQDIF", "Cheque Diferido", "3", "3,6"),
	BOLETA_DEPOSITO_EFECTIVO("03", "EFE", "Efectivo", "4", "4,7"),
	CHEQUE("01", "CHEQ", "Cheque", "5", "2,5"),
	CHEQUEDIF("02", "CHEQDIF", "Cheque Diferido", "6", "3,6"),
	EFECTIVO("03", "EFE", "Efectivo", "7", "4,7"),
	TRANSFERENCIA("05", "TRANS", "Transferencia", "8", "8"),
	INTERDEPOSITO("17", "INTER", "Interdepósito", "9", "9"),
	RETENCION("", "RET", "Retencion/Impuesto", "10", "10"),
	//
	IMPUESTO_MUNICIPAL_SEGURIDAD_E_HIGIENGE(
			TipoRetencionEnum.IMPUESTO_MUNICIPAL_SEGURIDAD_E_HIGIENGE.getIdTipoMedioPago(), "RET", "Retencion/Impuesto", "10", "10"),
	IMPUESTO_AL_SELLO(
			TipoRetencionEnum.IMPUESTO_AL_SELLO.getIdTipoMedioPago(), "RET", "Retencion/Impuesto", "10", "10"),
	RETENCION_SEGURIDAD_SOCIAL(
			TipoRetencionEnum.RETENCION_SEGURIDAD_SOCIAL.getIdTipoMedioPago(), "RET", "Retencion/Impuesto", "10", "10"),
	RETENCION_GANANCIA(
			TipoRetencionEnum.RETENCION_GANANCIA.getIdTipoMedioPago(), "RET", "Retencion/Impuesto", "10", "10"),
	RETENCION_IVA(
			TipoRetencionEnum.RETENCION_IVA.getIdTipoMedioPago(), "RET", "Retencion/Impuesto", "10", "10"),
	RETENCION_IIBB(
			TipoRetencionEnum.RETENCION_IIBB.getIdTipoMedioPago(), "RET", "Retencion/Impuesto", "10", "10"),
	RETENCION_IVA_RG3349(
			TipoRetencionEnum.RETENCION_IVA_RG3349.getIdTipoMedioPago(), "RET", "Retencion/Impuesto", "10", "10"),
	IMPUESTO_TASAS_MUNICIPALES(
			TipoRetencionEnum.IMPUESTO_TASAS_MUNICIPALES.getIdTipoMedioPago(), "RET", "Retencion/Impuesto", "10", "10"),
	// 
	// CALIPSO / MIC
	NOTACREDITO("22", "CRE", "Nota de Crédito", "", ""),
	PAGOACUENTA("21", "CTA", "Pago a Cuenta", "", ""),
	REMANENTE("", "REM", "Remanente", "", ""),
	//
	REMANENTE_ACTUALIZABLE(
			TipoRemanenteEnum.idMedioPago_Remanente_Actualizable(), "REM", "Remanente", "", ""),
	REMANENTE_NO_ACTUALIZABLE(
			TipoRemanenteEnum.idMedioPago_Remanente_NO_Actualizable(), "REM", "Remanente", "", ""),
	//
	// OTROS MEDIOS DE PAGO
	DESISTIMIENTO("16", "16", "Desistimiento", "", ""),
	PLANPAGO("25", "25", "Plan de Pago", "", ""),
	OTRAS_COMPENSACIONES("33", "33", "Otras Compensaciones", "", ""),
	LIQUIDOPRODUCTO("35", "35", "Compensación Liquido Producto", "", ""),
	INTERCOMPANY("34", "34", "Compensación Intercompany", "", ""),
	IIBB("39","39","Compensación IIBB","",""),
	CESION_CREDITOS("40","40","Compensación Cesión Créditos","",""),
	PROVEEDORES("41","41","Compensación Proveedores","",""),
	//NOTACREDPENDEMI("36", "36", "Nota de Crédito pendiente de Emisión", ""); // Por decisiones del sprint 5, se usara en el futuro
	
	SALDO_A_COBRAR("43", "43", "Saldo a Cobrar", "", "");
	
	String idTipoMedioPago;
	String valor;
	String descripcion;
	String idTipoValor;
	String grupoIdTipoValor;
	
	private TipoCreditoEnum(String idTipoMedioPago, String valor, String descripcion, String idTipoValor, String grupoIdTipoValor) {
		this.idTipoMedioPago = idTipoMedioPago;
		this.valor = valor;
		this.descripcion = descripcion;
		this.idTipoValor = idTipoValor;
		this.grupoIdTipoValor = grupoIdTipoValor;
	}

	/**
	 * 
	 * @return
	 */
	public String getIdTipoMedioPago() {
		return idTipoMedioPago;
	}
	
	
	public String getValor() {
		return valor;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public String getIdTipoValor() {
		return idTipoValor;
	}
	
	public String grupoIdTipoValores() {
		return grupoIdTipoValor;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public static TipoCreditoEnum getEnumByName(String name) {

		for (TipoCreditoEnum enumerador : TipoCreditoEnum.values()) {
			if (enumerador.name().equalsIgnoreCase(name)) {
				return enumerador;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param idTipoMedioPago
	 * @return
	 */
	public static TipoCreditoEnum getEnumByIdTipoMedioPago(String idTipoMedioPago) {

		for (TipoCreditoEnum enumerador : TipoCreditoEnum.values()) {
			if (enumerador.getIdTipoMedioPago().equalsIgnoreCase(idTipoMedioPago)) {
				return enumerador;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param valor
	 * @return
	 */
	public static TipoCreditoEnum getEnumByValor(String valor) {

		for (TipoCreditoEnum enumerador : TipoCreditoEnum.values()) {
			if (enumerador.getValor().equalsIgnoreCase(valor)) {
				return enumerador;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param descripcion
	 * @return
	 */
	public static TipoCreditoEnum getEnumByDescripcion(String descripcion) {

		for (TipoCreditoEnum enumerador : TipoCreditoEnum.values()) {
			if (enumerador.getDescripcion().equalsIgnoreCase(descripcion)) {
				return enumerador;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param idTipoValor
	 * @return
	 */
	public static TipoCreditoEnum getEnumByIdTipoValor(String idTipoValor) {

		for (TipoCreditoEnum enumerador : TipoCreditoEnum.values()) {
			if (enumerador.getIdTipoValor().equalsIgnoreCase(idTipoValor)) {
				return enumerador;
			}
		}
		return null;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public static List<TipoCreditoEnum> getEnumCreditos(){
		List<TipoCreditoEnum> lista = new ArrayList<TipoCreditoEnum>();
		lista.add(CHEQUE);
		lista.add(CHEQUEDIF);
		lista.add(EFECTIVO);
		lista.add(INTERDEPOSITO);
		lista.add(NOTACREDITO);
		lista.add(PAGOACUENTA);
		lista.add(REMANENTE);	// Puedo usar cualquier tipo de remanente, ya que utilizo el 'valor' que es el mismo para todos
		lista.add(RETENCION);			// Puedo usar cualquier tipo de remanente, ya que utilizo el 'valor' que es el mismo para todos
		lista.add(TRANSFERENCIA);
		
		return lista;
	}
	
	/**
	 * 
	 * @return
	 */
	public static List<TipoCreditoEnum> getEnumMediosPagos(){
		List<TipoCreditoEnum> lista = new ArrayList<TipoCreditoEnum>();
		lista.add(DESISTIMIENTO);
		lista.add(PLANPAGO);
		lista.add(OTRAS_COMPENSACIONES);
		lista.add(LIQUIDOPRODUCTO);
		lista.add(INTERCOMPANY);
		lista.add(IIBB);
		lista.add(CESION_CREDITOS);
		lista.add(PROVEEDORES);
		//lista.add(NOTACREDPENDEMI); // Por decisiones del sprint 5, se usara en el futuro
		return lista;
	}
}
