package ar.com.telecom.shiva.base.registros.mapeos;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.REG;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenCargoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoArchivoOperacionMasivaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDocumentoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRegistroEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.IOperacionMasivaRegistroMapeador;
import ar.com.telecom.shiva.base.registros.datos.entrada.decoradores.MicMasRegistro;
import ar.com.telecom.shiva.base.registros.datos.entrada.decoradores.MicMasRegistroAplicarDeuda;
import ar.com.telecom.shiva.base.registros.datos.entrada.decoradores.MicMasRegistroDesistimiento;
import ar.com.telecom.shiva.base.registros.datos.entrada.decoradores.MicMasRegistroGanancias;
import ar.com.telecom.shiva.base.registros.datos.entrada.decoradores.MicMasRegistroReintegro;
import ar.com.telecom.shiva.base.registros.datos.salida.agrupador.MicOperacionMasivaCargoProximaFacturaSalida;
import ar.com.telecom.shiva.base.registros.datos.salida.agrupador.MicOperacionMasivaDatosGralesSalida;
import ar.com.telecom.shiva.base.registros.datos.salida.agrupador.MicOperacionMasivaDebitoSalida;
import ar.com.telecom.shiva.base.registros.datos.salida.agrupador.MicOperacionMasivaDesistimientoSalida;
import ar.com.telecom.shiva.base.registros.datos.salida.agrupador.MicOperacionMasivaGananciaSalida;
import ar.com.telecom.shiva.base.registros.datos.salida.agrupador.MicOperacionMasivaMedioPagoSalida;
import ar.com.telecom.shiva.base.registros.datos.salida.agrupador.MicOperacionMasivaRegistroSalida;
import ar.com.telecom.shiva.base.registros.datos.salida.agrupador.MicOperacionMasivaReintegroSalida;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistro;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroAplicarDeuda;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroDesistimiento;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroGanancias;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroReintegro;

public class MicOperacionMasivaRegistroMapeador implements IOperacionMasivaRegistroMapeador {

	
	@Autowired
	IParametroServicio parametroServicio;

	// u578936 (Max) El atributo se utiliza en el batch por lo que hay un solo hilo de ejecucion
	private Map<Long, Long> operacionMasivaRegistrosCant = new HashMap<Long, Long>();


	public MicOperacionMasivaRegistroMapeador() {
	
	}
	
	public void inicializarContadorOperacionMasiva() {
		operacionMasivaRegistrosCant.clear();
	}
	
	@Override
	public ShvMasRegistro mapRegistroEntrada(MicMasRegistro registro, ShvMasRegistro modelo) throws NegocioExcepcion {
		if (Validaciones.isObjectNull(modelo)) {
			if (registro instanceof MicMasRegistroAplicarDeuda) {
				modelo = new ShvMasRegistroAplicarDeuda();
			} else if (registro instanceof MicMasRegistroDesistimiento) {
				modelo = new ShvMasRegistroDesistimiento();
			} else if (registro instanceof MicMasRegistroGanancias) {
				modelo = new ShvMasRegistroGanancias();
			} else if (registro instanceof MicMasRegistroReintegro) {
				modelo = new ShvMasRegistroReintegro();
			} else {
				throw new NegocioExcepcion("El registro no es de una clase conocida");
			}
		}
		if (modelo instanceof ShvMasRegistroAplicarDeuda) {
			modelo = map((MicMasRegistroAplicarDeuda) registro, (ShvMasRegistroAplicarDeuda) modelo);
		} else if (modelo instanceof ShvMasRegistroDesistimiento) {
			modelo = map((MicMasRegistroDesistimiento) registro, (ShvMasRegistroDesistimiento) modelo);
		} else if (modelo instanceof ShvMasRegistroGanancias) {
			modelo = map((MicMasRegistroGanancias) registro, (ShvMasRegistroGanancias) modelo);
		} else if (modelo instanceof ShvMasRegistroReintegro) {
			modelo = map((MicMasRegistroReintegro) registro, (ShvMasRegistroReintegro) modelo);
		} else {
			throw new NegocioExcepcion("El modelo no es de una clase conocida");
		}
		modelo.setFechaSalida(registro.getFechaArchivoSalida());
		modelo.setNombreArchivoSalida(registro.getNombreArchivoSalida());
		return modelo;
	}
	
