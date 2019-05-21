package ar.com.telecom.shiva.presentacion.bean.dto;

import java.util.Set;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.modelo.ShvTalCompensacion;

public class ReciboPreImpresoDto extends DTO {

	private static final long serialVersionUID = 1L;
	
	private String idTalonarioSelected;
	private String recibosAAnularSelected;
	
	private String nroRecibo;
	private String importe;
	private String estado;
	private String usuarioGestor;
	private String idUsuarioGestor;

	private String fechaAnulacion;
	private String usuarioAnulacion;
	private String nombreUsuarioAnulacion;
	

	private String mailUsuarioAnulacion;
	
	private boolean editable;
	
	//VALORES
	private String listaValores; //concatenacion de Valores
	private Set<ValorDto> valores; 
	private String tipoValor;
	private String codClienteValores;
	private String descClienteValores;
	private String fechaDepositoValores;
	private String importeValores;
 
	//COMPENSACIONES
	private String listaCompensaciones; //concatenacion de Compensaciones
	private Set<ShvTalCompensacion> compensaciones;
	private String referenciaCompensacion;
	private String importeCompensacion;
	
	
	private String fechaIngreso;
	private String segmento;
	private String empresa;
	private String nroTalonario;
	
	private boolean volverPantallaRendicion;

	
	public boolean getReciboConCompensacion() {
		return (Estado.REC_COMPENSACION.descripcion().equalsIgnoreCase(estado));
	}
	
	public String getListaValores() {
		return listaValores;
	}

	public void setListaValores(String valor) {
		this.listaValores = valor;
	}

	
	public String getListaCompensaciones() {
		return listaCompensaciones;
	}

	public void setListaCompensaciones(String compensacion) {
		this.listaCompensaciones = compensacion;
	}

	
	public ReciboPreImpresoDto(){
		
	}
	
	public ReciboPreImpresoDto(Integer id){
		super.setId(id);
	}
	

	public String getNroRecibo() {
		return nroRecibo;
	}

	public void setNroRecibo(String nroRecibo) {
		this.nroRecibo = nroRecibo;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getUsuarioAnulacion() {
		return usuarioAnulacion;
	}

	public void setUsuarioAnulacion(String usuarioAnulacion) {
		this.usuarioAnulacion = usuarioAnulacion;
	}

	public String getFechaAnulacion() {
		return fechaAnulacion;
	}

	public void setFechaAnulacion(String fechaAnulacion) {
		this.fechaAnulacion = fechaAnulacion;
	}

	public String getUsuarioGestor() {
		return usuarioGestor;
	}

	public void setUsuarioGestor(String usuarioGestor) {
		this.usuarioGestor = usuarioGestor;
	}
	

	public String getIdUsuarioGestor() {
		return idUsuarioGestor;
	}

	public void setIdUsuarioGestor(String idUsuarioGestor) {
		this.idUsuarioGestor = idUsuarioGestor;
	}
	
	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public String getTipoValor() {
		return tipoValor;
	}

	public void setTipoValor(String tipoValor) {
		this.tipoValor = tipoValor;
	}

	public String getCodClienteValores() {
		return codClienteValores;
	}

	public void setCodClienteValores(String codClienteValores) {
		this.codClienteValores = codClienteValores;
	}

	public String getDescClienteValores() {
		return descClienteValores;
	}

	public void setDescClienteValores(String descClienteValores) {
		this.descClienteValores = descClienteValores;
	}

	public String getFechaDepositoValores() {
		return fechaDepositoValores;
	}

	public void setFechaDepositoValores(String fechaDepositoValores) {
		this.fechaDepositoValores = fechaDepositoValores;
	}

	public String getImporteValores() {
		return importeValores;
	}

	public void setImporteValores(String importeValores) {
		this.importeValores = importeValores;
	}

	public String getReferenciaCompensacion() {
		return referenciaCompensacion;
	}

	public void setReferenciaCompensacion(String referenciaCompensacion) {
		this.referenciaCompensacion = referenciaCompensacion;
	}

	public String getImporteCompensacion() {
		return importeCompensacion;
	}

	public void setImporteCompensacion(String importeCompensacion) {
		this.importeCompensacion = importeCompensacion;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getNroTalonario() {
		return nroTalonario;
	}

	public void setNroTalonario(String nroTalonario) {
		this.nroTalonario = nroTalonario;
	}

	public Set<ValorDto> getValores() {
		return valores;
	}

	public void setValores(Set<ValorDto> valores) {
		this.valores = valores;
	}

	public Set<ShvTalCompensacion> getCompensaciones() {
		return compensaciones;
	}

	public void setCompensaciones(Set<ShvTalCompensacion> compensaciones) {
		this.compensaciones = compensaciones;
	}

	public String getNombreUsuarioAnulacion() {
		return nombreUsuarioAnulacion;
	}

	public void setNombreUsuarioAnulacion(String nombreUsuarioAnulacion) {
		this.nombreUsuarioAnulacion = nombreUsuarioAnulacion;
	}

	public String getMailUsuarioAnulacion() {
		return mailUsuarioAnulacion;
	}

	public void setMailUsuarioAnulacion(String mailUsuarioAnulacion) {
		this.mailUsuarioAnulacion = mailUsuarioAnulacion;
	}

	public String getRecibosAAnularSelected() {
		return recibosAAnularSelected;
	}

	public void setRecibosAAnularSelected(String recibosAAnularSelected) {
		this.recibosAAnularSelected = recibosAAnularSelected;
	}

	public String getIdTalonarioSelected() {
		return idTalonarioSelected;
	}

	public void setIdTalonarioSelected(String idTalonarioSelected) {
		this.idTalonarioSelected = idTalonarioSelected;
	}

	public boolean isVolverPantallaRendicion() {
		return volverPantallaRendicion;
	}

	public void setVolverPantallaRendicion(boolean volverPantallaRendicion) {
		this.volverPantallaRendicion = volverPantallaRendicion;
	}

}