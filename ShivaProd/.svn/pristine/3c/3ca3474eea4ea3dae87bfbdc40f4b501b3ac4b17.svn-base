package ar.com.telecom.shiva.presentacion.bean.filtro;

import java.text.ParseException;
import java.util.Date;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCotizacionSapEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;

public class CotizacionFiltro extends Filtro {
	private static final long serialVersionUID = 20161004153L;

	private String 					fecha;
	private String 					moneda;
	private TipoCotizacionSapEnum	tipoCotizacion;
	private MonedaEnum				monedaProcedencia;
	private MonedaEnum				monedaDestino;
	private Date 					fechaValidezDesde;


	public CotizacionFiltro() {

	}
	
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}
	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public boolean validar() {
		try {
			return !(Validaciones.isObjectNull(getFechaValidezDesde()) || Validaciones.isObjectNull(getMonedaDestino()));
		} catch (ParseException e) {
			return false;
		}
	}
	
	/**
	 * @return the tipoCotizacion
	 */
	public TipoCotizacionSapEnum getTipoCotizacion() {
		return tipoCotizacion;
	}

	/**
	 * @param tipoCotizacion the tipoCotizacion to set
	 */
	public void setTipoCotizacion(TipoCotizacionSapEnum tipoCotizacion) {
		this.tipoCotizacion = tipoCotizacion;
	}

	/**
	 * @return the monedaProcedencia
	 */
	public MonedaEnum getMonedaProcedencia() {
		return monedaProcedencia;
	}

	/**
	 * @param monedaProcedencia the monedaProcedencia to set
	 */
	public void setMonedaProcedencia(MonedaEnum monedaProcedencia) {
		this.monedaProcedencia = monedaProcedencia;
	}

	/**
	 * @return the monedaDestino
	 */
	public MonedaEnum getMonedaDestino() {
		return MonedaEnum.getEnumBySigno2(moneda);
	}

	/**
	 * @param monedaDestino the monedaDestino to set
	 */
	public void setMonedaDestino(MonedaEnum monedaDestino) {
		this.monedaDestino = monedaDestino;
	}

	/**
	 * @return the fechaValidezDesde
	 * @throws ParseException 
	 */
	public Date getFechaValidezDesde() throws ParseException {
		return Utilidad.parseDatePickerString(this.fecha);
	}

	/**
	 * @param fechaValidezDesde the fechaValidezDesde to set
	 */
	public void setFechaValidezDesde(Date fechaValidezDesde) {
		this.fechaValidezDesde = fechaValidezDesde;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CotizacionFiltro [fecha=" + fecha + ", moneda=" + moneda + "]";
	}
}
