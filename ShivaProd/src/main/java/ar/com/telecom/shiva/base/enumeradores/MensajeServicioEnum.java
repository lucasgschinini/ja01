package ar.com.telecom.shiva.base.enumeradores;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.utils.Validaciones;

public enum MensajeServicioEnum {
	
	CLP_APROP_DEUDA("Apropiacion de deuda", SistemaEnum.CALIPSO.descripcionCorta, "CA","", null),
	MIC_APROP_DEUDA("Apropiacion de deuda",SistemaEnum.MIC.descripcionCorta, "MA","", null),
	CLP_APROP_MP("Apropiacion de medio de pago", SistemaEnum.CALIPSO.descripcionCorta, "CA","", null),
	MIC_APROP_MP("Apropiacion de medio de pago",SistemaEnum.MIC.descripcionCorta, "MA","", null),
	CLP_APROP_DEUDA_Y_MP("Apropiacion de deuda y medio de pago",SistemaEnum.CALIPSO.descripcionCorta, "CA","", null),
	MIC_APROP_DEUDA_Y_MP("Apropiacion de deuda y medio de pago",SistemaEnum.MIC.descripcionCorta, "MA","", null),
	CLP_DESAPROPIACION("Desapropiacion", SistemaEnum.CALIPSO.descripcionCorta, "CD","", null),
	MIC_DESAPROPIACION("Desapropiacion", SistemaEnum.MIC.descripcionCorta, "MD","", null),
	CLP_CONFIRMACION("Confirmacion", SistemaEnum.CALIPSO.descripcionCorta, "CC","", null),
	MIC_CONFIRMACION("Confirmacion", SistemaEnum.MIC.descripcionCorta, "MC","", null),
	MIC_RESPUESTA_APROP_DEUDA("Respuesta de Apropiacion de deuda", SistemaEnum.MIC.descripcionCorta, "MA","", null),
	MIC_RESPUESTA_APROP_MP("Respuesta de Apropiacion de medio de pago", SistemaEnum.MIC.descripcionCorta, "MA","", null),
	MIC_RESPUESTA_APROP_DEUDA_y_MP("Respuesta de Apropiacion de deuda y medio de pago", SistemaEnum.MIC.descripcionCorta, "MA","", null),
	MIC_RESPUESTA_DESAPROPIACION("Respuesta de Desapropiacion", SistemaEnum.MIC.descripcionCorta, "MD","", null),
	MIC_RESPUESTA_CONFIRMACION("Respuesta de Confirmacion", SistemaEnum.MIC.descripcionCorta, "MC","", null),
	
	//Sprint 5
	CLP_GENERACION_CARGO_DEBITO("Generacion de Cargo de Debito", SistemaEnum.CALIPSO.descripcionCorta, "CGCD","", null),
	MIC_GENERACION_CARGO_DEBITO("Generacion de Cargo de Debito", SistemaEnum.MIC.descripcionCorta, "MGCD","", null),
	CLP_GENERACION_CARGO_CREDITO("Generacion de Cargo de Credito", SistemaEnum.CALIPSO.descripcionCorta, "CGCC", "", null),
	MIC_GENERACION_CARGO_CREDITO("Generacion de Cargo de Credito", SistemaEnum.MIC.descripcionCorta, "MGCC","", null),
	CLP_GENERACION_CARGO_INTERES("Generacion de Cargo de Interes", SistemaEnum.CALIPSO.descripcionCorta, "CGCI","", null),
	MIC_GENERACION_CARGO_INTERES("Generacion de Cargo de Interes", SistemaEnum.MIC.descripcionCorta, "MGCI","", null),
	MIC_RESPUESTA_GENERACION_CARGO_DEBITO("Respuesta de Generacion de Cargo de Debito", SistemaEnum.MIC.descripcionCorta, "MGCD", "", null),
	MIC_RESPUESTA_GENERACION_CARGO_CREDITO("Respuesta de Generacion de Cargo de Credito", SistemaEnum.MIC.descripcionCorta, "MGCC", "", null),
	MIC_RESPUESTA_GENERACION_CARGO_INTERES("Respuesta de Generacion de Cargo de Interes", SistemaEnum.MIC.descripcionCorta, "MGCI", "", null),
	
