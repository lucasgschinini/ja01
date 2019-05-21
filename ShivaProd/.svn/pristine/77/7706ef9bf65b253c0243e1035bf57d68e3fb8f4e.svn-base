package ar.com.telecom.shiva.base.jms.servicios.impl.dummy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ar.com.telecom.shiva.base.enumeradores.EstadoConceptoTercerosEnum;
import ar.com.telecom.shiva.base.enumeradores.OkNokEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.excepciones.otros.JmsExcepcion;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicConsultaCreditoEntrada;
import ar.com.telecom.shiva.base.jms.datos.entrada.MicConsultaDeudaEntrada;
import ar.com.telecom.shiva.base.jms.datos.salida.MicConsultaCreditoSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.MicConsultaDeudaSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaADCSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaGeneracionCargoSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.MicRespuestaDescobroSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicControlPaginado;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicCredito;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDebito;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicResultadoConsulta;
import ar.com.telecom.shiva.base.jms.servicios.IMicJmsSyncServicio;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionDescobroDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionADCDto;
import ar.com.telecom.shiva.negocio.dto.cobros.MicTransaccionGeneracionCargosDto;

public class MicDummyJmsSyncServicioImpl implements IMicJmsSyncServicio {

	@Override
	public MicConsultaDeudaSalida consultarDeuda(MicConsultaDeudaEntrada salidaDeDatos) throws JmsExcepcion {
		
		MicConsultaDeudaSalida salida = new MicConsultaDeudaSalida();
		MicDebito debito = null;
		int nroComprobante = 500;
		
		List<MicDebito> debitos = new ArrayList<MicDebito>();
		for (int i = 1; i <= 100; i++) {
			debito = new MicDebito();
			debito.setCodigoCliente(new BigInteger("93018"));
			debito.setCuenta(new Integer(777 + i));
			debito.setCodigoTipoDuc(new Integer(1));
			debito.setDescripcionTipoDuc("VENTA LINEAS");
			debito.setCodigoEstadoDuc("G");
			debito.setDescripcionEstadoDuc("GENERADO");
			debito.setAcuerdo("0000045560");
			
			// Id Documento
			debito.setClaseComprobante(TipoClaseComprobanteEnum.A);
			debito.setNumeroComprobante("00000" + (nroComprobante++));
			debito.setSucursalComprobante("0078");
			Random rnd = new Random();
			if(rnd.nextBoolean()){
				debito.setTipoComprobante(TipoComprobanteEnum.DEB);
			}else{
				debito.setTipoComprobante(TipoComprobanteEnum.DUC);				
			}	
			
			debito.setNumeroReferenciaMic("89955");
			try {
				debito.setFechaVencimiento(Utilidad.parseFechaBarraString("1999/04/15"));
				debito.setFechaEmision(Utilidad.parseFechaBarraString("1999/03/30"));
				debito.setFechaUltimoPagoParcial(Utilidad.parseFechaBarraString("1999/04/15"));
				debito.setFechaPuestaAlCobro(Utilidad.parseFechaBarraString("1999/04/15"));
			} catch (ParseException e) {
				throw new JmsExcepcion(e);
			}
			debito.setImporteAl1erVencimiento(new BigDecimal(124));
			debito.setImporteAl2doVencimiento(new BigDecimal(125));
			debito.setSaldoAl1erVencimiento(new BigDecimal(126));
			debito.setImporte3erosTransferidos(new BigDecimal(127));
			debito.setCodigoEstadoAcuerdoFacturacion(null);
			debito.setDescripcionEstadoAcuerdoFacturacion("");
			debito.setEstadoConceptosTerceros(EstadoConceptoTercerosEnum.NO_TRANSFERIDOS);
			debito.setPosibilidadDestransferencia(SiNoEnum.NO);
			debito.setImporte3erosTransferidos(new BigDecimal(126));
			debito.setImporte1erVencimientoCon3eros(new BigDecimal(126));
			debito.setImporte2erVencimientoCon3eros(new BigDecimal(126));
			//Se agrega esto para probar tanto con codigo 55 que hace la llamada 
			//a Deimos como otro codigo que no la hace
			if(rnd.nextBoolean()){
				debito.setCodigoEstadoFactura("55");
			}else{
				debito.setCodigoEstadoFactura("00");
			}			
			debito.setDescripcionEstadoFactura("ENV. AL CLIENTE");
			debito.setCodigoMarcaReclamo("D");
			debito.setDescripcionMarcaReclamo("ANULADO");
			debito.setCodigoMarcaCyQ("C");
			debito.setDescripcionMarcaCyQ("CONCURSO");
			
			debito.setNumeroConvenio(null);
			debito.setCuota(null);
			
			
			if(rnd.nextBoolean()){
				debito.getInformacionAdicionalTagetik().setRazonSocialCliente("PEREZ PEREZ");
				debito.getInformacionAdicionalTagetik().setTipoCliente("1A");
				debito.getInformacionAdicionalTagetik().setCuit("30697241325");
				debito.getInformacionAdicionalTagetik().setCicloFacturacion(null);
				debito.getInformacionAdicionalTagetik().setMarketingCustomerGroup(null);
				debito.getInformacionAdicionalTagetik().setTipoFactura(51);
				debito.getInformacionAdicionalTagetik().setDescripcionTipoFactura("FACT. FLEXCAB");
			}else{
				debito.getInformacionAdicionalTagetik().setRazonSocialCliente("CLIENTE FABIO");
				debito.getInformacionAdicionalTagetik().setTipoCliente("1B");
				debito.getInformacionAdicionalTagetik().setCuit("20305054519");
				debito.getInformacionAdicionalTagetik().setCicloFacturacion(null);
				debito.getInformacionAdicionalTagetik().setMarketingCustomerGroup(null);
				debito.getInformacionAdicionalTagetik().setTipoFactura(00);
				debito.getInformacionAdicionalTagetik().setDescripcionTipoFactura("NI IDEA");
			}	

			try {
				debito.getInformacionAdicionalDacota().setFechaVencimientoMora(Utilidad.parseFechaBarraString("1999/04/15"));
			} catch (ParseException e) {
				throw new JmsExcepcion(e);
			}
			
			debito.getInformacionAdicionalDacota().setIndicadorPeticionCorte(null);
			debito.getInformacionAdicionalDacota().setCodigoTarifa(null);

			debito.getInformacionAdicionalSaldosTerceros().setSaldoTerceroFinanciableNOTransferible(new BigDecimal(126));
			debito.getInformacionAdicionalSaldosTerceros().setSaldoTerceroFinanciableTransferible(new BigDecimal(126));
			debito.getInformacionAdicionalSaldosTerceros().setSaldoTerceroNOFinanciableTransferible(new BigDecimal(126));
			
			debitos.add(debito);
		}
		
		MicControlPaginado paginado = new MicControlPaginado();
		paginado.setCantidadRegistrosRetornados(100L);
		paginado.setCantidadRegistrosTotales(200L);
		
		MicResultadoConsulta resultado = new MicResultadoConsulta();
		resultado.setResultadoConsulta(OkNokEnum.OK);
		
		salida.setListaDebitos(debitos);
		salida.setControlPaginado(paginado);
		salida.setResultadoConsulta(resultado);

		return salida;
	}

