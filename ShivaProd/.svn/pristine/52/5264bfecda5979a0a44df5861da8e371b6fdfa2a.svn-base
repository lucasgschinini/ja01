package ar.com.telecom.shiva.base.registros.datos.entrada.agrupador;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.dto.REG;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoOperacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRegistroEnum;

/**
 * @author u578936 M.A.Uehara
 *
 */
public class DetalleAplicacionManualEntrada extends REG {
	private static final long serialVersionUID = 20180103L;

	private int nroRegistro;
	private Long idOperacion;
	private TipoRegistroEnum tipoRegistro;
	private TipoOperacionEnum tipoOperacion;
	private String cuit;
	private String contrato;
	private MonedaEnum moneda;
	private BigDecimal montoImputarEnMonedaOrigen;
	private BigDecimal montoImputarEnPesos;
	private String idTransaccionShiva;
	private String fechaRealDePago;
	private String tipoDeCredito;
	private String referenciaDelValor;
	private String tipoCambio;
	private Long idTratamientoDiferencia;
	private Long IdTransaccion;
	private List<String> ListaOperacionExterna;
	private String registroArchivo;
	private List<String> errores = new ArrayList<String>();
	private String tipoMedioPago;
	private String descripcionError = "";
	private boolean tieneRta;
	private List<DetalleAplicacionManualRta> rta = new ArrayList<DetalleAplicacionManualRta>();
	private Long idMedioPago;
	private MonedaEnum monedaMedioPago;
	private BigDecimal importeTotalMedioPago;

	public DetalleAplicacionManualEntrada() {
	}

	/**
	 * @return the nroRegistro
	 */
	public int getNroRegistro() {
		return nroRegistro;
	}

	/**
	 * @param nroRegistro the nroRegistro to set
	 */
	public void setNroRegistro(int nroRegistro) {
		this.nroRegistro = nroRegistro;
	}

	/**
	 * @return the tipoOperacion
	 */
	public TipoOperacionEnum getTipoOperacion() {
		return tipoOperacion;
	}

	/**
	 * @param tipoOperacion the tipoOperacion to set
	 */
	public void setTipoOperacion(TipoOperacionEnum tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	/**
	 * @return the cuit
	 */
	
	public String getCuit() {
		return cuit;
	}

	/**
	 * @param cuit the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * @return the contrato
	 */
	public String getContrato() {
		return contrato;
	}

	/**
	 * @param contrato the contrato to set
	 */
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}

	/**
	 * @return the moneda
	 */
	public MonedaEnum getMoneda() {
		return moneda;
	}

	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(MonedaEnum moneda) {
		this.moneda = moneda;
	}

	/**
	 * @return the montoImputarEnMonedaOrigen
	 */
	public BigDecimal getMontoImputarEnMonedaOrigen() {
		return montoImputarEnMonedaOrigen;
	}

	/**
	 * @param montoImputarEnMonedaOrigen the montoImputarEnMonedaOrigen to set
	 */
	public void setMontoImputarEnMonedaOrigen(BigDecimal montoImputarEnMonedaOrigen) {
		this.montoImputarEnMonedaOrigen = montoImputarEnMonedaOrigen;
	}

	/**
	 * @return the montoImputarEnPesos
	 */
	public BigDecimal getMontoImputarEnPesos() {
		return montoImputarEnPesos;
	}

	/**
	 * @param montoImputarEnPesos the montoImputarEnPesos to set
	 */
	public void setMontoImputarEnPesos(BigDecimal montoImputarEnPesos) {
		this.montoImputarEnPesos = montoImputarEnPesos;
	}

	/**
	 * @return the idTransaccionShiva
	 */
	public String getIdTransaccionShiva() {
		return idTransaccionShiva;
	}

	/**
	 * @param idTransaccionShiva the idTransaccionShiva to set
	 */
	public void setIdTransaccionShiva(String idTransaccionShiva) {
		this.idTransaccionShiva = idTransaccionShiva;
	}

	/**
	 * @return the fechaRealDePago
	 */
	public String getFechaRealDePago() {
		return fechaRealDePago;
	}

	/**
	 * @param fechaRealDePago the fechaRealDePago to set
	 */
	public void setFechaRealDePago(String fechaRealDePago) {
		this.fechaRealDePago = fechaRealDePago;
	}

	/**
	 * @return the tipoDeCredito
	 */
	public String getTipoDeCredito() {
		return tipoDeCredito;
	}

