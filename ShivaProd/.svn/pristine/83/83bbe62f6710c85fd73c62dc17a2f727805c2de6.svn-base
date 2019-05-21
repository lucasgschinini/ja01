package ar.com.telecom.shiva.negocio.mapeos;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.IEmpresaDao;
import ar.com.telecom.shiva.persistencia.dao.IGestorDao;
import ar.com.telecom.shiva.persistencia.dao.ISegmentoDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvTalTalonario;
import ar.com.telecom.shiva.presentacion.bean.dto.TalonarioDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

public class TalonarioMapeador extends Mapeador {
	
	private IEmpresaDao empresaDao;
	private ISegmentoDao segmentoDao;
	private IGestorDao gestorDao;
	
	@Autowired 
	private ILdapServicio ldapServicio;
	
	public DTO map(Modelo modelo) throws NegocioExcepcion {
		
		ShvTalTalonario talonarioModelo = (ShvTalTalonario) modelo;
		TalonarioDto talonarioDto = new TalonarioDto(talonarioModelo.getIdTalonario());
		
		
		talonarioDto.setIdTalonario(String.valueOf(talonarioModelo.getIdTalonario()));
		//CONCURRENCIA
		talonarioDto.setFechaUltimaModificacion(talonarioModelo.getWorkflow().getFechaUltimaModificacion());
		talonarioDto.setTimeStamp(talonarioDto.getTimeStampDTO());
		
		talonarioDto.setIdEmpresa(talonarioModelo.getEmpresa().getIdEmpresa());
		talonarioDto.setEmpresa(talonarioModelo.getEmpresa().getDescripcion());
		talonarioDto.setIdSegmento(talonarioModelo.getSegmento().getIdSegmento());
		talonarioDto.setSegmento(talonarioModelo.getSegmento().getDescripcion());
		talonarioDto.setRangoDesde(Utilidad.rellenarCerosIzquierda(String.valueOf(talonarioModelo.getRangoDesde()),Constantes.LONGITUD_NRO_RECIBO));
		talonarioDto.setRangoHasta(Utilidad.rellenarCerosIzquierda(String.valueOf(talonarioModelo.getRangoHasta()),Constantes.LONGITUD_NRO_RECIBO));
		talonarioDto.setIdEstado(talonarioModelo.getWorkflow().getShvWfWorkflowEstado().iterator().next().getEstado().name());
		talonarioDto.setEstado(talonarioModelo.getWorkflow().getShvWfWorkflowEstado().iterator().next().getEstado().descripcion());
		talonarioDto.setNroSerie(Utilidad.rellenarCerosIzquierda(String.valueOf(talonarioModelo.getNroSerie()),Constantes.LONGITUD_NRO_SERIE));
		talonarioDto.setFechaAlta(talonarioModelo.getFechaAlta());
		talonarioDto.setFechaAsignacion(Utilidad.formatDateAndTimeFull(talonarioModelo.getFechaAsignacion()));
		talonarioDto.setFechaRendicion(Utilidad.formatDateAndTimeFull(talonarioModelo.getFechaRendicion()));
		talonarioDto.setFechaAprobacionRendicion(Utilidad.formatDateAndTimeFull(talonarioModelo.getFechaAprobacionRendicion()));
		
		if ( !Validaciones.isNullOrEmpty(String.valueOf(talonarioModelo.getUsuarioGestor()))){
			talonarioDto.setIdUsuarioGestor(String.valueOf(talonarioModelo.getUsuarioGestor().getIdGestor()));
			talonarioDto.setUsuarioGestor(talonarioModelo.getUsuarioGestor().getNombreYApellido());
		}
		//usuarios de LDAP
		UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(talonarioModelo.getUsuarioAlta());
		talonarioDto.setUsuarioAlta(usuarioLdap != null?usuarioLdap.getTuid():"");
		talonarioDto.setNombreUsuarioAlta(usuarioLdap != null?usuarioLdap.getNombreCompleto():"");
		talonarioDto.setMailUsuarioAlta(usuarioLdap != null?usuarioLdap.getMail():"");		
		
		usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(talonarioModelo.getUsuarioAsignacion());
		talonarioDto.setUsuarioAsignacion(usuarioLdap != null?usuarioLdap.getTuid():"");
		talonarioDto.setNombreUsuarioAsignacion(usuarioLdap != null?usuarioLdap.getNombreCompleto():"");
		talonarioDto.setMailUsuarioAsignacion(usuarioLdap != null?usuarioLdap.getMail():"");		
		
		usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(talonarioModelo.getUsuarioRendicion());
		talonarioDto.setUsuarioRendicion(usuarioLdap != null?usuarioLdap.getTuid():"");
		talonarioDto.setNombreUsuarioRendicion(usuarioLdap != null?usuarioLdap.getNombreCompleto():"");
		talonarioDto.setMailUsuarioRendicion(usuarioLdap != null?usuarioLdap.getMail():"");		
		
		usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(talonarioModelo.getUsuarioAprobacionRendicion());
		talonarioDto.setUsuarioAprobacionRendicion(usuarioLdap != null?usuarioLdap.getTuid():"");
		talonarioDto.setNombreUsuarioAprobacionRendicion(usuarioLdap != null?usuarioLdap.getNombreCompleto():"");
		talonarioDto.setMailUsuarioAprobacionRendicion(usuarioLdap != null?usuarioLdap.getMail():"");		
		
		return talonarioDto;
	}
	
	
	public Modelo map(DTO dto, Modelo modelo) throws NegocioExcepcion {
		
		TalonarioDto talonarioDto = (TalonarioDto) dto;
		ShvTalTalonario talonarioModelo;
		if (modelo != null){
			 talonarioModelo = (ShvTalTalonario) modelo;
		}else{
			 talonarioModelo = new ShvTalTalonario();
		}
		try{
			talonarioModelo.setEmpresa(empresaDao.buscar(talonarioDto.getIdEmpresa()));
			talonarioModelo.setSegmento(segmentoDao.buscarSegmento(talonarioDto.getIdSegmento()));
			talonarioModelo.setNroSerie(Integer.valueOf(talonarioDto.getNroSerie()));
			if (!Validaciones.isNullOrEmpty(talonarioDto.getIdUsuarioGestor()))
				talonarioModelo.getUsuarioGestor().setIdGestor((Integer.valueOf(talonarioDto.getIdUsuarioGestor())));
			if (!Validaciones.isNullOrEmpty(talonarioDto.getUsuarioGestor()))
				talonarioModelo.getUsuarioGestor().setNombreYApellido((talonarioDto.getUsuarioGestor()));
			talonarioModelo.setRangoDesde(Integer.valueOf(talonarioDto.getRangoDesde()));
			talonarioModelo.setRangoHasta(Integer.valueOf(talonarioDto.getRangoHasta()));

			if (!Validaciones.isNullOrEmpty(talonarioDto.getFechaAsignacion()))
				talonarioModelo.setFechaAsignacion(Utilidad.parseDateAndTimeString(talonarioDto.getFechaAsignacion()));
			if (!Validaciones.isNullOrEmpty(talonarioDto.getFechaRendicion()))
				talonarioModelo.setFechaRendicion(Utilidad.parseDateAndTimeString(talonarioDto.getFechaRendicion()));
			if (!Validaciones.isNullOrEmpty(talonarioDto.getFechaAprobacionRendicion()))
				talonarioModelo.setFechaAprobacionRendicion(Utilidad.parseDateAndTimeString(talonarioDto.getFechaAprobacionRendicion()));
			if (!Validaciones.isNullOrEmpty(talonarioDto.getUsuarioAlta()))
				talonarioModelo.setUsuarioAlta(talonarioDto.getUsuarioAlta());
			if (!Validaciones.isNullOrEmpty(talonarioDto.getUsuarioAsignacion()))
				talonarioModelo.setUsuarioAsignacion(talonarioDto.getUsuarioAsignacion());
			if (!Validaciones.isNullOrEmpty(talonarioDto.getUsuarioRendicion()))
				talonarioModelo.setUsuarioRendicion(talonarioDto.getUsuarioRendicion());
			if (!Validaciones.isNullOrEmpty(talonarioDto.getUsuarioAprobacionRendicion()))
				talonarioModelo.setUsuarioAprobacionRendicion(talonarioDto.getUsuarioAprobacionRendicion());
		}catch(Exception e){
			throw new NegocioExcepcion(e.getMessage(), e);			
		}
		
		return talonarioModelo;
	}
	
	public IEmpresaDao getEmpresaDao() {
		return empresaDao;
	}


	public void setEmpresaDao(IEmpresaDao empresaDao) {
		this.empresaDao = empresaDao;
	}


	public ISegmentoDao getSegmentoDao() {
		return segmentoDao;
	}


	public void setSegmentoDao(ISegmentoDao segmentoDao) {
		this.segmentoDao = segmentoDao;
	}


	public IGestorDao getGestorDao() {
		return gestorDao;
	}


	public void setGestorDao(IGestorDao gestorDao) {
		this.gestorDao = gestorDao;
	}




	/******************************
	 * Getters & Setters 
	 */
	
	
}
