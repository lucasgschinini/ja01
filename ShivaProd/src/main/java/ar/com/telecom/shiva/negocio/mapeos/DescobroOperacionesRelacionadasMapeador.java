package ar.com.telecom.shiva.negocio.mapeos;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mapeadores.MapeadorResultadoBusqueda;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusqueda;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaDescobroOperacionesRelacionadas;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroOperacionesRelacionadasDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

public class DescobroOperacionesRelacionadasMapeador extends MapeadorResultadoBusqueda{

	
	@Autowired 
	private ILdapServicio ldapServicio;

	@Override
	public DTO map(VistaSoporteResultadoBusqueda vistaSoporteResultadoBusqueda)
			throws NegocioExcepcion{

		VistaSoporteResultadoBusquedaDescobroOperacionesRelacionadas operacionesRelacionadas =  (VistaSoporteResultadoBusquedaDescobroOperacionesRelacionadas) vistaSoporteResultadoBusqueda;
		DescobroOperacionesRelacionadasDto dto = new DescobroOperacionesRelacionadasDto();
		if(!Validaciones.isNullEmptyOrDash(operacionesRelacionadas.getSistema())){
			dto.setSistema(SistemaEnum.getEnumByName(operacionesRelacionadas.getSistema()).getDescripcion());
		}
		dto.setIdOperacionRelacionada(operacionesRelacionadas.getIdOperacionRelacionada());
		dto.setIdOperacionCobroPadre(operacionesRelacionadas.getIdOperacionCobroPadre());
		dto.setIdTransaccionCobroPadre(operacionesRelacionadas.getIdTransaccionCobroPadre());
		if(!Validaciones.isNullEmptyOrDash(operacionesRelacionadas.getTipoDocumentoRelacionado())){
			dto.setTipoDocumentoRelacionado(TipoComprobanteEnum.getEnumByValor(operacionesRelacionadas.getTipoDocumentoRelacionado()).getDescripcion());
		}
		dto.setNroDocumentoRelacionado(operacionesRelacionadas.getNroDocumentoRelacionado());
		dto.setMotivoCobro(operacionesRelacionadas.getMotivoCobro());
		dto.setIdCliente(operacionesRelacionadas.getIdCliente());
		if(!Validaciones.isNullOrEmpty(operacionesRelacionadas.getEstadoCobro())) {
			dto.setEstadoCobro(Estado.getEnumByName(operacionesRelacionadas.getEstadoCobro()).descripcion());
		}
		
		if(!Validaciones.isNullOrEmpty(operacionesRelacionadas.getSubEstadoCobro())) {
			dto.setSubEstadoCobro(operacionesRelacionadas.getSubEstadoCobro());
		}
		if(!Validaciones.isNullOrEmpty(operacionesRelacionadas.getFechaUltimoEstado())) {
			dto.setFechaUltimoEstado(operacionesRelacionadas.getFechaUltimoEstado());
		}
		if(!Validaciones.isNullOrEmpty(operacionesRelacionadas.getAnalista())) {
			UsuarioLdapDto analista = ldapServicio.buscarUsuarioPorUid(operacionesRelacionadas.getAnalista());
			if(!Validaciones.isObjectNull(analista)){
				dto.setAnalista(analista.getNombreCompleto());
			}
		}
		if(!Validaciones.isNullOrEmpty(operacionesRelacionadas.getCopropietario())) {
			UsuarioLdapDto copropietario = ldapServicio.buscarUsuarioPorUid(operacionesRelacionadas.getCopropietario());
			if(!Validaciones.isObjectNull(copropietario)){
				dto.setCopropietario(copropietario.getNombreCompleto());
			}
		}
		
		if(!Validaciones.isNullOrEmpty(operacionesRelacionadas.getAnalistaTeamComercial())) {
			UsuarioLdapDto analistateamcomercial = ldapServicio.buscarUsuarioPorUid(operacionesRelacionadas.getAnalistaTeamComercial());
			if(!Validaciones.isObjectNull(analistateamcomercial)){
				dto.setAnalistaTeamComercial(analistateamcomercial.getNombreCompleto());
			}
		}
		if(!Validaciones.isNullOrEmpty(operacionesRelacionadas.getImporteFormateado())) {
			dto.setImporteFormateado(Utilidad.formatCurrency(new BigDecimal(operacionesRelacionadas.getImporteFormateado()), true, true));
		}
		
		dto.setFechaAltaOp((operacionesRelacionadas.getFechaAlta()));
		
		if(!Validaciones.isNullOrEmpty(operacionesRelacionadas.getFechaDerivacion())) {
			dto.setFechaDerivacion(operacionesRelacionadas.getFechaDerivacion());
		}
		if(!Validaciones.isNullOrEmpty(operacionesRelacionadas.getFechaAutorizacionReferente())) {
			dto.setFechaAutorizacionReferente(operacionesRelacionadas.getFechaAutorizacionReferente());
		}
		if(!Validaciones.isNullOrEmpty(operacionesRelacionadas.getFechaImputacion())) {
			dto.setFechaImputacion(operacionesRelacionadas.getFechaImputacion());
		}
		
		return dto;
	}
}
