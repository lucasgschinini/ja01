package ar.com.telecom.shiva.base.enumeradores;

public enum ErroresCreditoEnum {
	// HAY MENSAJES QUE ESTAN DUPLICADOS EN DEBITOS TANTO CON CREDITOS
	E_8999(
		"8999",
		"El documento se encuentra en un cobro en estado 'Pendiente de Imputar Cobro' o 'Cobro en proceso'.",
		null
	),
	W_8501( 
		"8501",
		"El documento está incluido en otro cobro SHIVA.",
		null
	),
	// MIC
	E_8001(
		"8001",
		"Los documentos con subtipo 'Factura online (76)' y 'NC online (77)' no pueden ser gestionados.",
		null
	),
	E_8020(
		"8020",
		"Los documentos en estado 'FACTURADA (00)' no pueden ser gestionados.",
		EstadoOrigenEnum.FACTURADA
	),
	E_8021(
		"8021",
		"Los documentos en estado 'ENV. A D/A (03)' no pueden ser gestionados.",
		EstadoOrigenEnum.ENV_A_DA
	),
	E_8022(
		"8022",
		"Los documentos en estado 'CONVENIO PAGO CUOTAS (60)' no pueden ser gestionados.",
		EstadoOrigenEnum.CONVENIO_PAGO_CUOTAS
	),
	E_8023(
		"8023",
		"Los documentos en estado 'ANULADA (70)' no pueden ser gestionados.",
		EstadoOrigenEnum.ANULADA
	),
	E_8024(
		"8024",
		"Los documentos en estado 'COMPENSADORA (71)' no pueden ser gestionados.",
		EstadoOrigenEnum.COMPENSADORA
	),
	W_8502(
		"8502",
		"El documento se encuentra en estado 'MIGRADA ('55')'",
		null
	),
	E_8040(
		"8040",
		"Los documentos en estado 'MIGRADA (55)', con estado en DEIMOS 'Gestión con acuerdo (EGCA)' con cuotas pagas no puede ser gestionados.",
		null
	),
	E_8041(
		"8041",
		"Los documentos en estado 'MIGRADA (55)', con estado en DEIMOS 'Deuda cobrada (CO)' no puede ser gestionados.",
		null
	),
	E_8060(
		"8060",
		"Los documentos con marca de reclamo en origen 'PENDIENTE (R)' no pueden ser gestionados.",
		null
	),
	E_8049(
			"8049",
			"Los remanentes de tipo 'III - Transferible actualizable' no pueden ser gestionados.",
			 TipoRemanenteEnum.TRANSFERIBLE_ACTUALIZABLE
		),
	E_8050(
		"8050",
		"Los remanentes de tipo 'IV - Ficticio para notas de créditos MIC' no pueden ser gestionados.",
		 TipoRemanenteEnum.FICTICIO_PARA_NOTAS_DE_CREDITO_MIC
	),
	E_8051(
		"8051",
		"Los remanentes de tipo 'V - Depósitos en garantía' no pueden ser gestionados.",
		 TipoRemanenteEnum.DEPOSITOS_EN_GARANTIA
	),
	E_8052(
		"8052",
		"Los remanentes de tipo 'VI - PAGO A CUENTA POR RECLAMO' no pueden ser gestionados.",
		 TipoRemanenteEnum.PAGO_A_CUENTA_POR_RECLAMO
	),
	// CALIPSO
	E_8030(
		"8030",
		"Los documentos en estado 'DESISTIDO' no pueden ser gestionados.",
		null
	),
	W_8503(
		"8503",
		"El documento se encuentra migrado en origen.",
		null
	),
	W_8504(
		"8504",
		"El documento se encuentra reclamado en origen.",
		null
	),
	W_8505(
		"8505",
		"El documento se encuentra en estado 'CONCURSO'.",
		EstadoOrigenEnum.CONCURSO
	),
	W_8506(
		"8506",
		"El documento se encuentra en estado 'FINANCIADA'.",
		EstadoOrigenEnum.FINANCIADA
	),
	W_8507(
		"8507",
		"El documento se encuentra en estado 'INCOBRABLE'.",
		EstadoOrigenEnum.INCOBRABLE
	),
	W_8508(
		"8508",
		"El documento se encuentra en estado 'INCOBRABLES'.",
		EstadoOrigenEnum.INCOBRABLES
	),
	W_8509(
		"8509",
		"El documento se encuentra en estado 'NO INNOVAR'.",
		EstadoOrigenEnum.NO_INNOVAR
	),
	W_8510(
		"8510",
		"El documento se encuentra en estado 'QUIEBRA'.",
		EstadoOrigenEnum.QUIEBRA
	),
	W_8511(
		"8511",
		"El documento se encuentra en estado 'LEGALES'.",
		EstadoOrigenEnum.LEGALES
	),
	// LOS ERRORES e_8512 y E_8513 se tratan diferente. No se tratan como regla sino como restriccion en SemaforoGestionabilidadCredito
	E_8512(
		"8512",
		"Los documentos de tipo 'CTA' con moneda 'Dólar' no pueden ser gestionados.",
		TipoComprobanteEnum.CTA
	),
	E_8513(
		"8513",
		"Los documentos de tipo 'Nota de Credito' con moneda 'Dólar' no pueden ser gestionados.",
		TipoComprobanteEnum.CRE
	),
	// Errores de SHIVA solo cobros
	E_8204(	
		"8204",
		"Los valores con motivo ´Valor en Garantía´ no pueden ser gestionados.",
		MotivoShivaEnum.VALOR_EN_GARANTIA
	),
	E_8205(	
		"8205",
		"Los valores con motivo ´Valor por Reclamo de Facturación´ no pueden ser gestionados.",
		MotivoShivaEnum.VALOR_POR_RECLAMO_DE_FACTURACION
	),
	E_8206(	
		"8206",
		"Los valores con estado 'No disponible' no pueden ser gestionados.",
		EstadoOrigenEnum.NO_DISPONIBLE
	),
	E_8207(	
		"8207",
		"Los valores con estado 'Aviso de pago pendiente de confirmar' no pueden ser gestionados.",
		EstadoOrigenEnum.AVISO_DE_PAGO_PENDIENTE_DE_CONFIRMAR
	),
	E_8208(	
		"8208",
		"Los valores con estado 'Aviso de pago rechazado' no pueden ser gestionados.",
		EstadoOrigenEnum.AVISO_DE_PAGO_RECHAZADO
	),
	E_8209(	
		"8209", 
		"Los valores con estado 'Usado' no pueden ser gestionados.",
		EstadoOrigenEnum.USADO
	),
	E_8210(	
		"8210", 
		"Los valores con estado 'Suspendido' no pueden ser gestionados.",
		EstadoOrigenEnum.SUSPENDIDO
	),
	E_8211(	
		"8211", 
		"Los valores con estado 'Anulado' no pueden ser gestionados.",
		EstadoOrigenEnum.ANULADO
	),
	E_8212(	
		"8212",
		"Los valores con estado 'Boleta pendiente de conciliación' no pueden ser gestionados.",
		EstadoOrigenEnum.BOLETA_PENDIENTE_DE_CONCILIACION
	),
	E_8213(	
		"8213",
		"Los valores con estado 'Boleta pendiente de autorizar' no pueden ser gestionados.",
		EstadoOrigenEnum.BOLETA_PENDIENTE_DE_AUTORIZAR
	),
	E_8214(	
		"8214",
		"Los valores con estado 'Boleta rechazada' no pueden ser gestionados.",
		EstadoOrigenEnum.BOLETA_RECHAZADA
	),
	E_8700(
		"8700",
		"Los documentos incluidos en una reversión en estado 'Descobro en error', 'Descobro pendiente' o 'Descobro en proceso' no pueden ser seleccionados.",
		null
	),
	W_8701(
		"8701",
		"El documento se encuentra incluido en una reversión en estado 'Descobro en edición' o 'Error en primer descobro'.",
		null
	),
	E_8702(
		"8702",
		"Saldo real del documento menor a 0,01 centavos.",
		null
	),	
	E_9000(
		"9000",
		"No se puede seleccionar documentos con moneda dólar, con la moneda operación en pesos.",
		null
	);;

	String codigo;
	String descripcion;
	Object objetoDiscriminador; // Se utiliza para discriminar el mensaje de error
	private ErroresCreditoEnum(String codigo, String descripcion, Object objetoDiscriminador) {
		this.descripcion = descripcion;
		this.codigo = codigo;
		this.objetoDiscriminador = objetoDiscriminador;
	}

	public String codigo() {
		return this.codigo;
	}
	public String descripcion() {
		return this.descripcion;
	}
	public Object objetoDiscriminador() {
		return this.objetoDiscriminador;
	}
}
