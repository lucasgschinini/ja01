package ar.com.telecom.shiva.base.jms.servicios;

import ar.com.telecom.shiva.base.excepciones.otros.JmsExcepcion;
import ar.com.telecom.shiva.base.jms.datos.salida.FacConsultaAcuerdoSalida;
import ar.com.telecom.shiva.base.jms.datos.salida.FacConsultaClienteSalida;
import ar.com.telecom.shiva.negocio.dto.cobros.simulacion.AcuerdoLegado;

public interface IFacJmsSyncServicio extends IJmsServicio {
	
	/**
	 * Permite consultar el acuerdo por el numero de acuerdo (I3)
	 * @throws JmsExcepcion
	 */
	public FacConsultaAcuerdoSalida consultarAcuerdo(Long numeroAcuerdo)  throws JmsExcepcion;
	
	/**
	 * Permite consultar el acuerdo por el numero de linea (A3)
	 * @throws JmsExcepcion
	 */
	public FacConsultaAcuerdoSalida consultarAcuerdoxLinea(String numeroLinea)  throws JmsExcepcion;
	
	/**
	 * Permite consultar el acuerdo por el número de cliente (B3)
	 * @throws JmsExcepcion
	 */
	public FacConsultaAcuerdoSalida consultarAcuerdoxCliente(Long numeroCliente)  throws JmsExcepcion;
	
	/**
	 * Permite consultar por el acuerdo por el numero de acuerdo (I1)
	 * @throws JmsExcepcion
	 */
	public FacConsultaClienteSalida consultarClientexAcuerdo(String numeroAcuerdo)  throws JmsExcepcion;
	
	/**
	 * Obtiene el primer acuerdo activo
	 * @param idClienteLegado
	 * @return AcuerdoLegado
	 * @throws JmsExcepcion
	 */
	public AcuerdoLegado buscarPrimerAcuerdoActivo(Long idClienteLegado) throws JmsExcepcion;
}
