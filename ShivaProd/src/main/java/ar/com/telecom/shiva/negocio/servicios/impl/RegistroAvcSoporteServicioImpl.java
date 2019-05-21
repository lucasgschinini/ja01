/**
 * 
 */
package ar.com.telecom.shiva.negocio.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.batch.springbatch.model.AvcRegistroAvc;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IRegistroAvcSoporteServicio;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowRegistrosAVC;
import ar.com.telecom.shiva.persistencia.dao.IRegistroAvcSoporteDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;

/**
 * @author u591368 F.N. Quispe
 *
 */
public class RegistroAvcSoporteServicioImpl implements IRegistroAvcSoporteServicio {

	@Autowired IRegistroAvcSoporteDao registroAvcSoporteDao;
	@Autowired IWorkflowRegistrosAVC workflowRegistroAvc;
	@Autowired IParametroServicio parametroServicio;
	
	@Override
	public AvcRegistroAvc crear(AvcRegistroAvc registroAvc) throws NegocioExcepcion {
		
		try {
			
			// Preparo los datos para el historial del Workflow
			String datosOriginales = "";
    		//String datosOriginales = deposito.getDatosOriginalesRegistroAVC();
    		//datosOriginales += Utilidad.datosOriginales(deposito, workflowRegistrosAVC.getListaDatosOriginalesDeposito());

			// Disparo el circuito workflow para el nuevo registro Avc
			ShvWfWorkflow workflow = workflowRegistroAvc.crearWorkflow(datosOriginales, parametroServicio.getValorTexto(Constantes.USUARIO_BATCH));
			registroAvc.setWorkFlow(workflow);
		
			// Genero el registro AVC en la base de datos
			registroAvc = registroAvcSoporteDao.crear(registroAvc);
			
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
		
			/*
		try {
			ShvAvcArchivoAvc archivoAVCModelo = (ShvAvcArchivoAvc) archivoAvcMapeador.map(archivoAVC, null);
			adjuntarArchivo(archivoAVC,archivoAVCModelo);
	        ShvAvcArchivoAvc archivoAVCInsertado = archivoAVCDao.crear(archivoAVCModelo);
	        
	        // Inserto el archivo y los registros AVC en la base
	        if (Validaciones.isCollectionNotEmpty(listaRegistrosAVC)) {
		        for (RegistroAVCDto registroAvc : listaRegistrosAVC) {
		        	ShvAvcRegistroAvc registroAVCModelo = null;
		        	
		        	// Deposito
		        	if(ConciliacionServicioImpl.esDeposito(registroAvc.getAcuerdo())){
		        		DepositoDto deposito = (DepositoDto) registroAvc;
		        		
		        		registroAVCModelo = (ShvAvcRegistroAvc) depositoMapeador.map(deposito, null);
		        		String datosOriginales = deposito.getDatosOriginalesRegistroAVC();
			    		datosOriginales += Utilidad.datosOriginales(deposito, workflowRegistrosAVC.getListaDatosOriginalesDeposito());
			    		ShvWfWorkflow workflow = workflowRegistrosAVC.crearWorkflow(datosOriginales, parametroServicio.getValorTexto(Constantes.USUARIO_BATCH));
			    		registroAVCModelo.setWorkFlow(workflow);
		        	} else {
			        	// Interdeposito
			        	if (TipoAcuerdoEnum.INTERDEPOSITO_6.descripcion().equals((registroAvc.getAcuerdo()))) {
			        		InterdepositoDto interdeposito = (InterdepositoDto) registroAvc;
			        		
			        		registroAVCModelo = (ShvAvcRegistroAvc) interdepositoMapeador.map(interdeposito, null);
			        		String datosOriginales = interdeposito.getDatosOriginalesRegistroAVC();
			    			datosOriginales += Utilidad.datosOriginales(interdeposito, workflowRegistrosAVC.getListaDatosOriginalesInterdeposito());
			    			ShvWfWorkflow workflow = workflowRegistrosAVC.crearWorkflow(datosOriginales, parametroServicio.getValorTexto(Constantes.USUARIO_BATCH));
			    			registroAVCModelo.setWorkFlow(workflow);
				    			
						} else {
							//Transferencia
							TransferenciaDto transferencia = (TransferenciaDto) registroAvc;
							if (TipoAcuerdoEnum.TRANSFERENCIA.descripcion().equals(transferencia.getAcuerdo())){
								buscarCuit(transferencia);
							}
							registroAVCModelo = (ShvAvcRegistroAvc) transferenciaMapeador.map(transferencia, null);
							String datosOriginales = transferencia.getDatosOriginalesRegistroAVC();
					    	datosOriginales += Utilidad.datosOriginales(transferencia, workflowRegistrosAVC.getListaDatosOriginalesTransferencia());
					    	ShvWfWorkflow workflow = workflowRegistrosAVC.crearWorkflow(datosOriginales, parametroServicio.getValorTexto(Constantes.USUARIO_BATCH));
					    	registroAVCModelo.setWorkFlow(workflow);
					    }
		        	}
		        	
		        	registroAVCModelo.setArchivoAvc(archivoAVCInsertado);
		        	registroAVCModelo = registroAVCDao.crear(registroAVCModelo);
				}
	        }
	        
	        return archivoAVCInsertado;
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		*/
		
		return registroAvc;
			
		}
}
