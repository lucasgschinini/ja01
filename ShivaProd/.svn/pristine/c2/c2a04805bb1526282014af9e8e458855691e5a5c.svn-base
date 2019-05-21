package ar.com.telecom.shiva.negocio.mapeos;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mapeadores.MapeadorResultadoBusqueda;
import ar.com.telecom.shiva.negocio.dto.ValorPorReversionDto;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteRegistrosAvcSinConciliar;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusqueda;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

/**
 * 
 * @author fabio.giaquinta.ruiz
 * 10/09/2014
 */
public class ResultadoBusquedaReversionSinConciliarMapeador extends MapeadorResultadoBusqueda {

	@Autowired 
	private ILdapServicio ldapServicio;
	
	public DTO map(VistaSoporteResultadoBusqueda vo) throws NegocioExcepcion {
	
		VistaSoporteRegistrosAvcSinConciliar vista = (VistaSoporteRegistrosAvcSinConciliar) vo;
		ValorPorReversionDto registroAvcSinConciliarDto = new ValorPorReversionDto();
		
		if(vista != null){			
				registroAvcSinConciliarDto.setEsEstadoPendConfirmar(vista.getEstado().equals(Estado.REV_PENDIENTE_CONFIRMAR.name()));
			
			registroAvcSinConciliarDto.setIdRegistro(vista.getIdRegistro());
			
			if(vista.getAcuerdoFormateado() != null){
				registroAvcSinConciliarDto.setAcuerdo(vista.getAcuerdoFormateado());
			}
			if(vista.getCodigoCliente() != null){
				registroAvcSinConciliarDto.setCodigoCliente(vista.getCodigoCliente());
			}
			if(vista.getNumeroBoleta() != null){
				registroAvcSinConciliarDto.setNroBoletaFormateado(vista.getNumeroBoleta().toString());
			}
			if(vista.getNumeroReferencia() != null){
				registroAvcSinConciliarDto.setReferenciaDelValorFormateado(vista.getNumeroReferencia().toString());
			}
			if(vista.getTipoValorFormateado() != null){
				registroAvcSinConciliarDto.setTipoValorFormateado(vista.getTipoValorFormateado());
			}
			if(vista.getTipoDto() != null){	
				registroAvcSinConciliarDto.setTipoDto(vista.getTipoDto());
			}
			if(vista.getImporte() != null){
				registroAvcSinConciliarDto.setImporte(vista.getImporte());
			}
			if(vista.getEstadoFormateado() != null){
				registroAvcSinConciliarDto.setEstadoFormateado(vista.getEstadoFormateado());
			}
			if(vista.getNombreArchivo() != null){
				registroAvcSinConciliarDto.setNombreArchivo(vista.getNombreArchivo());
			}
			if(vista.getRazonSocial() != null){	
				registroAvcSinConciliarDto.setRazonSocial(vista.getRazonSocial());
			}
			if(vista.getAnalistaTeamComercial() != null){	
				registroAvcSinConciliarDto.setAnalistaTeamComercial(vista.getAnalistaTeamComercial());
			}
			if(vista.getFechaAltaFormateada() != null){	
				registroAvcSinConciliarDto.setFechaAltaFormateadaSoloDia(vista.getFechaAltaFormateada());
			}
			if(vista.getFechaDepositoFormateada() != null){	
				registroAvcSinConciliarDto.setFechaDepositoFormateada(vista.getFechaDepositoFormateada());
			}
			if(vista.getFechaDerivacion() != null){	
				registroAvcSinConciliarDto.setFechaDerivacionFormateada(vista.getFechaDerivacion());
			}
			if(vista.getAnalistaDerivacion() != null){	
				registroAvcSinConciliarDto.setAnalistaDerivacion(vista.getAnalistaDerivacion());
			}
			//usuarios de LDAP
			UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(vista.getAnalistaDerivacion());
			registroAvcSinConciliarDto.setUsuarioAlta(usuarioLdap != null?usuarioLdap.getTuid():"");
			registroAvcSinConciliarDto.setNombreUsuarioAlta(usuarioLdap != null?usuarioLdap.getNombreCompleto():"");
			registroAvcSinConciliarDto.setMailUsuarioAlta(usuarioLdap != null?usuarioLdap.getMail():"");
			
			UsuarioLdapDto usuarioLdapTeamComercial = ldapServicio.buscarUsuarioPorUidEnMemoria(vista.getAnalistaTeamComercial());
			registroAvcSinConciliarDto.setUsuarioTeamComercial(usuarioLdapTeamComercial != null?usuarioLdapTeamComercial.getTuid():"");
			registroAvcSinConciliarDto.setNombreUsuarioTeamComercial(usuarioLdapTeamComercial != null?usuarioLdapTeamComercial.getNombreCompleto():"");
			registroAvcSinConciliarDto.setMailUsuarioTeamComercial(usuarioLdapTeamComercial != null?usuarioLdapTeamComercial.getMail():"");
		
		}
		return registroAvcSinConciliarDto;
		
	}

}