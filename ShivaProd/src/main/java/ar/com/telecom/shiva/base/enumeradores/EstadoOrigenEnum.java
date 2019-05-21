package ar.com.telecom.shiva.base.enumeradores;

import java.util.ArrayList;
import java.util.List;

public enum EstadoOrigenEnum {
	
	//MIC - estadoFactura --> estadoOrigen
	ENV_AL_CLIENTE("01", "ENV. AL CLIENTE"),
	AVISO_DE_INCOMUNIC("20", "AVISO DE INCOMUNIC."),
	IMPAGO_AF_NO_INCOMP("21", "IMPAGO A/F NO INCOM."),
	SUSP_PROC_INCOMUNI("23", "SUSP. PROC. INCOMUNI"),
	IMPAGO_GEST_PERSONAL("24", "IMPAGO GEST. PERSONAL"),
	AVISO_INC_GP_AUT("25", "AVISO INC. G/P AUT."),
	AVISO_INC_GP("26", "AVISO INC. G/P"),
	INC_GP_AUTORIZADO("28", "INC. G/P AUTORIZADO"),
	ORDEN_INC_GENERADA("29", "ORDEN INC. GENERADA"),
	REAB_PEND_COBRO_GEN("30", "REAB.PEND.COBRO GEN"),
	REAB_PEND_COBRO("31", "REAB.PEND.COBRO"),
	ORD_INC_GENERADA_GP("33", "ORD.INC.GENERADA G/P"),
	BAJA_GESTIO_PERS_AUT("4A", "BAJA GESTIO.PERS.AUT"),
	ORD_BAJ_GES_PERS_GEN("4B", "ORD.BAJ.GES.PERS.GEN"),
	INCOMUNICADO("40", "INCOMUNICADO"),
	ORDEN_BAJA_GENERADA("43", "ORDEN BAJA GENERADA"),
	AVISO_BAJA_ENVIADO("44", "AVISO BAJA ENVIADO"),
	ACUSE_DE_RECIBO("45", "ACUSE DE RECIBO"),
	INCOMUNICADO_GP("46", "INCOMUNICADO G/P"),
	AVISO_BAJA_GP_AUT("47", "AVISO BAJA G/P AUT"),
	AVISO_BAJA_GP("48", "AVISO BAJA G/P"),
	ACUSE_RES_GES_PERSON("49", "ACUSE.REC.GES.PERSON"),
	BAJA("50", "BAJA"),
	IMPAGO_FACT_ESPECIAL("72", "IMPAGO FACT.ESPECIAL"),
	MIGRADA("55", "MIGRADA"),
	FACTURADA("00", "FACTURADA"),
	ENV_A_DA("03", "ENV. A D/A"),
	CONVENIO_PAGO_CUOTAS("60", "CONVENIO PAGO CUOTAS"),
	ANULADA("70", "ANULADA"),
	COMPENSADORA("71", "COMPENSADORA"),
	COBRADA_POR_DA("10", "COBRADA POR D/A"),
	COBRADA_OF_TECO("11", "COBRADA OF. TECO"),
	COBRADA_EN_BANCO("12", "COBRADA EN BANCO"),
	
	//MIC - estdoDUC --> estadoOrigen
	GENERADO("G", "GENERADO"),
	ENVIADO("E", "ENVIADO"),
	VENCIDO("V", "VENCIDO"),
	CONV_BAJA_INEX("B", "CONV. EN BAJA/INEX"),
	COBRADO("C", "COBRADO"),
	COBRADO_Y_APLIC_REF("A", "COBRADO Y APLIC.REF."),
	COBRADO_Y_MOV_RTE_I("M", "COBRADO Y MOV. RTE I"),
	COBRADO_Y_TRANSFER("T", "COBRADO Y TRANSFER."),
	COBRADO_Y_ENV_FACT("I", "COBRADO Y ENV. FACT"),
	COBRADO_Y_UTILIZADO("U", "COBRADO Y UTILIZADO"),
	COBRO_REVERTIDO("R", "COBRO REVERTIDO"),
	//agregado en sprint 5
	APROPIADO_SHIVA("Z", "APROPIADO SHIVA"),
		
