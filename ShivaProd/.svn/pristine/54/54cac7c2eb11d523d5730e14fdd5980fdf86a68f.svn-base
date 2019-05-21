package ar.com.telecom.shiva.base.registros.datos.entrada.decoradores;

import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosAcuerdoClienteEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosClienteImputadoEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosDebitoImputadoDacotaEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosDebitoImputadoGralesEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosDebitoImputadoSaldoTercerosEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosDebitoImputadoTagetikEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosRespuestaGeneralesEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaRegistroEntrada;

public class MicMasRegistroDesistimiento extends MicMasRegistro {

	public MicMasRegistroDesistimiento() {
		// TODO Auto-generated constructor stub
	}

	public MicMasRegistroDesistimiento(
			MicOperacionMasivaRegistroEntrada registro) {
		super(registro);
		// TODO Auto-generated constructor stub
	}
	// Datos del debito imputado: cliente
	public MicOperacionMasivaDatosClienteImputadoEntrada getDatosClienteImputado() {
		return this.registro.getDatosClienteImputado();
	}
	// Datos del debito imputado: datos generales (foto de los datos previos a la imputacion)
	public MicOperacionMasivaDatosDebitoImputadoGralesEntrada getDatosDebitoImputadoDatosGenerales() {
		return this.registro.getDatosDebitoImputadoDatosGenerales();
	}
	// Datos del debito imputado: tagetik  (foto de los datos previos a la imputacion)
	public MicOperacionMasivaDatosDebitoImputadoTagetikEntrada getDatosDebitoImputadoTegetik() {
		return this.registro.getDatosDebitoImputadoTegetik();
	}
	// Datos del debito imputado: Dacota  (foto de los datos previos a la imputacion)
	public MicOperacionMasivaDatosDebitoImputadoDacotaEntrada getDatosDebitoImputadoDacota() {
		return this.registro.getDatosDebitoImputadoDacota();
	}
	// Datos del debito imputado: Saldos de terceros  (foto de los datos previos a la imputacion)
	public MicOperacionMasivaDatosDebitoImputadoSaldoTercerosEntrada getDatosDebitoImputadoSaldoTerceros() {
		return this.registro.getDatosDebitoImputadoSaldoTerceros();
	}
	// Datos del acuerdo de intereses por mora / acuerdo del reintegro: datos del cliente
	public MicOperacionMasivaDatosAcuerdoClienteEntrada getDatosAcuerdosCliente() {
		return this.registro.getDatosAcuerdosCliente();
	}
	// Datos de respuesta generales: resultado de la invocación a nivel debito
	public MicOperacionMasivaDatosRespuestaGeneralesEntrada getDatosRespuestaDebito() {
		return this.registro.getDatosRespuestaDebito();
	}
}
