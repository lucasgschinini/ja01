package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.comparador.ComparatorOrdenShvCobMedioPago;
import ar.com.telecom.shiva.base.enumeradores.EstadoFacturaMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoImputacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;

@Entity
@Table(name = "SHV_COB_TRANSACCION")
public class ShvCobTransaccion extends Modelo {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_COB_TRANSACCION")
    @SequenceGenerator(name="SEQ_SHV_COB_TRANSACCION", sequenceName="SEQ_SHV_COB_TRANSACCION",allocationSize=1)
	@Column(name="ID_TRANSACCION")	
	private Integer idTransaccion;
	
	@Column(name="NUMERO_TRANSACCION")	
	private Integer numeroTransaccion;
	
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy="transaccion")
	private Set<ShvCobFactura> shvCobFactura = new HashSet<ShvCobFactura>(0);
	
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy="transaccion")
	private Set<ShvCobMedioPago> shvCobMedioPago = new HashSet<ShvCobMedioPago>(0);
	
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy="transaccion")
	private Set<ShvCobTratamientoDiferencia> listaTratamientosDiferencias = new HashSet<ShvCobTratamientoDiferencia>(0);
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="ID_OPERACION", referencedColumnName="ID_OPERACION") 	
	private ShvCobOperacion operacion;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO")	
	private EstadoTransaccionEnum estadoProcesamiento;
	
	@Column(name="MENSAJE_ESTADO")
	private String mensajeEstado;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_MENSAJE_ESTADO")
	private TipoMensajeEstadoEnum tipoMensajeEstado;

	@Column(name="ID_TRANSACCION_PADRE")	
	private Integer idTransaccionPadre;
	
	@Column(name="NUMERO_TRANSACCION_PADRE")	
	private Integer numeroTransaccionPadre;

	@Column(name="GRUPO")	
	private Integer grupo;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_IMPUTACION")
	private TipoImputacionEnum tipoImputacion;
	
	@Enumerated(EnumType.STRING)
	@Column(name="SISTEMA")
	private SistemaEnum sistema;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ID_SOCIEDAD")
	private SociedadEnum idSociedad;
	
	@Column(name="NUMERO_TRANSACCION_DEPENDENCIA")
	private Integer numeroTransaccionDependencia;
	
	@Enumerated(EnumType.STRING)
	@Column(name="SISTEMA_DEPENDENCIA")
	private SistemaEnum sistemaDependencia;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ID_SOCIEDAD_DEPENDENCIA")
	private SociedadEnum idSociedadDependencia;	

	@Column(name="APOCOPE")
	private String apocope;

	@Column(name="NUMERO_TRANSACCION_FICTICIO")	
	private Integer numeroTransaccionFicticio;
	
	@Column(name="NUM_TRANSACCION_PADRE_FICTICIO")	
	private Integer numeroTransaccionPadreFicticio;

	public Integer getNumeroTransaccionPadreFicticio() {
		return numeroTransaccionPadreFicticio;
	}

	public void setNumeroTransaccionPadreFicticio(
			Integer numeroTransaccionPadreFicticio) {
		this.numeroTransaccionPadreFicticio = numeroTransaccionPadreFicticio;
	}

	/**
	 * @return the numeroTransaccionFicticio
	 */
	public Integer getNumeroTransaccionFicticio() {
		return numeroTransaccionFicticio;
	}

	/**
	 * @param numeroTransaccionFicticio the numeroTransaccionFicticio to set
	 */
	public void setNumeroTransaccionFicticio(Integer numeroTransaccionFicticio) {
		this.numeroTransaccionFicticio = numeroTransaccionFicticio;
	}

	/**
	 * @return the apocope
	 */
	public String getApocope() {
		return apocope;
	}

	/**
	 * @param apocope the apocope to set
	 */
	public void setApocope(String apocope) {
		this.apocope = apocope;
	}

	public Integer getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(Integer idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	
	public Set<ShvCobFactura> getShvCobFactura() {
		return shvCobFactura;
	}

	public void setShvCobFactura(Set<ShvCobFactura> shvCobFactura) {
		this.shvCobFactura = shvCobFactura;
	}

	public ShvCobOperacion getOperacion() {
		return operacion;
	}

	public void setOperacion(ShvCobOperacion operacion) {
		this.operacion = operacion;
	}

	public Set<ShvCobMedioPago> getShvCobMedioPago() {
		return shvCobMedioPago;
	}

	public void setShvCobMedioPago(Set<ShvCobMedioPago> shvCobMedioPago) {
		this.shvCobMedioPago = shvCobMedioPago;
	}

	public EstadoTransaccionEnum getEstadoProcesamiento() {
		return estadoProcesamiento;
	}

	public void setEstadoProcesamiento(EstadoTransaccionEnum estadoProcesamiento) {
		this.estadoProcesamiento = estadoProcesamiento;
	}

	public Integer getNumeroTransaccion() {
		return numeroTransaccion;
	}

	public void setNumeroTransaccion(Integer numeroTransaccion) {
		this.numeroTransaccion = numeroTransaccion;
	}

	/**
	 * Retorna el Numero de Transaccion con formato 0000000.00000
	 * @return
	 */
	public String getOperacionTransaccionFormateado() {
	
		String operacionTransaccionFormateado = Utilidad.rellenarCerosIzquierda(String.valueOf(getOperacion().getIdOperacion()), 7) + "." +
			Utilidad.rellenarCerosIzquierda(String.valueOf(getNumeroTransaccion()), 5);
		
		return operacionTransaccionFormateado;
	}
	
	/**
	 * Retorna el Numero de Transaccion Ficticio con formato 0000000.00000
	 * @return
	 */
	public String getOperacionTransaccionFicticioFormateado() {
	
		String operacionTransaccionFormateado = Utilidad.rellenarCerosIzquierda(String.valueOf(getOperacion().getIdOperacion()), 7) + "." +
			Utilidad.rellenarCerosIzquierda(String.valueOf(getNumeroTransaccionFicticio()), 5);
		
		return operacionTransaccionFormateado;
	}
	
	/**
	 * Retorna el Numero de Transaccion Ficticio con formato 0000000.00000
	 * @return
	 */
	public String getOperacionTransaccionFormateadoDescobro() {
	
		String operacionTransaccionFormateado = Utilidad.rellenarCerosIzquierda(String.valueOf(getOperacion().getIdOperacionOriginal()), 7) + "." +
			Utilidad.rellenarCerosIzquierda(String.valueOf(getNumeroTransaccion()), 5);
		
		return operacionTransaccionFormateado;
	}
	
	/**
	 * Retorna el Numero de Transaccion Ficticio con formato 0000000.00000
	 * @return
	 */
	public String getOperacionTransaccionFicticioFormateadoDescobro() {
	
		String operacionTransaccionFormateado = Utilidad.rellenarCerosIzquierda(String.valueOf(getOperacion().getIdOperacionOriginal()), 7) + "." +
			Utilidad.rellenarCerosIzquierda(String.valueOf(getNumeroTransaccionFicticio()), 5);
		
		return operacionTransaccionFormateado;
	}
	
	/**
	 * Retorna el Numero de Transaccion con formato(Para documentos generados por diferencia de cambio, toma el numero de la transaccion padre) 0000000.00000
	 * @return
	 */
	public String getOperacionTransaccionFormateadoDC() {
	
		String operacionTransaccionFormateado = Utilidad.rellenarCerosIzquierda(String.valueOf(getOperacion().getIdOperacion()), 7) + "." +
			Utilidad.rellenarCerosIzquierda(String.valueOf(getNumeroTransaccionPadre()), 5);
		
		return operacionTransaccionFormateado;
	}
	
	/**
	 * Retorna el Numero de Transaccion Ficticio con formato(Para documentos generados por diferencia de cambio, toma el numero de la transaccion padre) 0000000.00000
	 * @return
	 */
	public String getOperacionTransaccionFicticioFormateadoDC() {
	
		String operacionTransaccionFormateado = Utilidad.rellenarCerosIzquierda(String.valueOf(getOperacion().getIdOperacion()), 7) + "." +
			Utilidad.rellenarCerosIzquierda(String.valueOf(getNumeroTransaccionPadreFicticio()), 5);
		
		return operacionTransaccionFormateado;
	}
	
	/**
	 * Retorna el Numero de Transaccion con formato(Para documentos generados por diferencia de cambio, toma el numero de la transaccion padre) 0000000.00000
	 * en Descobros
	 * @return
	 */
	public String getOperacionTransaccionFormateadoDCDescobros() {
	
		String operacionTransaccionFormateado = Utilidad.rellenarCerosIzquierda(String.valueOf(getOperacion().getIdOperacionOriginal()), 7) + "." +
			Utilidad.rellenarCerosIzquierda(String.valueOf(getNumeroTransaccionPadre()), 5);
		
		return operacionTransaccionFormateado;
	}
	
	/**
	 * Retorna el Numero de Transaccion Ficticio con formato(Para documentos generados por diferencia de cambio, toma el numero de la transaccion padre) 0000000.00000
	 * en Descobros
	 * @return
	 */
	public String getOperacionTransaccionFicticioFormateadoDCDescobros() {
	
		String operacionTransaccionFormateado = Utilidad.rellenarCerosIzquierda(String.valueOf(getOperacion().getIdOperacionOriginal()), 7) + "." +
			Utilidad.rellenarCerosIzquierda(String.valueOf(getNumeroTransaccionPadreFicticio()), 5);
		
		return operacionTransaccionFormateado;
	}

	/**
	 * Retorna la factura. En caso de que sea de tipo Calipso, retorna la factura 
	 * que tenga GeneradoPorCobro = NO. En caso de que la factura sea de MIC, retorno la
	 * primera ya que es la unica.
	 * @return
	 */
	public ShvCobFactura getFactura(){
		for(ShvCobFactura factura : shvCobFactura){
			if(factura instanceof ShvCobFacturaCalipso){

				// siempre va a existir una sola factura con GeneradoPorCobro = NO
				if(Validaciones.isObjectNull(factura.getGeneradoPorCobro())
						|| SiNoEnum.NO.equals(((ShvCobFacturaCalipso)factura).getGeneradoPorCobro())){
					return factura;
				}
			}else{
				// si la factura es de MIC retorno la primera/unica factura
				return factura;
			}
		}
		return null;
	}
	
	/**
	 * Retorna la factura asociada a la transaccion, sin ningun tipo de filtrado
	 * 
	 * @return
	 */
	public ShvCobFactura getFacturaTransaccion() {

		for(ShvCobFactura factura : shvCobFactura) {
			return factura;
		}
		return null;
	}
	
	public ShvCobFactura getFacturaDC(){
		
		for(ShvCobFactura factura : shvCobFactura){
			if(factura instanceof ShvCobFacturaCalipso){
				if (EstadoFacturaMedioPagoEnum.NUEVO_POR_DIFERENCIA_DE_CAMBIO.equals(factura.getEstado())){
					return factura;
				}
			}
		}
		return null;
	}
	
	public Set<ShvCobFactura> getListaFacturasDiferenciaCambio(){
	
		Set<ShvCobFactura> facturasDc = new HashSet<ShvCobFactura>();
	
		for(ShvCobFactura factura : shvCobFactura){
			if (Utilidad.esDiferenciaCambio(factura)){
				facturasDc.add(factura);
			}
		}
		return facturasDc;
	}
	
	/**
	 * Retorna el tratamiento de diferencia.
	 * @return
	 */
	public ShvCobTratamientoDiferencia getTratamientoDiferencia() {
		Iterator<ShvCobTratamientoDiferencia> iterator = listaTratamientosDiferencias.iterator();
		if (iterator.hasNext()) {
			return iterator.next();
		}
		return null;
	}
	
	/**
	 * Retorna los medios de pago. En caso de que sea de tipo CTA, retorna el medio de pago 
	 * que tenga GeneradoPorCobro = NO.
	 * @return
	 */
	public Set<ShvCobMedioPago> getMediosPago(){
		Set<ShvCobMedioPago> listaMediosPago = new HashSet<ShvCobMedioPago>();
		for(ShvCobMedioPago medioPago : shvCobMedioPago){
			
			// Solo se agregan nuevos medios de pago Cta en la apropiacion a calipso
			if(medioPago instanceof ShvCobMedioPagoCTA){
				// si GeneradoPorCobro = NO lo agrego a la lista, sino no
				if(((ShvCobMedioPagoCTA)medioPago).getGeneradoPorCobro().equals(SiNoEnum.NO)){
					listaMediosPago.add(medioPago);
				}
			}else{
				// si  es de tipo MIC, usuario o Shiva lo agrego
				listaMediosPago.add(medioPago);
			}
		}
		return listaMediosPago;
	}
	
	public Set<ShvCobMedioPago> getListaMediosPagoDiferenciaCambio(){
		Set<ShvCobMedioPago> listaMediosPago = new HashSet<ShvCobMedioPago>();
		for(ShvCobMedioPago medioPago : shvCobMedioPago){
			
			if(Utilidad.esDiferenciaCambio(medioPago)){
				listaMediosPago.add(medioPago);
			}
		}
		return listaMediosPago;
	}
	
	public Set<ShvCobMedioPago> getMediosPagoShivaUsuario(){
		Set<ShvCobMedioPago> listaMediosPago = new HashSet<ShvCobMedioPago>();
		for(ShvCobMedioPago medioPago : shvCobMedioPago){
			
			// Solo se agregan nuevos medios de pago Cta en la apropiacion a calipso
			if(medioPago instanceof ShvCobMedioPagoCTA){
				// si GeneradoPorCobro = NO lo agrego a la lista, sino no
				if(((ShvCobMedioPagoCTA)medioPago).getGeneradoPorCobro().equals(SiNoEnum.NO)){
					listaMediosPago.add(medioPago);
				}
			} else if(medioPago instanceof ShvCobMedioPagoShiva
					|| medioPago instanceof ShvCobMedioPagoUsuario){
				// si  es de tipo MIC, usuario o Shiva lo agrego
				listaMediosPago.add(medioPago);
			}
		}
		return listaMediosPago;
	}
	
	public Long obtenerIdClienteLegadoDeTratamientoDiferencia() {
		Iterator<ShvCobTratamientoDiferencia> iterator = listaTratamientosDiferencias.iterator();
		if (iterator.hasNext()) {
			return iterator.next().getIdClienteLegadoAcuerdoTrasladoCargo();
		}
		return null;
	}

	public String getMensajeEstado() {
		return mensajeEstado;
	}

	public void setMensajeEstado(String mensajeEstado) {
		this.mensajeEstado = mensajeEstado;
	}

	/**
	 * @return the tratamientoDiferencia
	 */
	public Set<ShvCobTratamientoDiferencia> getListaTratamientosDiferencias() {
		return listaTratamientosDiferencias;
	}

	/**
	 * @param tratamientoDiferencia the tratamientoDiferencia to set
	 */
	public void setListaTratamientosDiferencias(Set<ShvCobTratamientoDiferencia> tratamientoDiferencia) {
		this.listaTratamientosDiferencias = tratamientoDiferencia;
	}

	/**
	 * @return the idTransaccionPadre
	 */
	public Integer getIdTransaccionPadre() {
		return idTransaccionPadre;
	}

	/**
	 * @param idTransaccionPadre the idTransaccionPadre to set
	 */
	public void setIdTransaccionPadre(Integer idTransaccionPadre) {
		this.idTransaccionPadre = idTransaccionPadre;
	}

	/**
	 * @return the idSociedad
	 */
	public SociedadEnum getIdSociedad() {
		return idSociedad;
	}

	/**
	 * @param idSociedad the idSociedad to set
	 */
	public void setIdSociedad(SociedadEnum idSociedad) {
		this.idSociedad = idSociedad;
	}

	/**
	 * @return the tipoMensajeEstado
	 */
	public TipoMensajeEstadoEnum getTipoMensajeEstado() {
		return tipoMensajeEstado;
	}

	/**
	 * @param tipoMensajeEstado the tipoMensajeEstado to set
	 */
	public void setTipoMensajeEstado(TipoMensajeEstadoEnum tipoMensajeEstado) {
		this.tipoMensajeEstado = tipoMensajeEstado;
	}
	
	/**
	 * @author u573005, fabio.giaquinta.ruiz, sprint 7
	 * @return
	 */
	public Set<ShvCobMedioPago> getMediosPagoOrdenadosPorIdMedioPago(){
		Set<ShvCobMedioPago> listaMediosPagoOrdenada = new TreeSet<ShvCobMedioPago>(new ComparatorOrdenShvCobMedioPago());
		listaMediosPagoOrdenada.addAll(getMediosPago());
		return listaMediosPagoOrdenada;
	}
	
	public Integer getNumeroTransaccionPadre() {
		return numeroTransaccionPadre;
	}

	public void setNumeroTransaccionPadre(Integer numeroTransaccionPadre) {
		this.numeroTransaccionPadre = numeroTransaccionPadre;
	}

	/**
	 * @return the grupo
	 */
	public Integer getGrupo() {
		return grupo;
	}

	/**
	 * @param grupo the grupo to set
	 */
	public void setGrupo(Integer grupo) {
		this.grupo = grupo;
	}

	/**
	 * @return the tipoImputacion
	 */
	public TipoImputacionEnum getTipoImputacion() {
		return tipoImputacion;
	}

	/**
	 * @param tipoImputacion the tipoImputacion to set
	 */
	public void setTipoImputacion(TipoImputacionEnum tipoImputacion) {
		this.tipoImputacion = tipoImputacion;
	}

	/**
	 * @return the sistema
	 */
	public SistemaEnum getSistema() {
		return sistema;
	}

	/**
	 * @param sistema the sistema to set
	 */
	public void setSistema(SistemaEnum sistema) {
		this.sistema = sistema;
	}

	/**
	 * @return the sistemaDependencia
	 */
	public SistemaEnum getSistemaDependencia() {
		return sistemaDependencia;
	}

	/**
	 * @param sistemaDependencia the sistemaDependencia to set
	 */
	public void setSistemaDependencia(SistemaEnum sistemaDependencia) {
		this.sistemaDependencia = sistemaDependencia;
	}

	
	/**
	 * @return the idSociedadDependencia
	 */
	public SociedadEnum getIdSociedadDependencia() {
		return idSociedadDependencia;
	}

	/**
	 * @param idSociedadDependencia the idSociedadDependencia to set
	 */
	public void setIdSociedadDependencia(SociedadEnum idSociedadDependencia) {
		this.idSociedadDependencia = idSociedadDependencia;
	}

	/**
	 * @return the numeroTransaccionDependencia
	 */
	public Integer getNumeroTransaccionDependencia() {
		return numeroTransaccionDependencia;
	}

	/**
	 * @param numeroTransaccionDependencia the numeroTransaccionDependencia to set
	 */
	public void setNumeroTransaccionDependencia(Integer numeroTransaccionDependencia) {
		this.numeroTransaccionDependencia = numeroTransaccionDependencia;
	}

	/**
	 * @author u562163, IM de prod.
	 * Metodo que se usa para decidir si se deben revertir los medios de pago shiva pertenecientes 
	 * a la transaccion.  
	 */	
	public Boolean revertirMediosDePago(){
		return (EstadoTransaccionEnum.ERROR_DESAPROPIACION.equals(getEstadoProcesamiento()) 
				&& EstadoFacturaMedioPagoEnum.DESAPROPIADA.equals(getFactura().getEstado()))
			|| (EstadoTransaccionEnum.ERROR_DESAPROPIACION.equals(getEstadoProcesamiento())
				&& EstadoFacturaMedioPagoEnum.PENDIENTE.equals(getFactura().getEstado()))
			|| EstadoTransaccionEnum.DESAPROPIADA.equals(getEstadoProcesamiento())
			|| EstadoTransaccionEnum.PENDIENTE.equals(getEstadoProcesamiento())
			|| EstadoTransaccionEnum.ERROR_MEDIO_PAGO_DEUDA.equals(getEstadoProcesamiento())
			|| EstadoTransaccionEnum.ERROR_MEDIO_PAGO.equals(getEstadoProcesamiento())
			|| EstadoTransaccionEnum.ERROR_DEUDA.equals(getEstadoProcesamiento());
	}
	
	/**
	 * 
	 * @param transaccion
	 * @return
	 */
	public boolean haySaldoAPagarOSaldoACobrar(){
		
		if (!Validaciones.isObjectNull(getTratamientoDiferencia())){
			if (TipoTratamientoDiferenciaEnum.SALDO_A_PAGAR.equals(getTratamientoDiferencia().getTipoTratamientoDiferencia())){
				return true;
			}
		} else {
			for (ShvCobMedioPago medioPago : getMediosPago()) {
				if (TipoMedioPagoEnum.SALDO_A_COBRAR.getDescripcion().equals(medioPago.getTipoMedioPago().getDescripcion())){
					return true;
				}
			}
		}
		
		return false;
	}
	public ShvCobMedioPago getMedioPagoPorIdMedioPago(Integer idMedioPago){
		for(ShvCobMedioPago medioPago : this.getShvCobMedioPago()){
			if(medioPago.getIdMedioPago().equals(idMedioPago)){
				return medioPago;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean hayAplicacionManual() {
		
		if (!Validaciones.isObjectNull(getTratamientoDiferencia())){
			if (TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEGOCIO_NET.equals(getTratamientoDiferencia().getTipoTratamientoDiferencia())
					|| TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_SAP.equals(getTratamientoDiferencia().getTipoTratamientoDiferencia())
					|| TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_CABLEVISION.equals(getTratamientoDiferencia().getTipoTratamientoDiferencia())
					|| TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_FIBERTEL.equals(getTratamientoDiferencia().getTipoTratamientoDiferencia())
					|| TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEXTEL.equals(getTratamientoDiferencia().getTipoTratamientoDiferencia())){
				return true;
			}
		} 
		
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean hayAplicacionManualSapONet(){
		
		if (!Validaciones.isObjectNull(getTratamientoDiferencia())){
			if (TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_NEGOCIO_NET.equals(getTratamientoDiferencia().getTipoTratamientoDiferencia())
					|| TipoTratamientoDiferenciaEnum.APLICACION_MANUAL_SAP.equals(getTratamientoDiferencia().getTipoTratamientoDiferencia())){
				return true;
			}
		} 
		
		return false;
	}
	
	/**
	 * Calcula el importe parcial imputado. Tomando el importe de los medios de pago de la transaccion CONFIRMADA
	 * @return
	 */
	public BigDecimal calcularImporteParcial(){
		
		BigDecimal importeParcial = BigDecimal.ZERO;
		for (ShvCobMedioPago mp : this.getMediosPago()){
			if (EstadoTransaccionEnum.CONFIRMADA.equals(this.getEstadoProcesamiento())){
				importeParcial = importeParcial.add(mp.getImporte());
			}
		}
		
		return importeParcial;
	}
}
