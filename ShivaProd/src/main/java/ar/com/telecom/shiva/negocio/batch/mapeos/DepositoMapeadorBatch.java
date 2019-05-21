package ar.com.telecom.shiva.negocio.batch.mapeos;

import ar.com.telecom.shiva.base.dto.Batch;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.MapeadorBatch;
import ar.com.telecom.shiva.negocio.batch.bean.DepositoBatch;
import ar.com.telecom.shiva.negocio.dto.DepositoDto;

public class DepositoMapeadorBatch extends MapeadorBatch {

	
	public Batch map(DTO dto) throws NegocioExcepcion {
		DepositoDto depositoDto = (DepositoDto) dto;
		
		DepositoBatch depositoBatch = new DepositoBatch(depositoDto.getAcuerdo());
		depositoBatch.setCodigoCliente(depositoDto.getCodigoCliente());
		depositoBatch.setImporte(depositoDto.getImporte());
		depositoBatch.setTipoValor(depositoDto.getTipoValor());
		
		// Deposito
		depositoBatch.setIdRecInstrumento(depositoDto.getIdRecInstrumento());
		depositoBatch.setRend(depositoDto.getRend());
		depositoBatch.setFechaPago(depositoDto.getFechaPago());
		depositoBatch.setNroCliente(depositoDto.getNroCliente());
		depositoBatch.setFormaPago(depositoDto.getFormaPago());
		depositoBatch.setEstadoAcreditacion(depositoDto.getEstadoAcreditacion());
		depositoBatch.setFechaAcreditacion(depositoDto.getFechaAcreditacion());
		depositoBatch.setNroBoleta(depositoDto.getNroBoleta());
		depositoBatch.setSucursalDeposito(depositoDto.getSucursalDeposito());
		depositoBatch.setNombreSucursal(depositoDto.getNombreSucursal());
		depositoBatch.setGrupoAcreedor(depositoDto.getGrupoAcreedor());
		depositoBatch.setNombreCliente(depositoDto.getNombreCliente());
		depositoBatch.setCodigoRechazo(depositoDto.getCodigoRechazo());
		
		// Cheque
		depositoBatch.setCodigoBanco(depositoDto.getCodigoBanco());
		depositoBatch.setSucursal(depositoDto.getSucursal());
		depositoBatch.setCodigoPostal(depositoDto.getCodigoPostal());
		depositoBatch.setNumeroCheque(depositoDto.getNumeroCheque());
		depositoBatch.setCuentaEmisora(depositoDto.getCuentaEmisora());
		depositoBatch.setFechaVencimiento(depositoDto.getFechaVencimiento());
		
		return depositoBatch;
	}

	public DTO map(Batch batch) throws NegocioExcepcion {
		DepositoBatch depositoBatch = (DepositoBatch) batch;
		
		DepositoDto depositoDto = new DepositoDto(depositoBatch.getAcuerdo());
		depositoDto.setCodigoCliente(depositoBatch.getCodigoCliente());
		depositoDto.setImporte(depositoBatch.getImporte());
		depositoDto.setTipoValor(depositoBatch.getTipoValor());
		
		// Deposito
		depositoDto.setIdRecInstrumento(depositoBatch.getIdRecInstrumento());
		depositoDto.setRend(depositoBatch.getRend());
		depositoDto.setFechaPago(depositoBatch.getFechaPago());
		depositoDto.setNroCliente(depositoBatch.getNroCliente());
		depositoDto.setFormaPago(depositoBatch.getFormaPago());
		depositoDto.setEstadoAcreditacion(depositoBatch.getEstadoAcreditacion());
		depositoDto.setFechaAcreditacion(depositoBatch.getFechaAcreditacion());
		depositoDto.setNroBoleta(depositoBatch.getNroBoleta());
		depositoDto.setSucursalDeposito(depositoBatch.getSucursalDeposito());
		depositoDto.setNombreSucursal(depositoBatch.getNombreSucursal());
		depositoDto.setGrupoAcreedor(depositoBatch.getGrupoAcreedor());
		depositoDto.setNombreCliente(depositoBatch.getNombreCliente());
		depositoDto.setCodigoRechazo(depositoBatch.getCodigoRechazo());
		
		// Cheque
		depositoDto.setCodigoBanco(depositoBatch.getCodigoBanco());
		depositoDto.setSucursal(depositoBatch.getSucursal());
		depositoDto.setCodigoPostal(depositoBatch.getCodigoPostal());
		depositoDto.setNumeroCheque(depositoBatch.getNumeroCheque());
		depositoDto.setCuentaEmisora(depositoBatch.getCuentaEmisora());
		depositoDto.setFechaVencimiento(depositoBatch.getFechaVencimiento());
		
		// Log caracteres especiales removidos
		depositoDto.setLogCaractEspecRemovidos(depositoBatch.getLogCaractEspecRemovidos());
		
		return depositoDto;
	}

}
