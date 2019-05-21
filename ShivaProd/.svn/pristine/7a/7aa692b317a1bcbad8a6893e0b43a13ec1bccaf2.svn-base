package ar.com.telecom.shiva.negocio.batch.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import ar.com.telecom.shiva.base.dto.Batch;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPagoEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;



public class TagetikBatch extends Batch {
	private static final long serialVersionUID = 1L;
	
	private Integer numeroTransaccion;
	private Integer ciclo;
	private Integer tipoFactura;
	private TipoPagoEnum tipoPago;
	private Date fechaEmision;
	private Date fechaVencimiento;
	private Date fechaValor;
	private String idTipoMedioPago;
	private String marketingGroup;
	private String caja;
	private BigDecimal importe;
	private Long idCliente;
	private String razonSocial;
	private String tipoCliente;
	private TipoComprobanteEnum tipoComprobante;
	private TipoClaseComprobanteEnum claseComprobante;
	private String sucComprobante;
	private String numComprobante;
	private String idCuentaCob;
	private Long idOperacion;
	private MonedaEnum moneda;
	private String fechaWorkflow;
	private String origen;
	private BigDecimal tipoCambio;
	private String descobro;
	// Sprint Dolares
	// SubTipoDocumentoEnum.java  --> Código interno que identifica el subtipo de documento emitido por Calipso (AUT, DIF, MAN, etc)
	private String subtipoDocumento; // subtipo_de_documento
	// Iddocctascob del documento padre --> ID del documento que originó a partir del cobro del mismo el presente documento.  Este campo se informará solamente en el cobro que lo origina.  En caso de hacer un pago parcial posterior no se informará. 
	private String idCuentaCobPadre; // ID_CUENTA_COB_PADRE 
	// Moneda del medio de pago --> Moneda de medio de pago (ARS, DOL)
	private MonedaEnum monedaMedioPago; // MONEDA_MEDIO_PAGO
	// Tipo de cambio del medio de pago --> Tipo de cambio a fecha valor del medio de pago.  Formato: 000,00000
	private BigDecimal tipoCambioMedioPago; // TIPO_CAMBIO_MEDIO_PAGO
	// Importe del medio de pago en moneda origen --> Importe aplicado del medio de pago expresado en la moneda origen del mismo
	private BigDecimal importeMedioPagoMonedaOrigen; // IMPORTE_MED_PAGO_MONEDA_ORIGEN
	// Moneda del cobro o operacion
	private MonedaEnum monedaOperacion;

	
	public TagetikBatch(Map<String,Object> map){
		if (map.get("ciclo") != null) {
			ciclo = Integer.valueOf(((BigDecimal)map.get("ciclo")).intValue());
		}
		if (map.get("tipo_factura") != null) {
			tipoFactura = Integer.valueOf(((BigDecimal)map.get("tipo_factura")).intValue());
		}
		if (map.get("tipo_pago") != null) {
			tipoPago = TipoPagoEnum.getEnumByName((String)map.get("tipo_pago"));
		}
		if (map.get("fecha_emision") != null) {
			fechaEmision = (Date)map.get("fecha_emision");
		}
		if (map.get("fecha_vencimiento") != null) {
			fechaVencimiento = (Date)map.get("fecha_vencimiento");
		}
		if (map.get("fecha_valor") != null) {
			fechaValor = (Date)map.get("fecha_valor");
		}
		if (map.get("id_tipo_medio_pago") != null) {
			idTipoMedioPago = (String)map.get("id_tipo_medio_pago");
		}
		if (map.get("marketing") != null) {
			marketingGroup = (String)map.get("marketing");
		}
		if (map.get("caja") != null) {
			caja = (String)map.get("caja");
		}
		if (map.get("importe") != null) {
			importe = (BigDecimal)map.get("importe");
		}
		if (map.get("id_cliente") != null) {
			idCliente = Long.valueOf(((BigDecimal)map.get("id_cliente")).longValue());
		}
		if (map.get("razon_social") != null) {
			razonSocial = (String)map.get("razon_social");
		}
		if (map.get("tipo_cliente") != null) {
			tipoCliente = (String)map.get("tipo_cliente");
		}
		if (map.get("tipo_comprobante") != null) {
			tipoComprobante = TipoComprobanteEnum.getEnumByName((String)map.get("tipo_comprobante"));
		}
		if (map.get("clase_comprobante") != null) {
			claseComprobante = TipoClaseComprobanteEnum.getEnumByName((String)map.get("clase_comprobante"));
		}
		if (map.get("suc_comprobante") != null) {
			sucComprobante = (String)map.get("suc_comprobante");
		}
		if (map.get("num_comprobante") != null) {
			numComprobante = (String)map.get("num_comprobante");
		}
		if (map.get("id_cuenta_cob") != null) {
			idCuentaCob = ((BigDecimal)map.get("id_cuenta_cob")).toString();
		}
		if (map.get("id_ope") != null) {
			idOperacion = Long.valueOf(((BigDecimal)map.get("id_ope")).longValue());
		}
		if (map.get("mon") != null) {
			moneda = MonedaEnum.getEnumByString((String)map.get("mon"));
		}
		if (map.get("fecha_workflow") != null) {
			fechaWorkflow = Utilidad.formatDatePointSeparated((Date)map.get("fecha_workflow"));	
		}
		if(map.get("descobro") != null) {
			descobro = (String)map.get("descobro");
		}
		if (map.get("origen") != null) {
			origen = (String)map.get("origen");
		}
		if (map.get("tipo_cambio") != null) {
			tipoCambio = (BigDecimal)map.get("tipo_cambio");
		}
		// Sprintg Dolares
		if (!Validaciones.isObjectNull(map.get("subtipo_de_documento"))) {
			subtipoDocumento = (String) map.get("subtipo_de_documento");
		}
		if (!Validaciones.isObjectNull(map.get("ID_CUENTA_COB_PADRE"))) {
			idCuentaCobPadre = ((BigDecimal)map.get("ID_CUENTA_COB_PADRE")).toString();
		}
		if (!Validaciones.isObjectNull(map.get("MONEDA_MEDIO_PAGO"))) {
			monedaMedioPago =  MonedaEnum.getEnumByString((String)map.get("MONEDA_MEDIO_PAGO"));
		}
		if (!Validaciones.isObjectNull(map.get("TIPO_CAMBIO_MEDIO_PAGO"))) {
			tipoCambioMedioPago = (BigDecimal)map.get("TIPO_CAMBIO_MEDIO_PAGO");
		}
		if (!Validaciones.isObjectNull(map.get("IMPORTE_MED_PAGO_MONEDA_ORGEN"))) {
			importeMedioPagoMonedaOrigen = (BigDecimal)map.get("IMPORTE_MED_PAGO_MONEDA_ORGEN");
		}
		if (!Validaciones.isObjectNull(map.get("MONEDA_OPERACION"))) {
			monedaOperacion =  MonedaEnum.getEnumByString((String)map.get("MONEDA_OPERACION"));
		}
		if (map.get("NUMERO_TRANSACCION") != null) {
			numeroTransaccion = Integer.valueOf(((BigDecimal)map.get("NUMERO_TRANSACCION")).intValue());
		}
	}
	/**
	 * Retorna true si la moneda del documento es igual a la moneda del medio depago
	 * @return
	 */
	public boolean monedaIguales() {
		return this.moneda.equals(this.monedaMedioPago);
	}
	/**
	 * 
	 * @param tag
	 * @param importeEnMonedaOrigenAcu
	 * @param ultimoElemento
	 */
	public BigDecimal calcularImporte(BigDecimal importeEnMonedaOrigenAcu, boolean ultimoElemento) {
		BigDecimal importeEnMonedaOrigen = BigDecimal.ZERO;

		if (this.monedaIguales()) {
			if (ultimoElemento) {
				importeEnMonedaOrigen = this.importe.subtract(importeEnMonedaOrigenAcu);
				importeEnMonedaOrigenAcu = BigDecimal.ZERO;
			} else {
				importeEnMonedaOrigen = this.importeMedioPagoMonedaOrigen;
				importeEnMonedaOrigenAcu = importeEnMonedaOrigenAcu.add(importeEnMonedaOrigen);
			}
		} else {
			if (MonedaEnum.DOL.equals(this.moneda)) {
				if (ultimoElemento) {
					importeEnMonedaOrigen = this.importe.subtract(importeEnMonedaOrigenAcu);
					importeEnMonedaOrigenAcu = BigDecimal.ZERO;
				} else {
					if (importeMedioPagoMonedaOrigen == null || this.tipoCambio  == null) {
						Traza.error(getClass(), "NULLLLL1!!");
					}
					importeEnMonedaOrigen = this.importeMedioPagoMonedaOrigen.divide(this.tipoCambio, 2, BigDecimal.ROUND_HALF_DOWN);
					importeEnMonedaOrigenAcu = importeEnMonedaOrigenAcu.add(importeEnMonedaOrigen);
				}
				
			} else {
				if (ultimoElemento) {
					importeEnMonedaOrigen = this.importe.subtract(importeEnMonedaOrigenAcu);
					importeEnMonedaOrigenAcu = BigDecimal.ZERO;
				} else {
					importeEnMonedaOrigen = this.importeMedioPagoMonedaOrigen.multiply(this.tipoCambioMedioPago);
					importeEnMonedaOrigenAcu = importeEnMonedaOrigenAcu.add(importeEnMonedaOrigen);
				}
			}
		}
		this.importe = importeEnMonedaOrigen;
		return importeEnMonedaOrigenAcu;
	}
	public BigDecimal calcularImporte() {
		if (
			TipoComprobanteEnum.DEB.equals(this.getTipoComprobante()) &&
			!Validaciones.isNullEmptyOrDash(this.idCuentaCobPadre)
		) {
			this.importe = this.importeMedioPagoMonedaOrigen;
		}
		return this.importe;
	}
	
