package ar.com.telecom.shiva.presentacion.bean.filtro;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;

public class Filtro extends Object implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String pantallaDestino="";
	private String url;
	
	private String importeDesde;
	private String importeHasta;
	private String fechaDesde;
	private String fechaHasta;
	private UsuarioSesion usuarioLogeado;
	
	private String empresa;
	private String segmento;
	private String idEmpresa;
	private String idSegmento;
	
	
	
	private List<String> listaCamposAValidar;
	
	/**
	 * @return the idEmpresa
	 */
	public String getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * @param idEmpresa the idEmpresa to set
	 */
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	/**
	 * @return the idSegmento
	 */
	public String getIdSegmento() {
		return idSegmento;
	}

	/**
	 * @param idSegmento the idSegmento to set
	 */
	public void setIdSegmento(String idSegmento) {
		this.idSegmento = idSegmento;
	}

	/**
	 * Establesco un rango de fechas de acuerdo a la diferencia ingresada
	 * @param diferencia
	 */
	@JsonIgnore
	public void crearRangoFechas(long diferencia) {
		Date hoy = new Date();
		this.setFechaHasta(Utilidad.formatDatePicker(hoy));
		hoy.setTime(hoy.getTime() - diferencia);
		this.setFechaDesde(Utilidad.formatDatePicker(hoy));
	}
	
	public String getImporteDesde() {
		return importeDesde;
	}

	public void setImporteDesde(String importeDesde) {
		this.importeDesde = importeDesde;
	}

	public String getImporteHasta() {
		return importeHasta;
	}

	public void setImporteHasta(String importeHasta) {
		this.importeHasta = importeHasta;
	}

	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
	public UsuarioSesion getUsuarioLogeado() {
		return usuarioLogeado;
	}

	public void setUsuarioLogeado(UsuarioSesion usuarioLogeado) {
		this.usuarioLogeado = usuarioLogeado;
	}

	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getSegmento() {
		return segmento;
	}
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public String getPantallaDestino() {
		return pantallaDestino;
	}

	public void setPantallaDestino(String pantallaDestino) {
		this.pantallaDestino = pantallaDestino;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getListaCamposAValidar() {
		return listaCamposAValidar;
	}

	public void setListaCamposAValidar(List<String> listaCamposAValidar) {
		this.listaCamposAValidar = listaCamposAValidar;
	}
}
