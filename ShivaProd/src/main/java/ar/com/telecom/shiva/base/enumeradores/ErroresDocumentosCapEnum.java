package ar.com.telecom.shiva.base.enumeradores;

public enum ErroresDocumentosCapEnum {

	E_4101(
			"4101",
			"Los documentos en estado 'Estado inicial' no pueden ser gestionados.",
			EstadoBloqueoSapEnum.A
		),
	E_4102(
				"4102",
				"Los documentos en estado 'Aprobación Interm.' no pueden ser gestionados.",
				EstadoBloqueoSapEnum.H
			),
	E_4103(
				"4103",
				"Los documentos en estado 'Rechazo CAP (SSGG)' no pueden ser gestionados.",
				EstadoBloqueoSapEnum.I
			),
	E_4104(
				"4104",
				"Los documentos en estado 'Aprobación imposit.' no pueden ser gestionados.",
				EstadoBloqueoSapEnum.J
			),
	E_4105(
				"4105",
				"Los documentos en estado 'Aprobación CAP' no pueden ser gestionados.",
				EstadoBloqueoSapEnum.K
			),
	E_4106(
				"4106",
				"Los documentos en estado 'Recepción Tesorería' no pueden ser gestionados.",
				EstadoBloqueoSapEnum.L
			),
	E_4107(
				"4107",
				"Los documentos en estado 'Devol. de Tesorería' no pueden ser gestionados.",
				EstadoBloqueoSapEnum.M
			),
	E_4108(
				"4108",
				"Los documentos en estado 'Rechazo vinculadas' no pueden ser gestionados.",
				EstadoBloqueoSapEnum.N
			),
	E_4109(
				"4109",
				"Los documentos en estado 'Rechazo impositivo' no pueden ser gestionados.",
				EstadoBloqueoSapEnum.O
			),
	E_4110(
				"4110",
				"Los documentos en estado 'Rechazo superv CAP' no pueden ser gestionados.",
				EstadoBloqueoSapEnum.P
			),
	E_4111(
				"4111",
				"Los documentos en estado 'Bloqueo Corresp.' no pueden ser gestionados.",
				EstadoBloqueoSapEnum.S
			),
	E_4112(
				"4112",
				"Los documentos en estado 'Aprobacion Corresp.' no pueden ser gestionados.",
				EstadoBloqueoSapEnum.T
			),
	E_4113(
				"4113",
				"Los documentos en estado 'Ret.Insc.INPI/DNDA' no pueden ser gestionados.",
				EstadoBloqueoSapEnum.U
			),
	E_4114(
				"4114",
				"Los documentos en estado 'Doc. Contabilizado' no pueden ser gestionados.",
				EstadoBloqueoSapEnum.V
			),
	E_4115(
				"4115",
				"Los documentos en estado 'Bloqueo Wholesale' no pueden ser gestionados.",
				EstadoBloqueoSapEnum.W
			),
	E_4116(
				"4116",
				"Los documentos en estado 'Analisis Imp.dde.CAP' no pueden ser gestionados.",
				EstadoBloqueoSapEnum.X
			),
	E_4117(
				"4117",
				"Los documentos en estado 'Aprobacion Wholesale' no pueden ser gestionados.",
				EstadoBloqueoSapEnum.Z
			),
			
			
	E_4201(
			"4201",
			"Los documentos en estado 'Autorizado el pago' no pueden ser gestionados.",
			EstadoBloqueoSapEnum.BLANCO
		),
	E_4202(
			"4202",
			"Los documentos en estado 'Preliminar S/ Aprob.' no pueden ser gestionados.",
			EstadoBloqueoSapEnum.NUMERAL
		),
	E_4203(
			"4203",
			"Los documentos en estado 'Pend. comp. SHIVA' no pueden ser gestionados.",
			EstadoBloqueoSapEnum.PESOS
		),
	E_4204(
			"4204",
			"Los documentos en estado 'Bloqueos Combinados' no pueden ser gestionados.",
			EstadoBloqueoSapEnum._0
		),
	E_4205(
			"4205",
			"Los documentos en estado 'Créd retenido temp.' no pueden ser gestionados.",
			EstadoBloqueoSapEnum._1
		),
	E_4206(
			"4206",
			"Los documentos en estado 'Embargo judicial' no pueden ser gestionados.",
			EstadoBloqueoSapEnum._2
		),
	E_4207(
			"4207",
			"Los documentos en estado 'Ret RG 726 AFIP' no pueden ser gestionados.",
			EstadoBloqueoSapEnum._3
		),
	E_4208(
			"4208",
			"Los documentos en estado 'Completar datos fisc' no pueden ser gestionados.",
			EstadoBloqueoSapEnum._4
		),
	E_4209(
			"4209",
			"Los documentos en estado 'RBA  Pago bloqueado' no pueden ser gestionados.",
			EstadoBloqueoSapEnum._5
		),
	E_4210(
			"4210",
			"Los documentos en estado 'FC Apócrifas' no pueden ser gestionados.",
			EstadoBloqueoSapEnum._6
		),
	E_4211(
			"4211",
			"Los documentos en estado 'Adeuda Dif. Cambio' no pueden ser gestionados.",
			EstadoBloqueoSapEnum._7
		),
	E_estadoBloqueoSap8(
			"4211",
			"Los documentos en estado 'Desb.Cert.de Ret' no pueden ser gestionados.",
			EstadoBloqueoSapEnum._8
		),
	E_estadoBloqueoSap9(
			"4211",
			"Los documentos en estado 'Probl. proveedor' no pueden ser gestionados.",
			EstadoBloqueoSapEnum._9
		),		
	E_estadoBloqueoSapInterr(
			"4211",
			"Los documentos en estado 'A la espera de NC' no pueden ser gestionados.",
			EstadoBloqueoSapEnum.INTERR
		),		
	E_estadoBloqueoSapIgual(
			"4211",
			"Los documentos en estado 'Retencion cobranza' no pueden ser gestionados.",
			EstadoBloqueoSapEnum.IGUAL
		),		
	E_estadoBloqueoSapExclama(
			"4211",
			"Los documentos en estado 'Canje' no pueden ser gestionados.",
			EstadoBloqueoSapEnum.EXCLAMA
		),		
	E_estadoBloqueoSapMas(
			"4211",
			"Los documentos en estado 'Carta Ya Presentada' no pueden ser gestionados.",
			EstadoBloqueoSapEnum.MAS
			),		
	E_4212(
			"4212",
			"Los documentos en estado 'Rechazado Imp.ddeCAP' no pueden ser gestionados.",
			EstadoBloqueoSapEnum.MENOR
		),
	E_4213(
			"4213",
			"Los documentos en estado 'Aprobado Imp.dde.CAP' no pueden ser gestionados.",
			EstadoBloqueoSapEnum.MAYOR
		),
	E_4214(
			"4214",
			"Los documentos en estado 'Probl.usuario requir' no pueden ser gestionados.",
			EstadoBloqueoSapEnum.B
		),
	E_4215(
			"4215",
			"Los documentos en estado 'Probl. proveedor' no pueden ser gestionados.",
			EstadoBloqueoSapEnum.C
		),
	E_4216(
			"4216",
			"Los documentos en estado 'Bloqueo no modificab' no pueden ser gestionados.",
			EstadoBloqueoSapEnum.D
		),
	E_4217(
			"4217",
			"Los documentos en estado 'Factura cedida' no pueden ser gestionados.",
			EstadoBloqueoSapEnum.E
		),
	E_4218(
			"4218",
			"Los documentos en estado 'Docum compensable' no pueden ser gestionados.",
			EstadoBloqueoSapEnum.G
		),
	E_4219(
			"4219",
			"Los documentos en estado 'Análisis Impuestos' no pueden ser gestionados.",
			EstadoBloqueoSapEnum.Q
		),
	E_4220(
			"4220",
			"Los documentos en estado 'Bloqueo de Logistica' no pueden ser gestionados.",
			EstadoBloqueoSapEnum.R
		),
	E_4221(
			"4221",
			"Los documentos en estado 'Bloqueo por división' no pueden ser gestionados.",
			EstadoBloqueoSapEnum.Y
		),
	E_4431(
			"4431",
			"Los documentos incluidos en una reversión en estado 'Descobro en error', 'Descobro pendiente' o 'Descobro en proceso' no pueden ser seleccionados.",
			null
		),
	E_4401(
			"4401",
			"El documento es preliminar, no puede ser gestionado.",
			null
		),
	
