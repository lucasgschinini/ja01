package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.EstadoFacturaMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoTransaccionEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoOperacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoOperacionReversionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.ICobroImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IDescobroOrigenCobradorServicio;
import ar.com.telecom.shiva.negocio.servicios.IDescobroServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IScardServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorPorReversionServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;
import ar.com.telecom.shiva.negocio.servicios.IVistaSoporteServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowDescobroOrigenCobrador;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.IDescobroDao;
import ar.com.telecom.shiva.persistencia.dao.IValorDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFactura;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCTA;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCliente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionCesionCredito;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionIIBB;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionIntercompany;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionLiquidoProducto;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionOtras;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoCompensacionProveedor;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDebitoProximaFacturaMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDesistimiento;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoNotaCreditoCalipso;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoNotaCreditoMic;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoPlanDePago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoRemanente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoShiva;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoUsuario;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobOperacion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTratamientoDiferencia;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvValValorSimplificado;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;

public class DescobroOrigenCobradorServicioImpl extends Servicio implements IDescobroOrigenCobradorServicio{

	@Autowired 
	IWorkflowDescobroOrigenCobrador workflowDescobroOrigenCobrador;
	
	@Autowired 
	IDescobroDao descobroDao;
	
	@Autowired 
	ICobroDao cobroDao;

	ICobroImputacionServicio cobroServicio;
	
	@Autowired
	IValorServicio valorServicio;
	
	@Autowired 
	IValorDao valorDao;
	
	@Autowired
	IValorPorReversionServicio valorPorReversionServicio;
	
	@Autowired 
	IScardServicio scardServicio;
	
	@Autowired
	private IParametroServicio parametroServicio;
	
	@Autowired
	private MailServicio mailServicio;
	
	@Autowired
	private IVistaSoporteServicio vistaSoporteServicio;
	
	@Autowired
	private IDescobroServicio descobroServicio;
	
	private boolean esUnCobroDeShiva = true;
	private String SEPARADOR_NUMERO_LINEA = "-";
	
