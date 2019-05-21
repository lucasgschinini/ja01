package ar.com.telecom.shiva.base.registros.datos.entrada.agrupador;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

import ar.com.telecom.shiva.base.comparador.ComparatorDetalleEntradaAplicacionManual;
import ar.com.telecom.shiva.base.utils.Validaciones;


/**
 * @author u578936 M.A.Uehara
 *
 */
public class AgrupaDetalleAplicacionManualEntrada {
	private Set<DetalleAplicacionManualEntrada> detalleAplicacionManual = new TreeSet<DetalleAplicacionManualEntrada>(new ComparatorDetalleEntradaAplicacionManual());
	private boolean rechazarOperacion = false;
	private String mensajeError ="";
	private Long idOperacion;
	private Long idCobro;
	private Long idDescobro;
	private Long idOperacionDescobro;

	public AgrupaDetalleAplicacionManualEntrada() {
	}
	/**
	 * @return the set
	 */
	public Set<DetalleAplicacionManualEntrada> getDetalleAplicacionManual() {
		return detalleAplicacionManual;
	}
	/**
	 * @param detalleAplicacionManual the set to set
	 */
	public void setDetalleAplicacionManual(Set<DetalleAplicacionManualEntrada> detalleAplicacionManual) {
		this.detalleAplicacionManual = detalleAplicacionManual;
	}
	/**
	 * Si todos los registros detalle tiene errores se rechaza la Operacion
	 * @return
	 */
	public boolean isRechazarOperacion() {
		// es rechazada por que se rechazo Operacion
		if (this.rechazarOperacion) {
			return true;
		}
		// No es rechzada por tiene algun campo si error
		for (DetalleAplicacionManualEntrada detalle : this.detalleAplicacionManual) {
			if (Validaciones.isCollectionNotEmpty(detalle.getErrores())) {
				return true;
			}
			if (!Validaciones.isNullEmptyOrDash(detalle.getDescripcionError())) {
				return true;
			}
		}
		
		return false;
	}
	public boolean isRechazarOperacionPorRta() {
		for (DetalleAplicacionManualEntrada detalle : this.detalleAplicacionManual) {
			if (!Validaciones.isCollectionNotEmpty(detalle.getRta())) {
				return true;
			}
		}
		return false;
	}
	/**
	 * @param rechazarOperacion the rechazarOperacion to set
	 */
	public void setRechazarOperacion(boolean rechazarOperacion) {
		this.rechazarOperacion = rechazarOperacion;
	}
	/**
	 * @return the mensajeError
	 */
	public String getMensajeError() {
		return mensajeError;
	}
	/**
	 * @param mensajeError the mensajeError to set
	 */
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	public Long getIdOperacion() {
		return idOperacion;
	}
	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}
	public Long getIdCobro() {
		return idCobro;
	}
	public void setIdCobro(Long idCobro) {
		this.idCobro = idCobro;
	}
	public Long getIdDescobro() {
		return idDescobro;
	}
	public void setIdDescobro(Long idDescobro) {
		this.idDescobro = idDescobro;
	}
	
	public BigDecimal getImporteTotalAgrupador(){
		
		BigDecimal importeTotal = new BigDecimal(0);
		
		for (DetalleAplicacionManualEntrada detalle: this.getDetalleAplicacionManual()){
			
			importeTotal = importeTotal.add(detalle.getImporteTotalRta());
		
		}
			
		return importeTotal;
	}
	public Long getIdOperacionDescobro() {
		return idOperacionDescobro;
	}
	public void setIdOperacionDescobro(Long idOperacionDescobro) {
		this.idOperacionDescobro = idOperacionDescobro;
	}
}
