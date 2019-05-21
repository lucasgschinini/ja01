package ar.com.telecom.shiva.base.registros.datos.entrada;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.AgrupaDetalleAplicacionManualEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.DetalleAplicacionManualEntrada;
import ar.com.telecom.shiva.base.utils.logs.Traza;

/**
 * @author u578936 M.A.Uehara
 *
 */
public class ArchivoDetalleAplicacionManualEntrada {
	private Map<String, AgrupaDetalleAplicacionManualEntrada> mapa = new HashMap<String, AgrupaDetalleAplicacionManualEntrada>();
	private boolean rechazarArchivoTodosRegistrosErroneos = false;
	private boolean archivoYaProcesado=false;
	private boolean existeAlgunError = false;
	private List<String> errores = new ArrayList<>();
	private BigDecimal importePie = BigDecimal.ZERO;
	private BigDecimal importeTotalRegistros = BigDecimal.ZERO;
	private boolean errorImporte = false;
	
	public BigDecimal getImporteTotalRegistros() {
		return importeTotalRegistros;
	}

	public void setImporteTotalRegistros(BigDecimal importeTotalRegistros) {
		this.importeTotalRegistros = importeTotalRegistros;
	}

	public BigDecimal getImportePie() {
		return importePie;
	}

	public void setImportePie(BigDecimal importePie) {
		this.importePie = importePie;
	}

	private String errorPie;
	private String errorConsistencia = "";
	private SistemaEnum sistema;

	public ArchivoDetalleAplicacionManualEntrada() {
	}

	/**
	 * @return the mapa
	 */
	public Map<String, AgrupaDetalleAplicacionManualEntrada> getMapa() {
		return mapa;
	}

	/**
	 * @param mapa the mapa to set
	 */
	public void setMapa(Map<String, AgrupaDetalleAplicacionManualEntrada> mapa) {
		this.mapa = mapa;
	}

	/**
	 * @return the rechazarArchivoTodosRegistrosErroneos
	 */
	public boolean isRechazarArchivoTodosRegistrosErroneos() {
		return rechazarArchivoTodosRegistrosErroneos;
	}

	/**
	 * @param rechazarArchivoTodosRegistrosErroneos the rechazarArchivoTodosRegistrosErroneos to set
	 */
	public void setRechazarArchivoTodosRegistrosErroneos(
			boolean rechazarArchivoTodosRegistrosErroneos) {
		this.rechazarArchivoTodosRegistrosErroneos = rechazarArchivoTodosRegistrosErroneos;
	}
	
	
	public boolean isArchivoYaProcesado() {
		return archivoYaProcesado;
	}

	public void setArchivoYaProcesado(boolean archivoYaProcesado) {
		this.archivoYaProcesado = archivoYaProcesado;
	}

	/**
	 * @return the rechazarArchivoTodosRegistrosErroneos
	 */
	public boolean isExisteAlgunError() {
		return existeAlgunError;
	}

	/**
	 * @param rechazarArchivoTodosRegistrosErroneos the rechazarArchivoTodosRegistrosErroneos to set
	 */
	public void setExisteAlgunError(
			boolean existeAlgunError) {
		this.existeAlgunError = existeAlgunError;
	}
	
	/**
	 * @return the errores
	 */
	public List<String> getErrores() {
		return errores;
	}

	/**
	 * @param errores the errores to set
	 */
	public void setErrores(List<String> errores) {
		this.errores = errores;
	}

	/**
	 * @return the errorPie
	 */
	public String getErrorPie() {
		return errorPie;
	}

	/**
	 * @param errorPie the errorPie to set
	 */
	public void setErrorPie(String errorPie) {
		this.errorPie = errorPie;
	}

	/**
	 * @return the errorConsistencia
	 */
	public String getErrorConsistencia() {
		return errorConsistencia;
	}

	/**
	 * @param errorConsistencia the errorConsistencia to set
	 */
	public void setErrorConsistencia(String errorConsistencia) {
		this.errorConsistencia = errorConsistencia;
	}

	/**
	 * @return the sistema
	 */
	public SistemaEnum getSistema() {
		return sistema;
	}

	/**
	 * @param sistema the sistema to set
	 */
	public void setSistema(SistemaEnum sistema) {
		this.sistema = sistema;
	}
	
	public boolean validarImporteTotalConImportePie(ArchivoDetalleAplicacionManualEntrada archivo) {
		BigDecimal importeArchivo = this.getImporteArchivo();
		if (
			(importeArchivo.compareTo(BigDecimal.ZERO) != 0
				&& importePie.compareTo(BigDecimal.ZERO) != 0)
				&& importeArchivo.compareTo(this.importePie) == 0){
			return true;
		}
		Traza.auditoria(getClass(), "ACUMULADO : " + importeArchivo);
		return false;
	}
	/**
	 * retorna el acumulado de todo el archivo a nivel registro
	 * @return
	 */
	public BigDecimal getImporteArchivo() {
		BigDecimal acumulado = BigDecimal.ZERO;
		Traza.auditoria(getClass(), "---> Comienza el calculo de Importe del archivo");
		Set<String>keys = this.getMapa().keySet();
		Traza.auditoria(getClass(), " Acumulado = " + acumulado.toString());
		for (String key : keys) {
			AgrupaDetalleAplicacionManualEntrada agrupo = this.getMapa().get(key);
			for (DetalleAplicacionManualEntrada detalle : agrupo.getDetalleAplicacionManual()) {
				Traza.auditoria(getClass(), " Mas pesificados = " + detalle.getMontoImputarEnPesos());
					acumulado = acumulado.add(detalle.getMontoImputarEnPesos());
					
			}
		}
		Traza.auditoria(getClass(), " Acumulado = " + acumulado.toString());
		return acumulado;
	}

	/**
	 * @return the errorImporte
	 */
	public boolean isErrorImporte() {
		return errorImporte;
	}

	/**
	 * @param errorImporte the errorImporte to set
	 */
	public void setErrorImporte(boolean errorImporte) {
		this.errorImporte = errorImporte;
	}
	
}
