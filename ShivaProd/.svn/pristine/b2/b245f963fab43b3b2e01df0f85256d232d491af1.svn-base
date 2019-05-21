package ar.com.telecom.shiva.persistencia.modelo;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.comparador.ComparatorOrdenSimulacionShvCobTransaccion;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoOperacionEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;

@Entity
@Table(name = "SHV_COB_OPERACION")
public class ShvCobOperacion extends Modelo{
	
	private static final long serialVersionUID = 1L;

	@Id 
	@Column(name="ID_OPERACION")
	private Long idOperacion;

	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_OPERACION")		
	private TipoOperacionEnum  tipoOperacion;
	
	@Column(name="ID_CAJA")				
	private String idCaja;
	
	@OneToMany(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER, mappedBy = "operacion", orphanRemoval=true)
	@OrderBy("numeroTransaccion")
	//private Set<ShvCobTransaccion> transacciones = new HashSet<ShvCobTransaccion>(0);
	private Set<ShvCobTransaccion> transacciones = new TreeSet<ShvCobTransaccion>(new ComparatorOrdenSimulacionShvCobTransaccion());

	@Column(name="ID_OPERACION_ORIGINAL")
	private Long idOperacionOriginal;
	
	@Enumerated(EnumType.STRING)
	@Column(name="MONEDA_OPERACION")
	private MonedaEnum  monedaOperacion ;
	
	public Long getIdOperacion() {
		return idOperacion;
	}
	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}
	
	public TipoOperacionEnum getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(TipoOperacionEnum tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	
	public String getIdCaja() {
		return idCaja;
	}
	public void setIdCaja(String idCaja) {
		this.idCaja = idCaja;
	}

	public Set<ShvCobTransaccion> getTransacciones() {
		return transacciones;
	}
	
	public void setTransacciones(Set<ShvCobTransaccion> transacciones) {
		this.transacciones = transacciones;
	}
	
	public Set<ShvCobTransaccion> getTransaccionesSinDifCambio() {
		
		if(Validaciones.isCollectionNotEmpty(transacciones)){
			Set<ShvCobTransaccion> listaAux = new HashSet<ShvCobTransaccion>();
			
			for(ShvCobTransaccion transaccion : transacciones){
				if(!Utilidad.esDiferenciaCambio(transaccion)){
					listaAux.add(transaccion);
				}				
			}		
			
			return listaAux;
		}
		
		return null;
	}
	
	public Set<ShvCobTransaccion> getTransaccionHijoConNroTransaccionPadre(Integer nroTransaccionPadre) {
		
		if(Validaciones.isCollectionNotEmpty(transacciones)){
			Set<ShvCobTransaccion> listaAux = new HashSet<ShvCobTransaccion>();
			
			for(ShvCobTransaccion transaccion : transacciones){
				if(!Validaciones.isObjectNull(transaccion.getNumeroTransaccionPadre())
						&& !Validaciones.isObjectNull(nroTransaccionPadre)
						&& nroTransaccionPadre.equals(transaccion.getNumeroTransaccionPadre())){
					listaAux.add(transaccion);
				}				
			}	
			return listaAux;
		}
		
		return null;
	}
	
	public Long getIdOperacionOriginal() {
		return idOperacionOriginal;
	}
	public void setIdOperacionOriginal(Long idOperacionOriginal) {
		this.idOperacionOriginal = idOperacionOriginal;
	}
	public MonedaEnum getMonedaOperacion() {
		return monedaOperacion;
	}
	public void setMonedaOperacion(MonedaEnum monedaOperacion) {
		this.monedaOperacion = monedaOperacion;
	}
}