	//Sprint 5 - Simulaciones
	CLP_APROP_DEUDA_SIM("Simulacion de apropiacion de deuda", SistemaEnum.CALIPSO.descripcionCorta, "CAS","", null),
	MIC_APROP_DEUDA_SIM("Simulacion de apropiacion de deuda",SistemaEnum.MIC.descripcionCorta, "MAS","", null),
	CLP_APROP_MP_SIM("Simulacion de apropiacion de medio de pago", SistemaEnum.CALIPSO.descripcionCorta, "CAS","", null),
	MIC_APROP_MP_SIM("Simulacion de apropiacion de medio de pago",SistemaEnum.MIC.descripcionCorta, "MAS","", null),
	CLP_APROP_DEUDA_Y_MP_SIM("Simulacion de apropiacion de deuda y medio de pago",SistemaEnum.CALIPSO.descripcionCorta, "CAS","", null),
	MIC_APROP_DEUDA_Y_MP_SIM("Simulacion de apropiacion de deuda y medio de pago",SistemaEnum.MIC.descripcionCorta, "MAS","", null),
	
	CLP_GENERACION_CARGO_DEBITO_SIM("Simulacion de generacion de Cargo de Debito", SistemaEnum.CALIPSO.descripcionCorta, "CGCDS","", null),
	MIC_GENERACION_CARGO_DEBITO_SIM("Simulacion de generacion de Cargo de Debito", SistemaEnum.MIC.descripcionCorta, "MGCDS","", null),
	CLP_GENERACION_CARGO_CREDITO_SIM("Simulacion de generacion de Cargo de Credito", SistemaEnum.CALIPSO.descripcionCorta, "CGCCS","", null),
	MIC_GENERACION_CARGO_CREDITO_SIM("Simulacion de generacion de Cargo de Credito", SistemaEnum.MIC.descripcionCorta, "MGCCS","", null),
	CLP_GENERACION_CARGO_INTERES_SIM("Simulacion de generacion de Cargo de Interes", SistemaEnum.CALIPSO.descripcionCorta, "CGCIS","", null),
	MIC_GENERACION_CARGO_INTERES_SIM("Simulacion de generacion de Cargo de Interes", SistemaEnum.MIC.descripcionCorta, "MGCIS","", null),
	
	DEI_APROPIACION_SIM("Simulacion de apropiacion de documentos", SistemaEnum.DEIMOS.descripcionCorta, "DAS","", null),
	DEI_APROPIACION("Apropiacion de documentos", SistemaEnum.DEIMOS.descripcionCorta, "DA","", null),
	DEI_DESAPROPIACION("Desapropiacion", SistemaEnum.DEIMOS.descripcionCorta, "DD","", null),
		
	//Sprint 7 - Descobros
	