	//Calipso - morosidad --> estadoOrigen
	DESISTIDO("","DESISTIDO"),
	CONCURSO("","CONCURSO"),
	FINANCIADA("","FINANCIADA"),
	INCOBRABLE("","INCOBRABLE"),
	INCOBRABLES("","INCOBRABLES"),
	LEGALES("","LEGALES"),
	NO_INNOVAR("","NO INNOVAR"),
	QUIEBRA("","QUIEBRA"),
	ADM("","ADM"),
	CARTA_COMERCIAL("","CARTA COMERCIAL"),
	PRIMER_CARTA_DOC("","PRIMER CARTA DOC"),
	SEGUNDA_CARTA_DOC("","SEGUNDA CARTA DOC"),
	WATERMARK("","WATERMARK"),
	
	//Shiva - estadoValor --> estadoOrigen
	ANULADO("","Anulado"),                   
	AVISO_DE_PAGO_PENDIENTE_DE_CONFIRMAR("","Aviso de Pago Pendiente Confirmar"),
	AVISO_DE_PAGO_RECHAZADO("","Aviso de pago rechazado"),
	BOLETA_PENDIENTE_DE_AUTORIZAR("","Boleta Pendiente Autorizar"),
	BOLETA_PENDIENTE_DE_CONCILIACION("","Boleta pendiente de conciliación"),
	BOLETA_RECHAZADA("","Boleta rechazada"),
	DISPONIBLE("","Disponible"),
	NO_DISPONIBLE("","No disponible"),
	SUSPENDIDO("","Suspendido"),
	USADO("","Usado");
	
	String codigo;
	String descripcion;
	
