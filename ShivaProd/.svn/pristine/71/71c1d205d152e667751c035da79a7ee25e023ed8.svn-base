package ar.com.telecom.shiva.base.jms.datos.salida;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicControlPaginado;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicCredito;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicResultadoConsulta;
import ar.com.telecom.shiva.base.jms.util.JmsCopiaTecnica;

@SuppressWarnings("serial")
public class MicConsultaCreditoSalida 
	extends JMS {
	
	protected JmsCopiaTecnica retorno = new JmsCopiaTecnica();
	protected List<MicCredito> listaCreditos = new ArrayList<MicCredito>();
	
	protected MicControlPaginado controlPaginado 		= new MicControlPaginado();
	protected MicResultadoConsulta resultadoConsulta 	= new MicResultadoConsulta();

	
	public MicConsultaCreditoSalida(){
		super();
	}
	
	public MicConsultaCreditoSalida(int totalRegistros){
		super();
		this.controlPaginado = new MicControlPaginado();
		this.controlPaginado.setCantidadRegistrosTotales(new Long(String.valueOf(totalRegistros)));
		this.controlPaginado.setCantidadRegistrosRetornados(0L);
	}
	
	/*********************************************************************
	 * Getters & Setters
	 *********************************************************************/
	
	public List<MicCredito> getListaCreditos() {
		return listaCreditos;
	}
	public void setListaCreditos(List<MicCredito> listaCreditos) {
		this.listaCreditos = listaCreditos;
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
}