	//Simulacion
	CLP_DESCOBRO_SIM("Simulacion de Descobro", SistemaEnum.CALIPSO.descripcionCorta, "CDS","MSJ_DESCOBRO_SIMULACION_CLP", null),
	MIC_DESCOBRO_SIM("Simulacion de Descobro", SistemaEnum.MIC.descripcionCorta, "MDS","MSJ_DESCOBRO_SIMULACION_MIC", null),
	CLP_DESCOBRO_GENERACION_CARGO_DEBITO_SIM("Simulacion - Generacion de Cargo de Debito - Descobro", SistemaEnum.CALIPSO.descripcionCorta, "CDGCDS","MSJ_DESCOBRO_SIMULACION_CLP", null),
	MIC_DESCOBRO_GENERACION_CARGO_DEBITO_SIM("Simulacion - Generacion de Cargo de Debito - Descobro", SistemaEnum.MIC.descripcionCorta, "MDGCDS","MSJ_DESCOBRO_SIMULACION_MIC", null),
	CLP_DESCOBRO_GENERACION_CARGO_CREDITO_SIM("Simulacion - Generacion de Cargo de Credito - Descobro", SistemaEnum.CALIPSO.descripcionCorta, "CDGCCS","MSJ_DESCOBRO_SIMULACION_CLP", null),
	MIC_DESCOBRO_GENERACION_CARGO_CREDITO_SIM("Simulacion - Generacion de Cargo de Credito - Descobro", SistemaEnum.MIC.descripcionCorta, "MDGCCS","MSJ_DESCOBRO_SIMULACION_MIC", null),
	CLP_DESCOBRO_GENERACION_CARGO_INTERES_SIM("Simulacion - Generacion de Cargo de Interes - Descobro", SistemaEnum.CALIPSO.descripcionCorta, "CDGCIS","MSJ_DESCOBRO_SIMULACION_CLP", null),
	MIC_DESCOBRO_GENERACION_CARGO_INTERES_SIM("Simulacion - Generacion de Cargo de Interes - Descobro", SistemaEnum.MIC.descripcionCorta, "MDGCIS","MSJ_DESCOBRO_SIMULACION_MIC", null),
	CLP_DESCOBRO_GENERACION_CARGO_DEBITO_INTERES_SIM("Simulacion - Generacion de Cargo de Debito Interes - Descobro", SistemaEnum.CALIPSO.descripcionCorta, "CDGCDIS","MSJ_DESCOBRO_SIMULACION_CLP", null),
	MIC_DESCOBRO_GENERACION_CARGO_DEBITO_INTERES_SIM("Simulacion - Generacion de Cargo de Debito Interes - Descobro", SistemaEnum.MIC.descripcionCorta, "MDGCDIS","MSJ_DESCOBRO_SIMULACION_MIC", null),
	CLP_DESCOBRO_GENERACION_CARGO_CREDITO_INTERES_SIM("Simulacion - Generacion de Cargo de Credito Interes - Descobro", SistemaEnum.CALIPSO.descripcionCorta, "CDGCCIS","MSJ_DESCOBRO_SIMULACION_CLP", null),
	MIC_DESCOBRO_GENERACION_CARGO_CREDITO_INTERES_SIM("Simulacion - Generacion de Cargo de Credito Interes - Descobro", SistemaEnum.MIC.descripcionCorta, "MDGCCIS","MSJ_DESCOBRO_SIMULACION_MIC", null),

