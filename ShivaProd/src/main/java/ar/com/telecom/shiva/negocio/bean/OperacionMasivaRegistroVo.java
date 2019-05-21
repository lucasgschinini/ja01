package ar.com.telecom.shiva.negocio.bean;

import java.io.Serializable;
import java.util.Date;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoArchivoOperacionMasivaEnum;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistro;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoCobro;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;

public class OperacionMasivaRegistroVo implements Serializable{

	private static final long serialVersionUID = -1059556524075075507L;
	private ShvParamEmpresa empresa;
	private ShvParamSegmento segmento;
	private String idCopropietario;
	private ShvParamMotivoCobro motivo;
	private String idAnalista;
	private TipoArchivoOperacionMasivaEnum tipoOperacionMasiva;
	private ShvMasRegistro registro;
	private long idOperacionMasiva;
	private Date fechaAlta;
	private String usuarioOperacionMasiva;
	private Date fechaDerivacion;
	private MonedaEnum monedaDelCobro;
	
	/**
	 * @return the monedaDelCobro
	 */
	public MonedaEnum getMonedaDelCobro() {
		return monedaDelCobro;
	}

	/**
	 * @param monedaDelCobro the monedaDelCobro to set
	 */
	public void setMonedaDelCobro(MonedaEnum monedaDelCobro) {
		this.monedaDelCobro = monedaDelCobro;
	}

	public OperacionMasivaRegistroVo()  {
	}

	/**
	 * @return the empresa
	 */
	public ShvParamEmpresa getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(ShvParamEmpresa empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return the segmento
	 */
	public ShvParamSegmento getSegmento() {
		return segmento;
	}

	/**
	 * @param segmento the segmento to set
	 */
	public void setSegmento(ShvParamSegmento segmento) {
		this.segmento = segmento;
	}

	/**
	 * @return the idCopropietario
	 */
	public String getIdCopropietario() {
		return idCopropietario;
	}

	/**
	 * @param idCopropietario the idCopropietario to set
	 */
	public void setIdCopropietario(String idCopropietario) {
		this.idCopropietario = idCopropietario;
	}

	/**
	 * @return the motivo
	 */
	public ShvParamMotivoCobro getMotivo() {
		return motivo;
	}

	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(ShvParamMotivoCobro motivo) {
		this.motivo = motivo;
	}

	/**
	 * @return the idAnalista
	 */
	public String getIdAnalista() {
		return idAnalista;
	}

	/**
	 * @param idAnalista the idAnalista to set
	 */
	public void setIdAnalista(String idAnalista) {
		this.idAnalista = idAnalista;
	}

	/**
	 * @return the tipoOperacionMasiva
	 */
	public TipoArchivoOperacionMasivaEnum getTipoOperacionMasiva() {
		return tipoOperacionMasiva;
	}

	/**
	 * @param tipoOperacionMasiva the tipoOperacionMasiva to set
	 */
	public void setTipoOperacionMasiva(
			TipoArchivoOperacionMasivaEnum tipoOperacionMasiva) {
		this.tipoOperacionMasiva = tipoOperacionMasiva;
	}

	/**
	 * @return the registro
	 */
	public ShvMasRegistro getRegistro() {
		return registro;
	}

	/**
	 * @param registro the registro to set
	 */
	public void setRegistro(ShvMasRegistro registro) {
		this.registro = registro;
	}

	/**
	 * @return the idOperacionMasiva
	 */
	public long getIdOperacionMasiva() {
		return idOperacionMasiva;
	}

	/**
	 * @param idOperacionMasiva the idOperacionMasiva to set
	 */
	public void setIdOperacionMasiva(long idOperacionMasiva) {
		this.idOperacionMasiva = idOperacionMasiva;
	}

	/**
	 * @return the fechaAlta
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * @param fechaAlta the fechaAlta to set
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * @return the usuarioOperacionMasiva
	 */
	public String getUsuarioOperacionMasiva() {
		return usuarioOperacionMasiva;
	}

	/**
	 * @param usuarioOperacionMasiva the usuarioOperacionMasiva to set
	 */
	public void setUsuarioOperacionMasiva(String usuarioOperacionMasiva) {
		this.usuarioOperacionMasiva = usuarioOperacionMasiva;
	}

	/**
	 * @return the fechaDerivacion
	 */
	public Date getFechaDerivacion() {
		return fechaDerivacion;
	}

	/**
	 * @param fechaDerivacion the fechaDerivacion to set
	 */
	public void setFechaDerivacion(Date fechaDerivacion) {
		this.fechaDerivacion = fechaDerivacion;
	}
}