	public Integer getCiclo() {
		return ciclo;
	}

	public void setCiclo(Integer ciclo) {
		this.ciclo = ciclo;
	}

	public Integer getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(Integer tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public TipoPagoEnum getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(TipoPagoEnum tipoPago) {
		this.tipoPago = tipoPago;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Date getFechaValor() {
		return fechaValor;
	}

	public void setFechaValor(Date fechaValor) {
		this.fechaValor = fechaValor;
	}

	public String getIdTipoMedioPago() {
		return idTipoMedioPago;
	}

	public void setIdTipoMedioPago(String idTipoMedioPago) {
		this.idTipoMedioPago = idTipoMedioPago;
	}

	public String getMarketingGroup() {
		return marketingGroup;
	}

	public void setMarketingGroup(String marketingGroup) {
		this.marketingGroup = marketingGroup;
	}

	public String getCaja() {
		return caja;
	}

	public void setCaja(String caja) {
		this.caja = caja;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public TipoComprobanteEnum getTipoComprobante() {
		return tipoComprobante;
	}

	public void setTipoComprobante(TipoComprobanteEnum tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	public TipoClaseComprobanteEnum getClaseComprobante() {
		return claseComprobante;
	}

	public void setClaseComprobante(TipoClaseComprobanteEnum claseComprobante) {
		this.claseComprobante = claseComprobante;
	}

	public String getSucComprobante() {
		return sucComprobante;
	}

	public void setSucComprobante(String sucComprobante) {
		this.sucComprobante = sucComprobante;
	}

	public String getNumComprobante() {
		return numComprobante;
	}

	public void setNumComprobante(String numComprobante) {
		this.numComprobante = numComprobante;
	}

	public String getIdCuentaCob() {
		return idCuentaCob;
	}

	public void setIdCuentaCob(String idCuentaCob) {
		this.idCuentaCob = idCuentaCob;
	}

	public Long getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}

	public MonedaEnum getMoneda() {
		return moneda;
	}

	public void setMoneda(MonedaEnum moneda) {
		this.moneda = moneda;
	}

	public String getFechaWorkflow() {
		return fechaWorkflow;
	}

	public void setFechaWorkflow(String fechaWorkflow) {
		this.fechaWorkflow = fechaWorkflow;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public String getDescobro() {
		return descobro;
	}

	public void setDescobro(String descobro) {
		this.descobro = descobro;
	}

	/**
	 * @return the subtipoDocumento
	 */
	public String getSubtipoDocumento() {
		return subtipoDocumento;
	}

	/**
	 * @param subtipoDocumento the subtipoDocumento to set
	 */
	public void setSubtipoDocumento(String subtipoDocumento) {
		this.subtipoDocumento = subtipoDocumento;
	}

	/**
	 * @return the idCuentaCobPadre
	 */
	public String getIdCuentaCobPadre() {
		return idCuentaCobPadre;
	}

	/**
	 * @param idCuentaCobPadre the idCuentaCobPadre to set
	 */
	public void setIdCuentaCobPadre(String idCuentaCobPadre) {
		this.idCuentaCobPadre = idCuentaCobPadre;
	}

	/**
	 * @return the monedaMedioPago
	 */
	public MonedaEnum getMonedaMedioPago() {
		return monedaMedioPago;
	}

	/**
	 * @param monedaMedioPago the monedaMedioPago to set
	 */
	public void setMonedaMedioPago(MonedaEnum monedaMedioPago) {
		this.monedaMedioPago = monedaMedioPago;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TagetikBatch [idCuentaCob=" + idCuentaCob + ", idOperacion="
				+ idOperacion + ", origen=" + origen + "]";
	}

	/**
	 * @return the tipoCambioMedioPago
	 */
	public BigDecimal getTipoCambioMedioPago() {
		return tipoCambioMedioPago;
	}

	/**
	 * @param tipoCambioMedioPago the tipoCambioMedioPago to set
	 */
	public void setTipoCambioMedioPago(BigDecimal tipoCambioMedioPago) {
		this.tipoCambioMedioPago = tipoCambioMedioPago;
	}

	/**
	 * @return the importeMedioPagoMonedaOrigen
	 */
	public BigDecimal getImporteMedioPagoMonedaOrigen() {
		return importeMedioPagoMonedaOrigen;
	}

	/**
	 * @param importeMedioPagoMonedaOrigen the importeMedioPagoMonedaOrigen to set
	 */
	public void setImporteMedioPagoMonedaOrigen(
			BigDecimal importeMedioPagoMonedaOrigen) {
		this.importeMedioPagoMonedaOrigen = importeMedioPagoMonedaOrigen;
	}
	/**
	 * @return the monedaOperacion
	 */
	public MonedaEnum getMonedaOperacion() {
		return monedaOperacion;
	}
	/**
	 * @param monedaOperacion the monedaOperacion to set
	 */
	public void setMonedaOperacion(MonedaEnum monedaOperacion) {
		this.monedaOperacion = monedaOperacion;
	}
	/**
	 * @return the numeroTransaccion
	 */
	public Integer getNumeroTransaccion() {
		return numeroTransaccion;
	}
	/**
	 * @param numeroTransaccion the numeroTransaccion to set
	 */
	public void setNumeroTransaccion(Integer numeroTransaccion) {
		this.numeroTransaccion = numeroTransaccion;
	}
	
	
}