	@Override
	public MicConsultaCreditoSalida consultarCredito(MicConsultaCreditoEntrada salidaDeDatos) throws JmsExcepcion {
		MicConsultaCreditoSalida salida = new MicConsultaCreditoSalida();
		MicCredito credito = null;
		
		List<MicCredito> creditos = new ArrayList<MicCredito>();
		for (int i = 1; i <= 100; i++) {
			credito = new MicCredito();
			try {
				credito.getInformacionGeneral().setFechaVencimiento(Utilidad.parseFechaBarraString("1999/04/15"));
			} catch (ParseException e) {
				throw new JmsExcepcion(e);
			}
			
			creditos.add(credito);
		}
		
		MicControlPaginado paginado = new MicControlPaginado();
		paginado.setCantidadRegistrosRetornados(100L);
		paginado.setCantidadRegistrosTotales(200L);
		
		MicResultadoConsulta resultado = new MicResultadoConsulta();
		resultado.setResultadoConsulta(OkNokEnum.OK);
		
		salida.setListaCreditos(creditos);
		salida.setControlPaginado(paginado);
		salida.setResultadoConsulta(resultado);

		return salida;
	}

	@Override
	public MicRespuestaADCSalida simularApropiarDeuda(
			MicTransaccionADCDto dto) throws JmsExcepcion {
		return null;
	}

	@Override
	public MicRespuestaADCSalida simularApropiarMedioPago(MicTransaccionADCDto dto)
			throws JmsExcepcion {
		return null;
	}

	@Override
	public MicRespuestaADCSalida simularApropiarDeudaYMedioPago(
			MicTransaccionADCDto dto) throws JmsExcepcion {
		return null;
	}

	@Override
	public MicRespuestaGeneracionCargoSalida simularCargoDebito(
			MicTransaccionGeneracionCargosDto dto) throws JmsExcepcion {
		return null;
	}

	@Override
	public MicRespuestaGeneracionCargoSalida simularCargoCredito(
			MicTransaccionGeneracionCargosDto dto) throws JmsExcepcion {
		return null;
	}

	@Override
	public MicRespuestaGeneracionCargoSalida simularInteres(
			MicTransaccionGeneracionCargosDto dto) throws JmsExcepcion {
		return null;
	}

	@Override
	public MicRespuestaDescobroSalida simularDescobro(
			MicTransaccionDescobroDto dto) throws JmsExcepcion {
		return null;
	}

	@Override
	public MicRespuestaGeneracionCargoSalida simularContracargoDebito(
			MicTransaccionGeneracionCargosDto dto) throws JmsExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MicRespuestaGeneracionCargoSalida simularContracargoCredito(
			MicTransaccionGeneracionCargosDto dto) throws JmsExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MicRespuestaGeneracionCargoSalida simularContracargoInteres(
			MicTransaccionGeneracionCargosDto dto) throws JmsExcepcion {
		// TODO Auto-generated method stub
		return null;
	}


}
