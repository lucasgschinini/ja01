package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoOperacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRegistroEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mail.Adjunto;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.registros.datos.entrada.ArchivoDetalleAplicacionManualEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.AgrupaDetalleAplicacionManualEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.DetalleAplicacionManualEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.DetalleAplicacionManualRta;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IProcesarEntradaDetalleAplicacionesManualesServicio;
import ar.com.telecom.shiva.negocio.servicios.ITareaServicio;
import ar.com.telecom.shiva.negocio.workflow.IWorkflowService;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowDescobros;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaDetalleAplicacionManual;
import ar.com.telecom.shiva.persistencia.bean.ResultadoBusquedaImporteTransaccionesAplicacionManual;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineDao;
import ar.com.telecom.shiva.persistencia.dao.IDescobroDao;
import ar.com.telecom.shiva.persistencia.dao.IGenericoDao;
import ar.com.telecom.shiva.persistencia.dao.IProcesarEntradaDetalleAplicacionesManualesDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvArcArchivo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobAplicacionManualBatchDetalle;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobDescobroCodigoOperacionExternaSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobEdCobroSimplificadoConWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobEdCodigoOperacionExternaSimplificado;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

import com.google.common.collect.Lists;

public class ProcesarEntradaDetalleAplicacionesManualesServicioImpl implements IProcesarEntradaDetalleAplicacionesManualesServicio {
	
	public static final String RTA = "RTA";
	public static final String SIN_KEY = "SIN_KEY";
	
	public static final int CAMPO_TIPO_REG = 0;
	public static final int CAMPO_TIPO_OPER = 1;
	public static final int CAMPO_ID_TRANSACCION = 8;
	public static final int CAMPO_PIE_FECHA = 1;
	public static final int CAMPO_PIE_CANT_REG = 2;
	public static final int CAMPO_PIE_IMPORTE_TOTAL = 3;
	public static final int CAMPO_MONEDA = 4;
	public static final int CAMPO_MONTO_MOMEDA_ORIGEN = 5;
	public static final int CAMPO_TIPO_CAMBIO = 6;
	public static final int CAMPO_MONTO_PESOS = 7;
	private static final String CARPETA_HISTORICO = "historico";
	
	@Autowired
	private IProcesarEntradaDetalleAplicacionesManualesDao procesarEntradaDetalleAplicacionesManualesDao;
	@Autowired
	private ICobroDao cobroDao;
	
	@Autowired
	private ICobroOnlineDao cobroOnlineDao;
	@Autowired private ILdapServicio ldapServicio;
	@Autowired private MailServicio mailServicio;
	@Autowired private IDescobroDao descobroDao;
	
	
	@Autowired
	private IParametroServicio parametroServicio;
	
	@Autowired IWorkflowCobros workflowCobros;
	
	@Autowired IWorkflowDescobros workflowDescobros;
	
	@Autowired IWorkflowService workflowService;
	
	@Autowired ITareaServicio tareaServicio;
	
	@Autowired IGenericoDao genericoDao;

	@Override
	public void procesarArchivosEntrada() throws NegocioExcepcion, ShivaExcepcion, PersistenciaExcepcion, ParseException {
		
			
		File pathOrigen = ControlArchivo.getDirectorio(Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.automatizacionConfirmacionManual.entrada"));
		
		File[] archivosAProcesar = ControlArchivo.listaArchivosDirectorio(pathOrigen.getAbsolutePath()); 
		
		Traza.auditoria(getClass(), "~");
		Traza.auditoria(getClass(), "Se han encontrado " + (archivosAProcesar != null ? (archivosAProcesar.length -1) : 0) + " archivos.");
		Traza.auditoria(getClass(), "~");
		
		String sistemasString = parametroServicio.getValorTexto(Constantes.LISTA_EMPRESAS_BATCH_AUTOMATIZACION_CONFIRMACION_APLICACION_MANUAL);
		List<SistemaEnum> sistemas = SistemaEnum.obtenerListaDeSistemas(sistemasString);
		
		//Recorro todos los archivos existentes en el directorio
		for (File file : archivosAProcesar) {
			
			String nombreArchivo = file.getName();
			StringBuffer error = new StringBuffer();
			ArchivoDetalleAplicacionManualEntrada resultadoProcesamientoArchivo = new ArchivoDetalleAplicacionManualEntrada();
			
			if (Validaciones.esNombreArchivoEntradaApliManual(nombreArchivo)){
				
				if (Validaciones.tieneSistemaAplicacionManualValido(nombreArchivo)){
					
					SistemaEnum sistemaOrigen = SistemaEnum.getEnumByDescripcionCorta(Utilidad.empresaArchivoEntradaAplicacionManual(nombreArchivo));
					
					if (sistemas.contains(sistemaOrigen)){
						
						ShvArcArchivo archivoAux = procesarEntradaDetalleAplicacionesManualesDao.buscarArchivoEntradaPorNombreArchivo(nombreArchivo);
						
						if (Validaciones.isObjectNull(archivoAux)){
							
							
							Traza.auditoria(getClass(), "~");
							Traza.auditoria(getClass(), "Se arranca con el procesamiento del archivo: " + nombreArchivo);
							Traza.auditoria(getClass(), "~");
							
							// Lectura del archivo
							String[] registrosArchivo = ControlArchivo.leerArchivo(file.getAbsolutePath());
							
							if (!Validaciones.isObjectNull(registrosArchivo)){

								resultadoProcesamientoArchivo = procesarArchivo(registrosArchivo, sistemaOrigen,nombreArchivo);
								
								enviarMailConDetalleDeProcesamiento(error, sistemaOrigen, resultadoProcesamientoArchivo, estadoProcesamiento(resultadoProcesamientoArchivo), nombreArchivo); //Ver el tema del estado
								
							} else {
								Traza.auditoria(getClass(), "El siguiente archivo se encuentra vacío: " + nombreArchivo);
								error.append(Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.archivo.vacio"));
								enviarMailArchivoInvalido(error, nombreArchivo);
								
							}
							
							
						} else {
							
							error.append(Utilidad.reemplazarMensajes(
									Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.archivo.procesado"),
									nombreArchivo, Utilidad.formatDatePicker(archivoAux.getFechaRecepcion())));
							
							resultadoProcesamientoArchivo.setArchivoYaProcesado(true);
							enviarMailConDetalleDeProcesamiento(error, sistemaOrigen, resultadoProcesamientoArchivo, TipoResultadoEnum.ERROR, nombreArchivo);
							
							Traza.auditoria(getClass(), "El archivo: " + nombreArchivo + " ya fue procesado anteriormente");
							
						}
						
					} else {
						
						Traza.auditoria(getClass(), "El siguiente archivo no tiene el Sistema configurado: " + nombreArchivo);
						error.append(Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.sistema.no.configurado"),
								Utilidad.empresaArchivoEntradaAplicacionManual(nombreArchivo)));
						
						enviarMailArchivoInvalido(error, nombreArchivo);
						
					}
					
				} else {
					
					Traza.auditoria(getClass(), "El siguiente archivo tiene un Sistema invalido: " + nombreArchivo);
					error.append(Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.sistema.invalido"),
							Utilidad.empresaArchivoEntradaAplicacionManual(nombreArchivo)));
					
					enviarMailArchivoInvalido(error, nombreArchivo);
					
				}
				
			} else {
				
				if (!CARPETA_HISTORICO.equals(nombreArchivo)) {
					
					Traza.auditoria(getClass(), "El siguiente archivo tiene formato invalido: " + nombreArchivo);
					error.append(Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.formatoArchivo"));
					enviarMailArchivoInvalido(error, nombreArchivo);
					
				}
				
			}
			
			if (!CARPETA_HISTORICO.equals(nombreArchivo)){
				
				//Muevo el archivo del path original a "Historico"
				Traza.auditoria(getClass(), "El archivo " + nombreArchivo + " se procesó con éxito. Se moverá a la carpeta historico");
				
				ControlArchivo.moverArchivoACarpetaHistorico(nombreArchivo, pathOrigen.getPath());
				
				Traza.auditoria(getClass(), "Se ha movido el archivo " + nombreArchivo + " a la carpeta historico exitosamente");
				
			}
			
		}
		
	}
	
	/**
	 * Metodo para grabar el archivo procesado en la tabla SHV_ARC_ARCHIVO
	 * @param nombreArchivo
	 * @throws PersistenciaExcepcion
	 */
	private ShvArcArchivo insertarArcArchivo(String nombreArchivo) throws PersistenciaExcepcion {
		
		ShvArcArchivo archivo = new ShvArcArchivo();
		
		archivo.setNombreArchivo(nombreArchivo);
		archivo.setProceso(Constantes.PROCESO_BATCH_PROCESAR_ENTRADA_DETALLE_APLICACIONES_MANUALES);
		archivo.setFechaRecepcion(new Date());
		
		archivo = procesarEntradaDetalleAplicacionesManualesDao.insertarArcArchivo(archivo);
		
		Traza.auditoria(getClass(), "Se ha insertado el archivo " + nombreArchivo + " en la base de datos");
		
		return archivo;
	}
	
	/**
	 * Metodo que se encarga de validar y procesar los registros del archivo
	 * @param registroArchivo
	 * @param sistema
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ArchivoDetalleAplicacionManualEntrada procesarArchivo(String[] registroArchivo, SistemaEnum sistema, String nombreArchivo) throws NegocioExcepcion {
		
		int registroDetalle = 0;
		int registroPie = 0;
		int posicionRTA = 0;
		int registrosConError = 0;
		
		ArchivoDetalleAplicacionManualEntrada archivo = new ArchivoDetalleAplicacionManualEntrada();
		archivo.setSistema(sistema);
		
		for (int i=0; i < registroArchivo.length; i++) {
			
			DetalleAplicacionManualEntrada registro = new DetalleAplicacionManualEntrada();
			
			String[] datos = registroArchivo[i].split("\\|");
			registro.setRegistroArchivo(registroArchivo[i]);
			registro.setNroRegistro(i);
			
			TipoRegistroEnum tipoRegistroEnum = TipoRegistroEnum.getEnumByCodigo(datos[CAMPO_TIPO_REG]);
			
			if (tipoRegistroValido(registro, datos[CAMPO_TIPO_REG])){
				
				if (TipoRegistroEnum.D.equals(tipoRegistroEnum)){
					
					posicionRTA = obtenerPosicionRTA(datos);
					
					if (posicionRTA == 0) {
						
						registro.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.literalRTA.inexistente"));
						Traza.advertencia(this.getClass(), registro.getDescripcionError());
						registrosConError++;
						
					} else if (tieneTipoOperacionValido(registro, datos)){
						
						if (validarConsistenciaDeDatos(registro, datos, posicionRTA, archivo)){
							archivo = setearImporteTotalRegistros(archivo,registro);
						} else {
							Traza.advertencia(this.getClass(), registro.getDescripcionError());
							registrosConError++;
						}
						
					} else {
						Traza.advertencia(this.getClass(), registro.getDescripcionError());
						registrosConError++;
					}
					boolean error = !this.completarDatosImporte(registro, datos);
					if (!archivo.isErrorImporte() && error) {
						archivo.setErrorImporte(error);
					}
					registroDetalle++;
					
				} else {
					
					if (TipoRegistroEnum.P.equals(tipoRegistroEnum)){
						
						registroPie++;
						
					}
				}
				
			} else {
				registroDetalle++;
				registrosConError++;
				Traza.advertencia(this.getClass(), registro.getDescripcionError());
			}
			
			if (!TipoRegistroEnum.P.equals(tipoRegistroEnum)){
				
				agregarDetalleAplicacionManualMap(registro, archivo.getMapa());
				
			}
			
		}
		
		if (tienePieValido(archivo, registroArchivo, registroDetalle, registroPie)){
			
			if (!archivo.isRechazarArchivoTodosRegistrosErroneos() && !archivo.isErrorImporte()) {
				if (archivo.validarImporteTotalConImportePie(archivo)) {
					procesarRegistros(archivo,nombreArchivo);
				} else {
					archivo.setErrorPie(Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.pie.importe.total"),
							Utilidad.formatCurrency(archivo.getImportePie(), true, true),
							Utilidad.formatCurrency(archivo.getImporteArchivo(), true, true)));
					archivo.getErrores().add(Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.pie.importe.total"),
							Utilidad.formatCurrency(archivo.getImportePie(), true, true),
							Utilidad.formatCurrency(archivo.getImporteArchivo(), true, true)));
				}
			}
		}
		
		return archivo;
	}
	

	private ArchivoDetalleAplicacionManualEntrada setearImporteTotalRegistros(ArchivoDetalleAplicacionManualEntrada archivo, DetalleAplicacionManualEntrada registro) {

		BigDecimal importeTotalRegistros = null;
		
		importeTotalRegistros = archivo.getImporteTotalRegistros();
		
		for (DetalleAplicacionManualRta rta : registro.getRta()){
			importeTotalRegistros = importeTotalRegistros.add(rta.getImporteAplicado());
		}
		
		archivo.setImporteTotalRegistros(importeTotalRegistros);
		
		return archivo;
	}

	/**
	 * Metodo para validar si el Tipo de Registro informado es valido
	 * @param registro
	 * @param tipoRegistro
	 * @return
	 */
	private boolean tipoRegistroValido(DetalleAplicacionManualEntrada registro, String tipoRegistro) {
		
		Traza.auditoria(getClass(), "~ Valida que el tipo de Registro sea Valido...");
		
		boolean tipoRegistroValido = true;
		
		if (Validaciones.isNullEmptyOrDash(tipoRegistro)){
			
			registro.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.tipoRegistro.obligatorio"));
			tipoRegistroValido = false;
			
		} else {
			
			TipoRegistroEnum tipoRegistroEnum = TipoRegistroEnum.getEnumByCodigo(tipoRegistro);
			
			if (Validaciones.isObjectNull(tipoRegistroEnum)){
				
				registro.setDescripcionError(Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.tipoRegistro.inexistente"),
						tipoRegistro));
				tipoRegistroValido = false;
				
			} else if ((!TipoRegistroEnum.D.equals(tipoRegistroEnum)) && (!TipoRegistroEnum.P.equals(tipoRegistroEnum))){
				
				registro.setDescripcionError(Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.tipoRegistro.invalido"),
						TipoRegistroEnum.D.codigo(), TipoRegistroEnum.P.codigo()));
				tipoRegistroValido = false;
				
			}
			
		}
		
