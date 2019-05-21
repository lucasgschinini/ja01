package ar.com.telecom.shiva.presentacion.bean.dto;

import java.text.ParseException;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.telecom.shiva.base.enumeradores.EstadoBloqueoSapEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SemaforoGestionabilidadEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDocumentoCapEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRenglonSapEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.util.IDatosComunesEntrada;

// u578936
@JsonIgnoreProperties (ignoreUnknown=true)
public class DocumentoCapDto extends DocumentoCapBd implements IDatosComunesEntrada {
	private static final long serialVersionUID = 20161226L;

	private Long idClienteLegado;
	
	private String saldoPesificado;
	private String importePesificadoDocAsociado;

	
	//private String tipoCambioDocAsociado;
	private Boolean sinDifDeCambio;
	// Moneda del importe del cobro
	private MonedaEnum monedaImporte;
	
	private String tipoCambio;
	private String importeGestionable;
	private String saldoMonedaOrigen;
	
	
	
	
	// TODO PARA GESTIONABILIDAD
	private String idDocumentoCapReferencia;
	private SistemaEnum sistemaOrigen;
	private SiNoEnum marcaPagoCompensacionEnProcesoShiva;
	private SiNoEnum documentoIncluidoEnOtraOperacionShivaEnEdicionEnum;
	private SiNoEnum esDocumentoPreliminar;
	private SiNoEnum marcaReversionDeCobroProcesoPendiente;
	private SiNoEnum marcaReversionDeCobroEdicion;
	private SiNoEnum proveedorInhabilitado;
	private String proveedorInhabilitadoCodigo;
	
	// TODO Atributos para el manejo de la vista
	private TipoRenglonSapEnum tipoRenglon;
	private boolean even;
	private int posicionInterna;
	private Integer posicionEnDocumento;
	private Date fechaEmisionDocumentoPadre;
	private SiNoEnum huerfano;

	/**
	 * Contructores
	 */
	public DocumentoCapDto() {}

	public DocumentoCapDto(DocumentoCapDto dto) {
		super(dto);
		this.monedaImporte = dto.getMonedaImporte();
		this.idClienteLegado = dto.getIdClienteLegado();
		this.idDocumentoCapReferencia = this.getCodigoSociedad() + "-" + this.getNroDocumentoSap() + "-" + this.getEjercicioFiscalDocSAP() + "-" + 0;
		this.setSistemaOrigen(SistemaEnum.SAP);
	}
		
	// TODO Atributos para el manejo de la vista
	public String getIdPantalla() {
		StringBuffer str = new StringBuffer();
		str.append(this.getCodigoSociedad());
		if (this.isEsReglonAsociado()) {
			str.append(Utilidad.reemplazarEspacioPorGuion(this.getNumeroDocSAPVinculado()));
		} else {
			str.append(Utilidad.reemplazarEspacioPorGuion(this.getNroDocumentoSap()));
		}
		return str.toString();
	}

	public String getIdPantallaSelected() {
		StringBuffer str = new StringBuffer(this.getIdPantalla());
		str.append("-");
		str.append(this.posicionInterna);
		return str.toString();
	}
	// Se utiliza para vincular los documentos Padre e hijo
	public String getIdPantallaRenglon() {
		StringBuffer str = new StringBuffer(this.getIdPantalla());
		str.append("-");
		if (this.isEsReglonAsociado()) {
			str.append(this.getPosicionDocSAPVinculado());
		} else if (this.isEsReglonAgrupador()){
			str.append(0);
		} else {
			str.append(this.getPosicionDocSAP());
		}
		return str.toString();
	}
	
	public void setPosicionInterna(int posicionInterna) {
		this.posicionInterna = posicionInterna;
	}

	public SiNoEnum getProveedorInhabilitado() {
		return proveedorInhabilitado;
	}
	public void setProveedorInhabilitado(SiNoEnum proveedorInhabilitado) {
		this.proveedorInhabilitado = proveedorInhabilitado;
	}
	public MonedaEnum getMonedaImporte() {
		return monedaImporte;
	}
	public void setMonedaImporte(MonedaEnum monedaImporte) {
		this.monedaImporte = monedaImporte;
	}
	@Override
	public SistemaEnum getSistemaOrigen() {
		return this.sistemaOrigen;
	}

