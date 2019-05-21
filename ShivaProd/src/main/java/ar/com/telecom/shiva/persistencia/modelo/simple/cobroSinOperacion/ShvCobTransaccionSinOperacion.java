package ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion;

import java.util.HashSet;
import java.util.Iterator;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.EstadoFacturaMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMensajeEstadoEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_COB_TRANSACCION")
public class ShvCobTransaccionSinOperacion extends Modelo {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_COB_TRANSACCION_SIN")
    @SequenceGenerator(name="SEQ_SHV_COB_TRANSACCION_SIN", sequenceName="SEQ_SHV_COB_TRANSACCION",allocationSize=1)
	@Column(name="ID_TRANSACCION")	
	private Integer idTransaccion;
	
	@Column(name="NUMERO_TRANSACCION")	
	private Integer numeroTransaccion;
	
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy="transaccion")
	private Set<ShvCobFacturaSinOperacion> shvCobFactura = new HashSet<ShvCobFacturaSinOperacion>(0);
	
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy="transaccion")
	private Set<ShvCobMedioPagoSinOperacion> shvCobMedioPago = new HashSet<ShvCobMedioPagoSinOperacion>(0);
	
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy="transaccion")
	private Set<ShvCobTratamientoDiferenciaSinOperacion> listaTratamientosDiferencias = new HashSet<ShvCobTratamientoDiferenciaSinOperacion>(0);
	
	@Column(name="ID_OPERACION")
	private Long idOperacion;
	
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

	@Column(name="GRUPO")
	private Long grupo;
	
	@Enumerated(EnumType.STRING)
	@Column(name="SISTEMA")
	private SistemaEnum sistema;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ID_SOCIEDAD")
	private SociedadEnum sociedad;
	
	@Column(name="NUMERO_TRANSACCION_DEPENDENCIA")	
	private Integer numeroTransaccionDependencia;
	
	@Enumerated(EnumType.STRING)
	@Column(name="SISTEMA_DEPENDENCIA")
	private SistemaEnum sistemaDependencia;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ID_SOCIEDAD_DEPENDENCIA")
	private SociedadEnum idSociedadDependencia;
	
	@Column(name="NUMERO_TRANSACCION_FICTICIO")	
	private Integer numeroTransaccionFicticio;
	
	public Integer getNumeroTransaccionFicticio() {
		return numeroTransaccionFicticio;
	}

	public void setNumeroTransaccionFicticio(Integer numeroTransaccionFicticio) {
		this.numeroTransaccionFicticio = numeroTransaccionFicticio;
	}

	public Integer getNumeroTransaccionDependencia() {
		return numeroTransaccionDependencia;
	}

	public void setNumeroTransaccionDependencia(Integer numeroTransaccionDependencia) {
		this.numeroTransaccionDependencia = numeroTransaccionDependencia;
	}

	public SociedadEnum getSociedad() {
		return sociedad;
	}

	public void setSociedad(SociedadEnum sociedad) {
		this.sociedad = sociedad;
	}

	public SistemaEnum getSistema() {
		return sistema;
	}

	public void setSistema(SistemaEnum sistema) {
		this.sistema = sistema;
	}

	public Integer getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(Integer idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	
	public Set<ShvCobFacturaSinOperacion> getShvCobFactura() {
		return shvCobFactura;
	}

	public void setShvCobFactura(Set<ShvCobFacturaSinOperacion> shvCobFactura) {
		this.shvCobFactura = shvCobFactura;
	}

	public Set<ShvCobMedioPagoSinOperacion> getShvCobMedioPago() {
		return shvCobMedioPago;
	}

	public void setShvCobMedioPago(Set<ShvCobMedioPagoSinOperacion> shvCobMedioPago) {
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
	 * Retorna el Numero de Transaccion Ficticio con formato 0000000.00000
	 * @return
	 */
	public String getOperacionTransaccionFicticioFormateado() {
	
		String operacionTransaccionFormateado = Utilidad.rellenarCerosIzquierda(String.valueOf(this.getIdOperacion()), 7) + "." +
			Utilidad.rellenarCerosIzquierda(String.valueOf(getNumeroTransaccionFicticio()), 5);
		
		return operacionTransaccionFormateado;
	}
	
	
	/**
	 * Retorna el Numero de Transaccion con formato 0000000.00000
	 * @return
	 */
	public String getOperacionTransaccionFormateado() {
	
		String operacionTransaccionFormateado = Utilidad.rellenarCerosIzquierda(String.valueOf(this.getIdOperacion()), 7) + "." +
			Utilidad.rellenarCerosIzquierda(String.valueOf(getNumeroTransaccion()), 5);
		
		return operacionTransaccionFormateado;
	}

	/**
	 * Retorna la factura. En caso de que sea de tipo Calipso, retorna la factura 
	 * que tenga GeneradoPorCobro = NO. En caso de que la factura sea de MIC, retorno la
	 * primera ya que es la unica.
	 * @return
	 */
	public ShvCobFacturaSinOperacion getFactura(){
		for(ShvCobFacturaSinOperacion factura : shvCobFactura){
			if(factura instanceof ShvCobFacturaCalipsoSinOperacion){

				// siempre va a existir una sola factura con GeneradoPorCobro = NO
				if(Validaciones.isObjectNull(factura.getGeneradoPorCobro())
						|| SiNoEnum.NO.equals(((ShvCobFacturaCalipsoSinOperacion)factura).getGeneradoPorCobro())){
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
	 * Retorna el tratamiento de diferencia.
	 * @return
	 */
	public ShvCobTratamientoDiferenciaSinOperacion getTratamientoDiferencia() {
		Iterator<ShvCobTratamientoDiferenciaSinOperacion> iterator = listaTratamientosDiferencias.iterator();
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
	public Set<ShvCobMedioPagoSinOperacion> getMediosPago(){
		Set<ShvCobMedioPagoSinOperacion> listaMediosPago = new HashSet<ShvCobMedioPagoSinOperacion>();
		for(ShvCobMedioPagoSinOperacion medioPago : shvCobMedioPago){
			
			// Solo se agregan nuevos medios de pago Cta en la apropiacion a calipso
			if(medioPago instanceof ShvCobMedioPagoCTASinOperacion){
				// si GeneradoPorCobro = NO lo agrego a la lista, sino no
				if(((ShvCobMedioPagoCTASinOperacion)medioPago).getGeneradoPorCobro().equals(SiNoEnum.NO)){
					listaMediosPago.add(medioPago);
				}
			}else{
				// si  es de tipo MIC, usuario o Shiva lo agrego
				listaMediosPago.add(medioPago);
			}
		}
		return listaMediosPago;
	}
	
	/**
	 * Retorna los medios de pago cta, los originales y los generados x cobro
	 * @return
	 */
	public Set<ShvCobMedioPagoSinOperacion> getMediosPagoCta(){
	
		Set<ShvCobMedioPagoSinOperacion> listaMediosPago = new HashSet<ShvCobMedioPagoSinOperacion>();
		for(ShvCobMedioPagoSinOperacion medioPago : shvCobMedioPago){
			// Solo se agregan medios de pago Cta
			if(medioPago instanceof ShvCobMedioPagoCTASinOperacion){
				
					listaMediosPago.add(medioPago);
				}

		}
		return listaMediosPago;
		
	}
	public Long obtenerIdClienteLegadoDeTratamientoDiferencia() {
		Iterator<ShvCobTratamientoDiferenciaSinOperacion> iterator = listaTratamientosDiferencias.iterator();
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
	public Set<ShvCobTratamientoDiferenciaSinOperacion> getListaTratamientosDiferencias() {
		return listaTratamientosDiferencias;
	}

	/**
	 * @param tratamientoDiferencia the tratamientoDiferencia to set
	 */
	public void setListaTratamientosDiferencias(Set<ShvCobTratamientoDiferenciaSinOperacion> tratamientoDiferencia) {
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

	public Long getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}

	/**
	 * Retorna el medio de pago que coincida con el idMedioPago de los medios de pago propios del cobro.
	 * @param idTransaccion
	 * @return
	 */
	public ShvCobMedioPagoSinOperacion getMedioPagoPorIdMedioPago(Integer idMedioPago){
		for(ShvCobMedioPagoSinOperacion medioPago : this.getShvCobMedioPago()){
			if(medioPago.getIdMedioPago().equals(idMedioPago)){
				return medioPago;
			}
		}
		return null;
	}

	public Long getGrupo() {
		return grupo;
	}

	public void setGrupo(Long grupo) {
		this.grupo = grupo;
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
	
	
}