	/**
	 * Se encarga de procesar un archivo de reversion de cobros.
	 * @param archivo
	 * @throws ShivaExcepcion
	 * @throws NegocioExcepcion
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public boolean procesarArchivoReversion(File archivo) throws ShivaExcepcion, NegocioExcepcion {
		
		Traza.auditoria(this.getClass(), "Se procesara el archivo nombre:" + archivo.getName());

		// variable utilizada para retornar
		boolean procesamientoTotal = true;
		//Esta lista se usa para guardar las lineas que validaron bien y que 
		List<String> lineasParaProcesarOrdenadas = new ArrayList<String>();
		TreeMap<String,String> logProcesamiento = new TreeMap<String,String>();
		
		// Recorrer las lineas del archivo y valido cada una. Las correctas las guardo para procesarlas
		String[] lineas = ControlArchivo.leerArchivo(archivo.getAbsolutePath(), Constantes.RETORNO_UNIX);
		
		if (!Validaciones.isObjectNull(lineas)) {
			int numeroLinea = 0;
			for (String linea : lineas) {
				numeroLinea++;
				// valido la linea. Si En caso de error informo por el log y mando mail
				String resultadoValidacion = validarLinea(numeroLinea, linea);
				if (Validaciones.isNullOrEmpty(resultadoValidacion)) {
					lineasParaProcesarOrdenadas.add(numeroLinea+SEPARADOR_NUMERO_LINEA+linea);
				} else {
					logProcesamiento.put(String.valueOf(numeroLinea),resultadoValidacion);
					// Si la validacion da false, guardo en la variable de retorno el false. 
					procesamientoTotal = false;
				}
			}
			
			if (Validaciones.isCollectionNotEmpty(lineasParaProcesarOrdenadas)) {
				Collections.sort(lineasParaProcesarOrdenadas, new Comparator<String>() {
					public int compare(String o1, String o2) {
						String idOperacionTransaccion1 = o1.split("\\"+SEPARADOR_NUMERO_LINEA)[1].split("\\|")[1];
						Long idOperacion1 = Long.valueOf(Utilidad.removeStartingZeros(idOperacionTransaccion1.split("\\.")[0]));
						
						String idOperacionTransaccion2 = o2.split("\\"+SEPARADOR_NUMERO_LINEA)[1].split("\\|")[1];
						Long idOperacion2 = Long.valueOf(Utilidad.removeStartingZeros(idOperacionTransaccion2.split("\\.")[0]));
						
						return idOperacion1.compareTo(idOperacion2);
					}
				});
				
				//Procesar la lista
				List<String> lineasMismaOperacion = new ArrayList<String>();
				for (String linea : lineasParaProcesarOrdenadas) {
					if(Validaciones.isCollectionNotEmpty(lineasMismaOperacion)){
						String idOperacion = Utilidad.removeStartingZeros(lineasMismaOperacion.get(0).split("\\"+SEPARADOR_NUMERO_LINEA)[1].split("\\|")[1].split("\\.")[0]);
						if(idOperacion.equalsIgnoreCase(Utilidad.removeStartingZeros(linea.split("\\|")[1].split("\\.")[0]))){
							//Si es la misma operacion lo agrego a la lista
							lineasMismaOperacion.add(linea);
						}else{
							//Si no es la misma operacion. Proceso la operacion con sus lineas
							procesarLineasMismaOperacion(lineasMismaOperacion,logProcesamiento);
							lineasMismaOperacion = new ArrayList<String>();
							lineasMismaOperacion.add(linea);
						}
					}else{
						// Si la lista esta vacia lo  
						lineasMismaOperacion.add(linea);
					}
				}
				
				if(Validaciones.isCollectionNotEmpty(lineasMismaOperacion)){
					// Si la lista tiene elementos son los de la ultimo idOperacion
					procesarLineasMismaOperacion(lineasMismaOperacion,logProcesamiento);
				}
			}
		}
			
		try {
			//Al terminar de procesar mueve el archivo de la carpeta "reversion" a la carpeta "reversion\historico" 
			ControlArchivo.moverArchivoACarpetaHistorico(archivo.getName(),Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.archivosReversiones"));
	 	} catch (NegocioExcepcion e) {
        	System.err.println(Utilidad.getStackTrace(e));
			Traza.error(getClass(), "No se ha podido mover el archivo " + archivo.getName() + " a la carpeta de historicos", e);
        }
			
		
		// mostrar en el log todo el procesamiento
	    tracearYEnviarMail(logProcesamiento, archivo.getName(),procesamientoTotal);
		// enviar mail del procesamiento
		return procesamientoTotal;
		
	}
	
	@SuppressWarnings("rawtypes")
	private void tracearYEnviarMail(TreeMap<String, String> logProcesamiento, String nombreArchivo, Boolean procesamientoTotal) throws NegocioExcepcion {
			StringBuffer cuerpo = new StringBuffer();
			
			cuerpo.append("El resultado del procesamiento del archivo " + nombreArchivo);
			
			if (procesamientoTotal) {
				cuerpo.append(" fue ok.");
			} else {
				cuerpo.append(" es el siguiente:" + "<br>");
			
				// Tracear
				Iterator it = logProcesamiento.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry linea = (Map.Entry)it.next();
		        	System.out.println(linea.getValue());
		        	Traza.auditoria(this.getClass(), linea.getValue().toString());
		        	cuerpo.append(linea.getValue() + "<br>");
				}
			}
			
			// mail
			Mail mail = new Mail(parametroServicio.getValorTexto(Mensajes.DESTINATARIOS_MAIL_PROCESO_REVERSION), 
					Utilidad.reemplazarMensajes(Mensajes.ASUNTO_MAIL_PROCESO_REVERSION, nombreArchivo), cuerpo);
			mailServicio.sendMail(mail);
		
	}

	/**
	 * Procesa los conjuntos de lineas del archivo que tienen la misma operacion.
	 * @param lineasMismaOperacion
	 * @throws NegocioExcepcion
	 */
	private void procesarLineasMismaOperacion(List<String> lineasMismaOperacion, TreeMap<String,String> logProcesamiento)  throws NegocioExcepcion{
		try{
			
			// De la primer linea saco el usuario por si no existe un descobro, al crearlo tengo que setear el usuario al workflow.
			String usuario = lineasMismaOperacion.get(0).split("\\"+SEPARADOR_NUMERO_LINEA)[1].split("\\|")[3].trim();
			
			ShvCobDescobro descobro = null;
			ShvCobCobro cobro = cobroDao.buscarCobroPorIdOperacionYNroTransaccionParaReversion(lineasMismaOperacion.get(0).split("\\"+SEPARADOR_NUMERO_LINEA)[1].split("\\|")[1].trim());
			if(!Validaciones.isObjectNull(cobro)){
				descobro = descobroDao.buscarDescobroPorIdCobro(cobro.getIdCobro());
			}
			
			for (String numeroLinea : lineasMismaOperacion) {
				String nroLinea = numeroLinea.split("\\"+SEPARADOR_NUMERO_LINEA)[0];
				String linea = numeroLinea.split("\\"+SEPARADOR_NUMERO_LINEA)[1];
				
				Traza.auditoria(this.getClass(), "Se procesara la linea numero: " + nroLinea + " ("+ linea + ")");
				
				String[] campos = linea.split("\\|");
				String cobrador = campos[2].trim();
				TipoOperacionReversionEnum tipoOperacion = TipoOperacionReversionEnum.getEnumByDescripcion(campos[0].trim());
				String idOperacionTransaccion = campos[1].trim();
				Integer numeroTransaccion = Integer.valueOf(Utilidad.removeStartingZeros(idOperacionTransaccion.split("\\.")[1]).trim());
				
				if(TipoOperacionReversionEnum.esTipoOperacionValor(tipoOperacion)){
					
					// Reversion de un valor
					revertirValor(linea, tipoOperacion);
					esUnCobroDeShiva = false;
				}else{
					// Busco el cobro. Si se llego a este punto ya valide previamente que el cobro exista y este en condiciones de desapropiar
//					ShvCobCobro cobro = cobroDao.buscarCobroPorIdOperacionYNroTransaccionParaReversion(lineasMismaOperacion.get(0).split("\\"+SEPARADOR_NUMERO_LINEA)[1].split("\\|")[1].trim());
					
					
					esUnCobroDeShiva = true;
					List<ShvValValorSimplificado> listaValoresSimplificados = obtenerValoresSimplificadosDelCobro(cobro.getTransaccionesOrdenadas());
					
					if(SistemaEnum.MIC.name().equals(cobrador)){
						if(!Validaciones.isObjectNull(cobro)){
//							descobro = descobroDao.buscarDescobroPorIdCobro(cobro.getIdCobro());
							if(Validaciones.isObjectNull(descobro)){
								// crea descobro
								ShvCobDescobro descobroNuevo = new ShvCobDescobro();
								//inicializo la operacion del descobro
								descobroNuevo.setOrigenDescobro(SistemaEnum.OTRO);
								ShvCobOperacion operacion = inicializarOperacionDeDescobro(cobro.getOperacion());
								ShvWfWorkflow workflowDescobro = workflowDescobroOrigenCobrador.crearWorkflow("[Batch Reversion de cobros] Se crea el Descobro a partir del cobro id: " + cobro.getIdCobro(), usuario);
								descobroNuevo.setWorkflow(workflowDescobro);
								descobroNuevo.setOperacion(operacion);
								descobroNuevo.setIdCobro(cobro.getIdCobro());
								descobroNuevo.setMonedaOperacion(cobro.getMonedaOperacion());
								
								cambiarEstadoDescobroAEnProceso(descobroNuevo,usuario);
								descobro = descobroNuevo;
							}else{
								cambiarEstadoDescobroAEnProceso(descobro,usuario);
							}
						}
						
						switch (tipoOperacion) {
					    case REVERSION_FACTURA:
					    	// [Tipo de operación]|[Id de Transacción Shiva]|[MIC]|[Usuario]|[Id de Factura]
					    	String idFactura = campos[4].trim();
					    	revertirFacturaMic(descobro, cobro, numeroTransaccion,idFactura,listaValoresSimplificados);
					    	break;
					    case REVERSION_REMANENTE:
					    	//[Tipo de operación]|[Id de Transacción Shiva]|[MIC]|[Usuario]|[Cuenta de Remanente]|[Tipo de Remanente]
							String cuentaRemanente = campos[4].trim();
							String tipoRemanente = campos[5].trim();
							revertirRemanente(descobro, numeroTransaccion, cuentaRemanente, tipoRemanente);
					    	break;
					    case REVERSION_NOTA_CREDITO:
					    	//[Tipo de operación]|[Id de Transacción Shiva]|[MIC]|[Usuario]|[Id de Nota de Crédito]
					    	String idNotaCredito = campos[4].trim();
					    	revertirNotaCredito(descobro, numeroTransaccion, idNotaCredito);
				    	default:
						};
					}else{
						if(SistemaEnum.CALIPSO.name().equals(cobrador)){
							String tipoComprobante = campos[4].trim();
							char claseComprobante = campos[5].trim().charAt(0);//Devuelve el primer caracter del string, no importa cuantos tenga.
							String sucursalComprobante = Utilidad.removeStartingZeros(campos[6].trim());
							String numeroComprobante = Utilidad.removeStartingZeros(campos[7].trim());
							
							if(!Validaciones.isObjectNull(cobro)){
//								descobro = descobroDao.buscarDescobroPorIdCobro(cobro.getIdCobro());
								if(Validaciones.isObjectNull(descobro)){
									// crea descobro
									ShvCobDescobro descobroNuevo = new ShvCobDescobro();
									//inicializo la operacion del descobro
									descobroNuevo.setOrigenDescobro(SistemaEnum.OTRO);
									ShvCobOperacion operacion = inicializarOperacionDeDescobro(cobro.getOperacion());
									ShvWfWorkflow workflowDescobro = workflowDescobroOrigenCobrador.crearWorkflow("[Batch Reversion de cobros] Se crea el Descobro a partir del cobro id: " + cobro.getIdCobro(), usuario);
									descobroNuevo.setWorkflow(workflowDescobro);
									descobroNuevo.setOperacion(operacion);
									descobroNuevo.setIdCobro(cobro.getIdCobro());
									descobroNuevo.setMonedaOperacion(cobro.getMonedaOperacion());
									cambiarEstadoDescobroAEnProceso(descobroNuevo,usuario);
									descobro = descobroNuevo;
								}else{
									cambiarEstadoDescobroAEnProceso(descobro,usuario);
								}
							}
							switch (tipoOperacion) {
					    	case REVERSION_FACTURA:
					    		//[Tipo de operación]|[Id de Transacción Shiva]|[CALIPSO]|[Usuario]|[tipo comprobante]|[clase comprobante]|[sucursal del comprobante]|[numero del comprobante]
					    		revertirFacturaCalipso(descobro,cobro,numeroTransaccion,tipoComprobante,claseComprobante,sucursalComprobante,numeroComprobante,listaValoresSimplificados);
						    	break;
					    	case REVERSION_CTA:
					    		//[Tipo de operación]|[Id de Transacción Shiva]|[CALIPSO]|[Usuario]|[tipo comprobante]|[clase comprobante]|[sucursal del comprobante]|[numero del comprobante]
					    		revertirCTA(descobro, numeroTransaccion, tipoComprobante,claseComprobante,sucursalComprobante,numeroComprobante);
					    		break;
					    	case REVERSION_NOTA_CREDITO:
					    		//[Tipo de operación]|[Id de Transacción Shiva]|[CALIPSO]|[Usuario]|[tipo comprobante]|[clase comprobante]|[sucursal del comprobante]|[numero del comprobante]
					    		revertirNotaCreditoCalipso(descobro, numeroTransaccion, tipoComprobante,claseComprobante,sucursalComprobante,numeroComprobante);
					    		break;
					    	default:
							};
						}
					}
					revertirMedioPagoShivaTratamiento(descobro, cobro, listaValoresSimplificados);
					
					
					
					//Procesar descobros
					if (esUnCobroDeShiva) {
						actualizarEstadoDescobros(descobro, cobro, usuario);
						
						for(ShvValValorSimplificado valor : listaValoresSimplificados){
							valorDao.actualizarValorSimplificado(valor);
						}
					}
				}
			}//fin de for de lineasMismaOperacion
			
			for (String numeroLinea : lineasMismaOperacion) {
				String nroLinea = numeroLinea.split("\\"+SEPARADOR_NUMERO_LINEA)[0];
				logProcesamiento.put(nroLinea, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.proceso.ok"), nroLinea));
			}
			
		} catch (NegocioExcepcion e) {
			throw new NegocioExcepcion(e);
		} catch (Throwable e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
	}
	
	private List<ShvValValorSimplificado> obtenerValoresSimplificadosDelCobro(List<ShvCobTransaccion> listaTransaccionesOrdenadas) throws NegocioExcepcion {
		
		List<ShvValValorSimplificado> listaValoresSimplificados = new ArrayList<ShvValValorSimplificado>();
		List<Long> listaIdValores = new ArrayList<Long>();
		
		for (ShvCobTransaccion transaccion : listaTransaccionesOrdenadas) {
			
			for (ShvCobMedioPago medioPago : transaccion.getMediosPago()){
				if (medioPago instanceof ShvCobMedioPagoShiva){
					ShvCobMedioPagoShiva medioPagoShiva = (ShvCobMedioPagoShiva)medioPago;
					listaIdValores.add(medioPagoShiva.getIdValor());
					
				}
			}
		}
				
			try {
				listaValoresSimplificados = valorServicio.listarValoresSimplificados(listaIdValores);
			} catch (NegocioExcepcion e) {
				throw new NegocioExcepcion(e.getMessage());
			}
		
		return listaValoresSimplificados;
	}

	/**
	 * Valida la linea e informa por Trazas los errores que tiene la linea.
	 * @param nroLinea
	 * @param linea
	 * @return retorna si es valida la linea o no
	 */
	private String validarLinea(int numeroLinea, String linea) throws NegocioExcepcion{
		try {
			String mensajeTraza = "";
			
			String nroLinea = String.valueOf(numeroLinea);
			String[] campos = linea.split("\\|");
			
			String tipoOperacionRegistro = campos[0].trim();
			TipoOperacionReversionEnum tipoOperacion = TipoOperacionReversionEnum.getEnumByDescripcion(tipoOperacionRegistro);
			
			//tipo operacion
			if(Validaciones.isObjectNull(tipoOperacion)){
				mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.tipoOperacion.incorrecto"), nroLinea, tipoOperacionRegistro); 
				return mensajeTraza;
			}
			
			// 1° dividir las validaciones por tipo Operacion
			if(TipoOperacionReversionEnum.esTipoOperacionValor(tipoOperacion)){
				// Si es de valor por reversion
				// No le doy importancia a lo que venga en ID de Transacción Shiva porque siempre va a venir 0000000.00000
				return validarLineaDeValorReversion(nroLinea, linea,tipoOperacion);
			}else{
				ShvCobCobro cobro = null;
				Map<String, String> datosMensaje = new HashMap<String, String>();
	    		datosMensaje.put("{0}", nroLinea);
	    		datosMensaje.put("{2}", linea);
	    		
	    		//idOperacion.numeroTransaccion
	    		String idOperacionTransaccion = campos[1];
				if(!Validaciones.isNullOrEmpty(campos[1]) ){
					cobro = cobroDao.buscarCobroPorIdOperacionYNroTransaccionParaReversion(campos[1]);
				}else{
					mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.operacion.vacia"), nroLinea, campos[1]); 
					return mensajeTraza;
				}
				
				
				String idOperacion = idOperacionTransaccion.split("\\.")[0];
				//Id operacion
				if(!Validaciones.isNumeric(idOperacion)){
					mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.operacion.numerico"), nroLinea, idOperacion); 
				}
				
				String numeroTransaccion = idOperacionTransaccion.split("\\.")[1];
				//numero transaccion
				if(!Validaciones.isNumeric(numeroTransaccion)){
					mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.transaccion.numerico"), nroLinea, numeroTransaccion);
				}
				
				//cobro
				if(Validaciones.isObjectNull(cobro)){
					mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.cobro.incorrecto"), nroLinea, idOperacionTransaccion);
					return mensajeTraza;
				}
				
				//descobro
				ShvCobDescobro descobro = descobroDao.buscarDescobroPorIdCobro(cobro.getIdCobro());
				if((!Validaciones.isObjectNull(descobro) && Estado.DES_DESCOBRADO.equals(descobro.getWorkflow().getShvWfWorkflowEstado().iterator().next().getEstado()))){
					mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.descobro.incorrecto"), nroLinea, idOperacionTransaccion);
					return mensajeTraza;
				}
				
				//cobrador
				String cobrador = campos[2].trim();
				if(!SistemaEnum.MIC.name().equals(cobrador) && !SistemaEnum.CALIPSO.name().equals(cobrador)){
					mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.cobrador.incorrecto"), nroLinea, cobrador);
				}
				
				if(SistemaEnum.MIC.name().equals(cobrador)){
					switch (tipoOperacion) {
				    case REVERSION_FACTURA:
				    	if(campos.length == 5){
				    		
				    		//Usuario
				    		String usuario = campos[3].trim();
				    		if(usuario.length() > 10 || Validaciones.isNullOrEmpty(usuario)){
					    		mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.usuario.longitud"), nroLinea, usuario);
					    	}
				    		
					    	//Id de la Factura en MIC -> alfanumerico de 15 posiciones (el mismo que se recibe en la interfaz de cobros de MIC)
				    		String idReferenciaFactura = campos[4].trim();
					    	if(!Validaciones.isAlphaNumeric(idReferenciaFactura)){
								mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.idFactura.alfanumerico"), nroLinea, idReferenciaFactura);
					    	}
					    	if(idReferenciaFactura.length() > 15){
					    		mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.idFactura.longitud"), nroLinea, idReferenciaFactura);
					    	}
				    	}else{
				    		datosMensaje.put("{1}", "5");
				    		mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.cantidad.campos"), datosMensaje);
				    	}
				    	
				    	break;
				    case REVERSION_REMANENTE:
				    	if(campos.length == 6){
				    		//Usuario
				    		String usuario = campos[3].trim();
				    		if(usuario.length() > 10 || Validaciones.isNullOrEmpty(usuario)){
					    		mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.usuario.longitud"), nroLinea, usuario);
					    	}
				    		
				    		//Cuenta de Remanente ->	Numerico de hasta 13 posiciones (el mismo que se recibe en la interfaz de cobros de MIC)
				    		String cuentaRemanente = campos[4].trim();
							if(!Validaciones.isNumeric(cuentaRemanente)){
								mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.cuentaRemanente.numerico"), nroLinea,cuentaRemanente);
							}
							if(cuentaRemanente.length() > 13){
					    		mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.cuentaRemanente.longitud"), nroLinea,cuentaRemanente);
					    	}
							
							String tipoRemanente = campos[5].trim();
							//Tipo de Remanente -> 01, 02 o 03
							if(!("0"+TipoRemanenteEnum.TRANSFERIBLE_NO_ACTUALIZABLE.codigo()).equals(tipoRemanente) && 
								!("0"+TipoRemanenteEnum.NO_TRANSFERIBLE_NO_ACTUALIZABLE.codigo()).equals(tipoRemanente) &&
								!("0"+TipoRemanenteEnum.TRANSFERIBLE_ACTUALIZABLE.codigo()).equals(tipoRemanente)){
									mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.tipoRemanente.incorrecto"), nroLinea,tipoRemanente);
							}
						}else{
							datosMensaje.put("{1}", "6");
				    		mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.cantidad.campos"), datosMensaje);
				    	}
	
				    	break;
				    case REVERSION_NOTA_CREDITO:
				    	if(campos.length == 5){
				    		
				    		//Usuario
				    		String usuario = campos[3].trim();
				    		if(usuario.length() > 10 || Validaciones.isNullOrEmpty(usuario)){
					    		mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.usuario.longitud"), nroLinea, usuario);
					    	}
				    		
					    	//Id de la Nota de Crédito en MIC, alfanumerico de 15 posiciones (el mismo que se recibe en la interfaz de cobros de MIC)
				    		String idNotaCredito = campos[4].trim();
					    	if(!Validaciones.isAlphaNumeric(idNotaCredito)){
								mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.notaCredito.alfanumerico"), nroLinea,idNotaCredito);
					    	}
					    	if(idNotaCredito.length() > 15){
					    		mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.notaCredito.longitud"), nroLinea,idNotaCredito);
					    	}
						}else{
							datosMensaje.put("{1}", "5");
				    		mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.cantidad.campos"), datosMensaje);
				    	}
			    	default:
					};
					
				}else if(SistemaEnum.CALIPSO.name().equals(cobrador)){
					if(campos.length == 8){
						String tipoComprobante = campos[4].trim();
						char claseComprobante = campos[5].trim().charAt(0);//Devuelve el primer caracter del string, no importa cuantos tenga.
						String sucursalComprobante = campos[6].trim();
						String numeroComprobante = campos[7].trim();
						
						//Usuario
			    		String usuario = campos[3].trim();
			    		if(usuario.length() > 10 || Validaciones.isNullOrEmpty(usuario)){
				    		mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.usuario.longitud"), nroLinea, usuario);
				    	}
						
						//Validaciones Genericas para todos las reversiones de cobrador Calipso
		//				Sucursal del Comprobante	Alfanumérico de 4 posiciones, 4 caracteres numéricos completados con 0 a la izquierda
						if(!Validaciones.isNumeric(sucursalComprobante)){
							mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.sucursalComprobante.numerico"), nroLinea,sucursalComprobante);
						}
						if(sucursalComprobante.length() != 4){
							mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.sucursalComprobante.longitud"), nroLinea,sucursalComprobante);
						}
						
		//				Numero del Comprobante	Alfanumérico de 8 posiciones, 8 caracteres numéricos completados con 0 a la izquierda
						if(!Validaciones.isNumeric(numeroComprobante)){
							mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.sucursalComprobante.numerico"), nroLinea,sucursalComprobante);
			    		}
						if(numeroComprobante.length() != 8){
							mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.sucursalComprobante.longitud"), nroLinea,sucursalComprobante);
						}
						
						
						switch (tipoOperacion) {
				    	case REVERSION_FACTURA:
				    		
				    		//Tipo de Comprobante	Alfanumérico de 3 posiciones, valores posibles "FAC" y "DEB"
				    		if(!TipoComprobanteEnum.FAC.name().equals(tipoComprobante) &&
				    				!TipoComprobanteEnum.DEB.name().equals(tipoComprobante)){
				    			mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.tipoComprobante.facYdeb"), nroLinea,tipoComprobante);
				    		}
				    		
				    		//Clase de Comprobante	Alfanumérico de 1 posicion, valores posibles "A", "B", "E"
				    		if(!TipoClaseComprobanteEnum.A.name().equals(String.valueOf(claseComprobante)) &&
				    				!TipoClaseComprobanteEnum.B.name().equals(String.valueOf(claseComprobante)) &&
				    				!TipoClaseComprobanteEnum.E.name().equals(String.valueOf(claseComprobante)))
				    		{
				    			mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.claseComprobante.abe"), nroLinea,String.valueOf(claseComprobante));
				    		}
				    		
					    	break;
					    	
				    	case REVERSION_CTA:
				    		//	Tipo de Comprobante	Alfanumérico de 3 posiciones, valores posibles "CTA"
				    		if(!TipoComprobanteEnum.CTA.name().equals(tipoComprobante)){
				    			mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.tipoComprobante.cta"), nroLinea,tipoComprobante);
				    		}
				    		
				    		// 	Clase de Comprobante	Alfanumérico de 1 posicion, valores posibles "X"
							if(!TipoClaseComprobanteEnum.X.name().equals(String.valueOf(claseComprobante))){
								mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.claseComprobante.x"), nroLinea,String.valueOf(claseComprobante));
				    		}
				    		break;
				    		
				    	case REVERSION_NOTA_CREDITO:
				    		// 	Tipo de Comprobante	Alfanumérico de 3 posiciones, valores posibles "CRE"
				    		if(!TipoComprobanteEnum.CRE.name().equals(tipoComprobante)){
				    			mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.tipoComprobante.cre"), nroLinea,tipoComprobante);
				    		}
				    		
				    		// 	Clase de Comprobante	Alfanumérico de 1 posicion, valores posibles "A", "B", "E"
				    		if(!TipoClaseComprobanteEnum.A.name().equals(String.valueOf(claseComprobante)) &&
				    				!TipoClaseComprobanteEnum.B.name().equals(String.valueOf(claseComprobante)) &&
				    				!TipoClaseComprobanteEnum.E.name().equals(String.valueOf(claseComprobante)))
				    		{
								mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.claseComprobante.abe"), nroLinea,String.valueOf(claseComprobante));
				    		}
				    		break;
				    	default:
						};
					}else{
						datosMensaje.put("{1}", "8");
			    		mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.cantidad.campos"), datosMensaje);
			    	}
				}
	
			}
			
			return mensajeTraza;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	@SuppressWarnings("incomplete-switch")
	private String validarLineaDeValorReversion(String nroLinea, String linea, TipoOperacionReversionEnum tipoOperacion) {
		String mensajeTraza = "";
		
		Map<String, String> datosMensaje = new HashMap<String, String>();
		datosMensaje.put("{0}", nroLinea);
		datosMensaje.put("{2}", linea);
		String[] campos = linea.split("\\|");
		
		//cobrador
		String cobrador = campos[2].trim();
		if(!SistemaEnum.MIC.name().equals(cobrador) && !SistemaEnum.CALIPSO.name().equals(cobrador)){
			mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.cobrador.incorrecto"), nroLinea, cobrador);
		}
		
		switch (tipoOperacion) {
	    	case REVERSION_VALORES_CHEQUE:
	    		if(campos.length == 10){
	    			
	    			//Usuario
		    		String usuario = campos[3].trim();
		    		if(usuario.length() > 10 || Validaciones.isNullOrEmpty(usuario)){
			    		mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.usuario.longitud"), nroLinea, usuario);
			    	}
		    		
//	    			Acuerdo 	Número de acuerdo. Para Calipso este dato vendrá vacío.
		    		mensajeTraza +=validarImporteYSaldo(nroLinea,linea);
	    			if(SistemaEnum.MIC.name().equals(cobrador)){
	    				String acuerdo = campos[6].trim();
	    				if(!Validaciones.isNumeric(acuerdo)){
	    					mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.acuerdo.numerico"), nroLinea, acuerdo);
	    				}
	    			}
	    			
//	    			Banco Origen 	Código numérico de 4 posiciones que indica el Banco origen. Se completa con ceros a izquierda de ser necesario.
	    			String bancoOrigen = campos[7].trim();
    				if(!Validaciones.isNumeric(bancoOrigen)){
    					mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.bancoOrigen.numerico"), nroLinea, bancoOrigen);
    				}
    				if(bancoOrigen.length() != 4){
    					mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.bancoOrigen.longitud"), nroLinea, bancoOrigen);
    				}
	    			
//	    			Nro. Cheque 	"Máximo de 8 posiciiones
//	    			Para MIC desde Shiva se deberán eliminar los espacios en blanco a la izquierda, en caso de que existan."
	    			String nroCheque = campos[8].trim();
    				if(!Validaciones.isNumeric(nroCheque)){
    					mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.nroCheque.numerico"),nroLinea,nroCheque);
    				}
	    			if(nroCheque.length() > 8){
	    				mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.nroCheque.longitud"),nroLinea,nroCheque);
    				}
	    		
//	    			Fecha Vencimiento	Corresponde a la Fecha de Depósito de SHIVA. El Formato debe ser DD/MM/AAAA
	    			String fechaVencimiento = campos[9].trim();
	    			if(!Validaciones.validarFechaConParse(fechaVencimiento)){
	    				mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.fechaVencimiento"),nroLinea,fechaVencimiento);
    				}
	    			
	    		}else{
	    			datosMensaje.put("{1}", "10");
	    			mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.cantidad.campos"),datosMensaje);
		    	}

	    	break;
	    	case REVERSION_VALORES_CHEQUE_DIFERIDO:
	    		if(campos.length == 10){
	    			//Usuario
		    		String usuario = campos[3].trim();
		    		if(usuario.length() > 10 || Validaciones.isNullOrEmpty(usuario)){
			    		mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.usuario.longitud"), nroLinea, usuario);
			    	}
	    			
	    			mensajeTraza +=validarImporteYSaldo(nroLinea,linea);
//	    			Acuerdo 	Número de acuerdo. Para Calipso este dato vendrá vacío.
	    			if(SistemaEnum.MIC.name().equals(cobrador)){
	    				String acuerdo = campos[6].trim();
	    				if(!Validaciones.isNumeric(acuerdo)){
	    					mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.acuerdo.numerico"), nroLinea, acuerdo);
	    				}
	    			}
	    			
//	    			Banco Origen 	Código numérico de 4 posiciones que indica el Banco origen. Se completa con ceros a izquierda de ser necesario.
	    			String bancoOrigen = campos[7].trim();
    				if(!Validaciones.isNumeric(bancoOrigen)){
    					mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.bancoOrigen.numerico"), nroLinea, bancoOrigen);
    				}
    				if(bancoOrigen.length() != 4){
    					mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.bancoOrigen.longitud"), nroLinea, bancoOrigen);
    				}
	    			
//	    			Nro. Cheque 	"Máximo de 8 posiciones
//	    			Para MIC desde Shiva se deberán eliminar los espacios en blanco a la izquierda, en caso de que existan."
	    			String nroCheque = campos[8].trim();
    				if(!Validaciones.isNumeric(nroCheque)){
    					mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.nroCheque.numerico"),nroLinea,nroCheque);
    				}
	    			if(nroCheque.length() > 8){
	    				mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.nroCheque.longitud"),nroLinea,nroCheque);
    				}
	    			
//	    			Fecha Vencimiento 	Fecha de vencimiento del cheque. El Formato debe ser DD/MM/AAAA
	    			String fechaVencimiento = campos[9].trim();
	    			if(!Validaciones.validarFechaConParse(fechaVencimiento)){
	    				mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.fechaVencimiento"),nroLinea,fechaVencimiento);
    				}
	    			
	    		}else{
	    			datosMensaje.put("{1}", "10");
	    			mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.cantidad.campos"),datosMensaje);
		    	}

	    		break;
	    	case REVERSION_VALORES_EFECTIVO:
	    		if(campos.length == 9){
	    			//Usuario
		    		String usuario = campos[3].trim();
		    		if(usuario.length() > 10 || Validaciones.isNullOrEmpty(usuario)){
			    		mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.usuario.longitud"), nroLinea, usuario);
			    	}
		    		
	    			mensajeTraza +=validarImporteYSaldo(nroLinea,linea);
//	    			Acuerdo 	Número de acuerdo. Para Calipso este dato vendrá vacío.
	    			if(SistemaEnum.MIC.name().equals(cobrador)){
	    				String acuerdo = campos[6].trim();
	    				if(!Validaciones.isNumeric(acuerdo)){
	    					mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.acuerdo.numerico"), nroLinea, acuerdo);
	    				}
	    			}
	    			
//	    			Nro. Boleta 	Númeríco
	    			String nroBoleta = campos[7].trim();
    				if(!Validaciones.isNumeric(nroBoleta)){
    					mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.nroBoleta.numerico"),nroLinea,nroBoleta);
    				}
	    			
//	    			Fecha de Depósito	Fecha de Depósito. El Formato debe ser DD/MM/AAAA
	    			String fechaDeposito = campos[8].trim();
	    			if(!Validaciones.validarFechaConParse(fechaDeposito)){
	    				mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.fechaDeposito"),nroLinea,fechaDeposito);
    				}
	    			
	    		}else{
	    			datosMensaje.put("{1}", "9");
	    			mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.cantidad.campos"),datosMensaje);
		    	}
	    		break;
	    		
	    	case REVERSION_VALORES_TRANSFERENCIA:
	    		if(campos.length == 10){
	    			//Usuario
		    		String usuario = campos[3].trim();
		    		if(usuario.length() > 10 || Validaciones.isNullOrEmpty(usuario)){
			    		mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.usuario.longitud"), nroLinea, usuario);
			    	}
		    		
	    			mensajeTraza +=validarImporteYSaldo(nroLinea,linea);
//	    		 	Acuerdo 	Número de acuerdo.
	    			String acuerdo = campos[6].trim();
    				if(!Validaciones.isNumeric(acuerdo)){
    					mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.acuerdo.numerico"), nroLinea, acuerdo);
    				}
    				
//	    		 	Banco Origen 	Código numérico de 4 posiciones que indica el Banco origen. Se completa con ceros a izquierda de ser necesario.
    				String bancoOrigen = campos[7].trim();
    				if(!Validaciones.isNumeric(bancoOrigen)){
    					mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.bancoOrigen.numerico"), nroLinea, bancoOrigen);
    				}
    				if(bancoOrigen.length() != 4){
    					mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.bancoOrigen.longitud"), nroLinea, bancoOrigen);
    				}
    				
//	    		 	Nro. Referencia 	Númerico
    				String nroReferencia = campos[8].trim();
    				if(!Validaciones.isNumeric(nroReferencia)){
    					mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.nroReferencia.numerico"),nroLinea,nroReferencia);
    				}
    				
//	    		 	Fecha Depósito	Fecha de Depósito. El Formato debe ser DD/MM/AAAA
	    			String fechaDeposito = campos[9].trim();
	    			if(!Validaciones.validarFechaConParse(fechaDeposito)){
	    				mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.fechaDeposito"),nroLinea,fechaDeposito);
    				}
	    			
	    		}else{
	    			datosMensaje.put("{1}", "10");
	    			mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.cantidad.campos"),datosMensaje);
		    	}
	    		break;
	    		
	    	case REVERSION_VALORES_INTERDEPOSITO:
	    		if(campos.length == 11){
	    			//Usuario
		    		String usuario = campos[3].trim();
		    		if(usuario.length() > 10 || Validaciones.isNullOrEmpty(usuario)){
			    		mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.usuario.longitud"), nroLinea, usuario);
			    	}
		    		
	    			mensajeTraza += validarImporteYSaldo(nroLinea,linea);
	    			//Acuerdo 	Número de acuerdo. Para MIC y CALIPSO viene vacio

	    			//Banco Origen 	Código numérico de 4 posiciones que indica el Banco origen. Se completa con ceros a izquierda de ser necesario.
	    			String bancoOrigen = campos[7].trim();
	    			if(!Validaciones.isNumeric(bancoOrigen)){
	    				mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.bancoOrigen.numerico"), nroLinea, bancoOrigen);
	    			}else{
	    				//Verifico que sea un codigo esperado 
	    				if (!bancoOrigen.equals(Constantes.BANCO_GALICIA) && !bancoOrigen.equals(Constantes.BANCO_CIUDAD) 
	    						&& !bancoOrigen.equals(Constantes.BANCO_PROVINCIA_0014) && !bancoOrigen.equals(Constantes.BANCO_PROVINCIA_0148)) {
	    					mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.bancoOrigen.incorrecto"), nroLinea, bancoOrigen);
	    				}
	    			}
	    			if(bancoOrigen.length() != 4){
	    				mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.bancoOrigen.longitud"), nroLinea, bancoOrigen);
	    			}
	    			
//	    		 	Nro. de Interdepósito CDIF 	Numérico
    				String nroInterdeposito = campos[8].trim();
    				if(!Validaciones.isNumeric(nroInterdeposito)){
    					mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.nroInterdeposito.numerico"),nroLinea,nroInterdeposito);
    				}
    				
//	    		 	Código de Organismo 	Alfanumérico de 3 posiciones
    				String codigoOrganismo = campos[9].trim();
    				if(!Validaciones.isAlphaNumeric(codigoOrganismo)){
    					mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.codigoOrganismo.alfanumerico"),nroLinea,codigoOrganismo);
    				}
    				if(codigoOrganismo.length() != 3){
    					mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.codigoOrganismo.longitud"),nroLinea,codigoOrganismo);
    				}
	    			
//	    		 	Fecha Depósito	Fecha de Depósito. El Formato debe ser DD/MM/AAAA
	    			String fechaDeposito = campos[10].trim();
	    			if(!Validaciones.validarFechaConParse(fechaDeposito)){
	    				mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.fechaDeposito"),nroLinea,fechaDeposito);
    				}
	    			
	    		}else{
	    			datosMensaje.put("{1}", "11");
	    			mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.cantidad.campos"),datosMensaje);
		    	}

	    		break;
		}
		
		return mensajeTraza;
	}

	/**
	 * Valida el importe y el saldo para los registro de reversion de tipo valor.
	 * @param nroLinea
	 * @param linea
	 * @return
	 */
	private String validarImporteYSaldo(String nroLinea, String linea) {
		String mensajeTraza = "";
		//		Importe original	"Acepta separador de miles (punto) y separador de decimales (coma). 
		//		Para MIC desde Shiva se deberán eliminar los espacios en blanco a la izquierda, en caso de que existan."
		String importe = linea.split("\\|")[4].trim();
		if(!Validaciones.isImporteFormato(Utilidad.removeStartingZeros(importe), 2)){
			mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.importe.formato"),nroLinea,importe);	
		}
		//		Saldo reversado	"Acepta separador de miles (punto) y separador de decimales (coma)
		//		Para MIC desde Shiva se deberán eliminar los espacios en blanco a la izquierda, en caso de que existan."
		String saldo = linea.split("\\|")[5].trim();
		if(!Validaciones.isImporteFormato(Utilidad.removeStartingZeros(saldo), 2)){
			mensajeTraza += Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.validacion.error.saldo.formato"),nroLinea,saldo);	
		}
		
		return mensajeTraza;
		
	}

	/**
	 * Recorre la lista de descobros y actualiza sus estados segun los estados 
	 * de sus Medios de pago y Facturas.
	 * @param descobros
	 * @throws NegocioExcepcion
	 */
	public void actualizarEstadoDescobros(ShvCobDescobro descobro, ShvCobCobro cobro, String usuario) throws NegocioExcepcion{
		try{
			
			//Actualizo los estados de las transacciones del descobro.
			actualizarEstadoTransaccionesPendientesDescobro(descobro);
			
			Boolean existenMPCalipsoPendientes = existenMedioPagoCalipsoPendientesDescobro(descobro);
			Boolean existenMPMicPendientes = existenMedioPagoMicPendientesDescobro(descobro);
			Boolean existenFacturasCapilsoPendientes = existenFacturasCalipsoPendientesDescobro(descobro);
			Boolean existenFacturasMicPendientes = existenFacturasMicPendientesDescobro(descobro);
			ShvWfWorkflow workflowActual = descobro.getWorkflow();
			
			if(!existenMPCalipsoPendientes && !existenMPMicPendientes
					&& !existenFacturasCapilsoPendientes && !existenFacturasMicPendientes){
				//DES_DESCOBRADO("Descobrado"),
				ShvWfWorkflow workflowActualizado = workflowDescobroOrigenCobrador.descobrarCobro(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a Descobrado", usuario);
				descobro.setWorkflow(workflowActualizado);
			}else{
				if(existenMPCalipsoPendientes && existenMPMicPendientes
						&& existenFacturasCapilsoPendientes && existenFacturasMicPendientes){
					//DES_AMBOS_COBRADORES("Pendiente reverso documentos y medios de pago en cobradores"),
					ShvWfWorkflow workflowActualizado = workflowDescobroOrigenCobrador.regPendReversoDocMedioCobradores(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a Pendiente reverso documentos y medios de pago en cobradores", usuario);
					descobro.setWorkflow(workflowActualizado);
				}else{
					if(!existenMPCalipsoPendientes && existenMPMicPendientes
							&& existenFacturasCapilsoPendientes && existenFacturasMicPendientes){
						//DES_DOCUMENTOS_COBRADORES_MEDIOS_MIC("Pendiente reverso documentos en cobradores y medios de pago en MIC"),
						ShvWfWorkflow workflowActualizado = workflowDescobroOrigenCobrador.regPendReversoDocCobradoresMedioMic(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a Pendiente reverso documentos en cobradores y medios de pago en MIC", usuario);
						descobro.setWorkflow(workflowActualizado);
					}else{
						if(existenMPCalipsoPendientes && !existenMPMicPendientes
								&& existenFacturasCapilsoPendientes && existenFacturasMicPendientes){
							//DES_DOCUMENTOS_COBRADORES_MEDIOS_CALIPSO("Pendiente reverso documentos en cobradores y medios de pago en CALIPSO"),
							ShvWfWorkflow workflowActualizado = workflowDescobroOrigenCobrador.regPendReversoDocCobradoresMedioCalipso(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a Pendiente reverso documentos en cobradores y medios de pago en CALIPSO", usuario);
							descobro.setWorkflow(workflowActualizado);
						}else{
							if(existenMPCalipsoPendientes && existenMPMicPendientes
									&& !existenFacturasCapilsoPendientes && existenFacturasMicPendientes){
								//DES_DOCUMENTO_MIC_MEDIOS_COBRADORES("Pendiente reverso documentos en MIC y medios de pago en cobradores"),
								ShvWfWorkflow workflowActualizado = workflowDescobroOrigenCobrador.regPendReversoDocMicMedioCobradores(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a Pendiente reverso documentos en MIC y medios de pago en cobradores", usuario);
								descobro.setWorkflow(workflowActualizado);
							}else{
								if(existenMPCalipsoPendientes && existenMPMicPendientes
										&& existenFacturasCapilsoPendientes && !existenFacturasMicPendientes){
									//DES_DOCUMENTO_CALIPSO_MEDIOS_COBRADORES("Pendiente reverso documentos en CALIPSO y medios de pago en cobradores"),
									ShvWfWorkflow workflowActualizado = workflowDescobroOrigenCobrador.regPendReversoDocCalipsoMedioCobradores(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a Pendiente reverso documentos en CALIPSO y medios de pago en cobradores", usuario);
									descobro.setWorkflow(workflowActualizado);
								}else{
									if(!existenMPCalipsoPendientes && !existenMPMicPendientes
											&& existenFacturasCapilsoPendientes && existenFacturasMicPendientes){
										//DES_DOCUMENTO_COBRADORES("Pendiente reverso documentos en cobradores"),
										ShvWfWorkflow workflowActualizado = workflowDescobroOrigenCobrador.regPendReversoDocCobradores(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a Pendiente reverso documentos en cobradores", usuario);
										descobro.setWorkflow(workflowActualizado);
									}else{
										if(existenMPCalipsoPendientes && existenMPMicPendientes
												&& !existenFacturasCapilsoPendientes && !existenFacturasMicPendientes){
											//DES_MEDIOS_COBRADORES("Pendiente reverso medios de pago en cobradores"),
											ShvWfWorkflow workflowActualizado = workflowDescobroOrigenCobrador.regPendReversoMedioCobradores(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a Pendiente reverso medios de pago en cobradores", usuario);
											descobro.setWorkflow(workflowActualizado);
										}else{
											if(existenMPCalipsoPendientes && !existenMPMicPendientes
													&& existenFacturasCapilsoPendientes && !existenFacturasMicPendientes){
												//DES_AMBOS_CALIPSO("Pendiente reverso medios de pago y documentos en Calipso"),
												ShvWfWorkflow workflowActualizado = workflowDescobroOrigenCobrador.regPendReversoDocMedioCalipso(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a Pendiente reverso medios de pago y documentos en Calipso", usuario);
												descobro.setWorkflow(workflowActualizado);
											}else{
												if(!existenMPCalipsoPendientes && !existenMPMicPendientes
														&& existenFacturasCapilsoPendientes && !existenFacturasMicPendientes){
													//DES_DOCUMENTO_CALIPSO("Pendiente reverso documentos en Calipso"),
													ShvWfWorkflow workflowActualizado = workflowDescobroOrigenCobrador.regPendReversoDocCalipso(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a Pendiente reverso documentos en Calipso", usuario);
													descobro.setWorkflow(workflowActualizado);
												}else{
													if(existenMPCalipsoPendientes && !existenMPMicPendientes
															&& !existenFacturasCapilsoPendientes && !existenFacturasMicPendientes){
														//DES_MEDIOS_CALIPSO("Pendiente reverso medios de pago en Calipso"),
														ShvWfWorkflow workflowActualizado = workflowDescobroOrigenCobrador.regPendReversoMedioCalipso(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a Pendiente reverso medios de pago en Calipso", usuario);
														descobro.setWorkflow(workflowActualizado);
													}else{
														if(!existenMPCalipsoPendientes && existenMPMicPendientes
																&& !existenFacturasCapilsoPendientes && existenFacturasMicPendientes){
															//DES_AMBOS_MIC("Pendiente reverso medios de pago y documentos en MIC"),
															ShvWfWorkflow workflowActualizado = workflowDescobroOrigenCobrador.regPendReversoDocMedioMic(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a Pendiente reverso medios de pago y documentos en MIC", usuario);
															descobro.setWorkflow(workflowActualizado);
														}else{
															if(!existenMPCalipsoPendientes && existenMPMicPendientes
																	&& !existenFacturasCapilsoPendientes && !existenFacturasMicPendientes){
																//DES_MEDIOS_MIC("Pendiente reverso medios de pago en MIC"),
																ShvWfWorkflow workflowActualizado = workflowDescobroOrigenCobrador.regPendReversoMedioMic(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a Pendiente reverso medios de pago en MIC", usuario);
																descobro.setWorkflow(workflowActualizado);
															}else{
																if(!existenMPCalipsoPendientes && !existenMPMicPendientes
																		&& !existenFacturasCapilsoPendientes && existenFacturasMicPendientes){
																	//DES_DOCUMENTO_MIC("Pendiente reverso documentos en MIC"),
																	ShvWfWorkflow workflowActualizado = workflowDescobroOrigenCobrador.regPendReversoDocMic(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a Pendiente reverso documentos en MIC", usuario);
																	descobro.setWorkflow(workflowActualizado);
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			descobroDao.modificar(descobro);
			cobroDao.modificar(cobro);
			tracearDatosImputacionDescobro(descobro, false);
			
			if(descobro.getWorkflow().getEstado().equals(Estado.DES_DESCOBRADO)){
				descobroServicio.informarAContabilidadYScard(descobro);
			}
				
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}

	/**
	 * Actualiza el estado del descobro a "DESCOBRO EN PROCESO".
	 * @param descobro
	 * @param descobros
	 * @throws NegocioExcepcion
	 */
	private void cambiarEstadoDescobroAEnProceso(ShvCobDescobro descobro, String usuario)  throws NegocioExcepcion{
		
		// Actualizo el estado a "DESCOBRO EN PROCESO"
		ShvWfWorkflow workflowActual = descobro.getWorkflow();
		switch((workflowActual.getShvWfWorkflowEstado().iterator().next()).getEstado()){
		case DES_IMPUTAR:
			ShvWfWorkflow workflowImputar = workflowDescobroOrigenCobrador.iniciarDescobroOrigenCobrador(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a descobro en proceso", usuario);
			descobro.setWorkflow(workflowImputar);
			break;
		case DES_AMBOS_COBRADORES:
			ShvWfWorkflow workflowAmbosCobradores = workflowDescobroOrigenCobrador.procPendReversoDocMedioCobradores(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a descobro en proceso", usuario);
			descobro.setWorkflow(workflowAmbosCobradores);
			break;
		case DES_DOCUMENTOS_COBRADORES_MEDIOS_MIC:
			ShvWfWorkflow workflowDocCobradoresMediosMic = workflowDescobroOrigenCobrador.procPendReversoDocCobradoresMedioMic(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a descobro en proceso", usuario);
			descobro.setWorkflow(workflowDocCobradoresMediosMic);
			break;
		case DES_DOCUMENTOS_COBRADORES_MEDIOS_CALIPSO:
			ShvWfWorkflow workflowDocCobradoresMediosCalipso = workflowDescobroOrigenCobrador.procPendReversoDocCobradoresMedioCalipso(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a descobro en proceso", usuario);
			descobro.setWorkflow(workflowDocCobradoresMediosCalipso);
			break;
		case DES_DOCUMENTO_MIC_MEDIOS_COBRADORES:
			ShvWfWorkflow workflowDocMicMediosCobradores = workflowDescobroOrigenCobrador.procPendReversoDocMicMedioCobradores(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a descobro en proceso", usuario);
			descobro.setWorkflow(workflowDocMicMediosCobradores);
			break;
		case DES_DOCUMENTO_CALIPSO_MEDIOS_COBRADORES:
			ShvWfWorkflow workflowDocCalipsoMediosCobradores = workflowDescobroOrigenCobrador.procPendReversoDocCalipsoMedioCobradores(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a descobro en proceso", usuario);
			descobro.setWorkflow(workflowDocCalipsoMediosCobradores);
			break;
		case DES_DOCUMENTO_COBRADORES:
			ShvWfWorkflow workflowActualizado = workflowDescobroOrigenCobrador.procPendReversoDocCobradores(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a descobro en proceso", usuario);
			descobro.setWorkflow(workflowActualizado);
			break;
		case DES_MEDIOS_COBRADORES:
			ShvWfWorkflow workflowMediosCobradores = workflowDescobroOrigenCobrador.procPendReversoMedioCobradores(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a descobro en proceso", usuario);
			descobro.setWorkflow(workflowMediosCobradores);
			break;
		case DES_AMBOS_CALIPSO:
			ShvWfWorkflow workflowAmbosCalipso = workflowDescobroOrigenCobrador.procPendReversoDocMedioCalipso(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a descobro en proceso", usuario);
			descobro.setWorkflow(workflowAmbosCalipso);
			break;
		case DES_DOCUMENTO_CALIPSO:
			ShvWfWorkflow workflowDocumentoCalipso = workflowDescobroOrigenCobrador.procPendReversoDocCalipso(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a descobro en proceso", usuario);
			descobro.setWorkflow(workflowDocumentoCalipso);
			break;
		case DES_MEDIOS_CALIPSO:
			ShvWfWorkflow workflowMediosCalipso = workflowDescobroOrigenCobrador.procPendReversoMedioCalipso(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a descobro en proceso", usuario);
			descobro.setWorkflow(workflowMediosCalipso);
			break;
		case DES_AMBOS_MIC:
			ShvWfWorkflow workflowAmbosMic = workflowDescobroOrigenCobrador.procPendReversoDocMedioMic(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a descobro en proceso", usuario);
			descobro.setWorkflow(workflowAmbosMic);
			break;
		case DES_MEDIOS_MIC:
			ShvWfWorkflow workflowMediosMic = workflowDescobroOrigenCobrador.procPendReversoMedioMic(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a descobro en proceso", usuario);
			descobro.setWorkflow(workflowMediosMic);
			break;
		case DES_DOCUMENTO_MIC:
			ShvWfWorkflow workflowDocumentoMic = workflowDescobroOrigenCobrador.procPendReversoDocMic(workflowActual, "[Batch Reversion de cobros] Se cambia el estado a descobro en proceso", usuario);
			descobro.setWorkflow(workflowDocumentoMic);
			break;
		default:
			break;
		};
	}
	
	/**
	 * Verifica si existe al menos una factura de tipo Mic en estado
	 * "Pendiente de descobro" en todas las Transacciones del descobro.
	 * @param descobro
	 * @return
	 */
	private Boolean existenFacturasMicPendientesDescobro(ShvCobDescobro descobro) {
		for(ShvCobTransaccion transaccion : descobro.getOperacion().getTransacciones()){
			if(!EstadoTransaccionEnum.DESCOBRO.equals(transaccion.getEstadoProcesamiento())){
				//Si no esta en estado Descobro, verifico si lo que falta revertir son las facturas de Mic
				if(transaccion.getFactura() instanceof ShvCobFacturaMic
						&& EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(transaccion.getFactura().getEstado())){
					//Si la Factura es de tipo Mic y esta en "pendiente de descobro", 
					//asumo que todavia hay por lo menos una factura Pendiente
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Verifica si existe al menos una factura de tipo Calipso en estado
	 * "Pendiente de descobro" en todas las Transacciones del descobro.
	 * @param descobro
	 * @return
	 */
	private Boolean existenFacturasCalipsoPendientesDescobro(ShvCobDescobro descobro) {
		for(ShvCobTransaccion transaccion : descobro.getOperacion().getTransacciones()){
			if(!EstadoTransaccionEnum.DESCOBRO.equals(transaccion.getEstadoProcesamiento())){
				
				if(!Validaciones.isObjectNull(transaccion.getFactura())){
					//Si no esta en estado Descobro, verifico si lo que falta revertir son las facturas de Calipso
					if(transaccion.getFactura() instanceof ShvCobFacturaCalipso
							&& EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(transaccion.getFactura().getEstado())){
						//Si la Factura es de tipo Calipso y esta en "pendiente de descobro", 
						//asumo que todavia hay por lo menos una factura Pendiente
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Verifica si existe al menos un Medio de Pago de tipo Mic en estado
	 * "Pendiente de descobro" en todas las Transacciones del descobro.
	 * @param descobro
	 * @return
	 */
	private Boolean existenMedioPagoMicPendientesDescobro(ShvCobDescobro descobro) {
		for(ShvCobTransaccion transaccion : descobro.getOperacion().getTransacciones()){
			if(!EstadoTransaccionEnum.DESCOBRO.equals(transaccion.getEstadoProcesamiento())){
				//Si no esta en estado Descobro, verifico si lo que falta revertir son los MP de Mic
				if(existenMediosPagoMic(transaccion)){
					for(ShvCobMedioPago medioPago : transaccion.getMediosPago()){
						//Si el MP es de tipo Mic y esta en "pendiente de descobro", 
						//asumo que todavia hay por lo menos un MP Pendiente
						if(medioPago instanceof ShvCobMedioPagoMic &&
								EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(medioPago.getEstado())){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Verifica si existe al menos un Medio de Pago de tipo Calipso en estado
	 * "Pendiente de descobro" en todas las Transacciones del descobro.
	 * @param descobro
	 * @return
	 */
	private Boolean existenMedioPagoCalipsoPendientesDescobro(ShvCobDescobro descobro) {
		for(ShvCobTransaccion transaccion : descobro.getOperacion().getTransacciones()){
			if(!EstadoTransaccionEnum.DESCOBRO.equals(transaccion.getEstadoProcesamiento())){
				
				if(!Validaciones.isObjectNull(transaccion.getFactura())){
					//Si no esta en estado Descobro, verifico si lo que falta revertir son los MP de Calipso
					if(existenMediosPagoCalipso(transaccion)){
						for(ShvCobMedioPago medioPago : transaccion.getMediosPago()){
							//Si el MP es de tipo calipso y esta en "pendiente de descobro", 
							//asumo que todavia hay por lo menos un MP Pendiente
							if(medioPago instanceof ShvCobMedioPagoCalipso &&
									EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(medioPago.getEstado())){
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * Recorre las transacciones del descobro. Por cada transaccion, si la factura y todos
	 * los Medios de pago estan en estado DESCOBRO actualiza la transaccion a DESCOBRO.
	 * @param descobro
	 */
	private void actualizarEstadoTransaccionesPendientesDescobro(ShvCobDescobro descobro) {
		forTransaccion:for(ShvCobTransaccion transaccion : descobro.getOperacion().getTransacciones()){
			if (!Validaciones.isObjectNull(transaccion.getFactura())){
				if(EstadoFacturaMedioPagoEnum.DESCOBRO.equals(transaccion.getFactura().getEstado())){
					for(ShvCobMedioPago medioPago : transaccion.getMediosPago()){
						if(!EstadoFacturaMedioPagoEnum.DESCOBRO.equals(medioPago.getEstado())){
							continue forTransaccion;
						}
					}
					transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.DESCOBRO);
				}
			} else {
				if(EstadoFacturaMedioPagoEnum.DESCOBRO.equals(transaccion.getTratamientoDiferencia().getEstado())){
					for(ShvCobMedioPago medioPago : transaccion.getMediosPago()){
						if(!EstadoFacturaMedioPagoEnum.DESCOBRO.equals(medioPago.getEstado())){
							continue forTransaccion;
						}
					}
					transaccion.setEstadoProcesamiento(EstadoTransaccionEnum.DESCOBRO);
				}
			}
		}
		
	}

	/**
	 * U562163
	 * setea a todas las transacciones, Facturas y Medios de Pago de la operacion a "PENDIENTE_DESCOBRO".
	 * @param operacion
	 */
	private ShvCobOperacion inicializarOperacionDeDescobro(ShvCobOperacion operacionVieja){
		ShvCobOperacion operacionNueva = new ShvCobOperacion();
		BeanUtils.copyProperties(operacionVieja, operacionNueva);

		Set<ShvCobTransaccion> transaccionesAux = new HashSet<ShvCobTransaccion>();

		for(ShvCobTransaccion transaccionVieja : operacionVieja.getTransacciones()){
			ShvCobTransaccion transaccionNueva = new ShvCobTransaccion();
			BeanUtils.copyProperties(transaccionVieja,transaccionNueva);

			// Medio de Pago
			Set<ShvCobMedioPago> mediosDePagosAux = new HashSet<ShvCobMedioPago>();
			for(ShvCobMedioPago medioPagoVieja : transaccionVieja.getMediosPago()){

				//CTA
				if(medioPagoVieja instanceof ShvCobMedioPagoCTA){
					if(((ShvCobMedioPagoCTA)medioPagoVieja).getGeneradoPorCobro().equals(SiNoEnum.NO)){
						ShvCobMedioPagoCTA medioPagoNueva = new ShvCobMedioPagoCTA();
						BeanUtils.copyProperties((ShvCobMedioPagoCTA)medioPagoVieja,medioPagoNueva);
						medioPagoNueva.setIdMedioPago(null);
						medioPagoNueva.setId(null);
						medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
						medioPagoNueva.setTransaccion(transaccionNueva);
						mediosDePagosAux.add(medioPagoNueva);
					}
				}else{
					//REMANENTE
					if(medioPagoVieja instanceof ShvCobMedioPagoRemanente){
						ShvCobMedioPagoRemanente medioPagoNueva = new ShvCobMedioPagoRemanente();
						BeanUtils.copyProperties((ShvCobMedioPagoRemanente)medioPagoVieja,medioPagoNueva);
						medioPagoNueva.setIdMedioPago(null);
						medioPagoNueva.setId(null);
						medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
						medioPagoNueva.setTransaccion(transaccionNueva);
						mediosDePagosAux.add(medioPagoNueva);
					}else{
						//NOTA DE CREDITO MIC
						if(medioPagoVieja instanceof ShvCobMedioPagoNotaCreditoMic){
							ShvCobMedioPagoNotaCreditoMic medioPagoNueva = new ShvCobMedioPagoNotaCreditoMic();
							BeanUtils.copyProperties((ShvCobMedioPagoNotaCreditoMic)medioPagoVieja,medioPagoNueva);
							medioPagoNueva.setIdMedioPago(null);
							medioPagoNueva.setId(null);
							medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
							medioPagoNueva.setTransaccion(transaccionNueva);
							mediosDePagosAux.add(medioPagoNueva);
						}else{
							//NOTA DE CREDITO CALIPSO
							if(medioPagoVieja instanceof ShvCobMedioPagoNotaCreditoCalipso){
								if(((ShvCobMedioPagoNotaCreditoCalipso)medioPagoVieja).getGeneradoPorCobro().equals(SiNoEnum.NO)){
									ShvCobMedioPagoNotaCreditoCalipso medioPagoNueva = new ShvCobMedioPagoNotaCreditoCalipso();
									BeanUtils.copyProperties((ShvCobMedioPagoNotaCreditoCalipso)medioPagoVieja,medioPagoNueva);
									medioPagoNueva.setIdMedioPago(null);
									medioPagoNueva.setId(null);
									medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
									medioPagoNueva.setTransaccion(transaccionNueva);
									mediosDePagosAux.add(medioPagoNueva);
								}
							}else{
								//Shiva
								if(medioPagoVieja instanceof ShvCobMedioPagoShiva){
									ShvCobMedioPagoShiva medioPagoNueva = new ShvCobMedioPagoShiva();
									BeanUtils.copyProperties((ShvCobMedioPagoShiva)medioPagoVieja,medioPagoNueva);
									medioPagoNueva.setIdMedioPago(null);
									medioPagoNueva.setId(null);
									medioPagoNueva.setIdValor(((ShvCobMedioPagoShiva) medioPagoVieja).getIdValor());
									medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
									medioPagoNueva.setTransaccion(transaccionNueva);
									mediosDePagosAux.add(medioPagoNueva);
								}else{
									if(medioPagoVieja instanceof ShvCobMedioPagoDesistimiento){
										//DESISTIMIENTO
										ShvCobMedioPagoDesistimiento medioPagoNueva = new ShvCobMedioPagoDesistimiento();
										BeanUtils.copyProperties((ShvCobMedioPagoDesistimiento)medioPagoVieja,medioPagoNueva);
										medioPagoNueva.setIdMedioPago(null);
										medioPagoNueva.setId(null);
										medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
										medioPagoNueva.setListaMedioPagoClientes(generarListaMedioPagoClientes(((ShvCobMedioPagoDesistimiento) medioPagoVieja).getListaMedioPagoClientes(),medioPagoNueva));
										medioPagoNueva.setTransaccion(transaccionNueva);
										mediosDePagosAux.add(medioPagoNueva);
									}else{
										if(medioPagoVieja instanceof ShvCobMedioPagoPlanDePago){
											//PLAN DE PAGO
											ShvCobMedioPagoPlanDePago medioPagoNueva = new ShvCobMedioPagoPlanDePago();
											BeanUtils.copyProperties((ShvCobMedioPagoPlanDePago)medioPagoVieja,medioPagoNueva);
											medioPagoNueva.setIdMedioPago(null);
											medioPagoNueva.setId(null);
											medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
											medioPagoNueva.setListaMedioPagoClientes(generarListaMedioPagoClientes(((ShvCobMedioPagoPlanDePago) medioPagoVieja).getListaMedioPagoClientes(),medioPagoNueva));
											medioPagoNueva.setTransaccion(transaccionNueva);
											mediosDePagosAux.add(medioPagoNueva);
										}else{
											if(medioPagoVieja instanceof ShvCobMedioPagoCompensacionOtras){
												//COMPENSACION
												ShvCobMedioPagoCompensacionOtras medioPagoNueva = new ShvCobMedioPagoCompensacionOtras();
												BeanUtils.copyProperties((ShvCobMedioPagoCompensacionOtras)medioPagoVieja,medioPagoNueva);
												medioPagoNueva.setIdMedioPago(null);
												medioPagoNueva.setId(null);
												medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
												medioPagoNueva.setListaMedioPagoClientes(generarListaMedioPagoClientes(((ShvCobMedioPagoCompensacionOtras) medioPagoVieja).getListaMedioPagoClientes(),medioPagoNueva));
												medioPagoNueva.setTransaccion(transaccionNueva);
												mediosDePagosAux.add(medioPagoNueva);
											} else {
												if (medioPagoVieja instanceof ShvCobMedioPagoCompensacionIIBB) {
													// Compensación IIBB
													ShvCobMedioPagoCompensacionIIBB medioPagoNueva = new ShvCobMedioPagoCompensacionIIBB();
													BeanUtils.copyProperties((ShvCobMedioPagoCompensacionIIBB)medioPagoVieja, medioPagoNueva);
													medioPagoNueva.setIdMedioPago(null);
													medioPagoNueva.setId(null);
													medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
													medioPagoNueva.setListaMedioPagoClientes(generarListaMedioPagoClientes(((ShvCobMedioPagoCompensacionIIBB) medioPagoVieja).getListaMedioPagoClientes(), medioPagoNueva));
													medioPagoNueva.setTransaccion(transaccionNueva);
													mediosDePagosAux.add(medioPagoNueva);
												} else {
													if (medioPagoVieja instanceof ShvCobMedioPagoCompensacionCesionCredito) {
														// Compensación Cesion Crédito
														ShvCobMedioPagoCompensacionCesionCredito medioPagoNueva = new ShvCobMedioPagoCompensacionCesionCredito();
														BeanUtils.copyProperties((ShvCobMedioPagoCompensacionCesionCredito)medioPagoVieja, medioPagoNueva);
														medioPagoNueva.setIdMedioPago(null);
														medioPagoNueva.setId(null);
														medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
														medioPagoNueva.setListaMedioPagoClientes(generarListaMedioPagoClientes(((ShvCobMedioPagoCompensacionCesionCredito) medioPagoVieja).getListaMedioPagoClientes(), medioPagoNueva));
														medioPagoNueva.setTransaccion(transaccionNueva);
														mediosDePagosAux.add(medioPagoNueva);
													} else {
														if (medioPagoVieja instanceof ShvCobMedioPagoCompensacionProveedor) {
															// Compensación Proveedor
															ShvCobMedioPagoCompensacionProveedor medioPagoNueva = new ShvCobMedioPagoCompensacionProveedor();
															BeanUtils.copyProperties((ShvCobMedioPagoCompensacionProveedor)medioPagoVieja, medioPagoNueva);
															medioPagoNueva.setIdMedioPago(null);
															medioPagoNueva.setId(null);
															medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
															medioPagoNueva.setListaMedioPagoClientes(generarListaMedioPagoClientes(((ShvCobMedioPagoCompensacionProveedor) medioPagoVieja).getListaMedioPagoClientes(), medioPagoNueva));
															medioPagoNueva.setTransaccion(transaccionNueva);
															mediosDePagosAux.add(medioPagoNueva);
														}else{
															if(medioPagoVieja instanceof ShvCobMedioPagoCompensacionIntercompany){
																//COMPENSACION INTERCOMPANY
																ShvCobMedioPagoCompensacionIntercompany medioPagoNueva = new ShvCobMedioPagoCompensacionIntercompany();
																BeanUtils.copyProperties((ShvCobMedioPagoCompensacionIntercompany)medioPagoVieja,medioPagoNueva);
																medioPagoNueva.setIdMedioPago(null);
																medioPagoNueva.setId(null);
																medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
																medioPagoNueva.setListaMedioPagoClientes(generarListaMedioPagoClientes(((ShvCobMedioPagoCompensacionIntercompany) medioPagoVieja).getListaMedioPagoClientes(),medioPagoNueva));
																medioPagoNueva.setTransaccion(transaccionNueva);
																mediosDePagosAux.add(medioPagoNueva);
															}else{
																if(medioPagoVieja instanceof ShvCobMedioPagoCompensacionLiquidoProducto){
																	//COMPENSACION LIQUIDO
																	ShvCobMedioPagoCompensacionLiquidoProducto medioPagoNueva = new ShvCobMedioPagoCompensacionLiquidoProducto();
																	BeanUtils.copyProperties((ShvCobMedioPagoCompensacionLiquidoProducto)medioPagoVieja,medioPagoNueva);
																	medioPagoNueva.setIdMedioPago(null);
																	medioPagoNueva.setId(null);
																	medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
																	medioPagoNueva.setListaMedioPagoClientes(generarListaMedioPagoClientes(((ShvCobMedioPagoCompensacionLiquidoProducto) medioPagoVieja).getListaMedioPagoClientes(),medioPagoNueva));
																	medioPagoNueva.setTransaccion(transaccionNueva);
																	mediosDePagosAux.add(medioPagoNueva);
																}else{
																	if(medioPagoVieja instanceof ShvCobMedioPagoDebitoProximaFacturaCalipso){
																		//DEBITO PROXIMA FACTURA CALIPSO
																		ShvCobMedioPagoDebitoProximaFacturaCalipso medioPagoNueva = new ShvCobMedioPagoDebitoProximaFacturaCalipso();
																		BeanUtils.copyProperties((ShvCobMedioPagoDebitoProximaFacturaCalipso)medioPagoVieja,medioPagoNueva);
																		medioPagoNueva.setIdMedioPago(null);
																		medioPagoNueva.setId(null);
																		medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
																		medioPagoNueva.setListaMedioPagoClientes(generarListaMedioPagoClientes(((ShvCobMedioPagoDebitoProximaFacturaCalipso) medioPagoVieja).getListaMedioPagoClientes(),medioPagoNueva));
																		medioPagoNueva.setTransaccion(transaccionNueva);
																		mediosDePagosAux.add(medioPagoNueva);
																	}else{
																		if(medioPagoVieja instanceof ShvCobMedioPagoDebitoProximaFacturaMic){
																			//DEBITO PROXIMA FACTURA MIC
																			ShvCobMedioPagoDebitoProximaFacturaMic medioPagoNueva = new ShvCobMedioPagoDebitoProximaFacturaMic();
																			BeanUtils.copyProperties((ShvCobMedioPagoDebitoProximaFacturaMic)medioPagoVieja,medioPagoNueva);
																			medioPagoNueva.setIdMedioPago(null);
																			medioPagoNueva.setId(null);
																			medioPagoNueva.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
																			medioPagoNueva.setListaMedioPagoClientes(generarListaMedioPagoClientes(((ShvCobMedioPagoDebitoProximaFacturaMic) medioPagoVieja).getListaMedioPagoClientes(),medioPagoNueva));
																			medioPagoNueva.setTransaccion(transaccionNueva);
																			mediosDePagosAux.add(medioPagoNueva);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			transaccionNueva.setShvCobMedioPago(mediosDePagosAux);
			

			if(!Validaciones.isObjectNull(transaccionVieja.getFactura())){
				// factura
				Set<ShvCobFactura> facturasAux = new HashSet<ShvCobFactura>();
				ShvCobFactura facturaVieja = transaccionVieja.getFactura();
				if(SiNoEnum.NO.equals(facturaVieja.getGeneradoPorCobro())){
					if(facturaVieja instanceof ShvCobFacturaMic){
						ShvCobFacturaMic facturaNueva = new ShvCobFacturaMic();
						BeanUtils.copyProperties((ShvCobFacturaMic)facturaVieja,facturaNueva);
						facturaNueva.setIdFactura(null);
						facturaNueva.setId(null);
						facturaNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
						facturaNueva.setTransaccion(transaccionNueva);
						facturasAux.add(facturaNueva);
					}else{
						if(facturaVieja instanceof ShvCobFacturaCalipso){
							ShvCobFacturaCalipso facturaNueva = new ShvCobFacturaCalipso();
							BeanUtils.copyProperties((ShvCobFacturaCalipso)facturaVieja,facturaNueva);
							facturaNueva.setIdFactura(null);
							facturaNueva.setId(null);
							facturaNueva.setEstado(EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO);
							facturaNueva.setTransaccion(transaccionNueva);
							facturasAux.add(facturaNueva);
						}
					}
					transaccionNueva.setShvCobFactura(facturasAux);
				}
			}else{
				//Tratamiento de diferencia
				Set<ShvCobTratamientoDiferencia> tratamientoAux = new HashSet<ShvCobTratamientoDiferencia>();
				ShvCobTratamientoDiferencia tratamientoViejo = transaccionVieja.getTratamientoDiferencia();

				ShvCobTratamientoDiferencia tratamientoNuevo = new ShvCobTratamientoDiferencia();
				BeanUtils.copyProperties((ShvCobTratamientoDiferencia)tratamientoViejo,tratamientoNuevo);
				tratamientoNuevo.setIdTratamientoDiferencia(null);
				tratamientoNuevo.setId(null);
				tratamientoNuevo.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
				tratamientoNuevo.setTransaccion(transaccionNueva);
				tratamientoAux.add(tratamientoNuevo);

				transaccionNueva.setListaTratamientosDiferencias(tratamientoAux);
			}

			// transaccion
			transaccionNueva.setIdTransaccion(null);
			transaccionNueva.setId(null);
			transaccionNueva.setEstadoProcesamiento(EstadoTransaccionEnum.PENDIENTE_DESCOBRO);
			transaccionNueva.setShvCobMedioPago(mediosDePagosAux);
			transaccionNueva.setOperacion(operacionNueva);
			transaccionesAux.add(transaccionNueva);
		}

		// Operacion
		operacionNueva.setTipoOperacion(TipoOperacionEnum.DESCOBRO);
		operacionNueva.setIdOperacion(null);
		operacionNueva.setId(null);
		operacionNueva.setTransacciones(transaccionesAux);
		//U562163 - IM02151346
		operacionNueva.setIdOperacionOriginal(operacionVieja.getIdOperacion());

		return operacionNueva;
	}

	/**
	 * Duplica la lista de ShvCobMedioPagoCliente y la retorna.
	 * @param mediosPagoClientes
	 * @param medioPagoUsuario 
	 * @return
	 */
	private Set<ShvCobMedioPagoCliente> generarListaMedioPagoClientes(Set<ShvCobMedioPagoCliente> mediosPagoClientes, ShvCobMedioPagoUsuario medioPagoUsuario) {
		Set<ShvCobMedioPagoCliente> mediosDePagoClientesAux = new HashSet<ShvCobMedioPagoCliente>();

		for(ShvCobMedioPagoCliente clienteViejo : mediosPagoClientes){
			//DEBITO PROXIMA FACTURA CALIPSO
			ShvCobMedioPagoCliente clienteNuevo = new ShvCobMedioPagoCliente();
			BeanUtils.copyProperties(clienteViejo,clienteNuevo);
			clienteNuevo.setIdMedioPagoCliente(null);
			clienteNuevo.setId(null);
			clienteNuevo.setMedioPagoUsuario(medioPagoUsuario);
			
			mediosDePagoClientesAux.add(clienteNuevo);
		}
		
		return mediosDePagoClientesAux;
	}

	/**
	 * Setea el estado a DESCOBRO de la factura que coincida con las parametros de entrada.
	 * @param cobro
	 * @param idTransaccion
	 * @param id
	 * @throws NegocioExcepcion 
	 */
	private void revertirFacturaMic(ShvCobDescobro descobro, ShvCobCobro cobro, Integer numeroTransaccion, String id,List<ShvValValorSimplificado> listaValoresSimplificados) throws NegocioExcepcion {
		
		for(ShvCobTransaccion transaccion : descobro.getOperacion().getTransacciones()){
			if(numeroTransaccion.equals(transaccion.getNumeroTransaccion())){
				
				if(transaccion.getFactura() instanceof ShvCobFacturaMic){
					ShvCobFacturaMic facturaMic = (ShvCobFacturaMic) transaccion.getFactura();
					if(facturaMic.getIdReferenciaFactura().equals(id)){
						facturaMic.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
						
						
						for(ShvCobMedioPago medioPago : transaccion.getMediosPago()){
							// Revierto los Medios de pago Shiva
							if(medioPago instanceof ShvCobMedioPagoShiva){
								ShvCobMedioPagoShiva medioPagoShiva = ((ShvCobMedioPagoShiva)medioPago);
								
								if (!Validaciones.isObjectNull(medioPagoShiva.getIdValor())) {
									Long idValor = medioPagoShiva.getIdValor();
									ShvValValorSimplificado valor = valorServicio.buscarValorSimplificado(idValor);
									
									if (listaValoresSimplificados.contains(valor)){
										int index = listaValoresSimplificados.indexOf(valor);
										valor = listaValoresSimplificados.get(index);
			            			
										BigDecimal saldoOriginal = valor.getSaldoDisponible();
										BigDecimal importe = medioPagoShiva.getImporte();
										valor = valorServicio.revertirValoresPertenecientesATransaccion(valor, importe, cobro,  numeroTransaccion, false);
										medioPagoShiva.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
										medioPagoShiva.setIdValor(valor.getIdValor());
										
										//Ver referencia ShvCobMedioPagoShiva
										//((ShvCobMedioPagoShiva)cobroServicio.getMedioPagoShivaPorIdValor(cobro, valor.getIdValor())).setValor(valor);
										
										BigDecimal saldoModificado = valor.getSaldoDisponible();
										
										Traza.auditoria(getClass(), "revertirValoresPertenecientesATransaccion", 
												"["+transaccion.getOperacionTransaccionFormateado()+"] Id Valor revertido: " + valor.getIdValor()
												+ " Saldo devuelto: " + importe
												+ " - Saldo original: " + saldoOriginal
												+ " - Saldo modificado: " + saldoModificado);
										
									
										valorServicio.actualizarValorSimplificado(valor);
									}
								}
							}
							// Revierto los Medios de pago Usuario
							if(medioPago instanceof ShvCobMedioPagoUsuario){
								medioPago.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
							}
						}
						return;
					}
				}
			}
		}
	}
	
	/**
	 * Revierte los medios de pago de tipo Shiva
	 * @param cobro
	 * @param idTransaccion
	 * @param id
	 * @throws NegocioExcepcion 
	 */
	private void revertirMedioPagoShivaTratamiento(ShvCobDescobro descobro, ShvCobCobro cobro, List<ShvValValorSimplificado> listaValoresSimplificados) throws NegocioExcepcion {
		
		for (ShvCobTransaccion transaccion : descobro.getTransaccionesOrdenadas()) {
			Integer numeroTransaccion = transaccion.getNumeroTransaccion();
			
			if (Validaciones.isObjectNull(transaccion.getFactura())) {

				for (ShvCobMedioPago medioPago : transaccion.getMediosPago()) {
					// Revierto los Medios de pago Shiva
					if (medioPago instanceof ShvCobMedioPagoShiva && EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(medioPago.getEstado())) {
						ShvCobMedioPagoShiva medioPagoShiva = ((ShvCobMedioPagoShiva) medioPago);

						if (!Validaciones.isObjectNull(medioPagoShiva.getIdValor())) {
							Long idValor = medioPagoShiva.getIdValor();
							ShvValValorSimplificado valor = valorServicio.buscarValorSimplificado(idValor);

							if (listaValoresSimplificados.contains(valor)) {
								int index = listaValoresSimplificados.indexOf(valor);
								valor = listaValoresSimplificados.get(index);

								BigDecimal saldoOriginal = valor.getSaldoDisponible();
								BigDecimal importe = medioPagoShiva.getImporte();
								valor = valorServicio.revertirValoresPertenecientesATransaccion(valor, importe, cobro, numeroTransaccion, false);
								medioPagoShiva.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
								medioPagoShiva.setIdValor(valor.getIdValor());

								BigDecimal saldoModificado = valor.getSaldoDisponible();

								Traza.auditoria(
										getClass(),
										"revertirValoresPertenecientesATransaccion",
										"["
												+ transaccion.getOperacionTransaccionFormateado()
												+ "] Id Valor revertido: "
												+ valor.getIdValor()
												+ " Saldo devuelto: " + importe
												+ " - Saldo original: "
												+ saldoOriginal
												+ " - Saldo modificado: "
												+ saldoModificado);

								valorServicio.actualizarValorSimplificado(valor);
							}
						}
					}
					
					if (medioPago instanceof ShvCobMedioPagoUsuario && EstadoFacturaMedioPagoEnum.PENDIENTE_DESCOBRO.equals(medioPago.getEstado())) {
						medioPago.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
					}
				}
			}
		}
	}
	
	/**
	 * Setea el estado a DESCOBRO de la factura que coincida con los parametros de entrada.
	 * @param cobro
	 * @param idTransaccion
	 * @param tipoComprobante
	 * @param claseComprobante
	 * @param sucursalComprobante
	 * @param numeroComprobante
	 * @throws NegocioExcepcion 
	 */
	private void revertirFacturaCalipso(ShvCobDescobro descobro, ShvCobCobro cobro, Integer numeroTransaccion, String tipoComprobante, 
			char claseComprobante, String sucursalComprobante, String numeroComprobante, List<ShvValValorSimplificado> listaValoresSimplificados) throws NegocioExcepcion {
			
		for(ShvCobTransaccion transaccion : descobro.getOperacion().getTransacciones()){
			if(numeroTransaccion.equals(transaccion.getNumeroTransaccion())){
				if(transaccion.getFactura() instanceof ShvCobFacturaCalipso){
					ShvCobFacturaCalipso facturaCalipso = (ShvCobFacturaCalipso) transaccion.getFactura();
					if(facturaCalipso.getTipoComprobante().getIdTipoComprobante().equalsIgnoreCase(tipoComprobante)
							&& facturaCalipso.getClaseComprobante().name().equalsIgnoreCase(String.valueOf(claseComprobante))
							&& Utilidad.removeStartingZeros(facturaCalipso.getSucursalComprobante()).equalsIgnoreCase(Utilidad.removeStartingZeros(sucursalComprobante))
							&& Utilidad.removeStartingZeros(facturaCalipso.getNumeroComprobante()).equalsIgnoreCase(Utilidad.removeStartingZeros(numeroComprobante))){
						
						facturaCalipso.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
						
						
						// Revierto los Medios de pago Usuario y Shiva
						for(ShvCobMedioPago medioPago : transaccion.getMediosPago()){
							
							if(medioPago instanceof ShvCobMedioPagoShiva){
								ShvCobMedioPagoShiva medioPagoShiva = ((ShvCobMedioPagoShiva)medioPago);
								if (!Validaciones.isObjectNull(medioPagoShiva.getIdValor())){
									ShvValValorSimplificado valor;
									
									try {
										valor = valorDao.buscarValorSimplificado(medioPagoShiva.getIdValor());
									} catch (PersistenciaExcepcion e) {
										throw new NegocioExcepcion(e.getMessage());
									}
									
			            			if (listaValoresSimplificados.contains(valor)){
										int index = listaValoresSimplificados.indexOf(valor);
										valor = listaValoresSimplificados.get(index);
										
										BigDecimal saldoOriginal = valor.getSaldoDisponible();
										BigDecimal importe = medioPagoShiva.getImporte();
										// En caso de ser de tipo Shiva debo revertir el saldo
										valor = valorServicio.revertirValoresPertenecientesATransaccion(valor, importe, cobro,  numeroTransaccion, false);
										medioPagoShiva.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
										
										//Ver referencia ShvCobMedioPagoShiva
										//((ShvCobMedioPagoShiva)cobroServicio.getMedioPagoShivaPorIdValor(cobro, valor.getIdValor())).setValor(valor);
										
										BigDecimal saldoModificado = valor.getSaldoDisponible();
										
										Traza.auditoria(getClass(), "revertirValoresPertenecientesATransaccion", 
												"["+transaccion.getOperacionTransaccionFormateado()+"] Id Valor revertido: " + valor.getIdValor()
												+ " Saldo devuelto: " + importe
												+ " - Saldo original: " + saldoOriginal
												+ " - Saldo modificado: " + saldoModificado);
										
										valorServicio.actualizarValorSimplificado(valor);
			            			}
								}	
							}
							if(medioPago instanceof ShvCobMedioPagoUsuario){
								medioPago.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
							}
						}
						return;
					}
				}
			}
		}
	}

	/**
	 * Setea el estado a DESCOBRO del Medio de Pago que coincida con los parametros de entrada.
	 * @param cobro
	 * @param idTransaccion
	 * @param idNotaCredito
	 */
	private void revertirNotaCredito(ShvCobDescobro descobro, Integer numeroTransaccion, String idNotaCredito) throws NegocioExcepcion{
			
		for(ShvCobTransaccion transaccion : descobro.getOperacion().getTransacciones()){
			if(numeroTransaccion.equals(transaccion.getNumeroTransaccion())){
				for(ShvCobMedioPago medioPago : transaccion.getMediosPago()){
					if(medioPago instanceof ShvCobMedioPagoNotaCreditoMic){
						if(((ShvCobMedioPagoNotaCreditoMic) medioPago).getNumeroNotaCredito().equalsIgnoreCase(idNotaCredito)){
							medioPago.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
							return;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Setea el estado a DESCOBRO del Medio de Pago que coincida con las parametros de entrada.
	 * @param cobro
	 * @param idTransaccion
	 * @param cuentaRemanente
	 * @param tipoRemanente
	 */
	private void revertirRemanente(ShvCobDescobro descobro, Integer numeroTransaccion, String cuentaRemanente, String tipoRemanente) throws NegocioExcepcion{
			
		for(ShvCobTransaccion transaccion : descobro.getOperacion().getTransacciones()){
			if(numeroTransaccion.equals(transaccion.getNumeroTransaccion())){
				for(ShvCobMedioPago medioPago : transaccion.getMediosPago()){
					if(medioPago instanceof ShvCobMedioPagoRemanente){
						
						ShvCobMedioPagoRemanente medioPagoRemanente = (ShvCobMedioPagoRemanente) medioPago;
						
						if(medioPagoRemanente.getCuentaRemanente().compareTo(Long.valueOf(cuentaRemanente))==0
								&& medioPagoRemanente.getTipoRemanente().compareTo(Long.valueOf(tipoRemanente))==0){
							
							medioPago.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
							return;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Setea el estado a DESCOBRO del Medio de Pago que coincida con las parametros de entrada.
	 * @param cobro
	 * @param idTransaccion
	 * @param tipoComprobante
	 * @param claseComprobante
	 * @param sucursalComprobante
	 * @param numeroComprobante
	 */
	private void revertirCTA(ShvCobDescobro descobro, Integer numeroTransaccion, String tipoComprobante, 
			char claseComprobante, String sucursalComprobante, String numeroComprobante) throws NegocioExcepcion{
			
		for(ShvCobTransaccion transaccion : descobro.getOperacion().getTransacciones()){
			if(numeroTransaccion.equals(transaccion.getNumeroTransaccion())){
				for(ShvCobMedioPago medioPago : transaccion.getMediosPago()){
					if(medioPago instanceof ShvCobMedioPagoCTA){
						if(((ShvCobMedioPagoCTA) medioPago).getTipoComprobante().name().equalsIgnoreCase(tipoComprobante)
								&& ((ShvCobMedioPagoCTA) medioPago).getClaseComprobante().name().equalsIgnoreCase(String.valueOf(claseComprobante))
								&& Utilidad.removeStartingZeros(((ShvCobMedioPagoCTA) medioPago).getSucursalComprobante()).equalsIgnoreCase(Utilidad.removeStartingZeros(sucursalComprobante))
								&& Utilidad.removeStartingZeros(((ShvCobMedioPagoCTA) medioPago).getNroComprobante()).equalsIgnoreCase(Utilidad.removeStartingZeros(numeroComprobante))){
							
							medioPago.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
							return;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Setea el estado a DESCOBRO del Medio de Pago que coincida con los parametros de entrada.
	 * @param cobro
	 * @param idTransaccion
	 * @param tipoComprobante
	 * @param claseComprobante
	 * @param sucursalComprobante
	 * @param numeroComprobante
	 */
	private void revertirNotaCreditoCalipso(ShvCobDescobro descobro, Integer numeroTransaccion, String tipoComprobante, 
			char claseComprobante, String sucursalComprobante, String numeroComprobante) throws NegocioExcepcion{
			
		for(ShvCobTransaccion transaccion : descobro.getOperacion().getTransacciones()){
			if(numeroTransaccion.equals(transaccion.getNumeroTransaccion())){
				for(ShvCobMedioPago medioPago : transaccion.getMediosPago()){
					if(medioPago instanceof ShvCobMedioPagoNotaCreditoCalipso){
						ShvCobMedioPagoNotaCreditoCalipso medioPagoNotaCreditoCalipso = (ShvCobMedioPagoNotaCreditoCalipso) medioPago;
						if(medioPagoNotaCreditoCalipso.getTipoComprobante().name().equalsIgnoreCase(tipoComprobante)
								&& medioPagoNotaCreditoCalipso.getClaseComprobante().name().equalsIgnoreCase(String.valueOf(claseComprobante))
								&& Utilidad.removeStartingZeros(medioPagoNotaCreditoCalipso.getSucursalComprobante()).equalsIgnoreCase(Utilidad.removeStartingZeros(sucursalComprobante))
								&& Utilidad.removeStartingZeros(medioPagoNotaCreditoCalipso.getNroComprobante()).equalsIgnoreCase(Utilidad.removeStartingZeros(numeroComprobante))){
							
							medioPago.setEstado(EstadoFacturaMedioPagoEnum.DESCOBRO);
							return;
						}
					}
				}
			}
		}
	}
	
	private void revertirValor(String linea, TipoOperacionReversionEnum tipoOperacion) throws NegocioExcepcion {
		String[] campos = linea.split("\\|");
		
		ValorDto valorDto = new ValorDto();
		
		switch (tipoOperacion) {
	    case REVERSION_VALORES_CHEQUE:
	    	valorDto.setIdTipoValor(TipoValorEnum.CHEQUE.getIdTipoValorString());
    		valorDto.setImporte(campos[4].trim());
	    	valorDto.setSaldoDisponible(campos[5].trim());
	    	valorDto.setIdAcuerdo(campos[6].trim());
	    	valorDto.setBancoOrigen(campos[7].trim());
	    	valorDto.setNumeroCheque(campos[8].trim());
	    	valorDto.setFechaDeposito(campos[9].trim());
	    	valorPorReversionServicio.crear(valorDto);
	    	break;
	    	
		case REVERSION_VALORES_CHEQUE_DIFERIDO:
			valorDto.setIdTipoValor(TipoValorEnum.CHEQUE_DIFERIDO.getIdTipoValorString());
			valorDto.setImporte(campos[4].trim());
	    	valorDto.setSaldoDisponible(campos[5].trim());
	    	valorDto.setIdAcuerdo(campos[6].trim());
	    	valorDto.setBancoOrigen(campos[7].trim());
	    	valorDto.setNumeroCheque(campos[8].trim());
	    	valorDto.setFechaVencimiento(campos[9].trim());
	    	valorPorReversionServicio.crear(valorDto);
	    	break;
	    	
		case REVERSION_VALORES_EFECTIVO:
			valorDto.setIdTipoValor(TipoValorEnum.EFECTIVO.getIdTipoValorString());
			valorDto.setImporte(campos[4].trim());
	    	valorDto.setSaldoDisponible(campos[5].trim());
	    	valorDto.setIdAcuerdo(campos[6].trim());
	    	valorDto.setNumeroBoleta(campos[7].trim());
	    	valorDto.setFechaDeposito(campos[8].trim());
	    	valorPorReversionServicio.crear(valorDto);
	    	break;
	    	
		case REVERSION_VALORES_TRANSFERENCIA:
			valorDto.setIdTipoValor(TipoValorEnum.TRANSFERENCIA.getIdTipoValorString());
			valorDto.setImporte(campos[4].trim());
	    	valorDto.setSaldoDisponible(campos[5].trim());
	    	valorDto.setIdAcuerdo(campos[6].trim());
	    	valorDto.setBancoOrigen(campos[7].trim());
	    	valorDto.setNumeroReferencia(campos[8].trim());
	    	valorDto.setFechaDeposito(campos[9].trim());
	    	valorPorReversionServicio.crear(valorDto);
	    	break;
	    	
		case REVERSION_VALORES_INTERDEPOSITO:
			valorDto.setIdTipoValor(TipoValorEnum.INTERDEPÓSITO.getIdTipoValorString());
			valorDto.setImporte(campos[4].trim());
	    	valorDto.setSaldoDisponible(campos[5].trim());
	    	
	    	if (SistemaEnum.MIC.name().equals(campos[2].trim())){
		    	//MIC no nos envia el idAcuerdo por lo tanto se setea segun el idBancoOrigen
		    	if (Constantes.BANCO_GALICIA.equals(campos[7].trim())) {
		    		valorDto.setIdAcuerdo("6");
				} else { 
					if (Constantes.BANCO_CIUDAD.equals(campos[7].trim())) {
			    		valorDto.setIdAcuerdo("87");
					} else { 
						if (Constantes.BANCO_PROVINCIA_0148.equals(campos[7].trim()) || Constantes.BANCO_PROVINCIA_0014.equals(campos[7].trim())) {
				    		valorDto.setIdAcuerdo("94");
						}
					}
				}
	    	}else{
	    		valorDto.setIdAcuerdo(campos[6].trim());
	    		valorDto.setBancoOrigen(campos[7].trim());
	    	}
	    	valorDto.setNumeroInterdepositoCdif(campos[8].trim());
	    	valorDto.setCodOrganismo(campos[9].trim());
	    	valorDto.setFechaDeposito(campos[10].trim());
	    	valorPorReversionServicio.crear(valorDto);
	    	break;
	    	
		default:
		};
		
	}
	
	/**
	 * Se Tracean los datos del descobro 
	 * @param descobro
	 */
	private void tracearDatosImputacionDescobro(ShvCobDescobro descobro, boolean esInicio) throws NegocioExcepcion{
			
		StringBuffer mensaje = new StringBuffer("\n");
		
		if(esInicio){
		mensaje.append("Descobro a descobrar ")
			   .append("id de descobro: " + descobro.getIdDescobro() + " y id de cobro: " + descobro.getIdCobro() +
		               ", estado: " + descobro.getWorkflow().getEstado().descripcion());
		}else{
			mensaje.append("Descobro descobrado ")
			   .append("id de descobro: " + descobro.getIdDescobro() + " y id de cobro: " + descobro.getIdCobro() +
		               ", estado: " + descobro.getWorkflow().getEstado().descripcion());
		}
		
		mensaje.append("\n");
		
		for(ShvCobTransaccion tran : descobro.getTransaccionesOrdenadas()){
			Long idOper = tran.getOperacion().getIdOperacion();
			Integer numTransaccion = tran.getNumeroTransaccion();
			String idOperacion   = (idOper == null)? "0000000":Utilidad.rellenarCerosIzquierda(idOper.toString(), 7);
    		String numeroTransaccion = (numTransaccion == null)?"00000":Utilidad.rellenarCerosIzquierda(numTransaccion.toString(), 5);
    		String idOperacionTransaccion = idOperacion+"."+numeroTransaccion;
    		
    		String salidaTraceo = "-Transaccion ("+idOperacionTransaccion+"), id: " 
    				+ tran.getIdTransaccion() + ", estado: " + tran.getEstadoProcesamiento().descripcion();
    		mensaje.append(salidaTraceo).append("\n");
    		
    		for(ShvCobFactura fact : tran.getShvCobFactura()){
				salidaTraceo = 
						Utilidad.rellenarEspaciosDerecha("|Fact   id: " + fact.getIdFactura(), 31) +
						Utilidad.rellenarEspaciosDerecha("| tipo: " + ((fact instanceof ShvCobFacturaMic)?"MIC":
							(fact instanceof ShvCobFacturaCalipso)?"CALIPSO":"-"), 17) + 
						"| estado: " + fact.getEstado().descripcion();
				
				mensaje.append(salidaTraceo).append("\n");
			}
    		
    		ShvCobTratamientoDiferencia tratamiento = tran.getTratamientoDiferencia();
    		if (!Validaciones.isObjectNull(tratamiento)){
    			salidaTraceo = 
    					Utilidad.rellenarEspaciosDerecha("|Tratamiento   id: " + tratamiento.getIdTratamientoDiferencia(), 24) +
    					Utilidad.rellenarEspaciosDerecha("| tipo: " + tratamiento.getTipoTratamientoDiferencia(), 17) + 
    						"| estado: " + tratamiento.getEstado().descripcion();
    			
    			mensaje.append(salidaTraceo).append("\n");
    		}
			
			int count = 1;
			
			for(ShvCobMedioPago mp : tran.getShvCobMedioPago()){
				String medioPagoTraceo = Utilidad.rellenarEspaciosDerecha("|MP " + count +"   id: " + mp.getIdMedioPago(), 31); 
				medioPagoTraceo += Utilidad.rellenarEspaciosDerecha("| tipo: " + ((mp instanceof ShvCobMedioPagoMic)?"MIC":(mp instanceof ShvCobMedioPagoCalipso)?"CALIPSO":
						(mp instanceof ShvCobMedioPagoUsuario)?"USUARIO":
						(mp instanceof ShvCobMedioPagoShiva)?"SHIVA":"-"), 17);
				medioPagoTraceo += "| estado: " + mp.getEstado().descripcion();
				
				mensaje.append(medioPagoTraceo).append("\n");
				count++;
			}
		}
		Traza.auditoria(getClass(), mensaje.toString());
	}
	
	/**
	 * Verifica si la transaccion contiene Medios de Pago de tipo MIC.
	 * @param transaccion
	 * @return
	 */
	private boolean existenMediosPagoMic(ShvCobTransaccion transaccion) {
		for(ShvCobMedioPago medioPago : transaccion.getMediosPago()){
			if(medioPago instanceof ShvCobMedioPagoMic){
					return true;
			}
		}
		return false;
	}

	public ShvCobDescobro buscarDescobroPorIdOperacion(Long idOperacion) throws NegocioExcepcion{
		try {
			return descobroDao.buscarDescobroPorIdOperacion(idOperacion);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
	}
	/**
	 * Verifica si la transaccion contiene Medios de Pago de tipo Calipso.
	 * @param transaccion
	 * @return
	 */
	private boolean existenMediosPagoCalipso(ShvCobTransaccion transaccion) {
		for(ShvCobMedioPago medioPago : transaccion.getMediosPago()){
			if(medioPago instanceof ShvCobMedioPagoCalipso){
					return true;
			}
		}
		return false;
	}
	
//	@Override
//	public List<Object[]> listarDescobrosParaSubdiario() throws NegocioExcepcion {
//		try {
//			return descobroDao.buscarCobrosParaSubdiario();
//		} catch (PersistenciaExcepcion e) {
//			throw new NegocioExcepcion(e.getMessage(),e);
//		}
//	}
	
	public IWorkflowDescobroOrigenCobrador getWorkflowDescobros() {
		return workflowDescobroOrigenCobrador;
	}

	public void setWorkflowDescobroOrigenCobrador(IWorkflowDescobroOrigenCobrador workflowDescobroOrigenCobrador) {
		this.workflowDescobroOrigenCobrador = workflowDescobroOrigenCobrador;
	}

	public IDescobroDao getDescobroDao() {
		return descobroDao;
	}

	public void setDescobroDao(IDescobroDao descobroDao) {
		this.descobroDao = descobroDao;
	}

	public ICobroDao getCobroDao() {
		return cobroDao;
	}

	public void setCobroDao(ICobroDao cobroDao) {
		this.cobroDao = cobroDao;
	}

	public IValorServicio getValorServicio() {
		return valorServicio;
	}

	public void setValorServicio(IValorServicio valorServicio) {
		this.valorServicio = valorServicio;
	}

	public IParametroServicio getParametroServicio() {
		return parametroServicio;
	}

	public void setParametroServicio(IParametroServicio parametroServicio) {
		this.parametroServicio = parametroServicio;
	}

	public ICobroImputacionServicio getCobroServicio() {
		return cobroServicio;
	}

	public void setCobroServicio(ICobroImputacionServicio cobroServicio) {
		this.cobroServicio = cobroServicio;
	}

	public MailServicio getMailServicio() {
		return mailServicio;
	}

	public void setMailServicio(MailServicio mailServicio) {
		this.mailServicio = mailServicio;
	}

	public IWorkflowDescobroOrigenCobrador getWorkflowDescobroOrigenCobrador() {
		return workflowDescobroOrigenCobrador;
	}
	
	

	
}