	// Atributos que se visualizan o se usan en la tabla
	// idClienteLegado
	public Long getIdClienteLegado() {
		return idClienteLegado;
	}
	public void setIdClienteLegado(Long idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}
	// idNumeroProveedor
	public String getIdNumeroProveedor() {
		return super.getIdNumeroProveedor();
	}
	public void setIdNumeroProveedor(String idNumeroProveedor) {
		super.setIdNumeroProveedor(idNumeroProveedor);
	}
	// Tipo de documento
	public TipoDocumentoCapEnum getTipoDocumento() {
		return super.getClaseDocSAP();
	}
	public String getTipoDocumentoCodigo() {
		String salida = "-";
		if (!Validaciones.isObjectNull(super.getClaseDocSAP())) {
			salida = super.getClaseDocSAP().getCodigo();
		}
		return salida;
	}
	// fecha de emision
	public String getFechaEmision() {
		return Utilidad.formatDatePicker(super.getFechaDocProveedor());
	}
	public void setFechaEmision(String fechaEmision) throws NegocioExcepcion {
		try {
			super.setFechaDocProveedor(Utilidad.parseDatePickerString(fechaEmision));
		} catch (ParseException e) {
			throw new NegocioExcepcion("Error al parsear la fecha de emision");
		}
	}
	// Numero renglon
	public Integer getNumeroRenglon() {
		return super.getPosicionDocSAP();
	}
	public void setNumeroRenglon(Integer numeroRenglon) {
		super.setPosicionDocSAP(numeroRenglon);
	}
	// Numero Sap
	public String getNroLegalSap() {
		return super.getNumeroLegalDocProveedor();
	}
	// Moneda
	public MonedaEnum getMoneda() {
		return super.getMonedaDocProveedor();
	}
	public void setMoneda(MonedaEnum moneda) {
		super.setMonedaDocProveedor(moneda);
	}
	// Signo de la moneda
	public String getMonedaSigno2() {
		return super.getMonedaDocProveedorSigno2();
	}
	
	// impore de la moneda origen
	public String getImporteMonedaOrigen() {
		String salida = new String("-");
		if (TipoRenglonSapEnum.AGRUPADOR.equals(this.tipoRenglon)) {
			salida = Utilidad.formatCurrency(super.getImpTotalDocMonedaDoc(), false, false);
		}
		return salida;
	}
	
	public void setImporteMonedaOrigen(String importeMonedaOrigen) {
		
	}
	// Tipo de cambio...Valor modificable en tabla
	public String getTipoCambio() {
		if (!this.isEsReglonAsociado()) {
			return this.tipoCambio;
		}
		return "-";
	}
	public String getTipoCambioEmision() {
		return Utilidad.formatDecimales(super.getTipoCambioRegistradoSAP(), 7);
	}
	public String getTipoCambioShiva() {
		return Utilidad.formatDecimales(super.getTipoCambioAFechaTipoCambio(), 7);
	}
	
	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	// importe Gestioble ...Valor modificable en tabla
	public String getImporteGestionable() {
		return this.importeGestionable;
	}
	public String getImporteGestionableEmision() {
		String salida = "-";
		if (this.isEsReglonAgrupador() && !this.isEsNoGestionable()) {
			salida = Utilidad.formatCurrency(super.getImporteGestionableEmisionBd(), false, false,2);
		}
		return salida;
	}
	public void setImporteGestionable(String importeGestionable) {
		this.importeGestionable = importeGestionable;
	}

	
	public String getImporteGestionableAFechaShivaTotal() {
		String salida = "-";
		if (this.isEsReglonAgrupador() && !this.isEsNoGestionable()) {
			salida = Utilidad.formatCurrency(super.getImporteGestionableAFechaShivaTotalBd(), false, false,2);
		}
		return salida;
	}
	public String getImporteGestionableAFechaEmisionTotal() {
		String salida = "-";
		if (this.isEsReglonAgrupador() && !this.isEsNoGestionable()) {
			salida = Utilidad.formatCurrency(super.getImporteGestionableAFechaEmisionTotalBd(), false, false,2);
		}
		return salida;
	}
	
	// Saldo en moneda origen
	public String getSaldoMonedaOrigen() {
		return this.saldoMonedaOrigen;
	}
	public void setSaldoMonedaOrigen(String saldoMonedaOrigen) {
		this.saldoMonedaOrigen = saldoMonedaOrigen;
	}
	
