package ar.com.telecom.shiva.base.registros.mapeos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.REG;
import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoConceptoTercerosEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoDUCEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaCyQEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaReclamoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoArchivoOperacionMasivaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClienteTagetikEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDUCEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoFacturaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRegistroEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.MapeadorREG;
import ar.com.telecom.shiva.base.registros.datos.MicOperacionMasivaCabeceraPie;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosAcuerdoClienteEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosAcuerdoGralesEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosClienteImputadoEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosCobranzasApropiacionDeudaEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosCobranzasGeneracionCargoEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosCobranzasGralesEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosCreditoAplicadoClienteEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosCreditoAplicadoDacotaEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosCreditoAplicadoGralesEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosCreditoAplicadoMedioPagoEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosCreditoAplicadoNotaCreditoEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosCreditoAplicadoRemanenteEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosCreditoAplicadoTagetikEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosDebitoImputadoDacotaEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosDebitoImputadoGralesEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosDebitoImputadoSaldoTercerosEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosDebitoImputadoTagetikEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaDatosRespuestaGeneralesEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.agrupador.MicOperacionMasivaParametrosEntrada;
import ar.com.telecom.shiva.base.registros.datos.entrada.enumeradores.MicOperacionMasivaCamposEntradaEnum;
import ar.com.telecom.shiva.base.registros.datos.entrada.enumeradores.MicOperacionMasivacAgrupacionCamposEntradaEnum;
import ar.com.telecom.shiva.base.registros.datos.salida.MicOperacionMasivaSalida;
import ar.com.telecom.shiva.base.registros.datos.salida.agrupador.MicOperacionMasivaRegistroSalida;
import ar.com.telecom.shiva.base.registros.util.RegistroUtilidad;
import ar.com.telecom.shiva.base.registros.util.definicion.CampoAEnviarREG;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.utils.singleton.ValidarAuxiliarSingleton;

public class MicOperacionMasivaMapeador extends MapeadorREG {

