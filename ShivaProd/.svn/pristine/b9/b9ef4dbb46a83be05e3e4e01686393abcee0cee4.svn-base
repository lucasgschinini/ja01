package ar.com.telecom.shiva.base.ws.cliente.servicios.impl.dummy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoProcesoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.ws.cliente.datos.IdDocumento;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoCargosWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoConfirmacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoConsultaAcuerdoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoCreditoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoDesapropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoDescobroWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaCalipsoDeudaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleCTAoNotaCredito;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleCTAoNotaCreditoRespuesta;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleFactura;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleNotaCreditoDebito;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoApropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoCargosWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoConfirmacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoConsultaAcuerdoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoCreditoWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoDesapropiacionWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoDescobroWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaCalipsoDeudaWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.ControlPaginado;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.ResultadoProcesamiento;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.CalipsoCredito;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.CalipsoDebito;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.Resultado;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.calipso.ResultadoAcuerdo;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteCalipsoServicio;

public class DummyCalipsoServicio implements IClienteCalipsoServicio {

	IClienteCalipsoServicio auxiliarClienteCalipsoServicio;
	boolean calipsoCobranzasWS;
	boolean calipsoConsultasWS;
	boolean calipsoConsultaAcuerdoWS;
	boolean calipsoCargosWS;
	boolean calipsoDescobrosWS;
	
	@Override
	public SalidaCalipsoApropiacionWS apropiarCobro(
			EntradaCalipsoApropiacionWS entradaCalipsoApropiacionWS)
			throws NegocioExcepcion {
		
		if (calipsoCobranzasWS) {
			
			return auxiliarClienteCalipsoServicio.apropiarCobro(entradaCalipsoApropiacionWS);
			
		} else {
			SalidaCalipsoApropiacionWS respuesta = new SalidaCalipsoApropiacionWS();
			respuesta.setIdOperacionTransaccion(entradaCalipsoApropiacionWS.getIdOperacion()+"."+entradaCalipsoApropiacionWS.getNumeroTransaccion());
			
			DetalleFactura detalleFactura = new DetalleFactura();
			detalleFactura.setIdCobranza(new BigInteger("5635455"));
	//		detalleFactura.setMontoCalculadoMora(new BigDecimal("98.64"));
			detalleFactura.setMontoCuenta(new BigDecimal("0"));
			respuesta.setDetalleFactura(detalleFactura);
			
			List<DetalleNotaCreditoDebito> listaNotasCreditoODebito = new ArrayList<DetalleNotaCreditoDebito>();
	//		DetalleNotaCreditoDebito detNotaCredito = new DetalleNotaCreditoDebito();
	//		
	//		IdDocumento notaCreditoResultante = new IdDocumento();
	//		notaCreditoResultante.setTipoComprobante(TipoComprobanteEnum.FAC);
	//		notaCreditoResultante.setClaseComprobante(TipoClaseComprobanteEnum.B);
	//		notaCreditoResultante.setSucursalComprobante("0446");
	//		notaCreditoResultante.setNumeroComprobante("00000660");
	//		
	//		detNotaCredito.setCtaoNotaCredito(notaCreditoResultante);
	//		detNotaCredito.setIdCtaONotaCredito(new BigInteger("16613430"));
	//		detNotaCredito.setImporte(new BigDecimal("1.35"));
	//		
	//		listaNotasCreditoODebito.add(detNotaCredito);
			respuesta.setListaNotasCreditoODebito(listaNotasCreditoODebito);
			
			
			DetalleCTAoNotaCredito detCTA = new DetalleCTAoNotaCredito();
			//Padre
			IdDocumento ctaPadre = new IdDocumento();
			ctaPadre.setTipoComprobante(TipoComprobanteEnum.CTA);
			ctaPadre.setClaseComprobante(TipoClaseComprobanteEnum.X);
			ctaPadre.setSucursalComprobante("1");
			ctaPadre.setNumeroComprobante("7");
			detCTA.setIdDocumento(ctaPadre);
			detCTA.setIdCobranza(new BigInteger("5635054"));
			
			DetalleCTAoNotaCreditoRespuesta detalleCtaNtaCre = new DetalleCTAoNotaCreditoRespuesta();
			//Hijo
			IdDocumento ctaHijo = new IdDocumento();
			ctaHijo.setTipoComprobante(TipoComprobanteEnum.CTA);
			ctaHijo.setClaseComprobante(TipoClaseComprobanteEnum.X);
			ctaHijo.setSucursalComprobante("123");
			ctaHijo.setNumeroComprobante("123");
			detalleCtaNtaCre.setCtaResultante(ctaHijo);
			detalleCtaNtaCre.setImporte(new BigDecimal(534));
			detCTA.setDetalleCtaNtaCre(detalleCtaNtaCre);
			
			List<DetalleCTAoNotaCredito> listaCTAoNotaDeCredito = new ArrayList<DetalleCTAoNotaCredito>();
			listaCTAoNotaDeCredito.add(detCTA);
			respuesta.setListaCTAoNotaDeCredito(listaCTAoNotaDeCredito);
			
			Resultado resultadoInvocacion = new Resultado();
			resultadoInvocacion.setResultado("OK");
			respuesta.setResultadoInvocacion(resultadoInvocacion);
			
			return respuesta;
		}
	}

