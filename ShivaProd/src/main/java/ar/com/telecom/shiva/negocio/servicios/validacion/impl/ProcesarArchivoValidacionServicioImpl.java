package ar.com.telecom.shiva.negocio.servicios.validacion.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.TipoAcuerdoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.batch.bean.DepositoBatch;
import ar.com.telecom.shiva.negocio.batch.bean.InterdepositoBatch;
import ar.com.telecom.shiva.negocio.batch.bean.TransferenciaBatch;
import ar.com.telecom.shiva.negocio.servicios.validacion.IProcesarArchivoValidacionServicio;
import ar.com.telecom.shiva.persistencia.dao.IBancoDao;
import ar.com.telecom.shiva.persistencia.dao.IOrganismoDao;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBanco;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBancoCliente;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamOrganismo;

public class ProcesarArchivoValidacionServicioImpl implements IProcesarArchivoValidacionServicio{

	
	@Autowired
	IBancoDao bancoDao;
	@Autowired
	IOrganismoDao organismoDao;
	
	public static final Integer INICIO_COD_ORGANISMO 	 = 0;
    public static final Integer FIN_COD_ORGANISMO    	 = 3;
    public static final Integer INICIO_COD_INTERDEPOSITO = 7;
    public static final Integer FIN_COD_INTERDEPOSITO 	 = 15;
    
    private static final String SEPARADOR_TRANSFERENCIA = ";";
	private static final String SEPERADOR_INTERDEPOSITO = ",";
	private static final String SEPERADOR_DEPOSITO = ";";
	private static final String SEPARADOR_DATOS_CHEQUE = "-";
	
