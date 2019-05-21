package ar.com.telecom.shiva.base.jms.datos.salida;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicControlPaginado;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDebito;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicResultadoConsulta;
import ar.com.telecom.shiva.base.jms.util.JmsCopiaTecnica;

@SuppressWarnings("serial")
public class MicConsultaDeudaSalida 
	extends JMS {
	
	protected JmsCopiaTecnica retorno = new JmsCopiaTecnica();
	protected List<MicDebito> listaDebitos = new ArrayList<MicDebito>();

	protected MicControlPaginado controlPaginado = new MicControlPaginado();
	protected MicResultadoConsulta resultadoConsulta = new MicResultadoConsulta();
	
	protected String informacionAMostrar;
	
	protected String idCobro;
	
	public MicConsultaDeudaSalida(){
		super();
	}
	
	public MicConsultaDeudaSalida(int totalRegistros){
		super();
		this.controlPaginado = new MicControlPaginado();
		this.controlPaginado.setCantidadRegistrosTotales(new Long(String.valueOf(totalRegistros)));
		this.controlPaginado.setCantidadRegistrosRetornados(0L);
	}
	
	/*********************************************************************
	 * Getters & Setters
	 *********************************************************************/
	public List<MicDebito> getListaDebitos() {
		return listaDebitos;
	}

	public void setListaDebitos(List<MicDebito> listaDebitos) {
		this.listaDebitos = listaDebitos;
	}

	public MicControlPaginado getControlPaginado() {
		return controlPaginado;
	}

	public void setControlPaginado(MicControlPaginado controlPaginado) {
		this.controlPaginado = controlPaginado;
	}

	public MicResultadoConsulta getResultadoConsulta() {
		return resultadoConsulta;
	}

	public void setResultadoConsulta(MicResultadoConsulta resultadoConsulta) {
		this.resultadoConsulta = resultadoConsulta;
	}

	public JmsCopiaTecnica getRetorno() {
		return retorno;
	}

	public void setRetorno(JmsCopiaTecnica retorno) {
		this.retorno = retorno;
	}

	public String getInformacionAMostrar() {
		return informacionAMostrar;
	}

	public void setInformacionAMostrar(String informacionAMostrar) {
		this.informacionAMostrar = informacionAMostrar;
	}

	public String getIdCobro() {
		return idCobro;
	}

	public void setIdCobro(String idCobro) {
		this.idCobro = idCobro;
	}
	
}
