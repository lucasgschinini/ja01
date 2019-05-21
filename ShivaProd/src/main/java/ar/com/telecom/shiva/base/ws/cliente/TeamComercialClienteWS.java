package ar.com.telecom.shiva.base.ws.cliente;

import java.util.Formatter;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.otros.WebServiceExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.EntradaTeamComercialClienteWS;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.delta.Rol;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.SalidaTeamComercialClienteWS;
import ar.com.telecom.shiva.presentacion.wsdl.delta.ConsultaTeamComercialRequest;
import ar.com.telecom.shiva.presentacion.wsdl.delta.ConsultaTeamComercialResponse;
import ar.com.telecom.shiva.presentacion.wsdl.delta.ConsultaTeamComercialResponse.Body;
import ar.com.telecom.shiva.presentacion.wsdl.delta.ConsultarTeamComercialClienteReq;
import ar.com.telecom.shiva.presentacion.wsdl.delta.ConsultarTeamComercialClienteReq.Legado;
import ar.com.telecom.shiva.presentacion.wsdl.delta.ConsultarTeamComercialClienteResp.Miembros;
import ar.com.telecom.shiva.presentacion.wsdl.delta.Error;
import ar.com.telecom.shiva.presentacion.wsdl.delta.TeamComercialCliente;
import ar.com.telecom.shiva.presentacion.wsdl.delta.header.request.HeaderRequest;
import ar.com.telecom.shiva.presentacion.wsdl.delta.header.request.HeaderRequest.Consumer;
import ar.com.telecom.shiva.presentacion.wsdl.delta.header.request.HeaderRequest.Message;
import ar.com.telecom.shiva.presentacion.wsdl.delta.header.request.HeaderRequest.Service;

/**
 * Contiene la salida de la consulta del team comercial realizada en Siebel, para un cliente dado.
 * La estructura del Team Comercial puede variar de acuerdo al tipo de cliente, los roles que se tratan actualmente pueden ser:
 * 
 * 	ANALISTA DE CUENTA DEL CLIENTE (ANALISTA)
 * 	ASESOR MOVIL (ASMOVIL)
 * 	ASISTENTE DE COBRANZAS (ASISCOB)
 * 	ASISTENTE DE CUENTA (ASSCUENT)
 * 	ASISTENTE DE DATOS (ASISDAT)
 * 	ASISTENTE DE VOZ (ASISVOZ)
 * 	COORD ING CUENTA INTEGRAL (COORICI)
 * 	CTRO ESP MOV CEM INCENTIVOS (CEMMOVIN)
 * 	CTRO ESPECIALISTAS MOVILES CEM (CEMMOV)
 * 	DIRECTOR (DIRECTOR)
 * 	EJECUTIVO DE CUENTA (EJECUENT)
 * 	EJECUTIVO DE CUENTA LIQ. (EJELIQI)
 * 	EJECUTIVO DE CUENTA MOVIL (EJEMOVIL)
 * 	EJECUTIVO DE CUENTA MOVIL LIQ. (EMOVSIS)
 * 	EJECUTIVO DE PRODUCTO (EJEPROD)
 * 	EJECUTIVO PRODUCTO DC-ICT (EJEICTSI)
 * 	EJECUTIVO PRODUCTO DC-ICT LIQ. (ECPRICT)
 * 	ESPECIALISTA (ESPECIAL)
 * 	GERENTE COBRANZA (GERCOBR)
 * 	GERENTE DC-ICT (GERICT)
 * 	GERENTE DE SOLUC. Y ALIANZAS (GERALI)
 * 	GERENTE DE VENTAS (GERVEN)
 * 	GERENTE ING CUENTA INTEGRAL (GTEINGC)
 * 	GERENTE ING DC-ICT (GTEICT)
 * 	GERENTE POSVENTA MOVIL (GERPVMO)
 * 	GERENTE PRODUCTO DC-ICT (GTEPRICT)
 * 	GERENTE REGIONAL (GERREG)
 * 	IMPLEMENTADOR (IMPLEM)
 * 	INGENIERO DC-ICT (INGICT)
 * 	INGENIERO DE CUENTA (INGCUENT)
 * 	INGENIERO DE CUENTA LIQ. (INGCUSIS)
 * 	JEFE DE TESORERIA (JT)
 * 	REGISTRACION (REGISTRA)
 * 	RESP EJECUTIVO DE CUENTA (GERCOM)
 * 	RESP EJECUTIVO DE CUENTA LIQ. (GECOMSIS)
 * 	RESPONSABLE ING DC-ICT (RESICT)
 * 	RESPONSABLE MOVIL (RESMOVIL)
 * 	RESPONSABLE MOVIL LIQ. (RMOVSIS)
 * 	RESPONSABLE PRODUCTO DC-ICT (RESPRICT)
 * 	RESPONSABLE PV FACT (RESPVFAC)
 * 	RESPONSABLE PV MOVIL (RESPVMOV)
 * 	RESPONSABLE PV REG (RESPVREG)
 *  
 *  Para cada uno de estos roles, esta clase ofrece metodos particulares que resolverán el retorno de los datos correspondientes
 * 
 * @author u564030
 *
 */
