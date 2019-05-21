package ar.com.telecom.shiva.base.ws.servidor.servicios.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.ConstantesRecepcionNovedadesSAP;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaGestionaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.LdapExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mail.Adjunto;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.ws.servidor.datos.RecepcionNovedadesDocumentosSAP.RecepcionNovedadesDocumentosSAPEntrada;
import ar.com.telecom.shiva.base.ws.servidor.datos.RecepcionNovedadesDocumentosSAP.RecepcionNovedadesDocumentosSAPSalida;
import ar.com.telecom.shiva.base.ws.servidor.datos.RecepcionNovedadesDocumentosSAP.ResultadoProcesamiento;
import ar.com.telecom.shiva.base.ws.servidor.servicios.IRecepcionNovedadesDocumentosSAPWSServicio;
import ar.com.telecom.shiva.base.ws.servidor.servicios.validacion.IRecepcionNovedadesDocumentosSAPValidacionServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.ITareaServicio;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaDatosImputacion;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.IDocumentoCapDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDocumentoCap;
import ar.com.telecom.shiva.presentacion.bean.dto.TareaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;


public class RecepcionNovedadesDocumentosSAPWSServicioImpl implements IRecepcionNovedadesDocumentosSAPWSServicio{

	@Autowired
	IRecepcionNovedadesDocumentosSAPValidacionServicio novedadesSapValidacionServicio;
	
	@Autowired
	IDocumentoCapDao documentoCapDao;
	
	@Autowired
	ICobroDao cobroDao;
	
	@Autowired
	ICobroOnlineServicio cobroOnlineServicio;
	
	@Autowired
	ITareaServicio tareaServicio;
	
	@Autowired
	ILdapServicio ldapServicio;
	
	@Autowired
	private MailServicio mailServicio;
	
	@Autowired
	private IParametroServicio parametroServicio;
	
