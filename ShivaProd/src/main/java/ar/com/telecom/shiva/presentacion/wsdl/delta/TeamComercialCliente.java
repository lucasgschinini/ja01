package ar.com.telecom.shiva.presentacion.wsdl.delta;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.7.6
 * 2019-05-21T12:28:48.650-03:00
 * Generated source version: 2.7.6
 * 
 */
@WebService(targetNamespace = "http://www.openuri.org/TeamComercialCliente/", name = "TeamComercialCliente")
@XmlSeeAlso({ar.com.telecom.shiva.presentacion.wsdl.delta.header.request.ObjectFactory.class, ar.com.telecom.shiva.presentacion.wsdl.delta.header.response.ObjectFactory.class, ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface TeamComercialCliente {

    @WebResult(name = "consultaTeamComercialResponse", targetNamespace = "http://www.openuri.org/TeamComercialCliente/", partName = "parameters")
    @WebMethod(action = "http://www.openuri.org/TeamComercialCliente/NewOperation")
    public ConsultaTeamComercialResponse consultaTeamComercial(
        @WebParam(partName = "parameters", name = "consultaTeamComercialRequest", targetNamespace = "http://www.openuri.org/TeamComercialCliente/")
        ConsultaTeamComercialRequest parameters
    );
}