public class TeamComercialClienteWS {
	
	@Autowired 
	TeamComercialCliente teamComercialClienteSoap;
	
	@SuppressWarnings("resource")
	public SalidaTeamComercialClienteWS consultarTeamComercialCliente(EntradaTeamComercialClienteWS eTeamComercialCliente) 
			throws WebServiceExcepcion {
		
		ConsultaTeamComercialRequest parametros = new ConsultaTeamComercialRequest();
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
		message.setPriority(Short.valueOf("1"));
		header.setMessage(message);
		
		Service service = new Service();
		service.setCode("ClienteAsociacionConsultar");
		service.setName("Cliente Asociacion Consultar");
		header.setService(service);
		
		ConsultarTeamComercialClienteReq teamComercialReq = new ConsultarTeamComercialClienteReq();
		if (!Validaciones.isNullOrEmpty(eTeamComercialCliente.getLegadoClienteID())) {
			Legado legado = new Legado();
			
			// Formateo el codigo de cliente para que siempre tenga 10 digitos, con ceros a la izquierda si fuera necesario.
			//legado.setLegadoClienteID(Utilidad.rellenarCerosIzquierda(eTeamComercialCliente.getLegadoClienteID(), 10));
			
			// Formateo el codigo de cliente para que siempre tenga 10 digitos, con ceros a la izquierda si fuera necesario.
			legado.setLegadoClienteID((new Formatter().format("%010d", new Long(eTeamComercialCliente.getLegadoClienteID()))).toString());

			teamComercialReq.setLegado(legado);
		}
		
		parametros.setHeader(header);
		parametros.setConsultarTeamComercialClienteReq(teamComercialReq);
		
		ConsultaTeamComercialResponse respuesta = teamComercialClienteSoap.consultaTeamComercial(parametros);
		
		if (respuesta != null && respuesta.getBody() != null) {
			
			Body bodyResp = respuesta.getBody();
			
			if (bodyResp.getConsultarTeamComercialClienteResp() != null) {
				
				SalidaTeamComercialClienteWS salida = new SalidaTeamComercialClienteWS();
				
				Miembros miembros = bodyResp.getConsultarTeamComercialClienteResp().getMiembros();
				
				if (miembros != null && miembros.getMiembro()!= null) {
					
					if (!miembros.getMiembro().isEmpty()) {
						
						for (ar.com.telecom.shiva.presentacion.wsdl.delta.ConsultarTeamComercialClienteResp.Miembros.Miembro miembro: miembros.getMiembro()) {
							Rol rol = new Rol(miembro.getCodigoRol(), 
											  miembro.getDescripcionRol(), 
											  miembro.getLegajo(), 
											  miembro.getNombreApellido());
							salida.addRol(rol);
						}
					}
					return salida;
				}
			}
			
			if (bodyResp.getError() != null) {
				Error error = bodyResp.getError();
				
				String mensajeError = error.getCodigo() + "," 
								+ error.getDescripcion() + ";"
								+ error.getRazon();
				throw new WebServiceExcepcion(mensajeError);
			}
		}
			
		throw new WebServiceExcepcion("WS TeamComercialCliente: Se ha producido un error en el webservice");
	}

	public TeamComercialCliente getTeamComercialClienteSoap() {
		return teamComercialClienteSoap;
	}

	public void setTeamComercialClienteSoap(
			TeamComercialCliente teamComercialClienteSoap) {
		this.teamComercialClienteSoap = teamComercialClienteSoap;
	}
}
