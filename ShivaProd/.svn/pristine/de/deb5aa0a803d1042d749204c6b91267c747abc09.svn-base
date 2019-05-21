package ar.com.telecom.shiva.negocio.batch.mapeos;

import ar.com.telecom.shiva.base.dto.Batch;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.MapeadorBatch;
import ar.com.telecom.shiva.negocio.batch.bean.InterdepositoBatch;
import ar.com.telecom.shiva.negocio.dto.InterdepositoDto;

public class InterdepositoMapeadorBatch extends MapeadorBatch {

	public Batch map(DTO dto) throws NegocioExcepcion {
		InterdepositoDto interdepositoDto = (InterdepositoDto) dto;
		InterdepositoBatch interdepositoBatch = new InterdepositoBatch(interdepositoDto.getAcuerdo());
		interdepositoBatch.setCodigoCliente(interdepositoDto.getCodigoCliente());
		interdepositoBatch.setImporte(interdepositoDto.getImporte());
		interdepositoBatch.setTipoValor(interdepositoDto.getTipoValor());
		
		interdepositoBatch.setFechaValor(interdepositoDto.getFechaValor());
		interdepositoBatch.setFechaIngreso(interdepositoDto.getFechaIngreso());
		interdepositoBatch.setConcepto(interdepositoDto.getConcepto());
		interdepositoBatch.setCodigoOperacion(interdepositoDto.getCodigoOperacion());
		interdepositoBatch.setDeposito(interdepositoDto.getDeposito());
		interdepositoBatch.setComprobante(interdepositoDto.getComprobante());
		interdepositoBatch.setCodigoOrganismo(interdepositoDto.getCodigoOrganismo());
		interdepositoBatch.setCodigoInterdeposito(interdepositoDto.getCodigoInterdeposito());
		interdepositoBatch.setSucursal(interdepositoDto.getSucursal());
		interdepositoBatch.setCodOpBanco(interdepositoDto.getCodOpBanco());
		interdepositoBatch.setPcc(interdepositoDto.getPcc());
		
		return interdepositoBatch;
	}

	public DTO map(Batch batch) throws NegocioExcepcion {
		InterdepositoBatch interdepositoBatch = (InterdepositoBatch) batch;
		InterdepositoDto interdepositoDto = new InterdepositoDto(interdepositoBatch.getAcuerdo());
		interdepositoDto.setCodigoCliente(interdepositoBatch.getCodigoCliente());
		interdepositoDto.setImporte(interdepositoBatch.getImporte());
		interdepositoDto.setTipoValor(interdepositoBatch.getTipoValor());
		
		interdepositoDto.setFechaValor(interdepositoBatch.getFechaValor());
		interdepositoDto.setFechaIngreso(interdepositoBatch.getFechaIngreso());
		interdepositoDto.setConcepto(interdepositoBatch.getConcepto());
		interdepositoDto.setCodigoOperacion(interdepositoBatch.getCodigoOperacion());
		interdepositoDto.setDeposito(interdepositoBatch.getDeposito());
		interdepositoDto.setComprobante(interdepositoBatch.getComprobante());
		interdepositoDto.setCodigoOrganismo(interdepositoBatch.getCodigoOrganismo());
		interdepositoDto.setCodigoInterdeposito(interdepositoBatch.getCodigoInterdeposito());
		interdepositoDto.setSucursal(interdepositoBatch.getSucursal());
		interdepositoDto.setCodOpBanco(interdepositoBatch.getCodOpBanco());
		interdepositoDto.setPcc(interdepositoBatch.getPcc());
		interdepositoDto.setLogCaractEspecRemovidos(interdepositoBatch.getLogCaractEspecRemovidos());
		
		return interdepositoDto;
	}
	
}
