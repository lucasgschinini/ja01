package ar.com.telecom.shiva.test.runner;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaGestionaEnum;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.ITareaServicio;
import ar.com.telecom.shiva.persistencia.dao.ITalonarioDao;
import ar.com.telecom.shiva.persistencia.dao.IValorDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvTalTalonario;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.presentacion.bean.dto.TareaDto;

public class TareaTestRunner {

	private TareaTestRunner(){ 
	}
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		
		//asignarTalonarioAlSupervisor(new Integer(136));
		//rendirTalonario(new Integer(142));
		//crearAltaAvisoPago(439);
		//crearAltaAvisoPago(186);
		//crearAltaAvisoPago(187);
		//crearAltaAvisoPago(188);
		//crearAltaAvisoPago(202);
		
		crearAltaAvisoPago(286);
		crearAltaAvisoPago(294);
		crearAltaAvisoPago(438);
		crearAltaAvisoPago(440);
		System.exit(0);
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	private static void crearAltaAvisoPago(Integer idValor) {
		try {
			ITareaServicio tareaServicio = (ITareaServicio) Configuracion.getBeanBatch("tareaServicio");
			IValorDao valorDao = (IValorDao) Configuracion.getBeanBatch("valorDao");
			
			System.out.println("Crear una tarea de un valor: Alta Aviso de Pago ------");
			
			ShvValValor valor = valorDao.buscarValor(idValor.toString());
			
			TareaDto tarea = new TareaDto();
			tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
			tarea.setIdWorkflow(valor.getWorkFlow().getIdWorkflow());
			tarea.setTipoTarea(TipoTareaEnum.CONF_AVISO_PAGO);
			tarea.setFechaCreacion(valor.getWorkFlow().getFechaUltimaModificacion());
			tarea.setUsuarioCreacion(valor.getWorkFlow().getUsuarioAlta());
			tarea.setPerfilAsignacion(TipoPerfilEnum.ADMIN_VALOR.descripcion());
			tarea.setGestionaSobre(tarea.getTipoTareaGestionaPorIdTipoValor(
					valor.getTipoValor()!=null?valor.getTipoValor().getIdTipoValor():0));
			
			//se mostrará “Nro. de Valor”, “Nro. de Cliente” y  “Razón Social”
			String informacionAdicional = "Cliente: " + valor.getIdClienteLegado() + " - " + valor.getRazonSocialClientePerfil() + "|";
			informacionAdicional+= valor.getNumeroValor();
			
			tarea.setIdValor(Long.valueOf(valor.getIdValor()));
			
			tarea.setAsuntoMail(TipoTareaEnum.CONF_AVISO_PAGO.estadoPendienteDescripcion() + " - Valor: " + valor.getIdValor());
			tarea.setCuerpoMail(informacionAdicional);
			
			tareaServicio.crearTareaTalonario(tarea);
			
		} catch (Throwable e) {
			Traza.error(TareaTestRunner.class, "Se ha producido un error", e);
		}
	}
	
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	private static void rendirTalonario(Integer idTalonario) {
		try {
			ITareaServicio tareaServicio = (ITareaServicio) Configuracion.getBeanBatch("tareaServicio");
			ITalonarioDao talonarioDao = (ITalonarioDao) Configuracion.getBeanBatch("talonarioDao");
			
			System.out.println("Crear una tarea de un talonario: RendirTalonario ------");
			
			ShvTalTalonario talonario = talonarioDao.buscarTalonario(idTalonario);
			
			String rango = "Rango:"+ Utilidad.rellenarCerosIzquierda(talonario.getNroSerie().toString(), Constantes.LONGITUD_NRO_SERIE) + "-" +
					Utilidad.rellenarCerosIzquierda(talonario.getRangoDesde().toString(), Constantes.LONGITUD_NRO_RECIBO) 
					+ " a " + Utilidad.rellenarCerosIzquierda(talonario.getNroSerie().toString(), Constantes.LONGITUD_NRO_SERIE) + "-" + 
					Utilidad.rellenarCerosIzquierda(talonario.getRangoHasta().toString(), Constantes.LONGITUD_NRO_RECIBO);
			
			TareaDto tarea = new TareaDto();
			tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
			tarea.setIdWorkflow(talonario.getWorkflow().getIdWorkflow());
			tarea.setTipoTarea(TipoTareaEnum.AUTR_REND_TAL);
			tarea.setFechaCreacion(talonario.getWorkflow().getFechaUltimaModificacion());
			tarea.setUsuarioCreacion(talonario.getUsuarioAlta());
			tarea.setPerfilAsignacion(Constantes.ADMIN_TALONARIO);
			
			tarea.setGestionaSobre(TipoTareaGestionaEnum.TALONARIO);
			tarea.setIdTalonario(Long.valueOf(talonario.getIdTalonario()));
			
			String asuntoMail = Utilidad.reemplazarMensajes(Mensajes.TALONARIO_RENDIDO,talonario.getEmpresa().getDescripcion(), rango);
			tarea.setAsuntoMail(asuntoMail);
			
			tareaServicio.crearTareaTalonario(tarea);
			
		} catch (Throwable e) {
			Traza.error(TareaTestRunner.class, "Se ha producido un error", e);
		}
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	private static void asignarTalonarioAlSupervisor(Integer idTalonario) {
		try {
			ITareaServicio tareaServicio = (ITareaServicio) Configuracion.getBeanBatch("tareaServicio");
			ITalonarioDao talonarioDao = (ITalonarioDao) Configuracion.getBeanBatch("talonarioDao");
			
			System.out.println("Crear una tarea de un talonario: Asignar Talonario Al supervisor ------");
			
			ShvTalTalonario talonario = talonarioDao.buscarTalonario(idTalonario);
			
			String rango = "Rango:"+ Utilidad.rellenarCerosIzquierda(talonario.getNroSerie().toString(), Constantes.LONGITUD_NRO_SERIE) + "-" +
					Utilidad.rellenarCerosIzquierda(talonario.getRangoDesde().toString(), Constantes.LONGITUD_NRO_RECIBO) 
					+ " a " + Utilidad.rellenarCerosIzquierda(talonario.getNroSerie().toString(), Constantes.LONGITUD_NRO_SERIE) + "-" + 
					Utilidad.rellenarCerosIzquierda(talonario.getRangoHasta().toString(), Constantes.LONGITUD_NRO_RECIBO);
			
			TareaDto tarea = new TareaDto();
			tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
			tarea.setIdWorkflow(talonario.getWorkflow().getIdWorkflow());
			tarea.setTipoTarea(TipoTareaEnum.ASIG_GESTOR_TAL);
			tarea.setFechaCreacion(talonario.getWorkflow().getFechaUltimaModificacion());
			tarea.setUsuarioCreacion(talonario.getUsuarioAlta());
			tarea.setPerfilAsignacion(Constantes.SUPERVISOR_TALONARIO);
			
			tarea.setGestionaSobre(TipoTareaGestionaEnum.TALONARIO);
			tarea.setIdTalonario(Long.valueOf(talonario.getIdTalonario()));
			
			String asuntoMail = Utilidad.reemplazarMensajes(Mensajes.TALONARIO_RENDIDO,talonario.getEmpresa().getDescripcion(), rango);
			tarea.setAsuntoMail(asuntoMail);
			
			tareaServicio.crearTareaTalonario(tarea);
			
		} catch (Throwable e) {
			Traza.error(TareaTestRunner.class, "Se ha producido un error", e);
		}
	}
}
