package ar.com.telecom.shiva.base.ws.cliente;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.remoting.jaxws.JaxWsSoapFaultException;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceFormatoMensajeExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaIceConsultaChequesWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaIceConsultaChequesWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.ResultadoProcesamiento;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.ice.ClienteCheque;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.ice.IceCheques;
import ar.com.telecom.shiva.presentacion.wsdl.ice.consultaCheques.Cheque;
import ar.com.telecom.shiva.presentacion.wsdl.ice.consultaCheques.Cliente;
import ar.com.telecom.shiva.presentacion.wsdl.ice.consultaCheques.ConsultaChequesEntrada;
import ar.com.telecom.shiva.presentacion.wsdl.ice.consultaCheques.ConsultaChequesPortType;
import ar.com.telecom.shiva.presentacion.wsdl.ice.consultaCheques.ConsultaChequesSalida;

public class IceConsultaChequesWS {
	
	@Autowired 
	ConsultaChequesPortType consultaChequesPortType;
	
	private String iceUser;
	private String icePassword;
	
	private final String MENSAJE = "WS IceConsultaCheque";

	/**
	 * 
	 * @param entrada
	 * @return
	 */
	public SalidaIceConsultaChequesWS consultarChequesIce (EntradaIceConsultaChequesWS entrada) throws NegocioExcepcion {

		try {
		
			ConsultaChequesEntrada consulta = new ConsultaChequesEntrada();
			ConsultaChequesSalida respuesta = new ConsultaChequesSalida();
			
			consulta.setUsuario(iceUser);
			consulta.setPassword(icePassword);
			
			if (!Validaciones.isObjectNull(entrada.getTipoInvocacion())) {
				consulta.setTipoDeInvocacion(entrada.getTipoInvocacion().getCodigo());
			}
	
			if (!Validaciones.isObjectNull(entrada.getCodigoEmpresaReceptora())) {
				consulta.setCodigoEmpresaReceptora(entrada.getCodigoEmpresaReceptora().getCodigo());
			}
			
			consulta.getBancos().addAll(entrada.getBancos());

			for (Long cliente : entrada.getClientes()) {
				consulta.getClientes().add(cliente.toString());
			}
	
			if (!Validaciones.isObjectNull(entrada.getImporteDesde())) {
				// Los importes viajan en formato centavos, sin puntos ni comas
				consulta.setImporteDesde(Utilidad.formatCurrency(entrada.getImporteDesde(), false, false).replace(".", "").replace(",", ""));
			}
			
			if (!Validaciones.isObjectNull(entrada.getImporteHasta())) {
				// Los importes viajan en formato centavos, sin puntos ni comas
				consulta.setImporteHasta(Utilidad.formatCurrency(entrada.getImporteHasta(), false, false).replace(".", "").replace(",", ""));
			}
	
			if (!Validaciones.isObjectNull(entrada.getNroCheque())) {
				consulta.setNumeroDeCheque(entrada.getNroCheque().toString());
			}
			
			if (!Validaciones.isObjectNull(entrada.getCodigoBancoCobro())) {
				consulta.setCodigoBancoCobro(entrada.getCodigoBancoCobro());
			}
			
			if (!Validaciones.isObjectNull(entrada.getFechaCobroDesde())) {
				consulta.setFechaCobroDesde(Utilidad.formatDateDDMMAAAA(entrada.getFechaCobroDesde()));
			}
			
			if (!Validaciones.isObjectNull(entrada.getFechaCobroHasta())) {
				consulta.setFechaCobroHasta(Utilidad.formatDateDDMMAAAA(entrada.getFechaCobroHasta()));
			}
			
			if (!Validaciones.isObjectNull(entrada.getCantidadDeRegistrosARetornar())) {
				consulta.setCantidadARetornar(entrada.getCantidadDeRegistrosARetornar().toString());
			}
			
			if (!Validaciones.isObjectNull(entrada.getUltimoRegistroProcesado())) {
				consulta.setUltimoRegistroProcesado(entrada.getUltimoRegistroProcesado().toString());
			}
	
			respuesta = consultaChequesPortType.consultaCheques(consulta);
			
			
			if (!Validaciones.isObjectNull(respuesta)) {
			
				SalidaIceConsultaChequesWS salida = new SalidaIceConsultaChequesWS();
				salida.setResultadoProcesamiento(new ResultadoProcesamiento());
				salida.setListaCheques(new ArrayList<IceCheques>());
				
				salida.getResultadoProcesamiento().setResultadoImputacion(respuesta.getResultadoInvocacion());
				salida.getResultadoProcesamiento().setCodigoError(respuesta.getCodigoRetorno());
				salida.getResultadoProcesamiento().setDescripcionError(respuesta.getDescripcionRetorno());
				
				if (!respuesta.getCheques().isEmpty()) {
		
					for (Cheque rtaCheque : respuesta.getCheques()) {
		
						IceCheques cheque = new IceCheques();
						
						cheque.setBancoEmisorCheque(rtaCheque.getBancoEmisorCheque());
						
						List<ClienteCheque> clientes = new ArrayList<ClienteCheque>();
						if(!rtaCheque.getClientes().isEmpty() && !Validaciones.isObjectNull(rtaCheque.getClientes().get(0))) {
							for (Cliente cliente : rtaCheque.getClientes()) {
								ClienteCheque clienteCheque = new ClienteCheque();
								clienteCheque.setIdClienteLegado(cliente.getIdClienteLegado());
								clienteCheque.setRazonSocial(cliente.getRazonSocial());
								clientes.add(clienteCheque);
							}	
						}
						cheque.setClientes(clientes);

						cheque.setCobroRevertido(rtaCheque.isCobroRevertido());
						cheque.setCodigoBancoDeCobro(rtaCheque.getCodigoBancoDeCobro());
						cheque.setCodigoDeTarea(rtaCheque.getCodigoDeTarea());
						cheque.setCodigoEmpresaReceptora(rtaCheque.getCodigoEmpresaReceptora());
						cheque.setCodigoEntidadGestora(rtaCheque.getCodigoEntidadGestora());
						cheque.setCodigoOperacion(rtaCheque.getCodigoDeOperacion());
						cheque.setCodigoPostalSucursal(rtaCheque.getCodigoPostalSucursal());
						cheque.setComision(rtaCheque.getComision());
						cheque.setCpClienteCodigoBarras(rtaCheque.getCpClienteCodigoBarras());
						cheque.setDescripcionBanco(rtaCheque.getDescripcionBanco());
						cheque.setFechaDeAcreditacion(mapearFecha(rtaCheque.getFechaDeAcreditacion()));
						cheque.setFechaDeCobro(mapearFecha(rtaCheque.getFechaDeCobro()));
						cheque.setFechaDeVctoOriginal(mapearFecha(rtaCheque.getFechaDeVctoOriginal()));
						cheque.setFechaPeticionReversion(mapearFecha(rtaCheque.getFechaPeticionReversion()));
						cheque.setFormaDePagoDelCliente(rtaCheque.getFormaDePagoDelCliente());
						cheque.setIdCheque(rtaCheque.getIdCheque());
						cheque.setIdentificadorDePago(rtaCheque.getIdentificadorDePago());
						cheque.setImporteAbsoluto(mapearImporte(rtaCheque.getImporteAbsoluto()));
						cheque.setImporteBono(mapearImporte(rtaCheque.getImporteBono()));
						cheque.setImporteCheque(mapearImporte(rtaCheque.getImporteCheque()));
						cheque.setImporteCobradoOReversar(mapearImporte(rtaCheque.getImporteCobradoOReversar()));
						cheque.setImporteComprobante(mapearImporte(rtaCheque.getImporteComprobante()));
						cheque.setImporteEfectivo(mapearImporte(rtaCheque.getImporteEfectivo()));
						cheque.setImporteOriginal(mapearImporte(rtaCheque.getImporteOriginal()));
						cheque.setImporteOtrasMonedas(mapearImporte(rtaCheque.getImporteOtrasMonedas()));
						cheque.setMarcaDeAjuste(rtaCheque.getMarcaDeAjuste());
						cheque.setMarcaDePago(rtaCheque.getMarcaDePago());
						cheque.setMoneda(MonedaEnum.getEnumByName(rtaCheque.getMoneda()));
						cheque.setNombreBancoDeCobro(rtaCheque.getNombreBancoDeCobro());
						cheque.setNombreEntidadGestora(rtaCheque.getNombreEntidadGestora());
						cheque.setNumeroCheque(rtaCheque.getNumeroCheque());

						if (!Validaciones.isNullEmptyOrDash(rtaCheque.getNumeroChequeCompleto())) {
							cheque.setNumeroChequeCompleto(rtaCheque.getNumeroChequeCompleto());
						}
						
						cheque.setNumeroConvenio(rtaCheque.getNumeroConvenio());
						cheque.setNumeroCuota(rtaCheque.getNumeroCuota());
						cheque.setNumeroDeAgencia(rtaCheque.getNumeroDeAgencia());
						cheque.setNumeroDeComprobante(rtaCheque.getNumeroDeComprobante());
						cheque.setNumeroDeSucursalCompleto(rtaCheque.getNumeroDeSucursalCompleto());
						cheque.setNumeroDeTarjeta(rtaCheque.getNumeroDeTarjeta());
						cheque.setNumeroLegal(rtaCheque.getNumeroLegal());
						cheque.setNumeroReferenciaMic(rtaCheque.getNumeroReferenciaMic());
						cheque.setProvinciaDelCodBarras(rtaCheque.getProvinciaDelCodBarras());
						cheque.setRecibo(rtaCheque.getRecibo());
						cheque.setReferenciaDelComprobante(rtaCheque.getReferenciaDelComprobante());
						cheque.setSucursalBanco(rtaCheque.getSucursalBanco());
						cheque.setTipoBono(rtaCheque.getTipoBono());
						cheque.setTipoClienteCodigoBarras(rtaCheque.getTipoClienteCodigoBarras());
						cheque.setTipoComprobante(rtaCheque.getTipoComprobante());
						cheque.setTipoDeCambio(rtaCheque.getTipoDeCambio());
						cheque.setTipoDeFacturacion(rtaCheque.getTipoDeFacturacion());
						cheque.setTipoDeLectura(rtaCheque.getTipoDeLectura());
						cheque.setTipoDeOperacion(rtaCheque.getTipoDeOperacion());
						cheque.setTipoDocumentoRelacionado(rtaCheque.getTipoDocumentoRelacionado());
						cheque.setTipoDocumentoRelacionadoTotfa(rtaCheque.getTipoDocumentoRelacionadoTotfa());
						cheque.setTipoOtrasMonedas(rtaCheque.getTipoOtrasMonedas());						
						
						salida.getListaCheques().add(cheque);
					}
					salida.setCantidadRegistrosDevueltos(new Long(respuesta.getCantidadRegistrosDevueltos()));
					salida.setCantidadDeRegistrosTotales(new Long(respuesta.getCantidadDeRegistrosTotales()));
				} else {
					salida.setCantidadRegistrosDevueltos(new Long(0));
					salida.setCantidadDeRegistrosTotales(new Long(0));
				}	
				return salida;
				
			} else {
				throw new WebServiceExcepcion(MENSAJE + ": Se ha producido un error en el webservice");
			}	
			
		} catch (JaxWsSoapFaultException e) {
			throw new WebServiceFormatoMensajeExcepcion(MENSAJE + " - Error de formato: " + e);	

		} catch (RemoteAccessException e) {
			throw new WebServiceExcepcion(MENSAJE + ": Falla de conexion: " + e);

		} catch (ParseException e) {
			throw new WebServiceExcepcion(MENSAJE + ": Falla en parseo de fechas: " + e);
		}
	}
	
	/**
	 * 
	 * @param icePassword
	 */
	public void setIcePassword(String icePassword) {
		this.icePassword = icePassword;
	}

	/**
	 * 
	 * @param iceUser
	 */
	public void setIceUser(String iceUser) {
		this.iceUser = iceUser;
	}
	
	/**
	 * 
	 * @param importeEnCentavos
	 * @return
	 */
	private BigDecimal mapearImporte(Long importeEnCentavos) {
		BigDecimal importe = BigDecimal.ZERO;
		
		if (!Validaciones.isObjectNull(importeEnCentavos) && importeEnCentavos != 0l) {
			importe = new BigDecimal(importeEnCentavos).divide(new BigDecimal("100"));
		}
		
		return importe;
	}
	
	/**
	 * 
	 * @param strFecha
	 * @return
	 * @throws ParseException
	 */
	private Date mapearFecha(String strFecha) throws ParseException {
		
		Date fecha = null;
		
		if (!Validaciones.isObjectNull(strFecha)) {
			fecha = Utilidad.deserializeAndFormatDate(strFecha);
		}
		
		return fecha;
	}

}
