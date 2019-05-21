package ar.com.telecom.shiva.presentacion.bean.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ar.com.telecom.shiva.base.enumeradores.TipoArchivoOperacionMasivaEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjunto;

public class OperacionMasivaDto extends GestionDto {

	private static final long serialVersionUID = 1L;
	
	private String empresa;
	private String segmento;
	
	private String idAnalista;
	private String urlFotoAnalista;
	private String nombreAnalista;
	private String mailAnalista;
	
	private String idCopropietario;
	private String nombreCopropietario;
	private String mailCopropietario;
	private String urlFotoCopropietario;
	
	private String idMotivo;
	private String motivo;
	private Long idOperacionMasiva;
	private Long IdTarea;
	private String estado;
	private String errorMensajeDuplicado;
	private boolean comboCopropietario = true;
	private String resultadoValidaciones;
	private boolean moverSeccionComprobante = false;
	private boolean mantenerComprobantesAdjuntos = false;
	
	private TipoArchivoOperacionMasivaEnum tipoOperacionMasiva;
	
	//Duplicidad
	private ShvDocDocumentoAdjunto archivoDuplicado;
	private boolean errorValorDuplicado = false;
	private boolean duplicado =false;
	
	//Atributos del archivo de operación masiva. 
	
	private MultipartFile fileOperacionMasiva;
	//private MultipartFile lastFileOperacionMasiva;
	private String fileNameOperacionMasiva = "";
	
	// se usa para mostrar errores en el jsp
	private String pathArchivo;
	List<ArchivoOperacionMasivaProcesadoDto> archivosPendientes = new ArrayList<ArchivoOperacionMasivaProcesadoDto>();
	
	
	//Comprobantes
	//Elijo un comprobante para borrar o mostrar un comprobante.
	private String ordenComprobanteSelected;
	private ComprobanteDto comprobanteDto;
	private List<ComprobanteDto> listaComprobantes = new ArrayList<ComprobanteDto>();
	private boolean errorArchivoVacio;
	
	//Se utiliza para traer el comprobante del jsp y se setea en el ComprobanteDto.
	private MultipartFile fileComprobanteModificacion;
	
	private boolean errorComprobanteVacioModif;
	private boolean comprobanteDescripcionVacioModif;
	private boolean comprobantePathVacioModif;
	private String  fechaIngresoFormateada;
	private String  comprobanteError;
	private String descripcionComprobante;
	
	private String observacion;
	private String observacionAnterior;
	
	//Busqueda Operaciones Masivas DataTable
	private String nombreArchivo;
	private Estado estadoOperacionMasiva;
	private String tipoDeOperacionMasiva;
	private String fechaUltimaModificacionFormatoJson;
	private Long motivoOM;
	private String motivoOMFormateado;
	//Analista ya esta creado mas arriba
	private String fechaAltaFormatoJson;
	private Date fechaDerivacion;
	private String fechaDerivacionFormatoJson;
	private Date fechaAutorizacion;
	private String fechaAutorizacionFormatoJson;
	private Date fechaProcesamiento;
	private String fechaProcesamientoFormatoJson;
	private String registrosIngresados;
	private String registrosProcesadosCorrectamente;
	private String registrosProcesadosConError;
	private String descripcionEstado;
	private String descripcionMotivo;
	private boolean mostrarBotonModificar;
	private boolean mostrarBotonAnular;
	private boolean mostrarBotonVerCobro;
	private boolean esEditable = false;
	private boolean archivoValidado = false;
	
