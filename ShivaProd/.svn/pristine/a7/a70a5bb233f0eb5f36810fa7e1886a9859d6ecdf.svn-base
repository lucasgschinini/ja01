package ar.com.telecom.shiva.negocio.batch.mapeos;

import ar.com.telecom.shiva.base.dto.Batch;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.TipoAcuerdoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.MapeadorBatch;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.batch.bean.InterdepositoBatch;
import ar.com.telecom.shiva.negocio.batch.bean.TransferenciaBatch;
import ar.com.telecom.shiva.negocio.dto.TransferenciaDto;

public class TransferenciaMapeadorBatch extends MapeadorBatch {

	public Batch map(DTO dto) throws NegocioExcepcion {
		TransferenciaDto transferenciaDto = (TransferenciaDto) dto;
		
		TransferenciaBatch transferenciaBatch = new TransferenciaBatch(transferenciaDto.getAcuerdo());
		transferenciaBatch.setCodigoCliente(transferenciaDto.getCodigoCliente());
		transferenciaBatch.setImporte(transferenciaDto.getImporte());
		transferenciaBatch.setTipoValor(transferenciaDto.getTipoValor());
		transferenciaBatch.setObservacionEditarCuit(transferenciaDto.getObservacionEditarCuit());
		transferenciaBatch.setFechaIngreso(transferenciaDto.getFechaIngreso());
		transferenciaBatch.setSucursal(transferenciaDto.getSucursal());
		transferenciaBatch.setReferencia(transferenciaDto.getReferencia());
		transferenciaBatch.setCodigo(transferenciaDto.getCodigo());
		transferenciaBatch.setObservacion(transferenciaDto.getObservacion());
		transferenciaBatch.setCuit(transferenciaDto.getCuit());
		transferenciaBatch.setSubtotal(transferenciaDto.getSubtotal());
		
		return transferenciaBatch;
	}

	public DTO map(Batch batch) throws NegocioExcepcion {
		TransferenciaDto transferenciaDto = null;
		if (batch instanceof TransferenciaBatch){
			TransferenciaBatch transferenciaBatch = (TransferenciaBatch) batch;
			
			transferenciaDto = new TransferenciaDto(transferenciaBatch.getIdAcuerdo());
			transferenciaDto.setCodigoCliente(transferenciaBatch.getCodigoCliente());
			transferenciaDto.setImporte(transferenciaBatch.getImporte());
			transferenciaDto.setTipoValor(transferenciaBatch.getTipoValor());
			transferenciaDto.setObservacionEditarCuit(transferenciaBatch.getObservacionEditarCuit());
			transferenciaDto.setFechaIngreso(transferenciaBatch.getFechaIngreso());
			transferenciaDto.setSucursal(transferenciaBatch.getSucursal());
			transferenciaDto.setReferencia(transferenciaBatch.getReferencia());
			transferenciaDto.setCodigo(transferenciaBatch.getCodigo());
			transferenciaDto.setObservacion(transferenciaBatch.getObservacion());
			transferenciaDto.setCuit(transferenciaBatch.getCuit());
			transferenciaDto.setSubtotal(transferenciaBatch.getSubtotal());
			transferenciaDto.setLogCaractEspecRemovidos(transferenciaBatch.getLogCaractEspecRemovidos());
		} else {
			InterdepositoBatch interdepositoBatch = (InterdepositoBatch) batch;
			
			transferenciaDto = new TransferenciaDto(interdepositoBatch.getAcuerdo());
			transferenciaDto.setCodigoCliente(interdepositoBatch.getCodigoCliente());
			transferenciaDto.setImporte(interdepositoBatch.getImporte());
			transferenciaDto.setTipoValor(String.valueOf(TipoValorEnum.TRANSFERENCIA.getIdTipoValor()));
			transferenciaDto.setFechaIngreso(interdepositoBatch.getFechaIngreso());
			if (!Validaciones.isNullOrEmpty(interdepositoBatch.getSucursal())){
				transferenciaDto.setSucursal(Long.valueOf(interdepositoBatch.getSucursal().trim()));
			}
			
			transferenciaDto.setReferencia(Long.valueOf(interdepositoBatch.getComprobante().trim()));

			if (TipoAcuerdoEnum.INTERDEPOSITO_87.descripcion().equals(interdepositoBatch.getAcuerdo()) && !Validaciones.isNullOrEmpty(interdepositoBatch.getCodigoOperacion())){
				transferenciaDto.setCodigo(Long.valueOf(interdepositoBatch.getCodigoOperacion().trim()));
			}
			if (TipoAcuerdoEnum.INTERDEPOSITO_94.descripcion().equals(interdepositoBatch.getAcuerdo()) && interdepositoBatch.getCodOpBanco() != null){
				transferenciaDto.setCodigo(interdepositoBatch.getCodOpBanco());
			}
			transferenciaDto.setObservacion("-");
			transferenciaDto.setLogCaractEspecRemovidos(interdepositoBatch.getLogCaractEspecRemovidos());
		}
		return transferenciaDto;
	}

}
