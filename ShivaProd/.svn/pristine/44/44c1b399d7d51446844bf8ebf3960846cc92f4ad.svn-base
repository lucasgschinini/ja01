package ar.com.telecom.shiva.base.ws.cliente;

import java.util.Formatter;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.excepciones.otros.WebServiceExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaSiebelConsultarClienteWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaSiebelConsultarClienteWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.siebel.RespuestaClienteCRM;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.siebel.RespuestaDomicilioCRM;
import ar.com.telecom.shiva.presentacion.wsdl.siebel.ClientRequest;
import ar.com.telecom.shiva.presentacion.wsdl.siebel.ClientResponse;
import ar.com.telecom.shiva.presentacion.wsdl.siebel.ClienteConsultarSoap;
import ar.com.telecom.shiva.presentacion.wsdl.siebel.ConsultarClienteReq;
import ar.com.telecom.shiva.presentacion.wsdl.siebel.ConsultarClienteReq.Legado;
import ar.com.telecom.shiva.presentacion.wsdl.siebel.ConsultarClienteResp.ClientesCRM;
import ar.com.telecom.shiva.presentacion.wsdl.siebel.RequestMessage;
import ar.com.telecom.shiva.presentacion.wsdl.siebel.RequestMessage.Body;
import ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.cliente.ClDomicilios;
import ar.com.telecom.shiva.presentacion.wsdl.siebel.diccionario.cliente.ClienteCRM;
import ar.com.telecom.shiva.presentacion.wsdl.siebel.header.request.HeaderRequest;
import ar.com.telecom.shiva.presentacion.wsdl.siebel.header.request.HeaderRequest.Consumer;
import ar.com.telecom.shiva.presentacion.wsdl.siebel.header.request.HeaderRequest.Message;
import ar.com.telecom.shiva.presentacion.wsdl.siebel.header.request.HeaderRequest.Service;

public class ClienteConsultarSiebelWS {
	
	private static final String ERROR_SIN_DATOS = "BIO-D-004"; 
	
	@Autowired 
	ClienteConsultarSoap clienteConsultarSoap;
	
