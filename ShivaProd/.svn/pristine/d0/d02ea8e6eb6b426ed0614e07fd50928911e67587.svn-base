package ar.com.telecom.shiva.presentacion.bean.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.telecom.shiva.base.comparador.ComparatorOrdenCobroClienteDto;
import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.presentacion.bean.paginacion.CreditosControlPaginacion;


/**
 * Clase DTO que se usa para los cobros que se editan online - Sprint 4
 * @author u562163
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class CobroDto extends GestionDto {
	
	private static final long serialVersionUID = 1L;

	private Long idCobro;
	private Long formAdjunto;
	private Long idCobroPadre;
	private String idCobroFormatiado;
	private Long idOperacion;
	@SuppressWarnings("unused")
	private String idOperacionFormateado;
	private Estado estado;
	private Integer idWorkflow;	
	private Collection<MarcaEnum> marcas = new ArrayList<MarcaEnum>();
	
	private String empresa; //Descripciones
	private String segmento; //Descripciones
	
	private String idAnalista;
	private String urlFotoAnalista;
	private String nombreAnalista;
	private String mailAnalista;
	
	private String idCopropietario;
	private String nombreCopropietario;
	private String mailCopropietario;
	private String UrlFotoCopropietario;
	
	private String idAnalistaTeamComercial;
	private String nombreAnalistaTeamComercial;
	private String mailAnalistaTeamComercial;
	private String UrlFotoAnalistaTeamComercial;
	
	private String observacion;
	private String observacionAnterior;
	private String observacionCap;

	private String idMotivoCobro;
	private String descripcionMotivoCobro;
	
	private Date fechaAlta;
	private String fechaAltaFormatoJson;
	private String usuarioAlta;
	private Date fechaDerivacion;
	private String fechaDerivacionFormatoJson;
	private String usuarioDerivacion;
	private Date fechaAprobacionSupervisorCobranza;
	private String fechaAprobacionSupervisorCobranzaFormatoJson;
	private String usuarioAprobacionSupervisorCobranza;
	private String nombreApellidoUsuarioAprobacionSupervisorCobranza;

	private Date fechaAprobacionReferenteCobranza;
	private String fechaAprobacionReferenteCobranzaFormatoJson;
	private String usuarioAprobacionReferenteCobranza;
	private String nombreApellidoUsuarioAprobacionReferenteCobranza;
	
	private Date fechaImputacion;
	private String fechaImputacionFormatoJson;
	private String usuarioImputacion;
	private Date fechaUltimoEstado;
	private String fechaUltimoEstadoFormatoJson;
	
	private Set<ClienteDto> clientes;
	private Set<CobroDebitoDto> debitos;
	private Set<CobroOtrosDebitoDto> otrosDebitos;
	private Set<CobroCreditoDto> creditos;
	private Set<CobroMedioDePagoDto> medios;

	private Set<CobroTransaccionDto> transacciones;
	private CobroTratamientoDiferenciaDto tratamientoDiferencia;
	
	private Set<ComprobanteDto> listaComprobantes;
	
	private Set<ComprobanteDto> listaComprobanteAplicacionManual;
	
	private Set<ComprobanteDto> listaComprobanteOtrosDebito;
	
	private ArrayList<String> listaIdsAdjuntosOtrosDebitos;
	
	private Set<CodigoOperacionExternaDto> listaCodigoOperacionesExternas;
	
	private BigDecimal sumaImpDebitos;
	private BigDecimal sumaImpOtrosDebitos;
	private BigDecimal sumaImpCreditos;
	private BigDecimal sumaImpMedioDePago;
	private BigDecimal sumaTotalDebitos;
	
	//Para invocar al servicio de facturacion por numero de linea
	private String numeroLinea;
	private String numeroAcuerdo;
	private String idMedioPago;
	private String monedaDocumento;
	private boolean esTratamientoDiferencia;
	
	// Para la busqueda de cobros
	@SuppressWarnings("unused")
	private String descripcionEstado;
	private String descripcionMarca;
	private Long importeTotalDeuda;
	private String importeTotalDeudaFormateado;
	private BigDecimal importeTotal;
	private String clientesCobro;
	private String codigoOperacionExterna;
	private String tipoCobro;
	
	
	
	
	//Para mostrar botones
	private boolean mostrarBotonAnular;
	private boolean mostrarBotonModificar;
	private boolean mostrarBotonRevertir;
	private boolean desapropiacionHabilitada = false;
	private boolean setearFechaAlta = false;
	
	private boolean transaccionesOK;
	
	private String vengoReedicion;
	private Long idTarea;
	
	private String idReversion;
	private String idOperacionReversion;
	private String idReversionPadre;
	private boolean btnGuardar = false;
	private Estado estadoReversion;
	private String estadoReversionDescripcion;

	private String idOperacionMasiva;
	private String nombreArchivo;
	private SistemaEnum origenDescobro;
	private boolean tieneDuc;
	private boolean debitoAProx;
	
	private String monedaOperacion;

	private String fechaTipoCambio;

	private String tipoCambio;
	private	Date fechaAprobacionOperacionesEspeciales;
	private String usuarioAprobacionSupervisorOperacionesEspeciales;
	private String nombreApellidoUsuarioAprobacionSupervisorOperacionesEspeciales;
	private String fechaAprobacionOperacionesEspecialesFormatoJson;
	private String idContradocumentoCap;
	private String idTipoMedioPago;
	
	private Date fechaRechazoAplicacionManual;
	private String usuarioRechazoAplicacionManual;
	private String nombreApellidoReferenteCaja;
	private String idReferenteCaja;
	private Date fechaReferenteCaja;
	private String fechaReferenteCajaFormatoJson;
	private String nombreApellidoReferenteOperacionExterna;
	private String idReferenteoperacionexterna;
	private Date fechaReferenteOperacionExterna;
	private String fechaReferenteOperacionExternaFormatoJson;
	
	private CreditosControlPaginacion controlPaginacion;
	
	// Se usan para borrar el adjunto
	private boolean borrarAdjuntoAplicacionManal = false;
	private String idAdjuntoApliacionManualPrev;
	
	private boolean eliminarTodosAdjuntosOtrosDeb = false;
	
	// Campo usado en la aprobacion del cobro
	private String sistemaDestinoDescripcion;
	private String sociedad;
	
	private BigDecimal importeParcial;
	
	public BigDecimal getImporteParcial() {
		return importeParcial;
	}
	public void setImporteParcial(BigDecimal importeParcial) {
		this.importeParcial = importeParcial;
	}
	/*******************************************************************************************************************
	 * Metodos particulares
	 *******************************************************************************************************************/
	public List<String> listaIdClientesLegado() {
		List<String> lista = new ArrayList<String>();
		if (Validaciones.isCollectionNotEmpty(clientes)) {
			for (ClienteDto clienteDto: clientes) {
				lista.add(clienteDto.getIdClienteLegado());
			}
		}
		return lista;
	}
	
	private String cuentaContable;
	
	//este metodo devuelve un string con todos los clientes separados por -
	public String getListaIdClientesLegado() {
		
		StringBuffer listaClientes = new StringBuffer("-");
		
		if(Validaciones.isCollectionNotEmpty(clientes)){
			
			listaClientes.deleteCharAt(0);
			
			Iterator<ClienteDto> itClientes = getClientesOrdenadosAsc().iterator();
			
			while(itClientes.hasNext()){
				ClienteDto clienteDto = itClientes.next();
				
				String infoCliente = (Utilidad.rellenarCerosIzquierda(clienteDto.getIdClienteLegado(), 10) );
				
				StringBuffer descripcionCliente =  new StringBuffer(infoCliente);
				
				if( itClientes.hasNext()){
					descripcionCliente.append("-");
				
				}
				listaClientes.append(descripcionCliente);
			}
		}
		
		return listaClientes.toString();
		
	}
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @param conBr
	 * @param conBarraRBarraN
	 * @return String
	 */
	private String getListaIdClientesLegadoRazonSocial(boolean conBr, boolean conBarraRBarraN) {
		
		StringBuffer listaClientes = new StringBuffer("-");
		
		if(Validaciones.isCollectionNotEmpty(clientes)){
			
			listaClientes.deleteCharAt(0);
			
			Iterator<ClienteDto> itClientes = getClientesOrdenadosAsc().iterator();
			
			while(itClientes.hasNext()){
				ClienteDto clienteDto = itClientes.next();
				
				String infoCliente = (clienteDto.getIdClienteLegado() + " " + clienteDto.getRazonSocial());
				
				if(infoCliente.length() >= 30){
					infoCliente = infoCliente.substring(0, 30);
				}			
				
				StringBuffer descripcionCliente =  new StringBuffer(infoCliente);
				
				if(conBr && itClientes.hasNext()){
					descripcionCliente.append(" <br>");
				} else if(conBarraRBarraN && itClientes.hasNext()){
					descripcionCliente.append(" \r\n");
				} else if(conBr && conBarraRBarraN && itClientes.hasNext()){
					descripcionCliente.append(" <br>\r\n");
				} else {
					descripcionCliente.append(" ");
				}
				listaClientes.append(descripcionCliente);
			}
		}
		
		return listaClientes.toString();
		
	}	
	
	/**
	 * Se devuelven los clientes ordenados de manera ascendente
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @return Set<CobroClienteDto>
	 */
	public Set<ClienteDto> getClientesOrdenadosAsc() {
	
		Set<ClienteDto> setClientes = new TreeSet<ClienteDto>(new ComparatorOrdenCobroClienteDto());
		
		if(Validaciones.isCollectionNotEmpty(clientes)){
			
			setClientes.addAll(clientes);
		}
		
		return setClientes;
	}	
	
	public String getListaIdClientesLegadoRazonSocialConBr(){
		return getListaIdClientesLegadoRazonSocial(true, false);
	}
	
	public String getListaIdClientesLegadoRazonSocialConBarraRBarraN(){
		return getListaIdClientesLegadoRazonSocial(false, true);
	}
	
	public String getListaIdClientesLegadoRazonSocialConBrBarraRBarraN(){
		return getListaIdClientesLegadoRazonSocial(true, true);
	}
	
	public String getListaIdClientesLegadoRazonSocialSolo(){
		return getListaIdClientesLegadoRazonSocial(false, false);
	}
	
	/*******************************************************************************************************************
	 * GETTERS & SETTERS
	 *******************************************************************************************************************/
	public String getIdAnalista() {
		return idAnalista;
	}

	public void setIdAnalista(String idAnalista) {
		this.idAnalista = idAnalista;
	}

	public String getIdCopropietario() {
		return idCopropietario;
	}
	

	public void setIdCopropietario(String idCopropietario) {
		this.idCopropietario = idCopropietario;
	}

	public String getIdMotivoCobro() {
		return idMotivoCobro;
	}

	public void setIdMotivoCobro(String idMotivoCobro) {
		this.idMotivoCobro = idMotivoCobro;
	}

	public Set<ClienteDto> getClientes() {
		return clientes;
	}

	public void setClientes(Set<ClienteDto> clientes) {
		this.clientes = clientes;
	}

	public Set<CobroDebitoDto> getDebitos() {
		return debitos;
	}

	public void setDebitos(Set<CobroDebitoDto> debitos) {
		this.debitos = debitos;
	}

	public Set<CobroOtrosDebitoDto> getOtrosDebitos() {
		return otrosDebitos;
	}

	public void setOtrosDebitos(Set<CobroOtrosDebitoDto> otrosDebitos) {
		this.otrosDebitos = otrosDebitos;
	}
	
	public Set<CobroCreditoDto> getCreditos() {
		return creditos;
	}

	public void setCreditos(Set<CobroCreditoDto> creditos) {
		this.creditos = creditos;
	}

	public Set<CobroMedioDePagoDto> getMedios() {
		return medios;
	}

	public void setMedios(Set<CobroMedioDePagoDto> medios) {
		this.medios = medios;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public String getNombreAnalista() {
		return nombreAnalista;
	}

	public void setNombreAnalista(String nombreAnalista) {
		this.nombreAnalista = nombreAnalista;
	}

	public String getMailAnalista() {
		return mailAnalista;
	}

	public void setMailAnalista(String mailAnalista) {
		this.mailAnalista = mailAnalista;
	}

	public Long getIdCobro() {
		return idCobro;
	}

	public void setIdCobro(Long idCobro) {
		this.idCobro = idCobro;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getUsuarioAlta() {
		return usuarioAlta;
	}

	public void setUsuarioAlta(String usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}

	public Date getFechaDerivacion() {
		return fechaDerivacion;
	}

	public void setFechaDerivacion(Date fechaDerivacion) {
		this.fechaDerivacion = fechaDerivacion;
	}

	/**
	 * 
	 * @return
	 */
	@JsonIgnore
	public String getFechaDerivacionFormateado(){
		return Utilidad.formatDateAndTimeFull(this.getFechaDerivacion());
	}
	
	/**
	 * 
	 * @return
	 */
	@JsonIgnore
	public String getFechaUltimoEstadoFormateado(){
		return Utilidad.formatDateAndTimeFull(this.getFechaUltimoEstado());
	}
	
	public String getUsuarioDerivacion() {
		return usuarioDerivacion;
	}

	public void setUsuarioDerivacion(String usuarioDerivacion) {
		this.usuarioDerivacion = usuarioDerivacion;
	}

	public Date getFechaAprobacionSupervisorCobranza() {
		return fechaAprobacionSupervisorCobranza;
	}

	public void setFechaAprobacionSupervisorCobranza(
			Date fechaAprobacionSupervisorCobranza) {
		this.fechaAprobacionSupervisorCobranza = fechaAprobacionSupervisorCobranza;
	}

	public String getUsuarioAprobacionSupervisorCobranza() {
		return usuarioAprobacionSupervisorCobranza;
	}

	/**
	 * @return the nombreApellidoUsuarioAprobacionSupervisorCobranza
	 */
	public String getNombreApellidoUsuarioAprobacionSupervisorCobranza() {
		return nombreApellidoUsuarioAprobacionSupervisorCobranza;
	}

	/**
	 * @param nombreApellidoUsuarioAprobacionSupervisorCobranza the nombreApellidoUsuarioAprobacionSupervisorCobranza to set
	 */
	public void setNombreApellidoUsuarioAprobacionSupervisorCobranza(
			String nombreApellidoUsuarioAprobacionSupervisorCobranza) {
		this.nombreApellidoUsuarioAprobacionSupervisorCobranza = nombreApellidoUsuarioAprobacionSupervisorCobranza;
	}

	public void setUsuarioAprobacionSupervisorCobranza(
			String usuarioAprobacionSupervisorCobranza) {
		this.usuarioAprobacionSupervisorCobranza = usuarioAprobacionSupervisorCobranza;
	}

	/**
	 * @return the nombreApellidoUsuarioAprobacionReferenteCobranza
	 */
	public String getNombreApellidoUsuarioAprobacionReferenteCobranza() {
		return nombreApellidoUsuarioAprobacionReferenteCobranza;
	}

	/**
	 * @param nombreApellidoUsuarioAprobacionReferenteCobranza the nombreApellidoUsuarioAprobacionReferenteCobranza to set
	 */
	public void setNombreApellidoUsuarioAprobacionReferenteCobranza(
			String nombreApellidoUsuarioAprobacionReferenteCobranza) {
		this.nombreApellidoUsuarioAprobacionReferenteCobranza = nombreApellidoUsuarioAprobacionReferenteCobranza;
	}

	public Date getFechaAprobacionReferenteCobranza() {
		return fechaAprobacionReferenteCobranza;
	}

	public void setFechaAprobacionReferenteCobranza(
			Date fechaAprobacionReferenteCobranza) {
		this.fechaAprobacionReferenteCobranza = fechaAprobacionReferenteCobranza;
	}

	public String getUsuarioAprobacionReferenteCobranza() {
		return usuarioAprobacionReferenteCobranza;
	}

	public void setUsuarioAprobacionReferenteCobranza(
			String usuarioAprobacionReferenteCobranza) {
		this.usuarioAprobacionReferenteCobranza = usuarioAprobacionReferenteCobranza;
	}

	public Date getFechaImputacion() {
		return fechaImputacion;
	}

	public void setFechaImputacion(Date fechaImputacion) {
		this.fechaImputacion = fechaImputacion;
	}

	public String getUsuarioImputacion() {
		return usuarioImputacion;
	}

	public void setUsuarioImputacion(String usuarioImputacion) {
		this.usuarioImputacion = usuarioImputacion;
	}


	public CobroTratamientoDiferenciaDto getTratamientoDiferencia() {
		return tratamientoDiferencia;
	}

	public void setTratamientoDiferencia(
			CobroTratamientoDiferenciaDto tratamientoDiferencia) {
		this.tratamientoDiferencia = tratamientoDiferencia;
	}

	public Set<ComprobanteDto> getListaComprobantes() {
		return listaComprobantes;
	}

	public void setListaComprobantes(Set<ComprobanteDto> listaComprobantes) {
		this.listaComprobantes = listaComprobantes;
	}

	public Long getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}

	public String getNombreCopropietario() {
		return nombreCopropietario;
	}

	public void setNombreCopropietario(String nombreCopropietario) {
		this.nombreCopropietario = nombreCopropietario;
	}

	public String getMailCopropietario() {
		return mailCopropietario;
	}

	public void setMailCopropietario(String mailCopropietario) {
		this.mailCopropietario = mailCopropietario;
	}

	public String getDescripcionMotivoCobro() {
		return descripcionMotivoCobro;
	}

	public void setDescripcionMotivoCobro(String descripcionMotivoCobro) {
		this.descripcionMotivoCobro = descripcionMotivoCobro;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getNumeroLinea() {
		return numeroLinea;
	}

	public void setNumeroLinea(String numeroLinea) {
		this.numeroLinea = numeroLinea;
	}

	public String getNumeroAcuerdo() {
		return numeroAcuerdo;
	}

	public void setNumeroAcuerdo(String numeroAcuerdo) {
		this.numeroAcuerdo = numeroAcuerdo;
	}

	public String getObservacion() {
		return observacion;
	}

	public String getObservacionFormateado() {
		if(Validaciones.isNullOrEmpty(observacion)){
			return "";
		}else{
			return observacion.replace("<br>", "\n");
		}
	}
	
	public String getObservacionFormateadoMail() {
		if(Validaciones.isNullOrEmpty(observacion)){
			return "";
		}else{
			return observacion.replace("\n", "<br>");
		}
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	// Para la busqueda de cobros
	public String getDescripcionEstado() {
		return estado!=null?estado.descripcion():"";
	}

	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}

	public String getFechaAltaFormatoJson() {
		if (Validaciones.isNullEmptyOrDash(fechaAltaFormatoJson) && !Validaciones.isObjectNull(fechaAlta)){
			return Utilidad.formatDateAndTimeFullFromDataBase(fechaAlta.toString());
		}
		return fechaAltaFormatoJson;
	}

	public void setFechaAltaFormatoJson(String fechaAltaFormatoJson) {
		this.fechaAltaFormatoJson = fechaAltaFormatoJson;
	}

	public String getFechaDerivacionFormatoJson() {
		if (Validaciones.isNullEmptyOrDash(fechaDerivacionFormatoJson) && !Validaciones.isObjectNull(fechaDerivacion)){
			return Utilidad.formatDateAndTimeFullFromDataBase(fechaDerivacion.toString());
		}
		return fechaDerivacionFormatoJson;
	}

	public void setFechaDerivacionFormatoJson(String fechaDerivacionFormatoJson) {
		this.fechaDerivacionFormatoJson = fechaDerivacionFormatoJson;
	}

	public String getFechaAprobacionSupervisorCobranzaFormatoJson() {
		return fechaAprobacionSupervisorCobranzaFormatoJson;
	}

	public void setFechaAprobacionSupervisorCobranzaFormatoJson(
			String fechaAprobacionSupervisorCobranzaFormatoJson) {
		this.fechaAprobacionSupervisorCobranzaFormatoJson = fechaAprobacionSupervisorCobranzaFormatoJson;
	}

	public String getFechaAprobacionReferenteCobranzaFormatoJson() {
		if (Validaciones.isNullEmptyOrDash(fechaAprobacionReferenteCobranzaFormatoJson) && !Validaciones.isObjectNull(fechaAprobacionReferenteCobranza)){
			return Utilidad.formatDateAndTimeFullFromDataBase(fechaAprobacionReferenteCobranza.toString());
		}
		return fechaAprobacionReferenteCobranzaFormatoJson;
	}

	public void setFechaAprobacionReferenteCobranzaFormatoJson(
			String fechaAprobacionReferenteCobranzaFormatoJson) {
		this.fechaAprobacionReferenteCobranzaFormatoJson = fechaAprobacionReferenteCobranzaFormatoJson;
	}
	
	public String getFechaImputacionFormatoJson() {
		if (Validaciones.isNullEmptyOrDash(fechaImputacionFormatoJson) && !Validaciones.isObjectNull(fechaImputacion)){
			return Utilidad.formatDateAndTimeFullFromDataBase(fechaImputacion.toString());
		}
		return fechaImputacionFormatoJson;
	}
	
	public void setFechaImputacionFormatoJson(String fechaImputacionFormatoJson) {
		this.fechaImputacionFormatoJson = fechaImputacionFormatoJson;
	}

	public Long getImporteTotalDeuda() {
		return importeTotalDeuda;
	}

	public void setImporteTotalDeuda(Long importeTotalDeuda) {
		this.importeTotalDeuda = importeTotalDeuda;
	}

	public String getIdOperacionFormateado() {
		if(idOperacion==null){
			return "";
		}else{
			return Utilidad.rellenarCerosIzquierda(idOperacion.toString().trim(), 7);
		}
	}

	public void setIdOperacionFormateado(String idOperacionFormateado) {
		this.idOperacionFormateado = idOperacionFormateado;
	}

	public BigDecimal getSumaImpDebitos() {
		BigDecimal impaCobrar = BigDecimal.ZERO;
		sumaImpDebitos = BigDecimal.ZERO;
		Set<CobroDebitoDto> listaDebitos = getDebitos();
		if(Validaciones.isCollectionNotEmpty(listaDebitos)){
			for (CobroDebitoDto cobroDebitoDto : listaDebitos) {
				if(!Validaciones.isObjectNull(cobroDebitoDto.getImpACobrar()) && cobroDebitoDto.getImpACobrar()!="-")
					impaCobrar = Utilidad.stringToBigDecimal(cobroDebitoDto.getImpACobrar());
					
				sumaImpDebitos = sumaImpDebitos.add(impaCobrar);
			}
		}else{
			return impaCobrar;
		}
		return sumaImpDebitos;
	}

	public BigDecimal getSumaImpOtrosDebitos() {
		BigDecimal impaCobrar = BigDecimal.ZERO;
		sumaImpOtrosDebitos = BigDecimal.ZERO;
		Set<CobroOtrosDebitoDto> listaOtrosDebitos = getOtrosDebitos();
		if(Validaciones.isCollectionNotEmpty(listaOtrosDebitos)){
			for (CobroOtrosDebitoDto cobroOtrosDebitoDto : listaOtrosDebitos) {
				if(!Validaciones.isObjectNull(cobroOtrosDebitoDto.getImporte()) && cobroOtrosDebitoDto.getImporte()!="-")
					impaCobrar = Utilidad.stringToBigDecimal(cobroOtrosDebitoDto.getImporte());
					
				sumaImpOtrosDebitos = sumaImpOtrosDebitos.add(impaCobrar);
			}
		}else{
			return impaCobrar;
		}
		return sumaImpOtrosDebitos;
	}
	
	public BigDecimal getSumaImpCreditos() {
		BigDecimal impaUtilizar = BigDecimal.ZERO;
		sumaImpCreditos = BigDecimal.ZERO;
		Set<CobroCreditoDto> listaCreditos = getCreditos();
		if(Validaciones.isCollectionNotEmpty(listaCreditos)){
			for (CobroCreditoDto cobroCreditoDto : listaCreditos) {
				if(!Validaciones.isObjectNull(cobroCreditoDto.getImporteAUtilizar()) && cobroCreditoDto.getImporteAUtilizar()!="-")
					impaUtilizar = Utilidad.stringToBigDecimal(cobroCreditoDto.getImporteAUtilizar());
				sumaImpCreditos = sumaImpCreditos.add(impaUtilizar);
			}
		}else{
			return impaUtilizar;
		}
		return sumaImpCreditos;
	}
	
	public BigDecimal getSumaImpMediosDePago() {
		BigDecimal impaUtilizar = BigDecimal.ZERO;
		sumaImpMedioDePago = (BigDecimal.ZERO);
		Set<CobroMedioDePagoDto> listaMedios = getMedios();
		if(Validaciones.isCollectionNotEmpty(listaMedios)){
			for (CobroMedioDePagoDto cobroMedioDePagoDto : listaMedios) {
				if(!Validaciones.isObjectNull(cobroMedioDePagoDto.getImporte()) && cobroMedioDePagoDto.getImporte()!="-")
					impaUtilizar = Utilidad.stringToBigDecimal(cobroMedioDePagoDto.getImporte());
				sumaImpMedioDePago = sumaImpMedioDePago.add(impaUtilizar);
			}
		}else{
			return impaUtilizar;
		}
		return sumaImpMedioDePago;
	}

	public String getImporteTotalDeudaFormateado() {
		return importeTotalDeudaFormateado;
	}

	public void setImporteTotalDeudaFormateado(
			String importeTotalDeudaFormateado) {
		this.importeTotalDeudaFormateado = importeTotalDeudaFormateado;
	}
	public String getIdCobroFormatiado() {
		return idCobro!=null?idCobro.toString():"";
	}

	public String getIdCobroFormatiadoJSPDetalle() {
		return idCobroFormatiado;
	}
	
	public void setIdCobroFormatiado(String idCobroFormatiado) {
		this.idCobroFormatiado = idCobroFormatiado;
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

	public String getUrlFotoCopropietario() {
		return UrlFotoCopropietario;
	}

	public void setUrlFotoCopropietario(String urlFotoCopropietario) {
		UrlFotoCopropietario = urlFotoCopropietario;
	}

	public String getDescripcionMarca() {
		return descripcionMarca;
	}

	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
	}
	public Set<CobroTransaccionDto> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(Set<CobroTransaccionDto> transacciones) {
		this.transacciones = transacciones;
	}
	
	public Set<CobroTransaccionDto> getTransaccionesSinDifCambio() {
		
		if(Validaciones.isCollectionNotEmpty(transacciones)){
			Set<CobroTransaccionDto> listaAux = new HashSet<CobroTransaccionDto>();
			
			for(CobroTransaccionDto transaccion : transacciones){
				if(!Utilidad.esDiferenciaCambio(transaccion)){
					listaAux.add(transaccion);
				}				
			}
			
			return listaAux;
		}	
		
		return transacciones;
	}

	/**
	 * @return the marca
	 */
	public Collection<MarcaEnum> getMarcas() {
		return marcas;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarcas(Collection<MarcaEnum> marca) {
		this.marcas = marca;
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
	public void setFechaUltimoEstadoFormatoJson(
			String fechaUltimoEstadoFormatoJson) {
		this.fechaUltimoEstadoFormatoJson = fechaUltimoEstadoFormatoJson;
	}

	public boolean getTransaccionesOK() {
		return transaccionesOK;
	}

	public void setTransaccionesOK(boolean transaccionesOK) {
		this.transaccionesOK = transaccionesOK;
	}

	/**
	 * @return the idCobroPadre
	 */
	public Long getIdCobroPadre() {
		return idCobroPadre;
	}

	/**
	 * @param idCobroPadre the idCobroPadre to set
	 */
	public void setIdCobroPadre(Long idCobroPadre) {
		this.idCobroPadre = idCobroPadre;
	}

	public boolean getSetearFechaAlta() {
		return setearFechaAlta;
	}

	public void setSetearFechaAlta(boolean setearFechaAlta) {
		this.setearFechaAlta = setearFechaAlta;
	}

	/**
	 * @return the clientesCobro
	 */
	public String getClientesCobro() {
		return clientesCobro;
	}

	/**
	 * @param clientesCobro the clientesCobro to set
	 */
	public void setClientesCobro(String clientesCobro) {
		this.clientesCobro = clientesCobro;
	}

	/**
	 * @return the idReversion
	 */
	public String getIdReversion() {
		return idReversion;
	}

	/**
	 * @param idReversion the idReversion to set
	 */
	public void setIdReversion(String idReversion) {
		this.idReversion = idReversion;
	}
	/**
	 * @return the observacionAnterior
	 */
	public String getObservacionAnterior() {
		return observacionAnterior;
	}

	/**
	 * @param observacionAnterior the observacionAnterior to set
	 */
	public void setObservacionAnterior(String observacionAnterior) {
		this.observacionAnterior = observacionAnterior;
	}

	/**
	 * @return the btnGuardar
	 */
	public boolean isBtnGuardar() {
		return btnGuardar;
	}

	/**
	 * @param btnGuardar the btnGuardar to set
	 */
	public void setBtnGuardar(boolean btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	
	public boolean isMostrarBotonRevertir() {
		return mostrarBotonRevertir;
	}

	public void setMostrarBotonRevertir(boolean mostrarBotonRevertir) {
		this.mostrarBotonRevertir = mostrarBotonRevertir;
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
	 * @return the urlFotoAnalistaTeamComercial
	 */
	public String getUrlFotoAnalistaTeamComercial() {
		return UrlFotoAnalistaTeamComercial;
	}

	/**
	 * @param urlFotoAnalistaTeamComercial the urlFotoAnalistaTeamComercial to set
	 */
	public void setUrlFotoAnalistaTeamComercial(String urlFotoAnalistaTeamComercial) {
		UrlFotoAnalistaTeamComercial = urlFotoAnalistaTeamComercial;
	}

	/**
	 * @return the idReversionPadre
	 */
	public String getIdReversionPadre() {
		return idReversionPadre;
	}

	/**
	 * @param idReversionPadre the idReversionPadre to set
	 */
	public void setIdReversionPadre(String idReversionPadre) {
		this.idReversionPadre = idReversionPadre;
	}

	/**
	 * @return the idOperacionReversion
	 */
	public String getIdOperacionReversion() {
		return idOperacionReversion;
	}

	/**
	 * @param idOperacionReversion the idOperacionReversion to set
	 */
	public void setIdOperacionReversion(String idOperacionReversion) {
		this.idOperacionReversion = idOperacionReversion;
	}
	

	/**
	 * @return the estadoReversion
	 */
	public Estado getEstadoReversion() {
		return estadoReversion;
	}

	/**
	 * @param estadoReversion the estadoReversion to set
	 */
	public void setEstadoReversion(Estado estadoReversion) {
		this.estadoReversion = estadoReversion;
	}

	/**
	 * @return the estadoReversionDescripcion
	 */
	public String getEstadoReversionDescripcion() {
		return estadoReversionDescripcion;
	}

	/**
	 * @param estadoReversionDescripcion the estadoReversionDescripcion to set
	 */
	public void setEstadoReversionDescripcion(String estadoReversionDescripcion) {
		this.estadoReversionDescripcion = estadoReversionDescripcion;
	}

	/**
	 * @return the desapropiacionHabilitada
	 */
	public boolean isDesapropiacionHabilitada() {
		return desapropiacionHabilitada;
	}

	/**
	 * @param desapropiacionHabilitada the desapropiacionHabilitada to set
	 */
	public void setDesapropiacionHabilitada(boolean desapropiacionHabilitada) {
		this.desapropiacionHabilitada = desapropiacionHabilitada;
	}

	/**
	 * @return the idOperacionMasiva
	 */
	public String getIdOperacionMasiva() {
		return idOperacionMasiva;
	}

	/**
	 * @param idOperacionMasiva the idOperacionMasiva to set
	 */
	public void setIdOperacionMasiva(String idOperacionMasiva) {
		this.idOperacionMasiva = idOperacionMasiva;
	}

	/**
	 * @return the nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * @param nombreArchivo the nombreArchivo to set
	 */
	public void setNombreArchivo(String nombreArchivoOpMas) {
		this.nombreArchivo = nombreArchivoOpMas;
	}

	public boolean getTieneDuc() {
		return tieneDuc;
	}

	public void setTieneDuc(boolean tieneDuc) {
		this.tieneDuc = tieneDuc;
	}

	/**
	 * @return the origenDescobro
	 */
	public SistemaEnum getOrigenDescobro() {
		return origenDescobro;
	}

	/**
	 * @param origenDescobro the origenDescobro to set
	 */
	public void setOrigenDescobro(SistemaEnum origenDescobro) {
		this.origenDescobro = origenDescobro;
	}

	/**
	 * @return the idMedioPago
	 */
	public String getIdMedioPago() {
		return idMedioPago;
	}

	/**
	 * @param idMedioPago the idMedioPago to set
	 */
	public void setIdMedioPago(String idMedioPago) {
		this.idMedioPago = idMedioPago;
	}

	/**
	 * @return the debitoAProx
	 */
	public boolean isDebitoAProx() {
		return debitoAProx;
	}

	/**
	 * @param debitoAProx the debitoAProx to set
	 */
	public void setDebitoAProx(boolean debitoAProx) {
		this.debitoAProx = debitoAProx;
	}

	public String getMonedaOperacion() {
		return monedaOperacion;
	}

	public void setMonedaOperacion(String monedaOperacion) {
		this.monedaOperacion = monedaOperacion;
	}

	/**
	 * @return the monedaDocumento
	 */
	public String getMonedaDocumento() {
		return monedaDocumento;
	}

	/**
	 * @param monedaDocumento the monedaDocumento to set
	 */
	public void setMonedaDocumento(String monedaDocumento) {
		this.monedaDocumento = monedaDocumento;
	}

	/**
	 * @return the esTratamientoDiferencia
	 */
	public boolean isEsTratamientoDiferencia() {
		return esTratamientoDiferencia;
	}

	/**
	 * @param esTratamientoDiferencia the esTratamientoDiferencia to set
	 */
	public void setEsTratamientoDiferencia(boolean esTratamientoDiferencia) {
		this.esTratamientoDiferencia = esTratamientoDiferencia;
	}

	/**
	 * @return the fechaTipoCambio
	 */
	public String getFechaTipoCambio() {
		return fechaTipoCambio;
	}

	/**
	 * @param fechaTipoCambio the fechaTipoCambio to set
	 */
	public void setFechaTipoCambio(String fechaTipoCambio) {
		this.fechaTipoCambio = fechaTipoCambio;
	}

	/**
	 * @return the tipoCambio
	 */
	public String getTipoCambio() {
		return tipoCambio;
	}

	/**
	 * @param tipoCambio the tipoCambio to set
	 */
	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	/**
	 * @return the fechaAprobacionOperacionesEspeciales
	 */
	public String getFechaAprobacionOperacionesEspecialesFormatoJson() {
		if (Validaciones.isNullEmptyOrDash(fechaAprobacionOperacionesEspecialesFormatoJson) && !Validaciones.isObjectNull(fechaAprobacionOperacionesEspeciales)){
			return Utilidad.formatDateAndTimeFullFromDataBase(fechaAprobacionOperacionesEspeciales.toString());
		}
		return fechaAprobacionOperacionesEspecialesFormatoJson;
	}

	/**
	 * @param fechaAprobacionOperacionesEspeciales the fechaAprobacionOperacionesEspeciales to set
	 */
	public void setFechaAprobacionOperacionesEspecialesFormatoJson(
			String fechaAprobacionOperacionesEspecialesFormatoJson) {
		this.fechaAprobacionOperacionesEspecialesFormatoJson = fechaAprobacionOperacionesEspecialesFormatoJson;
	}

	/**
	 * @return the fechaAprobacionOperacionesEspeciales
	 */
	public Date getFechaAprobacionOperacionesEspeciales() {
		return fechaAprobacionOperacionesEspeciales;
	}

	/**
	 * @param fechaAprobacionOperacionesEspeciales the fechaAprobacionOperacionesEspeciales to set
	 */
	public void setFechaAprobacionOperacionesEspeciales(
			Date fechaAprobacionOperacionesEspeciales) {
		this.fechaAprobacionOperacionesEspeciales = fechaAprobacionOperacionesEspeciales;
	}

	/**
	 * @return the observacionCap
	 */
	public String getObservacionCap() {
		return observacionCap;
	}

	/**
	 * @param observacionCap the observacionCap to set
	 */
	public void setObservacionCap(String observacionCap) {
		this.observacionCap = observacionCap;
	}

	public void setNombreApellidoUsuarioAprobacionSupervisorOperacionesEspeciales(String nombreApellidoUsuarioAprobacionSupervisorOperacionesEspeciales) {
		this.nombreApellidoUsuarioAprobacionSupervisorOperacionesEspeciales = nombreApellidoUsuarioAprobacionSupervisorOperacionesEspeciales;
		
	}

	public String getUsuarioAprobacionSupervisorOperacionesEspeciales() {
		return usuarioAprobacionSupervisorOperacionesEspeciales;
	}

	public void setUsuarioAprobacionSupervisorOperacionesEspeciales(String usuarioAprobacionSupervisorOperacionesEspeciales) {
		this.usuarioAprobacionSupervisorOperacionesEspeciales = usuarioAprobacionSupervisorOperacionesEspeciales;
	}

	public String getNombreApellidoUsuarioAprobacionSupervisorOperacionesEspeciales() {
		return nombreApellidoUsuarioAprobacionSupervisorOperacionesEspeciales;
	}

	/**
	 * @return the idContradocumentoCap
	 */
	public String getIdContradocumentoCap() {
		return idContradocumentoCap;
	}

	/**
	 * @param idContradocumentoCap the idContradocumentoCap to set
	 */
	public void setIdContradocumentoCap(String idContradocumentoCap) {
		this.idContradocumentoCap = idContradocumentoCap;
	}

	/**
	 * @return the idTipoMedioPago
	 */
	public String getIdTipoMedioPago() {
		return idTipoMedioPago;
	}

	/**
	 * @param idTipoMedioPago the idTipoMedioPago to set
	 */
	public void setIdTipoMedioPago(String idTipoMedioPago) {
		this.idTipoMedioPago = idTipoMedioPago;
	}
	
	/**
	 * @return the controlPaginacion
	 */
	public CreditosControlPaginacion getControlPaginacion() {
		return controlPaginacion;
	}

	/**
	 * @param controlPaginacion the controlPaginacion to set
	 */
	public void setControlPaginacion(CreditosControlPaginacion controlPaginacion) {
		this.controlPaginacion = controlPaginacion;
	}

	public Date getFechaRechazoAplicacionManual() {
		return fechaRechazoAplicacionManual;
	}

	public void setFechaRechazoAplicacionManual(Date fechaRechazoAplicacionManual) {
		this.fechaRechazoAplicacionManual = fechaRechazoAplicacionManual;
	}

	public String getUsuarioRechazoAplicacionManual() {
		return usuarioRechazoAplicacionManual;
	}

	public void setUsuarioRechazoAplicacionManual(
			String usuarioRechazoAplicacionManual) {
		this.usuarioRechazoAplicacionManual = usuarioRechazoAplicacionManual;
	}

	public Set<ComprobanteDto> getListaComprobanteAplicacionManual() {
		return listaComprobanteAplicacionManual;
	}

	public void setListaComprobanteAplicacionManual(
			Set<ComprobanteDto> listaComprobanteAplicacionManual) {
		this.listaComprobanteAplicacionManual = listaComprobanteAplicacionManual;
	}
	
	public ArrayList<String> getListaIdsAdjuntosOtrosDebitos() {
		return listaIdsAdjuntosOtrosDebitos;
	}

	public void setListaIdsAdjuntosOtrosDebitos(
			ArrayList<String> listaComprobanteOtrosDebito) {
		this.listaIdsAdjuntosOtrosDebitos = listaComprobanteOtrosDebito;
	}

	public String getTipoCobro() {
		return tipoCobro;
	}


	public void setTipoCobro(String tipoCobro) {
		this.tipoCobro = tipoCobro;
	}


	public Set<CodigoOperacionExternaDto> getListaCodigoOperacionesExternas() {
		return listaCodigoOperacionesExternas;
	}


	public void setListaCodigoOperacionesExternas(
			Set<CodigoOperacionExternaDto> listaCodigoOperacionesExternas) {
		this.listaCodigoOperacionesExternas = listaCodigoOperacionesExternas;
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
	public String getCuentaContable() {
		return cuentaContable;
	}
	public void setCuentaContable(String cuentaContable) {
		this.cuentaContable = cuentaContable;
	}


	public String getNombreApellidoReferenteCaja() {
		return nombreApellidoReferenteCaja;
	}


	public void setNombreApellidoReferenteCaja(String nombreApellidoReferenteCaja) {
		this.nombreApellidoReferenteCaja = nombreApellidoReferenteCaja;
	}


	public String getIdReferenteCaja() {
		return idReferenteCaja;
	}


	public void setIdReferenteCaja(String idReferenteCaja) {
		this.idReferenteCaja = idReferenteCaja;
	}


	public Date getFechaReferenteCaja() {
		return fechaReferenteCaja;
	}


	public void setFechaReferenteCaja(Date fechaReferenteCaja) {
		this.fechaReferenteCaja = fechaReferenteCaja;
	}


	public String getNombreApellidoReferenteOperacionExterna() {
		return nombreApellidoReferenteOperacionExterna;
	}


	public void setNombreApellidoReferenteOperacionExterna(
			String nombreApellidoReferenteOperacionExterna) {
		this.nombreApellidoReferenteOperacionExterna = nombreApellidoReferenteOperacionExterna;
	}


	public String getIdReferenteoperacionexterna() {
		return idReferenteoperacionexterna;
	}


	public void setIdReferenteoperacionexterna(String idReferenteoperacionexterna) {
		this.idReferenteoperacionexterna = idReferenteoperacionexterna;
	}


	public Date getFechaReferenteOperacionExterna() {
		return fechaReferenteOperacionExterna;
	}


	public void setFechaReferenteOperacionExterna(
			Date fechaReferenteOperacionExterna) {
		this.fechaReferenteOperacionExterna = fechaReferenteOperacionExterna;
	}

	/**
	 * @return the importeTotal
	 */
	public BigDecimal getImporteTotal() {
		return importeTotal;
	}
	/**
	 * @param importeTotal the importeTotal to set
	 */
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}
	
	/**
	 * @return the idWorkflow
	 */
	public Integer getIdWorkflow() {
		return idWorkflow;
	}
	/**
	 * @param idWorkflow the idWorkflow to set
	 */
	public void setIdWorkflow(Integer idWorkflow) {
		this.idWorkflow = idWorkflow;
	}
	
	/**
	 * @return the borrarAdjuntoAplicacionManal
	 */
	public boolean isBorrarAdjuntoAplicacionManal() {
		return borrarAdjuntoAplicacionManal;
	}
	/**
	 * @param borrarAdjuntoAplicacionManal the borrarAdjuntoAplicacionManal to set
	 */
	public void setBorrarAdjuntoAplicacionManal(boolean borrarAdjuntoAplicacionManal) {
		this.borrarAdjuntoAplicacionManal = borrarAdjuntoAplicacionManal;
	}
	/**
	 * @return the idAdjuntoApliacionManualPrev
	 */
	public String getIdAdjuntoApliacionManualPrev() {
		return idAdjuntoApliacionManualPrev;
	}
	/**
	 * @param idAdjuntoApliacionManualPrev the idAdjuntoApliacionManualPrev to set
	 */
	public void setIdAdjuntoApliacionManualPrev(String idAdjuntoApliacionManualPrev) {
		this.idAdjuntoApliacionManualPrev = idAdjuntoApliacionManualPrev;
	}
	/**
	 * @return the fechaReferenteCajaFormatoJson
	 */
	public String getFechaReferenteCajaFormatoJson() {
		if (Validaciones.isNullEmptyOrDash(fechaReferenteCajaFormatoJson) && !Validaciones.isObjectNull(fechaReferenteCaja)){
			return Utilidad.formatDateAndTimeFullFromDataBase(fechaReferenteCaja.toString());
		}
		return fechaReferenteCajaFormatoJson;
	}
	/**
	 * @param fechaReferenteCajaFormatoJson the fechaReferenteCajaFormatoJson to set
	 */
	public void setFechaReferenteCajaFormatoJson(String fechaReferenteCajaFormatoJson) {
		this.fechaReferenteCajaFormatoJson = fechaReferenteCajaFormatoJson;
	}
	/**
	 * @return the fechaReferenteOperacionExternaFormatoJson
	 */
	public String getFechaReferenteOperacionExternaFormatoJson() {
		if (Validaciones.isNullEmptyOrDash(fechaReferenteOperacionExternaFormatoJson) && !Validaciones.isObjectNull(fechaReferenteOperacionExterna)){
			return Utilidad.formatDateAndTimeFullFromDataBase(fechaReferenteOperacionExterna.toString());
		}
		return fechaReferenteOperacionExternaFormatoJson;
	}
	/**
	 * @param fechaReferenteOperacionExternaFormatoJson the fechaReferenteOperacionExternaFormatoJson to set
	 */
	public void setFechaReferenteOperacionExternaFormatoJson(String fechaReferenteOperacionExternaFormatoJson) {
		this.fechaReferenteOperacionExternaFormatoJson = fechaReferenteOperacionExternaFormatoJson;
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
	 * @return the nombreArchivoApliManual
	 */
	/**
	 * @return the eliminarTodosAdjuntosOtrosDeb
	 */
	public boolean isEliminarTodosAdjuntosOtrosDeb() {
		return eliminarTodosAdjuntosOtrosDeb;
	}
	/**
	 * @param eliminarTodosAdjuntosOtrosDeb the eliminarTodosAdjuntosOtrosDeb to set
	 */
	public void setEliminarTodosAdjuntosOtrosDeb(boolean eliminarTodosAdjuntosOtrosDeb) {
		this.eliminarTodosAdjuntosOtrosDeb = eliminarTodosAdjuntosOtrosDeb;
	}
	public String getVengoReedicion() {
		return vengoReedicion;
	}
	public void setVengoReedicion(String vengoReedicion) {
		this.vengoReedicion = vengoReedicion;
	}
	public Long getIdTarea() {
		return idTarea;
	}
	public void setIdTarea(Long idTarea) {
		this.idTarea = idTarea;
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
	/**
	 * @return the formAdjunto
	 */
	public Long getFormAdjunto() {
		return formAdjunto;
	}
	/**
	 * @param formAdjunto the formAdjunto to set
	 */
	public void setFormAdjunto(Long formAdjunto) {
		this.formAdjunto = formAdjunto;
	}
	public Set<ComprobanteDto> getListaComprobanteOtrosDebito() {
		return listaComprobanteOtrosDebito;
	}
	public void setListaComprobanteOtrosDebito(
			Set<ComprobanteDto> listaComprobanteOtrosDebito) {
		this.listaComprobanteOtrosDebito = listaComprobanteOtrosDebito;
	}
	
}