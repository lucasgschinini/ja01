package ar.com.telecom.shiva.negocio.mapeos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.comparador.ComparatorCodigoOperacionExternaDto;
import ar.com.telecom.shiva.base.comparador.ComparatorOrdenDescobroTransaccionDtoInvertido;
import ar.com.telecom.shiva.base.comparador.ComparatorShvWfWorkflowMarca;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoIdReversionPadreEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.IDescobroServicio;
import ar.com.telecom.shiva.persistencia.dao.IDescobroDao;
import ar.com.telecom.shiva.persistencia.dao.IEmpresaDao;
import ar.com.telecom.shiva.persistencia.dao.IMotivoDescobroDao;
import ar.com.telecom.shiva.persistencia.dao.ISegmentoDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobroCodigoOperacionExterna;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobroDocumentoRelacionado;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowMarca;
import ar.com.telecom.shiva.presentacion.bean.dto.CodigoOperacionExternaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroDocumentoRelacionadoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroOperacionesRelacionadasDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroTransaccionDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
/*
 * @author u572487 Guido.Settecerze
 */
public class DescobroMapeador extends Mapeador {
	
	@Autowired private IEmpresaDao empresaDao;
	@Autowired private ISegmentoDao segmentoDao;
	@Autowired private IMotivoDescobroDao motivoDescobroDao;
	@Autowired private ILdapServicio ldapServicio;
	@Autowired private IDescobroDao descobroDao;
	@Autowired private IDescobroServicio descobroServicio;
	@Autowired private DescobroDocRelacionadosMapeador descobroDocRelacionadosMapeo;
	
