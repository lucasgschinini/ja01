package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.TipoRegistroEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.registros.datos.MicOperacionMasivaCabeceraPie;
import ar.com.telecom.shiva.base.registros.datos.entrada.MicOperacionMasivaEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaRegistroEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.enumeradores.MicOperacionMasivacAgrupacionCamposEntradaEnum;
import ar.com.telecom.shiva.base.registros.mapeos.MicOperacionMasivaMapeador;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.utils.singleton.ValidarAuxiliarSingleton;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.negocio.servicios.IOperacionMasivaArchivaServicio;
import ar.com.telecom.shiva.negocio.servicios.IOperacionMasivaRegistroValidacionServicio;

public class OperacionMasivaArchivoServicioImpl implements IOperacionMasivaArchivaServicio {

	@Autowired 
	ICobroOnlineServicio cobroOnlineServicio;

	@Autowired
	MicOperacionMasivaMapeador micOperacionMasivaMapeoRegistro;
	
	@Autowired
	IOperacionMasivaRegistroValidacionServicio operacionMasivaRegistroValidacionServicio;

	
	private static final int LINEA_CABECERA = 0;
	
	private String validarCabeceraPie(MicOperacionMasivaCabeceraPie reg, boolean cabecera) {
		TipoRegistroEnum tipoRegistro = null;
		String msgError = Utilidad.EMPTY_STRING;

		if (cabecera) {
			tipoRegistro = TipoRegistroEnum.C;
		} else {
			tipoRegistro = TipoRegistroEnum.P;
		}
		if (!tipoRegistro.equals(reg.getTipoRegistro())) {
			msgError = "El registro no es " + ((cabecera) ? TipoRegistroEnum.C.descripcion() : TipoRegistroEnum.P.descripcion());
			System.err.println(msgError);
			Traza.error(getClass(), msgError);
		}
		return msgError;
	}
	
	@Override
	public MicOperacionMasivaEntrada procesarRegistrosSalidaMic(String lineas[], MicOperacionMasivaEntrada reg, String nombreArchivo) throws NegocioExcepcion {
		Traza.auditoria(this.getClass(), "- Se procesan los registros del archivo nombre: " + nombreArchivo);
		Date now = new Date();
		// Leo las lineas del cuerpo omito la primera linea y la ultima
		for (int index = 1; index < lineas.length - 1; index++) {
			Traza.auditoria(this.getClass(), "- Se procesa el registro numero: " + index);
			MicOperacionMasivaRegistroEntrada registro = new MicOperacionMasivaRegistroEntrada();
			this.procesarLinea(index, lineas[index], registro);
			registro.setNombreArchivoOperacionMasivaSalidaMic(nombreArchivo);
			registro.setFechaArchivoOperacionMasivaSalidaMic(now);
			reg.getRegistros().add(registro);
		}
		Traza.auditoria(this.getClass(), "- Se ha finalizado de procesar los registros del archivo nombre: " + nombreArchivo);
		return reg;
	}
	@Override
	public MicOperacionMasivaEntrada validarRegistrosSalidaMic(String lineas[], MicOperacionMasivaEntrada reg, String nombreArchivo) throws NegocioExcepcion {
		Traza.auditoria(this.getClass(), "- Se valida los registros del archivo nombre: " + nombreArchivo);
		// Leo las lineas del cuerpo omito la primera linea y la ultima
		for (int index = 1; index < lineas.length - 1; index++) {
			ValidarAuxiliarSingleton.getInstance().setNrolinea(index);
			operacionMasivaRegistroValidacionServicio.validarRegistro(lineas[index].split("\\|"));
		}
		Traza.auditoria(this.getClass(), "- Se ha finalizado la validacion de los registros del archivo nombre: " + nombreArchivo);
		return reg;
	}
	public String validarLinea(int index, String linea, MicOperacionMasivaRegistroEntrada regSalida) throws NegocioExcepcion {
		ValidarAuxiliarSingleton.getInstance().setNrolinea(index++);
		String[] campos = linea.split("\\" + Constantes.SEPARADOR_PIPE);
		if (campos.length != 110) {
			String mensajeError = Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorCantidades"),
				String.valueOf(campos.length),
				String.valueOf(110)
			);
			ValidarAuxiliarSingleton.getInstance().setLinea(mensajeError);
		}
		
