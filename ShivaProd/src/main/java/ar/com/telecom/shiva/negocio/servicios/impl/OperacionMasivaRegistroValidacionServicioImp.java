package ar.com.telecom.shiva.negocio.servicios.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoConceptoTercerosEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoDUCEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaCyQEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaReclamoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoArchivoOperacionMasivaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClienteTagetikEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDUCEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoFacturaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRegistroEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;
import ar.com.telecom.shiva.base.registros.datos.entrada.enumeradores.MicOperacionMasivaCamposEntradaEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.singleton.ValidarAuxiliarSingleton;
import ar.com.telecom.shiva.negocio.servicios.IOperacionMasivaRegistroValidacionServicio;

public class OperacionMasivaRegistroValidacionServicioImp implements IOperacionMasivaRegistroValidacionServicio {

	public OperacionMasivaRegistroValidacionServicioImp() {
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean validarRegistro(String[] campos) {
		boolean validado = true;
		
		String campoValue = "";
		MicOperacionMasivaCamposEntradaEnum campo = null;
		Map<String, Boolean> validarGrupos = new HashMap<String, Boolean>();
		Map<String, TipoResultadoEnum> rtaMic = new HashMap<String, TipoResultadoEnum>();

		validarGrupos.put("validarCobranzaApropiacionDeDeuda", false);
		validarGrupos.put("validarCobranzaGeneracionCargo", false);
		validarGrupos.put("validarDebitoImputadoCliente", false);
		validarGrupos.put("validarDebitoImputadoDatosGenerales", false);
		validarGrupos.put("validarDebitoImputadoTagetik", false);
		validarGrupos.put("validarDebitoDakota", false);
		validarGrupos.put("validarSaldoTerceros", false);
		validarGrupos.put("validarCrediotAplicadoCliente", false);
		validarGrupos.put("validarMedioPago", false);
		validarGrupos.put("validarNotaCredito", false);
		validarGrupos.put("validarCreditoRemanete", false);
		validarGrupos.put("validarCreditoDatosGenerales", false);
		validarGrupos.put("validarCreditoAplicadoTagetik", false);
		validarGrupos.put("validarAcuerdoInteresPorMoraDatosGenerales", false);
		validarGrupos.put("validarAcuerdoInteresPorMoraCliente", false);
		validarGrupos.put("validarCreditoAplicadoDakota", false);
		validarGrupos.put("validarRespuestasResultadoDebito", false);
		validarGrupos.put("validarRespuestasResultadoCredito", false);
		validarGrupos.put("validarRespuestasResultadoReintegro", false);

		
		rtaMic.put("validarRespuestasResultadoDebito", null);
		rtaMic.put("validarRespuestasResultadoCredito", null);
		rtaMic.put("validarRespuestasResultadoReintegro", null);

		if (campos.length != Constantes.OPERACION_MASIVA_CANTIDAD_REGISTROS) {
			String mensajeError = Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorCantidades"),
				String.valueOf(campos.length),
				String.valueOf(Constantes.OPERACION_MASIVA_CANTIDAD_REGISTROS)
			);
			ValidarAuxiliarSingleton.getInstance().setLinea(mensajeError);
			return false;
		}
		
		// Parametros generales del registro de salida
		if (!this.validarParametrosGenerales(true, campos)) {
			validado = false;
		}

		campo = MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_TIPO_INVOCACION;
		campoValue = campos[campo.posicion()];
		
		TipoArchivoOperacionMasivaEnum tipo = TipoArchivoOperacionMasivaEnum.getEnumByCodigo(campoValue);
		if (Validaciones.isObjectNull(tipo)) {
			return false;
		}
		switch (tipo) {
		case DEUDA:
			validarGrupos.put("validarCobranzaApropiacionDeDeuda", true);
			validarGrupos.put("validarDebitoImputadoCliente", true);
			validarGrupos.put("validarDebitoImputadoDatosGenerales", true);
			validarGrupos.put("validarDebitoImputadoTagetik", true);
			validarGrupos.put("validarDebitoDakota", true);
			validarGrupos.put("validarSaldoTerceros", true);
			validarGrupos.put("validarCrediotAplicadoCliente", true);
			validarGrupos.put("validarMedioPago", true);
			validarGrupos.put("validarNotaCredito", true);
			validarGrupos.put("validarCreditoRemanete", true);
			validarGrupos.put("validarCreditoDatosGenerales", true);
			validarGrupos.put("validarCreditoAplicadoDakota", true);
			validarGrupos.put("validarCreditoAplicadoTagetik", true);
			validarGrupos.put("validarAcuerdoInteresPorMoraDatosGenerales", true);
			validarGrupos.put("validarAcuerdoInteresPorMoraCliente", true);
			validarGrupos.put("validarRespuestasResultadoDebito", true);
			validarGrupos.put("validarRespuestasResultadoCredito", true);
			
			break;
		case DSIST:
			validarGrupos.put("validarDebitoImputadoCliente", true);
			validarGrupos.put("validarDebitoImputadoDatosGenerales", true);
			validarGrupos.put("validarDebitoImputadoTagetik", true);
			validarGrupos.put("validarDebitoDakota", true);
			validarGrupos.put("validarSaldoTerceros", true);
			validarGrupos.put("validarRespuestasResultadoDebito", true);
			
			break;
		case GNCIA:
			validarGrupos.put("validarCrediotAplicadoCliente", true);
			validarGrupos.put("validarMedioPago", true);
			validarGrupos.put("validarNotaCredito", true);
			validarGrupos.put("validarCreditoRemanete", true);
			validarGrupos.put("validarCreditoDatosGenerales", true);
			validarGrupos.put("validarCreditoAplicadoTagetik", true);
			validarGrupos.put("validarCreditoAplicadoDakota", true);
			validarGrupos.put("validarRespuestasResultadoCredito", true);
			break;
		case REINT:
			validarGrupos.put("validarCobranzaGeneracionCargo", true);
			validarGrupos.put("validarCrediotAplicadoCliente", true);
			validarGrupos.put("validarMedioPago", true);
			validarGrupos.put("validarNotaCredito", true);
			validarGrupos.put("validarCreditoRemanete", true);
			validarGrupos.put("validarCreditoDatosGenerales", true);
			validarGrupos.put("validarCreditoAplicadoTagetik", true);
			validarGrupos.put("validarCreditoAplicadoDakota", true);
			validarGrupos.put("validarAcuerdoInteresPorMoraDatosGenerales", true);
			validarGrupos.put("validarAcuerdoInteresPorMoraCliente", true);
			validarGrupos.put("validarRespuestasResultadoReintegro", true);
			validarGrupos.put("validarRespuestasResultadoCredito", true);
			break;
			default:
		}
		// Valido primero el resltado de la operacion
		if (!this.validarRespuestasResultadoDebito(validarGrupos.get("validarRespuestasResultadoDebito"), campos, rtaMic)) {
			validado = false;
		}
		if (!this.validarRespuestasResultadoCredito(validarGrupos.get("validarRespuestasResultadoCredito"), campos, rtaMic)) {
			validado = false;
		}
		if (!this.validarRespuestasResultadoReintegro(validarGrupos.get("validarRespuestasResultadoReintegro"), campos, rtaMic)) {
			validado = false;
		}
		TipoArchivoOperacionMasivaEnum tipoArchivo = TipoArchivoOperacionMasivaEnum.getEnumByCodigo(
			campos[MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_TIPO_INVOCACION.posicion()]
		);
		boolean rta = false;
		if (!Validaciones.isObjectNull(tipoArchivo)) {
			if (verficarResultado(tipoArchivo, rtaMic)) {
				rta = true;
			} else {
				rta = false;
			}
		} else {
			rta = true;
		}

		// Datos de cobranza generales
		if (!this.validarCobranzaGenerales(true, campos, rta)) {
			validado = false;
		}

		if (this.validarCobranzaApropiacionDeDeuda(validarGrupos.get("validarCobranzaApropiacionDeDeuda"), campos, rta, validarGrupos)) {
			validado = false;
		}
		if (this.validarDebitoImputadoCliente(validarGrupos.get("validarDebitoImputadoCliente"), campos, rta, validarGrupos)) {
			validado = false;
		}
		if (this.validarDebitoImputadoDatosGenerales(validarGrupos.get("validarDebitoImputadoDatosGenerales"), campos, rta, validarGrupos)) {
			validado = false;
		}
		if (this.validarDebitoImputadoTagetik(validarGrupos.get("validarDebitoImputadoTagetik"), campos, rta, validarGrupos)) {
			validado = false;
		}
		if (this.validarDebitoDakota(validarGrupos.get("validarDebitoDakota"), campos, rta, validarGrupos)) {
			validado = false;
		}
		if (this.validarSaldoTerceros(validarGrupos.get("validarSaldoTerceros"), campos, rta, validarGrupos)) {
			validado = false;
		}
		if (this.validarCrediotAplicadoCliente(validarGrupos.get("validarCrediotAplicadoCliente"), campos, rta, validarGrupos)) {
			validado = false;
		}
		if (this.validarMedioPago(validarGrupos.get("validarMedioPago"), campos, rta, validarGrupos)) {
			validado = false;
		}
		if (this.validarNotaCredito(validarGrupos.get("validarNotaCredito"), campos, rta, validarGrupos)) {
			validado = false;
		}
		if (this.validarCreditoRemanete(validarGrupos.get("validarCreditoRemanete"), campos, rta, validarGrupos)) {
			validado = false;
		}
		if (this.validarCreditoDatosGenerales(validarGrupos.get("validarCreditoDatosGenerales"), campos, rta, validarGrupos)) {
			validado = false;
		}
		if (this.validarCreditoAplicadoTagetik(validarGrupos.get("validarCreditoAplicadoTagetik"), campos, rta, validarGrupos)) {
			validado = false;
		}
		if (this.validarAcuerdoInteresPorMoraDatosGenerales(validarGrupos.get("validarAcuerdoInteresPorMoraDatosGenerales"), campos, rta, validarGrupos)) {
			validado = false;
		}
		if (this.validarAcuerdoInteresPorMoraCliente(validarGrupos.get("validarAcuerdoInteresPorMoraCliente"), campos, rta, validarGrupos)) {
			validado = false;
		}
		if (this.validarCreditoAplicadoDakota(validarGrupos.get("validarCreditoAplicadoDakota"), campos, rta, validarGrupos)) {
			validado = false;
		}
	
		return validado;
	}
	
	
	
