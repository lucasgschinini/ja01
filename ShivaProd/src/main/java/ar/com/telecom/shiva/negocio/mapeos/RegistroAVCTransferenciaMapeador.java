package ar.com.telecom.shiva.negocio.mapeos;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.negocio.dto.TransferenciaDto;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvc;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvcTransferencia;


public class RegistroAVCTransferenciaMapeador extends Mapeador {
	@Autowired
	private RegistroAVCMapeador registroAVCMapeador;
	
	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvAvcRegistroAvc modelo = (ShvAvcRegistroAvc) vo;
		TransferenciaDto transferenciaDto = new TransferenciaDto(String.valueOf(modelo.getAcuerdo().getIdAcuerdo()));
		Estado estado = modelo.getWorkFlow().getShvWfWorkflowEstado().iterator().next().getEstado();
		transferenciaDto.setIdRegistro(modelo.getIdRegistroAvc());
		transferenciaDto.setAcuerdo(modelo.getAcuerdo().getDescripcion());
		transferenciaDto.setBancoDeposito(modelo.getAcuerdo().getBancoCuenta().getBanco().getDescripcion());
		transferenciaDto.setNumeroCuenta(modelo.getAcuerdo().getBancoCuenta().getCuentaBancaria());
		transferenciaDto.setIdAcuerdo(String.valueOf(modelo.getAcuerdo().getIdAcuerdo()));
		transferenciaDto.setImporte(modelo.getImporte());
		transferenciaDto.setTipoValor(String.valueOf(modelo.getTipoValor().getIdTipoValor()));
		transferenciaDto.setCodigoCliente(String.valueOf(modelo.getCodigoCliente()));
		transferenciaDto.setEstadoFormateado(estado.descripcion());
		transferenciaDto.setTipoValorFormateado(modelo.getTipoValor().getDescripcion());
		transferenciaDto.setEsEstadoPendConfirmar(estado.equals(Estado.AVC_PENDIENTE_CONFIRMAR_ALTA_VALOR));
		transferenciaDto.setNombreArchivo(modelo.getArchivoAvc().getNombreArchivo());
		transferenciaDto.setTimeStamp(String.valueOf(modelo.getWorkFlow().getFechaUltimaModificacion().getTime()));
		
		transferenciaDto.setFechaIngreso(modelo.getTransferencia().getFechaIngreso());
		transferenciaDto.setObservacion(modelo.getTransferencia().getObservacion());
		transferenciaDto.setReferencia(modelo.getTransferencia().getReferencia());
		transferenciaDto.setSucursal(modelo.getTransferencia().getSucursal());
		transferenciaDto.setCodigo(modelo.getTransferencia().getCodigo());
		transferenciaDto.setCuit(modelo.getTransferencia().getCuit());
		
		/*OBSERVACIONES*/
		transferenciaDto.setObservacionAnulacion(modelo.getObservacionAnulacion());
		transferenciaDto.setObservacionConfirmarAnulacion(modelo.getObservacionConfirmarAnulacion());
		transferenciaDto.setObservacionEditarCuit(modelo.getTransferencia().getObservacionEditarCuit());
		transferenciaDto.setObservaciones(modelo.getObservaciones());
		
		return transferenciaDto;
		
	}

	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		TransferenciaDto transferencia = (TransferenciaDto) dto;

		try {
			// Mapeo el registro AVC
			ShvAvcRegistroAvc registroAVCModelo = (ShvAvcRegistroAvc) registroAVCMapeador.map(transferencia, null);
			
			// Mapeo el Transferencia
			ShvAvcRegistroAvcTransferencia registroAVCTransferenciaModelo = new ShvAvcRegistroAvcTransferencia();
			registroAVCTransferenciaModelo.setFechaIngreso(transferencia.getFechaIngreso());
			registroAVCTransferenciaModelo.setObservacion(transferencia.getObservacion());
			registroAVCTransferenciaModelo.setReferencia(transferencia.getReferencia());
			registroAVCTransferenciaModelo.setSucursal(transferencia.getSucursal());
			registroAVCTransferenciaModelo.setCodigo(transferencia.getCodigo());
			registroAVCTransferenciaModelo.setCuit(transferencia.getCuit());
			registroAVCTransferenciaModelo.setObservacionEditarCuit(transferencia.getObservacionEditarCuit());
			
			/*OBSERVACIONES*/
			registroAVCModelo.setObservacionAnulacion(transferencia.getObservacionAnulacion());
			registroAVCModelo.setObservacionConfirmarAnulacion(transferencia.getObservacionConfirmarAnulacion());
			
			registroAVCTransferenciaModelo.setRegistroAvc(registroAVCModelo);
			registroAVCModelo.setTransferencia(registroAVCTransferenciaModelo);
						
			return registroAVCModelo;
		} catch (Exception e) {
			throw new NegocioExcepcion(e);
		}
	}

	public RegistroAVCMapeador getRegistroAVCMapeador() {
		return registroAVCMapeador;
	}

	public void setRegistroAVCMapeador(RegistroAVCMapeador registroAVCMapeador) {
		this.registroAVCMapeador = registroAVCMapeador;
	}

}
