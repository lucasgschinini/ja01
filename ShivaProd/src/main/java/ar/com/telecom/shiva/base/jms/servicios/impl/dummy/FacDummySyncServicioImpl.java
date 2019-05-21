package ar.com.telecom.shiva.base.jms.servicios.impl.dummy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.excepciones.otros.JmsExcepcion;
import ar.com.telecom.shiva.base.jms.IControlJms;
import ar.com.telecom.shiva.base.jms.datos.salida.FacConsultaAcuerdoSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.FacConsultaAcuerdoSalida.AcuerdoFacturacion;
import ar.com.telecom.shiva.base.jms.datos.salida.FacConsultaClienteSalida;
import ar.com.telecom.shiva.base.jms.servicios.IFacJmsSyncServicio;
import ar.com.telecom.shiva.base.mapeadores.MapeadorJMS;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.AcuerdoLegado;

public class FacDummySyncServicioImpl implements IFacJmsSyncServicio {

	@Autowired
	IControlJms facConsultasControlMQ;
	
	@Autowired 
	MapeadorJMS facConsultaAcuerdoMapeoJMS;

	@Autowired 
	MapeadorJMS facConsultaAcuerdoxClienteMapeoJMS;
	
	@Autowired 
	MapeadorJMS facConsultaClienteMapeoJMS;
	
	@Override
	public FacConsultaAcuerdoSalida consultarAcuerdo(Long numeroAcuerdo)
			throws JmsExcepcion {
		
		FacConsultaAcuerdoSalida jms = new FacConsultaAcuerdoSalida();
		AcuerdoFacturacion acuerdo = jms.new AcuerdoFacturacion();
		acuerdo.setNumeroAcuerdo(numeroAcuerdo);
		acuerdo.setEstadoAcuerdo(EstadoAcuerdoFacturacionEnum.ACTIVO);
		List<AcuerdoFacturacion> listaAcuerdoFacturacion = new ArrayList<FacConsultaAcuerdoSalida.AcuerdoFacturacion>();
		listaAcuerdoFacturacion.add(acuerdo);
		jms.setListaAcuerdoFacturacion(listaAcuerdoFacturacion);
		return jms;
	}

	@Override
	public FacConsultaAcuerdoSalida consultarAcuerdoxLinea(String numeroLinea)
			throws JmsExcepcion {
		
		FacConsultaAcuerdoSalida jms = new FacConsultaAcuerdoSalida();
		AcuerdoFacturacion acuerdo = jms.new AcuerdoFacturacion();
		acuerdo.setNumeroAcuerdo(new Long("2"));
		acuerdo.setEstadoAcuerdo(EstadoAcuerdoFacturacionEnum.ACTIVO);
		List<AcuerdoFacturacion> listaAcuerdoFacturacion = new ArrayList<FacConsultaAcuerdoSalida.AcuerdoFacturacion>();
		listaAcuerdoFacturacion.add(acuerdo);
		jms.setListaAcuerdoFacturacion(listaAcuerdoFacturacion);
		return jms;
	}

	@Override
	public FacConsultaAcuerdoSalida consultarAcuerdoxCliente(Long numeroCliente)
			throws JmsExcepcion {
		
		FacConsultaAcuerdoSalida jms = new FacConsultaAcuerdoSalida();
		AcuerdoFacturacion acuerdo = jms.new AcuerdoFacturacion();
		acuerdo.setNumeroAcuerdo(new Long("3"));
		acuerdo.setEstadoAcuerdo(EstadoAcuerdoFacturacionEnum.ACTIVO);
		List<AcuerdoFacturacion> listaAcuerdoFacturacion = new ArrayList<FacConsultaAcuerdoSalida.AcuerdoFacturacion>();
		listaAcuerdoFacturacion.add(acuerdo);
		jms.setListaAcuerdoFacturacion(listaAcuerdoFacturacion);
		return jms;
	}

	@Override
	public FacConsultaClienteSalida consultarClientexAcuerdo(
			String numeroAcuerdo) throws JmsExcepcion {
		
		FacConsultaClienteSalida jms = new FacConsultaClienteSalida();
		jms.setNumeroCliente(new Long(4));
		return jms;
	}
	
	/************************************************************************
	 * Getters/Setters
	 ***********************************************************************/
	public IControlJms getFacConsultasControlMQ() {
		return facConsultasControlMQ;
	}

	public void setFacConsultasControlMQ(
			IControlJms facConsultasControlMQ) {
		this.facConsultasControlMQ = facConsultasControlMQ;
	}

	public MapeadorJMS getFacConsultaAcuerdoMapeoJMS() {
		return facConsultaAcuerdoMapeoJMS;
	}

	public void setFacConsultaAcuerdoMapeoJMS(MapeadorJMS facConsultaAcuerdoMapeoJMS) {
		this.facConsultaAcuerdoMapeoJMS = facConsultaAcuerdoMapeoJMS;
	}

	public MapeadorJMS getFacConsultaClienteMapeoJMS() {
		return facConsultaClienteMapeoJMS;
	}

	public void setFacConsultaClienteMapeoJMS(MapeadorJMS facConsultaClienteMapeoJMS) {
		this.facConsultaClienteMapeoJMS = facConsultaClienteMapeoJMS;
	}

	public MapeadorJMS getFacConsultaAcuerdoxClienteMapeoJMS() {
		return facConsultaAcuerdoxClienteMapeoJMS;
	}

	public void setFacConsultaAcuerdoxClienteMapeoJMS(
			MapeadorJMS facConsultaAcuerdoxClienteMapeoJMS) {
		this.facConsultaAcuerdoxClienteMapeoJMS = facConsultaAcuerdoxClienteMapeoJMS;
	}

	@Override
	public AcuerdoLegado buscarPrimerAcuerdoActivo(Long idClienteLegado)
			throws JmsExcepcion {
		// TODO Auto-generated method stub
		return null;
	}	

}
