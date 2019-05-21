package ar.com.telecom.shiva.persistencia.modelo.simple;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobroCodigoOperacionExterna;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobOperacion;

/**
 * @author u578936 M.A.Uehara
 *
 */
@Entity
@Table(name = "SHV_COB_DESCOBRO")
public class ShvCobDescobroSimplificadoCodigoExternoOperacion extends Modelo {
 	
	private static final long serialVersionUID = 20171024L;

	@Id
	@Column(name="ID_DESCOBRO")	
	private Long idDescobro;

	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumns({@JoinColumn(name="ID_OPERACION", referencedColumnName="ID_OPERACION")}) 	
	private ShvCobOperacion operacion;

	
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "descobro")
	private Set<ShvCobDescobroCodigoOperacionExterna> codigosOperacionesExternas = new HashSet<ShvCobDescobroCodigoOperacionExterna>(0);


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
	 * @return the operacion
	 */
	public ShvCobOperacion getOperacion() {
		return operacion;
	}


	/**
	 * @param operacion the operacion to set
	 */
	public void setOperacion(ShvCobOperacion operacion) {
		this.operacion = operacion;
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
	public void setCodigosOperacionesExternas(
			Set<ShvCobDescobroCodigoOperacionExterna> codigosOperacionesExternas) {
		this.codigosOperacionesExternas = codigosOperacionesExternas;
	}
}
