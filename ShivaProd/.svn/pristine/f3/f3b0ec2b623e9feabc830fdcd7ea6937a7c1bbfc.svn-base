package ar.com.telecom.shiva.negocio.mapeos;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.dto.RegistroAVCDto;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.dao.IAcuerdoDao;
import ar.com.telecom.shiva.persistencia.dao.ITipoValorDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvc;

public class RegistroAVCMapeador extends Mapeador {

	@Autowired
	private IAcuerdoDao acuerdoDao;
	@Autowired
	private ITipoValorDao tipoValorDao;
	
	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvAvcRegistroAvc modelo = (ShvAvcRegistroAvc) vo;
		
		RegistroAVCDto registroDto = new RegistroAVCDto();
		Estado estado = modelo.getWorkFlow().getShvWfWorkflowEstado().iterator().next().getEstado();
		registroDto.setIdRegistro(modelo.getIdRegistroAvc());
		registroDto.setAcuerdo(String.valueOf(modelo.getAcuerdo().getDescripcion()));
		registroDto.setIdAcuerdo(String.valueOf(modelo.getAcuerdo().getIdAcuerdo()));		
		registroDto.setImporte(modelo.getImporte());
		registroDto.setImporteParaComparar(modelo.getImporte());
		registroDto.setCodigoCliente(String.valueOf(modelo.getCodigoCliente()));
		registroDto.setTimeStamp(registroDto.getTimeStampDTO());
		registroDto.setEstadoFormateado(estado.descripcion());
		registroDto.setEsEstadoPendConfirmar(estado.equals(Estado.AVC_PENDIENTE_CONFIRMAR_ALTA_VALOR));
		registroDto.setTipoValorFormateado(modelo.getTipoValor().getDescripcion());
		registroDto.setNombreArchivo(modelo.getArchivoAvc().getNombreArchivo());
		return registroDto;
	}

	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		RegistroAVCDto registro = (RegistroAVCDto) dto;

		ShvAvcRegistroAvc registroAVCModelo = (ShvAvcRegistroAvc) (vo != null ? vo
				: new ShvAvcRegistroAvc());
		try {
			
			// Mapeo el registro AVC
			registroAVCModelo.setAcuerdo(acuerdoDao.buscarAcuerdo((Validaciones.isObjectNull(registro.getIdAcuerdo()))?registro.getAcuerdo():registro.getIdAcuerdo()));
			registroAVCModelo.setTipoValor(tipoValorDao.buscarTipoValor(registro.getTipoValor()));
			registroAVCModelo.setImporte(registro.getImporte());
			registroAVCModelo.setCodigoCliente(registro.getCodigoCliente());
			
			return registroAVCModelo;
		} catch (Exception e) {
			throw new NegocioExcepcion(e);
		}
	}

	public IAcuerdoDao getAcuerdoDao() {
		return acuerdoDao;
	}

	public void setAcuerdoDao(IAcuerdoDao acuerdoDao) {
		this.acuerdoDao = acuerdoDao;
	}

}
