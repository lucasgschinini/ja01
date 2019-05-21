package ar.com.telecom.shiva.persistencia.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SHV_VAL_VALOR_ADJUNTO")
public class ShvValValorAdjunto extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idAdjunto;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumns({ @JoinColumn(name = "ID_ADJUNTO", referencedColumnName = "ID_ADJUNTO", nullable = false) })
	private ShvDocDocumentoAdjunto idValDocDocumentoAdjunto;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumns({ @JoinColumn(name = "ID_VALOR", referencedColumnName = "ID_VALOR", nullable = false) })
	private ShvValValor valor;

	public Integer getIdAdjunto() {
		return idAdjunto;
	}

	public void setIdAdjunto(Integer idAdjunto) {
		this.idAdjunto = idAdjunto;
	}

	public ShvDocDocumentoAdjunto getIdValDocDocumentoAdjunto() {
		return idValDocDocumentoAdjunto;
	}

	public void setIdValDocDocumentoAdjunto(
			ShvDocDocumentoAdjunto idValDocDocumentoAdjunto) {
		this.idValDocDocumentoAdjunto = idValDocDocumentoAdjunto;
	}

	public ShvValValor getValor() {
		return valor;
	}

	public void setValor(ShvValValor valor) {
		this.valor = valor;
	}

}