	@Override
	public RecepcionNovedadesDocumentosSAPSalida consultarRecepcionNovedades(
			RecepcionNovedadesDocumentosSAPEntrada entrada) throws ParseException, NegocioExcepcion {
		
		RecepcionNovedadesDocumentosSAPSalida salida = new RecepcionNovedadesDocumentosSAPSalida();
		ResultadoProcesamiento resultadoProcesamiento = new ResultadoProcesamiento();
		//se utiliza para saber si el error es por existencia del documento cap setResultadoImputacion("OK"/"NOK");
		boolean esErrorExistenciaDocCap = false;
		
		try {
			
			validarDatosEntradaNovedadesSap(entrada);
			
			ShvCobMedioPagoDocumentoCap documentoCap = documentoCapDao.buscarPorIdK2andIdSociedadandAnioFiscal(entrada.getDocumentoOriginal().getIdDocumento(), entrada.getDocumentoOriginal().getIdSociedad(), entrada.getDocumentoOriginal().getAnioFiscal());
			ShvCobMedioPagoDocumentoCap contraDocumentoCap = documentoCapDao.buscarPorIdContraK2andIdSociedadContraDocandAnioFiscalContraDoc(entrada.getContraDocumentoCancelatorio().getIdDocumento(), entrada.getContraDocumentoCancelatorio().getIdSociedad(), entrada.getContraDocumentoCancelatorio().getAnioFiscal());
			
			if(Validaciones.isObjectNull(documentoCap)) {
				esErrorExistenciaDocCap = true;
				throw new ValidacionExcepcion(ConstantesRecepcionNovedadesSAP.NOK_WS_RECEP_NOV_DOC_INEXISTENTE, 
						Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.documentoInexistente"),"");
			}
			//Busca si el Id del contraK2 ya fue utilizado previamente
			//Si el "contradocumento" existe es porque ya fue usado el ID
			if(contraDocumentoCap != null){
					esErrorExistenciaDocCap = true;
					throw new ValidacionExcepcion(ConstantesRecepcionNovedadesSAP.NOK_WS_RECEP_NOV_DOC_INEXISTENTE, 
							Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.contradocumentoExistente"),"");
			}
				
			actualizarDocumentoCap(documentoCap, entrada);
			
			enviarMailyGenerarTarea(documentoCap.getIdCobro(),entrada.getDocumentoOriginal().getIdDocumento(),entrada.getContraDocumentoCancelatorio().getIdDocumento(), entrada.getUsuario());
			
			resultadoProcesamiento.setResultadoImputacion("OK");
			resultadoProcesamiento.setCodigoError("");
			resultadoProcesamiento.setDescripcionError("");
			
			salida.setResultado(resultadoProcesamiento);
			
			return salida;
			
		} catch (ValidacionExcepcion e) {
			if(esErrorExistenciaDocCap){
				resultadoProcesamiento.setResultadoImputacion("NOK");
			}else{
				resultadoProcesamiento.setResultadoImputacion("ERR");
			}
			resultadoProcesamiento.setCodigoError(e.getCodigoLeyenda());
			resultadoProcesamiento.setDescripcionError(e.getLeyenda());
			
			salida.setResultado(resultadoProcesamiento);
			return salida;

		} catch (PersistenciaExcepcion e) {
			
			resultadoProcesamiento.setResultadoImputacion("ERR");
			resultadoProcesamiento.setCodigoError(ConstantesRecepcionNovedadesSAP.ERR_WS_RECEP_NOV_ERROR_INESPERADO);
			resultadoProcesamiento.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("error.recepcionNovedadesSAP.errorInesperado"));
			
			salida.setResultado(resultadoProcesamiento);
			return salida;
		}
		
	}
	
	
	
	public void validarDatosEntradaNovedadesSap(RecepcionNovedadesDocumentosSAPEntrada entrada) throws ValidacionExcepcion {
		
		
		novedadesSapValidacionServicio.validacionTipoNovedad(entrada.getTipoNovedad());
		
		novedadesSapValidacionServicio.validacionUsuario(entrada.getUsuario());
		
		novedadesSapValidacionServicio.validacionFechaCreacionContradocumento(entrada.getFechaCreacionContradocumento());
		
		novedadesSapValidacionServicio.validacionDocumentoSap(entrada.getDocumentoOriginal(), true);
		
		novedadesSapValidacionServicio.validacionDocumentoSap(entrada.getContraDocumentoCancelatorio(), false);
		
	}
	
	@SuppressWarnings("deprecation")
	public void actualizarDocumentoCap (ShvCobMedioPagoDocumentoCap documentoCap, RecepcionNovedadesDocumentosSAPEntrada entrada) throws ParseException, PersistenciaExcepcion {
		
		documentoCap.setIdContradocumento(entrada.getContraDocumentoCancelatorio().getIdDocumento());
		documentoCap.setIdSociedadContradocumento(entrada.getContraDocumentoCancelatorio().getIdSociedad());
		documentoCap.setAnioFiscalContradocumento(entrada.getContraDocumentoCancelatorio().getAnioFiscal());
		documentoCap.setFechaCreacionContradocumento(Utilidad.deserializeAndFormatDate(entrada.getFechaCreacionContradocumento().toString()));
		documentoCap.setUsuarioCreacionContradocumento(entrada.getUsuario());
		//usuario creacion
		
		documentoCapDao.actualizarDocumentoCap(documentoCap);
	}
	
//	Destinatario: Analista 
//	En copia: Copropietario
//	Asunto: Nueva Tarea - Reversión de Compensación Pendiente: id operación/id acta de compensación (K$)/id acta compensación rechazada (-K$)  - Nro de cliente/Razón Social 
//	Cuerpo: listado de clientes incluidos en el cobro (Nro de cliente/Razón Social)
//	Adjunto: archivo excel con la exportación del cobro

	
	public void enviarMailyGenerarTarea(Long idCobro, String idDocumentoCap, String idContradocumentoCap, String usuarioCreacion) throws PersistenciaExcepcion, NegocioExcepcion {
		
			
			ShvCobCobro cobro = cobroDao.buscarCobro(idCobro);
			
			// Busca todos los datos necesario para la generacion del MAIL
			List<ResultadoBusquedaDatosImputacion> listaDatos = (List<ResultadoBusquedaDatosImputacion>) cobroDao.buscarDatosImputacion(cobro.getOperacion().getIdOperacion());
			
			String analista = listaDatos.get(0).getIdAnalista();
			String copropietario = listaDatos.get(0).getCopropietario();
			String conCopia = Utilidad.agregarAnalistaTeamComercialACopropietario(listaDatos, copropietario);
			
			String cuerpo = "Listado de clientes incluidos en el cobro: <br><br>";
			String asunto = "";
			String nroCliente = listaDatos.get(0).getNumCliente().toString();
			String razonSocial = listaDatos.get(0).getRazonSocial();
			String clienteYRazonSocial = nroCliente + " / " + razonSocial;
			Long idOperacion = cobro.getOperacion().getIdOperacion();
			BigDecimal importe = listaDatos.get(0).getImporte();
			
			for (ResultadoBusquedaDatosImputacion datos : listaDatos){
				cuerpo+= " Cliente: " + datos.getNumCliente() + " / " + datos.getRazonSocial() + " <br> ";
			}
			
			crearTareaPendiente(cobro, idDocumentoCap, idContradocumentoCap, usuarioCreacion, analista,importe,TipoTareaEnum.DES_COMP_PEND, nroCliente, razonSocial,cuerpo, false);
			asunto = "Nueva Tarea - Reversión de Compensación Pendiente: " + idOperacion + " - " + clienteYRazonSocial;
			
			//Envia el mail del resultado de la imputación
			enviarMail(analista, conCopia, asunto, cuerpo, cobro.getIdCobro());
			
	}
	
	public void crearTareaPendiente(ShvCobCobro cobro, String idDocumentoCap, String idContradocumentoCap, String usuarioCreacion, String analista, BigDecimal importe,
			TipoTareaEnum tipoTarea, String nroCliente, String razonSocial, String cuerpo, Boolean enviarMail) throws NegocioExcepcion {
		
		TareaDto tarea = new TareaDto();
		
		String usuarioSAP = parametroServicio.getValorTexto(Constantes.USUARIO_SAP);
		
		tarea.setPerfilAsignacion(TipoPerfilEnum.ANALISTA.name());
		
		tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		tarea.setIdWorkflow(cobro.getWorkflow().getIdWorkflow());
		
		tarea.setUsuarioCreacion(usuarioSAP);
		tarea.setTipoTarea(tipoTarea);
		tarea.setFechaCreacion(new Date());
		tarea.setUsuarioAsignacion(analista);
		tarea.setPerfilAsignacion(TipoPerfilEnum.ANALISTA_COBRANZA.descripcion());
		tarea.setGestionaSobre(TipoTareaGestionaEnum.COBRO);
		
		tarea.setReferencia( cobro.getOperacion().getIdOperacion()+"/"+idDocumentoCap+"/"+idContradocumentoCap);
		
		tarea.setMonedaImporte("$");
		tarea.setImporte(Utilidad.formatCurrency(importe, false, true));
		
		tarea.setIdCobro(cobro.getIdCobro());
		tarea.setNroCliente(nroCliente);
		tarea.setRazonSocial(razonSocial);
		tarea.setEnviarMail(enviarMail);
		
		tarea.setIdOperacion(cobro.getOperacion().getIdOperacion());
		
		tareaServicio.crearTareaReversionCompensacionPendiente(tarea);
	}
	
	public void enviarMail(String analista, String conCopia, String asunto, String cuerpo, Long idCobro) throws NegocioExcepcion {
		
		String para =""; 
		String cc ="";
		
		ByteArrayOutputStream outputStream = null;
		
		try {
			outputStream = new ByteArrayOutputStream();
			HSSFWorkbook workbook = cobroOnlineServicio.exportarCobro(idCobro);
			workbook.write(outputStream);
		
			List<Adjunto> listaAdjuntos = new ArrayList<Adjunto>();
			listaAdjuntos.add(new Adjunto(outputStream.toByteArray(), asunto, "xls"));

			UsuarioLdapDto usuarioLdapAnalista;
			if(!Validaciones.isNullOrEmpty(analista)){
				usuarioLdapAnalista = ldapServicio.buscarUsuarioPorUidEnMemoria(analista);
				if(!Validaciones.isNullOrEmpty(usuarioLdapAnalista.getMail())){
					para = usuarioLdapAnalista.getMail() + ";";
				}
			}
			

			if(!Validaciones.isNullOrEmpty(conCopia)){
				for(String usuario : conCopia.split(";")){
					UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(usuario);
					if(!Validaciones.isNullOrEmpty(usuarioLdap.getMail())){
						cc += usuarioLdap.getMail() + ";";
					}
				}
			}
			
			Mail mail = new Mail(para,cc,asunto,new StringBuffer(cuerpo));
			mail.setAdjuntos(listaAdjuntos);
			mailServicio.sendMail(mail);
			
		}catch (LdapExcepcion | IOException | PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
}
