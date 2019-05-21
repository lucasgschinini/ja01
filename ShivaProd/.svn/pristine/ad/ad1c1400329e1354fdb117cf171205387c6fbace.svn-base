package ar.com.telecom.shiva.persistencia.modelo.simple;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCodigoOperacionExterna;

/**
 * @author u578936 M.A.Uehara
 *
 */
@Entity
@Table(name = "SHV_COB_ED_COBRO")
public class ShvCobEdCobroSimplificadoCodigoExternoOperacion extends Modelo {
	private static final long serialVersionUID = 20171024L;

	@Id
	@Column(name="ID_COBRO", updatable = false)
	private Long idCobro;
	
	@Column(name="ID_OPERACION", updatable = false)
	private Long idOperacion;

	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name="ID_COBRO", referencedColumnName="ID_COBRO")
	@NotFound(action=NotFoundAction.IGNORE)
	private Set<ShvCobEdCodigoOperacionExterna> codigosOperacionesExternas = new HashSet<ShvCobEdCodigoOperacionExterna>(0);

	
	public ShvCobEdCobroSimplificadoCodigoExternoOperacion() {
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
	 * @return the idOperacion
	 */
	public Long getIdOperacion() {
		return idOperacion;
	}


	/**
	 * @param idOperacion the idOperacion to set
	 */
	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}


	/**
	 * @return the codigosOperacionesExternas
	 */
	public Set<ShvCobEdCodigoOperacionExterna> getCodigosOperacionesExternas() {
		return codigosOperacionesExternas;
	}


	/**
	 * @param codigosOperacionesExternas the codigosOperacionesExternas to set
	 */
	public void setCodigosOperacionesExternas(
			Set<ShvCobEdCodigoOperacionExterna> codigosOperacionesExternas) {
		this.codigosOperacionesExternas = codigosOperacionesExternas;
	}


	
}