	private byte fileBytes[] = null;
	
	
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
	public String getFechaIngresoFormateada() {
		return fechaIngresoFormateada;
	}
	public void setFechaIngresoFormateada(String fechaIngresoFormateada) {
		this.fechaIngresoFormateada = fechaIngresoFormateada;
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

	/**
	 * @return the idAnalista
	 */
	public String getIdAnalista() {
		return idAnalista;
	}
	/**
	 * @param idAnalista the idAnalista to set
	 */
	public void setIdAnalista(String idAnalista) {
		this.idAnalista = idAnalista;
	}
	/**
	 * @return the nombreAnalista
	 */
	public String getNombreAnalista() {
		return nombreAnalista;
	}
	/**
	 * @param nombreAnalista the nombreAnalista to set
	 */
	public void setNombreAnalista(String nombreAnalista) {
		this.nombreAnalista = nombreAnalista;
	}
	/**
	 * @return the idCopropietario
	 */
	public String getIdCopropietario() {
		return idCopropietario;
	}
	/**
	 * @param idCopropietario the idCopropietario to set
	 */
	public void setIdCopropietario(String idCopropietario) {
		this.idCopropietario = idCopropietario;
	}
	/**
	 * @return the comprobanteDto
	 */
	public ComprobanteDto getComprobanteDto() {
		return comprobanteDto;
	}
	/**
	 * @param comprobanteDto the comprobanteDto to set
	 */
	public void setComprobanteDto(ComprobanteDto comprobanteDto) {
		this.comprobanteDto = comprobanteDto;
	}
	/**
	 * @return the comboCopropietario
	 */
	public boolean isComboCopropietario() {
		return comboCopropietario;
	}
	/**
	 * @param comboCopropietario the comboCopropietario to set
	 */
	public void setComboCopropietario(boolean comboCopropietario) {
		this.comboCopropietario = comboCopropietario;
	}
	public String getIdMotivo() {
		return idMotivo;
	}
	public void setIdMotivo(String idMotivo) {
		this.idMotivo = idMotivo;
	}
	public String getResultadoValidaciones() {
		return resultadoValidaciones;
	}
	public void setResultadoValidaciones(String resultadoValidaciones) {
		this.resultadoValidaciones = resultadoValidaciones;
	}
	/**
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}
	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	/**
	 * @return the segmento
	 */
	public String getSegmento() {
		return segmento;
	}
	/**
	 * @param segmento the segmento to set
	 */
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}
	