	private ShvMasRegistro map(MicMasRegistroAplicarDeuda registro, ShvMasRegistroAplicarDeuda modelo) throws NegocioExcepcion {
		
		// Datos de cobranza apropiacion de deuda
		modelo.setCobranzaApropiacionInteresesBonificadosNoRegulados(registro.getDatosCobranzaApropiacionDeuda().getInteresesBonificadosNoRegulados());
		modelo.setCobranzaApropiacionInteresesBonificadosRegulados(registro.getDatosCobranzaApropiacionDeuda().getInteresesBonificadosRegulados());
		modelo.setCobranzaApropiacionInteresesTrasladadosNoRegulados(registro.getDatosCobranzaApropiacionDeuda().getInteresesTrasladadosNoRegulados());
		modelo.setCobranzaApropiacionInteresesTrasladadosRegulados(registro.getDatosCobranzaApropiacionDeuda().getInteresesTrasladadosRegulados());
		// Datos del debito imputado: cliente
		modelo.setDebitoImputadoCodigoCliente(registro.getDatosClienteImputado().getCodigoCliente());
		// Datos del debito imputado: datos generales (foto de los datos previos a la imputacion)
		modelo.setDebitoImputadoGralCuentaDebito(registro.getDatosDebitoImputadoDatosGenerales().getCuenta());
		modelo.setDebitoImputadoGralTipoDocumento(registro.getDatosDebitoImputadoDatosGenerales().getTipoDocumento());
		modelo.setDebitoImputadoGralTipoDuc(registro.getDatosDebitoImputadoDatosGenerales().getTipoDuc());
		modelo.setDebitoImputadoGralEstadoDuc(registro.getDatosDebitoImputadoDatosGenerales().getEstadoDuc());
		modelo.setDebitoImputadoGralAcuerdo(registro.getDatosDebitoImputadoDatosGenerales().getAcuerdo());
		modelo.setDebitoImputadoGralClaseComprobante(registro.getDatosDebitoImputadoDatosGenerales().getClaseComprobante());
		modelo.setDebitoImputadoGralSucursalComprobante(registro.getDatosDebitoImputadoDatosGenerales().getSucursalComprobante());
		modelo.setDebitoImputadoGralNumeroComprobante(registro.getDatosDebitoImputadoDatosGenerales().getNumeroComprobante());
		modelo.setDebitoImputadoGralNumeroReferenciaMic(!Validaciones.isObjectNull(registro.getDatosDebitoImputadoDatosGenerales().getNumeroReferenciaMic()) ? registro.getDatosDebitoImputadoDatosGenerales().getNumeroReferenciaMic().toString() : "");
		modelo.setDebitoImputadoGralFechaVencimiento(registro.getDatosDebitoImputadoDatosGenerales().getFechaVencimiento());
		modelo.setDebitoImputadoGralImportePrimerVencimiento(registro.getDatosDebitoImputadoDatosGenerales().getImportePrimerVencimiento());
		modelo.setDebitoImputadoGralImporteSegundoVencimiento(registro.getDatosDebitoImputadoDatosGenerales().getImporteSegundoVencimiento());
		modelo.setDebitoImputadoGralSaldoPrimerVencimiento(registro.getDatosDebitoImputadoDatosGenerales().getSaldoPrimerVencimiento());
		modelo.setDebitoImputadoGralEstadoAcuerdoFacturacion(registro.getDatosDebitoImputadoDatosGenerales().getEstadoAcuerdoFacturacion());
		modelo.setDebitoImputadoGralEstadoConceptoTerceros(registro.getDatosDebitoImputadoDatosGenerales().getEstadoConceptoTerceros());
		modelo.setDebitoImputadoGralPosibilidadDestransferencia(registro.getDatosDebitoImputadoDatosGenerales().getPosibilidadDestransferencia());
		modelo.setDebitoImputadoGralImporteTercerosTransferibles(registro.getDatosDebitoImputadoDatosGenerales().getImporteTercerosTransferibles());
		modelo.setDebitoImputadoGralImportePrimerVencimientoConTerceros(registro.getDatosDebitoImputadoDatosGenerales().getImportePrimerVencimientoConTerceros());
		modelo.setDebitoImputadoGralImporteSegundoVencimientoConTerceros(registro.getDatosDebitoImputadoDatosGenerales().getImporteSegundoVencimientoConTerceros());
		modelo.setDebitoImputadoGralCodigoEstadoFactura(registro.getDatosDebitoImputadoDatosGenerales().getCodigoEstadoFactura());
		modelo.setDebitoImputadoGralMarcaReclamo(registro.getDatosDebitoImputadoDatosGenerales().getMarcaReclamo());
		modelo.setDebitoImputadoGralMarcaCyq(registro.getDatosDebitoImputadoDatosGenerales().getMarcaCyq());
		modelo.setDebitoImputadoGralFechaEmision(registro.getDatosDebitoImputadoDatosGenerales().getFechaEmision());
		modelo.setDebitoImputadoGralFechaVencimiento(registro.getDatosDebitoImputadoDatosGenerales().getFechaVencimiento());
		modelo.setDebitoImputadoGralNumeroConvenio(registro.getDatosDebitoImputadoDatosGenerales().getNumeroConvenio());
		modelo.setDebitoImputadoGralCuota(registro.getDatosDebitoImputadoDatosGenerales().getCuota());
		modelo.setDebitoImputadoGralFechaUltimoPagoParcial(registro.getDatosDebitoImputadoDatosGenerales().getFechaUltimoPagoParcial());
		modelo.setDebitoImputadoGralFechaPuestaCobro(registro.getDatosDebitoImputadoDatosGenerales().getFechaPuestaCobro());

		//Datos del debito imputado: tagetik  (foto de los datos previos a la imputacion)
		modelo.setDebitoTagetikRazonSocialCliente(registro.getDatosDebitoImputadoTegetik().getRazonSocialCliente());
		modelo.setDebitoTagetikTipoCliente(registro.getDatosDebitoImputadoTegetik().getTipoCliente());
		modelo.setDebitoTagetikCuit(registro.getDatosDebitoImputadoTegetik().getCuit());
		modelo.setDebitoTagetikCicloFacturacion(registro.getDatosDebitoImputadoTegetik().getCicloFacturacion());
		modelo.setDebitoTagetikMarketingCustomerGroup(registro.getDatosDebitoImputadoTegetik().getMarketingCustomerGroup());
		modelo.setDebitoTagetikTipoFactura(registro.getDatosDebitoImputadoTegetik().getTipoFactura());
		
		// Datos del debito imputado: Dacota  (foto de los datos previos a la imputacion)
		modelo.setDebitoDakotaFechaVencimientoMora(registro.getDatosDebitoImputadoDacota().getFechaVencimientoMora());
		modelo.setDebitoDakotaIndicadorPeticionCorte(registro.getDatosDebitoImputadoDacota().getIndicadorPeticionCorte());
		modelo.setDebitoDakotaCodigoTarifa(registro.getDatosDebitoImputadoDacota().getCodigoTarifa());
		// Datos del debito imputado: Saldos de terceros  (foto de los datos previos a la imputacion)
		modelo.setDebitoSaldosTercerosSaldoTerceroFinanciableNOTransferible(registro.getDatosDebitoImputadoSaldoTerceros().getSaldoTerceroFinanciableNOTransferible());
		modelo.setDebitoSaldosTercerosSaldoTerceroFinanciableTransferible(registro.getDatosDebitoImputadoSaldoTerceros().getSaldoTerceroFinanciableTransferible());
		modelo.setDebitoSaldosTercerosSaldoTerceroNOFinanciableTransferible(registro.getDatosDebitoImputadoSaldoTerceros().getSaldoTerceroNOFinanciableTransferible());

		// Datos del credito aplicado: Tagetik  (foto de los datos previos a la imputacion)
		modelo.setCreditoTagetikRazonSocialCliente(registro.getDatosCreditoAplicadoTagetik().getRazonSocialCliente());
		modelo.setCreditoTagetikTipoCliente(registro.getDatosCreditoAplicadoTagetik().getTipoCliente());
		modelo.setCreditoTagetikCuit(registro.getDatosCreditoAplicadoTagetik().getCuit());
		modelo.setCreditoTagetikCicloFacturacion(registro.getDatosCreditoAplicadoTagetik().getCicloFacturacion());
		modelo.setCreditoTagetikMarketingCustomerGroup(registro.getDatosCreditoAplicadoTagetik().getMarketingCustomerGroup());
		modelo.setCreditoTagetikTipoFactura(registro.getDatosCreditoAplicadoTagetik().getTipoFactura());
		modelo.setCreditoTagetikImporteOriginal(registro.getDatosCreditoAplicadoTagetik().getImporteOriginal());
		//Datos del credito aplicado: Dacota (foto de los datos previos a la imputacion)
		modelo.setCreditoDakotaFechaVencimientoMora(registro.getDatosCreditoAplicadoDacota().getFechaVencimientoMora());
		modelo.setCreditoDakotaIndicadorPeticionCorte(registro.getDatosCreditoAplicadoDacota().getIndicadorPeticionCorte());
		modelo.setCreditoDakotaCodigoTarifa(registro.getDatosCreditoAplicadoDacota().getCodigoTarifa());
		//Datos del acuerdo de intereses por mora / acuerdo del reintegro: datos generales (foto de los datos previos a la imputacion)
		modelo.setAcuerdoGralCodigoCliente(registro.getDatosAcuerdoGenerales().getCodigoCliente());
		modelo.setAcuerdoGralAcuerdoFacturacion(registro.getDatosAcuerdoGenerales().getAcuerdoFacturacion());
		modelo.setAcuerdoGralLinea(registro.getDatosAcuerdoGenerales().getLinea());
		modelo.setAcuerdoGralEstadoAcuerdoFacturacion(registro.getDatosAcuerdoGenerales().getEstadoAcuerdoFacturacion());
		modelo.setAcuerdoDatosClientecodigoCliente(registro.getDatosAcuerdosCliente().getCodigoCliente());
		// Datos del credito aplicado: Nota de credito (foto de los datos previos a la imputacion)
		
		modelo.setCreditoMedioPagoTipoComprobante(registro.getDatosCreditoAplicadoNotaCredito().getTipoComprobante());
		modelo.setCreditoMedioPagoClaseComprobante(registro.getDatosCreditoAplicadoNotaCredito().getClaseComprobante());
		modelo.setCreditoMedioPagoSucursalComprobante(registro.getDatosCreditoAplicadoNotaCredito().getSucursalComprobante());
		modelo.setCreditoMedioPagoNumeroComprobante(registro.getDatosCreditoAplicadoNotaCredito().getNumeroComprobante());
		modelo.setCreditoMedioPagoNroReferenciaMic(registro.getDatosCreditoAplicadoNotaCredito().getNroReferenciaMic());
		// Datos del credito aplicado: datos generales (foto de los datos previos a la imputacion)
		modelo.setCreditoGralImporte(registro.getDatosCreditoAplicadoDatosGenerales().getImporte());
		modelo.setCreditoGralSaldo(registro.getDatosCreditoAplicadoDatosGenerales().getSaldo());
		modelo.setCreditoGralFechaAlta(registro.getDatosCreditoAplicadoDatosGenerales().getFechaAlta());
		modelo.setCreditoGralFechaEmision(registro.getDatosCreditoAplicadoDatosGenerales().getFechaEmision());
		modelo.setCreditoGralFechaUltimoMovimiento(registro.getDatosCreditoAplicadoDatosGenerales().getFechaUltimoMovimiento());
		modelo.setCreditoGralMarcaReclamo(registro.getDatosCreditoAplicadoDatosGenerales().getMarcaReclamo());
		modelo.setCreditoGralMarcaCyq(registro.getDatosCreditoAplicadoDatosGenerales().getMarcaCyq());
		modelo.setEstadoOrigen(registro.getDatosCreditoAplicadoDatosGenerales().getEstadoOrigen());
		modelo.setRemanenteTipoRemanente(registro.getDatosCreditoAplicadoRemanente().getTipoRemanente());
		modelo.setResultadoConsultaDebito(registro.getDatosRespuestaDebito().getResultadoConsulta());
		modelo.setCodigoErrorDebito(registro.getDatosRespuestaDebito().getCodigoError());
		modelo.setDescripcionErrorDebito(registro.getDatosRespuestaDebito().getDescripcionError());
		modelo.setResultadoConsultaCredito(registro.getDatosRespuestaCredito().getResultadoConsulta());
		modelo.setCodigoErrorCredito(registro.getDatosRespuestaCredito().getCodigoError());
		modelo.setDescripcionErrorCredito(registro.getDatosRespuestaCredito().getDescripcionError());
		
		modelo.setIdCobranza(registro.getDatosCobranzaGenerales().getIdCobranza());
		modelo.setFechaValorCobranza(registro.getDatosCobranzaGenerales().getFechaValorCobranza());

		modelo.setCreditoCodigoCliente(registro.getDatosCreditoAplicadoCliente().getCodigoCliente());
		modelo.setCreditoMedioPagoCuenta(registro.getDatosCreditoAplicadoMedioPago().getCuenta());
		modelo.setCreditoMedioPagoTipoCredito(registro.getDatosCreditoAplicadoMedioPago().getTipoCredito());
		
		return modelo;
	}
	private ShvMasRegistro map(MicMasRegistroDesistimiento registro, ShvMasRegistroDesistimiento modelo) throws NegocioExcepcion {
		// Datos del debito imputado: cliente
		modelo.setDebitoImputadoCodigoCliente(registro.getDatosClienteImputado().getCodigoCliente());
		// Datos del debito imputado: datos generales (foto de los datos previos a la imputacion)
		modelo.setDebitoImputadoGralCuentaDebito(registro.getDatosDebitoImputadoDatosGenerales().getCuenta());
		modelo.setDebitoImputadoGralTipoDocumento(registro.getDatosDebitoImputadoDatosGenerales().getTipoDocumento());
		modelo.setDebitoImputadoGralTipoDuc(registro.getDatosDebitoImputadoDatosGenerales().getTipoDuc());
		modelo.setDebitoImputadoGralEstadoDuc(registro.getDatosDebitoImputadoDatosGenerales().getEstadoDuc());
		modelo.setDebitoImputadoGralAcuerdo(registro.getDatosDebitoImputadoDatosGenerales().getAcuerdo());
		modelo.setDebitoImputadoGralClaseComprobante(registro.getDatosDebitoImputadoDatosGenerales().getClaseComprobante());
		modelo.setDebitoImputadoGralSucursalComprobante(registro.getDatosDebitoImputadoDatosGenerales().getSucursalComprobante());
		modelo.setDebitoImputadoGralNumeroComprobante(registro.getDatosDebitoImputadoDatosGenerales().getNumeroComprobante());
		modelo.setDebitoImputadoGralNumeroReferenciaMic(!Validaciones.isObjectNull(registro.getDatosDebitoImputadoDatosGenerales().getNumeroReferenciaMic()) ? registro.getDatosDebitoImputadoDatosGenerales().getNumeroReferenciaMic() : null);
		modelo.setDebitoImputadoGralFechaVencimiento(registro.getDatosDebitoImputadoDatosGenerales().getFechaVencimiento());
		modelo.setDebitoImputadoGralImportePrimerVencimiento(registro.getDatosDebitoImputadoDatosGenerales().getImportePrimerVencimiento());
		modelo.setDebitoImputadoGralImporteSegundoVencimiento(registro.getDatosDebitoImputadoDatosGenerales().getImporteSegundoVencimiento());
		modelo.setDebitoImputadoGralSaldoPrimerVencimiento(registro.getDatosDebitoImputadoDatosGenerales().getSaldoPrimerVencimiento());
		modelo.setDebitoImputadoGralEstadoAcuerdoFacturacion(registro.getDatosDebitoImputadoDatosGenerales().getEstadoAcuerdoFacturacion());
		modelo.setDebitoImputadoGralEstadoConceptoTerceros(registro.getDatosDebitoImputadoDatosGenerales().getEstadoConceptoTerceros());
		modelo.setDebitoImputadoGralPosibilidadDestransferencia(registro.getDatosDebitoImputadoDatosGenerales().getPosibilidadDestransferencia());
		modelo.setDebitoImputadoGralImporteTercerosTransferibles(registro.getDatosDebitoImputadoDatosGenerales().getImporteTercerosTransferibles());
		modelo.setDebitoImputadoGralImportePrimerVencimientoConTerceros(registro.getDatosDebitoImputadoDatosGenerales().getImportePrimerVencimientoConTerceros());
		modelo.setDebitoImputadoGralImporteSegundoVencimientoConTerceros(registro.getDatosDebitoImputadoDatosGenerales().getImporteSegundoVencimientoConTerceros());
		modelo.setDebitoImputadoGralCodigoEstadoFactura(registro.getDatosDebitoImputadoDatosGenerales().getCodigoEstadoFactura());
		modelo.setDebitoImputadoGralMarcaReclamo(registro.getDatosDebitoImputadoDatosGenerales().getMarcaReclamo());
		modelo.setDebitoImputadoGralMarcaCyq(registro.getDatosDebitoImputadoDatosGenerales().getMarcaCyq());
		modelo.setDebitoImputadoGralFechaEmision(registro.getDatosDebitoImputadoDatosGenerales().getFechaEmision());
		modelo.setDebitoImputadoGralNumeroConvenio(registro.getDatosDebitoImputadoDatosGenerales().getNumeroConvenio());
		modelo.setDebitoImputadoGralCuota(registro.getDatosDebitoImputadoDatosGenerales().getCuota());
		modelo.setDebitoImputadoGralFechaUltimoPagoParcial(registro.getDatosDebitoImputadoDatosGenerales().getFechaUltimoPagoParcial());
		modelo.setDebitoImputadoGralFechaPuestaCobro(registro.getDatosDebitoImputadoDatosGenerales().getFechaPuestaCobro());
		//Datos del debito imputado: tagetik  (foto de los datos previos a la imputacion)
		modelo.setDebitoTagetikRazonSocialCliente(registro.getDatosDebitoImputadoTegetik().getRazonSocialCliente());
		modelo.setDebitoTagetikTipoCliente(registro.getDatosDebitoImputadoTegetik().getTipoCliente());
		modelo.setDebitoTagetikCuit(registro.getDatosDebitoImputadoTegetik().getCuit());
		modelo.setDebitoTagetikCicloFacturacion(registro.getDatosDebitoImputadoTegetik().getCicloFacturacion());
		modelo.setDebitoTagetikMarketingCustomerGroup(registro.getDatosDebitoImputadoTegetik().getMarketingCustomerGroup());
		modelo.setDebitoTagetikTipoFactura(registro.getDatosDebitoImputadoTegetik().getTipoFactura());
		// Datos del debito imputado: Dacota  (foto de los datos previos a la imputacion)
		modelo.setDebitoDakotaFechaVencimientoMora(registro.getDatosDebitoImputadoDacota().getFechaVencimientoMora());
		modelo.setDebitoDakotaIndicadorPeticionCorte(registro.getDatosDebitoImputadoDacota().getIndicadorPeticionCorte());
		modelo.setDebitoDakotaCodigoTarifa(registro.getDatosDebitoImputadoDacota().getCodigoTarifa());
		// Datos del debito imputado: Saldos de terceros  (foto de los datos previos a la imputacion)
		modelo.setDebitoSaldosTercerosSaldoTerceroFinanciableNOTransferible(registro.getDatosDebitoImputadoSaldoTerceros().getSaldoTerceroFinanciableNOTransferible());
		modelo.setDebitoSaldosTercerosSaldoTerceroFinanciableTransferible(registro.getDatosDebitoImputadoSaldoTerceros().getSaldoTerceroFinanciableTransferible());
		modelo.setDebitoSaldosTercerosSaldoTerceroNOFinanciableTransferible(registro.getDatosDebitoImputadoSaldoTerceros().getSaldoTerceroNOFinanciableTransferible());
		modelo.setResultadoConsultaDebito(registro.getDatosRespuestaDebito().getResultadoConsulta());
		modelo.setCodigoErrorDebito(registro.getDatosRespuestaDebito().getCodigoError());
		modelo.setDescripcionErrorDebito(registro.getDatosRespuestaDebito().getDescripcionError());
		
		
		//
		modelo.setIdCobranza(registro.getDatosCobranzaGenerales().getIdCobranza());
		modelo.setFechaValorCobranza(registro.getDatosCobranzaGenerales().getFechaValorCobranza());
		return modelo;
	}
	private ShvMasRegistro map(MicMasRegistroGanancias registro, ShvMasRegistroGanancias modelo) throws NegocioExcepcion {
		
		modelo.setCreditoCodigoCliente(registro.getDatosCreditoAplicadoCliente().getCodigoCliente());
		modelo.setCreditoMedioPagoCuenta(registro.getDatosCreditoAplicadoMedioPago().getCuenta());
		modelo.setCreditoMedioPagoTipoCredito(registro.getDatosCreditoAplicadoMedioPago().getTipoCredito());
		modelo.setCreditoMedioPagoTipoComprobante(registro.getDatosCreditoAplicadoNotaCredito().getTipoComprobante());
		modelo.setCreditoMedioPagoClaseComprobante(registro.getDatosCreditoAplicadoNotaCredito().getClaseComprobante());
		modelo.setCreditoMedioPagoSucursalComprobante(registro.getDatosCreditoAplicadoNotaCredito().getSucursalComprobante());
		modelo.setCreditoMedioPagoNumeroComprobante(registro.getDatosCreditoAplicadoNotaCredito().getNumeroComprobante());
		modelo.setCreditoMedioPagoNroReferenciaMic(registro.getDatosCreditoAplicadoNotaCredito().getNroReferenciaMic());
		modelo.setRemanenteTipoRemanente(registro.getDatosCreditoAplicadoRemanente().getTipoRemanente());
		modelo.setCreditoGralImporte(registro.getDatosCreditoAplicadoDatosGenerales().getImporte());
		modelo.setCreditoGralSaldo(registro.getDatosCreditoAplicadoDatosGenerales().getSaldo());
		modelo.setCreditoGralFechaAlta(registro.getDatosCreditoAplicadoDatosGenerales().getFechaAlta());
		modelo.setCreditoGralFechaEmision(registro.getDatosCreditoAplicadoDatosGenerales().getFechaEmision());
		modelo.setCreditoGralFechaVencimiento(registro.getDatosCreditoAplicadoDatosGenerales().getFechaVencimiento());
		modelo.setCreditoGralFechaUltimoMovimiento(registro.getDatosCreditoAplicadoDatosGenerales().getFechaUltimoMovimiento());
		modelo.setCreditoGralMarcaReclamo(registro.getDatosCreditoAplicadoDatosGenerales().getMarcaReclamo());
		modelo.setCreditoGralMarcaCyq(registro.getDatosCreditoAplicadoDatosGenerales().getMarcaCyq());
		modelo.setEstadoOrigen(registro.getDatosCreditoAplicadoDatosGenerales().getEstadoOrigen());
		modelo.setCreditoTagetikRazonSocialCliente(registro.getDatosCreditoAplicadoTagetik().getRazonSocialCliente());
		modelo.setCreditoTagetikTipoCliente(registro.getDatosCreditoAplicadoTagetik().getTipoCliente());
		modelo.setCreditoTagetikCuit(registro.getDatosCreditoAplicadoTagetik().getCuit());
		modelo.setCreditoTagetikCicloFacturacion(registro.getDatosCreditoAplicadoTagetik().getCicloFacturacion());
		modelo.setCreditoTagetikMarketingCustomerGroup(registro.getDatosCreditoAplicadoTagetik().getMarketingCustomerGroup());
		modelo.setCreditoTagetikTipoFactura(registro.getDatosCreditoAplicadoTagetik().getTipoFactura());
		modelo.setCreditoTagetikImporteOriginal(registro.getDatosCreditoAplicadoTagetik().getImporteOriginal());
		//Datos del credito aplicado: Dacota (foto de los datos previos a la imputacion)
		modelo.setCreditoDakotaFechaVencimientoMora(registro.getDatosCreditoAplicadoDacota().getFechaVencimientoMora());
		modelo.setCreditoDakotaIndicadorPeticionCorte(registro.getDatosCreditoAplicadoDacota().getIndicadorPeticionCorte());
		modelo.setCreditoDakotaCodigoTarifa(registro.getDatosCreditoAplicadoDacota().getCodigoTarifa());
		modelo.setResultadoConsultaCredito(registro.getDatosRespuestaCredito().getResultadoConsulta());
		modelo.setCodigoErrorCredito(registro.getDatosRespuestaCredito().getCodigoError());
		modelo.setDescripcionErrorCredito(registro.getDatosRespuestaCredito().getDescripcionError());
		
		modelo.setIdCobranza(registro.getDatosCobranzaGenerales().getIdCobranza());
		modelo.setFechaValorCobranza(registro.getDatosCobranzaGenerales().getFechaValorCobranza());
		
		return modelo;
	}
	private ShvMasRegistro map(MicMasRegistroReintegro registro, ShvMasRegistroReintegro modelo) throws NegocioExcepcion {
		modelo.setCobranzaCargoInteresesTrasladadosRegulados(registro.getDatosCobranzasGeneracionCargoEntrada().getInteresesTrasladadosRegulados());
		modelo.setCobranzaCargoInteresesTrasladadosNoRegulados(registro.getDatosCobranzasGeneracionCargoEntrada().getInteresesTrasladadosNoRegulados());
		modelo.setCobranzaCargoInteresesBonificadosRegulados(registro.getDatosCobranzasGeneracionCargoEntrada().getInteresesBonificadosRegulados());
		modelo.setCobranzaCargoInteresesBonificadosNoRegulados(registro.getDatosCobranzasGeneracionCargoEntrada().getInteresesBonificadosNoRegulados());
		modelo.setCreditoCodigoCliente(registro.getDatosCreditoAplicadoCliente().getCodigoCliente());
		modelo.setCreditoMedioPagoCuenta(registro.getDatosCreditoAplicadoMedioPago().getCuenta());
		modelo.setCreditoMedioPagoTipoCredito(registro.getDatosCreditoAplicadoMedioPago().getTipoCredito());
		modelo.setCreditoMedioPagoTipoComprobante(registro.getDatosCreditoAplicadoNotaCredito().getTipoComprobante());
		modelo.setCreditoMedioPagoClaseComprobante(registro.getDatosCreditoAplicadoNotaCredito().getClaseComprobante());
		modelo.setCreditoMedioPagoSucursalComprobante(registro.getDatosCreditoAplicadoNotaCredito().getSucursalComprobante());
		modelo.setCreditoMedioPagoNumeroComprobante(registro.getDatosCreditoAplicadoNotaCredito().getNumeroComprobante());
		modelo.setCreditoMedioPagoNroReferenciaMic(registro.getDatosCreditoAplicadoNotaCredito().getNroReferenciaMic());
		modelo.setRemanenteTipoRemanente(registro.getDatosCreditoAplicadoRemanente().getTipoRemanente());
		modelo.setCreditoGralImporte(registro.getDatosCreditoAplicadoDatosGenerales().getImporte());
		modelo.setCreditoGralSaldo(registro.getDatosCreditoAplicadoDatosGenerales().getSaldo());
		modelo.setCreditoGralFechaAlta(registro.getDatosCreditoAplicadoDatosGenerales().getFechaAlta());
		modelo.setCreditoGralFechaEmision(registro.getDatosCreditoAplicadoDatosGenerales().getFechaEmision());
		modelo.setCreditoGralFechaVencimiento(registro.getDatosCreditoAplicadoDatosGenerales().getFechaVencimiento());
		modelo.setCreditoGralFechaUltimoMovimiento(registro.getDatosCreditoAplicadoDatosGenerales().getFechaUltimoMovimiento());
		modelo.setCreditoGralMarcaReclamo(registro.getDatosCreditoAplicadoDatosGenerales().getMarcaReclamo());
		modelo.setCreditoGralMarcaCyq(registro.getDatosCreditoAplicadoDatosGenerales().getMarcaCyq());
		modelo.setEstadoOrigen(registro.getDatosCreditoAplicadoDatosGenerales().getEstadoOrigen());
		modelo.setCreditoTagetikRazonSocialCliente(registro.getDatosCreditoAplicadoTagetik().getRazonSocialCliente());
		modelo.setCreditoTagetikTipoCliente(registro.getDatosCreditoAplicadoTagetik().getTipoCliente());
		modelo.setCreditoTagetikCuit(registro.getDatosCreditoAplicadoTagetik().getCuit());
		modelo.setCreditoTagetikCicloFacturacion(registro.getDatosCreditoAplicadoTagetik().getCicloFacturacion());
		modelo.setCreditoTagetikMarketingCustomerGroup(registro.getDatosCreditoAplicadoTagetik().getMarketingCustomerGroup());
		modelo.setCreditoTagetikTipoFactura(registro.getDatosCreditoAplicadoTagetik().getTipoFactura());
		modelo.setCreditoTagetikImporteOriginal(registro.getDatosCreditoAplicadoTagetik().getImporteOriginal());
		
		//Datos del credito aplicado: Dacota (foto de los datos previos a la imputacion)
		modelo.setCreditoDakotaFechaVencimientoMora(registro.getDatosCreditoAplicadoDacota().getFechaVencimientoMora());
		modelo.setCreditoDakotaIndicadorPeticionCorte(registro.getDatosCreditoAplicadoDacota().getIndicadorPeticionCorte());
		modelo.setCreditoDakotaCodigoTarifa(registro.getDatosCreditoAplicadoDacota().getCodigoTarifa());

		//Datos del acuerdo de intereses por mora / acuerdo del reintegro: datos generales (foto de los datos previos a la imputacion)
		modelo.setAcuerdoGralCodigoCliente(registro.getDatosAcuerdoGenerales().getCodigoCliente());
		modelo.setAcuerdoGralAcuerdoFacturacion(registro.getDatosAcuerdoGenerales().getAcuerdoFacturacion());
		modelo.setAcuerdoGralLinea(registro.getDatosAcuerdoGenerales().getLinea());
		modelo.setAcuerdoGralEstadoAcuerdoFacturacion(registro.getDatosAcuerdoGenerales().getEstadoAcuerdoFacturacion());
		modelo.setAcuerdoDatosClientecodigoCliente(registro.getDatosAcuerdosCliente().getCodigoCliente());
		modelo.setResultadoConsultaCredito(registro.getDatosRespuestaCredito().getResultadoConsulta());
		modelo.setCodigoErrorCredito(registro.getDatosRespuestaCredito().getCodigoError());
		modelo.setDescripcionErrorCredito(registro.getDatosRespuestaCredito().getDescripcionError());
		modelo.setResultadoConsultaReintegro(registro.getDatosRespuestaReintegor().getResultadoConsulta());
		modelo.setCodigoErrorReintegro(registro.getDatosRespuestaReintegor().getCodigoError());
		modelo.setDescripcionErrorReintegro(registro.getDatosRespuestaReintegor().getDescripcionError());
		
		// Datos del credito aplicado: Nota de credito (foto de los datos previos a la imputacion)
		modelo.setCreditoMedioPagoTipoComprobante(registro.getDatosCreditoAplicadoNotaCredito().getTipoComprobante());
		modelo.setCreditoMedioPagoClaseComprobante(registro.getDatosCreditoAplicadoNotaCredito().getClaseComprobante());
		modelo.setCreditoMedioPagoSucursalComprobante(registro.getDatosCreditoAplicadoNotaCredito().getSucursalComprobante());
		modelo.setCreditoMedioPagoNumeroComprobante(registro.getDatosCreditoAplicadoNotaCredito().getNumeroComprobante());
		modelo.setCreditoMedioPagoNroReferenciaMic(registro.getDatosCreditoAplicadoNotaCredito().getNroReferenciaMic());
		
		modelo.setIdCobranza(registro.getDatosCobranzaGenerales().getIdCobranza());
		modelo.setFechaValorCobranza(registro.getDatosCobranzaGenerales().getFechaValorCobranza());

		return modelo;
	}
	
