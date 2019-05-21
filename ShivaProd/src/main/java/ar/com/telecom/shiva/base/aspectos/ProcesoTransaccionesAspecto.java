package ar.com.telecom.shiva.base.aspectos;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import ar.com.telecom.shiva.base.anotaciones.ControlProcesoTransacciones;
import ar.com.telecom.shiva.base.aspectos.util.Invocacion;
import ar.com.telecom.shiva.base.excepciones.ProcesoTransaccionesExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.utils.singleton.ControlVariablesSingleton;
import ar.com.telecom.shiva.persistencia.dao.IProcesoTransaccionesDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvTransaccionesProceso;

@Aspect
public class ProcesoTransaccionesAspecto {	

	/**
	 * 
	 */
	@Autowired
	private IProcesoTransaccionesDao procesoTransaccionesDao;
	
	/**
	 * 
	 */
	private Map<String, Map<String, Invocacion>> usuarioInvocaciones = new HashMap<String, Map<String, Invocacion>>();
	
	/**
	 * Verifica la cantidad de invocaciones que realizó un usuario a un proceso. Si supero la misma, 
	 * se dispara una excepción dando cuenta de esta situación.
	 * @param joinPoint
	 * @param cco
	 * @throws ProcesoTransaccionesExcepcion
	 */
	@Before("execution(public * *(..)) && @annotation(cco)")
	public void chequearOperacionesHora(JoinPoint joinPoint, ControlProcesoTransacciones cco) throws ProcesoTransaccionesExcepcion {
		boolean throwExcepcion = false;
		UserDetails userDetails = 
				(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = userDetails.getUsername().trim();
		String strProceso = joinPoint.getSignature().getName().trim();
		try {
			ShvTransaccionesProceso proceso = this.procesoTransaccionesDao.buscarPorNombreDeProceso(strProceso);
			Date fechaActual = Utilidad.obtenerFechaActual();
			if (proceso != null && proceso.getHabilitado()) {
				if (this.usuarioInvocaciones.containsKey(userName)
						&& this.usuarioInvocaciones.get(userName).containsKey(strProceso)) {
					if (Utilidad.seEncuentraEnRango(this.usuarioInvocaciones.get(userName).get(strProceso).getFechaPrimeraInvocacion(), 
							Utilidad.sumMinutos(this.usuarioInvocaciones.get(userName).get(strProceso).getFechaPrimeraInvocacion(), proceso.getMinutos()), 
							fechaActual)) {
						if (this.usuarioInvocaciones.get(userName).get(strProceso).getCantidad() < proceso.getTransacciones()) {
							this.usuarioInvocaciones.get(userName).get(strProceso).incrementarCantidad();
						} else {
							throwExcepcion = true;
						}
					} else {
						this.usuarioInvocaciones.get(userName).get(strProceso).setCantidad(0);
						this.usuarioInvocaciones.get(userName).get(strProceso).incrementarCantidad();
						this.usuarioInvocaciones.get(userName).get(strProceso).setFechaPrimeraInvocacion(fechaActual);
					}
				} else {
					if (!this.usuarioInvocaciones.containsKey(userName)) {
						this.usuarioInvocaciones.put(userName, new HashMap<String, Invocacion>());
					}
					this.usuarioInvocaciones.get(userName).put(strProceso, new Invocacion(1, Utilidad.obtenerFechaActual()));
				}
			}
		} catch (Exception e) {
			Traza.error(getClass(), "Ocurrió un error al chequear la invocación del "
					+ "servicio " + strProceso + "por parte del usuario " + userName, e.getCause());
			if (this.usuarioInvocaciones.containsKey(userName)
					&& this.usuarioInvocaciones.get(userName).containsKey(strProceso)) {
				this.usuarioInvocaciones.get(userName).remove(strProceso);
			}
		}
		if (throwExcepcion) {
			String message = "El usuario " + userName + " a superado la cantidad de invocaciones "
					+ "permitidas del servicio " + strProceso;
			throw new ProcesoTransaccionesExcepcion(message);
		}
	}
	
	/**
	 * Limpiar los datos que se encuentran en memoria 
	 */
	public void limpiarDatos() {
		try {
			Traza.controlTransaccion(getClass(), "Comienzo - Limpieza de los datos");	
			for (String key : this.usuarioInvocaciones.keySet()) {
				this.usuarioInvocaciones.get(key).clear();
			}
			this.usuarioInvocaciones.clear();
			ControlVariablesSingleton.getUsuarioIntentosLogin().clear();
			ControlVariablesSingleton.getPassIntentosLogin().clear();
		} catch (Exception e) {
			Traza.error(getClass(), "Ocurrió un error al limpiar datos", e.getCause());
		}
		Traza.controlTransaccion(getClass(), "Fin - Limpieza de los datos");	
	}

	public IProcesoTransaccionesDao getProcesoTransaccionesDao() {
		return procesoTransaccionesDao;
	}

	public void setProcesoTransaccionesDao(
			IProcesoTransaccionesDao procesoTransaccionesDao) {
		this.procesoTransaccionesDao = procesoTransaccionesDao;
	}

	public Map<String, Map<String, Invocacion>> getUsuarioInvocaciones() {
		return usuarioInvocaciones;
	}

	public void setUsuarioInvocaciones(
			Map<String, Map<String, Invocacion>> usuarioInvocaciones) {
		this.usuarioInvocaciones = usuarioInvocaciones;
	}
	
}