	public List<ArchivoOperacionMasivaProcesadoDto> getArchivosPendientes() {
		return archivosPendientes;
	}
	public void setArchivosPendientes(
			List<ArchivoOperacionMasivaProcesadoDto> archivosPendientes) {
		this.archivosPendientes = archivosPendientes;
	}
	public String getPathArchivo() {
		return pathArchivo;
	}
	public void setPathArchivo(String pathArchivo) {
		this.pathArchivo = pathArchivo;
	}
	public String getOrdenComprobanteSelected() {
		return ordenComprobanteSelected;
	}
	public void setOrdenComprobanteSelected(String ordenComprobanteSelected) {
		this.ordenComprobanteSelected = ordenComprobanteSelected;
	}
	public MultipartFile getFileOperacionMasiva() {
		return fileOperacionMasiva;
	}
	public void setFileOperacionMasiva(MultipartFile fileOperacionMasiva) {
		this.fileOperacionMasiva = fileOperacionMasiva;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public boolean getMoverSeccionComprobante() {
		return moverSeccionComprobante;
	}
	public void setMoverSeccionComprobante(boolean moverSeccionComprobante) {
		this.moverSeccionComprobante = moverSeccionComprobante;
	}
	public Long getIdOperacionMasiva() {
		return idOperacionMasiva;
	}
	public void setIdOperacionMasiva(Long idOperacionMasiva) {
		this.idOperacionMasiva = idOperacionMasiva;
	}
	public Long getIdTarea() {
		return IdTarea;
	}
	public void setIdTarea(Long idTarea) {
		IdTarea = idTarea;
	}
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getErrorMensajeDuplicado() {
		return errorMensajeDuplicado;
	}
	public void setErrorMensajeDuplicado(String errorMensajeDuplicado) {
		this.errorMensajeDuplicado = errorMensajeDuplicado;
	}
	public boolean isErrorValorDuplicado() {
		return errorValorDuplicado;
	}
	public void setErrorValorDuplicado(boolean errorValorDuplicado) {
		this.errorValorDuplicado = errorValorDuplicado;
	}
	public boolean isDuplicado() {
		return duplicado;
	}
	public void setDuplicado(boolean duplicado) {
		this.duplicado = duplicado;
	}
	public ShvDocDocumentoAdjunto getArchivoDuplicado() {
		return archivoDuplicado;
	}
	public void setArchivoDuplicado(ShvDocDocumentoAdjunto archivoDuplicado) {
		this.archivoDuplicado = archivoDuplicado;
	}
//	public MultipartFile getLastFileOperacionMasiva() {
//		return lastFileOperacionMasiva;
//	}
//	public void setLastFileOperacionMasiva(MultipartFile lastFileOperacionMasiva) {
//		this.lastFileOperacionMasiva = lastFileOperacionMasiva;
//	}
	public String getFileNameOperacionMasiva() {
		return fileNameOperacionMasiva;
	}
	public void setFileNameOperacionMasiva(String fileNameOperacionMasiva) {
		this.fileNameOperacionMasiva = fileNameOperacionMasiva;
	}
	public boolean isMantenerComprobantesAdjuntos() {
		return mantenerComprobantesAdjuntos;
	}
	public void setMantenerComprobantesAdjuntos(boolean mantenerComprobantesAdjuntos) {
		this.mantenerComprobantesAdjuntos = mantenerComprobantesAdjuntos;
	}
	public TipoArchivoOperacionMasivaEnum getTipoOperacionMasiva() {
		return tipoOperacionMasiva;
	}
	public void setTipoOperacionMasiva(
			TipoArchivoOperacionMasivaEnum tipoOperacionMasiva) {
		this.tipoOperacionMasiva = tipoOperacionMasiva;
	}
	public String getTipoDeOperacionMasiva() {
		return tipoDeOperacionMasiva;
	}
	public void setTipoDeOperacionMasiva(String tipoDeOperacionMasiva) {
		this.tipoDeOperacionMasiva = tipoDeOperacionMasiva;
	}
	
	public String getFechaUltimaModificacionFormatoJson() {
		if (Validaciones.isNullEmptyOrDash(fechaUltimaModificacionFormatoJson) && !Validaciones.isObjectNull(this.getFechaUltimaModificacion())){
			return Utilidad.formatDateAndTimeFullFromDataBase(this.getFechaUltimaModificacion().toString());
		}
		return fechaUltimaModificacionFormatoJson;
	}
	public void setFechaUltimaModificacionFormatoJson(
			String fechaUltimaModificacionFormatoJson) {
		this.fechaUltimaModificacionFormatoJson = fechaUltimaModificacionFormatoJson;
	}
	public Long getMotivoOM() {
		return motivoOM;
	}
	public void setMotivoOM(Long motivoOM) {
		this.motivoOM = motivoOM;
	}
	public String getMotivoOMFormateado(String id) {
		String motivo;
		
		switch(id) {
		case "1":
			motivo = "Financiación de deuda";
			break;
		case "2":
			motivo = "Reintegro";
			break;
		case "3":
			motivo = "Depuración de crédito";
			break;
			default:
			motivo = "";
			break;
				
		}
		return motivo;
	}
	public void setMotivoOMFormateado(String motivoOMFormateado) {
		this.motivoOMFormateado = motivoOMFormateado;
	}
	public String getFechaAltaFormatoJson() {
		if (Validaciones.isNullEmptyOrDash(fechaAltaFormatoJson) && !Validaciones.isObjectNull(this.getFechaAlta())){
			return Utilidad.formatDateAndTimeFullFromDataBase(this.getFechaAlta().toString());
	}
	return fechaAltaFormatoJson;
	}
	public void setFechaAltaFormatoJson(String fechaAltaFormatoJson) {
		this.fechaAltaFormatoJson = fechaAltaFormatoJson;
	}
	public Date getFechaDerivacion() {
		return fechaDerivacion;
	}
	public void setFechaDerivacion(Date fechaDerivacion) {
		this.fechaDerivacion = fechaDerivacion;
	}
	
	public String getFechaDerivacionFormatoJson() {
		if (Validaciones.isNullEmptyOrDash(fechaDerivacionFormatoJson) && !Validaciones.isObjectNull(this.getFechaDerivacion())){
			return Utilidad.formatDateAndTimeFullFromDataBase(this.getFechaDerivacion().toString());
	}
		return fechaDerivacionFormatoJson;
	}
	
	public void setFechaDerivacionFormatoJson(String fechaDerivacionFormatoJson) {
		this.fechaDerivacionFormatoJson = fechaDerivacionFormatoJson;
	}
	
	public Date getFechaAutorizacion() {
		return fechaAutorizacion;
	}
	public void setFechaAutorizacion(Date fechaAutorizacion) {
		this.fechaAutorizacion = fechaAutorizacion;
	}
	
	public String getFechaAutorizacionFormatoJson() {
		if (Validaciones.isNullEmptyOrDash(fechaAutorizacionFormatoJson) && !Validaciones.isObjectNull(this.getFechaAutorizacion())){
			return Utilidad.formatDateAndTimeFullFromDataBase(this.getFechaAutorizacion().toString());
	}
		return fechaAutorizacionFormatoJson;
	}
	
	public void setFechaAutorizacionFormatoJson(String fechaAutorizacionFormatoJson) {
		this.fechaAutorizacionFormatoJson = fechaAutorizacionFormatoJson;
	}
	
	public Date getFechaProcesamiento() {
		return fechaProcesamiento;
	}
	public void setFechaProcesamiento(Date fechaProcesamiento) {
		this.fechaProcesamiento = fechaProcesamiento;
	}
	
	public String getFechaProcesamientoFormatoJson() {
		if (Validaciones.isNullEmptyOrDash(fechaProcesamientoFormatoJson) && !Validaciones.isObjectNull(this.getFechaProcesamiento())){
			return Utilidad.formatDateAndTimeFullFromDataBase(this.getFechaProcesamiento().toString());
	}
		return fechaProcesamientoFormatoJson;
	}
	
	public void setFechaProcesamientoFormatoJson(String fechaProcesamientoFormatoJson) {
		this.fechaProcesamientoFormatoJson = fechaProcesamientoFormatoJson;
	}
	public String getRegistrosIngresados() {
		return registrosIngresados;
	}
	public void setRegistrosIngresados(String registrosIngresados) {
		this.registrosIngresados = registrosIngresados;
	}
	public String getRegistrosProcesadosCorrectamente() {
		return registrosProcesadosCorrectamente;
	}
	public void setRegistrosProcesadosCorrectamente(
			String registrosProcesadosCorrectamente) {
		this.registrosProcesadosCorrectamente = registrosProcesadosCorrectamente;
	}
	public String getRegistrosProcesadosConError() {
		return registrosProcesadosConError;
	}
	public void setRegistrosProcesadosConError(String registrosProcesadosConError) {
		this.registrosProcesadosConError = registrosProcesadosConError;
	}
	public Estado getEstadoOperacionMasiva() {
		return estadoOperacionMasiva;
	}
	public void setEstadoOperacionMasiva(Estado estadoOperacionMasiva) {
		this.estadoOperacionMasiva = estadoOperacionMasiva;
	}
	
		
	public String getDescripcionEstado() {
		return descripcionEstado;
	}
	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}
	public String getDescripcionMotivo() {
		return descripcionMotivo;
	}
	public void setDescripcionMotivo(String descripcionMotivo) {
		this.descripcionMotivo = descripcionMotivo;
	}
	public String getNombreCopropietario() {
		return nombreCopropietario;
	}
	public void setNombreCopropietario(String nombreCopropietario) {
		this.nombreCopropietario = nombreCopropietario;
	}
	public boolean isMostrarBotonModificar() {
		return mostrarBotonModificar;
	}
	public void setMostrarBotonModificar(boolean mostrarBotonModificar) {
		this.mostrarBotonModificar = mostrarBotonModificar;
	}
	public boolean isMostrarBotonAnular() {
		return mostrarBotonAnular;
	}
	public void setMostrarBotonAnular(boolean mostrarBotonAnular) {
		this.mostrarBotonAnular = mostrarBotonAnular;
	}
	public boolean isEsEditable() {
		return esEditable;
	}
	public void setEsEditable(boolean esEditable) {
		this.esEditable = esEditable;
	}
	public boolean isArchivoValidado() {
		return archivoValidado;
	}
	public void setArchivoValidado(boolean archivoValidado) {
		this.archivoValidado = archivoValidado;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getObservacionAnterior() {
		return observacionAnterior;
	}
	public void setObservacionAnterior(String observacionAnterior) {
		this.observacionAnterior = observacionAnterior;
	}
	public String getUrlFotoAnalista() {
		return urlFotoAnalista;
	}
	public void setUrlFotoAnalista(String urlFotoAnalista) {
		this.urlFotoAnalista = urlFotoAnalista;
	}
	public String getMailAnalista() {
		return mailAnalista;
	}
	public void setMailAnalista(String mailAnalista) {
		this.mailAnalista = mailAnalista;
	}
	public String getMailCopropietario() {
		return mailCopropietario;
	}
	public void setMailCopropietario(String mailCopropietario) {
		this.mailCopropietario = mailCopropietario;
	}
	public String getUrlFotoCopropietario() {
		return urlFotoCopropietario;
	}
	public void setUrlFotoCopropietario(String urlFotoCopropietario) {
		this.urlFotoCopropietario = urlFotoCopropietario;
	}
	public String getMotivoOMFormateado() {
		return motivoOMFormateado;
	}
	public boolean isMostrarBotonVerCobro() {
		return mostrarBotonVerCobro;
	}
	public void setMostrarBotonVerCobro(boolean mostrarBotonVerCobro) {
		this.mostrarBotonVerCobro = mostrarBotonVerCobro;
	}
	/**
	 * @return the fileBytes
	 */
	public byte[] getFileBytes() {
		return fileBytes;
	}
	/**
	 * @param fileBytes the fileBytes to set
	 */
	public void setFileBytes(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}
	
	
	

}