	//Imputacion
	CLP_DESCOBRO("Descobro", SistemaEnum.CALIPSO.descripcionCorta, "CD","MSJ_DESCOBRO_IMPUTACION_CLP", EstadoFacturaMedioPagoEnum.ERROR_DESCOBRO),
	MIC_DESCOBRO("Descobro", SistemaEnum.MIC.descripcionCorta, "MD","MSJ_DESCOBRO_IMPUTACION_MIC", EstadoFacturaMedioPagoEnum.ERROR_DESCOBRO),
	MIC_RESPUESTA_DESCOBRO("Respuesta de Descobro", SistemaEnum.MIC.descripcionCorta, "MRD","MSJ_DESCOBRO_IMPUTACION_MIC", EstadoFacturaMedioPagoEnum.ERROR_DESCOBRO),
	CLP_DESCOBRO_GENERACION_CARGO_DEBITO("Generacion de Cargo de Debito - Descobro", SistemaEnum.CALIPSO.descripcionCorta, "CDGCD","MSJ_DESCOBRO_IMPUTACION_CLP", EstadoFacturaMedioPagoEnum.ERROR_CONTRACARGO),
	CLP_DESCOBRO_GENERACION_CARGO_CREDITO("Generacion de Cargo de Credito - Descobro", SistemaEnum.CALIPSO.descripcionCorta, "CDGCC","MSJ_DESCOBRO_IMPUTACION_CLP", EstadoFacturaMedioPagoEnum.ERROR_CONTRACARGO),
	CLP_DESCOBRO_GENERACION_CARGO_INTERES("Generacion de Cargo de Interes - Descobro", SistemaEnum.CALIPSO.descripcionCorta, "CDGCI","MSJ_DESCOBRO_IMPUTACION_CLP", EstadoFacturaMedioPagoEnum.ERROR_CONTRACARGO),
	CLP_DESCOBRO_GENERACION_CARGO_DEBITO_INTERES("Generacion de Cargo de Debito Interes - Descobro", SistemaEnum.CALIPSO.descripcionCorta, "CDGCDI","MSJ_DESCOBRO_IMPUTACION_CLP", EstadoFacturaMedioPagoEnum.ERROR_CONTRACARGO),
	CLP_DESCOBRO_GENERACION_CARGO_CREDITO_INTERES("Generacion de Cargo de Credito Interes - Descobro", SistemaEnum.CALIPSO.descripcionCorta, "CDGCCI","MSJ_DESCOBRO_IMPUTACION_CLP", EstadoFacturaMedioPagoEnum.ERROR_CONTRACARGO),
	MIC_DESCOBRO_GENERACION_CARGO_DEBITO("Generacion de Cargo de Debito - Descobro", SistemaEnum.MIC.descripcionCorta, "MDGCD","MSJ_DESCOBRO_IMPUTACION_MIC", EstadoFacturaMedioPagoEnum.ERROR_CONTRACARGO),
	MIC_DESCOBRO_GENERACION_CARGO_CREDITO("Generacion de Cargo de Credito - Descobro", SistemaEnum.MIC.descripcionCorta, "MDGCC","MSJ_DESCOBRO_IMPUTACION_MIC", EstadoFacturaMedioPagoEnum.ERROR_CONTRACARGO),
	MIC_DESCOBRO_GENERACION_CARGO_INTERES("Generacion de Cargo de Interes - Descobro", SistemaEnum.MIC.descripcionCorta, "MDGCI","MSJ_DESCOBRO_IMPUTACION_MIC", EstadoFacturaMedioPagoEnum.ERROR_CONTRACARGO),
	MIC_DESCOBRO_GENERACION_CARGO_DEBITO_INTERES("Generacion de Cargo de Debito Interes - Descobro", SistemaEnum.MIC.descripcionCorta, "MDGCDI","MSJ_DESCOBRO_IMPUTACION_MIC", EstadoFacturaMedioPagoEnum.ERROR_CONTRACARGO),
	MIC_DESCOBRO_GENERACION_CARGO_CREDITO_INTERES("Generacion de Cargo de Credito Interes - Descobro", SistemaEnum.MIC.descripcionCorta, "MDGCCI","MSJ_DESCOBRO_IMPUTACION_MIC", EstadoFacturaMedioPagoEnum.ERROR_CONTRACARGO),
	MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_DEBITO("Respuesta de Generacion de Cargo de Debito - Descobro", SistemaEnum.MIC.descripcionCorta, "MDGCD","MSJ_DESCOBRO_IMPUTACION_MIC", EstadoFacturaMedioPagoEnum.ERROR_CONTRACARGO),
	MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_CREDITO("Respuesta de Generacion de Cargo de Credito - Descobro", SistemaEnum.MIC.descripcionCorta, "MDGCC","MSJ_DESCOBRO_IMPUTACION_MIC", EstadoFacturaMedioPagoEnum.ERROR_CONTRACARGO),
	MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_INTERES("Respuesta de Generacion de Cargo de Interes - Descobro", SistemaEnum.MIC.descripcionCorta, "MDGCI","MSJ_DESCOBRO_IMPUTACION_MIC", EstadoFacturaMedioPagoEnum.ERROR_CONTRACARGO),
	
	// Sap
	SAP_VERIFICAR_PARTIDAS_SIM("Simulacion de solicitud de generacion de Compensación", SistemaEnum.SAP.descripcionCorta, "DAS", "", null),
	SAP_REGISTRAR_COMPENSACION("Registrar Compensación", SistemaEnum.SAP.descripcionCorta, "RC", "", null),
	SAP_CONSULTAR_PROVEEDOR_SIM("Simulacion de consulta de proveedor", SistemaEnum.SAP.descripcionCorta, "DAS", "", null),
	SAP_CONSULTAR_PROVEEDOR("Consulta de proveedor imputacion", SistemaEnum.SAP.descripcionCorta, "DAS", "", null);
	
	
	String descripcion;
	String aplicacion;
	String sigla;
	String tipoMensajeria;
	EstadoFacturaMedioPagoEnum tipoError;
	