	@Override
	public REG mapRegistroSalida(ShvMasRegistro modelo) throws NegocioExcepcion {
		MicOperacionMasivaRegistroSalida reg = new MicOperacionMasivaRegistroSalida();
		TipoArchivoOperacionMasivaEnum tipoInvocacion = null;
		
		// Parametros generales del registro de entrada
		reg.getParametrosGenerales().setTipoRegistro(TipoRegistroEnum.D);
		reg.getParametrosGenerales().setIdOperacionMasiva(modelo.getOperacionMasiva().getIdOperacionMasiva());
		reg.getParametrosGenerales().setIdTransaccion(1l); 
		reg.getParametrosGenerales().setUsuarioCobrador(Constantes.SHIVA_APLICACION.toUpperCase());
		reg.getParametrosGenerales().setSecuencialOrdenamiento(
			this.obtenerSecuencia(modelo.getOperacionMasiva().getIdOperacionMasiva()));
		reg.getParametrosGenerales().setIdOperacion(modelo.getIdOperacion());
		
		// Datos generales
		reg.setDatosGenerales(this.mapDatosGenerales(modelo));
		
		// Datos del medio de pago
		reg.setDatosMedioPago(this.mapMedioPago(modelo));
		if (modelo instanceof ShvMasRegistroAplicarDeuda) {
			tipoInvocacion = TipoArchivoOperacionMasivaEnum.DEUDA;
			// Datos del debito
			reg.setDatosDebitos(this.mapDebito(modelo));
		} else if (modelo instanceof ShvMasRegistroGanancias) {
			
			tipoInvocacion = TipoArchivoOperacionMasivaEnum.GNCIA;
			reg.setDatosGanancia(this.mapGanancia(modelo));
		
		} else if (modelo instanceof ShvMasRegistroDesistimiento) {
			tipoInvocacion = TipoArchivoOperacionMasivaEnum.DSIST;
			reg.setDatosDebitos(this.mapDebito(modelo));
			reg.setDatosDesistimiento(this.mapDesistimiento(modelo));
		
		} else if (modelo instanceof ShvMasRegistroReintegro) {
			tipoInvocacion = TipoArchivoOperacionMasivaEnum.REINT;
			reg.setDatosReintegro(this.mapReintengro(modelo));
			reg.setDatosCargoProximaFactura(this.mapProxoma(modelo, reg.getParametrosGenerales().getIdOperacion()));
		}
		reg.getParametrosGenerales().setTipoInvocacion(tipoInvocacion);
		return reg;
	}

