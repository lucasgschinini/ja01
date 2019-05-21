package ar.com.telecom.shiva.negocio.mapeos;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.AcuseReciboEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoContactoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoNotificacionEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.bean.Bean;
import ar.com.telecom.shiva.persistencia.dao.IProvinciaDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjNotificacion;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjNotificacionCarta;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvLgjNotificacionSimplificado;
import ar.com.telecom.shiva.presentacion.bean.dto.LegajoChequeRechazadoNotificacionCartaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.LegajoChequeRechazadoNotificacionDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

public class LegajoChequeRechazadoNotificacionMapeador extends Mapeador {

	@Autowired
	private IProvinciaDao	provinciaDao;
	@Autowired private ILdapServicio ldapServicio;
	
	@Override
	public DTO map(Modelo vo) throws NegocioExcepcion {
		LegajoChequeRechazadoNotificacionDto legajoChequeRechazadoNotificacionDto = new LegajoChequeRechazadoNotificacionDto();
		

		if (vo instanceof ShvLgjNotificacion) {
			ShvLgjNotificacion modelo = (ShvLgjNotificacion) vo;
			if (!Validaciones.isObjectNull(modelo.getIdNotificacion())) {
				legajoChequeRechazadoNotificacionDto.setIdNotificacion(modelo.getIdNotificacion());
			}
			if (!Validaciones.isObjectNull(modelo.getLegajoChequeRechazado().getIdLegajoChequeRechazado())) {
				legajoChequeRechazadoNotificacionDto.setIdLegajoChequeRechazado(modelo.getLegajoChequeRechazado().getIdLegajoChequeRechazado());
			}
			if (!Validaciones.isObjectNull(modelo.getTipoNotificacion())) {
				legajoChequeRechazadoNotificacionDto.setTipoNotificacion(modelo.getTipoNotificacion().getIndice());
				legajoChequeRechazadoNotificacionDto.setTipoNotificacionDescripicion(modelo.getTipoNotificacion().getDescripcion());
			}
			if (!Validaciones.isNullEmptyOrDash(modelo.getDatosReceptor())) {
				legajoChequeRechazadoNotificacionDto.setDatosReceptor(modelo.getDatosReceptor());
			}
			if (!Validaciones.isObjectNull(modelo.getFechaContacto())) {
				legajoChequeRechazadoNotificacionDto.setFechaContacto(Utilidad.formatDatePicker(modelo.getFechaContacto()));
			}
			if (!Validaciones.isObjectNull(modelo.getFechaRecepcion())) {
				legajoChequeRechazadoNotificacionDto.setFechaRecepcion(Utilidad.formatDatePicker(modelo.getFechaRecepcion()));
			}
			if (!Validaciones.isObjectNull(modelo.getFechaCreacion())) {
				legajoChequeRechazadoNotificacionDto.setFechaCreacion(Utilidad.formatDatePicker(modelo.getFechaCreacion()));
			}
			if (!Validaciones.isObjectNull(modelo.getTipoContacto())) {
				legajoChequeRechazadoNotificacionDto.setTipoContacto(modelo.getTipoContacto().getIndice());
				legajoChequeRechazadoNotificacionDto.setTipoContactoDescripcion(modelo.getTipoContacto().getDescripcion());
			}
			if (!Validaciones.isNullEmptyOrDash(modelo.getDatosContacto())) {
				legajoChequeRechazadoNotificacionDto.setDatosContacto(modelo.getDatosContacto());
			}
			if (!Validaciones.isObjectNull(modelo.getAcuseRecibo())) {
				legajoChequeRechazadoNotificacionDto.setAcuseRecibo(modelo.getAcuseRecibo().getIndice());
				legajoChequeRechazadoNotificacionDto.setAcuseReciboDescripcion(modelo.getAcuseRecibo().getDescripcion());
			}
			if (!Validaciones.isNullEmptyOrDash(legajoChequeRechazadoNotificacionDto.getObservaciones())) {
				legajoChequeRechazadoNotificacionDto.setObservaciones(legajoChequeRechazadoNotificacionDto.getObservaciones());
			}
			if (!Validaciones.isObjectNull(modelo.getEstado())) {
				legajoChequeRechazadoNotificacionDto.setEstado(modelo.getEstado());
				legajoChequeRechazadoNotificacionDto.setEstadoDescripion(modelo.getEstado().getDescripcion());
			}
			if (!Validaciones.isObjectNull(modelo.getUsuarioAlta())) {
				legajoChequeRechazadoNotificacionDto.setUsuarioAlta(modelo.getUsuarioAlta());
				legajoChequeRechazadoNotificacionDto.setUsuarioDesc(modelo.getUsuarioDesc());
			}
			if (!Validaciones.isObjectNull(modelo.getObservaciones())) {
				legajoChequeRechazadoNotificacionDto.setObservaciones(modelo.getObservaciones());
			}
		} else if (vo instanceof ShvLgjNotificacionSimplificado) {
			ShvLgjNotificacionSimplificado modelo = (ShvLgjNotificacionSimplificado) vo;
			
			if (!Validaciones.isObjectNull(modelo.getIdNotificacion())) {
				legajoChequeRechazadoNotificacionDto.setIdNotificacion(modelo.getIdNotificacion());
			}
			if (!Validaciones.isObjectNull(modelo.getIdLegajoChequeRechazado())) {
				legajoChequeRechazadoNotificacionDto.setIdLegajoChequeRechazado(modelo.getIdLegajoChequeRechazado());
			}
			if (!Validaciones.isObjectNull(modelo.getTipoNotificacion())) {
				legajoChequeRechazadoNotificacionDto.setTipoNotificacion(modelo.getTipoNotificacion().getIndice());
				legajoChequeRechazadoNotificacionDto.setTipoNotificacionDescripicion(modelo.getTipoNotificacion().getDescripcion());
			}
			if (!Validaciones.isNullEmptyOrDash(modelo.getDatosReceptor())) {
				legajoChequeRechazadoNotificacionDto.setDatosReceptor(modelo.getDatosReceptor());
			}
			if (!Validaciones.isObjectNull(modelo.getFechaContacto())) {
				legajoChequeRechazadoNotificacionDto.setFechaContacto(Utilidad.formatDatePicker(modelo.getFechaContacto()));
			}
			if (!Validaciones.isObjectNull(modelo.getFechaRecepcion())) {
				legajoChequeRechazadoNotificacionDto.setFechaRecepcion(Utilidad.formatDatePicker(modelo.getFechaRecepcion()));
			}
			if (!Validaciones.isObjectNull(modelo.getFechaCreacion())) {
				legajoChequeRechazadoNotificacionDto.setFechaCreacion(Utilidad.formatDatePicker(modelo.getFechaCreacion()));
			}
			if (!Validaciones.isObjectNull(modelo.getTipoContacto())) {
				legajoChequeRechazadoNotificacionDto.setTipoContacto(modelo.getTipoContacto().getIndice());
				legajoChequeRechazadoNotificacionDto.setTipoContactoDescripcion(modelo.getTipoContacto().getDescripcion());
			}
			if (!Validaciones.isNullEmptyOrDash(modelo.getDatosContacto())) {
				legajoChequeRechazadoNotificacionDto.setDatosContacto(modelo.getDatosContacto());
			}
			if (!Validaciones.isObjectNull(modelo.getAcuseRecibo())) {
				legajoChequeRechazadoNotificacionDto.setAcuseRecibo(modelo.getAcuseRecibo().getIndice());
				legajoChequeRechazadoNotificacionDto.setAcuseReciboDescripcion(modelo.getAcuseRecibo().getDescripcion());
			}
			if (!Validaciones.isNullEmptyOrDash(legajoChequeRechazadoNotificacionDto.getObservaciones())) {
				legajoChequeRechazadoNotificacionDto.setObservaciones(legajoChequeRechazadoNotificacionDto.getObservaciones());
			}
			if (!Validaciones.isObjectNull(modelo.getEstado())) {
				legajoChequeRechazadoNotificacionDto.setEstado(modelo.getEstado());
				legajoChequeRechazadoNotificacionDto.setEstadoDescripion(modelo.getEstado().getDescripcion());
			}
			if (!Validaciones.isObjectNull(modelo.getUsuarioAlta())) {
				legajoChequeRechazadoNotificacionDto.setUsuarioAlta(modelo.getUsuarioAlta());
				legajoChequeRechazadoNotificacionDto.setUsuarioDesc(modelo.getUsuarioDesc());
			}
			if (!Validaciones.isObjectNull(modelo.getObservaciones())) {
				legajoChequeRechazadoNotificacionDto.setObservaciones(modelo.getObservaciones());
			}
			
		}
		if (!Validaciones.isNullEmptyOrDash(legajoChequeRechazadoNotificacionDto.getUsuarioAlta())) {
			UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(legajoChequeRechazadoNotificacionDto.getUsuarioAlta());
			legajoChequeRechazadoNotificacionDto.setMailAnalista(usuarioLdap.getMail());
			legajoChequeRechazadoNotificacionDto.setUrlFotoAnalista(legajoChequeRechazadoNotificacionDto.urlFotoUsuario(legajoChequeRechazadoNotificacionDto.getUsuarioAlta()));
		}
		
		
		
		return legajoChequeRechazadoNotificacionDto;
	}