	private static final int CANT_CAMPOS_CABECERA = 3;
	
@Override
	public String serializar(REG reg) throws NegocioExcepcion {
		MicOperacionMasivaSalida datosAEnviar = (MicOperacionMasivaSalida) reg;
		StringBuffer mensaje = new StringBuffer("");
		
		List<CampoAEnviarREG> listaCamposCabecera = new ArrayList<CampoAEnviarREG>();
		listaCamposCabecera.add(new CampoAEnviarREG("tipoRegistro", datosAEnviar.getCabecera().getTipoRegistro().name()));
		listaCamposCabecera.add(new CampoAEnviarREG("cantidadRegistros", String.valueOf(datosAEnviar.getCabecera().getCantidadRegistros())));
		listaCamposCabecera.add(new CampoAEnviarREG("fechaProcesamiento", Utilidad.formatDateAAAAMMDD(datosAEnviar.getCabecera().getFechaProcesamiento())));
		mensaje.append(RegistroUtilidad.generarLinea(getDefaultFormatoRegistro(), listaCamposCabecera, "cabecera"));
		
		for (MicOperacionMasivaRegistroSalida registro: datosAEnviar.getRegistros()) {
			
			List<CampoAEnviarREG> listaCamposCuerpo = new ArrayList<CampoAEnviarREG>();
			
			listaCamposCuerpo.add(new CampoAEnviarREG("tipoRegistro", 
					registro.getParametrosGenerales().getTipoRegistro().name()));
			listaCamposCuerpo.add(new CampoAEnviarREG("idOperacionMasiva", 
					String.valueOf(registro.getParametrosGenerales().getIdOperacionMasiva())));
			listaCamposCuerpo.add(new CampoAEnviarREG("tipoInvocacion", 
					registro.getParametrosGenerales().getTipoInvocacion().getCodigo()));
			listaCamposCuerpo.add(new CampoAEnviarREG("idOperacion", 
					String.valueOf(registro.getParametrosGenerales().getIdOperacion())));
			listaCamposCuerpo.add(new CampoAEnviarREG("idTransaccion", 
					String.valueOf(registro.getParametrosGenerales().getIdTransaccion())));
			listaCamposCuerpo.add(new CampoAEnviarREG("usuarioCobrador", 
					registro.getParametrosGenerales().getUsuarioCobrador()));
			listaCamposCuerpo.add(new CampoAEnviarREG("secuencialOrdenamiento", 
					String.valueOf(registro.getParametrosGenerales().getSecuencialOrdenamiento())));
			
			String importeFormateado = 
					Utilidad.formatCurrencySacarPunto(
							Utilidad.formatCurrencyBDAgregandoDecimales(
									registro.getDatosGenerales().getImporte(), 2).toString());
			listaCamposCuerpo.add(new CampoAEnviarREG("importe", importeFormateado));
			listaCamposCuerpo.add(new CampoAEnviarREG("moneda", 
					registro.getDatosGenerales().getMoneda()!=null?
							registro.getDatosGenerales().getMoneda().name():""));
			

			listaCamposCuerpo.add(new CampoAEnviarREG("tipoOperacion", 
					registro.getDatosDebitos().getTipoOperacion()!=null?
							registro.getDatosDebitos().getTipoOperacion().getDescripcionCorta():""));
			listaCamposCuerpo.add(new CampoAEnviarREG("clienteDuenoDebito", 
					registro.getDatosDebitos().getClienteDuenoDebito()!=null?
							String.valueOf(registro.getDatosDebitos().getClienteDuenoDebito()):""));
			listaCamposCuerpo.add(new CampoAEnviarREG("tipoDocumento", 
					registro.getDatosDebitos().getTipoDocumento()!=null?
							registro.getDatosDebitos().getTipoDocumento().codigo():""));
			listaCamposCuerpo.add(new CampoAEnviarREG("numeroReferenciaFactura", 
					registro.getDatosDebitos().getNumeroReferenciaFactura()!=null?
							String.valueOf(registro.getDatosDebitos().getNumeroReferenciaFactura()):""));
			listaCamposCuerpo.add(new CampoAEnviarREG("destransferirTerceros",
					registro.getDatosDebitos().getDestransferirTerceros()!=null?
							registro.getDatosDebitos().getDestransferirTerceros().getDescripcionCorta():""));
			listaCamposCuerpo.add(new CampoAEnviarREG("deudaMigrada",
					registro.getDatosDebitos().getDeudaMigrada()!=null?
							registro.getDatosDebitos().getDeudaMigrada().getDescripcionCorta():""));
			listaCamposCuerpo.add(new CampoAEnviarREG("clienteDuenoAcuerdo", 
					registro.getDatosDebitos().getClienteDuenoAcuerdo()!=null?
							String.valueOf(registro.getDatosDebitos().getClienteDuenoAcuerdo()):""));
			listaCamposCuerpo.add(new CampoAEnviarREG("acuerdoFacturacionDestino",
					registro.getDatosDebitos().getAcuerdoFacturacionDestino()!=null?
							String.valueOf(registro.getDatosDebitos().getAcuerdoFacturacionDestino()):""));

			String porcentajeBonificacionInteresesFormateado = Utilidad.EMPTY_STRING;
			if (
				TratamientoInteresesEnum.BV.equals(registro.getDatosDebitos().getAccionSobreIntereses()) &&
				TipoArchivoOperacionMasivaEnum.DEUDA.equals(registro.getParametrosGenerales().getTipoInvocacion())
			) {
				if (registro.getDatosDebitos().getPorcentajeBonificacionIntereses() != null) {
					if (registro.getDatosDebitos().getPorcentajeBonificacionIntereses() < Constantes.CIEN_PORCIENTO) {
						registro.getDatosDebitos().setAccionSobreIntereses(TratamientoInteresesEnum.TC);
						porcentajeBonificacionInteresesFormateado = registro.getDatosDebitos().getPorcentajeBonificacionIntereses().toString();
					}
				}
			}
	
			listaCamposCuerpo.add(new CampoAEnviarREG("accionSobreIntereses",
					registro.getDatosDebitos().getAccionSobreIntereses()!=null?
							registro.getDatosDebitos().getAccionSobreIntereses().name():""));
			
			importeFormateado = 
					Utilidad.formatCurrencySacarPunto(
							Utilidad.formatCurrencyBDAgregandoDecimales(
									registro.getDatosDebitos().getImporteBonificacionIntereses(), 2).toString());
			listaCamposCuerpo.add(new CampoAEnviarREG("importeBonificacionIntereses", importeFormateado));

			
			listaCamposCuerpo.add(new CampoAEnviarREG("porcentajeBonificacionIntereses", porcentajeBonificacionInteresesFormateado));

			
			listaCamposCuerpo.add(new CampoAEnviarREG("clienteDuenoCredito",
					registro.getDatosMedioPago().getClienteDuenoCredito()!=null?
							String.valueOf(registro.getDatosMedioPago().getClienteDuenoCredito()):""));
			listaCamposCuerpo.add(new CampoAEnviarREG("tipoMedioPago", 
					registro.getDatosMedioPago().getTipoMedioPago()!=null?
							registro.getDatosMedioPago().getTipoMedioPago().name():""));
			listaCamposCuerpo.add(new CampoAEnviarREG("cuenta", 
					registro.getDatosMedioPago().getCuenta()!=null?
							String.valueOf(registro.getDatosMedioPago().getCuenta()):""));
							
			listaCamposCuerpo.add(new CampoAEnviarREG("tipoRemanente",
					registro.getDatosMedioPago().getTipoRemanente()!=null?
							String.valueOf(registro.getDatosMedioPago().getTipoRemanente()):""));
			listaCamposCuerpo.add(new CampoAEnviarREG("numeroReferenciaNC", 
					registro.getDatosMedioPago().getNumeroReferenciaNC()!=null?
						String.valueOf(registro.getDatosMedioPago().getNumeroReferenciaNC()):""));
			listaCamposCuerpo.add(new CampoAEnviarREG("creditoMigrado",
					registro.getDatosMedioPago().getCreditoMigrado()!=null?
							registro.getDatosMedioPago().getCreditoMigrado().getDescripcionCorta():""));		
			
			listaCamposCuerpo.add(new CampoAEnviarREG("fechaValorGanancias", 
					Utilidad.formatDateAAAAMMDD(registro.getDatosGanancia().getFechaValorGanancias())));		
		
			listaCamposCuerpo.add(new CampoAEnviarREG("numeroActaDesistimiento", 
					registro.getDatosDesistimiento().getNumeroActaDesistimiento()!=null?
							registro.getDatosDesistimiento().getNumeroActaDesistimiento():""));
			listaCamposCuerpo.add(new CampoAEnviarREG("fechaFirmaActaDesistimiento", 
					Utilidad.formatDateAAAAMMDD(registro.getDatosDesistimiento().getFechaFirmaActaDesistimiento())));

			listaCamposCuerpo.add(new CampoAEnviarREG("tramiteReintegro",
					registro.getDatosReintegro().getTramiteReintegro()!=null?
							String.valueOf(registro.getDatosReintegro().getTramiteReintegro()):""));
			listaCamposCuerpo.add(new CampoAEnviarREG("fechaAltaTramiteReintegro", 
					Utilidad.formatDateAAAAMMDD(registro.getDatosReintegro().getFechaAltaTramiteReintegro())));
			listaCamposCuerpo.add(new CampoAEnviarREG("tipoReintegro",
					registro.getDatosReintegro().getTipoReintegro()!=null?
							registro.getDatosReintegro().getTipoReintegro().name():""));
		
			listaCamposCuerpo.add(new CampoAEnviarREG("fechaValorCargo", 
					Utilidad.formatDateAAAAMMDD(registro.getDatosCargoProximaFactura().getFechaValorCargo())));
			listaCamposCuerpo.add(new CampoAEnviarREG("codigoCliente",
					registro.getDatosCargoProximaFactura().getCodigoCliente()!=null?
							String.valueOf(registro.getDatosCargoProximaFactura().getCodigoCliente()):""));
			listaCamposCuerpo.add(new CampoAEnviarREG("acuerdoFacturacionDestino1", 
					registro.getDatosCargoProximaFactura().getAcuerdoFacturacionDestino()!=null?
							String.valueOf(registro.getDatosCargoProximaFactura().getAcuerdoFacturacionDestino()):""));
			listaCamposCuerpo.add(new CampoAEnviarREG("lineaDestino", 
					registro.getDatosCargoProximaFactura().getLineaDestino()!=null?
							String.valueOf(registro.getDatosCargoProximaFactura().getLineaDestino()):""));
			
			importeFormateado = 
					Utilidad.formatCurrencySacarPunto(
							Utilidad.formatCurrencyBDAgregandoDecimales(
									registro.getDatosCargoProximaFactura().getImporteRegulado(), 2).toString());
			listaCamposCuerpo.add(new CampoAEnviarREG("importeRegulado", importeFormateado));
			listaCamposCuerpo.add(new CampoAEnviarREG("monedaImporteRegulado",
					registro.getDatosCargoProximaFactura().getMonedaImporteRegulado()!=null?
							registro.getDatosCargoProximaFactura().getMonedaImporteRegulado().name():""));

			importeFormateado = 
					Utilidad.formatCurrencySacarPunto(
							Utilidad.formatCurrencyBDAgregandoDecimales(
									registro.getDatosCargoProximaFactura().getImporteNoRegulado(), 2).toString());
			listaCamposCuerpo.add(new CampoAEnviarREG("importeNoRegulado", importeFormateado));
			listaCamposCuerpo.add(new CampoAEnviarREG("monedaImporteNoRegulado", 
					registro.getDatosCargoProximaFactura().getMonedaImporteNoRegulado()!=null?
							registro.getDatosCargoProximaFactura().getMonedaImporteNoRegulado().name():""));
			
			listaCamposCuerpo.add(new CampoAEnviarREG("fechaHasta", 
					Utilidad.formatDateAAAAMMDD(registro.getDatosCargoProximaFactura().getFechaHasta())));
			listaCamposCuerpo.add(new CampoAEnviarREG("calcularFechaHasta",
					registro.getDatosCargoProximaFactura().getCalcularFechaHasta()!=null?
							registro.getDatosCargoProximaFactura().getCalcularFechaHasta().getDescripcionCorta():""));
			listaCamposCuerpo.add(new CampoAEnviarREG("tratamientoIntereses",
					registro.getDatosCargoProximaFactura().getTratamientoIntereses()!=null?
							registro.getDatosCargoProximaFactura().getTratamientoIntereses().getCodigoMicCargo():""));
			listaCamposCuerpo.add(new CampoAEnviarREG("origenCargo",
					registro.getDatosCargoProximaFactura().getOrigenCargo()!=null?
							String.valueOf(registro.getDatosCargoProximaFactura().getOrigenCargo()):""));
			listaCamposCuerpo.add(new CampoAEnviarREG("leyendaFacturaCargo",
					registro.getDatosCargoProximaFactura().getLeyendaFacturaCargo()));
			listaCamposCuerpo.add(new CampoAEnviarREG("leyendaFacturaInteres", 
					registro.getDatosCargoProximaFactura().getLeyendaFacturaInteres()));
		
			mensaje.append(RegistroUtilidad.generarLinea(getDefaultFormatoRegistro(), listaCamposCuerpo, "cuerpo"));
		}
		
		List<CampoAEnviarREG> listaCamposPie = new ArrayList<CampoAEnviarREG>();
		listaCamposPie.add(new CampoAEnviarREG("tipoRegistro", datosAEnviar.getPie().getTipoRegistro().name()));
		listaCamposPie.add(new CampoAEnviarREG("cantidadRegistros", String.valueOf(datosAEnviar.getPie().getCantidadRegistros())));
		listaCamposPie.add(new CampoAEnviarREG("fechaProcesamiento", Utilidad.formatDateAAAAMMDD(datosAEnviar.getPie().getFechaProcesamiento())));
		mensaje.append(RegistroUtilidad.generarLinea(getDefaultFormatoRegistro(), listaCamposPie, "pie"));
		
		return mensaje.toString();
	}

