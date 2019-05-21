package ar.com.telecom.shiva.base.enumeradores;
/**
 * @author u578936 MA.Uehara
 *
 */
public enum CamposReglasGestionabilidadEnum {
	ORIGEN(
		"Origen",
		"sistemaOrigen"
	),
	//  12/10/2017 fnq
	NOT_ORIGEN(
		"Origen",
		"sistemaOrigen"
	),
	
	EMPRESA(
		"Empresa",
		""
	), // Por el momento no se utiliza
	MONEDA(
		"Moneda",
		"moneda"
	), // Por el momento no se utiliza
	NOT_MONEDA(
		"Moneda",
		"moneda"
	), 
	TIPO(
		"Tipo",
		"tipoComprobanteEnum"
	),
	SUBTIPO(
		"SubTipo",
		"tipoFactura"
	),
	SUBTIPO_DUC(
		"SubTipo",
		"tipoDuc"
	),
	ESTADO(
		"Estado",
		"estadoOrigenEnum"
	),
	MOTIVO(
		"Motivo",
		"motivo"
	),
	MARCA_RECLAMO_ORIGEN(
		"Marca de reclamo en origen",
		"marcaReclamo"
	),
	MARCA_CQ_ORIGEN(
		"Marca de C&Q en origen",
		""
	), // Por el momento no se utiliza
	MARCA_MIGRACION_ORIGEN(
		"Marca de migrado en origen",
		"marcaMigradaOrigenEnum"
	),
	//MARCA_MODULO_MARCAS_EVA("Marca del módulo de marcas de EVA", ""), // Deprecado
	MARCA_PAGO_COMPENSACION_PROCESO_SHIVA(
		"Marca de pago/compensación en proceso en SHIVA",
		"marcaPagoCompensacionEnProcesoShiva"
	), 
	DOCUMENTO_INCLUIDO_EN_OTRA_OPERACION_SHIVA(
		"¿El documento está incluido en otra operación SHIVA en edición?",
		"documentoIncluidoEnOtraOperacionShivaEnEdicionEnum"
	),
	ESTADO_DEIMOS(
		"Estado en DEIMOs",
		"dmosEstadoTramite"
	),
	CANTIDAD_CUOTAS_PAGAS_DEIMOS(
		"Cantidad de cuotas pagas en DEIMOs",
		"dmosCantidadDeCuotasPagas"
	),
	MARCA_REVERSION_PROCESO_PENDIENTE(
		"marca si la reversion de cobro en proceso pendiente",
		"marcaReversionDeCobroProcesoPendiente"
	),
	MARCA_REVERSION_EDICION(
		"marca si el debito o credtio esta en una reversion en estado edicion",
		"marcaReversionDeCobroEdicion"
	),
	SALDO_PESIFICADO_FECHA_DE_EMISION(
		"saldo pesificado a fecha de emisión",
		"SaldoPesificado"
	),
	SALDO_PRIMER_VENC_MON_ORIGEN(
		"saldo primer vencimiento moneda origen",
		"saldo1erVencMonOrigen"
	),
	SALDO_MONEDA_ORIGEN(
		"saldo Moneda Origen",
		"saldoMonOrigen"
	),
	MONEDA_OPERACION(
		"Moneda Operacion o Moneda Importe a cobrar o Moneda Importe a Utilizar",
		"MonedaImporteEnum"
	),
	TIPO_SAP(
		"Tipo",
		"tipoDocumento"
	),
	NOT_TIPO_SAP(
		"Tipo",
		"tipoDocumento"
	),
	ESTADO_BLOQUEO_SAP(
		"estado",
		"estadoBloqueoSapEnum"
	),
	ES_DOCUMENTO_PRELIMINAR(
			"¿Es documento preliminar?",
			"esDocumentoPreliminar"
	),
	PROVEEDOR_INHABILITADO(
			"¿El documento está asociado a un proveedor inhabilitado?",
			"proveedorInhabilitado"
			),
	INFORMA_PADRE_INEXISTENTE(
			"Informa padre inexistente",
			"huerfano"
			),
	SOCIEDAD(
			"Sociedad",
			"codigoSociedad"),
			
	// SAP
	// Campos de la clase GestionabilidadDto. Resultados
	COBRAR_DEUDA("¿Se puede cobrar la deuda?", "cobrarDeuda"),
	SEMAFORO("Semáforo", "semaforo"),
	OBSERVACIONES("Observaciones", "observacion"),
	ERRORES_DEBITO("ErrorDebito", "Codigos"),
	ERRORES_CREDITO("ErrorCredito", "Codigos"),
	ERRORES_CAP("ErrorCap", "Codigos");

	String nombre;
	String atributo;

	private CamposReglasGestionabilidadEnum(
		String nombre,
		String atributo
	) {
		this.nombre = nombre;
		this.atributo = atributo;
	}

	public String nombre() {
		return this.nombre;
	}

	public String atributo() {
		return this.atributo;
	}

	public static CamposReglasGestionabilidadEnum getEnumByName(String name){
		for (CamposReglasGestionabilidadEnum enumerador : CamposReglasGestionabilidadEnum.values()) {
			if (enumerador.name().equals(name)) {
				return enumerador;
			}
		}
		return null;
	}
}