	//Parametros generales del registro de salida
	@Override
	public boolean validarParametrosGenerales(boolean aplica, String[] campos) {
		boolean validado = true;
		if(aplica){
			// Tipo de Registro
			MicOperacionMasivaCamposEntradaEnum campo = MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_TIPO_REGISTRO;
			String campoValue = campos[campo.posicion()];

			if (campo.longitud() != campoValue.length()) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorLongitud"),
						String.valueOf(campoValue.length()),
						String.valueOf(campo.longitud())
						);
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
			}
			if (!TipoRegistroEnum.D.name().equals(campoValue.trim())) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorImagen"),
						String.valueOf(campoValue.trim()),
						String.valueOf(TipoRegistroEnum.D.name())
						);
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
			}
			// Id de Operación Masiva Shiva"
			campo = MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_ID_OPERACION_MASIVA_SHIVA;
			campoValue = campos[campo.posicion()];
			if (campo.longitud() != campoValue.length()) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorLongitud"),
						String.valueOf(campoValue.length()),
						String.valueOf(campo.longitud())
						);
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
			}
			if (!Validaciones.isNumeric(campoValue)) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.numerico"),
						String.valueOf(campoValue)
						);
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
			}
			if (Validaciones.isEmptyNumericoSerializado(campoValue)) {
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.nulo"));
				validado  = false;
			}
			// Tipo de invocación
			campo = MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_TIPO_INVOCACION;
			campoValue = campos[campo.posicion()];
			if (campo.longitud() != campoValue.length()) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorLongitud"),
						String.valueOf(campoValue.length()),
						String.valueOf(campo.longitud())
						);
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
			}
			if (Validaciones.isEmptyNumericoSerializado(campoValue)) {
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.nulo"));
				validado  = false;
			}
			TipoArchivoOperacionMasivaEnum  tipoArchivoOperacionMasivaEnum = TipoArchivoOperacionMasivaEnum.getEnumByCodigo(campoValue);
			if (Validaciones.isObjectNull(tipoArchivoOperacionMasivaEnum)) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorImagen"),
						campoValue.trim(),
						TipoArchivoOperacionMasivaEnum.DEUDA.getCodigo() + "|" + 
								TipoArchivoOperacionMasivaEnum.DSIST.getCodigo() + "|" + 
								TipoArchivoOperacionMasivaEnum.REINT.getCodigo() + "|" +
								TipoArchivoOperacionMasivaEnum.GNCIA.getCodigo()
						);
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
			}
			// Id de Operación de Shiva
			campo = MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_ID_OPERACION_SHIVA;
			campoValue = campos[campo.posicion()];
			if (campo.longitud() != campoValue.length()) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorLongitud"),
						String.valueOf(campoValue.length()),
						String.valueOf(campo.longitud())
						);
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
			}
			if (!Validaciones.isNumeric(campoValue)) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.numerico"),
						String.valueOf(campoValue)
						);
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
			}
			if (Validaciones.isEmptyNumericoSerializado(campoValue)) {
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.nulo"));
				validado  = false;
			}
			//Id de secuencia de Transacción
			campo = MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_ID_SECUENCIA_TRANSACCION;
			campoValue = campos[campo.posicion()];
			if (campo.longitud() != campoValue.length()) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorLongitud"),
						String.valueOf(campoValue.length()),
						String.valueOf(campo.longitud())
						);
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
			}
			if (!Validaciones.isNumeric(campoValue)) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.numerico"),
						String.valueOf(campoValue)
						);
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
			}
			if (Validaciones.isEmptyNumericoSerializado(campoValue)) {
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.nulo"));
				validado  = false;
			}
		} else {

			// Tipo de Registro
			MicOperacionMasivaCamposEntradaEnum campo = MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_TIPO_REGISTRO;
			String campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			
			// Id de Operación Masiva Shiva"
			campo = MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_ID_OPERACION_MASIVA_SHIVA;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			
			// Tipo de invocación
			campo = MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_TIPO_INVOCACION;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			
			// Id de Operación de Shiva
			campo = MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_ID_OPERACION_SHIVA;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			
			//Id de secuencia de Transacción
			campo = MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_ID_SECUENCIA_TRANSACCION;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
		}
		return validado;
	}
	
	// Datos de cobranza generales
	@Override
	public boolean validarCobranzaGenerales(boolean aplica, String[] campos, boolean carryRta) {
		boolean validado = true;

		if (aplica) {
			// Id de Cobranza
			MicOperacionMasivaCamposEntradaEnum campo = MicOperacionMasivaCamposEntradaEnum.COBRANZA_GENERALES_ID_COBRANZA;
			String campoValue = campos[campo.posicion()];
			if (campo.longitud() != campoValue.length()) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorLongitud"),
						String.valueOf(campoValue.length()),
						String.valueOf(campo.longitud())
						);
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
			}
			if (!Validaciones.isNumeric(campoValue)) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.numerico"),
						String.valueOf(campoValue)
						);
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
			}
			if (Validaciones.isEmptyNumericoSerializado(campoValue)) {
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.nulo"));
				validado  = false;
			}
			// Fecha valor de la cobranza
			campo = MicOperacionMasivaCamposEntradaEnum.COBRANZA_GENERALES_FECHA_VALOR_COBRANZA;
			campoValue = campos[campo.posicion()];

			if (!validarCampoFecha(campo, campoValue, campos, carryRta)) {
				validado = false;
			}
		} else {
			// Id de Cobranza
			MicOperacionMasivaCamposEntradaEnum campo = MicOperacionMasivaCamposEntradaEnum.COBRANZA_GENERALES_ID_COBRANZA;
			String campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			
			// Fecha valor de la cobranza
			campo = MicOperacionMasivaCamposEntradaEnum.COBRANZA_GENERALES_FECHA_VALOR_COBRANZA;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
		}
		return validado;
	}
	// Datos de cobranza apropiacion de deuda
	@Override
	public boolean validarCobranzaApropiacionDeDeuda(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos) {
		boolean validado = true;

		if (!carryRta) {
			aplica = false;
		}
		if (aplica) {
			// Intereses trasladados regulados
			MicOperacionMasivaCamposEntradaEnum campo = MicOperacionMasivaCamposEntradaEnum.COBRANZA_APROPIACION_D_INTERESES_TRASLADADOS_REGULADOS;
			String campoValue = campos[campo.posicion()];

			if (verificarCampoNumerico(campo, campoValue, campos)) {
				validado = false;
			}

			campo = MicOperacionMasivaCamposEntradaEnum.COBRANZA_APROPIACION_D_INTERESES_TRASLADADOS_NO_REGULADOS;
			campoValue = campos[campo.posicion()];
			
			if (verificarCampoNumerico(campo, campoValue, campos)) {
				validado = false;
			}
			campo = MicOperacionMasivaCamposEntradaEnum.COBRANZA_APROPIACION_D_INTERESES_BONIFICADOS_REGULADOS;
			campoValue = campos[campo.posicion()];
			
			if (verificarCampoNumerico(campo, campoValue, campos)) {
				validado = false;
			}
			campo = MicOperacionMasivaCamposEntradaEnum.COBRANZA_APROPIACION_D_INTERESES_BONIFICADOS_NO_REGULADOS;
			campoValue = campos[campo.posicion()];
			if (verificarCampoNumerico(campo, campoValue, campos)) {
				validado = false;
			}
		} else {
			
			MicOperacionMasivaCamposEntradaEnum campo = MicOperacionMasivaCamposEntradaEnum.COBRANZA_APROPIACION_D_INTERESES_TRASLADADOS_REGULADOS;
			String campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}

			campo = MicOperacionMasivaCamposEntradaEnum.COBRANZA_APROPIACION_D_INTERESES_TRASLADADOS_NO_REGULADOS;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			
			campo = MicOperacionMasivaCamposEntradaEnum.COBRANZA_APROPIACION_D_INTERESES_BONIFICADOS_REGULADOS;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			
			campo = MicOperacionMasivaCamposEntradaEnum.COBRANZA_APROPIACION_D_INTERESES_BONIFICADOS_NO_REGULADOS;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
		}
		return validado;
	}
	// Datos del debito imputado: cliente
	@Override
	public boolean validarDebitoImputadoCliente(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos) {
		boolean validado = true;
		if (!carryRta) {
			aplica = false;
		}
		if (aplica) {
			MicOperacionMasivaCamposEntradaEnum campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_CODIGO_CLIENTE;
			String campoValue = campos[campo.posicion()];
			validado = verificarCampoNumerico(campo, campoValue, campos);
		} else {
			MicOperacionMasivaCamposEntradaEnum campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_CODIGO_CLIENTE;
			String campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
		}
		return validado;
	}

	// Datos del debito imputado: datos generales (foto de los datos previos a la imputacion)
	@Override
	public boolean validarDebitoImputadoDatosGenerales(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos) {
		boolean validado = true;
		TipoComprobanteEnum tipoDocumento_18 = null;
		if (!carryRta) {
			aplica = false;
		}
		if (aplica) {
			//Cuenta
			MicOperacionMasivaCamposEntradaEnum campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CUENTA;
			String campoValue = campos[campo.posicion()];
			validado = verificarCampoNumerico(campo, campoValue, campos);
	
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_TIPO_DOCUMENTO;
			campoValue = campos[campo.posicion()];
	
			// Tipo de Documento
			if (campo.longitud() != campoValue.length()) {
				String mensajeError = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorLongitud"),
					String.valueOf(campoValue.length()),
					String.valueOf(campo.longitud())
				);
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
			}
			campoValue = campoValue.trim();
			if (
				!TipoComprobanteEnum.CON.getValor().equals(campoValue) &&
				!TipoComprobanteEnum.DEB.getValor().equals(campoValue) &&
				!TipoComprobanteEnum.DUC.getValor().equals(campoValue) &&
				!TipoComprobanteEnum.FAC.getValor().equals(campoValue)
			) {
				String mensajeError = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString(
						"error.operacionMasiva.validarCampos.mic.errorImagen"),
						campoValue.trim(),
						TipoComprobanteEnum.CON.getValor() + "|" + 
							TipoComprobanteEnum.DEB.getValor() + "|" + 
							TipoComprobanteEnum.DUC.getValor() + "|" +
							TipoComprobanteEnum.FAC.getValor()
				);
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
			} else {
				tipoDocumento_18 = TipoComprobanteEnum.getEnumByValor(campoValue);
			}
			
			// Codigo Tipo de DUC
			if (!Validaciones.isObjectNull(tipoDocumento_18) && TipoComprobanteEnum.DUC.equals(tipoDocumento_18)) {
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_TIPO_DUC;
				campoValue = campos[campo.posicion()];

				if (campo.longitud() != campoValue.length()) {
					String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorLongitud"),
						String.valueOf(campoValue.length()),
						String.valueOf(campo.longitud())
					);
					ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
					validado  = false;
				}
				
				TipoDUCEnum tipoDucEnum = null;
				tipoDucEnum = TipoDUCEnum.getEnumByCodigo(campoValue.trim());
				if (Validaciones.isObjectNull(tipoDucEnum)) {
					String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString(
							"error.operacionMasiva.validarCampos.mic.errorImagen"),
							campoValue.trim(),
							TipoDUCEnum.getStringValues()
						);
					ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
					validado  = false;
				}
				// Descripcion Tipo de DUC... 20
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_TIPO_DUC;
				campoValue = campos[campo.posicion()];
				tipoDucEnum = null;
				if (campo.longitud() != campoValue.length()) {
					String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorLongitud"),
						String.valueOf(campoValue.length()),
						String.valueOf(campo.longitud())
					);
					ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
					validado  = false;
				}
				tipoDucEnum = TipoDUCEnum.getEnumByDescripcion(campoValue.trim());
				if (Validaciones.isObjectNull(tipoDucEnum)) {
					String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString(
							"error.operacionMasiva.validarCampos.mic.errorImagen"),
							campoValue.trim(),
							TipoDUCEnum.getStringValues()
						);
					ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
					validado  = false;
				}
				// Codigo Estado del DUC
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_DUC;
				campoValue = campos[campo.posicion()];
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				campoValue = campoValue.trim();
				if (
					!EstadoDUCEnum.GENERADO.codigo().equals(campoValue.trim()) &&
					!EstadoDUCEnum.ENVIADO.codigo().equals(campoValue.trim()) &&
					!EstadoDUCEnum.VENCIDO.codigo().equals(campoValue.trim()) &&
					!EstadoDUCEnum.CONV_BAJA_INEX.codigo().equals(campoValue.trim())
				) {
						String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString(
								"error.operacionMasiva.validarCampos.mic.errorImagen"),
								campoValue.trim(),
								EstadoDUCEnum.GENERADO.codigo() + "|" + 
										EstadoDUCEnum.ENVIADO.codigo() + "|" + 
										EstadoDUCEnum.VENCIDO.codigo() + "|" +
										EstadoDUCEnum.CONV_BAJA_INEX.codigo() 
						);
						ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
						validado  = false;
				}
				// Descripción Estado del DUC
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_DUC;
				campoValue = campos[campo.posicion()];
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				campoValue = campoValue.trim();
				if (
					!EstadoDUCEnum.GENERADO.descripcion().equals(campoValue.trim()) &&
					!EstadoDUCEnum.ENVIADO.descripcion().equals(campoValue.trim()) &&
					!EstadoDUCEnum.VENCIDO.descripcion().equals(campoValue.trim()) &&
					!EstadoDUCEnum.CONV_BAJA_INEX.descripcion().equals(campoValue.trim())
				) {
						String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString(
								"error.operacionMasiva.validarCampos.mic.errorImagen"),
								campoValue.trim(),
								EstadoDUCEnum.GENERADO.descripcion() + "|" + 
										EstadoDUCEnum.ENVIADO.descripcion() + "|" + 
										EstadoDUCEnum.VENCIDO.descripcion() + "|" +
										EstadoDUCEnum.CONV_BAJA_INEX.descripcion() 
						);
						ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
						validado  = false;
				}
				
			} else {
				// Codigo Tipo de DUC
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_TIPO_DUC;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				
				// Descripcion Tipo de DUC
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_TIPO_DUC;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Codigo Estado del DUC
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_DUC;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Descripción Estado del DUC
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_DUC;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
			}
			// Acuerdo
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_ACUERDO;
			campoValue = campos[campo.posicion()];
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			if (!Validaciones.isObjectNull(tipoDocumento_18) && !TipoComprobanteEnum.DUC.equals(tipoDocumento_18)) {
				//Clase de Comprobante
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CLASE_COMPROBANTE;
				campoValue = campos[campo.posicion()];
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				campoValue = campoValue.trim();
				if (
					!"A".equals(campoValue.trim()) &&
					!"B".equals(campoValue.trim()) &&
					!"D".equals(campoValue.trim()) &&
					!"S".equals(campoValue.trim())
				) {
					String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString(
							"error.operacionMasiva.validarCampos.mic.errorImagen"),
							campoValue.trim(),
							"A / B / D (sin letra factura mixta) / S (cuando es sin letra)"
						);
						ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
						validado  = false;
				}
				// Sucursal del Comprobante
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_SUCURSAL_COMPROBANTE;
				campoValue = campos[campo.posicion()];
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if (!verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
				// Numero del Comprobante
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_NUMERO_COMPROBANTE;
				campoValue = campos[campo.posicion()];
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if (!verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
				// Numero de Referencia MIC
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_NUMERO_REFERENCIA_MIC;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
			} else {
				//Clase de Comprobante
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CLASE_COMPROBANTE;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Sucursal del Comprobante
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_SUCURSAL_COMPROBANTE;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Numero del Comprobante
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_NUMERO_COMPROBANTE;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Numero de Referencia MIC
				campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_NUMERO_REFERENCIA_MIC;
				campoValue = campos[campo.posicion()];
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
			}	
			// Fecha Vencimiento
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_FECHA_VENCIMIENTO;
			campoValue = campos[campo.posicion()];
			
			if (!validarCampoFecha(campo, campoValue, campos, carryRta)) {
				validado = false;
			}
			// Importe al 1er vencimiento
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_PRIMER_VENCIMIENTO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNumerico(campo, campoValue, campos)) {
				validado = false;
			}
			// Importe al 2do vencimiento
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_SEGUNDO_VENCIMIENTO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNumerico(campo, campoValue, campos)) {
				validado = false;
			}
			// Saldo al 1er vencimiento
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_SALDO_PRIMER_VENCIMIENTO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNumerico(campo, campoValue, campos)) {
				validado = false;
			}
			// Codigo Estado acuerdo Facturación
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_ACUERDO_FACTURACION;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNumerico(campo, campoValue, campos)) {
				validado = false;
			}
			campoValue = campoValue.trim();
			if (
				!EstadoAcuerdoFacturacionEnum.POTENCIAL.codigo().equals(campoValue.trim()) &&
				!EstadoAcuerdoFacturacionEnum.ACTIVO.codigo().equals(campoValue.trim()) &&
				!EstadoAcuerdoFacturacionEnum.INCOMUNICADO.codigo().equals(campoValue.trim()) &&
				!EstadoAcuerdoFacturacionEnum.BAJA_DEFINITIVA.codigo().equals(campoValue.trim())
			) {
				String mensajeError = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString(
						"error.operacionMasiva.validarCampos.mic.errorImagen"),
						campoValue.trim(),
						EstadoAcuerdoFacturacionEnum.POTENCIAL.codigo() + " | " +
						EstadoAcuerdoFacturacionEnum.ACTIVO.codigo() + " | " +
						EstadoAcuerdoFacturacionEnum.INCOMUNICADO.codigo() + " | "+
						EstadoAcuerdoFacturacionEnum.BAJA_DEFINITIVA.codigo()
				);
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
			}
	
			// Descripcion Estado acuerdo Factuación
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_ACUERDO_FACTURACION;
			campoValue = campos[campo.posicion()];
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			campoValue = campoValue.trim();
			if (
				!"POTENCIAL".equals(campoValue.trim()) &&
				!"ACTIVO".equals(campoValue.trim()) &&
				!"INCOMUNICA".equals(campoValue.trim()) &&
				!"BAJA DEFIN".equals(campoValue.trim())
			) {
				String mensajeError = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString(
						"error.operacionMasiva.validarCampos.mic.errorImagen"),
						campoValue.trim(),
						"POTENCIAL" + " - " +
						"ACTIVO" + " - " +
						"INCOMUNICA" + " - "+
						"BAJA DEFIN"
					);
					ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
					validado  = false;
			}
			//validarCampoSiNo
			// Estado Conceptos de Terceros
			EstadoConceptoTercerosEnum conseptoTerceros = null;
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_ESTADO_CONCEPTOS_TERCEROS;
			campoValue = campos[campo.posicion()];
			
			List<String> opcionesConseptoTerceros = new ArrayList<String>();
			StringBuffer leyendaOpcionesConseptoTerceros = new StringBuffer();
			leyendaOpcionesConseptoTerceros.append(EstadoConceptoTercerosEnum.NO_TRANSFERIDOS.getCodigoMic());
			leyendaOpcionesConseptoTerceros.append("(");
			leyendaOpcionesConseptoTerceros.append(EstadoConceptoTercerosEnum.NO_TRANSFERIDOS.name());
			leyendaOpcionesConseptoTerceros.append(") | ");
			leyendaOpcionesConseptoTerceros.append(EstadoConceptoTercerosEnum.PARCIALMENTE_TRANSFERIDOS.getCodigoMic());
			leyendaOpcionesConseptoTerceros.append("(");
			leyendaOpcionesConseptoTerceros.append(EstadoConceptoTercerosEnum.PARCIALMENTE_TRANSFERIDOS.name());
			leyendaOpcionesConseptoTerceros.append(") | ");
			leyendaOpcionesConseptoTerceros.append(EstadoConceptoTercerosEnum.TOTALMENTE_TRANSFERIDOS.getCodigoMic());
			leyendaOpcionesConseptoTerceros.append(").");

			opcionesConseptoTerceros.add(EstadoConceptoTercerosEnum.NO_TRANSFERIDOS.getCodigoMic());
			opcionesConseptoTerceros.add(EstadoConceptoTercerosEnum.PARCIALMENTE_TRANSFERIDOS.getCodigoMic());
			opcionesConseptoTerceros.add(EstadoConceptoTercerosEnum.TOTALMENTE_TRANSFERIDOS.getCodigoMic());

			if (!verificarCampoEnGrupo(opcionesConseptoTerceros, leyendaOpcionesConseptoTerceros.toString(), campo, campoValue, campos)) {
				validado = false;
			} else {
				conseptoTerceros = EstadoConceptoTercerosEnum.getEnumByCodigoMic(campoValue.trim());
			}
			
			// Posibilidad Destransferencia
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_POSIBILIDAD_DESTRANSFERENCIA;
			campoValue = campos[campo.posicion()];
			if (!validarCampoSiNo(campo, campoValue, campos)) {
				validado = false;
			}
			
			// Importe 3eros Transferidos
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_TERCEROS_TRANSFERIDOS;
			campoValue = campos[campo.posicion()];
			if (
				EstadoConceptoTercerosEnum.PARCIALMENTE_TRANSFERIDOS.equals(conseptoTerceros) ||
				EstadoConceptoTercerosEnum.TOTALMENTE_TRANSFERIDOS.equals(conseptoTerceros)
			) {
				if (!verificarCampoNumerico(campo, campoValue, campos)) {
					validado = false;
				}
			} else {
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
			}
			// Importe 1er vencimiento con 3eros
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_PRIMER_VENCIMIENTO_TERCEROS;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNumerico(campo, campoValue, campos)) {
				validado = false;
			}
			
			
			// Importe 2do vencimiento con 3eros
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_SEGUNDO_VENCIMIENTO_TERCEROS;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNumerico(campo, campoValue, campos)) {
				validado = false;
			}
			// Codigo Estado Factura
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_FACTURA;
			campoValue = campos[campo.posicion()];
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			if (!verificarCampoEnGrupo(EstadoOrigenEnum.getEstadoFacturaMicCodigo(), EstadoOrigenEnum.getEstadoFacturaMicLeyenda(), campo, campoValue, campos)) {
				validado = false;
			}
			// Descripcion Estado Factura
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_FACTURA;
			campoValue = campos[campo.posicion()];
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			if (!verificarCampoEnGrupo(EstadoOrigenEnum.getEstadoFacturaMicDescripcion(), EstadoOrigenEnum.getEstadoFacturaMicLeyenda(), campo, campoValue, campos)) {
				validado = false;
			}
			//Codigo Marca Reclamo
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_MARCA_RECLAMO;
			campoValue = campos[campo.posicion()];
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			List<MarcaReclamoEnum> lstMarca = new ArrayList<MarcaReclamoEnum>();
			lstMarca.add(MarcaReclamoEnum.SIN_MARCA);
			lstMarca.add(MarcaReclamoEnum.ANULADO);
			lstMarca.add(MarcaReclamoEnum.RESUELTO_A);
			lstMarca.add(MarcaReclamoEnum.RESUELTO_P);
			lstMarca.add(MarcaReclamoEnum.RESUELTO_T);
			lstMarca.add(MarcaReclamoEnum.PENDIENTE);
			List<String> listaCodigo = new ArrayList<String>();
			List<String> listaDesc = new ArrayList<String>();
			StringBuffer leyenda = new StringBuffer();
			for (MarcaReclamoEnum m : lstMarca) {
				listaCodigo.add(m.codigo());
				if (MarcaReclamoEnum.SIN_MARCA.equals(m)) {
					listaDesc.add(Utilidad.rellenarEspaciosDerecha("", 10));
				} else {
					listaDesc.add(m.descripcion());
				}
				leyenda.append(m.descripcion() + "(" + m.codigo() + ")");
			}
			if (!verificarCampoEnGrupo(true, listaCodigo, leyenda.toString(), campo, campoValue, campos)) {
				validado = false;
			}
			
			//Descripcion Marca Reclamo
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_MARCA_RECLAMO;
			campoValue = campos[campo.posicion()];
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			if (!verificarCampoEnGrupo(true, listaDesc, leyenda.toString(), campo, campoValue, campos)) {
				validado = false;
			}
			// Codigo Marca C&Q
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_MARCA_CYQ;
			campoValue = campos[campo.posicion()];
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			if (!verificarCampoEnGrupo(true, MarcaCyQEnum.getNames(), MarcaCyQEnum.getLeyenda(), campo, campoValue, campos)) {
				validado = false;
			}
			//DEBITO_IMPUTADO_GRAL_DESCRIPCION_MARCA_CYQ
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_MARCA_CYQ;
			campoValue = campos[campo.posicion()];
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			if (!verificarCampoEnGrupo(true, MarcaCyQEnum.getDesc(), MarcaCyQEnum.getLeyenda(), campo, campoValue, campos)) {
				validado = false;
			}
			//DEBITO_IMPUTADO_GRAL_FECHA_EMISION
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_FECHA_EMISION;
			campoValue = campos[campo.posicion()];
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			if (!verificarCampoFecha(true, campo, campoValue, campos)) {
				validado = false;
			}
			// Número de Convenio
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_NUMERO_CONVENIO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNumerico(campo, campoValue, campos)) {
				validado = false;
			}
			// Cuota
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CUOTA;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNumerico(campo, campoValue, campos)) {
				validado = false;
			}
			// Fecha de ultimo pago parcial
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_FECHA_ULTIMO_PAGO_PARCIAL;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoFecha(false, campo, campoValue, campos)) {
				validado = false;
			}
			//DEBITO_IMPUTADO_GRAL_FECHA_PUESTA_AL_COBRO
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_FECHA_PUESTA_AL_COBRO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoFecha(true, campo, campoValue, campos)) {
				validado = false;
			}
		} else {

			//Cuenta
			MicOperacionMasivaCamposEntradaEnum campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CUENTA;
			String campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}

			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_TIPO_DOCUMENTO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}

			// Codigo Tipo de DUC
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_TIPO_DUC;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}

			// Descripcion Tipo de DUC... 20
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_TIPO_DUC;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}

			// Codigo Estado del DUC
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_DUC;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}

			// Descripción Estado del DUC
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_DUC;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}

			// Codigo Tipo de DUC
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_TIPO_DUC;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}

			// Descripcion Tipo de DUC
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_TIPO_DUC;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Codigo Estado del DUC
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_DUC;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Descripción Estado del DUC
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_DUC;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Acuerdo
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_ACUERDO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			//Clase de Comprobante
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CLASE_COMPROBANTE;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Sucursal del Comprobante
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_SUCURSAL_COMPROBANTE;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Numero del Comprobante
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_NUMERO_COMPROBANTE;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Numero de Referencia MIC
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_NUMERO_REFERENCIA_MIC;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			//Clase de Comprobante
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CLASE_COMPROBANTE;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Sucursal del Comprobante
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_SUCURSAL_COMPROBANTE;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Numero del Comprobante
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_NUMERO_COMPROBANTE;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
//			// Numero de Referencia MIC
//			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_NUMERO_REFERENCIA_MIC;
//			campoValue = campos[campo.posicion()];
//			if (!verificarCampoNulo(campo, campoValue, campos)) {
//				validado = false;
//			}
			// Fecha Vencimiento
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_FECHA_VENCIMIENTO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}

			// Importe al 1er vencimiento
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_PRIMER_VENCIMIENTO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Importe al 2do vencimiento
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_SEGUNDO_VENCIMIENTO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Saldo al 1er vencimiento
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_SALDO_PRIMER_VENCIMIENTO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Codigo Estado acuerdo Facturación
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_ACUERDO_FACTURACION;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}

			// Descripcion Estado acuerdo Factuación
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_ACUERDO_FACTURACION;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			//validarCampoSiNo
			// Estado Conceptos de Terceros
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_ESTADO_CONCEPTOS_TERCEROS;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}

			// Posibilidad Destransferencia
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_POSIBILIDAD_DESTRANSFERENCIA;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}

			// Importe 3eros Transferidos
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_TERCEROS_TRANSFERIDOS;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}

			// Importe 1er vencimiento con 3eros
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_PRIMER_VENCIMIENTO_TERCEROS;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}

			// Importe 2do vencimiento con 3eros
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_SEGUNDO_VENCIMIENTO_TERCEROS;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Codigo Estado Factura
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_FACTURA;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Descripcion Estado Factura
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_FACTURA;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			//Codigo Marca Reclamo
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_MARCA_RECLAMO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}

			//Descripcion Marca Reclamo
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_MARCA_RECLAMO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Codigo Marca C&Q
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_MARCA_CYQ;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			//DEBITO_IMPUTADO_GRAL_DESCRIPCION_MARCA_CYQ
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_MARCA_CYQ;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			//DEBITO_IMPUTADO_GRAL_FECHA_EMISION
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_FECHA_EMISION;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Número de Convenio
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_NUMERO_CONVENIO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Cuota
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CUOTA;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Fecha de ultimo pago parcial
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_FECHA_ULTIMO_PAGO_PARCIAL;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			//DEBITO_IMPUTADO_GRAL_FECHA_PUESTA_AL_COBRO
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_FECHA_PUESTA_AL_COBRO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}

		}
		return validado;
	}
	// Datos del debito imputado: tagetik  (foto de los datos previos a la imputacion)
	@Override
	public boolean validarDebitoImputadoTagetik(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos) {
		boolean validado = true;
		MicOperacionMasivaCamposEntradaEnum campo = null;
		String campoValue = null;
		List<String> lista = new ArrayList<String>();
		String leyenda = "";
		if (!carryRta) {
			aplica = false;
		}
		if (aplica) {
			// Razón social cliente
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_TAGETIK_RAZON_SOCIAL_CLIENTE;
			campoValue = campos[campo.posicion()];
			
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			
			// Tipo de Cliente
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_TAGETIK_TIPO_CLIENTE;
			campoValue = campos[campo.posicion()];
			
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			StringBuffer str = new StringBuffer();
			for (TipoClienteTagetikEnum e : TipoClienteTagetikEnum.values()) {
				lista.add(e.codigo());
				str.append(e.codigo() + " " + e.descripcion() + " | ");
			}
			if (!verificarCampoEnGrupo(lista, str.toString().substring(0, str.toString().length() - 3), campo, campoValue, campos)) {
				validado = false;
			}
			lista.clear();
			// CUIT
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_TAGETIK_CUIT;
			campoValue = campos[campo.posicion()];
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			// Ciclo de Facturación
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_TAGETIK_CICLO_FACTURACION;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNumerico(campo, campoValue, campos)) {
				validado = false;
			}
			// Marketing Customer Group.
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_TAGETIK_MARKETING_CUSTOMER_GROUP;
			campoValue = campos[campo.posicion()];
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			// Tipo de Factura
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_TAGETIK_TIPO_FACTURA;
			campoValue = campos[campo.posicion()];
			
			lista = new ArrayList<String>();
			str = new StringBuffer();
			lista.add(String.valueOf(TipoFacturaEnum.FACT_FLEXCAB.codigo()));
			lista.add(String.valueOf(TipoFacturaEnum.FACT_BAJAS.codigo()));
			lista.add(String.valueOf(TipoFacturaEnum.FACT_POST_BAJ.codigo()));
			lista.add(String.valueOf(TipoFacturaEnum.FACT_INIC_ESP.codigo()));
			lista.add(String.valueOf(TipoFacturaEnum.NOTA_DEB_N_PAG.codigo()));
			lista.add(String.valueOf(TipoFacturaEnum.INTERES_RECARGO.codigo()));
			lista.add(String.valueOf(TipoFacturaEnum.DESCUENTOS.codigo()));
			lista.add(String.valueOf(TipoFacturaEnum.DERECHO_REHABIL.codigo()));
			lista.add(String.valueOf(TipoFacturaEnum.ND_INICIAL_ESP.codigo()));
			lista.add(String.valueOf(TipoFacturaEnum.DESC_F_INIC_ESP.codigo()));
			lista.add(String.valueOf(TipoFacturaEnum.FACTURA_ONLINE.codigo()));
			lista.add(String.valueOf(TipoFacturaEnum.NC_ONLINE.codigo()));
			lista.add("00");
			
			str.append(TipoFacturaEnum.FACT_FLEXCAB.codigo() + " " + TipoFacturaEnum.FACT_FLEXCAB.descripcion());
			str.append("|");
			str.append(TipoFacturaEnum.FACT_BAJAS.codigo() + " " + TipoFacturaEnum.FACT_BAJAS.descripcion());
			str.append("|");
			str.append(TipoFacturaEnum.FACT_POST_BAJ.codigo() + " " + TipoFacturaEnum.FACT_POST_BAJ.descripcion());
			str.append("|");
			str.append(TipoFacturaEnum.FACT_INIC_ESP.codigo() + " " + TipoFacturaEnum.FACT_INIC_ESP.descripcion());
			str.append("|");
			str.append(TipoFacturaEnum.NOTA_DEB_N_PAG.codigo() + " " + TipoFacturaEnum.NOTA_DEB_N_PAG.descripcion());
			str.append("|");
			str.append(TipoFacturaEnum.INTERES_RECARGO.codigo() + " " + TipoFacturaEnum.INTERES_RECARGO.descripcion());
			str.append("|");
			str.append(TipoFacturaEnum.DESCUENTOS.codigo() + " " + TipoFacturaEnum.DESCUENTOS.descripcion());
			str.append("|");
			str.append(TipoFacturaEnum.DERECHO_REHABIL.codigo() + " " + TipoFacturaEnum.DERECHO_REHABIL.descripcion());
			str.append("|");
			str.append(TipoFacturaEnum.ND_INICIAL_ESP.codigo() + " " + TipoFacturaEnum.ND_INICIAL_ESP.descripcion());
			str.append("|");
			str.append(TipoFacturaEnum.DESC_F_INIC_ESP.codigo() + " " + TipoFacturaEnum.DESC_F_INIC_ESP.descripcion());
			str.append("|");
			str.append(TipoFacturaEnum.FACTURA_ONLINE.codigo() + " " + TipoFacturaEnum.FACTURA_ONLINE.descripcion());
			str.append("|");
			str.append(TipoFacturaEnum.NC_ONLINE.codigo() + " " + TipoFacturaEnum.NC_ONLINE.descripcion());
			
			
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			if (!verificarCampoEnGrupo(false, lista, leyenda, campo, campoValue, campos)) {
				validado = false;
			}
			
			// Descripción Tipo de Factura
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_TAGETIK_DESCRIPCION_TIPO_FACTURA;
			campoValue = campos[campo.posicion()];
			lista = new ArrayList<String>();
			
			
			
			lista.add(TipoFacturaEnum.FACT_FLEXCAB.descripcion());
			lista.add(TipoFacturaEnum.FACT_BAJAS.descripcion());
			lista.add(TipoFacturaEnum.FACT_POST_BAJ.descripcion());
			lista.add(TipoFacturaEnum.NOTA_DEB_N_PAG.descripcion());
			lista.add(TipoFacturaEnum.INTERES_RECARGO.descripcion());
			lista.add(TipoFacturaEnum.DESCUENTOS.descripcion());
			lista.add(TipoFacturaEnum.DERECHO_REHABIL.descripcion());
			lista.add(TipoFacturaEnum.ND_INICIAL_ESP.descripcion());
			lista.add(TipoFacturaEnum.DESC_F_INIC_ESP.descripcion());
			lista.add(TipoFacturaEnum.FACTURA_ONLINE.descripcion());
			lista.add(TipoFacturaEnum.NC_ONLINE.descripcion());
			lista.add("");
			
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			
			if (!verificarCampoEnGrupo(false, lista, leyenda, campo, campoValue, campos)) {
				validado = false;
			}
		} else {
			// Razón social cliente
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_TAGETIK_RAZON_SOCIAL_CLIENTE;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Tipo de Cliente
			
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_TAGETIK_TIPO_CLIENTE;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// CUIT
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_TAGETIK_CUIT;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Ciclo de Facturación
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_TAGETIK_CICLO_FACTURACION;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Marketing Customer Group.
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_TAGETIK_MARKETING_CUSTOMER_GROUP;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Tipo de Factura
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_TAGETIK_TIPO_FACTURA;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Descripción Tipo de Factura
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_TAGETIK_DESCRIPCION_TIPO_FACTURA;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			
		}
		return validado;
	}
	
	// Datos del debito imputado: Dacota  (foto de los datos previos a la imputacion)
	@Override
	public boolean validarDebitoDakota(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos) {
		
		boolean validado = true;
		MicOperacionMasivaCamposEntradaEnum campo = null;
		String campoValue = null;
		if (!carryRta) {
			aplica = false;
		}
		if (aplica) {
			// Tipo Cuenta
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_DAKOTA_FECHA_VENCIMIENTO_MORA;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoFecha(false, campo, campoValue, campos)) {
				validado = false;
			}
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_DAKOTA_INDICADOR_PETICION_CORTE;
			campoValue = campos[campo.posicion()];
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_DAKOTA_CODIGO_TARIFA;
			campoValue = campos[campo.posicion()];
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
		} else {
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_DAKOTA_FECHA_VENCIMIENTO_MORA;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_DAKOTA_INDICADOR_PETICION_CORTE;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			campo = MicOperacionMasivaCamposEntradaEnum.DEBITO_DAKOTA_CODIGO_TARIFA;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
		}
		return validado;
	}
	
	// Datos del debito imputado: Saldos de terceros  (foto de los datos previos a la imputacion)
	@Override
	public boolean validarSaldoTerceros(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos) {
		
		boolean validado = true;
		MicOperacionMasivaCamposEntradaEnum campo = null;
		String campoValue = null;
		if (!carryRta) {
			aplica = false;
		}
		if (aplica) {
			// Tipo Cuenta
			campo = MicOperacionMasivaCamposEntradaEnum.SALDO_TERCERO_FINANCIABLE_NO_TRANSFERIBLE;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNumerico(campo, campoValue, campos)) {
				validado = false;
			}
			campo = MicOperacionMasivaCamposEntradaEnum.SALDO_TERCERO_FINACIABLE_TRANSFERIBLE;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNumerico(campo, campoValue, campos)) {
				validado = false;
			}
			campo = MicOperacionMasivaCamposEntradaEnum.SALDO_TERCERO_NO_FINANCIABLE_TRANSAFERIBLE;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNumerico(campo, campoValue, campos)) {
				validado = false;
			}
		} else {
			campo = MicOperacionMasivaCamposEntradaEnum.SALDO_TERCERO_FINANCIABLE_NO_TRANSFERIBLE;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			campo = MicOperacionMasivaCamposEntradaEnum.SALDO_TERCERO_FINACIABLE_TRANSFERIBLE;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			campo = MicOperacionMasivaCamposEntradaEnum.SALDO_TERCERO_NO_FINANCIABLE_TRANSAFERIBLE;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
		}
		return validado;
	}
	
	// Datos del credito aplicado: cliente
	@Override
	public boolean validarCrediotAplicadoCliente(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos) {
		boolean validado = true;
		if (!carryRta) {
			aplica = false;
		}
		if (aplica) {
			MicOperacionMasivaCamposEntradaEnum campo = MicOperacionMasivaCamposEntradaEnum.CODIGO_CLIENTE_CREDITO_APLICADO;
			String campoValue = campos[campo.posicion()];
			validado = verificarCampoNumerico(campo, campoValue, campos);
		} else {
			MicOperacionMasivaCamposEntradaEnum campo = MicOperacionMasivaCamposEntradaEnum.CODIGO_CLIENTE_CREDITO_APLICADO;
			String campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
		}
		return validado;
	}
	// Datos del credito aplicado: medio de pago (foto de los datos previos a la imputacion)
	@Override
	public boolean validarMedioPago(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos) {
		
		boolean validado = true;
		MicOperacionMasivaCamposEntradaEnum campo = null;
		String campoValue = null;
		if (!carryRta) {
			aplica = false;
		}
		if (aplica) {
			// Tipo Cuenta
			campo = MicOperacionMasivaCamposEntradaEnum.MEDIO_DE_PAGO_CUENTA;
			campoValue = campos[campo.posicion()];
	
			if (!verificarCampoNumerico(campo, campoValue, campos)) {
				validado = false;
			}
			campo = MicOperacionMasivaCamposEntradaEnum.MEDIO_DE_PAGO_TIPO_CREDITO;
			campoValue = campos[campo.posicion()];
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			List<String>lista = new ArrayList<String>();
			lista.add("CRE");
			lista.add("REM");
			if (!lista.contains(campoValue)) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString(
							"error.operacionMasiva.validarCampos.mic.errorImagen"),
							campoValue.trim(),
							"CRE | REM"
					);
					ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
					validado  = false;
			} else {
				if ("CRE".equals(campoValue)) {
					validarGrupos.put("validarNotaCredito", true);
					validarGrupos.put("validarCreditoRemanete", false);
					
				} else {
					// Si el medio de pago es remanete no aplica 
					validarGrupos.put("validarNotaCredito", false);
					validarGrupos.put("validarCreditoRemanete", true);
					
				}
			}
		} else {
			campo = MicOperacionMasivaCamposEntradaEnum.MEDIO_DE_PAGO_CUENTA;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			campo = MicOperacionMasivaCamposEntradaEnum.MEDIO_DE_PAGO_TIPO_CREDITO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
		}
		return validado;
	}
	
	// Datos del credito aplicado: Nota de credito (foto de los datos previos a la imputacion)
	@Override
	public boolean validarNotaCredito(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos) {
		boolean validado = true;
		MicOperacionMasivaCamposEntradaEnum campo = null;
		String campoValue = null;
		if (!carryRta) {
			aplica = false;
		}
		if (aplica) {
			// Tipo de Comprobante
			campo = MicOperacionMasivaCamposEntradaEnum.NC_TIPO_COMPROBANTE;
			campoValue = campos[campo.posicion()];
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			if (!"CRE".equals(campoValue)) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString(
							"error.operacionMasiva.validarCampos.mic.errorImagen"),
							campoValue.trim(),
							"CRE"
					);
					ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
					validado  = false;
			}
			// Clase de Comprobante
			campo = MicOperacionMasivaCamposEntradaEnum.NC_CLASE_COMPROBANTE;
			campoValue = campos[campo.posicion()];
			
			List<String>lista = new ArrayList<String>();
			lista.add("A");
			lista.add("B");
			lista.add("S");
			lista.add(" ");
			lista.add("");
			String leyenda = "A | B | S (Vacio)";
			// Sucursal del Comprobante
			if (!verificarCampoEnGrupo(false, lista, leyenda, campo, campoValue, campos)) {
				validado = false;
			}
			
			campo = MicOperacionMasivaCamposEntradaEnum.NC_SUCURSAL_COMPROBANTE;
			campoValue = campos[campo.posicion()];
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			// Numero del Comprobante
			campo = MicOperacionMasivaCamposEntradaEnum.NC_NUMERO_COMPROBANTE;
			campoValue = campos[campo.posicion()];
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			// Nro de Referencia MIC
			campo = MicOperacionMasivaCamposEntradaEnum.NC_NUMERO_REFERENCIA_MIC;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNumerico(campo, campoValue, campos)) {
				validado = false;
			}
		} else {
			// Tipo de Comprobante
			campo = MicOperacionMasivaCamposEntradaEnum.NC_TIPO_COMPROBANTE;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Clase de Comprobante
			campo = MicOperacionMasivaCamposEntradaEnum.NC_CLASE_COMPROBANTE;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Sucursal del Comprobante
			campo = MicOperacionMasivaCamposEntradaEnum.NC_SUCURSAL_COMPROBANTE;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Numero del Comprobante
			campo = MicOperacionMasivaCamposEntradaEnum.NC_NUMERO_COMPROBANTE;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Nro de Referencia MIC
			campo = MicOperacionMasivaCamposEntradaEnum.NC_NUMERO_REFERENCIA_MIC;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
		}
		return validado;
	}
	
	// Datos del credito aplicado: Remanente (foto de los datos previos a la imputacion)
	@Override
	public boolean validarCreditoRemanete(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos) {
		boolean validado = true;
		MicOperacionMasivaCamposEntradaEnum campo = null;
		String campoValue = null;
		if (!carryRta) {
			aplica = false;
		}
		if (aplica) {
			// Codigo de Tipo de Remanente
			campo = MicOperacionMasivaCamposEntradaEnum.REMANENTE_CODIGO_TIPO;
			campoValue = campos[campo.posicion()];
			List<String> lista = new ArrayList<String>();
			lista.add("1");
			lista.add("2");
			String leyenda = "1 o 2";
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			if (!verificarCampoEnGrupo(false, lista, leyenda.toString(), campo, campoValue, campos)) {
				validado = false;
			}
			// Descripcion del Tipo de Remanente
			campo = MicOperacionMasivaCamposEntradaEnum.REMANENTE_DESCRIPCION_TIPO;
			campoValue = campos[campo.posicion()];
		
			lista.clear();
			lista.add("TRANSFERIBLE NO ACTUALIZABLE");
			lista.add("NO TRANSFERIBLE NO ACTUALIZABLE");
			
			leyenda = "TRANSFERIBLE NO ACTUALIZABLE o NO TRANSFERIBLE NO ACTUALIZABLE";
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			if (!verificarCampoEnGrupo(false, lista, leyenda.toString(), campo, campoValue, campos)) {
				validado = false;
			}
		} else {
			// Codigo de Tipo de Remanente
			campo = MicOperacionMasivaCamposEntradaEnum.REMANENTE_CODIGO_TIPO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Descripcion del Tipo de Remanente
			campo = MicOperacionMasivaCamposEntradaEnum.REMANENTE_DESCRIPCION_TIPO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
		}
		return validado;
	}
	
	
	// Datos del credito aplicado: datos generales (foto de los datos previos a la imputacion)
	@Override
	public boolean validarCreditoDatosGenerales(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos) {
		boolean validado = true;
		MicOperacionMasivaCamposEntradaEnum campo = null;
		String campoValue = null;
		if (!carryRta) {
			aplica = false;
		}
		if (aplica) {
			// importe
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_IMPORTE;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNumerico(campo, campoValue, campos)) {
				validado = false;
			}
			// Saldo
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_SALDO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNumerico(campo, campoValue, campos)) {
				validado = false;
			}
			String tipo = campos[MicOperacionMasivaCamposEntradaEnum.MEDIO_DE_PAGO_TIPO_CREDITO.posicion()];
			
			
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_FECHA_ALTA;
			campoValue = campos[campo.posicion()];
			
			// Fecha alta
			if ("REM".equals(tipo)) {
				if (!verificarCampoFecha(false, campo, campoValue, campos)) {
					validado = false;
				}
			} else {
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
			}
			if (!"REM".equals(tipo)) { 
				// Fecha de emisión
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_FECHA_EMISION;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoFecha(true, campo, campoValue, campos)) {
					validado = false;
				}
				// Fecha vencimiento
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_FECHA_VENCIMIENTO;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoFecha(true, campo, campoValue, campos)) {
					validado = false;
				}
				// Fecha último movimiento cobro (PP)
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_FECHA_ULTIMO_MOVIMIENTO_COBRO;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoFecha(false, campo, campoValue, campos)) {
					validado = false;
				}
				// Codigo Marca Reclamo
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_CODIGO_MARCA_RECLAMO;
				campoValue = campos[campo.posicion()];
				
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				
				List<MarcaReclamoEnum> lstMarca = new ArrayList<MarcaReclamoEnum>();
				lstMarca.add(MarcaReclamoEnum.SIN_MARCA);
				lstMarca.add(MarcaReclamoEnum.ANULADO);
				lstMarca.add(MarcaReclamoEnum.RESUELTO_A);
				lstMarca.add(MarcaReclamoEnum.RESUELTO_P);
				lstMarca.add(MarcaReclamoEnum.RESUELTO_T);
				lstMarca.add(MarcaReclamoEnum.PENDIENTE);
				List<String> listaCodigo = new ArrayList<String>();
				List<String> listaDesc = new ArrayList<String>();
				
				StringBuffer leyenda = new StringBuffer();
				for (MarcaReclamoEnum m : lstMarca) {
					listaCodigo.add(m.codigo());
					if (MarcaReclamoEnum.SIN_MARCA.equals(m)) {
						listaDesc.add(Utilidad.rellenarEspaciosDerecha("", 10));
					} else {
						listaDesc.add(m.descripcion());
					}
					leyenda.append(m.descripcion() + "(" + m.codigo() + ")");
				}
				if (!verificarCampoEnGrupo(true, listaCodigo, leyenda.toString(), campo, campoValue, campos)) {
					validado = false;
				}
				
				// Descripcion Marca Reclamo
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_DESCRIPCION_MARCA_RECLAMO;
				campoValue = campos[campo.posicion()];
				
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if (!verificarCampoEnGrupo(true, listaDesc, leyenda.toString(), campo, campoValue, campos)) {
					validado = false;
				}
			
				// Codigo Marca C&Q
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_CODIGO_MARCA_CYQ ;
				campoValue = campos[campo.posicion()];
				
				
				
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if (!verificarCampoEnGrupo(true, MarcaCyQEnum.getNames(), MarcaCyQEnum.getLeyenda(), campo, campoValue, campos)) {
					validado = false;
				}
				// Descripcion Marca C&Q
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_DESCRIPCION_MARCA_CYQ;
				campoValue = campos[campo.posicion()];
				
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if (!verificarCampoEnGrupo(true, MarcaCyQEnum.getDesc(), MarcaCyQEnum.getLeyenda(), campo, campoValue, campos)) {
					validado = false;
				}
				//Codigo Estado Crédito
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_CODIGO_ESTADO_CREDITO;
				campoValue = campos[campo.posicion()];
				
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if (!verificarCampoEnGrupo(EstadoOrigenEnum.getEstadoFacturaMicCodigo(), EstadoOrigenEnum.getEstadoFacturaMicLeyenda(), campo, campoValue, campos)) {
					validado = false;
				}
				// Descripcion Estado Crédito
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_DESCRIPCION_ESTADO_CREDITO;
				campoValue = campos[campo.posicion()];
				if (!verificarLongitud(campo, campoValue, campos)) {
					validado = false;
				}
				if (!verificarCampoEnGrupo(EstadoOrigenEnum.getEstadoFacturaMicDescripcion(), EstadoOrigenEnum.getEstadoFacturaMicLeyenda(), campo, campoValue, campos)) {
					validado = false;
				}
			} else {
				// Fecha de emisión
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_FECHA_EMISION;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				
				// Fecha vencimiento
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_FECHA_VENCIMIENTO;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Fecha último movimiento cobro (PP)
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_FECHA_ULTIMO_MOVIMIENTO_COBRO;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Codigo Marca Reclamo
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_CODIGO_MARCA_RECLAMO;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Descripcion Marca Reclamo
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_DESCRIPCION_MARCA_RECLAMO;
				campoValue = campos[campo.posicion()];
				
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Codigo Marca C&Q
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_CODIGO_MARCA_CYQ ;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Descripcion Marca C&Q
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_DESCRIPCION_MARCA_CYQ;
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				
			
				//Codigo Estado Crédito
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_CODIGO_ESTADO_CREDITO;
				campoValue = campos[campo.posicion()];
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
				// Descripcion Estado Crédito
				campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_DESCRIPCION_ESTADO_CREDITO;
				campoValue = campos[campo.posicion()];
				campoValue = campos[campo.posicion()];
				if (!verificarCampoNulo(campo, campoValue, campos)) {
					validado = false;
				}
			}
		} else {
			// importe
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_IMPORTE;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Saldo
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_SALDO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Fecha alta
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_FECHA_ALTA;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Fecha de emisión
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_FECHA_EMISION;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Fecha vencimiento
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_FECHA_VENCIMIENTO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Fecha último movimiento cobro (PP)
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_FECHA_ULTIMO_MOVIMIENTO_COBRO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Codigo Marca Reclamo
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_CODIGO_MARCA_RECLAMO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Descripcion Marca Reclamo
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_DESCRIPCION_MARCA_RECLAMO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Codigo Marca C&Q
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_CODIGO_MARCA_CYQ ;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Descripcion Marca C&Q
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_DESCRIPCION_MARCA_CYQ;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			//Codigo Estado Crédito
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_CODIGO_ESTADO_CREDITO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Descripcion Estado Crédito
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_DESCRIPCION_ESTADO_CREDITO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
		}
		
		return validado;
	}
	// Datos del credito aplicado: Tagetik  (foto de los datos previos a la imputacion)
	@Override
	public boolean validarCreditoAplicadoTagetik(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos) {
		boolean validado = true;
		MicOperacionMasivaCamposEntradaEnum campo = null;
		String campoValue = null;
		List<String> lista = new ArrayList<String>();
		String leyenda = "";
		if (!carryRta) {
			aplica = false;
		}
		if (aplica) {
			// Razón social cliente
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_RAZON_SOCIAL_CLIENTE;
			campoValue = campos[campo.posicion()];
			
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			// Tipo de Cliente
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_TIPO_CLIENTE;
			campoValue = campos[campo.posicion()];
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			StringBuffer str = new StringBuffer();
			for (TipoClienteTagetikEnum e : TipoClienteTagetikEnum.values()) {
				lista.add(e.codigo());
				str.append(e.codigo() + " " + e.descripcion() + " | ");
			}
			if (!verificarCampoEnGrupo(lista, str.toString().substring(0, str.toString().length() - 3), campo, campoValue, campos)) {
				validado = false;
			}
			lista.clear();
			// CUIT
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_CUIT;
			campoValue = campos[campo.posicion()];
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			// Ciclo de Facturación
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_CICLO_FACTURACION;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNumerico(campo, campoValue, campos)) {
				validado = false;
			}
			// Marketing Customer Group.
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_MARKETING_CUSTOMER_GROUP;
			campoValue = campos[campo.posicion()];
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			// Tipo de Factura
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_TIPO_FACTURA;
			campoValue = campos[campo.posicion()];
			
			lista = new ArrayList<String>();
			str = new StringBuffer();
			
			
			lista.add(String.valueOf(TipoFacturaEnum.FACT_FLEXCAB.codigo()));
			lista.add(String.valueOf(TipoFacturaEnum.FACT_BAJAS.codigo()));
			lista.add(String.valueOf(TipoFacturaEnum.FACT_POST_BAJ.codigo()));
			lista.add(String.valueOf(TipoFacturaEnum.FACT_INIC_ESP.codigo()));
			lista.add(String.valueOf(TipoFacturaEnum.NOTA_DEB_N_PAG.codigo()));
			lista.add(String.valueOf(TipoFacturaEnum.INTERES_RECARGO.codigo()));
			lista.add(String.valueOf(TipoFacturaEnum.DESCUENTOS.codigo()));
			lista.add(String.valueOf(TipoFacturaEnum.DERECHO_REHABIL.codigo()));
			lista.add(String.valueOf(TipoFacturaEnum.ND_INICIAL_ESP.codigo()));
			lista.add(String.valueOf(TipoFacturaEnum.DESC_F_INIC_ESP.codigo()));
			lista.add(String.valueOf(TipoFacturaEnum.FACTURA_ONLINE.codigo()));
			lista.add(String.valueOf(TipoFacturaEnum.NC_ONLINE.codigo()));
			lista.add("00");
			
			str.append(TipoFacturaEnum.FACT_FLEXCAB.codigo() + " " + TipoFacturaEnum.FACT_FLEXCAB.descripcion());
			str.append("|");
			str.append(TipoFacturaEnum.FACT_BAJAS.codigo() + " " + TipoFacturaEnum.FACT_BAJAS.descripcion());
			str.append("|");
			str.append(TipoFacturaEnum.FACT_POST_BAJ.codigo() + " " + TipoFacturaEnum.FACT_POST_BAJ.descripcion());
			str.append("|");
			str.append(TipoFacturaEnum.FACT_INIC_ESP.codigo() + " " + TipoFacturaEnum.FACT_INIC_ESP.descripcion());
			str.append("|");
			str.append(TipoFacturaEnum.NOTA_DEB_N_PAG.codigo() + " " + TipoFacturaEnum.NOTA_DEB_N_PAG.descripcion());
			str.append("|");
			str.append(TipoFacturaEnum.INTERES_RECARGO.codigo() + " " + TipoFacturaEnum.INTERES_RECARGO.descripcion());
			str.append("|");
			str.append(TipoFacturaEnum.DESCUENTOS.codigo() + " " + TipoFacturaEnum.DESCUENTOS.descripcion());
			str.append("|");
			str.append(TipoFacturaEnum.DERECHO_REHABIL.codigo() + " " + TipoFacturaEnum.DERECHO_REHABIL.descripcion());
			str.append("|");
			str.append(TipoFacturaEnum.ND_INICIAL_ESP.codigo() + " " + TipoFacturaEnum.ND_INICIAL_ESP.descripcion());
			str.append("|");
			str.append(TipoFacturaEnum.DESC_F_INIC_ESP.codigo() + " " + TipoFacturaEnum.DESC_F_INIC_ESP.descripcion());
			str.append("|");
			str.append(TipoFacturaEnum.FACTURA_ONLINE.codigo() + " " + TipoFacturaEnum.FACTURA_ONLINE.descripcion());
			str.append("|");
			str.append(TipoFacturaEnum.NC_ONLINE.codigo() + " " + TipoFacturaEnum.NC_ONLINE.descripcion());
			
			
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			if (!verificarCampoEnGrupo(false, lista, leyenda, campo, campoValue, campos)) {
				validado = false;
			}
			
			// Descripción Tipo de Factura
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_DESCRIPCION_TIPO_FACTURA;
			campoValue = campos[campo.posicion()];
			lista = new ArrayList<String>();
			
			lista.add(TipoFacturaEnum.FACT_FLEXCAB.descripcion());
			lista.add(TipoFacturaEnum.FACT_BAJAS.descripcion().trim()); // Tengo que realizar u trim dado que en la descripcion tiene 3 espacios al final 
			lista.add(TipoFacturaEnum.FACT_POST_BAJ.descripcion());
			lista.add(TipoFacturaEnum.FACT_INIC_ESP.descripcion());
			lista.add(TipoFacturaEnum.NOTA_DEB_N_PAG.descripcion());
			lista.add(TipoFacturaEnum.INTERES_RECARGO.descripcion());
			lista.add(TipoFacturaEnum.DESCUENTOS.descripcion());
			lista.add(TipoFacturaEnum.DERECHO_REHABIL.descripcion());
			lista.add(TipoFacturaEnum.ND_INICIAL_ESP.descripcion());
			lista.add(TipoFacturaEnum.DESC_F_INIC_ESP.descripcion());
			lista.add(TipoFacturaEnum.FACTURA_ONLINE.descripcion());
			lista.add(TipoFacturaEnum.NC_ONLINE.descripcion());
			lista.add(TipoFacturaEnum.NC_ONLINE.descripcion());
			lista.add("");

			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			
			if (!verificarCampoEnGrupo(false, lista, leyenda, campo, campoValue, campos)) {
				validado = false;
			}
			
			// Importe Original
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_IMPORTE_ORIGINAL;
			campoValue = campos[campo.posicion()];
			
			if (!verificarCampoNumerico(campo, campoValue, campos)) {
				validado = false;
			}
		} else {
			// Razón social cliente
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_RAZON_SOCIAL_CLIENTE;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Tipo de Cliente
			
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_TIPO_CLIENTE;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// CUIT
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_CUIT;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Ciclo de Facturación
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_CICLO_FACTURACION;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Marketing Customer Group.
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_MARKETING_CUSTOMER_GROUP;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Tipo de Factura
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_TIPO_FACTURA;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Descripción Tipo de Factura
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_DESCRIPCION_TIPO_FACTURA;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			
			// Importe Original
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_IMPORTE_ORIGINAL;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
		}
		return validado;
	}
	// Datos del acuerdo de intereses por mora / acuerdo del reintegro: datos generales (foto de los datos previos a la imputacion)
	@Override
	public boolean validarAcuerdoInteresPorMoraDatosGenerales(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos) {
		boolean validado = true;
		MicOperacionMasivaCamposEntradaEnum campo = null;
		String campoValue = null;
		List<String> lista = new ArrayList<String>();
		String leyenda = "";
		if (!carryRta) {
			aplica = false;
		}
		if (aplica) {
			// Código de cliente
			campo = MicOperacionMasivaCamposEntradaEnum.ACUERDO_GNERALES_CODIGO_CLIENTE_MORA;
			campoValue = campos[campo.posicion()];
			validado = verificarCampoNumerico(campo, campoValue, campos);
			// Acuerdo de Facturación de intereses / reintegro
			campo = MicOperacionMasivaCamposEntradaEnum.ACUERDO_GNERALES_ACUERDO_FACTURACION_INTERESES_REINTEGRO;
			campoValue = campos[campo.posicion()];
			if (verificarCampoNumerico(campo, campoValue, campos)) {
				validado = false;
			}
			// Numero de Linea
			campo = MicOperacionMasivaCamposEntradaEnum.ACUERDO_GNERALES_NUMERO_LINEA;
			campoValue = campos[campo.posicion()];
			if (verificarCampoNumerico(campo, campoValue, campos)) {
				validado = false;
			}
			// Codigo Estado acuerdo
			campo = MicOperacionMasivaCamposEntradaEnum.ACUERDO_GNERALES_CODIGO_ESTADO_ACUERDO;
			campoValue = campos[campo.posicion()];
			if (verificarCampoNumerico(campo, campoValue, campos)) {
				validado = false;
			}
			lista.add("00");
			lista.add("01");
			lista.add("02");
			lista.add("06");
			leyenda = "00 | 01 | 02 | 06";
			
			if (!verificarCampoEnGrupo(true, lista, leyenda, campo, campoValue, campos)) {
				validado = false;
			}
			// escripcion Estado acuerdo
			campo = MicOperacionMasivaCamposEntradaEnum.ACUERDO_GNERALES_DESCRIPCION_ESTADO_ACUERDO;
			campoValue = campos[campo.posicion()];

			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			lista.clear();
			lista.add("POTENCIAL");
			lista.add("ACTIVO");
			lista.add("INCOMUNICA");
			lista.add("BAJA DEFIN");
			leyenda = " POTENCIAL | ACTIVO | INCOMUNICA (INCOMUNICADO) | BAJA DEFIN (BAJA DEFINITIVA)";
			if (!verificarCampoEnGrupo(false, lista, leyenda, campo, campoValue.trim(), campos)) {
				validado = false;
			}
		} else {
			// Código de cliente
			campo = MicOperacionMasivaCamposEntradaEnum.ACUERDO_GNERALES_CODIGO_CLIENTE_MORA;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Acuerdo de Facturación de intereses / reintegro
			campo = MicOperacionMasivaCamposEntradaEnum.ACUERDO_GNERALES_ACUERDO_FACTURACION_INTERESES_REINTEGRO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Numero de Linea
			campo = MicOperacionMasivaCamposEntradaEnum.ACUERDO_GNERALES_NUMERO_LINEA;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Codigo Estado acuerdo
			campo = MicOperacionMasivaCamposEntradaEnum.ACUERDO_GNERALES_CODIGO_ESTADO_ACUERDO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// escripcion Estado acuerdo
			campo = MicOperacionMasivaCamposEntradaEnum.ACUERDO_GNERALES_DESCRIPCION_ESTADO_ACUERDO;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
		}
		return validado;
	}
	// Datos del acuerdo de intereses por mora / acuerdo del reintegro: datos del cliente
	@Override
	public boolean validarAcuerdoInteresPorMoraCliente(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos) {
		boolean validado = true;
		MicOperacionMasivaCamposEntradaEnum campo = null;
		String campoValue = null;
		if (!carryRta) {
			aplica = false;
		}
		if (aplica) {
			campo = MicOperacionMasivaCamposEntradaEnum.ACUERDO_CLIENTE_CODIGO_CLIENTE;
			campoValue = campos[campo.posicion()];
			validado = verificarCampoNumerico(campo, campoValue, campos);
		} else {
			campo = MicOperacionMasivaCamposEntradaEnum.ACUERDO_CLIENTE_CODIGO_CLIENTE;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
		}
		return validado;
	}
	// Datos del credito aplicado: Dacota (foto de los datos previos a la imputacion)
	@Override
	public boolean validarCreditoAplicadoDakota(boolean aplica, String[] campos, boolean carryRta, Map<String, Boolean> validarGrupos) {
		boolean validado = true;
		MicOperacionMasivaCamposEntradaEnum campo = null;
		String campoValue = null;
		if (!carryRta) {
			aplica = false;
		}
		if (aplica) {
			// Fecha vencimiento mora
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_DAKOTA_FECHA_VENCIMIENTO_MORA;
			campoValue = campos[campo.posicion()];

			if (!verificarCampoFecha(false, campo, campoValue, campos)) {
				validado = false;
			}
			// Indicador de Petición de Corte
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_DAKOTA_INDICADOR_PETICION_CORTE;
			campoValue = campos[campo.posicion()];

			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			// Codigo Tarifa
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_DAKOTA_CODIGO_TARIFA;
			campoValue = campos[campo.posicion()];

			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			
		} else {
			// Fecha vencimiento mora
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_DAKOTA_FECHA_VENCIMIENTO_MORA;
			campoValue = campos[campo.posicion()];

			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Indicador de Petición de Corte
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_DAKOTA_INDICADOR_PETICION_CORTE;
			campoValue = campos[campo.posicion()];

			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			// Codigo Tarifa
			campo = MicOperacionMasivaCamposEntradaEnum.CREDITO_DAKOTA_CODIGO_TARIFA;
			campoValue = campos[campo.posicion()];

			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
		}
		return validado;
	}
	
	// Datos de respuesta generales: resultado de la invocación a nivel debito
	@Override
	public boolean validarRespuestasResultadoDebito(boolean aplica, String[] campos, Map<String, TipoResultadoEnum> rtaMic) {
		boolean validado = true;
		TipoResultadoEnum resultado = null;
		MicOperacionMasivaCamposEntradaEnum campo = null;
		String campoValue = "";
		String codigoError = "";
		
		if (aplica) {
			//Resultado de la consulta
			campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_DEBITO_RESULTADO_CONSULTA;
			campoValue = campos[campo.posicion()];

			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			List<String> lista = new ArrayList<String>();
			lista.add(TipoResultadoEnum.NOK.name());
			lista.add(TipoResultadoEnum.OK.name());
			lista.add(TipoResultadoEnum.WRN.name());
			lista.add("");

			String leyenda = TipoResultadoEnum.OK.name() + " / " + TipoResultadoEnum.WRN.name() + " / " + TipoResultadoEnum.NOK.name();
			if (!verificarCampoEnGrupo(lista, leyenda, campo, campoValue, campos)) {
				validado = false;
			} else {
				resultado = TipoResultadoEnum.getEnumByName(campoValue.trim());
				rtaMic.put("validarRespuestasResultadoDebito", resultado);
			}

			// Código de error 
			campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_DEBITO_CODIGO_ERROR;
			campoValue = campos[campo.posicion()];
	
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			if (TipoResultadoEnum.WRN.equals(resultado) || TipoResultadoEnum.NOK.equals(resultado)) {
				if (Validaciones.isEmptyAlfanumericoSerializado(campoValue)) {
					String mensajeError = Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.nulo");
					ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
				} else {
					codigoError = campoValue;
				}
			}
			
			campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_DEBITO_DESCRIPCION_ERROR;
			campoValue = campos[campo.posicion()];
	
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			if (TipoResultadoEnum.WRN.equals(resultado) || TipoResultadoEnum.NOK.equals(resultado)) {
				if (TipoResultadoEnum.NOK.equals(resultado) && codigoError.equals(ConstantesCobro.CODIGO_ERROR_9999)) {
					if (Validaciones.isEmptyAlfanumericoSerializado(campoValue)) {
						String mensajeError = Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.nulo");
						ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
						validado  = false;
					}
				}
			}
		} else {
			campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_DEBITO_RESULTADO_CONSULTA;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_DEBITO_CODIGO_ERROR;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_DEBITO_DESCRIPCION_ERROR;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
		}
		return validado;
	}
	
	@Override
	public boolean validarRespuestasResultadoCredito(boolean aplica, String[] campos, Map<String, TipoResultadoEnum> rtaMic) {
		boolean validado = true;
		TipoResultadoEnum resultado = null;
		MicOperacionMasivaCamposEntradaEnum campo = null;
		String campoValue = "";
		String codigoError = "";
		if (aplica) {
			//Resultado de la consulta
			campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_CREDITO_RESULTADO_CONSULTA;
			campoValue = campos[campo.posicion()];

			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			List<String> lista = new ArrayList<String>();
			lista.add(TipoResultadoEnum.NOK.name());
			lista.add(TipoResultadoEnum.OK.name());
			lista.add(TipoResultadoEnum.WRN.name());
			lista.add("");

			String leyenda = TipoResultadoEnum.OK.name() + " / " + TipoResultadoEnum.WRN.name() + " / " + TipoResultadoEnum.NOK.name();
			if (!verificarCampoEnGrupo(lista, leyenda, campo, campoValue, campos)) {
				validado = false;
			} else {
				resultado = TipoResultadoEnum.getEnumByName(campoValue.trim());
				rtaMic.put("validarRespuestasResultadoCredito", resultado);
			}

			// Código de error 
			campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_CREDITO_CODIGO_ERROR;
			campoValue = campos[campo.posicion()];
	
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			if (TipoResultadoEnum.WRN.equals(resultado) || TipoResultadoEnum.NOK.equals(resultado)) {
				if (Validaciones.isEmptyAlfanumericoSerializado(campoValue)) {
					String mensajeError = Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.nulo");
					ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
				} else {
					codigoError = campoValue;
				}
			}
			
			campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_CREDITO_DESCRIPCION_ERROR;
			campoValue = campos[campo.posicion()];
	
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			if (TipoResultadoEnum.WRN.equals(resultado) || TipoResultadoEnum.NOK.equals(resultado)) {
				if (TipoResultadoEnum.NOK.equals(resultado) && codigoError.equals(ConstantesCobro.CODIGO_ERROR_9999)) {
					if (Validaciones.isEmptyAlfanumericoSerializado(campoValue)) {
						String mensajeError = Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.nulo");
						ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
					validado  = false;
					}
				}
			}
		} else {
			campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_CREDITO_RESULTADO_CONSULTA;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_CREDITO_CODIGO_ERROR;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_CREDITO_DESCRIPCION_ERROR;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
		}
		return  validado;
	}
	
	@Override
	public boolean validarRespuestasResultadoReintegro(boolean aplica, String[] campos, Map<String, TipoResultadoEnum> rtaMic) {
		boolean validado = true;
		TipoResultadoEnum resultado = null;
		MicOperacionMasivaCamposEntradaEnum campo = null;
		String campoValue = "";
		
		if (aplica) {
			//Resultado de la consulta
			campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_REINTEGRO_RESULTADO_CONSULTA;
			campoValue = campos[campo.posicion()];

			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			List<String> lista = new ArrayList<String>();
			lista.add(TipoResultadoEnum.NOK.name());
			lista.add(TipoResultadoEnum.OK.name());
			lista.add(TipoResultadoEnum.WRN.name());
			lista.add("");

			String leyenda = TipoResultadoEnum.OK.name() + " / " + TipoResultadoEnum.WRN.name() + " / " + TipoResultadoEnum.NOK.name() + " / '(vacia)";
			if (!verificarCampoEnGrupo(lista, leyenda, campo, campoValue, campos)) {
				validado = false;
			} else {
				resultado = TipoResultadoEnum.getEnumByName(campoValue.trim());
				rtaMic.put("validarRespuestasResultadoReintegro", resultado);
			}
		
			// Código de error 
			campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_CREDITO_CODIGO_ERROR;
			campoValue = campos[campo.posicion()];
	
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			if (TipoResultadoEnum.WRN.equals(resultado) || TipoResultadoEnum.NOK.equals(resultado)) {
				if (Validaciones.isEmptyAlfanumericoSerializado(campoValue)) {
					String mensajeError = Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.nulo");
					ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
				}
			}
			
			campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_REINTEGRO_DESCRIPCION_ERROR;
			campoValue = campos[campo.posicion()];
	
			if (!verificarLongitud(campo, campoValue, campos)) {
				validado = false;
			}
			if (TipoResultadoEnum.WRN.equals(resultado) || TipoResultadoEnum.NOK.equals(resultado)) {
				if (Validaciones.isEmptyAlfanumericoSerializado(campoValue)) {
					String mensajeError = Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.nulo");
					ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
				}
			}
		} else {
			campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_REINTEGRO_RESULTADO_CONSULTA;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_REINTEGRO_CODIGO_ERROR;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
			campo = MicOperacionMasivaCamposEntradaEnum.RESPUESTA_REINTEGRO_DESCRIPCION_ERROR;
			campoValue = campos[campo.posicion()];
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
		}

		return validado;
	}

	private boolean validarCampoFecha(MicOperacionMasivaCamposEntradaEnum campo, String campoValue, String[] campos, boolean carry) {
		boolean validado = true;
		if (campo.longitud() != campoValue.length()) {
			String mensajeError = Utilidad.reemplazarMensajes(
			Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorLongitud"),
				String.valueOf(campoValue.length()),
				String.valueOf(campo.longitud())
			);
			ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
			validado  = false;
		}
		if (carry) {
			if (Validaciones.isEmptyNumericoSerializado(campoValue)) {
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.nulo"));
				validado  = false;
			}
			
			try{
				if (Validaciones.isObjectNull(Utilidad.parseDateWSFormat(campoValue))) {
					throw new ParseException("",0);
				}
			} catch (ParseException e){
				String mensajeError = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString(
						"error.operacionMasiva.validarCampos.mic.errorFecha"), 
						campoValue
					);
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado  = false;
			}
		} else {
			if (!verificarCampoNulo(campo, campoValue, campos)) {
				validado = false;
			}
		}
		
		return validado;
	}
	
	private boolean verificarCampoNumerico(MicOperacionMasivaCamposEntradaEnum campo, String campoValue, String[] campos) {
		boolean validado = true;

		if (campo.longitud() != campoValue.length()) {
			String mensajeError = Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorLongitud"),
				String.valueOf(campoValue.length()),
				String.valueOf(campo.longitud())
			);
			ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
			validado  = false;
		}
		if (!Validaciones.isNumeric(campoValue)) {
			String mensajeError = Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.numerico"),
				String.valueOf(campoValue)
			);
			ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
			validado  = false;
		}