		return tipoRegistroValido;
	}
	
	/**
	 * Retorna la posicion donde se encuentra el literal RTA
	 * @param datos
	 * @return
	 */
	private int obtenerPosicionRTA(String[] datos) {
		
		Traza.auditoria(getClass(), "~ Obtengo la posicion del RTA...");
		
		int posicion = 0;
		int i=0;
		
		for (i=0; i < datos.length; i++){
			
			if (RTA.equals(datos[i])){
				posicion=i;
				break;
			}
		}
		
		if ((datos.length==i)&&(!RTA.equals(datos[i-1]))){
			
			posicion=0;
			
		}
				
		return posicion;
		
	}
	
	/**
	 * Metodo que valida si el tipo de Operacion indicado es correcto
	 * @param registro
	 * @param datos
	 * @return
	 */
	private boolean tieneTipoOperacionValido(DetalleAplicacionManualEntrada registro, String[] datos) {
		
		Traza.auditoria(getClass(), "~ Valida que el tipo de Operacion sea Valido...");
		
		boolean tipoOperacionValido = true;
		
		if (Validaciones.isNullEmptyOrDash(datos[CAMPO_TIPO_OPER])){
			
			registro.setDescripcionError(Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.tipoOperacion.invalido"),
					TipoOperacionEnum.COBRO.tipoOperacionesExternas(),
					TipoOperacionEnum.DESCOBRO.tipoOperacionesExternas()));
			tipoOperacionValido = false;
			
		} else {
			
			TipoOperacionEnum tipoOperacionEnum = TipoOperacionEnum.getEnumByTipoOperacionesExternas(datos[CAMPO_TIPO_OPER]);
			
			if (Validaciones.isObjectNull(tipoOperacionEnum)){
				
				registro.setDescripcionError(Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.tipoOperacion.inexistente"),
						datos[CAMPO_TIPO_OPER]));
				tipoOperacionValido = false;
				
			} else if ((!TipoOperacionEnum.COBRO.equals(tipoOperacionEnum)) && (!TipoOperacionEnum.DESCOBRO.equals(tipoOperacionEnum))){
				
				registro.setDescripcionError(Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.tipoOperacion.invalido"),
						TipoOperacionEnum.COBRO.tipoOperacionesExternas(),
						TipoOperacionEnum.DESCOBRO.tipoOperacionesExternas()));
				tipoOperacionValido = false;
				
			} else {
				registro.setTipoOperacion(tipoOperacionEnum);
				
			}
			
		}		
	
		return tipoOperacionValido;
	}
	/**
	 * 
	 * @param registro
	 * @param datos
	 * @return
	 */
	private boolean completarDatosImporte(DetalleAplicacionManualEntrada registro, String[] datos) {
		// Seteo los datos de importe, moneda y importe pesificado
		boolean validacion = true;

//		if (Validaciones.isNullEmptyOrDash(datos[CAMPO_MONEDA])) {
//			registro.getErrores().add(Utilidad.reemplazarMensajes(
//				Propiedades.MENSAJES_PROPIEDADES.getString("error.aplicacion.manual.validarCampos.Moneda.obligatorio"),
//				String.valueOf(registro.getNroRegistro())
//			));
//		} else {
//			MonedaEnum monedaEnum = MonedaEnum.getEnumByName(datos[CAMPO_MONEDA]);
//			if (Validaciones.isObjectNull(monedaEnum)) {
//				registro.getErrores().add(Utilidad.reemplazarMensajes(
//					Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.moneda.incorrecta"),
//					MonedaEnum.DOL.name(),
//					MonedaEnum.PES.name()
//				));
//				validacion = false;
//			} else if (
//				!MonedaEnum.DOL.equals(monedaEnum) &&
//				!MonedaEnum.PES.equals(monedaEnum)
//			) {
//				registro.getErrores().add(Utilidad.reemplazarMensajes(
//					Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.moneda.incorrecta"),
//					String.valueOf(registro.getNroRegistro()),
//					MonedaEnum.DOL.name(),
//					MonedaEnum.PES.name()
//				));
//				validacion = false;
//			} else {
//				registro.setMoneda(monedaEnum);
//			}
//		}

		// Monto a imputar en moneda origen
//		if (
//			!Validaciones.isObjectNull(registro.getMoneda()) &&
//			!MonedaEnum.PES.equals(registro.getMoneda())
//		) {
//			if (Validaciones.isNullEmptyOrDash(datos[CAMPO_MONTO_MOMEDA_ORIGEN])) {
//				registro.getErrores().add(
//					Propiedades.MENSAJES_PROPIEDADES.getString("error.aplicacion.manual.validarCampos.montoMonedaOrigen.obligatorio")
//				);
//				validacion = false;
//			} else if (!Validaciones.isImporteFormatoDosDecimales(datos[CAMPO_MONTO_MOMEDA_ORIGEN])) {
//				registro.getErrores().add(
//					Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.montoMonedaOrigen.mal.informado")
//				);
//				validacion = false;
//			} else {
//				registro.setMontoImputarEnMonedaOrigen(Utilidad.stringToBigDecimal(datos[CAMPO_MONTO_MOMEDA_ORIGEN]));
//			}
//		} else {
//			if (!Validaciones.isNullEmptyOrDash(datos[CAMPO_MONTO_MOMEDA_ORIGEN])) {
//				registro.getErrores().add(
//					Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.montoMonedaOrigen.no.aplica")
//				);
//				validacion = false;
//			}
//		}

		// Monto Pesos
		if (Validaciones.isNullEmptyOrDash(datos[CAMPO_MONTO_PESOS])) {
			registro.getErrores().add(
				Propiedades.MENSAJES_PROPIEDADES.getString("error.aplicacion.manual.validarCampos.montPesos.obligatorio")
			);
			validacion = false;
		} else if (!Validaciones.isImporteFormatoDosDecimales(datos[CAMPO_MONTO_PESOS])) {
			registro.getErrores().add(Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.montoPesos.mal.informado"),
				datos[CAMPO_MONTO_PESOS]
			));
			validacion = false;
		} else {
			registro.setMontoImputarEnPesos(Utilidad.stringToBigDecimal(datos[CAMPO_MONTO_PESOS]));
		}

		return validacion;
	}
	/**
	 * Se valida la consistencia de datos que viene en la respuesta
	 * @param registro
	 * @param datos
	 * @param posicionRTA
	 */
	public boolean validarConsistenciaDeDatos(DetalleAplicacionManualEntrada registro, String[] datos, int posicionRTA, ArchivoDetalleAplicacionManualEntrada archivo){
		
		Traza.auditoria(getClass(), "~ Valida que la consistencia de los datos...");
		
		boolean esValido = true;
		
		int indiceRta = posicionRTA + 1;
		
		if (tieneCantidadDeColumnasCorrectas(registro, datos, posicionRTA)){
			
			if (esCampoIdTransaccionValido(registro, datos)){
				while (indiceRta < datos.length - 1) {
					
					DetalleAplicacionManualRta respuesta = new DetalleAplicacionManualRta();
					respuesta.setSistema(datos[indiceRta]);
					respuesta.setReferente(datos[indiceRta+1]);
					respuesta.setIdOperacionExterna(datos[indiceRta+2]);
					respuesta.setImporteAplicadoString(datos[indiceRta+3]);

					if (!esValidoFormatoRta(registro, respuesta)){
						esValido = false;
						break;
					}
					
					indiceRta += 4;
				}
				
			} else {
				esValido = false;
			}
			
		} else {
			
			esValido = false;
		}
		
		return esValido;
	}
	
	/**
	 * Se valida que venga informado los 4 campos de la respuesta
	 * @param registro
	 * @param respuesta
	 * @return
	 */
	public boolean esValidoFormatoRta(DetalleAplicacionManualEntrada registro, DetalleAplicacionManualRta respuesta) {
		
		Traza.auditoria(getClass(), "~ Valida el formato de la respuesta...");
		
		boolean esValido=true;
		
//		Sistema
		if (Validaciones.isObjectNull(respuesta.getSistema())) {
			
			registro.setDescripcionError(Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.sistema.no.informado"),
					respuesta.getSistema() + Constantes.SEPARADOR_PIPE + 
					respuesta.getReferente() + Constantes.SEPARADOR_PIPE + 
					respuesta.getIdOperacionExterna() + Constantes.SEPARADOR_PIPE + 
					Utilidad.formatCurrency(respuesta.getImporteAplicadoString(), false, true)));
			 
			esValido = false;
			
		} else if (respuesta.getSistema().length() > Constantes.CANTIDAD_MAXIMA_CARACTERES_SISTEMA) {
			
			registro.setDescripcionError(Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.sistema.excede.caracteres"),
					respuesta.getSistema() + Constantes.SEPARADOR_PIPE + 
					respuesta.getReferente() + Constantes.SEPARADOR_PIPE + 
					respuesta.getIdOperacionExterna() + Constantes.SEPARADOR_PIPE + 
					Utilidad.formatCurrency(respuesta.getImporteAplicadoString(), false, true)));
			 
			esValido = false;
			
		}
		
