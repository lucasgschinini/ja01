package ar.com.telecom.shiva.batch.springbatch.model;

/**
 * 
 * @author u564030 Pablo M. Ibarrola
 *
 */
public class GenericDataItem {
	
	// Datos propios del archivo a leer
	private String fechaValor;
	private String fechaIngreso;
	private String concepto;
	private String codigoOperacion;
	private String comprobante;
	private String deposito;
	private String sucursal;
	private String importe;
	private String descripcion;
	private String codigoOpBanco;
	private String pcc;
	private String columnaSiempreVacia;

	// Datos para uso interno
	private String codigoOrganismo;
	private Long codigoInterdeposito;
	private String referencia;
	private String codigo;
	private String cuit;

	private Long idAcuerdo;
	private String idAcuerdoStr;
	private Long idTipoValor;
	private String codigoCliente;
	private String observacion;
	private String observaciones;
	private String razonSocialClientePerfil;
	private Long idClientePerfil;

	/**
	 * @return the fechaValor
	 */
	public String getFechaValor() {
		return fechaValor;
	}
	/**
	 * @param fechaValor the fechaValor to set
	 */
	public void setFechaValor(String fechaValor) {
		this.fechaValor = fechaValor;
	}
	/**
	 * @return the fechaIngreso
	 */
	public String getFechaIngreso() {
		return fechaIngreso;
	}
	/**
	 * @param fechaIngreso the fechaIngreso to set
	 */
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	/**
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}
	/**
	 * @param concepto the concepto to set
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	/**
	 * @return the codigoOperacion
	 */
	public String getCodigoOperacion() {
		return codigoOperacion;
	}
	/**
	 * @param codigoOperacion the codigoOperacion to set
	 */
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}
	/**
	 * @return the comprobante
	 */
	public String getComprobante() {
		return comprobante;
	}
	/**
	 * @param comprobante the comprobante to set
	 */
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}
	/**
	 * @return the deposito
	 */
	public String getDeposito() {
		return deposito;
	}
	/**
	 * @param deposito the deposito to set
	 */
	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}
	/**
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}
	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	/**
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}
	/**
	 * @param importe the importe to set
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the codigoOpBanco
	 */
	public String getCodigoOpBanco() {
		return codigoOpBanco;
	}
	/**
	 * @param codigoOpBanco the codigoOpBanco to set
	 */
	public void setCodigoOpBanco(String codigoOpBanco) {
		this.codigoOpBanco = codigoOpBanco;
	}
	/**
	 * @return the pcc
	 */
	public String getPcc() {
		return pcc;
	}
	/**
	 * @param pcc the pcc to set
	 */
	public void setPcc(String pcc) {
		this.pcc = pcc;
	}
	/**
	 * @return the columnaSiempreVacia
	 */
	public String getColumnaSiempreVacia() {
		return columnaSiempreVacia;
	}
	/**
	 * @param columnaSiempreVacia the columnaSiempreVacia to set
	 */
	public void setColumnaSiempreVacia(String columnaSiempreVacia) {
		this.columnaSiempreVacia = columnaSiempreVacia;
	}
	/**
	 * @return the codigoOrganismo
	 */
	public String getCodigoOrganismo() {
		return codigoOrganismo;
	}
	/**
	 * @param codigoOrganismo the codigoOrganismo to set
	 */
	public void setCodigoOrganismo(String codigoOrganismo) {
		this.codigoOrganismo = codigoOrganismo;
	}
	/**
	 * @return the codigoInterdeposito
	 */
	public Long getCodigoInterdeposito() {
		return codigoInterdeposito;
	}
	/**
	 * @param codigoInterdeposito the codigoInterdeposito to set
	 */
	public void setCodigoInterdeposito(Long codigoInterdeposito) {
		this.codigoInterdeposito = codigoInterdeposito;
	}
	/**
	 * @return the referencia
	 */
	public String getReferencia() {
		return referencia;
	}
	/**
	 * @param referencia the referencia to set
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}
	/**
	 * @param cuit the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	/**
	 * @return the idAcuerdo
	 */
	public Long getIdAcuerdo() {
		return idAcuerdo;
	}
	/**
	 * @param idAcuerdo the idAcuerdo to set
	 */
	public void setIdAcuerdo(Long idAcuerdo) {
		this.idAcuerdo = idAcuerdo;
	}
	/**
	 * @return the idAcuerdoStr
	 */
	public String getIdAcuerdoStr() {
		return idAcuerdoStr;
	}
	/**
	 * @param idAcuerdoStr the idAcuerdoStr to set
	 */
	public void setIdAcuerdoStr(String idAcuerdoStr) {
		this.idAcuerdoStr = idAcuerdoStr;
	}
	/**
	 * @return the idTipoValor
	 */
	public Long getIdTipoValor() {
		return idTipoValor;
	}
	/**
	 * @param idTipoValor the idTipoValor to set
	 */
	public void setIdTipoValor(Long idTipoValor) {
		this.idTipoValor = idTipoValor;
	}
	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}
	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}
	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	/**
	 * @return the razonSocialClientePerfil
	 */
	public String getRazonSocialClientePerfil() {
		return razonSocialClientePerfil;
	}
	/**
	 * @param razonSocialClientePerfil the razonSocialClientePerfil to set
	 */
	public void setRazonSocialClientePerfil(String razonSocialClientePerfil) {
		this.razonSocialClientePerfil = razonSocialClientePerfil;
	}
	/**
	 * @return the idClientePerfil
	 */
	public Long getIdClientePerfil() {
		return idClientePerfil;
	}
	/**
	 * @param idClientePerfil the idClientePerfil to set
	 */
	public void setIdClientePerfil(Long idClientePerfil) {
		this.idClientePerfil = idClientePerfil;
	}
}