	// Saldo pesificado ...Valor modificable en tabla
	public String getSaldoPesificado() {
		String salida = "-";
		if (!this.monedaImporte.equals(super.getMonedaDocProveedor())) {
			salida = this.saldoPesificado;
		}
		return salida;
	}
	public void setSaldoPesificado(String saldoPesificado) {
		this.saldoPesificado = saldoPesificado;
	}
	public String getSaldoPesificadoEmision() {
		String salida = "-";
		if (!this.monedaImporte.equals(super.getMonedaDocProveedor())) {
			if (this.isEsReglonAgrupador()) {
				salida = this.getSaldoPesificadoTotalAFechaEmisionaString();
			} else if (this.isEsReglonCap()) {
				salida = Utilidad.formatCurrency(super.getImporteDoc(), false, false,2);
			}
		}
		return salida;
	}
	public String getSaldoPesificadoTotalAFechaShivaString() {
		String salida = "-";
		if (this.isEsReglonAgrupador()) {
			salida = Utilidad.formatCurrency(super.getSaldoPesificadoTotalAFechaShivaBd(), false, false,2);
		}
		return salida;
	}
	public String getSaldoPesificadoTotalAFechaEmisionaString() {
		String salida = "-";
		if (this.isEsReglonAgrupador()) {
			salida = Utilidad.formatCurrency(super.getSaldoPesificadoTotalAFechaEmisionBd(), false, false,2);
		} 
		return salida;
	}
	
	public String getImpDocPESToString() {
		String salida = "-";
		if (this.isEsReglonCap() || this.isEsReglonAsociado()) {
			salida = Utilidad.formatCurrency(super.getImpDocPES(), false, false,2);
		}
		return salida;
	}
	public String getImporteDocToString() {
		String salida = "-";
		if (this.isEsReglonCap() || this.isEsReglonAsociado()) {
			salida = Utilidad.formatCurrency(super.getImporteDoc(), false, false,2);
		}
		return salida;
	}
	public String getImporteDiferenciaCambio(){
		String importe;
		if ( !this.isEsMismaMoneda() && (this.isEsReglonCap() || this.isEsReglonAsociado()) ) {
				importe = Utilidad.formatCurrency(this.getImporteDoc().add(this.getImpDocPES().negate()), false, false,2);
			} else {
			importe = "-";
		}
		
		return importe;
	}
			
	/**
	 * @return the importeMonedaOrigenDocAsociado
	 */
	public String getImporteMonedaOrigenDocAsociado() {
		String salida = new String("-");
		if (this.isEsReglonAsociado()) {
			salida = Utilidad.formatCurrency(super.getImporteDocMonedaDoc(), false, false, 2);
		}
		return salida;
	}
	/**
	 * @param importeMonedaOrigenDocAsociado the importeMonedaOrigenDocAsociado to set
	 */
	public void setImporteMonedaOrigenDocAsociado(
			String importeMonedaOrigenDocAsociado) {
		
	}

	/**
	 * @return the importePesificadoDocAsociado
	 */
	public String getImportePesificadoDocAsociado() {
		String salida = "-";
		if (this.isEsReglonAgrupador() || (this.isEsReglonAsociado() && !this.isEsMismaMoneda())) {
				salida = this.importePesificadoDocAsociado;
		}
		return salida;
	}
	/**
	 * @param importePesificadoDocAsociado the importePesificadoDocAsociado to set
	 */
	public void setImportePesificadoDocAsociado(String importePesificadoDocAsociado) {
		this.importePesificadoDocAsociado = importePesificadoDocAsociado;
	}
	/**
	 * @return the importePesificadoDocAsociado
	 */
	public String getImportePesificadoDocAsociadoEmision() {
		String salida = "-";
		if (!this.isEsMismaMoneda()) {
			if (this.isEsReglonAgrupador()) {
				salida = Utilidad.formatCurrency(super.getImportePesificadoDocAsociadoEmisionBd(), false, false, 2);
			} else if (this.isEsReglonAsociado()) {
				salida = this.getImporteDocToString();
			}
		}
		
		return salida;
	}



	/**
	 * @return the tipoCambioDocAsociado
	 */
	public String getTipoCambioDocAsociado() {
		
		String salida = new String("-");
		if (this.isEsReglonAsociado() && !this.isEsMismaMoneda()) {
			
				salida = this.tipoCambio;
			
		}
		return salida;
	}

	
	

	/**
	 * @param tipoCambioDocAsociado the tipoCambioDocAsociado to set
	 */
	public void setTipoCambioDocAsociado(String tipoCambioDocAsociado) {
		//this.tipoCambioDocAsociado = tipoCambioDocAsociado;
	}
	/**
	 * @return the sinDifDeCambio
	 */
	public Boolean getSinDifDeCambio() {
		return sinDifDeCambio;
	}