	/*****************************************************************************
	* metodos privados
	*****************************************************************************/
	private MicOperacionMasivaCargoProximaFacturaSalida mapProxoma(ShvMasRegistro modelo, Long idOperacion) {
		MicOperacionMasivaCargoProximaFacturaSalida reg = new MicOperacionMasivaCargoProximaFacturaSalida();
		ShvMasRegistroReintegro derivada = (ShvMasRegistroReintegro) modelo;

		reg.setFechaValorCargo(derivada.getFechaCreacion());
		reg.setCodigoCliente(derivada.getClientesSiebel().getClienteDuenoAcuerdo());
		reg.setAcuerdoFacturacionDestino(derivada.getAcuerdoFacturacionDestino());
		reg.setLineaDestino(derivada.getLineaDestino());
		
		reg.setImporteRegulado(null);
		reg.setMonedaImporteRegulado(null);
		reg.setImporteNoRegulado(derivada.getImporte());
		reg.setMonedaImporteNoRegulado(MonedaEnum.PES);
		
		reg.setFechaHasta(null);
		reg.setCalcularFechaHasta(SiNoEnum.SI); 
 		
		if (Validaciones.isObjectNull(derivada.getReintegraConInteres())) {
			reg.setTratamientoIntereses(TratamientoInteresesEnum.SC);
		} else if (SiNoEnum.SI.equals(derivada.getReintegraConInteres())) {
			reg.setTratamientoIntereses(TratamientoInteresesEnum.TM);
		} else {
			reg.setTratamientoIntereses(TratamientoInteresesEnum.BM);
		}
		
		String leyendaFacturaCargo = "";
		String leyendaFacturaInteres = "";
		if (!Validaciones.isObjectNull(derivada.getNumeroReferenciaNC())) {
 			// Nota de credito
 			reg.setOrigenCargo(new Long(OrigenCargoEnum.TRASLADO_NC.codigo()));
 			
 			leyendaFacturaCargo = Propiedades.MENSAJES_PROPIEDADES.getString("cobros.leyendaExposicionFactura.cargo.TRASLADO_NC");
 			leyendaFacturaInteres = Propiedades.MENSAJES_PROPIEDADES.getString("cobros.leyendaExposicionFactura.intereses.TRASLADO_NC");
            
 			String numeroDocumento = Utilidad.getReferenciaNumeroDocumento(derivada.getClaseComprobante(),
 					derivada.getSucursalComprobante(), derivada.getNumeroComprobante());
 			
 			leyendaFacturaCargo = Utilidad.reemplazarMensajes(leyendaFacturaCargo, numeroDocumento, idOperacion.toString());
 			leyendaFacturaInteres = Utilidad.reemplazarMensajes(leyendaFacturaInteres, numeroDocumento, idOperacion.toString());
 			
 		} else {
 			reg.setOrigenCargo(new Long(OrigenCargoEnum.TRASLADO_REMANENTE.codigo()));
 			
 			leyendaFacturaCargo = Propiedades.MENSAJES_PROPIEDADES.getString("cobros.leyendaExposicionFactura.cargo.TRASLADO_REMANENTE");
 			leyendaFacturaInteres = Propiedades.MENSAJES_PROPIEDADES.getString("cobros.leyendaExposicionFactura.intereses.TRASLADO_REMANENTE");

 			leyendaFacturaCargo = Utilidad.reemplazarMensajes(leyendaFacturaCargo, Utilidad.rellenarCerosIzquierda(String.valueOf(derivada.getCuenta()),13) , 
 					String.valueOf(derivada.getTipoRemanente().getCodigo()), idOperacion.toString());
 			
 			leyendaFacturaInteres = Utilidad.reemplazarMensajes(leyendaFacturaInteres, Utilidad.rellenarCerosIzquierda(String.valueOf(derivada.getCuenta()),13) , 
 					String.valueOf(derivada.getTipoRemanente().getCodigo()), idOperacion.toString());
 			
		}
		
		reg.setLeyendaFacturaCargo(leyendaFacturaCargo);
		reg.setLeyendaFacturaInteres(leyendaFacturaInteres);
		
		return reg;
	}
	
