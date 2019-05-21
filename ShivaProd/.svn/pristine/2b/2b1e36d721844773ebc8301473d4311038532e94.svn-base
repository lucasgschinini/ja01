package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.OrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaGestionaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ConcurrenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.dto.ValorPorReversionDto;
import ar.com.telecom.shiva.negocio.mapeos.ValorPorReversionMapeador;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.ITareaServicio;
import ar.com.telecom.shiva.negocio.servicios.ITeamComercialServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorPorReversionServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowValoresPorReversion;
import ar.com.telecom.shiva.persistencia.dao.IAcuerdoDao;
import ar.com.telecom.shiva.persistencia.dao.IBancoDao;
import ar.com.telecom.shiva.persistencia.dao.IGenericoDao;
import ar.com.telecom.shiva.persistencia.dao.IOrganismoDao;
import ar.com.telecom.shiva.persistencia.dao.IReversionValorDao;
import ar.com.telecom.shiva.persistencia.dao.ITareaDao;
import ar.com.telecom.shiva.persistencia.dao.IValorDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvValReversionValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValorPorReversion;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowEstado;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.TareaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.TeamComercialDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;

public class ValorPorReversionServicioImpl extends Servicio implements IValorPorReversionServicio {

	@Autowired 
	IGenericoDao genericoDao;
	
	@Autowired 
	IReversionValorDao reversionValorDao;

	@Autowired
	private IParametroServicio parametroServicio;

	@Autowired
	IValorDao valorDao;
	
	@Autowired
	IValorServicio valorServicio;
	
	@Autowired
	ITareaServicio tareaServicio;
	
	@Autowired
	IWorkflowValoresPorReversion workflowValorPorReversion;

	@Autowired
	IAcuerdoDao acuerdoDao;
	
	@Autowired
	IBancoDao bancoDao;
	
	@Autowired
	private ITareaDao tareaDao;
	
	@Autowired
	IOrganismoDao organismoDao;
	@Autowired
	ValorPorReversionMapeador valorPorReversionMapeador;
	
	@Autowired 
	ILdapServicio ldapServicio;
	
	@Autowired
	ITeamComercialServicio teamComercialServicio;
	
	@Autowired
	MailServicio mailServicio;

	@Override
	public DTO buscar(Integer id) throws NegocioExcepcion {
		return null;
	}

	@Override
	public Collection<DTO> listar(Filtro filtro) throws NegocioExcepcion {
		return null;
	}

