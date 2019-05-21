package ar.com.telecom.shiva.negocio.batch.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import ar.com.telecom.shiva.base.dto.Batch;
import ar.com.telecom.shiva.base.utils.Validaciones;


public class SubdiarioBatch extends Batch {

	private static final long serialVersionUID = 1L;
	
	 private Date fechaProcesamiento;
     private String idTransaccion;
     private String numeroTransaccionFicticio;
     private String numeroRecibo;
     private String tipoOperacion;
     private String tipoComprobante;
     private String documentoLegal;
     private BigDecimal clienteLegado;
     private String razonSocial;
     private String moneda;
     private BigDecimal importe;
     private BigDecimal importeAplicadoEnPesos;
     private BigDecimal importeMonedaOrigen;
     private Date fechaValor;
     private String idCaja;
     private int idFactura;
     
     public SubdiarioBatch(Map<String, Object> map){
    	 this.fechaProcesamiento = (Date) map.get("FECHAPROCESAMIENTO");
    	 this.idTransaccion = (String) map.get("IDTRANSACCION");
    	 this.numeroTransaccionFicticio = (String) map.get("NUMERO_TRANSACCION_FICTICIO");
    	 this.numeroRecibo = (String) map.get("NUMERORECIBO");
    	 this.tipoOperacion = (String) map.get("TIPOOPERACION");
    	 this.tipoComprobante = (String) map.get("TIPOCOMPROBANTE");
    	 this.documentoLegal = (String) map.get("DOCUMENTOLEGAL");
    	 this.clienteLegado = (BigDecimal) map.get("CLIENTELEGADO");
    	 this.razonSocial = (String) map.get("RAZONSOCIAL");
    	 this.moneda = (String) map.get("MONEDA");
    	 this.importe = (BigDecimal) map.get("IMPORTE");
    	 this.importeAplicadoEnPesos = (BigDecimal) map.get("IMPORTE_APLICADO_EN_PESOS");
    	 this.importeMonedaOrigen = (BigDecimal) map.get("IMPORTE_MONEDA_ORIGEN");
    	 this.fechaValor = (Date) map.get("FECHAVALOR");
    	 this.idCaja = (String) map.get("IDCAJA");
    	 
    	 if (!Validaciones.isObjectNull(map.get("ID_FACTURA"))){
    		 this.idFactura = Integer.parseInt(map.get("ID_FACTURA").toString());
    	 }
    	 
     }
     
/*********************************************************************************
*                         Setters & Getters                                      *
*********************************************************************************/
	public Date getFechaProcesamiento() {
		return fechaProcesamiento;
	}
	public void setFechaProcesamiento(Date fechaProcesamiento) {
		this.fechaProcesamiento = fechaProcesamiento;
	}
	public String getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	public String getNumeroRecibo() {
		return numeroRecibo;
	}
	public void setNumeroRecibo(String numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public String getTipoComprobante() {
		return tipoComprobante;
	}
	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}
	public String getDocumentoLegal() {
		return documentoLegal;
	}
	public void setDocumentoLegal(String numComprobante) {
		this.documentoLegal = numComprobante;
	}
	public BigDecimal getClienteLegado() {
		return clienteLegado;
	}
	public void setClienteLegado(BigDecimal clienteLegado) {
		this.clienteLegado = clienteLegado;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public Date getFechaValor() {
		return fechaValor;
	}
	public void setFechaValor(Date fechaValor) {
		this.fechaValor = fechaValor;
	}

	public String getIdCaja() {
		return idCaja;
	}

	public void setIdCaja(String idCaja) {
		this.idCaja = idCaja;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public BigDecimal getImporteAplicadoEnPesos() {
		return importeAplicadoEnPesos;
	}

	public void setImporteAplicadoEnPesos(BigDecimal importeAplicadoEnPesos) {
		this.importeAplicadoEnPesos = importeAplicadoEnPesos;
	}

	public BigDecimal getImporteMonedaOrigen() {
		return importeMonedaOrigen;
	}

	public void setImporteMonedaOrigen(BigDecimal importeMonedaOrigen) {
		this.importeMonedaOrigen = importeMonedaOrigen;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	/**
	 * @return the numeroTransaccionFicticio
	 */
	public String getNumeroTransaccionFicticio() {
		return numeroTransaccionFicticio;
	}

	/**
	 * @param numeroTransaccionFicticio the numeroTransaccionFicticio to set
	 */
	public void setNumeroTransaccionFicticio(String numeroTransaccionFicticio) {
		this.numeroTransaccionFicticio = numeroTransaccionFicticio;
	}
	
}