	@Override
	public SalidaCalipsoConfirmacionWS confirmarCobro(
			EntradaCalipsoConfirmacionWS entradaCalipsoConfirmacionWS)
			throws NegocioExcepcion {
		
		if (calipsoCobranzasWS) {
			
			return auxiliarClienteCalipsoServicio.confirmarCobro(entradaCalipsoConfirmacionWS);
			
		} else {
			SalidaCalipsoConfirmacionWS respuesta = new SalidaCalipsoConfirmacionWS();
			
			Resultado resultadoInvocacion = new Resultado();
			resultadoInvocacion.setResultado("OK");
			respuesta.setResultadoInvocacion(resultadoInvocacion);
			return respuesta;
		}
	}

	@Override
	public SalidaCalipsoDesapropiacionWS desapropiarOperacion(
			EntradaCalipsoDesapropiacionWS entradaCalipsoDesapropiacionWS)
			throws NegocioExcepcion {
		
		if (calipsoCobranzasWS) {
			
			return auxiliarClienteCalipsoServicio.desapropiarOperacion(entradaCalipsoDesapropiacionWS);
			
		} else {
			SalidaCalipsoDesapropiacionWS respuesta = new SalidaCalipsoDesapropiacionWS();
			
			Resultado resultadoInvocacion = new Resultado();
			resultadoInvocacion.setResultado("OK");
			respuesta.setResultadoInvocacion(resultadoInvocacion);
			return respuesta;
		}
	}

	@Override
	public SalidaCalipsoApropiacionWS testApropiarCobro(
			EntradaCalipsoApropiacionWS entradaCalipsoApropiacionWS)
			throws NegocioExcepcion {
		return null;
	}

	@Override
	public SalidaCalipsoConfirmacionWS testConfirmarCobro(
			EntradaCalipsoConfirmacionWS entradaCalipsoConfirmacionWS)
			throws NegocioExcepcion {
		return null;
	}

	@Override
	public SalidaCalipsoDesapropiacionWS testDesapropiarOperacion(
			EntradaCalipsoDesapropiacionWS entradaCalipsoDesapropiacionWS)
			throws NegocioExcepcion {
		return null;
	}