	/**
	 * @param sinDifDeCambio the sinDifDeCambio to set
	 */
	public void setSinDifDeCambio(Boolean sinDifDeCambio) {
		this.sinDifDeCambio = sinDifDeCambio;
	}
	/**
	 * @return the marcaPagoCompensacionEnProcesoShiva
	 */
	public SiNoEnum getMarcaPagoCompensacionEnProcesoShiva() {
		return marcaPagoCompensacionEnProcesoShiva;
	}


	/**
	 * @param marcaPagoCompensacionEnProcesoShiva the marcaPagoCompensacionEnProcesoShiva to set
	 */
	public void setMarcaPagoCompensacionEnProcesoShiva(
			SiNoEnum marcaPagoCompensacionEnProcesoShiva) {
		this.marcaPagoCompensacionEnProcesoShiva = marcaPagoCompensacionEnProcesoShiva;
	}




	/**
	 * @return the documentoIncluidoEnOtraOperacionShivaEnEdicionEnum
	 */
	public SiNoEnum getDocumentoIncluidoEnOtraOperacionShivaEnEdicionEnum() {
		return documentoIncluidoEnOtraOperacionShivaEnEdicionEnum;
	}




	/**
	 * @param documentoIncluidoEnOtraOperacionShivaEnEdicionEnum the documentoIncluidoEnOtraOperacionShivaEnEdicionEnum to set
	 */
	public void setDocumentoIncluidoEnOtraOperacionShivaEnEdicionEnum(
			SiNoEnum documentoIncluidoEnOtraOperacionShivaEnEdicionEnum) {
		this.documentoIncluidoEnOtraOperacionShivaEnEdicionEnum = documentoIncluidoEnOtraOperacionShivaEnEdicionEnum;
	}

	


	/**
	 * @return the marcaReversionDeCobroEdicion
	 */
	public SiNoEnum getMarcaReversionDeCobroEdicion() {
		return marcaReversionDeCobroEdicion;
	}

	/**
	 * @param marcaReversionDeCobroEdicion the marcaReversionDeCobroEdicion to set
	 */
	public void setMarcaReversionDeCobroEdicion(
			SiNoEnum marcaReversionDeCobroEdicion) {
		this.marcaReversionDeCobroEdicion = marcaReversionDeCobroEdicion;
	}

	



	/**
	 * @return the esDocumentoPreliminar
	 */
	public SiNoEnum getEsDocumentoPreliminar() {
		return esDocumentoPreliminar;
	}




	/**
	 * @param esDocumentoPreliminar the esDocumentoPreliminar to set
	 */
	public void setEsDocumentoPreliminar(SiNoEnum esDocumentoPreliminar) {
		this.esDocumentoPreliminar = esDocumentoPreliminar;
	}




	/**
	 * @return the marcaReversionDeCobroProcesoPendiente
	 */
	public SiNoEnum getMarcaReversionDeCobroProcesoPendiente() {
		return marcaReversionDeCobroProcesoPendiente;
	}




	/**
	 * @param marcaReversionDeCobroProcesoPendiente the marcaReversionDeCobroProcesoPendiente to set
	 */
	public void setMarcaReversionDeCobroProcesoPendiente(
			SiNoEnum marcaReversionDeCobroProcesoPendiente) {
		this.marcaReversionDeCobroProcesoPendiente = marcaReversionDeCobroProcesoPendiente;
	}




	/**
	 * @return the tipoRenglon
	 */
	public TipoRenglonSapEnum getTipoRenglon() {
		return tipoRenglon;
	}




	/**
	 * @param tipoRenglon the tipoRenglon to set
	 */
	public void setTipoRenglon(TipoRenglonSapEnum tipoRenglon) {
		this.tipoRenglon = tipoRenglon;
	}

	/**
	 * @return the even
	 */
	public boolean isEven() {
		return even;
	}

	/**
	 * @param even the even to set
	 */
	public void setEven(boolean even) {
		this.even = even;
	}

	/**
	 * @param sistemaOrigen the sistemaOrigen to set
	 */
	public void setSistemaOrigen(SistemaEnum sistemaOrigen) {
		this.sistemaOrigen = sistemaOrigen;
	}
	// Setter y getter visuales

	
	
	public boolean isEsMismaMoneda() {
		return this.monedaImporte == super.getMonedaDocProveedor();
	}

	/**
	 * @return the idDocumentoCapReferencia
	 */
	public String getIdDocumentoCapReferencia() {
		return idDocumentoCapReferencia;
	}

