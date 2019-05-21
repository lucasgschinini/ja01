package ar.com.telecom.shiva.negocio.servicios.validacion;

import java.util.Date;
import java.util.List;

import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.BoletaConValorFiltro;

public interface IBoletaConValorValidacionServicio {

	void validacionEmpresa(String empresa) throws ValidacionExcepcion;

	void validacionSegmento(String segmento) throws ValidacionExcepcion;

	void validacionCodCliente(String codCliente) throws ValidacionExcepcion;
	
	void validacionCodClienteEsNumerico(String codCliente) throws ValidacionExcepcion;

	void validacionRazonSocial(String razonSocial) throws ValidacionExcepcion;

	void validacionEmail(String email) throws ValidacionExcepcion;

	void validacionEmailCheck(String email) throws ValidacionExcepcion;

	void validacionAnalista(String analista) throws ValidacionExcepcion;

	void validacionCopropietario(String copropietario)
			throws ValidacionExcepcion;

	void validacionImporte(String importe) throws ValidacionExcepcion;

	void validacionAcuerdo(String acuerdo) throws ValidacionExcepcion;

	void validacionBancoDeposito(String bancoDeposito)
			throws ValidacionExcepcion;

	void validacionBancoOrigen(String bancoDeposito) throws ValidacionExcepcion;

	void validacionNroCuenta(String nroCuenta) throws ValidacionExcepcion;

	void validacionOperacionAsociada(String operacionAsociada)
			throws ValidacionExcepcion;

	void validacionNumeroDeCheque(String operacionAsociada)
			throws ValidacionExcepcion;

	void validacionFechaVencimiento(String operacionAsociada)
			throws ValidacionExcepcion;

	void validacionTipoValor(String tipoValor) throws ValidacionExcepcion;

	void validacionReciboPreImpreso(String reciboPreImpreso)
			throws ValidacionExcepcion;

	void validacionFechaRecibo(String fechaRecibo) throws ValidacionExcepcion;
	
	void validacionFechaRecibo(String fechaRecibo, boolean obligatorio) throws ValidacionExcepcion;

	void validacionOrigen(String origen) throws ValidacionExcepcion;

	void validacionObservaciones(String observaciones)
			throws ValidacionExcepcion;

	void validacionObservacionMail(String observacionMail,
			Boolean checkEnviarMailBoleta) throws ValidacionExcepcion;

	void validacionCheckRadioSeleccionado(Boolean checkImprimirBoleta,
			Boolean checkEnviarMailBoleta) throws ValidacionExcepcion;

	void validacionFechaAltaDesdeHasta(BoletaConValorFiltro boletaFiltro)
			throws ValidacionExcepcion;

	void validacionImporteDesdeHasta(BoletaConValorFiltro boletaFiltro)
			throws ValidacionExcepcion;

	void validacionObservacionMail(String observacionMail)
			throws ValidacionExcepcion;

	void validacionMotivo(String motivo) throws ValidacionExcepcion;

	void validacionCodClienteSiebel(String codClienteSiebel) throws ValidacionExcepcion;
	
	void validacionCodClienteSiebelNoValidado(String codCliente, String codClienteSiebel) throws ValidacionExcepcion;

	void validacionModificacion(ValorDto boletaDto) throws ValidacionExcepcion;

	// AVISO
	void validacionNumeroDocumentoContable(String numeroDocumentoContable)
			throws ValidacionExcepcion;

	void validacionFechaDeposito(String fechaDeposito)
			throws ValidacionExcepcion;

	void validacionCuitIIBB(String cuit) throws ValidacionExcepcion;

	void validacionCuit(String cuit) throws ValidacionExcepcion;

	void validacionProvincia(String provincia) throws ValidacionExcepcion;
	
	void validacionFechaEmisionPorProvincia(Date fechaEmision, String idProvincia) throws ValidacionExcepcion;

	void validacionFechaTransferencia(String fechaTransferencia)
			throws ValidacionExcepcion;

	void validacionNumeroReferencia(String numeroReferencia)
			throws ValidacionExcepcion;

	void validacionTipoImpuesto(String tipoImpuesto) throws ValidacionExcepcion;

	void validacionNumeroConstancia(String nroConstancia)
			throws ValidacionExcepcion;

	void validacionFechaEmision(String fechaEmision) throws ValidacionExcepcion;

	void validacionNumeroBoleta(String numeroBoleta) throws ValidacionExcepcion;
	
	void validacionNumeroBoletaNumerico(String numeroBoleta) throws ValidacionExcepcion;
	
	void validacionOperacionAsociadaObligatoria(String operacionAsociada)
			throws ValidacionExcepcion;

	void validacionNumeroLegalComprobante(String numeroLegalComprobante)
			throws ValidacionExcepcion;
	
	void validacionNumeroLegalComprobanteObligatorio(String numeroLegalComprobante)
			throws ValidacionExcepcion;

	void validacionListaComprobantes(List<ComprobanteDto> listaComprobantes)
			throws ValidacionExcepcion;

	void validacionFechaDesde(String fechaDesde) throws ValidacionExcepcion;

	void validacionFechaHasta(String fechaHasta) throws ValidacionExcepcion;

	void validacionFechaDesdeHasta(String fechaDesde, String fechaHasta)
			throws ValidacionExcepcion;

	void validacionImporteDesdeHastaFormato(String importe)
			throws ValidacionExcepcion;

	void validacionImporteDesdeHasta(String importeDesde, String importeHasta)
			throws ValidacionExcepcion;

	void validacionFechaInterdeposito(String fechaInterdeposito)
			throws ValidacionExcepcion;

	void validacionNumeroInterdeposito(String numeroInterdepositoCdif)
			throws ValidacionExcepcion;

	void validacionCodOrganismo(String codOrganismo)
			throws ValidacionExcepcion;

	void validacionLetraComprobante(String letraComprobante)
			throws ValidacionExcepcion;

	void validacionCuitObligatorio(String cuit) throws ValidacionExcepcion;
	
	void validacionObservacionConfirmacion(String observacionConfirmacion, boolean esObligatorio) throws ValidacionExcepcion;
	
	void validarNumeroBoletaFiltro(String numero) throws ValidacionExcepcion;
	
	void validarReferenciaValorFiltro(String numero) throws ValidacionExcepcion;
	
	void validarIdValorFiltro(String numero) throws ValidacionExcepcion;
	
	void validarIdCobroShivaFiltro(String operacionTransaccion) throws ValidacionExcepcion;
	
	void validarReciboPreImpreso(String nroRecibo) throws ValidacionExcepcion;
	
	void validarFechaIngresoRecibo(String fechaIngresoRecibo) throws ValidacionExcepcion;
	public void validacionFechaEmisionCheque(String fechaEmision)	throws ValidacionExcepcion;
		
}