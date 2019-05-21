package ar.com.telecom.shiva.presentacion.bean.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.telecom.shiva.base.comparador.ComparatorOrdenDescobroOperacionesRelacionadasDto;
import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoIdReversionPadreEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;

/**
 * @author u579607
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class DescobroDto extends GestionDto{
	

	private static final long serialVersionUID = 1L;

	private Long idDescobroPadre;
	private String idDescobroPadreFormateado;
	private String idDescobroPadreFormateadoOtro;
	private Long idDescobro;
	private String idDescobroFormateado;
	private Long idOperacionCobro;
	private String idOperacionCobroFormateado; 
	private Long idOperacionDescobro;
	
	private Long idCobro;
	private String idCobroFormateado;
	private String empresa; //Descripciones
	private String segmento; //Descripciones
	
	private Long idLegajo;
	private String solapa;
	
	
	private String idMotivoReversion;
	private String descripcionMotivoReversion;
	private String clientesDescobro;
	private Estado estado;
	private String descripcionEstado;
	private MarcaEnum marca;
	private String descripcionMarca;
	private Date fechaUltimoEstado;
	private String fechaUltimoEstadoFormatoJson;
	
	private String idAnalista;
	private String urlFotoAnalista;
	private String nombreAnalista;
	private String mailAnalista;
	
	private String idCopropietario;
	private String nombreCopropietario;
	private String mailCopropietario;
	private String UrlFotoCopropietario;
	
	private String idAnalistaTeamComercial;
	private String urlFotoAnalistaTeamComercial;
	private String nombreAnalistaTeamComercial;
	private String mailAnalistaTeamComercial;
	
	private Long importeTotal;
	private String importeTotalFormateado;
	
	private Date fechaAlta;
	private String usuarioAlta;
	private String fechaAltaFormatoJson;
	private Date fechaDerivacion;
	private String fechaDerivacionFormatoJson;
	private Date fechaReversion;
	private String fechaReversionFormatoJson;
	
	private String observacion;
	private String observacionAnterior;
	private boolean simulado;
	
	private Date fechaAprobAplicacionManualRefOperacionesExternas;
	private String usuarioAprobAplicacionManualRefOperacionesExternas;
	private String nombreApellidoAprobAplicacionManualRefOperacionesExternas;
	private Date fechaConfirmacionAplicacionManualRefCaja;
	private String usuarioAprobAplicacionManualRefCaja;
	private String nombreApellidoAprobAplicacionManualRefCaja;

	private Set<CobroTransaccionDto> transaccionesDelCobro;
	private Set<DescobroTransaccionDto> transacciones;
	private Set<DescobroOperacionesRelacionadasDto> operacionesRelacionadas;
	private Set<DescobroDocumentoRelacionadoDto> documentosRelacionados;
	private List<ComprobanteDto> listaComprobantes;
	private List<CodigoOperacionExternaDto> listaCodigoDeOperacionesExternas;
	private String codigoOperacionExterna;
	private String tipoCobro;
	
	private boolean mostrarBotonAnular;
	private boolean mostrarBotonModificar;
	private boolean edicionParcial;
	
	private boolean guardadoOk;
	private boolean primerDescobro;
	private boolean vengoDeLaEdicion;
	private boolean reversionHabilitada = false;

	
	private String simularDescobro;
	
	private Collection<MarcaEnum> marcas = new ArrayList<MarcaEnum>();
	
	private TipoIdReversionPadreEnum tipoIdReversionPadreEnum;
	
	private String monedaOperacion;
	
	// Campo usado en la aprobacion del cobro
	private String sistemaDestinoDescripcion;
	private String sociedad;
	
	/*******************************************************************************************************************
	 * METODOS 	
	 *******************************************************************************************************************/
	public String getIdCobroFormateadoJSPDetalle() {
		return idCobroFormateado;
	}
	
	public String getIdOperacionFormateado() {
		if(idOperacionDescobro==null){
			return "";
		}else{
			return Utilidad.rellenarCerosIzquierda(idOperacionDescobro.toString().trim(), 7);
		}
	}
	
	/*******************************************************************************************************************
	 * GETTERS & SETTERS
	 *******************************************************************************************************************/
	
	/**
	 * @return the idDescobroPadre
	 */
	public Long getIdDescobroPadre() {
		return idDescobroPadre;
	}
	/**
	 * @param idDescobroPadre the idDescobroPadre to set
	 */
	public void setIdDescobroPadre(Long idDescobroPadre) {
		this.idDescobroPadre = idDescobroPadre;
	}
	/**
	 * @return the idDescobroPadreFormateado
	 */
	public String getIdDescobroPadreFormateado() {
		return idDescobroPadreFormateado;
	}
	/**
	 * @param idDescobroPadreFormateado the idDescobroPadreFormateado to set
	 */
	public void setIdDescobroPadreFormateado(String idDescobroPadreFormateado) {
		this.idDescobroPadreFormateado = idDescobroPadreFormateado;
	}
	/**
	 * @return the idDescobro
	 */
	public Long getIdDescobro() {
		return idDescobro;
	}
	/**
	 * @param idDescobro the idDescobro to set
	 */
	public void setIdDescobro(Long idDescobro) {
		this.idDescobro = idDescobro;
	}
	/**
	 * @return the idDescobroFormateado
	 */
	public String getIdDescobroFormateado() {
		return idDescobroFormateado;
	}

	/**
	 * @return the idOperacionCobro
	 */
	public Long getIdOperacionCobro() {
		return idOperacionCobro;
	}
	/**
	 * @param idOperacionCobro the idOperacionCobro to set
	 */
	public void setIdOperacionCobro(Long idOperacionCobro) {
		this.idOperacionCobro = idOperacionCobro;
	}
	/**
	 * @return the idOperacionCobroFormateado
	 */
	public String getIdOperacionCobroFormateado() {
		return idOperacionCobroFormateado;
	}
	/**
	 * @param idOperacionCobroFormateado the idOperacionCobroFormateado to set
	 */
	public void setIdOperacionCobroFormateado(String idOperacionCobroFormateado) {
		this.idOperacionCobroFormateado = idOperacionCobroFormateado;
	}
	/**
	 * @return the idMotivoReversion
	 */
	public String getIdMotivoReversion() {
		return idMotivoReversion;
	}
	/**
	 * @param idMotivoReversion the idMotivoReversion to set
	 */
	public void setIdMotivoReversion(String idMotivoReversion) {
		this.idMotivoReversion = idMotivoReversion;
	}
	/**
	 * @return the descripcionMotivoReversion
	 */
	public String getDescripcionMotivoReversion() {
		return descripcionMotivoReversion;
	}
	/**
	 * @param descripcionMotivoReversion the descripcionMotivoReversion to set
	 */
	public void setDescripcionMotivoReversion(String descripcionMotivoReversion) {
		this.descripcionMotivoReversion = descripcionMotivoReversion;
	}
	/**
	 * @return the clientesDescobro
	 */
	public String getClientesDescobro() {
		return clientesDescobro;
	}
	/**
	 * @param clientesDescobro the clientesDescobro to set
	 */
	public void setClientesDescobro(String clientesDescobro) {
		this.clientesDescobro = clientesDescobro;
	}
	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	/**
	 * @return the marca
	 */
	public MarcaEnum getMarca() {
		return marca;
	}
	/**
	 * @param marca the marca to set
	 */
	public void setMarca(MarcaEnum marca) {
		this.marca = marca;
	}
	/**
	 * @return the fechaUltimoEstado
	 */
	public Date getFechaUltimoEstado() {
		return fechaUltimoEstado;
	}
	/**
	 * @param fechaUltimoEstado the fechaUltimoEstado to set
	 */
	public void setFechaUltimoEstado(Date fechaUltimoEstado) {
		this.fechaUltimoEstado = fechaUltimoEstado;
	}
	/**
	 * @return the fechaUltimoEstadoFormatoJson
	 */
	public String getFechaUltimoEstadoFormatoJson() {
		return fechaUltimoEstadoFormatoJson;
	}
	/**
	 * @param fechaUltimoEstadoFormatoJson the fechaUltimoEstadoFormatoJson to set
	 */
	public void setFechaUltimoEstadoFormatoJson(String fechaUltimoEstadoFormatoJson) {
		this.fechaUltimoEstadoFormatoJson = fechaUltimoEstadoFormatoJson;
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
	 * @return the urlFotoAnalista
	 */
	public String getUrlFotoAnalista() {
		return urlFotoAnalista;
	}
	/**
	 * @param urlFotoAnalista the urlFotoAnalista to set
	 */
	public void setUrlFotoAnalista(String urlFotoAnalista) {
		this.urlFotoAnalista = urlFotoAnalista;
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
	 * @return the mailAnalista
	 */
	public String getMailAnalista() {
		return mailAnalista;
	}
	/**
	 * @param mailAnalista the mailAnalista to set
	 */
	public void setMailAnalista(String mailAnalista) {
		this.mailAnalista = mailAnalista;
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
	 * @return the nombreCopropietario
	 */
	public String getNombreCopropietario() {
		return nombreCopropietario;
	}
	/**
	 * @param nombreCopropietario the nombreCopropietario to set
	 */
	public void setNombreCopropietario(String nombreCopropietario) {
		this.nombreCopropietario = nombreCopropietario;
	}
	/**
	 * @return the mailCopropietario
	 */
	public String getMailCopropietario() {
		return mailCopropietario;
	}
	/**
	 * @param mailCopropietario the mailCopropietario to set
	 */
	public void setMailCopropietario(String mailCopropietario) {
		this.mailCopropietario = mailCopropietario;
	}
	/**
	 * @return the urlFotoCopropietario
	 */
	public String getUrlFotoCopropietario() {
		return UrlFotoCopropietario;
	}
	/**
	 * @param urlFotoCopropietario the urlFotoCopropietario to set
	 */
	public void setUrlFotoCopropietario(String urlFotoCopropietario) {
		UrlFotoCopropietario = urlFotoCopropietario;
	}
	/**
	 * @return the idAnalistaTeamComercial
	 */
	public String getIdAnalistaTeamComercial() {
		return idAnalistaTeamComercial;
	}
	/**
	 * @param idAnalistaTeamComercial the idAnalistaTeamComercial to set
	 */
	public void setIdAnalistaTeamComercial(String idAnalistaTeamComercial) {
		this.idAnalistaTeamComercial = idAnalistaTeamComercial;
	}
	/**
	 * @return the urlFotoAnalistaTeamComercial
	 */
	public String getUrlFotoAnalistaTeamComercial() {
		return urlFotoAnalistaTeamComercial;
	}
	/**
	 * @param urlFotoAnalistaTeamComercial the urlFotoAnalistaTeamComercial to set
	 */
	public void setUrlFotoAnalistaTeamComercial(String urlFotoAnalistaTeamComercial) {
		this.urlFotoAnalistaTeamComercial = urlFotoAnalistaTeamComercial;
	}
	/**
	 * @return the nombreAnalistaTeamComercial
	 */
	public String getNombreAnalistaTeamComercial() {
		return nombreAnalistaTeamComercial;
	}
	/**
	 * @param nombreAnalistaTeamComercial the nombreAnalistaTeamComercial to set
	 */
	public void setNombreAnalistaTeamComercial(String nombreAnalistaTeamComercial) {
		this.nombreAnalistaTeamComercial = nombreAnalistaTeamComercial;
	}
	/**
	 * @return the mailAnalistaTeamComercial
	 */
	public String getMailAnalistaTeamComercial() {
		return mailAnalistaTeamComercial;
	}
	/**
	 * @param mailAnalistaTeamComercial the mailAnalistaTeamComercial to set
	 */
	public void setMailAnalistaTeamComercial(String mailAnalistaTeamComercial) {
		this.mailAnalistaTeamComercial = mailAnalistaTeamComercial;
	}
	/**
	 * @return the importeTotal
	 */
	public Long getImporteTotal() {
		return importeTotal;
	}
	/**
	 * @param importeTotal the importeTotal to set
	 */
	public void setImporteTotal(Long importeTotal) {
		this.importeTotal = importeTotal;
	}
	/**
	 * @return the importeTotalFormateado
	 */
	public String getImporteTotalFormateado() {
		return importeTotalFormateado;
	}
	/**
	 * @param importeTotalFormateado the importeTotalFormateado to set
	 */
	public void setImporteTotalFormateado(String importeTotalFormateado) {
		this.importeTotalFormateado = importeTotalFormateado;
	}
	/**
	 * @return the fechaAlta
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}
	/**
	 * @param fechaAlta the fechaAlta to set
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	/**
	 * @return the fechaAltaFormatoJson
	 */
	public String getFechaAltaFormatoJson() {
		return fechaAltaFormatoJson;
	}
	/**
	 * @param fechaAltaFormatoJson the fechaAltaFormatoJson to set
	 */
	public void setFechaAltaFormatoJson(String fechaAltaFormatoJson) {
		this.fechaAltaFormatoJson = fechaAltaFormatoJson;
	}
	/**
	 * @return the fechaDerivacion
	 */
	public Date getFechaDerivacion() {
		return fechaDerivacion;
	}
	/**
	 * @param fechaDerivacion the fechaDerivacion to set
	 */
	public void setFechaDerivacion(Date fechaDerivacion) {
		this.fechaDerivacion = fechaDerivacion;
	}
	/**
	 * @return the fechaDerivacionFormatoJson
	 */
	public String getFechaDerivacionFormatoJson() {
		return fechaDerivacionFormatoJson;
	}
	/**
	 * @param fechaDerivacionFormatoJson the fechaDerivacionFormatoJson to set
	 */
	public void setFechaDerivacionFormatoJson(String fechaDerivacionFormatoJson) {
		this.fechaDerivacionFormatoJson = fechaDerivacionFormatoJson;
	}
	/**
	 * @return the fechaReversion
	 */
	public Date getFechaReversion() {
		return fechaReversion;
	}
	/**
	 * @param fechaReversion the fechaReversion to set
	 */
	public void setFechaReversion(Date fechaReversion) {
		this.fechaReversion = fechaReversion;
	}
	/**
	 * @return the fechaReversionFormatoJson
	 */
	public String getFechaReversionFormatoJson() {
		return fechaReversionFormatoJson;
	}
	/**
	 * @param fechaReversionFormatoJson the fechaReversionFormatoJson to set
	 */
	public void setFechaReversionFormatoJson(String fechaReversionFormatoJson) {
		this.fechaReversionFormatoJson = fechaReversionFormatoJson;
	}
	/**
	 * @return the descripcionEstado
	 */
	public String getDescripcionEstado() {
		return descripcionEstado;
	}
	/**
	 * @param descripcionEstado the descripcionEstado to set
	 */
	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}
	/**
	 * @return the descripcionMarca
	 */
	public String getDescripcionMarca() {
		return descripcionMarca;
	}
	/**
	 * @param descripcionMarca the descripcionMarca to set
	 */
	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
	}
	/**
	 * @return the mostrarBotonAnular
	 */
	public boolean isMostrarBotonAnular() {
		return mostrarBotonAnular;
	}
	/**
	 * @param mostrarBotonAnular the mostrarBotonAnular to set
	 */
	public void setMostrarBotonAnular(boolean mostrarBotonAnular) {
		this.mostrarBotonAnular = mostrarBotonAnular;
	}
	/**
	 * @return the mostrarBotonModificar
	 */
	public boolean isMostrarBotonModificar() {
		return mostrarBotonModificar;
	}
	/**
	 * @param mostrarBotonModificar the mostrarBotonModificar to set
	 */
	public void setMostrarBotonModificar(boolean mostrarBotonModificar) {
		this.mostrarBotonModificar = mostrarBotonModificar;
	}
	/**
	 * @return the idOperacionDescobro
	 */
	public Long getIdOperacionDescobro() {
		return idOperacionDescobro;
	}
	/**
	 * @param idOperacionDescobro the idOperacionDescobro to set
	 */
	public void setIdOperacionDescobro(Long idOperacionDescobro) {
		this.idOperacionDescobro = idOperacionDescobro;
	}
	/**
	 * @return the idCobro
	 */
	public Long getIdCobro() {
		return idCobro;
	}
	/**
	 * @param idCobro the idCobro to set
	 */
	public void setIdCobro(Long idCobro) {
		this.idCobro = idCobro;
	}
	/**
	 * @return the idCobroFormateado
	 */
	public String getIdCobroFormateado() {
		return !Validaciones.isObjectNull(idCobro)?idCobro.toString():"";
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
	/**
	 * @return the usuarioAlta
	 */
	public String getUsuarioAlta() {
		return usuarioAlta;
	}
	/**
	 * @param usuarioAlta the usuarioAlta to set
	 */
	public void setUsuarioAlta(String usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}
	/**
	 * @return the transacciones
	 */
	public Set<DescobroTransaccionDto> getTransacciones() {
		return transacciones;
	}
	
	/**
	 * @param transacciones the transacciones to set
	 */
	public void setTransacciones(Set<DescobroTransaccionDto> transacciones) {
		this.transacciones = transacciones;
	}
	
	public Set<DescobroTransaccionDto> getTransaccionesSinDifCambio() {
		
		if(Validaciones.isCollectionNotEmpty(transacciones)){
			Set<DescobroTransaccionDto> listaAux = new HashSet<DescobroTransaccionDto>();
			
			for(DescobroTransaccionDto transaccion : transacciones){
				if(!Utilidad.esDiferenciaCambio(transaccion)){
					listaAux.add(transaccion);
				}				
			}
			return listaAux;
		}
		return transacciones;
	}
	
	public void setIdDescobroFormateado(String idDescobroFormateado) {
		this.idDescobroFormateado = idDescobroFormateado;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @return the simulado
	 */
	public boolean isSimulado() {
		return simulado;
	}
	/**
	 * @param simulado the simulado to set
	 */
	public void setSimulado(boolean simulado) {
		this.simulado = simulado;
	}
	
	public Collection<MarcaEnum> getMarcas() {
		return marcas;
	}
	public void setMarcas(Collection<MarcaEnum> marcas) {
		this.marcas = marcas;
	}

	public Set<DescobroOperacionesRelacionadasDto> getOperacionesRelacionadas() {
		return operacionesRelacionadas;
	}
	
	public List<DescobroOperacionesRelacionadasDto> obtenerOperacionesRelacionadasOrdenadasDescendente() {
	
		List<DescobroOperacionesRelacionadasDto> mainList = new ArrayList<DescobroOperacionesRelacionadasDto>();
		
		mainList.addAll(operacionesRelacionadas);
		
		
		Collections.sort(mainList, new ComparatorOrdenDescobroOperacionesRelacionadasDto());
		
		
		return mainList;
	}

	public void setOperacionesRelacionadas(
			Set<DescobroOperacionesRelacionadasDto> operacionesRelacionadas) {
		this.operacionesRelacionadas = operacionesRelacionadas;
	}
	
	public boolean getGuardadoOk() {
		return guardadoOk;
	}

	public void setGuardadoOk(boolean guardadoOk) {
		this.guardadoOk = guardadoOk;
	}

	public boolean getPrimerDescobro() {
		return primerDescobro;
	}

	public void setPrimerDescobro(boolean primerDescobro) {
		this.primerDescobro = primerDescobro;
	}
	
	public List<ComprobanteDto> getListaComprobantes() {
		return listaComprobantes;
	}

	public void setListaComprobantes(List<ComprobanteDto> listaComprobantes) {
		this.listaComprobantes = listaComprobantes;
	}
	
	public boolean isVengoDeLaEdicion() {
		return vengoDeLaEdicion;
	}

	public void setVengoDeLaEdicion(boolean vengoDeLaEdicion) {
		this.vengoDeLaEdicion = vengoDeLaEdicion;
	}

	/**
	 * @return the edicionParcial
	 */
	public boolean isEdicionParcial() {
		return edicionParcial;
	}

	/**
	 * @param edicionParcial the edicionParcial to set
	 */
	public void setEdicionParcial(boolean edicionParcial) {
		this.edicionParcial = edicionParcial;
	}

	public Set<CobroTransaccionDto> getTransaccionesDelCobro() {
		return transaccionesDelCobro;
	}

	public void setTransaccionesDelCobro(Set<CobroTransaccionDto> transaccionesDelCobro) {
		this.transaccionesDelCobro = transaccionesDelCobro;
	}

	public String getObservacionAnterior() {
		return observacionAnterior;
	}

	public void setObservacionAnterior(String observacionAnterior) {
		this.observacionAnterior = observacionAnterior;
	}

	public String getSimularDescobro() {
		return simularDescobro;
	}

	public void setSimularDescobro(String simularDescobro) {
		this.simularDescobro = simularDescobro;
	}

	public String getIdDescobroPadreFormateadoOtro() {
		return idDescobroPadreFormateadoOtro;
	}

	public void setIdDescobroPadreFormateadoOtro(
			String idDescobroPadreFormateadoOtro) {
		this.idDescobroPadreFormateadoOtro = idDescobroPadreFormateadoOtro;
	}

	public TipoIdReversionPadreEnum getTipoIdReversionPadreEnum() {
		return tipoIdReversionPadreEnum;
	}

	public void setTipoIdReversionPadreEnum(TipoIdReversionPadreEnum tipoIdReversionPadreEnum) {
		this.tipoIdReversionPadreEnum = tipoIdReversionPadreEnum;
	}

	public boolean isReversionHabilitada() {
		return reversionHabilitada;
	}

	public void setReversionHabilitada(boolean reversionHabilitada) {
		this.reversionHabilitada = reversionHabilitada;
	}

	/**
	 * @return the documentosRelacionados
	 */
	public Set<DescobroDocumentoRelacionadoDto> getDocumentosRelacionados() {
		return documentosRelacionados;
	}

	/**
	 * @param documentosRelacionados the documentosRelacionados to set
	 */
	public void setDocumentosRelacionados(Set<DescobroDocumentoRelacionadoDto> documentosRelacionados) {
		this.documentosRelacionados = documentosRelacionados;
	}

	public String getMonedaOperacion() {
		return monedaOperacion;
	}

	public void setMonedaOperacion(String monedaOperacion) {
		this.monedaOperacion = monedaOperacion;
	}

	public Long getIdLegajo() {
		return idLegajo;
	}

	public void setIdLegajo(Long idLegajo) {
		this.idLegajo = idLegajo;
	}

	public String getSolapa() {
		return solapa;
	}

	public void setSolapa(String solapa) {
		this.solapa = solapa;
	}

	/**
	 * @return the listaCodigoDeOperacionesExternas
	 */
	public List<CodigoOperacionExternaDto> getListaCodigoDeOperacionesExternas() {
		return listaCodigoDeOperacionesExternas;
	}

	/**
	 * @param listaCodigoDeOperacionesExternas the listaCodigoDeOperacionesExternas to set
	 */
	public void setListaCodigoDeOperacionesExternas(
			List<CodigoOperacionExternaDto> listaCodigoDeOperacionesExternas) {
		this.listaCodigoDeOperacionesExternas = listaCodigoDeOperacionesExternas;
	}

	/**
	 * @return the codigoOperacionExterna
	 */
	public String getCodigoOperacionExterna() {
		return codigoOperacionExterna;
	}

	/**
	 * @param codigoOperacionExterna the codigoOperacionExterna to set
	 */
	public void setCodigoOperacionExterna(String codigoOperacionExterna) {
		this.codigoOperacionExterna = codigoOperacionExterna;
	}

	/**
	 * @return the tipoCobro
	 */
	public String getTipoCobro() {
		return tipoCobro;
	}

	/**
	 * @param tipoCobro the tipoCobro to set
	 */
	public void setTipoCobro(String tipoCobro) {
		this.tipoCobro = tipoCobro;
	}

	/**
	 * @return the fechaAprobAplicacionManualRefOperacionesExternas
	 */
	public Date getFechaAprobAplicacionManualRefOperacionesExternas() {
		return fechaAprobAplicacionManualRefOperacionesExternas;
	}

	/**
	 * @param fechaAprobAplicacionManualRefOperacionesExternas the fechaAprobAplicacionManualRefOperacionesExternas to set
	 */
	public void setFechaAprobAplicacionManualRefOperacionesExternas(
			Date fechaAprobAplicacionManualRefOperacionesExternas) {
		this.fechaAprobAplicacionManualRefOperacionesExternas = fechaAprobAplicacionManualRefOperacionesExternas;
	}

	/**
	 * @return the usuarioAprobAplicacionManualRefOperacionesExternas
	 */
	public String getUsuarioAprobAplicacionManualRefOperacionesExternas() {
		return usuarioAprobAplicacionManualRefOperacionesExternas;
	}

	/**
	 * @param usuarioAprobAplicacionManualRefOperacionesExternas the usuarioAprobAplicacionManualRefOperacionesExternas to set
	 */
	public void setUsuarioAprobAplicacionManualRefOperacionesExternas(
			String usuarioAprobAplicacionManualRefOperacionesExternas) {
		this.usuarioAprobAplicacionManualRefOperacionesExternas = usuarioAprobAplicacionManualRefOperacionesExternas;
	}

	/**
	 * @return the nombreApellidoAprobAplicacionManualRefOperacionesExternas
	 */
	public String getNombreApellidoAprobAplicacionManualRefOperacionesExternas() {
		return nombreApellidoAprobAplicacionManualRefOperacionesExternas;
	}

	/**
	 * @param nombreApellidoAprobAplicacionManualRefOperacionesExternas the nombreApellidoAprobAplicacionManualRefOperacionesExternas to set
	 */
	public void setNombreApellidoAprobAplicacionManualRefOperacionesExternas(
			String nombreApellidoAprobAplicacionManualRefOperacionesExternas) {
		this.nombreApellidoAprobAplicacionManualRefOperacionesExternas = nombreApellidoAprobAplicacionManualRefOperacionesExternas;
	}

	/**
	 * @return the fechaConfirmacionAplicacionManualRefCaja
	 */
	public Date getFechaConfirmacionAplicacionManualRefCaja() {
		return fechaConfirmacionAplicacionManualRefCaja;
	}

	/**
	 * @param fechaConfirmacionAplicacionManualRefCaja the fechaConfirmacionAplicacionManualRefCaja to set
	 */
	public void setFechaConfirmacionAplicacionManualRefCaja(
			Date fechaConfirmacionAplicacionManualRefCaja) {
		this.fechaConfirmacionAplicacionManualRefCaja = fechaConfirmacionAplicacionManualRefCaja;
	}

	/**
	 * @return the usuarioAprobAplicacionManualRefCaja
	 */
	public String getUsuarioAprobAplicacionManualRefCaja() {
		return usuarioAprobAplicacionManualRefCaja;
	}

	/**
	 * @param usuarioAprobAplicacionManualRefCaja the usuarioAprobAplicacionManualRefCaja to set
	 */
	public void setUsuarioAprobAplicacionManualRefCaja(
			String usuarioAprobAplicacionManualRefCaja) {
		this.usuarioAprobAplicacionManualRefCaja = usuarioAprobAplicacionManualRefCaja;
	}

	/**
	 * @return the nombreApellidoAprobAplicacionManualRefCaja
	 */
	public String getNombreApellidoAprobAplicacionManualRefCaja() {
		return nombreApellidoAprobAplicacionManualRefCaja;
	}

	/**
	 * @param nombreApellidoAprobAplicacionManualRefCaja the nombreApellidoAprobAplicacionManualRefCaja to set
	 */
	public void setNombreApellidoAprobAplicacionManualRefCaja(
			String nombreApellidoAprobAplicacionManualRefCaja) {
		this.nombreApellidoAprobAplicacionManualRefCaja = nombreApellidoAprobAplicacionManualRefCaja;
	}

	/**
	 * @return the sistemaDestinoDescripcion
	 */
	public String getSistemaDestinoDescripcion() {
		return sistemaDestinoDescripcion;
	}

	/**
	 * @param sistemaDestinoDescripcion the sistemaDestinoDescripcion to set
	 */
	public void setSistemaDestinoDescripcion(String sistemaDestinoDescripcion) {
		this.sistemaDestinoDescripcion = sistemaDestinoDescripcion;
	}

	/**
	 * @return the sociedad
	 */
	public String getSociedad() {
		return sociedad;
	}

	/**
	 * @param sociedad the sociedad to set
	 */
	public void setSociedad(String sociedad) {
		this.sociedad = sociedad;
	}
		
}