	@Override
	public Long crear(DTO dto) throws NegocioExcepcion {
		ValorDto valorDto = (ValorDto) dto;
		try {
			//Mapear
			ShvValValorPorReversion valor = new ShvValValorPorReversion();
			
			if(!valorDto.getImporte().startsWith("-")) {
				valor.setImporte(Utilidad.stringToBigDecimal(valorDto.getImporte()));
			}else {
				throw new NegocioExcepcion("Importe con valor negativo");
			}
			
			if(!valorDto.getSaldoDisponible().startsWith("-")) {
				valor.setSaldoDisponible(Utilidad.stringToBigDecimal(valorDto.getSaldoDisponible()));
			}else {
				throw new NegocioExcepcion("Saldo disponible con valor negativo");
			}
				
			if(!Validaciones.isObjectNull(TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(valorDto.getIdTipoValor())))) {
				valor.setIdTipoValor(Long.valueOf(valorDto.getIdTipoValor()));
			}else {
				throw new NegocioExcepcion("El Id Tipo de Valor es inexistente");
			}
	    	
			if(!"".equals(valorDto.getIdAcuerdo())){
				if(!Validaciones.isObjectNull(acuerdoDao.buscarAcuerdo(valorDto.getIdAcuerdo()))) {
					valor.setIdAcuerdo(Long.valueOf(valorDto.getIdAcuerdo()));
				}else {
					throw new NegocioExcepcion("El Id Acuerdo es inexistente");
				}
			}
	
			
	    	String datosOriginales = Utilidad.datosOriginales(valorDto, getListaValorPorReversion());
			datosOriginales +="[Alta generada por Reverso de Operaciones No Shiva en Cobrador] (Importe: "+valor.getImporte()+") (Saldo reversado: "+valor.getSaldoDisponible()+")";
	    	//HDY: creo workflow 
			ShvWfWorkflow workflow = workflowValorPorReversion.crearWorkflow(datosOriginales, parametroServicio.getValorTexto(Constantes.USUARIO_BATCH));
			valor.setWorkflow(workflow);
	    	
			
	    	if(!Validaciones.isNullOrEmpty(valorDto.getBancoOrigen())){
	    		if(!Validaciones.isObjectNull(bancoDao.buscarBanco(valorDto.getBancoOrigen()))) {
	    			valor.setIdBancoOrigen(valorDto.getBancoOrigen());
	    		}else {
					throw new NegocioExcepcion("El Id de Banco Origen es inexistente");
				}
	    	}
	    	if(!Validaciones.isNullOrEmpty(valorDto.getNumeroCheque())){
	    		valor.setNumeroCheque(Long.valueOf(valorDto.getNumeroCheque()));
	    	}
	    	try {
	    		if(!Validaciones.isNullOrEmpty(valorDto.getFechaDeposito())){
	    			valor.setFechaDeposito(Utilidad.deserializeAndFormatDate(valorDto.getFechaDeposito(),Utilidad.DATE_FORMAT_PICKER));
	    		}
	    		if(!Validaciones.isNullOrEmpty(valorDto.getFechaVencimiento())){
	    			valor.setFechaVencimiento(Utilidad.deserializeAndFormatDate(valorDto.getFechaVencimiento(),Utilidad.DATE_FORMAT_PICKER));
	    		}
	    	} catch (ParseException e) {
	    		throw new NegocioExcepcion(e.getMessage(),e);
	    	}
	    	if(!Validaciones.isNullOrEmpty(valorDto.getNumeroBoleta())){
	    		valor.setNumeroBoleta(Long.valueOf(valorDto.getNumeroBoleta()));
	    	}
	    	if(!Validaciones.isNullOrEmpty(valorDto.getNumeroReferencia())){
	    		valor.setNumeroReferencia(Long.valueOf(valorDto.getNumeroReferencia()));
	    	}
	    	if(!Validaciones.isNullOrEmpty(valorDto.getNumeroInterdepositoCdif())){
	    		valor.setCodigoInterdeposito(Long.valueOf(valorDto.getNumeroInterdepositoCdif()));
	    	}
	    	
	    	if(!Validaciones.isNullOrEmpty(valorDto.getCodOrganismo())){
	    		if(!Validaciones.isObjectNull(organismoDao.buscarOrganismo(valorDto.getCodOrganismo()))) {
	    			valor.setCodigoOrganismo(valorDto.getCodOrganismo());
	    		}else {
					throw new NegocioExcepcion("El Codigo de Organismo es inexistente");
				}
	    	}
	    	
			genericoDao.insertar(ShvValValorPorReversion.class,valor);
			return valor.getIdValorPorReversion();
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		} catch (ShivaExcepcion shve) {
			throw new NegocioExcepcion(shve.getMessage(), shve);
		}
		
	}

	@Override
	public void modificar(DTO dto) throws NegocioExcepcion {
	}

	@Override
	public void anular(DTO dto) throws NegocioExcepcion {
	}

	@Override
	public String getDatosModificados(DTO dto) throws NegocioExcepcion {
		return null;
	}

	@Override
	public void verificarConcurrencia(Serializable id, Long timeStamp) throws NegocioExcepcion {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ValorPorReversionDto> listarPendientesYRechazados() throws NegocioExcepcion {
		List<ShvValValorPorReversion> listaModelo = new ArrayList<ShvValValorPorReversion>();
		List<ValorPorReversionDto> listaDto = new ArrayList<ValorPorReversionDto>();
		List<String> listaCondiciones = new ArrayList<String>();
		listaCondiciones.add(Estado.REV_PENDIENTE.name());
		listaCondiciones.add(Estado.REV_PENDIENTE_CONFIRMAR.name());
		listaCondiciones.add(Estado.REV_RECHAZADA.name());
		try {
			listaModelo = (List<ShvValValorPorReversion>) genericoDao.listarCondicionalWfEstado(ShvValValorPorReversion.class, listaCondiciones);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		for (ShvValValorPorReversion valorModelo : listaModelo) {
			ValorPorReversionDto valorPorReversionDto = (ValorPorReversionDto) defaultMapeador.map(valorModelo);
			listaDto.add(valorPorReversionDto);
		}
		return listaDto;
	}


	@SuppressWarnings("unchecked")
	public ValorDto buscarValorPorReversion(String idValorPorReversion) throws NegocioExcepcion{
		 try {
			 List<ShvValValorPorReversion> lista =(List<ShvValValorPorReversion>) genericoDao.listarPorValor(ShvValValorPorReversion.class, "idValorPorReversion", idValorPorReversion);
			 if (!lista.isEmpty()) {
				 return valorPorReversionMapeador.mapValorDto(lista.get(0));
			 } else {
				 return null;
			 }
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, rollbackFor = { Exception.class,WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public void generarTarea(ValorDto valorDto, UsuarioSesion userSesion) throws NegocioExcepcion {
		valorDto.setGenerarValorNoDispoblePorDefecto(true);
		ShvValValor valor = null;
		
		try {
			ShvValValorPorReversion valorPorReversionModelo = ((List<ShvValValorPorReversion>) genericoDao.listarPorValor(ShvValValorPorReversion.class, "idValorPorReversion", valorDto.getValorPorReversion())).get(0);
			if(Validaciones.isObjectNull(valorDto.getIdValor())){
				//Alta de valor por primera vez
				if(!Validaciones.isNullOrEmpty(valorDto.getFechaInterdeposito())){
					valorDto.setFechaInterdeposito(Utilidad.formatDateAndTime(Utilidad.parseDatePickerString(valorDto.getFechaInterdeposito())));
				}
				valorDto.formarNumeroValor();
				verificarConcurrencia(String.valueOf(valorPorReversionModelo.getIdValorPorReversion()), valorDto.getTimeStamp());
				valor = crearValor(valorDto);
				avanzarWorkflowPendienteConfirmar(valorDto,valorPorReversionModelo);
			}else{
				//Alta de valor luego de haberse rechazado
				valorServicio.administrarModificar(valorDto, userSesion);
				avanzarWorkflowReConfirmar(valorDto, valorPorReversionModelo);
				valor = valorServicio.buscarValValorPorIdValor(valorDto.getIdValor().intValue());
				
				// Preparo e-mail
				String cc = null;
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
				
				String subject = " - Valor por reversión tomado por otro usuario - " + mailServicio.armarAsuntoValor(valor);
				subject = Utilidad.reemplazarMensajes(Mensajes.ASUNTO_ALTA_REVERSION_RECHAZADA, subject);
				StringBuffer body = new StringBuffer();
				body.append(mailServicio.armarLineaCuerpoValor(valor));
				body.append("<br>");
				body.append("Usuario que tomó el valor por reversión: " + userSesion.getNombreCompleto());
								
				Mail mail = new Mail(null, cc, subject, body);
				// Fin preparación e-mail
								
				tareaServicio.finalizarTarea(TipoTareaEnum.REV_ALTA_V_REV, valorPorReversionModelo.getWorkflow().getIdWorkflow(), userSesion.getIdUsuario(), mail);
			
				
				
			}
			//Crear tarea en la bandeja de entrada y enviar mail. 
			//La tarea se asocia al registro AVC
			TareaDto tarea = new TareaDto();
			tarea.setIdValorPorReversion(valorPorReversionModelo.getIdValorPorReversion());
			tarea.setIdWorkflow(valorPorReversionModelo.getWorkflow().getIdWorkflow());
			tarea.setTipoTarea(TipoTareaEnum.CONF_ALTA_V_REV);
			tarea.setFechaCreacion(new Date());
			tarea.setUsuarioCreacion(userSesion.getIdUsuario());
			tarea.setPerfilAsignacion(TipoPerfilEnum.ADMIN_VALOR.descripcion());
			tarea.setGestionaSobre(getTipoTareaGestionaPorIdTipoValor(valorPorReversionModelo.getIdTipoValor()));

			String asunto = " - " + mailServicio.armarAsuntoValor(valor);
			asunto = "- " + Utilidad.reemplazarMensajes(Mensajes.ASUNTO_ALTA_VALOR_PENDIENTE_CONFIRMAR, asunto);
			tarea.setAsuntoMail(asunto);
			String cuerpo = mailServicio.armarLineaCuerpoValor(valor);					
			tarea.setCuerpoMail(cuerpo);
//----------------------------------------------------------------------------------------------------			
			tareaServicio.crearTareaValorPorReversion(tarea);
			
		} catch (ParseException | PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	
	
	@SuppressWarnings("unchecked")
	private ShvValValor crearValor(ValorDto valorDto) throws NegocioExcepcion {
		valorDto.setMigracion(true);
		try {
			ShvValValor valorModelo = valorServicio.crearValor(valorDto);
			ShvValReversionValor reversionValorModelo = new ShvValReversionValor();
			reversionValorModelo.getShvValReversionValorPK().setValor(valorModelo);
			reversionValorModelo.getShvValReversionValorPK().setValorPorReversion(((List<ShvValValorPorReversion>) genericoDao.listarPorValor(ShvValValorPorReversion.class, "idValorPorReversion", valorDto.getValorPorReversion())).get(0));
			reversionValorDao.crearReversionValor(reversionValorModelo);
			return valorModelo;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	private TipoTareaGestionaEnum getTipoTareaGestionaPorIdTipoValor(Long idTipoValor) {
		switch (TipoValorEnum.getEnumByIdTipoValor(idTipoValor)) {
		    case INTERDEPÓSITO:
		    	return TipoTareaGestionaEnum.VALOR_INTERDEPOSITO;
		    case TRANSFERENCIA:
		    	return TipoTareaGestionaEnum.VALOR_TRANSF;
		    case CHEQUE:
		    	return TipoTareaGestionaEnum.VALOR_CHEQUE;
		    case CHEQUE_DIFERIDO:
		    	return TipoTareaGestionaEnum.VALOR_CHEQUE_DIFERIDO;
		    case EFECTIVO:
		    	return TipoTareaGestionaEnum.VALOR_EFECTIVO;
			default:
				return null;
		}
	}
	
	@Override
	public ValorDto buscarValorCreadoAPartirReversion(String valorPorReversion) throws NegocioExcepcion {
		try {
			ShvValReversionValor reversionValor = reversionValorDao.buscarReversionValor(valorPorReversion);
			if (!Validaciones.isObjectNull(reversionValor)){
				ValorDto valorDto = valorServicio.buscarValorModificacion(reversionValor.getShvValReversionValorPK().getValor());
				valorDto.setEstadoRegistroAvc(reversionValor.getShvValReversionValorPK().getValorPorReversion().getWorkflow().getEstado().descripcion());
				valorDto.setTimeStamp(String.valueOf(reversionValor.getShvValReversionValorPK().getValor().getWorkFlow().getFechaUltimaModificacion().getTime()));
				return valorDto;
			} else {
				return null;
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
			
		}
	}
	
	public ShvValReversionValor buscarReversionValor(String idValorPorReversion) throws NegocioExcepcion {
		try {
			return reversionValorDao.buscarReversionValor(idValorPorReversion);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void confirmarTareaAltaValor(ValorDto valorDto) throws NegocioExcepcion {
		
		//Busco los Modelos
		ShvValReversionValor reversionValor = buscarReversionValor(valorDto.getValorPorReversion());
		ShvValValorPorReversion valorPorReversion = reversionValor.getShvValReversionValorPK().getValorPorReversion();
		ShvValValor valorModelo = reversionValor.getShvValReversionValorPK().getValor();
		
		//Armo el Mail
		String destinatarioPara = null; // Queda en nulo porque esta direccion corresponde al generador de la tarea, se completa al finalizar la tarea
		String destinatarioCc   = null;
		/**
		 * @author u573005, sprint 3, correccion correos
		 */		
//		String asunto = " - " + mailServicio.armarAsuntoValor(valorModelo);
//		asunto = Utilidad.reemplazarMensajes(Mensajes.ASUNTO_VALOR_DISPONIBLE, asunto);
//		StringBuffer cuerpo = new StringBuffer();
//		cuerpo.append(mailServicio.armarLineaCuerpoValor(valorModelo));
		
		//Modificacion asunto y cuerpo Req. 67475 Sprint 07
		StringBuffer asunto = mailServicio.armarAsuntoValorStringBuffer(valorModelo);
		StringBuffer cuerpo = mailServicio.armarCuerpoValorStringBuffer(valorModelo);
//----------------------------------------------------------------------------------------		
		if (!Validaciones.isNullOrEmpty(valorModelo.getIdAnalista())) {
			String mailPara = mailServicio.obtenerMailUsuario(valorModelo.getIdAnalista());
			if (!Validaciones.isNullOrEmpty(mailPara)){
				destinatarioPara = mailPara;
			}				
		}
		
		if (!Validaciones.isNullOrEmpty(valorModelo.getIdCopropietario())) {
			String mailCc = mailServicio.obtenerMailUsuario(valorModelo.getIdCopropietario());
			if (!Validaciones.isNullOrEmpty(mailCc)) {
				destinatarioCc = mailCc;
			}
		}
		
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
		
		Mail mail = new Mail(destinatarioPara, destinatarioCc, null, asunto.toString(), cuerpo);
		
		verificarConcurrencia(String.valueOf(valorPorReversion.getIdValorPorReversion()), valorDto.getTimeStamp());
		
		//Actualizo los workflows
		avanzarWorkflowConfirmar(valorDto, valorPorReversion);
		valorServicio.actualizarWorkflowDeValorADisponible(valorDto, valorDto.getUsuarioModificacion());
		// Finalizo la tarea  
		Date fechaEjecucion = new Date();
		tareaServicio.finalizarTareaCorrecto(
				TipoTareaEnum.CONF_ALTA_V_REV, 
				valorPorReversion.getWorkflow().getIdWorkflow(), 
				fechaEjecucion, 
				valorDto.getUsuarioModificacion(), 
				mail);
		
	}

	/**
	 * Metodo para rechazar el alta de un valor a partir de reversión.
	 * Ademas finaliza la tarea asociada.
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void rechazarTareaAltaValor(ValorDto valorDto) throws NegocioExcepcion {
		try {
			//Busco los Modelos
			ShvValReversionValor reversionValor = buscarReversionValor(valorDto.getValorPorReversion());
			ShvValValorPorReversion valorPorReversion = reversionValor.getShvValReversionValorPK().getValorPorReversion();
			ShvValValor valorModelo = reversionValor.getShvValReversionValorPK().getValor();
				
			// Agrego observación de rechazo a las observaciones del valor
			valorModelo.setObservaciones(((valorModelo.getObservaciones() != null) ? valorModelo.getObservaciones().trim() : "") + "\n" +
					((valorDto.getObservacionesConfirmarAlta() != null) ? valorDto.getObservacionesConfirmarAlta().trim() : ""));
					
			valorDao.actualizarValor(valorModelo);
									
			verificarConcurrencia(String.valueOf(valorPorReversion.getIdValorPorReversion()), valorDto.getTimeStamp());
			
			//Actualizo los workflows
			avanzarWorkflowRechazar(valorDto, valorPorReversion);//avanza el workflow
			
			// Genero la tarea para el Analista
			TareaDto tarea = new TareaDto();
			tarea.setIdValorPorReversion(valorPorReversion.getIdValorPorReversion());
			tarea.setIdWorkflow(valorPorReversion.getWorkflow().getIdWorkflow());
			tarea.setTipoTarea(TipoTareaEnum.REV_ALTA_V_REV);
			tarea.setFechaCreacion(new Date());
			tarea.setUsuarioCreacion(valorDto.getUsuarioModificacion());
			tarea.setPerfilAsignacion(TipoPerfilEnum.ANALISTA.descripcion());
			tarea.setUsuarioAsignacion(tareaDao.buscarTareaPendiente(TipoTareaEnum.CONF_ALTA_V_REV, valorPorReversion.getWorkflow().getIdWorkflow()).getUsuarioCreacion());
			tarea.setGestionaSobre(getTipoTareaGestionaPorIdTipoValor(valorPorReversion.getIdTipoValor()));
			tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
			tarea.setEnviarMail(false);
			tareaServicio.crearTareaValorPorReversion(tarea);
			
				
			/**
			 * @author u573005, sprint 3, se corrigio el armado del mail
			 */
			String destinatarioPara = null;
			String destinatarioCc   = null;
			
			String asunto = " - " + mailServicio.armarAsuntoValor(valorModelo);
			asunto = Utilidad.reemplazarMensajes(Mensajes.ASUNTO_ALTA_REVERSION_RECHAZADA, asunto);
			StringBuffer cuerpo = new StringBuffer();
			cuerpo.append(mailServicio.armarLineaCuerpoValor(valorModelo));
			cuerpo.append(" | Usuario responsable del rechazo: " + ldapServicio.buscarUsuarioPorUidEnMemoria(valorDto.getUsuarioModificacion()).getNombreCompleto());
			
			if (!Validaciones.isNullOrEmpty(valorDto.getObservacionesConfirmarAlta())) {
				cuerpo.append("<br>" + Utilidad.reemplazarMensajes(Mensajes.CAMPO_OBSERVACIONES,valorDto.getObservacionesConfirmarAlta().trim()));
			}

			String analista = valorModelo.getIdAnalista();
			if (!Validaciones.isNullOrEmpty(analista)) {
				String mailPara = mailServicio.obtenerMailUsuario(analista);
				if (!Validaciones.isNullOrEmpty(mailPara)) {
					destinatarioPara = mailPara;
				}
			}
			
			String copropietario = valorModelo.getIdCopropietario();
			if (!Validaciones.isNullOrEmpty(copropietario)) {
				
				String mailCc = mailServicio.obtenerMailUsuario(copropietario);
				if (!Validaciones.isNullOrEmpty(mailCc)) {
					destinatarioCc = mailCc;
				}
			}
			
			TeamComercialDto teamComercialDto = teamComercialServicio.buscarTeamComercial(valorModelo.getIdClienteLegado().toString());
			if (!Validaciones.isObjectNull(teamComercialDto) && !Validaciones.isNullOrEmpty(teamComercialDto.getAnalistaCobranzaDatos())) {
				String mailCc = mailServicio.obtenerMailUsuario(teamComercialDto.getUserAnalistaCobranzaDatos());
				if(!Validaciones.isNullOrEmpty(mailCc)){
					destinatarioCc = ((!Validaciones.isNullOrEmpty(destinatarioCc)) ? destinatarioCc + ";" : "") + mailCc;	
				}				
			}

			Mail mail = new Mail(destinatarioPara, destinatarioCc, null, asunto, cuerpo);
			
			// Finalizo la tarea  
			Date fechaEjecucion = new Date();
			
			tareaServicio.finalizarTarea(
					TipoTareaEnum.CONF_ALTA_V_REV, 
					valorPorReversion.getWorkflow().getIdWorkflow(), 
					fechaEjecucion, 
					valorDto.getUsuarioModificacion(), 
					mail);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage());
		}
	}
	
	
	/**
	 * busca el Modelo y llama al metodo avanzarWorkflowConfirmar local
	 * @param valorDto
	 * @throws NegocioExcepcion
	 */
	@SuppressWarnings("unchecked")
	public void avanzarWorkflowConfirmar(ValorDto valorDto) throws NegocioExcepcion {
		try {
			List<ShvValValorPorReversion> valorPorReversion = (List<ShvValValorPorReversion>) genericoDao.listarPorValor(ShvValValorPorReversion.class, "idValorPorReversion", valorDto.getValorPorReversion());
			if (valorPorReversion.isEmpty()){
				avanzarWorkflowConfirmar(valorDto, valorPorReversion.get(0));
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	private void avanzarWorkflowConfirmar(ValorDto valorDto, ShvValValorPorReversion valorPorReversion) throws NegocioExcepcion {
		ShvWfWorkflow workflowActualizado = workflowValorPorReversion.confirmarAltaValorPorReversion(valorPorReversion.getWorkflow(), "", valorDto.getUsuarioModificacion());
		valorPorReversion.setWorkflow(workflowActualizado);
		try {
			genericoDao.actualizar(ShvValValorPorReversion.class, valorPorReversion, "ID_VALOR_POR_REVERSION="+valorPorReversion.getIdValorPorReversion());
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	/**
	 * busca el Modelo y llama al metodo avanzarWorkflowRechazar local
	 * @param valorDto
	 * @throws NegocioExcepcion
	 */
	@SuppressWarnings("unchecked")
	public void avanzarWorkflowRechazar(ValorDto valorDto) throws NegocioExcepcion {
		try {
			List<ShvValValorPorReversion> valorPorReversion = (List<ShvValValorPorReversion>) genericoDao.listarPorValor(ShvValValorPorReversion.class, "idValorPorReversion", valorDto.getValorPorReversion());
			if (valorPorReversion.isEmpty()){
				avanzarWorkflowConfirmar(valorDto, valorPorReversion.get(0));
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	private void avanzarWorkflowRechazar(ValorDto valorDto, ShvValValorPorReversion valorPorReversion) throws NegocioExcepcion {
		ShvWfWorkflow workflowActualizado = workflowValorPorReversion.rechazarAltaValorPorReversion(valorPorReversion.getWorkflow(), "", valorDto.getUsuarioModificacion());
		valorPorReversion.setWorkflow(workflowActualizado);
		try {
			genericoDao.actualizar(ShvValValorPorReversion.class, valorPorReversion, "ID_VALOR_POR_REVERSION="+valorPorReversion.getIdValorPorReversion());
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * busca el Modelo y llama al metodo avanzarWorkflowConfirmar local
	 * @param valorDto
	 * @throws NegocioExcepcion
	 */
	@SuppressWarnings("unchecked")
	public void avanzarWorkflowReConfirmar(ValorDto valorDto) throws NegocioExcepcion {
		try {
			List<ShvValValorPorReversion> valorPorReversion = (List<ShvValValorPorReversion>) genericoDao.listarPorValor(ShvValValorPorReversion.class, "idValorPorReversion", valorDto.getValorPorReversion());
			if (valorPorReversion.isEmpty()){
				avanzarWorkflowReConfirmar(valorDto, valorPorReversion.get(0));
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	private void avanzarWorkflowReConfirmar(ValorDto valorDto, ShvValValorPorReversion valorPorReversion) throws NegocioExcepcion {
		ShvWfWorkflow workflowActualizado = workflowValorPorReversion.solicitarReconfirmacionAltaValorPorReversion(valorPorReversion.getWorkflow(), "", valorDto.getUsuarioModificacion());
		valorPorReversion.setWorkflow(workflowActualizado);
		try {
			genericoDao.actualizar(ShvValValorPorReversion.class, valorPorReversion, "ID_VALOR_POR_REVERSION="+valorPorReversion.getIdValorPorReversion());
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * busca el Modelo y llama al metodo avanzarWorkflowConfirmar local
	 * @param valorDto
	 * @throws NegocioExcepcion
	 */
	@SuppressWarnings("unchecked")
	public void avanzarWorkflowPendienteConfirmar(ValorDto valorDto) throws NegocioExcepcion {
		try {
			List<ShvValValorPorReversion> valorPorReversion = (List<ShvValValorPorReversion>) genericoDao.listarPorValor(ShvValValorPorReversion.class, "idValorPorReversion", valorDto.getValorPorReversion());
			if (valorPorReversion.isEmpty()){
				avanzarWorkflowPendienteConfirmar(valorDto, valorPorReversion.get(0));
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	private void avanzarWorkflowPendienteConfirmar(ValorDto valorDto, ShvValValorPorReversion valorPorReversion) throws NegocioExcepcion {
		ShvWfWorkflow workflowActualizado = workflowValorPorReversion.solicitarConfirmacionAltaValorPorReversion(valorPorReversion.getWorkflow(), "", valorDto.getUsuarioModificacion());
		valorPorReversion.setWorkflow(workflowActualizado);
		try {
			genericoDao.actualizar(ShvValValorPorReversion.class, valorPorReversion, "ID_VALOR_POR_REVERSION="+valorPorReversion.getIdValorPorReversion());
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	@SuppressWarnings("unchecked")
	public void verificarConcurrencia(String idValorPorReversion, String timeStamp) throws NegocioExcepcion {
		try {
			List<ShvValValorPorReversion> listaValorPorReversion = (List<ShvValValorPorReversion>) genericoDao.listarPorValor(ShvValValorPorReversion.class, "idValorPorReversion", idValorPorReversion);
			if (!listaValorPorReversion.isEmpty()) {
				ShvValValorPorReversion valorPorReversion = listaValorPorReversion.get(0);
				ShvWfWorkflow wf = valorPorReversion.getWorkflow();
				
				//Si la ultima modificacion es distinta a la actual
				if (wf.getFechaUltimaModificacion().getTime() > Long.valueOf(timeStamp).longValue()) {
					throw new ConcurrenciaExcepcion();
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * devuelve la lista de campos del valor por reversion
	 * @return
	 */
	private List<String> getListaValorPorReversion() {
		List<String> lista = new ArrayList<String>();
		
		lista.add("idAcuerdo"); 
		lista.add("importe");
		lista.add("saldoDisponible");
		lista.add("numeroCheque");
		lista.add("bancoOrigen");
		lista.add("fechaDeposito");
		lista.add("numeroReferencia");
		lista.add("codigoOrganismo");
		lista.add("numeroBoleta");
		lista.add("codigoInterdeposito");
		
		return lista;
		
	}
	
	public boolean validarValorPorReversion(ValorDto valorDto, Errors errors, String tagError)
			throws NegocioExcepcion {

		Boolean actualizarValor = false;
		Boolean valorNoEsReversion = false;
		
		List<ShvValValor> listaValorBd = valorServicio.validarUnicidadValor(valorDto);

		ShvValValor valorBdMasNuevo = null;
		Long fechaMasNueva = null;
		if(Validaciones.isCollectionNotEmpty(listaValorBd)){
			/**
			 * Obtengo el valor mas nuevo en funcion de la fecha de alta para validar. Si existe y es el mismo, le adiciono saldo proveniente de este archivo/registro
			 * que estoy procesando
			 */
			for(ShvValValor valorBd : listaValorBd){
				if(valorBd != null){
					if(valorBd.getParamOrigen() != null){
						Integer idOrigen = valorBd.getParamOrigen().getIdOrigen();
						if(idOrigen.equals(OrigenEnum.REVERSION.codigo())){
							
							if(valorBdMasNuevo != null){
								Long fechaComparacion = valorBd.getFechaAlta().getTime();
								if(fechaMasNueva < fechaComparacion){
									valorBdMasNuevo = valorBd;
									fechaMasNueva = fechaComparacion;
								}
							}else{
								valorBdMasNuevo = valorBd;
								fechaMasNueva = valorBdMasNuevo.getFechaAlta().getTime();
							}
						} else {
							valorNoEsReversion = true;
						}
					}
				}
			}
			
			if(!valorNoEsReversion){
				if(valorBdMasNuevo  != null){
					if(!valorBdMasNuevo.getWorkFlow().getShvWfWorkflowEstado().isEmpty()){
						BigDecimal saldoDisponibleOriginal = new BigDecimal(0);
						BigDecimal importe = Utilidad.stringToBigDecimal(valorDto.getImporte());
						BigDecimal saldoReversado = Utilidad.stringToBigDecimal(valorDto.getSaldoDisponible());
						
						Set<ShvWfWorkflowEstado> estado = valorBdMasNuevo.getWorkFlow().getShvWfWorkflowEstado();
						
						switch(estado.iterator().next().getEstado()){
							case VAL_DISPONIBLE:
								saldoDisponibleOriginal = saldoDisponibleOriginal.add(valorBdMasNuevo.getSaldoDisponible());
								valorDto.setValorPorReversionActualizar(valorBdMasNuevo);
								actualizarValor = true;
								saldoDisponibleOriginal = saldoDisponibleOriginal.add(saldoReversado);
								
								if(saldoDisponibleOriginal.compareTo(importe) == 1){
									valorDto.setErrorSaldoReversado(true);
									actualizarValor = true;
									errors.rejectValue(tagError,"error.valorPorReversion.saldo.mayorQueImporte");
								}else{
									valorDto.setActualizarSaldoEstado(actualizarValor);
								}
								break;
							case VAL_NO_DISPONIBLE:
								valorDto.setErrorSaldoReversado(true);
								actualizarValor = true;
								errors.rejectValue(tagError,"error.valorPorReversion.estado.valNoDisponible");
							default:
								return true;
						}
						/**
						 * Cuando el estado del valor es usado y suspendido 
						 * se crea el valor por reversion con lo
						 * cual se salta esta condicion						
						 */
					}
				}
			}
		}else{
			actualizarValor = true;
		}
			
		/**
		 * Mando true para que el registro se considere unico
		 */
		return actualizarValor;
	}

	public void procesarValorPorReversion(ValorDto valorDto,
			UsuarioSesion userSesion) throws NegocioExcepcion {
		if(valorDto.isActualizarSaldoEstado()){
			
			ShvValValor valor = valorDto.getValorPorReversionActualizar();
			valor.setSaldoDisponible(valor.getSaldoDisponible().add(Utilidad.stringToBigDecimal(valorDto.getSaldoDisponible())));
			actualizarValor(valor);
			
			valorDto.setUsuarioModificacion(userSesion.getIdUsuario());
			actualizarEstadoValorPorReversion(valorDto);
		}else{
			generarTarea(valorDto, userSesion);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation=Propagation.REQUIRED)
	public void eliminarTareaAceptarAltaValorPorReversion(ValorDto valorDto, UsuarioSesion usuarioSesion) throws NegocioExcepcion {
		try {
			ShvValValorPorReversion valorPorReversionModelo = ((List<ShvValValorPorReversion>) genericoDao.listarPorValor(ShvValValorPorReversion.class, "idValorPorReversion", valorDto.getValorPorReversion())).get(0);
			tareaServicio.finalizarTarea(TipoTareaEnum.REV_ALTA_V_REV, valorPorReversionModelo.getWorkflow().getIdWorkflow(), new Date(), usuarioSesion.getIdUsuario(), null);
			//tareaServicio.eliminarTarea(TipoTareaEnum.REV_ALTA_V_REV, valorPorReversionModelo.getWorkflow().getIdWorkflow(), usuarioSesion.getIdUsuario());
		} catch (Throwable e) {
			throw new NegocioExcepcion(e);
		}
	}

	public void actualizarValor(ShvValValor valor) throws NegocioExcepcion {

		try {

			verificarConcurrencia(String.valueOf(valor.getIdValor()), Long.valueOf(valor.getWorkFlow().getFechaUltimaModificacion().getTime()));
			valorDao.actualizarValor(valor);
				
		}catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}catch (ConcurrenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public void actualizarEstadoValorPorReversion(ValorDto valorDto) throws NegocioExcepcion {

		try {
			if(valorDto.getValorPorReversionActualizar() != null){
								
				ShvValValorPorReversion valorPorReversionModelo = ((List<ShvValValorPorReversion>) genericoDao.listarPorValor(ShvValValorPorReversion.class, "idValorPorReversion", valorDto.getValorPorReversion())).get(0);
				ShvWfWorkflow workflowActualizado = workflowValorPorReversion.actualizarSaldo(valorPorReversionModelo.getWorkflow(), "", valorDto.getUsuarioModificacion());
				valorPorReversionModelo.setWorkflow(workflowActualizado);
			}
		}catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
	}

	public IGenericoDao getGenericoDao() {
		return genericoDao;
	}

	public void setGenericoDao(IGenericoDao genericoDao) {
		this.genericoDao = genericoDao;
	}

	public IValorDao getValorDao() {
		return valorDao;
	}

	public void setValorDao(IValorDao valorDao) {
		this.valorDao = valorDao;
	}
	

	public IParametroServicio getParametroServicio() {
		return parametroServicio;
	}

	public void setParametroServicio(IParametroServicio parametroServicio) {
		this.parametroServicio = parametroServicio;
	}

	public IWorkflowValoresPorReversion getWorkflowValorPorReversion() {
		return workflowValorPorReversion;
	}

	public void setWorkflowValorPorReversion(
			IWorkflowValoresPorReversion workflowValorPorReversion) {
		this.workflowValorPorReversion = workflowValorPorReversion;
	}

	public IReversionValorDao getReversionValorDao() {
		return reversionValorDao;
	}

	public void setReversionValorDao(IReversionValorDao reversionValorDao) {
		this.reversionValorDao = reversionValorDao;
	}

	public IValorServicio getValorServicio() {
		return valorServicio;
	}

	public void setValorServicio(IValorServicio valorServicio) {
		this.valorServicio = valorServicio;
	}

	public ITareaServicio getTareaServicio() {
		return tareaServicio;
	}

	public void setTareaServicio(ITareaServicio tareaServicio) {
		this.tareaServicio = tareaServicio;
	}

	public IAcuerdoDao getAcuerdoDao() {
		return acuerdoDao;
	}

	public void setAcuerdoDao(IAcuerdoDao acuerdoDao) {
		this.acuerdoDao = acuerdoDao;
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

	public ValorPorReversionMapeador getValorPorReversionMapeador() {
		return valorPorReversionMapeador;
	}

	public void setValorPorReversionMapeador(
			ValorPorReversionMapeador valorPorReversionMapeador) {
		this.valorPorReversionMapeador = valorPorReversionMapeador;
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

	public MailServicio getMailServicio() {
		return mailServicio;
	}

	public void setMailServicio(MailServicio mailServicio) {
		this.mailServicio = mailServicio;
	}
	
}