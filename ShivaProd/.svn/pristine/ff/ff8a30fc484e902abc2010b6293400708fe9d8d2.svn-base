package ar.com.telecom.shiva.test.modulos;

import java.util.Arrays;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.EstadoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaCyQEnum;
import ar.com.telecom.shiva.base.enumeradores.MarcaReclamoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoArchivoOperacionMasivaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoFacturaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRemanenteEnum;
import ar.com.telecom.shiva.base.registros.datos.entrada.enumeradores.MicOperacionMasivaCamposEntradaEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;

public class OperacionMasivaDummy {

	public OperacionMasivaDummy() {
	}

	private int camposGanancia[] = {1, 2, 3, 4, 5, 6, 7, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 
			74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,105,106,107};
	
	
	
	
	
	
	
	/**
	 * 
	 * @return
	 */
	private String crearGanancia() {
		MicOperacionMasivaCamposEntradaEnum campos[] = MicOperacionMasivaCamposEntradaEnum.values();
		List  lista = Arrays.asList(camposGanancia);
		
		StringBuffer str = new StringBuffer();
		str.append(this.datosGenerales("333", "333", TipoArchivoOperacionMasivaEnum.GNCIA));
		
		int i = 0;
		for (MicOperacionMasivaCamposEntradaEnum campo: campos) {
			if (lista.contains(campo.posicion()+i)) {
				
				
//				datos(campo, null, null, null, "9032", null, "99999",
//						int comprobante,
//						boolean marcaReclamoVacia,
//						SiNoEnum posibilidadDestraferencia, true);
			} else {
				if (campo.tipo() == Constantes.TIPO_ALFANUMERICO) {
					str.append(Utilidad.rellenarEspaciosDerecha("", campo.longitud())); str.append("|");
				} else {
					str.append(Utilidad.rellenarCerosIzquierda("", campo.longitud())); str.append("|");
				}
			}
			
		}
		return "";
	}
	
	
	/**
	 * Cabecera/Pie 
	 * @param key
	 * @param valor
	 * @return
	 */
	public String datosCabeceraPie(MicOperacionMasivaCamposEntradaEnum key, String valor) {
		String value = Utilidad.EMPTY_STRING;
		switch (key) {
			case HEAD_TIPO_REGISTRO:
				break;	
			case HEAD_CANT_REGISTRO:
				break;	
			case HEAD_FECHA_PROCESAMIENRO:
				break;
			default:
				break;
		}
		return value;
	}

	/**
	 * Parametros generales del registro 
	 * @param key
	 * @param idOperacionMasiva
	 * @param idOperacionCobro
	 * @param tipo
	 * @return
	 */
	public String datosGenerales(String idOperacionMasiva,
			String idOperacionCobro,
			TipoArchivoOperacionMasivaEnum tipo) 
	{
		StringBuffer value = new StringBuffer(Utilidad.EMPTY_STRING);
		value.append("D|");
		value.append(Utilidad.rellenarCerosIzquierda(idOperacionMasiva, 10)+"|");
		value.append(tipo.getCodigo()+"|");
		value.append(Utilidad.rellenarCerosIzquierda(idOperacionCobro, 7)+"|");
		value.append("00001|");
		
		return value.toString();
	}
	
