package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import ar.com.telecom.shiva.base.comparador.ComparatorOrdenModificacionShvCobTransaccionInvertido;
import ar.com.telecom.shiva.base.enumeradores.EstadoFacturaMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoIdReversionPadreEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoDescobro;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;

@Entity
@Table(name = "SHV_COB_DESCOBRO")
public class ShvCobDescobro extends Modelo{
 	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_COB_DESCOBRO")
    @SequenceGenerator(name="SEQ_SHV_COB_DESCOBRO", sequenceName="SEQ_SHV_COB_DESCOBRO", allocationSize = 1)
	@Column(name="ID_DESCOBRO")	
	private Long idDescobro;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_WORKFLOW", referencedColumnName="ID_WORKFLOW")
	private ShvWfWorkflow workflow;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumns({@JoinColumn(name="ID_OPERACION", referencedColumnName="ID_OPERACION")}) 	
	private ShvCobOperacion operacion;

	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_EMPRESA") 
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamEmpresa empresa;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_SEGMENTO")
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamSegmento segmento;
	
	@Column(name="ID_ANALISTA")
	private String idAnalista;

	@Column(name="ANALISTA")
	private String nombreApellidoAnalista;

	@Column(name="ID_COPROPIETARIO")
	private String idCopropietario;
	
	@Column(name="COPROPIETARIO")
	private String nombreApellidoCopropietario;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_MOTIVO_DESCOBRO")
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamMotivoDescobro motivo;
	
	@Column(name="FECHA_ALTA")
	private Date fechaAlta;

	@Column(name="USUARIO_ALTA")
	private String usuarioAlta;
	
	@Column(name="FECHA_DERIVACION")
	private Date fechaDerivacion;

	@Column(name="USUARIO_DERIVACION")
	private String usuarioDerivacion;
	
	@Column(name="FECHA_REVERSION")
	private Date fechaReversion;

	@Column(name="USUARIO_REVERSION")
	private String usuarioReversion;
	
	@Column(name="FECHA_ULTIMA_MODIFICACION")
	private Date fechaUltimaModificacion;

	@Column(name="USUARIO_ULTIMA_MODIFICACION")
	private String usuarioUltimaModificacion;
	
	@Column(name="ID_DESCOBRO_PADRE")
	private Long idDescobroPadre;
	
	@Column(name="ID_COBRO")
	private Long idCobro;

	@Column(name="ID_LEGAJO_CHEQUE_RECHAZADO")
	private Long idLegajo;
		
	@Column(name="OBSERVACION")
	private String observacion;
	
	@Column(name="IMPORTE_TOTAL")	
	private BigDecimal importeTotal;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_ID_REVERSION_PADRE")
	private TipoIdReversionPadreEnum tipoIdReversionPadreEnum;

	@Enumerated(EnumType.STRING)
	@Column(name="ORIGEN_DESCOBRO")
	private SistemaEnum origenDescobro;

	@Column(name="FECH_APROB_APLI_MAN_REF_OP_EXT")
	private Date fechaAprobAplicacionManualRefOperacionesExternas;

	@Column(name="USER_APROB_APLI_MAN_REF_OP_EXT")
	private String usuarioAprobAplicacionManualRefOperacionesExternas;

	@Column(name="NAME_APROB_APLI_MAN_REF_OP_EXT")
	private String nombreApellidoAprobAplicacionManualRefOperacionesExternas;

	@Column(name="FECH_APROB_APLI_MAN_REF_CAJ")
	private Date fechaConfirmacionAplicacionManualRefCaja;

	@Column(name="USER_APROB_APLI_MAN_REF_CAJ")
	private String usuarioAprobAplicacionManualRefCaja;

	@Column(name="NAME_APROB_APLI_MAN_REF_CAJ")
	private String nombreApellidoAprobAplicacionManualRefCaja;
	