//		Referente
		if (Validaciones.isObjectNull(respuesta.getReferente())) {
			
			registro.setDescripcionError(Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.referente.no.informado"),
					respuesta.getSistema() + Constantes.SEPARADOR_PIPE + 
					respuesta.getReferente() + Constantes.SEPARADOR_PIPE + 
					respuesta.getIdOperacionExterna() + Constantes.SEPARADOR_PIPE + 
					Utilidad.formatCurrency(respuesta.getImporteAplicadoString(), false, true)));
			 
			esValido = false;
			
		} else if (respuesta.getReferente().length() > Constantes.CANTIDAD_MAXIMA_CARACTERES_REFERENCIA) {
			
			registro.setDescripcionError(Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.referente.excede.caracteres"),
					respuesta.getSistema() + Constantes.SEPARADOR_PIPE + 
					respuesta.getReferente() + Constantes.SEPARADOR_PIPE + 
					respuesta.getIdOperacionExterna() + Constantes.SEPARADOR_PIPE + 
					Utilidad.formatCurrency(respuesta.getImporteAplicado(), false, true)));
			 
			esValido = false;
		}

//		Id Operacion Externa
		if (Validaciones.isObjectNull(respuesta.getIdOperacionExterna())) {
			
			registro.setDescripcionError(Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.id.operacion.externa.no.informado"),
					respuesta.getSistema() + Constantes.SEPARADOR_PIPE + 
					respuesta.getReferente() + Constantes.SEPARADOR_PIPE + 
					respuesta.getIdOperacionExterna() + Constantes.SEPARADOR_PIPE + 
					Utilidad.formatCurrency(respuesta.getImporteAplicadoString(), false, true)));
			 
			esValido = false;
			
		} else if (respuesta.getIdOperacionExterna().length() > Constantes.CANTIDAD_MAXIMA_CARACTERES_OPERACION_EXTERNA) {
			registro.setDescripcionError(Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.id.operacion.externa.excede.caracteres"),
					respuesta.getSistema() + Constantes.SEPARADOR_PIPE + 
					respuesta.getReferente() + Constantes.SEPARADOR_PIPE + 
					respuesta.getIdOperacionExterna() + Constantes.SEPARADOR_PIPE + 
					Utilidad.formatCurrency(respuesta.getImporteAplicado(), false, true)));
			 
			esValido = false;
		}
		