	@Override
	public REG deserializar(REG reg, String[] campos) throws NegocioExcepcion {
		
		if (reg instanceof MicOperacionMasivaCabeceraPie) {
			return deserializarCabeceraPie((MicOperacionMasivaCabeceraPie) reg, campos);
		}
		if (reg instanceof MicOperacionMasivaParametrosEntrada) {
			return deserializarParametrosEntrada((MicOperacionMasivaParametrosEntrada) reg, campos);
		}
		if (reg instanceof MicOperacionMasivaDatosCobranzasGralesEntrada) {
			return deserializarDatosCobranzasGralesEntrada((MicOperacionMasivaDatosCobranzasGralesEntrada) reg, campos);
		}
		if (reg instanceof MicOperacionMasivaDatosCobranzasApropiacionDeudaEntrada) {
			return deserializarDatosCobranzasApropiacionDeudaEntrada((MicOperacionMasivaDatosCobranzasApropiacionDeudaEntrada) reg, campos);
		}
		if (reg instanceof MicOperacionMasivaDatosCobranzasGeneracionCargoEntrada) {
			return deserializarDatosCobranzasGeneracionCargoEntrada((MicOperacionMasivaDatosCobranzasGeneracionCargoEntrada) reg, campos);
		}
		if (reg instanceof MicOperacionMasivaDatosClienteImputadoEntrada) {
			return deserializarDatosClienteImputadoEntrada((MicOperacionMasivaDatosClienteImputadoEntrada) reg, campos);
		}
		if (reg instanceof MicOperacionMasivaDatosDebitoImputadoGralesEntrada) {
			return deserializarDatosDebitoImputadoGralesEntrada((MicOperacionMasivaDatosDebitoImputadoGralesEntrada) reg, campos);
		}
		if (reg instanceof MicOperacionMasivaDatosDebitoImputadoTagetikEntrada) {
			return deserializarDatosDebitoImputadoTagetikEntrada((MicOperacionMasivaDatosDebitoImputadoTagetikEntrada) reg, campos);
		}
		if (reg instanceof MicOperacionMasivaDatosDebitoImputadoDacotaEntrada) {
			return deserializarDatosDebitoImputadoDacotaEntrada((MicOperacionMasivaDatosDebitoImputadoDacotaEntrada) reg, campos);
		}
		if (reg instanceof MicOperacionMasivaDatosDebitoImputadoSaldoTercerosEntrada) {
			return deserializarDatosDebitoImputadoSaldoTercerosEntrada((MicOperacionMasivaDatosDebitoImputadoSaldoTercerosEntrada) reg, campos);
		}
		/******************************************************************************************/
		if (reg instanceof MicOperacionMasivaDatosCreditoAplicadoClienteEntrada) {
			return deserializarDatosCreditoAplicadoClienteEntrada((MicOperacionMasivaDatosCreditoAplicadoClienteEntrada) reg, campos);
		}
		if (reg instanceof MicOperacionMasivaDatosCreditoAplicadoMedioPagoEntrada) {
			return deserializarDatosCreditoAplicadoMedioPagoEntrada((MicOperacionMasivaDatosCreditoAplicadoMedioPagoEntrada) reg, campos);
		}
		if (reg instanceof MicOperacionMasivaDatosCreditoAplicadoNotaCreditoEntrada) {
			return deserializarDatosCreditoAplicadoNotaCreditoEntrada((MicOperacionMasivaDatosCreditoAplicadoNotaCreditoEntrada) reg, campos);
		}
		if (reg instanceof MicOperacionMasivaDatosCreditoAplicadoRemanenteEntrada) {
			return deserializarDatosCreditoAplicadoRemanenteEntrada((MicOperacionMasivaDatosCreditoAplicadoRemanenteEntrada) reg, campos);
		}
		if (reg instanceof MicOperacionMasivaDatosCreditoAplicadoGralesEntrada) {
			return deserializarDatosCreditoAplicadoGralesEntrada((MicOperacionMasivaDatosCreditoAplicadoGralesEntrada) reg, campos);
		}
		if (reg instanceof MicOperacionMasivaDatosCreditoAplicadoTagetikEntrada) {
			return deserializarDatosCreditoAplicadoTagetikEntrada((MicOperacionMasivaDatosCreditoAplicadoTagetikEntrada) reg, campos);
		}
		if (reg instanceof MicOperacionMasivaDatosCreditoAplicadoDacotaEntrada) {
			return deserializarDatosCreditoAplicadoDacotaEntrada((MicOperacionMasivaDatosCreditoAplicadoDacotaEntrada) reg, campos);
		}
		if (reg instanceof MicOperacionMasivaDatosAcuerdoGralesEntrada) {
			return deserializarDatosAcuerdoGralesEntrada((MicOperacionMasivaDatosAcuerdoGralesEntrada) reg, campos);
		}
		if (reg instanceof MicOperacionMasivaDatosAcuerdoClienteEntrada) {
			return deserializarDatosAcuerdoClienteEntrada((MicOperacionMasivaDatosAcuerdoClienteEntrada) reg, campos);
		}
		
		return null;
	}

	

