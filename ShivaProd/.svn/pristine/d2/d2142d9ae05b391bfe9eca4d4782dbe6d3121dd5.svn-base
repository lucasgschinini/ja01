package ar.com.telecom.shiva.negocio.mapeos;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.negocio.servicios.IValorMedioPagoServicio;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValoresVista;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorVistaDto;

public class ValorVistaMapeador extends Mapeador {

	@Autowired 
	private IValorMedioPagoServicio valorMedioPagoServicio;
	
	
	public DTO map(Modelo vo) throws NegocioExcepcion {

		ShvValValoresVista valorVista = (ShvValValoresVista) vo;

		ValorVistaDto vistaDto = new ValorVistaDto();
		
		vistaDto.setIdValor(valorVista.getIdValor());
		vistaDto.setFechaIngresoRecibo(valorVista.getFechaIngresoRecibo());
		vistaDto.setNumeroRecibo(valorVista.getNumeroRecibo());
		vistaDto.setFacturaRelacionada(valorVista.getFacturaRelacionada());
		vistaDto.setFechaVencimiento(valorVista.getFechaVencimiento());
		vistaDto.setNumeroConstanciaDeRecepcion(valorVista.getNumeroConstanciaDeRecepcion());
		vistaDto.setFechaTransferencia(valorVista.getFechaTransferencia());
		vistaDto.setNumeroConstanciaDeRetencion(valorVista.getNumeroConstanciaDeRetencion());
		vistaDto.setNumeroChequeAReemplazar(valorVista.getNumeroChequeAReemplazar());
		vistaDto.setDbEnviadaMail(valorVista.getDbEnviadaMail());
		vistaDto.setDbImpresa(valorVista.getDbImpresa());
		vistaDto.setFechaDisponible(valorVista.getFechaDisponible());
		vistaDto.setFechaUltimoEstado(valorVista.getFechaUltimoEstado());
		vistaDto.setFechaDeposito(valorVista.getFechaDeposito());
		vistaDto.setNumeroDocumentoContable(valorVista.getNumeroDocumentoContable());
		vistaDto.setFechaDeAlta(valorVista.getFechaAlta());
		vistaDto.setArchivoDeValoresAConciliar(valorVista.getArchivoDeValoresAConciliar());
		vistaDto.setNumeroValor(valorVista.getNumeroValor());
		vistaDto.setDocumentacionOriginalRecibida(valorVista.getDocumentacionOriginalRecibida());
		vistaDto.setSegmento(valorVista.getSegmento());
		vistaDto.setTipoValor(valorVista.getTipoValor());
		vistaDto.setRazonSocialClienteAgrupador(valorVista.getRazonSocialClientePerfil());
		vistaDto.setEmpresa(valorVista.getEmpresa());
		vistaDto.setBancoDeposito(valorVista.getBancoDeposito());
		vistaDto.setNumeroAcuerdo(valorVista.getIdAcuerdo());
		vistaDto.setEstadoValor(valorVista.getEstadoValor());
		vistaDto.setMotivoSuspension(valorVista.getMotivoSuspension());
		vistaDto.setEjecutivo(valorVista.getEjecutivo());
		vistaDto.setAsistente(valorVista.getAsistente());
		vistaDto.setUsuarioAutorizacion(valorVista.getUsuarioAutorizacion());
		vistaDto.setAnalista(valorVista.getAnalista());
		vistaDto.setCopropietario(valorVista.getCopropietario());
		vistaDto.setImporte(valorVista.getImporte());
		vistaDto.setSaldoDisponible(valorVista.getSaldoDisponible());
		vistaDto.setFechaValor(valorVista.getFechaValor());
		vistaDto.setMotivo(valorVista.getMotivo());
		vistaDto.setOperacionAsociada(valorVista.getOperacionAsociada());
		vistaDto.setObservaciones(valorVista.getObservaciones());
		vistaDto.setCodigoClienteLegado(valorVista.getCodigoClienteLegado());
		vistaDto.setFechaEmision(valorVista.getFechaEmision());
		vistaDto.setCuit(valorVista.getCuit());
		vistaDto.setDescripcionProvincia(valorVista.getDescripcionProvincia());
		vistaDto.setTipoComprobante(valorVista.getTipoComprobante());
		vistaDto.setLetraComprobante(valorVista.getLetraComprobante());
		vistaDto.setNumeroLegalComprobante(valorVista.getNumeroLegalComprobante());
		vistaDto.setOrigen(valorVista.getOrigen());
		vistaDto.setDescripcionBancoOrigen(valorVista.getDescripcionBancoOrigen());
		vistaDto.setNumeroBoleta(valorVista.getNumeroBoleta());
		vistaDto.setNumeroCheque(valorVista.getNumeroCheque());
		vistaDto.setNumeroReferencia(valorVista.getNumeroReferencia());
		vistaDto.setNumeroInterdeposito(valorVista.getNumeroInterdeposito());
		vistaDto.setCodigoOrganismo(valorVista.getCodigoOrganismo());
		vistaDto.setNumeroConstancia(valorVista.getNumeroConstancia());
		vistaDto.setIdEstadoValor(valorVista.getIdEstadoValor());
		vistaDto.setIdTipoRentencionImpuesto(valorVista.getIdTipoRentencionImpuesto());
		vistaDto.setIdTipoValor(valorVista.getIdTipoValor());

		return vistaDto;
	}

	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		return null;
	}
}
