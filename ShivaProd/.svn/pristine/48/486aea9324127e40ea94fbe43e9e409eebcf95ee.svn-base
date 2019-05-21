package ar.com.telecom.shiva.negocio.servicios.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.MensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.batch.GenerarSalidaDetalleAplicacionesManualesBatchRunner;
import ar.com.telecom.shiva.negocio.servicios.IGenerarSalidaDetalleAplicacionesManualesServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteAutomatizacionConfirmacionAplicacionManual;
import ar.com.telecom.shiva.persistencia.dao.IGenerarSalidaDetalleAplicacionesManualesDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvArcArchivo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobAplicacionManualBatchDetalle;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteAutomatizacionConfirmacionAplicacionManualFiltro;

public class GenerarSalidaDetalleAplicacionesManualesServicioImpl implements IGenerarSalidaDetalleAplicacionesManualesServicio {
	
	@Autowired private IGenerarSalidaDetalleAplicacionesManualesDao generarSalidaDetalleAplicacionesManualesDao;
	@Autowired private IParametroServicio parametroServicio;
	@Autowired private ILdapServicio ldapServicio;
	@Autowired private MailServicio mailServicio;
	
	/**
	 * @param fechaHasta 
	 * 
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class, NegocioExcepcion.class}, propagation=Propagation.REQUIRED)
	public void procesarTareaGeneracionSalidaDetalleAplicacionesManuales(String fechaHasta) throws NegocioExcepcion {
		
		try {
			
			Traza.auditoria(getClass(), "Comienzo del metodo procesarTareaGeneracionSalidaDetalleAplicacionesManuales");
			
			String sistemasString = parametroServicio.getValorTexto(Constantes.LISTA_EMPRESAS_BATCH_AUTOMATIZACION_CONFIRMACION_APLICACION_MANUAL);
			List<SistemaEnum> sistemas = SistemaEnum.obtenerListaDeSistemas(sistemasString);
			
			//Logica para el armado de un archivo por empresa
			for(SistemaEnum sistema : sistemas) {
				
				StringBuffer cuerpoMail = new StringBuffer();
				Long cantidadRegistros = 0L; 
				
				//Armado de filtro
				VistaSoporteAutomatizacionConfirmacionAplicacionManualFiltro filtro = new VistaSoporteAutomatizacionConfirmacionAplicacionManualFiltro(sistema.name());
				
				/* Busco todos los datos necesarios para armar el reporte de los cobros pendientes de confirmacion aplicacion manual.
				 * estos datos deberan de ser de cobros en estado PENDIENTE DE CONFIRMACION APLICACION MANUAL
				 * y que no esten en la tabla SHV_COB_APLIC_MANUAL_BATCH_DET dado que no se deben informar los cobros que ya se informaron
				 */
				List<VistaSoporteAutomatizacionConfirmacionAplicacionManual> reporteCobrosPendienteConfirmacionAplicacionManual = generarSalidaDetalleAplicacionesManualesDao.buscarCobrosPendientesConfirmacionParaInformar(filtro);
				
				Traza.auditoria(getClass(), "~");
				Traza.auditoria(getClass(), "~ Para el sistema: " + sistema + " se obtuvieron " + reporteCobrosPendienteConfirmacionAplicacionManual.size() + " tareas pendientes de aprobacion.");
				Traza.auditoria(getClass(), "~");
				
