package ar.com.telecom.shiva.base.enumeradores;


public enum ErroresDebitoEnum {
	//HAY MENSAJES QUE ESTAN DUPLICADOS EN DEBITOS TANTO CON CREDITOS
	// TODOS
	E_8999(
		"8999",
		"El documento se encuentra en un cobro en 'Pendiente de Imputar Cobro' o 'Cobro en proceso'.",
		null
	),
	W_8501( //TODO: 
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
	E_8025(
		"8025",
		"Los documentos en estado 'COBRADA POR D/A (10)' no pueden ser gestionados.",
		EstadoOrigenEnum.COBRADA_POR_DA
	),
	E_8026(
		"8026",
		"Los documentos en estado 'COBRADA OF. TECO (11)' no pueden ser gestionados.",
		EstadoOrigenEnum.COBRADA_OF_TECO
	),
	E_8027(
		"8027",
		"Los documentos en estado 'COBRADA EN BANCO (12)' no pueden ser gestionados.",
		EstadoOrigenEnum.COBRADA_EN_BANCO
	),
	W_8502(
		"8502",
		"El documento se encuentra en estado 'MIGRADA ('55')'.",
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
	E_8061(
		"8061",
		"Los documentos en estado 'COBRADO' no pueden ser gestionados.",
		EstadoOrigenEnum.COBRADO
	),
	E_8062(
		"8062",
		"Los documentos en estado 'COBRADO Y APLIC.REF.' no pueden ser gestionados.",
		EstadoOrigenEnum.COBRADO_Y_APLIC_REF
	),
	E_8063(
		"8063",
		"Los documentos en estado 'COBRADO Y MOV. RTE I' no pueden ser gestionados.",
		EstadoOrigenEnum.COBRADO_Y_MOV_RTE_I
	),
	E_8064(
		"8064",
		"Los documentos en estado 'COBRADO Y TRANSFER.' no pueden ser gestionados.",
		EstadoOrigenEnum.COBRADO_Y_TRANSFER
	),
	E_8065(
		"8065",
		"Los documentos en estado 'COBRADO Y ENV. FACT' no pueden ser gestionados.",
		EstadoOrigenEnum.COBRADO_Y_ENV_FACT
	),
	E_8066(
		"8066",
		"Los documentos en estado 'COBRADO Y UTILIZADO' no pueden ser gestionados.",
		EstadoOrigenEnum.COBRADO_Y_UTILIZADO
	),
	E_8067(
		"8067",
		"Los documentos en estado 'APROPIADO SHIVA' no pueden ser gestionados.",
		EstadoOrigenEnum.APROPIADO_SHIVA
	),
	
		
	// CALIPSO
	E_8030(
		"8030",
		"Los documentos en estado 'DESISTIDO' no pueden ser gestionados.",
		EstadoOrigenEnum.DESISTIDO
	),
	E_8031(
			"8031",
			"Los documentos en estado 'ENV. A D/A' no pueden ser gestionados.",
			EstadoOrigenEnum.ENV_A_DA
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
	E_8513(
		"8513",
		"Los documentos con moneda 'Dólar' no pueden ser gestionados.",
		null
	),
	
	E_8132(
		"8132",
		"Los documentos DUCs en estado 'COBRADO' no pueden ser gestionados.",
		null
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
	);


	String codigo;
	String descripcion;
	Object objetoDiscriminador; // Se utiliza para discriminar el mensaje de error
	private ErroresDebitoEnum(String codigo, String descripcion, Object objetoDiscriminador) {
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