	private MicOperacionMasivaReintegroSalida mapReintengro(ShvMasRegistro modelo) {
		MicOperacionMasivaReintegroSalida reg = new MicOperacionMasivaReintegroSalida();
		ShvMasRegistroReintegro derivada = (ShvMasRegistroReintegro) modelo;
		reg.setTramiteReintegro(derivada.getTramiteReintegro());
		reg.setFechaAltaTramiteReintegro(derivada.getFechaAltaTramiteReintegro());
		reg.setTipoReintegro(derivada.getTipoReintegro());
		return reg;
	}
	
	private MicOperacionMasivaDesistimientoSalida mapDesistimiento(ShvMasRegistro modelo) {
		MicOperacionMasivaDesistimientoSalida reg = new MicOperacionMasivaDesistimientoSalida();
		ShvMasRegistroDesistimiento derivada = (ShvMasRegistroDesistimiento) modelo;
		reg.setNumeroActaDesistimiento(derivada.getNumeroActaDesistimiento());
		reg.setFechaFirmaActaDesistimiento(derivada.getFechaActaDesistimiento());
		return reg;
	}
	
	private MicOperacionMasivaGananciaSalida mapGanancia(ShvMasRegistro modelo) {
		MicOperacionMasivaGananciaSalida reg = new MicOperacionMasivaGananciaSalida();
		ShvMasRegistroGanancias derivada = (ShvMasRegistroGanancias) modelo;
		reg.setFechaValorGanancias(derivada.getFechaCreacion());
		return reg;
	}

