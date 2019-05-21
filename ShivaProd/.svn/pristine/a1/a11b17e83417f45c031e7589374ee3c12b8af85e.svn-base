package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaGestionaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ConcurrenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.LdapExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.mapeadores.MapeadorResultadoBusqueda;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.dto.DepositoDto;
import ar.com.telecom.shiva.negocio.dto.InterdepositoDto;
import ar.com.telecom.shiva.negocio.dto.RegistroAVCDto;
import ar.com.telecom.shiva.negocio.dto.TransferenciaDto;
import ar.com.telecom.shiva.negocio.mapeos.RegistroAVCDepositoMapeador;
import ar.com.telecom.shiva.negocio.mapeos.RegistroAVCInterdepositoMapeador;
import ar.com.telecom.shiva.negocio.mapeos.RegistroAVCTransferenciaMapeador;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IRegistroAVCServicio;
import ar.com.telecom.shiva.negocio.servicios.ITareaServicio;
import ar.com.telecom.shiva.negocio.servicios.ITeamComercialServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorPorReversionServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;
import ar.com.telecom.shiva.negocio.servicios.IVistaSoporteServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowRegistrosAVC;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteRegistrosAvcSinConciliar;
import ar.com.telecom.shiva.persistencia.dao.IBancoDao;
import ar.com.telecom.shiva.persistencia.dao.IDocumentoAdjuntoDao;
import ar.com.telecom.shiva.persistencia.dao.IOrganismoDao;
import ar.com.telecom.shiva.persistencia.dao.IRegistroAVCDao;
import ar.com.telecom.shiva.persistencia.dao.ITareaDao;
import ar.com.telecom.shiva.persistencia.dao.IValorDao;
import ar.com.telecom.shiva.persistencia.dao.impl.GenericoDaoImpl;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAdjuntoPk;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvc;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvcCheque;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvcChequeDiferido;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvcEfectivo;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvcInterdeposito;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvcTransferencia;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvcValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvcValorPK;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBanco;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamCuit;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.TareaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.TeamComercialDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteRegistrosAvcSinConciliarFiltro;

public class RegistroAVCServicioImpl extends Servicio implements IRegistroAVCServicio {

	@Autowired private IRegistroAVCDao registroAVCDao;
	@Autowired private RegistroAVCDepositoMapeador depositoMapeador;
	@Autowired private RegistroAVCInterdepositoMapeador interdepositoMapeador;
	@Autowired private RegistroAVCTransferenciaMapeador transferenciaMapeador;
	@Autowired private IWorkflowRegistrosAVC workflowRegistrosAVC;
	@Autowired private IDocumentoAdjuntoDao documentoAdjuntoDao;
	@Autowired private IValorServicio valorServicio;
	@Autowired private IValorPorReversionServicio valorPorReversionServicio;
	@Autowired private IBancoDao bancoDao;
	@Autowired private IValorDao valorDao;
	@Autowired private IOrganismoDao organismoDao;
	@Autowired private ITareaDao tareaDao;
	@Autowired ILdapServicio ldapServicio;
	@Autowired MailServicio mailServicio;
	@Autowired ITareaServicio tareaServicio;
	@Autowired private IParametroServicio parametroServicio;
	@Autowired private IVistaSoporteServicio vistaSoporteServicio;
	@Autowired private ITeamComercialServicio teamComercialServicio;
	@Autowired private MapeadorResultadoBusqueda resultadoBusquedaReversionSinConciliarMapeador;
	@Autowired private MapeadorResultadoBusqueda resultadoBusquedaDepositoSinConciliarMapeador;
	@Autowired private MapeadorResultadoBusqueda resultadoBusquedaInterdepositoSinConciliarMapeador;
	@Autowired private MapeadorResultadoBusqueda resultadoBusquedaTransferenciaSinConciliarMapeador;
	@Autowired private GenericoDaoImpl genericoDao;
	