	/**
	 * Método para procesar ACUERDOS 28,29,33,34	
	 * @param linea
	 * @return
	 * @throws ValidacionExcepcion
	 */
	public DepositoBatch procesarDepositos(String i, String linea, String tipoAcuerdo) throws ValidacionExcepcion {
		String logError = "";

		DepositoBatch acuerdo = new DepositoBatch(tipoAcuerdo);
 
		if(Validaciones.isNullOrEmpty(linea)){
			logError += agregarAlLogError(i, Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.lineaNula"));
			throw new ValidacionExcepcion(Constantes.GRABAR_REGISTROS_AVC,logError);
		} else {

			//Separo los campos de la cadena
			String[] campos = linea.split(SEPERADOR_DEPOSITO, -1);
			if(campos.length != 16){
				logError += agregarAlLogError(i, Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.cantidadCampos"));
			} else {
			
				//VALIDACION DE LOS FORMATOS DE CAMPO
				// Importe
		    	if(Validaciones.isNullOrEmpty(campos[7])){
		    		logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.ImporteNulo"), campos[7]));
		    	}else if(campos[7].startsWith("-")){
		    		
		    		if (!Validaciones.isImporteFormato(campos[7].substring(1),2)) {
			    		logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.ImporteFormato"), campos[7]));
			    	}
		    		//Si el valor es negativo setear null al importe
		    		acuerdo.setImporte(null);
		    		return acuerdo;
		    	}else if (!Validaciones.isImporteFormato(campos[7],2)) {
		    		logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.ImporteFormato"), campos[7]));
		    	}else{
		    		acuerdo.setImporte(Utilidad.stringToBigDecimal((campos[7])));
		    	}
				
				// IdRecInstrumento
				acuerdo.setIdRecInstrumento((Validaciones.isNumeric(campos[0]))?Long.valueOf(campos[0]):null);
				
				// Rend
				acuerdo.setRend((Validaciones.isNumeric(campos[1]))?Long.valueOf(campos[1]):null);
				
		    	// Fecha Pago
				if (!Validaciones.validarFecha(campos[2])) {
					logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.fechaPagoFormato"), campos[2]));
		    	}else{
		    		try {
						acuerdo.setFechaPago(Utilidad.parseDatePickerString(campos[2]));
					} catch (ParseException e) {
						logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.fechaPagoParser"), campos[2]));
					}
		    	} 
				
				// Nro. Cliente
				if(!Validaciones.isNullOrEmpty(campos[3])){
			    	if (!Validaciones.isNumeric(campos[3])){
			    		logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.nroClienteFormato"), campos[3]));
			    	}else{
			    		acuerdo.setCodigoCliente(campos[3]);
			    	}
				}else{
					acuerdo.setCodigoCliente(null);
				}
		    	
		    	// Forma de Pago
		    	if (Validaciones.isNullOrEmpty(campos[4])){
		    		acuerdo.setFormaPago(null);
		    	} else {
			    	String formaPago = campos[4];
			    	if(!Validaciones.esFormatoTexto(formaPago)){
			    		acuerdo.setFormaPago(Utilidad.removerCaracteresEspeciales(formaPago));
			    		acuerdo.setLogCaractEspecRemovidos((!Validaciones.isNullOrEmpty(acuerdo.getLogCaractEspecRemovidos()) ? acuerdo.getLogCaractEspecRemovidos() : "")
			    				+ agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.remover.caracteres"), "Forma de Pago", formaPago)));
			    	}else{
			    		acuerdo.setFormaPago(formaPago);
			    	}
		    	}
		    	
		    	if("34".equalsIgnoreCase(tipoAcuerdo)){
		    		// Acuerdos 34
		    		// Cheque Diferido
		    		
		    		acuerdo.setTipoValor(String.valueOf(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getIdTipoValor()));
		    		logError += validarDatosChequeDiferido(i, campos[5], acuerdo, logError);
					if (!Validaciones.validarFecha(campos[9])) {
						logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.fechaVtoCPDFormato"), campos[9]));
			    	}else{
			    		try {
							acuerdo.setFechaVencimiento(Utilidad.parseDatePickerString(campos[9]));
						} catch (ParseException e) {
							logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.fechaVtoCPDParser"), campos[9]));
						}
			    	} 
		    	}else{
		    		// Acuerdos 28 - 29 - 33 
		    		// Es Cheque o Efectivo
		    		
		        	// Datos Cheque
		    	    if(!Validaciones.isNullOrEmpty(campos[5].trim())){
		        		String[] datosCheque = campos[5].split(SEPARADOR_DATOS_CHEQUE);
		        		if(datosCheque.length==5){ 
		    	    		if(Validaciones.isNumeric(datosCheque[0])){
		    	    			if(String.valueOf(datosCheque[0]).length()==3){
									try {
										ShvParamBanco banco = bancoDao.buscarBanco("0"+datosCheque[0]);
			    	    				if(!Validaciones.isObjectNull(banco)){
			    	    					acuerdo.setCodigoBanco(Long.valueOf(datosCheque[0]));
			    	    				}else{
			    	    					logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.CodigoBancoIncorrecto"), datosCheque[0]));
			    	    				}
									} catch (PersistenciaExcepcion e) {
										logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.CodigoBancoIncorrecto"), datosCheque[0]));
									}
		    	    			}else{
		    	    				logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.CodigoBancoCantidadIncorrecto"), datosCheque[0]));
		    	    			}
		    	    		 }else{
		    	    			 logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.CodigoBancoNulo"), datosCheque[0]));
		    	    		 }
		    	    		 
		    	    		 if(Validaciones.isNumeric(datosCheque[1])){
		    	    			 if(String.valueOf(datosCheque[1]).length()==3){
		    	    				 acuerdo.setSucursal(Long.valueOf(datosCheque[1]));	 
		    	    			 }else{
		    	    				 logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.SucursalIncorrecto"), datosCheque[1]));
		    	    			 }
		    	    		 }else{
		    	    			 logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.SucursalNulo"), datosCheque[1]));
		    	    		 }
		    	    		 
		    	    		 if(Validaciones.isNumeric(datosCheque[2])){
		    	    			 if(String.valueOf(datosCheque[2]).length()==4){
		    	    				 acuerdo.setCodigoPostal(Long.valueOf(datosCheque[2]));
		    	    			 }else{
		    	    				 logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.CodigoPostalIncorrecto"), datosCheque[2]));
		    	    			 }
		    	    		 }else{
		    	    			 logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.CodigoPostalNulo"), datosCheque[2]));
		    	    		 }	
		    	    		 
		    	    		 if(Validaciones.isNumeric(datosCheque[3])){
		    	    			 if(String.valueOf(datosCheque[3]).length()==8){
		    	    				 acuerdo.setNumeroCheque(Long.valueOf(datosCheque[3]));
		    	    			 }else{
		    	    				 logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.NumeroChequeIncorrecto"), datosCheque[3]));
		    	    			 }
		    	    		 }else{
		    	    			 logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.NumeroChequeNulo"), datosCheque[3]));
		    	    		 }
		    	    		 
		    	    		 if(Validaciones.isNumeric(datosCheque[4])){
		    	    			 if(String.valueOf(datosCheque[4]).length()==11){
		    	    				 acuerdo.setCuentaEmisora(Long.valueOf(datosCheque[4]));
		    	    			 }else{
		    	    				 logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.CuentaEmisoraIncorrecto"), datosCheque[4]));
		    	    			 }
		    	    		 }else{
		    	    			 logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.CuentaEmisoraNulo"), datosCheque[4]));
		    	    		 }
		    	    		 
		    	    		 acuerdo.setTipoValor(String.valueOf(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoValor()));
		        		}else{
		        			logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.DatosChequeCantidadIncorrecta"), campos[5]));
		        		}
		       		}else{
		       			acuerdo.setTipoValor(String.valueOf(TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO.getIdTipoValor()));
		       		}
		    	}
		    	
		    	// Estado Acreditacion
		    	String estadoAcreditacion = campos[6];
		    	if(!Validaciones.esFormatoTexto(estadoAcreditacion)){
		    		acuerdo.setEstadoAcreditacion(Utilidad.removerCaracteresEspeciales(estadoAcreditacion));
		    		acuerdo.setLogCaractEspecRemovidos((!Validaciones.isNullOrEmpty(acuerdo.getLogCaractEspecRemovidos()) ? acuerdo.getLogCaractEspecRemovidos() : "")
		    				+ agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.remover.caracteres"), "Estado Acreditacion", estadoAcreditacion)));
		    	} else {
		    		acuerdo.setEstadoAcreditacion(estadoAcreditacion);
		    	}
		    	
		    	// Fecha Acreditacion
				if (!Validaciones.validarFecha(campos[8])) {
					logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.fechaAcreditacionFormato"), campos[8]));
		    	}else{
		    		try {
						acuerdo.setFechaAcreditacion(Utilidad.parseDatePickerString(campos[8]));
					} catch (ParseException e) {
						logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.fechaAcreditacionParser"), campos[8]));
					}
		    	} 
		    	
		    	// Nro Boleta
		    	if (!Validaciones.isNumeric(campos[10])) {
		    		logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.nroBoletaFormato"), campos[10]));
		    	}else{
		    		acuerdo.setNroBoleta(Long.valueOf(campos[10]));
		    	}
		    	 
		    	// Sucursal
		    	String sucursal = campos[11];
		    	if (!Validaciones.esFormatoTexto(sucursal)) {
		    		String sucursalFormateada = Utilidad.removerCaracteresEspeciales(sucursal);
		    		if (Validaciones.isNumeric(sucursalFormateada)){
		    			acuerdo.setSucursal(Long.valueOf(sucursalFormateada));
		    		} else {
		    			acuerdo.setSucursal(null);
		    		}
		    		acuerdo.setLogCaractEspecRemovidos((!Validaciones.isNullOrEmpty(acuerdo.getLogCaractEspecRemovidos()) ? acuerdo.getLogCaractEspecRemovidos() : "")
		    				+ agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.remover.caracteres"), "Sucursal", sucursal)));
		    	} else {
		    		acuerdo.setSucursal((Validaciones.isNumeric(sucursal))?Long.valueOf(sucursal):null);
		    	}
		    	
		    	// Nombre Sucursal
		    	String nombreSucursal = campos[12];
		    	if (!Validaciones.esFormatoTexto(nombreSucursal)) {
		    		acuerdo.setNombreSucursal(Utilidad.removerCaracteresEspeciales(nombreSucursal));
		    		acuerdo.setLogCaractEspecRemovidos((!Validaciones.isNullOrEmpty(acuerdo.getLogCaractEspecRemovidos()) ? acuerdo.getLogCaractEspecRemovidos() : "")
		    				+ agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.remover.caracteres"), "Nombre Sucursal", nombreSucursal)));
		    	} else {
		    		acuerdo.setNombreSucursal(nombreSucursal);
		    	}

		    	
		    	// Grupo Acreedor
		    	String grupoAcreedor = campos[13];
		    	if(!Validaciones.esFormatoTexto(grupoAcreedor)){
		    		acuerdo.setGrupoAcreedor(Utilidad.removerCaracteresEspeciales(grupoAcreedor));
		    		acuerdo.setLogCaractEspecRemovidos((!Validaciones.isNullOrEmpty(acuerdo.getLogCaractEspecRemovidos()) ? acuerdo.getLogCaractEspecRemovidos() : "")
		    				+ agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.remover.caracteres"), "Grupo Acreedor", grupoAcreedor)));
		    	}else{
		    		acuerdo.setGrupoAcreedor(grupoAcreedor);
		    	}
		    	
		    	
		    	// Nombre Cliente
		    	String nombreCliente = campos[14];
		    	if (!Validaciones.esFormatoTexto(nombreCliente)) {
		    		acuerdo.setNombreCliente(Utilidad.removerCaracteresEspeciales(nombreCliente));
		    		acuerdo.setLogCaractEspecRemovidos((!Validaciones.isNullOrEmpty(acuerdo.getLogCaractEspecRemovidos()) ? acuerdo.getLogCaractEspecRemovidos() : "")
		    				+ agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.remover.caracteres"), "Nombre Cliente", nombreCliente)));
		    	} else {
		    		acuerdo.setNombreCliente(nombreCliente);
		    	}
		    	
		    	// Cod.Rechazo
		    	String codigoRechazo = campos[15];
		    	if (!Validaciones.esFormatoTexto(codigoRechazo)) {
		    		acuerdo.setCodigoRechazo(Utilidad.removerCaracteresEspeciales(codigoRechazo));
		    		acuerdo.setLogCaractEspecRemovidos((!Validaciones.isNullOrEmpty(acuerdo.getLogCaractEspecRemovidos()) ? acuerdo.getLogCaractEspecRemovidos() : "")
		    				+ agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.remover.caracteres"), "Codigo Rechazo", codigoRechazo)));
		    	} else {
		    		acuerdo.setCodigoRechazo(codigoRechazo);
		    	}
			}
		}
		
    	// Si hubo algun error lanzo la excepcion con todo el log
    	if (!Validaciones.isNullOrEmpty(logError)){
    		throw new ValidacionExcepcion(Constantes.NO_GRABAR_REGISTROS_AVC, logError);
    	}
    	
    	return acuerdo;
	}

	/**
	 * Método para procesar ACUERDOS 6, 87, 94	
	 * @param linea
	 * @return
	 * @throws ValidacionExcepcion
	 */
	public InterdepositoBatch procesarInterdepositos(String i, String linea, String tipoAcuerdo, List<ShvParamBancoCliente> listaBancoCliente, String secuencialArchivo) throws ValidacionExcepcion {

		String logError = "";
		InterdepositoBatch interdeposito = new InterdepositoBatch(tipoAcuerdo);
		interdeposito.setTipoValor(String.valueOf(TipoValorEnum.INTERDEPÓSITO.getIdTipoValor()));

		if(Validaciones.isNullOrEmpty(linea)){
			logError += agregarAlLogError(i, Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.lineaNula"));
			throw new ValidacionExcepcion(Constantes.GRABAR_REGISTROS_AVC,logError);
		} else {

			String nlinea = linea.replaceAll("\"","");
	
			//Separo los campos de la cadena
			String[] campos = nlinea.split(SEPERADOR_INTERDEPOSITO, -1);
			// IMPORTANTE!!! se valida que vengan 12 campos, porque en el archivo posta viene al final una columna vacia
			if(campos.length != 12){
				logError += agregarAlLogError(i, Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.cantidadCampos"));
			} else {
				String sucursal = campos[6];
				
				//VALIDACION DE LOS FORMATOS DE CAMPO
				// Importe
				String importe = campos[7].replace('.',',');
				if(Validaciones.isNullOrEmpty(importe)){
					logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.ImporteNulo"), campos[7]));
				}else if(importe.startsWith("-")){
					if (!Validaciones.isImporteFormato(importe.substring(1),2)) {
						logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.ImporteFormato"), campos[7]));
					}
					//Si el valor es negativo setear null al importe
					interdeposito.setImporte(null);
					return interdeposito;
				}else if (!Validaciones.isImporteFormato(importe,2)) {
					logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.ImporteFormato"), campos[7]));
				}else{
					interdeposito.setImporte(Utilidad.stringToBigDecimal((importe)));
				}
				// Sucursal				
		    	if(!Validaciones.esFormatoTexto(sucursal)){
		    		interdeposito.setSucursal(Utilidad.removerCaracteresEspeciales(sucursal));
		    		interdeposito.setLogCaractEspecRemovidos((!Validaciones.isNullOrEmpty(interdeposito.getLogCaractEspecRemovidos()) ? interdeposito.getLogCaractEspecRemovidos() : "")
		    				+ agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.remover.caracteres"), "Sucursal", sucursal)));
		    	}else{
		    		interdeposito.setSucursal(sucursal.trim());
		    	}
				
				/**
				 * @fgiaquinta 05-01-2015
				 */
				// Cod. Op. Banco	
		    	String codOpBanco = campos[9];
				if(TipoAcuerdoEnum.INTERDEPOSITO_94.descripcion().equalsIgnoreCase(tipoAcuerdo)){
					if(!Validaciones.isNullOrEmpty(codOpBanco)){
						if(Validaciones.isNumeric(codOpBanco.trim())){

							ShvParamBancoCliente bancoCliente = null;
							if(!Validaciones.isObjectNull(listaBancoCliente)){
								for(ShvParamBancoCliente registro : listaBancoCliente){
									if(!Validaciones.isNullOrEmpty(registro.getCodigoOpBanco())){
										if(registro.getCodigoOpBanco().equals(codOpBanco.trim())){
											bancoCliente = registro;
											break;
										}
									}
								}
							}

							if(!Validaciones.isObjectNull(bancoCliente)){
								interdeposito.setCodOpBanco(Long.valueOf(codOpBanco.trim()));		 
							}else{
								logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.conciliacion.validacion.transferencia.bancoCliente.94"),
										TipoAcuerdoEnum.INTERDEPOSITO_94.descripcion(),String.valueOf(codOpBanco.trim())));
								throw new ValidacionExcepcion(Constantes.GRABAR_REGISTROS_AVC,logError);
							}							
						}else{
							logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.interdeposito.codOpBancoFormato"), codOpBanco));
							throw new ValidacionExcepcion(Constantes.GRABAR_REGISTROS_AVC,logError);
						}
					}else{
						logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.conciliacion.validacion.transferencia.bancoCliente.94"),
								TipoAcuerdoEnum.INTERDEPOSITO_94.descripcion(),"nulo"));
						throw new ValidacionExcepcion(Constantes.GRABAR_REGISTROS_AVC,logError);
					}
				}else{
					if (!Validaciones.isNullOrEmpty(codOpBanco)){
						if(Validaciones.isNumeric(codOpBanco.trim())){
							interdeposito.setCodOpBanco(Long.valueOf(codOpBanco.trim()));
						}else{
							logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.interdeposito.codOpBancoFormato"), codOpBanco));
						}
					}
				}				
							
				// Cod.Operacion
				String codigoOperacion = campos[3];
				if(TipoAcuerdoEnum.INTERDEPOSITO_87.descripcion().equalsIgnoreCase(tipoAcuerdo)){
					if(!Validaciones.isNullOrEmpty(codigoOperacion)) {
						if(!Validaciones.esFormatoTexto(codigoOperacion)){
							codigoOperacion = Utilidad.removerCaracteresEspeciales(codigoOperacion);
				    		interdeposito.setLogCaractEspecRemovidos((!Validaciones.isNullOrEmpty(interdeposito.getLogCaractEspecRemovidos()) ? interdeposito.getLogCaractEspecRemovidos() : "")
				    				+ agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.remover.caracteres"), "Codigo Operacion", codigoOperacion)));
						}
						
						ShvParamBancoCliente bancoCliente = null;
						if(!Validaciones.isObjectNull(listaBancoCliente)){
							for(ShvParamBancoCliente registro : listaBancoCliente){
								if(!Validaciones.isNullOrEmpty(registro.getCodigoOpBanco())
									&& !Validaciones.isNullOrEmpty(registro.getSucursal())	
										){
									if(registro.getCodigoOpBanco().equals(codigoOperacion)
											&& registro.getSucursal().equals(interdeposito.getSucursal())){
										bancoCliente = registro;
										break;
									}
								}
							}
						}
						
						if(!Validaciones.isObjectNull(bancoCliente)){
							interdeposito.setCodigoOperacion(codigoOperacion);
						}else{
							logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.conciliacion.validacion.transferencia.bancoCliente.87"),
									TipoAcuerdoEnum.INTERDEPOSITO_87.descripcion(),String.valueOf(interdeposito.getSucursal()),codigoOperacion));
							throw new ValidacionExcepcion(Constantes.GRABAR_REGISTROS_AVC,logError);
						}	
						
					}else{
						logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.conciliacion.validacion.transferencia.bancoCliente.87"),
								TipoAcuerdoEnum.INTERDEPOSITO_87.descripcion(),String.valueOf(interdeposito.getSucursal()),"nulo"));
						throw new ValidacionExcepcion(Constantes.GRABAR_REGISTROS_AVC,logError);
					}
				}else{
					if(!Validaciones.isNullOrEmpty(codigoOperacion)) {
				    	if(!Validaciones.esFormatoTexto(codigoOperacion)){
				    		interdeposito.setCodigoOperacion(Utilidad.removerCaracteresEspeciales(codigoOperacion));
				    		interdeposito.setLogCaractEspecRemovidos((!Validaciones.isNullOrEmpty(interdeposito.getLogCaractEspecRemovidos()) ? interdeposito.getLogCaractEspecRemovidos() : "")
				    				+ agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.remover.caracteres"), "Codigo Operacion", codigoOperacion)));
				    	}else{
				    		interdeposito.setCodigoOperacion(codigoOperacion);
				    	}
					} else {
						interdeposito.setCodigoOperacion(null);
					}
				}		
				
				// Comprobante
				String comprobante = campos[4];
				if(TipoAcuerdoEnum.INTERDEPOSITO_87.descripcion().equalsIgnoreCase(tipoAcuerdo)){
					if(!Validaciones.isNullOrEmpty(comprobante)){
						comprobante = comprobante.replace(".", "").trim();
						if(Validaciones.isNumeric(comprobante)){
							interdeposito.setComprobante(comprobante);
						}else{
							logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.conciliacion.validacion.interdeposito.comprobante.noValido.87"), comprobante));
							throw new ValidacionExcepcion(Constantes.NO_GRABAR_REGISTROS_AVC,logError);
						}
					}else{
						logError += agregarAlLogError(i, Propiedades.MENSAJES_PROPIEDADES.getString("error.conciliacion.validacion.interdeposito.comprobante.nulo.87"));
						throw new ValidacionExcepcion(Constantes.NO_GRABAR_REGISTROS_AVC,logError);
					}
				}else{
					interdeposito.setComprobante(comprobante.replace(".", "").trim());				
				}
				
				// Fecha Valor
				String fechaValor = campos[0];
				try {
					if(Utilidad.EMPTY_STRING.equals(fechaValor.trim())){
						interdeposito.setFechaValor(null);
					}else {
						Locale locale = new Locale("ES");
						DateFormat sdf = Utilidad.getSimpleDateFormat("dd-MMM-yyyy",locale);
						Date fechaValorDate = sdf.parse(fechaValor);
						interdeposito.setFechaValor(fechaValorDate);
					}
				} catch (ParseException e) {
					try{
						Locale locale = new Locale("EN");
						DateFormat sdf = Utilidad.getSimpleDateFormat("dd-MMM-yyyy",locale);
						Date fechaValorDate = sdf.parse(fechaValor);
						interdeposito.setFechaValor(fechaValorDate);
					} catch (ParseException f) {
						logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.interdeposito.fechaPagoParser"), fechaValor));
					}
				}
				
				// Fecha Ingreso
				String fechaIngreso = campos[1];
				try {
					if(Utilidad.EMPTY_STRING.equals(fechaIngreso.trim())){
						interdeposito.setFechaIngreso(null);
					}else {
						Locale locale = new Locale("ES");
						DateFormat sdf = Utilidad.getSimpleDateFormat("dd-MMM-yyyy",locale);
						sdf.setLenient(false);
						Date fechaIngresoDate = sdf.parse(fechaIngreso);
						interdeposito.setFechaIngreso(fechaIngresoDate);
					}
				} catch (ParseException e) {
					
					try{						
						Locale locale = new Locale("EN");
						DateFormat sdf = Utilidad.getSimpleDateFormat("dd-MMM-yyyy",locale);
						Date fechaIngresoDate = sdf.parse(fechaIngreso);
						interdeposito.setFechaIngreso(fechaIngresoDate);
					} catch (ParseException f) {
						logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.interdeposito.fechaIngresoParser"), fechaIngreso));
					}
				}				
				
				// Concepto
				String concepto = campos[2];
		    	if(!Validaciones.esFormatoTexto(concepto)){
		    		interdeposito.setConcepto(Utilidad.removerCaracteresEspeciales(concepto));
		    		interdeposito.setLogCaractEspecRemovidos((!Validaciones.isNullOrEmpty(interdeposito.getLogCaractEspecRemovidos()) ? interdeposito.getLogCaractEspecRemovidos() : "")
		    				+ agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.remover.caracteres"), "Concepto", concepto)));
		    	}else{
		    		interdeposito.setConcepto(concepto);
		    	}					
		
				// Deposito
				String deposito = campos[5];
		    	if(!Validaciones.esFormatoTexto(deposito)){
		    		interdeposito.setDeposito(Utilidad.removerCaracteresEspeciales(deposito));
		    		interdeposito.setLogCaractEspecRemovidos((!Validaciones.isNullOrEmpty(interdeposito.getLogCaractEspecRemovidos()) ? interdeposito.getLogCaractEspecRemovidos() : "")
		    				+ agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.remover.caracteres"), "Deposito", deposito)));
		    	}else{
		    		interdeposito.setDeposito(deposito);
		    	}

				// El campo descripcion que recibo se debe parsear. Los tres primeros caracteres forman 
				// el SAF, los siguientes cuatro se eliminan y los restantes conforman el SIDIF
		    	String descripcion = campos[8];
		    	if (TipoAcuerdoEnum.INTERDEPOSITO_6.descripcion().equals((interdeposito.getAcuerdo()))){
					if (Validaciones.isNullOrEmpty(descripcion)){
						logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.interdeposito.descripcionNula"), descripcion));
					} else {
						if (descripcion.trim().length() != 15){
							String codigo = descripcion.trim();
							Traza.auditoria(getClass(),"~ Linea " + i +  ": Al ser la descripción '" + codigo + "' con longitud distinta de 15 se procede a buscar la misma en la tabla SHV_PARAM_ORGANISMO.");
							String codOrganismo = buscarCodOrganismoPorDescripcionAlternativa(codigo);
							if(!Validaciones.isNullOrEmpty(codOrganismo)){
								Traza.auditoria(getClass(), "~ Linea " + i +  ": Se encuentra la descripción '" + codigo + "' en SHV_PARAM_ORGANISMO.");
								interdeposito.setCodigoOrganismo(codOrganismo);	
								interdeposito.setCodigoInterdeposito(
										 Utilidad.formatDateAAMMDD(new Date())
										+Utilidad.rellenarCerosIzquierda(secuencialArchivo, 2)
										+Utilidad.rellenarCerosIzquierda(i, 4));
								
							} else {
								Traza.auditoria(getClass(), "~ Linea " + i +  ": No se encontró la descripción '" + codigo + "' en la tabla SHV_PARAM_ORGANISMO.");
								logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.conciliacion.validacion.interdeposito.obligatorio.codigoOrganismo"), codigo));
							}
						} else {
							boolean errorInterdeposito6 = false;
							String codigos = descripcion.trim();
							Traza.auditoria(getClass(),"~ Linea " + i +  ": Descripción '" + codigos + "' con longitud igual a 15 se validarán el codigo de organismo y de interdeposito.");
							String codOrganismo = codigos.substring(INICIO_COD_ORGANISMO, FIN_COD_ORGANISMO);
				
							if(Validaciones.isAlphaNumeric(codOrganismo)){
								interdeposito.setCodigoOrganismo(codOrganismo);			 
							} else {
								errorInterdeposito6 = true;
								logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.interdeposito.codOrganismoFormato"), codOrganismo));
							}
				
							String CodInterdeposito = codigos.substring(INICIO_COD_INTERDEPOSITO, FIN_COD_INTERDEPOSITO);
				
							if(Validaciones.isNumeric(CodInterdeposito)){
								interdeposito.setCodigoInterdeposito(CodInterdeposito);		 
							}else {
								errorInterdeposito6 = true;
								logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.interdeposito.codInterdepositoFormato"), CodInterdeposito));
							}
							
							if(errorInterdeposito6){
								Traza.auditoria(getClass(), "~ Linea " + i +  ": Al haber un error " + logError +" se procede a buscar la descripción '" + codigos + "' en la tabla SHV_PARAM_ORGANISMO.");
								String codigoOrganismo = buscarCodOrganismoPorDescripcionAlternativa(codigos);
								if(!Validaciones.isNullOrEmpty(codigoOrganismo)){
									Traza.auditoria(getClass(), "~ Linea " + i +  ": Se encuentra la descripción '" + codigos + "' en SHV_PARAM_ORGANISMO.");
									interdeposito.setCodigoOrganismo(codigoOrganismo);	
									interdeposito.setCodigoInterdeposito(
											 Utilidad.formatDateAAMMDD(new Date())
											+Utilidad.rellenarCerosIzquierda(secuencialArchivo, 2)
											+Utilidad.rellenarCerosIzquierda(i, 4));
								} else {
									Traza.auditoria(getClass(), "~ Linea " + i +  ": No se encontró la descripción '" + codigos + "' en la tabla SHV_PARAM_ORGANISMO.");
									logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("error.conciliacion.validacion.interdeposito.obligatorio.codigoOrganismo"), codigos));
								}
							} else {
								Traza.auditoria(getClass(), "~ Linea " + i +  ": El codigo de organismo es alfanumerico '" + codOrganismo + "'");
								Traza.auditoria(getClass(), "~ Linea " + i +  ": El codigo de intedeposito es numerico '" + CodInterdeposito + "'");
							}
						}
					}
		    	}

				// PCC
		    	String pcc = campos[10];
		    	if(!Validaciones.esFormatoTexto(pcc)){
		    		interdeposito.setPcc(Utilidad.removerCaracteresEspeciales(pcc));
		    		interdeposito.setLogCaractEspecRemovidos((!Validaciones.isNullOrEmpty(interdeposito.getLogCaractEspecRemovidos()) ? interdeposito.getLogCaractEspecRemovidos() : "")
		    				+ agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.remover.caracteres"), "PCC", pcc)));
		    	}else{
		    		interdeposito.setPcc(pcc);
		    	}
			}
		}
		
		// Si hubo algun error lanzo la excepcion con todo el log
    	if (
			(
			TipoAcuerdoEnum.INTERDEPOSITO_6.descripcion().equalsIgnoreCase(tipoAcuerdo)
			||
			TipoAcuerdoEnum.INTERDEPOSITO_94.descripcion().equalsIgnoreCase(tipoAcuerdo)
			||
			TipoAcuerdoEnum.INTERDEPOSITO_87.descripcion().equalsIgnoreCase(tipoAcuerdo)
			)
    		&& !Validaciones.isNullOrEmpty(logError)){
    		throw new ValidacionExcepcion(Constantes.NO_GRABAR_REGISTROS_AVC,logError);
    	}
    	
		return interdeposito;
	}

	/**
	 * Método para procesar ACUERDOS 125	
	 * @param linea
	 * @return
	 * @throws ValidacionExcepcion
	 */
	public TransferenciaBatch procesarTransferencias(String i, String linea, String tipoAcuerdo, List<ShvParamBancoCliente> listaBancoCliente) throws ValidacionExcepcion {
		
		String logError = "";
		
		TransferenciaBatch transferencia = new TransferenciaBatch(tipoAcuerdo);
		transferencia.setTipoValor(String.valueOf(TipoValorEnum.TRANSFERENCIA.getIdTipoValor()));
		 
		if(Validaciones.isNullOrEmpty(linea)){
			logError += agregarAlLogError(i, Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.lineaNula"));
			throw new ValidacionExcepcion(Constantes.GRABAR_REGISTROS_AVC,logError);
		} else {
		
			String[] campos = linea.split(SEPARADOR_TRANSFERENCIA);
			
			
			//Separo el resto de los campos de la cadena
			if(campos.length != 7){
				logError += agregarAlLogError(i, Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.cantidadCampos"));
			} else {
			
				//VALIDACION DE LOS FORMATOS DE CAMPO
				// Importe
				if(Validaciones.isNullOrEmpty(campos[2])){
		    		logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.ImporteNulo"), campos[2]));
		    	}else if(campos[2].startsWith("-")){
		    		String importe = campos[2].substring(1).replace('.',',');
		    		if (!Validaciones.isImporteFormato(importe,2)) {
		    			logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.ImporteFormato"), campos[2]));
		    		} else {
			    		//Si el valor es negativo setear null al importe
			    		transferencia.setImporte(null);
			    		return transferencia;
		    		}
		    	}else {
		    		String importe = "";
		    		if(campos[2].startsWith("+")){
		    			importe = campos[2].substring(1).replace('.',',');
		    		}else{
		    			importe = campos[2].replace('.',',');
		    		}
		    		if (!Validaciones.isImporteFormato(importe,2)) {
		    			logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.ImporteFormato"), campos[2]));
		    		}else{
		    			transferencia.setImporte(Utilidad.stringToBigDecimal(importe));
		    		}
		    	}
				
				// Fecha de ingreso
				String fecha = campos[0];
				if(Validaciones.isNullOrEmpty(fecha)){
					logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.transferencia.fechaIngresoNula"), fecha));
				}else{
		    		try {
		    			transferencia.setFechaIngreso(Utilidad.parseDateWSFormat(fecha));
					} catch (ParseException e) {
						logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.transferencia.fechaIngresoFormato"), fecha));
					}
				}
				
				// Observacion
				String observacion = campos[1];
		    	if(!Validaciones.esFormatoTexto(observacion)){
		    		transferencia.setObservacion(Utilidad.removerCaracteresEspeciales(observacion));
		    		transferencia.setLogCaractEspecRemovidos((!Validaciones.isNullOrEmpty(transferencia.getLogCaractEspecRemovidos()) ? transferencia.getLogCaractEspecRemovidos() : "")
		    				+ agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.remover.caracteres"), "Observacion", observacion)));
		    	}else{
		    		transferencia.setObservacion(observacion);
		    	}
				
				
				// Referencia
				if (!Validaciones.isNumeric(campos[3])) {
					logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.transferencia.referenciaFormato"), campos[3]));
		    	}else{
		    		transferencia.setReferencia(Long.valueOf(campos[3]));
		    	} 
				
				// Sucursal
		    	String sucursal = campos[4];
		    	if(!Validaciones.esFormatoTexto(sucursal)){
		    		String sucursalFormateada = Utilidad.removerCaracteresEspeciales(sucursal);
		    		if (Validaciones.isNumeric(sucursalFormateada)){
		    			transferencia.setSucursal(Long.valueOf(sucursalFormateada));
		    		} else {
		    			transferencia.setSucursal(null);
		    		}
		    		transferencia.setLogCaractEspecRemovidos((!Validaciones.isNullOrEmpty(transferencia.getLogCaractEspecRemovidos()) ? transferencia.getLogCaractEspecRemovidos() : "")
		    				+ agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.remover.caracteres"), "Sucursal", sucursal)));
		    	}else{
		    		transferencia.setSucursal((Validaciones.isNumeric(sucursal))?Long.valueOf(sucursal):null);
		    	}
				
				// Subtotales
				transferencia.setSubtotal((Validaciones.isNumeric(campos[5]))?Long.valueOf(campos[5]):null);
				
				// Código
		    	String codigo = campos[6];
		    	if(!Validaciones.esFormatoTexto(codigo)){
		    		String codigoFormateada = Utilidad.removerCaracteresEspeciales(codigo);
		    		if (Validaciones.isNumeric(codigoFormateada)){
		    			transferencia.setCodigo(Long.valueOf(codigoFormateada));
		    		} else {
		    			transferencia.setCodigo(null);
		    		}
		    		transferencia.setLogCaractEspecRemovidos((!Validaciones.isNullOrEmpty(transferencia.getLogCaractEspecRemovidos()) ? transferencia.getLogCaractEspecRemovidos() : "")
		    				+ agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.remover.caracteres"), "Codigo", codigo)));
		    	}else{
		    		transferencia.setCodigo((Validaciones.isNumeric(codigo))?Long.valueOf(codigo):null);
		    	}
			}
		}
		
		// Si hubo algun error lanzo la excepcion con todo el log
    	if (!Validaciones.isNullOrEmpty(logError)){
    		throw new ValidacionExcepcion(Constantes.NO_GRABAR_REGISTROS_AVC,logError);
    	}
		
		return transferencia;
	}

	/**
	 * Agregar el detalle del error a la variable logError. Al final le suma el salto de renglon
	 * @param logError
	 * @param i
	 * @param reemplazarMensajes
	 */
	public String agregarAlLogError(String i, String reemplazarMensajes) {
		return Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.error.logProcesamiento"), i, 
				reemplazarMensajes + System.lineSeparator());
	}
	
	private String validarDatosChequeDiferido(String i, String datos, DepositoBatch acuerdo, String logError){
    	// Datos Cheque
	    if(!Validaciones.isNullOrEmpty(datos.trim())){
    		String[] datosCheque = datos.split(SEPARADOR_DATOS_CHEQUE);
    		if(datosCheque.length==5){
	    		if(Validaciones.isNumeric(datosCheque[0])){
	    			if(String.valueOf(datosCheque[0]).length()==3){
	    				acuerdo.setCodigoBanco(Long.valueOf(datosCheque[0]));
	    			}else{
	    				logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.CodigoBancoCantidadIncorrecto"), datosCheque[0]));
	    			}
	    		 }else{
	    			 logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.CodigoBancoNulo"), datosCheque[0]));
	    		 }
	    		 
	    		 if(Validaciones.isNumeric(datosCheque[1])){
	    			 if(String.valueOf(datosCheque[1]).length()==3){
	    				 acuerdo.setSucursal(Long.valueOf(datosCheque[1]));
	    			 }else{
	    				 logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.SucursalIncorrecto"), datosCheque[1]));
	    			 }
	    		 }else{
	    			 logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.SucursalNulo"), datosCheque[1]));
	    		 }
	    		 
	    		 if(Validaciones.isNumeric(datosCheque[2])){
	    			 if(String.valueOf(datosCheque[2]).length()==4){
	    				 acuerdo.setCodigoPostal(Long.valueOf(datosCheque[2]));
	    			 }else{
	    				 logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.CodigoPostalIncorrecto"), datosCheque[2]));
	    			 }
	    		 }else{
	    			 logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.CodigoPostalNulo"), datosCheque[2]));
	    		 }	
	    		 
	    		 if(Validaciones.isNumeric(datosCheque[3])){
	    			 if(String.valueOf(datosCheque[3]).length()==8){
	    				 acuerdo.setNumeroCheque(Long.valueOf(datosCheque[3]));
	    			 }else{
	    				 logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.NumeroChequeIncorrecto"), datosCheque[3]));
	    			 }
	    		 }else{
	    			 logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.NumeroChequeNulo"), datosCheque[3]));
	    		 }
	    		 
	    		 if(Validaciones.isNumeric(datosCheque[4])){
	    			 if(String.valueOf(datosCheque[4]).length()==11){
	    				 acuerdo.setCuentaEmisora(Long.valueOf(datosCheque[4]));
	    			 }else{
	    				 logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.CuentaEmisoraIncorrecto"), datosCheque[4]));
	    			 }
	    		 }else{
	    			 logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.CuentaEmisoraNulo"), datosCheque[4]));
	    		 }
	    		 
    		}else{
    			logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.DatosChequeCantidadIncorrecta"), datos));
    		}
   		} else {
   			logError += agregarAlLogError(i, Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.deposito.DatosChequeIncompletoParaAcuerdo34"), datos));
   		}
	    
	    return logError;
	}

	public String buscarCodOrganismoPorDescripcionAlternativa(String descripcion) {
		ShvParamOrganismo organismo = null;
		try {
			Traza.auditoria(getClass(), "Se busca si existe la descripción '" + descripcion + "' en la tabla SHV_PARAM_ORGANISMO.");
			organismo = organismoDao.buscarDescripcionAlternativa(descripcion);
			
		} catch (PersistenciaExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!Validaciones.isObjectNull(organismo)){
			return organismo.getIdOrganismo();
		} else {
			return null;	
		}	
	}
	
	public IBancoDao getBancoDao() {
		return bancoDao;
	}

	public void setBancoDao(IBancoDao bancoDao) {
		this.bancoDao = bancoDao;
	}

	/**
	 * @return the organismoDao
	 */
	public IOrganismoDao getOrganismoDao() {
		return organismoDao;
	}

	/**
	 * @param organismoDao the organismoDao to set
	 */
	public void setOrganismoDao(IOrganismoDao organismoDao) {
		this.organismoDao = organismoDao;
	}
	
}
