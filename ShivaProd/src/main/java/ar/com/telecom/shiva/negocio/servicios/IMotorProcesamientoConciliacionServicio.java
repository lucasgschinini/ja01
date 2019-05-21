package ar.com.telecom.shiva.negocio.servicios;

import java.util.HashMap;
import java.util.List;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteMotorConciliacion;

public interface IMotorProcesamientoConciliacionServicio{

	/**
	 * Metodo que procesa la conciliacion que le viene por parametro.
	 * @param conciliacion
	 * @param cuerpoMailMap
	 * @throws NegocioExcepcion
	 */
	void ejecutarProcesoConciliacion(List<VistaSoporteMotorConciliacion> listaDeConciliaciones, HashMap<String, List<String>> cuerpoMailMap, Mail asuntoMail) throws NegocioExcepcion;

	/**
	 * Metodo que consulta la vista SHV_SOP_MOTOR_CONCILIACION y arma una lista con las conciliaciones
	 * @return
	 * @throws NegocioExcepcion
	 */
//	HashMap<Long, VistaSoporteMotorConciliacion> listarConciliacionesParaProcesar() throws NegocioExcepcion;

	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	List<VistaSoporteMotorConciliacion> listarRegistrosMotorConciliacionPorReglaMenor() throws NegocioExcepcion;
}