	public List<RegistroAVCDto> listarRegistrosDepositoPendientesConciliar() throws NegocioExcepcion {
		List<RegistroAVCDto> listaRegistrosDto = new ArrayList<RegistroAVCDto>();
		try {
			Collection<ShvAvcRegistroAvc> listaBoletaModelo = registroAVCDao.listarRegistrosDepositoPendientesConciliar();
			for (ShvAvcRegistroAvc shvRegistrosAVC : listaBoletaModelo) {
				if(!Validaciones.isObjectNull(shvRegistrosAVC.getDeposito())){	
					listaRegistrosDto.add((RegistroAVCDto) depositoMapeador.map(shvRegistrosAVC));
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		return listaRegistrosDto;
	}
	
	public List<RegistroAVCDto> listarRegistrosInterdepositoYTransferenciasPendientesConciliar() throws NegocioExcepcion {
		List<RegistroAVCDto> listaRegistrosDto = new ArrayList<RegistroAVCDto>();
		try {
			Collection<ShvAvcRegistroAvc> listaBoletaModelo = registroAVCDao.listarRegistrosInterdepositoYTransferenciaPendientesConciliar();
			for (ShvAvcRegistroAvc shvRegistrosAVC : listaBoletaModelo) {
				if(!Validaciones.isObjectNull(shvRegistrosAVC.getInterdeposito())){	
					listaRegistrosDto.add((RegistroAVCDto) interdepositoMapeador.map(shvRegistrosAVC));
				}else{
					if(!Validaciones.isObjectNull(shvRegistrosAVC.getTransferencia())){	
						listaRegistrosDto.add((RegistroAVCDto) transferenciaMapeador.map(shvRegistrosAVC));
					}
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		return listaRegistrosDto;
	}
	
	/**
	 * Lista todos los registros avc en estados 'Pendientes de conciliar' o 
	 * 'Pendiente de Confirmar Alta de Valor' o 'Alta de Valor Rechazada'.
	 * @return
	 * @throws NegocioExcepcion
	 */
	@SuppressWarnings("unchecked")
	public List<RegistroAVCDto> listarRegistrosAVCSinConciliar(VistaSoporteRegistrosAvcSinConciliarFiltro filtro, boolean conReversion) throws NegocioExcepcion {
		List<RegistroAVCDto> listaRegistrosDto = new ArrayList<RegistroAVCDto>();
		
		try {
			/**
			 * @author fabio.giaquinta.ruiz
			 */
			List<VistaSoporteRegistrosAvcSinConciliar> listaRegistrosAvc = vistaSoporteServicio.listarRegistrosAVCSinConciliar(filtro);
			for (VistaSoporteRegistrosAvcSinConciliar registro : listaRegistrosAvc){

				if(registro != null){
					switch(registro.getTipoDto()){
					case "REVERSION":
						if(conReversion){
							listaRegistrosDto.add((RegistroAVCDto) resultadoBusquedaReversionSinConciliarMapeador.map(registro));
						}
						break;
					case "DEPOSITO":
						listaRegistrosDto.add((RegistroAVCDto) resultadoBusquedaDepositoSinConciliarMapeador.map(registro));
						break;
					case "INTERDEPOSITO":
						listaRegistrosDto.add((RegistroAVCDto) resultadoBusquedaInterdepositoSinConciliarMapeador.map(registro));
						break;
					case "TRANSFERENCIA":
						listaRegistrosDto.add((RegistroAVCDto) resultadoBusquedaTransferenciaSinConciliarMapeador.map(registro));
						break;
					default:	
						break;
					}
				}
			}

		} catch (NegocioExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		try {
			listaRegistrosDto = (List<RegistroAVCDto>) Utilidad.guionesNull(listaRegistrosDto);
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		return listaRegistrosDto;
	}
	
	/**
	 * Pasa el registro avc a pendiente de anulacion y crea una tarea en la bandeja de entrada.
	 * Ademas envia el mail avisando.
	 */
	public void anularRegistroAVCSinConciliar(String idRegistro, String observaciones, UsuarioSesion userSesion) throws NegocioExcepcion
	{
		try {
			RegistroAVCDto registroAvc = buscarRegistroAVC(idRegistro);
			registroAvc.setUsuarioModificacion(userSesion.getIdUsuario());
			
			ShvAvcRegistroAvc registroModelo = registroAVCDao.buscarRegistroAVC(registroAvc.getIdRegistro().toString());
			registroModelo.setObservacionAnulacion(observaciones);
			registroModelo.setObservaciones(((registroModelo.getObservaciones() != null) ? registroModelo.getObservaciones().trim() : "") + Constantes.RETORNO_WIN + 
					observaciones);
			ShvWfWorkflow wf = registroModelo.getWorkFlow();
			
			//Verifico concurrencia
			verificarConcurrencia(registroAvc.getIdRegistro(), Long.valueOf(registroAvc.getTimeStamp()));
			
			/**
			 * @author u562163, IM de prod. 
			 */
			//Cambiar el estado del registro a Pendiente de Anulación
			ShvWfWorkflow workflowActualizado = null;
			if(Estado.AVC_PENDIENTE.equals(wf.getEstado())){
				workflowActualizado = workflowRegistrosAVC.solicitarRegistroAnulacion(wf, " ", userSesion.getIdUsuario());
			} else {
				if(Estado.AVC_ALTA_VALOR_RECHAZADA.equals(wf.getEstado())){
					workflowActualizado = workflowRegistrosAVC.solicitarAnulacionAltaValorRechazada(wf, " ", userSesion.getIdUsuario());
				}
			}
			
			if(!Validaciones.isObjectNull(workflowActualizado)){
				registroModelo.setWorkFlow(workflowActualizado);
				
				registroAVCDao.modificar(registroModelo);
				
				//crear tarea en la bandeja de entrada y enviar mail.
				TareaDto tarea = new TareaDto();
				tarea.setIdRegistroAVC(registroModelo.getIdRegistroAvc());
				tarea.setIdWorkflow(registroModelo.getWorkFlow().getIdWorkflow());
				tarea.setTipoTarea(TipoTareaEnum.CONF_ANUL_R_AVC);
				tarea.setFechaCreacion(new Date());
				tarea.setUsuarioCreacion(userSesion.getIdUsuario());
				tarea.setPerfilAsignacion(TipoPerfilEnum.SUPERVISOR_ADMIN_VALOR.descripcion());
				tarea.setGestionaSobre(getTipoTareaGestionaPorIdTipoValor(registroModelo.getTipoValor().getIdTipoValor()));
				tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
				tarea.setAsuntoMail(registroModelo.getAcuerdo().getBancoCuenta().getBanco().getEmpresa().getDescripcion() + Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.mail.confirmar.alta.valor"));
				
				//String cuerpoMail = getCuerpoMailParaAnulacionRegistroAvc(registroModelo).toString();
				//cuerpoMail += "<br><br>" + observaciones;
				//tarea.setCuerpoMail(cuerpoMail);
				tarea.setEnviarMail(false);
				
				tareaServicio.crearTareaRegistroAVC(tarea);
			} else {
				Traza.error(getClass(), " No se puede anular el registro AVC id: "+idRegistro
						+" en estado: "+wf.getEstado().descripcion()+" por no estar en estado Pendiente o Alta de Valor Rechazada ");
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * Anulación masiva de registros AVC sin conciliar
	 * @param idsRegistros ids de los registros a anular separados por coma ","
	 * @param observacion observación del usuario
	 * @param usuario usuario que lleva a cabo la acción 
	 * @throws NegocioExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void anularRegistrosAVCSinConciliar(String idsRegistros, String observacion, String usuario) throws NegocioExcepcion {
		try {
			RegistroAVCDto registroAVCDto = null;
			ShvAvcRegistroAvc registroAVC = null;
			Boolean enviarMail = true;
			Set<TareaDto> tareas = new HashSet<TareaDto>();
			HashMap<String, List<String>> cuerpoMailMap = new HashMap<String, List<String>>();
			for (String idRegistro : idsRegistros.split(",")) {
				registroAVCDto = buscarRegistroAVC(idRegistro);
				registroAVCDto.setUsuarioModificacion(usuario);
				registroAVC = registroAVCDao.buscarRegistroAVC(registroAVCDto.getIdRegistro().toString());
				registroAVC.setObservacionAnulacion(observacion);
				registroAVC.setObservaciones(((registroAVC.getObservaciones() != null) ? registroAVC.getObservaciones().trim() : "") + Constantes.RETORNO_WIN + observacion);
				ShvWfWorkflow wf = registroAVC.getWorkFlow();
				
				// Verificar concurrencia
				this.verificarConcurrencia(registroAVCDto.getIdRegistro(), Long.valueOf(registroAVCDto.getTimeStamp()));

				/**
				 * @author u562163, IM de prod. 
				 */	
				// Cambiar el estado del registro a Pendiente de Anulación
				ShvWfWorkflow workflowActualizado = null;
				if(Estado.AVC_PENDIENTE.equals(wf.getEstado())){
					workflowActualizado = workflowRegistrosAVC.solicitarRegistroAnulacion(wf, " ", usuario);
				} else {
					if(Estado.AVC_ALTA_VALOR_RECHAZADA.equals(wf.getEstado())){
						workflowActualizado = workflowRegistrosAVC.solicitarAnulacionAltaValorRechazada(wf, " ", usuario);
					} else {
						throw new NegocioExcepcion("ERROR - Se intenta anular el registro avc id: " +registroAVCDto.getIdRegistro()+
								" en estado: "+wf.getEstado()+", y no se encuentra ni en estado Pendiente ni Alta de valor rechazada.");
					}
				}
				
				if(!Validaciones.isObjectNull(workflowActualizado)){
					registroAVC.setWorkFlow(workflowActualizado);
					registroAVCDao.modificar(registroAVC);
					
					// Crear tarea
					tareas.add(this.crearTarea(registroAVC, observacion, usuario));
					prepararMail(cuerpoMailMap,registroAVC, observacion);
					
					// Preparo e-mail para el usuario asignado a la tarea que va a finalizar
					String subject = " - Anulación de registro AVC solicitada por otro usuario - " + mailServicio.armarAsuntoValor(registroAVC);
					subject = Utilidad.reemplazarMensajes(Mensajes.ASUNTO_ANULACION_REGISTRO_AVC_RECHAZADO, subject);
					StringBuffer body = new StringBuffer();
					body.append(mailServicio.armarLineaCuerpoValor(registroAVC));
					body.append("<br>");
					body.append("Usuario que solicitó la anulación del registro AVC: " 
							+ ldapServicio.buscarUsuarioPorUidEnMemoria(usuario).getNombreCompleto());
					
					Mail mail = new Mail(null, null, subject, body);
					// Fin preparación e-mail
					
					tareaServicio.finalizarTarea(TipoTareaEnum.REV_ANUL_R_AVC, registroAVC.getWorkFlow().getIdWorkflow(), usuario, mail);
				} else {
					enviarMail = false;
					Traza.error(getClass(), " No se puede anular el registro AVC id: "+idRegistro
							+" en estado: "+wf.getEstado().descripcion()+" por no estar en estado Pendiente o Alta de Valor Rechazada ");
				}
			}
			
			if(enviarMail){
				/**
				 * @author u573005, Sprint3, defecto 53
				 */			
				String asuntoIndividual = "Nueva tarea - " + Mensajes.ASUNTO_ANULAR_REGISTRO_AVC + " - ";
				if(!Validaciones.isObjectNull(registroAVC)){
					asuntoIndividual += registroAVC.getNumeroReferenciaDelValorFormateado();
				}
				
				mailServicio.enviarMailMasivo("Nuevas tareas - Anulaciones de registros AVC pendientes de autorizar|Los siguientes registros AVC requieren confirmación de anulación:|" + asuntoIndividual, cuerpoMailMap);
				
				for (TareaDto tarea : tareas) {
					tarea.setEnviarMail(false);
					tareaServicio.crearTareaRegistroAVC(tarea);
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	private void prepararMail(Map<String,List<String>> mapMail,ShvAvcRegistroAvc avcModelo, String observaciones){
		String key = "";
		StringBuffer cuerpo = new StringBuffer();
		String informacionAdicional = mailServicio.armarLineaCuerpoValor(avcModelo);
		cuerpo.append(informacionAdicional);
		if (!Validaciones.isNullOrEmpty(observaciones)){
			cuerpo.append("<br>" + Utilidad.reemplazarMensajes(Mensajes.CAMPO_OBSERVACIONES,observaciones));
		}
		try {
			String perfilAsignacion = TipoPerfilEnum.SUPERVISOR_ADMIN_VALOR.descripcion();
			Collection<UsuarioLdapDto> usuariosLdap = ldapServicio.buscarUsuariosPorPerfilEnMemoria(perfilAsignacion);
			StringBuffer usuarios = new StringBuffer("");
			for (UsuarioLdapDto usuario: usuariosLdap) {
				if (!Validaciones.isNullOrEmpty(usuario.getMail())) {
					usuarios.append(usuario.getMail()+";");  
				}
			}
			usuarios.setLength(Math.max(usuarios.length() - 1, 0));
			key = usuarios.toString();
			if (mapMail.containsKey(key)){
				mapMail.get(key).add(cuerpo.toString());
			} else {
				ArrayList<String> nuevaLista = new ArrayList<String>();
				nuevaLista.add(cuerpo.toString());
				mapMail.put(key, nuevaLista);
			}
		} catch (LdapExcepcion e) {
			Traza.advertencia(getClass(), "Se ha producido un error del servicio LDAP y no se pudo enviar el correo" , e);
		}
	}
	
	/**
	 * 
	 * @param registroAVC
	 * @param observacion
	 * @param usuario
	 * @return
	 */
	private TareaDto crearTarea(ShvAvcRegistroAvc registroAVC, String observacion, String usuario) {
		TareaDto tarea = new TareaDto();
		tarea.setIdRegistroAVC(registroAVC.getIdRegistroAvc());
		tarea.setIdWorkflow(registroAVC.getWorkFlow().getIdWorkflow());
		tarea.setTipoTarea(TipoTareaEnum.CONF_ANUL_R_AVC);
		tarea.setFechaCreacion(new Date());
		tarea.setUsuarioCreacion(usuario);
		tarea.setPerfilAsignacion(TipoPerfilEnum.SUPERVISOR_ADMIN_VALOR.descripcion());
		tarea.setGestionaSobre(getTipoTareaGestionaPorIdTipoValor(registroAVC.getTipoValor().getIdTipoValor()));
		tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		tarea.setAsuntoMail(registroAVC.getAcuerdo().getBancoCuenta().getBanco().getEmpresa().getDescripcion() 
				+ Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.mail.confirmar.alta.valor"));
		String cuerpoMail = getCuerpoMailParaAnulacionRegistroAvc(registroAVC).toString();
		cuerpoMail += "<br><br>" + observacion;
		tarea.setCuerpoMail(cuerpoMail);
		return tarea;
	}
	
	/**
	 * Retorna un tipo tarea Gestiona segun el tipo valor. Utilizado para 
	 * las tareas.
	 * @param idTipoValor
	 * @return
	 */
	public TipoTareaGestionaEnum getTipoTareaGestionaPorIdTipoValor(Long idTipoValor) {
		switch (TipoValorEnum.getEnumByIdTipoValor(idTipoValor)) {
	    case BOLETA_DEPOSITO_CHEQUE:
	    	return TipoTareaGestionaEnum.REGISTRO_AVC_DEP_CHQ;
	    case BOLETA_DEPOSITO_CHEQUE_DIFERIDO:
	    	return TipoTareaGestionaEnum.REGISTRO_AVC_DEP_CHQ_DIF;
	    case BOLETA_DEPOSITO_EFECTIVO:
	    	return TipoTareaGestionaEnum.REGISTRO_AVC_DEP_EFE;
	    case INTERDEPÓSITO:
	    	return TipoTareaGestionaEnum.REGISTRO_AVC_INTERDEPOSITO;
	    case TRANSFERENCIA:
	    	return TipoTareaGestionaEnum.REGISTRO_AVC_TRANSF;
	    case CHEQUE:
	    	return TipoTareaGestionaEnum.VALOR_CHEQUE;
	    case CHEQUE_DIFERIDO:
	    	return TipoTareaGestionaEnum.VALOR_CHEQUE_DIFERIDO;
	    case EFECTIVO:
	    	return TipoTareaGestionaEnum.VALOR_EFECTIVO;
		default:
	    	break;
		};
		return null;
	}
	
	/**
	 * Confirma la anulacion del registro avc. 
	 * Pasa el registro de Pendiente de anulacion a anulado.
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void confirmarAnulacionRegistroAvc(RegistroAVCDto registroDto) throws NegocioExcepcion {
		
		try {
			ShvAvcRegistroAvc registroModelo = registroAVCDao.buscarRegistroAVC(registroDto.getIdRegistro().toString());
			
			String datosModificados = "";
			
			if (!Validaciones.isNullOrEmpty(registroDto.getObservacionConfirmarAnulacion())) {
				registroModelo.setObservacionConfirmarAnulacion(registroDto.getObservacionConfirmarAnulacion());
				
				datosModificados = ("[" + Constantes.DATOS_MODIFICADOS_OBSERVACIONES+"](" + Constantes.DATOS_MODIFICADOS_VALOR_ORIGINAL + ": " + registroDto.getObservacionConfirmarAnulacion() + ")");
			}
			
			ShvWfWorkflow wf = registroModelo.getWorkFlow();
			
			//Verifico concurrencia
			verificarConcurrencia(registroDto.getIdRegistro(), Long.valueOf(registroDto.getTimeStamp()));
			
			//Cambiar el estado del registro a Pendiente de Anulación
			ShvWfWorkflow workflowActualizado = workflowRegistrosAVC.confirmarAnulacion(wf, datosModificados, registroDto.getUsuarioModificacion());
			
			registroModelo.setWorkFlow(workflowActualizado);
			registroAVCDao.modificar(registroModelo);
			
			// Armo el correo que notificará la confirmará la disponiblidad del valor.
			String destinatarioPara = null; // Queda en nulo porque esta direccion corresponde al generador de la tarea, se completa al finalizar la tarea
			String asunto = registroModelo.getAcuerdo().getBancoCuenta().getBanco().getEmpresa().getDescripcion() 
							+ Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.mail.confirmar.anulacion.registro.ok");
			StringBuffer cuerpo = getCuerpoMailParaAnulacionRegistroAvc(registroModelo)  
			                      .append("<br><br>" + registroDto.getObservacionConfirmarAnulacion());

			Mail mailTarea = new Mail(destinatarioPara, asunto, cuerpo);

			// Finalizo la tarea
			Date fechaEjecucion = new Date();
			tareaServicio.finalizarTarea(TipoTareaEnum.CONF_ANUL_R_AVC, registroModelo.getWorkFlow().getIdWorkflow(), 
					fechaEjecucion, registroDto.getUsuarioModificacion(), mailTarea);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * Método para rechazar el pedido de confirmacion de la anulación del registro Avc.
	 * Ademas finaliza la tarea asociada.
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void rechazarPedidoDeAnulacionRegistroAvc(RegistroAVCDto registroDto) throws NegocioExcepcion {
		try {

			String datosModificados = ("[" + Constantes.DATOS_MODIFICADOS_OBSERVACIONES+"](" + Constantes.DATOS_MODIFICADOS_VALOR_ORIGINAL + ": " + registroDto.getObservacionConfirmarAnulacion() + ")");
			
			ShvAvcRegistroAvc registroModelo = registroAVCDao.buscarRegistroAVC(registroDto.getIdRegistro().toString());
			ShvWfWorkflow wf = registroModelo.getWorkFlow();
			
			//Verifico concurrencia
			verificarConcurrencia(registroDto.getIdRegistro(), Long.valueOf(registroDto.getTimeStamp()));
			
			//Cambiar el estado del registro a Pendiente de Anulación
			ShvWfWorkflow workflowActualizado = workflowRegistrosAVC.rechazarPedidoDeAnulacion(wf, datosModificados, registroDto.getUsuarioModificacion());
			
			registroModelo.setWorkFlow(workflowActualizado);
			registroModelo.setObservaciones(((registroModelo.getObservaciones() != null) ? registroModelo.getObservaciones() : "") + Constantes.RETORNO_WIN + 
					registroDto.getObservacionConfirmarAnulacion());
			registroAVCDao.modificar(registroModelo);
			
			// Genero la tarea para el Analista
			TareaDto tarea = new TareaDto();
			tarea.setIdRegistroAVC(registroModelo.getIdRegistroAvc());
			tarea.setIdWorkflow(registroModelo.getWorkFlow().getIdWorkflow());
			tarea.setTipoTarea(TipoTareaEnum.REV_ANUL_R_AVC);
			tarea.setFechaCreacion(new Date());
			tarea.setUsuarioCreacion(registroDto.getUsuarioModificacion());
			tarea.setPerfilAsignacion(TipoPerfilEnum.ADMIN_VALOR.descripcion());
			tarea.setUsuarioAsignacion(tareaDao.buscarTareaPendiente(TipoTareaEnum.CONF_ANUL_R_AVC, registroModelo.getWorkFlow().getIdWorkflow()).getUsuarioCreacion());
			tarea.setGestionaSobre(getTipoTareaGestionaPorIdTipoValor(registroModelo.getTipoValor().getIdTipoValor()));
			tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
			tarea.setEnviarMail(false);
			tareaServicio.crearTareaRegistroAVC(tarea);
						
			// Preparo e-mail
			String subject = " - " + registroModelo.getNumeroReferenciaDelValorFormateado();
			subject = Utilidad.reemplazarMensajes(Mensajes.ASUNTO_ANULACION_REGISTRO_AVC_RECHAZADO, subject);
			StringBuffer body = new StringBuffer();
			body.append(mailServicio.armarLineaCuerpoValor(registroModelo));
			body.append(" " + Constantes.SEPARADOR_PIPE + " ");
			body.append("Usuario responsable del rechazo: " + ldapServicio.buscarUsuarioPorUidEnMemoria(registroDto.getUsuarioModificacion()).getNombreCompleto());
			if (!Validaciones.isNullOrEmpty(registroDto.getObservacionConfirmarAnulacion())) {
				body.append("<br>" + Utilidad.reemplazarMensajes(Mensajes.CAMPO_OBSERVACIONES, registroDto.getObservacionConfirmarAnulacion().trim()));
			}
			/**
			 * @author u573005, sprint3, defecto 81
			 * Se manda mail a los admin valor cuando se finaliza la tarea de 
			 * revision de rechazo de anulacion del registro avc
			 */
			StringBuffer para = new StringBuffer("");
			String perfilAsignacion = TipoPerfilEnum.ADMIN_VALOR.descripcion(); 
			Collection<UsuarioLdapDto> usuariosLdap = ldapServicio.buscarUsuariosPorPerfilEnMemoria(perfilAsignacion);
			
			for (UsuarioLdapDto usuario: usuariosLdap) {
				if (!Validaciones.isNullOrEmpty(usuario.getMail())) {
					para.append(usuario.getMail()+";");  
				}
			}
			para.setLength(Math.max(para.length() - 1, 0));
			
			Mail mail = new Mail(para.toString(), null, subject, body);
			// Fin preparación e-mail
			
			// Finalizo la tarea
			tareaServicio.finalizarTarea(TipoTareaEnum.CONF_ANUL_R_AVC, registroModelo.getWorkFlow().getIdWorkflow(), new Date(), null, mail);
		
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * 
	 * @param shvAvcRegistroAvc
	 * @return
	 */
	private StringBuffer getCuerpoMailParaAnulacionRegistroAvc(ShvAvcRegistroAvc shvAvcRegistroAvc) {
		
		StringBuffer cuerpoMail = new StringBuffer("");

		switch (TipoValorEnum.getEnumByIdTipoValor(shvAvcRegistroAvc.getTipoValor().getIdTipoValor())) {
	    case BOLETA_DEPOSITO_CHEQUE:
	    	ShvAvcRegistroAvcCheque depositoCheque = shvAvcRegistroAvc.getDeposito().getDepositoCheque();
			cuerpoMail.append("Tipo Registro: " + shvAvcRegistroAvc.getTipoValor().getDescripcion() + " | ");
			cuerpoMail.append("Banco Origen: " + depositoCheque.getCodigoBanco() + " | ");
	    	cuerpoMail.append("Número de Cheque: " + depositoCheque.getNumeroCheque() + " | ");
	    	cuerpoMail.append("Número de Boleta: " + shvAvcRegistroAvc.getDeposito().getNroBoleta() + Constantes.RETORNO_WIN);
	    	break;
	    case BOLETA_DEPOSITO_CHEQUE_DIFERIDO:
	    	ShvAvcRegistroAvcChequeDiferido depositoChequeDiferido = shvAvcRegistroAvc.getDeposito().getDepositoChequeDiferido();
	    		    	cuerpoMail.append("Tipo Registro: " + shvAvcRegistroAvc.getTipoValor().getDescripcion() + " | ");
	    	cuerpoMail.append("Número de Cheque: " + depositoChequeDiferido.getNumeroCheque() + " | ");
	    	cuerpoMail.append("Banco Origen: " + depositoChequeDiferido.getCodigoBanco() + " | ");
	    	cuerpoMail.append("Fecha de Vencimiento: " + Utilidad.formatDatePicker(depositoChequeDiferido.getFechaVencimiento()) + "|");
	    	cuerpoMail.append("Número de Boleta: " + shvAvcRegistroAvc.getDeposito().getNroBoleta() + Constantes.RETORNO_WIN);
	    	break;
	    case BOLETA_DEPOSITO_EFECTIVO:
	    	cuerpoMail.append("Tipo Registro: " + shvAvcRegistroAvc.getTipoValor().getDescripcion() + " | ");
	    	cuerpoMail.append("Número de Boleta: " + shvAvcRegistroAvc.getDeposito().getNroBoleta() + Constantes.RETORNO_WIN);
	    	break;
	    case INTERDEPÓSITO:
	    	ShvAvcRegistroAvcInterdeposito interdeposito = shvAvcRegistroAvc.getInterdeposito();
	    	cuerpoMail.append("Tipo Registro: " + shvAvcRegistroAvc.getTipoValor().getDescripcion() + " | ");
	    	cuerpoMail.append("Código de Organismo: " + interdeposito.getCodigoOrganismo() + " | ");
	    	cuerpoMail.append("Código de Interdepósito: " + interdeposito.getCodigoInterdeposito() + Constantes.RETORNO_WIN);
	    	break;
	    case TRANSFERENCIA:
	    	ShvAvcRegistroAvcTransferencia transferencia = shvAvcRegistroAvc.getTransferencia();
	    	cuerpoMail.append("Tipo Registro: " + shvAvcRegistroAvc.getTipoValor().getDescripcion() + " | ");
	    	cuerpoMail.append("Número de Referencia: " + transferencia.getReferencia() + Constantes.RETORNO_WIN);
	    	break;
	    default:
		}
		return cuerpoMail;
	}
	
	
	/**
	 * Modifica el estado del registro AVC a Conciliado.
	 */
	public ShvAvcRegistroAvc establecerRegistroConciliado(RegistroAVCDto registroAvc) throws NegocioExcepcion {
		try {
			ShvAvcRegistroAvc registroModelo = registroAVCDao.buscarRegistroAVC(registroAvc.getIdRegistro().toString());
			ShvWfWorkflow wf = registroModelo.getWorkFlow();
			
			//Verifico concurrencia
			verificarConcurrencia(registroAvc.getIdRegistro(), Long.valueOf(registroAvc.getTimeStamp()));
			
			//Cambiar el estado del registro a Conciliado
			ShvWfWorkflow workflowActualizado = workflowRegistrosAVC.establecerRegistroConciliado(wf, " ", registroAvc.getUsuarioModificacion());
			
			registroModelo.setWorkFlow(workflowActualizado);
			
			ShvAvcRegistroAvc registroActualizado = registroAVCDao.modificar(registroModelo);
			return registroActualizado;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	/**
	 * Modifica el estado del registro AVC a Conciliacion Sugerida.
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public ShvAvcRegistroAvc establecerRegistroConciliacionSugerida(RegistroAVCDto registroAvc) throws NegocioExcepcion {
		try {
			ShvAvcRegistroAvc registroModelo = registroAVCDao.buscarRegistroAVC(registroAvc.getIdRegistro().toString());

			//Verifico concurrencia
			verificarConcurrencia(registroAvc.getIdRegistro(), Long.valueOf(registroAvc.getTimeStamp()));
			
			//Cambiar el estado del registro a Conciliacion Sugerida.
			ShvWfWorkflow workflowActualizado = workflowRegistrosAVC.establecerRegistroConciliacionSugerida(registroModelo.getWorkFlow(), " ", registroAvc.getUsuarioModificacion());
			
			registroModelo.setWorkFlow(workflowActualizado);
			
			return registroAVCDao.modificar(registroModelo);
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/** 
	 * Modifica el estado del registro AVC a AVC_CONCILIADO_SIN_BOLETA 
	 * Ademas graba la observacion de la confirmacion en ObservacionesConfirmarAlta.
	 * @param String
	 * @throws NegocioExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation = Propagation.REQUIRED)
	public void establecerRegistroConciliadoSinBoleta(RegistroAVCDto registroAvcDto) throws NegocioExcepcion {
		try {
			StringBuffer datosModificados = new StringBuffer();
			datosModificados.append("[" + Constantes.DATOS_MODIFICADOS_OBSERVACIONES + "](" 
			                            + Constantes.DATOS_MODIFICADOS_VALOR_ORIGINAL + ": " 
			                            + registroAvcDto.getObservacionesConfirmarRechazarAltaDeValoresAPartirDeRegistroAVC() + ")");
			
			ShvAvcRegistroAvc registroAvc = registroAVCDao.buscarRegistroAVC(String.valueOf(registroAvcDto.getIdRegistro()));
			ShvWfWorkflow workflowActualizado = workflowRegistrosAVC.establecerRegistroConciliadoSinBoleta(
														registroAvc.getWorkFlow(), datosModificados.toString(), registroAvcDto.getUsuarioModificacion());

			registroAvc.setWorkFlow(workflowActualizado);
			
			registroAVCDao.modificar(registroAvc);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}	
	
	/**
	 * Modifica el estado del registro AVC a AVC_ALTA_VALOR_RECHAZADA
	 * Ademas graba la observacion del rechazo en ObservacionesConfirmarAlta.
	 * @param valorDto
	 * @throws NegocioExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation = Propagation.REQUIRED)
	public void establecerRegistroAltaValorRechazada(RegistroAVCDto registroAvcDto) throws NegocioExcepcion {
		try {
			StringBuffer datosModificados = new StringBuffer();
			datosModificados.append("[" + Constantes.DATOS_MODIFICADOS_OBSERVACIONES + "](" 
			                            + Constantes.DATOS_MODIFICADOS_VALOR_ORIGINAL + ": " 
			                            + registroAvcDto.getObservacionesConfirmarRechazarAltaDeValoresAPartirDeRegistroAVC() + ")");

			ShvAvcRegistroAvc registroAvc = registroAVCDao.buscarRegistroAVC(String.valueOf(registroAvcDto.getIdRegistro()));
			ShvWfWorkflow workflowActualizado = workflowRegistrosAVC.rechazarAltaValor(
														registroAvc.getWorkFlow(), datosModificados.toString(), registroAvcDto.getUsuarioModificacion());

			registroAvc.setWorkFlow(workflowActualizado);

			registroAVCDao.modificar(registroAvc);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	/**
	 * Verificar concurrencia
	 * 
	 */
	public void  verificarConcurrencia(Serializable id, Long timeStamp) throws NegocioExcepcion {
		try {
			ShvAvcRegistroAvc registroModelo = registroAVCDao.buscarRegistroAVC(id.toString());
			
			ShvWfWorkflow wf = registroModelo.getWorkFlow();
			
			//Si la ultima modificacion es distinta a la actual
			if (wf.getFechaUltimaModificacion().getTime() != timeStamp.longValue()) {
				throw new ConcurrenciaExcepcion(registroModelo.getIdRegistroAvc());
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * Cambiar el estado del registro a Conciliado con diferencias.
	 * @param registroAvc
	 * @throws NegocioExcepcion
	 */
	public void establecerRegistroConciliadoConDiferencia(RegistroAVCDto registroAvc, String codigoCliente) throws NegocioExcepcion {
		try {
			String usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
			ShvAvcRegistroAvc registroModelo = registroAVCDao.buscarRegistroAVC(registroAvc.getIdRegistro().toString());
			
			ShvWfWorkflow wf = registroModelo.getWorkFlow();
			
			if (!String.valueOf(registroAvc.getCodigoCliente()).equals(codigoCliente)) {
				if(!Validaciones.isObjectNull(codigoCliente)){
					wf = workflowRegistrosAVC.actualizarWorkflow(wf, 
							"[Codigo Cliente](" + Constantes.DATOS_MODIFICADOS_VALOR_ORIGINAL + ": "+registroAvc.getCodigoCliente()+")(" + Constantes.DATOS_MODIFICADOS_VALOR_MODIFICADO + ": "+codigoCliente+")", 
							usuarioBatch);
					registroAvc.setCodigoCliente(String.valueOf(codigoCliente));
					
					registroModelo.setWorkFlow(wf);
					registroAVCDao.modificar(registroModelo);
				}
			}
			
			//Verifico concurrencia
			verificarConcurrencia(registroAvc.getIdRegistro(), wf.getFechaUltimaModificacion().getTime());
			
			//Cambiar el estado del registro a Conciliado con diferencias.
			ShvWfWorkflow workflowActualizado = workflowRegistrosAVC.conciliarRegistroConDiferencias(wf, " ", registroAvc.getUsuarioModificacion());
			
			registroModelo.setWorkFlow(workflowActualizado);
			
			registroAVCDao.modificar(registroModelo);
			
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * Cambiar el estado del registro de Conciliacion sugerida a Pendiente de Conciliacion. 
	 * @param registroAvc
	 * @throws NegocioExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void deshacerConciliacionSugerida(RegistroAVCDto registroAvc) throws NegocioExcepcion {
		try {
			ShvAvcRegistroAvc registroModelo = registroAVCDao.buscarRegistroAVC(registroAvc.getIdRegistro().toString());
			ShvWfWorkflow wf = registroModelo.getWorkFlow();
			
			//Verifico concurrencia
			verificarConcurrencia(registroAvc.getIdRegistro(), wf.getFechaUltimaModificacion().getTime());
			
			//Cambiar el estado del registro a Pendiente de Conciliacion. Es decir Deshace la conciliacion sugerida.
			ShvWfWorkflow workflowActualizado = workflowRegistrosAVC.deshacerRegistroConciliacionSugerida(wf, " ", registroAvc.getUsuarioModificacion());
			
			registroModelo.setWorkFlow(workflowActualizado);
			
			registroAVCDao.modificar(registroModelo);
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * Cambiar el estado del registro de alta de valor rechazada a Pendiente de confirmar alta de valor
	 * @param registroAvc
	 * @throws NegocioExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void solicitarReconfirmacionAltaValor(RegistroAVCDto registroAvc) throws NegocioExcepcion {
		try {
			ShvAvcRegistroAvc registroModelo = registroAVCDao.buscarRegistroAVC(registroAvc.getIdRegistro().toString());
			ShvWfWorkflow wf = registroModelo.getWorkFlow();
			
			//Verifico concurrencia
			verificarConcurrencia(registroAvc.getIdRegistro(), wf.getFechaUltimaModificacion().getTime());
			
			//Cambiar el estado del registro a Pendiente de Conciliacion. Es decir Deshace la conciliacion sugerida.
			ShvWfWorkflow workflowActualizado = workflowRegistrosAVC.solicitarReconfirmacionAltaValor(wf, " ", registroAvc.getUsuarioModificacion());
			registroModelo.setWorkFlow(workflowActualizado);
			registroAVCDao.modificar(registroModelo);
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	
	/**
	 * Busca un registro avc y lo mapea segun su tipo.
	 */
	public RegistroAVCDto buscarRegistroAVC(String id) throws NegocioExcepcion {
		try {
			ShvAvcRegistroAvc registroModelo = registroAVCDao.buscarRegistroAVC(id.toString());
			RegistroAVCDto registroDto = new RegistroAVCDto();
			if(!Validaciones.isObjectNull(registroModelo.getDeposito())){
				registroDto = (DepositoDto) depositoMapeador.map(registroModelo);
			}else{
				if(!Validaciones.isObjectNull(registroModelo.getInterdeposito())){	
					registroDto = (InterdepositoDto) interdepositoMapeador.map(registroModelo);
				}else{
					if(!Validaciones.isObjectNull(registroModelo.getTransferencia())){	
						registroDto = (TransferenciaDto) transferenciaMapeador.map(registroModelo);
					}
				}
			}
			return registroDto;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * Busca un registro avc y lo mapea a un ValorDto. Metodo utilizado en 
	 * el alta de valores a partir de un registro AVC.
	 */
	public ValorDto buscarRegistroAvcYMapearValorDto(String idRegistro) throws NegocioExcepcion {
		try{
			ValorDto valorDto = new ValorDto();
			
			ShvAvcRegistroAvc registroModelo = registroAVCDao.buscarRegistroAVC(idRegistro);
			//Campos generales
			valorDto.setIdEmpresa(registroModelo.getAcuerdo().getBancoCuenta().getBanco().getEmpresa().getIdEmpresa());
			valorDto.setEmpresa(registroModelo.getAcuerdo().getBancoCuenta().getBanco().getEmpresa().getDescripcion());
			valorDto.setImporte(Utilidad.formatCurrency(registroModelo.getImporte(),2));
			valorDto.setEstadoRegistroAvc(registroModelo.getWorkFlow().getShvWfWorkflowEstado().iterator().next().getEstado().descripcion());
			valorDto.setOrigen(Constantes.ORIGEN_CONCILIACION_DESCRIPCION);
			valorDto.setIdOrigen(Constantes.ORIGEN_CONCILIACION_ID);
			valorDto.setAcuerdo(registroModelo.getAcuerdo().getDescripcion());
			valorDto.setIdAcuerdo(String.valueOf(registroModelo.getAcuerdo().getIdAcuerdo()));
			valorDto.setBancoDeposito(registroModelo.getAcuerdo().getBancoCuenta().getBanco().getDescripcion());
			valorDto.setIdBancoDeposito(registroModelo.getAcuerdo().getBancoCuenta().getBanco().getIdBanco());
			valorDto.setNumeroCuenta(registroModelo.getAcuerdo().getBancoCuenta().getCuentaBancaria());
			valorDto.setIdNroCuenta(String.valueOf(registroModelo.getAcuerdo().getBancoCuenta().getIdBancoCuenta()));
			valorDto.setIdRegistroAvc(idRegistro);
			valorDto.setCodCliente((Validaciones.isNullOrEmpty(registroModelo.getCodigoCliente()))?"":registroModelo.getCodigoCliente());
			valorDto.setValorNuevo(true);
			valorDto.setFechaAlta(new Date());
			valorDto.setFechaAltaValor(Utilidad.formatDatePicker(new Date()));
			
			//Campos especificos
			/**
			 * @author u573005, sprint3
			 * desde siempre se mapean los tipos 2,3 y 4 con los 5, 6 y 7
			 * respectivamente y esto es correcto
			 */
			switch (TipoValorEnum.getEnumByIdTipoValor(registroModelo.getTipoValor().getIdTipoValor())) {
		    case BOLETA_DEPOSITO_CHEQUE:
		    	valorDto.setTipoValor(TipoValorEnum.CHEQUE.getDescripcion());
				valorDto.setIdTipoValor(String.valueOf(TipoValorEnum.CHEQUE.getIdTipoValor()));
				
		    	ShvAvcRegistroAvcCheque regAuxCheque = registroModelo.getDeposito().getDepositoCheque();
		    	valorDto.setFechaDeposito(Utilidad.formatDatePicker(regAuxCheque.getDeposito().getFechaPago()));
		    	valorDto.setNumeroBoleta(String.valueOf(regAuxCheque.getDeposito().getNroBoleta()));
		    	
		    	String idBandoOrigenAux = "";
		    	int cantCodigoBanco = regAuxCheque.getCodigoBanco().toString().length();
				if(cantCodigoBanco<4){
		    		StringBuilder sb = new StringBuilder();
	                int add = 4 - cantCodigoBanco;
	                for (int i = 0; i < add; i++) {
	                    sb.append(Utilidad.ZERO_CHARACTER);
	                }
	                sb.append(regAuxCheque.getCodigoBanco());
	                idBandoOrigenAux = sb.toString();
		    	}
		    	if(!Validaciones.isNullOrEmpty(idBandoOrigenAux)){
			    	ShvParamBanco bancoOrigenChe = bancoDao.buscarBanco(idBandoOrigenAux);
			    	valorDto.setIdBancoOrigen(bancoOrigenChe.getIdBanco());
			    	valorDto.setBancoOrigen(bancoOrigenChe.getDescripcion());
		    	}
		    	
		    	valorDto.setNumeroCheque(String.valueOf(regAuxCheque.getNumeroCheque()));
		    	valorDto.setFechaDeposito(Utilidad.formatDatePicker(registroModelo.getDeposito().getFechaPago()));
		    	break;
		    case BOLETA_DEPOSITO_CHEQUE_DIFERIDO:
		    	valorDto.setTipoValor(TipoValorEnum.CHEQUE_DIFERIDO.getDescripcion());
				valorDto.setIdTipoValor(String.valueOf(TipoValorEnum.CHEQUE_DIFERIDO.getIdTipoValor()));
				
		    	ShvAvcRegistroAvcChequeDiferido regAuxChequeDiferido = registroModelo.getDeposito().getDepositoChequeDiferido();
		    	valorDto.setFechaDeposito(Utilidad.formatDatePicker(regAuxChequeDiferido.getDeposito().getFechaPago()));
		    	valorDto.setNumeroBoleta(String.valueOf(regAuxChequeDiferido.getDeposito().getNroBoleta()));
		    	String idBandoOrigenAux2 = "";
		    	int cantCodigoBancoDif = String.valueOf(regAuxChequeDiferido.getCodigoBanco()).length();
				if(cantCodigoBancoDif<4){
		    		StringBuilder sb = new StringBuilder();
	                int add = 4 - cantCodigoBancoDif;
	                for (int i = 0; i < add; i++) {
	                    sb.append(Utilidad.ZERO_CHARACTER);
	                }
	                sb.append(regAuxChequeDiferido.getCodigoBanco());
	                idBandoOrigenAux2 = sb.toString();
		    	}
		    	if(!Validaciones.isNullOrEmpty(idBandoOrigenAux2)){
		    		ShvParamBanco bancoOrigenCheDif = bancoDao.buscarBanco(idBandoOrigenAux2);
			    	valorDto.setIdBancoOrigen(bancoOrigenCheDif.getIdBanco());
			    	valorDto.setBancoOrigen(bancoOrigenCheDif.getDescripcion());
		    	}
		    	
		    	valorDto.setNumeroCheque(String.valueOf(regAuxChequeDiferido.getNumeroCheque()));
		    	valorDto.setFechaVencimiento(Utilidad.formatDatePicker(regAuxChequeDiferido.getFechaVencimiento()));
		    	valorDto.setFechaDeposito(Utilidad.formatDatePicker(registroModelo.getDeposito().getFechaPago()));
		    	break;
		    case BOLETA_DEPOSITO_EFECTIVO:
		    	valorDto.setTipoValor(TipoValorEnum.EFECTIVO.getDescripcion());
				valorDto.setIdTipoValor(String.valueOf(TipoValorEnum.EFECTIVO.getIdTipoValor()));
				
		    	ShvAvcRegistroAvcEfectivo regAuxEfectivo = registroModelo.getDeposito().getDepositoEfectivo();
		    	valorDto.setFechaDeposito(Utilidad.formatDatePicker(regAuxEfectivo.getDeposito().getFechaPago()));
		    	valorDto.setNumeroBoleta(String.valueOf(regAuxEfectivo.getDeposito().getNroBoleta()));
		    	valorDto.setFechaDeposito(Utilidad.formatDatePicker(registroModelo.getDeposito().getFechaPago()));
		    	break;
		    case INTERDEPÓSITO:
		    	valorDto.setTipoValor(registroModelo.getTipoValor().getDescripcion());
				valorDto.setIdTipoValor(String.valueOf(registroModelo.getTipoValor().getIdTipoValor()));
				
		    	ShvAvcRegistroAvcInterdeposito regAuxInterdeposito = registroModelo.getInterdeposito();
		    	valorDto.setNumeroInterdepositoCdif(String.valueOf(regAuxInterdeposito.getCodigoInterdeposito()));
//		    	if(!Validaciones.isObjectNull(organismoDao.buscarOrganismo(regAuxInterdeposito.getCodigoOrganismo()))){
		    		valorDto.setCodOrganismo(regAuxInterdeposito.getCodigoOrganismo());
//		    	}
		    	valorDto.setFechaInterdeposito(Utilidad.formatDatePicker(regAuxInterdeposito.getFechaIngreso()));
		    	break;
		    case TRANSFERENCIA:
		    	valorDto.setTipoValor(registroModelo.getTipoValor().getDescripcion());
				valorDto.setIdTipoValor(String.valueOf(registroModelo.getTipoValor().getIdTipoValor()));
				
		    	ShvAvcRegistroAvcTransferencia regAuxTransferencia = registroModelo.getTransferencia();
		    	valorDto.setNumeroReferencia(String.valueOf(regAuxTransferencia.getReferencia()));
		    	valorDto.setFechaTransferencia(Utilidad.formatDatePicker(regAuxTransferencia.getFechaIngreso()));
		    	valorDto.setCuit(regAuxTransferencia.getCuit());
		    	valorDto.setObservacionRegistroAvc(regAuxTransferencia.getObservacion());
		    	break;
		    default:
			}
				
			return valorDto;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * Metodo que actualiza el cuit y las observaciones de una transferencia.
	 */
	public void actualizarCuitYObservacionDeTransferencia(TransferenciaDto transferenciaDto) throws NegocioExcepcion {
		try{
			//Busco el registro transferencia
			ShvAvcRegistroAvc registroAvc = registroAVCDao.buscarRegistroAVC(String.valueOf(transferenciaDto.getIdRegistro()));
			
			//le seteo el cuit y la observacion
			registroAvc.getTransferencia().setCuit(transferenciaDto.getCuit());
			registroAvc.getTransferencia().setObservacionEditarCuit(transferenciaDto.getNuevaObservacion());
			registroAvc.setObservaciones(((registroAvc.getObservaciones() != null) ? registroAvc.getObservaciones() : "") + Constantes.RETORNO_WIN +
				transferenciaDto.getNuevaObservacion());
			
			List<Long> iDComprobantes = new ArrayList<Long>();
			
			List<ShvDocDocumentoAdjunto> adjuntosFinales = new ArrayList<ShvDocDocumentoAdjunto>();
			
			for (ComprobanteDto comprobante : transferenciaDto.getListaComprobantes()) {
				if(Validaciones.isObjectNull(comprobante.getIdComprobante())){
					//creo el adjunto
					ShvDocDocumentoAdjunto docAdjunto = new ShvDocDocumentoAdjunto();
//					docAdjunto.setIdValorAdjunto(Long.valueOf(comprobante.getIdComprobante().toString()));
					docAdjunto.setArchivoAdjunto(comprobante.getDocumento());
					docAdjunto.setDescripcion(comprobante.getDescripcionArchivo());
					docAdjunto.setFechaCreacion(new Date());
					docAdjunto.setNombreArchivo(comprobante.getNombreArchivo());
					docAdjunto.setUsuarioCreacion(comprobante.getUsuarioCreacion());
					
					ShvDocDocumentoAdjunto docAdjuntoModelo = documentoAdjuntoDao.crear(docAdjunto);
					ShvAvcRegistroAdjuntoPk registroAdjuntoPK = new ShvAvcRegistroAdjuntoPk();
					registroAdjuntoPK.setDocumentoAdjunto(docAdjuntoModelo);
					registroAdjuntoPK.setRegistroAvc(registroAvc);
					ShvAvcRegistroAdjunto registroAdjunto = new ShvAvcRegistroAdjunto();
					registroAdjunto.setShvAvcRegistroAdjuntoPk(registroAdjuntoPK);
					registroAVCDao.insertarDocumentoAjunto(registroAdjunto);
					
					adjuntosFinales.add(docAdjuntoModelo);
				}else{
					//Actualizo el adjunto
					ShvDocDocumentoAdjunto docAdjunto = documentoAdjuntoDao.buscarDocumentoAdjunto(Long.valueOf(comprobante.getIdComprobante().toString()));
					docAdjunto.setArchivoAdjunto(comprobante.getDocumento());
					docAdjunto.setDescripcion(comprobante.getDescripcionArchivo());
					docAdjunto.setNombreArchivo(comprobante.getNombreArchivo());
					docAdjunto.setUsuarioCreacion(transferenciaDto.getUsuarioModificacion());
					docAdjunto = documentoAdjuntoDao.crear(docAdjunto);
					iDComprobantes.add(Long.valueOf(comprobante.getIdComprobante().toString()));
					
					adjuntosFinales.add(docAdjunto);
				}
				
			}
			
			List<ShvDocDocumentoAdjunto> documentosAdjuntosBBDD = documentoAdjuntoDao.listarDocumentosAdjuntosPorIdTransferencia(registroAvc.getIdRegistroAvc());
			for (ShvDocDocumentoAdjunto docAdjunto : adjuntosFinales) {
				if (documentosAdjuntosBBDD.contains(docAdjunto)) {
					documentosAdjuntosBBDD.remove(docAdjunto);
				}
			}
			
			if (Validaciones.isCollectionNotEmpty(documentosAdjuntosBBDD)) {
				for (ShvDocDocumentoAdjunto docAdjunto : documentosAdjuntosBBDD) {
					documentoAdjuntoDao.eliminarDocumentoAdjuntoARegistroAvcTransaccion(docAdjunto);
				}
			}
			
//			if(Validaciones.isCollectionNotEmpty(iDComprobantes)) {
//				List<ShvDocDocumentoAdjunto> documentosAdjuntosBBDD = documentoAdjuntoDao.listarDocumentosAdjuntosPorIdTransferencia(registroAvc.getIdRegistroAvc());
//				for (ShvDocDocumentoAdjunto docAdjunto : documentosAdjuntosBBDD) {
//					if(!iDComprobantes.contains(docAdjunto.getIdValorAdjunto())){
//						documentoAdjuntoDao.eliminarDocumentoAdjuntoARegistroAvcTransaccion(docAdjunto);
//					}
//				}
//			}

			// Actualizo en la base
			registroAVCDao.modificar(registroAvc);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * retorna la lista de ComprobanteDto adjuntados a una transferencia.
	 */
	public List<ComprobanteDto> buscarDocumentosAdjuntosPorIdTransferencia(String idRegistro) throws NegocioExcepcion {
		try{
			List<ComprobanteDto> comprobantes = new ArrayList<ComprobanteDto>();
			List<ShvAvcRegistroAdjunto> transferencias =  registroAVCDao.buscarDocumentosAdjuntosPorIdTransferencia(idRegistro);
			for (ShvAvcRegistroAdjunto registroAdjunto : transferencias) {
				ComprobanteDto comprobante = new ComprobanteDto();
				ShvDocDocumentoAdjunto documento = registroAdjunto.getShvAvcRegistroAdjuntoPk().getDocumentoAdjunto();
				if(!Validaciones.isObjectNull(documento)){
					comprobante.setId(documento.getIdValorAdjunto());
					comprobante.setIdComprobante(documento.getIdValorAdjunto());
					comprobante.setNombreArchivo(documento.getNombreArchivo());
					comprobante.setDescripcionArchivo(documento.getDescripcion());
					comprobante.setDocumento(documento.getArchivoAdjunto());
					comprobante.setFechaAlta(documento.getFechaCreacion());
					comprobante.setUsuarioCreacion(documento.getUsuarioCreacion());
					comprobantes.add(comprobante);
				}
			}
			
			return comprobantes;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * Metodo que valida si existe modificacion en la transferencia
	 * @throws NegocioExcepcion 
	 */
	public void validacionModificacionTransferencia(TransferenciaDto transferenciaDto) throws NegocioExcepcion {
		try {
			ShvAvcRegistroAvc registroModelo = registroAVCDao.buscarRegistroAVC(String.valueOf(transferenciaDto.getIdRegistro()));
			String observacionEditarQuit = "";
			String cuit = "";
			
			if (!Validaciones.isObjectNull(registroModelo.getTransferencia().getObservacionEditarCuit())) {
				observacionEditarQuit = registroModelo.getTransferencia().getObservacionEditarCuit();
			} else if (!Validaciones.isObjectNull(registroModelo.getTransferencia().getCuit())) {
				cuit = registroModelo.getTransferencia().getCuit();
			}
			
			if(!Validaciones.isObjectNull(registroModelo.getTransferencia())){
				if(cuit.equals(transferenciaDto.getCuit()) && observacionEditarQuit.equals(transferenciaDto.getObservacionEditarCuit())){
					
					TransferenciaDto transferenciaDtoBBDD = (TransferenciaDto) transferenciaMapeador.map(registroModelo);
					transferenciaDtoBBDD.setListaComprobantes(buscarDocumentosAdjuntosPorIdTransferencia(String.valueOf(transferenciaDto.getIdRegistro())));
					
					if(transferenciaDto.getListaComprobantes().containsAll(transferenciaDtoBBDD.getListaComprobantes())
							&& transferenciaDtoBBDD.getListaComprobantes().containsAll(transferenciaDto.getListaComprobantes())){
						throw new ValidacionExcepcion("", Propiedades.MENSAJES_PROPIEDADES.getString("error.noHayModificacion"));
					}
				}
			}
		} catch (PersistenciaExcepcion e) {
			Traza.error(RegistroAVCServicioImpl.class, e.getMessage(), e);
			throw new ValidacionExcepcion(e.getMessage(), e);
		}
		
	}
	
	/**
	 * Metodo que graba el valor en estado "no disponible" y ademas
	 * grabar en la tabla ShvAvcRegistroAvcValor para asociar el Registro avc y el valor.
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public ShvValValor crearValorAPartirRegistroAvc(ValorDto valorDto) throws NegocioExcepcion {
		try {
			valorDto.setMigracion(true);
			ShvValValor valorBBDD = valorServicio.crearValor(valorDto);
			ShvAvcRegistroAvc registroBBDD = registroAVCDao.buscarRegistroAVC(valorDto.getIdRegistroAvc());
			
			if(registroBBDD.getWorkFlow().getShvWfWorkflowEstado().iterator().next().getEstado().equals(Estado.AVC_PENDIENTE)){
				ShvWfWorkflow workflowActualizado = workflowRegistrosAVC.solicitarConfirmacionAltaValor(registroBBDD.getWorkFlow(), "Solicitando confirmacion para el alta del valor", valorDto.getIdAnalista());
				registroBBDD.setWorkFlow(workflowActualizado);
			}else{
				if(registroBBDD.getWorkFlow().getShvWfWorkflowEstado().iterator().next().getEstado().equals(Estado.AVC_ALTA_VALOR_RECHAZADA)){
					ShvWfWorkflow workflowActualizado = workflowRegistrosAVC.solicitarReconfirmacionAltaValor(registroBBDD.getWorkFlow(), "Solicitando confirmacion para el alta del valor", valorDto.getIdAnalista());
					registroBBDD.setWorkFlow(workflowActualizado);
				}
			}
			valorServicio.actualizarEstadoRecibo(null, valorBBDD, true);

			ShvAvcRegistroAvcValor registroAvcValor = new ShvAvcRegistroAvcValor();
			ShvAvcRegistroAvcValorPK registroAvcValorPK = new ShvAvcRegistroAvcValorPK();
			registroAvcValorPK.setValor(valorBBDD);
			registroAvcValorPK.setRegistroAvc(registroBBDD);
			registroAvcValor.setShvAvcRegistroAvcValorPK(registroAvcValorPK);
			
			registroAVCDao.crearShvAvcRegistroAvcValor(registroAvcValor);
			return valorBBDD;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * Metodo para confirmar el alta de un valor a partir de un registro avc.
	 * Ademas finaliza la tarea asociada.
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void confirmarTareaAltaValorAPartirRegistroAvc(ValorDto valorDto) throws NegocioExcepcion {
		
		String idRegistroAvc = valorDto.getIdRegistroAvc();
		
		// Actualizo el estado del registro AVC		
		RegistroAVCDto registroAvcDto = new RegistroAVCDto();
		registroAvcDto.setIdRegistro(new Long(idRegistroAvc));
		registroAvcDto.setUsuarioModificacion(valorDto.getUsuarioModificacion());
		registroAvcDto.setObservacionesConfirmarRechazarAltaDeValoresAPartirDeRegistroAVC(valorDto.getObservacionesConfirmarAlta());
		
		establecerRegistroConciliadoSinBoleta(registroAvcDto);
		
		// Busco la PK Compuesta por idRegistroAvc y ya me traigo el modelo de registroA y el valor asociado al registro AVC en cuestión
		// TODO: Revisado por Pablo: Por alguna razón la PK compuesta no esta mapeada en el ShvAvcRegistroAVC?
		
		ShvAvcRegistroAvc registroAvcModelo = null;
		ShvValValor valorModelo = null;
		try {
			ShvAvcRegistroAvcValor registroValor = registroAVCDao.buscarRegistroAvcValorPorIdRegistroAvc(idRegistroAvc);
			
			registroAvcModelo = registroValor.getShvAvcRegistroAvcValorPK().getRegistroAvc();
			valorModelo       = registroValor.getShvAvcRegistroAvcValorPK().getValor();

		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}

		// Le seteo el verdadero IdValor a valorDto
		valorDto.setIdValor(valorModelo.getIdValor());
		
		// Actualizo el estado del valor asociado a Disponible
		valorServicio.actualizarWorkflowDeValorADisponible(valorDto, valorDto.getUsuarioModificacion());

		// Armo el correo que notificará la confirmará la disponiblidad del valor.
		
		String destinatarioPara = ""; // Queda en nulo porque esta direccion corresponde al generador de la tarea, se completa al finalizar la tarea
		String destinatarioCc   = "";

//		String asunto = " - " + mailServicio.armarAsuntoValor(valorModelo);
		
		//Modificacion asunto y cuerpo Req. 67475 Sprint 07
		StringBuffer asunto = mailServicio.armarAsuntoValorStringBuffer(valorModelo);
		StringBuffer cuerpo = mailServicio.armarCuerpoValorStringBuffer(valorModelo);
		
//		asunto = Utilidad.reemplazarMensajes(Mensajes.ASUNTO_VALOR_DISPONIBLE, asunto);
//		StringBuffer cuerpo = new StringBuffer();

//		cuerpo.append(mailServicio.armarLineaCuerpoValor(valorModelo));
		//--------------------------------------------------------------------------------------------------------------
		if (!Validaciones.isNullOrEmpty(valorModelo.getIdAnalista())) {
			String mailPara = mailServicio.obtenerMailUsuario(valorModelo.getIdAnalista());
			if (!Validaciones.isNullOrEmpty(mailPara)){
				destinatarioPara = mailPara;
			}				
		}
		
		if (!Validaciones.isNullOrEmpty(valorModelo.getIdCopropietario())) {
			String mailCc = mailServicio.obtenerMailUsuario(valorModelo.getIdCopropietario());
			if (!Validaciones.isNullOrEmpty(mailCc)){
				destinatarioCc = mailCc;
			}				
		}
		//MODIFICACION LUCAS :Agrega al analista team comercial en CC--------------------------------------------		 
		if (!Validaciones.isNullOrEmpty(valorModelo.getIdClienteLegado().toString())) {
			TeamComercialDto usuarioTeamComercial = teamComercialServicio.buscarTeamComercial(valorModelo.getIdClienteLegado().toString());
			if (!Validaciones.isObjectNull(usuarioTeamComercial) && !Validaciones.isNullOrEmpty(usuarioTeamComercial.getUserAnalistaCobranzaDatos())) {
				String mailCc = mailServicio.obtenerMailUsuario(usuarioTeamComercial.getUserAnalistaCobranzaDatos());
				
				if(!Validaciones.isNullOrEmpty(destinatarioCc)){
					if (!Validaciones.isNullOrEmpty(mailCc)){
						destinatarioCc += "; " + mailCc;
					}
				}else{
					if (!Validaciones.isNullOrEmpty(mailCc)){
						destinatarioCc = mailCc;
					}
				}
					
			}
		}
		//--------------------------------------------
		Mail mail = new Mail(destinatarioPara, destinatarioCc, null, asunto.toString(), cuerpo);
		
		// Finalizo la tarea  
		Date fechaEjecucion = new Date();
		
		tareaServicio.finalizarTareaCorrecto(
				TipoTareaEnum.CONF_ALTA_V_AVC, 
				registroAvcModelo.getWorkFlow().getIdWorkflow(), 
				fechaEjecucion, 
				valorModelo.getIdAnalista(), 
				mail);
	}
	
	/**
	 * Metodo para rechazar el alta de un valor a partir de un registro avc.
	 * Ademas finaliza la tarea asociada.
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void rechazarTareaAltaValorAPartirRegistroAvc(ValorDto valorDto, UsuarioSesion usuarioSesion) throws NegocioExcepcion {
		try {

			String idRegistroAvc = valorDto.getIdRegistroAvc();

			// Actualizo el estado del registro AVC		
			RegistroAVCDto registroAvcDto = new RegistroAVCDto();
			registroAvcDto.setIdRegistro(new Long(idRegistroAvc));
			registroAvcDto.setUsuarioModificacion(valorDto.getUsuarioModificacion());
			registroAvcDto.setObservacionesConfirmarRechazarAltaDeValoresAPartirDeRegistroAVC(valorDto.getObservacionesConfirmarAlta());

			establecerRegistroAltaValorRechazada(registroAvcDto);

			// Busco la PK Compuesta por idRegistroAvc y ya me traigo el modelo de registroA y el valor asociado al registro AVC en cuestión
			// TODO: Revisado por Pablo: Por alguna razón la PK compuesta no esta mapeada en el ShvAvcRegistroAVC?

			ShvAvcRegistroAvc registroAvcModelo = null;
			ShvValValor valorModelo = null;

			ShvAvcRegistroAvcValor registroValor = registroAVCDao.buscarRegistroAvcValorPorIdRegistroAvc(idRegistroAvc);

			registroAvcModelo = registroValor.getShvAvcRegistroAvcValorPK().getRegistroAvc();
			valorModelo       = registroValor.getShvAvcRegistroAvcValorPK().getValor();

			// Agrego observación de rechazo a las observaciones del valor
			valorModelo.setObservaciones(((valorModelo.getObservaciones() != null) ? valorModelo.getObservaciones().trim() : "") + 
					((valorDto.getObservacionesConfirmarAlta() != null) ? Constantes.RETORNO_WIN + valorDto.getObservacionesConfirmarAlta().trim() : ""));
			valorDao.actualizarValor(valorModelo);
			
			// Genero la tarea para el Analista
			TareaDto tarea = new TareaDto();
			tarea.setIdRegistroAVC(registroAvcModelo.getIdRegistroAvc());
			tarea.setIdWorkflow(registroAvcModelo.getWorkFlow().getIdWorkflow());
			tarea.setTipoTarea(TipoTareaEnum.REV_ALTA_V_AVC);
			tarea.setFechaCreacion(new Date());
			tarea.setUsuarioCreacion(usuarioSesion.getIdUsuario());
			tarea.setPerfilAsignacion(TipoPerfilEnum.ANALISTA.descripcion());
			tarea.setUsuarioAsignacion(tareaDao.buscarTareaPendiente(TipoTareaEnum.CONF_ALTA_V_AVC, registroAvcModelo.getWorkFlow().getIdWorkflow()).getUsuarioCreacion());
			tarea.setGestionaSobre(getTipoTareaGestionaPorIdTipoValor(registroAvcModelo.getTipoValor().getIdTipoValor()));
			tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
			tarea.setEnviarMail(false);
			tareaServicio.crearTareaRegistroAVC(tarea);
			
			// Preparo e-mail
			String to = mailServicio.obtenerMailUsuario(valorModelo.getIdAnalista());
			
			String cc = "";
			if (!Validaciones.isNullOrEmpty(valorModelo.getIdCopropietario())) {
				String mailCc = mailServicio.obtenerMailUsuario(valorModelo.getIdCopropietario());
				if(!Validaciones.isNullOrEmpty(mailCc)){
					cc = mailCc;	
				}				
			}
			
			TeamComercialDto teamComercialDto = teamComercialServicio.buscarTeamComercial(valorModelo.getIdClienteLegado().toString());
			if (!Validaciones.isObjectNull(teamComercialDto) && !Validaciones.isNullOrEmpty(teamComercialDto.getAnalistaCobranzaDatos())) {
				String mailCc = mailServicio.obtenerMailUsuario(teamComercialDto.getUserAnalistaCobranzaDatos());
				if(!Validaciones.isNullOrEmpty(mailCc)){
					cc = ((!Validaciones.isNullOrEmpty(cc)) ? cc + ";" : "") + mailCc;	
				}				
			}
			String subject = " - " + mailServicio.armarAsuntoValor(valorModelo);
			subject = Utilidad.reemplazarMensajes(Mensajes.ASUNTO_ALTA_REGISTRO_AVC_RECHAZADA, subject);
			StringBuffer body = new StringBuffer();
			body.append(mailServicio.armarLineaCuerpoValor(valorModelo));
			body.append(" " + Constantes.SEPARADOR_PIPE + " ");
			body.append("Usuario responsable del rechazo: " + usuarioSesion.getNombreCompleto());
			if (!Validaciones.isNullOrEmpty(valorDto.getObservacionesConfirmarAlta())) {
				body.append("<br>" + Utilidad.reemplazarMensajes(Mensajes.CAMPO_OBSERVACIONES,valorDto.getObservacionesConfirmarAlta().trim()));
			}
			
			Mail mail = new Mail(to, cc, subject, body);
			// Fin preparación e-mail

			// Finalizo la tarea  
			Date fechaEjecucion = new Date();

			tareaServicio.finalizarTarea(
					TipoTareaEnum.CONF_ALTA_V_AVC, 
					registroAvcModelo.getWorkFlow().getIdWorkflow(), 
					fechaEjecucion, 
					valorDto.getUsuarioModificacion(), 
					null);
			
			mailServicio.sendMail(mail);

		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	public void generarTarea(ValorDto valorDto, UsuarioSesion userSesion) throws NegocioExcepcion {
		
		valorDto.setGenerarValorNoDispoblePorDefecto(true);
		ShvValValor valor = null;
		
		try {
			ShvAvcRegistroAvc registroModelo = null;
			if(Validaciones.isObjectNull(valorDto.getIdValor())){
				//Alta de valor por primera vez
				if(!Validaciones.isNullOrEmpty(valorDto.getFechaInterdeposito())){
						valorDto.setFechaInterdeposito(Utilidad.formatDateAndTime(Utilidad.parseDatePickerString(valorDto.getFechaInterdeposito())));
				}
								
				//Muy raro que pase estos errores en produccion
				if (Validaciones.isNullOrEmpty(valorDto.getIdTipoValor())) {
					Traza.advertencia(getClass(), "generarTarea --> IDValor -->  "+valorDto.getIdValor()+" IdTipoValor vacio --> Descripcion: " + valorDto.getTipoValor() + " idAcuerdo -->"+valorDto.getIdAcuerdo()+" Acuerdo -->"+ valorDto.getAcuerdo());
//					valorDto.setIdTipoValor(TipoValorEnum.getEnumByDescripcion(valorDto.getTipoValor()).getIdString());
				}
				//Fin.Muy raro que pase estos errores en produccion
				
				valorDto.formarNumeroValor();
				valor = crearValorAPartirRegistroAvc(valorDto);
			} else {
				//Alta de valor luego de haberse rechazado
				
				//Muy raro que pase estos errores en produccion
				if (Validaciones.isNullOrEmpty(valorDto.getIdTipoValor())) {
					Traza.advertencia(getClass(), "generarTarea --> IDValor -->  "+valorDto.getIdValor()+" IdTipoValor vacio --> Descripcion: " + valorDto.getTipoValor() + " idAcuerdo -->"+valorDto.getIdAcuerdo()+" Acuerdo -->"+ valorDto.getAcuerdo());
				}
				//Fin.Muy raro que pase estos errores en produccion
				
				RegistroAVCDto registroDto = buscarRegistroAVC(valorDto.getIdRegistroAvc());
				registroDto.setUsuarioModificacion(userSesion.getIdUsuario());
				solicitarReconfirmacionAltaValor(registroDto);
				valorServicio.administrarModificar(valorDto, userSesion);
				valor = valorServicio.buscarValValorPorIdValor(valorDto.getIdValor().intValue());
				
				// Preparo e-mail
				String cc = "";
				if (!Validaciones.isNullOrEmpty(valor.getIdCopropietario())) {
					String mailCc = mailServicio.obtenerMailUsuario(valor.getIdCopropietario());
					if (!Validaciones.isNullOrEmpty(mailCc)) {
						cc = mailCc;
					}
				}
				
				TeamComercialDto teamComercialDto = teamComercialServicio.buscarTeamComercial(valor.getIdClienteLegado().toString());
				if (!Validaciones.isObjectNull(teamComercialDto) && !Validaciones.isNullOrEmpty(teamComercialDto.getUserAnalistaCobranzaDatos())) {
					String mailCc = mailServicio.obtenerMailUsuario(teamComercialDto.getUserAnalistaCobranzaDatos());
					if (!Validaciones.isNullOrEmpty(mailCc)) {
						cc = ((!Validaciones.isNullOrEmpty(cc)) ? cc + ";" : "") + mailCc;
					}
				}
				
				String subject = " - Registro AVC tomado por otro usuario - " + mailServicio.armarAsuntoValor(valor);
				subject = Utilidad.reemplazarMensajes(Mensajes.ASUNTO_ALTA_REGISTRO_AVC_RECHAZADA, subject);
				StringBuffer body = new StringBuffer();
				body.append(mailServicio.armarLineaCuerpoValor(valor));
				body.append("<br>");
				body.append("Usuario que tomó el registro AVC: " + userSesion.getNombreCompleto());
								
				Mail mail = new Mail(null, cc, subject, body);
				// Fin preparación e-mail
				
				registroModelo = registroAVCDao.buscarRegistroAVC(valorDto.getIdRegistroAvc());
				tareaServicio.finalizarTarea(TipoTareaEnum.REV_ALTA_V_AVC, registroModelo.getWorkFlow().getIdWorkflow(), userSesion.getIdUsuario(), mail);
			}
			
			if (registroModelo == null) registroModelo = registroAVCDao.buscarRegistroAVC(valorDto.getIdRegistroAvc());
			
			//Crear tarea en la bandeja de entrada y enviar mail. 
			//La tarea se asocia al registro AVC
			TareaDto tarea = new TareaDto();
			tarea.setIdRegistroAVC(registroModelo.getIdRegistroAvc());
			tarea.setIdWorkflow(registroModelo.getWorkFlow().getIdWorkflow());
			tarea.setTipoTarea(TipoTareaEnum.CONF_ALTA_V_AVC);
			tarea.setFechaCreacion(new Date());
			tarea.setUsuarioCreacion(userSesion.getIdUsuario());
			tarea.setPerfilAsignacion(TipoPerfilEnum.ADMIN_VALOR.descripcion());
			tarea.setGestionaSobre(getTipoTareaGestionaPorIdTipoValor(registroModelo.getTipoValor().getIdTipoValor()));
			tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
			
			String asunto = " - " + mailServicio.armarAsuntoValor(valor);
			asunto = "- " + Utilidad.reemplazarMensajes(Mensajes.ASUNTO_ALTA_VALOR_PENDIENTE_CONFIRMAR, asunto);
			tarea.setAsuntoMail(asunto);
			tarea.setCuerpoMail(mailServicio.armarLineaCuerpoValor(valor));
			
			tarea.setEnviarMail(true);
			tareaServicio.crearTareaRegistroAVC(tarea);
			
			registroModelo.setFechaDerivacion(new Date());
			registroModelo.setAnalistaDerivacion(userSesion.getIdUsuario());
			actualizarRegistroAvc(registroModelo);
		} catch (ParseException | PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	@Override
	public ShvAvcRegistroAvcValor buscarRegistroAVCValor(String idRegistro) throws NegocioExcepcion {
		try {
			return registroAVCDao.buscarRegistroAvcValorPorIdRegistroAvc(idRegistro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
			
		}
	}

	public void conciliarInterdepositoSinBoleta(InterdepositoDto dto) throws NegocioExcepcion{
		try {
			ShvAvcRegistroAvc registroAvc = registroAVCDao.buscarRegistroAVC(String.valueOf(dto.getIdRegistro().longValue()));
		
			ShvWfWorkflow wf = workflowRegistrosAVC.establecerRegistroPendienteConciliadoSinBoleta(registroAvc.getWorkFlow(), " ", dto.getUsuarioModificacion());
			registroAvc.setWorkFlow(wf);
			
			registroAVCDao.modificar(registroAvc);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	public void conciliarTransferenciaSinBoleta(ShvAvcRegistroAvc registroAvc, String usuarioModificacion) throws NegocioExcepcion{
		try {
			ShvWfWorkflow wf = workflowRegistrosAVC.establecerRegistroPendienteConciliadoSinBoleta(registroAvc.getWorkFlow(), " ",usuarioModificacion);
			registroAvc.setWorkFlow(wf);
			
			registroAVCDao.modificar(registroAvc);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	public void guardarRelacionConValor(Long idRegistro, ShvValValor valor) throws NegocioExcepcion{
		try{
			ShvAvcRegistroAvc registro = registroAVCDao.buscarRegistroAVC(String.valueOf(idRegistro));
			ShvAvcRegistroAvcValorPK pk = new ShvAvcRegistroAvcValorPK();
			pk.setValor(valor);
			pk.setRegistroAvc(registro);
			ShvAvcRegistroAvcValor shvAvcRegistroAvcValor = new ShvAvcRegistroAvcValor();
			shvAvcRegistroAvcValor.setShvAvcRegistroAvcValorPK(pk);
			registroAVCDao.crearShvAvcRegistroAvcValor(shvAvcRegistroAvcValor);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	@Override
	public void procesarInterdepositoValor(InterdepositoDto interdepositoDto,ShvValValor valor) throws NegocioExcepcion {
		conciliarInterdepositoSinBoleta(interdepositoDto);
		guardarRelacionConValor(interdepositoDto.getIdRegistro(), valor);
	}
	
	public ShvAvcRegistroAvc buscarRegistroAVCModelo(String idRegistro) throws NegocioExcepcion {
		try {
			return registroAVCDao.buscarRegistroAVC(idRegistro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	public void actualizarRegistroAvc(ShvAvcRegistroAvc registroModelo) throws NegocioExcepcion {
		try {
			registroAVCDao.modificar(registroModelo);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	@Override
	public List<Estado> listarComboEstadosRegistrosAVC() throws NegocioExcepcion {
		try {
			List<Estado> lista = Estado.listarEstados("AVC");
			return lista;
		} catch (Throwable e){
			throw new NegocioExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ShvParamCuit buscarCuitTablaParametrica(String cuit) throws NegocioExcepcion {
		try {
			List<ShvParamCuit> listaCuit = (List<ShvParamCuit>) genericoDao.listarPorValor(ShvParamCuit.class, "cuit", cuit);
			if(listaCuit.size() == 1){				
				return listaCuit.get(0);
			}else{
				return null;
			}			
			
		} catch (Throwable e){
			throw new NegocioExcepcion(e);
		}
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation=Propagation.REQUIRED)
	public void eliminarTareaAceptarAltaValorAPartirRegistroAvc(ValorDto valorDto, UsuarioSesion usuarioSesion) throws NegocioExcepcion {
		try {
			ShvAvcRegistroAvc registroModelo = registroAVCDao.buscarRegistroAVC(valorDto.getIdRegistroAvc());
			tareaServicio.finalizarTarea(TipoTareaEnum.REV_ALTA_V_AVC, registroModelo.getWorkFlow().getIdWorkflow(), new Date(), usuarioSesion.getIdUsuario(), null);
			//tareaServicio.eliminarTarea(TipoTareaEnum.REV_ALTA_V_AVC, registroModelo.getWorkFlow().getIdWorkflow(), usuarioSesion.getIdUsuario());
		} catch (Throwable e){
			throw new NegocioExcepcion(e);
		}
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation=Propagation.REQUIRED)
	public void eliminarTareaRevisarAnulacionRegistroAVC(String idRegistro, UsuarioSesion usuarioSesion) throws NegocioExcepcion {
		try {
			ShvAvcRegistroAvc registroAVC = registroAVCDao.buscarRegistroAVC(idRegistro);
			tareaServicio.finalizarTarea(TipoTareaEnum.REV_ANUL_R_AVC, registroAVC.getWorkFlow().getIdWorkflow(), new Date(), usuarioSesion.getIdUsuario(), null);
			//tareaServicio.eliminarTarea(TipoTareaEnum.REV_ANUL_R_AVC, registroAVC.getWorkFlow().getIdWorkflow(), usuarioSesion.getIdUsuario());
		} catch (Throwable e){
			throw new NegocioExcepcion(e);
		}
	}
	
	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/

	public IRegistroAVCDao getRegistroAVCDao() {
		return registroAVCDao;
	}

	public void setRegistroAVCDao(IRegistroAVCDao registroAVCDao) {
		this.registroAVCDao = registroAVCDao;
	}

	public RegistroAVCDepositoMapeador getDepositoMapeador() {
		return depositoMapeador;
	}

	public void setDepositoMapeador(RegistroAVCDepositoMapeador depositoMapeador) {
		this.depositoMapeador = depositoMapeador;
	}

	public RegistroAVCInterdepositoMapeador getInterdepositoMapeador() {
		return interdepositoMapeador;
	}

	public void setInterdepositoMapeador(RegistroAVCInterdepositoMapeador interdepositoMapeador) {
		this.interdepositoMapeador = interdepositoMapeador;
	}

	public IWorkflowRegistrosAVC getWorkflowRegistrosAVC() {
		return workflowRegistrosAVC;
	}

	public void setWorkflowRegistrosAVC(IWorkflowRegistrosAVC workflowRegistrosAVC) {
		this.workflowRegistrosAVC = workflowRegistrosAVC;
	}

	public RegistroAVCTransferenciaMapeador getTransferenciaMapeador() {
		return transferenciaMapeador;
	}

	public void setTransferenciaMapeador(RegistroAVCTransferenciaMapeador transferenciaMapeador) {
		this.transferenciaMapeador = transferenciaMapeador;
	}

	public IDocumentoAdjuntoDao getDocumentoAdjuntoDao() {
		return documentoAdjuntoDao;
	}

	public void setDocumentoAdjuntoDao(IDocumentoAdjuntoDao documentoAdjuntoDao) {
		this.documentoAdjuntoDao = documentoAdjuntoDao;
	}

	public IValorServicio getValorServicio() {
		return valorServicio;
	}

	public void setValorServicio(IValorServicio valorServicio) {
		this.valorServicio = valorServicio;
	}

	public IBancoDao getBancoDao() {
		return bancoDao;
	}

	public void setBancoDao(IBancoDao bancoDao) {
		this.bancoDao = bancoDao;
	}

	public IOrganismoDao getOrganismoDao() {
		return organismoDao;
	}

	public void setOrganismoDao(IOrganismoDao organismoDao) {
		this.organismoDao = organismoDao;
	}

	public IValorPorReversionServicio getValorPorReversionServicio() {
		return valorPorReversionServicio;
	}

	public void setValorPorReversionServicio(
			IValorPorReversionServicio valorPorReversionServicio) {
		this.valorPorReversionServicio = valorPorReversionServicio;
	}

	public IValorDao getValorDao() {
		return valorDao;
	}

	public void setValorDao(IValorDao valorDao) {
		this.valorDao = valorDao;
	}

	public ILdapServicio getLdapServicio() {
		return ldapServicio;
	}

	public void setLdapServicio(ILdapServicio ldapServicio) {
		this.ldapServicio = ldapServicio;
	}

	public MailServicio getMailServicio() {
		return mailServicio;
	}

	public void setMailServicio(MailServicio mailServicio) {
		this.mailServicio = mailServicio;
	}

	public ITareaServicio getTareaServicio() {
		return tareaServicio;
	}

	public void setTareaServicio(ITareaServicio tareaServicio) {
		this.tareaServicio = tareaServicio;
	}

	public MapeadorResultadoBusqueda getResultadoBusquedaReversionSinConciliarMapeador() {
		return resultadoBusquedaReversionSinConciliarMapeador;
	}

	public void setResultadoBusquedaReversionSinConciliarMapeador(
			MapeadorResultadoBusqueda resultadoBusquedaReversionSinConciliarMapeador) {
		this.resultadoBusquedaReversionSinConciliarMapeador = resultadoBusquedaReversionSinConciliarMapeador;
	}

	public MapeadorResultadoBusqueda getResultadoBusquedaDepositoSinConciliarMapeador() {
		return resultadoBusquedaDepositoSinConciliarMapeador;
	}

	public void setResultadoBusquedaDepositoSinConciliarMapeador(
			MapeadorResultadoBusqueda resultadoBusquedaDepositoSinConciliarMapeador) {
		this.resultadoBusquedaDepositoSinConciliarMapeador = resultadoBusquedaDepositoSinConciliarMapeador;
	}

	public MapeadorResultadoBusqueda getResultadoBusquedaInterdepositoSinConciliarMapeador() {
		return resultadoBusquedaInterdepositoSinConciliarMapeador;
	}

	public void setResultadoBusquedaInterdepositoSinConciliarMapeador(
			MapeadorResultadoBusqueda resultadoBusquedaInterdepositoSinConciliarMapeador) {
		this.resultadoBusquedaInterdepositoSinConciliarMapeador = resultadoBusquedaInterdepositoSinConciliarMapeador;
	}

	public MapeadorResultadoBusqueda getResultadoBusquedaTransferenciaSinConciliarMapeador() {
		return resultadoBusquedaTransferenciaSinConciliarMapeador;
	}

	public void setResultadoBusquedaTransferenciaSinConciliarMapeador(
			MapeadorResultadoBusqueda resultadoBusquedaTransferenciaSinConciliarMapeador) {
		this.resultadoBusquedaTransferenciaSinConciliarMapeador = resultadoBusquedaTransferenciaSinConciliarMapeador;
	}

	public IParametroServicio getParametroServicio() {
		return parametroServicio;
	}

	public void setParametroServicio(IParametroServicio parametroServicio) {
		this.parametroServicio = parametroServicio;
	}

	public ITareaDao getTareaDao() {
		return tareaDao;
	}

	public void setTareaDao(ITareaDao tareaDao) {
		this.tareaDao = tareaDao;
	}

	public ITeamComercialServicio getTeamComercialServicio() {
		return teamComercialServicio;
	}

	public void setTeamComercialServicio(ITeamComercialServicio teamComercialServicio) {
		this.teamComercialServicio = teamComercialServicio;
	}
}
