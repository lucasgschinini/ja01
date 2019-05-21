package ar.com.telecom.shiva.persistencia.modelo.param;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;

/**
 * @author u578936 M.A.Uehara
 *
 */
@Entity
@Table(name = "SHV_PARAM_CONF_REGLA")
public class ShvParamConfRegla extends Modelo {

	private static final long serialVersionUID = 20180519L;

	@Id
	@Column (name="ID_CONF_REGLA")
	private Long idConfRegla;
	@Enumerated(EnumType.STRING)
	@Column (name="MONEDA_OPERACION")
	private MonedaEnum mondedaOperacion;
	@Enumerated(EnumType.STRING)
	@Column (name="SOCIEDAD")
	private SociedadEnum sociedad;
	@Enumerated(EnumType.STRING)
	@Column (name="MONEDA")
	private MonedaEnum moneda;
	@Enumerated(EnumType.STRING)
	@Column (name="SISTEMA_ORIGEN")
	private SistemaEnum sistemaOrigen;
	@Enumerated(EnumType.STRING)
	@Column (name="TIPO_COMPROBANTE")
	private TipoComprobanteEnum tipoComprobante;
	
	
	public ShvParamConfRegla() {
		// TODO Auto-generated constructor stub
	}

}
