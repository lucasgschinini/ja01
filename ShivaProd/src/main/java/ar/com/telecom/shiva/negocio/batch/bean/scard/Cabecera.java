package ar.com.telecom.shiva.negocio.batch.bean.scard;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"tipo",
					  "idOperacion",
					  "idOperacionMasiva",
					  "comprobante",
					  "fecha",
					  "hora",
					  "cliente",
					  "razonSocial",
					  "domicilio",
					  "responsableIVA",
					  "cuit",
					  "total",
					  "monedaOperacion",
					  "descripcion",
					  "concepto",
					  "analista"})

public class Cabecera {

	private String tipo;
	private String idOperacion;
	private String idOperacionMasiva;
	private String comprobante;
	private String fecha;
	private String hora;
	private String cliente;
	private String razonSocial;
	private String domicilio;
	private String responsableIVA;
	private String cuit;
	private String total;
	private String monedaOperacion;
	private String descripcion;
	private String concepto;
	private String analista;
	
	@XmlElement
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@XmlElement(name="id_operacion", nillable=true)
	public String getIdOperacion() {
		return idOperacion;
	}
	public void setIdOperacion(String idOperacion) {
		this.idOperacion = idOperacion;
	}
	@XmlElement(name="id_operacion_masiva", nillable=true)
	public String getIdOperacionMasiva() {
		return idOperacionMasiva;
	}
	public void setIdOperacionMasiva(String idOperacionMasiva) {
		this.idOperacionMasiva = idOperacionMasiva;
	}
	@XmlElement
	public String getComprobante() {
		return comprobante;
	}
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}
	@XmlElement
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	@XmlElement
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	@XmlElement
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	@XmlElement(name="razon_social", nillable=true)
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	@XmlElement
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	@XmlElement(name="responsable_iva",nillable=true)
	public String getResponsableIVA() {
		return responsableIVA;
	}
	public void setResponsableIVA(String responsableIVA) {
		this.responsableIVA = responsableIVA;
	}
	@XmlElement
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	@XmlElement
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	@XmlElement(name="desc_total",nillable=true)
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@XmlElement
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	@XmlElement
	public String getAnalista() {
		return analista;
	}
	public void setAnalista(String analista) {
		this.analista = analista;
	}
	/**
	 * @return the monedaOperacion
	 */
	@XmlElement(name="moneda_operacion")
	public String getMonedaOperacion() {
		return monedaOperacion;
	}
	/**
	 * @param monedaOperacion the monedaOperacion to set
	 */
	public void setMonedaOperacion(String monedaOperacion) {
		this.monedaOperacion = monedaOperacion;
	}
}
