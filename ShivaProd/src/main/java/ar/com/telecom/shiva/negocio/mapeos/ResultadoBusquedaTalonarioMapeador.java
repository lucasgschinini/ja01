package ar.com.telecom.shiva.negocio.mapeos;


import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mapeadores.MapeadorResultadoBusqueda;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusqueda;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaTalonarios;
import ar.com.telecom.shiva.presentacion.bean.dto.TalonarioDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

public class ResultadoBusquedaTalonarioMapeador extends MapeadorResultadoBusqueda {
	
	@Autowired 
	private ILdapServicio ldapServicio;

	public DTO map (VistaSoporteResultadoBusqueda vistaSoporteResultadoBusqueda) throws NegocioExcepcion {
		
		VistaSoporteResultadoBusquedaTalonarios talonarioVista = (VistaSoporteResultadoBusquedaTalonarios) vistaSoporteResultadoBusqueda;
	
		TalonarioDto tal = new TalonarioDto();
		tal.setIdEmpresa(talonarioVista.getIdEmpresa());
		tal.setEmpresa(talonarioVista.getEmpresa());
		tal.setIdTalonario(String.valueOf(talonarioVista.getIdTalonario()));
		tal.setIdSegmento(talonarioVista.getIdSegmento());
		tal.setSegmento(talonarioVista.getSegmento());
		tal.setRangoDesde(Utilidad.rellenarCerosIzquierda(String.valueOf(talonarioVista.getRangoDesde()),Constantes.LONGITUD_NRO_RECIBO));
		tal.setRangoHasta(Utilidad.rellenarCerosIzquierda(String.valueOf(talonarioVista.getRangoHasta()),Constantes.LONGITUD_NRO_RECIBO));
		tal.setEstado(talonarioVista.getEstado());
		tal.setIdEstado(talonarioVista.getIdEstado());
		tal.setUsuarioGestor(talonarioVista.getGestorAsignado());
		tal.setFechaAsignacion(talonarioVista.getFechaAsignacion());
		tal.setFechaAprobacionRendicion(talonarioVista.getFechaAprobacionRendicion());
		tal.setFechaRendicion(talonarioVista.getFechaRendicion());
		tal.setFechaAlta(talonarioVista.getFechaAlta());
		tal.setNroSerie(Utilidad.rellenarCerosIzquierda(String.valueOf(talonarioVista.getNumeroSerie()),Constantes.LONGITUD_NRO_SERIE));
				
		//usuarios de LDAP
		UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(talonarioVista.getUsuarioAlta());
		if(!Validaciones.isNullOrEmpty(talonarioVista.getUsuarioAlta())){
				tal.setUsuarioAlta(usuarioLdap != null?usuarioLdap.getTuid():"");
				tal.setNombreUsuarioAlta(usuarioLdap != null?usuarioLdap.getNombreCompleto():"");
				tal.setMailUsuarioAlta(usuarioLdap != null?usuarioLdap.getMail():"");	
		}
		if(!Validaciones.isNullOrEmpty(talonarioVista.getUsuarioAsignacion())){
				usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(talonarioVista.getUsuarioAsignacion());
				tal.setUsuarioAsignacion(usuarioLdap != null?usuarioLdap.getTuid():"");
				tal.setNombreUsuarioAsignacion(usuarioLdap != null?usuarioLdap.getNombreCompleto():"");
				tal.setMailUsuarioAsignacion(usuarioLdap != null?usuarioLdap.getMail():"");		
		}
		if(!Validaciones.isNullOrEmpty(talonarioVista.getUsuarioRendicion())){
				usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(talonarioVista.getUsuarioRendicion());
				tal.setUsuarioRendicion(usuarioLdap != null?usuarioLdap.getTuid():"");
				tal.setNombreUsuarioRendicion(usuarioLdap != null?usuarioLdap.getNombreCompleto():"");
				tal.setMailUsuarioRendicion(usuarioLdap != null?usuarioLdap.getMail():"");		
		}
				
		if(!Validaciones.isNullOrEmpty(talonarioVista.getUsuarioAprobacionRendicion())){
				usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(talonarioVista.getUsuarioAprobacionRendicion());
				tal.setUsuarioAprobacionRendicion(usuarioLdap != null?usuarioLdap.getTuid():"");
				tal.setNombreUsuarioAprobacionRendicion(usuarioLdap != null?usuarioLdap.getNombreCompleto():"");
				tal.setMailUsuarioAprobacionRendicion(usuarioLdap != null?usuarioLdap.getMail():"");	
		}
				
	
		return tal;
		}
	
}
	