	/**
	 * @param idDocumentoCapReferencia the idDocumentoCapReferencia to set
	 */
	public void setIdDocumentoCapReferencia(String idDocumentoCapReferencia) {
		this.idDocumentoCapReferencia = idDocumentoCapReferencia;
	}

	/**
	 * @return the fechaEmisionDocumentoPadre
	 */
	public Date getFechaEmisionDocumentoPadre() {
		return fechaEmisionDocumentoPadre;
	}

	/**
	 * @param fechaEmisionDocumentoPadre the fechaEmisionDocumentoPadre to set
	 */
	public void setFechaEmisionDocumentoPadre(Date fechaEmisionDocumentoPadre) {
		this.fechaEmisionDocumentoPadre = fechaEmisionDocumentoPadre;
	}
	public Date getFechaEmisionOrdenacion() {
		Date fecha;
		
		
		if (
			this.isEsReglonAsociado()
		) {
			fecha = this.getFechaEmisionDocumentoPadre();
		} else {
			fecha = super.getFechaDocProveedor();
		}
		
		return fecha;
	}

	/**
	 * @return the posicionEnDocumento
	 */
	public Integer getPosicionEnDocumento() {
		return posicionEnDocumento;
	}

	/**
	 * @param posicionEnDocumento the posicionEnDocumento to set
	 */
	public void setPosicionEnDocumento(Integer posicionEnDocumento) {
		this.posicionEnDocumento = posicionEnDocumento;
	}
	// seudoAtributos de control
		public boolean isEsGestionableRojo() {
			try {
				return SemaforoGestionabilidadEnum.ROJO.getDescripcion().equalsIgnoreCase(this.getSemaforo().getSemaforo());
			} catch (Exception e) {
				System.out.println("asdasdasasdadasdwqe");
			}
			return false;
		}
		public boolean isEsGestionableNaranja() {
			
			try {
			return SemaforoGestionabilidadEnum.NARANJA.getDescripcion().equalsIgnoreCase(this.getSemaforo().getSemaforo());
			} catch (Exception e) {
				System.out.println("asdasdasasdadasdwqe");
			}
			return false;
		}
		public boolean isEsNoGestionable() {
			return this.isEsGestionableRojo() || this.isEsGestionableNaranja();
		}
		public boolean isEsReglonAgrupador() {
			return TipoRenglonSapEnum.AGRUPADOR.equals(this.tipoRenglon);
		}
		public boolean isEsReglonAsociado() {
			return TipoRenglonSapEnum.REF.equals(this.tipoRenglon) || TipoRenglonSapEnum.REF_FIN.equals(this.tipoRenglon);
		}
		public boolean isEsReglonCap() {
			return TipoRenglonSapEnum.CAP.equals(this.tipoRenglon) || TipoRenglonSapEnum.CAP_FIN.equals(this.tipoRenglon);
		}
		public boolean isEsRenglonFin() {
			return TipoRenglonSapEnum.REF_FIN.equals(this.tipoRenglon) || TipoRenglonSapEnum.CAP_FIN.equals(this.tipoRenglon);
		}
		public Integer obtenerTipoRenglon() {
			Integer prioridad = -1;
			switch (this.tipoRenglon) {
			case AGRUPADOR:
				prioridad = 0;
				break;
			case CAP:
				prioridad = 1;
				break;
			case REF:
				prioridad = 2;
				break;
			case REF_FIN:
				prioridad = 3;
				break;
			case CAP_FIN:
				prioridad = 4;
				break;
			default:
				break;
			}
			
			return prioridad;
		}
		public Integer getPosicionSap() {
			if (this.isEsReglonAgrupador()) {
				return 0;
			} else if (this.isEsReglonAsociado()) {
				return super.getPosicionDocSAPVinculado();
			} else {
				return super.getPosicionDocSAP();
			}
		}
		public int obtenerSemaforoPrioridad() {
			return super.obtenerSemaforoPrioridad();
		}
		public String obtenerSemaforoColor() {
			return super.obtenerSemaforoColor();
		}
		@Override
		public IdDocumento getIdDocumentoCalipso() {
			return null;
		}

		@Override
		public String getNumeroReferenciaMic() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getClaseString() {
			return this.getClass().getName();
		}

		// Getter and Setter
		/**
		 * @return the posicionInterna
		 */
		public int getPosicionInterna() {
			return posicionInterna;
		}

