package ar.com.telecom.shiva.base.jms.datos.salida;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.jms.util.JmsRetorno;
import ar.com.telecom.shiva.base.utils.Validaciones;

@SuppressWarnings("serial")
public class FacConsultaAcuerdoSalida 
	extends JMS {
	
	private JmsRetorno retorno = new JmsRetorno();
	private List<AcuerdoFacturacion> listaAcuerdoFacturacion = new ArrayList<AcuerdoFacturacion>();
  	
	public class AcuerdoFacturacion {
		private Long numeroAcuerdo;
		private EstadoAcuerdoFacturacionEnum estadoAcuerdo;
		private String fechaComienzoAcuerdoFactura;
		private String fechaFinalizacionAcuerdoFactura;
		private String fechaUltimaFactura;
		
		public AcuerdoFacturacion() {
		}
		
		public Long getNumeroAcuerdo() {
			return numeroAcuerdo;
		}

		public void setNumeroAcuerdo(Long numeroAcuerdo) {
			this.numeroAcuerdo = numeroAcuerdo;
		}

		public EstadoAcuerdoFacturacionEnum getEstadoAcuerdo() {
			return estadoAcuerdo;
		}

		public void setEstadoAcuerdo(EstadoAcuerdoFacturacionEnum estadoAcuerdo) {
			this.estadoAcuerdo = estadoAcuerdo;
		}
		
		public String getFechaComienzoAcuerdoFactura() {
			return fechaComienzoAcuerdoFactura;
		}

		public void setFechaComienzoAcuerdoFactura(String fechaComienzoAcuerdoFactura) {
			this.fechaComienzoAcuerdoFactura = fechaComienzoAcuerdoFactura;
		}

		public String getFechaFinalizacionAcuerdoFactura() {
			return fechaFinalizacionAcuerdoFactura;
		}

		public void setFechaFinalizacionAcuerdoFactura(
				String fechaFinalizacionAcuerdoFactura) {
			this.fechaFinalizacionAcuerdoFactura = fechaFinalizacionAcuerdoFactura;
		}

		public String getFechaUltimaFactura() {
			return fechaUltimaFactura;
		}

		public void setFechaUltimaFactura(String fechaUltimaFactura) {
			this.fechaUltimaFactura = fechaUltimaFactura;
		}
		
		public String toString() {
			String str = "";
			if(!Validaciones.isObjectNull(numeroAcuerdo)){
				str += "[numeroAcuerdo: "+numeroAcuerdo+"],";
			} else {
				str += "[numeroAcuerdo: vacio],";
			}
			if(!Validaciones.isObjectNull(estadoAcuerdo)){
				str += "[estadoAcuerdo: "+estadoAcuerdo.descripcion() +"]";	
			} else {
				str += "[estadoAcuerdo: vacio]";	
			}
			if(!Validaciones.isObjectNull(fechaFinalizacionAcuerdoFactura)){
				str += "[fechaFinalizacionAcuerdoFactura: "+ fechaFinalizacionAcuerdoFactura +"]";	
			} else {
				str += "[fechaFinalizacionAcuerdoFactura: vacio]";	
			}
			if(!Validaciones.isObjectNull(fechaUltimaFactura)){
				str += "[fechaUltimaFactura: "+fechaUltimaFactura +"]";	
			} else {
				str += "[fechaUltimaFactura: vacio]";	
			}
			return str;
		}
	}
	
  	/*********************************************************************
	 * Getters & Setters
	 *********************************************************************/
  	
	public JmsRetorno getRetorno() {
		return retorno;
	}

	public void setRetorno(JmsRetorno retorno) {
		this.retorno = retorno;
	}
	
	public List<AcuerdoFacturacion> getListaAcuerdoFacturacion() {
		return listaAcuerdoFacturacion;
	}

	public void setListaAcuerdoFacturacion(List<AcuerdoFacturacion> listaAcuerdoFacturacion) {
		this.listaAcuerdoFacturacion = listaAcuerdoFacturacion;
	}

	public AcuerdoFacturacion getPrimerAcuerdoFacturacion() {
		if (this.listaAcuerdoFacturacion.isEmpty()) {
			return new AcuerdoFacturacion(); 
		}
		return this.listaAcuerdoFacturacion.get(0);
	}
	
	@Override
 	public String toString() {
 		String str = retorno.toString()+ ",";
 	    for (int i = 0; i < listaAcuerdoFacturacion.size(); i++) {
 	    	str += listaAcuerdoFacturacion.get(i).toString();
 	    }
 	    return str;
	}
}