				if (Validaciones.isCollectionNotEmpty(reporteCobrosPendienteConfirmacionAplicacionManual)) {
					
					List<StringBuffer> listaTxtDetalleAplicacionesManuales = new ArrayList<StringBuffer>();
					BigDecimal importeTotal = new BigDecimal(0);
					
					for (VistaSoporteAutomatizacionConfirmacionAplicacionManual resultado : reporteCobrosPendienteConfirmacionAplicacionManual) {
						
						if (Constantes.MONEDA_DOL.equals(resultado.getMoneda())){
							resultado.calcularImporteEnPesos();
						}
						
						listaTxtDetalleAplicacionesManuales.add(crearDetalleAplicacionesManualesStringBuffer(resultado));
						cantidadRegistros++;
						
						if(!Validaciones.isObjectNull(resultado.getMontoImputarEnPesos())){
							
							importeTotal = importeTotal.add(resultado.getMontoImputarEnPesos());
							
						}
						
					}
					
					//Crear pie de archivo
					listaTxtDetalleAplicacionesManuales.add(crearPieAplicacionesManuales(cantidadRegistros,importeTotal,fechaHasta));
					
					//Creo el archivo
					String nombreDeArchivo=generoNombreArchivoDetalleAplicacionesManuales(sistema, fechaHasta);
					crearArchivoDetalleAplicacionesManuales(listaTxtDetalleAplicacionesManuales, nombreDeArchivo);
					
					cuerpoMail.append("Se genero el archivo: " + nombreDeArchivo + "<br>");
					cuerpoMail.append("Cantidad de tareas de Detalle Enviados: " + cantidadRegistros + "<br>");
					cuerpoMail.append("Importe Total de tareas: " + Utilidad.formatCurrency(importeTotal,true,true) + "<br>");
					
					grabarTareasEnTablasDeSalida(nombreDeArchivo, cantidadRegistros, importeTotal,reporteCobrosPendienteConfirmacionAplicacionManual, sistema);
					
				} else {
					cuerpoMail.append("No existieron movimientos para el dia: " + fechaHasta);
				}
				