	private MicOperacionMasivaMedioPagoSalida mapMedioPago(ShvMasRegistro modelo) {
		MicOperacionMasivaMedioPagoSalida reg = new MicOperacionMasivaMedioPagoSalida();

		if (modelo instanceof ShvMasRegistroAplicarDeuda) {
			ShvMasRegistroAplicarDeuda modeloAplicarDeuda = (ShvMasRegistroAplicarDeuda) modelo;

			if (!Validaciones.isObjectNull(modeloAplicarDeuda.getClientesSiebel())) {
				reg.setClienteDuenoCredito(modeloAplicarDeuda.getClientesSiebel().getClienteDuenoCredito());
			}
			if (!Validaciones.isNullEmptyOrDash(modeloAplicarDeuda.getNumeroReferenciaNC())) {
				reg.setNumeroReferenciaNC(Long.parseLong(modeloAplicarDeuda.getNumeroReferenciaNC()));
				reg.setTipoMedioPago(TipoMedioPagoEnum.NC);	
			} else {
				reg.setTipoMedioPago(TipoMedioPagoEnum.RT);
				reg.setTipoRemanente(modeloAplicarDeuda.getTipoRemanente().getCodigo());
			}
			reg.setCuenta(modeloAplicarDeuda.getCuenta());
			reg.setCreditoMigrado(modeloAplicarDeuda.getCreditoMigrado());
		
		} else if (modelo instanceof ShvMasRegistroGanancias) {
			ShvMasRegistroGanancias modeloAplicarGanancias = (ShvMasRegistroGanancias) modelo;

			if (!Validaciones.isObjectNull(modeloAplicarGanancias.getClientesSiebel())) {
				reg.setClienteDuenoCredito(modeloAplicarGanancias.getClientesSiebel().getClienteDuenoCredito());
			}
			if (!Validaciones.isObjectNull(modeloAplicarGanancias.getNumeroReferenciaNC())) {
				reg.setNumeroReferenciaNC(modeloAplicarGanancias.getNumeroReferenciaNC());
				reg.setTipoMedioPago(TipoMedioPagoEnum.NC);
			} else {
				reg.setTipoMedioPago(TipoMedioPagoEnum.RT);
				reg.setTipoRemanente(modeloAplicarGanancias.getTipoRemanente().getCodigo());
			}
			reg.setCuenta(modeloAplicarGanancias.getCuentaOrigen());
			reg.setCreditoMigrado(modeloAplicarGanancias.getCreditoMigrado());
		
		} else if (modelo instanceof ShvMasRegistroReintegro) {
			ShvMasRegistroReintegro modeloReintegro = (ShvMasRegistroReintegro) modelo;

			if (!Validaciones.isObjectNull(modeloReintegro.getClientesSiebel())) {
				reg.setClienteDuenoCredito(modeloReintegro.getClientesSiebel().getClienteDuenoCredito());
			}
			if (!Validaciones.isObjectNull(modeloReintegro.getNumeroReferenciaNC())) {
				reg.setNumeroReferenciaNC(modeloReintegro.getNumeroReferenciaNC());
				reg.setTipoMedioPago(TipoMedioPagoEnum.NC);
				reg.setNumeroDocumento(Utilidad.getReferenciaNumeroDocumento(modeloReintegro.getCreditoMedioPagoClaseComprobante(), 
						modeloReintegro.getCreditoMedioPagoSucursalComprobante(), 
						modeloReintegro.getCreditoMedioPagoNumeroComprobante()));
			} else {
				reg.setTipoMedioPago(TipoMedioPagoEnum.RT);
				reg.setTipoRemanente(modeloReintegro.getTipoRemanente().getCodigo());
			}
			reg.setCuenta(modeloReintegro.getCuenta());
			reg.setCreditoMigrado(modeloReintegro.getCreditoMigrado());
		}
		return reg;
	}
	
	  

