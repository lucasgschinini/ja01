package ar.com.telecom.shiva.negocio.servicios.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoAccionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEstadoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ConcurrenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.LdapExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.mapeadores.MapeadorResultadoBusqueda;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.negocio.servicios.IDescobroServicio;
import ar.com.telecom.shiva.negocio.servicios.ITareaServicio;
import ar.com.telecom.shiva.negocio.servicios.IVistaSoporteServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaTareaPendiente;
import ar.com.telecom.shiva.persistencia.dao.ITalonarioDao;
import ar.com.telecom.shiva.persistencia.dao.ITareaDao;
import ar.com.telecom.shiva.persistencia.dao.IValorDao;
import ar.com.telecom.shiva.persistencia.dao.IWorkflowDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfTarea;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.TareaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteTareasPendientesFiltro;

public class TareaServicioImpl extends Servicio implements ITareaServicio {

	@Autowired
	ITareaDao tareaDao;
	@Autowired
	MailServicio mailServicio;
	@Autowired 
	ILdapServicio ldapServicio;
	@Autowired
	IValorDao valorDao;
	@Autowired
	ITalonarioDao talonarioDao;
	@Autowired
	IWorkflowDao workflowDao;
	
	@Autowired
	IVistaSoporteServicio vistaSoporteServicio;
	@Autowired
	MapeadorResultadoBusqueda resultadoBusquedaTareaPendienteMapeador;
	@Autowired
	ICobroOnlineServicio cobroOnlineServicio;
	@Autowired
	IDescobroServicio descobroServicio;
	
	@Override
	public void crearTareaTalonario(DTO dto) throws NegocioExcepcion {
		
		TareaDto tareaDto = (TareaDto) dto;
		tareaDto.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		crearTarea(tareaDto);
	}

	@Override
	public void crearTareaValor(DTO dto) throws NegocioExcepcion {
		TareaDto tareaDto = (TareaDto) dto;
		tareaDto.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		crearTarea(tareaDto);
	}

	@Override
	public void crearTareaRegistroAVC(DTO dto) throws NegocioExcepcion {
		TareaDto tareaDto = (TareaDto) dto;
		tareaDto.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		crearTarea(tareaDto);
	}
	