//		if (Validaciones.isEmptyNumericoSerializado(campoValue)) {
//			ValidarAuxiliarSingleton.getInstance().setLinea(campo, Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.nulo"));
//			validado  = false;
//		}
		return validado;
	}
	private boolean verificarLongitud(MicOperacionMasivaCamposEntradaEnum campo, String campoValue, String[] campos) {
		if (campo.longitud() != campoValue.length()) {
			String mensajeError = Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorLongitud"),
				String.valueOf(campoValue.length()),
				String.valueOf(campo.longitud())
			);
			ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
			return false;
		}
		return true;
	}
	
	private boolean validarCampoSiNo(MicOperacionMasivaCamposEntradaEnum campo, String campoValue, String[] campos) {
		boolean validado = true;

		if (campo.longitud() != campoValue.length()) {
			String mensajeError = Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorLongitud"),
				String.valueOf(campoValue.length()),
				String.valueOf(campo.longitud())
			);
			ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
			validado  = false;
		}
		if (
			!SiNoEnum.SI.getDescripcionCorta().equals(campoValue.trim()) &&
			!SiNoEnum.NO.getDescripcionCorta().equals(campoValue.trim())
		) {
			String mensajeError = Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString(
					"error.operacionMasiva.validarCampos.mic.errorImagen"),
					campoValue.trim(),
					SiNoEnum.SI.getDescripcionCorta() + " | " + SiNoEnum.NO.getDescripcionCorta()
			);
			ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
			validado  = false;
		}
		return validado;
	}
	
	private boolean verificarCampoEnGrupo(List<String> grupo, String leyenda, MicOperacionMasivaCamposEntradaEnum campo, String campoValue, String[] campos) {
		boolean validado = true;
		if (!grupo.contains(campoValue.trim())) {
			String mensajeError = Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString(
					"error.operacionMasiva.validarCampos.mic.errorImagen"),
					campoValue.trim(),
					leyenda
			);
			ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
			validado  = false;
		}
		return validado;
	}
	private boolean verificarCampoEnGrupo(boolean conNulos, List<String> grupo, String leyenda, MicOperacionMasivaCamposEntradaEnum campo, String campoValue, String[] campos) {
		boolean validado = true;
		if (conNulos) {
			if (campo.tipo() == Constantes.TIPO_ALFANUMERICO) {
				if (Validaciones.isEmptyAlfanumericoSerializado(campoValue)) {
					return true;
				}
			} else {
				if (Validaciones.isEmptyNumericoSerializado(campoValue)) {
					return true;
				}
			}
		}
		if (!grupo.contains(campoValue.trim())) {
			String mensajeError = Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString(
					"error.operacionMasiva.validarCampos.mic.errorImagen"),
					campoValue.trim(),
					leyenda
			);
			ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
			validado  = false;
		}
		return validado;
	}
	
	private boolean verificarCampoFecha(boolean obligatorio, MicOperacionMasivaCamposEntradaEnum campo, String campoValue, String[] campos) {
		boolean validado = true;

		if (verificarCampoNumerico(campo, campoValue, campos)) {
			validado = false;
		}
		if (!obligatorio) {
			if (Validaciones.isEmptyNumericoSerializado(campoValue)) {
				return true;
			}
			if (Validaciones.isEmptyAlfanumericoSerializado(campoValue)) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorFecha"), 
							campoValue);
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado = false;
			}
		}
		try{
			if(Validaciones.isObjectNull(Utilidad.parseDateWSFormat(campoValue))) {
				throw new ParseException("", 0);
			}
		} catch(ParseException e){
			String mensajeError = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorFecha"), 
						"Fecha de Acta Desistimiento");
			ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
			validado  = false;
		}
		return validado;
	}
	private boolean verificarCampoNulo(MicOperacionMasivaCamposEntradaEnum campo, String campoValue, String[] campos) {
		boolean validado = true;

		if (!verificarLongitud(campo, campoValue, campos)) {
			validado = false;
		}
		if (campo.tipo() == Constantes.TIPO_ALFANUMERICO) {
			if (!Validaciones.isEmptyAlfanumericoSerializado(campoValue)) {
				String mensajeError = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.empty"),
						campoValue,
						"ALFANUMERICO",
						Utilidad.rellenarEspaciosDerecha(
							"",
							campo.longitud()
				));
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado = false;
			}
		} else {
			if (!Validaciones.isEmptyNumericoSerializado(campoValue)) {
				String mensajeError = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.empty"),
						campoValue,
						"NUMERICO",
						Utilidad.rellenarCerosIzquierda(
						"",
						campo.longitud()
				));
				ValidarAuxiliarSingleton.getInstance().setLinea(campo, mensajeError);
				validado = false;
			}
		}
		return validado;
	}

	private boolean verficarResultado(TipoArchivoOperacionMasivaEnum tipo, Map<String, TipoResultadoEnum> rtaMic) {
		boolean salida = false;
		TipoResultadoEnum rtaDebito = rtaMic.get("validarRespuestasResultadoDebito");
		TipoResultadoEnum rtaCredito = rtaMic.get("validarRespuestasResultadoCredito");
		TipoResultadoEnum rtaReintegro  = rtaMic.get("validarRespuestasResultadoReintegro");
		
		switch (tipo) {
		case DEUDA:
			if (
				!Validaciones.isObjectNull(rtaCredito) && 
				(
					TipoResultadoEnum.OK.equals(rtaCredito)||
					TipoResultadoEnum.WRN.equals(rtaCredito)
				) &&
				!Validaciones.isObjectNull(rtaDebito) && 
				(
					TipoResultadoEnum.OK.equals(rtaDebito) ||
					TipoResultadoEnum.WRN.equals(rtaDebito)
				)
			) {
				salida = true;
			}
			break;
		case DSIST:
			if (!Validaciones.isObjectNull(rtaDebito) && 
				(
					TipoResultadoEnum.OK.equals(rtaDebito) ||
					TipoResultadoEnum.WRN.equals(rtaDebito)
				)
			) {
				salida = true;
			}
			break;
		case GNCIA:
			if (
				!Validaciones.isObjectNull(rtaCredito) &&
				(
					TipoResultadoEnum.OK.equals(rtaCredito) ||
					TipoResultadoEnum.WRN.equals(rtaCredito)
				)
			) {
				salida = true;
			}
			break;
		case REINT:
			if (
				!Validaciones.isObjectNull(rtaReintegro) &&
				(
					TipoResultadoEnum.OK.equals(rtaReintegro) ||
					TipoResultadoEnum.WRN.equals(rtaReintegro)
				) &&
				!Validaciones.isObjectNull(rtaCredito) &&
				(
					TipoResultadoEnum.WRN.equals(rtaCredito) ||
					TipoResultadoEnum.OK.equals(rtaCredito)
				)
			) {
				salida = true;
			}
			break;
	
		}	
		return salida;
	}
}
