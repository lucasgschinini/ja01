package ar.com.telecom.shiva.negocio.servicios.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoProcesoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.dto.cobros.CobMensajeriaTransaccionDto;
import ar.com.telecom.shiva.negocio.servicios.IMensajeriaTransaccionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.persistencia.dao.IMensajeriaTransaccionDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccionMensajeriaDetalle;

public class MensajeriaTransaccionServicioImpl extends Servicio implements IMensajeriaTransaccionServicio {

	@Autowired
	private IMensajeriaTransaccionDao mensajeriaTransaccionDao;
	
	@Autowired
	IParametroServicio parametroServicio;
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation=Propagation.REQUIRES_NEW)
	public DTO crearTransaccionPropia(DTO dto) throws NegocioExcepcion {
		return this.crear(dto);
	}

	@Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation=Propagation.REQUIRED)
	public DTO crearTransaccionPropagada(DTO dto) throws NegocioExcepcion {
		return this.crear(dto);
	}
	
	
	/**
	 * Permite crear el mensaje
	 */
	private DTO crear(DTO dto) throws NegocioExcepcion {
		try {
			ShvCobTransaccionMensajeriaDetalle modelo = 
				(ShvCobTransaccionMensajeriaDetalle) defaultMapeador.map(dto, null);

			//Por defecto siempre va a haber 0 de reintento
			modelo.setCantReintentos(0);
			
			ShvCobTransaccionMensajeriaDetalle cobTransaccionMensajeria
				= mensajeriaTransaccionDao.guardarMensaje(modelo);
			
			CobMensajeriaTransaccionDto dtoNuevo = (CobMensajeriaTransaccionDto)
					defaultMapeador.map(cobTransaccionMensajeria);
			
			return dtoNuevo;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation=Propagation.REQUIRED)
	public synchronized void modificar(DTO dto) throws NegocioExcepcion {
		
		try {
			CobMensajeriaTransaccionDto cobMensajeriaDto = (CobMensajeriaTransaccionDto) dto;
			ShvCobTransaccionMensajeriaDetalle modelo = 
					mensajeriaTransaccionDao.buscarMensajePorIdMensajeria(cobMensajeriaDto.getIdTransaccionMensajeria());
			
			if (modelo!=null) {
				
				//Actualizo el modelo
				ShvCobTransaccionMensajeriaDetalle cobTransaccionMensajeria = 
					(ShvCobTransaccionMensajeriaDetalle) defaultMapeador.map(dto, modelo);
				
				cobTransaccionMensajeria = mensajeriaTransaccionDao.guardarMensaje(cobTransaccionMensajeria);
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}	
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation=Propagation.REQUIRES_NEW)
	public synchronized void modificarTransaccionPropia(DTO dto) throws NegocioExcepcion {
		
		try {
			CobMensajeriaTransaccionDto cobMensajeriaDto = (CobMensajeriaTransaccionDto) dto;
			ShvCobTransaccionMensajeriaDetalle modelo = mensajeriaTransaccionDao.buscarMensajePorIdMensajeria(cobMensajeriaDto.getIdTransaccionMensajeria());
			
			if (modelo!=null) {
				
				//Actualizo el modelo
				ShvCobTransaccionMensajeriaDetalle cobTransaccionMensajeria = 
					(ShvCobTransaccionMensajeriaDetalle) defaultMapeador.map(dto, modelo);
				
				cobTransaccionMensajeria = mensajeriaTransaccionDao.guardarMensaje(cobTransaccionMensajeria);
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}	
	}
	
	@Override
	public DTO buscar(Integer idMensajeria)
			throws NegocioExcepcion {
		
		try {
			ShvCobTransaccionMensajeriaDetalle cobTransaccionMensajeria = 
					mensajeriaTransaccionDao.buscarMensajePorIdMensajeria(idMensajeria);
			
			return (CobMensajeriaTransaccionDto) defaultMapeador.map(cobTransaccionMensajeria); 
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	@Override
	public DTO buscar(Long idOperacion, Integer idTransaccion, SistemaEnum cobrador,
			MensajeEstadoEnum estado) throws NegocioExcepcion {
		
		try {
			List<ShvCobTransaccionMensajeriaDetalle> lista = 
					mensajeriaTransaccionDao.buscarMensajePorIdOperacionTransaccion(idOperacion, idTransaccion);
			
			ShvCobTransaccionMensajeriaDetalle mensajeriaEncontrado = null;
			for (ShvCobTransaccionMensajeriaDetalle msj: lista) {
				if (msj.getServicio().getAplicacion().equalsIgnoreCase(cobrador.getDescripcionCorta())
						&& msj.getEstado().equals(estado)) {
					
					mensajeriaEncontrado = msj;
					break;
				}
			}
			
			if (mensajeriaEncontrado != null) {
				return (CobMensajeriaTransaccionDto) defaultMapeador.map(mensajeriaEncontrado); 
			}
			
			return null;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	@Override
	public DTO buscarMensajeDesapropiacionConfirmacionEnviadoAlMIC(Long idOperacion) throws NegocioExcepcion {
		
		try {
			List<ShvCobTransaccionMensajeriaDetalle> lista = 
					mensajeriaTransaccionDao.buscarMensajesDesapropiacionConfirmacionEnviadosMIC(idOperacion);
			
			if (Validaciones.isCollectionNotEmpty(lista)) {
				//Ordena las fechas de mayor a menor
				Collections.sort(lista, new  Comparator<ShvCobTransaccionMensajeriaDetalle>() {
					public int compare(ShvCobTransaccionMensajeriaDetalle o1,
							ShvCobTransaccionMensajeriaDetalle o2) {
						return (o1.getFechaCreacion().after(o2.getFechaCreacion()))?-1:+1;
					}
				});
				
				ShvCobTransaccionMensajeriaDetalle mensajeriaEncontrado = null;
				for (ShvCobTransaccionMensajeriaDetalle msj: lista) {
					
					mensajeriaEncontrado = msj;
					break;
				}
				
				if (mensajeriaEncontrado != null) {
					return (CobMensajeriaTransaccionDto) defaultMapeador.map(mensajeriaEncontrado); 
				}
			}
			return null;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	@Override
	public DTO buscarMensajeApropiacionCargoEnviadoAlMIC(Long idOperacion, Integer idTransaccion) throws NegocioExcepcion {
		
		try {
			List<ShvCobTransaccionMensajeriaDetalle> lista = 
					mensajeriaTransaccionDao.buscarMensajesApropiacionCargoEnviadosMIC(idOperacion, idTransaccion);
			
			if (Validaciones.isCollectionNotEmpty(lista)) {
				//Ordena las fechas de mayor a menor
				Collections.sort(lista, new  Comparator<ShvCobTransaccionMensajeriaDetalle>() {
					public int compare(ShvCobTransaccionMensajeriaDetalle o1,
							ShvCobTransaccionMensajeriaDetalle o2) {
						return (o1.getFechaCreacion().after(o2.getFechaCreacion()))?-1:+1;
					}
				});
				
				ShvCobTransaccionMensajeriaDetalle mensajeriaEncontrado = null;
				for (ShvCobTransaccionMensajeriaDetalle msj: lista) {
					
					mensajeriaEncontrado = msj;
					break;
				}
				
				if (mensajeriaEncontrado != null) {
					return (CobMensajeriaTransaccionDto) defaultMapeador.map(mensajeriaEncontrado); 
				}
			}
			return null;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	@Override
	public DTO buscarMensajeDescobroCargoEnviadoAlMIC(Long idOperacionCobro, Integer numeroTransaccion) throws NegocioExcepcion {
		
		try {
			List<ShvCobTransaccionMensajeriaDetalle> lista = 
					mensajeriaTransaccionDao.buscarMensajesDescobroEnviadosMIC(idOperacionCobro, numeroTransaccion);
			
			if (Validaciones.isCollectionNotEmpty(lista)) {
				//Ordena las fechas de mayor a menor
				Collections.sort(lista, new  Comparator<ShvCobTransaccionMensajeriaDetalle>() {
					public int compare(ShvCobTransaccionMensajeriaDetalle o1,
							ShvCobTransaccionMensajeriaDetalle o2) {
						return (o1.getFechaCreacion().after(o2.getFechaCreacion()))?-1:+1;
					}
				});
				
				ShvCobTransaccionMensajeriaDetalle mensajeriaEncontrado = null;
				for (ShvCobTransaccionMensajeriaDetalle msj: lista) {
					
					mensajeriaEncontrado = msj;
					break;
				}
				
				if (mensajeriaEncontrado != null) {
					return (CobMensajeriaTransaccionDto) defaultMapeador.map(mensajeriaEncontrado); 
				}
			}
			return null;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	@Override
	public DTO buscarMensaje(Long idOperacion, Integer idTransaccion, MensajeServicioEnum servicio) throws NegocioExcepcion {
		
		try {
			List<ShvCobTransaccionMensajeriaDetalle> lista = null;
    		if (SistemaEnum.MIC.getDescripcionCorta().equalsIgnoreCase(servicio.getAplicacion())) {
    			//Si es por MIC
    			if (MensajeServicioEnum.MIC_CONFIRMACION.equals(servicio)
					|| MensajeServicioEnum.MIC_DESAPROPIACION.equals(servicio)) {
					lista = mensajeriaTransaccionDao.buscarMensajesPorIdOperacion(idOperacion, servicio);
				} else {
					lista = mensajeriaTransaccionDao.buscarMensajesPorIdOperacionTransaccion(idOperacion, idTransaccion, servicio);
				}
				
    		} else if (SistemaEnum.CALIPSO.getDescripcionCorta().equalsIgnoreCase(servicio.getAplicacion())) {
    			//Si es por calipso
    			if (MensajeServicioEnum.CLP_CONFIRMACION.equals(servicio)
    					|| MensajeServicioEnum.CLP_DESAPROPIACION.equals(servicio)) {
    				lista = mensajeriaTransaccionDao.buscarMensajesPorIdOperacion(idOperacion, servicio);
				} else {
					lista = mensajeriaTransaccionDao.buscarMensajesPorIdOperacionTransaccion(idOperacion, idTransaccion, servicio);
				}
    		} else if (SistemaEnum.SAP.getDescripcionCorta().equalsIgnoreCase(servicio.getAplicacion())) {
    			if (MensajeServicioEnum.SAP_REGISTRAR_COMPENSACION.equals(servicio)
    					|| MensajeServicioEnum.SAP_CONSULTAR_PROVEEDOR.equals(servicio)){
    				lista = mensajeriaTransaccionDao.buscarMensajesPorIdOperacion(idOperacion, servicio);
    			}
    		}
			
			if (Validaciones.isCollectionNotEmpty(lista)) {
				//Ordena las fechas de mayor a menor
				Collections.sort(lista, new  Comparator<ShvCobTransaccionMensajeriaDetalle>() {
					public int compare(ShvCobTransaccionMensajeriaDetalle o1,
							ShvCobTransaccionMensajeriaDetalle o2) {
						return (o1.getFechaCreacion().after(o2.getFechaCreacion()))?-1:+1;
					}
				});
				
				ShvCobTransaccionMensajeriaDetalle mensajeriaEncontrado = lista.get(0);
				return (CobMensajeriaTransaccionDto) defaultMapeador.map(mensajeriaEncontrado);
				
			} else {
				
				//Decimos que no hay ningun mensaje enviado y vamos a enviar el primer mensaje (NUEVO MENSAJE)
				return null;
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 */
	@Override
	public DTO buscarUltimoMensajeRespuesta(Long idOperacion) throws NegocioExcepcion {
		try {
			ShvCobTransaccionMensajeriaDetalle ultimoMensajeRecibido = new ShvCobTransaccionMensajeriaDetalle();
			ultimoMensajeRecibido = mensajeriaTransaccionDao.buscarUltimoMensajeCompletado(idOperacion); 
			
			if (ultimoMensajeRecibido != null && ultimoMensajeRecibido.getServicio() != null) {
				if (ultimoMensajeRecibido.getServicio().name().contains("MIC_RESPUESTA_")) {
					return (CobMensajeriaTransaccionDto) defaultMapeador.map(ultimoMensajeRecibido); 
				}
			}
			
			return null;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	@Override
	public DTO buscarMensajeRecibido(Long idOperacion, Integer idTransaccion, MensajeServicioEnum servicio, String mensajeRecibido)
			throws NegocioExcepcion {
		
		try {
			List<ShvCobTransaccionMensajeriaDetalle> lista = new ArrayList<ShvCobTransaccionMensajeriaDetalle>();
			if (idTransaccion == null) {
				lista = mensajeriaTransaccionDao.buscarMensajesPorIdOperacion(idOperacion, servicio); 
			} else {
				lista = mensajeriaTransaccionDao.buscarMensajesPorIdOperacionTransaccion(idOperacion, idTransaccion, servicio);
			}
			
			ShvCobTransaccionMensajeriaDetalle mensajeriaEncontrado = null;
			for (ShvCobTransaccionMensajeriaDetalle msj: lista) {
				if (!Validaciones.isNullOrEmpty(mensajeRecibido)
					&& mensajeRecibido.equalsIgnoreCase(msj.getRespuestaRecibida())) {
					
					mensajeriaEncontrado = msj;
					break;
				}
			}
			
			if (mensajeriaEncontrado != null) {
				return (CobMensajeriaTransaccionDto) defaultMapeador.map(mensajeriaEncontrado); 
			}
			
			return null;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	
	@Override
	public Collection<DTO> listarMensajeriasPendientesMIC(
			Integer cantMaxReintentos, String fechaPermitidaParaReenvio, TipoProcesoEnum tipoImputacion)
			throws NegocioExcepcion {
		
		try {
			List<ShvCobTransaccionMensajeriaDetalle> lista = 
					mensajeriaTransaccionDao.listarMensajesPendientesMIC(fechaPermitidaParaReenvio);
			
			List<DTO> listaDto = new ArrayList<DTO>(); 
			for (ShvCobTransaccionMensajeriaDetalle msj: lista) {
				boolean incluirMensajeMicAReenviar = false;
				
				//Si es cobro, excluyo todos los mensajes del descobro
				if (TipoProcesoEnum.COBRO.equals(tipoImputacion) 
						&& (!(MensajeServicioEnum.MIC_DESCOBRO.equals(msj.getServicio())
								|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_CREDITO.equals(msj.getServicio())
								|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_DEBITO.equals(msj.getServicio())
								|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_INTERES.equals(msj.getServicio())
								|| MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO.equals(msj.getServicio())
								|| MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_CREDITO.equals(msj.getServicio())
								|| MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_DEBITO.equals(msj.getServicio())
								|| MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_INTERES.equals(msj.getServicio()) ))) {
					incluirMensajeMicAReenviar = true;
				}
				//Si es descobro, incluyo todos los mensajes del descobro
				if (TipoProcesoEnum.DESCOBRO.equals(tipoImputacion) 
						&& (MensajeServicioEnum.MIC_DESCOBRO.equals(msj.getServicio())
								|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_CREDITO.equals(msj.getServicio())
								|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_DEBITO.equals(msj.getServicio())
								|| MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_INTERES.equals(msj.getServicio())
								|| MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO.equals(msj.getServicio())
								|| MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_CREDITO.equals(msj.getServicio())
								|| MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_DEBITO.equals(msj.getServicio())
								|| MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_INTERES.equals(msj.getServicio()) )) {
					incluirMensajeMicAReenviar = true;
				}
				
				if (incluirMensajeMicAReenviar) {
					CobMensajeriaTransaccionDto dto = (CobMensajeriaTransaccionDto) defaultMapeador.map(msj);
					if (msj.getServicio().name().contains("MIC_RESPUESTA_") &&
							msj.getCantReintentos().compareTo(cantMaxReintentos) >= 0)  
					{
						this.cancelarMensaje(dto);
					} else {
						listaDto.add(dto);
					}
				}
			}
			
			return listaDto;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	
	@Override
	public DTO buscarMensajePorIdOperacion(Long idOperacion, MensajeServicioEnum servicio)
			throws NegocioExcepcion {
		
		try {
			List<ShvCobTransaccionMensajeriaDetalle> lista = 
					mensajeriaTransaccionDao.buscarMensajesPorIdOperacion(idOperacion, servicio);
			
			if (Validaciones.isCollectionNotEmpty(lista)) {
				//Ordena las fechas de mayor a menor
				Collections.sort(lista, new  Comparator<ShvCobTransaccionMensajeriaDetalle>() {
					public int compare(ShvCobTransaccionMensajeriaDetalle o1,
							ShvCobTransaccionMensajeriaDetalle o2) {
						return (o1.getFechaCreacion().after(o2.getFechaCreacion()))?-1:+1;
					}
				});
				
				ShvCobTransaccionMensajeriaDetalle mensajeriaEncontrado = lista.get(0);
				if (mensajeriaEncontrado != null) {
					return (CobMensajeriaTransaccionDto) defaultMapeador.map(mensajeriaEncontrado); 
				}
			}
			return null;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	@Override
	public DTO buscarMensajePorIdOperacionTransaccion(
			Long idOperacion, Integer idTransaccion,
			MensajeServicioEnum servicio) throws NegocioExcepcion {
	
		try {
			List<ShvCobTransaccionMensajeriaDetalle> lista = 
					mensajeriaTransaccionDao.buscarMensajesPorIdOperacionTransaccion(idOperacion, idTransaccion, servicio);
			
			if (Validaciones.isCollectionNotEmpty(lista)) {
				//Ordena las fechas de mayor a menor
				Collections.sort(lista, new  Comparator<ShvCobTransaccionMensajeriaDetalle>() {
					public int compare(ShvCobTransaccionMensajeriaDetalle o1,
							ShvCobTransaccionMensajeriaDetalle o2) {
						return (o1.getFechaCreacion().after(o2.getFechaCreacion()))?-1:+1;
					}
				});
				
				ShvCobTransaccionMensajeriaDetalle mensajeriaEncontrado = lista.get(0);
				if (mensajeriaEncontrado != null) {
					return (CobMensajeriaTransaccionDto) defaultMapeador.map(mensajeriaEncontrado); 
				}
			}
			return null;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	@Override
	public DTO buscarMensajePorIdOperacionTransaccionYEstadoMensaje(
			Long idOperacion, Integer idTransaccion,
			MensajeServicioEnum servicio, MensajeEstadoEnum estado) throws NegocioExcepcion {
	
		try {
			List<ShvCobTransaccionMensajeriaDetalle> lista = 
					mensajeriaTransaccionDao.buscarMensajesPorIdOperacionTransaccionYEstadoMensaje(idOperacion, idTransaccion, servicio, estado);
			
			if (Validaciones.isCollectionNotEmpty(lista)) {
				//Ordena las fechas de mayor a menor
				Collections.sort(lista, new  Comparator<ShvCobTransaccionMensajeriaDetalle>() {
					public int compare(ShvCobTransaccionMensajeriaDetalle o1,
							ShvCobTransaccionMensajeriaDetalle o2) {
						return (o1.getFechaCreacion().after(o2.getFechaCreacion()))?-1:+1;
					}
				});
				
				ShvCobTransaccionMensajeriaDetalle mensajeriaEncontrado = lista.get(0);
				if (mensajeriaEncontrado != null) {
					return (CobMensajeriaTransaccionDto) defaultMapeador.map(mensajeriaEncontrado); 
				}
			}
			return null;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * Cambia el estado del mensaje a "CANCELADO"
	 */
	public void cancelarMensaje(CobMensajeriaTransaccionDto mensajeDto) throws NegocioExcepcion {
		mensajeDto.setEstado(MensajeEstadoEnum.CANCELADO);
		modificar(mensajeDto);
	}
	
	
	/************************************************************************
	 * Getters/Setters
	 ***********************************************************************/
	public IMensajeriaTransaccionDao getMensajeriaTransaccionDao() {
		return mensajeriaTransaccionDao;
	}

	public void setMensajeriaTransaccionDao(
			IMensajeriaTransaccionDao mensajeriaTransaccionDao) {
		this.mensajeriaTransaccionDao = mensajeriaTransaccionDao;
	}

	@Override
	public DTO buscarMensajeCancelado(Long idOperacion) throws NegocioExcepcion {
		try {
			List<ShvCobTransaccionMensajeriaDetalle> lista = mensajeriaTransaccionDao.buscarMensajeCancelado(idOperacion);
			
			if (Validaciones.isCollectionNotEmpty(lista)) {
				//Ordena las fechas de mayor a menor
				Collections.sort(lista, new  Comparator<ShvCobTransaccionMensajeriaDetalle>() {
					public int compare(ShvCobTransaccionMensajeriaDetalle o1,
							ShvCobTransaccionMensajeriaDetalle o2) {
						return (o1.getFechaCreacion().after(o2.getFechaCreacion()))?-1:+1;
					}
				});
				
				ShvCobTransaccionMensajeriaDetalle mensajeriaEncontrado = lista.get(0);
				if (mensajeriaEncontrado != null) {
					return (CobMensajeriaTransaccionDto) defaultMapeador.map(mensajeriaEncontrado); 
				}
			}
			return null;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	@Override
	public DTO buscarRespuestaConfirmacionMic(Long idOperacion) throws NegocioExcepcion {
		try {
			List<ShvCobTransaccionMensajeriaDetalle> lista = mensajeriaTransaccionDao.buscarMensajeConfirmacionCompletadaMic(idOperacion);
			
			if (Validaciones.isCollectionNotEmpty(lista)) {
				//Ordena las fechas de mayor a menor
				Collections.sort(lista, new  Comparator<ShvCobTransaccionMensajeriaDetalle>() {
					public int compare(ShvCobTransaccionMensajeriaDetalle o1,
							ShvCobTransaccionMensajeriaDetalle o2) {
						return (o1.getFechaCreacion().after(o2.getFechaCreacion()))?-1:+1;
					}
				});
				
				ShvCobTransaccionMensajeriaDetalle mensajeriaEncontrado = lista.get(0);
				if (mensajeriaEncontrado != null) {
					return (CobMensajeriaTransaccionDto) defaultMapeador.map(mensajeriaEncontrado); 
				}
			}
			return null;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	@Override
	public DTO buscarRespuestaDesapropiacionMic(Long idOperacion) throws NegocioExcepcion {
		try {
			List<ShvCobTransaccionMensajeriaDetalle> lista = mensajeriaTransaccionDao.buscarMensajeDesapropiacionCompletadaMic(idOperacion);
			
			if (Validaciones.isCollectionNotEmpty(lista)) {
				//Ordena las fechas de mayor a menor
				Collections.sort(lista, new  Comparator<ShvCobTransaccionMensajeriaDetalle>() {
					public int compare(ShvCobTransaccionMensajeriaDetalle o1,
							ShvCobTransaccionMensajeriaDetalle o2) {
						return (o1.getFechaCreacion().after(o2.getFechaCreacion()))?-1:+1;
					}
				});
				
				ShvCobTransaccionMensajeriaDetalle mensajeriaEncontrado = lista.get(0);
				if (mensajeriaEncontrado != null) {
					return (CobMensajeriaTransaccionDto) defaultMapeador.map(mensajeriaEncontrado); 
				}
			}
			return null;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	@Override
	public List<CobMensajeriaTransaccionDto> buscarRespuestasDesapropiacionParcialMic(Long idOperacion) throws NegocioExcepcion {
		try {
			List<ShvCobTransaccionMensajeriaDetalle> lista = mensajeriaTransaccionDao.buscarMensajeDesapropiacionParcialCompletadaMic(idOperacion);
			
			if (Validaciones.isCollectionNotEmpty(lista)) {
				//Ordena las fechas de mayor a menor
//				Collections.sort(lista, new  Comparator<ShvCobTransaccionMensajeriaDetalle>() {
//					public int compare(ShvCobTransaccionMensajeriaDetalle o1,
//							ShvCobTransaccionMensajeriaDetalle o2) {
//						return (o1.getFechaCreacion().after(o2.getFechaCreacion()))?-1:+1;
//					}
//				});
				
				List<CobMensajeriaTransaccionDto> listaDto = new ArrayList<CobMensajeriaTransaccionDto>(); 

				for (ShvCobTransaccionMensajeriaDetalle shvCobTransaccionMensajeriaDetalle : lista) {
					
					if (!Validaciones.isObjectNull(shvCobTransaccionMensajeriaDetalle)) {
						listaDto.add((CobMensajeriaTransaccionDto) defaultMapeador.map(shvCobTransaccionMensajeriaDetalle)); 
					}
				}
				return listaDto;
				
			}
			return null;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * @param idOperacion
	 * @param servio1
	 * @param servio2
	 * @param servio3
	 * @param servio4
	 * @param servio5
	 * @param servio6
	 * @param servio7
	 * @param servio8
	 * @param servio9
	 * @param servio10
	 * @param servio11
	 * @param servio12
	 * @param servio13
	 * @param servio14
	 * @param servio15
	 * @param servio16
	 * @throws PersistenciaExcepcion
	 */
	public void borrarMensajesPorIdOperacionServiciosVarios(Long idOperacion, Integer idTransaccion, List<MensajeServicioEnum> listaMensajes) throws PersistenciaExcepcion {
		mensajeriaTransaccionDao.borrarMensajesPorIdOperacionServiciosVarios(
				idOperacion, idTransaccion, listaMensajes
//				MensajeServicioEnum.MIC_DESCOBRO,
//				MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO,
//				MensajeServicioEnum.CLP_DESCOBRO,
//				MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_DEBITO,
//				MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_DEBITO,
//				MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_CREDITO,
//				MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_CREDITO,
//				MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_INTERES,
//				MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_INTERES,
//				MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_DEBITO_INTERES,
//				MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_DEBITO_INTERES,
//				MensajeServicioEnum.CLP_DESCOBRO_GENERACION_CARGO_CREDITO_INTERES,
//				MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_CREDITO_INTERES,
//				MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_DEBITO,
//				MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_CREDITO,
//				MensajeServicioEnum.MIC_RESPUESTA_DESCOBRO_GENERACION_CARGO_INTERES
				);
	}	

	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void borrarMensajesPorIdTransaccionMensajeria(ShvCobTransaccionMensajeriaDetalle mensaje) throws NegocioExcepcion {
		try{
			mensajeriaTransaccionDao.borrarMensajesPorIdTransaccionMensajeria(mensaje.getIdTransaccionMensajeria());
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void borrarMensajesPorIdOperacion(Long idOperacion) throws NegocioExcepcion {
		try{
			if(!Validaciones.isObjectNull(idOperacion)){
				int registrosBorrados = mensajeriaTransaccionDao.borrarMensajesPorIdOperacion(idOperacion);
				Traza.auditoria(getClass(), "Se borraron " + registrosBorrados + " registros de mensajeria de la operacion: "+ idOperacion.toString());
			}
		} catch (PersistenciaExcepcion e) {
			Traza.error(getClass(), e.getMessage());
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	@Override
	public boolean mensajeAnteriormenteProcesado(Long idOperacion, Integer idTransaccion, MensajeServicioEnum servicio) throws NegocioExcepcion {
		try{
			return mensajeriaTransaccionDao.mensajeAnteriormenteProcesado(idOperacion, idTransaccion, servicio);
		} catch (PersistenciaExcepcion e) {
			Traza.error(getClass(), e.getMessage());
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
}
