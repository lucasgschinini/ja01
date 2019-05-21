package ar.com.telecom.shiva.base.registros.datos.entrada.decoradores;

import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosAcuerdoClienteEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosAcuerdoGralesEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosCobranzasGeneracionCargoEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosCreditoAplicadoClienteEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosCreditoAplicadoDacotaEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosCreditoAplicadoGralesEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosCreditoAplicadoMedioPagoEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosCreditoAplicadoNotaCreditoEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosCreditoAplicadoRemanenteEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosCreditoAplicadoTagetikEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosRespuestaGeneralesEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaRegistroEntrada;

public class MicMasRegistroReintegro extends MicMasRegistro {

	public MicMasRegistroReintegro() {
		// TODO Auto-generated constructor stub
	}

	public MicMasRegistroReintegro(MicOperacionMasivaRegistroEntrada registro) {
		super(registro);
		// TODO Auto-generated constructor stub
	}
	
	// Datos de cobranza generacion de cargo
	public MicOperacionMasivaDatosCobranzasGeneracionCargoEntrada getDatosCobranzasGeneracionCargoEntrada() {
		return this.registro.getDatosCobranzasGeneracionCargoEntrada();
	}
	// Datos del credito aplicado: cliente
	public MicOperacionMasivaDatosCreditoAplicadoClienteEntrada getDatosCreditoAplicadoCliente() {
		return this.registro.getDatosCreditoAplicadoCliente();
	}
	// Datos del credito aplicado: medio de pago (foto de los datos previos a la imputacion)
	public MicOperacionMasivaDatosCreditoAplicadoMedioPagoEntrada getDatosCreditoAplicadoMedioPago() {
		return this.registro.getDatosCreditoAplicadoMedioPago();
	}
	// Datos del credito aplicado: Nota de credito (foto de los datos previos a la imputacion)
	public MicOperacionMasivaDatosCreditoAplicadoNotaCreditoEntrada getDatosCreditoAplicadoNotaCredito() {
		return this.registro.getDatosCreditoAplicadoNotaCredito();
	}
	// Datos del credito aplicado: Remanente (foto de los datos previos a la imputacion)
	public MicOperacionMasivaDatosCreditoAplicadoRemanenteEntrada getDatosCreditoAplicadoRemanente() {
		return this.registro.getDatosCreditoAplicadoRemanente();
	}
	// Datos del credito aplicado: datos generales (foto de los datos previos a la imputacion)
	public MicOperacionMasivaDatosCreditoAplicadoGralesEntrada getDatosCreditoAplicadoDatosGenerales() {
		return this.registro.getDatosCreditoAplicadoDatosGenerales();
	}
	// Datos del credito aplicado: Tagetik  (foto de los datos previos a la imputacion)
	public MicOperacionMasivaDatosCreditoAplicadoTagetikEntrada getDatosCreditoAplicadoTagetik() {
		return this.registro.getDatosCreditoAplicadoTagetik();
	}
	// Datos del credito aplicado: Dacota (foto de los datos previos a la imputacion)
	public MicOperacionMasivaDatosCreditoAplicadoDacotaEntrada getDatosCreditoAplicadoDacota() {
		return this.registro.getDatosCreditoAplicadoDacota();
	}
	// Datos del acuerdo de intereses por mora / acuerdo del reintegro: datos generales (foto de los datos previos a la imputacion)
	public MicOperacionMasivaDatosAcuerdoGralesEntrada getDatosAcuerdoGenerales() {
		return this.registro.getDatosAcuerdoGenerales();
	}
	// Datos del acuerdo de intereses por mora / acuerdo del reintegro: datos del cliente
	public MicOperacionMasivaDatosAcuerdoClienteEntrada getDatosAcuerdosCliente() {
		return this.registro.getDatosAcuerdosCliente();
	}
	
	// Datos de respuesta generales: resultado de la invocación a nivel reintegro
	public MicOperacionMasivaDatosRespuestaGeneralesEntrada getDatosRespuestaReintegor() {
		return this.registro.getDatosRespuestaReintegro();
	}
	// Datos de respuesta generales: resultado de la invocación a nivel credito
	public MicOperacionMasivaDatosRespuestaGeneralesEntrada getDatosRespuestaCredito() {
		return this.registro.getDatosRespuestaCredito();
	}
}