	/**
	 * u578936 - Maxi
	   Comentado para --> "Procesar la respuesta del archivo de entrada generado por MIC como respuesta al procesamiento solicitado"
	 * @param reg
	 * @param campos
	 * @return
	 * @throws NegocioExcepcion
	 */
	private MicOperacionMasivaCabeceraPie deserializarCabeceraPie(MicOperacionMasivaCabeceraPie reg, String campos[]) throws NegocioExcepcion {
		
		if (campos.length != CANT_CAMPOS_CABECERA) {
			String mensajeError = Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorCantidad"), 
				String.valueOf(CANT_CAMPOS_CABECERA )
			);
			ValidarAuxiliarSingleton.getInstance().setLineHeader(mensajeError);
		}
		// Tipo de Registro
		if (campos[MicOperacionMasivaCamposEntradaEnum.HEAD_TIPO_REGISTRO.posicion()].length() <= MicOperacionMasivaCamposEntradaEnum.HEAD_TIPO_REGISTRO.longitud()) {
			reg.setTipoRegistro(
				TipoRegistroEnum.getEnumByName(
					campos[MicOperacionMasivaCamposEntradaEnum.HEAD_TIPO_REGISTRO.posicion()]
			));
		} else {
			String mensajeError = Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.mic.errorCantidad"), 
				String.valueOf(MicOperacionMasivaCamposEntradaEnum.HEAD_TIPO_REGISTRO.longitud())
			);
			ValidarAuxiliarSingleton.getInstance().setLineHeader(mensajeError);
		}
		try {
			reg.setCantidadRegistros(
				(Long) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.HEAD_CANT_REGISTRO)
			);
		} catch (NegocioExcepcion e) {
			ValidarAuxiliarSingleton.getInstance().setLineHeader(e.getMessage());
		}
		
		try {
			reg.setFechaProcesamiento(
				(Date) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.HEAD_FECHA_PROCESAMIENRO)
			);
		} catch (NegocioExcepcion e) {
			ValidarAuxiliarSingleton.getInstance().setLineHeader(e.getMessage());
		}
		return reg;
	}

	/**
	 * 
	 * @param reg
	 * @param campos
	 * @return
	 * @throws NegocioExcepcion
	 */
	private MicOperacionMasivaParametrosEntrada deserializarParametrosEntrada(MicOperacionMasivaParametrosEntrada reg, String campos[]) throws NegocioExcepcion {
		reg.setTipoRegistro(
			TipoRegistroEnum.getEnumByName(
				campos[MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_TIPO_REGISTRO.posicion()]
		));
		reg.setIdOperacionMasiva(
			(Long) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_ID_OPERACION_MASIVA_SHIVA)
		);
		reg.setTipoInvocacion(
			TipoArchivoOperacionMasivaEnum.getEnumByCodigo(
				campos[MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_TIPO_INVOCACION.posicion()]
		));
		reg.setIdOperacion(
			(Long) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_ID_OPERACION_SHIVA)
		);
		reg.setIdTransaccion(
			(Long) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.PARAMETROS_GENERALES_ID_SECUENCIA_TRANSACCION)
		);
		return reg;
	}
	
	/**
	 * 
	 * @param reg
	 * @param campos
	 * @return
	 * @throws NegocioExcepcion
	 */
	private MicOperacionMasivaDatosCobranzasGralesEntrada deserializarDatosCobranzasGralesEntrada(MicOperacionMasivaDatosCobranzasGralesEntrada reg, String campos[]) throws NegocioExcepcion {
		reg.setIdCobranza(
			(Long) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.COBRANZA_GENERALES_ID_COBRANZA)
		);
		reg.setFechaValorCobranza(
			(Date) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.COBRANZA_GENERALES_FECHA_VALOR_COBRANZA)
		);
		return reg;
	}
	
	/**
	 * 
	 * @param reg
	 * @param campos
	 * @return
	 * @throws NegocioExcepcion
	 */
	private MicOperacionMasivaDatosCobranzasApropiacionDeudaEntrada deserializarDatosCobranzasApropiacionDeudaEntrada(MicOperacionMasivaDatosCobranzasApropiacionDeudaEntrada reg, String campos[]) throws NegocioExcepcion {
		reg.setInteresesTrasladadosRegulados(
			(BigDecimal) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.COBRANZA_APROPIACION_D_INTERESES_TRASLADADOS_REGULADOS)
		);
		reg.setInteresesTrasladadosNoRegulados(
			(BigDecimal) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.COBRANZA_APROPIACION_D_INTERESES_TRASLADADOS_NO_REGULADOS)
		);
		reg.setInteresesBonificadosRegulados(
			(BigDecimal) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.COBRANZA_APROPIACION_D_INTERESES_BONIFICADOS_REGULADOS)
		);
		reg.setInteresesBonificadosNoRegulados(
			(BigDecimal) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.COBRANZA_APROPIACION_D_INTERESES_BONIFICADOS_NO_REGULADOS)
		);
		return reg;
	}
	
	/**
	 * 
	 * @param reg
	 * @param campos
	 * @return
	 * @throws NegocioExcepcion
	 */
	private MicOperacionMasivaDatosCobranzasGeneracionCargoEntrada deserializarDatosCobranzasGeneracionCargoEntrada(MicOperacionMasivaDatosCobranzasGeneracionCargoEntrada reg, String campos[]) throws NegocioExcepcion {
		reg.setInteresesTrasladadosRegulados(
			(BigDecimal) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.COBRANZA_GENERACION_CARGO_INTERESES_TRASLADADOS_REGULADOS)
		);
		reg.setInteresesTrasladadosNoRegulados(
			(BigDecimal) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.COBRANZA_GENERACION_CARGO_INTERESES_TRASLADADOS_NO_REGULADOS)
		);
		reg.setInteresesBonificadosRegulados(
			(BigDecimal) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.COBRANZA_GENERACION_CARGO_INTERESES_BONIFICADOS_REGULADOS)
		);
		reg.setInteresesBonificadosNoRegulados(
			(BigDecimal) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.COBRANZA_GENERACION_CARGO_INTERESES_BONIFICADOS_NO_REGULADOS)
		);
		return reg;
	}

	/**
	 * 
	 * @param reg
	 * @param campos
	 * @return
	 * @throws NegocioExcepcion
	 */
	private MicOperacionMasivaDatosClienteImputadoEntrada deserializarDatosClienteImputadoEntrada(MicOperacionMasivaDatosClienteImputadoEntrada reg, String campos[]) throws NegocioExcepcion {
		reg.setCodigoCliente(
			(Long) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_CODIGO_CLIENTE)
		);
		return reg;
	}

	/**
	 * 
	 * @param reg
	 * @param campos
	 * @return
	 * @throws NegocioExcepcion
	 */
	private MicOperacionMasivaDatosDebitoImputadoGralesEntrada deserializarDatosDebitoImputadoGralesEntrada(MicOperacionMasivaDatosDebitoImputadoGralesEntrada reg, String campos[]) throws NegocioExcepcion {
		
		reg.setCuenta(
			(Long) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CUENTA)
		);
		reg.setTipoDocumento(
			this.obtenerValorTipoComprobante(
				campos,
				MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_TIPO_DOCUMENTO
			)
		);
		reg.setTipoDuc(
			this.obtenerValorTipoDuc(
				campos,
				MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_TIPO_DUC,
				MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_TIPO_DUC
			)
		);
		
		//	Descripcion Tipo de DUC	Alfanumérico	20
		reg.setEstadoDuc(
			this.obtenerValorEstadoDuc(
				campos,
				MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_DUC,
				MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_DUC
			)	
		);
		//	Descripción Estado del DUC	Alfanumérico	18
		reg.setAcuerdo(
			(String) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_ACUERDO)
		);
		reg.setClaseComprobante(
			this.obtenerClaseComprobanteEnum(
				campos,
				MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CLASE_COMPROBANTE
			)	
		);
		
		reg.setSucursalComprobante(
			(String) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_SUCURSAL_COMPROBANTE)
		);
		reg.setNumeroComprobante(
			(String) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_NUMERO_COMPROBANTE)
		);
		reg.setNumeroReferenciaMic(
			(Long) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_NUMERO_REFERENCIA_MIC)
		);
		reg.setFechaVencimiento(
			(Date) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_FECHA_VENCIMIENTO)
		);
		reg.setImportePrimerVencimiento(
			(BigDecimal) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_PRIMER_VENCIMIENTO)
		);
		reg.setImporteSegundoVencimiento(
			(BigDecimal) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_SEGUNDO_VENCIMIENTO)
		);
		reg.setSaldoPrimerVencimiento(
			(BigDecimal) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_SALDO_PRIMER_VENCIMIENTO)
		);
		reg.setEstadoAcuerdoFacturacion(
			this.obtenerValorEstadoAcuerdoFacturacion(
				campos,
				MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_ACUERDO_FACTURACION,
				MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_ACUERDO_FACTURACION
		));
			//	Codigo Estado acuerdo Facturación	Numérico	2
			//	Descripcion Estado acuerdo Factuación	Alfanumérico	10
		//TODO Ver si no es el enum EstadoConceptoTercero
		reg.setEstadoConceptoTerceros(
			this.obtenerValorEstadoConceptoTerceros(
				campos,
				MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_ESTADO_CONCEPTOS_TERCEROS
		));
		reg.setPosibilidadDestransferencia(
			this.obtenerValorSiNo(
				campos, 
				MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_POSIBILIDAD_DESTRANSFERENCIA
		));
		reg.setImporteTercerosTransferibles(
			(BigDecimal) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_TERCEROS_TRANSFERIDOS)
		);
		reg.setImportePrimerVencimientoConTerceros(
			(BigDecimal) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_PRIMER_VENCIMIENTO_TERCEROS)
		);
		reg.setImporteSegundoVencimientoConTerceros(
			(BigDecimal) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_IMPORTE_SEGUNDO_VENCIMIENTO_TERCEROS)
		);
		reg.setCodigoEstadoFactura(
			this.obtenerValorEstadoFactura(
				campos,
				MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_FACTURA,
				MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_FACTURA
		));
		//	Descripcion Estado Factura	Alfanumérico	20
		reg.setMarcaReclamo(
			this.obtenerValorMarcaReclamo(
				campos,
				MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_MARCA_RECLAMO,
				MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_MARCA_RECLAMO
		));
		//	Codigo Marca Reclamo	Alfanumérico	1
		//	Descripcion Marca Reclamo	Alfanumérico	10
		reg.setMarcaCyq(
			this.obtenerValorMarcaCyq(
				campos,
				MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CODIGO_MARCA_CYQ,
				MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_DESCRIPCION_MARCA_CYQ
		));
		//	Codigo Marca C&Q	Alfanumérico	1
		//	Descripcion Marca C&Q	Alfanumérico	8
		reg.setFechaEmision(
			(Date) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_FECHA_EMISION)
		);
		reg.setNumeroConvenio(
			(Long) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_NUMERO_CONVENIO)
		);
		reg.setCuota(
			(Long) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_CUOTA)
		);
		reg.setFechaUltimoPagoParcial(
			(Date) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_FECHA_ULTIMO_PAGO_PARCIAL)
		);
		reg.setFechaPuestaCobro(
			(Date) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.DEBITO_IMPUTADO_GRAL_FECHA_PUESTA_AL_COBRO)
		);
		return reg;
	}

	/**
	 * 
	 * @param reg
	 * @param campos
	 * @return
	 * @throws NegocioExcepcion
	 */
	private MicOperacionMasivaDatosDebitoImputadoTagetikEntrada deserializarDatosDebitoImputadoTagetikEntrada(MicOperacionMasivaDatosDebitoImputadoTagetikEntrada reg, String campos[]) throws NegocioExcepcion {
	
		reg.setRazonSocialCliente(
			(String) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.DEBITO_TAGETIK_RAZON_SOCIAL_CLIENTE)
		);
		reg.setTipoCliente(
			this.obtenerValorTipoClienteTagetik(
				campos,
				MicOperacionMasivaCamposEntradaEnum.DEBITO_TAGETIK_TIPO_CLIENTE
			)
		);
		//	Tipo de Cliente	Alfanumérico	2
		reg.setCuit(
			(String) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.DEBITO_TAGETIK_CUIT)
		);
		reg.setCicloFacturacion(
			(Integer) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.DEBITO_TAGETIK_CICLO_FACTURACION)
		);
		
		reg.setMarketingCustomerGroup(
			(String) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.DEBITO_TAGETIK_MARKETING_CUSTOMER_GROUP)
		);
		reg.setTipoFactura(
			this.obtenerValorTipoFactura(
				campos,
				MicOperacionMasivaCamposEntradaEnum.DEBITO_TAGETIK_TIPO_FACTURA,
				MicOperacionMasivaCamposEntradaEnum.DEBITO_TAGETIK_DESCRIPCION_TIPO_FACTURA
		));
		
		return reg;
	}
	
	/**
	 * 
	 * @param reg
	 * @param campos
	 * @return
	 * @throws NegocioExcepcion
	 */
	private MicOperacionMasivaDatosDebitoImputadoDacotaEntrada deserializarDatosDebitoImputadoDacotaEntrada(MicOperacionMasivaDatosDebitoImputadoDacotaEntrada reg, String campos[]) throws NegocioExcepcion {
		
		reg.setFechaVencimientoMora(
			(Date) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.DEBITO_DAKOTA_FECHA_VENCIMIENTO_MORA)
		);
		reg.setIndicadorPeticionCorte(
			(String) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.DEBITO_DAKOTA_INDICADOR_PETICION_CORTE)
		);
		reg.setCodigoTarifa(
			(String) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.DEBITO_DAKOTA_CODIGO_TARIFA)
		);
		return reg;
	}
	
	/**
	 * 
	 * @param reg
	 * @param campos
	 * @return
	 * @throws NegocioExcepcion
	 */
	private MicOperacionMasivaDatosDebitoImputadoSaldoTercerosEntrada deserializarDatosDebitoImputadoSaldoTercerosEntrada(MicOperacionMasivaDatosDebitoImputadoSaldoTercerosEntrada reg, String campos[]) throws NegocioExcepcion {
		
		reg.setSaldoTerceroFinanciableNOTransferible(
			(BigDecimal) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.SALDO_TERCERO_FINANCIABLE_NO_TRANSFERIBLE)
		);
		reg.setSaldoTerceroFinanciableTransferible(
			(BigDecimal) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.SALDO_TERCERO_FINACIABLE_TRANSFERIBLE)
		);
		reg.setSaldoTerceroNOFinanciableTransferible(
			(BigDecimal) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.SALDO_TERCERO_NO_FINANCIABLE_TRANSAFERIBLE)
		);
		return reg;
	}
	
	

	//***********************************************************************************************************************/
	
	/**
	 * 
	 * @param reg
	 * @param campos
	 * @return
	 * @throws NegocioExcepcion
	 */
	private MicOperacionMasivaDatosCreditoAplicadoClienteEntrada deserializarDatosCreditoAplicadoClienteEntrada(MicOperacionMasivaDatosCreditoAplicadoClienteEntrada reg, String campos[]) throws NegocioExcepcion {
		reg.setCodigoCliente(
			(Long) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.CODIGO_CLIENTE_CREDITO_APLICADO)
		);
		return reg;
	}
	
	
	/**
	 * 
	 * @param reg
	 * @param campos
	 * @return
	 * @throws NegocioExcepcion
	 */
	private MicOperacionMasivaDatosCreditoAplicadoMedioPagoEntrada deserializarDatosCreditoAplicadoMedioPagoEntrada(MicOperacionMasivaDatosCreditoAplicadoMedioPagoEntrada reg, String campos[]) throws NegocioExcepcion {
		reg.setCuenta(
			(Long) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.MEDIO_DE_PAGO_CUENTA)
		);
		reg.setTipoCredito(
				this.obtenerValorTipoComprobante(
						campos,
						MicOperacionMasivaCamposEntradaEnum.MEDIO_DE_PAGO_TIPO_CREDITO
					)
		);
		
		return reg;
	}
	
	/**
	 * 
	 * @param reg
	 * @param campos
	 * @return
	 * @throws NegocioExcepcion
	 */
	private MicOperacionMasivaDatosCreditoAplicadoNotaCreditoEntrada deserializarDatosCreditoAplicadoNotaCreditoEntrada(MicOperacionMasivaDatosCreditoAplicadoNotaCreditoEntrada reg, String campos[]) throws NegocioExcepcion { 
		
		reg.setTipoComprobante(
			this.obtenerValorTipoComprobante(
				campos,
				MicOperacionMasivaCamposEntradaEnum.MEDIO_DE_PAGO_TIPO_CREDITO
		));
		reg.setClaseComprobante(
			this.obtenerClaseComprobanteEnum(
				campos,
				MicOperacionMasivaCamposEntradaEnum.NC_CLASE_COMPROBANTE
			)	
		);
			
		reg.setSucursalComprobante(
			(String) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.NC_SUCURSAL_COMPROBANTE)
		);
		reg.setNumeroComprobante(
			(String) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.NC_NUMERO_COMPROBANTE)
		);
		reg.setNroReferenciaMic(
			(Long) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.NC_NUMERO_REFERENCIA_MIC)
		);
		return reg;
	}
	
	/**
	 * 
	 * @param reg
	 * @param campos
	 * @return
	 * @throws NegocioExcepcion
	 */
	private MicOperacionMasivaDatosCreditoAplicadoRemanenteEntrada deserializarDatosCreditoAplicadoRemanenteEntrada(MicOperacionMasivaDatosCreditoAplicadoRemanenteEntrada reg, String campos[]) throws NegocioExcepcion { 
		reg.setTipoRemanente(
			this.obtenerValorTipoRemanenteEnum(
				campos, 
				MicOperacionMasivaCamposEntradaEnum.REMANENTE_CODIGO_TIPO,
				MicOperacionMasivaCamposEntradaEnum.REMANENTE_DESCRIPCION_TIPO
		));
		return reg;
	}

	/**
	 * 
	 * @param reg
	 * @param campos
	 * @return
	 * @throws NegocioExcepcion
	 */
	private MicOperacionMasivaDatosCreditoAplicadoGralesEntrada deserializarDatosCreditoAplicadoGralesEntrada(MicOperacionMasivaDatosCreditoAplicadoGralesEntrada reg, String campos[]) throws NegocioExcepcion {
		
		reg.setImporte(
			(BigDecimal) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_IMPORTE)
		);
		reg.setSaldo(
			(BigDecimal) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_SALDO)
		);
		reg.setFechaAlta(
			(Date) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_FECHA_ALTA)
		);
		reg.setFechaEmision(
			(Date) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_FECHA_EMISION)
		);
		reg.setFechaUltimoMovimiento(
			(Date) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_FECHA_ULTIMO_MOVIMIENTO_COBRO)
		);
		
	
	
		//	Descripcion Estado Factura	Alfanumérico	20
		reg.setMarcaReclamo(
			this.obtenerValorMarcaReclamo(
				campos,
				MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_CODIGO_MARCA_RECLAMO,
				MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_DESCRIPCION_MARCA_RECLAMO
		));
		//	Codigo Marca Reclamo	Alfanumérico	1
		//	Descripcion Marca Reclamo	Alfanumérico	10
		reg.setMarcaCyq(
			this.obtenerValorMarcaCyq(
				campos,
				MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_CODIGO_MARCA_CYQ,
				MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_DESCRIPCION_MARCA_CYQ
		));
		reg.setEstadoOrigen(
			this.obtenerValorEstadoFactura(
				campos,
				MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_CODIGO_ESTADO_CREDITO,
				MicOperacionMasivaCamposEntradaEnum.CREDITO_APLICADO_DESCRIPCION_ESTADO_CREDITO
			));
		return reg;
	}
	
	/**
	 * 
	 * @param reg
	 * @param campos
	 * @return
	 * @throws NegocioExcepcion
	 */
	private MicOperacionMasivaDatosCreditoAplicadoTagetikEntrada deserializarDatosCreditoAplicadoTagetikEntrada(MicOperacionMasivaDatosCreditoAplicadoTagetikEntrada reg, String campos[]) throws NegocioExcepcion {
	
		reg.setRazonSocialCliente(
			(String) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_RAZON_SOCIAL_CLIENTE)
		);
		reg.setTipoCliente(
			(String) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_TIPO_CLIENTE)
		);
		reg.setCuit(
			(String) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_CUIT)
		);
		reg.setCicloFacturacion(
			(Integer) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_CICLO_FACTURACION)
		);
		reg.setMarketingCustomerGroup(
			(String) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_MARKETING_CUSTOMER_GROUP)
		);
		
		reg.setTipoFactura(
			this.obtenerValorTipoFactura(
				campos,
				MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_TIPO_FACTURA,
				MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_DESCRIPCION_TIPO_FACTURA
		));
		reg.setImporteOriginal(
			(BigDecimal) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.CREDITO_TAGETIK_IMPORTE_ORIGINAL)
		);
		return reg;
	}
	
	/**
	 * 
	 * @param reg
	 * @param campos
	 * @return
	 * @throws NegocioExcepcion
	 */
	private MicOperacionMasivaDatosCreditoAplicadoDacotaEntrada deserializarDatosCreditoAplicadoDacotaEntrada(MicOperacionMasivaDatosCreditoAplicadoDacotaEntrada reg, String campos[]) throws NegocioExcepcion {
		reg.setFechaVencimientoMora(
			(Date) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.CREDITO_DAKOTA_FECHA_VENCIMIENTO_MORA)
		);
		reg.setIndicadorPeticionCorte(
			(String) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.CREDITO_DAKOTA_INDICADOR_PETICION_CORTE)
		);
		reg.setCodigoTarifa(
			(String) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.CREDITO_DAKOTA_CODIGO_TARIFA)
		);
		return reg;
	}
	
	/**
	 * 
	 * @param reg
	 * @param campos
	 * @return
	 * @throws NegocioExcepcion
	 */
	private MicOperacionMasivaDatosAcuerdoGralesEntrada deserializarDatosAcuerdoGralesEntrada(MicOperacionMasivaDatosAcuerdoGralesEntrada reg, String campos[]) throws NegocioExcepcion {
		reg.setCodigoCliente(
			(Long) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.ACUERDO_GNERALES_CODIGO_CLIENTE_MORA)
		);
		reg.setAcuerdoFacturacion(
			(Long) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.ACUERDO_GNERALES_ACUERDO_FACTURACION_INTERESES_REINTEGRO)
		);
		reg.setLinea(
			(Long) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.ACUERDO_GNERALES_NUMERO_LINEA)
		);
		reg.setEstadoAcuerdoFacturacion(
			this.obtenerValorEstadoAcuerdoFacturacion(
				campos,
				MicOperacionMasivaCamposEntradaEnum.ACUERDO_GNERALES_CODIGO_ESTADO_ACUERDO,
				MicOperacionMasivaCamposEntradaEnum.ACUERDO_GNERALES_DESCRIPCION_ESTADO_ACUERDO
		));
		return reg;
	}
	
	/**
	 * 
	 * @param reg
	 * @param campos
	 * @return
	 * @throws NegocioExcepcion
	 */
	private MicOperacionMasivaDatosAcuerdoClienteEntrada deserializarDatosAcuerdoClienteEntrada(MicOperacionMasivaDatosAcuerdoClienteEntrada reg, String campos[]) throws NegocioExcepcion {
		reg.setCodigoCliente(
			(Long) this.obtenerValor(campos, MicOperacionMasivaCamposEntradaEnum.ACUERDO_CLIENTE_CODIGO_CLIENTE)
		);
		return reg;
	}
	
	 
	/**
	 * Para datosRespuestaDebit, datosRespuestaCredito y datosRespuestaReintegro
	 */
	public MicOperacionMasivaDatosRespuestaGeneralesEntrada deserializar(
			MicOperacionMasivaDatosRespuestaGeneralesEntrada reg,
			MicOperacionMasivacAgrupacionCamposEntradaEnum agrupador,
			String campos[]
	) throws NegocioExcepcion {
		TipoResultadoEnum resultadoConsulta = null; 
		resultadoConsulta = TipoResultadoEnum.getEnumByName( // El sistema usa MIC pero la respuesta esta como CALIPSO
			campos[MicOperacionMasivaCamposEntradaEnum.getEnumByName(agrupador.name() + "_RESULTADO_CONSULTA").posicion()].trim()
		);
//		if (Validaciones.isObjectNull(resultadoConsulta)) {
//			String desc = "Error en el campo " + agrupador.name() + "_RESULTADO_CONSULTA";
//			Traza.error(getClass(), desc);
//			throw new NegocioExcepcion(desc);
//		}
		reg.setResultadoConsulta(resultadoConsulta);
		reg.setCodigoError(
			(String) this.obtenerValor(
				campos,
				MicOperacionMasivaCamposEntradaEnum.getEnumByName(agrupador.name() + "_CODIGO_ERROR"
		)));
		reg.setDescripcionError(
			(String) this.obtenerValor(
				campos,
				MicOperacionMasivaCamposEntradaEnum.getEnumByName(agrupador.name() + "_DESCRIPCION_ERROR"
		)));
		
		return reg;
	}
	
	