	/**
	 * Cuerpo Ganancias
	 * @param key
	 * @param tipo
	 * @param tipoComprobante
	 * @param estadoConceptoTercerosDeuda
	 * @param clienteDebito
	 * @param clienteCredito
	 * @param clienteAcuerdo
	 * @param numRefMicFac
	 * @param comprobante
	 * @param marcaReclamoVacia
	 * @param posibilidadDestraferencia
	 * @return
	 */
	public String datos(
		MicOperacionMasivaCamposEntradaEnum key,
		TipoComprobanteEnum tipoComprobante,
		SiNoEnum estadoConceptoTercerosDeuda,
		String clienteDebito,
		String clienteCredito,
		String clienteAcuerdo,
		String numRefMicFac,
		int comprobante,
		boolean marcaReclamoVacia,
		SiNoEnum posibilidadDestraferencia,
		boolean esNotaCredito) 
	{

		String value = Utilidad.EMPTY_STRING;
		switch (key) {
			// Datos de cobranza generales
			case COBRANZA_GENERALES_ID_COBRANZA:
				value = Utilidad.rellenarCerosIzquierda("71", 12);
				break;	
			case COBRANZA_GENERALES_FECHA_VALOR_COBRANZA:
				value = "20160220";
				break;

			// Datos de cobranza apropiacion de deuda
			case COBRANZA_APROPIACION_D_INTERESES_TRASLADADOS_REGULADOS:
				value = Utilidad.rellenarCerosIzquierda("100", 10);
				break;
			case COBRANZA_APROPIACION_D_INTERESES_TRASLADADOS_NO_REGULADOS:
				value = Utilidad.rellenarCerosIzquierda("1000", 10);
				break;
			case COBRANZA_APROPIACION_D_INTERESES_BONIFICADOS_REGULADOS:
				value = Utilidad.rellenarCerosIzquierda("10000", 10);
				break;
			case COBRANZA_APROPIACION_D_INTERESES_BONIFICADOS_NO_REGULADOS:
				value = Utilidad.rellenarCerosIzquierda("100000", 10);
				break;

			// Datos de cobranza generacion de cargo
			case COBRANZA_GENERACION_CARGO_INTERESES_TRASLADADOS_REGULADOS:
				value = Utilidad.rellenarCerosIzquierda("10", 10);
				break;
			case COBRANZA_GENERACION_CARGO_INTERESES_TRASLADADOS_NO_REGULADOS:
				value = Utilidad.rellenarCerosIzquierda("100", 10);
				break;
			case COBRANZA_GENERACION_CARGO_INTERESES_BONIFICADOS_REGULADOS:
				value = Utilidad.rellenarCerosIzquierda("1000", 10);
				break;
			case COBRANZA_GENERACION_CARGO_INTERESES_BONIFICADOS_NO_REGULADOS:
				value = Utilidad.rellenarCerosIzquierda("10000", 10);
				break;
	
			// Datos del debito imputado: cliente
			case DEBITO_IMPUTADO_CODIGO_CLIENTE:
				value = Utilidad.rellenarCerosIzquierda(clienteDebito, 10);
				break;
		
			// Datos del debito imputado: datos generales (foto de los datos previos a la imputacion)
			case DEBITO_IMPUTADO_GRAL_CUENTA:
				value = "001";
				break;
			case DEBITO_IMPUTADO_GRAL_TIPO_DOCUMENTO:
					value = tipoComprobante.name();
				break;
			case DEBITO_IMPUTADO_GRAL_CODIGO_TIPO_DUC:
				if (TipoComprobanteEnum.DUC.equals(tipoComprobante)) {
					value = "02";
				} else {
					value = Utilidad.rellenarCerosIzquierda("", 2);
				}
				break;
			case DEBITO_IMPUTADO_GRAL_DESCRIPCION_TIPO_DUC:
				if (TipoComprobanteEnum.DUC.equals(tipoComprobante)) {
					value = Utilidad.rellenarEspaciosDerecha("SERV. TEMPORARIOS", 20);
				} else {
					value = Utilidad.rellenarEspaciosDerecha("", 20);
				}
				break;
			case DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_DUC:
				if (TipoComprobanteEnum.DUC.equals(tipoComprobante)) {
					value = "G";
				} else {
					value = Utilidad.rellenarEspaciosDerecha("", 1);
				}
				break;
			
			case DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_DUC:
				if (TipoComprobanteEnum.DUC.equals(tipoComprobante)) {
					value = Utilidad.rellenarEspaciosDerecha("GENERADO", 18);
				} else {
					value = Utilidad.rellenarEspaciosDerecha("", 18);
				}
				break;
			case DEBITO_IMPUTADO_GRAL_ACUERDO:
				value = Utilidad.addStartingZeros("1234567890", 10);
				break;
			case DEBITO_IMPUTADO_GRAL_CLASE_COMPROBANTE:
				if (!TipoComprobanteEnum.DUC.equals(tipoComprobante)) {
					value = "A";
				} else {
					value = Utilidad.rellenarEspaciosDerecha("", 1);
				}
				break;
			case DEBITO_IMPUTADO_GRAL_SUCURSAL_COMPROBANTE:
				if (!TipoComprobanteEnum.DUC.equals(tipoComprobante)) {
					value = "0111";
				} else {
					value = Utilidad.rellenarEspaciosDerecha("", 4);
				}
				break;
			case DEBITO_IMPUTADO_GRAL_NUMERO_COMPROBANTE:
				if (!TipoComprobanteEnum.DUC.equals(tipoComprobante)) {
					value = Utilidad.rellenarEspaciosDerecha(""+comprobante, 8);
				} else {
					value = Utilidad.rellenarEspaciosDerecha("", 8);
				}
				break;
			
			case DEBITO_IMPUTADO_GRAL_NUMERO_REFERENCIA_MIC:
				if (!Validaciones.isEmptyString(numRefMicFac)) {
					value = Utilidad.rellenarEspaciosDerecha(numRefMicFac, 15);
				} else {
					value = Utilidad.rellenarEspaciosDerecha("", 15);
				}
				break;
			case DEBITO_IMPUTADO_GRAL_FECHA_VENCIMIENTO:
				value = "20160221";
				break;
			case DEBITO_IMPUTADO_GRAL_IMPORTE_PRIMER_VENCIMIENTO:
				switch (tipoComprobante) {
				case DUC:
					value = Utilidad.rellenarCerosIzquierda("100", 10);	
					break;
				case FAC:
					value = Utilidad.rellenarCerosIzquierda("2000", 10);
					break;
				default:
					value = Utilidad.rellenarCerosIzquierda("", 10);
					break;
				}
				break;
			case DEBITO_IMPUTADO_GRAL_IMPORTE_SEGUNDO_VENCIMIENTO:
				switch (tipoComprobante) {
				case DUC:
					value = Utilidad.rellenarCerosIzquierda("100", 10);	
					break;
				case FAC:
					value = Utilidad.rellenarCerosIzquierda("2001", 10);
					break;
				default:
					value = Utilidad.rellenarCerosIzquierda("", 10);
					break;
				}
				break;
			case DEBITO_IMPUTADO_GRAL_SALDO_PRIMER_VENCIMIENTO:
				switch (tipoComprobante) {
				case DUC:
					value = Utilidad.rellenarCerosIzquierda("100", 10);	
					break;
				case FAC:
					value = Utilidad.rellenarCerosIzquierda("2000", 10);
					break;
				default:
					value = Utilidad.rellenarCerosIzquierda("", 10);
					break;
				}
				break;
			case DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_ACUERDO_FACTURACION:
				value ="00";
				break;
			case DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_ACUERDO_FACTURACION:
				value = Utilidad.rellenarEspaciosDerecha("POTENCIAL", 10);
				break;
			case DEBITO_IMPUTADO_GRAL_ESTADO_CONCEPTOS_TERCEROS:
				if (SiNoEnum.SI.equals(estadoConceptoTercerosDeuda)) {
					value = "S";
				} else {
					value ="N";
				}
				break;
			case DEBITO_IMPUTADO_GRAL_POSIBILIDAD_DESTRANSFERENCIA:
				if (SiNoEnum.SI.equals(posibilidadDestraferencia)) {
					value = "S";
				} else {
					value ="N";
				}
				break;
			case DEBITO_IMPUTADO_GRAL_IMPORTE_TERCEROS_TRANSFERIDOS:
				if (SiNoEnum.SI.equals(estadoConceptoTercerosDeuda)) {
					value = Utilidad.rellenarCerosIzquierda("", 10);
				} else {
					value = Utilidad.rellenarCerosIzquierda("100", 10);	
				}
				break;
			case DEBITO_IMPUTADO_GRAL_IMPORTE_PRIMER_VENCIMIENTO_TERCEROS:
				if (SiNoEnum.SI.equals(estadoConceptoTercerosDeuda)) {
					value = Utilidad.rellenarCerosIzquierda("", 10);
				} else {
					value = Utilidad.rellenarCerosIzquierda("100", 10);	
				}
				break;
			case DEBITO_IMPUTADO_GRAL_IMPORTE_SEGUNDO_VENCIMIENTO_TERCEROS:
				if (SiNoEnum.SI.equals(estadoConceptoTercerosDeuda)) {
					value = Utilidad.rellenarCerosIzquierda("", 10);
				} else {
					value = Utilidad.rellenarCerosIzquierda("100", 10);	
				}
				break;
			case DEBITO_IMPUTADO_GRAL_CODIGO_ESTADO_FACTURA:
				value = "01";
				break;
			case DEBITO_IMPUTADO_GRAL_DESCRIPCION_ESTADO_FACTURA:
				value = Utilidad.rellenarEspaciosDerecha("ENV. AL CLIENTE", 20);
				break;
			case DEBITO_IMPUTADO_GRAL_CODIGO_MARCA_RECLAMO:
				if (marcaReclamoVacia) {
					value = " ";
				} else {
					value = MarcaReclamoEnum.ANULADO.codigo();
				}
				break;
			case DEBITO_IMPUTADO_GRAL_DESCRIPCION_MARCA_RECLAMO:
				if (marcaReclamoVacia) {
					value = Utilidad.rellenarEspaciosDerecha("", 10);
				} else {
					value = Utilidad.rellenarEspaciosDerecha(MarcaReclamoEnum.ANULADO.descripcion(), 10);
				}
				break;
			case DEBITO_IMPUTADO_GRAL_CODIGO_MARCA_CYQ:
				value = MarcaCyQEnum.Q.codigo();
				break;
			case DEBITO_IMPUTADO_GRAL_DESCRIPCION_MARCA_CYQ:
				value = Utilidad.rellenarEspaciosDerecha(MarcaCyQEnum.Q.descripcion(), 8);
				break;
			case DEBITO_IMPUTADO_GRAL_FECHA_EMISION:
				value = "20160222";
				break;
			case DEBITO_IMPUTADO_GRAL_NUMERO_CONVENIO:
				value = "101";
				break;
			case DEBITO_IMPUTADO_GRAL_CUOTA:
				value = "111";
				break;
			case DEBITO_IMPUTADO_GRAL_FECHA_ULTIMO_PAGO_PARCIAL:
				value = "20160223";
				break;
			case DEBITO_IMPUTADO_GRAL_FECHA_PUESTA_AL_COBRO:
				value = "20160224";
				break;
			
			// Datos del debito imputado: tagetik  (foto de los datos p:revios a la imputacion)
			case DEBITO_TAGETIK_RAZON_SOCIAL_CLIENTE:
				value = Utilidad.rellenarEspaciosDerecha("Razón social cliente", 30);
				break;
			case DEBITO_TAGETIK_TIPO_CLIENTE:
				value = "16";
				break;
			case DEBITO_TAGETIK_CUIT:
				value = "30709711640";
				break;
			case DEBITO_TAGETIK_CICLO_FACTURACION:
				value = "01";
				break;
			case DEBITO_TAGETIK_MARKETING_CUSTOMER_GROUP:
				value = "A001";
				break;
			case DEBITO_TAGETIK_TIPO_FACTURA:
				value = ""+TipoFacturaEnum.FACT_FLEXCAB.codigo();
				break;
			case DEBITO_TAGETIK_DESCRIPCION_TIPO_FACTURA:
				value = TipoFacturaEnum.FACT_FLEXCAB.descripcion();
				break;	
			
			// Datos del debito imputado: Dacota  (foto de los datos previos a la imputacion)
			case DEBITO_DAKOTA_FECHA_VENCIMIENTO_MORA:
				value = "20160225";
				break;
			case DEBITO_DAKOTA_INDICADOR_PETICION_CORTE:
				value = "1";
				break;
			case DEBITO_DAKOTA_CODIGO_TARIFA:
				value = "01234";
				break;
			
			// Datos del debito imputado: Saldos de terceros  (foto de los datos previos a la imputacion)
			case SALDO_TERCERO_FINANCIABLE_NO_TRANSFERIBLE:
				value = Utilidad.rellenarCerosIzquierda("100", 10);
				break;
			case SALDO_TERCERO_FINACIABLE_TRANSFERIBLE:
				value = Utilidad.rellenarCerosIzquierda("1000", 10);
				break;
			case SALDO_TERCERO_NO_FINANCIABLE_TRANSAFERIBLE:
				value = Utilidad.rellenarCerosIzquierda("10000", 10);
				break;
				
			// Datos del credito aplicado: cliente
			case CODIGO_CLIENTE_CREDITO_APLICADO:
				if (!Validaciones.isEmptyString(clienteCredito)) {
					value = Utilidad.rellenarCerosIzquierda(clienteCredito, 10);
				} else {
					value = Utilidad.rellenarCerosIzquierda("", 10);
				}
				break;
			
			// Datos del credito aplicado: medio de pago (foto de los datos previos a la imputacion)
			case MEDIO_DE_PAGO_CUENTA:
				value = Utilidad.rellenarCerosIzquierda("001", 3);
				break;
			case MEDIO_DE_PAGO_TIPO_CREDITO:
				if (esNotaCredito)
					value = Utilidad.rellenarEspaciosDerecha(TipoCreditoEnum.NOTACREDITO.getValor(), 3);
				else 
					value = Utilidad.rellenarEspaciosDerecha(TipoCreditoEnum.REMANENTE.getValor(), 3);
				break;
			
			// Datos del credito aplicado: Nota de credito (foto de los datos previos a la imputacion)
			case NC_TIPO_COMPROBANTE:
				value = Utilidad.rellenarEspaciosDerecha(TipoCreditoEnum.NOTACREDITO.getValor(), 3);
				break;
			case NC_CLASE_COMPROBANTE:
				if (esNotaCredito)
					value = Utilidad.rellenarEspaciosDerecha(TipoClaseComprobanteEnum.S.name(), 1); //Si es Nota Credito
				else 
					value = Utilidad.rellenarEspaciosDerecha(TipoClaseComprobanteEnum.A.name(), 1); //Si es Nota Credito
				break;
			case NC_SUCURSAL_COMPROBANTE:
				value = Utilidad.rellenarEspaciosDerecha("32", 4);
				break;
			case NC_NUMERO_COMPROBANTE:
				value = Utilidad.rellenarEspaciosDerecha("345", 8);
				break;
			case NC_NUMERO_REFERENCIA_MIC:
				if (!Validaciones.isEmptyString(numRefMicFac)) {
					value = Utilidad.rellenarCerosIzquierda(numRefMicFac, 15);
				} else {
					value = Utilidad.rellenarCerosIzquierda("", 15);
				}
				break;
			
			// Datos del credito aplicado: Remanente (foto de los datos previos a la imputacion)
			case REMANENTE_CODIGO_TIPO:
				value =  Utilidad.rellenarCerosIzquierda(""+TipoRemanenteEnum.TRANSFERIBLE_NO_ACTUALIZABLE.codigo(), 1);
				break;
			case REMANENTE_DESCRIPCION_TIPO:
				value =  Utilidad.rellenarEspaciosDerecha(TipoRemanenteEnum.TRANSFERIBLE_NO_ACTUALIZABLE.descripcion(), 40);
				break;
					
			// Datos del credito aplicado: datos generales (foto de los datos previos a la imputacion)
			case CREDITO_APLICADO_IMPORTE:
				value = Utilidad.rellenarCerosIzquierda("200", 10);
				break;
			case CREDITO_APLICADO_SALDO:
				value = Utilidad.rellenarCerosIzquierda("100", 10);
				break;
			case CREDITO_APLICADO_FECHA_ALTA:
				if (!esNotaCredito) { 
					value = "20160420";
				} else {
					value = Utilidad.rellenarCerosIzquierda("", 8);
				}
				break;
			case CREDITO_APLICADO_FECHA_EMISION:
				if (esNotaCredito) { 
					value = "20160421";
				} else {
					value = Utilidad.rellenarCerosIzquierda("", 8);
				}
				break;
			case CREDITO_APLICADO_FECHA_VENCIMIENTO:
				if (esNotaCredito) { 
					value = "20160422";
				} else {
					value = Utilidad.rellenarCerosIzquierda("", 8);
				}
				break;
			case CREDITO_APLICADO_FECHA_ULTIMO_MOVIMIENTO_COBRO:
				if (esNotaCredito) { 
					value = "20160423";
				} else {
					value = Utilidad.rellenarCerosIzquierda("", 8);
				}
				break;
			case CREDITO_APLICADO_CODIGO_MARCA_RECLAMO:
				if (esNotaCredito) { 
					value = Utilidad.rellenarEspaciosDerecha(MarcaReclamoEnum.ANULADO.codigo(), 1);
				} else {
					value = Utilidad.rellenarEspaciosDerecha("", 1);
				}
				break;
			case CREDITO_APLICADO_DESCRIPCION_MARCA_RECLAMO:
				if (esNotaCredito) { 
					value = Utilidad.rellenarEspaciosDerecha(MarcaReclamoEnum.ANULADO.descripcion(), 10);
				} else {
					value = Utilidad.rellenarEspaciosDerecha("", 1);
				}
				break;
			case CREDITO_APLICADO_CODIGO_MARCA_CYQ:
				if (esNotaCredito) { 
					value = Utilidad.rellenarEspaciosDerecha(MarcaCyQEnum.Q.codigo(), 1);
				} else {
					value = Utilidad.rellenarEspaciosDerecha("", 1);
				}
				break;
			case CREDITO_APLICADO_DESCRIPCION_MARCA_CYQ:
				if (esNotaCredito) { 
					value = Utilidad.rellenarEspaciosDerecha(MarcaCyQEnum.Q.descripcion(), 8);
				} else {
					value = Utilidad.rellenarEspaciosDerecha("", 1);
				}
				break;
			case CREDITO_APLICADO_CODIGO_ESTADO_CREDITO:
				value = Utilidad.rellenarEspaciosDerecha(EstadoOrigenEnum.INCOMUNICADO.codigo(), 2);
				break;
			case CREDITO_APLICADO_DESCRIPCION_ESTADO_CREDITO:
				value = Utilidad.rellenarEspaciosDerecha(EstadoOrigenEnum.INCOMUNICADO.descripcion(), 20);
				break;
				
			// Datos del credito aplicado: Tagetik  (foto de los datos previos a la imputacion)
			case CREDITO_TAGETIK_RAZON_SOCIAL_CLIENTE:
//				dddvalue = Utilidad.rellenarEspaciosDerecha(EstadoOrigenEnum.INCOMUNICADO.descripcion(), 20);
				break;
			case CREDITO_TAGETIK_TIPO_CLIENTE:
				break;
			case CREDITO_TAGETIK_CUIT:
				break;
			case CREDITO_TAGETIK_CICLO_FACTURACION:
				break;
			case CREDITO_TAGETIK_MARKETING_CUSTOMER_GROUP:
				break;
			case CREDITO_TAGETIK_TIPO_FACTURA:
				break;
			case CREDITO_TAGETIK_DESCRIPCION_TIPO_FACTURA:
				break;
			case CREDITO_TAGETIK_IMPORTE_ORIGINAL:
				break;
			// Datos del credito aplicado: Dacota (foto de los datos previos a la imputacion)
			case CREDITO_DAKOTA_FECHA_VENCIMIENTO_MORA:
				value = "20160425";
				break;
			case CREDITO_DAKOTA_INDICADOR_PETICION_CORTE:
				break;
			case CREDITO_DAKOTA_CODIGO_TARIFA:
				break;
		// Datos del acuerdo de intereses por mora / acuerdo del reintegro: datos generales (foto de los datos previos a la imputacion)
			case ACUERDO_GNERALES_CODIGO_CLIENTE_MORA:
				if (!Validaciones.isEmptyString(clienteAcuerdo)) {
					value = Utilidad.rellenarCerosIzquierda(clienteAcuerdo, 10);
				} else {
					value = Utilidad.rellenarCerosIzquierda("", 10);
				}
				break;
			case ACUERDO_GNERALES_ACUERDO_FACTURACION_INTERESES_REINTEGRO:
				break;
			case ACUERDO_GNERALES_NUMERO_LINEA:
				break;
			case ACUERDO_GNERALES_CODIGO_ESTADO_ACUERDO:
				break;
			case ACUERDO_GNERALES_DESCRIPCION_ESTADO_ACUERDO:
				break;
		// Datos del acuerdo de intereses por mora / acuerdo del reintegro: datos del cliente
			case ACUERDO_CLIENTE_CODIGO_CLIENTE:
				if (!Validaciones.isEmptyString(clienteAcuerdo)) {
					value = Utilidad.rellenarCerosIzquierda(clienteAcuerdo, 10);
				} else {
					value = Utilidad.rellenarCerosIzquierda("", 10);
				}
				break;
		// Datos de respuesta generales: resultado de la invocación a nivel debito
			case RESPUESTA_DEBITO_RESULTADO_CONSULTA:
				break;
			case RESPUESTA_DEBITO_CODIGO_ERROR:
				break;
			case RESPUESTA_DEBITO_DESCRIPCION_ERROR:
				break;
			// Datos de respuesta generales: resultado de la invocación a nivel credito
			case RESPUESTA_CREDITO_RESULTADO_CONSULTA:
				break;
			case RESPUESTA_CREDITO_CODIGO_ERROR:
				break;
			case RESPUESTA_CREDITO_DESCRIPCION_ERROR:
				break;
			// Datos de respuesta generales: resultado de la invocación a nivel reintegro (solo si genera cargo por PF)
			
			case RESPUESTA_REINTEGRO_RESULTADO_CONSULTA:
				break;
			case RESPUESTA_REINTEGRO_CODIGO_ERROR:
				break;
			case RESPUESTA_REINTEGRO_DESCRIPCION_ERROR:
				
			break;	
			
			default: 
				value = Constantes.EMPTY_STRING;
				break;
		}
		
		return value;
	}
	
}