	@Override
	public DTO map(Modelo vo) throws NegocioExcepcion {
		try {
			ShvCobDescobro modelo = (ShvCobDescobro) vo;
			
			DescobroDto descobroDto = new DescobroDto();
			
//			descobroDto.setId(modelo.getIdDescobro());
			descobroDto.setIdDescobro(modelo.getIdDescobro());
			if(!Validaciones.isObjectNull(modelo.getIdDescobroPadre())){
				descobroDto.setIdDescobroPadre(new Long(modelo.getIdDescobroPadre()));
			}
			if(TipoIdReversionPadreEnum.OTRO.equals(modelo.getTipoIdReversionPadreEnum())){
				if(!Validaciones.isObjectNull(modelo.getIdDescobroPadre())){
					descobroDto.setIdDescobroPadre(modelo.getIdDescobroPadre());
					descobroDto.setIdDescobroPadreFormateadoOtro(modelo.getIdDescobroPadre().toString());
					descobroDto.setIdDescobroPadreFormateado(TipoIdReversionPadreEnum.OTRO.getDescripcionCorta());
				}
			}else if (TipoIdReversionPadreEnum.SUGERIDO.equals(modelo.getTipoIdReversionPadreEnum())){
				if(!Validaciones.isObjectNull(modelo.getIdDescobroPadre())){
					descobroDto.setIdDescobroPadre(modelo.getIdDescobroPadre());
					descobroDto.setIdDescobroPadreFormateado(modelo.getIdDescobroPadre().toString());
					descobroDto.setIdDescobroPadreFormateadoOtro("");
				}
			}
			descobroDto.setIdCobro(modelo.getIdCobro());
			descobroDto.setIdLegajo(modelo.getIdLegajo());
			if (modelo.getIdDescobro()!=null) {
				descobroDto.setIdDescobroFormateado(modelo.getIdDescobro().toString());
			}
			if(modelo.getOperacion()!=null){
				descobroDto.setIdOperacionDescobro(modelo.getOperacion().getIdOperacion());
			}
			descobroDto.setFechaUltimaModificacion(modelo.getWorkflow().getFechaUltimaModificacion());
			descobroDto.setTimeStamp(descobroDto.getTimeStampDTO());
			
			if(!Validaciones.isObjectNull(modelo.getEmpresa())){
				descobroDto.setIdEmpresa(modelo.getEmpresa().getIdEmpresa());
				descobroDto.setEmpresa(modelo.getEmpresa().getDescripcion());
			}
			if(!Validaciones.isObjectNull(modelo.getSegmento())){
				descobroDto.setIdSegmento(modelo.getSegmento().getIdSegmento());
				descobroDto.setSegmento(modelo.getSegmento().getDescripcion());
			}
			if(!Validaciones.isObjectNull(modelo.getMotivo())){
				descobroDto.setIdMotivoReversion(String.valueOf(modelo.getMotivo().getIdMotivoDescobro()));
				descobroDto.setDescripcionMotivoReversion(modelo.getMotivo().getDescripcion());
			}
			descobroDto.setFechaAlta(modelo.getFechaAlta());
			descobroDto.setUsuarioAlta(modelo.getUsuarioAlta());
			descobroDto.setFechaDerivacion(modelo.getFechaDerivacion());
//			descobroDto.setUsuarioDerivacion(modelo.getUsuarioDerivacion());
			descobroDto.setFechaUltimaModificacion(modelo.getFechaUltimaModificacion());
			descobroDto.setUsuarioModificacion(modelo.getUsuarioUltimaModificacion());
			descobroDto.setFechaReversion(modelo.getFechaReversion());
			descobroDto.setObservacion(modelo.getObservacion());
					
			descobroDto.setEstado(modelo.getWorkflow().getEstado());
			
			if(!modelo.getWorkflow().getWorkflowEstado().getWorkflowMarcas().isEmpty()) {
				
				Set<ShvWfWorkflowMarca> marcas = modelo.getWorkflow().getWorkflowEstado().getWorkflowMarcas();
				TreeSet<ShvWfWorkflowMarca> workflowMarcas = new TreeSet<ShvWfWorkflowMarca>(new ComparatorShvWfWorkflowMarca());
				workflowMarcas.addAll(marcas);
				
				for (ShvWfWorkflowMarca marca : marcas) {
					
					if(MarcaEnum.SIMULACION_BATCH_FINALIZADA_CON_ERROR.equals(marca.getMarca()) 
							|| MarcaEnum.SIMULACION_BATCH_FINALIZADA_CON_EXITO.equals(marca.getMarca())) {
						descobroDto.setSimulado(true);
					}
				}
				
				descobroDto.setMarca(workflowMarcas.first().getMarca());
				descobroDto.setDescripcionMarca(workflowMarcas.first().getMarca().descripcion());
				
			}
			
			/* Analista */
			UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(modelo.getIdAnalista());
			descobroDto.setIdAnalista(modelo.getIdAnalista());
			descobroDto.setNombreAnalista(usuarioLdap != null?usuarioLdap.getNombreCompleto():"");
			descobroDto.setMailAnalista(usuarioLdap != null?usuarioLdap.getMail():"");
			
			/* Copropietario */
			usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(modelo.getIdCopropietario());
			descobroDto.setIdCopropietario(usuarioLdap!=null?modelo.getIdCopropietario():"");
			descobroDto.setNombreCopropietario(usuarioLdap!=null?usuarioLdap.getNombreCompleto():"");
			descobroDto.setMailCopropietario(usuarioLdap != null?usuarioLdap.getMail():"");
			
			List<ComprobanteDto> listaComprobantes = new ArrayList<ComprobanteDto>();
			List<ShvDocDocumentoAdjunto> listaAdjuntos = descobroDao.buscarAdjuntosDelDescobroOnline(modelo.getIdDescobro());
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
			
			descobroDto.setListaComprobantes(listaComprobantes);
		
			List<DescobroTransaccionDto> buscarTransacciones = descobroServicio.buscarTransacciones(modelo.getIdDescobro());
			Set<DescobroTransaccionDto> buscarTransaccionesSet = new TreeSet<DescobroTransaccionDto>(new ComparatorOrdenDescobroTransaccionDtoInvertido());
			buscarTransaccionesSet.addAll(buscarTransacciones);
			descobroDto.setTransacciones(buscarTransaccionesSet);
			
			List<DescobroOperacionesRelacionadasDto> buscarOperacionesRelacionadas = descobroServicio.buscarOperacionesRelacionadas(modelo.getIdDescobro());
			Set<DescobroOperacionesRelacionadasDto> buscarOperacionesRelacionadasSet = new HashSet<DescobroOperacionesRelacionadasDto>(buscarOperacionesRelacionadas);
			if(!buscarOperacionesRelacionadasSet.isEmpty()) {
				descobroDto.setOperacionesRelacionadas(buscarOperacionesRelacionadasSet);
			}
			
			if (Validaciones.isCollectionNotEmpty(modelo.getDocumentosRelacionados())) {
				Set<DescobroDocumentoRelacionadoDto> listaDocRelacionadosDto = new HashSet<DescobroDocumentoRelacionadoDto>();
				for (ShvCobDescobroDocumentoRelacionado docuRelac : modelo.getDocumentosRelacionados()) {
					listaDocRelacionadosDto.add((DescobroDocumentoRelacionadoDto) descobroDocRelacionadosMapeo.map(docuRelac));
				}
				descobroDto.setDocumentosRelacionados(listaDocRelacionadosDto);
			}
			
			//MonedaOperacion
			descobroDto.setMonedaOperacion(modelo.getMonedaOperacion() != null? modelo.getMonedaOperacion().getSigno2() : null);
			
			
			if (Validaciones.isCollectionNotEmpty(modelo.getCodigosOperacionesExternas())) {
				descobroDto.setListaCodigoDeOperacionesExternas(new ArrayList<CodigoOperacionExternaDto>());
				for (ShvCobDescobroCodigoOperacionExterna shvCobDescobroCodigoOperacionExterna : modelo.getCodigosOperacionesExternas()) {
					
					String signoImporte = Validaciones.isObjectNull(shvCobDescobroCodigoOperacionExterna.getMonedaImporte()) ? MonedaEnum.PES.getSigno2() : shvCobDescobroCodigoOperacionExterna.getMonedaImporte().getSigno2();
					
					descobroDto.getListaCodigoDeOperacionesExternas().add(
						new CodigoOperacionExternaDto(
							shvCobDescobroCodigoOperacionExterna.getIdCobDescobroCodOperExt(),
							shvCobDescobroCodigoOperacionExterna.getDescobro().getIdDescobro().toString(),
							shvCobDescobroCodigoOperacionExterna.getCodigoOperacionExterna(),							
							shvCobDescobroCodigoOperacionExterna.getReferente(),
							shvCobDescobroCodigoOperacionExterna.getNumeroTransaccion(),
							Validaciones.isObjectNull(shvCobDescobroCodigoOperacionExterna.getImporte()) ? null : signoImporte + Utilidad.formatCurrency(shvCobDescobroCodigoOperacionExterna.getImporte(), false, true).toString(),
							Validaciones.isObjectNull(shvCobDescobroCodigoOperacionExterna.getSistema()) ? null : shvCobDescobroCodigoOperacionExterna.getSistema() 
					));
				}
				Collections.sort(
					descobroDto.getListaCodigoDeOperacionesExternas(), new ComparatorCodigoOperacionExternaDto()
				);
			}
			
			if (!Validaciones.isObjectNull(modelo.getFechaAprobAplicacionManualRefOperacionesExternas())) {
				descobroDto.setFechaAprobAplicacionManualRefOperacionesExternas(
					modelo.getFechaAprobAplicacionManualRefOperacionesExternas()
				);
				descobroDto.setUsuarioAprobAplicacionManualRefOperacionesExternas(
					modelo.getUsuarioAprobAplicacionManualRefOperacionesExternas()
				);
				descobroDto.setNombreApellidoAprobAplicacionManualRefOperacionesExternas(
					modelo.getNombreApellidoAprobAplicacionManualRefOperacionesExternas()
				);
				
			} 
			if (!Validaciones.isObjectNull(modelo.getFechaConfirmacionAplicacionManualRefCaja())) {
				descobroDto.setFechaConfirmacionAplicacionManualRefCaja(
					modelo.getFechaConfirmacionAplicacionManualRefCaja()
				);
				descobroDto.setUsuarioAprobAplicacionManualRefCaja(
					modelo.getUsuarioAprobAplicacionManualRefCaja()
				);
				descobroDto.setNombreApellidoAprobAplicacionManualRefCaja(
					modelo.getNombreApellidoAprobAplicacionManualRefCaja()
				);
			}
			
			return descobroDto;
		
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	@Override
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		DescobroDto descobroDto = (DescobroDto) dto;
		ShvCobDescobro descobroModelo = (ShvCobDescobro)
				(vo != null ? vo : new ShvCobDescobro());
		try {
			descobroModelo.setIdDescobro(descobroDto.getIdDescobro());
			if (!Validaciones.isObjectNull(descobroDto.getIdDescobroPadre())) {
				if (!Validaciones.isObjectNull(descobroDto.getIdDescobroPadre())) {
					descobroModelo.setIdDescobroPadre(new Long(descobroDto.getIdDescobroPadre()));
				}
			}
			if (TipoIdReversionPadreEnum.OTRO.getDescripcionCorta().equals(descobroDto.getIdDescobroPadreFormateado())){
				descobroModelo.setTipoIdReversionPadreEnum(TipoIdReversionPadreEnum.OTRO);
				if (!Validaciones.isNullEmptyOrDash(descobroDto.getIdDescobroPadreFormateadoOtro())) {
					descobroModelo.setIdDescobroPadre(new Long(descobroDto.getIdDescobroPadreFormateadoOtro()));
				}
			} else if (!Validaciones.isNullOrEmpty(descobroDto.getIdDescobroPadreFormateado())) {
				descobroModelo.setTipoIdReversionPadreEnum(TipoIdReversionPadreEnum.SUGERIDO);
				if (!Validaciones.isNullEmptyOrDash(descobroDto.getIdDescobroPadreFormateado())) {
					descobroModelo.setIdDescobroPadre(new Long(descobroDto.getIdDescobroPadreFormateado()));
				}
			}
//			if(!Validaciones.isObjectNull(descobroDto.getIdDescobroPadreFormateado())){
//				descobroModelo.setIdDescobroPadre(new Long(descobroDto.getIdDescobroPadreFormateado()));
//				descobroModelo.setTipoIdReversionPadreEnum(TipoIdReversionPadreEnum.OTRO);
//			}
			descobroModelo.setIdCobro(descobroDto.getIdCobro());
			descobroModelo.setIdLegajo(descobroDto.getIdLegajo());
			descobroModelo.setEmpresa(empresaDao.buscar(descobroDto.getIdEmpresa()));
			descobroModelo.setSegmento(segmentoDao.buscarSegmento(descobroDto.getIdSegmento()));
			descobroModelo.setIdAnalista(descobroDto.getIdAnalista());
			descobroModelo.setIdCopropietario(descobroDto.getIdCopropietario());
			descobroModelo.setNombreApellidoAnalista(descobroDto.getNombreAnalista());
			if (!Validaciones.isNullOrEmpty(descobroDto.getIdCopropietario())) {
				descobroModelo.setNombreApellidoCopropietario(ldapServicio.buscarUsuarioPorUid(descobroDto.getIdCopropietario()).getNombreCompleto());
			}
			if (!Validaciones.isNullOrEmpty(descobroDto.getIdMotivoReversion())) {
				descobroModelo.setMotivo(motivoDescobroDao.buscarMotivoDescobro(descobroDto.getIdMotivoReversion()));
			} else {
				descobroModelo.setMotivo(null);
			}
			descobroModelo.setUsuarioUltimaModificacion(descobroDto.getUsuarioModificacion());
			descobroModelo.setFechaUltimaModificacion(descobroDto.getFechaUltimaModificacion());
			if (Validaciones.isObjectNull(descobroModelo.getFechaAlta())
					&& !Validaciones.isObjectNull(descobroDto.getFechaAlta())) {
				descobroModelo.setFechaAlta(descobroDto.getFechaAlta());
			}
			if (Validaciones.isObjectNull(descobroModelo.getUsuarioAlta())
					&& !Validaciones.isObjectNull(descobroDto.getUsuarioAlta())) {
				descobroModelo.setUsuarioAlta(descobroDto.getUsuarioAlta());
			}
			descobroModelo.setObservacion(descobroDto.getObservacion());
			//MonedaOperacion
			descobroModelo.setMonedaOperacion(MonedaEnum.getEnumBySigno2(descobroDto.getMonedaOperacion()));
		
			return descobroModelo;

		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	public IMotivoDescobroDao getMotivoDescobroDao() {
		return motivoDescobroDao;
	}

	public void setMotivoDescobroDao(IMotivoDescobroDao motivoDescobroDao) {
		this.motivoDescobroDao = motivoDescobroDao;
	}

}