		return "";
	}
	// TODO u578934 maxi
	// Procesar la respuesta del archivo de entrada generado por MIC como respuesta al procesamiento solicitado
	
	public String procesarLinea (int index, String linea, MicOperacionMasivaRegistroEntrada regSalida) throws NegocioExcepcion {
		StringBuffer msgError = new StringBuffer();

		String nroLinea = String.valueOf(index + 1);
		String[] campos = linea.split("\\" + Constantes.SEPARADOR_PIPE);
		//List<Campo> camposSalida = micOperacionMasivaMapeoRegistro.getDefaultFormatoRegistro().getCamposDeserializableOrdenada();
		if (campos.length != 110) { 	//TODO Lean sacar del xml 
			msgError.append("Error de validaciones: El registro nro: ");
			msgError.append(nroLinea);
			msgError.append(" posee un numero erroneo de campos");
			System.err.println(msgError.toString());
			Traza.error(getClass(), msgError.toString());
			throw new NegocioExcepcion(msgError.toString());
		}
		// TODO Refactorizar Luego para utilizar los bean de sprint!!! u578936 maxi. Me equivoque de estrategia!!!

		micOperacionMasivaMapeoRegistro.deserializar(regSalida.getParametrosGenerales(), campos);
		// Datos de cobranza generales
		micOperacionMasivaMapeoRegistro.deserializar(regSalida.getDatosCobranzaGenerales(), campos);
		// Datos deuda
//		// Datos de cobranza apropiacion de deuda
		micOperacionMasivaMapeoRegistro.deserializar(regSalida.getDatosCobranzaApropiacionDeuda(), campos);
		// Datos de cobranza generacion de cargo
		micOperacionMasivaMapeoRegistro.deserializar(regSalida.getDatosCobranzasGeneracionCargoEntrada(), campos);
		// Datos del debito imputado: cliente
		micOperacionMasivaMapeoRegistro.deserializar(regSalida.getDatosClienteImputado(), campos);
		// Datos del debito imputado: datos generales (foto de los datos previos a la imputacion)
		micOperacionMasivaMapeoRegistro.deserializar(regSalida.getDatosDebitoImputadoDatosGenerales(), campos);
		// Datos del debito imputado: tagetik  (foto de los datos previos a la imputacion)
		micOperacionMasivaMapeoRegistro.deserializar(regSalida.getDatosDebitoImputadoTegetik(), campos);
		// Datos del debito imputado: Dacota  (foto de los datos previos a la imputacion)
		micOperacionMasivaMapeoRegistro.deserializar(regSalida.getDatosDebitoImputadoDacota(), campos);
		// Datos del debito imputado: Saldos de terceros  (foto de los datos previos a la imputacion)
		micOperacionMasivaMapeoRegistro.deserializar(regSalida.getDatosDebitoImputadoSaldoTerceros(), campos);

		// Creditos!!!
		// Datos creditos
		micOperacionMasivaMapeoRegistro.deserializar(regSalida.getDatosCreditoAplicadoCliente(), campos);	
		//Datos del credito aplicado: medio de pago (foto de los datos previos a la imputacion)
		micOperacionMasivaMapeoRegistro.deserializar(regSalida.getDatosCreditoAplicadoMedioPago(), campos);
		
		micOperacionMasivaMapeoRegistro.deserializar(regSalida.getDatosCreditoAplicadoNotaCredito(), campos);
		
		micOperacionMasivaMapeoRegistro.deserializar(regSalida.getDatosCreditoAplicadoRemanente(), campos);
		
		// Datos del credito aplicado: datos generales (foto de los datos previos a la imputacion)Datos del credito aplicado: datos generales (foto de los datos previos a la imputacion)
		micOperacionMasivaMapeoRegistro.deserializar(regSalida.getDatosCreditoAplicadoDatosGenerales(), campos);
		// Datos del credito aplicado: Tagetik  (foto de los datos previos a la imputacion)
		micOperacionMasivaMapeoRegistro.deserializar(regSalida.getDatosCreditoAplicadoTagetik(), campos);
//		
		// Datos del credito aplicado: Dacota (foto de los datos previos a la imputacion)
		micOperacionMasivaMapeoRegistro.deserializar(regSalida.getDatosCreditoAplicadoDacota(), campos);
		// Datos del acuerdo de intereses por mora / acuerdo del reintegro: datos generales (foto de los datos previos a la imputacion)
		micOperacionMasivaMapeoRegistro.deserializar(regSalida.getDatosAcuerdoGenerales(), campos);
		// Datos del acuerdo de intereses por mora / acuerdo del reintegro: datos del cliente
		micOperacionMasivaMapeoRegistro.deserializar(regSalida.getDatosAcuerdosCliente(), campos);
		// Datos de respuesta generales: resultado de la invocación a nivel debito
		micOperacionMasivaMapeoRegistro.deserializar(
			regSalida.getDatosRespuestaDebito(),
			MicOperacionMasivacAgrupacionCamposEntradaEnum.RESPUESTA_DEBITO,
			campos
		);
//		// Datos de respuesta generales: resultado de la invocación a nivel credito
		micOperacionMasivaMapeoRegistro.deserializar(
				regSalida.getDatosRespuestaCredito(),
			MicOperacionMasivacAgrupacionCamposEntradaEnum.RESPUESTA_CREDITO,
			campos
		);
//		// Datos de respuesta generales: resultado de la invocación a nivel reintegro (solo si genera cargo por PF)
		micOperacionMasivaMapeoRegistro.deserializar(
			regSalida.getDatosRespuestaReintegro(),
			MicOperacionMasivacAgrupacionCamposEntradaEnum.RESPUESTA_REINTEGRO,
			campos
		);
		
		return "";

	}
	/**
	 * Valida la consistencia del archivo de Operacion masiva dese mic
	 * El archivo debe estar formado por cabecera y pie o cabecera, cuerpo y pie.
	 */
	@Override
	public void validarConsistencia(File archivo, MicOperacionMasivaEntrada reg) throws NegocioExcepcion {
		String msgError = Utilidad.EMPTY_STRING;
		Traza.auditoria(this.getClass(), "Se valida la consistensia del archivo nombre:" + archivo.getName());

		// Recorrer las lineas del archivo y valido cada una. Las correctas las guardo para procesarlas
		String[] lineas = null;
		try {
			lineas = ControlArchivo.leerArchivo(archivo.getAbsolutePath());

			if (Validaciones.isObjectNull(lineas)) {
				msgError = "Error de consistencia: el archivo " + archivo.getName() + " esta vacio.";
				Traza.error(getClass(), msgError);
				ValidarAuxiliarSingleton.getInstance().setLinea(true, msgError);
				throw new NegocioExcepcion(msgError);
				
			} else {
				if (lineas.length < 2) {
					msgError = "Error de consistencia: el archivo " + archivo.getName() + " no posee el formato Cabecera-Cuerpo-Pie o Cabecera-Pie";
					Traza.error(getClass(), msgError);
					ValidarAuxiliarSingleton.getInstance().setLinea(true, msgError);
					throw new NegocioExcepcion(msgError);
				}
			}

			// Valido cabecera
			ValidarAuxiliarSingleton.getInstance().setTipoRegistro(TipoRegistroEnum.C);
			micOperacionMasivaMapeoRegistro.deserializar(reg.getCabecera(), lineas[LINEA_CABECERA].split("\\|"));

			Traza.auditoria(this.getClass(), "Se valida la cabecera del archivo:" + archivo.getName());
			
			msgError = this.validarCabeceraPie(reg.getCabecera(), true);
			if (!Validaciones.isEmptyString(msgError)) {
				ValidarAuxiliarSingleton.getInstance().setLineHeader(msgError);
			}
			// Valido pie
			ValidarAuxiliarSingleton.getInstance().setTipoRegistro(TipoRegistroEnum.P);
			micOperacionMasivaMapeoRegistro.deserializar(reg.getPie(), lineas[lineas.length - 1].split("\\|"));
			msgError = "";
			msgError = this.validarCabeceraPie(reg.getPie(), false);

			Traza.auditoria(this.getClass(), "Se valida la pie del archivo:" + archivo.getName());
			if (!Validaciones.isEmptyString(msgError)) {
				ValidarAuxiliarSingleton.getInstance().setLineHeader(msgError);
			}

			if (reg.getPie().getCantidadRegistros().compareTo(reg.getCabecera().getCantidadRegistros()) != 0) {
				msgError = "La cantidad de registro de la cabecera difiere de la del pie";
				System.err.println(msgError);
				Traza.error(getClass(), msgError);
				ValidarAuxiliarSingleton.getInstance().setLineHeader("Error de consistencia", msgError);
			}
			
			if (reg.getCabecera().getCantidadRegistros().compareTo(Long.valueOf(lineas.length - 2)) != 0) {
				msgError = "La cantidad de registro del cuerpo difiere de la de la cabecera";
				System.err.println(msgError);
				Traza.error(getClass(), msgError);
				ValidarAuxiliarSingleton.getInstance().setLineHeader("Error de consistencia", msgError);
			}
			if (reg.getPie().getCantidadRegistros().compareTo(Long.valueOf(lineas.length - 2)) != 0) {
				msgError = "La cantidad de registro del cuerpo difiere de la de la pie";
				System.err.println(msgError);
				Traza.error(getClass(), msgError);
				ValidarAuxiliarSingleton.getInstance().setLineHeader("Error de consistencia", msgError);
			}
			Traza.auditoria(this.getClass(), "Se ha validado la cabecera y el pie del archivo:" + archivo.getName());
		} catch (ShivaExcepcion e) {
			msgError = "Error de consistencia: No se pudo leer el archivo de nombre: " + archivo.getName();
			System.err.println(msgError);
			Traza.error(getClass(), msgError);
			ValidarAuxiliarSingleton.getInstance().setLinea(false, msgError);
		}
		return;
	}
}
