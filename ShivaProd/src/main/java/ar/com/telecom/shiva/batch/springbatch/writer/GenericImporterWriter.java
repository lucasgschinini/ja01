/**
 * 
 */
package ar.com.telecom.shiva.batch.springbatch.writer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.batch.springbatch.model.AvcRegistroAvcTransferencia;
import ar.com.telecom.shiva.batch.springbatch.model.GenericDataFile;
import ar.com.telecom.shiva.batch.springbatch.model.GenericDataItem;
import ar.com.telecom.shiva.negocio.servicios.IArchivoAVCSoporteServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IRegistroAvcSoporteServicio;
import ar.com.telecom.shiva.persistencia.dao.IAcuerdoDao;
import ar.com.telecom.shiva.persistencia.dao.ITipoValorDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcArchivoAvc;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;

/**
 * @author u564030 Pablo M. Ibarrola
 *
 */
public class GenericImporterWriter implements ItemWriter<GenericDataItem> {
 
	@Value("#{stepExecution}")
	private StepExecution stepExecution;
	
	@Autowired
	private IArchivoAVCSoporteServicio archivoAvcSoporteServicio;
	
	@Autowired
	private IRegistroAvcSoporteServicio registroAvcSoporteServicio;
	
	@Autowired
	private IParametroServicio parametroServicio;
	
	@Autowired
	private ITipoValorDao tipoValorDao;
	