	public SalidaCalipsoDeudaWS consultaDebito(EntradaCalipsoDeudaWS entradaCalipsoDebitoWS) throws NegocioExcepcion {
		if (calipsoConsultasWS) {
			
			return auxiliarClienteCalipsoServicio.consultaDebito(entradaCalipsoDebitoWS);
			
		} else {
		
			SalidaCalipsoDeudaWS salida = new SalidaCalipsoDeudaWS();
			List<CalipsoDebito> debitos = new ArrayList<CalipsoDebito>();
			
			CalipsoDebito debito = null;
			Integer idDocCtasCob = new Integer(771073);
			int nroComprobante = 30;
			
			
			if(!Validaciones.isObjectNull((entradaCalipsoDebitoWS.getAcuerdo()))){
				if("0".equals(entradaCalipsoDebitoWS.getAcuerdo().toString())){				
					nroComprobante = 0;
				}
			}
			for (int i = 1; i <= nroComprobante; i++) {
				idDocCtasCob = idDocCtasCob + i;
				
				debito = new CalipsoDebito();
				debito.setCodigoAcuerdoFacturacion("1524");
				debito.setEstadoAcuerdoFacturacion("FACTURABLE");
				debito.setEstadoMorosidad("");
				try {
					debito.setFechaEmision(Utilidad.parseFechaBarraString("1999/03/30"));
					debito.setFechaVencimiento(Utilidad.parseFechaBarraString("1999/04/15"));
				} catch (ParseException e) {
					throw new NegocioExcepcion(e);
				}
				debito.setFechaUltimoPagoParcial(null);
				
				debito.setIdClienteLegado("93018");
				debito.setIdDocCtasCob(new BigInteger(idDocCtasCob.toString()));
				
				debito.getIdDocumento().setClaseComprobante(TipoClaseComprobanteEnum.A);
				debito.getIdDocumento().setNumeroComprobante("00000" + (nroComprobante++));
				debito.getIdDocumento().setSucursalComprobante("8864");
				debito.getIdDocumento().setTipoComprobante(TipoComprobanteEnum.FAC);
				
				debito.setImporte1erVencimientoMonedaOrigen(new BigDecimal("1606.2500"));
				debito.setImporte1erVencimientoPesificado(new BigDecimal("1606.2500"));

				//Registros queden como si y otros como no
				Random rnd = new Random();
				debito.setMarcaMigradoDeimos(SiNoEnum.SI);
				
				debito.setMarcaReclamo(SiNoEnum.NO);
				if(rnd.nextBoolean()){
					debito.setMonedaOriginalFactura(MonedaEnum.DOL);
					debito.getIdDocumento().setTipoComprobante(TipoComprobanteEnum.CTA);
				}else{
					debito.setMonedaOriginalFactura(MonedaEnum.PES);
					debito.getIdDocumento().setTipoComprobante(TipoComprobanteEnum.FAC);
				}
				
				debito.setSaldo1erVencimientoMonedaOrigen(new BigDecimal("1606.2500"));
				debito.setSaldoPesificado(new BigDecimal("1606.2500"));
				debito.setTipoCambioActual(new BigDecimal("0.9995000"));
				
				debito.getInformacionAdicionalTagetikCalipso().setRazonSocialCliente("PEREZ PEREZ");
				debito.getInformacionAdicionalTagetikCalipso().setTipoCliente("OP");
				debito.getInformacionAdicionalTagetikCalipso().setCuit("30697241325");
				
				debito.getInformacionAdicionalDacota().setUnidadOperativa("UOP");
				debito.getInformacionAdicionalDacota().setSubTipo("MIG");
				debito.getInformacionAdicionalDacota().setHolding("");
				
				debitos.add(debito);
			}
			
			ControlPaginado paginado = new ControlPaginado();
			paginado.setCantidadRegistrosTotales(new BigInteger("40"));
			paginado.setCantidadRegistrosRetornados(new BigInteger("30"));
			ResultadoProcesamiento resultado = new ResultadoProcesamiento();
			resultado.setResultadoImputacion("OK");
			
			salida.setControlPaginado(paginado);
			salida.setListaDebitos(debitos);
			salida.setResultadoProcesamiento(resultado);
			return salida;
		}
	}