	@SuppressWarnings("resource")
	public SalidaSiebelConsultarClienteWS consultarClientes(EntradaSiebelConsultarClienteWS entrada) 
			throws WebServiceExcepcion {
		
		boolean consultarPorLegado = false;
		ClientRequest parametros = new ClientRequest();
		RequestMessage value = new RequestMessage();
		HeaderRequest header = new HeaderRequest();
		
		Consumer consumer = new Consumer();
		consumer.setCode("SHIVA");
		consumer.setName("shiva");
		consumer.setCredentials("");
		consumer.setUserID("");
		header.setConsumer(consumer);
		
		Message message = new Message();
		message.setMessageId(Utilidad.getUUIDCalendar().toString());
		message.setConversationId(Utilidad.getUUIDCalendar().toString());
		header.setMessage(message);
		
		Service service = new Service();
		service.setCode("ClienteConsultar");
		service.setName("consulta de cliente");
		header.setService(service);
		
		ConsultarClienteReq clienteReq = new ConsultarClienteReq();
		
		if (!Validaciones.isNullOrEmpty(entrada.getLegadoClienteID())) {
			Legado legado = new Legado();
			
			// Formateo el codigo de cliente para que siempre tenga 10 digitos, con ceros a la izquierda si fuera necesario.
			//legado.setLegadoClienteID(Utilidad.rellenarCerosIzquierda(codigoClienteLegajo, 10));
			// Formateo el codigo de cliente para que siempre tenga 10 digitos, con ceros a la izquierda si fuera necesario.
			legado.setLegadoClienteID((new Formatter().format("%010d", new Long(entrada.getLegadoClienteID()))).toString());
			
			clienteReq.setLegado(legado);
			
			consultarPorLegado = true;
		}
		
		if (!Validaciones.isNullOrEmpty(entrada.getCrmHoldingID())) {
			clienteReq.setCrmHoldingID(entrada.getCrmHoldingID());
		}
		
		if (!Validaciones.isNullOrEmpty(entrada.getCrmAgenciaNegocio())) {
			clienteReq.setCrmAgenciaNegocio(entrada.getCrmAgenciaNegocio());
		}
		
		if (!Validaciones.isNullOrEmpty(entrada.getCrmClienteCUIT())) {
			clienteReq.setCrmClienteCUIT(entrada.getCrmClienteCUIT());
		}
		
		Body body = new Body();
		body.setConsultarClienteReq(clienteReq);
		
		value.setHeader(header);
		value.setBody(body);
		parametros.setRequestMessage(value);
		
		ClientResponse respuesta = clienteConsultarSoap.clientRequest(parametros);
		
		if (respuesta != null
				&& respuesta.getResponseMessage() != null 
				&& respuesta.getResponseMessage().getBody() != null) {
			
			ar.com.telecom.shiva.presentacion.wsdl.siebel.ResponseMessage.Body bodyResp = 
					respuesta.getResponseMessage().getBody();
			
			if (bodyResp.getConsultarClienteResp() != null) {
				
				SalidaSiebelConsultarClienteWS datosSalida = new SalidaSiebelConsultarClienteWS();
				
				ClientesCRM clientesCRM = bodyResp.getConsultarClienteResp().getClientesCRM();
				
				if (clientesCRM != null && clientesCRM.getClienteCRM() != null) {
					
					/**
					 *	<cli:clienteCRM xmlns:cli="http://www.telecom.com.ar/XMLSchema/bios/clienteDicc">
					 */

					for (ClienteCRM clienteCRM: clientesCRM.getClienteCRM()) {
						
						/**
						 * <cli:cuenta>
						 */

						if (clienteCRM != null && clienteCRM.getCuenta()!= null) {
							
							//Si la consulta fue por el legado cliente id
							if (consultarPorLegado) {
								RespuestaClienteCRM datos = new RespuestaClienteCRM();
								datos.setCodigoClienteLegado(clienteReq.getLegado().getLegadoClienteID());
								datos = cargarDatosGenerales(datos, clienteCRM);
								
								datosSalida.getListaClientes().put(datos.getCodigoClienteLegado(), datos);
							} else {
								
								/**
				                 * <res:legados>
	                             * 		<res:legado id="GIRAFE" clienteId="0000007005" ofId="PRE"/>
	                           	 * </res:legados>
				                 */
								
								if (!Validaciones.isObjectNull(clienteCRM.getCuenta().getLegados()) 
										&& !Validaciones.isObjectNull(clienteCRM.getCuenta().getLegados().getLegado())) {
									
									for (ClienteCRM.Cuenta.Legados.Legado legado : clienteCRM.getCuenta().getLegados().getLegado()) {
										
										RespuestaClienteCRM datos = new RespuestaClienteCRM();
										if (legado!= null && !Validaciones.isNullOrEmpty(legado.getClienteId())) {
											datos.setCodigoClienteLegado(legado.getClienteId());
										} else {
											throw new WebServiceExcepcion("WS ClienteConsultar: Legado o legado.clienteId se encuentra vacio");
										}
										datos = cargarDatosGenerales(datos, clienteCRM);
										
										datosSalida.getListaClientes().put(datos.getCodigoClienteLegado(), datos);
									}
									
								} else {
									throw new WebServiceExcepcion("WS ClienteConsultar: Legados se encuentra vacio");
								}
								
							} // fin-else consultar por legado
							
						} else {
							throw new WebServiceExcepcion("WS ClienteConsultar: clienteCRM o clienteCRM.getCuenta se encuentran vacios");
						}
					} // Fin for
					
				} // fin clientesCRM
				
				return datosSalida;
			} // Fin bodyResp.getConsultarClienteResp()
		
			if (bodyResp.getError() != null) {
				ar.com.telecom.shiva.presentacion.wsdl.siebel.Error error = bodyResp.getError();
				
				if (error.getCodigo().equalsIgnoreCase(ERROR_SIN_DATOS)) {
					return null;
				}
				
				String mensajeError = error.getCodigo() + "," 
								+ error.getDescripcion() + ";"
								+ error.getRazon();
				throw new WebServiceExcepcion(mensajeError);
			}
		}
			
		throw new WebServiceExcepcion("WS ClienteConsultar: clientResponse o responseMessage o body o consultarClienteResp se encuentran vacios");
	}
	
	
	/**
	 * Me permite cargar datos generales
	 * @param datos
	 * @param clienteCRM
	 * @return
	 * @throws WebServiceExcepcion
	 */
	private RespuestaClienteCRM cargarDatosGenerales(RespuestaClienteCRM datos, ClienteCRM clienteCRM) throws WebServiceExcepcion {
		String codigoClienteAgrupador = clienteCRM.getCuenta().getNumeroCliente();
		if (!Validaciones.isNullOrEmpty(codigoClienteAgrupador)) {
			datos.setCodigoClienteAgrupador(codigoClienteAgrupador);
		} else {
			throw new WebServiceExcepcion("WS ClienteConsultar: codigoClienteAgrupador se encuentra vacio");
		}
		
		String razonSocial= "";
		if(Validaciones.isNullOrEmpty(clienteCRM.getCuenta().getRazonSocial())){
			if(Validaciones.isNullOrEmpty(clienteCRM.getCuenta().getApellido())){
				razonSocial = clienteCRM.getCuenta().getNombre();
			}else{
				razonSocial = clienteCRM.getCuenta().getApellido();
				if(!Validaciones.isNullOrEmpty(clienteCRM.getCuenta().getNombre())){
					razonSocial += ", " + clienteCRM.getCuenta().getNombre();
				}
			}
		}else{
			razonSocial = clienteCRM.getCuenta().getRazonSocial();
		}
		if (!Validaciones.isNullOrEmpty(razonSocial)) {
			razonSocial = razonSocial.replace("&amp;", "&");
			razonSocial = razonSocial.replace("\'", "´");
		}
		datos.setRazonSocialClienteAgrupador(razonSocial);
		
		/**
		<cli:clDocumento>
			<cli:tipo>CUT</cli:tipo>
			<cli:numero>30709723770</cli:numero>
			<cli:codigoPais />
			<cli:descripcionPaisDocumento />
			<cli:descripcionTipoDocumento>CUIT</cli:descripcionTipoDocumento>
		</cli:clDocumento>
		*/
		String cuit= "";
		if(!Validaciones.isObjectNull(clienteCRM.getCuenta().getClDocumento())
				&& !Validaciones.isNullOrEmpty(clienteCRM.getCuenta().getClDocumento().getTipo())
				&& "CUT".equalsIgnoreCase(clienteCRM.getCuenta().getClDocumento().getTipo())
				&& !Validaciones.isNullOrEmpty(clienteCRM.getCuenta().getClDocumento().getNumero())) 
		{
			cuit = clienteCRM.getCuenta().getClDocumento().getNumero();
		}
		datos.setCuit(cuit);
		
		/**
		<cli:nombreCliente />
		*/
		String nombreCliente="";
		if (!Validaciones.isNullOrEmpty(clienteCRM.getPerfil().getNombreCliente())) {
			nombreCliente = clienteCRM.getPerfil().getNombreCliente();
			if (!Validaciones.isNullOrEmpty(nombreCliente)) {
				nombreCliente = nombreCliente.replace("&amp;", "&");
				nombreCliente = nombreCliente.replace("\'", "´");
			}
		}
		
		/**
		 name="holdingFlag"
		 name="numeroHolding"
		 name="nombreHolding" 
		 */
		String numeroHolding= "";
		if(!Validaciones.isNullOrEmpty(clienteCRM.getCuenta().getNumeroHolding())){
			numeroHolding = clienteCRM.getCuenta().getNumeroHolding();
		}
		String nombreHolding= "";
		if(!Validaciones.isNullOrEmpty(clienteCRM.getCuenta().getNombreHolding())){
			nombreHolding = clienteCRM.getCuenta().getNombreHolding();
		}
		if (!Validaciones.isNullOrEmpty(nombreHolding)) {
			nombreHolding = nombreHolding.replace("&amp;", "&");
			nombreHolding = nombreHolding.replace("\'", "´");
		}
		//Logica para tratamiento de holding
		if (SiNoEnum.SI.getDescripcionCortaIngles().equalsIgnoreCase(clienteCRM.getCuenta().getHoldingFlag())) {
			datos.setNumeroHolding(codigoClienteAgrupador);
			datos.setNombreHolding(nombreCliente);
		} 
		if (!SiNoEnum.SI.getDescripcionCortaIngles().equalsIgnoreCase(clienteCRM.getCuenta().getHoldingFlag())
				&& !Validaciones.isNullOrEmpty(numeroHolding)
				&& !Validaciones.isNullOrEmpty(nombreHolding)) {
			datos.setNumeroHolding(numeroHolding);
			datos.setNombreHolding(nombreHolding);
		}
				
		/**
		<cli:nombreAgenciaNegocio /> --> Descripcion 
		<cli:numeroAgenciaNegocio /> --> Agencia negocio
		*/
		if (clienteCRM.getCuenta().getAgenciaNegocio()!=null) {
			String descripcionAgenciaNegocio = null;
			if(!Validaciones.isNullOrEmpty(clienteCRM.getCuenta().getAgenciaNegocio().getNombreAgenciaNegocio())) {
				descripcionAgenciaNegocio = clienteCRM.getCuenta().getAgenciaNegocio().getNombreAgenciaNegocio();
			}
			String agenciaNegocio = null;
			if(!Validaciones.isNullOrEmpty(clienteCRM.getCuenta().getAgenciaNegocio().getNumeroAgenciaNegocio())) {
				agenciaNegocio = clienteCRM.getCuenta().getAgenciaNegocio().getNumeroAgenciaNegocio();
			}
			
			//Logica para tratamiento de agencia negocio
			if (SiNoEnum.SI.getDescripcionCortaIngles().equalsIgnoreCase(clienteCRM.getCuenta().getAgenciaNegocio().getCabeceraFlag())) {
				datos.setAgenciaNegocio(codigoClienteAgrupador);
				datos.setDescripcionAgenciaNegocio(nombreCliente);
			} 
			if (!SiNoEnum.SI.getDescripcionCortaIngles().equalsIgnoreCase(clienteCRM.getCuenta().getAgenciaNegocio().getCabeceraFlag())
					&& !Validaciones.isNullOrEmpty(descripcionAgenciaNegocio)
					&& !Validaciones.isNullOrEmpty(agenciaNegocio)) {
				
				datos.setAgenciaNegocio(agenciaNegocio);
				datos.setDescripcionAgenciaNegocio(descripcionAgenciaNegocio);
				
			}
		}
		
		/**
		 * Nota: Segun en Siebel, si no devuelve agenciaNegocio, no tendremos agrupadorSegmentoNegocio 
		 * name="agrupadorSegmentoNegocio"
		 */
		String segmentoAgenciaNegocio= "";
		if(!Validaciones.isObjectNull(clienteCRM.getCuenta())
				&& !Validaciones.isNullOrEmpty(clienteCRM.getCuenta().getAgrupadorSegmentoNegocio())) {
			segmentoAgenciaNegocio = clienteCRM.getCuenta().getAgrupadorSegmentoNegocio();
		}
		datos.setSegmentoAgencia(segmentoAgenciaNegocio);
		
		
		/**
		 * <cli:provincia>
		 */
		//u562163
		if(clienteCRM != null && clienteCRM.getClDomiciliosFinca()!= null){
			datos.setIdProvincia(clienteCRM.getClDomiciliosFinca().getDomicilioFinca().get(0).getProvincia().getCodigo());
		}
		/**
		 * <cli:domicilio tipo="Legal"> utilizo este para los datos generados para los pdf de sap
		 * u578936 Max
		 */
		if (!Validaciones.isObjectNull(clienteCRM.getClDomicilios()) && !clienteCRM.getClDomicilios().getDomicilio().isEmpty()) {
			ClDomicilios.Domicilio domicilio = clienteCRM.getClDomicilios().getDomicilio().get(0);
			datos.setDomicilio(new RespuestaDomicilioCRM());
			datos.getDomicilio().setCodigoCiudad(domicilio.getCodigoCiudad());
			datos.getDomicilio().setDescLocalidad(domicilio.getDescLocalidad());
			datos.getDomicilio().setDescProvincia(domicilio.getDescProvincia());
			datos.getDomicilio().setCodigoCalle(domicilio.getCodigoCalle());
			datos.getDomicilio().setNombreCalle(domicilio.getNombreCalle());
			datos.getDomicilio().setAltura(domicilio.getAltura());
			datos.getDomicilio().setNombreEdificio(domicilio.getNombreEdificio());
			datos.getDomicilio().setEscalera(domicilio.getEscalera());
			datos.getDomicilio().setPiso(domicilio.getPiso());
			datos.getDomicilio().setPuerta(domicilio.getPuerta());
			datos.getDomicilio().setCodigoPostal(domicilio.getCodigoPostal());
		}
		return datos;
	}
}