	@OneToMany(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER, mappedBy = "descobro", orphanRemoval = true)
	@OrderBy("idOperacionRelacionada")
	private Set<ShvCobDescobroOperacionRelacionada> operacionesRelacionadas = new HashSet<ShvCobDescobroOperacionRelacionada>(0);
	
	@OneToMany(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER, mappedBy = "descobro", orphanRemoval = true)
	@OrderBy("idDocumentoRelacionado")
	private Set<ShvCobDescobroDocumentoRelacionado> documentosRelacionados = new HashSet<ShvCobDescobroDocumentoRelacionado>(0);
	
	@Enumerated(EnumType.STRING)
	@Column(name="MONEDA_OPERACION")
	private MonedaEnum  monedaOperacion;
	
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "descobro")
	private Set<ShvCobDescobroCodigoOperacionExterna> codigosOperacionesExternas = new HashSet<ShvCobDescobroCodigoOperacionExterna>(0);

	
	/**
	 * Retorna la transaccion que coincida con el numeroTransaccion de la lista de transacciones propias del cobro.
	 * @param idTransaccion
	 * @return
	 */
	public ShvCobTransaccion getTransaccionPorNumeroTransaccion(Integer numeroTransaccion){
		for (ShvCobTransaccion transaccion : getOperacion().getTransacciones()) {
			if(transaccion.getNumeroTransaccion().equals(numeroTransaccion)){
				return transaccion;
			}
		}
		return null;
	}
 
	/**
	 * Retorna una lista ordenada de las transacciones, por id de transaccion.
	 * @param cobro
	 * @return
	 */
	public List<ShvCobTransaccion> getTransaccionesOrdenadas() {
		List<ShvCobTransaccion> listaAux = new ArrayList<ShvCobTransaccion>();
		for(ShvCobTransaccion tran : getOperacion().getTransacciones()){
			listaAux.add(tran);
		}
		Collections.sort(listaAux, new Comparator<ShvCobTransaccion>(){
                     public int compare(ShvCobTransaccion t1,ShvCobTransaccion t2){
                    	 return t1.getNumeroTransaccion()-t2.getNumeroTransaccion();
                     }});
		return listaAux;
	}

	
	/**
	 * Retorna transaccion en error
	 * @param cobro
	 * @return
	 */
	public ShvCobTransaccion getTransaccionEnError() {
		
		for(ShvCobTransaccion tran : getOperacion().getTransacciones()){
			if(EstadoTransaccionEnum.getEnumByTipoEstado("ERROR_DESCOBRO").contains(tran.getEstadoProcesamiento())) {
				return tran;
			}
		}
		return null;
	}

	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * Retorna una lista ordenada de las transacciones de manera inversa, osea la ultima transaccion aparece primero, por id de transaccion.
	 * @return List<ShvCobTransaccion>
	 */
	public List<ShvCobTransaccion> getTransaccionesOrdenadasInversa() {
		
		List<ShvCobTransaccion> listaAux = new ArrayList<ShvCobTransaccion>();
	
		listaAux.addAll(getOperacion().getTransacciones());
		
		Collections.sort(listaAux, new ComparatorOrdenModificacionShvCobTransaccionInvertido());
		
		return listaAux;
	}
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * Retorna una lista ordenada de las transacciones de manera inversa, osea la ultima transaccion aparece primero, por id de transaccion.
	 * Ademas descarta aquellas transacciones que ya descobraron en los cobradores y les falta unicamente descobrar un medio de pago shiva, usuario o tratamiento
	 * a la diferencia de tipo reintegro, como envio a ganancias, etc.
	 * Tampoco trae las transacciones con diferencia de cambio
	 * @return List<ShvCobTransaccion>
	 */
	public List<ShvCobTransaccion> getTransaccionesOrdenadasInversaSinMedioPagoShivaUsuarioTratamientoDiferencia() {
		
		List<ShvCobTransaccion> listaAux = new ArrayList<ShvCobTransaccion>();
		
		for(ShvCobTransaccion transaccion : getOperacion().getTransacciones()){
			
			Integer pendienteDescobroCobrador = 0;
			Integer pendienteDescobroNoCobrador = 0;
			boolean transaccionPendienteDescobro = false;
			
			if(EstadoTransaccionEnum.listarEstadosDescobroNoDescobrado().contains(transaccion.getEstadoProcesamiento())
					&& !Utilidad.esDiferenciaCambio(transaccion)){
				transaccionPendienteDescobro = true;
			}
			
			if (EstadoTransaccionEnum.listarEstadosDescobroNoDescobrado().contains(transaccion.getEstadoProcesamiento())
					&& !Utilidad.esDiferenciaCambio(transaccion)) {
				
				if (!Validaciones.isObjectNull(transaccion.getFactura())
						&& !EstadoFacturaMedioPagoEnum.DESCOBRO.equals(transaccion.getFactura().getEstado())){
					pendienteDescobroCobrador++;
				}
				
				if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())){
					if((!TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())
							&& !TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())
							)
							&& !EstadoFacturaMedioPagoEnum.DESCOBRO.equals(transaccion.getTratamientoDiferencia().getEstado())){
						pendienteDescobroNoCobrador++;
					}
				}
			
			
				if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())){
					if((TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())
							|| TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())
							)
							&& !EstadoFacturaMedioPagoEnum.DESCOBRO.equals(transaccion.getTratamientoDiferencia().getEstado())){
						pendienteDescobroCobrador++;
					}
				}
				
				for (ShvCobMedioPago medioPago : transaccion.getMediosPago()) {
					if ((
						!(medioPago instanceof ShvCobMedioPagoUsuario)
						&& !(medioPago instanceof ShvCobMedioPagoShiva)
						)
							&& !EstadoFacturaMedioPagoEnum.DESCOBRO.equals(medioPago.getEstado())){
						pendienteDescobroCobrador++;
					}
					if ((medioPago instanceof ShvCobMedioPagoUsuario || medioPago instanceof ShvCobMedioPagoShiva)
								&& !EstadoFacturaMedioPagoEnum.DESCOBRO.equals(medioPago.getEstado())){
						pendienteDescobroNoCobrador++;
					}
				}
			}
			
			if((transaccionPendienteDescobro && pendienteDescobroNoCobrador == 0) || pendienteDescobroCobrador > 0){
				listaAux.add(transaccion);
			}
		}
		
		Collections.sort(listaAux, new ComparatorOrdenModificacionShvCobTransaccionInvertido());
		
		return listaAux;
	}
	
	public List<ShvCobTransaccion> getTransaccionesOrdenadasInversaSoloMedioPagoShivaUsuarioTratamientoDiferencia() {
		
		List<ShvCobTransaccion> listaAux = new ArrayList<ShvCobTransaccion>();
		
		for(ShvCobTransaccion transaccion : getOperacion().getTransacciones()){
			
			Integer pendienteDescobroNoCobrador = 0;
			
			if (EstadoTransaccionEnum.listarEstadosDescobroNoDescobrado().contains(transaccion.getEstadoProcesamiento())) {
			
				if (!Validaciones.isObjectNull(transaccion.getTratamientoDiferencia())){
					if((!TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_CLP.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())
							&& !TipoTratamientoDiferenciaEnum.REINTEGRO_CRED_PROX_FAC_MIC.equals(transaccion.getTratamientoDiferencia().getTipoTratamientoDiferencia())
							)
							&& !EstadoFacturaMedioPagoEnum.DESCOBRO.equals(transaccion.getTratamientoDiferencia().getEstado())){
						pendienteDescobroNoCobrador++;
					}
				}
				
				for (ShvCobMedioPago medioPago : transaccion.getMediosPago()) {
					if (
						(medioPago instanceof ShvCobMedioPagoUsuario || medioPago instanceof ShvCobMedioPagoShiva)
							&& !EstadoFacturaMedioPagoEnum.DESCOBRO.equals(medioPago.getEstado())){
						pendienteDescobroNoCobrador++;
					}
				}
			}
			
			if(pendienteDescobroNoCobrador > 0){
				listaAux.add(transaccion);
			}
		}
		
		Collections.sort(listaAux, new ComparatorOrdenModificacionShvCobTransaccionInvertido());
		
		return listaAux;
	}
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * Antes que nada, se descartan las transacciones de diferencia de cambio y 
	 * despues retorna una lista ordenada de las transacciones de manera inversa, osea la ultima transaccion aparece primero, por id de transaccion.
	 * @return List<ShvCobTransaccion>
	 */
	public List<ShvCobTransaccion> getTransaccionesOrdenadasInversaSinDiferenciaCambio() {
		List<ShvCobTransaccion> listaAux = new ArrayList<ShvCobTransaccion>();
		
		for(ShvCobTransaccion transaccion : getOperacion().getTransacciones()){
			if(!Utilidad.esDiferenciaCambio(transaccion)){
				listaAux.add(transaccion);
			}				
		}		
		
		Collections.sort(listaAux, new ComparatorOrdenModificacionShvCobTransaccionInvertido());
		
		return listaAux;
	}
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * Antes que nada, se descartan las transacciones de diferencia de cambio y que tengan error causada por el cobro, 
	 * despues retorna una lista ordenada de las transacciones de manera inversa, osea la ultima transaccion aparece primero, por id de transaccion.
	 * @return List<ShvCobTransaccion>
	 */
	public List<ShvCobTransaccion> getTransaccionesOKOrdenadasInversaSinDiferenciaCambio() {
		List<ShvCobTransaccion> listaAux = new ArrayList<ShvCobTransaccion>();
		
		for(ShvCobTransaccion transaccion : getOperacion().getTransacciones()){
			if(!Utilidad.esDiferenciaCambio(transaccion) &&
				!EstadoTransaccionEnum.listarEstadosErrorParaDescobro().contains(transaccion.getEstadoProcesamiento())){
				listaAux.add(transaccion);
			}				
		}		
		
		Collections.sort(listaAux, new ComparatorOrdenModificacionShvCobTransaccionInvertido());
		
		return listaAux;
	}
	
	/**
	 * Retorna si hay algun cobro en estado Descobrada.
	 * @param cobro
	 * @return
	 */
	public boolean hayAlgunaTransaccionDescobrada() {
		for(ShvCobTransaccion tran : this.getTransaccionesOrdenadasInversa()){
			if (!EstadoTransaccionEnum.PENDIENTE_DESCOBRO.equals(tran.getEstadoProcesamiento())) {
				return true; 
			}
		}
		return false;
	}
	
	/**
	 * Retorna la transaccion que coincida con el idTransaccion de la lista de transacciones propias del cobro.
	 * @param idTransaccion
	 * @return
	 */
	public ShvCobTransaccion getTransaccionPorIdTransaccion(Integer idTransaccion){
		for (ShvCobTransaccion transaccion : getOperacion().getTransacciones()) {
			if(transaccion.getIdTransaccion().equals(idTransaccion)){
				return transaccion;
			}
		}
		return null;
	}
	
	/**
	 * Retorna el medio de pago que coincida con el idMedioPago de los medios de pago propios del cobro.
	 * @param idTransaccion
	 * @return
	 */
	public ShvCobMedioPago getMedioPagoPorIdMedioPago(Integer idMedioPago){
		for (ShvCobTransaccion transaccion : getOperacion().getTransacciones()) {
			for(ShvCobMedioPago medioPago : transaccion.getShvCobMedioPago()){
				if(medioPago.getIdMedioPago().equals(idMedioPago)){
					return medioPago;
				}
			}
		}
		return null;
	}
	
	/*****************************************************************************************
	 * Setters & Getters
	 */
	
	public Long getIdDescobro() {
		return idDescobro;
	}

	public void setIdDescobro(Long idDescobro) {
		this.idDescobro = idDescobro;
	}

	public ShvWfWorkflow getWorkflow() {
		return workflow;
	}

	public void setWorkflow(ShvWfWorkflow workflow) {
		this.workflow = workflow;
	}

	public ShvCobOperacion getOperacion() {
		return operacion;
	}

	public void setOperacion(ShvCobOperacion operacion) {
		this.operacion = operacion;
	}
	public ShvParamEmpresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(ShvParamEmpresa empresa) {
		this.empresa = empresa;
	}

	public ShvParamSegmento getSegmento() {
		return segmento;
	}

	public void setSegmento(ShvParamSegmento segmento) {
		this.segmento = segmento;
	}

	public String getIdAnalista() {
		return idAnalista;
	}

	public void setIdAnalista(String idAnalista) {
		this.idAnalista = idAnalista;
	}

	public String getNombreApellidoAnalista() {
		return nombreApellidoAnalista;
	}

	public void setNombreApellidoAnalista(String nombreApellidoAnalista) {
		this.nombreApellidoAnalista = nombreApellidoAnalista;
	}

	public String getIdCopropietario() {
		return idCopropietario;
	}

	public void setIdCopropietario(String idCopropietario) {
		this.idCopropietario = idCopropietario;
	}

	public String getNombreApellidoCopropietario() {
		return nombreApellidoCopropietario;
	}

	public void setNombreApellidoCopropietario(String nombreApellidoCopropietario) {
		this.nombreApellidoCopropietario = nombreApellidoCopropietario;
	}

	public ShvParamMotivoDescobro getMotivo() {
		return motivo;
	}

	public void setMotivo(ShvParamMotivoDescobro shvParamMotivoDescobro) {
		this.motivo = shvParamMotivoDescobro;
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

	public String getUsuarioDerivacion() {
		return usuarioDerivacion;
	}

	public void setUsuarioDerivacion(String usuarioDerivacion) {
		this.usuarioDerivacion = usuarioDerivacion;
	}

	public Date getFechaReversion() {
		return fechaReversion;
	}

	public void setFechaReversion(Date fechaReversion) {
		this.fechaReversion = fechaReversion;
	}

	public String getUsuarioReversion() {
		return usuarioReversion;
	}

	public void setUsuarioReversion(String usuarioReversion) {
		this.usuarioReversion = usuarioReversion;
	}

	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public String getUsuarioUltimaModificacion() {
		return usuarioUltimaModificacion;
	}

	public void setUsuarioUltimaModificacion(String usuarioUltimaModificacion) {
		this.usuarioUltimaModificacion = usuarioUltimaModificacion;
	}

	public Long getIdCobro() {
		return idCobro;
	}

	public void setIdCobro(Long idCobro) {
		this.idCobro = idCobro;
	}

	public Set<ShvCobDescobroOperacionRelacionada> getOperacionesRelacionadas() {
		return operacionesRelacionadas;
	}

	public void setOperacionesRelacionadas(
			Set<ShvCobDescobroOperacionRelacionada> operacionesRelacionadas) {
		this.operacionesRelacionadas = operacionesRelacionadas;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	public BigDecimal getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	public Long getIdDescobroPadre() {
		return idDescobroPadre;
	}

	public void setIdDescobroPadre(Long idDescobroPadre) {
		this.idDescobroPadre = idDescobroPadre;
	}

	public TipoIdReversionPadreEnum getTipoIdReversionPadreEnum() {
		return tipoIdReversionPadreEnum;
	}

	public void setTipoIdReversionPadreEnum(TipoIdReversionPadreEnum tipoIdReversionPadreEnum) {
		this.tipoIdReversionPadreEnum = tipoIdReversionPadreEnum;
	}

	public Set<ShvCobDescobroDocumentoRelacionado> getDocumentosRelacionados() {
		return documentosRelacionados;
	}
	
	public Set<ShvCobDescobroDocumentoRelacionado> getDocumentosRelacionadosDeTransaccion(Integer nroTransaccion) {
		
		Set<ShvCobDescobroDocumentoRelacionado> listaDocRelacionadosFiltrada = new HashSet<>();
		if(Validaciones.isCollectionNotEmpty(documentosRelacionados)){
			for(ShvCobDescobroDocumentoRelacionado docRelacionado : documentosRelacionados){
				if(nroTransaccion.equals(docRelacionado.getNroTransaccion())){
					listaDocRelacionadosFiltrada.add(docRelacionado);
				}
			}
			return listaDocRelacionadosFiltrada;
		}		
		return null;
	}

	public void setDocumentosRelacionados(
			Set<ShvCobDescobroDocumentoRelacionado> documentosRelacionados) {
		this.documentosRelacionados = documentosRelacionados;
	}

	/**
	 * @return the origenReversion
	 */
	public SistemaEnum getOrigenDescobro() {
		return origenDescobro;
	}

	/**
	 * @param origenDescobro the origenReversion to set
	 */
	public void setOrigenDescobro(SistemaEnum origenDescobro) {
		this.origenDescobro = origenDescobro;
	}

	public MonedaEnum getMonedaOperacion() {
		return monedaOperacion;
	}

	public void setMonedaOperacion(MonedaEnum monedaOperacion) {
		this.monedaOperacion = monedaOperacion;
	}
	
	public List<ShvCobTransaccion> getTransaccionesDC(Integer numeroTransaccion) {
		
		List<ShvCobTransaccion> lista = new ArrayList<ShvCobTransaccion>();
		
		for(ShvCobTransaccion transaccion : getOperacion().getTransacciones()){

			if(!Validaciones.isObjectNull(transaccion.getNumeroTransaccionPadre())
					&& numeroTransaccion.equals(transaccion.getNumeroTransaccionPadre())
					// Levantamos registros en estado diferencia de cambio de simulacion e imputacion, así podemos probar SCARD y otros
					// temas con el cobro simulado. Lo correcto sería que solo levante diferencias de cambio de imputacion (EstadoTransaccionEnum.DIFERENCIA_DE_CAMBIO)
					// El otro estado no debiera ser necesario nunca, para el flujo normal del negocio.
					&& Utilidad.esDiferenciaCambio(transaccion)){
				
				lista.add(transaccion);
			}
		}
		
		return lista;
	}

	public Long getIdLegajo() {
		return idLegajo;
	}

	public void setIdLegajo(Long idLegajo) {
		this.idLegajo = idLegajo;
	}

	/**
	 * @return the codigosOperacionesExternas
	 */
	public Set<ShvCobDescobroCodigoOperacionExterna> getCodigosOperacionesExternas() {
		return codigosOperacionesExternas;
	}

	/**
	 * @param codigosOperacionesExternas the codigosOperacionesExternas to set
	 */
	public void setCodigosOperacionesExternas(Set<ShvCobDescobroCodigoOperacionExterna> codigosOperacionesExternas) {
		this.codigosOperacionesExternas = codigosOperacionesExternas;
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
	public void setFechaAprobAplicacionManualRefOperacionesExternas(Date fechaAprobAplicacionManualRefOperacionesExternas) {
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
	public void setUsuarioAprobAplicacionManualRefOperacionesExternas(String usuarioAprobAplicacionManualRefOperacionesExternas) {
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
	public void setNombreApellidoAprobAplicacionManualRefOperacionesExternas(String nombreApellidoAprobAplicacionManualRefOperacionesExternas) {
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
	public void setFechaConfirmacionAplicacionManualRefCaja(Date fechaConfirmacionAplicacionManualRefCaja) {
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
	public void setUsuarioAprobAplicacionManualRefCaja(String usuarioAprobAplicacionManualRefCaja) {
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
	public void setNombreApellidoAprobAplicacionManualRefCaja(String nombreApellidoAprobAplicacionManualRefCaja) {
		this.nombreApellidoAprobAplicacionManualRefCaja = nombreApellidoAprobAplicacionManualRefCaja;
	}
}