	private EstadoOrigenEnum(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	
	public String codigo(){
		return this.codigo;
	}
	
	public String descripcion() {
	    return this.descripcion;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static EstadoOrigenEnum getEnumByName(String name) {
		
		for (EstadoOrigenEnum estadoOrigen : EstadoOrigenEnum.values()) {
			if (estadoOrigen.name().equalsIgnoreCase(name)) {
				return estadoOrigen;
			}
		}
		        
		return null; 
	}
	
	/**
	 * 
	 * @param codigo
	 * @return
	 */
	public static EstadoOrigenEnum getEnumByCodigo(String codigo) {
		
		for (EstadoOrigenEnum estadoOrigen : EstadoOrigenEnum.values()) {
			if (estadoOrigen.codigo().equalsIgnoreCase(codigo)) {
				return estadoOrigen;
			}
		}
		        
		return null; 
	}
	
	/**
	 * 
	 * @param descripcion
	 * @return
	 */
	public static EstadoOrigenEnum getEnumByDescripcion(String descripcion) {
		
		for (EstadoOrigenEnum estadoOrigen : EstadoOrigenEnum.values()) {
			if (estadoOrigen.descripcion().equalsIgnoreCase(descripcion)) {
				return estadoOrigen;
			}
		}
		return null;
	}
	public static List<EstadoOrigenEnum> getEstadoFacturaMic() {
		List<EstadoOrigenEnum> estadosFacturaMic = new ArrayList<EstadoOrigenEnum>();
		estadosFacturaMic.add(EstadoOrigenEnum.ENV_AL_CLIENTE);
		estadosFacturaMic.add(EstadoOrigenEnum.AVISO_DE_INCOMUNIC);
		estadosFacturaMic.add(EstadoOrigenEnum.IMPAGO_AF_NO_INCOMP);
		estadosFacturaMic.add(EstadoOrigenEnum.SUSP_PROC_INCOMUNI);
		estadosFacturaMic.add(EstadoOrigenEnum.IMPAGO_GEST_PERSONAL);
		estadosFacturaMic.add(EstadoOrigenEnum.AVISO_INC_GP_AUT);
		estadosFacturaMic.add(EstadoOrigenEnum.AVISO_INC_GP);
		estadosFacturaMic.add(EstadoOrigenEnum.INC_GP_AUTORIZADO);
		estadosFacturaMic.add(EstadoOrigenEnum.ORDEN_INC_GENERADA);
		estadosFacturaMic.add(EstadoOrigenEnum.REAB_PEND_COBRO_GEN);
		estadosFacturaMic.add(EstadoOrigenEnum.REAB_PEND_COBRO);
		estadosFacturaMic.add(EstadoOrigenEnum.ORD_INC_GENERADA_GP);
		estadosFacturaMic.add(EstadoOrigenEnum.BAJA_GESTIO_PERS_AUT);
		estadosFacturaMic.add(EstadoOrigenEnum.ORD_BAJ_GES_PERS_GEN);
		estadosFacturaMic.add(EstadoOrigenEnum.INCOMUNICADO);
		estadosFacturaMic.add(EstadoOrigenEnum.ORDEN_BAJA_GENERADA);
		estadosFacturaMic.add(EstadoOrigenEnum.AVISO_BAJA_ENVIADO);
		estadosFacturaMic.add(EstadoOrigenEnum.ACUSE_DE_RECIBO);
		estadosFacturaMic.add(EstadoOrigenEnum.INCOMUNICADO_GP);
		estadosFacturaMic.add(EstadoOrigenEnum.AVISO_BAJA_GP_AUT);
		estadosFacturaMic.add(EstadoOrigenEnum.AVISO_BAJA_GP);
		estadosFacturaMic.add(EstadoOrigenEnum.ACUSE_RES_GES_PERSON);
		estadosFacturaMic.add(EstadoOrigenEnum.BAJA);
		estadosFacturaMic.add(EstadoOrigenEnum.IMPAGO_FACT_ESPECIAL);
		estadosFacturaMic.add(EstadoOrigenEnum.MIGRADA);
		estadosFacturaMic.add(EstadoOrigenEnum.FACTURADA);
		estadosFacturaMic.add(EstadoOrigenEnum.ENV_A_DA);
		estadosFacturaMic.add(EstadoOrigenEnum.CONVENIO_PAGO_CUOTAS);
		estadosFacturaMic.add(EstadoOrigenEnum.ANULADA);
		estadosFacturaMic.add(EstadoOrigenEnum.COMPENSADORA);
//COBRADA_POR_DA("10", "COBRADA POR D/A"),
//COBRADA_OF_TECO("11", "COBRADA OF. TECO"),
//COBRADA_EN_BANCO("12", "COBRADA EN BANCO"),
		return estadosFacturaMic;
	}
	
	public static List<String> getEstadoFacturaMicDescripcion() {
		List<String> estadosFacturaMic = new ArrayList<String>();
		for (EstadoOrigenEnum eoe : getEstadoFacturaMic()) {
			estadosFacturaMic.add(eoe.descripcion());
		}
		
//COBRADA_POR_DA("10", "COBRADA POR D/A"),
//COBRADA_OF_TECO("11", "COBRADA OF. TECO"),
//COBRADA_EN_BANCO("12", "COBRADA EN BANCO"),
		return estadosFacturaMic;
	}
	public static List<String> getEstadoFacturaMicCodigo() {
		List<String> estadosFacturaMic = new ArrayList<String>();
		for (EstadoOrigenEnum eoe : getEstadoFacturaMic()) {
			estadosFacturaMic.add(eoe.codigo());
		}
		
//COBRADA_POR_DA("10", "COBRADA POR D/A"),
//COBRADA_OF_TECO("11", "COBRADA OF. TECO"),
//COBRADA_EN_BANCO("12", "COBRADA EN BANCO"),
		return estadosFacturaMic;
	}
	public static String getEstadoFacturaMicLeyenda() {
		StringBuffer str = new StringBuffer();
		for (EstadoOrigenEnum eoe : getEstadoFacturaMic()) {
			str.append(eoe.descripcion() + "(" + eoe.codigo() + ")");
			str.append(" | ");
		}
		if (str.length() > 0) {
			str.delete(str.length() - 3, str.length());
		}
//COBRADA_POR_DA("10", "COBRADA POR D/A"),
//COBRADA_OF_TECO("11", "COBRADA OF. TECO"),
//COBRADA_EN_BANCO("12", "COBRADA EN BANCO"),
		return str.toString();
	}
}
