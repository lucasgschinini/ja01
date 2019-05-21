package ar.com.telecom.shiva.persistencia.modelo.param;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_PARAM_TIPO_LETRA_COMP")
public class ShvParamTipoLetraComprobante extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_TIPO_LETRA_COMPROBANTE", nullable = false)
	private String idTipoLetraComprobante;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	@Enumerated(EnumType.STRING)
	@Column(name = "HABILITADA", nullable = false)
	private SiNoEnum habilitada;

	public String getIdTipoLetraComprobante() {
		return idTipoLetraComprobante;
	}

	public void setIdTipoLetraComprobante(String idTipoLetraComprobante) {
		this.idTipoLetraComprobante = idTipoLetraComprobante;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public SiNoEnum getHabilitada() {
		return habilitada;
	}

	public void setHabilitada(SiNoEnum habilitada) {
		this.habilitada = habilitada;
	}

}