		/**
		 * @return the huerfano
		 */
		public SiNoEnum getHuerfano() {
			return huerfano;
		}

		/**
		 * @param huerfano the huerfano to set
		 */
		public void setHuerfano(SiNoEnum huerfano) {
			this.huerfano = huerfano;
		}
		
		public String contruirIdReferencia () {
			Integer posicion;
			if (this.isEsReglonAgrupador()) {
				posicion = 0;
			} else {
				posicion = this.getPosicionDocSAP();
			}
			return this.getCodigoSociedad() + "-" + this.getNroDocumentoSap() + "-" + this.getEjercicioFiscalDocSAP() + "-" + posicion;
		}
		
		public String contruirIdReferenciaVinculado() {
			return this.getCodigoSociedad() + "-" + this.getNumeroDocSAPVinculado() + "-" + this.getEjercicioFiscalDocSAPVinculado() + "-" + this.getPosicionDocSAPVinculado();
		}
		
		public EstadoBloqueoSapEnum getEstadoBloqueoSapEnum() {
			return super.getBloqueoPago();
		}

		/**
		 * @return the proveedorInhabilitadoCodigo
		 */
		public String getProveedorInhabilitadoCodigo() {
			return proveedorInhabilitadoCodigo;
		}

		/**
		 * @param proveedorInhabilitadoCodigo the proveedorInhabilitadoCodigo to set
		 */
		public void setProveedorInhabilitadoCodigo(String proveedorInhabilitadoCodigo) {
			this.proveedorInhabilitadoCodigo = proveedorInhabilitadoCodigo;
		}
		
		//Todos los campos que se utilizan en la vista de detalle
		public String getSinDifDeCambioDetalle(){
			if(!Validaciones.isObjectNull(getSinDifDeCambio())){
				if ((tipoRenglon.getCodigo() == "RENGLON_AGRUPADOR" || isEsReglonAsociado()) && !isEsNoGestionable() && !isEsMismaMoneda()){
					return "<input type=\"checkbox\"" + ( getSinDifDeCambio() ? " checked" : "" ) + " disabled>";
				}else{
					return "-";
				}
			} else {
				return null;
			}
				
		}
		
		public String getImportePesificadoDocAsociadoDetalle(){
			if(!Validaciones.isNullEmptyOrDash(getImportePesificadoDocAsociado())){
				return MonedaEnum.PES.getSigno2() + getImportePesificadoDocAsociado();
			} else {
				return "-";
			}
				
		}
		
		public String getImporteMonedaOrigenDocAsociadoDetalle() {
			if(isEsReglonAsociado() && !Validaciones.isNullEmptyOrDash(getImporteMonedaOrigenDocAsociado())){
				return getMonedaSigno2Detalle() + getImporteMonedaOrigenDocAsociado();
			} else {
				return "-";
			}
		}
	
		public String getImporteMonedaOrigenDetalle() {
			if(!Validaciones.isNullEmptyOrDash(getImporteMonedaOrigen())) {
					return getMonedaSigno2Detalle() + getImporteMonedaOrigen();
			} else {
				return "-";
			}
			
		}
		
		public String getImporteGestionableDetalle() {
			if(isEsReglonAgrupador() && !Validaciones.isNullEmptyOrDash(getImporteGestionable())) {
				return MonedaEnum.PES.getSigno2() + getImporteGestionable();
			} else {
				return "-";
			}
			
		}
		
		public String getSaldoMonedaOrigenDetalle() {
			if(!isEsReglonAsociado() && !Validaciones.isNullEmptyOrDash(getSaldoMonedaOrigen())) {
					return getMonedaSigno2Detalle() + getSaldoMonedaOrigen();
			} else {
				return "-";
			}
			
		}

		public String getSaldoPesificadoDetalle() {
			if(!isEsMismaMoneda() && !Validaciones.isNullEmptyOrDash(getSaldoPesificado())) {
				return MonedaEnum.PES.getSigno2() + getSaldoPesificado();
			} else {
				return "-";
			}
			
		}

		public String getImporteDiferenciaCambioDetalle() {
			if(Validaciones.isObjectNull(getSinDifDeCambio()) || !getSinDifDeCambio()) {
				return getImporteDiferenciaCambio();
			} else {
				return "-";
			}
			
		}
		
		public String getMonedaSigno2Detalle() {
			if(MonedaEnum.EUR.getSigno2().equals(getMonedaDocProveedorSigno2())){
				return "&euro;";
			}else{
				return super.getMonedaDocProveedorSigno2();
			}
		}
		//
}