	@Override
	public SalidaCalipsoCreditoWS consultaCredito(EntradaCalipsoCreditoWS entradaCalipsoCreditoWS) throws NegocioExcepcion {
		
		if (calipsoConsultasWS) {
			
			return auxiliarClienteCalipsoServicio.consultaCredito(entradaCalipsoCreditoWS);
			
		} else {
			SalidaCalipsoCreditoWS salida = new SalidaCalipsoCreditoWS();
			CalipsoCredito credito = null;
			List<CalipsoCredito> creditos = new ArrayList<CalipsoCredito>();
			
			for (int i = 1; i <= 100; i++) {
				
				credito = new CalipsoCredito();
				try {
					credito.setFechaVencimiento(Utilidad.parseFechaBarraString("1999/03/30"));
				} catch (ParseException e) {
					throw new NegocioExcepcion(e);
				}
				creditos.add(credito);
				
	//			
	//			idDocCtasCob = idDocCtasCob + i;
	//			
	//			debito = new CalipsoDebito();
	//			debito.setCodigoAcuerdoFacturacion("1524");
	//			debito.setEstadoAcuerdoFacturacion("FACTURABLE");
	//			debito.setEstadoMorosidad("");

	//			debito.setFechaUltimoPagoParcial(null);
	//			debito.setIdClienteLegado(new BigInteger("93018"));
	//			debito.setIdDocCtasCob(new BigInteger(idDocCtasCob.toString()));
	//			
	//			debito.getIdDocumento().setClaseComprobante(TipoClaseComprobanteEnum.A);
	//			debito.getIdDocumento().setNumeroComprobante("00000" + (nroComprobante++));
	//			debito.getIdDocumento().setSucursalComprobante("8864");
	//			debito.getIdDocumento().setTipoComprobante(TipoComprobanteEnum.FAC);
	//			
	//			debito.setImporte1erVencimientoMonedaOrigen(new BigDecimal("1606.2500"));
	//			debito.setImporte1erVencimientoPesificado(new BigDecimal("1606.2500"));
	//
	////			Registros queden como si y otros como no
	//			Random rnd = new Random();
	//			debito.setMarcaMigradoDeimos(SiNoEnum.SI);
	//			
	//			debito.setMarcaReclamo(SiNoEnum.NO);
	//			if(rnd.nextBoolean()){
	//				debito.setMonedaOriginalFactura(MonedaEnum.DOL);
	//				debito.getIdDocumento().setTipoComprobante(TipoComprobanteEnum.CTA);
	//			}else{
	//				debito.setMonedaOriginalFactura(MonedaEnum.PES);
	//				debito.getIdDocumento().setTipoComprobante(TipoComprobanteEnum.FAC);
	//			}
	//			
	//			debito.setSaldo1erVencimientoMonedaOrigen(new BigDecimal("1606.2500"));
	//			debito.setSaldoPesificado(new BigDecimal("1606.2500"));
	//			debito.setTipoCambioActual(new BigDecimal("0.9995000"));
	//			
	//			debito.getInformacionAdicionalTagetikCalipso().setRazonSocialCliente("PEREZ PEREZ");
	//			debito.getInformacionAdicionalTagetikCalipso().setTipoCliente("OP");
	//			debito.getInformacionAdicionalTagetikCalipso().setCuit("30697241325");
	//			
	//			debito.getInformacionAdicionalDacota().setUnidadOperativa("UOP");
	//			debito.getInformacionAdicionalDacota().setSubTipo("MIG");
	//			debito.getInformacionAdicionalDacota().setHolding("");
	//			
	//			debitos.add(debito);
				
	
			}
			
			ControlPaginado paginado = new ControlPaginado();
			paginado.setCantidadRegistrosTotales(new BigInteger("200"));
			paginado.setCantidadRegistrosRetornados(new BigInteger("100"));
			ResultadoProcesamiento resultado = new ResultadoProcesamiento();
			resultado.setResultadoImputacion("OK");
			
			salida.setControlPaginado(paginado);
			salida.setListaCreditos(creditos);
			salida.setResultadoProcesamiento(resultado);
			return salida;
		}
	}