				enviarMailConDetalleDeProcesamiento(cuerpoMail, sistema);
				
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
			

	}

	/**
	 * Formatea el detalle del Reporte de Cobros Pendientes de confirmacion aplicacion ManualValores Por Empresa	
	 * @param valor
	 * @return
	 * @throws NegocioExcepcion
	 */
	public StringBuffer crearDetalleAplicacionesManualesStringBuffer(VistaSoporteAutomatizacionConfirmacionAplicacionManual tarea) throws NegocioExcepcion {
		
		String referenciaDelValor="";
		StringBuffer detalle = new StringBuffer();
		Traza.auditoria(getClass(), "Se esta procesando la transaccion :" + tarea.getIdTransaccionShivaDeCobro());
		
		detalle.append(Constantes.TIPO_REGISTRO_SALIDA_DETALLE_APLICACIONES_MANUALES_CUERPO + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(tarea.getTipoOperacion()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(tarea.getCuit()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(tarea.getPoseeAdjunto()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(tarea.getMoneda()) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(tarea.getMontoImputarEnMonedaOrigen(), false, true)) + Constantes.SEPARADOR_PIPE);
//		detalle.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(tarea.getTipoCambio(), false, false, 5)) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(Validaciones.isObjectNull(tarea.getTipoCambio()) ? "" : Utilidad.formatCurrencyBDAgregandoDecimales(tarea.getTipoCambio(), 5)).replace(",", "|").replace(".", ",").replace("|", ".") + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatCurrency(tarea.getMontoImputarEnPesos(), false, true)) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(tarea.getIdTransaccionShivaDeCobro()) + Constantes.SEPARADOR_PIPE);
		if (tarea.getTipoOperacion().equals(Constantes.TIPO_OPERACION_DESCOBRO)){
			detalle.append(Utilidad.generarSalidaConValorOVacio(tarea.getIdOperacionDescobro()) + Constantes.SEPARADOR_PIPE);
		}
		detalle.append(Utilidad.generarSalidaConValorOVacio(Utilidad.formatDateDDMMAAAAconBarra(tarea.getFechaRealDePago())) + Constantes.SEPARADOR_PIPE);
		detalle.append(Utilidad.generarSalidaConValorOVacio(tarea.getTipoDeCredito()) + Constantes.SEPARADOR_PIPE);
		
		if (tarea.getSistemaOrigenMedioPago().equals(Constantes.MEDIO_PAGO_SHIVA)){
			
			referenciaDelValor = tarea.getReferenciaDelValor().replace(Constantes.SEPARADOR_PIPE, "-");
		}
		
		if (tarea.getTipoDeCredito().equals(Constantes.MEDIO_PAGO_NOTA_CREDITO)){
			
			referenciaDelValor = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.detalle.aplicacion.manual.descripcion.nota.credito"), 
					tarea.getSistemaOrigenMedioPago(),tarea.getReferenciaMedioDePago());
			
		}
		
		if (tarea.getTipoDeCredito().equals(Constantes.MEDIO_PAGO_PAGO_CUENTA)){
			
			referenciaDelValor = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.detalle.aplicacion.manual.descripcion.pago.cuenta"), 
					tarea.getReferenciaMedioDePago());
			
		}
		
		detalle.append(Utilidad.generarSalidaConValorOVacio(referenciaDelValor));
		
		if (tarea.getTipoOperacion().equals(Constantes.TIPO_OPERACION_DESCOBRO)){
			detalle.append(Constantes.SEPARADOR_PIPE);
			detalle.append(Utilidad.generarSalidaConValorOVacio(tarea.getListaOperacionesExternas()));
		}
		
		return detalle;	
		
	}
	
	public String generoNombreArchivoDetalleAplicacionesManuales(SistemaEnum sistema, String fechaHasta) throws NegocioExcepcion {
		
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		
		Date fechaDelDia = null;
		try {

			fechaDelDia = formatoDelTexto.parse(fechaHasta);

		} catch (ParseException e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
																		                                                            
		String nombreDeArchivo = Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("procesoBatch.automatizacionTareaConfirmacionAplicacionManual.nombre.archivo"),
				"SHIVA",
				"Salida",
				sistema.getDescripcionCorta(),
				Utilidad.formatDateAAAAMMDD(fechaDelDia),
				"csv");
		
		return nombreDeArchivo;
	}

	/**
	 * Generacion del Archivo de Reporte de Salida Por Empresa
	 *Autor: Fernando Formento
	 *		 U562163
	 * */
	public void crearArchivoDetalleAplicacionesManuales(List<StringBuffer> listaArchivoDetalleAplicacionesManuales, String nombreDeArchivo) throws NegocioExcepcion {
		
		try {
						
			ControlArchivo.escribirArchivos(
					listaArchivoDetalleAplicacionesManuales,
					Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.automatizacionConfirmacionManual.salida"),
					nombreDeArchivo,
					""
			);
			
			Traza.auditoria(getClass(), "Se creo el siguiente archivo: " + nombreDeArchivo);
			Traza.auditoria(GenerarSalidaDetalleAplicacionesManualesBatchRunner.class,"Se ha generado el archivo con " + listaArchivoDetalleAplicacionesManuales.size() + " tareas");
							
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	private StringBuffer crearPieAplicacionesManuales(Long cantidadtareas, BigDecimal importeTotal, String fechaHasta) {
		
		StringBuffer pie = new StringBuffer();
		Traza.auditoria(getClass(), "Se genera el pie de archivo");
		
		pie.append(Constantes.TIPO_REGISTRO_SALIDA_DETALLE_APLICACIONES_MANUALES_PIE + Constantes.SEPARADOR_PIPE);
		pie.append(fechaHasta + Constantes.SEPARADOR_PIPE);
		pie.append(cantidadtareas + Constantes.SEPARADOR_PIPE);
		pie.append(Utilidad.formatCurrency(importeTotal, false, true));

		return pie;
		
	}		
	
	/**
	 * 
	 * @param cuerpoMail
	 * @param sistema
	 * @throws NegocioExcepcion
	 */
	private void enviarMailConDetalleDeProcesamiento(StringBuffer cuerpoMail, SistemaEnum sistema) throws NegocioExcepcion{
		try{
			
			Traza.auditoria(getClass(), "~ Comienza la lógica de envío de mail con detalle de procesamiento...");
			
			// Destinatarios 'para'
			//
			
			TipoPerfilEnum perfilBusqueda = TipoPerfilEnum.REFERENTE_OPERACIONES_EXTERNAS;
			
			if (!SistemaEnum.CABLEVISION.equals(sistema) && !SistemaEnum.NEXTEL.equals(sistema) && !SistemaEnum.FIBERTEL.equals(sistema)) {
				perfilBusqueda = TipoPerfilEnum.REFERENTE_CAJA;
			}
			
			StringBuffer destinatariosPara = new StringBuffer();
			Collection<UsuarioLdapDto> usuariosLdap = ldapServicio.buscarUsuariosPorPerfilEnMemoria(perfilBusqueda.descripcion());
			for (UsuarioLdapDto usuario: usuariosLdap) {
				if (!Validaciones.isNullOrEmpty(usuario.getMail())) {
					destinatariosPara.append(usuario.getMail());
					destinatariosPara.append(";");
				}
			}
			
			// Destinatarios 'en copia'
			//
			String listaDestinatarioCorreoEnComun = parametroServicio.getValorTexto(Constantes.LISTA_GRUPO_MAIL_COMUN_AUTOMATIZACION_CONFIRMACION_APLICACION_MANUAL);
			String listaDestinatarioCorreoPorSistema = parametroServicio.getValorTexto(Constantes.LISTA_GRUPO_MAIL_BATCH_AUTOMATIZACION_CONFIRMACION_APLICACION_MANUAL);
			String destinatariosCC = listaDestinatarioCorreoEnComun + ";" + obtenerGrupoDeMails(listaDestinatarioCorreoPorSistema, sistema.getDescripcionCorta());
			
			String asunto = Propiedades.MENSAJES_PROPIEDADES.getString("mail.automatizacion.confirmacion.aplicacion.manual");
			asunto += " " + sistema.getDescripcion();
			
			// Si tengo destinatarios, armo mail y lo envío
			
			if (!Validaciones.isNullOrEmpty(destinatariosPara.toString()) && !Validaciones.isNullOrEmpty(destinatariosCC)) {
				
				Mail mailDetalleProcesamiento = new Mail(destinatariosPara.toString(), 
									 destinatariosCC.replace(",", ";") + ";", 
									 asunto,
									 cuerpoMail);
				
				mailServicio.sendMail(mailDetalleProcesamiento);
				
			} else {
				Traza.auditoria(getClass(), "~ Error: no existen usuarios con perfil '" 
											+ TipoPerfilEnum.REFERENTE_OPERACIONES_EXTERNAS.descripcion() 
											+ "' ni destinatarios configurados para la empresa '" 
											+ sistema.getDescripcion() 
											+ " (" + sistema.name() + ")"
											+ "' para poder enviar el detalle del procesamiento.");
			}
			
			Traza.auditoria(getClass(), "~ Fin de envío de mail.");
			Traza.auditoria(getClass(), "~");
			
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * @param grupoDeMails
	 * @param sistema
	 * @return
	 */
	private String obtenerGrupoDeMails(String grupoDeMails, String sistema) {
			
		String grupoDeMail = "";
		HashMap<String, String> listaGrupoDeMails = new HashMap<String, String>();
		String[] grupoDeMailsString = grupoDeMails.split(";");
		
		for (String grupoDeMailString : grupoDeMailsString) {
			String[] grupoDeMailPorEmpresa = grupoDeMailString.split("-");
			listaGrupoDeMails.put(grupoDeMailPorEmpresa[0], grupoDeMailPorEmpresa[1]);
		}
		
		if (listaGrupoDeMails.containsKey(sistema)){
			grupoDeMail = listaGrupoDeMails.get(sistema).toString();
		}
		
		return grupoDeMail;
	}
	
	private void grabarTareasEnTablasDeSalida(String nombreDeArchivo, Long cantidadRegistros, BigDecimal importeTotal,List<VistaSoporteAutomatizacionConfirmacionAplicacionManual> tareasPendienteConfirmacionAplicacionManual, SistemaEnum sistema) throws PersistenciaExcepcion {
		
		ShvArcArchivo archivo = new ShvArcArchivo();
		archivo.setNombreArchivo(nombreDeArchivo);
		archivo.setProceso(Constantes.PROCESO_BATCH_GENERAR_SALIDA_DETALLE_APLICACIONES_MANUALES);
		archivo.setCantidadRegistros(cantidadRegistros);
		archivo.setImporteTotal(importeTotal);
		archivo.setFechaEnvio(new Date());
		
		Set<ShvCobAplicacionManualBatchDetalle> detalleAplicacionManual = new HashSet<ShvCobAplicacionManualBatchDetalle>();
		
		for (VistaSoporteAutomatizacionConfirmacionAplicacionManual tarea: tareasPendienteConfirmacionAplicacionManual) {
			
			detalleAplicacionManual.add(grabarDetalleEnTabla(tarea, archivo, sistema));
			
		}
		
		archivo.setRegistros(detalleAplicacionManual);
		
		generarSalidaDetalleAplicacionesManualesDao.insertarArcArchivo(archivo);
	}
	
	public ShvCobAplicacionManualBatchDetalle grabarDetalleEnTabla(VistaSoporteAutomatizacionConfirmacionAplicacionManual tarea, ShvArcArchivo archivo, SistemaEnum sistema) throws PersistenciaExcepcion {
		
		String referenciaDelValor = "";
		ShvCobAplicacionManualBatchDetalle detalleAplicacionManual = new ShvCobAplicacionManualBatchDetalle();
		
		detalleAplicacionManual.setIdTratamientoDiferencia(tarea.getIdTratamientoDiferencia());
		detalleAplicacionManual.setEstadoTarea(MensajeEstadoEnum.PENDIENTE);
		detalleAplicacionManual.setTipoOperacion(tarea.getTipoOperacion());
		detalleAplicacionManual.setCuit(tarea.getCuit());
		detalleAplicacionManual.setPoseeAdjunto(tarea.getPoseeAdjunto());
		detalleAplicacionManual.setMoneda(tarea.getMoneda());
		detalleAplicacionManual.setMontoImputarMonedaOrigen(tarea.getMontoImputarEnMonedaOrigen());
		detalleAplicacionManual.setTipoCambio(tarea.getTipoCambio());
		detalleAplicacionManual.setMontoImputarPesos(tarea.getMontoImputarEnPesos());
		if (!Validaciones.isObjectNull(tarea.getIdTransaccion())) {
			detalleAplicacionManual.setIdTransaccion(Long.parseLong(tarea.getIdTransaccion()));
		}
		detalleAplicacionManual.setIdTransaccionCobro(tarea.getIdTransaccionShivaDeCobro());
		detalleAplicacionManual.setFechaValorMedioPago(tarea.getFechaRealDePago());
		detalleAplicacionManual.setTipoMedioPago(tarea.getTipoDeCredito());
		detalleAplicacionManual.setIdTransaccionDescobro(tarea.getIdTransaccionDescobro());
		detalleAplicacionManual.setSistema(sistema);
		detalleAplicacionManual.setArchivo(archivo);
		
		if (!Validaciones.isObjectNull(tarea.getIdCobro())) {
			detalleAplicacionManual.setIdCobro(Long.parseLong(tarea.getIdCobro()));
			detalleAplicacionManual.setIdOperacion(Long.parseLong(tarea.getIdOperacion()));
		} 
		
		if (!Validaciones.isObjectNull(tarea.getIdDescobro())) {
			detalleAplicacionManual.setIdDescobro(Long.parseLong(tarea.getIdDescobro()));
			detalleAplicacionManual.setIdOperacionDescobro(Long.parseLong(tarea.getIdOperacionDescobro()));
		}
		
		if (tarea.getSistemaOrigenMedioPago().equals(Constantes.MEDIO_PAGO_SHIVA)){
			
			referenciaDelValor = tarea.getReferenciaDelValor().replace(Constantes.SEPARADOR_PIPE, "-");
		}
		
		if (tarea.getTipoDeCredito().equals(Constantes.MEDIO_PAGO_NOTA_CREDITO)){
			
			referenciaDelValor = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.detalle.aplicacion.manual.descripcion.nota.credito"), 
					tarea.getSistemaOrigenMedioPago(),tarea.getReferenciaMedioDePago());
			
		}
		
		if (tarea.getTipoDeCredito().equals(Constantes.MEDIO_PAGO_PAGO_CUENTA)){
			
			referenciaDelValor = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("reporte.detalle.aplicacion.manual.descripcion.pago.cuenta"), 
					tarea.getReferenciaMedioDePago());
			
		}
		
		detalleAplicacionManual.setReferenciaValor(referenciaDelValor);
		detalleAplicacionManual.setOperacionesExternas(tarea.getListaOperacionesExternas());
		
		return detalleAplicacionManual;
		
	}
	
	public IGenerarSalidaDetalleAplicacionesManualesDao getGenerarSalidaDetalleAplicacionesManualesDao() {
		return generarSalidaDetalleAplicacionesManualesDao;
	}

	public void setGenerarSalidaDetalleAplicacionesManualesDao(
			IGenerarSalidaDetalleAplicacionesManualesDao generarSalidaDetalleAplicacionesManualesDao) {
		this.generarSalidaDetalleAplicacionesManualesDao = generarSalidaDetalleAplicacionesManualesDao;
	}

}