	/**
	 * No se usa 
	 */
	public void eliminarTarea(TipoTareaEnum tipoTarea, Integer idWorkflow, String usuarioAsignado) throws NegocioExcepcion {
		try {
			ShvWfTarea tarea = tareaDao.buscarTareaPendiente(tipoTarea, idWorkflow);
			if (tarea != null 
					&& tarea.getUsuarioAsignacion() != null 
					&& tarea.getUsuarioAsignacion().toUpperCase().equals(usuarioAsignado.toUpperCase())) {
				tareaDao.eliminarTarea(tarea);
				
			} else {
				Traza.auditoria(getClass(), "No se ha encontrado la tarea para el workflow: " + idWorkflow + " y el usuario: " + usuarioAsignado);
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	@Override
	public void crearTareaValorPorReversion(DTO dto) throws NegocioExcepcion {
		TareaDto tareaDto = (TareaDto) dto;
		tareaDto.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		crearTarea(tareaDto);
	}
	
	@Override
	public void crearTareaOperacionMasiva(DTO dto) throws NegocioExcepcion {
		TareaDto tareaDto = (TareaDto) dto;
		tareaDto.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		crearTarea(tareaDto);
	}
	
	@Override
	public void crearTareaResultadoValidacionDebitos(DTO dto) throws NegocioExcepcion {
		TareaDto tareaDto = (TareaDto) dto;
		tareaDto.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		crearTarea(tareaDto);
	}
	
	@Override
	public void crearTareaAprobacionCobro(DTO dto) throws NegocioExcepcion {
		TareaDto tareaDto = (TareaDto) dto;
		tareaDto.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		crearTarea(tareaDto);
	}
	
	@Override
	public void crearTareaImputacionManualCobro(DTO dto) throws NegocioExcepcion {
		TareaDto tareaDto = (TareaDto) dto;
		tareaDto.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		crearTarea(tareaDto);
	}
	
	@Override
	public void crearTareaRechazoAprobacionCobro(DTO dto) throws NegocioExcepcion {
		TareaDto tareaDto = (TareaDto) dto;
		tareaDto.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		crearTarea(tareaDto);
	}
	
	@Override
	public void crearTareaRechazoAprobacionOperacionMasiva(DTO dto) throws NegocioExcepcion {
		TareaDto tareaDto = (TareaDto) dto;
		tareaDto.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		crearTarea(tareaDto);
	}
	
	@Override
	public void crearTareaResultadoImputacion(DTO dto) throws NegocioExcepcion {
		TareaDto tareaDto = (TareaDto) dto;
		tareaDto.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		crearTarea(tareaDto);
	}

	@Override //Imputar reversión de cobro sistema externo
	public void crearTareaImputacionReversionCobroSistemaExterno(DTO dto) throws NegocioExcepcion {
		TareaDto tareaDto = (TareaDto) dto;
		tareaDto.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		crearTarea(tareaDto);
	}

	@Override
	public void crearTareaReversionCompensacionPendiente(DTO dto) throws NegocioExcepcion {
		TareaDto tareaDto = (TareaDto) dto;
		tareaDto.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		crearTarea(tareaDto);
	}
	
	@Override
	public void crearTareaResultadoSimulacion(DTO dto) throws NegocioExcepcion {
		TareaDto tareaDto = (TareaDto) dto;
		tareaDto.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		crearTarea(tareaDto);
	}
	
	@Override
	@Deprecated
	/**
	 * No utilizar este metodo para finalizar una tarea. Utilizar el metodo 
	 * finalizarTareaCorrecto(TipoTareaEnum tipoTarea, Integer idWorkflow, Date fechaEjecucion, String usuarioEjecucion, Mail mailTarea).
	 */
	public void finalizarTarea(TipoTareaEnum tipoTarea, Integer idWorkflow, Date fechaEjecucion, String usuarioEjecucion, Mail mailTarea) throws NegocioExcepcion {
		try {
			
			ShvWfTarea tarea = tareaDao.buscarTareaPendiente(tipoTarea, idWorkflow);
			if (tarea!=null) {
				tarea.setFechaEjecucion(fechaEjecucion);
				tarea.setUsuarioEjecucion(usuarioEjecucion);
				tarea.setEstado(TipoTareaEstadoEnum.FINALIZADA);
				this.modificarTarea(tarea);
				
				if (mailTarea != null) {
					String mailPara = mailServicio.obtenerMailUsuario(usuarioEjecucion);
					if(!Validaciones.isNullOrEmpty(mailPara)){
						mailTarea.setDestinatarioPara(mailPara);
					}
					mailServicio.sendMail(mailTarea);
				}
			} else {
				Traza.auditoria(getClass(), "No se ha encontrado la tarea para el workflow: " + idWorkflow);
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	@Override
	public void finalizarTareaCorrecto(TipoTareaEnum tipoTarea, Integer idWorkflow, Date fechaEjecucion, String usuarioEjecucion, Mail mailTarea) throws NegocioExcepcion {
		finalizarTareaCorrecto(tipoTarea,null,null,idWorkflow,fechaEjecucion,usuarioEjecucion,mailTarea, null, null);
	}
	
	/**
	 * Metodo correcto que finaliza y setea los usuarios de forma correcta.
	 */
	@Override
	public void finalizarTareaCorrecto(TipoTareaEnum tipoTarea, SociedadEnum sociedad, SistemaEnum sistema, Integer idWorkflow, Date fechaEjecucion, String usuarioEjecucion, Mail mailTarea, TipoAccionEnum accion, String observaciones) throws NegocioExcepcion {
		try {
			
			ShvWfTarea tarea = tareaDao.buscarTareaPendiente(tipoTarea,sociedad,sistema,idWorkflow);
			if (tarea!=null) {
				tarea.setFechaEjecucion(fechaEjecucion);
				tarea.setUsuarioEjecucion(usuarioEjecucion);
				tarea.setNombreUsuarioEjecucion(ldapServicio.buscarUsuarioPorUidEnMemoria(usuarioEjecucion).getNombreCompleto());
				if(!Validaciones.isObjectNull(accion)){
					tarea.setTipoAccion(accion);
				}
				tarea.setObservaciones(observaciones);
				tarea.setEstado(TipoTareaEstadoEnum.FINALIZADA);
				this.modificarTarea(tarea);
				if (mailTarea != null) {
					String mailPara = mailServicio.obtenerMailUsuario(tarea.getUsuarioCreacion());
					if(!Validaciones.isNullOrEmpty(mailPara)){
						mailTarea.setDestinatarioPara(mailPara);
					}
					mailServicio.sendMail(mailTarea);
				}
			} else {
				Traza.auditoria(getClass(), "No se ha encontrado la tarea para el workflow: " + idWorkflow);
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public void finalizarTarea(TipoTareaEnum tipoTarea, Integer idWorkflow, String usuarioEjecucion, Mail mail) throws NegocioExcepcion {
		try {
			ShvWfTarea tarea = tareaDao.buscarTareaPendiente(tipoTarea, idWorkflow);
			if (!Validaciones.isObjectNull(tarea)) {
				tarea.setFechaEjecucion(new Date());
				tarea.setUsuarioEjecucion(usuarioEjecucion);
				UsuarioLdapDto usuario = ldapServicio.buscarUsuarioPorUid(usuarioEjecucion);
				if(!Validaciones.isObjectNull(usuario)){
					tarea.setNombreUsuarioEjecucion(usuario.getNombreCompleto());
				}
				tarea.setNombreUsuarioEjecucion(usuario.getNombreCompleto());
				tarea.setEstado(TipoTareaEstadoEnum.FINALIZADA);
				this.modificarTarea(tarea);
				if (mail != null 
						&& !Validaciones.isNullOrEmpty(mail.getAsunto()) 
						&& !Validaciones.isObjectNull(mail.getCuerpo())
						&& !Validaciones.isNullOrEmpty(mail.getCuerpo().toString())
						&& tarea.getUsuarioAsignacion() != null 
						&& tarea.getUsuarioAsignacion().toUpperCase().compareTo(usuarioEjecucion.toUpperCase()) != 0) {
					String mailPara = mailServicio.obtenerMailUsuario(tarea.getUsuarioAsignacion());
					if(!Validaciones.isNullOrEmpty(mailPara)){
						mail.setDestinatarioPara(mailPara);
					}
					mailServicio.sendMail(mail);
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
	}

	@Override
	public Collection<DTO> listarTareasPendientes(VistaSoporteTareasPendientesFiltro filtro) throws NegocioExcepcion{

		Collection<DTO> nuevoResultado = new ArrayList<DTO>();

		List<VistaSoporteResultadoBusquedaTareaPendiente> resultado = new ArrayList<VistaSoporteResultadoBusquedaTareaPendiente>();
		resultado = vistaSoporteServicio.consultarTareasPendientes(filtro);
		
		List<String> estadosMarcados = Arrays.asList(
			new String[] {
				Estado.COB_RECHAZADO.descripcion(),
				Estado.COB_EN_EDICION.descripcion(),
			}
		);
		List<TipoTareaEnum> tareaConEstadoMarca = Arrays.asList(
			new TipoTareaEnum[] {
				TipoTareaEnum.COB_REV_RECH,
				TipoTareaEnum.COB_REV_DEB_IMP
			}
		);
		List<TipoTareaEnum> tareaSimulacionConEstadoMarca = Arrays.asList(
			new TipoTareaEnum[] {
				TipoTareaEnum.COB_RES_SIM_ERR,
				TipoTareaEnum.COB_RES_SIM_OK
			}
		);
		for (VistaSoporteResultadoBusquedaTareaPendiente vistaSoporteResultadoBusquedaTareaPendiente : resultado) {
			TareaDto tareaDto = (TareaDto)resultadoBusquedaTareaPendienteMapeador.map(vistaSoporteResultadoBusquedaTareaPendiente);
			if (tareaConEstadoMarca.contains(tareaDto.getTipoTarea())) {
				if (estadosMarcados.contains(tareaDto.getEstadoPendienteDescripcion())) {
					List<MarcaEnum> marcas = cobroOnlineServicio.obtenerUltimasMarcas(tareaDto.getIdCobro(), null, true);
					for (MarcaEnum marca: marcas) {
						tareaDto.setMarcaDescripcion(marca.descripcion());
					}
				} 
			} else if (tareaSimulacionConEstadoMarca.contains(tareaDto.getTipoTarea())) {
				List<MarcaEnum> marcas = cobroOnlineServicio.obtenerUltimasMarcas(tareaDto.getIdCobro(), null, false);
				String marcaSimulacion = "";
				String marcaRechazo = "";
				for (MarcaEnum marca: marcas) {
					if (MarcaEnum.RECHAZADO_POR_REFERENTE.equals(marca)) {
						marcaRechazo = marca.descripcion();
					} else if (MarcaEnum.SIMULACION_BATCH_FINALIZADA_CON_EXITO.equals(marca)) {
						marcaSimulacion = marca.descripcion();
					} else if (MarcaEnum.SIMULACION_BATCH_FINALIZADA_CON_ERROR.equals(marca)) {
						marcaSimulacion = marca.descripcion();
					}
				}
				tareaDto.setMarcaDescripcion(Utilidad.concatena(marcaSimulacion, marcaRechazo, " - "));
			}
			this.armarIconosDeMailyChat(tareaDto);
			this.definirAsignacion(tareaDto, filtro.getUsuarioLogeado());
			this.definirPermitirTomarTarea(tareaDto, filtro.getUsuarioLogeado());
			this.definirPermitirLiberarTarea(tareaDto, filtro.getUsuarioLogeado());
			nuevoResultado.add(tareaDto);
		}
		
		return nuevoResultado;
	}
	/**
	 * Este método se encarga de verificar si el usuario actual, dada la tarea actual, puede presentar el botón "tomar tarea" en la bandeja de entrada
	 * Actualmente esta funcionalidad se encuentra para los perfiles 
	 *   - Referente de Caja
	 *   - Referente de Operaciones Externas
	 *   
	 * @param tarea
	 * @param usuario
	 * @throws NegocioExcepcion
	 */
	private void definirPermitirTomarTarea(TareaDto tarea, UsuarioSesion usuario) throws NegocioExcepcion {
		
		tarea.setPermitirTomarTarea(false);
		if (((usuario.getEsReferenteCaja() && (tarea.getEsTareaDescobroConfirmarAplicacionManual() || tarea.getEsTareaCobroConfirmarAplicacionManual()))
				|| (usuario.getEsReferenteOperacionesExternas() && (tarea.getEsTareaDescobroConfirmarAplicacionManual() || tarea.getEsTareaCobroConfirmarAplicacionManual())))
				&& !usuario.getIdUsuario().equals(tarea.getUsuarioAsignacion())) {

			tarea.setPermitirTomarTarea(true);
		}
	}
	
	/**
 	 * Este método se encarga de verificar si el usuario actual, dada la tarea actual, puede presentar el botón "liberar tarea" en la bandeja de entrada
	 * Actualmente esta funcionalidad se encuentra para los perfiles 
	 *   - Referente de Caja
	 *   - Referente de Operaciones Externas
	 *   
	 * @param tarea
	 * @param usuario
	 * @throws NegocioExcepcion
	 */
	private void definirPermitirLiberarTarea(TareaDto tarea, UsuarioSesion usuario) throws NegocioExcepcion {
		
		tarea.setPermitirLiberarTarea(false);
		if (((usuario.getEsReferenteCaja() && (tarea.getEsTareaDescobroConfirmarAplicacionManual() || tarea.getEsTareaCobroConfirmarAplicacionManual()))
				|| (usuario.getEsReferenteOperacionesExternas() && (tarea.getEsTareaDescobroConfirmarAplicacionManual() || tarea.getEsTareaCobroConfirmarAplicacionManual())))
				&& usuario.getIdUsuario().equals(tarea.getUsuarioAsignacion())) {
			
			tarea.setPermitirLiberarTarea(true);
			
		}
	}
	
	/**
	 * Este método se encarga de tomar la tarea de la bandeja de entrada y guardar como usuario asignacion al usuario logueado que tomo la tarea.
	 */
	public void tomarTarea(TipoTareaEnum tipoTarea, Integer idWorkflow,SociedadEnum sociedad, SistemaEnum sistema, String loginUser) throws NegocioExcepcion {
		try {
			Long timeStamp = new Date().getTime();
			ShvWfTarea tarea = tareaDao.buscarTareaPendiente(tipoTarea, sociedad, sistema, idWorkflow);
			
//			Se verifica la concurrencia
			
			verificarConcurrencia(tarea, timeStamp);
			
			if ( !Validaciones.isObjectNull(tarea)) {
					Date fechaTomada = new Date();
					tarea.setFechaUltimaModificacion(fechaTomada);
					tarea.setUsuarioAsignacion(loginUser);
					tarea = tareaDao.actualizarTarea(tarea);
		
			} else {
				Traza.auditoria(getClass(), "No se ha encontrado la tarea para el workflow: " + idWorkflow );
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	/**
	 * Este método se encarga de liberar la tarea de la bandeja de entrada
	 */
	public void liberarTarea(TipoTareaEnum tipoTarea, Integer idWorkflow, SistemaEnum sistema, SociedadEnum sociedad, String usuarioAsignado) throws NegocioExcepcion {
		try {
			ShvWfTarea tarea = tareaDao.buscarTareaPendiente(tipoTarea, sociedad, sistema, idWorkflow);
			if (!Validaciones.isObjectNull(tarea)
					&& !Validaciones.isObjectNull(tarea.getUsuarioAsignacion()) 
					&& tarea.getUsuarioAsignacion().toUpperCase().equals(usuarioAsignado.toUpperCase())) {			
				
				tarea.setUsuarioAsignacion(null);
				tarea.setFechaUltimaModificacion(null);
				tarea = tareaDao.actualizarTarea(tarea);
			
			} else {
				Traza.auditoria(getClass(), "No se ha encontrado la tarea para el workflow: " + idWorkflow + " y el usuario: " + usuarioAsignado);
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 *  Este método se encarga de verificar la concurrencia para que una tarea no pueda ser tomada por dos usuarios a la vez. 
	 *  Además se utiliza la fecha que se encuentra guardada en la tabla ShvWfTarea ya que no es necesario agarrar la misma desde pantalla.
	 * 
	 */
	public void  verificarConcurrencia(ShvWfTarea tarea, Long timeStamp) throws NegocioExcepcion {

			//Verifica si la fecha ultima modificación es nula
			if (!Validaciones.isObjectNull(tarea.getFechaUltimaModificacion())){
			//Compara el horario si la tarea fue tomada
			if (timeStamp.longValue() >=  tarea.getFechaUltimaModificacion().getTime()) {
				throw new ConcurrenciaExcepcion(tarea.getId());
			}
			}
		
	}
	private TareaDto setearMarcaDescripcionDescobro(TareaDto tareaDto) throws NegocioExcepcion {
		
		List<String> estadosMarcadosDescobro = Arrays.asList(
			new String[] {
				Estado.DES_EN_EDICION.descripcion(),
			}
		);
		
		List<TipoTareaEnum> tareaSimulacionConEstadoMarcaDescobro = Arrays.asList(
			new TipoTareaEnum[] {
				TipoTareaEnum.DES_RES_SIM_ERR,
				TipoTareaEnum.DES_RES_SIM_OK
			}
		);
		
		if (estadosMarcadosDescobro.contains(tareaDto.getEstadoPendienteDescripcion())
				&& tareaSimulacionConEstadoMarcaDescobro.contains(tareaDto.getTipoTarea())) {
			
			//obtengo todas las marcas
			List<MarcaEnum> marcas = descobroServicio.obtenerUltimasMarcas(tareaDto.getIdDescobro(), null, true);
			
			if(Validaciones.isCollectionNotEmpty(marcas)){		
				for (MarcaEnum marca: marcas) {
					tareaDto.setMarcaDescripcion(marca.descripcion());
				}
			}
		}
		
		return tareaDto;
	}
	
	
	/**
	 * Armo los iconos de mail y chat segun el perfil ingresado
	 * @param dto
	 * @param perfil
	 * @throws NegocioExcepcion
	 */
	private void armarIconosDeMailyChat(TareaDto dto) throws NegocioExcepcion {
		StringBuffer supervisoresMail = new StringBuffer("");
		StringBuffer supervisoresChat = new StringBuffer("");
		StringBuffer usuarioCreacionMail = new StringBuffer("");
		StringBuffer usuarioCreacionChat = new StringBuffer("");
		
		if (!Validaciones.isNullOrEmpty(dto.getUsuarioCreacion())) {
		UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(dto.getUsuarioCreacion().trim());
		if(usuarioLdap!=null){
			usuarioCreacionMail.append(usuarioLdap.getMail()+";");  
			usuarioCreacionChat.append("<sip:"+usuarioLdap.getMail()+">"); 

			dto.setIdUsuarioCreacion(dto.getUsuarioCreacion().trim());
			dto.setNombreUsuarioCreacion(usuarioLdap.getNombreCompleto());
		}
	}
		
		if (!Validaciones.isNullOrEmpty(dto.getUsuarioAsignacion())) {
			UsuarioLdapDto asignado = ldapServicio.buscarUsuarioPorUidEnMemoria(dto.getUsuarioAsignacion().trim());
			if(asignado!=null){
				supervisoresMail.append(asignado.getMail()+";");  
				supervisoresChat.append("<sip:"+asignado.getMail()+">"); 

				dto.setIdUsuarioAsignado(dto.getUsuarioAsignacion().trim());
				dto.setNombreUsuarioAsignado(asignado.getNombreCompleto());
			}
		} else {
			Collection<UsuarioLdapDto> usuarioLdapLista = ldapServicio.buscarUsuariosPorPerfilEnMemoria(dto.getPerfilAsignacion());
			for (UsuarioLdapDto usuario: usuarioLdapLista) {			
				if (!Validaciones.isNullOrEmpty(usuario.getMail())) {
					supervisoresMail.append(usuario.getMail()+";");  
					supervisoresChat.append("<sip:"+usuario.getMail()+">");  
				}			
			}
			
			if(usuarioLdapLista.size()>0){			
				dto.setIdUsuarioAsignado(((usuarioLdapLista.size()==1)?usuarioLdapLista.iterator().next().getTuid():""));
				dto.setNombreUsuarioAsignado(((usuarioLdapLista.size()==1)?usuarioLdapLista.iterator().next().getNombreCompleto():dto.getGrupoAsignado()));	
			}
		}

		dto.setMailUsuarioAsignado(supervisoresMail.toString());
		dto.setChatUsuarioAsignado(supervisoresChat.toString());
		dto.setMailUsuarioCreacion(usuarioCreacionMail.toString());
		dto.setChatUsuarioCreacion(usuarioCreacionChat.toString());
	}
	
	private void definirAsignacion(TareaDto dto, UsuarioSesion usuarioSesion) throws NegocioExcepcion {
		dto.setDelegado(1);
		if (!Validaciones.isNullOrEmpty(dto.getUsuarioAsignacion())
				&& usuarioSesion.getIdUsuario().toUpperCase().equals(dto.getUsuarioAsignacion().toUpperCase())) {
			dto.setDelegado(0);
		} else {
			for (String perfil : usuarioSesion.getPerfiles()) {
				if(!Validaciones.isNullOrEmpty(dto.getPerfilAsignacion())){
					if (perfil.toUpperCase().equals(dto.getPerfilAsignacion().toUpperCase())) {
						dto.setDelegado(0);
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Método que crea tarea y retorna el Numero de tarea que fue creado
	 */
	private Long crearTarea(DTO dto) throws NegocioExcepcion {
		TareaDto tareaDto = (TareaDto) dto;
		ShvWfTarea tarea = null;

		try {
			Traza.auditoria(TareaServicioImpl.class, " tipo:" + tareaDto.getTipoTarea() + " wf:" + tareaDto.getIdWorkflow()
					+" sociedad: " +tareaDto.getSociedad() + " sistema: " + tareaDto.getSistema());
			ShvWfTarea tareaABuscar = tareaDao.buscarTareaPendiente(tareaDto.getTipoTarea(),tareaDto.getSociedad(),tareaDto.getSistema(), tareaDto.getIdWorkflow());
			if (tareaABuscar != null) {
				tarea = (ShvWfTarea) defaultMapeador.map(tareaDto, tareaABuscar);
				tarea = tareaDao.actualizarTarea(tarea);
			
			} else {
			
				tarea = (ShvWfTarea) defaultMapeador.map(tareaDto, null);
				tarea = tareaDao.insertarTarea(tarea);
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}

		if (tareaDto.getEnviarMail()) {
			try {
				String perfilAsignacion = tareaDto.getPerfilAsignacion();

				if (
					!TipoTareaEnum.DES_CONFIRMA_APL_MAN.equals(tareaDto.getTipoTarea()) &&
					!Validaciones.isNullOrEmpty(tareaDto.getEmpresa()) && !Validaciones.isNullOrEmpty(tareaDto.getSegmento())
				) {
					perfilAsignacion += "_" + tareaDto.getEmpresa() + "_" + tareaDto.getSegmento(); 
				}

				Collection<UsuarioLdapDto> usuariosLdap = ldapServicio.buscarUsuariosPorPerfilEnMemoria(perfilAsignacion);
				StringBuffer mails = new StringBuffer("");
				for (UsuarioLdapDto usuario: usuariosLdap) {
					if (!Validaciones.isNullOrEmpty(usuario.getMail())) {
						mails.append(usuario.getMail()+";");  
					}
				}

				if (!Validaciones.isNullOrEmpty(mails.toString())) {
					String mailsMasivo = mails.toString().substring(0, mails.length()-1);
					String subject = Utilidad.reemplazarMensajes(Mensajes.NUEVA_TAREA, tareaDto.getAsuntoMail());
					StringBuffer content = new StringBuffer(tareaDto.getCuerpoMail());	

					Mail mail = new Mail(mailsMasivo, subject, content);
					if (!Validaciones.isObjectNull(tareaDto.getAdjuntosMail()) && !tareaDto.getAdjuntosMail().isEmpty()) {
						Traza.advertencia(getClass(), "Cantidad de adjuntos " + tareaDto.getAdjuntosMail().size());	
						mail.setAdjuntos(tareaDto.getAdjuntosMail());
					}
					mailServicio.sendMail(mail);
				}
				

			} catch (LdapExcepcion e) {
				Traza.advertencia(getClass(), "Se ha producido un error del servicio LDAP y no se pudo enviar el correo" , e);
			}
		}
		
		return Long.valueOf(tarea.getIdTarea());
	}

	/**
	 * Método que modifica tarea 
	 */
	private void modificarTarea(ShvWfTarea tarea) throws NegocioExcepcion {
		try {
			tareaDao.actualizarTarea(tarea);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * Método que busca tarea y retorna el Numero de tarea que fue creado
	 */
	public DTO buscarTarea(Long id) throws NegocioExcepcion {
		
		try {
			ShvWfTarea tarea = tareaDao.buscarTarea(id);
			TareaDto dto = (TareaDto) defaultMapeador.map(tarea);
			
			return dto;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public List<ShvWfTarea> buscarTareasPendientes(Integer idWorkflow) throws NegocioExcepcion {
		
		try {
			List<ShvWfTarea> listaTareas = tareaDao.buscarTareasPendientes(idWorkflow);
			
			return listaTareas;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	

	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/

	/**
	 * @return the tareaDao
	 */
	public ITareaDao getTareaDao() {
		return tareaDao;
	}

	/**
	 * @param tareaDao the tareaDao to set
	 */
	public void setTareaDao(ITareaDao tareaDao) {
		this.tareaDao = tareaDao;
	}

	/**
	 * @return the mailServicio
	 */
	public MailServicio getMailServicio() {
		return mailServicio;
	}

	/**
	 * @param mailServicio the mailServicio to set
	 */
	public void setMailServicio(MailServicio mailServicio) {
		this.mailServicio = mailServicio;
	}

	/**
	 * @return the ldapServicio
	 */
	public ILdapServicio getLdapServicio() {
		return ldapServicio;
	}

	/**
	 * @param ldapServicio the ldapServicio to set
	 */
	public void setLdapServicio(ILdapServicio ldapServicio) {
		this.ldapServicio = ldapServicio;
	}

	/**
	 * @return the valorDao
	 */
	public IValorDao getValorDao() {
		return valorDao;
	}

	/**
	 * @param valorDao the valorDao to set
	 */
	public void setValorDao(IValorDao valorDao) {
		this.valorDao = valorDao;
	}

	/**
	 * @return the talonarioDao
	 */
	public ITalonarioDao getTalonarioDao() {
		return talonarioDao;
	}

	/**
	 * @param talonarioDao the talonarioDao to set
	 */
	public void setTalonarioDao(ITalonarioDao talonarioDao) {
		this.talonarioDao = talonarioDao;
	}

	/**
	 * @return the workflowDao
	 */
	public IWorkflowDao getWorkflowDao() {
		return workflowDao;
	}

	/**
	 * @param workflowDao the workflowDao to set
	 */
	public void setWorkflowDao(IWorkflowDao workflowDao) {
		this.workflowDao = workflowDao;
	}

	/**
	 * @return the vistaSoporteServicio
	 */
	public IVistaSoporteServicio getVistaSoporteServicio() {
		return vistaSoporteServicio;
	}

	/**
	 * @param vistaSoporteServicio the vistaSoporteServicio to set
	 */
	public void setVistaSoporteServicio(IVistaSoporteServicio vistaSoporteServicio) {
		this.vistaSoporteServicio = vistaSoporteServicio;
	}

	/**
	 * @return the resultadoBusquedaMapeador
	 */
	public MapeadorResultadoBusqueda getResultadoBusquedaTareaPendienteMapeador() {
		return resultadoBusquedaTareaPendienteMapeador;
	}

	/**
	 * @param resultadoBusquedaMapeador the resultadoBusquedaMapeador to set
	 */
	public void setResultadoBusquedaTareaPendienteMapeador(
			MapeadorResultadoBusqueda resultadoBusquedaMapeador) {
		this.resultadoBusquedaTareaPendienteMapeador = resultadoBusquedaMapeador;
	}

	/**
	 * @return the cobroOnlineServicio
	 */
	public ICobroOnlineServicio getCobroOnlineServicio() {
		return cobroOnlineServicio;
	}

	/**
	 * @param cobroOnlineServicio the cobroOnlineServicio to set
	 */
	public void setCobroOnlineServicio(ICobroOnlineServicio cobroOnlineServicio) {
		this.cobroOnlineServicio = cobroOnlineServicio;
	}
}