	private MensajeServicioEnum(String descripcion, String aplicacion, String sigla, String tipoMensajeria, EstadoFacturaMedioPagoEnum tipoError) {
	    this.descripcion = descripcion;
	    this.aplicacion = aplicacion;
	    this.sigla = sigla;
	    this.tipoMensajeria = tipoMensajeria;
	    this.tipoError = tipoError;
	}
	
	public String getDescripcion() {
	    return this.descripcion;
	}
	
	public String getAplicacion() {
	    return this.aplicacion;
	}
	
	public String getSigla() {
	    return this.sigla;
	}
	
	public String getTipoMensajeria() {
		return this.tipoMensajeria;
	}
	
	public EstadoFacturaMedioPagoEnum getTipoError() {
		return this.tipoError;
	}
	
	public boolean esMensajeDescobrosCalipso(){
		
		List<MensajeServicioEnum> listaContracargoCalipso = new ArrayList<MensajeServicioEnum>();
		listaContracargoCalipso.add(MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_CREDITO);
		listaContracargoCalipso.add(MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_CREDITO_SIM);
		listaContracargoCalipso.add(MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_DEBITO);
		listaContracargoCalipso.add(MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_DEBITO_SIM);
		listaContracargoCalipso.add(MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_INTERES);
		listaContracargoCalipso.add(MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_INTERES_SIM);
		listaContracargoCalipso.add(MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_CREDITO_INTERES);
		listaContracargoCalipso.add(MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_CREDITO_INTERES_SIM);
		listaContracargoCalipso.add(MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_DEBITO_INTERES);
		listaContracargoCalipso.add(MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_DEBITO_INTERES_SIM);
		
		if(listaContracargoCalipso.contains(this)){
			return true;
		}
		return false;
	}
	
	public boolean esMensajeSolicitudParaImputacionCobrosMic(){
		
		List<MensajeServicioEnum> lista = new ArrayList<MensajeServicioEnum>();
		lista.add(MensajeServicioEnum.MIC_APROP_DEUDA);
		lista.add(MensajeServicioEnum.MIC_APROP_DEUDA_Y_MP);
		lista.add(MensajeServicioEnum.MIC_APROP_MP);
		lista.add(MensajeServicioEnum.MIC_CONFIRMACION);
		lista.add(MensajeServicioEnum.MIC_DESAPROPIACION);
		lista.add(MensajeServicioEnum.MIC_GENERACION_CARGO_CREDITO);
		lista.add(MensajeServicioEnum.MIC_GENERACION_CARGO_DEBITO);
		lista.add(MensajeServicioEnum.MIC_GENERACION_CARGO_INTERES);
		
		if(lista.contains(this)){
			return true;
		}
		return false;
	}
	
	public boolean esMensajeSolicitudParaImputacionDescobrosMic(){
		
		List<MensajeServicioEnum> lista = new ArrayList<MensajeServicioEnum>();
		lista.add(MensajeServicioEnum.MIC_DESCOBRO);
		lista.add(MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_CREDITO);
		lista.add(MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_CREDITO_INTERES);
		lista.add(MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_DEBITO);
		lista.add(MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_DEBITO_INTERES);
		lista.add(MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_INTERES);
		
		if(lista.contains(this)){
			return true;
		}
		return false;
	}
	public static List<MensajeServicioEnum> getEnumByTipoMensajeria(String tipoMensajeria) {
		
		List<MensajeServicioEnum> listaMensajes = new ArrayList<MensajeServicioEnum>();
		for (MensajeServicioEnum msj : MensajeServicioEnum.values()) {
			if (msj.getTipoMensajeria().equalsIgnoreCase(tipoMensajeria)) {
				listaMensajes.add(msj);
			}
		}
		return listaMensajes;
	}
	
	
public static List<MensajeServicioEnum> getEnumByTipoError(EstadoFacturaMedioPagoEnum tipoError) {
		
		List<MensajeServicioEnum> listaMensajes = new ArrayList<MensajeServicioEnum>();
		for (MensajeServicioEnum msj : MensajeServicioEnum.values()) {
			if (!Validaciones.isObjectNull(msj.getTipoError()) && msj.getTipoError().equals(tipoError)) {
				listaMensajes.add(msj);
			}
		}
		return listaMensajes;
	}
	
}
