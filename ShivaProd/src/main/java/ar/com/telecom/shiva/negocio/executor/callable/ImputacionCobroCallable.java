package ar.com.telecom.shiva.negocio.executor.callable;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.concurrent.Callable;

import ar.com.telecom.shiva.base.constantes.Configuracion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.EstadoProcesamientoHilosEnum;
import ar.com.telecom.shiva.base.enumeradores.TrazaEnum;
import ar.com.telecom.shiva.base.utils.ControlMemoriaCPU;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.batch.ImputacionCobrosBatchRunner;
import ar.com.telecom.shiva.negocio.dto.cobros.imputacion.ResultadoImputacionCobro;
import ar.com.telecom.shiva.negocio.executor.rto.cobros.ImputacionCobroRto;
import ar.com.telecom.shiva.negocio.servicios.ICobroImputacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IProcesamientoHilosCobrosServicio;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvCobCobroSimpleSinWorkflow;

public class ImputacionCobroCallable implements Callable<ResultadoImputacionCobro> {
	
	private String idThread;
	private ImputacionCobroRto datosEntrada;
	
	public ImputacionCobroCallable(ImputacionCobroRto datosEntrada) {
		this.datosEntrada = datosEntrada;
	}
	
	public ResultadoImputacionCobro call() throws Exception {
		String usuarioBatch = "";
		final String orgName = Thread.currentThread().getName();
		double startTime = System.nanoTime();
		
		if (datosEntrada == null || datosEntrada.getCobroAProcesar().getIdOperacion() == null) {
			Traza.error(this.getClass(), "El objeto datosEntrada o el campo idOperacion no deben estar vacio");
			ResultadoImputacionCobro resultado = new ResultadoImputacionCobro(datosEntrada.getCount(), null);
			resultado.setResultado(Boolean.FALSE);
			return resultado;
        }
		
		IParametroServicio parametroServicio = (IParametroServicio) Configuracion.getBeanBatch("parametroServicio");
		ICobroImputacionServicio cobroServicio = (ICobroImputacionServicio) Configuracion.getBeanBatch("cobroServicio");
		//Creo un nuevo registro en la base, guardo el estado del hilo actual (EN CURSO), y seteo los datos que tengo del cobro
		IProcesamientoHilosCobrosServicio procesamientoHilosCobrosServicio = (IProcesamientoHilosCobrosServicio) Configuracion.getBeanBatch("procesamientoHilosCobrosServicio");
		
		/*
		datosEntrada.getCobroAProcesar().setFechaInicio(new Date());
		datosEntrada.getCobroAProcesar().setEstado(EstadoProcesamientoHilosEnum.EN_CURSO);
		datosEntrada.getCobroAProcesar().setTipoImputacion(TipoImputacionEnum.AUTOMATICA);
		
		procesamientoHilosCobrosServicio.insertarActualizarHilo(datosEntrada.getCobroAProcesar());
		*/
		String strIdOperacion = Utilidad.rellenarCerosIzquierda(datosEntrada.getCobroAProcesar().getIdOperacion().toString(), 7);
		ResultadoImputacionCobro resultado = new ResultadoImputacionCobro(datosEntrada.getCount(), strIdOperacion);
		
		ShvCobCobroSimpleSinWorkflow cobroSimple = null;
		
		try {
			this.idThread = "Id_Operacion("+strIdOperacion+")";
			Traza.cambiarNombreArchivoLog("/operaciones/" + this.idThread);
			Thread.currentThread().setName(TrazaEnum.IMPUTACION_COBROS.name());
			
			Traza.auditoria(this.getClass(), 
					Utilidad.reemplazarMensajes("Se ha comenzado el proceso del hilo nro: "+resultado.getNumHilo()+" {0}.", this.idThread));
	        
			usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
			
	        cobroServicio.imputarCobro(datosEntrada);
				
	        	
				
		} catch (Throwable e) {
			
			datosEntrada.getCobroAProcesar().setFechaFin(new Date());
			datosEntrada.getCobroAProcesar().setEstado(EstadoProcesamientoHilosEnum.ERROR);
			
			procesamientoHilosCobrosServicio.insertarActualizarHilo(datosEntrada.getCobroAProcesar());
			
			cobroSimple = cobroServicio.buscarCobroSimpleSinWorkflowPorIdOperacion(datosEntrada.getCobroAProcesar().getIdOperacion());
			cobroSimple.setFechaUltProcesamiento(datosEntrada.getCobroAProcesar().getFechaFin());
			cobroServicio.modificar(cobroSimple);
			
			Traza.error(this.getClass(),  
					Utilidad.reemplazarMensajes("Se ha producido un error al imputar el cobro (idOperacion: {0})",
							strIdOperacion));
			Traza.error(this.getClass(),e.getMessage(), e);
			Traza.auditoria(ImputacionCobrosBatchRunner.class, "-------------------------------------------------------------------------------------");
			
			resultado.setResultado(Boolean.FALSE);
			return resultado;
			
		} 
			
			datosEntrada.getCobroAProcesar().setFechaFin(new Date());
			datosEntrada.getCobroAProcesar().setEstado(EstadoProcesamientoHilosEnum.FINALIZADO);
			
			procesamientoHilosCobrosServicio.insertarActualizarHilo(datosEntrada.getCobroAProcesar());
			
			cobroSimple = cobroServicio.buscarCobroSimpleSinWorkflowPorIdOperacion(datosEntrada.getCobroAProcesar().getIdOperacion());
			cobroSimple.setFechaUltProcesamiento(datosEntrada.getCobroAProcesar().getFechaFin());
			cobroServicio.modificar(cobroSimple);
			
			Traza.auditoria(this.getClass(),  
					Utilidad.reemplazarMensajes("Se ha finalizado el hilo "+resultado.getNumHilo()+" {0}. ID OPERACION: " + datosEntrada.getCobroAProcesar().getIdOperacion(), this.idThread));
			
			double stopTime = System.nanoTime();
			double elapsedTime = (stopTime - startTime) / 1000000000.0;
			String detalle = "Resultado: " + new DecimalFormat("#.########").format(elapsedTime);
			
			Traza.tiempo(this.getClass(), usuarioBatch, "ejecutarImputacionesCobros", detalle);
			Traza.infoMemoriaCPU(this.getClass(), "ejecutarImputacionesCobros", ControlMemoriaCPU.getInformacionMemoria());
			
			Thread.currentThread().setName(orgName);
			Traza.resetearDefaultArchivoLog();
			return resultado;
		
	}
}
