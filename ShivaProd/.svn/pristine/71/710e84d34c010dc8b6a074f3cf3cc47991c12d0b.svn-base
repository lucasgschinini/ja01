package ar.com.telecom.shiva.negocio.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;

/**
 * @author u529234
 *
 */
public class TransferenciaDto extends RegistroAVCDto {

	private static final long serialVersionUID = 1L;

	private Date fechaIngreso;
	private Long sucursal;
	private Long referencia;
	private Long codigo;
	private String observacion;
	private String observacionEditarCuit;
	private String nuevaObservacion;
	private String cuit;
	private Long subtotal;
	// Manejo Comprobantes
	private List<ComprobanteDto> listaComprobantes;
	private boolean errorArchivoVacio;
	
	private MultipartFile fileComprobanteModificacion;
	private boolean errorComprobanteVacioModif;
	private boolean comprobanteDescripcionVacioModif;	
	private boolean comprobantePathVacioModif;	
	private String  comprobanteError;
	private String descripcionComprobante;
	
	//Elijo un comprobante para borrar o mostrar un comprobante
	private String idComprobanteSelected;
	
	private String errorAltaInterdeposito;
	private String fechaErrorFormateado;
		
	public TransferenciaDto() {
		this.listaComprobantes = new ArrayList<ComprobanteDto>();
	}
	
	public TransferenciaDto(String acuerdo) {
		setAcuerdo(acuerdo);
		this.listaComprobantes = new ArrayList<ComprobanteDto>();
	}
	
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public Long getSucursal() {
		return sucursal;
	}
	public void setSucursal(Long sucursal) {
		this.sucursal = sucursal;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getObservacionEditarCuit() {
		return observacionEditarCuit;
	}

	public void setObservacionEditarCuit(String observacionEditarCuit) {
		this.observacionEditarCuit = observacionEditarCuit;
	}

	public Long getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Long subtotal) {
		this.subtotal = subtotal;
	}

	public void setReferencia(Long referencia) {
		this.referencia = referencia;		
	}

	public Long getReferencia() {
		return referencia;
	}

	@Override
	public String getCuitFormateado(){
		return this.cuit;
	}
	
	
	/**
	 * Se utiliza en la pantalla conciliacion-consultar-registros-avc-sin-conciliar.
	 * @return
	 */
	public String getObservacionesFormateado(){
		return observacion;
		
	}

	public List<ComprobanteDto> getListaComprobantes() {
		return listaComprobantes;
	}

	public void setListaComprobantes(List<ComprobanteDto> listaComprobantes) {
		this.listaComprobantes = listaComprobantes;
	}

	public boolean isErrorArchivoVacio() {
		return errorArchivoVacio;
	}

	public void setErrorArchivoVacio(boolean errorArchivoVacio) {
		this.errorArchivoVacio = errorArchivoVacio;
	}

	public MultipartFile getFileComprobanteModificacion() {
		return fileComprobanteModificacion;
	}

	public void setFileComprobanteModificacion(
			MultipartFile fileComprobanteModificacion) {
		this.fileComprobanteModificacion = fileComprobanteModificacion;
	}

	public boolean isErrorComprobanteVacioModif() {
		return errorComprobanteVacioModif;
	}

	public void setErrorComprobanteVacioModif(boolean errorComprobanteVacioModif) {
		this.errorComprobanteVacioModif = errorComprobanteVacioModif;
	}

	public boolean isComprobanteDescripcionVacioModif() {
		return comprobanteDescripcionVacioModif;
	}

	public void setComprobanteDescripcionVacioModif(
			boolean comprobanteDescripcionVacioModif) {
		this.comprobanteDescripcionVacioModif = comprobanteDescripcionVacioModif;
	}

	public boolean isComprobantePathVacioModif() {
		return comprobantePathVacioModif;
	}

	public void setComprobantePathVacioModif(boolean comprobantePathVacioModif) {
		this.comprobantePathVacioModif = comprobantePathVacioModif;
	}
	
	@Override
	public String getFechaIngresoFormateada(){
		return Utilidad.formatDate(this.fechaIngreso);
	}

	public String getComprobanteError() {
		return comprobanteError;
	}

	public void setComprobanteError(String comprobanteError) {
		this.comprobanteError = comprobanteError;
	}

	public String getDescripcionComprobante() {
		return descripcionComprobante;
	}

	public void setDescripcionComprobante(String descripcionComprobante) {
		this.descripcionComprobante = descripcionComprobante;
	}

	public String getIdComprobanteSelected() {
		return idComprobanteSelected;
	}

	public void setIdComprobanteSelected(String idComprobanteSelected) {
		this.idComprobanteSelected = idComprobanteSelected;
	}

	public String getNuevaObservacion() {
		return nuevaObservacion;
	}

	public void setNuevaObservacion(String nuevaObservacion) {
		this.nuevaObservacion = nuevaObservacion;
	}

	public String getFechaErrorFormateado() {
		return fechaErrorFormateado;
	}

	public void setFechaErrorFormateado(String fechaErrorFormateado) {
		this.fechaErrorFormateado = fechaErrorFormateado;
	}

	public String getErrorAltaInterdeposito() {
		return errorAltaInterdeposito;
	}

	public void setErrorAltaInterdeposito(String errorAltaInterdeposito) {
		this.errorAltaInterdeposito = errorAltaInterdeposito;
	}

}
