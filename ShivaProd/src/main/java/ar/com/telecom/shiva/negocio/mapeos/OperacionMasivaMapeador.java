package ar.com.telecom.shiva.negocio.mapeos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.dao.IEmpresaDao;
import ar.com.telecom.shiva.persistencia.dao.IMotivoCobroDao;
import ar.com.telecom.shiva.persistencia.dao.IOperacionMasivaAdjuntoDao;
import ar.com.telecom.shiva.persistencia.dao.ISegmentoDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasOperacionMasiva;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.OperacionMasivaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

public class OperacionMasivaMapeador extends Mapeador {
	
	@Autowired private IEmpresaDao empresaDao;
	@Autowired private ISegmentoDao segmentoDao;
	@Autowired private IMotivoCobroDao motivoDao;
	@Autowired private ILdapServicio ldapServicio;
	@Autowired private IOperacionMasivaAdjuntoDao operacionMasivaAdjuntoDao;

	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvMasOperacionMasiva modelo = (ShvMasOperacionMasiva) vo;
		OperacionMasivaDto operacionMasivaDto = new OperacionMasivaDto();
		
		try {
			//Para la concurrencia
			operacionMasivaDto.setFechaUltimaModificacion(modelo.getWorkFlow().getFechaUltimaModificacion());
			operacionMasivaDto.setTimeStamp(operacionMasivaDto.getTimeStampDTO());
			
			operacionMasivaDto.setIdOperacionMasiva(modelo.getIdOperacionMasiva());
			operacionMasivaDto.setIdEmpresa(modelo.getEmpresa().getIdEmpresa());
			operacionMasivaDto.setEmpresa(modelo.getEmpresa().getDescripcion());
			operacionMasivaDto.setIdSegmento(modelo.getSegmento().getIdSegmento());
			operacionMasivaDto.setSegmento(modelo.getSegmento().getDescripcion());
	
			Estado estado = modelo.getWorkFlow().getEstado();
			operacionMasivaDto.setEstadoOperacionMasiva(estado);
			operacionMasivaDto.setDescripcionEstado(estado.descripcion());
	
			operacionMasivaDto.setTipoOperacionMasiva(modelo.getTipoOperacionMasiva());
			
			/* Analista */
			UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(modelo.getIdAnalista());
			operacionMasivaDto.setIdAnalista(usuarioLdap != null?usuarioLdap.getTuid():"");
			operacionMasivaDto.setNombreAnalista(usuarioLdap != null?usuarioLdap.getNombreCompleto():"");
			operacionMasivaDto.setMailAnalista(usuarioLdap != null?usuarioLdap.getMail():"");
			operacionMasivaDto.setUrlFotoAnalista(operacionMasivaDto.urlFotoUsuario(modelo.getIdAnalista()));
					
			/* Copropietario */
			if(!Validaciones.isNullEmptyOrDash(modelo.getIdCopropietario())){
				usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(modelo.getIdCopropietario());
				operacionMasivaDto.setIdCopropietario(usuarioLdap!=null?modelo.getIdCopropietario():"");
				operacionMasivaDto.setNombreCopropietario(usuarioLdap.getNombreCompleto());
				operacionMasivaDto.setMailCopropietario(usuarioLdap.getMail());
				operacionMasivaDto.setUrlFotoCopropietario(operacionMasivaDto.urlFotoUsuario(modelo.getIdCopropietario()));			
			}
			
			operacionMasivaDto.setIdMotivo(!Validaciones.isObjectNull(modelo.getMotivo())?String.valueOf(modelo.getMotivo().getIdMotivoCobro()):"");
			operacionMasivaDto.setMotivo(!Validaciones.isObjectNull(modelo.getMotivo())?String.valueOf(modelo.getMotivo().getDescripcion()):"");
			
			operacionMasivaDto.setTipoOperacionMasiva(modelo.getTipoOperacionMasiva());
			operacionMasivaDto.setFechaAlta(operacionMasivaDto.getFechaAlta());
			operacionMasivaDto.setFechaUltimaModificacion(modelo.getFechaModificacion());
			operacionMasivaDto.setUsuarioModificacion(modelo.getUsuarioModificacion());
			operacionMasivaDto.setObservacion(modelo.getObservacion());
	//		operacionMasivaDto.setObservacionAnterior(observacionAnterior);
			
			List<ComprobanteDto> listaComprobantes = new ArrayList<ComprobanteDto>();
			List<ShvDocDocumentoAdjunto> listaAdjuntos = operacionMasivaAdjuntoDao.buscarComprobantesxIdOperacionMasiva(modelo.getIdOperacionMasiva());
			if(Validaciones.isCollectionNotEmpty(listaAdjuntos)){
				for (ShvDocDocumentoAdjunto docAdjunto : listaAdjuntos) {
					ComprobanteDto comprobante = new ComprobanteDto();
					comprobante.setId(docAdjunto.getIdValorAdjunto());
					comprobante.setIdComprobante(docAdjunto.getIdValorAdjunto());
					comprobante.setNombreArchivo(docAdjunto.getNombreArchivo());
					comprobante.setDescripcionArchivo(docAdjunto.getDescripcion());
					comprobante.setDocumento(docAdjunto.getArchivoAdjunto());
					comprobante.setUsuarioCreacion(docAdjunto.getUsuarioCreacion());
					comprobante.setFechaAlta(docAdjunto.getFechaCreacion());
					
					listaComprobantes.add(comprobante);
				}
			}
			operacionMasivaDto.setListaComprobantes(listaComprobantes);
		
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		return operacionMasivaDto;
	}
	
	
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		//PropertyTranslator.translate(dto);
		OperacionMasivaDto operacionMasivaDto = (OperacionMasivaDto) dto;
		
		ShvMasOperacionMasiva operacionMasivaModelo = (ShvMasOperacionMasiva)
				(vo != null ? vo : new ShvMasOperacionMasiva());
		try{
			operacionMasivaModelo.setIdAnalista(operacionMasivaDto.getIdAnalista());
			
			operacionMasivaModelo.setEmpresa(empresaDao.buscar(operacionMasivaDto.getIdEmpresa()));
			operacionMasivaModelo.setSegmento(segmentoDao.buscarSegmento(operacionMasivaDto.getIdSegmento()));
			if(!Validaciones.isNullOrEmpty(operacionMasivaDto.getIdMotivo())){
				operacionMasivaModelo.setMotivo(motivoDao.buscarMotivoCobro(operacionMasivaDto.getIdMotivo()));
			}
			if(!Validaciones.isNullOrEmpty(operacionMasivaDto.getIdCopropietario())){
				operacionMasivaModelo.setIdCopropietario(operacionMasivaDto.getIdCopropietario());
			} else {
				operacionMasivaModelo.setIdCopropietario(null);
			}
			
			operacionMasivaModelo.setTipoOperacionMasiva(operacionMasivaDto.getTipoOperacionMasiva());
			if(!Validaciones.isObjectNull(operacionMasivaDto.getFechaAlta())){
				operacionMasivaModelo.setFechaCreacion(operacionMasivaDto.getFechaAlta());
			}
			operacionMasivaModelo.setFechaModificacion(operacionMasivaDto.getFechaUltimaModificacion());
			operacionMasivaModelo.setUsuarioModificacion(operacionMasivaDto.getUsuarioModificacion());
			operacionMasivaModelo.setObservacion(operacionMasivaDto.getObservacion());
			
		}catch(Exception e){
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return operacionMasivaModelo;
	}

}
