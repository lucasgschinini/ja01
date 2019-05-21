package ar.com.telecom.shiva.negocio.dto;

import java.math.BigDecimal;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.DatosWorkflowEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;

public class RegistroAVCDto extends DTO {

	private static final long serialVersionUID = 1L;

	private Long 	   idRegistro;
	private String 	   acuerdo;
	private String 	   idAcuerdo;
	private String 	   codigoCliente;
	private BigDecimal importe;
	private BigDecimal importeParaComparar;
	private String	   nombreArchivo;
	private String	   tipoValor;
	private String     estadoFormateado;
	private String     tipoValorFormateado;
	private String     bancoDeposito;
	private String     numeroCuenta;
	private String 	   observacionAnulacion; // utilizada al anular el registro avc
	private String 	   observacionConfirmarAnulacion; // utilizada al confirmar anulacion del registro avc
	private String 	   observaciones;
	private boolean    esEstadoPendConfirmar;
	private String     tipoDto;
	
	private String     idRegistroSelected;
	private String     conciliacionesSelected;
	private String     nuevaObservacion;
	private String     registrosAVCAAnularSelected;
	
	private String 		observacionesConfirmarRechazarAltaDeValoresAPartirDeRegistroAVC;
	
	private String razonSocial;
	private String analistaTeamComercial;
	private String fechaAltaFormateadaSoloDia;
	private String fechaAltaDesdeFormateada;
	private String fechaAltaHastaFormateada;
	private String fechaDeposito;
	private String fechaDerivacionFormateada;
	private String fechaDerivacionDesdeFormateada;
	private String fechaDerivacionHastaFormateada;
	private String analistaDerivacion;
	private String nroBoletaFiltro;
	private String referenciaDelValorFiltro;
	private String referenciaDelValorFormateado;
	private String nroBoletaFormateado;
	private String importeDesde;
	private String importeHasta; 
	
	private String usuarioAlta;
	private String nombreUsuarioAlta;
	private String mailUsuarioAlta;
	
	private String usuarioTeamComercial;
	private String nombreUsuarioTeamComercial;
	private String mailUsuarioTeamComercial;
	
	private String razonSocialClientePerfil;
	private Long idClientePerfil;
	
	// Log caracteres especiales removidos
	private String logCaractEspecRemovidos = "";
	
	private boolean errorMapeo = false;
	
	public String getFechaIngresoFormateada(){
		return null;
	}
	
	public boolean isErrorMapeo() {
		return errorMapeo;
	}

	public void setErrorMapeo(boolean errorMapeo) {
		this.errorMapeo = errorMapeo;
	}

	public RegistroAVCDto() {
	}

	public Long getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(Long idRegistro) {
		this.idRegistro = idRegistro;
	}

	public String getAcuerdo() {
		return acuerdo;
	}

	public void setAcuerdo(String acuerdo) {
		this.acuerdo = acuerdo;
	}

	public String getIdAcuerdo() {
		return idAcuerdo;
	}