	/**
	 * @param tipoDeCredito the tipoDeCredito to set
	 */
	public void setTipoDeCredito(String tipoDeCredito) {
		this.tipoDeCredito = tipoDeCredito;
	}

	/**
	 * @return the referenciaDelValor
	 */
	public String getReferenciaDelValor() {
		return referenciaDelValor;
	}

	/**
	 * @param referenciaDelValor the referenciaDelValor to set
	 */
	public void setReferenciaDelValor(String referenciaDelValor) {
		this.referenciaDelValor = referenciaDelValor;
	}

	/**
	 * @return the tipoCambio
	 */
	public String getTipoCambio() {
		return tipoCambio;
	}

	/**
	 * @param tipoCambio the tipoCambio to set
	 */
	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	/**
	 * @return the idTratamientoDiferencia
	 */
	public Long getIdTratamientoDiferencia() {
		return idTratamientoDiferencia;
	}

	/**
	 * @param idTratamientoDiferencia the idTratamientoDiferencia to set
	 */
	public void setIdTratamientoDiferencia(Long idTratamientoDiferencia) {
		this.idTratamientoDiferencia = idTratamientoDiferencia;
	}

	/**
	 * @return the idTransaccion
	 */
	public Long getIdTransaccion() {
		return IdTransaccion;
	}

	/**
	 * @param idTransaccion the idTransaccion to set
	 */
	public void setIdTransaccion(Long idTransaccion) {
		IdTransaccion = idTransaccion;
	}

	/**
	 * @return the listaOperacionExterna
	 */
	public List<String> getListaOperacionExterna() {
		return ListaOperacionExterna;
	}

	/**
	 * @param listaOperacionExterna the listaOperacionExterna to set
	 */
	public void setListaOperacionExterna(List<String> listaOperacionExterna) {
		ListaOperacionExterna = listaOperacionExterna;
	}

	/**
	 * @return the reg
	 */
	public String getRegistroArchivo() {
		return registroArchivo;
	}

	/**
	 * @param reg the reg to set
	 */
	public void setRegistroArchivo(String reg) {
		this.registroArchivo = reg;
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
	 * @return the tipoRegistro
	 */
	public TipoRegistroEnum getTipoRegistro() {
		return tipoRegistro;
	}

	/**
	 * @param tipoRegistro the tipoRegistro to set
	 */
	public void setTipoRegistro(TipoRegistroEnum tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
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
	 * @return the rta
	 */
	public List<DetalleAplicacionManualRta> getRta() {
		return rta;
	}

	/**
	 * @param rta the rta to set
	 */
	public void setRta(List<DetalleAplicacionManualRta> rta) {
		this.rta = rta;
	}

	/**
	 * @return the tipoMedioPago
	 */
	public String getTipoMedioPago() {
		return tipoMedioPago;
	}

	/**
	 * @param tipoMedioPago the tipoMedioPago to set
	 */
	public void setTipoMedioPago(String tipoMedioPago) {
		this.tipoMedioPago = tipoMedioPago;
	}

	/**
	 * @return the tieneRta
	 */
	public boolean isTieneRta() {
		return tieneRta;
	}

	/**
	 * @param tieneRta the tieneRta to set
	 */
	public void setTieneRta(boolean tieneRta) {
		this.tieneRta = tieneRta;
	}

	public String getDescripcionError() {
		return descripcionError;
	}

	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}
	
	public BigDecimal getImporteTotalRta(){
		
		BigDecimal importeTotal = new BigDecimal(0);
		for (DetalleAplicacionManualRta rta: this.getRta()){
			
			importeTotal = importeTotal.add(rta.getImporteAplicado());
		
		}
			
		return importeTotal;
	}

	public Long getIdMedioPago() {
		return idMedioPago;
	}

	public void setIdMedioPago(Long idMedioPago) {
		this.idMedioPago = idMedioPago;
	}

	public MonedaEnum getMonedaMedioPago() {
		return monedaMedioPago;
	}

	public void setMonedaMedioPago(MonedaEnum monedaMedioPago) {
		this.monedaMedioPago = monedaMedioPago;
	}

	public BigDecimal getImporteTotalMedioPago() {
		return importeTotalMedioPago;
	}

	public void setImporteTotalMedioPago(BigDecimal importeTotalMedioPago) {
		this.importeTotalMedioPago = importeTotalMedioPago;
	}
	
}
