package ar.com.telecom.shiva.negocio.conciliacion.definicion;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.EstadoReglaConciliacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;

public class ReglaConciliacionDto extends DTO{

	private static final long serialVersionUID = 1L;
	
	private Long idRegla;
	private EstadoReglaConciliacionEnum estado;
	private TipoValorEnum tipoValor;
	private List<Conciliacion> listaConciliacion;
	
	
	public Long getIdRegla() {
		return idRegla;
	}
	public void setIdRegla(Long idRegla) {
		this.idRegla = idRegla;
	}
	public EstadoReglaConciliacionEnum getEstado() {
		return estado;
	}
	public void setEstado(EstadoReglaConciliacionEnum estado) {
		this.estado = estado;
	}
	public TipoValorEnum getTipoValor() {
		return tipoValor;
	}
	public void setTipoValor(TipoValorEnum tipoValor) {
		this.tipoValor = tipoValor;
	}
	public void setListaConciliacion(List<Conciliacion> listaConciliacion) {
		this.listaConciliacion = listaConciliacion;
	}
	
	public List<Conciliacion> getListaConciliacion() {
		return listaConciliacion;
	}
	/**
	 * Retorna la lista de concialiciones ordenadas por su campo "ordenConciliacion".
	 * @return
	 */
	public List<Conciliacion> getListaConciliacionOrdenada() {
		Comparator<Conciliacion> c = new Comparator<Conciliacion>() {

			public int compare(Conciliacion o1, Conciliacion o2) {
				return o1.getOrdenConciliacion().compareTo(o2.getOrdenConciliacion());
			}
		};
		Collections.sort(listaConciliacion, c );
		return listaConciliacion;
	}
	
}