	@Override
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		ShvLgjNotificacionSimplificado modelo = (Validaciones.isObjectNull(vo) ? new ShvLgjNotificacionSimplificado() : (ShvLgjNotificacionSimplificado) vo);
		LegajoChequeRechazadoNotificacionDto legajoChequeRechazadoNotificacionDto = (LegajoChequeRechazadoNotificacionDto) dto;

		
		if (!Validaciones.isObjectNull(legajoChequeRechazadoNotificacionDto.getIdNotificacion())) {
			modelo.setIdNotificacion(legajoChequeRechazadoNotificacionDto.getIdNotificacion());
		}

		if (!Validaciones.isObjectNull(legajoChequeRechazadoNotificacionDto.getIdLegajoChequeRechazado())) {
			modelo.setIdLegajoChequeRechazado(legajoChequeRechazadoNotificacionDto.getIdLegajoChequeRechazado());
		}
		if (!Validaciones.isObjectNull(legajoChequeRechazadoNotificacionDto.getTipoNotificacion())) {
			modelo.setTipoNotificacion(TipoNotificacionEnum.getEnumByIndice(legajoChequeRechazadoNotificacionDto.getTipoNotificacion()));
		}
		if (!Validaciones.isNullEmptyOrDash(legajoChequeRechazadoNotificacionDto.getDatosReceptor())) {
			modelo.setDatosReceptor(legajoChequeRechazadoNotificacionDto.getDatosReceptor());
		}
		try {
			if (!Validaciones.isNullEmptyOrDash(legajoChequeRechazadoNotificacionDto.getFechaContacto())) {
				modelo.setFechaContacto(Utilidad.parseDatePickerString(legajoChequeRechazadoNotificacionDto.getFechaContacto()));
			}
			if (!Validaciones.isNullEmptyOrDash(legajoChequeRechazadoNotificacionDto.getFechaRecepcion())) {
				modelo.setFechaRecepcion(Utilidad.parseDatePickerString(legajoChequeRechazadoNotificacionDto.getFechaRecepcion()));
			}
		} catch (ParseException e) {
			Traza.error(getClass(), e.getMessage());
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		if (!Validaciones.isObjectNull(legajoChequeRechazadoNotificacionDto.getTipoContacto())) {
			modelo.setTipoContacto(TipoContactoEnum.getEnumByIndice(legajoChequeRechazadoNotificacionDto.getTipoContacto()));
		}
		if (!Validaciones.isNullEmptyOrDash(legajoChequeRechazadoNotificacionDto.getDatosContacto())) {
			modelo.setDatosContacto(legajoChequeRechazadoNotificacionDto.getDatosContacto());
		}
		if (!Validaciones.isObjectNull(legajoChequeRechazadoNotificacionDto.getAcuseRecibo())) {
			modelo.setAcuseRecibo(AcuseReciboEnum.getEnumByIndice(legajoChequeRechazadoNotificacionDto.getAcuseRecibo()));
		}
		if (!Validaciones.isNullEmptyOrDash(legajoChequeRechazadoNotificacionDto.getObservaciones())) {
			modelo.setObservaciones(legajoChequeRechazadoNotificacionDto.getObservaciones());
		}
		if (!Validaciones.isObjectNull(legajoChequeRechazadoNotificacionDto.getEstado())) {
			modelo.setEstado(legajoChequeRechazadoNotificacionDto.getEstado());
		}

		return modelo;
	}

	
	public DTO map(Bean be) throws NegocioExcepcion {
		return null;
	}
	
