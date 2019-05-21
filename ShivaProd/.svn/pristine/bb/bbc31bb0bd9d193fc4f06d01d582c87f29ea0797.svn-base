package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaGestionaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ConcurrenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.mapeadores.MapeadorResultadoBusqueda;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.mapeos.ReciboPreImpresoMapeador;
import ar.com.telecom.shiva.negocio.mapeos.ResultadoBusquedaReciboPreimpresoMapeador;
import ar.com.telecom.shiva.negocio.mapeos.ValorMapeador;
import ar.com.telecom.shiva.negocio.servicios.ICombosServicio;
import ar.com.telecom.shiva.negocio.servicios.IReciboPreImpresoServicio;
import ar.com.telecom.shiva.negocio.servicios.ITalonarioServicio;
import ar.com.telecom.shiva.negocio.servicios.ITareaServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;
import ar.com.telecom.shiva.negocio.servicios.IVistaSoporteServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowRecibosPreImpresos;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowTalonarios;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaTalonarios;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaReciboPreimpreso;
import ar.com.telecom.shiva.persistencia.dao.ICompensacionDao;
import ar.com.telecom.shiva.persistencia.dao.IGestorDao;
import ar.com.telecom.shiva.persistencia.dao.ITalonarioDao;
import ar.com.telecom.shiva.persistencia.dao.ITareaDao;
import ar.com.telecom.shiva.persistencia.dao.IVistaSoporteDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvTalCompensacion;
import ar.com.telecom.shiva.persistencia.modelo.ShvTalReciboPreImpreso;
import ar.com.telecom.shiva.persistencia.modelo.ShvTalTalonario;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamGestor;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ReciboPreImpresoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.TalonarioDto;
import ar.com.telecom.shiva.presentacion.bean.dto.TareaDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;
import ar.com.telecom.shiva.presentacion.bean.filtro.TalonarioFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaReciboPreImpresoFiltro;

public class TalonarioServicioImpl 
	extends Servicio implements ITalonarioServicio{
	
	@Autowired 
	ITalonarioDao talonarioDao;
	
	@Autowired
	IVistaSoporteServicio vistaSoporteServicio;
	
	@Autowired 
	IVistaSoporteDao vistaSoporteDao;
	
	@Autowired 
	IReciboPreImpresoServicio reciboServicio;
	
	@Autowired
	IGestorDao gestorDao;
	
	@Autowired
	ITareaDao tareaDao;
	
	@Autowired 
	IValorServicio valorServicio;
	
	@Autowired 
	private ValorMapeador valorMapeo;

	@Autowired 
	IWorkflowTalonarios workflowTalonarios;
	
	@Autowired 
	IWorkflowRecibosPreImpresos workflowRecibosPreImpresos;
	
	@Autowired
	ReciboPreImpresoMapeador reciboPreImpresoMapeo;
	
	@Autowired 
	ResultadoBusquedaReciboPreimpresoMapeador resultadoBusquedaReciboPreImpresoMapeo;
	
	@Autowired
	MailServicio mailServicio;
	
	@Autowired 
	ILdapServicio ldapServicio;
	
	@Autowired 
	ICompensacionDao compensacionDao;
	
	@Autowired 
	ITareaServicio tareaServicio;
	
	@Autowired 
	MapeadorResultadoBusqueda resultadoBusquedaTalonarioMapeador;
	
	@Autowired
	protected ICombosServicio combosServicio;
	
	protected static final String SELECT_EMPRESAS = "empresas";
	protected static final String SELECT_SEGMENTOS = "segmentos";
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public Long crear(DTO dto) throws NegocioExcepcion {
		
		TalonarioDto talonarioDto = (TalonarioDto) dto;
		
		try {
			ShvTalTalonario talonario = (ShvTalTalonario) defaultMapeador.map(talonarioDto, null);
			talonarioDto.setNroSerie(Utilidad.rellenarCerosIzquierda(talonarioDto.getNroSerie(),Constantes.LONGITUD_NRO_SERIE));
			talonarioDto.setRangoDesde(Utilidad.rellenarCerosIzquierda(talonarioDto.getRangoDesde(),Constantes.LONGITUD_NRO_RECIBO));
			talonarioDto.setRangoHasta(Utilidad.rellenarCerosIzquierda(talonarioDto.getRangoHasta(),Constantes.LONGITUD_NRO_RECIBO));
			String datosOriginales = Utilidad.datosOriginales(talonarioDto,getListaTalonarioOGestor(true));
			
			ShvWfWorkflow workflow = workflowTalonarios.crearWorkflow(datosOriginales, talonarioDto.getUsuarioAlta());
			talonario.setFechaAlta(workflow.getFechaUltimaModificacion());
			talonario.setUsuarioAlta(talonarioDto.getUsuarioAlta());
			talonario.setWorkflow(workflow);
			talonarioDao.insertarTalonario(talonario);
			
			//Creo la tarea y envio mail a los asignados
			TareaDto tarea = new TareaDto();
			
			String rango = generarRangoParaMail(talonario);
			
			tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
			tarea.setIdWorkflow(talonario.getWorkflow().getIdWorkflow());
			tarea.setTipoTarea(TipoTareaEnum.ASIG_GESTOR_TAL);
			tarea.setFechaCreacion(talonario.getWorkflow().getFechaUltimaModificacion());
			tarea.setUsuarioCreacion(talonario.getUsuarioAlta());
			tarea.setPerfilAsignacion(Constantes.SUPERVISOR_TALONARIO);
			
			tarea.setGestionaSobre(TipoTareaGestionaEnum.TALONARIO);
			tarea.setIdTalonario(Long.valueOf(talonario.getIdTalonario()));
			tarea.setEmpresa(talonario.getEmpresa().getIdEmpresa());
			tarea.setSegmento(talonario.getSegmento().getIdSegmento());
			/**
			 * @author u573005, sprint3, defecto 88
			 */
			String asuntoMail = Utilidad.reemplazarMensajes(Mensajes.TALONARIO_ASIGNADO, talonario.getSegmento().getDescripcion(), rango);
			tarea.setAsuntoMail(asuntoMail);
			
			// Crea una nueva tarea pendiente
			tareaServicio.crearTareaTalonario(tarea);
			
			if (talonario.getIdTalonario() != null) {
				return Long.parseLong(talonario.getIdTalonario().toString());
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
		return null;
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation=Propagation.REQUIRED)
	public void eliminarTareaRevisionTalonario(TalonarioDto talonarioDto, UsuarioSesion usuarioSesion) throws NegocioExcepcion {
		try {
			ShvTalTalonario	talonario = talonarioDao.buscarTalonario(Integer.valueOf(talonarioDto.getIdTalonario()));
			tareaServicio.finalizarTarea(TipoTareaEnum.REV_TAL, talonario.getWorkflow().getIdWorkflow(), new Date(), usuarioSesion.getIdUsuario(), null);
			//tareaServicio.eliminarTarea(TipoTareaEnum.REV_TAL, talonario.getWorkflow().getIdWorkflow(), usuarioSesion.getIdUsuario());
		} catch (Throwable e){
			throw new NegocioExcepcion(e);
		}
	}
	
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void rechazoTalonario(TalonarioDto talonarioDto) throws NegocioExcepcion {
		try {
			ShvTalTalonario	talonario = talonarioDao.buscarTalonario(Integer.valueOf(talonarioDto.getIdTalonario()));
			String rango = generarRangoParaMail(talonario);
			StringBuffer datosModificados =  new StringBuffer();
			verificarConcurrencia(talonarioDto.getIdTalonario(), Long.valueOf(talonarioDto.getTimeStampAux()));
			datosModificados.append("["+Constantes.DATOS_MODIFICADOS_OBSERVACIONES+"]("+Constantes.DATOS_MODIFICADOS_VALOR_ORIGINAL+": "+talonarioDto.getObservaciones()+")");
			workflowTalonarios.rechazarErrorRango(talonario.getWorkflow(), datosModificados.toString(), talonarioDto.getUsuarioModificacion());

			// Preparo e-mail
			/**
			 * @author u573005 sprint 3, defecto 66
			 */
			String to = mailServicio.obtenerMailUsuario(talonario.getUsuarioAlta());
			
			String subject = "- Segmento: " + talonario.getSegmento().getDescripcion();
			subject += "/" + rango;
			subject = Utilidad.reemplazarMensajes(Mensajes.TALONARIO_RECHAZADO, subject);
			
			StringBuffer body = new StringBuffer();
			body.append("Usuario que rechazó la asignación del talonario: " 
					+ ldapServicio.buscarUsuarioPorUidEnMemoria(talonarioDto.getUsuarioModificacion()).getNombreCompleto());
			if (!Validaciones.isNullOrEmpty(talonarioDto.getObservaciones())) {
				body.append("<br>" + Utilidad.reemplazarMensajes(Mensajes.CAMPO_OBSERVACIONES, talonarioDto.getObservaciones().trim()));
			}
			
			Mail mail = new Mail(to, null, subject, body);
			// Fin preparación e-mail
			
			if (talonario.getWorkflow().getEstado().equals(Estado.TAL_DERIVADO_ADMINISTRADOR)){
				
				TareaDto tarea = new TareaDto();
				tarea.setIdTalonario(Long.valueOf(talonario.getIdTalonario()));
				tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
				tarea.setIdWorkflow(talonario.getWorkflow().getIdWorkflow());
				tarea.setTipoTarea(TipoTareaEnum.REV_TAL);
				tarea.setFechaCreacion(talonario.getWorkflow().getFechaUltimaModificacion());
				tarea.setUsuarioCreacion(talonarioDto.getUsuarioModificacion());
				tarea.setPerfilAsignacion(Constantes.ADMIN_TALONARIO);
				tarea.setUsuarioAsignacion(tareaDao.buscarTareaPendiente(TipoTareaEnum.ASIG_GESTOR_TAL, talonario.getWorkflow().getIdWorkflow()).getUsuarioCreacion());
				tarea.setGestionaSobre(TipoTareaGestionaEnum.TALONARIO);

				tarea.setEnviarMail(false);
				
				// Crea una nueva tarea pendiente para el administrador de talonarios
				tareaServicio.crearTareaTalonario(tarea);
			}
			
			tareaServicio.finalizarTarea(TipoTareaEnum.ASIG_GESTOR_TAL, talonario.getWorkflow().getIdWorkflow(), new Date(), talonarioDto.getUsuarioModificacion(), null);
			
			// Envio del e-mail
			mailServicio.sendMail(mail);
			
		} catch (NumberFormatException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} 
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void rendirTalonario(TalonarioDto talonarioDto) throws NegocioExcepcion {
		try {
			ShvTalTalonario	talonario = talonarioDao.buscarTalonario(Integer.valueOf(talonarioDto.getIdTalonario()));
			
			String rango = generarRangoParaMail(talonario);
			
			talonario.setUsuarioRendicion(talonarioDto.getUsuarioRendicion());
			talonario.setFechaRendicion(new Date());
			StringBuffer datosModificados =  new StringBuffer();
			verificarConcurrenciaLista(talonario, Long.valueOf(talonarioDto.getTimeStampAux()));
			ShvWfWorkflow wf = null;
			if (talonarioDto.getEstado().equalsIgnoreCase(Estado.TAL_ASIGNADO_GESTOR.name())){
				wf = workflowTalonarios.asignarAdministrador(talonario.getWorkflow(), datosModificados.toString(), talonarioDto.getUsuarioRendicion());
			} else {
				wf= workflowTalonarios.reasignarAdministrador(talonario.getWorkflow(), datosModificados.toString(), talonarioDto.getUsuarioRendicion());
			}
			talonario.setWorkflow(wf);
			talonarioDao.actualizarTalonario(talonario);
			
			//Creo la tarea y envio mail a los asignados
			TareaDto tarea = new TareaDto();
			tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
			tarea.setIdWorkflow(talonario.getWorkflow().getIdWorkflow());
			tarea.setTipoTarea(TipoTareaEnum.AUTR_REND_TAL);
			tarea.setFechaCreacion(talonario.getWorkflow().getFechaUltimaModificacion());
			tarea.setUsuarioCreacion(talonario.getUsuarioRendicion());
			tarea.setPerfilAsignacion(Constantes.ADMIN_TALONARIO);
			
			tarea.setGestionaSobre(TipoTareaGestionaEnum.TALONARIO);
			tarea.setIdTalonario(Long.valueOf(talonario.getIdTalonario()));
			
			String asuntoMail = Utilidad.reemplazarMensajes(Mensajes.TALONARIO_RENDIDO, talonario.getSegmento().getDescripcion(), rango);
			tarea.setAsuntoMail(asuntoMail);
			
			tareaServicio.crearTareaTalonario(tarea);
			
			// Preparo e-mail
			String subject = "- Talonario rendido por otro usuario - Segmento: " + talonario.getSegmento().getDescripcion();
			subject += "/" + rango;
			subject = Utilidad.reemplazarMensajes(Mensajes.TALONARIO_RENDICION_RECHAZADA, subject);
			
			StringBuffer body = new StringBuffer();
			body.append("Usuario que rechazó la rendición del talonario: " 
					+ ldapServicio.buscarUsuarioPorUidEnMemoria(talonarioDto.getUsuarioRendicion()).getNombreCompleto());
			
			Mail mail = new Mail(null, null, subject, body);
			// Fin preparación e-mail
			
			tareaServicio.finalizarTarea(TipoTareaEnum.REV_REND_TAL, talonario.getWorkflow().getIdWorkflow(), talonarioDto.getUsuarioRendicion(), mail);
			
		} catch (NumberFormatException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} 	
	}

	private String generarRangoParaMail(ShvTalTalonario talonario) {
		String rango = "Rango: "+ Utilidad.rellenarCerosIzquierda(talonario.getNroSerie().toString(), Constantes.LONGITUD_NRO_SERIE) + "-" +
				Utilidad.rellenarCerosIzquierda(talonario.getRangoDesde().toString(), Constantes.LONGITUD_NRO_RECIBO) 
				+ " a " + Utilidad.rellenarCerosIzquierda(talonario.getNroSerie().toString(), Constantes.LONGITUD_NRO_SERIE) + "-" + 
				Utilidad.rellenarCerosIzquierda(talonario.getRangoHasta().toString(), Constantes.LONGITUD_NRO_RECIBO);
		return rango;
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void anularTalonario(String idTalonario, String usuarioModificacion) throws NegocioExcepcion {
		try {
			ShvTalTalonario talonarioModelo = talonarioDao.buscarTalonario(Integer.valueOf(idTalonario));
			boolean anular = true;
			for (ShvTalReciboPreImpreso rec : talonarioModelo.getShvTalReciboPreImpreso()) {
				if (!rec.getWorkflow().getShvWfWorkflowEstado().iterator().next().getEstado().equals(Estado.REC_ANULADO)){
					anular = false;
				}
			}
			if (anular){
				workflowTalonarios.dispararAnulacionAutomatica(talonarioModelo.getWorkflow(), "", usuarioModificacion);
			}
		} catch (NumberFormatException | PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void anular(DTO dto) throws NegocioExcepcion {
		TalonarioDto talonarioDto = (TalonarioDto) dto;
		
		try {
			ShvTalTalonario talonarioModelo = talonarioDao.buscarTalonario(Integer.valueOf(talonarioDto.getIdTalonario()));
			boolean anular = true;
			for (ShvTalReciboPreImpreso rec : talonarioModelo.getShvTalReciboPreImpreso()) {
				if (!rec.getWorkflow().getShvWfWorkflowEstado().iterator().next().getEstado().equals(Estado.REC_ANULADO)){
					anular = false;
				}
			}
			if (anular){
				workflowTalonarios.dispararAnulacionAutomatica(talonarioModelo.getWorkflow(), "", talonarioDto.getUsuarioModificacion());
			}
		} catch (NumberFormatException | PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void rechazarRendicionTalonario(TalonarioDto talonarioDto, UsuarioSesion userSesion) throws NegocioExcepcion {
		try {

			ShvTalTalonario	talonario = talonarioDao.buscarTalonario(Integer.valueOf(talonarioDto.getIdTalonario()));
			String rango = generarRangoParaMail(talonario);
			StringBuffer datosModificados =  new StringBuffer();
			datosModificados.append("["+Constantes.DATOS_MODIFICADOS_OBSERVACIONES+"]("+talonarioDto.getObservaciones()+")");
			ShvWfWorkflow wf = workflowTalonarios.rechazarRendicion(talonario.getWorkflow(), datosModificados.toString(), userSesion.getIdUsuario());
			talonario.setWorkflow(wf);
			talonarioDao.actualizarTalonario(talonario);
			
			// Preparo e-mail
			String to = mailServicio.obtenerMailUsuario(talonario.getUsuarioRendicion());
			String subject = "- Segmento: " + talonario.getSegmento().getDescripcion();
			subject += "/" + rango;
			subject = Utilidad.reemplazarMensajes(Mensajes.TALONARIO_RENDICION_RECHAZADA, subject);
			
			StringBuffer body = new StringBuffer();
			body.append("Usuario que rechazó la rendición del talonario: " + userSesion.getNombreCompleto());
			if (!Validaciones.isNullOrEmpty(talonarioDto.getObservaciones())) {
				body.append("<br>" + Utilidad.reemplazarMensajes(Mensajes.CAMPO_OBSERVACIONES, talonarioDto.getObservaciones().trim()));
			}
			
			Mail mail = new Mail(to, null, subject, body);
			// Fin preparación e-mail
			
			tareaServicio.finalizarTarea(TipoTareaEnum.AUTR_REND_TAL, talonario.getWorkflow().getIdWorkflow(), new Date(), talonarioDto.getUsuarioModificacion(), null);
			
			TareaDto tarea = new TareaDto();
			tarea.setIdTalonario(Long.valueOf(talonario.getIdTalonario()));
			tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
			tarea.setIdWorkflow(talonario.getWorkflow().getIdWorkflow());
			tarea.setTipoTarea(TipoTareaEnum.REV_REND_TAL);
			tarea.setFechaCreacion(talonario.getWorkflow().getFechaUltimaModificacion());
			tarea.setUsuarioCreacion(userSesion.getIdUsuario());
			tarea.setPerfilAsignacion(Constantes.SUPERVISOR_TALONARIO);
			tarea.setGestionaSobre(TipoTareaGestionaEnum.TALONARIO);
			tarea.setEnviarMail(false);
			tareaServicio.crearTareaTalonario(tarea);
			
			// Envio del e-mail
			mailServicio.sendMail(mail);
			
		} catch (NumberFormatException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void autorizarRendicionTalonario(TalonarioDto talonarioDto, UsuarioSesion userSesion) throws NegocioExcepcion {
		try {

			boolean puedeSerAutorizado = true;
			String reciboAnterior = "";
			String mensajeError = "";
			
			ShvTalTalonario	talonario = talonarioDao.buscarTalonario(Integer.valueOf(talonarioDto.getIdTalonario()));
			
			Set<ShvTalReciboPreImpreso> talPreImpreso = talonario.getShvTalReciboPreImpreso();
			
			for (ShvTalReciboPreImpreso recibo : talPreImpreso) {
				if (recibo.getWorkflow().getShvWfWorkflowEstado().iterator().next().getEstado().equals(Estado.REC_VALORES_PENDIENTE)) {
					puedeSerAutorizado = false;
					List<List<ShvValValor>> valores = valorServicio.valoresDeUnRecibo(recibo.getNumeroRecibo());
					
					for (List<ShvValValor> valorTempList : valores) {
						for (ShvValValor valorTemp : valorTempList) {		
							if (valorTemp.getWorkFlow().getEstado().equals(Estado.VAL_BOLETA_PENDIENTE_CONCILIACION) 							
								|| valorTemp.getWorkFlow().getEstado().equals(Estado.VAL_BOLETA_PENDIENTE_AUTORIZAR)) {								
								if (Validaciones.isNullOrEmpty(mensajeError) || reciboAnterior != recibo.getNumeroRecibo()) {
									mensajeError = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("recibo.error.autorizarRendicion"), recibo.getNumeroRecibo());
									mensajeError += "<br>" + valorTemp.getNumeroValor();
								} else {
									mensajeError += "<br>" + valorTemp.getNumeroValor();
								}
							}
						}
					}
					reciboAnterior = recibo.getNumeroRecibo();
				}
			}
			talonarioDto.setErrorRendicionMensaje(mensajeError);
			
			if (puedeSerAutorizado) {
				talonario.setUsuarioAprobacionRendicion(userSesion.getIdUsuario());
				Date date = new Date();
				talonario.setFechaAprobacionRendicion(date);
				
				StringBuffer datosModificados =  new StringBuffer();
				ShvWfWorkflow wf = workflowTalonarios.aceptarRendicion(talonario.getWorkflow(), datosModificados.toString(), userSesion.getIdUsuario());
				talonario.setWorkflow(wf);
				talonarioDao.actualizarTalonario(talonario);
				
				//Finalizo la tarea Pendiente (Autorizar Rendicion de Talonario) 
				//y mando mail al generador de la tarea
				Date fechaAsignacion = new Date();
				tareaServicio.finalizarTarea(TipoTareaEnum.AUTR_REND_TAL, talonario.getWorkflow().getIdWorkflow(), fechaAsignacion, userSesion.getIdUsuario(), null);
			} else {
				throw new NegocioExcepcion("No se puedeAutorizar");
			}
			
		} catch (NumberFormatException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	/**
	 * verifica si existe el rango de talonario
	 */
	public Boolean existeTalonarioRango(String desde, String hasta, String nroSerie) throws NegocioExcepcion{
		
		try {
			return talonarioDao.verificacionRango(desde, hasta, nroSerie);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}

	}
	
	/**
	 * verifica si existe el rango de talonario
	 */
	public Boolean existeTalonarioRango(String desde, String hasta, String idTalonario, String nroSerie) throws NegocioExcepcion{
		
		try {
			return talonarioDao.verificacionRango(desde, hasta, idTalonario, nroSerie);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
	}
	
	/**
	 * devuelve una lista con los estados de talonario
	 */
	@Override
	public List<Estado> listarComboEstados() throws NegocioExcepcion {
		try {
			List<Estado> lista = Estado.listarEstados("TAL");
			return lista;
		} catch (Throwable e){
			throw new NegocioExcepcion(e);
		}
	}
	
	/**
	 * devuelve una lista con los gestores segun empresa y segmento
	 */
	@Override
	public List<ShvParamGestor> comboGestores(TalonarioDto talonarioDto)
			throws NegocioExcepcion {
		try {
			return gestorDao.listarGestoresActivos(talonarioDto.getIdEmpresa(), talonarioDto.getIdSegmento());
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	/**
	 * busca los talonarios, en la base de datos, segun el filtro y devuelve una lista
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Collection<DTO> listar(Filtro filtro) throws NegocioExcepcion {
		TalonarioFiltro talonarioFiltro = (TalonarioFiltro) filtro;
		try {
			Collection<ShvTalTalonario> listaTalonarioModelo;
			listaTalonarioModelo = talonarioDao.listarTalonarios(talonarioFiltro);
			Collection<DTO> listaTalonarioDTO = defaultMapeador.map(listaTalonarioModelo);
			
			return listaTalonarioDTO;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	
	public void cargarCombosTalonarios(ModelAndView mv,
			UsuarioSesion userSesion, Filtro filtro) throws ShivaExcepcion {
		try {
			List<ShvParamEmpresa> listaEmpresas = (List<ShvParamEmpresa>) combosServicio
					.listarEmpresasPorUsuario(userSesion);
			List<ShvParamSegmento> listaSegmentos = new ArrayList<ShvParamSegmento>();
			
			ShvParamSegmento todos = new ShvParamSegmento();
			todos.setIdSegmento("todos");
			todos.setId("todos");
			todos.setDescripcion("Todos");

			if (listaEmpresas.size() == 1) {
				String idEmpresa = listaEmpresas.get(0).getIdEmpresa();
				filtro.setEmpresa(idEmpresa);
				listaSegmentos = combosServicio
						.listarSegmentoPorEmpresaYUsuario(idEmpresa, userSesion);

				mv.addObject("comboEmpresa", false);
			} else {
				if (!Validaciones.isNullOrEmpty(filtro.getEmpresa())) {
					listaSegmentos = combosServicio
							.listarSegmentoPorEmpresaYUsuario(
									filtro.getEmpresa(), userSesion);
				}
				mv.addObject("comboEmpresa", true);
			}
			
			listaSegmentos.add(todos);
			
			mv.addObject(SELECT_SEGMENTOS, listaSegmentos);
			if (listaSegmentos.size() > 2) {
				mv.addObject("comboSegmento", true);
			} else {
				mv.addObject("comboSegmento", false);
				if (listaSegmentos.size() == 1) {
					filtro.setSegmento(listaSegmentos.get(0).getIdSegmento());
				}
			}

			mv.addObject(SELECT_EMPRESAS, listaEmpresas);
		} catch (NegocioExcepcion e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * busca un talonario por el id
	 */
	@Override
	public DTO buscar(Integer id) throws NegocioExcepcion {
		try {
			ShvTalTalonario talonarioModelo;
			talonarioModelo = talonarioDao.buscarTalonario(id);
			TalonarioDto talonarioDTO = (TalonarioDto) defaultMapeador.map(talonarioModelo);
			
			return talonarioDTO;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	/**
	 * busca los recibos en la base de datos y devuelve una lista
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ReciboPreImpresoDto> buscarRecibos(String talonarioId) throws NegocioExcepcion {
		try {
			List<VistaSoporteResultadoBusquedaReciboPreimpreso> listaVistaSoporteResultadoBusquedaReciboPreimpreso;
			VistaSoporteBusquedaReciboPreImpresoFiltro filtro = new VistaSoporteBusquedaReciboPreImpresoFiltro();
			filtro.setIdTalonario(talonarioId);
			listaVistaSoporteResultadoBusquedaReciboPreimpreso = vistaSoporteDao.buscarRecibos(filtro);
			List<ReciboPreImpresoDto> listaRecDto = (List<ReciboPreImpresoDto>) resultadoBusquedaReciboPreImpresoMapeo.map(listaVistaSoporteResultadoBusquedaReciboPreimpreso);
			return listaRecDto;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	/**
	 * Ejecuta la modificacion del talonario
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	@Override
	public void modificar(DTO dto) throws NegocioExcepcion {
		TalonarioDto talonarioDto = (TalonarioDto) dto;
		String[] concatenacion = talonarioDto.getIdEmpresa().split("\\|");
		if (concatenacion.length >=2){
			talonarioDto.setIdEmpresa(concatenacion[0]);
			talonarioDto.setEmpresa(concatenacion[1]);
		}
		concatenacion = talonarioDto.getIdSegmento().split("\\|");
		if (concatenacion.length >=2){
			talonarioDto.setIdSegmento(concatenacion[0]);
			talonarioDto.setSegmento(concatenacion[1]);
		}
		try {
			ShvTalTalonario modeloViejo = talonarioDao.buscarTalonario(Integer.valueOf(talonarioDto.getIdTalonario()));
			
			TalonarioDto talonarioViejo = (TalonarioDto) defaultMapeador.map(modeloViejo);
			ShvTalTalonario talonario = (ShvTalTalonario) defaultMapeador.map(talonarioDto, modeloViejo);
			
			String rango = generarRangoParaMail(talonario);
			
			talonarioDto.setNroSerie(Utilidad.rellenarCerosIzquierda(talonarioDto.getNroSerie(),Constantes.LONGITUD_NRO_SERIE));
			talonarioDto.setRangoDesde(Utilidad.rellenarCerosIzquierda(talonarioDto.getRangoDesde(),Constantes.LONGITUD_NRO_RECIBO));
			talonarioDto.setRangoHasta(Utilidad.rellenarCerosIzquierda(talonarioDto.getRangoHasta(),Constantes.LONGITUD_NRO_RECIBO));
			String datosModificados;
				datosModificados = Utilidad.generarDatosModificados(
						talonarioViejo,talonarioDto,getListaTalonarioOGestor(true));
			ShvWfWorkflow wf = modeloViejo.getWorkflow();
			
			//CONCURRENCIA
			verificarConcurrencia(talonarioDto.getIdTalonario(), Long.valueOf(talonarioDto.getTimeStampAux()));
			
			String asuntoMail = Utilidad.reemplazarMensajes(Mensajes.TALONARIO_MODIFICADO,talonarioDto.getEmpresa(), rango);
			
			Estado estadoViejoWorkflow = modeloViejo.getWorkflow().getShvWfWorkflowEstado().iterator().next().getEstado(); 
			
			if (estadoViejoWorkflow.equals(Estado.TAL_DERIVADO_ADMINISTRADOR)){
				talonario.setWorkflow(workflowTalonarios.reasignarSupervisor(wf, datosModificados, talonarioDto.getUsuarioModificacion()));
			}
			
			talonarioDao.actualizarTalonario(talonario);
			
			//Creo una nueva tarea (Asignar gestor del talonario) y envio el correo
			if (estadoViejoWorkflow.equals(Estado.TAL_DERIVADO_ADMINISTRADOR)){
				asuntoMail = Utilidad.reemplazarMensajes(Mensajes.TALONARIO_ASIGNADO,talonarioDto.getSegmento(), rango);
				
				TareaDto tarea = new TareaDto();
				tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
				tarea.setIdWorkflow(talonario.getWorkflow().getIdWorkflow());
				tarea.setTipoTarea(TipoTareaEnum.ASIG_GESTOR_TAL);
				tarea.setFechaCreacion(talonario.getWorkflow().getFechaUltimaModificacion());
				tarea.setUsuarioCreacion(talonario.getUsuarioAlta());
				tarea.setPerfilAsignacion(Constantes.SUPERVISOR_TALONARIO);
				
				tarea.setGestionaSobre(TipoTareaGestionaEnum.TALONARIO);
				tarea.setIdTalonario(Long.valueOf(talonario.getIdTalonario()));
				tarea.setEmpresa(talonario.getEmpresa().getIdEmpresa());
				tarea.setSegmento(talonario.getSegmento().getIdSegmento());
				tarea.setAsuntoMail(asuntoMail);
				
				// Crea una nueva tarea pendiente
				tareaServicio.crearTareaTalonario(tarea);
				
				// Preparo e-mail
				String subject = "- Talonario tomado por otro usuario - ";
				subject += "Segmento: " + talonario.getSegmento().getDescripcion();
				subject += "/" + rango;
				subject = Utilidad.reemplazarMensajes(Mensajes.TALONARIO_RECHAZADO, subject);
				
				StringBuffer body = new StringBuffer();
				body.append("Usuario que tomó el talonario: " 
						+ ldapServicio.buscarUsuarioPorUidEnMemoria(talonarioDto.getUsuarioModificacion()).getNombreCompleto());
								
				Mail mail = new Mail(null, null, subject, body);
				// Fin preparación e-mail
				tareaServicio.finalizarTarea(TipoTareaEnum.REV_TAL, talonario.getWorkflow().getIdWorkflow(), talonarioDto.getUsuarioModificacion(), mail);
				
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * asigna un gestor al talonario recibe un talonarioDto
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void asignarGestor(TalonarioDto talonarioDto) throws NegocioExcepcion{
		ShvTalTalonario talonario;
		TalonarioDto talonarioViejo;
		try {
			talonario = talonarioDao.buscarTalonario(Integer.valueOf(talonarioDto.getIdTalonario()));
			talonario.setUsuarioAsignacion(talonarioDto.getUsuarioModificacion());
			talonarioViejo = (TalonarioDto) defaultMapeador.map(talonario);
			String datosModificados = Utilidad.generarDatosModificados(
					talonarioViejo,talonarioDto,getListaTalonarioOGestor(false));
			ShvWfWorkflow wf = talonario.getWorkflow();
			ShvWfWorkflow wfActualizado = null;
			
			verificarConcurrencia(talonarioDto.getIdTalonario(), Long.valueOf(talonarioDto.getTimeStampAux()));
			
			//Actualizo el workflow
			wfActualizado = workflowTalonarios.asignarGestor(wf, datosModificados, talonarioDto.getUsuarioModificacion());
		
			//Nuevos Recibos
			for (int i = Integer.valueOf(talonarioDto.getRangoDesde()); i <= Integer.valueOf(talonarioDto.getRangoHasta()); i++) {
				ShvTalReciboPreImpreso rec = new ShvTalReciboPreImpreso();
				rec.setTalonario(talonario);
				rec.setNumeroRecibo(Utilidad.rellenarCerosIzquierda(talonarioDto.getNroSerie(),Constantes.LONGITUD_NRO_SERIE)+"-"+Utilidad.rellenarCerosIzquierda(String.valueOf(i),Constantes.LONGITUD_NRO_RECIBO));
				String datosOriginales = "["+Constantes.DATOS_MODIFICADOS_NRO_RECIBO+"]("+Constantes.DATOS_MODIFICADOS_VALOR+": "+rec.getNumeroRecibo()+")["+Constantes.DATOS_MODIFICADOS_ID_TALONARIO+"]("+Constantes.DATOS_MODIFICADOS_VALOR+": "+rec.getTalonario().getIdTalonario()+")";
				rec.setWorkflow(workflowRecibosPreImpresos.crearWorkflow(datosOriginales, talonarioDto.getUsuarioModificacion()));
				talonario.getShvTalReciboPreImpreso().add(rec);
			}
			
			ShvParamGestor usuarioGestor = new ShvParamGestor();
			usuarioGestor.setIdGestor(Integer.valueOf(talonarioDto.getIdUsuarioGestor()));
			usuarioGestor.setNombreYApellido(talonarioDto.getUsuarioGestor());
			talonario.setUsuarioGestor(usuarioGestor);
			Date fechaAsignacion = new Date();
			talonario.setFechaAsignacion(fechaAsignacion);
			talonario.setWorkflow(wfActualizado); 
			talonarioDao.actualizarTalonario(talonario); 
			
			//Finalizo la tarea Pendiente (Asignar Gestor del Talonario) 
			//pero no mando mail al generador de la tarea
			tareaServicio.finalizarTarea(TipoTareaEnum.ASIG_GESTOR_TAL, talonario.getWorkflow().getIdWorkflow(), fechaAsignacion, talonarioDto.getUsuarioModificacion(), null);
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	/**
	 * devuelve una lista con las compensaciones de un recibo según el id
	 */
	public List<ShvTalCompensacion> listaCompensaciones(Integer idRecibo) throws NegocioExcepcion {
		
		try {
			return compensacionDao.listarCompensacionesPorIdRecibo(idRecibo);
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e); 
		}
		  
	}
	
	/**
	 * Busco el recibo
	 */
	@SuppressWarnings("unchecked")
	public ReciboPreImpresoDto buscarRecibo(String idRecibo) throws NegocioExcepcion {
		
		try {
			List<VistaSoporteResultadoBusquedaReciboPreimpreso> listaVistaSoporteResultadoBusquedaReciboPreimpreso;
			VistaSoporteBusquedaReciboPreImpresoFiltro filtro = new VistaSoporteBusquedaReciboPreImpresoFiltro();
			filtro.setIdRecibo(idRecibo);
			listaVistaSoporteResultadoBusquedaReciboPreimpreso = vistaSoporteDao.buscarRecibos(filtro);
			List<ReciboPreImpresoDto> listaRecDto = (List<ReciboPreImpresoDto>) resultadoBusquedaReciboPreImpresoMapeo.map(listaVistaSoporteResultadoBusquedaReciboPreimpreso);
			return listaRecDto.get(0);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e); 
		}
	}
	
	public void verificarConcurrenciaLista(ShvTalTalonario talonarioModelo, Long timeStamp) throws ConcurrenciaExcepcion {
		String listaIdConcurrentes = "";
		Iterator<ShvTalReciboPreImpreso> iterador = talonarioModelo.getShvTalReciboPreImpreso().iterator();
		while (iterador.hasNext()){
			ShvTalReciboPreImpreso next = iterador.next();
			if (next.getWorkflow().getFechaUltimaModificacion().getTime() > timeStamp.longValue()){
				listaIdConcurrentes += next.getNumeroRecibo()+",";
			}
		}
		if (!listaIdConcurrentes.equals("")){
			listaIdConcurrentes = listaIdConcurrentes.substring(0,listaIdConcurrentes.length()-1);
			throw new ConcurrenciaExcepcion(listaIdConcurrentes);
		}
	}

	public String validarFechaIngresoRecibos(String idTalonario) throws NegocioExcepcion{
		
		try {

			List<ReciboPreImpresoDto> lista = buscarRecibos(idTalonario);
			
			String error = "";
			boolean hayError = false;
			for (ReciboPreImpresoDto recibo : lista) {
				String mensajeError = reciboServicio.validarFechaIngreso(recibo);
					if (!Validaciones.isNullOrEmpty(mensajeError)){
						if (!error.equals("")){
								error += "<br>"+mensajeError;
							} else {
								error += mensajeError;
							}
							hayError = true;
							error += "<br>";
						}
				}
				
			if (hayError){
				return error;
			} else {
				return null;
			}
		} catch (NumberFormatException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	public String validarFechaIngresoNula(TalonarioDto talonarioDto) throws NegocioExcepcion{
		ShvTalTalonario talonarioModelo;
		String error = "";
		TreeSet<String> listaRecibos = new TreeSet<String>();
		try {
			talonarioModelo = talonarioDao.buscarTalonario(Integer.valueOf(talonarioDto.getIdTalonario()));
			for (ShvTalReciboPreImpreso recibo : talonarioModelo.getShvTalReciboPreImpreso()) {
				
				if(Validaciones.isObjectNull(recibo.getFechaIngreso()) && !Estado.REC_ANULADO.equals(recibo.getWorkflow().getEstado())){
					listaRecibos.add(recibo.getNumeroRecibo());
				}
			}
		} catch (NumberFormatException | PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
		
		if(!listaRecibos.isEmpty()){
			Iterator<String> iterator = listaRecibos.iterator();
			while (iterator.hasNext()) {
				if(Validaciones.isNullOrEmpty(error)){
					error = "Se debe ingresar una Fecha Ingreso para el/los recibo/s " + iterator.next();
				} else {
					error += ", " + iterator.next();
				}
			}
			error += ". Hasta tanto no se completen las fechas faltantes no se podrá rendir el talonario.";
		}
		return error;
	}
	
	/**
	 * Verifica si hay concurrencia para la modificacion
	 */
	@Override
	public void verificarConcurrencia(Serializable id, Long timeStamp)
			throws NegocioExcepcion {
		try {
			ShvTalTalonario talonarioModelo = talonarioDao.buscarTalonario(Integer.valueOf(id.toString()));
			if (talonarioModelo.getWorkflow().getFechaUltimaModificacion().getTime() > timeStamp.longValue()){
				throw new ConcurrenciaExcepcion();
			} 
		} catch (NumberFormatException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	
	/***************************************************************************
	 * Lista de datos modificados para el workflow
	 * ************************************************************************/
	/**
	 * devuelve un string con los datos modificados. Compara, por el id, el dto que recibe con el talonario de la base de datos.
	 */
	public String getDatosModificados(DTO dto) throws NegocioExcepcion {
		TalonarioDto talonarioDto = (TalonarioDto) dto;
		String[] concatenacion = talonarioDto.getIdEmpresa().split("\\|");
		if (concatenacion.length >=2){
			talonarioDto.setIdEmpresa(concatenacion[0]);
			talonarioDto.setEmpresa(concatenacion[1]);
		}
		concatenacion = talonarioDto.getIdSegmento().split("\\|");
		if (concatenacion.length >=2){
			talonarioDto.setIdSegmento(concatenacion[0]);
			talonarioDto.setSegmento(concatenacion[1]);
		}
		try {
			ShvTalTalonario talonarioModelo = talonarioDao.buscarTalonario(Integer.valueOf(talonarioDto.getIdTalonario()));
							
			//Datos Modificados
			TalonarioDto talonarioDtoViejo = (TalonarioDto) defaultMapeador.map(talonarioModelo);
			return Utilidad.generarDatosModificados(talonarioDtoViejo,talonarioDto,getListaModificarTalonario());
		
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} 
	}
	

	/**
	 * Retorna la lista de todos los atributos del Dto que deben compararse para la modificacion de Talonarios.
	 * @return
	 */
	private List<String> getListaModificarTalonario() {
		List<String> lista = new ArrayList<String>();
		lista.add("timeStamp");
		lista.add("empresa");
		lista.add("segmento");
		lista.add("rangoDesde");
		lista.add("rangoHasta");
		lista.add("usuarioGestor");
		lista.add("nroSerie");
		return lista;
	}
	
	/**
	 * devuelve la lista de campos del talonario o usuario gestor. 
	 * Recibe un boolean, si es true devuelve los campos del Talonario, si es false el gestor
	 * @param AdminOSup
	 * @return
	 */
	private List<String> getListaTalonarioOGestor(Boolean AdminOSup) {
		List<String> lista = new ArrayList<String>();
		if (AdminOSup){
			lista.add("empresa");
			lista.add("segmento");
			lista.add("rangoDesde");
			lista.add("rangoHasta");
			lista.add("nroSerie");
		}else{	
			lista.add("usuarioGestor");
		}
		return lista;
	}
	
	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/
	public ITalonarioDao getTalonarioDao() {
		return talonarioDao;
	}
	public void setTalonarioDao(ITalonarioDao talonarioDao) {
		this.talonarioDao = talonarioDao;
	}
	
	public IWorkflowTalonarios getWorkflowTalonarios() {
		return workflowTalonarios;
	}
	public void setWorkflowTalonarios(IWorkflowTalonarios workflowTalonarios) {
		this.workflowTalonarios = workflowTalonarios;
	}
	
	public ReciboPreImpresoMapeador getReciboPreImpresoMapeo() {
		return reciboPreImpresoMapeo;
	}
	public void setReciboPreImpresoMapeo(
			ReciboPreImpresoMapeador reciboPreImpresoMapeo) {
		this.reciboPreImpresoMapeo = reciboPreImpresoMapeo;
	}
	public ResultadoBusquedaReciboPreimpresoMapeador getResultadoBusquedaReciboPreImpresoMapeo() {
		return resultadoBusquedaReciboPreImpresoMapeo;
	}
	public void setResultadoBusquedaReciboPreImpresoMapeo(ResultadoBusquedaReciboPreimpresoMapeador resultadoBusquedaReciboPreimpresoMapeador) {
		this.resultadoBusquedaReciboPreImpresoMapeo = resultadoBusquedaReciboPreimpresoMapeador;
	}

	public IValorServicio getValorServicio() {
		return valorServicio;
	}

	public void setValorServicio(IValorServicio valorServicio) {
		this.valorServicio = valorServicio;
	}

	public ICompensacionDao getCompensacionDao() {
		return compensacionDao;
	}

	public void setCompensacionDao(ICompensacionDao compensacionDao) {
		this.compensacionDao = compensacionDao;
	}

	public MailServicio getMailServicio() {
		return mailServicio;
	}

	public void setMailServicio(MailServicio mailServicio) {
		this.mailServicio = mailServicio;
	}

	public IGestorDao getGestorDao() {
		return gestorDao;
	}

	public void setGestorDao(IGestorDao gestorDao) {
		this.gestorDao = gestorDao;
	}

	public ILdapServicio getLdapServicio() {
		return ldapServicio;
	}

	public void setLdapServicio(ILdapServicio ldapServicio) {
		this.ldapServicio = ldapServicio;
	}

	public ITareaServicio getTareaServicio() {
		return tareaServicio;
	}

	public void setTareaServicio(ITareaServicio tareaServicio) {
		this.tareaServicio = tareaServicio;
	}

	public ITareaDao getTareaDao() {
		return tareaDao;
	}

	public void setTareaDao(ITareaDao tareaDao) {
		this.tareaDao = tareaDao;
	}

	@Override
	public ArrayList<TalonarioDto> buscarTalonarios(
			TalonarioFiltro talonarioFiltro) throws NegocioExcepcion {
		try {
			ArrayList<TalonarioDto> listaTalonarios = new ArrayList<TalonarioDto>();

			List<VistaSoporteResultadoBusquedaTalonarios> listaTalonariosVistaSoporte;

			
			listaTalonariosVistaSoporte = vistaSoporteServicio.buscarTalonarios(talonarioFiltro);

			TalonarioDto talonarioDto = new TalonarioDto();
			for (VistaSoporteResultadoBusquedaTalonarios vistaSoporteResultadoBusquedaTalonarios : listaTalonariosVistaSoporte) {
				talonarioDto = (TalonarioDto) resultadoBusquedaTalonarioMapeador.map(vistaSoporteResultadoBusquedaTalonarios);
				listaTalonarios.add(talonarioDto);					
			
			}
			return listaTalonarios;
		} catch (Exception e) {
			throw new NegocioExcepcion(e);
		}
	}
	
	/**
	 * @return the resultadoBusquedaTalonarioMapeador
	 */
	public MapeadorResultadoBusqueda getResultadoBusquedaTalonarioMapeador() {
		return resultadoBusquedaTalonarioMapeador;
	}

	/**
	 * @param resultadoBusquedaTalonarioMapeador the resultadoBusquedaTalonarioMapeador to set
	 */
	public void setResultadoBusquedaTalonarioMapeador(
			MapeadorResultadoBusqueda resultadoBusquedaTalonarioMapeador) {
		this.resultadoBusquedaTalonarioMapeador = resultadoBusquedaTalonarioMapeador;
	}
	
}
