package ar.com.telecom.shiva.persistencia.modelo.simple;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoValor;

@Entity
@Table(name = "SHV_VAL_VALOR")
public class ShvValValorSimple extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_VALOR", nullable = false)
	private Long idValor;

	@Column(name = "ID_EMPRESA", nullable = false)
	private String idEmpresa;

	@Column(name = "ID_SEGMENTO", nullable = false)
	private String idSegmento;
	
	@Formula("ID_CLIENTE_LEGADO||' - '||RAZON_SOCIAL_CLIENTE_PERFIL||';'||ID_CLIENTE_PERFIL")
	private String codigoClienteSiebelFormula;

	@Column(name = "ID_CLIENTE_PERFIL", nullable = false)
	private Long idClientePerfil;

	@Column(name = "ID_CLIENTE_LEGADO", nullable = false)
	private Long idClienteLegado;

	@Column(name = "RAZON_SOCIAL_CLIENTE_PERFIL")
	private String razonSocialClientePerfil;

	@Column(name = "EMAIL")
	private String Email;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_TIPO_VALOR", referencedColumnName = "ID_TIPO_VALOR", nullable = false)
	private ShvParamTipoValor tipoValor;
	
	@Column(name = "ID_ANALISTA", nullable = false)
	private String idAnalista;

	@Column(name = "ID_COPROPIETARIO")
	private String idCopropietario;

	public Long getIdValor() {
		return idValor;
	}

	public void setIdValor(Long idValor) {
		this.idValor = idValor;
	}

	public String getCodigoClienteSiebelFormula() {
		return codigoClienteSiebelFormula;
	}

	public void setCodigoClienteSiebelFormula(String codigoClienteSiebelFormula) {
		this.codigoClienteSiebelFormula = codigoClienteSiebelFormula;
	}

	public Long getIdClientePerfil() {
		return idClientePerfil;
	}

	public void setIdClientePerfil(Long idClientePerfil) {
		this.idClientePerfil = idClientePerfil;
	}

	public Long getIdClienteLegado() {
		return idClienteLegado;
	}

	public void setIdClienteLegado(Long idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}

	public String getRazonSocialClientePerfil() {
		return razonSocialClientePerfil;
	}

	public void setRazonSocialClientePerfil(String razonSocialClientePerfil) {
		this.razonSocialClientePerfil = razonSocialClientePerfil;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getIdAnalista() {
		return idAnalista;
	}

	public void setIdAnalista(String idAnalista) {
		this.idAnalista = idAnalista;
	}

	public String getIdCopropietario() {
		return idCopropietario;
	}

	public void setIdCopropietario(String idCopropietario) {
		this.idCopropietario = idCopropietario;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getIdSegmento() {
		return idSegmento;
	}

	public void setIdSegmento(String idSegmento) {
		this.idSegmento = idSegmento;
	}

	public ShvParamTipoValor getTipoValor() {
		return tipoValor;
	}

	public void setTipoValor(ShvParamTipoValor tipoValor) {
		this.tipoValor = tipoValor;
	}	
}