	@Override
	public SalidaCalipsoConsultaAcuerdoWS consultaAcuerdo(
			EntradaCalipsoConsultaAcuerdoWS entrada) throws NegocioExcepcion {
		
		
		if (calipsoConsultaAcuerdoWS) {
			
			return auxiliarClienteCalipsoServicio.consultaAcuerdo(entrada);
			
		} else {
			SalidaCalipsoConsultaAcuerdoWS salida = new SalidaCalipsoConsultaAcuerdoWS();
			ResultadoAcuerdo resultadoAcuerdo = new ResultadoAcuerdo();
			Resultado resultado = new Resultado();
			
			resultado.setResultado("OK");
			resultadoAcuerdo.setEstado(EstadoAcuerdoFacturacionEnum.ACTIVO);
			if("70051".equals(entrada.getAcuerdoFacturacion().toString())){
				resultadoAcuerdo.setIdClienteLegado(new BigInteger("7005"));
			} else if("90321".equals(entrada.getAcuerdoFacturacion().toString())){
				resultadoAcuerdo.setIdClienteLegado(new BigInteger("9032"));
			} else if("26011739301".equals(entrada.getAcuerdoFacturacion().toString())){
				resultadoAcuerdo.setIdClienteLegado(new BigInteger("2601173930"));
			}else {
				resultado.setResultado("NOK");
				resultado.setCodigoError("");
				resultado.setDescripcionError("");
				resultadoAcuerdo.setEstado(null);
			}			
			
			salida.setResultado(resultado);
			salida.setAcuerdo(resultadoAcuerdo);
			return salida;
		}
	}

	@Override
	public SalidaCalipsoCargosWS generarCargoCalipso(
			EntradaCalipsoCargosWS entrada, TipoProcesoEnum tipoProceso) throws NegocioExcepcion {
		if (calipsoCargosWS) {
			
			return auxiliarClienteCalipsoServicio.generarCargoCalipso(entrada, tipoProceso);
			
		} else {
			
			return null;
		}
	}


	@Override
	public SalidaCalipsoDescobroWS descobrarCobro(
			EntradaCalipsoDescobroWS entrada) throws NegocioExcepcion {
		
		if (calipsoDescobrosWS) {
			
			return auxiliarClienteCalipsoServicio.descobrarCobro(entrada);
			
		} else {
			
			return null;
		}
	}
	
	public boolean isCalipsoCobranzasWS() {
		return calipsoCobranzasWS;
	}

	public void setCalipsoCobranzasWS(boolean calipsoCobranzasWS) {
		this.calipsoCobranzasWS = calipsoCobranzasWS;
	}

	public boolean isCalipsoConsultasWS() {
		return calipsoConsultasWS;
	}

	public void setCalipsoConsultasWS(boolean calipsoConsultasWS) {
		this.calipsoConsultasWS = calipsoConsultasWS;
	}

	public boolean isCalipsoConsultaAcuerdoWS() {
		return calipsoConsultaAcuerdoWS;
	}

	public void setCalipsoConsultaAcuerdoWS(boolean calipsoConsultaAcuerdoWS) {
		this.calipsoConsultaAcuerdoWS = calipsoConsultaAcuerdoWS;
	}

	public boolean isCalipsoCargosWS() {
		return calipsoCargosWS;
	}

	public void setCalipsoCargosWS(boolean calipsoCargosWS) {
		this.calipsoCargosWS = calipsoCargosWS;
	}

	public IClienteCalipsoServicio getAuxiliarClienteCalipsoServicio() {
		return auxiliarClienteCalipsoServicio;
	}

	public void setAuxiliarClienteCalipsoServicio(
			IClienteCalipsoServicio auxiliarClienteCalipsoServicio) {
		this.auxiliarClienteCalipsoServicio = auxiliarClienteCalipsoServicio;
	}

	public boolean isCalipsoDescobrosWS() {
		return calipsoDescobrosWS;
	}

	public void setCalipsoDescobrosWS(boolean calipsoDescobrosWS) {
		this.calipsoDescobrosWS = calipsoDescobrosWS;
	}
	
}