	@Autowired
	private IAcuerdoDao acuerdoDao;
	
	
    @Override
    public void write(List<? extends GenericDataItem> items) throws Exception {

    	int contadorRegistrosEscritos = Constantes.CERO;

    	Traza.auditoria(this.getClass(), "");
    	Traza.auditoria(this.getClass(), "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    	Traza.auditoria(this.getClass(), "Aqui ya tenemos el resultado del procesamiento del batch");

        // Traigo el bean del contexto
        GenericDataFile genericFile = (GenericDataFile) stepExecution.getJobExecution().getExecutionContext().get("genericDataFile");
        
        // Traigo el logProcesamiento del contexto        
        String logProcesamiento = (String) stepExecution.getJobExecution().getExecutionContext().get("logProcesamiento");
        
        // Traigo un boolean del contexto para ver si el archivo contiene errores
        boolean tieneErrorElArchivo = false; 
        
        if(!Validaciones.isObjectNull(stepExecution.getJobExecution().getExecutionContext().get("tieneError"))) {
        	tieneErrorElArchivo = (boolean) stepExecution.getJobExecution().getExecutionContext().get("tieneError");
        };
        
        
        if (Validaciones.isNullEmptyOrDash(logProcesamiento)) {
        	// El log de procesamiento será 'Archivo procesado correctamente.' en caso de que no se hayan detectado errores.
        	logProcesamiento = Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.archivo.procesado.correctamente");
        	stepExecution.getJobExecution().getExecutionContext().put("logProcesamiento", logProcesamiento);
        }

        Traza.auditoria(this.getClass(), "Resultado: Log de Procesamiento: ");
        Traza.auditoria(this.getClass(), logProcesamiento);
        Traza.auditoria(this.getClass(), ""); 
        Traza.auditoria(this.getClass(), "Voy a crear el archivo Avc (shv_avc_archivo_avc): ");
        
        // Busco el acuerdo
        ShvParamAcuerdo acuerdo = acuerdoDao.buscarAcuerdo(genericFile.getIdAcuerdo());
        
        // Creo el documento adjunto
        ShvDocDocumentoAdjunto documentoAdjunto = new ShvDocDocumentoAdjunto();
        documentoAdjunto.setArchivoAdjunto(genericFile.getArchivoAdjunto());
        documentoAdjunto.setNombreArchivo(genericFile.getNombreArchivo());
        documentoAdjunto.setFechaCreacion(new Date());
        documentoAdjunto.setUsuarioCreacion(parametroServicio.getValorTexto(Constantes.USUARIO_BATCH));
        
        // Creo el Archivo AVC
	    ShvAvcArchivoAvc archivoAVC = new ShvAvcArchivoAvc();
	    archivoAVC.setDocumentoAdjunto(documentoAdjunto);
        archivoAVC.setLogProcesamiento(logProcesamiento);
        archivoAVC.setFechaProcesamiento(genericFile.getFechaProcesamiento());
        archivoAVC.setNombreArchivo(genericFile.getNombreArchivo());
        archivoAVC.setUsuarioProcesamiento(genericFile.getUsuarioProcesamiento());
        archivoAVC.setAcuerdo(acuerdo);
		
        archivoAVC = archivoAvcSoporteServicio.crear(archivoAVC);
        Traza.auditoria(this.getClass(), "Se ha generado el archivo Avc con ID: " + archivoAVC.getIdArchivosAvc());
        Traza.auditoria(this.getClass(), " ");
	        
	        
	    if (!tieneErrorElArchivo) {
	    	for (GenericDataItem registro : items) {
	    		Traza.auditoria(this.getClass(), "Escribiendo registro Avc (shv_avc_registro_avc y shv_avc_reg_avc_transferencia)... ");
	            Traza.auditoria(this.getClass(), registro.toString());
	            
	            /*
	            AvcRegistroAvcInterdeposito registroAvc = new AvcRegistroAvcInterdeposito();
	            registroAvc.setArchivoAvc(archivoAVC);
	            registroAvc.setAcuerdo(acuerdo);
	
	            registroAvc.setCodigoCliente(registro.getCodigoCliente());
	            registroAvc.setIdClientePerfil(registro.getIdClientePerfil());
	            registroAvc.setImporte(new BigDecimal(registro.getImporte()));
	            registroAvc.setObservaciones(registro.getObservaciones());
	            registroAvc.setRazonSocialClientePerfil(registro.getRazonSocialClientePerfil());
	            registroAvc.setTipoValor(tipoValorDao.buscarTipoValor(String.valueOf(TipoValorEnum.INTERDEPÓSITO.getIdTipoValor())));
	            
	            registroAvc.setCodigoInterdeposito(registro.getCodigoInterdeposito());
	            registroAvc.setCodigoOperacion(registro.getCodigoOperacion());
	            registroAvc.setCodigoOrganismo(registro.getCodigoOrganismo());
	            registroAvc.setCodOpBanco(Long.parseLong(registro.getCodigoOpBanco()));
	            registroAvc.setComprobante(registro.getComprobante());
	            registroAvc.setConcepto(registro.getConcepto());
	            registroAvc.setDeposito(registro.getDeposito());
	            registroAvc.setFechaIngreso(Utilidad.parseDateStringCustomFormat(registro.getFechaIngreso(),"dd-MMM-yyyy"));
	            registroAvc.setFechaValor(Utilidad.parseDateStringCustomFormat(registro.getFechaValor(),"dd-MMM-yyyy"));
	            registroAvc.setPcc(registro.getPcc());
	            registroAvc.setSucursal(registro.getSucursal());
	            */
	            
	            AvcRegistroAvcTransferencia registroAvc = new AvcRegistroAvcTransferencia();
	            registroAvc.setArchivoAvc(archivoAVC);
	            registroAvc.setAcuerdo(acuerdo);
	            
	            registroAvc.setCodigoCliente(registro.getCodigoCliente());
	            registroAvc.setIdClientePerfil(registro.getIdClientePerfil());
	            registroAvc.setImporte(new BigDecimal(registro.getImporte()));
	            registroAvc.setObservaciones(registro.getObservaciones());
	            registroAvc.setRazonSocialClientePerfil(registro.getRazonSocialClientePerfil());
	            registroAvc.setTipoValor(tipoValorDao.buscarTipoValor(String.valueOf(TipoValorEnum.TRANSFERENCIA.getIdTipoValor())));
	            
	            registroAvc.setFechaIngreso(Utilidad.parseDateStringCustomFormat(registro.getFechaIngreso(),"dd-MMM-yyyy"));
	            if (!Validaciones.isObjectNull(registro.getSucursal())) {
	            	registroAvc.setSucursal(new Long(registro.getSucursal()));
	            }
	            
	            if (!Validaciones.isObjectNull(registro.getReferencia())) { 
	            	registroAvc.setReferencia(new Long(registro.getReferencia()));
	            } else {
	            	registroAvc.setReferencia(new Long(registro.getComprobante().replace(".", "").trim()));
	            }
	            
	            if (!Validaciones.isObjectNull(registro.getCodigoOpBanco())) {
	            	registroAvc.setCodigo(new Long(registro.getCodigoOpBanco()));
	            }
	            registroAvc.setObservacion(Constantes.SIGNO_MENOS);
	            
	        	
	        	/*
	        	private String observacion;
	        	private String observacionEditarCuit;
	        	private String cuit;
	
	        	registroAvc.setCodigoInterdeposito(registro.getCodigoInterdeposito());
	            registroAvc.setCodigoOperacion(registro.getCodigoOperacion());
	            registroAvc.setCodigoOrganismo(registro.getCodigoOrganismo());
	            registroAvc.setCodOpBanco(Long.parseLong(registro.getCodigoOpBanco()));
	            registroAvc.setComprobante(registro.getComprobante());
	            registroAvc.setConcepto(registro.getConcepto());
	            registroAvc.setDeposito(registro.getDeposito());
	            registroAvc.setFechaValor(Utilidad.parseDateStringCustomFormat(registro.getFechaValor(),"dd-MMM-yyyy"));
	            registroAvc.setPcc(registro.getPcc());
	            registroAvc.setSucursal(registro.getSucursal());  
	            */          
	            
	            // Impacto en la tabla SHV_AVC_REGISTRO_AVC
	            registroAvcSoporteServicio.crear(registroAvc);          
	            Traza.auditoria(this.getClass(), "Se ha generado el registro Avc con ID: " + registroAvc.getIdRegistroAvc());
	            
	            
	            contadorRegistrosEscritos++;
	        }
	    	Traza.auditoria(GenericImporterWriter.class,"Se han escrito correctamente " + contadorRegistrosEscritos + " registros");
	    	Traza.auditoria(GenericImporterWriter.class,"");
        } else { 
        	Traza.error(this.getClass(), "Se rechazará el archivo debido a que tiene errores en su contenido.");
        }
    }
}