	public void setIdAcuerdo(String idAcuerdo) {
		this.idAcuerdo = idAcuerdo;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getTipoValor() {
		return tipoValor;
	}

	public void setTipoValor(String tipoValor) {
		this.tipoValor = tipoValor;
	}

	public String getDatosOriginalesRegistroAVC() throws ShivaExcepcion{
		String resultado = "["+DatosWorkflowEnum.getEnumByName("acuerdo").descripcion()+"](" + Constantes.DATOS_MODIFICADOS_VALOR + ": "+this.acuerdo+")";
		resultado=resultado+"["+DatosWorkflowEnum.getEnumByName("codigoCliente").descripcion()+"](" + Constantes.DATOS_MODIFICADOS_VALOR + ": "+this.codigoCliente+")";
		resultado=resultado+"["+DatosWorkflowEnum.getEnumByName("importe").descripcion()+"](" + Constantes.DATOS_MODIFICADOS_VALOR + ": "+this.importe+")";
		return resultado;
	}

	public BigDecimal getImporteParaComparar() {
		return importeParaComparar;
	}

	public void setImporteParaComparar(BigDecimal importeParaComparar) {
		this.importeParaComparar = importeParaComparar;
	}

	public String getEstadoFormateado() {
		return estadoFormateado;
	}

	public void setEstadoFormateado(String estadoFormateado) {
		this.estadoFormateado = estadoFormateado;
	}

	public String getTipoValorFormateado() {
		return tipoValorFormateado;
	}

	public void setTipoValorFormateado(String tipoValorFormateado) {
		this.tipoValorFormateado = tipoValorFormateado;
	}
	
	public String getBancoDeposito() {
		return bancoDeposito;
	}

	public void setBancoDeposito(String bancoDeposito) {
		this.bancoDeposito = bancoDeposito;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getCodigoClienteFormateado(){
		return (Validaciones.isNullOrEmpty(codigoCliente))?"-":this.codigoCliente;
	}
	
	public boolean getEsDeposito(){
		return (this instanceof DepositoDto);
	}
	public boolean getEsInterdeposito(){
		return (this instanceof InterdepositoDto);
	}
	public boolean getEsTransferencia(){
		return (this instanceof TransferenciaDto);
	}
	public String getEsValorReversion(){
		if (this instanceof ValorPorReversionDto) {
			return SiNoEnum.SI.getDescripcion();
		} else {
			return "-";
		}
	}
	
	public String getImporteFormateado(){
		return Utilidad.formatCurrency(this.importe, 2);
	}

	public String getObservacionAnulacion() {
		return observacionAnulacion;
	}

	public void setObservacionAnulacion(String observacionAnulacion) {
		this.observacionAnulacion = observacionAnulacion;
	}

	public boolean isEsEstadoPendConfirmar() {
		return esEstadoPendConfirmar;
	}

	public void setEsEstadoPendConfirmar(boolean esEstadoPendConfirmar) {
		this.esEstadoPendConfirmar = esEstadoPendConfirmar;
	}

	public String getObservacionConfirmarAnulacion() {
		return observacionConfirmarAnulacion;
	}

	public void setObservacionConfirmarAnulacion(
			String observacionConfirmarAnulacion) {
		this.observacionConfirmarAnulacion = observacionConfirmarAnulacion;
	}

	public String getIdRegistroSelected() {
		return idRegistroSelected;
	}

	public void setIdRegistroSelected(String idRegistroSelected) {
		this.idRegistroSelected = idRegistroSelected;
	}

	public String getConciliacionesSelected() {
		return conciliacionesSelected;
	}

	public void setConciliacionesSelected(String conciliacionesSelected) {
		this.conciliacionesSelected = conciliacionesSelected;
	}

	/**
	 * @return the observacionesConfirmarRechazarAltaDeValoresAPartirDeRegistroAVC
	 */
	public String getObservacionesConfirmarRechazarAltaDeValoresAPartirDeRegistroAVC() {
		return observacionesConfirmarRechazarAltaDeValoresAPartirDeRegistroAVC;
	}

	/**
	 * @param observacionesConfirmarRechazarAltaDeValoresAPartirDeRegistroAVC the observacionesConfirmarRechazarAltaDeValoresAPartirDeRegistroAVC to set
	 */
	public void setObservacionesConfirmarRechazarAltaDeValoresAPartirDeRegistroAVC(
			String observacionesConfirmarRechazarAltaDeValoresAPartirDeRegistroAVC) {
		this.observacionesConfirmarRechazarAltaDeValoresAPartirDeRegistroAVC = observacionesConfirmarRechazarAltaDeValoresAPartirDeRegistroAVC;
	}
	
	public String getTipoDto() {
		return tipoDto;
	}

	public void setTipoDto(String tipoDto) {
		this.tipoDto = tipoDto;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getAnalistaTeamComercial() {
		return analistaTeamComercial;
	}

	public void setAnalistaTeamComercial(String analistaTeamComercial) {
		this.analistaTeamComercial = analistaTeamComercial;
	}

	public String getFechaAltaFormateadaSoloDia() {
		return fechaAltaFormateadaSoloDia;
	}

	public void setFechaAltaFormateadaSoloDia(String fechaAltaFormateadaSoloDia) {
		this.fechaAltaFormateadaSoloDia = fechaAltaFormateadaSoloDia;
	}

	public String getFechaDepositoFormateada() {
		return fechaDeposito;
	}

	public void setFechaDepositoFormateada(String fechaDeposito) {
		this.fechaDeposito = fechaDeposito;
	}

	public String getAnalistaDerivacion() {
		return analistaDerivacion;
	}

	public void setAnalistaDerivacion(String analistaDerivacion) {
		this.analistaDerivacion = analistaDerivacion;
	}
	
	public String getCodigoOrganismo() {
		return "-";
	}
	public String getErrorAltaInterdeposito() {
		return "-";
	}
	public String getFechaVencimientoFormateado() {
		return "-";
	}
	public String getFechaErrorFormateado() {
		return "-";
	}
	public String getBancoOrigenFormateado() {
		return "-";
	}
	public String getReferenciaValorFormateado() {
		return referenciaDelValorFormateado;
	}
	public void setReferenciaValorFormateado(String referenciaDelValorFormateado) {
		this.referenciaDelValorFormateado = referenciaDelValorFormateado;
	}
	public String getCuitFormateado(){
		return "-";
	}
	public String getObservacionesFormateado(){
		return "-";
	}

	public String getFechaDeposito() {
		return fechaDeposito;
	}

	public void setFechaDeposito(String fechaDeposito) {
		this.fechaDeposito = fechaDeposito;
	}

	public String getFechaDerivacionFormateada() {
		return fechaDerivacionFormateada;
	}

	public void setFechaDerivacionFormateada(String fechaDerivacionFormateada) {
		this.fechaDerivacionFormateada = fechaDerivacionFormateada;
	}

	public String getFechaAltaDesdeFormateada() {
		return fechaAltaDesdeFormateada;
	}

	public void setFechaAltaDesdeFormateada(String fechaAltaDesdeFormateada) {
		this.fechaAltaDesdeFormateada = fechaAltaDesdeFormateada;
	}

	public String getFechaAltaHastaFormateada() {
		return fechaAltaHastaFormateada;
	}

	public void setFechaAltaHastaFormateada(String fechaAltaHastaFormateada) {
		this.fechaAltaHastaFormateada = fechaAltaHastaFormateada;
	}

	public String getFechaDerivacionDesdeFormateada() {
		return fechaDerivacionDesdeFormateada;
	}

	public void setFechaDerivacionDesdeFormateada(
			String fechaDerivacionDesdeFormateada) {
		this.fechaDerivacionDesdeFormateada = fechaDerivacionDesdeFormateada;
	}

	public String getFechaDerivacionHastaFormateada() {
		return fechaDerivacionHastaFormateada;
	}

	public void setFechaDerivacionHastaFormateada(
			String fechaDerivacionHastaFormateada) {
		this.fechaDerivacionHastaFormateada = fechaDerivacionHastaFormateada;
	}

	public String getNroBoletaFiltro() {
		return nroBoletaFiltro;
	}

	public void setNroBoletaFiltro(String nroBoletaFiltro) {
		this.nroBoletaFiltro = nroBoletaFiltro;
	}

	public String getReferenciaDelValorFiltro() {
		return referenciaDelValorFiltro;
	}

	public void setReferenciaDelValorFiltro(String referenciaDelValorFiltro) {
		this.referenciaDelValorFiltro = referenciaDelValorFiltro;
	}

	public String getNroBoletaFormateado() {
		return nroBoletaFormateado;
	}
	public void setNroBoletaFormateado(String nroBoletaFormateado) {
		this.nroBoletaFormateado = nroBoletaFormateado;
	}

	public String getReferenciaDelValorFormateado() {
		return referenciaDelValorFormateado;
	}

	public void setReferenciaDelValorFormateado(String referenciaDelValorFormateado) {
		this.referenciaDelValorFormateado = referenciaDelValorFormateado;
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

	public String getUsuarioAlta() {
		return usuarioAlta;
	}

	public void setUsuarioAlta(String usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}

	public String getNombreUsuarioAlta() {
		return nombreUsuarioAlta;
	}
	
	public void setNombreUsuarioAlta(String nombreUsuarioAlta) {
		this.nombreUsuarioAlta = nombreUsuarioAlta;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	public String getObservaciones() {
		return observaciones;
	}

	public String getMailUsuarioAlta() {
		return mailUsuarioAlta;
	}

	public void setMailUsuarioAlta(String mailUsuarioAlta) {
		this.mailUsuarioAlta = mailUsuarioAlta;
	}
	
	public String getUsuarioTeamComercial() {
		return usuarioTeamComercial;
	}

	public String getNuevaObservacion() {
		return nuevaObservacion;
	}

	public void setUsuarioTeamComercial(String usuarioTeamComercial) {
		this.usuarioTeamComercial = usuarioTeamComercial;
	}

	public void setNuevaObservacion(String nuevaObservacion) {
		this.nuevaObservacion = nuevaObservacion;
	}

	public String getRegistrosAVCAAnularSelected() {
		return registrosAVCAAnularSelected;
	}

	public void setRegistrosAVCAAnularSelected(String registrosAVCAAnularSelected) {
		this.registrosAVCAAnularSelected = registrosAVCAAnularSelected;
	}
	
	public String getNombreUsuarioTeamComercial() {
		return nombreUsuarioTeamComercial;
	}

	public void setNombreUsuarioTeamComercial(String nombreUsuarioTeamComercial) {
		this.nombreUsuarioTeamComercial = nombreUsuarioTeamComercial;
	}

	public String getMailUsuarioTeamComercial() {
		return mailUsuarioTeamComercial;
	}

	public void setMailUsuarioTeamComercial(String mailUsuarioTeamComercial) {
		this.mailUsuarioTeamComercial = mailUsuarioTeamComercial;
	}
	public String getObservacion() {
		return "";
	}

	public String getLogCaractEspecRemovidos() {
		return logCaractEspecRemovidos;
	}

	public void setLogCaractEspecRemovidos(String logCaractEspecRemovidos) {
		this.logCaractEspecRemovidos = logCaractEspecRemovidos;
	}

	public String getRazonSocialClientePerfil() {
		return razonSocialClientePerfil;
	}

	public void setRazonSocialClientePerfil(String razonSocialClientePerfil) {
		this.razonSocialClientePerfil = razonSocialClientePerfil;
	}

	public Long getIdClientePerfil() {
		return idClientePerfil;
	}

	public void setIdClientePerfil(Long idClientePerfil) {
		this.idClientePerfil = idClientePerfil;
	}
	
}