	/***
	 * 
	 * @param modelo
	 * @return
	 */
	private MicOperacionMasivaDebitoSalida mapDebito(ShvMasRegistro modelo) {
		MicOperacionMasivaDebitoSalida reg = new MicOperacionMasivaDebitoSalida();

		if (modelo instanceof ShvMasRegistroAplicarDeuda) {
			ShvMasRegistroAplicarDeuda modeloAplicarDeuda = (ShvMasRegistroAplicarDeuda) modelo;

			reg.setTipoOperacion(modeloAplicarDeuda.getTipoOperacion());

			if (!Validaciones.isObjectNull(modeloAplicarDeuda.getClientesSiebel())) {
				reg.setClienteDuenoDebito(modeloAplicarDeuda.getClientesSiebel().getClienteDuenoDebito());
				reg.setClienteDuenoAcuerdo(modeloAplicarDeuda.getClientesSiebel().getClienteDuenoAcuerdo());
			}

			reg.setNumeroReferenciaFactura(modeloAplicarDeuda.getNumeroReferenciaFactura());
			reg.setDestransferirTerceros(modeloAplicarDeuda.getDestransferirTerceros());
			reg.setDeudaMigrada(modeloAplicarDeuda.getDeudaMigrada());
			reg.setAcuerdoFacturacionDestino(modeloAplicarDeuda.getAcuerdoFacturacionDestino());
			reg.setAccionSobreIntereses(modeloAplicarDeuda.getAccionSobreIntereses());
			if (TratamientoInteresesEnum.BV.equals(modeloAplicarDeuda.getAccionSobreIntereses())) {
				reg.setPorcentajeBonificacionIntereses(modeloAplicarDeuda.getPorcentajeBonificacionIntereses());
				reg.setImporteBonificacionIntereses(modeloAplicarDeuda.getImporteBonificacionIntereses());
			}

		} else if (modelo instanceof ShvMasRegistroDesistimiento) {
			ShvMasRegistroDesistimiento modeloDesistimiento = (ShvMasRegistroDesistimiento) modelo;

			reg.setTipoOperacion(TipoPagoEnum.PAGO_TOTAL);

			if (!Validaciones.isObjectNull(modeloDesistimiento.getClientesSiebel())) {
				reg.setClienteDuenoDebito(modeloDesistimiento.getClientesSiebel().getClienteDuenoDebito());
				reg.setClienteDuenoAcuerdo(modeloDesistimiento.getClientesSiebel().getClienteDuenoAcuerdo());
			}
			reg.setNumeroReferenciaFactura(modeloDesistimiento.getNumeroReferenciaFactura());
			reg.setDeudaMigrada(modeloDesistimiento.getDeudaMigrada()); 
		}
		reg.setTipoDocumento(TipoDocumentoEnum.FACTURA_NOTA_DEBITO);
		return reg;
	}

	private MicOperacionMasivaDatosGralesSalida mapDatosGenerales(ShvMasRegistro modelo) {
		MicOperacionMasivaDatosGralesSalida reg = new MicOperacionMasivaDatosGralesSalida();
		reg.setImporte(modelo.getImporte());
		reg.setMoneda(MonedaEnum.PES);
		return reg;
	}
	
	/**
	 * u578936 (Max) El atributo se utiliza en el batch por lo que hay un solo hilo de ejecucion
	 * @param idOperacionMasiva
	 * @param operacionMasivaRegistrosCant
	 * @return
	 */
	private long obtenerSecuencia(long idOperacionMasiva) {
		Long cant =  operacionMasivaRegistrosCant.get(idOperacionMasiva);
		if (Validaciones.isObjectNull(cant)) {
			cant = 0l;
		}
		cant++;
		operacionMasivaRegistrosCant.put(idOperacionMasiva, cant);
		return cant;
	}
}