//		Importe Aplicado
		BigDecimal importeAplicado = null;
		try{	
			if (!Validaciones.isNullOrEmpty(respuesta.getImporteAplicadoString())){
				if(Validaciones.isImporteFormatoDosDecimales(respuesta.getImporteAplicadoString())){
					importeAplicado = Utilidad.stringToBigDecimal(respuesta.getImporteAplicadoString());
					respuesta.setImporteAplicado(importeAplicado);
					
				}else {
					registro.setDescripcionError(Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.importe.mal.informado"),
							respuesta.getSistema() + Constantes.SEPARADOR_PIPE + 
							respuesta.getReferente() + Constantes.SEPARADOR_PIPE + 
							respuesta.getIdOperacionExterna() + Constantes.SEPARADOR_PIPE + 
							respuesta.getImporteAplicadoString()));
					
					esValido=false;
				}
				
			} else {
				registro.setDescripcionError(Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.importe.no.informado"),
						respuesta.getSistema() + Constantes.SEPARADOR_PIPE + 
						respuesta.getReferente() + Constantes.SEPARADOR_PIPE + 
						respuesta.getIdOperacionExterna() + Constantes.SEPARADOR_PIPE + 
						respuesta.getImporteAplicadoString()));
				 
				esValido = false;
				
			}
		} catch (NumberFormatException e) {
			registro.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.importe.mal.informado"));
			esValido=false;
		}		
		
		if (esValido){
			
			registro.getRta().add(respuesta);
			
		}
		
		return esValido;
	}
	
	/**
	 * Metodo que indica si tiene la cantidad de columnas correctas dependiendo si es COB o RCO
	 * @param registro
	 * @param datos
	 * @param posicionRTA
	 * @return
	 */
	public boolean tieneCantidadDeColumnasCorrectas(DetalleAplicacionManualEntrada registro, String[] datos, int posicionRTA) {
		
		Traza.auditoria(getClass(), "~ Valida la cantidad de columnas...");
		
		boolean tieneCantidadDeColumnasCorrectas=true;
		int cantColumnasDespuesRTA=datos.length - posicionRTA-1;
		int cantMinimaCampos = 12;
		TipoOperacionEnum tipoOperacionEnum = TipoOperacionEnum.getEnumByTipoOperacionesExternas(datos[CAMPO_TIPO_OPER]);
		
		if (TipoOperacionEnum.COBRO.equals(tipoOperacionEnum)){
		
			if (posicionRTA != cantMinimaCampos){
				
				registro.setDescripcionError(Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.cant.columnas"),
						tipoOperacionEnum.tipoOperacionesExternas()));
				tieneCantidadDeColumnasCorrectas=false;
				
			}
		} else if (TipoOperacionEnum.DESCOBRO.equals(tipoOperacionEnum)) {
			cantMinimaCampos = 13;
			int cantColumnasDescobro = posicionRTA - cantMinimaCampos;
			
			if ((cantColumnasDescobro % 4)!=0) {
				
				registro.setDescripcionError(Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.cant.columnas"),
						tipoOperacionEnum.tipoOperacionesExternas()));
				tieneCantidadDeColumnasCorrectas=false;
				
			}
			
		}
		
		if ((cantColumnasDespuesRTA % 4)!=0) {
			
			registro.setDescripcionError(Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.cant.columnas.RTA"),
					tipoOperacionEnum.tipoOperacionesExternas()));
			tieneCantidadDeColumnasCorrectas=false;
			
		}
		
		return tieneCantidadDeColumnasCorrectas;
		
	}
	
	/**
	 * Metodo que indica si el campo Id. Transaccion es valido.
	 * @param registro
	 * @param datos
	 * @return
	 */
	public boolean esCampoIdTransaccionValido(DetalleAplicacionManualEntrada registro, String[] datos) {
		
		Traza.auditoria(getClass(), "~ Valida el formato del campo Id Transaccion Shiva...");
		
		boolean esCampoIdTransaccionValido=true;
		
		if (Validaciones.isNullEmptyOrDash(datos[CAMPO_ID_TRANSACCION])) {
			
			registro.setDescripcionError(Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.operacion.no.informado"));
			esCampoIdTransaccionValido = false;
			
		} else {
			
			if (!tieneIdTransaccionFormatoCorrecto(datos[CAMPO_ID_TRANSACCION])){
				
				registro.setDescripcionError(Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.operacion.formato"),
						datos[CAMPO_ID_TRANSACCION]));
				esCampoIdTransaccionValido = false;
				
			} else {
				
				registro.setIdTransaccionShiva(datos[CAMPO_ID_TRANSACCION]);
				String idOperacionTransaccion[] = datos[CAMPO_ID_TRANSACCION].split("\\.");
				registro.setIdOperacion(Long.valueOf(idOperacionTransaccion[0]));
				
			}
			
		}
		
		return esCampoIdTransaccionValido;
	}

	/**
	 * Se valida que el formato del ID Transaccion Shiva sea [nnnnnnn].[nnnnn]
	 * @param idTransaccion
	 * @return
	 */
	public boolean tieneIdTransaccionFormatoCorrecto(String idTransaccion) {
		
		boolean formatoCorrecto = true;
		String transaccion[] = idTransaccion.split("\\.");
	
		if (transaccion.length != 2) {
			
			formatoCorrecto = false;
			
		} else {
			
			if (transaccion[0].length() > 7) {
				
				formatoCorrecto = false;
				
			} else if (!Validaciones.isNumeric(transaccion[0])){
				
				formatoCorrecto = false;
				
			}
			
			if (transaccion[1].length() != 5) {
				
				formatoCorrecto = false;
				
			} else if (!Validaciones.isNumeric(transaccion[1])){
				
				formatoCorrecto = false;
				
			}
		}
		
		return formatoCorrecto;
		
	}
	
	/**
	 * Metodo para agregar los detalles del cobro en un MAP para el manejo mas facil de las operaciones.
	 * @param registro
	 * @param mapa
	 */
	public void agregarDetalleAplicacionManualMap(DetalleAplicacionManualEntrada registro, Map<String, AgrupaDetalleAplicacionManualEntrada> mapa) {
		
		AgrupaDetalleAplicacionManualEntrada agrupador = null;
		
		if (Validaciones.isObjectNull(registro.getIdOperacion())) {
			
			agrupador = mapa.get(SIN_KEY);
			
			if (Validaciones.isObjectNull(agrupador)) {
				
				agrupador = new AgrupaDetalleAplicacionManualEntrada();
				
			}
			
			agrupador.getDetalleAplicacionManual().add(registro);
			mapa.put(SIN_KEY, agrupador);
			
		} else {
			
			if(Validaciones.isObjectNull(mapa.get(registro.getIdOperacion().toString()))){
				
				agrupador = new AgrupaDetalleAplicacionManualEntrada();
				
			} else {
				
				agrupador = mapa.get(registro.getIdOperacion().toString());
				
			}
			
			agrupador.getDetalleAplicacionManual().add(registro);
			mapa.put(registro.getIdOperacion().toString(), agrupador);
			
		}
		
	}
	
	/**
	 * Valida que el formato del Pie sea valido
	 * @param archivo
	 * @param registroArchivo
	 * @param cantRegistroDetalle
	 * @param cantRegistroPie
	 * @return
	 */
	public boolean tienePieValido(ArchivoDetalleAplicacionManualEntrada archivo, String[] registroArchivo, int cantRegistroDetalle, int cantRegistroPie){
		
		Traza.auditoria(getClass(), "~ Valida que el formato del Pie...");
		
		boolean esValido=true;
		int cantRegistroDetallePie=0;
		BigDecimal importeTotal = new BigDecimal(0);
		
		if (cantRegistroPie==0){
			
			archivo.setErrorPie(Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.pie.no.informado"));
			esValido=false;
			
		} else if (cantRegistroPie != 1){
			
			archivo.setErrorPie(Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.pie.no.unico"));
			esValido=false;
			
		} else {
			
			String pie[] = registroArchivo[registroArchivo.length - 1].split("\\|");
			
			if (!TipoRegistroEnum.P.codigo().equals(pie[CAMPO_TIPO_REG])){
				
				archivo.setErrorPie(Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.pie.no.informado.lugar.correcto"));
				esValido=false;
				
			} else if (pie.length != 4){
				
				archivo.setErrorPie(Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.pie.cant.columnas"));
				esValido=false;
				
			} else {
				
				cantRegistroDetallePie = Integer.parseInt(pie[CAMPO_PIE_CANT_REG]);
				
				if (cantRegistroDetallePie==0){
					
					archivo.setErrorPie(Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.pie.cant.registros.no.informado"));
					esValido=false;
					
				} else {
					
					String importePie = pie[CAMPO_PIE_IMPORTE_TOTAL];

					try{
					
						if (!Validaciones.isNullEmptyOrDash(importePie)) {
							if (Validaciones.isImporteFormatoDosDecimales(importePie)) {
								importeTotal = Utilidad.stringToBigDecimal(pie[CAMPO_PIE_IMPORTE_TOTAL]);
								archivo.setImportePie(importeTotal);
								
							} else {
								archivo.setErrorPie(Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.pie.importe.total.mal.informado"));
								esValido=false;
							}
							
						} else {
							archivo.setErrorPie(Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.pie.importe.total.no.informado"));
							esValido=false;
						}
						
					} catch (NumberFormatException e) {
						archivo.setErrorPie(Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.pie.importe.total.mal.informado"));
						esValido=false;
					}
				}
				
			}
			
		}
		
		if (esValido){
			
			if (cantRegistroDetallePie != cantRegistroDetalle){
				
				archivo.setErrorPie(Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.pie.cant.registros.no.concuerda"),
						String.valueOf(cantRegistroDetallePie),
						String.valueOf(cantRegistroDetalle)));
				esValido = false;
			}
		}
		
		return esValido;
		
	}
	
	public void procesarRegistros(ArchivoDetalleAplicacionManualEntrada archivo, String nombreArchivo) throws NegocioExcepcion {
		
		Traza.auditoria(getClass(), "Se empieza a procesar los registros cuyo formato fue validado con exito.");
		List<String> keys = new ArrayList<String>(archivo.getMapa().keySet());
		
		int cantOperacionesRechazadas=0;
		int cantOperacionesProcesadas=0;
		
		for (String key : keys) {
			
			if (!key.equals(SIN_KEY)) {
				
				AgrupaDetalleAplicacionManualEntrada agrupado = archivo.getMapa().get(key);
				
				// Si la Operacion esta rechazada no voy a Validar los datos a nivel negocio
				if (!agrupado.isRechazarOperacion()){
					
					if (validarYcompletarDatosDeLaOperacion(agrupado,archivo.getSistema())){
						
						if (!validarImporte(agrupado)){
							
							if (!agrupado.isRechazarOperacionPorRta()) {
								
								Traza.auditoria(getClass(), "Proceso");
								finalizarOperacion(agrupado,archivo.getSistema(),nombreArchivo);
								
							} else {
								
								// Rechazado por falta de respuesta
								archivo.setExisteAlgunError(true);
								cantOperacionesRechazadas++;
								
							}
							
						} else {
							
							// Rechazado por falta de respuesta
							archivo.setExisteAlgunError(true);
							cantOperacionesRechazadas++;
							
						}

					} else {
						
						archivo.setExisteAlgunError(true);
						cantOperacionesRechazadas++;
						
					}
					
				} else {
					
					archivo.setExisteAlgunError(true);
					cantOperacionesRechazadas++;
					
				}
				
				cantOperacionesProcesadas++;
			}
		}
		
		//Si se rechaza todos los registros se rechaza el archivo completo.
		if (cantOperacionesProcesadas==cantOperacionesRechazadas){
			
			archivo.setRechazarArchivoTodosRegistrosErroneos(true);
			
		}
		
	}
	
	/**
	 * Obtengo los datos del Id Operacion y valido el mismo
	 * @param agrupado
	 * @param sistema
	 * @return
	 * @throws NegocioExcepcion
	 */
	public boolean validarYcompletarDatosDeLaOperacion(AgrupaDetalleAplicacionManualEntrada agrupado, SistemaEnum sistema) throws NegocioExcepcion {
		
		// Aca tengo que validar a nivel de negocio
		List<ResultadoBusquedaDetalleAplicacionManual> lista = null;
		Long idOperacion = null;
		TipoOperacionEnum tipoOperacion = null;
		
		List<DetalleAplicacionManualEntrada> list = Lists.newArrayList(agrupado.getDetalleAplicacionManual()); 
		idOperacion = new Long(list.get(0).getIdOperacion());
		tipoOperacion = list.get(0).getTipoOperacion();
		
		try {
			
			lista = obtenerDatosRegistro(idOperacion, tipoOperacion);
			
		} catch (NegocioExcepcion e) {
			
			throw new NegocioExcepcion (e.getMessage(),e);
			
		}
		
		Long idOperacionRespuesta = null;
		Long idCobro = null;
		Long idDescobro = null;
		Long idOperacionDescobro = null;
		SistemaEnum sistemaRespuesta = null;
		Estado estado = null;
		MonedaEnum moneda = null;
		
		//·	SHV - La operación Shiva informada debe existir en el sistema
		// Si la operacion no existe
		if (Validaciones.isCollectionNotEmpty(lista)){
			
			idOperacionRespuesta = lista.get(0).getIdOperacion();
			idCobro = lista.get(0).getIdCobro();
			idDescobro = lista.get(0).getIdDescobro();
			sistemaRespuesta = lista.get(0).getSistema();
			estado = lista.get(0).getEstadoCobro();
			moneda = lista.get(0).getMoneda();
			idOperacionDescobro = lista.get(0).getIdOperacionDescobro();
			
			if (!Validaciones.isObjectNull(idOperacionRespuesta)){
				
				agrupado.setIdOperacion(idOperacion);
				
				if (!idOperacion.equals(idOperacionRespuesta)){
					
					agrupado.setMensajeError(
							Utilidad.reemplazarMensajes(
									Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.operacion.no.existe"),
									idOperacion.toString()));
					agrupado.setRechazarOperacion(true);
					
				} else {

					//·	SHV - La operación Shiva informada debe estar pendiente de confirmación manual
					if (!Validaciones.isObjectNull(estado)){
						
						if (tieneEstadoValidoDeCobro(estado,idCobro,idDescobro,idOperacionDescobro, agrupado, tipoOperacion)){
							
							if(!Validaciones.isObjectNull(sistemaRespuesta)){
								
								if(!sistemaRespuesta.equals(sistema)){
									
									agrupado.setMensajeError(
											Utilidad.reemplazarMensajes(
													Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.sistema.difiere"),
													idOperacion.toString(),
													sistemaRespuesta.getDescripcion()));
									agrupado.setRechazarOperacion(true);
									
								}
								
							
							} else {
								
								agrupado.setMensajeError(
										Utilidad.reemplazarMensajes(
												Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.sistema.inexistente"),
												idOperacion.toString()));
								agrupado.setRechazarOperacion(true);
								
							}
							
						}
					
				} else {
					
						agrupado.setMensajeError(
								Utilidad.reemplazarMensajes(
										Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.estado.inexistente"),
										idOperacion.toString()));
						agrupado.setRechazarOperacion(true);
						
					}
				}
			
			} else {
				
				agrupado.setMensajeError(
						Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.operacion.no.existe"),
								idOperacion.toString()));
				agrupado.setRechazarOperacion(true);
				
			}
						
		} else {
			
			agrupado.setMensajeError(
					Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.operacion.no.existe"),
							idOperacion.toString()));
			agrupado.setRechazarOperacion(true);
			
		}
		
		if(!agrupado.isRechazarOperacion()){
			//Si no se rechazó la operación, seteo los datos para el cod op externa
			for (DetalleAplicacionManualEntrada detalle : agrupado.getDetalleAplicacionManual()) {
				
				for (ResultadoBusquedaDetalleAplicacionManual resultado : lista) {
					String idOperacionTransaccionResultado[] = resultado.getNumTransaccionFormateado().split("\\.");
					String idOperacionTransaccionShiva[] = detalle.getIdTransaccionShiva().split("\\.");
					Long nroTransaccionResultado = new Long(idOperacionTransaccionResultado[1]);
					Long nroTransaccionShiva = new Long(idOperacionTransaccionShiva[1]);
					if(nroTransaccionShiva.equals(nroTransaccionResultado)){
						detalle.setIdTransaccion(resultado.getIdTransaccion());
						detalle.setIdTratamientoDiferencia(resultado.getIdTratamientoDiferencia());
						detalle.setIdMedioPago(resultado.getIdMedioPago());
						detalle.setMonedaMedioPago(resultado.getMonedaImporteTotalMedioPago());
						detalle.setImporteTotalMedioPago(resultado.getImporteTotalMedioPago());
						detalle.setMoneda(moneda);
						if (TipoOperacionEnum.DESCOBRO.equals(tipoOperacion)){
							detalle.setIdTransaccionShiva(resultado.getNumTransaccionFormateado());
						}
					}
				}
			}
		}
		
		return !agrupado.isRechazarOperacion();
	}
	
	/**
	 * Valido el estado del Cobro o la Reversion que se encuentre en Pendiente de Confirmacion de Aplicacion Manual 
	 * @param resultado
	 * @param tipoOperacion
	 * @return
	 */
	public boolean tieneEstadoValidoDeCobro(Estado estadoRespuesta, Long idCobro, Long idDescobro,Long idOperacionDescobro, AgrupaDetalleAplicacionManualEntrada agrupado, TipoOperacionEnum tipoOperacion) {
		
		Estado estado = null;
		Boolean isDescobro = false;
		
		if (TipoOperacionEnum.COBRO.equals(tipoOperacion)){
			
			estado = Estado.COB_PENDIENTE_CONFIRMACION_MANUAL;
			
			if (!Validaciones.isObjectNull(idCobro)){
				
				agrupado.setIdCobro(idCobro);
				
			}
			
		} else if (TipoOperacionEnum.DESCOBRO.equals(tipoOperacion)){
			
			estado = Estado.DES_PENDIENTE_CONFIRMACION_MANUAL;
			
			if (!Validaciones.isObjectNull(idDescobro)){
				
				agrupado.setIdDescobro(idDescobro);
				
			}
			
			if (!Validaciones.isObjectNull(idOperacionDescobro)){
				
				agrupado.setIdOperacionDescobro(idOperacionDescobro);
				
			}
			
			if (!Validaciones.isObjectNull(idCobro)){
				
				agrupado.setIdCobro(idCobro);
				
			}
			isDescobro = true;
		}
		
		if (!estado.equals(estadoRespuesta)){
			if(isDescobro) {
				agrupado.setMensajeError(
						Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.estado.invalido"),
								agrupado.getIdOperacionDescobro().toString(),
								estadoRespuesta.descripcion()));
				agrupado.setRechazarOperacion(true);
			} else {
				agrupado.setMensajeError(
						Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.estado.invalido"),
								agrupado.getIdOperacion().toString(),
								estadoRespuesta.descripcion()));
				agrupado.setRechazarOperacion(true);
			}
		} 
		
		return !agrupado.isRechazarOperacion();
	}

	/**
	 * Obtengo la informacion correspondiente al Id Operacion
	 * @param idOperacion
	 * @param tipoOperacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<ResultadoBusquedaDetalleAplicacionManual> obtenerDatosRegistro(Long idOperacion, TipoOperacionEnum tipoOperacion) throws NegocioExcepcion{
		
		List<ResultadoBusquedaDetalleAplicacionManual> lista = new ArrayList<ResultadoBusquedaDetalleAplicacionManual>();
		
		try {
			Traza.auditoria(ProcesarEntradaDetalleAplicacionesManualesServicioImpl.class, "Se inicia la búsqueda de datos para la operacion:" + idOperacion);
			lista = procesarEntradaDetalleAplicacionesManualesDao.obtenerDatosRegistro(idOperacion,tipoOperacion);
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		return lista;
	}
	
	/**
	 * Obtengo los datos de las transacciones y valido el importe
	 * @param agrupado
	 * @param sistema
	 * @return
	 * @throws NegocioExcepcion
	 */
	public boolean validarImporte(AgrupaDetalleAplicacionManualEntrada agrupado) throws NegocioExcepcion {
		
		List<ResultadoBusquedaImporteTransaccionesAplicacionManual> resultado = null;
		Long idOperacion = agrupado.getIdOperacion();
		Boolean transaccionExiste=true;
		Boolean importeInvalido=false;
		StringBuffer transaccionesInexistentes=new StringBuffer();
		BigDecimal importeTotalCobro = new BigDecimal(0);
		
		try {
			
			resultado = obtenerDatosDeTransacciones(idOperacion);
			
		} catch (NegocioExcepcion e) {
			
			throw new NegocioExcepcion (e.getMessage(),e);
			
		}
		
		//Valido que todas las transacciones del cobro hayan sidos aplicadas en los sistemas externos con los importes indicados.
		for (ResultadoBusquedaImporteTransaccionesAplicacionManual transaccion: resultado){
			
			String idOperacionTransaccionShiva[] = transaccion.getNumTransaccionFormateado().split("\\.");
			Long nroTransaccionShiva = new Long(idOperacionTransaccionShiva[1]);
			importeTotalCobro = importeTotalCobro.add(transaccion.getImporte());
			
			transaccionExiste=false;
			
			for (DetalleAplicacionManualEntrada detalle : agrupado.getDetalleAplicacionManual()) {
				
				String idOperacionTransaccionArchivo[] = detalle.getIdTransaccionShiva().split("\\.");
				Long nroTransaccionDetalle = new Long(idOperacionTransaccionArchivo[1]);
				BigDecimal importeRegistroDetalle = detalle.getImporteTotalRta();
				
				if (nroTransaccionShiva.equals(nroTransaccionDetalle)){
					
					transaccionExiste=true;
					
					if (importeRegistroDetalle.compareTo(transaccion.getImporte())!=0) {
						
						detalle.setDescripcionError(Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.importe.diferente"),
								Utilidad.formatCurrency(importeRegistroDetalle, true, true),
								Utilidad.formatCurrency(transaccion.getImporte(), true, true)));
						
						importeInvalido=true;
						
					}
					
					break;
					
				}
				
			}
			
			//Se guardaran las transacciones que no vinieron informados en el archivo.
			if (!transaccionExiste){
				
				if (transaccionesInexistentes.length()!=0){
					
					transaccionesInexistentes.append("|");
				}
				
				transaccionesInexistentes.append(transaccion.getNumTransaccionFormateado());
				
			}
			
		}
			
		if (!transaccionExiste){
			
			agrupado.setMensajeError(Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.transacciones.no.procesadas"),
					transaccionesInexistentes.toString()));
			agrupado.setRechazarOperacion(true);
			
			importeInvalido=true;
			
		}
		
		//Se valida que la sumatoria de todos los importes aplicados en el archivo sea igual a la sumatoria de los importes de las transacciones indicados en el cobro.
		if (!agrupado.isRechazarOperacion()){
			
			BigDecimal importeTotalAgrupador = agrupado.getImporteTotalAgrupador();
			
			if (importeTotalAgrupador.compareTo(importeTotalCobro)!=0){
				
				agrupado.setMensajeError(Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("procesar.detalle.aplicacion.manual.error.importe.total.difiere"),
						Utilidad.formatCurrency(importeTotalAgrupador, true, true),
						Utilidad.formatCurrency(importeTotalCobro, true, true)));
				agrupado.setRechazarOperacion(true);
				
				importeInvalido=true;
				
			}
			
		}
		
		return importeInvalido;
		
	}
	
	/**
	 * Obtengo la informacion de las transacciones correspondientes al Id Operacion
	 * @param idOperacion
	 * @param tipoOperacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List <ResultadoBusquedaImporteTransaccionesAplicacionManual> obtenerDatosDeTransacciones(Long idOperacion) throws NegocioExcepcion{
		
		List <ResultadoBusquedaImporteTransaccionesAplicacionManual> resultado = new ArrayList<ResultadoBusquedaImporteTransaccionesAplicacionManual>();
		
		try {
			Traza.auditoria(ProcesarEntradaDetalleAplicacionesManualesServicioImpl.class, "Se inicia la búsqueda de datos de los importes de las transacciones para la operacion:" + idOperacion);
			resultado = procesarEntradaDetalleAplicacionesManualesDao.obtenerImporteTransaccionesAplicacionManual(idOperacion);
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		return resultado;
	}
	
	/**
	 * Metodo que envia mail de los archivos con formato invalido
	 * @param cuerpo
	 * @param nombreDeArchivo
	 * @throws NegocioExcepcion
	 */
	private void enviarMailArchivoInvalido(StringBuffer cuerpo, String nombreDeArchivo) throws NegocioExcepcion{
		try{
			
			Traza.auditoria(getClass(), "~");
			Traza.auditoria(getClass(), "~ Comienza la lógica de envío de mail por archivo con nombre invalido...");
			Traza.auditoria(getClass(), "~");
			
			//
			// Destinatarios 'para'
			//
			String destinatariosPara = parametroServicio.getValorTexto(Constantes.LISTA_GRUPO_MAIL_COMUN_AUTOMATIZACION_CONFIRMACION_APLICACION_MANUAL);
			
			//
			// Asunto
			//
			String asunto = Propiedades.MENSAJES_PROPIEDADES.getString("mail.asunto.proceso.archivo.entrada.aplicacion.manual.mailArchivoInvalido");
			
			// Si tengo destinatarios, armo mail y lo envío
			
			StringBuffer cuerpoMail = new StringBuffer();
			
			cuerpoMail.append("Nombre archivo: " + nombreDeArchivo + "<br>");
			cuerpoMail.append("Error: " + cuerpo + "<br>");
			
			if (!Validaciones.isNullOrEmpty(destinatariosPara.toString())) {
				
				Mail mailDetalleProcesamiento = new Mail(destinatariosPara.toString().replace(",", ";") + ";", 
									 asunto,
									 cuerpoMail);
				
				mailServicio.sendMail(mailDetalleProcesamiento);
				
			}
			
			Traza.auditoria(getClass(), "~ Fin de envío de mail.");
			Traza.auditoria(getClass(), "~");
			
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * Devuelve el estado final del resultado de procesamiento del archivo
	 * @param resultadoProcesamientoArchivo
	 * @return
	 */
	private TipoResultadoEnum estadoProcesamiento(ArchivoDetalleAplicacionManualEntrada resultadoProcesamientoArchivo) {
		
		TipoResultadoEnum estado=null;
		
		estado = TipoResultadoEnum.OK;
		
		if (!Validaciones.isNullEmptyOrDash(resultadoProcesamientoArchivo.getErrorPie())
				|| resultadoProcesamientoArchivo.isRechazarArchivoTodosRegistrosErroneos()
			|| resultadoProcesamientoArchivo.isErrorImporte())
		{
			
			estado = TipoResultadoEnum.ERROR;
			
		} else if (resultadoProcesamientoArchivo.isExisteAlgunError()){
			
			estado = TipoResultadoEnum.NOK;
			
		}
		
		return estado;
		
	}
	
	/**
	 * Metodo donde se envia mail con detalle del procesamiento
	 * @param cuerpo
	 * @param sistema
	 * @param estado
	 * @throws NegocioExcepcion
	 */
	private void enviarMailConDetalleDeProcesamiento(StringBuffer cuerpo, SistemaEnum sistema, ArchivoDetalleAplicacionManualEntrada resultadoProcesamientoArchivo, TipoResultadoEnum estado, String nombreArchivo) throws NegocioExcepcion{
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
			
			// Asunto
			String asunto = "";
			
			if(TipoResultadoEnum.OK.equals(estado)){
				
				asunto = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("mail.asunto.proceso.archivo.entrada.aplicacion.manual.mailOk"), 
						sistema.getDescripcion());
				
			} else if (TipoResultadoEnum.NOK.equals(estado)) {
				
				asunto = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("mail.asunto.proceso.archivo.entrada.aplicacion.manual.mailNok"), 
						sistema.getDescripcion());
				
			} else if (TipoResultadoEnum.ERROR.equals(estado)) {
				
				asunto = Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("mail.asunto.proceso.archivo.entrada.aplicacion.manual.mailRechazado"), 
						sistema.getDescripcion());
				
			}
			
			//Adjunto
			List<Adjunto> listaAdjuntos = new ArrayList<Adjunto>();
			
			if (Validaciones.isNullEmptyOrDash(resultadoProcesamientoArchivo.getErrorPie()) && 
				(!TipoResultadoEnum.OK.equals(estado)) && (!resultadoProcesamientoArchivo.isArchivoYaProcesado())
			){
				
				String contenidoArchivo = registrosConErrores(resultadoProcesamientoArchivo);
			//	Traza.advertencia(getClass(), ">>-------------------------> " + contenidoArchivo);
				byte[] bytes = contenidoArchivo.getBytes();
				
				listaAdjuntos.add(new Adjunto(bytes, nombreArchivo.replaceAll(Constantes.ARCHIVO_CSV, ""), "csv"));
				
			}
			
			//Cuerpo
			StringBuffer cuerpoMail = new StringBuffer();
			
			if (resultadoProcesamientoArchivo.isRechazarArchivoTodosRegistrosErroneos()){
				
				cuerpoMail.append(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("mail.cuerpo.proceso.archivo.entrada.aplicacion.manual"), 
						nombreArchivo, "Todos los registros del archivo tienen error."));
				
			} else if (!TipoResultadoEnum.ERROR.equals(estado)) {
				
				cuerpoMail.append(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("mail.cuerpo.proceso.archivo.entrada.aplicacion.manual"), 
						nombreArchivo, obtenerCantidadDeRegistrosProcesadosYError(resultadoProcesamientoArchivo)));
				
			} else if (!Validaciones.isNullEmptyOrDash(resultadoProcesamientoArchivo.getErrorPie())){
				
				cuerpoMail.append(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("mail.cuerpo.proceso.archivo.entrada.aplicacion.manual"), 
						nombreArchivo, resultadoProcesamientoArchivo.getErrorPie()));
				
			} else if (resultadoProcesamientoArchivo.isErrorImporte()) {
				
				cuerpoMail.append(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("mail.cuerpo.proceso.archivo.entrada.aplicacion.manual"), 
						nombreArchivo, "Hay un error en un importe en pesos de un registro que impide la validación por importe. Revisar el archivo adjunto "));
				
			} else {
				
				cuerpoMail.append(Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("mail.cuerpo.proceso.archivo.entrada.aplicacion.manual"), 
						nombreArchivo, cuerpo.toString()));
				
			}
			
			//Si tengo destinatarios, armo mail y lo envío
			if (!Validaciones.isNullOrEmpty(destinatariosPara.toString()) && !Validaciones.isNullOrEmpty(destinatariosCC)) {
				
				Mail mailDetalleProcesamiento = new Mail(destinatariosPara.toString(), 
									 destinatariosCC.replace(",", ";") + ";", 
									 asunto,
									 cuerpoMail);
				
				if (Validaciones.isCollectionNotEmpty(listaAdjuntos)){
					
					mailDetalleProcesamiento.setAdjuntos(listaAdjuntos);
					
				}
				
				mailServicio.sendMail(mailDetalleProcesamiento);
				
			} else {
				Traza.auditoria(getClass(), "~ Error: no existen usuarios con perfil '" 
											+ perfilBusqueda
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
	 * Metodo que devuelve la leyenda de cantida de registros recibidos, procesados OK y NOK
	 * @param resultadoProcesamientoArchivo
	 * @return
	 * @throws NegocioExcepcion
	 * @throws IOException
	 */
	public String obtenerCantidadDeRegistrosProcesadosYError(ArchivoDetalleAplicacionManualEntrada resultadoProcesamientoArchivo) throws NegocioExcepcion, IOException {
		
		List<String> keys = new ArrayList<String>(resultadoProcesamientoArchivo.getMapa().keySet());
		int cantDeRegistrosProcesados=0;
		int cantDeRegistrosConError=0;
		int cantDeRegistrosOK=0;
		String respuesta="";
		
		for (String key : keys) {
			
			AgrupaDetalleAplicacionManualEntrada agrupado = resultadoProcesamientoArchivo.getMapa().get(key);
			
			if (agrupado.isRechazarOperacion() || agrupado.isRechazarOperacionPorRta()){
				
				cantDeRegistrosConError += agrupado.getDetalleAplicacionManual().size();
				
			} else {
				
				cantDeRegistrosOK += agrupado.getDetalleAplicacionManual().size();
				
			}
				
			cantDeRegistrosProcesados+=agrupado.getDetalleAplicacionManual().size();
			
		}
		
		if (cantDeRegistrosProcesados != 0){
			
			respuesta = " Cantidad de registros recibidos: " + cantDeRegistrosProcesados;
			
		}
		
		if (cantDeRegistrosOK != 0){
			
			respuesta += " | Cantidad de registros procesados OK: " + cantDeRegistrosOK;
			
		}
		
		if (cantDeRegistrosConError != 0){
			
			respuesta += " | Cantidad de registros procesados NOK: " + cantDeRegistrosConError;
			
		}
		
		return respuesta;
		
	}
	
	public String registrosConErrores(ArchivoDetalleAplicacionManualEntrada resultadoProcesamientoArchivo) throws NegocioExcepcion, IOException {
		
		List<String> keys = new ArrayList<String>(resultadoProcesamientoArchivo.getMapa().keySet());
		StringBuffer registrosArchivo = new StringBuffer();
		String contenidoArchivo;
		
		for (String key : keys) {
			
			AgrupaDetalleAplicacionManualEntrada agrupado = resultadoProcesamientoArchivo.getMapa().get(key);
			
			if (key.equals(SIN_KEY)){
				
				for (DetalleAplicacionManualEntrada detalle: agrupado.getDetalleAplicacionManual()) {
					
					registrosArchivo.append(detalle.getRegistroArchivo() + "|" + detalle.getDescripcionError());
					if (Validaciones.isCollectionNotEmpty(detalle.getErrores())) {
						for (String error : detalle.getErrores()) {
							registrosArchivo.append("  ");
							registrosArchivo.append(error);
						}
					}
					registrosArchivo.append(Constantes.RETORNO_WIN);
				}
				
			} else {
				
				if (agrupado.isRechazarOperacion()) {
					
					for (DetalleAplicacionManualEntrada detalle: agrupado.getDetalleAplicacionManual()) {
						
						if (!Validaciones.isNullEmptyOrDash(detalle.getDescripcionError())) {
							
							registrosArchivo.append(detalle.getRegistroArchivo() + "|" + detalle.getDescripcionError());
							
						} else {
							registrosArchivo.append(detalle.getRegistroArchivo() + "|" + agrupado.getMensajeError());
							
						}
						if (Validaciones.isCollectionNotEmpty(detalle.getErrores())) {
							for (String error : detalle.getErrores()) {
								registrosArchivo.append(" ");
								registrosArchivo.append(error);
							}
						} 
						
						registrosArchivo.append(Constantes.RETORNO_WIN);
						
					}
					
				} else if (agrupado.isRechazarOperacionPorRta()) {
					
					for (DetalleAplicacionManualEntrada detalle: agrupado.getDetalleAplicacionManual()) {
						
						if (Validaciones.isNullEmptyOrDash(detalle.getDescripcionError())) {
							
							registrosArchivo.append(detalle.getRegistroArchivo() + "|" + "Registro No procesado por error en otra transaccion");
							
						} else {
							
							registrosArchivo.append(detalle.getRegistroArchivo() + "|" + detalle.getDescripcionError());
							
						}
						if (Validaciones.isCollectionNotEmpty(detalle.getErrores())) {
							for (String error : detalle.getErrores()) {
								registrosArchivo.append("  ");
								registrosArchivo.append(error);
							}
						} 
						registrosArchivo.append(Constantes.RETORNO_WIN);
						
					}
					
				}
				
				
			}
			
		}
		
		contenidoArchivo = registrosArchivo.toString();
		
		return contenidoArchivo;
	}
	
	/**
	 * Metodo donde se obtiene el grupo de Mails pertenecienta a un determinado Sistema
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
	
	public void avanzarEstadoWorkflow(Long idOperacion, SistemaEnum sistema, TipoOperacionEnum tipoOperacion, Long idCobro) throws NegocioExcepcion {
		
		try {
			String usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
			String nombreUsuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_NOMBRE_BATCH);
			
			ShvCobEdCobroSimplificadoConWorkflow cobro = null;
			ShvCobDescobro descobro = null;
			ShvWfWorkflow workflow = null;
			
			if (TipoOperacionEnum.COBRO.equals(tipoOperacion)){
				cobro = cobroOnlineDao.buscarCobroSimplificadoConWorkflow(idCobro);
				workflow = workflowService.buscarWorkflow(cobro.getIdWorkflow());
				Traza.auditoria(ProcesarEntradaDetalleAplicacionesManualesServicioImpl.class, "Se va a realizar el avance de workflow -> "+tipoOperacion+" [IdOperacion: "+idOperacion+"] workflow actual:[" + workflow.getEstado().descripcion()+"]");
				workflow = workflowCobros.registrarCobroPendienteDeConfirmacionManualAEnProceso(workflow, "", usuarioBatch);
				
				Traza.auditoria(ProcesarEntradaDetalleAplicacionesManualesServicioImpl.class, "Se actualizó el workflow ->"+tipoOperacion+" [IdOperacion: "+idOperacion+"] workflow actualizado:[" + workflow.getEstado().descripcion()+"]");
				if (SistemaEnum.NEGOCIO_NET.equals(sistema)
						|| SistemaEnum.SAP.equals(sistema)){
					
					workflow = workflowService.agregarWorkflowMarca(workflow, usuarioBatch, MarcaEnum.CONFIRMADO_POR_REFERENTE_CAJA);
					
					cobro.setIdReferenteCaja(usuarioBatch);
					cobro.setNombreApellidoReferenteCaja(nombreUsuarioBatch);
					cobro.setFechaReferenteCaja(new Date());
					
				} else if (SistemaEnum.CABLEVISION.equals(sistema)
						|| SistemaEnum.NEXTEL.equals(sistema)){
					
					workflow = workflowService.agregarWorkflowMarca(workflow, usuarioBatch, MarcaEnum.CONFIRMADO_POR_REFERENTE_OPERACIONES_EXTERNAS);
					
					cobro.setIdReferenteoperacionexterna(usuarioBatch);
					cobro.setNombreApellidoReferenteOperacionExterna(nombreUsuarioBatch);
					cobro.setFechaReferenteOperacionExterna(new Date());
				}
				
				cobro = cobroOnlineDao.guardarCobroSimplificadoConWorkflow(cobro);
				
			} else if (TipoOperacionEnum.DESCOBRO.equals(tipoOperacion)){
				
				descobro = descobroDao.buscarDescobroPorIdCobro(idCobro);
				workflow = descobro.getWorkflow();
				Traza.auditoria(ProcesarEntradaDetalleAplicacionesManualesServicioImpl.class, "Se va a realizar el avance de workflow ->"+tipoOperacion+" [IdOperacion: "+idOperacion+"] workflow actual:[" + workflow.getEstado().descripcion()+"]");
				workflow = workflowDescobros.registrarDescobroPendienteConfirmacionManualAEnProceso(workflow, "", usuarioBatch);
				Traza.auditoria(ProcesarEntradaDetalleAplicacionesManualesServicioImpl.class, "Se actualizó el workflow -> "+tipoOperacion+" [IdOperacion: "+idOperacion+"] workflow actualizado:[" + workflow.getEstado().descripcion()+"]");
				
				if (SistemaEnum.NEGOCIO_NET.equals(sistema)
						|| SistemaEnum.SAP.equals(sistema)){
					
					descobro.setUsuarioAprobAplicacionManualRefCaja(usuarioBatch);
					descobro.setNombreApellidoAprobAplicacionManualRefCaja(nombreUsuarioBatch);
					descobro.setFechaConfirmacionAplicacionManualRefCaja(new Date());
					
				}  else if (SistemaEnum.CABLEVISION.equals(sistema)
						|| SistemaEnum.NEXTEL.equals(sistema)){
					
					descobro.setUsuarioAprobAplicacionManualRefOperacionesExternas(usuarioBatch);
					descobro.setNombreApellidoAprobAplicacionManualRefOperacionesExternas(nombreUsuarioBatch);
					descobro.setFechaAprobAplicacionManualRefOperacionesExternas(new Date());
				}
				
				descobro.setWorkflow(workflow);
				descobro = descobroDao.modificar(descobro);
				
			}
			
			cerrarTareaConfirmacionAplicacionManual(workflow,usuarioBatch,tipoOperacion, sistema);
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	public void insertarCodOperacionExterna(AgrupaDetalleAplicacionManualEntrada agrupado, ShvArcArchivo archivo) throws NegocioExcepcion{
		
		try{
			for (DetalleAplicacionManualEntrada det : agrupado.getDetalleAplicacionManual()) {
				if (!Validaciones.isCollectionNotEmpty(det.getErrores())){
					
					if(Validaciones.isCollectionNotEmpty(det.getRta())){
						for (DetalleAplicacionManualRta rta : det.getRta()) {
							
							
							if (TipoOperacionEnum.COBRO.equals(det.getTipoOperacion())){
								
								ShvCobEdCodigoOperacionExternaSimplificado codOpExterna = new ShvCobEdCodigoOperacionExternaSimplificado();
								codOpExterna.setCodigoOperacionExterna(rta.getIdOperacionExterna());
								codOpExterna.setIdCobro(agrupado.getIdCobro());
								codOpExterna.setReferente(rta.getReferente());
								codOpExterna.setSistema(rta.getSistema());
								codOpExterna.setImporte(rta.getImporteAplicado());
								codOpExterna.setIdTransaccion(det.getIdTransaccion().intValue());
								codOpExterna.setNumeroTransaccionFormateado(det.getIdTransaccionShiva());
								codOpExterna.setIdTratamientoDiferencia(det.getIdTratamientoDiferencia().intValue());
								codOpExterna.setIdMedioPago(det.getIdMedioPago().intValue());
								codOpExterna.setMonedaImporte(det.getMoneda());
								codOpExterna.setMonedaImporteTotalMedioPago(det.getMonedaMedioPago());
								codOpExterna.setImporteTotalMedioPago(det.getImporteTotalMedioPago());
								codOpExterna.setArchivo(archivo);
								
								
								procesarEntradaDetalleAplicacionesManualesDao.guardarShvCobEdCodigoOperacionExternaSimplificado(codOpExterna);
								
							} else if (TipoOperacionEnum.DESCOBRO.equals(det.getTipoOperacion())){
								
								ShvCobDescobroCodigoOperacionExternaSimplificado codOpExterna = new ShvCobDescobroCodigoOperacionExternaSimplificado();
								codOpExterna.setCodigoOperacionExterna(rta.getIdOperacionExterna());
								codOpExterna.setIdDescobro(agrupado.getIdDescobro());
								codOpExterna.setReferente(rta.getReferente());
								codOpExterna.setSistema(rta.getSistema());
								codOpExterna.setImporte(rta.getImporteAplicado());
								codOpExterna.setIdTransaccion(det.getIdTransaccion().intValue());
								codOpExterna.setNumeroTransaccionFormateado(det.getIdTransaccionShiva());
								codOpExterna.setIdTratamientoDiferencia(det.getIdTratamientoDiferencia().intValue());
								codOpExterna.setIdMedioPago(det.getIdMedioPago().intValue());
								codOpExterna.setMonedaImporte(det.getMoneda());
								codOpExterna.setMonedaImporteTotalMedioPago(det.getMonedaMedioPago());
								codOpExterna.setImporteTotalMedioPago(det.getImporteTotalMedioPago());
								codOpExterna.setArchivo(archivo);
								
								
								procesarEntradaDetalleAplicacionesManualesDao.guardarShvCobDescobroCodigoOperacionExternaSimplificado(codOpExterna);
							}
						}
					}
				}
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
	}
	
	public void actualizarShvCobAplicManualBatchDet(AgrupaDetalleAplicacionManualEntrada agrupado, TipoOperacionEnum tipoOperacion) throws NegocioExcepcion{
		try {
			
			List<String> listaIdTransacciones = new ArrayList<String>();
			
			for (DetalleAplicacionManualEntrada det : agrupado.getDetalleAplicacionManual()) {
				if (!Validaciones.isCollectionNotEmpty(det.getErrores())){
					listaIdTransacciones.add(det.getIdTransaccionShiva());
				}
			}
		
			List<ShvCobAplicacionManualBatchDetalle> listaDetalle = new ArrayList<ShvCobAplicacionManualBatchDetalle>();
			listaDetalle = procesarEntradaDetalleAplicacionesManualesDao.obtenerListaShvCobAplicacionManualBatchDetalle(listaIdTransacciones,tipoOperacion);
			for (ShvCobAplicacionManualBatchDetalle shvCobAplicacionManualBatchDetalle : listaDetalle) {
				shvCobAplicacionManualBatchDetalle.setEstadoTarea(MensajeEstadoEnum.COMPLETADO);
				procesarEntradaDetalleAplicacionesManualesDao.actualizarShvCobAplicacionManualBatchDetalle(shvCobAplicacionManualBatchDetalle);
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	public boolean seEncuentraEnLaBD(String nombre) throws PersistenciaExcepcion {
		boolean estaEnBd = true;
		ShvArcArchivo archivo = procesarEntradaDetalleAplicacionesManualesDao.buscarArchivoEntradaPorNombreArchivo(nombre);
		if (Validaciones.isObjectNull(archivo)) {
			estaEnBd= false;
		}
		return estaEnBd;
	}
	
	/**
	 * 
	 * @param workflow
	 * @param usuarioModificacion
	 * @param tipoOperacion
	 * @param sistemaDestino
	 * @throws NegocioExcepcion
	 */
	public void cerrarTareaConfirmacionAplicacionManual(ShvWfWorkflow workflow, String usuarioModificacion, TipoOperacionEnum tipoOperacion, SistemaEnum sistemaDestino) throws NegocioExcepcion{
		
		try {
			if (TipoOperacionEnum.COBRO.equals(tipoOperacion) || TipoOperacionEnum.DESCOBRO.equals(tipoOperacion)) {
				tareaServicio.finalizarTareaCorrecto(this.retornaTareaAplicacionManual(sistemaDestino, workflow.getEstado()), workflow.getIdWorkflow(), workflow.getFechaUltimaModificacion(), usuarioModificacion, null);
			}
			Traza.auditoria(this.getClass(), "Se cerró la tarea correctamente");
		} catch (NegocioExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * retorno la tarea aplicacion manual segun el sistemas destino y el estado
	 * @param sistemaDestino
	 * @param estado
	 * @return
	 */
	private TipoTareaEnum retornaTareaAplicacionManual(SistemaEnum sistemaDestino, Estado estado) {
		TipoTareaEnum tipoTarea = null;
		
		if (Estado.COB_PENDIENTE_CONFIRMACION_MANUAL.equals(estado)) {
			tipoTarea = TipoTareaEnum.COB_CONF_APLIC_MANUAL;
		} else if(Estado.COB_ERROR_COBRO.equals(estado)){
			tipoTarea = TipoTareaEnum.COB_ERR_CONF_APLIC_MANUAL;
		} else if (Estado.DES_PENDIENTE_CONFIRMACION_MANUAL.equals(estado)) {
					tipoTarea = TipoTareaEnum.DES_CONFIRMA_APL_MAN;
			} 	
		
		return tipoTarea;
	}
	
	 @Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void finalizarOperacion(AgrupaDetalleAplicacionManualEntrada agrupado, SistemaEnum sistema, String nombreArchivo) throws NegocioExcepcion{
		
		List<DetalleAplicacionManualEntrada> lista = Lists.newArrayList(agrupado.getDetalleAplicacionManual());
		TipoOperacionEnum tipoOperacion = lista.get(0).getTipoOperacion();
		
		Traza.auditoria(this.getClass(), "Se guarda el archivo en la base de datos.");
		ShvArcArchivo archivo;
		try {
			archivo = insertarArcArchivo(nombreArchivo);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
		Traza.auditoria(this.getClass(), "Se guardan en la base de datos cod operaciones externas");
		insertarCodOperacionExterna(agrupado,archivo);
		
		Traza.auditoria(this.getClass(), "Se actualizan los ShvCobAplicManualBatchDet correspondientes");
		actualizarShvCobAplicManualBatchDet(agrupado,tipoOperacion);
		
		Traza.auditoria(this.getClass(), "Avance de workflow y cierre de tarea");
		avanzarEstadoWorkflow(agrupado.getIdOperacion(),sistema, tipoOperacion,agrupado.getIdCobro());
	}
}