	E_8999("8999",
			"El documento se encuentra en un cobro en 'Pendiente de Imputar Cobro' o 'Cobro en proceso'.",
			null
		),
	W_8501( 
			"8501",
			"El documento está incluido en otro cobro SHIVA.",
			null
		),
	W_8701("8701",
			"El documento se encuentra incluido en una reversión en estado 'Descobro en edición' o 'Error en primer descobro'.",
			null
		),
	E_4601(
		"4601",
		"No existe Documento padre",
		null
	),
	E_4602(
		"4602",
		"Documento padre esta marcado como V.",
		null
	),
	E_4301(
		"4301",
		"El documento es de tipo 'Liq a Pagar WS', no puede ser gestionado",
		TipoDocumentoCapEnum.DQ
	),
	E_4302(
		"4302",
		"El documento es de tipo 'Compensación SHIVA', no puede ser gestionado",
		TipoDocumentoCapEnum.K$
	),
	E_4303(
		"4303",
		"El documento es de tipo 'Rendición G Menores', no puede ser gestionado",
		TipoDocumentoCapEnum.AD
	),
	E_4304(
		"4304",
		"El documento es de tipo 'Antic. de gastos FF', no puede ser gestionado",
		TipoDocumentoCapEnum.K0
	),
	E_4305(
		"4305",
		"El documento es de tipo 'Docum Financieros', no puede ser gestionado",
		TipoDocumentoCapEnum.K1
	),
	E_4306(
		"4306",
		"El documento es de tipo 'Rendición Fondo Fijo', no puede ser gestionado",
		TipoDocumentoCapEnum.K3
	),
	E_4307(
		"4307",
		"El documento es de tipo 'Tasas Impuestos IMP', no puede ser gestionado",
		TipoDocumentoCapEnum.K4
	),
	E_4308(
		"4308",
		"El documento es de tipo 'Tasas Impuestos CAP', no puede ser gestionado",
		TipoDocumentoCapEnum.K5
	),
	E_4309(
		"4309",
		"El documento es de tipo 'Comision recaudacion', no puede ser gestionado",
		TipoDocumentoCapEnum.K6
	),
	E_4310(
		"4310",
		"El documento es de tipo 'Doc. anticipos Comex', no puede ser gestionado",
		TipoDocumentoCapEnum.K7
	),
	E_4311(
		"4311",
		"El documento es de tipo 'Liquido Producto', no puede ser gestionado",
		TipoDocumentoCapEnum.K8
	),
	E_4312(
		"4312",
		"El documento es de tipo 'Recarga T. Monedero', no puede ser gestionado",
		TipoDocumentoCapEnum.K9
	),
	E_4313(
		"4313",
		"El documento es de tipo 'Doc.Anul.Fact.Prov.', no puede ser gestionado",
		TipoDocumentoCapEnum.KA
	),
	E_4314(
		"4314",
		"El documento es de tipo 'Factura Prov Exterio', no puede ser gestionado",
		TipoDocumentoCapEnum.KE
	),
	E_4315(
		"4315",
		"El documento es de tipo 'N Crédito Prov Exter', no puede ser gestionado",
		TipoDocumentoCapEnum.KF
	),
	E_4316(
		"4316",
		"El documento es de tipo 'N Débito Prov Exteri', no puede ser gestionado",
		TipoDocumentoCapEnum.KG
	),
	E_4317(
		"4317",
		"El documento es de tipo 'Factura Serv Public', no puede ser gestionado",
		TipoDocumentoCapEnum.KH
	),
	E_4318(
		"4319",
		"El documento es de tipo 'N Crédito Serv Publi', no puede ser gestionado",
		TipoDocumentoCapEnum.KI
	),
	E_4319(
		"4319",
		"El documento es de tipo 'Fact. de Telecentros', no puede ser gestionado",
		TipoDocumentoCapEnum.KJ
	),
	E_4320(
		"4320",
		"El documento es de tipo 'Despacho Aduana', no puede ser gestionado",
		TipoDocumentoCapEnum.KO
	),
	E_4321(
		"4321",
		"El documento es de tipo 'Otr Comprob Serv Ext', no puede ser gestionado",
		TipoDocumentoCapEnum.KP
	),
	E_4322(
		"4322",
		"El documento es de tipo 'Pago PM o Compensac', no puede ser gestionado",
		TipoDocumentoCapEnum.KS
	),
	E_4323(
		"4323",
		"El documento es de tipo 'Provisiones acumulab', no puede ser gestionado",
		TipoDocumentoCapEnum.KU
	),
	E_4324(
		"4324",
		"El documento es de tipo 'Provis c/OC p/Gasto', no puede ser gestionado",
		TipoDocumentoCapEnum.KV
	),
	E_4325(
		"4325",
		"El documento es de tipo 'Provis c/OC p/FI-AA', no puede ser gestionado",
		TipoDocumentoCapEnum.KW
	),
	E_4326(
		"4326",
		"El documento es de tipo 'Provisiones sin OC', no puede ser gestionado",
		TipoDocumentoCapEnum.KX
	),
	E_4327(
		"4327",
		"El documento es de tipo 'Comisiones bancarias', no puede ser gestionado",
		TipoDocumentoCapEnum.KY
	),
	E_4328(
		"4328",
		"El documento es de tipo 'NC_Dif. de Cambio', no puede ser gestionado",
		TipoDocumentoCapEnum.EX
	),
	E_4329(
		"4329",
		"El documento es de tipo 'FC_Dif. de Cambio', no puede ser gestionado",
		TipoDocumentoCapEnum.EQ
	),
	E_4331(
		"4331",
		"El documento es de tipo 'Dif. de Cambio', no puede ser gestionado",
		TipoDocumentoCapEnum.QU
	),
	E_4332(
		"4332",
		"El documento es de tipo 'Pago a Proveedor', no puede ser gestionado",
		TipoDocumentoCapEnum.KZ
	),
	E_4333(
		"4333",
		"El documento es de tipo 'ND_Dif. de Cambio', no puede ser gestionado",
		TipoDocumentoCapEnum.XE
	),
	E_4334(
		"4334",
		"El documento es de tipo 'Doc. Contable Manual', no puede ser gestionado",
		TipoDocumentoCapEnum.SA
	),
	E_tipoDocumentoCapAB(
			"4334",
			"El documento es de tipo 'Doc.Anulac.Contab.', no puede ser gestionado",
			TipoDocumentoCapEnum.AB
			),
	E_tipoDocumentoCapCC(
			"4334",
			"El documento es de tipo 'Conv - Not Créd Prov', no puede ser gestionado",
			TipoDocumentoCapEnum.CC
			),
	E_tipoDocumentoCapCD(
			"4334",
			"El documento es de tipo 'Conv - ND emitida', no puede ser gestionado",
			TipoDocumentoCapEnum.CD
			),
	E_tipoDocumentoCapCE(
			"4334",
			"El documento es de tipo 'Conv - NC emitida', no puede ser gestionado",
			TipoDocumentoCapEnum.CE
			),
	E_tipoDocumentoCapCF(
			"4334",
			"El documento es de tipo 'Conv - Factura Prov', no puede ser gestionado",
			TipoDocumentoCapEnum.CF
			),
	E_tipoDocumentoCapCI(
			"4334",
			"El documento es de tipo 'Conv - Docum intern', no puede ser gestionado",
			TipoDocumentoCapEnum.CI
			),
	E_tipoDocumentoCapDJ(
			"4334",
			"El documento es de tipo 'RC Cliente Canje.', no puede ser gestionado",
			TipoDocumentoCapEnum.DJ
			),
	E_tipoDocumentoCapHR(
			"4334",
			"El documento es de tipo 'Módulo HR', no puede ser gestionado",
			TipoDocumentoCapEnum.HR
			),
	E_tipoDocumentoCapHS(
			"4334",
			"El documento es de tipo 'Sueldos Confidencial', no puede ser gestionado",
			TipoDocumentoCapEnum.HS
			),
	E_tipoDocumentoCapDT(
			"4334",
			"El documento es de tipo 'Doc_LiqTarj Corp', no puede ser gestionado",
			TipoDocumentoCapEnum.DT
			),
	E_tipoDocumentoCapMS(
			"4334",
			"El documento es de tipo 'Rendición Tarj Corp', no puede ser gestionado",
			TipoDocumentoCapEnum.MS
			),
	E_tipoDocumentoCapN1(
			"4334",
			"El documento es de tipo 'Sueld Ajustes y Comp', no puede ser gestionado",
			TipoDocumentoCapEnum.N1
			),
	E_tipoDocumentoCapN2(
			"4334",
			"El documento es de tipo 'Sueld Carg Sociales', no puede ser gestionado",
			TipoDocumentoCapEnum.N2
			),
	E_tipoDocumentoCapN3(
			"4334",
			"El documento es de tipo 'Sueld Embargos', no puede ser gestionado",
			TipoDocumentoCapEnum.N3
			),
	E_tipoDocumentoCapN4(
			"4334",
			"El documento es de tipo 'Sueldos Anticipos', no puede ser gestionado",
			TipoDocumentoCapEnum.N4
			),
	E_tipoDocumentoCapN5(
			"4334",
			"El documento es de tipo 'Comp Sueldos a Pagar', no puede ser gestionado",
			TipoDocumentoCapEnum.N5
			),
	E_tipoDocumentoCapN6(
			"4334",
			"El documento es de tipo 'Liquidacion Sueldos', no puede ser gestionado",
			TipoDocumentoCapEnum.N6
			),
	E_tipoDocumentoCapN7(
			"4334",
			"El documento es de tipo 'Documento Interno', no puede ser gestionado",
			TipoDocumentoCapEnum.N7
			),
	E_tipoDocumentoCapN8(
			"4334",
			"El documento es de tipo 'Sueldo Imp.Pciales', no puede ser gestionado",
			TipoDocumentoCapEnum.N8
			),
	E_tipoDocumentoCapP1(
			"4334",
			"El documento es de tipo 'Aj canje Incr.CF', no puede ser gestionado",
			TipoDocumentoCapEnum.P1
			),
	E_tipoDocumentoCapP7(
			"4334",
			"El documento es de tipo 'Dc. Gen. Prov.CANJE.', no puede ser gestionado",
			TipoDocumentoCapEnum.P7
			),
	E_tipoDocumentoCapPE(
			"4334",
			"El documento es de tipo 'Prov. Exterior Canje', no puede ser gestionado",
			TipoDocumentoCapEnum.PE
			),
	E_tipoDocumentoCapPJ(
			"4334",
			"El documento es de tipo 'Factura de Canje', no puede ser gestionado",
			TipoDocumentoCapEnum.PJ
			),
	E_tipoDocumentoCapPK(
			"4334",
			"El documento es de tipo 'ND de Canje', no puede ser gestionado",
			TipoDocumentoCapEnum.PK
			),
	E_tipoDocumentoCapPL(
			"4334",
			"El documento es de tipo 'NC de Canje', no puede ser gestionado",
			TipoDocumentoCapEnum.PL
			),
	E_tipoDocumentoCapPY(
			"4334",
			"El documento es de tipo 'Compensación Canje', no puede ser gestionado",
			TipoDocumentoCapEnum.PY
			),
	E_tipoDocumentoCapYP(
			"4334",
			"El documento es de tipo 'FC Canje', no puede ser gestionado",
			TipoDocumentoCapEnum.YP
			),
	E_tipoDocumentoCapYQ(
			"4334",
			"El documento es de tipo 'NC Canje', no puede ser gestionado",
			TipoDocumentoCapEnum.YQ
			),
	E_tipoDocumentoCapZA(
			"4334",
			"El documento es de tipo 'Doc.Anul.Fact.Prov.', no puede ser gestionado",
			TipoDocumentoCapEnum.ZA
			),
	E_tipoDocumentoCapZP(
			"4334",
			"El documento es de tipo 'Pagos automáticos', no puede ser gestionado",
			TipoDocumentoCapEnum.ZP
			),
	E_4501(
		"4501",
		"El documento está asociado a un proveedor inhabilitado. Bloqueo diferente a 'Autorizado el pago'",
		null
	),
	E_4502(
		"4502",
		"El documento está asociado a un proveedor inhabilitado. Estado Bloqueo 'Probl.usuario requir'",
		EstadoBloqueoSapEnum.B
	),
	E_4503(
		"4503",
		"El documento está asociado a un proveedor inhabilitado. Estado Bloqueo 'Probl. proveedor'",
		EstadoBloqueoSapEnum.C
	),
	E_4504(
		"4504",
		"El documento está asociado a un proveedor inhabilitado. Estado Bloqueo 'Bloqueos Combinados'",
		EstadoBloqueoSapEnum._0
	),
	E_4505(
		"4505",
		"El documento está asociado a un proveedor inhabilitado. Estado Bloqueo 'Créd retenido temp.'",
		EstadoBloqueoSapEnum._1
	),
	E_4506(
			"4506",
			"El documento está asociado a un proveedor inhabilitado. Estado Bloqueo 'Embargo judicial.'",
			EstadoBloqueoSapEnum._2
		),
	E_4507(
			"4507",
			"El documento está asociado a un proveedor inhabilitado. Estado Bloqueo ' Ret RG 726 AFIP.'",
			EstadoBloqueoSapEnum._3
		),
	E_4508(
			"4508",
			"El documento está asociado a un proveedor inhabilitado. Estado Bloqueo ' Completar datos fisc.'",
			EstadoBloqueoSapEnum._4
	),
	E_3200(
			"3200",
			"La Sociedad 3200 no puede ser gestionada." ,
			null),
	E_4509(
			"4509",
			"El documento está asociado a un proveedor inhabilitado. Estado Bloqueo ' RBA  Pago bloqueado.'",
			EstadoBloqueoSapEnum._5
		),
		E_4510(
				"451-",
				"El documento está asociado a un proveedor inhabilitado. Estado Bloqueo 'FC Apócrifas.'",
				EstadoBloqueoSapEnum._6
			);
	String codigo;
	String descripcion;
	Object objetoDiscriminador; // Se utiliza para discriminar el mensaje de error
	
	private ErroresDocumentosCapEnum(String codigo, String descripcion, Object objetoDiscriminador) {
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