	public Modelo map(Bean be, Modelo mo) throws NegocioExcepcion {
	
		return null;
	}
	

	public Modelo mapCarta(DTO dto, Modelo vo) throws NegocioExcepcion {
		ShvLgjNotificacionCarta modelo = (Validaciones.isObjectNull(vo) ? new ShvLgjNotificacionCarta() : (ShvLgjNotificacionCarta) vo);
		LegajoChequeRechazadoNotificacionCartaDto legajoChequeRechazadoNotificacionCartaDto = (LegajoChequeRechazadoNotificacionCartaDto) dto;

		if (!Validaciones.isNullEmptyOrDash(legajoChequeRechazadoNotificacionCartaDto.getAnalistaFirmante())) {
			modelo.setAnalistaFirmante(legajoChequeRechazadoNotificacionCartaDto.getAnalistaFirmante());
		}
		try {
			if (!Validaciones.isNullEmptyOrDash(legajoChequeRechazadoNotificacionCartaDto.getFecha())) {
				modelo.setFecha(Utilidad.parseDatePickerString(legajoChequeRechazadoNotificacionCartaDto.getFecha()));
			}
		} catch (ParseException e) {
			Traza.error(getClass(), e.getMessage());
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		if (!Validaciones.isNullEmptyOrDash(legajoChequeRechazadoNotificacionCartaDto.getCalle())) {
			modelo.setCalle(legajoChequeRechazadoNotificacionCartaDto.getCalle());
		}
		if (!Validaciones.isNullEmptyOrDash(legajoChequeRechazadoNotificacionCartaDto.getCodigoPostal())) {
			modelo.setCodigoPostal(legajoChequeRechazadoNotificacionCartaDto.getCodigoPostal());
		}
		if (!Validaciones.isNullEmptyOrDash(legajoChequeRechazadoNotificacionCartaDto.getDepartamento())) {
			modelo.setDepartamento(legajoChequeRechazadoNotificacionCartaDto.getDepartamento());
		}
		if (!Validaciones.isNullEmptyOrDash(legajoChequeRechazadoNotificacionCartaDto.getLocalidad())) {
			modelo.setLocalidad(legajoChequeRechazadoNotificacionCartaDto.getLocalidad());
		}
		if (!Validaciones.isNullEmptyOrDash(legajoChequeRechazadoNotificacionCartaDto.getNumero())) {
			modelo.setNumero(legajoChequeRechazadoNotificacionCartaDto.getNumero());
		}
		if (!Validaciones.isNullEmptyOrDash(legajoChequeRechazadoNotificacionCartaDto.getPiso())) {
			modelo.setPiso(legajoChequeRechazadoNotificacionCartaDto.getPiso());
		}
		if (!Validaciones.isNullEmptyOrDash(legajoChequeRechazadoNotificacionCartaDto.getNumLineaServicio())) {
			modelo.setNroLinea(legajoChequeRechazadoNotificacionCartaDto.getNumLineaServicio());
		}
		if (!Validaciones.isNullEmptyOrDash(legajoChequeRechazadoNotificacionCartaDto.getNombreDestinatario())) {
			modelo.setNombreDestinatario(legajoChequeRechazadoNotificacionCartaDto.getNombreDestinatario());
		}

		if (!Validaciones.isObjectNull(legajoChequeRechazadoNotificacionCartaDto.getFechaReembolso())){
			try {
				modelo.setFechaReembolso(Utilidad.parseDatePickerString(legajoChequeRechazadoNotificacionCartaDto.getFechaReembolso()));
			} catch (ParseException e) {
				Traza.error(getClass(), e.getMessage());
				throw new NegocioExcepcion(e.getMessage(), e);
			}
		}
		if (!Validaciones.isNullEmptyOrDash(legajoChequeRechazadoNotificacionCartaDto.getProvincia())) {
			try {
				modelo.setProvincia(provinciaDao.listarProvinciasPorId(legajoChequeRechazadoNotificacionCartaDto.getProvincia()));
			} catch (PersistenciaExcepcion e) {
				Traza.error(getClass(), e.getMessage());
				throw new NegocioExcepcion(e.getMessage(), e);
			}
		} else {
			modelo.setProvincia(null);
		}

		return modelo;
	}
}