//	/***********************************************************************************************************************************/
	private TipoRemanenteEnum obtenerValorTipoRemanenteEnum(
			String campos[], 
			MicOperacionMasivaCamposEntradaEnum campo,
			MicOperacionMasivaCamposEntradaEnum campoDesc	
	) throws NegocioExcepcion {
		if (
			!Validaciones.isObjectNull(campos[campo.posicion()]) &&
			!Validaciones.isEmptyString(campos[campo.posicion()])
		) {
			Integer u = (Integer) this.obtenerValor(
				campos[campo.posicion()],
				campo.getNombreColumna(),
				campo.tipo()
			);
			return TipoRemanenteEnum.getEnumByCodigo(Long.valueOf(u));
		}
		return null;
	}

	private Object obtenerValor(String campos[], MicOperacionMasivaCamposEntradaEnum campo) throws NegocioExcepcion {
		Object valor = null;
		try {
			if (MicOperacionMasivaCamposEntradaEnum.HEAD_FECHA_PROCESAMIENRO.equals(campo)) {
				if (campos[campo.posicion()].trim().length() == 8) {
					if (Date.class.equals(campo.clase())) {
						valor = Utilidad.deserializeAndFormatDate(
							campos[campo.posicion()].trim()
						);
					}
				} else {
					String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
						campo.getNombreColumna(), String.valueOf(campo.longitud()));
					throw new NegocioExcepcion(mensajeError);
				}
			} else {
				if (campos[campo.posicion()].length() <= campo.longitud()) {
					if (String.class.equals(campo.clase())) {
						valor = campos[campo.posicion()];
					} else if (Long.class.equals(campo.clase())) {
						if (campo.tipo() == Constantes.TIPO_ALFANUMERICO) {
							if (!Validaciones.isEmptyAlfanumericoSerializado(campos[campo.posicion()])) {
								valor = Long.valueOf(
									campos[campo.posicion()]
								);
							}
						} else {
							valor = Long.valueOf(
								campos[campo.posicion()]
							);
						}
					} else if (Date.class.equals(campo.clase())) {
						valor = Utilidad.deserializeAndFormatDate(
							campos[campo.posicion()]
						);
					} else if (BigDecimal.class.equals(campo.clase())) {
						valor = Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(
							campos[campo.posicion()].trim()),
							2
						);
					} else if (Integer.class.equals(campo.clase())) {
						valor = Integer.valueOf(campos[campo.posicion()]);
					}
				} else {
					String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
						campo.getNombreColumna(), String.valueOf(campo.longitud()));
					throw new NegocioExcepcion(mensajeError);
				}
			}
		} catch (NegocioExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e); 
		} catch (Exception e) {
			String desc = "Error en el campo " + campo.getNombreColumna();
			Traza.error(getClass(), desc, e);
			throw new NegocioExcepcion(desc,e);
		}
		return valor;
	}
	private Object obtenerValor(String campo, String descripcion, int tipo) throws NegocioExcepcion {
		Object valor = null;
		try {
			switch (tipo) {
			case Constantes.TIPO_ALFANUMERICO:
				valor = campo;
				break;
			case Constantes.TIPO_NUMERICO:
				valor = Integer.valueOf(campo.trim());
				break;
			default:
				break;
			}
		} catch (Exception e) {
			String desc = "Error en el campo " + descripcion;
			Traza.error(getClass(), desc, e);
			throw new NegocioExcepcion(desc,e);
		}
		return valor;
	}
	
	private TipoComprobanteEnum obtenerValorTipoComprobante(
		String campos[], 
		MicOperacionMasivaCamposEntradaEnum campo
	) throws NegocioExcepcion {

		String valor = (String) this.obtenerValor(
			campos[campo.posicion()],
			"",
			campo.tipo()
		);
		TipoComprobanteEnum tipoComprobanteEnum = TipoComprobanteEnum.getEnumByValor(
			valor.trim()
		);
		return tipoComprobanteEnum;
	}
	
	private TipoDUCEnum obtenerValorTipoDuc(
		String campos[], 
		MicOperacionMasivaCamposEntradaEnum campo,
		MicOperacionMasivaCamposEntradaEnum campoDescripcion
	) throws NegocioExcepcion {
		int codigo = (Integer) this.obtenerValor(
			campos[campo.posicion()],
			campo.getNombreColumna(),
			campo.tipo()	
		);
		return TipoDUCEnum.getEnumByCodigo(Utilidad.rellenarCerosIzquierda(String.valueOf(codigo), 2));
	}
	
	private EstadoDUCEnum obtenerValorEstadoDuc(
			String campos[], 
			MicOperacionMasivaCamposEntradaEnum campo,
			MicOperacionMasivaCamposEntradaEnum campoDescripcion
	) throws NegocioExcepcion {
		String codigo = (String) this.obtenerValor(
			campos[campo.posicion()],
			campo.getNombreColumna(),
			campo.tipo()	
		);
		return EstadoDUCEnum.getEnumByCodigo(codigo.trim());
	}
	
	private TipoClaseComprobanteEnum obtenerClaseComprobanteEnum(
			String campos[], 
			MicOperacionMasivaCamposEntradaEnum campo
	) throws NegocioExcepcion {
		String codigo = (String) this.obtenerValor(
			campos[campo.posicion()],
			campo.getNombreColumna(),
			campo.tipo()	
		);
		return TipoClaseComprobanteEnum.getEnumByName(codigo.trim());
	}
	
	private EstadoAcuerdoFacturacionEnum obtenerValorEstadoAcuerdoFacturacion(
			String campos[], 
			MicOperacionMasivaCamposEntradaEnum campo,
			MicOperacionMasivaCamposEntradaEnum campoDescripcion
	) throws NegocioExcepcion {
		if (Constantes.TIPO_ALFANUMERICO == campo.tipo()) {
			String codigo = (String) this.obtenerValor(
					campos[campo.posicion()],
					campo.getNombreColumna(),
					campo.tipo()	
				);
			return EstadoAcuerdoFacturacionEnum.getEnumByCodigo(codigo.trim());
		}
		Integer codigo = (Integer) this.obtenerValor(
				campos[campo.posicion()],
				campo.getNombreColumna(),
				campo.tipo()	
			);
		return EstadoAcuerdoFacturacionEnum.getEnumByCodigo(Utilidad.rellenarCerosIzquierda(codigo.toString(), 2));
	
		
	}
	
	private EstadoConceptoTercerosEnum obtenerValorEstadoConceptoTerceros(
		String campos[], 
		MicOperacionMasivaCamposEntradaEnum campo
	) throws NegocioExcepcion {
		String codigo = (String) this.obtenerValor(
			campos[campo.posicion()],
			campo.getNombreColumna(),
			campo.tipo()	
		);
		return EstadoConceptoTercerosEnum.getEnumByCodigoMic(codigo.trim());
	}
	
	private SiNoEnum obtenerValorSiNo(
		String campos[], 
		MicOperacionMasivaCamposEntradaEnum campo
	) throws NegocioExcepcion {
		String codigo = (String) this.obtenerValor(
			campos[campo.posicion()],
			campo.getNombreColumna(),
			campo.tipo()	
		);
		return SiNoEnum.getEnumByDescripcionCorta(codigo.trim());
	}
	
	private EstadoOrigenEnum obtenerValorEstadoFactura(
			String campos[], 
			MicOperacionMasivaCamposEntradaEnum campo,
			MicOperacionMasivaCamposEntradaEnum campoDescripcion
	) throws NegocioExcepcion {
		String codigo = (String) this.obtenerValor(
			campos[campo.posicion()],
			campo.getNombreColumna(),
			campo.tipo()	
		);
		return EstadoOrigenEnum.getEnumByCodigo(codigo.trim());
	}
	
	private MarcaReclamoEnum obtenerValorMarcaReclamo(
			String campos[], 
			MicOperacionMasivaCamposEntradaEnum campo,
			MicOperacionMasivaCamposEntradaEnum campoDescripcion
	) throws NegocioExcepcion {
		String codigo = (String) this.obtenerValor(
			campos[campo.posicion()],
			campo.getNombreColumna(),
			campo.tipo()	
		);
		return MarcaReclamoEnum.getEnumByCodigo(codigo.trim());
	}
	
	private MarcaCyQEnum obtenerValorMarcaCyq(
			String campos[], 
			MicOperacionMasivaCamposEntradaEnum campo,
			MicOperacionMasivaCamposEntradaEnum campoDescripcion
	) throws NegocioExcepcion {
		String codigo = (String) this.obtenerValor(
			campos[campo.posicion()],
			campo.getNombreColumna(),
			campo.tipo()	
		);
		return MarcaCyQEnum.getEnumByCodigo(codigo.trim());
	}
	
	private TipoClienteTagetikEnum obtenerValorTipoClienteTagetik(
		String campos[], 
		MicOperacionMasivaCamposEntradaEnum campo
	) throws NegocioExcepcion {
		String codigo = (String) this.obtenerValor(
			campos[campo.posicion()],
			campo.getNombreColumna(),
			campo.tipo()
		);
		return TipoClienteTagetikEnum.getEnumByCodigo(codigo.trim());
	}
	
	private TipoFacturaEnum obtenerValorTipoFactura(
			String campos[], 
			MicOperacionMasivaCamposEntradaEnum campo,
			MicOperacionMasivaCamposEntradaEnum campoDescripcion
	) throws NegocioExcepcion {
		
		int codigo = (Integer) this.obtenerValor(
			campos[campo.posicion()],
			campo.getNombreColumna(),
			campo.tipo()	
		);
		return TipoFacturaEnum.getEnumByCodigo(codigo);
	}

}
