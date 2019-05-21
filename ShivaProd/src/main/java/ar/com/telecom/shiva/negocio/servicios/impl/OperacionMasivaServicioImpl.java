package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.EstadoRegistroOperacionMasivaEnum;
import ar.com.telecom.shiva.base.enumeradores.OperacionMasivaBatchEmailEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoArchivoOperacionMasivaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoRegistroEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoReintegroEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoResultadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaGestionaEnum;
import ar.com.telecom.shiva.base.enumeradores.TratamientoInteresesEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.LdapExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mail.Adjunto;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.mapeadores.IOperacionMasivaRegistroMapeador;
import ar.com.telecom.shiva.base.registros.datos.entrada.MicOperacionMasivaEntrada;
import ar.com.telecom.shiva.base.registros.datos.salida.MicOperacionMasivaSalida;
import ar.com.telecom.shiva.base.registros.datos.salida.agrupador.MicOperacionMasivaRegistroSalida;
import ar.com.telecom.shiva.base.registros.mapeos.MicOperacionMasivaMapeador;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.utils.singleton.ValidarAuxiliarSingleton;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteSiebelServicio;
import ar.com.telecom.shiva.batch.ImputacionCobrosBatchRunner;
import ar.com.telecom.shiva.negocio.bean.ImportarOperacionesMasivasAuxiliar;
import ar.com.telecom.shiva.negocio.mapeos.ArchivoOperacionMasivaMapeador;
import ar.com.telecom.shiva.negocio.mapeos.OperacionMasivaMapeador;
import ar.com.telecom.shiva.negocio.mapeos.OperacionesMasivasHistorialMapeador;
import ar.com.telecom.shiva.negocio.mapeos.OperacionesMasivasVistaMapeador;
import ar.com.telecom.shiva.negocio.mapeos.RegistroOperacionMasivaMapeador;
import ar.com.telecom.shiva.negocio.servicios.IClienteServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.negocio.servicios.IOperacionMasivaArchivaServicio;
import ar.com.telecom.shiva.negocio.servicios.IOperacionMasivaRegistroServicio;
import ar.com.telecom.shiva.negocio.servicios.IOperacionMasivaRegistroValidacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IOperacionMasivaServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.ITareaServicio;
import ar.com.telecom.shiva.negocio.servicios.IVistaSoporteServicio;
import ar.com.telecom.shiva.negocio.servicios.bean.ClienteBean;
import ar.com.telecom.shiva.negocio.servicios.validacion.IOperacionMasivaValidacionServicio;
import ar.com.telecom.shiva.negocio.workflow.IWorkflowService;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowOperacionesMasivas;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteOperacionesMasivas;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaOperacionMasivaHistorial;
import ar.com.telecom.shiva.persistencia.dao.IArchivoOperacionMasivaDao;
import ar.com.telecom.shiva.persistencia.dao.IDocumentoAdjuntoDao;
import ar.com.telecom.shiva.persistencia.dao.IGenericoDao;
import ar.com.telecom.shiva.persistencia.dao.IOperacionMasivaAdjuntoDao;
import ar.com.telecom.shiva.persistencia.dao.IOperacionMasivaDao;
import ar.com.telecom.shiva.persistencia.dao.IParametroDao;
import ar.com.telecom.shiva.persistencia.dao.IRegistroOperacionMasivaDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasOperacionMasiva;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasOperacionMasivaAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasOperacionMasivaAdjuntoPk;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasOperacionMasivaArchivo;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistro;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroAplicarDeuda;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroDesistimiento;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroGanancias;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroReintegro;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowEstado;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowEstadoHist;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvMasOperacionMasivaSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvMasOperacionMasivaSimplificadoWorkFlow;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ArchivoOperacionMasivaProcesadoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.OperacionMasivaArchivoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.OperacionMasivaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.OperacionMasivaHistoricaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.RegistroOperacionMasivaAplicarDeudaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.RegistroOperacionMasivaDesistimientoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.RegistroOperacionMasivaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.RegistroOperacionMasivaGananciasDto;
import ar.com.telecom.shiva.presentacion.bean.dto.RegistroOperacionMasivaReintegroDto;
import ar.com.telecom.shiva.presentacion.bean.dto.TareaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteOperacionMasivaFiltro;

public class OperacionMasivaServicioImpl implements IOperacionMasivaServicio {

	@Autowired
	private IOperacionMasivaDao operacionMasivaDao;
	
	@Autowired
	IRegistroOperacionMasivaDao registroOperacionMasivaDao;
	
	@Autowired
	private ArchivoOperacionMasivaMapeador archivoOperacionMasivaMapeador;
	
	@Autowired
	private RegistroOperacionMasivaMapeador registroOperacionMasivaMapeador;
	
	@Autowired
	IOperacionMasivaValidacionServicio operacionMasivaValidacionServicio;
	
	@Autowired
	IWorkflowOperacionesMasivas workflowOperacionesMasivas;
	
	@Autowired 
	IWorkflowService workflowService;
	
	@Autowired
	IDocumentoAdjuntoDao documentoAdjuntoDao;
	
	@Autowired
	IArchivoOperacionMasivaDao archivoOperacionMasivaDao;
	
	@Autowired
	private IOperacionMasivaAdjuntoDao operacionMasivaAdjuntoDao;
	
	@Autowired
	OperacionMasivaMapeador operacionMasivaMapeador;
	@Autowired 
	IVistaSoporteServicio vistaSoporteServicio;
	
	@Autowired 
	OperacionesMasivasVistaMapeador operacionMasivaVistaMapeo;	
	
	@Autowired
	MicOperacionMasivaMapeador micOperacionMasivaMapeoRegistro;
	
	@Autowired 
	OperacionesMasivasHistorialMapeador operacionMasivaHistorialMapeo;
	
	@Autowired
	ITareaServicio tareaServicio;
	
	@Autowired
	ILdapServicio ldapServicio;
	
	@Autowired
	MailServicio mailServicio;
	
	@Autowired 
	ICobroOnlineServicio cobroOnlineServicio;

	@Autowired 
	IOperacionMasivaRegistroServicio operacionMasivaRegistroServicio;
	
	@Autowired 
	IGenericoDao genericoDao;
	
	@Autowired
	private IParametroServicio parametroServicio;
	
	@Autowired
	IOperacionMasivaRegistroMapeador micOperacionMasivaRegistroMapeador;
	@Autowired
	IOperacionMasivaArchivaServicio operacionMasivaArchivaServicio;

	@Autowired
	IClienteSiebelServicio clienteConsultarSiebelServicio;
	
	@Autowired 
	IParametroDao parametroDao;
	
	@Autowired
	IOperacionMasivaRegistroValidacionServicio operacionMasivaRegistroValidacionServicio;
	@Autowired
	private IClienteServicio clienteServicio;
	
	private final String UNDERSCORE = "_";
	private final String SEMICOLON = ";";
	private final String WHITESPACE = " ";
	private final String CARRIAGE_RETURN = "\n";
	
	/** Archivo Aplicar Deuda - Indice de campos del registro **/
	public static final int TIPO_OPERACION = 0;
	public static final int CLIENTE_DUENO_DEBITO_DEUDA = 1; 
	public static final int NUMERO_REFERENCIA_FACTURA_DEUDA = 2;
	public static final int DESTRANSFERIR_TERCEROS = 3;
	public static final int DEUDA_MIGRADA = 4;
	public static final int IMPORTE_DEUDA = 5;
	public static final int CLIENTE_DUENO_CREDITO = 6;
	public static final int CUENTA_DEUDA = 7;
	public static final int TIPO_REMANENTE = 8;
	public static final int NUMERO_REFERENCIA_NC = 9;
	public static final int CREDITO_MIGRADO = 10;
	public static final int ACCION_SOBRE_INTERESES = 11;
	public static final int PORCENTAJE_BONIFICACION_INTERESES = 12;
	public static final int IMPORTE_BONIFICACION_INTERESES = 13;
	public static final int CLIENTE_DUENO_ACUERDO_FACTURACION = 14;
	public static final int ACUERDO_FACTURACION_DESTINO = 15;
	private final int[] lengthCamposAplicarDeuda = {1,10,15,1,1,10,10,13,3,15,1,2,3,10,10,10};
	
	/** Archivo Desestiemiento - Indice de campos del registro **/
	public static final int CLIENTE_DUENO_DEBITO_DESIST = 0;
	public static final int NUMERO_REFERENCIA_FACTURA_DESIST = 1; 
	public static final int IMPORTE_DESISTIMIENTO = 2;
	public static final int	DEUDA_MIGRADA_DESISTIMIENTO = 3;
	public static final int NRO_ACTA_DESISTIMIENTO = 4;
	public static final int FECHA_ACTA_DESISTIMIENTO = 5;
	private final int[] lengthCamposDesistimiento = {10,15,10,1,25,8}; 
	
	/** Archivo Ganancias - Indice de campos del registro **/
	public static final int CLIENTE_DUENO_CREDITO_GANCIA = 0;
	public static final int CUENTA_ORIGEN_GANCIA = 1;
	public static final int TIPO_REMANENTE_GANCIA = 2;
	public static final int NRO_REFERENCIA_NC_GANCIA = 3;
	public static final int IMPORTE_GANCIA = 4;
	public static final int CREDITO_MIGRADO_GANCIA = 5;
	private final int[] lengthCamposGanancias = {10,13,3,15,10,1}; 

	/** Archivo Remanente - Indice de campos del registro **/
	public static final int CLIENTE_DUENO_CREDITO_REINT = 0;
	public static final int CUENTA_REINT = 1;
	public static final int TIPO_REMANENTE_REINT = 2;
	public static final int NUMERO_REFERENCIA_NC_REINT = 3;
	public static final int NUMERO_DOCUMENTO_REINT = 4;
	public static final int CREDITO_MIGRADO_REINT = 5;
	public static final int IMPORTE_REINT= 6;
	public static final int TRAMITE_REINTEGRO = 7;
	public static final int FECHA_ALTA_TRAMITE_REINTEGRO = 8;
	public static final int TIPO_REINTEGRO = 9;
	public static final int CLIENTE_DUENO_ACUERDO_FACTURACION_REINT = 10;
	public static final int ACUERDO_FACTURACION_DESTINO_REINT = 11;
	public static final int LINEA_DESTINO = 12;
	public static final int REINTEGRA_CON_INTERESES = 13;
	private final int[] lengthCamposReintegros = {10,13,3,15,15,1,10,10,8,3,10,10,18,1};
	
	private static final String EXISTEN_MAS_ERRORES_PERO_NO_SERAN_MOSTRADOS = "Existen más errores pero no serán mostrados.";

//	private static final String COMPONENTE_OPERACION_MASIVA = "Componente de la Operacion Masiva";
	private static final String ARCHIVO_OPERACION_MASIVA = "Archivo de la Operacion Masiva";
	
	/**
	 * Metodo que procesa un OperacionMasivaDto y si el procesamiento es OK, 
	 * luego de insertar en la base crea y retorna un ArchivoOperacionMasivaProcesadoDto.
	 * Si el procesamiento es incorrecto retorna null.
	 * @throws PersistenciaExcepcion 
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public ArchivoOperacionMasivaProcesadoDto procesarArchivo(OperacionMasivaDto operacionMasiva, boolean hayCambioTarea) throws NegocioExcepcion, PersistenciaExcepcion{
		ArchivoOperacionMasivaProcesadoDto archivoProcesado = null;
		
		File file = ControlArchivo.convert(operacionMasiva.getFileBytes(), operacionMasiva.getFileOperacionMasiva());
		String[] registros = null;

		try {
			registros = ControlArchivo.leerArchivo(file.getPath(), Constantes.RETORNO_UNIX);
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		TipoArchivoOperacionMasivaEnum tipoArchivo = TipoArchivoOperacionMasivaEnum.getEnumByName(getTipoArchivo(file.getName()));

		//Defensive Programming (El Nombre del achivo del cual se extrae el tipo de operación
		//masiva no debería tener un formato erroneo ya que se valida en el jsp)
		if (Validaciones.isObjectNull(tipoArchivo)) {
			operacionMasiva.setResultadoValidaciones("ERROR: Tipo de archivo no valido");
		} else if (Validaciones.isObjectNull(registros)) {
			operacionMasiva.setResultadoValidaciones("ERROR: Archivo vacio");
		} 

		// Proceso los registros
		if (!operacionMasiva.isDuplicado()) {
			if (tipoArchivo!=null) {
				switch (tipoArchivo) {
				case DEUDA:					
					archivoProcesado = procesarRegistrosAplicarDeuda(registros, operacionMasiva, file);
					break;
				case DSIST:
					archivoProcesado = procesarRegistrosDesistimiento(registros, operacionMasiva, file);
					break;
				case GNCIA:
					archivoProcesado = procesarRegistrosGanancias(registros, operacionMasiva, file);
					break;
				case REINT:
					archivoProcesado = procesarRegistrosReintegro(registros, operacionMasiva, file);
					break;
				//case CONVENIO:
				//	procesarRegistrosCuotasDeConvenio(registros, operacionMasiva, file);
				//	break;
				default:
					break;
				}
			}
		}
		
		if (hayCambioTarea) {
			ShvMasOperacionMasiva operacionMasivaModelo2 = buscarOperacionMasivaModelo(operacionMasiva.getIdOperacionMasiva());
			ShvWfWorkflow workflow = operacionMasivaModelo2.getWorkFlow();
			tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.OP_MAS_ERROR, workflow.getIdWorkflow(), operacionMasiva.getFechaUltimaModificacion(), operacionMasiva.getUsuarioModificacion(), null);
			tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.OP_MAS_PARCIAL, workflow.getIdWorkflow(), operacionMasiva.getFechaUltimaModificacion(), operacionMasiva.getUsuarioModificacion(), null);
			
			ShvMasOperacionMasiva operacionMasivaModelo = buscarOperacionMasivaModelo(operacionMasiva.getIdOperacionMasiva());
			ShvMasOperacionMasivaArchivo opMasivaArchivo = buscarArchivoOperacionMasivaModelo(operacionMasiva.getIdOperacionMasiva());
			if(TipoArchivoOperacionMasivaEnum.DSIST.equals(operacionMasivaModelo.getTipoOperacionMasiva())){
				crearTareaPendienteAprobacionOperacionMasiva(operacionMasivaModelo, opMasivaArchivo, operacionMasiva.getUsuarioModificacion());
			}
		}
		
		
		return archivoProcesado;
	}

	/**
	 * 
	 * @param idOperacionMasiva
	 * @param nombreArchivo
	 * @param tipoOperacion
	 * @param cantRegistros
	 * @param estado
	 * @param importe
	 * @param copropietario
	 * @param analista
	 * @param motivo
	 * @return
	 */
	private static ArchivoOperacionMasivaProcesadoDto cargarOperacionMasiva(
			Long idOperacionMasiva,String nombreArchivo,String tipoOperacion,
			int cantRegistros,String estado,BigDecimal importe,
			UsuarioLdapDto copropietario, UsuarioLdapDto analista, String motivo) 
	{
		ArchivoOperacionMasivaProcesadoDto archivoOperacionMasivaProcesadoDto = new ArchivoOperacionMasivaProcesadoDto();
		
		if(idOperacionMasiva!=null){
			archivoOperacionMasivaProcesadoDto.setIdOperacionMasiva(idOperacionMasiva);
		}
		archivoOperacionMasivaProcesadoDto.setNombreArchivo(nombreArchivo);
		if(!Validaciones.isNullEmptyOrDash(tipoOperacion)){
			archivoOperacionMasivaProcesadoDto.setTipoOperacion(tipoOperacion);
		}
		archivoOperacionMasivaProcesadoDto.setCantRegistros(cantRegistros);
		archivoOperacionMasivaProcesadoDto.setEstado(estado);
		archivoOperacionMasivaProcesadoDto.setImporte(Utilidad.formatCurrency(importe,2));
		archivoOperacionMasivaProcesadoDto.setCopropietario(copropietario!=null?copropietario.getNombreCompleto():null);
		archivoOperacionMasivaProcesadoDto.setAnalista(analista != null?analista.getNombreCompleto():null);
		
		/* Analista */
		archivoOperacionMasivaProcesadoDto.setIdAnalista(analista != null?analista.getTuid():"");
		archivoOperacionMasivaProcesadoDto.setNombreAnalista(analista != null?analista.getNombreCompleto():"");
		archivoOperacionMasivaProcesadoDto.setMailAnalista(analista != null?analista.getMail():"");
		archivoOperacionMasivaProcesadoDto.setUrlFotoAnalista(archivoOperacionMasivaProcesadoDto.urlFotoUsuario(analista.getTuid()));
				
		/* Copropietario */
		if(!Validaciones.isObjectNull(copropietario)){
			archivoOperacionMasivaProcesadoDto.setIdCopropietario(copropietario!=null?copropietario.getTuid():"");
			archivoOperacionMasivaProcesadoDto.setNombreCopropietario(copropietario.getNombreCompleto());
			archivoOperacionMasivaProcesadoDto.setMailCopropietario(copropietario.getMail());
			archivoOperacionMasivaProcesadoDto.setUrlFotoCopropietario(archivoOperacionMasivaProcesadoDto.urlFotoUsuario(copropietario.getTuid()));			
		}
		
		archivoOperacionMasivaProcesadoDto.setMotivo(motivo);
		
		return archivoOperacionMasivaProcesadoDto;
	}
	
	/**
	 * Retorna el tipo del archivo que lo obtiene del nombre del archivo.
	 * @param nombreArchivo
	 * @return
	 */
	private String getTipoArchivo(String nombreArchivo) {
		return (nombreArchivo.split(UNDERSCORE))[0];
	}

	/**
	 * Procesa un archivo del tipo Aplicar Dueda. Si los registros son correctos
	 * los inserta en la base.
	 * @param registros
	 * @throws NegocioExcepcion
	 */
	public ArchivoOperacionMasivaProcesadoDto procesarRegistrosAplicarDeuda(String[] registros, OperacionMasivaDto operacionMasiva, File file) throws NegocioExcepcion {
		
		try {
			ArchivoOperacionMasivaProcesadoDto archivoOperacionMasivaProcesadoDto = null;
			List<RegistroOperacionMasivaDto> aplicarDeudaRegistros = new ArrayList<RegistroOperacionMasivaDto>();
			BigDecimal importeTotal = new BigDecimal(0);
	
			for(String reg : registros) {
				String[] campos = this.obtenerCampos(reg);
				RegistroOperacionMasivaAplicarDeudaDto regAplicarDeuda = new RegistroOperacionMasivaAplicarDeudaDto();

				regAplicarDeuda.setTipoOperacion(campos[TIPO_OPERACION]);
				regAplicarDeuda.setClienteDuenoDebito(!Validaciones.isNullOrEmpty(campos[CLIENTE_DUENO_DEBITO_DEUDA])?Long.valueOf(campos[CLIENTE_DUENO_DEBITO_DEUDA]):null);
				regAplicarDeuda.setNumeroReferenciaFactura(!Validaciones.isNullOrEmpty(campos[NUMERO_REFERENCIA_FACTURA_DEUDA])?Long.valueOf(campos[NUMERO_REFERENCIA_FACTURA_DEUDA]):null);
				regAplicarDeuda.setDestransferirTerceros(!Validaciones.isNullOrEmpty(campos[DESTRANSFERIR_TERCEROS])?SiNoEnum.getEnumByDescripcionCorta(campos[DESTRANSFERIR_TERCEROS]):null);
				regAplicarDeuda.setDeudaMigrada(!Validaciones.isNullOrEmpty(campos[DEUDA_MIGRADA])?SiNoEnum.getEnumByDescripcionCorta(campos[DEUDA_MIGRADA]):null);
				regAplicarDeuda.setImporte(Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(campos[IMPORTE_DEUDA]), 2));
				regAplicarDeuda.setClienteDuenoCredito(!Validaciones.isNullOrEmpty(campos[CLIENTE_DUENO_CREDITO])?Long.valueOf(campos[CLIENTE_DUENO_CREDITO]):null);
				regAplicarDeuda.setCuenta(!Validaciones.isNullOrEmpty(campos[CUENTA_DEUDA])?Long.valueOf(campos[CUENTA_DEUDA]):null);
				regAplicarDeuda.setTipoRemanente(!Validaciones.isNullOrEmpty(campos[TIPO_REMANENTE])?TipoOrigenEnum.getEnumByName(campos[TIPO_REMANENTE]):null);
				regAplicarDeuda.setNumeroReferenciaNC(!Validaciones.isNullOrEmpty(campos[NUMERO_REFERENCIA_NC])?campos[NUMERO_REFERENCIA_NC]:null);
				regAplicarDeuda.setCreditoMigrado(!Validaciones.isNullOrEmpty(campos[CREDITO_MIGRADO])?SiNoEnum.getEnumByDescripcionCorta(campos[CREDITO_MIGRADO]):null);
				regAplicarDeuda.setAccionSobreIntereses(!Validaciones.isNullOrEmpty(campos[ACCION_SOBRE_INTERESES])?TratamientoInteresesEnum.getEnumByName(campos[ACCION_SOBRE_INTERESES]):null);
				regAplicarDeuda.setPorcentajeBonificacionIntereses(!Validaciones.isNullOrEmpty(campos[PORCENTAJE_BONIFICACION_INTERESES])?Long.valueOf(campos[PORCENTAJE_BONIFICACION_INTERESES]):null);
				regAplicarDeuda.setImporteBonificacionIntereses(Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(campos[IMPORTE_BONIFICACION_INTERESES]), 2));
				regAplicarDeuda.setClienteDuenoAcuerdoFacturacion(!Validaciones.isNullOrEmpty(campos[CLIENTE_DUENO_ACUERDO_FACTURACION])?Long.valueOf(campos[CLIENTE_DUENO_ACUERDO_FACTURACION]):null);
				regAplicarDeuda.setAcuerdoFacturacionDestino(!Validaciones.isNullOrEmpty(campos[ACUERDO_FACTURACION_DESTINO])?Long.valueOf(campos[ACUERDO_FACTURACION_DESTINO]):null);		
				
				aplicarDeudaRegistros.add(regAplicarDeuda);
				importeTotal = importeTotal.add(regAplicarDeuda.getImporte());
			}
			//Guardo en el modelo
			ShvMasRegistroAplicarDeuda regAD = new ShvMasRegistroAplicarDeuda();
			operacionMasiva.setTipoOperacionMasiva(TipoArchivoOperacionMasivaEnum.DEUDA);
			ShvMasOperacionMasiva opMas = generarYGuardarModelosEnLasTablas(regAD, operacionMasiva, file,	aplicarDeudaRegistros, importeTotal);
			operacionMasiva.setPathArchivo(file.getName());

			UsuarioLdapDto analista = ldapServicio.buscarUsuarioPorUidEnMemoria(operacionMasiva.getIdAnalista());
			UsuarioLdapDto copropietario = ldapServicio.buscarUsuarioPorUidEnMemoria(operacionMasiva.getIdCopropietario());

			archivoOperacionMasivaProcesadoDto = cargarOperacionMasiva(opMas.getIdOperacionMasiva(),
			operacionMasiva.getPathArchivo(), TipoArchivoOperacionMasivaEnum.DEUDA.getDescripcion(), 
			registros.length,EstadoRegistroOperacionMasivaEnum.PENDIENTE_PROCESAR.getDescripcion(), importeTotal,
			copropietario, analista, opMas.getMotivo().getDescripcion());
			return archivoOperacionMasivaProcesadoDto;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (IOException ioe) {
			throw new NegocioExcepcion(ioe.getMessage(),ioe);
		}
	}
	/**
	 * Procesa un archivo del tipo Desistimiento. Si los registros son correctos
	 * los inserta en la base.
	 * @param registros
	 */
	public ArchivoOperacionMasivaProcesadoDto procesarRegistrosDesistimiento(String[] registros, OperacionMasivaDto operacionMasiva, File file) throws NegocioExcepcion {
	
		try {
			ArchivoOperacionMasivaProcesadoDto archivoOperacionMasivaProcesadoDto = null;
			List<RegistroOperacionMasivaDto> desistimientoRegistros = new ArrayList<RegistroOperacionMasivaDto>();
			BigDecimal importeTotal = new BigDecimal(0);

			for(String reg : registros) {
				String[] campos = this.obtenerCampos(reg);
				RegistroOperacionMasivaDesistimientoDto regDesistimiento = new RegistroOperacionMasivaDesistimientoDto();

				regDesistimiento.setClienteDuenoDebito(!Validaciones.isNullOrEmpty(campos[CLIENTE_DUENO_DEBITO_DESIST])?Long.valueOf(campos[CLIENTE_DUENO_DEBITO_DESIST]):null);
				regDesistimiento.setNumeroReferenciaFactura(!Validaciones.isNullOrEmpty(campos[NUMERO_REFERENCIA_FACTURA_DEUDA])?Long.valueOf(campos[NUMERO_REFERENCIA_FACTURA_DESIST]):null);
				regDesistimiento.setImporte(Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(campos[IMPORTE_DESISTIMIENTO]), 2));
				regDesistimiento.setDeudaMigrada(!Validaciones.isNullOrEmpty(campos[DEUDA_MIGRADA_DESISTIMIENTO])?SiNoEnum.getEnumByDescripcionCorta(campos[DEUDA_MIGRADA_DESISTIMIENTO]):null);
				regDesistimiento.setNumeroActaDesistimiento(!Validaciones.isNullOrEmpty(campos[NRO_ACTA_DESISTIMIENTO])?campos[NRO_ACTA_DESISTIMIENTO]:null);
				regDesistimiento.setFechaActaDesistimiento(!Validaciones.isNullOrEmpty(campos[FECHA_ACTA_DESISTIMIENTO])?Utilidad.deserializeAndFormatDate(campos[FECHA_ACTA_DESISTIMIENTO]):null);

				desistimientoRegistros.add(regDesistimiento);
				importeTotal = importeTotal.add(regDesistimiento.getImporte());
			}

			ShvMasRegistroDesistimiento desistimientoModelo = new ShvMasRegistroDesistimiento();
			operacionMasiva.setTipoOperacionMasiva(TipoArchivoOperacionMasivaEnum.DSIST);
			ShvMasOperacionMasiva opMas = generarYGuardarModelosEnLasTablas(desistimientoModelo, operacionMasiva, file, desistimientoRegistros, importeTotal);

			operacionMasiva.setPathArchivo(file.getName());
			UsuarioLdapDto analista = ldapServicio.buscarUsuarioPorUidEnMemoria(operacionMasiva.getIdAnalista());
			UsuarioLdapDto copropietario = ldapServicio.buscarUsuarioPorUidEnMemoria(operacionMasiva.getIdCopropietario());

			archivoOperacionMasivaProcesadoDto = cargarOperacionMasiva(opMas.getIdOperacionMasiva(),
					operacionMasiva.getPathArchivo(), TipoArchivoOperacionMasivaEnum.DSIST.getDescripcion(), 
					registros.length,EstadoRegistroOperacionMasivaEnum.PENDIENTE_APROBACION.getDescripcion(), importeTotal,
					copropietario, analista, opMas.getMotivo().getDescripcion());

			return archivoOperacionMasivaProcesadoDto;

		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (IOException ioe) {
			throw new NegocioExcepcion(ioe.getMessage(),ioe);
		}	
	}
	
	/**
	 * Procesa un archivo del tipo Ganancias. Si los registros son correctos
	 * los inserta en la base.
	 * @param registros
	 */
	public ArchivoOperacionMasivaProcesadoDto procesarRegistrosGanancias(String[] registros, OperacionMasivaDto operacionMasiva, File file) throws NegocioExcepcion {
		List<RegistroOperacionMasivaDto> gananciaRegistros =
				new ArrayList<RegistroOperacionMasivaDto>();
		BigDecimal importeTotal = new BigDecimal(0);

		ArchivoOperacionMasivaProcesadoDto archivoOperacionMasivaProcesadoDto = null;

		for (String reg : registros) {
			String[] campos = this.obtenerCampos(reg);
			RegistroOperacionMasivaGananciasDto regGanancias = 	new RegistroOperacionMasivaGananciasDto();

			regGanancias.setClienteDuenoCredito(!Validaciones.isNullOrEmpty(campos[CLIENTE_DUENO_CREDITO_GANCIA])?Long.valueOf(campos[CLIENTE_DUENO_CREDITO_GANCIA]):null);
			regGanancias.setCuentaOrigen(!Validaciones.isNullOrEmpty(campos[CUENTA_ORIGEN_GANCIA])?Long.valueOf(campos[CUENTA_ORIGEN_GANCIA]):null);
			regGanancias.setTipoRemanente(!Validaciones.isNullOrEmpty(campos[TIPO_REMANENTE_GANCIA])?TipoOrigenEnum.getEnumByName(campos[TIPO_REMANENTE_GANCIA]):null);
			regGanancias.setNumeroReferenciaNC(!Validaciones.isNullOrEmpty(campos[NRO_REFERENCIA_NC_GANCIA])?Long.valueOf(campos[NRO_REFERENCIA_NC_GANCIA]):null);
			regGanancias.setImporte(Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(campos[IMPORTE_GANCIA]), 2));
			regGanancias.setCreditoMigrado(!Validaciones.isNullOrEmpty(campos[CREDITO_MIGRADO_GANCIA])?SiNoEnum.getEnumByDescripcionCorta(campos[CREDITO_MIGRADO_GANCIA]):null);

			importeTotal = importeTotal.add(regGanancias.getImporte());
			gananciaRegistros.add(regGanancias);
		}
		ShvMasRegistroGanancias gannaciasModelo = new ShvMasRegistroGanancias();
		try {
			operacionMasiva.setTipoOperacionMasiva(TipoArchivoOperacionMasivaEnum.GNCIA);
			ShvMasOperacionMasiva opMas = generarYGuardarModelosEnLasTablas(gannaciasModelo, operacionMasiva, file, gananciaRegistros, importeTotal);
			operacionMasiva.setPathArchivo(file.getName());

			UsuarioLdapDto analista = ldapServicio.buscarUsuarioPorUidEnMemoria(operacionMasiva.getIdAnalista());
			UsuarioLdapDto copropietario = ldapServicio.buscarUsuarioPorUidEnMemoria(operacionMasiva.getIdCopropietario());
								
			archivoOperacionMasivaProcesadoDto = cargarOperacionMasiva(opMas.getIdOperacionMasiva(),
					operacionMasiva.getPathArchivo(), TipoArchivoOperacionMasivaEnum.GNCIA.getDescripcion(), 
					registros.length,EstadoRegistroOperacionMasivaEnum.PENDIENTE_PROCESAR.getDescripcion(), importeTotal,
					copropietario, analista, opMas.getMotivo().getDescripcion());
		} catch (PersistenciaExcepcion | IOException e) {
			throw new NegocioExcepcion (e.getMessage(), e);
		}

		return archivoOperacionMasivaProcesadoDto;
	}
	
	/**
	 * Procesa un archivo del tipo Reintegro. Si los registros son correctos
	 * los inserta en la base.
	 * @param registros
	 */
	public ArchivoOperacionMasivaProcesadoDto procesarRegistrosReintegro(String[] registros, OperacionMasivaDto operacionMasiva, File file) throws NegocioExcepcion {
		List<RegistroOperacionMasivaDto> reintegroRegistros = new ArrayList<RegistroOperacionMasivaDto>();
		BigDecimal importeTotal = new BigDecimal(0);
		
		ArchivoOperacionMasivaProcesadoDto archivoOperacionMasivaProcesadoDto = null;
		
		for (String reg : registros) {
			String[] campos = this.obtenerCampos(reg);
			RegistroOperacionMasivaReintegroDto regReintegro = new RegistroOperacionMasivaReintegroDto();

			regReintegro.setClienteDuenoCredito(!Validaciones.isNullOrEmpty(campos[CLIENTE_DUENO_CREDITO_REINT])?Long.valueOf(campos[CLIENTE_DUENO_CREDITO_REINT]):null);
			regReintegro.setCuenta(!Validaciones.isNullOrEmpty(campos[CUENTA_REINT])?Long.valueOf(campos[CUENTA_REINT]):null);
			regReintegro.setTipoRemanente(!Validaciones.isNullOrEmpty(campos[TIPO_REMANENTE_REINT])?TipoOrigenEnum.getEnumByName(campos[TIPO_REMANENTE_REINT]):null);
			regReintegro.setNumeroReferenciaNC(!Validaciones.isNullOrEmpty(campos[NUMERO_REFERENCIA_NC_REINT])?Long.valueOf(campos[NUMERO_REFERENCIA_NC_REINT]):null);
			regReintegro.setNumeroDocumento(!Validaciones.isNullOrEmpty(campos[NUMERO_DOCUMENTO_REINT])?campos[NUMERO_DOCUMENTO_REINT]:null);
			regReintegro.setCreditoMigrado(!Validaciones.isNullOrEmpty(campos[CREDITO_MIGRADO_REINT])?SiNoEnum.getEnumByDescripcionCorta(campos[CREDITO_MIGRADO_REINT]):null);
			regReintegro.setImporte(Utilidad.formatCurrencyBD(Utilidad.stringToBigDecimal(campos[IMPORTE_REINT]), 2));
			regReintegro.setTramiteReintegro(!Validaciones.isNullOrEmpty(campos[TRAMITE_REINTEGRO])?Long.valueOf(campos[TRAMITE_REINTEGRO]):null);
			regReintegro.setFechaAltaTramiteReintegro(!Validaciones.isNullOrEmpty(campos[FECHA_ALTA_TRAMITE_REINTEGRO])?Utilidad.deserializeAndFormatDate(campos[FECHA_ALTA_TRAMITE_REINTEGRO]):null);
			regReintegro.setTipoReintegro(!Validaciones.isNullOrEmpty(campos[TIPO_REINTEGRO])?TipoReintegroEnum.getEnumByName(campos[TIPO_REINTEGRO]):null);
			regReintegro.setClienteDuenoAcuerdoFacturacion(!Validaciones.isNullOrEmpty(campos[CLIENTE_DUENO_ACUERDO_FACTURACION_REINT])?Long.valueOf(campos[CLIENTE_DUENO_ACUERDO_FACTURACION_REINT]):null);
			regReintegro.setAcuerdoFacturacionDestino(!Validaciones.isNullOrEmpty(campos[ACUERDO_FACTURACION_DESTINO_REINT])?Long.valueOf(campos[ACUERDO_FACTURACION_DESTINO_REINT]):null);		
			regReintegro.setLineaDestino(!Validaciones.isNullOrEmpty(campos[LINEA_DESTINO])?Long.valueOf(campos[LINEA_DESTINO]):null);
			regReintegro.setReintegraConInteres(!Validaciones.isNullOrEmpty(campos[REINTEGRA_CON_INTERESES])?SiNoEnum.getEnumByDescripcionCorta(campos[REINTEGRA_CON_INTERESES]):null);
			
			importeTotal = importeTotal.add(regReintegro.getImporte());
			reintegroRegistros.add(regReintegro);
		}
		
		ShvMasRegistroReintegro reintegroModelo = new ShvMasRegistroReintegro();
		try {
			operacionMasiva.setTipoOperacionMasiva(TipoArchivoOperacionMasivaEnum.REINT);
			ShvMasOperacionMasiva opMas = generarYGuardarModelosEnLasTablas(reintegroModelo, operacionMasiva, file, reintegroRegistros, importeTotal);
			operacionMasiva.setPathArchivo(file.getName());

			UsuarioLdapDto analista = ldapServicio.buscarUsuarioPorUidEnMemoria(operacionMasiva.getIdAnalista());
			UsuarioLdapDto copropietario = ldapServicio.buscarUsuarioPorUidEnMemoria(operacionMasiva.getIdCopropietario());

			archivoOperacionMasivaProcesadoDto = cargarOperacionMasiva(opMas.getIdOperacionMasiva(),
					operacionMasiva.getPathArchivo(), TipoArchivoOperacionMasivaEnum.REINT.getDescripcion(), 
					registros.length,EstadoRegistroOperacionMasivaEnum.PENDIENTE_PROCESAR.getDescripcion(), importeTotal,
					copropietario, analista, opMas.getMotivo().getDescripcion());

		} catch (PersistenciaExcepcion | IOException e) {
				throw new NegocioExcepcion (e.getMessage(), e);
		}
		
		return archivoOperacionMasivaProcesadoDto;
	}
	
	/**
	 * Este metodo no esta desarrollado por falta de informacion.
	 * @param registros
	 */
	public void procesarRegistrosCuotasDeConvenio(String[] registros, OperacionMasivaDto operacionMasiva, File file) throws NegocioExcepcion {
	}

	/**
	 * Retorna un array con los campos del registro
	 * @param registro
	 * @return
	 */
	private String[] obtenerCampos(String registro) {
		// Se agrega un espacio al registro para que si el último campo no vienen nada, no rebote 
		// el archivo por cantidad de campos necesarios
		return Utilidad.limpiarArrayString((registro + " ").split(SEMICOLON));
	}
	
	/**
	 * Agregar 
	 * @param numeroRegistro
	 * @param mensaje
	 */
	private String agregarMensajeError(int numeroRegistro, String mensaje) {
		return Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarInformacionError"), 
					String.valueOf(numeroRegistro), mensaje);
	}
	
	/** 
	 * Archivo Aplicar Deuda - Indice de campos del registro 
	 * 1	Tipo Operación				Alfanumérico	1	"T: Saldo total del documento P: Saldo parcial del documento"
	 * 2	Cliente dueño del débito	Numérico	10	
	 * 3	Número referencia Factura	Numérico	15	
	 * 4	Destransferir terceros		Alfanumérico	1	"S N"
	 * 5	Deuda migrada				Alfanumérico	1	"S N"
	 * 6	Importe						Numérico	10	Expresado en centavos
	 * 7	Cliente dueño del crédito	Numérico	10	
	 * 8	Cuenta						Numérico	13	
	 * 9	Tipo Remanente				Alfanumérico	3	"RT1: no actualizable/transferible RT2: no actualizable/no transferible Vacío: en caso que el cobro se haga con una nota de crédito"
	 * 10	Número Referencia NC		Numérico	15	"Número de referencia de la nota de crédito Vacío: en caso que el cobro se haga con remanente"
	 * 11	Crédito migrado				Alfanumérico	1	"S N Vacío: en caso que el cobro se haga con remanente" 
	 * 12	Acción sobre intereses		Alfanumérico	2	"BV BM TV TM"
	 * 13	Porcentaje bonificación intereses	Numérico	3	"Expresado en entero (solo es válido para la opción de tratamiento de intereses BV) Vacío: en caso que se decida no llevar a cabo bonificación de intereses o bien la bonificación se expresa en un importe"
	 * 14	Importe bonificación intereses		Numérico	10	"Expresado en centavos (solo es válido para la opción de tratamiento de intereses BV) Vacío: en caso que se decida no llevar a cabo bonificación de intereses o bien la bonificación se expresa en un porcentaje"
	 * 15	Cliente dueño del acuerdo de facturación	Numérico	10	Vacío: en caso que se decida no trasferir intereses a próxima factura (esto es tanto transferencia total de intereses o bien parcial por bonificación parcial)
	 * 16	Acuerdo de Facturación Destino				Numérico	10	"Acuerdo de facturación destino Vacío: en caso que se decida no trasferir intereses a próxima factura (esto es tanto transferencia total de intereses o bien parcial por bonificación parcial)"
	 ****/
	private boolean validarCamposAplicarDeuda(String[] campos, int nroRegistro, ImportarOperacionesMasivasAuxiliar importarOperacionesMasivasAuxiliar) {
		boolean hayError = false;
		importarOperacionesMasivasAuxiliar.getValidacionParcial().setLength(0);
		
		//Evalua la cantidad de campos en una linea
		if (campos.length == getLengthCamposAplicarDeuda().length) {

			if ((!Validaciones.isNullOrEmpty(campos[TIPO_REMANENTE])) 
					&& (!Validaciones.isNullOrEmpty(campos[NUMERO_REFERENCIA_NC]))) {
				
				String mensaje = agregarMensajeError(nroRegistro, 
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposDeuda.TipoRemNroRefNC.excluyentes"));
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else 
			if (Validaciones.isNullOrEmpty(campos[TIPO_REMANENTE]) 
					&& Validaciones.isNullOrEmpty(campos[NUMERO_REFERENCIA_NC])) {
				
				String mensaje = agregarMensajeError(nroRegistro, 
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposDeuda.TipoRemNroRefNC.vacio"));
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {	
				// Tipo remanente
				if (Validaciones.isNullOrEmpty(campos[NUMERO_REFERENCIA_NC])){
					if ((campos[TIPO_REMANENTE].length() > getLengthCamposAplicarDeuda()[TIPO_REMANENTE]) 
							&& (campos[TIPO_REMANENTE].length() > 0)) {
						
						String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
								"Tipo Remanente", String.valueOf(getLengthCamposAplicarDeuda()[TIPO_REMANENTE]));
						
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					} else 
					if (Validaciones.isObjectNull(TipoOrigenEnum.getEnumByName1y2(campos[TIPO_REMANENTE]))) {
						if (!Validaciones.isEmptyString(campos[TIPO_REMANENTE])) {
							String valoresPosibles = "Vacio";
							valoresPosibles += WHITESPACE + TipoOrigenEnum.RT1;
							valoresPosibles += WHITESPACE + TipoOrigenEnum.RT2;
							
							String mensajeError = Utilidad.reemplazarMensajes(
									Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampo"), 
										"Tipo Remanente", valoresPosibles);
							String mensaje = agregarMensajeError(nroRegistro, mensajeError);
							importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
							hayError = true;
						} 
					}
				}

				// Numero de referencia NC
				if (Validaciones.isNullOrEmpty(campos[TIPO_REMANENTE])){
					if ((campos[NUMERO_REFERENCIA_NC].length() > getLengthCamposAplicarDeuda()[NUMERO_REFERENCIA_NC]) 
							&& (campos[NUMERO_REFERENCIA_NC].length() > 0)) {
						
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
									"Número de Referencia NC", String.valueOf(getLengthCamposAplicarDeuda()[NUMERO_REFERENCIA_NC]));
							
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					} else {
						if (!Validaciones.isEmptyString(campos[NUMERO_REFERENCIA_NC])
								&& !Validaciones.isNumeric(campos[NUMERO_REFERENCIA_NC])) {
							String mensajeError = Utilidad.reemplazarMensajes(
									Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
										"Número de Referencia NC");
							String mensaje = agregarMensajeError(nroRegistro, mensajeError);
							importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
							hayError = true;
						} else {
							if (Validaciones.isEmptyString(campos[NUMERO_REFERENCIA_NC])) {
								String mensajeError = Utilidad.reemplazarMensajes(
									Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorObligatorio"), 
									"Número de Referencia NC");
								String mensaje = agregarMensajeError(nroRegistro, mensajeError);
								importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
								hayError = true;
							} else {
								if (Validaciones.isNumeric(campos[NUMERO_REFERENCIA_NC])) {
									if (Validaciones.isEmptyNumericoSerializado(campos[NUMERO_REFERENCIA_NC])) {
										String mensajeError = Utilidad.reemplazarMensajes(
											Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampoNoValido"), 
											"Número de Referencia NC");
										String mensaje = agregarMensajeError(nroRegistro, mensajeError);
										importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
										hayError = true;
									}
								}
							}
						}
					}
				}
			}
			
			// Tipo de Operacion
			if (campos[TIPO_OPERACION].length() != getLengthCamposAplicarDeuda()[TIPO_OPERACION]) {
				
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Tipo Operación", String.valueOf(getLengthCamposAplicarDeuda()[TIPO_OPERACION]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (Validaciones.isEmptyString(campos[TIPO_OPERACION])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorObligatorio"), 
							"Tipo Operacion");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (!campos[TIPO_OPERACION].equalsIgnoreCase("T") 
							&& !campos[TIPO_OPERACION].equalsIgnoreCase("P")) {
						
						String valoresPosibles = "T o P";
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampo"), 
									"Tipo Operacion", valoresPosibles);
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}
				}
			}
				
			// Cuenta
			if (campos[CUENTA_DEUDA].length() > getLengthCamposAplicarDeuda()[CUENTA_DEUDA]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Cuenta", String.valueOf(getLengthCamposAplicarDeuda()[CUENTA_DEUDA]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (Validaciones.isEmptyString(campos[CUENTA_DEUDA]) && !Validaciones.isEmptyString(campos[TIPO_REMANENTE])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorObligatorioConTipoRemanente"), 
							"Cuenta");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (!Validaciones.isEmptyString(campos[CUENTA_DEUDA]) &&
							!Validaciones.isNumeric(campos[CUENTA_DEUDA])) {
						
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
									"Cuenta");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					} else if (Validaciones.isEmptyNumericoSerializado(campos[CUENTA_DEUDA])) {
							String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampoNoValido"), 
									"Cuenta");
							String mensaje = agregarMensajeError(nroRegistro, mensajeError);
							importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
							hayError = true;
					}
				}
			}

			// Numero referencia factura
			if (campos[NUMERO_REFERENCIA_FACTURA_DEUDA].length() > getLengthCamposAplicarDeuda()[NUMERO_REFERENCIA_FACTURA_DEUDA]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Número de Referencia Factura", String.valueOf(getLengthCamposAplicarDeuda()[NUMERO_REFERENCIA_FACTURA_DEUDA]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (Validaciones.isEmptyString(campos[NUMERO_REFERENCIA_FACTURA_DEUDA])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorObligatorio"), 
								"Número de Referencia Factura");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (!Validaciones.isNumeric(campos[NUMERO_REFERENCIA_FACTURA_DEUDA])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
									"Número de Referencia Factura");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					} else if (Validaciones.isEmptyNumericoSerializado(campos[NUMERO_REFERENCIA_FACTURA_DEUDA])) {
							String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampoNoValido"), 
									"Número de Referencia Factura");
							String mensaje = agregarMensajeError(nroRegistro, mensajeError);
							importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
							hayError = true;
					}
				}
			}

			// Importe
			if (campos[IMPORTE_DEUDA].length() > getLengthCamposAplicarDeuda()[IMPORTE_DEUDA]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Importe", String.valueOf(getLengthCamposAplicarDeuda()[IMPORTE_DEUDA]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (Validaciones.isEmptyString(campos[IMPORTE_DEUDA])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorObligatorio"), 
								"Importe");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (!Validaciones.isNumeric(campos[IMPORTE_DEUDA])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
									"Importe");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					} else if (Validaciones.isEmptyNumericoSerializado(campos[IMPORTE_DEUDA])) {
						String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampoNoValido"), 
							"Importe");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}
				}
			}
			
			// Terceros
			if (campos[DESTRANSFERIR_TERCEROS].length() > getLengthCamposAplicarDeuda()[DESTRANSFERIR_TERCEROS]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Destransferir Terceros", String.valueOf(getLengthCamposAplicarDeuda()[DESTRANSFERIR_TERCEROS]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (Validaciones.isEmptyString(campos[DESTRANSFERIR_TERCEROS])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorObligatorio"), 
								"Destransferir Terceros");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (Validaciones.isObjectNull(SiNoEnum.getEnumByDescripcionCorta(campos[DESTRANSFERIR_TERCEROS].trim()))) {
						String valoresPosibles = "";
						for (SiNoEnum enumOrigen : SiNoEnum.values()) {
							valoresPosibles += WHITESPACE + enumOrigen.getDescripcionCorta();
						}
						
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampo"), 
									"Destransferir Terceros", valoresPosibles);
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}
				}
			}
			
			// Deuda Migrada
			if (campos[DEUDA_MIGRADA].length() > getLengthCamposAplicarDeuda()[DEUDA_MIGRADA]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Deuda Migrada", String.valueOf(getLengthCamposAplicarDeuda()[DEUDA_MIGRADA]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (Validaciones.isEmptyString(campos[DEUDA_MIGRADA])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorObligatorio"), 
								"Deuda Migrada");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (Validaciones.isObjectNull(SiNoEnum.getEnumByDescripcionCorta(campos[DEUDA_MIGRADA]))) {
						String valoresPosibles = "";
						for (SiNoEnum enumOrigen : SiNoEnum.values()) {
							valoresPosibles += WHITESPACE + enumOrigen.getDescripcionCorta();
						}
						
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampo"), 
									"Deuda Migrada", valoresPosibles);
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}
				}
			}
			
			// CLIENTE_DUENO_DEBITO
			if (campos[CLIENTE_DUENO_DEBITO_DEUDA].length() > getLengthCamposAplicarDeuda()[CLIENTE_DUENO_DEBITO_DEUDA]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Cliente Dueño Debito", String.valueOf(getLengthCamposAplicarDeuda()[CLIENTE_DUENO_DEBITO_DEUDA]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (Validaciones.isEmptyString(campos[CLIENTE_DUENO_DEBITO_DEUDA])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorObligatorio"), 
								"Cliente Dueño Debito");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (!Validaciones.isNumeric(campos[CLIENTE_DUENO_DEBITO_DEUDA])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
									"Cliente Dueño Debito");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					} else if (!Validaciones.isEmptyString(campos[CLIENTE_DUENO_DEBITO_DEUDA])
							&& Constantes.CERO == Long.valueOf(campos[CLIENTE_DUENO_DEBITO_DEUDA])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampoNoValido"), 
									"Cliente Dueño Debito");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					} else if (Validaciones.isEmptyNumericoSerializado(campos[CLIENTE_DUENO_DEBITO_DEUDA])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampoNoValido"), 
								"Cliente Dueño Debito");
							String mensaje = agregarMensajeError(nroRegistro, mensajeError);
							importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
							hayError = true;
						}
				}
			}
			
			// CLIENTE_DUENO_CREDITO
			if (campos[CLIENTE_DUENO_CREDITO].length() > getLengthCamposAplicarDeuda()[CLIENTE_DUENO_CREDITO]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Cliente Dueño Credito", String.valueOf(getLengthCamposAplicarDeuda()[CLIENTE_DUENO_CREDITO]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (Validaciones.isEmptyString(campos[CLIENTE_DUENO_CREDITO])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorObligatorio"), 
								"Cliente Dueño Credito");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (!Validaciones.isNumeric(campos[CLIENTE_DUENO_CREDITO])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
									"Cliente Dueño Credito");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}else if (!Validaciones.isEmptyString(campos[CLIENTE_DUENO_CREDITO])
							&& Constantes.CERO == Long.valueOf(campos[CLIENTE_DUENO_CREDITO])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampoNoValido"), 
									"Cliente Dueño Credito");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					} else if (Validaciones.isEmptyNumericoSerializado(campos[CLIENTE_DUENO_CREDITO])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampoNoValido"), 
								"Cliente Dueño Credito");
							String mensaje = agregarMensajeError(nroRegistro, mensajeError);
							importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
							hayError = true;
					}
				}
			}
			
			// CREDITO_MIGRADO
			if (campos[CREDITO_MIGRADO].length() > getLengthCamposAplicarDeuda()[CREDITO_MIGRADO]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Credito Migrado", String.valueOf(getLengthCamposAplicarDeuda()[CREDITO_MIGRADO]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (Validaciones.isObjectNull(SiNoEnum.getEnumByDescripcionCorta(campos[CREDITO_MIGRADO]))
						&& !Validaciones.isEmptyString(campos[CREDITO_MIGRADO])) 
				{
					String valoresPosibles = "Vacio";
					for (SiNoEnum enume : SiNoEnum.values()) {
						valoresPosibles += WHITESPACE + enume.getDescripcionCorta();
					}
					
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampo"), 
							"Credito Migrado", valoresPosibles);
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true; 
				}
			}
			
			// Accion sobre intereses
			TratamientoInteresesEnum accionSobreIntereses = null;

			if (campos[ACCION_SOBRE_INTERESES].length() > getLengthCamposAplicarDeuda()[ACCION_SOBRE_INTERESES]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Accion sobre intereses", String.valueOf(getLengthCamposAplicarDeuda()[ACCION_SOBRE_INTERESES]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (Validaciones.isEmptyString(campos[ACCION_SOBRE_INTERESES])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorObligatorio"), 
								"Accion sobre intereses");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					accionSobreIntereses = TratamientoInteresesEnum.getEnumByNameAccionIntereses(campos[ACCION_SOBRE_INTERESES].trim());
					if (Validaciones.isObjectNull(accionSobreIntereses)) {
						String valoresPosibles = "";
						for (TratamientoInteresesEnum enume : TratamientoInteresesEnum.getValuesAccionIntereses()) {
							valoresPosibles += WHITESPACE + enume.name();
						}
						
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampo"), 
									"Accion sobre intereses", valoresPosibles);
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}
				}
			}
			// PORCENTAJE_BONIFICACION_INTERESES
			// Si es BV puede que tenga Porcentaje Bonifiacin interses
			if (
				!Validaciones.isObjectNull(accionSobreIntereses) &&
				TratamientoInteresesEnum.BV.equals(accionSobreIntereses)
			) {
				if (campos[PORCENTAJE_BONIFICACION_INTERESES].length() > getLengthCamposAplicarDeuda()[PORCENTAJE_BONIFICACION_INTERESES]) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
								"Porcentaje Bonificacion Intereses", String.valueOf(getLengthCamposAplicarDeuda()[PORCENTAJE_BONIFICACION_INTERESES]));
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (!Validaciones.isEmptyString(campos[PORCENTAJE_BONIFICACION_INTERESES])
							&& !Validaciones.isNumeric(campos[PORCENTAJE_BONIFICACION_INTERESES])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
									"Porcentaje Bonificacion Intereses");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}
				}
			} else {
				if (!Validaciones.isEmptyString(campos[PORCENTAJE_BONIFICACION_INTERESES])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposDeuda.PorcentajeBonificacion.lleno.no.BV"), 
								"Porcentaje Bonificacion Intereses");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				}
			}
			
			if (
				!Validaciones.isObjectNull(accionSobreIntereses) &&
				TratamientoInteresesEnum.BV.equals(accionSobreIntereses)
			) {
				// IMPORTE_BONIFICACION_INTERESES
				if (campos[IMPORTE_BONIFICACION_INTERESES].length() > getLengthCamposAplicarDeuda()[IMPORTE_BONIFICACION_INTERESES]) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
								"Importe Bonificacion Intereses", String.valueOf(getLengthCamposAplicarDeuda()[IMPORTE_BONIFICACION_INTERESES]));
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (!Validaciones.isEmptyString(campos[IMPORTE_BONIFICACION_INTERESES])
							&& !Validaciones.isNumeric(campos[IMPORTE_BONIFICACION_INTERESES])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
									"Importe Bonificacion Intereses");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}
				}
			} else {
				if (!Validaciones.isEmptyString(campos[IMPORTE_BONIFICACION_INTERESES])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposDeuda.ImporteBonificacion.lleno.no.BV"), 
								"Importe Bonificacion Intereses");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				}
			}
				
			
			// Cliente Dueño Acuerdo Facturacion
			if (campos[CLIENTE_DUENO_ACUERDO_FACTURACION].length() > getLengthCamposAplicarDeuda()[CLIENTE_DUENO_ACUERDO_FACTURACION]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Cliente Dueño Acuerdo Facturacion", String.valueOf(getLengthCamposAplicarDeuda()[CLIENTE_DUENO_ACUERDO_FACTURACION]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (!Validaciones.isEmptyString(campos[CLIENTE_DUENO_ACUERDO_FACTURACION])
						&& !Validaciones.isNumeric(campos[CLIENTE_DUENO_ACUERDO_FACTURACION])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
								"Cliente Dueño Acuerdo Facturacion");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				}else if (!Validaciones.isEmptyString(campos[CLIENTE_DUENO_ACUERDO_FACTURACION])
						&& Constantes.CERO == Long.valueOf(campos[CLIENTE_DUENO_ACUERDO_FACTURACION])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampoNoValido"), 
								"Cliente Dueño Acuerdo Facturacion");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				}
				
			}
			
			// Cliente Dueño Acuerdo Facturacion
			if (campos[ACUERDO_FACTURACION_DESTINO].length() > getLengthCamposAplicarDeuda()[ACUERDO_FACTURACION_DESTINO]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Acuerdo Facturacion Destino", String.valueOf(getLengthCamposAplicarDeuda()[ACUERDO_FACTURACION_DESTINO]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (!Validaciones.isEmptyString(campos[ACUERDO_FACTURACION_DESTINO])
						&& !Validaciones.isNumeric(campos[ACUERDO_FACTURACION_DESTINO])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
								"Acuerdo Facturacion Destino");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				}
			}
			if (
					!Validaciones.isObjectNull(accionSobreIntereses) &&
					TratamientoInteresesEnum.BV.equals(accionSobreIntereses)
			) {
				if ((!Validaciones.isNullOrEmpty(campos[PORCENTAJE_BONIFICACION_INTERESES])) 
						&& (!Validaciones.isNullOrEmpty(campos[IMPORTE_BONIFICACION_INTERESES])
							&& Validaciones.isNumeric(campos[IMPORTE_BONIFICACION_INTERESES])
							&& Long.valueOf(campos[IMPORTE_BONIFICACION_INTERESES]).compareTo(new Long(0))!=0)) {
				
					String mensaje = agregarMensajeError(nroRegistro, 
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposDeuda.PorcentajeImporteBonificacion.excluyentes"));
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				}  
			}
			if ((!Validaciones.isNullOrEmpty(campos[NUMERO_REFERENCIA_NC])) 
					&& (Validaciones.isNullOrEmpty(campos[CREDITO_MIGRADO]))) {
				
				String mensaje = agregarMensajeError(nroRegistro, 
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposDeuda.NroRefNCCreditoMigrado"));
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} 
			
			if ((!Validaciones.isNullOrEmpty(campos[TIPO_REMANENTE])) 
					&& (!Validaciones.isNullOrEmpty(campos[CREDITO_MIGRADO]))) {
				
				String mensaje = agregarMensajeError(nroRegistro, 
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposDeuda.TipoRemNoCreditoMigrado"));
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} 
			
			if ((!Validaciones.isNullOrEmpty(campos[ACCION_SOBRE_INTERESES])
					&& TratamientoInteresesEnum.BV.equals(TratamientoInteresesEnum.getEnumByName(campos[ACCION_SOBRE_INTERESES])))
				&& ((Validaciones.isNumeric(campos[PORCENTAJE_BONIFICACION_INTERESES])
						&& "100".equalsIgnoreCase(campos[PORCENTAJE_BONIFICACION_INTERESES]))
					|| (Validaciones.isNumeric(campos[IMPORTE_BONIFICACION_INTERESES])
						&& Long.valueOf(campos[IMPORTE_BONIFICACION_INTERESES]).compareTo(new Long(0))==0))) {
				
				String mensaje = agregarMensajeError(nroRegistro, 
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposDeuda.TratamientoBV1"));
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} 
			
			if ((!Validaciones.isNullOrEmpty(campos[ACCION_SOBRE_INTERESES])
					&& (TratamientoInteresesEnum.TV.equals(TratamientoInteresesEnum.getEnumByName(campos[ACCION_SOBRE_INTERESES]))
							|| TratamientoInteresesEnum.TM.equals(TratamientoInteresesEnum.getEnumByName(campos[ACCION_SOBRE_INTERESES]))
							|| TratamientoInteresesEnum.BV.equals(TratamientoInteresesEnum.getEnumByName(campos[ACCION_SOBRE_INTERESES]))))
					&& ((Validaciones.isNumeric(campos[PORCENTAJE_BONIFICACION_INTERESES])
						&& "100".equalsIgnoreCase(campos[PORCENTAJE_BONIFICACION_INTERESES])))
					&& (!Validaciones.isNullOrEmpty(campos[ACUERDO_FACTURACION_DESTINO]))) {
				
				String mensaje = agregarMensajeError(nroRegistro, 
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposDeuda.TratamientoBV2"));
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			}
		} else {
			String mensajeAuxiliar = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCantCampos"), 
						String.valueOf(getLengthCamposAplicarDeuda().length), String.valueOf(campos.length));
			String mensaje = agregarMensajeError(nroRegistro, mensajeAuxiliar);
			importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
			hayError = true;
		}
		
		if (hayError) {
			importarOperacionesMasivasAuxiliar.getResultadoValidaciones().append(importarOperacionesMasivasAuxiliar.getValidacionParcial().toString());
		}
		
		return hayError;
	}
	
	/**
	 * Valida campos correspondiente a la operacion Masiva Desistimiento
	 * @param campos
	 * @param nroRegistro
	 * @return
	 */
	private boolean validarCamposDesistimiento(String[] campos, int nroRegistro, ImportarOperacionesMasivasAuxiliar importarOperacionesMasivasAuxiliar) {
		boolean hayError = false;
		importarOperacionesMasivasAuxiliar.getValidacionParcial().setLength(0);
		
		if (campos.length == getLengthCamposDesistimiento().length) {
			
			// Cliente Dueño Debito
			if (campos[CLIENTE_DUENO_DEBITO_DESIST].length() > getLengthCamposDesistimiento()[CLIENTE_DUENO_DEBITO_DESIST]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Cliente Dueño Debito", String.valueOf(getLengthCamposDesistimiento()[CLIENTE_DUENO_DEBITO_DESIST]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (Validaciones.isEmptyString(campos[CLIENTE_DUENO_DEBITO_DESIST])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorObligatorio"), 
								"Cliente Dueño Debito");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (!Validaciones.isNumeric(campos[CLIENTE_DUENO_DEBITO_DESIST])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
									"Cliente Dueño Debito");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}else if (!Validaciones.isEmptyString(campos[CLIENTE_DUENO_DEBITO_DESIST])
							&& Constantes.CERO == Long.valueOf(campos[CLIENTE_DUENO_DEBITO_DESIST])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampoNoValido"), 
									"Cliente Dueño Debito");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}
				}
			}
			
			
			// Numero referencia factura
			if (campos[NUMERO_REFERENCIA_FACTURA_DESIST].length() > getLengthCamposDesistimiento()[NUMERO_REFERENCIA_FACTURA_DESIST]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Número de Referencia Factura", String.valueOf(getLengthCamposDesistimiento()[NUMERO_REFERENCIA_FACTURA_DESIST]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (Validaciones.isEmptyString(campos[NUMERO_REFERENCIA_FACTURA_DESIST])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorObligatorio"), 
							"Número de Referencia Factura");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (!Validaciones.isNumeric(campos[NUMERO_REFERENCIA_FACTURA_DESIST])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
									"Número de Referencia Factura");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					} else if (Validaciones.isEmptyNumericoSerializado(campos[NUMERO_REFERENCIA_FACTURA_DESIST])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampoNoValido"), 
								"Número de Referencia Factura");
							String mensaje = agregarMensajeError(nroRegistro, mensajeError);
							importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
							hayError = true;
					}
				}
			}

			// Importe
			if (campos[IMPORTE_DESISTIMIENTO].length() > getLengthCamposDesistimiento()[IMPORTE_DESISTIMIENTO]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Importe", String.valueOf(getLengthCamposDesistimiento()[IMPORTE_DESISTIMIENTO]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (Validaciones.isEmptyString(campos[IMPORTE_DESISTIMIENTO])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorObligatorio"), 
							"Importe");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (!Validaciones.isNumeric(campos[IMPORTE_DESISTIMIENTO])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
									"Importe");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					} else if (Validaciones.isEmptyNumericoSerializado(campos[IMPORTE_DESISTIMIENTO])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampoNoValido"), 
								"Importe");
							String mensaje = agregarMensajeError(nroRegistro, mensajeError);
							importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
							hayError = true;
					}
				}
			}
			
			// Deuda Migrada
			if (campos[DEUDA_MIGRADA_DESISTIMIENTO].length() > getLengthCamposDesistimiento()[DEUDA_MIGRADA_DESISTIMIENTO]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Deuda Migrada", String.valueOf(getLengthCamposDesistimiento()[DEUDA_MIGRADA_DESISTIMIENTO]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (Validaciones.isEmptyString(campos[DEUDA_MIGRADA_DESISTIMIENTO])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorObligatorio"), 
							"Deuda Migrada");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (Validaciones.isObjectNull(SiNoEnum.getEnumByDescripcionCorta(campos[DEUDA_MIGRADA_DESISTIMIENTO]))) {
						String valoresPosibles = "";
						for (SiNoEnum enumOrigen : SiNoEnum.values()) {
							valoresPosibles += WHITESPACE + enumOrigen.getDescripcionCorta();
						}
						
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampo"), 
									"Deuda Migrada", valoresPosibles);
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}
				}
			}
			
			//
			if (campos[NRO_ACTA_DESISTIMIENTO].length() > getLengthCamposDesistimiento()[NRO_ACTA_DESISTIMIENTO]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Número de Acta Desistimiento", String.valueOf(getLengthCamposDesistimiento()[NRO_ACTA_DESISTIMIENTO]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (Validaciones.isEmptyString(campos[NRO_ACTA_DESISTIMIENTO])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorAlfanumerico"), 
								"Número de Acta Desistimiento");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				}
			}
			
			//
			if (campos[FECHA_ACTA_DESISTIMIENTO].length() != getLengthCamposDesistimiento()[FECHA_ACTA_DESISTIMIENTO]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Fecha de Acta Desistimiento", String.valueOf(getLengthCamposDesistimiento()[FECHA_ACTA_DESISTIMIENTO]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (Validaciones.isEmptyString(campos[FECHA_ACTA_DESISTIMIENTO])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorObligatorio"), 
							"Fecha de Acta Desistimiento");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					try{
						if(Validaciones.isObjectNull(Utilidad.parseDateWSFormat(campos[FECHA_ACTA_DESISTIMIENTO]))){
							throw new ParseException("", 0);
						}
					} catch(ParseException e){
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorFecha"), 
									"Fecha de Acta Desistimiento");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}
				}
			}			
			
		} else {
			String mensajeAuxiliar = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCantCampos"), 
						String.valueOf(getLengthCamposDesistimiento().length), String.valueOf(campos.length));
			String mensaje = agregarMensajeError(nroRegistro, mensajeAuxiliar);
			importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
			hayError = true; 
		}
		
		if (hayError) {
			importarOperacionesMasivasAuxiliar.getResultadoValidaciones().append(importarOperacionesMasivasAuxiliar.getValidacionParcial().toString());
		}
		
		return hayError;
	}
	
	/**
	 * Valida campos correspondiente a la operacion Masiva GANANCIAS
	 * @param campos
	 * @param nroRegistro
	 * @return
	 * 
	 * 1	Cliente dueño del crédito			Numérico	10	
	 * 2	Cuenta			Numérico	13	
	 * 3	Tipo Remanente			Alfanumérico	3	"RT1: no actualizable/transferible RT2: no actualizable/no transferible Vacío: en caso que el cobro se haga con una nota de crédito"
	 * 4	Número Referencia NC			Numérico	15	"Número de referencia de la nota de crédito Vacío: en caso que el cobro se haga con remanente"
	 * 5	Importe			Numérico	10	Expresado en centavos
	 * 6	Crédito migrado			Alfanumérico	1	"S N Vacío: en caso que el cobro se haga con remanente"
	 *	
	 * Se deberá validar la consistencia del registro en sí mismo:
	 *	•	Tipo Remanente y Número Referencia NC son excluyentes.
	 *	•	Si se informa Número de Referencia NC se debe informa la marca de crédito migrado.
	 */
	private boolean validarCamposGanacias (String[] campos, int nroRegistro, ImportarOperacionesMasivasAuxiliar importarOperacionesMasivasAuxiliar) {
		boolean hayError = false;
		importarOperacionesMasivasAuxiliar.getValidacionParcial().setLength(0);
		
		if (campos.length == getLengthCamposGanancias().length) {
			if ((!Validaciones.isNullOrEmpty(campos[TIPO_REMANENTE_GANCIA])) 
					&& (!Validaciones.isNullOrEmpty(campos[NRO_REFERENCIA_NC_GANCIA]))) {
				
				String mensaje = agregarMensajeError(nroRegistro, 
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposGanancia"));
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else 
			if (Validaciones.isNullOrEmpty(campos[TIPO_REMANENTE_GANCIA]) 
					&& Validaciones.isNullOrEmpty(campos[NRO_REFERENCIA_NC_GANCIA])) {
				
				String mensaje = agregarMensajeError(nroRegistro, 
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposGananciaVacio"));
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {	
				// Tipo remanente
				if (Validaciones.isNullOrEmpty(campos[NRO_REFERENCIA_NC_GANCIA])){
					if ((campos[TIPO_REMANENTE_GANCIA].length() > getLengthCamposGanancias()[TIPO_REMANENTE_GANCIA]) 
							&& (campos[TIPO_REMANENTE_GANCIA].length() > 0)) {
						
						String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
								"Tipo Remanente", String.valueOf(getLengthCamposGanancias()[TIPO_REMANENTE_GANCIA]));
						
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					} else 
					if (Validaciones.isObjectNull(TipoOrigenEnum.getEnumByName1y2(campos[TIPO_REMANENTE_GANCIA]))) {
						if (!Validaciones.isEmptyString(campos[TIPO_REMANENTE_GANCIA])) {
							String valoresPosibles = "Vacio";
							valoresPosibles += WHITESPACE + TipoOrigenEnum.RT1;
							valoresPosibles += WHITESPACE + TipoOrigenEnum.RT2;
														
							String mensajeError = Utilidad.reemplazarMensajes(
									Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampo"), 
										"Tipo Remanente", valoresPosibles);
							String mensaje = agregarMensajeError(nroRegistro, mensajeError);
							importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
							hayError = true;
						} 
					}
				}

				// Numero de referencia NC
				if (Validaciones.isNullOrEmpty(campos[TIPO_REMANENTE_GANCIA])){
					if ((campos[NRO_REFERENCIA_NC_GANCIA].length() > getLengthCamposGanancias()[NRO_REFERENCIA_NC_GANCIA]) 
							&& (campos[NRO_REFERENCIA_NC_GANCIA].length() > 0)) {
						
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
									"Número de Referencia NC", String.valueOf(getLengthCamposGanancias()[NRO_REFERENCIA_NC_GANCIA]));
							
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					} else {
						if (!Validaciones.isEmptyString(campos[NRO_REFERENCIA_NC_GANCIA])
								&& !Validaciones.isNumeric(campos[NRO_REFERENCIA_NC_GANCIA])) {
							String mensajeError = Utilidad.reemplazarMensajes(
									Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
										"Número de Referencia NC");
							String mensaje = agregarMensajeError(nroRegistro, mensajeError);
							importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
							hayError = true;
						} else if (Validaciones.isEmptyNumericoSerializado(campos[NRO_REFERENCIA_NC_GANCIA])) {
							String mensajeError = Utilidad.reemplazarMensajes(
									Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampoNoValido"), 
									"Número de Referencia NC");
								String mensaje = agregarMensajeError(nroRegistro, mensajeError);
								importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
								hayError = true;
						}
					}
				} 
			}
			
			// Cliente Dueño Credito
			if (campos[CLIENTE_DUENO_CREDITO_GANCIA].length() > getLengthCamposGanancias()[CLIENTE_DUENO_CREDITO_GANCIA]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Cliente Dueño Credito", String.valueOf(getLengthCamposGanancias()[CLIENTE_DUENO_CREDITO_GANCIA]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (Validaciones.isEmptyString(campos[CLIENTE_DUENO_CREDITO_GANCIA])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorObligatorio"), 
								"Cliente Dueño Credito");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (!Validaciones.isNumeric(campos[CLIENTE_DUENO_CREDITO_GANCIA])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
									"Cliente Dueño Credito");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}else if (!Validaciones.isEmptyString(campos[CLIENTE_DUENO_CREDITO_GANCIA])
							&& Constantes.CERO == Long.valueOf(campos[CLIENTE_DUENO_CREDITO_GANCIA])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampoNoValido"), 
									"Cliente Dueño Credito");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}
				}
			}
			
			// Cuenta Origen
			if (campos[CUENTA_ORIGEN_GANCIA].length() > getLengthCamposGanancias()[CUENTA_ORIGEN_GANCIA] ) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Cuenta Origen", String.valueOf(getLengthCamposGanancias()[CUENTA_ORIGEN_GANCIA]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (Validaciones.isEmptyString(campos[CUENTA_ORIGEN_GANCIA]) && !Validaciones.isEmptyString(campos[TIPO_REMANENTE_GANCIA])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorObligatorioConTipoRemanente"), 
								"Cuenta Origen");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (!Validaciones.isEmptyString(campos[CUENTA_ORIGEN_GANCIA]) && 
							!Validaciones.isNumeric(campos[CUENTA_ORIGEN_GANCIA])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
									"Cuenta Origen");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					} else if (Validaciones.isEmptyNumericoSerializado(campos[CUENTA_ORIGEN_GANCIA])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampoNoValido"), 
								"Cuenta Origen");
							String mensaje = agregarMensajeError(nroRegistro, mensajeError);
							importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
							hayError = true;
					}
				}
			}
			
			// Importe
			if (campos[IMPORTE_GANCIA].length() > getLengthCamposGanancias()[IMPORTE_GANCIA]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Importe", String.valueOf(getLengthCamposGanancias()[IMPORTE_GANCIA]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (Validaciones.isEmptyString(campos[IMPORTE_GANCIA])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorObligatorio"), 
								"Importe");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (!Validaciones.isEmptyString(campos[IMPORTE_GANCIA]) && !Validaciones.isNumeric(campos[IMPORTE_GANCIA])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
									"Importe");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}  else if (Validaciones.isEmptyNumericoSerializado(campos[IMPORTE_GANCIA])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampoNoValido"), 
								"Importe");
							String mensaje = agregarMensajeError(nroRegistro, mensajeError);
							importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
							hayError = true;
					}
				}
			}
			
			
			
			// CREDITO_MIGRADO
			if (campos[CREDITO_MIGRADO_GANCIA].length() > getLengthCamposGanancias()[CREDITO_MIGRADO_GANCIA]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Credito Migrado", String.valueOf(getLengthCamposGanancias()[CREDITO_MIGRADO_GANCIA]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (Validaciones.isObjectNull(SiNoEnum.getEnumByDescripcionCorta(campos[CREDITO_MIGRADO_GANCIA]))
						&& !Validaciones.isEmptyString(campos[CREDITO_MIGRADO_GANCIA])) 
				{
					String valoresPosibles = "Vacio";
					for (SiNoEnum enume : SiNoEnum.values()) {
						valoresPosibles += WHITESPACE + enume.getDescripcionCorta();
					}
					
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampo"), 
							"Credito Migrado", valoresPosibles);
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true; 
				}
			}
			
			if ((!Validaciones.isNullOrEmpty(campos[NRO_REFERENCIA_NC_GANCIA])) 
					&& (Validaciones.isNullOrEmpty(campos[CREDITO_MIGRADO_GANCIA]))) {
				
				String mensaje = agregarMensajeError(nroRegistro, 
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposGanancia.NroRefNCCreditoMigrado"));
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} 
			
			if ((!Validaciones.isNullOrEmpty(campos[TIPO_REMANENTE_GANCIA])) 
					&& (!Validaciones.isNullOrEmpty(campos[CREDITO_MIGRADO_GANCIA]))) {
				
				String mensaje = agregarMensajeError(nroRegistro, 
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposGanancia.TipoRemNoCreditoMigrado"));
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} 
			
			
		} else {
			String mensajeAuxiliar = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCantCampos"), 
						String.valueOf(getLengthCamposDesistimiento().length), String.valueOf(campos.length));
			String mensaje = agregarMensajeError(nroRegistro, mensajeAuxiliar);
			importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
			hayError = true; 
			Traza.error(getClass(), importarOperacionesMasivasAuxiliar.getValidacionParcial().toString());
		}
		
		if (hayError) {
			importarOperacionesMasivasAuxiliar.getResultadoValidaciones().append(importarOperacionesMasivasAuxiliar.getValidacionParcial().toString());
		}
		
		return hayError;
	}
	
	/**
	 * 
	 * 	
	1	Cliente dueño del crédito			Numérico	10	
	2	Cuenta			Numérico	13	
	3	Tipo Remanente			Alfanumérico	3	"RT1: no actualizable/transferible 	RT2: no actualizable/no transferible 	Vacío: en caso que el cobro se haga con una nota de crédito"
	4	Número Referencia NC			Numérico	15	"Número de referencia de la nota de crédito 	Vacío: en caso que el cobro se haga con remanente"
	5	Crédito migrado			Alfanumérico	1	"S 	N 	Vacío: en caso que el cobro se haga con remanente"
	6	Importe			Numérico	10	Expresado en centavos
	7	Trámite de reintegro			Numérico	10	
	8	Fecha alta de trámite de reintegro			Numérico	8	AAAAMMDD
	9	Tipo de reintegro			Alfanumérico	3	"PF: Próxima factura PCT: Pago cuenta terceros 	CH: Cheque 	RI: Red inteligente	CBU: Transferencia bancaria"
	10	Cliente dueño del acuerdo de facturación			Numérico	10	Vacío: en caso que se decida no trasferir intereses a próxima factura (esto es tanto transferencia total de intereses o bien parcial por bonificación parcial)
	11	Acuerdo de Facturación Destino			Numérico	10	"Acuerdo de facturación destino Vacío: en caso que se decida no reintegrar a próxima factura o en caso que el reintegro a próxima factura se haga por línea"
	12	Línea destino			Numérico	18	"Línea destino sin guiones ni separaciones (ej: 1145453817) Vacío: en caso que se decida no reintegrar a próxima factura o en caso que el reintegro a próxima factura se haga por acuerdo de facturación"
	13	Reintegra con intereses			Alfanumérico	1	"S 	N 	Vacío: en caso que se decida no reintegrar a próxima factura"
	
	•	Tipo Remanente y Número Referencia NC son excluyentes.
	•	Si se informa Número de Referencia NC se debe informa la marca de crédito migrado.
	•	Si la opción de tratamiento de intereses es CC entonces la fecha de alta de trámite de reintegro debe estar completa.
	•	Si la opción de tipo de reintegro es PF entonces el campo acuerdo de facturación destino debe estar completo.
	
	 * @param campos
	 * @param nroRegistro
	 * @return
	 */
	private boolean validarCamposReintegro(String[] campos, int nroRegistro, ImportarOperacionesMasivasAuxiliar importarOperacionesMasivasAuxiliar) {
		boolean hayError = false;
		importarOperacionesMasivasAuxiliar.getValidacionParcial().delete(0, importarOperacionesMasivasAuxiliar.getValidacionParcial().length());
		
		if (campos.length == getLengthCamposReintegro().length) {
			if ((!Validaciones.isNullOrEmpty(campos[TIPO_REMANENTE_REINT])) 
					&& (!Validaciones.isNullOrEmpty(campos[NUMERO_REFERENCIA_NC_REINT]))) {
				
				String mensaje = agregarMensajeError(nroRegistro, 
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposReintegro.TipoRemNroRefNC.excluyentes"));
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else 
			if (Validaciones.isNullOrEmpty(campos[TIPO_REMANENTE_REINT])
					&& Validaciones.isNullOrEmpty(campos[NUMERO_REFERENCIA_NC_REINT])) {
				
				String mensaje = agregarMensajeError(nroRegistro, 
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposReintegro.TipoRemNroRefNC.vacio"));
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {	
				// Tipo remanente
				if (Validaciones.isNullOrEmpty(campos[NUMERO_REFERENCIA_NC_REINT])){
					if ((campos[TIPO_REMANENTE_REINT].length() > getLengthCamposReintegro()[TIPO_REMANENTE_REINT]) 
							&& (campos[TIPO_REMANENTE_REINT].length() > 0)) {
						
						String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
								"Tipo Remanente", String.valueOf(getLengthCamposReintegro()[TIPO_REMANENTE_REINT]));
						
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					} else 
					if (Validaciones.isObjectNull(TipoOrigenEnum.getEnumByName1y2(campos[TIPO_REMANENTE_REINT]))) {
						if (!Validaciones.isEmptyString(campos[TIPO_REMANENTE_REINT])) {
							String valoresPosibles = "Vacio";
							valoresPosibles += WHITESPACE + TipoOrigenEnum.RT1;
							valoresPosibles += WHITESPACE + TipoOrigenEnum.RT2;
							
							String mensajeError = Utilidad.reemplazarMensajes(
									Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampo"), 
										"Tipo Remanente", valoresPosibles);
							String mensaje = agregarMensajeError(nroRegistro, mensajeError);
							importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
							hayError = true;
						} 
					}
				}
				
				// Numero de referencia NC
				if (Validaciones.isNullOrEmpty(campos[TIPO_REMANENTE_REINT])){
					if ((campos[NUMERO_REFERENCIA_NC_REINT].length() > getLengthCamposReintegro()[NUMERO_REFERENCIA_NC_REINT]) 
							&& (campos[NUMERO_REFERENCIA_NC_REINT].length() > 0)) {
						
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
									"Número de Referencia NC", String.valueOf(getLengthCamposReintegro()[NUMERO_REFERENCIA_NC_REINT]));
							
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					} else {
						if (!Validaciones.isEmptyString(campos[NUMERO_REFERENCIA_NC_REINT])
								&& !Validaciones.isNumeric(campos[NUMERO_REFERENCIA_NC_REINT])) {
							String mensajeError = Utilidad.reemplazarMensajes(
									Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
										"Número de Referencia NC");
							String mensaje = agregarMensajeError(nroRegistro, mensajeError);
							importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
							hayError = true;
						}  else if (Validaciones.isEmptyNumericoSerializado(campos[NUMERO_REFERENCIA_NC_REINT])) {
							String mensajeError = Utilidad.reemplazarMensajes(
									Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampoNoValido"), 
									"Número de Referencia NC");
								String mensaje = agregarMensajeError(nroRegistro, mensajeError);
								importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
								hayError = true;
						} 
					}
				}
			}
		
			// Cliente dueño del crédito
			if (campos[CLIENTE_DUENO_CREDITO_REINT].length() > getLengthCamposReintegro()[CLIENTE_DUENO_CREDITO_REINT]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Cliente dueño del crédito", String.valueOf(getLengthCamposReintegro()[CLIENTE_DUENO_CREDITO_REINT]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (Validaciones.isEmptyString(campos[CLIENTE_DUENO_CREDITO_REINT])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorObligatorio"), 
							"Cliente dueño del crédito");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (!Validaciones.isEmptyString(campos[CLIENTE_DUENO_CREDITO_REINT]) && !Validaciones.isNumeric(campos[CLIENTE_DUENO_CREDITO_REINT])) {
						
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
									"Cliente dueño del crédito");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}else if (!Validaciones.isEmptyString(campos[CLIENTE_DUENO_CREDITO_GANCIA])
							&& Constantes.CERO == Long.valueOf(campos[CLIENTE_DUENO_CREDITO_GANCIA])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampoNoValido"), 
									"Cliente Dueño del crédito");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}
				}
			}
				
			// Cuenta
			if (campos[CUENTA_REINT].length() > getLengthCamposReintegro()[CUENTA_REINT]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Cuenta", String.valueOf(getLengthCamposReintegro()[CUENTA_REINT]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (Validaciones.isEmptyString(campos[CUENTA_REINT])  && !Validaciones.isEmptyString(campos[TIPO_REMANENTE_REINT])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorObligatorioConTipoRemanente"), 
							"Cuenta");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (!Validaciones.isEmptyString(campos[CUENTA_REINT]) && !Validaciones.isNumeric(campos[CUENTA_REINT])) {
						
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
									"Cuenta");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					} else if (Validaciones.isNumeric(campos[CUENTA_REINT])) {
						if (Validaciones.isEmptyNumericoSerializado(campos[CUENTA_REINT])) {
							String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampoNoValido"), 
								"Cuenta");
							String mensaje = agregarMensajeError(nroRegistro, mensajeError);
							importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
							hayError = true;
						}
					} 
				} 
			}

			// CREDITO_MIGRADO
			if (campos[CREDITO_MIGRADO_REINT].length() > getLengthCamposReintegro()[CREDITO_MIGRADO_REINT]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Credito Migrado", String.valueOf(getLengthCamposReintegro()[CREDITO_MIGRADO_REINT]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (Validaciones.isObjectNull(SiNoEnum.getEnumByDescripcionCorta(campos[CREDITO_MIGRADO_REINT]))
						&& !Validaciones.isEmptyString(campos[CREDITO_MIGRADO_REINT])) 
				{
					String valoresPosibles = "Vacio";
					for (SiNoEnum enume : SiNoEnum.values()) {
						valoresPosibles += WHITESPACE + enume.getDescripcionCorta();
					}
					
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampo"), 
							"Credito Migrado", valoresPosibles);
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true; 
				}
			}
			if ((!Validaciones.isNullOrEmpty(campos[NUMERO_REFERENCIA_NC_REINT])) 
					&& (Validaciones.isNullOrEmpty(campos[CREDITO_MIGRADO_REINT]))) {
				
				String mensaje = agregarMensajeError(nroRegistro, 
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposReintegro.NroRefNCCreditoMigrado"));
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} 
			if ((!Validaciones.isNullOrEmpty(campos[TIPO_REMANENTE_REINT])) 
					&& (!Validaciones.isNullOrEmpty(campos[CREDITO_MIGRADO_REINT]))) {
				
				String mensaje = agregarMensajeError(nroRegistro, 
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposReintegro.TipoRemNoCreditoMigrado"));
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} 
			
			// Importe
			if (campos[IMPORTE_REINT].length() > getLengthCamposReintegro()[IMPORTE_REINT]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Importe", String.valueOf(getLengthCamposReintegro()[IMPORTE_REINT]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (Validaciones.isEmptyString(campos[IMPORTE_REINT])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorObligatorio"), 
								"Importe");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (!Validaciones.isEmptyString(campos[IMPORTE_REINT]) && !Validaciones.isNumeric(campos[IMPORTE_REINT])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
									"Importe");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}   else if (Validaciones.isEmptyNumericoSerializado(campos[IMPORTE_REINT])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampoNoValido"), 
								"Importe");
							String mensaje = agregarMensajeError(nroRegistro, mensajeError);
							importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
							hayError = true;
					}
				}
			}

			// TRAMITE_REINTEGRO
			if (campos[TRAMITE_REINTEGRO].length() > getLengthCamposReintegro()[TRAMITE_REINTEGRO]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Tramite Reintegro", String.valueOf(getLengthCamposReintegro()[TRAMITE_REINTEGRO]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (Validaciones.isEmptyString(campos[TRAMITE_REINTEGRO])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorObligatorio"), 
								"Tramite Reintegro");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (!Validaciones.isEmptyString(campos[TRAMITE_REINTEGRO]) && !Validaciones.isNumeric(campos[TRAMITE_REINTEGRO])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
									"Tramite Reintegro");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}    else if (Validaciones.isEmptyNumericoSerializado(campos[TRAMITE_REINTEGRO])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampoNoValido"), 
								"Tramite Reintegro");
							String mensaje = agregarMensajeError(nroRegistro, mensajeError);
							importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
							hayError = true;
					}
				} 
			}

			if (campos[FECHA_ALTA_TRAMITE_REINTEGRO].length() != getLengthCamposReintegro()[FECHA_ALTA_TRAMITE_REINTEGRO]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Fecha alta de trámite de reintegro", String.valueOf(getLengthCamposReintegro()[FECHA_ALTA_TRAMITE_REINTEGRO]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				try{
					if (Validaciones.isObjectNull(Utilidad.parseDateWSFormat(campos[FECHA_ALTA_TRAMITE_REINTEGRO]))) {
						throw new ParseException("",0);
					}
				} catch (ParseException e){
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorFecha"), 
								"Fecha alta de trámite de reintegro");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				}
			}

			// Tipo Reintegro
			TipoReintegroEnum tipoReintegro = null;
			if (campos[TIPO_REINTEGRO].length() > getLengthCamposReintegro()[TIPO_REINTEGRO]) {
				String mensajeError = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Tipo Reintegro", String.valueOf(getLengthCamposReintegro()[DESTRANSFERIR_TERCEROS]));
				String mensaje = agregarMensajeError(nroRegistro, mensajeError);
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if (Validaciones.isEmptyString(campos[TIPO_REINTEGRO])) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorObligatorio"), 
								"Tipo Reintegro");
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					tipoReintegro = TipoReintegroEnum.getEnumByName(campos[TIPO_REINTEGRO].trim());

					if (Validaciones.isObjectNull(tipoReintegro)) {
						String valoresPosibles = "";
						for (TipoReintegroEnum enumOrigen : TipoReintegroEnum.values()) {
							valoresPosibles += WHITESPACE + enumOrigen.name();
						}
						
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampo"), 
									"Tipo Reintegro", valoresPosibles);
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}
				}
			}

			if (Validaciones.isObjectNull(tipoReintegro) || TipoReintegroEnum.PF.equals(tipoReintegro)) {
				// Cliente dueño del acuerdo de facturación
				if (campos[CLIENTE_DUENO_ACUERDO_FACTURACION_REINT].length() > getLengthCamposReintegro()[CLIENTE_DUENO_ACUERDO_FACTURACION_REINT]) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
								"Cliente dueño del acuerdo de facturación", String.valueOf(getLengthCamposReintegro()[CLIENTE_DUENO_ACUERDO_FACTURACION_REINT]));
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (!Validaciones.isEmptyString(campos[CLIENTE_DUENO_ACUERDO_FACTURACION_REINT])
							&& !Validaciones.isNumeric(campos[CLIENTE_DUENO_ACUERDO_FACTURACION_REINT])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
									"Cliente Dueño Acuerdo Facturacion");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					} else if (!Validaciones.isEmptyString(campos[CLIENTE_DUENO_ACUERDO_FACTURACION_REINT])
							&& Constantes.CERO == Long.valueOf(campos[CLIENTE_DUENO_ACUERDO_FACTURACION_REINT])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampoNoValido"), 
									"Cliente Dueño Acuerdo Facturacion");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					} else if (Validaciones.isEmptyString(campos[CLIENTE_DUENO_ACUERDO_FACTURACION_REINT])) {
							String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposReintegro.TratamientoPFObligatorio"), 
								"Cliente dueño del acuerdo de facturación"
							);
							String mensaje = agregarMensajeError(nroRegistro, mensajeError);
							importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
							hayError = true;
						}
				}
			
				// ACUERDO_FACTURACION_DESTINO
				if (campos[ACUERDO_FACTURACION_DESTINO_REINT].length() > getLengthCamposReintegro()[ACUERDO_FACTURACION_DESTINO_REINT]) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
								"Acuerdo Facturacion Destino", String.valueOf(getLengthCamposReintegro()[ACUERDO_FACTURACION_DESTINO_REINT]));
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (!Validaciones.isEmptyString(campos[ACUERDO_FACTURACION_DESTINO_REINT])
							&& !Validaciones.isNumeric(campos[ACUERDO_FACTURACION_DESTINO_REINT])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
									"Acuerdo Facturacion Destino");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}
				}
			
				if (campos[LINEA_DESTINO].length() > getLengthCamposReintegro()[LINEA_DESTINO]) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
								"Línea destino", String.valueOf(getLengthCamposReintegro()[LINEA_DESTINO]));
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (!Validaciones.isEmptyString(campos[LINEA_DESTINO])
							&& !Validaciones.isNumeric(campos[LINEA_DESTINO])) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
									"Línea destino");
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}
				}
				
				//REINTEGRA_CON_INTERESES
				if (campos[REINTEGRA_CON_INTERESES].length() > getLengthCamposReintegro()[REINTEGRA_CON_INTERESES]) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
								"Reintegra con intereses", String.valueOf(getLengthCamposReintegro()[REINTEGRA_CON_INTERESES]));
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (Validaciones.isObjectNull(SiNoEnum.getEnumByDescripcionCorta(campos[REINTEGRA_CON_INTERESES]))
							&& !Validaciones.isEmptyString(campos[REINTEGRA_CON_INTERESES])) 
					{
						String valoresPosibles = "Vacio";
						for (SiNoEnum enume : SiNoEnum.values()) {
							valoresPosibles += WHITESPACE + enume.getDescripcionCorta();
						}
						
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampo"), 
								"Reintegra con intereses", valoresPosibles);
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true; 
					}
				}
				// Validaciones de exclu
				if (
					(!Validaciones.isNullOrEmpty(campos[ACUERDO_FACTURACION_DESTINO_REINT])) && 
					(!Validaciones.isNullOrEmpty(campos[LINEA_DESTINO]))
				) {
					String mensaje = agregarMensajeError(
						nroRegistro, 
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposReintegro.AcuerdoLinea.excluyentes")
					);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else if (
					(!Validaciones.isNullOrEmpty(campos[ACUERDO_FACTURACION_DESTINO_REINT]) && 
					 Validaciones.isNullOrEmpty(campos[CLIENTE_DUENO_ACUERDO_FACTURACION_REINT]))
				) {	
					String mensaje = agregarMensajeError(
						nroRegistro, 
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposReintegro.TratamientoPF2")
					);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				}
				if (
					(Validaciones.isNullOrEmpty(campos[ACUERDO_FACTURACION_DESTINO_REINT]) && 
					Validaciones.isNullOrEmpty(campos[LINEA_DESTINO]))
				) {	
					String mensaje = agregarMensajeError(
						nroRegistro, 
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposReintegro.TratamientoPF")
					);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				}
//				if (
//					(!Validaciones.isNullOrEmpty(campos[TIPO_REINTEGRO]) && 
//					TipoReintegroEnum.PF.equals(TipoReintegroEnum.getEnumByName(campos[TIPO_REINTEGRO]))) && 
//					(
//						(
//							Validaciones.isNullOrEmpty(campos[ACUERDO_FACTURACION_DESTINO_REINT]) && 
//							Validaciones.isNullOrEmpty(campos[LINEA_DESTINO])
//						) || 
//						(
//							!Validaciones.isNullOrEmpty(campos[ACUERDO_FACTURACION_DESTINO_REINT]) && 
//							!Validaciones.isNullOrEmpty(campos[LINEA_DESTINO]))
//						)
//				) {
//					String mensaje = agregarMensajeError(
//						nroRegistro, 
//						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposReintegro.TratamientoPF")
//					);
//					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
//					hayError = true;
//				}
				
				//Otras validaciones dependientes de otros campos
				if (!Validaciones.isEmptyString(campos[REINTEGRA_CON_INTERESES])
						&& Validaciones.isEmptyString(campos[FECHA_ALTA_TRAMITE_REINTEGRO])) {
					
					String mensaje = agregarMensajeError(nroRegistro, 
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposReintegro.TratamientoFechaAltaTramiteReintegro"));
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				}
				if ((!Validaciones.isNullOrEmpty(campos[TIPO_REINTEGRO])
						&& TipoReintegroEnum.PF.equals(TipoReintegroEnum.getEnumByName(campos[TIPO_REINTEGRO])))
						&& (Validaciones.isNullOrEmpty(campos[REINTEGRA_CON_INTERESES]))) {
					
					String mensaje = agregarMensajeError(nroRegistro, 
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposReintegro.TratamientoPF1"));
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				}
			} else {
				if (campos[CLIENTE_DUENO_ACUERDO_FACTURACION_REINT].length() > getLengthCamposReintegro()[CLIENTE_DUENO_ACUERDO_FACTURACION_REINT]) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
								"Cliente dueño del acuerdo de facturación", String.valueOf(getLengthCamposReintegro()[CLIENTE_DUENO_ACUERDO_FACTURACION_REINT]));
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					// Verifico si es nulo
					if (!Validaciones.isEmptyString(campos[CLIENTE_DUENO_ACUERDO_FACTURACION_REINT])) {
						String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposReintegro.TratamientoNoPF"), 
							"Cliente dueño del acuerdo de facturación"
						);
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}
				}
				if (campos[ACUERDO_FACTURACION_DESTINO_REINT].length() > getLengthCamposReintegro()[ACUERDO_FACTURACION_DESTINO_REINT]) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
								"Acuerdo Facturacion Destino", String.valueOf(getLengthCamposReintegro()[ACUERDO_FACTURACION_DESTINO_REINT]));
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (!Validaciones.isEmptyString(campos[ACUERDO_FACTURACION_DESTINO_REINT])) {
						String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposReintegro.TratamientoNoPF"), 
							"Acuerdo Facturacion Destino"
						);
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}
				}
				if (campos[LINEA_DESTINO].length() > getLengthCamposReintegro()[LINEA_DESTINO]) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
								"Línea destino", String.valueOf(getLengthCamposReintegro()[LINEA_DESTINO]));
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (!Validaciones.isEmptyString(campos[LINEA_DESTINO])) {
						String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposReintegro.TratamientoNoPF"), 
							"Línea destino"
						);
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}
				}
				
				if (campos[REINTEGRA_CON_INTERESES].length() > getLengthCamposReintegro()[REINTEGRA_CON_INTERESES]) {
					String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
								"Reintegra con intereses", String.valueOf(getLengthCamposReintegro()[REINTEGRA_CON_INTERESES]));
					String mensaje = agregarMensajeError(nroRegistro, mensajeError);
					importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
					hayError = true;
				} else {
					if (!Validaciones.isEmptyString(campos[REINTEGRA_CON_INTERESES])) {
						String mensajeError = Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposReintegro.TratamientoNoPF"), 
							"Reintegra con intereses"
						);
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}
				}
			}
			 
		
			//Validar el campo, el cuál es obligatorio para nota de crédito.
			if ((!Validaciones.isNullOrEmpty(campos[NUMERO_REFERENCIA_NC_REINT])) 
					&& (Validaciones.isNullOrEmpty(campos[NUMERO_DOCUMENTO_REINT]))) {
				
				String mensaje = agregarMensajeError(nroRegistro, 
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposReintegro.NroRefNCNumeroDocumento"));
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} else {
				if ((!Validaciones.isNullOrEmpty(campos[NUMERO_REFERENCIA_NC_REINT])) 
						&& (!Validaciones.isNullOrEmpty(campos[NUMERO_DOCUMENTO_REINT]))) {
					if (campos[NUMERO_DOCUMENTO_REINT].length() > getLengthCamposReintegro()[NUMERO_DOCUMENTO_REINT]) {
						String mensajeError = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
									"Número de documento", String.valueOf(getLengthCamposReintegro()[NUMERO_DOCUMENTO_REINT]));
						String mensaje = agregarMensajeError(nroRegistro, mensajeError);
						importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					} else {
						//validar el formato
						StringBuffer mensaje = validarNumeroDocumentoMIC(nroRegistro, campos[NUMERO_DOCUMENTO_REINT]);
						if (mensaje!=null) {
							importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje.toString());
							hayError = true;
						}
					}
				}
			}
			if ((!Validaciones.isNullOrEmpty(campos[TIPO_REMANENTE_REINT])) 
					&& (!Validaciones.isNullOrEmpty(campos[NUMERO_DOCUMENTO_REINT]))) {
				
				String mensaje = agregarMensajeError(nroRegistro, 
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCamposReintegro.TipoRemNoNumeroDocumento"));
				importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			}
			
		} else {
			String mensajeAuxiliar = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCantCampos"), 
						String.valueOf(getLengthCamposReintegro().length), String.valueOf(campos.length));
			String mensaje = agregarMensajeError(nroRegistro, mensajeAuxiliar);
			importarOperacionesMasivasAuxiliar.getValidacionParcial().append(mensaje + CARRIAGE_RETURN);
			hayError = true;
		}

		if (hayError) {
			importarOperacionesMasivasAuxiliar.getResultadoValidaciones().append(importarOperacionesMasivasAuxiliar.getValidacionParcial().toString());
		}
		
		return hayError;
	}
	
	/**
	 * Validar el numero de documentoMIC
	 * @param nroRegistro
	 * @param numeroDocumento
	 * @return
	 */
	private StringBuffer validarNumeroDocumentoMIC(int nroRegistro, String numeroDocumento) {
		
		StringBuffer validacionNumeroDocumento = new StringBuffer("");
			
		String[] split = numeroDocumento.trim().split("-");
		
		boolean flagEvitoValidacionesInecesarias = false;
		boolean flagSinLetraYconGuion = false;
		boolean flagSinLetra = false;
		boolean flagConLetra = false;
		boolean hayError = false;
		
		if(!Validaciones.isNullOrEmpty(numeroDocumento) && split.length != 3 && split.length != 2){
			String mensajeAuxiliar = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampo"), 
						"Número de documento", "X-nnnn-nnnnnnnn o nnnn-nnnnnnnn");
			String mensaje = agregarMensajeError(nroRegistro, mensajeAuxiliar);
			flagEvitoValidacionesInecesarias = true;
			validacionNumeroDocumento.append(mensaje + CARRIAGE_RETURN);
			hayError = true;
		} else {
			if(split.length == 2 || split[0].trim().isEmpty()){
				flagSinLetra = true;
			}
			if(split.length == 3){
				flagConLetra = true;
			}
		}
		
		if(flagSinLetra){
			if(!Validaciones.isNullOrEmpty(numeroDocumento) && 
					numeroDocumento.trim().startsWith("-")) {
				
				String mensajeAuxiliar = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampo"), 
							"Número de documento", "Falta ingresar la Letra, o bien quite el - del inicio del campo");
				String mensaje = agregarMensajeError(nroRegistro, mensajeAuxiliar);
				flagSinLetraYconGuion = true;
				
				validacionNumeroDocumento.append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			}
			//Sucursal
			if (!Validaciones.isNullOrEmpty(numeroDocumento) &&
					!flagSinLetraYconGuion && split[0].trim().length() != ConstantesCobro.cantidadCaracteresSucursal){
			
				String mensajeAuxiliar = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Sucursal del número de documento", String.valueOf(ConstantesCobro.cantidadCaracteresSucursal));
				String mensaje = agregarMensajeError(nroRegistro, mensajeAuxiliar);
				validacionNumeroDocumento.append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			}
			
			if (!Validaciones.isNullOrEmpty(numeroDocumento) &&
					!flagSinLetraYconGuion && !Validaciones.isNumeric(split[0].trim()) && (!split[0].trim().startsWith("D") )) 
			{
				String mensajeAuxiliar = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
							"Sucursal del número de documento");
				String mensaje = agregarMensajeError(nroRegistro, mensajeAuxiliar);
				validacionNumeroDocumento.append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			}

			//Comprobante
			if (!Validaciones.isNullOrEmpty(numeroDocumento) &&
					!flagSinLetraYconGuion && split[1].trim().length() != ConstantesCobro.cantidadCaracteresComprobante) {
				
				String mensajeAuxiliar = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
							"Comprobante del número de documento", String.valueOf(ConstantesCobro.cantidadCaracteresComprobante));
				String mensaje = agregarMensajeError(nroRegistro, mensajeAuxiliar);
				validacionNumeroDocumento.append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			} 
			if (!Validaciones.isNullOrEmpty(numeroDocumento) && !Validaciones.isNumeric(split[1].trim())) {
				String mensajeAuxiliar = Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
							"Comprobante del número de documento");
				String mensaje = agregarMensajeError(nroRegistro, mensajeAuxiliar);
				validacionNumeroDocumento.append(mensaje + CARRIAGE_RETURN);
				hayError = true;
			}
			
		} else {
			if(flagConLetra && !Validaciones.isNullOrEmpty(numeroDocumento)){
				if(!flagEvitoValidacionesInecesarias){
					// Letra
					if (split[0].trim().length() > ConstantesCobro.logitudEsperadaLETRA) {
						
						String mensajeAuxiliar = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
									"Letra del número de documento", String.valueOf(ConstantesCobro.logitudEsperadaLETRA));
						String mensaje = agregarMensajeError(nroRegistro, mensajeAuxiliar);
						validacionNumeroDocumento.append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					} 
					
					if (!split[0].trim().equalsIgnoreCase("A") && !split[0].trim().equalsIgnoreCase("B") 
							&& !split[0].trim().equalsIgnoreCase(" ") && !split[0].trim().equalsIgnoreCase("")) {
						
						String mensajeAuxiliar = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorCampo"), 
									"Letra del número de documento", "A, B, o Vacio");
						String mensaje = agregarMensajeError(nroRegistro, mensajeAuxiliar);
						validacionNumeroDocumento.append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					} 
					
					if (Validaciones.isNullOrEmpty(split[0].trim())) {
						String mensajeAuxiliar = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorObligatorio"), 
									"Letra del número de documento");
						String mensaje = agregarMensajeError(nroRegistro, mensajeAuxiliar);
						validacionNumeroDocumento.append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}

					//Sucursal
					if (split[1].trim().length() != ConstantesCobro.cantidadCaracteresSucursal){
						String mensajeAuxiliar = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
									"Sucursal del número de documento", String.valueOf(ConstantesCobro.cantidadCaracteresSucursal));
						String mensaje = agregarMensajeError(nroRegistro, mensajeAuxiliar);
						validacionNumeroDocumento.append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}
					if (!Validaciones.isNumeric(split[1].trim()) && (!split[1].trim().startsWith("D"))){
						
						String mensajeAuxiliar = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
									"Sucursal del número de documento");
						String mensaje = agregarMensajeError(nroRegistro, mensajeAuxiliar);
						validacionNumeroDocumento.append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}

					//Comprobante
					if (split[2].trim().length() != ConstantesCobro.cantidadCaracteresComprobante) {
						String mensajeAuxiliar = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorLongitud"), 
									"Comprobante del número de documento", String.valueOf(ConstantesCobro.cantidadCaracteresComprobante));
						String mensaje = agregarMensajeError(nroRegistro, mensajeAuxiliar);
						validacionNumeroDocumento.append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					} 
					if (!Validaciones.isNumeric(split[2].trim())) {
						String mensajeAuxiliar = Utilidad.reemplazarMensajes(
								Propiedades.MENSAJES_PROPIEDADES.getString("error.operacionMasiva.validarCampos.errorNumerico"), 
									"Comprobante del número de documento");
						String mensaje = agregarMensajeError(nroRegistro, mensajeAuxiliar);
						validacionNumeroDocumento.append(mensaje + CARRIAGE_RETURN);
						hayError = true;
					}
				}
			}
		}
		
		if (hayError) {
			return validacionNumeroDocumento;
		} 
		return null;
	}
	
	
	//Envìo de mail de operaciòn masiva, en caso de registro de desistimiento
	//UsuarioSesion userSesion
	//userSesion.getIdUsuario() es el mismo que operacionMasiva.getIdAnalista()
	public void generarTareaOperacionMasiva(ShvMasOperacionMasiva opMasivaModelo, ShvMasOperacionMasivaArchivo opMasArchivo) throws NegocioExcepcion {
		TareaDto tarea = new TareaDto();
		tarea.setIdOperacionMasiva(opMasivaModelo.getIdOperacionMasiva());
		tarea.setIdWorkflow(opMasivaModelo.getWorkFlow().getIdWorkflow());
		tarea.setTipoTarea(TipoTareaEnum.APROBAR_OP_MAS);
		tarea.setFechaCreacion(new Date());
		tarea.setUsuarioCreacion(opMasivaModelo.getIdAnalista());
		tarea.setPerfilAsignacion(TipoPerfilEnum.SUPERVISOR_OPERACION_MASIVA.descripcion());
		tarea.setInformacionAdicional(generarInformacionAdicional(opMasArchivo));
		tarea.setGestionaSobre(TipoTareaGestionaEnum.OPERACION_MASIVA);
		tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		tarea.setAsuntoMail(opMasivaModelo.getEmpresa().getDescripcion() + " - Operación Masiva Pendiente de Aprobar - " + opMasArchivo.getNombreArchivo());
		tarea.setCuerpoMail(generarCuerpoMail(opMasArchivo,opMasivaModelo.getTipoOperacion()));
		
		tareaServicio.crearTareaOperacionMasiva(tarea);
		
	}
	
	/**
	 * 
	 * @param regOperacionMasivaModelo
	 * @param operacionMasiva
	 * @param file
	 * @param registros
	 * @param importeTotal
	 * @throws WorkflowExcepcion
	 * @throws PersistenciaExcepcion
	 * @throws NegocioExcepcion
	 * @throws IOException
	 */
	private ShvMasOperacionMasiva generarYGuardarModelosEnLasTablas(ShvMasRegistro regOperacionMasivaModelo, OperacionMasivaDto operacionMasiva,File file,
			List<RegistroOperacionMasivaDto> registros,	BigDecimal importeTotal) 
			throws WorkflowExcepcion, PersistenciaExcepcion, NegocioExcepcion, IOException {
		
		ShvMasOperacionMasivaArchivo operacionMasivaArchivoModelo = null;
		ShvMasOperacionMasiva operacionMasivaModelo = null;
		ShvMasOperacionMasiva opMas = null;
		Date fechaAgregar = new Date();
		
		
		if(operacionMasiva.getIdOperacionMasiva()!= null){
			// Entra cuando la Operacion Masiva esta en edicion
			
			operacionMasivaArchivoModelo = archivoOperacionMasivaDao.buscarArchivoPorIdOperacionMasiva(operacionMasiva.getIdOperacionMasiva());
			if(operacionMasiva.isArchivoValidado()){
				archivoOperacionMasivaDao.eliminar(operacionMasivaArchivoModelo);
			}
			operacionMasivaModelo = operacionMasivaDao.buscarOperacionMasiva(operacionMasiva.getIdOperacionMasiva());
			
			if(operacionMasiva.isArchivoValidado()){
				opMas = (ShvMasOperacionMasiva) operacionMasivaMapeador.map(operacionMasiva, operacionMasivaModelo);
			} else {
				opMas = (ShvMasOperacionMasiva) operacionMasivaMapeador.map(operacionMasiva, null);
				opMas.setIdOperacionMasiva(operacionMasivaModelo.getIdOperacionMasiva());
			}
			//Obtengo el workflow y modifico el estado segun el tipo de registro
			
			ShvWfWorkflow workFlow = opMas.getWorkFlow();
			
			if(operacionMasiva.getIdOperacionMasiva()== null || operacionMasiva.isArchivoValidado()){
				if (registros.get(0) instanceof RegistroOperacionMasivaDesistimientoDto) {			
					//Pendiente Aprobacion
					if(workFlow.getEstado() == Estado.MAS_RECHAZADA){
						// De Rechazada a Pendiente de Aprobacion
						opMas.setWorkFlow(workflowOperacionesMasivas.solicitarReaprobacionAltaDeOperacionMasiva(workFlow, " ", operacionMasiva.getUsuarioModificacion()));
					}else if(workFlow.getEstado() == Estado.MAS_ERROR) {
						opMas.setWorkFlow(workflowOperacionesMasivas.solicitarReaprobacionDeOperacionMasivaEnError(workFlow, " ", operacionMasiva.getUsuarioModificacion()));
					}
				}else {
					//Pendiente Procesar
					if(workFlow.getEstado() == Estado.MAS_RECHAZADA){
						// De Rechazada a Pendiente de Procesar
						opMas.setWorkFlow(workflowOperacionesMasivas.regresarOperacionMasivaRechazadaAPendienteProcesar(workFlow, " ", operacionMasiva.getUsuarioModificacion()));
					}else if(workFlow.getEstado() == Estado.MAS_ERROR) {
						// De Error a Pendiente de Procesar
						opMas.setWorkFlow(workflowOperacionesMasivas.regresarOperacionMasivaEnErrorAPendienteProcesar(workFlow, " ", operacionMasiva.getUsuarioModificacion()));
					}
				}
			}
			
			opMas.setUsuarioModificacion(operacionMasiva.getUsuarioModificacion());
			opMas.setFechaModificacion(fechaAgregar);
			
			//Seteamos en error los registros del archivo viejo
			for(ShvMasRegistro modeloRegistro : opMas.getRegistros()){
				modeloRegistro.setEstado(EstadoRegistroOperacionMasivaEnum.ERROR);
			}
			
			if(operacionMasiva.getIdOperacionMasiva()== null || operacionMasiva.isArchivoValidado()){
				opMas = operacionMasivaDao.modificar(opMas);
			}
		} else {
			// Entra cuando la Operacion Masiva se esta creando
			operacionMasiva.setFechaAlta(fechaAgregar);
			operacionMasiva.setFechaUltimaModificacion(null);
			
			opMas = (ShvMasOperacionMasiva) operacionMasivaMapeador.map(operacionMasiva, operacionMasivaModelo);
			ShvWfWorkflow workFlow = workflowOperacionesMasivas.crearWorkflow(" ", operacionMasiva.getUsuarioModificacion());
		
			
			if (registros.get(0) instanceof RegistroOperacionMasivaDesistimientoDto) {			 
				opMas.setWorkFlow(workflowOperacionesMasivas.solicitarAprobacionDeAltaDeOperacionMasiva(workFlow, operacionMasiva.getObservacion(), operacionMasiva.getUsuarioModificacion()));
			}else{
				opMas.setWorkFlow(workflowOperacionesMasivas.registrarOperacionMasivaEnProcesoDeImputacion(workFlow, operacionMasiva.getObservacion(), operacionMasiva.getUsuarioModificacion()));
			}
		
			opMas.setUsuarioModificacion(null);
			opMas = operacionMasivaDao.crear(opMas);
		}
		
		
		
		if(!Validaciones.isObjectNull(opMas)){
			for (RegistroOperacionMasivaDto dto : registros) {
					dto.setFechaCreacion(fechaAgregar);
					dto.setUsuarioCreacion(operacionMasiva.getIdAnalista());
					dto.setEstado(EstadoRegistroOperacionMasivaEnum.PENDIENTE_DATOS_SIEBEL);
					dto.setNombreArchivo(file.getName());
					regOperacionMasivaModelo = (ShvMasRegistro) registroOperacionMasivaMapeador.map(dto, null);
					regOperacionMasivaModelo.setOperacionMasiva(opMas);
					opMas.getRegistros().add(regOperacionMasivaModelo);
			}
			
		
			// Comprobantes de la Operacion Masiva
			for (ComprobanteDto comprobante : operacionMasiva.getListaComprobantes()) {
				ShvDocDocumentoAdjunto adjunto = new ShvDocDocumentoAdjunto();
				adjunto.setDescripcion(comprobante.getDescripcionArchivo());
				adjunto.setFechaCreacion(fechaAgregar);
				adjunto.setNombreArchivo(comprobante.getNombreArchivo());
				adjunto.setUsuarioCreacion(operacionMasiva.getIdAnalista());
				adjunto.setArchivoAdjunto(comprobante.getDocumento());
				if(operacionMasiva.getIdOperacionMasiva()== null || operacionMasiva.isArchivoValidado()){
					adjunto = documentoAdjuntoDao.crear(adjunto);
				}
				ShvMasOperacionMasivaAdjuntoPk opMasAdjuntoPk = new ShvMasOperacionMasivaAdjuntoPk();
				opMasAdjuntoPk.setArchivoAdjunto(adjunto);
				opMasAdjuntoPk.setOperacionMasiva(opMas);
				
				ShvMasOperacionMasivaAdjunto opMasAdjunto = new ShvMasOperacionMasivaAdjunto();
				opMasAdjunto.setOperacionMasivaAdjuntoPk(opMasAdjuntoPk);
				if(operacionMasiva.getIdOperacionMasiva()== null || operacionMasiva.isArchivoValidado()){
					operacionMasivaAdjuntoDao.crear(opMasAdjunto);
				}
			}
			
		
			// Archivo Adjunto Operacion Masiva
			ShvDocDocumentoAdjunto adjunto = new ShvDocDocumentoAdjunto();
			adjunto.setDescripcion(ARCHIVO_OPERACION_MASIVA);
			adjunto.setFechaCreacion(fechaAgregar);
			adjunto.setNombreArchivo(file.getName());
			adjunto.setUsuarioCreacion(operacionMasiva.getIdAnalista());
			adjunto.setArchivoAdjunto(Files.readAllBytes(file.toPath()));
			if(operacionMasiva.getIdOperacionMasiva()== null || operacionMasiva.isArchivoValidado()){
				adjunto = documentoAdjuntoDao.crear(adjunto);
			}
			// Creo el modelo de archivo de operacion masiva
			ShvMasOperacionMasivaArchivo opMasArchivo = new ShvMasOperacionMasivaArchivo();
			opMasArchivo.setImporteTotal(importeTotal);
			opMasArchivo.setOperacionMasiva(opMas);
			opMasArchivo.setNombreArchivo(file.getName());
			opMasArchivo.setCantidadRegistros(Long.valueOf(registros.size()));
			opMasArchivo.setDocumentoAdjunto(adjunto);
			if(opMas.getWorkFlow()!=null){
				opMasArchivo.setFechaDerivacion(opMas.getWorkFlow().getFechaUltimaModificacion());
			}
			if(operacionMasiva.getIdOperacionMasiva()== null || operacionMasiva.isArchivoValidado()){
				opMasArchivo = archivoOperacionMasivaDao.crear(opMasArchivo);
			}
			if(operacionMasiva.getIdOperacionMasiva()== null || operacionMasiva.isArchivoValidado()){
				if (registros.get(0) instanceof RegistroOperacionMasivaDesistimientoDto) { 
					validarAprobacionOperacionMasivaEnviarMail(opMas, opMasArchivo, operacionMasiva.getUsuarioModificacion());
				}
			}
		}	
		return opMas;
	}
	
	/**
	 * Metodo genera tarea y envia mail.
	 * 
	 * @author u572487 Guido.Settecerze
	 * @param cobroDto
	 * @param userSesion
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public void validarAprobacionOperacionMasivaEnviarMail(ShvMasOperacionMasiva opMasivaModelo, ShvMasOperacionMasivaArchivo opMasArchivo, String userSesion) throws NegocioExcepcion {
		crearTareaPendienteAprobacionOperacionMasiva(opMasivaModelo, opMasArchivo, userSesion);
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * @param operacionMasiva
	 * @throws NegocioExcepcion
	 */
	private void crearTareaPendienteAprobacionOperacionMasiva(ShvMasOperacionMasiva opMasivaModelo, ShvMasOperacionMasivaArchivo opMasArchivo,
			String usuarioModificacion) throws NegocioExcepcion {
		
		BigDecimal importeTotal = BigDecimal.ZERO;
		List<ShvMasRegistro> registros = opMasivaModelo.getRegistros();
		for (ShvMasRegistro shvMasRegistro : registros) {
			if(!EstadoRegistroOperacionMasivaEnum.ERROR.equals(shvMasRegistro.getEstado())){
				shvMasRegistro.setEstado(EstadoRegistroOperacionMasivaEnum.PENDIENTE_APROBACION);
				importeTotal = importeTotal.add(shvMasRegistro.getImporte());
			}
		}
		
		opMasivaModelo.setRegistros(registros);
		ShvWfWorkflow workflow = opMasivaModelo.getWorkFlow();
		
		Date now = new Date();
		String idUsuario = usuarioModificacion;

		TareaDto tarea = new TareaDto();
		String asuntoMail= "Nueva Tarea - Operación masiva pendiente de autorizar - ";
		asuntoMail += opMasivaModelo.getIdOperacionMasiva();
		asuntoMail += " / ";
		asuntoMail += opMasArchivo.getNombreArchivo();
		
		StringBuffer cuerpoMail = new StringBuffer();
		cuerpoMail.append(" Detalle de la operación masiva: ");
		cuerpoMail.append("<br>");
		cuerpoMail.append("Número de operación masiva: ");
		cuerpoMail.append(opMasivaModelo.getIdOperacionMasiva().toString());
		cuerpoMail.append("<br>");
		cuerpoMail.append("Nombre de archivo de operación masiva: ");
		cuerpoMail.append(opMasArchivo.getNombreArchivo());
		cuerpoMail.append("<br>");
		cuerpoMail.append("Tipo de Operación masiva: ");
		cuerpoMail.append(opMasivaModelo.getTipoOperacion().getDescripcion());
		if(!Validaciones.isNullOrEmpty(opMasivaModelo.getObservacion())){
			cuerpoMail.append("<br><br>");
			cuerpoMail.append("Observaciones: ");
			cuerpoMail.append(opMasivaModelo.getObservacion());
		}
		tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		tarea.setRefBandeja(opMasivaModelo.getIdOperacionMasiva().toString()+"/"+opMasArchivo.getNombreArchivo());
		tarea.setImporte(importeTotal.toString().replace(".", ","));
		
		try {
			if(Estado.MAS_PENDIENTE_APROBACION.equals(workflow.getEstado())){
				opMasivaModelo.setUsuarioModificacion(idUsuario);
				opMasivaModelo.setFechaModificacion(now);
				opMasivaModelo = operacionMasivaDao.modificar(opMasivaModelo);
				
			} else {
				if(Estado.MAS_RECHAZADA.equals(workflow.getEstado())){
					opMasivaModelo.setUsuarioModificacion(idUsuario);
					opMasivaModelo.setFechaModificacion(now);
					workflow.setUsuarioModificacion(idUsuario);
					
					// avanza de “Rechazado” a "Pend. Referente Cobranza"
					workflow = workflowOperacionesMasivas.solicitarReaprobacionAltaDeOperacionMasiva(workflow, opMasivaModelo.getObservacion(), idUsuario);
					opMasivaModelo.setWorkFlow(workflow);
					tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.REV_OP_MAS_RECH, workflow.getIdWorkflow(), now, idUsuario, null);

					opMasivaModelo = operacionMasivaDao.modificar(opMasivaModelo);
				}
			}

		}catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		tarea.setIdWorkflow(workflow.getIdWorkflow());
		tarea.setIdItem(opMasivaModelo.getIdOperacionMasiva());
		tarea.setFechaCreacion(now);
		tarea.setUsuarioCreacion(workflow.getUsuarioAlta());
		tarea.setFechaEjecucion(null);
		tarea.setUsuarioEjecucion(null);
		
		tarea.setIdOperacionMasiva(opMasivaModelo.getIdOperacionMasiva());
		tarea.setSegmento(opMasivaModelo.getSegmento().getDescripcion());
		tarea.setEmpresa(opMasivaModelo.getEmpresa().getDescripcion());
		tarea.setTipoTarea(TipoTareaEnum.APROBAR_OP_MAS);
		tarea.setPerfilAsignacion(TipoPerfilEnum.REFERENTE_COBRANZA.descripcion());
		tarea.setInformacionAdicional(generarInformacionAdicional(opMasArchivo));
		tarea.setGestionaSobre(TipoTareaGestionaEnum.OPERACION_MASIVA);
		tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		tarea.setEnviarMail(false);
		tareaServicio.crearTareaOperacionMasiva(tarea);
		
		Collection<UsuarioLdapDto> usuariosLdap = ldapServicio.buscarUsuariosPorPerfilEnMemoria(TipoPerfilEnum.REFERENTE_COBRANZA.descripcion());
		StringBuffer mails = new StringBuffer("");
		for (UsuarioLdapDto usuario: usuariosLdap) {
			if (!Validaciones.isNullOrEmpty(usuario.getMail())) {
				mails.append(usuario.getMail()+";");  
			}
		}
		UsuarioLdapDto copropietario = ldapServicio.buscarUsuarioPorUidEnMemoria(opMasivaModelo.getIdCopropietario());
		String copropietarioFinal = "";
		if(!Validaciones.isObjectNull(copropietario)){
			copropietarioFinal = copropietario.getMail().toString();
		}
		Mail mail = new Mail(mails.toString(), copropietarioFinal, asuntoMail, cuerpoMail);
		mailServicio.sendMail(mail);
		
		
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * Metodo que se encarga del rechazo de una Operacion Masiva (cambio de estado de wf / Enviar Mail / Crear Tarea).
	 * @param operacionMasivaDto
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public void rechazarAprobacionOperacionMasivaCambiarEstadoWorkFlow(OperacionMasivaDto operacionMasivaDto,  UsuarioSesion usuarioModificacion, String observacion) throws NegocioExcepcion {
		try {
			ShvMasOperacionMasiva operacionMasivaModelo = operacionMasivaDao.buscarOperacionMasiva(operacionMasivaDto.getIdOperacionMasiva());
			BigDecimal importeTotal = BigDecimal.ZERO;
			List<ShvMasRegistro> registros = operacionMasivaModelo.getRegistros();
			for (ShvMasRegistro shvMasRegistro : registros) {
				if(!EstadoRegistroOperacionMasivaEnum.ERROR.equals(shvMasRegistro.getEstado())){
					shvMasRegistro.setEstado(EstadoRegistroOperacionMasivaEnum.APROBACION_RECHAZADA);
				}
				importeTotal = importeTotal.add(shvMasRegistro.getImporte());
				
			}
			operacionMasivaModelo.setRegistros(registros);
			operacionMasivaModelo = operacionMasivaDao.modificar(operacionMasivaModelo);
			
			ShvWfWorkflow workflow = operacionMasivaModelo.getWorkFlow();
			Date date = new Date();
			
			String asunto = "Operación masiva rechazada por Referente - ";
			asunto += operacionMasivaModelo.getIdOperacionMasiva().toString();
			asunto += " / ";
			OperacionMasivaArchivoDto archivoDto = buscarListaComprobantesXIdArchivo(operacionMasivaModelo.getIdOperacionMasiva().toString());
			asunto += archivoDto.getNombreArchivo();
			StringBuffer cuerpoMail = new StringBuffer();
			cuerpoMail.append("Detalle de la operación masiva ");
			cuerpoMail.append("<br>");
			cuerpoMail.append("<br>");
			cuerpoMail.append("Número de operación masiva: ");
			cuerpoMail.append(operacionMasivaModelo.getIdOperacionMasiva().toString());
			cuerpoMail.append("<br>");
			cuerpoMail.append("Nombre de archivo de operación masiva: ");
			ShvMasOperacionMasivaArchivo se = buscarArchivoOperacionMasivaModelo(operacionMasivaDto.getIdOperacionMasiva());
			cuerpoMail.append(se.getNombreArchivo());
			cuerpoMail.append("<br>");
			cuerpoMail.append("Tipo de Operación masiva: ");
			cuerpoMail.append(operacionMasivaModelo.getTipoOperacion().getDescripcion());
			cuerpoMail.append("<br>");
			cuerpoMail.append("Nombre y apellido del usuario que rechazó el pedido: ");
			cuerpoMail.append(usuarioModificacion.getNombreCompleto());
			if(!Validaciones.isNullOrEmpty(observacion)){
				cuerpoMail.append("<br><br>");
				cuerpoMail.append("Observaciones: ");
				cuerpoMail.append(observacion);
			}
			operacionMasivaDto.setNombreArchivo(se.getNombreArchivo());
			
			UsuarioLdapDto usuarioLdapAnalista = ldapServicio.buscarUsuarioPorUidEnMemoria(operacionMasivaModelo.getIdAnalista());
			UsuarioLdapDto usuarioLdapCopropietario = ldapServicio.buscarUsuarioPorUidEnMemoria(operacionMasivaModelo.getIdCopropietario());
			crearTareaPendienteRechazada(operacionMasivaModelo, operacionMasivaDto, usuarioModificacion, observacion, usuarioLdapAnalista, importeTotal.toString());
			
			String mailCopropietario = "";
			if(!Validaciones.isObjectNull(usuarioLdapCopropietario)){
				mailCopropietario = usuarioLdapCopropietario.getMail();
			}
				
			Mail mail = new Mail(usuarioLdapAnalista.getMail(), mailCopropietario, null, asunto, cuerpoMail);
			tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.APROBAR_OP_MAS, workflow.getIdWorkflow(), date, usuarioModificacion.getIdUsuario(), mail);
			
			operacionMasivaModelo.setFechaModificacion(date);
			operacionMasivaModelo.setUsuarioModificacion(usuarioModificacion.getIdUsuario());
			operacionMasivaModelo = operacionMasivaDao.modificar(operacionMasivaModelo);
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * @param operacionMasivaDto
	 * @throws NegocioExcepcion
	 */
	private void crearTareaPendienteRechazada(ShvMasOperacionMasiva operacionMasivaModelo, OperacionMasivaDto operacionMasivaDto, UsuarioSesion usuarioModificacion, 
			String observacion, UsuarioLdapDto usuarioLdapAnalista, String importe) throws NegocioExcepcion {

			ShvWfWorkflow workflow = operacionMasivaModelo.getWorkFlow();
			if(!Estado.MAS_RECHAZADA.equals(workflow.getEstado())){
				String idUsuario = usuarioModificacion.getIdUsuario();
				
				TareaDto tarea = new TareaDto();
				tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
				workflow = workflowOperacionesMasivas.rechazarAltaDeOperacionMasiva(workflow, observacion, idUsuario);
				operacionMasivaModelo.setWorkFlow(workflow);
				
				tarea.setImporte(importe.replace(".", ","));
				tarea.setIdWorkflow(workflow.getIdWorkflow());
				tarea.setTipoTarea(TipoTareaEnum.REV_OP_MAS_RECH);
				tarea.setFechaCreacion(workflow.getFechaAlta());
				tarea.setUsuarioCreacion(idUsuario);
				tarea.setFechaEjecucion(null);
				tarea.setUsuarioEjecucion(null);
				
				tarea.setNombreUsuarioAsignado(usuarioLdapAnalista.getNombreCompleto());
				tarea.setIdUsuarioAsignado(usuarioLdapAnalista.getTuid());
				tarea.setMailUsuarioAsignado(usuarioLdapAnalista.getMail());
				//tarea.setPerfilAsignacion(TipoPerfilEnum.ANALISTA_COBRANZA.descripcion());
				tarea.setUsuarioAsignacion(workflow.getUsuarioAlta());
				tarea.setGestionaSobre(TipoTareaGestionaEnum.OPERACION_MASIVA);
				tarea.setRefBandeja(operacionMasivaModelo.getIdOperacionMasiva().toString()+"/"+operacionMasivaDto.getNombreArchivo());
				tarea.setIdOperacionMasiva(operacionMasivaModelo.getIdOperacionMasiva());
				tarea.setSegmento(operacionMasivaModelo.getSegmento().getDescripcion());
				tarea.setEmpresa(operacionMasivaModelo.getEmpresa().getDescripcion());
				tarea.setEnviarMail(false);
				tareaServicio.crearTareaRechazoAprobacionOperacionMasiva(tarea);
				
				//operacionMasivaModelo.setObservacion(Utilidad.EMPTY_STRING);
			}
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * @param cobroDto
	 * @param usuarioModificacion
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public void aprobarOperacionMasivaCambiarEstadoWorkFlow(OperacionMasivaDto operacionMasivaDto,  UsuarioSesion usuarioModificacion, String observacion) throws NegocioExcepcion {
		try {
			String idUsuario = usuarioModificacion.getIdUsuario();
			Date fechaActual = new Date();
			ShvMasOperacionMasiva operacionMasivaModelo = operacionMasivaDao.buscarOperacionMasiva(operacionMasivaDto.getIdOperacionMasiva());
			ShvMasOperacionMasivaArchivo archivoModelo = buscarArchivoOperacionMasivaModelo(operacionMasivaDto.getIdOperacionMasiva());
			
			List<ShvMasRegistro> registros = operacionMasivaModelo.getRegistros();
			for (ShvMasRegistro shvMasRegistro : registros) {
				if(!EstadoRegistroOperacionMasivaEnum.ERROR.equals(shvMasRegistro.getEstado())){
					shvMasRegistro.setEstado(EstadoRegistroOperacionMasivaEnum.PENDIENTE_DATOS_SIEBEL);
				}
			}
			operacionMasivaModelo.setRegistros(registros);
			
			ShvWfWorkflow workflow = operacionMasivaModelo.getWorkFlow();
			workflow = workflowOperacionesMasivas.aprobarAltaDeOperacionMasiva(workflow, observacion, idUsuario);
			Date date = workflow.getFechaUltimaModificacion();
			String asunto = "Operación masiva autorizada por Referente - ";
			asunto += operacionMasivaModelo.getIdOperacionMasiva().toString();
			asunto += " / ";
			OperacionMasivaArchivoDto archivoDto = buscarListaComprobantesXIdArchivo(operacionMasivaModelo.getIdOperacionMasiva().toString());
			asunto += archivoDto.getNombreArchivo();
			
			StringBuffer cuerpoMail = new StringBuffer();
			cuerpoMail.append("Detalle de la operación masiva ");
			cuerpoMail.append("<br>");
			cuerpoMail.append("<br>");
			cuerpoMail.append("Número de operación masiva: ");
			cuerpoMail.append(operacionMasivaModelo.getIdOperacionMasiva().toString());
			cuerpoMail.append("<br>");
			cuerpoMail.append("Nombre de archivo de operación masiva: ");
			cuerpoMail.append(archivoModelo.getNombreArchivo());
			cuerpoMail.append("<br>");
			cuerpoMail.append("Tipo de Operación masiva: ");
			cuerpoMail.append(operacionMasivaModelo.getTipoOperacion().getDescripcion());
			if(!Validaciones.isNullOrEmpty(observacion)){
				cuerpoMail.append("<br><br>");
				cuerpoMail.append("Observaciones: ");
				cuerpoMail.append(observacion);
			}
			
			UsuarioLdapDto usuarioLdapAnalista = ldapServicio.buscarUsuarioPorUidEnMemoria(operacionMasivaModelo.getIdAnalista());
			UsuarioLdapDto usuarioLdapCopropietario = ldapServicio.buscarUsuarioPorUidEnMemoria(operacionMasivaModelo.getIdCopropietario());
			String mailCopropietario = "";
			if(!Validaciones.isObjectNull(usuarioLdapCopropietario)) {
				mailCopropietario = usuarioLdapCopropietario.getMail();
			}
			Mail mail = new Mail(usuarioLdapAnalista.getMail(), mailCopropietario, null, asunto, cuerpoMail);
			tareaServicio.finalizarTarea(TipoTareaEnum.APROBAR_OP_MAS, workflow.getIdWorkflow(), date, idUsuario, mail);

			operacionMasivaModelo.setWorkFlow(workflow);
			operacionMasivaModelo.setFechaModificacion(fechaActual);
			operacionMasivaModelo.setUsuarioModificacion(idUsuario);
			
			archivoModelo.setFechaAutorizacion(fechaActual);
			
			operacionMasivaModelo = operacionMasivaDao.modificar(operacionMasivaModelo);
			
			archivoModelo = archivoOperacionMasivaDao.crear(archivoModelo); 
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * @param operacionMasivaDto
	 * @param usuarioModificacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public OperacionMasivaDto buscarOperacionMasiva(Long idOperacionMasiva) throws NegocioExcepcion {
		ShvMasOperacionMasiva operacionMasivaModelo;
		OperacionMasivaDto operacionMasivaDtoFinal = null;
		
		try {
			operacionMasivaModelo = operacionMasivaDao.buscarOperacionMasiva(idOperacionMasiva);
			
			DTO operacionMasivaDto = operacionMasivaMapeador.map(operacionMasivaModelo);
			operacionMasivaDtoFinal = (OperacionMasivaDto) operacionMasivaDto;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return operacionMasivaDtoFinal;
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * @param operacionMasivaDto
	 * @param usuarioModificacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ShvMasOperacionMasiva buscarOperacionMasivaModelo(Long idOperacionMasiva) throws NegocioExcepcion {
		ShvMasOperacionMasiva operacionMasivaModelo;
		try {
			operacionMasivaModelo = operacionMasivaDao.buscarOperacionMasiva(idOperacionMasiva);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return operacionMasivaModelo;
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * @param operacionMasivaDto
	 * @param usuarioModificacion
	 * @return
	 * @throws NegocioExcepcion
	 */
	public ShvMasOperacionMasivaArchivo buscarArchivoOperacionMasivaModelo(Long idOperacionMasiva) throws NegocioExcepcion {
		ShvMasOperacionMasivaArchivo se; 
		try {
			se = archivoOperacionMasivaDao.buscarListaComprobantesPorIdArchivo(idOperacionMasiva.toString());
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return se;
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 */
	public ComprobanteDto buscarAdjuntoOperacionMasiva(Long idAdjunto)
			throws NegocioExcepcion {
		try {
			ShvDocDocumentoAdjunto docAdjunto = documentoAdjuntoDao.buscarDocumentoAdjunto(idAdjunto);
			
			if (docAdjunto!=null) {
				ComprobanteDto comprobante = new ComprobanteDto();
				comprobante.setIdComprobante(docAdjunto.getIdValorAdjunto());
				comprobante.setNombreArchivo(docAdjunto.getNombreArchivo());
				comprobante.setDescripcionArchivo(docAdjunto.getDescripcion());
				comprobante.setDocumento(docAdjunto.getArchivoAdjunto());
				comprobante.setUsuarioCreacion(docAdjunto.getUsuarioCreacion());
				comprobante.setFechaAlta(docAdjunto.getFechaCreacion());
				
				return comprobante;
			}
			return null;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion (e.getMessage(), e);
		}
	}
	
	/**
	 * Metodo que anula tarea RECHAZADA desde la bandeja de entrada del Analista Cobranza.
	 * 
	 * @author u572487 Guido.Settecerze (CSS' assistant).
	 * @param idCobro
	 * @param userSesion
	 */
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public void anularTarea(Long idOperacionMasiva, UsuarioSesion userSesion) throws NegocioExcepcion{
		String datosModificados = "";
		ShvMasOperacionMasiva operacionMasivaModelo;
		try {
			String idUsuario = userSesion.getIdUsuario();
			
			operacionMasivaModelo = operacionMasivaDao.buscarOperacionMasiva(idOperacionMasiva);
			
//			operacionMasivaModelo.setUsuarioUltimaModificacion(idUsuario);
//			operacionMasivaModelo.setFechaUltimaModificacion(new Date());
			
			ShvWfWorkflow workflow = operacionMasivaModelo.getWorkFlow();
			workflow = workflowOperacionesMasivas.anularOperacionMasivaRechazada(workflow, datosModificados, idUsuario);
			operacionMasivaModelo.setWorkFlow(workflow);
			
//			operacionMasivaModelo = cobroOnlineDao.guardarCobro(operacionMasivaModelo);
			
			tareaServicio.finalizarTarea(TipoTareaEnum.REV_OP_MAS_RECH, workflow.getIdWorkflow(), null/*operacionMasivaModelo.getFechaUltimaModificacion()*/,
					operacionMasivaModelo.getIdAnalista(), null);
			
		}catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	
//	@Transactional(readOnly=false)
	public List<OperacionMasivaHistoricaDto> obtenerHistorialOperacionMasiva (String idOperacioMasiva) throws NegocioExcepcion{
		
		List<OperacionMasivaHistoricaDto> listaHistoricoDto = new ArrayList<OperacionMasivaHistoricaDto>();
		
		try {
			List<VistaSoporteResultadoBusquedaOperacionMasivaHistorial> historialOperacionMasiva = vistaSoporteServicio.obtenerHistorialOperacionMasiva(idOperacioMasiva);

			for (VistaSoporteResultadoBusquedaOperacionMasivaHistorial hist : historialOperacionMasiva) {
				listaHistoricoDto.add((OperacionMasivaHistoricaDto) operacionMasivaHistorialMapeo.map(hist)); 
			}
		} catch (NegocioExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return listaHistoricoDto;
		
	}
	
	/**
	 * Metodo que busca una lista de comprobantes a partir del nombre del archivo
	 * @throws PersistenciaExcepcion 
	 */
	
	public OperacionMasivaArchivoDto buscarListaComprobantesXNombreArchivo(String nombreArchivo) throws NegocioExcepcion, PersistenciaExcepcion{
		
		
		ShvMasOperacionMasivaArchivo se = archivoOperacionMasivaDao.buscarArchivoAvcPorNombreArchivo(nombreArchivo);
		
		OperacionMasivaArchivoDto operacionMasivaArchivoDto = (OperacionMasivaArchivoDto) archivoOperacionMasivaMapeador.map(se);
		
		return operacionMasivaArchivoDto;
		
	}
	
	
	
	/**
	 * Metodo que busca una lista de comprobantes a partir del id del archivo
	 * @throws PersistenciaExcepcion 
	 */
	
	public OperacionMasivaArchivoDto buscarListaComprobantesXIdArchivo(String idArchivo) throws NegocioExcepcion, PersistenciaExcepcion{
		
		
		ShvMasOperacionMasivaArchivo se = archivoOperacionMasivaDao.buscarListaComprobantesPorIdArchivo(idArchivo);
		
		OperacionMasivaArchivoDto operacionMasivaArchivoDto = (OperacionMasivaArchivoDto) archivoOperacionMasivaMapeador.map(se);
		
		return operacionMasivaArchivoDto;
		
	}
	
	/**
	 * Metodo que anula operaciones masivas desde la busqueda
	 * @throws PersistenciaExcepcion 
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void anularOperacionMasiva(String idOperacionMasiva, String idUsuario) throws NegocioExcepcion, PersistenciaExcepcion{
		
		ShvMasOperacionMasiva buscarOperacionMasiva = operacionMasivaDao.buscarOperacionMasiva(new Long(idOperacionMasiva));
		ShvWfWorkflow workflow = buscarOperacionMasiva.getWorkFlow();
		Estado estado = workflow.getEstado();
		
		if(Estado.MAS_PENDIENTE_PROCESAR.equals(estado)) {
			workflow = workflowOperacionesMasivas.cancelarOperacionMasivaPendienteDeProcesar(workflow, "", idUsuario);
			buscarOperacionMasiva.getWorkFlow().getWorkflowEstado().setFechaModificacion(workflow.getFechaUltimaModificacion());
			buscarOperacionMasiva.setFechaModificacion(workflow.getFechaUltimaModificacion());
			buscarOperacionMasiva.setUsuarioModificacion(idUsuario);
			buscarOperacionMasiva = operacionMasivaDao.modificar(buscarOperacionMasiva);
			
		} else 
		if(Estado.MAS_RECHAZADA.equals(estado)){
			workflow = workflowOperacionesMasivas.anularOperacionMasivaRechazada(workflow, "", idUsuario);
			
			buscarOperacionMasiva.getWorkFlow().getWorkflowEstado().setFechaModificacion(workflow.getFechaUltimaModificacion());
			buscarOperacionMasiva.setFechaModificacion(workflow.getFechaUltimaModificacion());
			buscarOperacionMasiva.setUsuarioModificacion(idUsuario);
			buscarOperacionMasiva = operacionMasivaDao.modificar(buscarOperacionMasiva);
			
			tareaServicio.finalizarTarea(TipoTareaEnum.REV_OP_MAS_RECH, workflow.getIdWorkflow(), workflow.getFechaUltimaModificacion(),
					buscarOperacionMasiva.getIdAnalista(), null);
		} else 
		if(Estado.MAS_ERROR.equals(estado)){
			workflow = workflowOperacionesMasivas.anularOperacionMasivaEnError(workflow, "", idUsuario);
			
			buscarOperacionMasiva.getWorkFlow().getWorkflowEstado().setFechaModificacion(workflow.getFechaUltimaModificacion());
			buscarOperacionMasiva.setFechaModificacion(workflow.getFechaUltimaModificacion());
			buscarOperacionMasiva.setUsuarioModificacion(idUsuario);
			buscarOperacionMasiva = operacionMasivaDao.modificar(buscarOperacionMasiva);
			
			tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.OP_MAS_ERROR, workflow.getIdWorkflow(), workflow.getFechaUltimaModificacion(), 
					buscarOperacionMasiva.getIdAnalista(), null);
		}
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void cargarDatosBanales(OperacionMasivaDto operacionMasiva) throws PersistenciaExcepcion, NegocioExcepcion{
		
		Date now = operacionMasiva.getFechaUltimaModificacion();
		
		ShvMasOperacionMasiva operacionMasivaModelo = null;
		ShvMasOperacionMasiva opMas = null;
		
		operacionMasivaModelo = operacionMasivaDao.buscarOperacionMasiva(operacionMasiva.getIdOperacionMasiva());
		opMas = (ShvMasOperacionMasiva) operacionMasivaMapeador.map(operacionMasiva, operacionMasivaModelo);
		
		opMas.setIdOperacionMasiva(operacionMasivaModelo.getIdOperacionMasiva());
		opMas.setUsuarioModificacion(operacionMasiva.getUsuarioModificacion());
		opMas.setFechaModificacion(now);
		opMas.setTipoOperacionMasiva(operacionMasivaModelo.getTipoOperacion());
		opMas = operacionMasivaDao.modificar(opMas);
		
//		ShvWfWorkflow workflow = opMas.getWorkFlow();
		
//		tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.OP_MAS_ERROR, workflow.getIdWorkflow(), now, operacionMasiva.getUsuarioModificacion(), null);
//		tareaServicio.finalizarTareaCorrecto(TipoTareaEnum.OP_MAS_PARCIAL, workflow.getIdWorkflow(), now, operacionMasiva.getUsuarioModificacion(), null);
		
//		ShvMasOperacionMasivaArchivo opMasArchivo = buscarArchivoOperacionMasivaModelo(operacionMasiva.getIdOperacionMasiva());
//		if(TipoArchivoOperacionMasivaEnum.DSIST.equals(operacionMasivaModelo.getTipoOperacionMasiva())){
//			crearTareaPendienteAprobacionOperacionMasiva(operacionMasivaModelo, opMasArchivo, operacionMasiva.getUsuarioModificacion());
//		}
	}
	
	public int[] getLengthCamposAplicarDeuda() {
		return lengthCamposAplicarDeuda;
	}

	public int[] getLengthCamposDesistimiento() {
		return lengthCamposDesistimiento;
	}
	
	


	public int[] getLengthCamposGanancias() {
		return lengthCamposGanancias;
	}

	public int[] getLengthCamposReintegro() {
		return lengthCamposReintegros;
	}
	
	public String generarInformacionAdicional(ShvMasOperacionMasivaArchivo opMasArchivo){
		return "Nombre del archivo: "+opMasArchivo.getNombreArchivo()+" <br>Cantidad de registros: "+opMasArchivo.getCantidadRegistros()+" <br>Importe: "+opMasArchivo.getImporteTotal();
	}
	
	public String generarCuerpoMail(ShvMasOperacionMasivaArchivo opMasArchivo,TipoArchivoOperacionMasivaEnum tipoOperacion){
		return "Tipo de operación masiva: "+tipoOperacion.getDescripcionCorta()+" <br>"+generarInformacionAdicional(opMasArchivo);
	}
	
	/***************************************************************************
	 * BATCH
	 * ************************************************************************/
	
	/**
	 * Procesar la respuesta del archivo de entrada generado por MIC como respuesta al procesamiento solicitado 
	 * @param registros
	 * @return
	 * @throws NegocioExcepcion
	 */
	private MicOperacionMasivaSalida armarMicOperacionMasivaSalida(List<ShvMasRegistro> registros) throws NegocioExcepcion {
		
		MicOperacionMasivaSalida datosSalida = new MicOperacionMasivaSalida();
		Date now = new Date();
		// Armo la cabecera
		datosSalida.getCabecera().setTipoRegistro(TipoRegistroEnum.C);
		datosSalida.getCabecera().setCantidadRegistros(new Long(registros.size()));
		datosSalida.getCabecera().setFechaProcesamiento(now);
		
		// Armo el pie
		datosSalida.getPie().setTipoRegistro(TipoRegistroEnum.P);
		datosSalida.getPie().setCantidadRegistros(new Long(registros.size()));
		datosSalida.getPie().setFechaProcesamiento(now);
		
		// Inicializo el contador de registros por operacion masiva.
		// Corre en un Batch de un solo hilo
		micOperacionMasivaRegistroMapeador.inicializarContadorOperacionMasiva();
		
		// Armo el cuerpo
		for (ShvMasRegistro registro : registros) {
			datosSalida.getRegistros().add(
				(MicOperacionMasivaRegistroSalida) micOperacionMasivaRegistroMapeador.mapRegistroSalida(registro)
			);
		}
		return datosSalida;
	}

	/**
	 * 
	 * @param ids
	 * @param nombreArchivo
	 * @param fechaArchivo
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<ShvMasRegistro> actualizarEstadosYcompletarCamposDeRegistroYmodifico(List<ShvMasRegistro> listaShvMasRegistro,
		String nombreArchivo, Date fechaArchivo) throws NegocioExcepcion {
		
		try {
			List<Long> idsReutilizables = registroOperacionMasivaDao.obtenerIdOperacionShivaReutilizable();
			String segmentosObligatoriosTrasladoInteres = Constantes.EMPTY_STRING;
			
			if(!Validaciones.isObjectNull(parametroServicio.getValorTexto(ConstantesCobro.COBROS_VALIDACION_SEGMENTO_OBLIGATORIO_TRASLADO_INTERESES_PROXIMA_FACTURA))) {
				segmentosObligatoriosTrasladoInteres = parametroServicio.getValorTexto(ConstantesCobro.COBROS_VALIDACION_SEGMENTO_OBLIGATORIO_TRASLADO_INTERESES_PROXIMA_FACTURA);
			}
			
			for (ShvMasRegistro shvMasRegistro : listaShvMasRegistro) {
				if (shvMasRegistro instanceof ShvMasRegistroReintegro) {
					
					ShvMasRegistroReintegro shvMasRegistroReintegro = (ShvMasRegistroReintegro) shvMasRegistro;
					
					String segmentoCliente = shvMasRegistroReintegro.getClientesSiebel().getSegmentoAgenciaNegocioCredito();
					
					if (SiNoEnum.NO.equals(shvMasRegistroReintegro.getReintegraConInteres())) { 
						if (Validaciones.pertenece(segmentoCliente,segmentosObligatoriosTrasladoInteres, ";")) {
							shvMasRegistro.setEstado(EstadoRegistroOperacionMasivaEnum.ERROR_SHIVA);
							shvMasRegistro.setDescripcionErrorReintegro(Propiedades.MENSAJES_PROPIEDADES.getString("operaciones.masivas.descripcion.error.reintegro"));
						}		
					}
				}
				
				if (!EstadoRegistroOperacionMasivaEnum.ERROR_SHIVA.equals(shvMasRegistro.getEstado())) {
					
					shvMasRegistro.setEstado(EstadoRegistroOperacionMasivaEnum.PROCESO_IMPUTACION);
					shvMasRegistro.setFechaModificacion(fechaArchivo);
					shvMasRegistro.setUsuarioModificacion(parametroServicio.getValorTexto(Constantes.USUARIO_BATCH));
					shvMasRegistro.setFechaEntrada(fechaArchivo);
					shvMasRegistro.setNombreArchivoEntrada(nombreArchivo);
					
					if (idsReutilizables.isEmpty()) {
						shvMasRegistro.setIdOperacion(genericoDao.proximoValorSecuencia("SEQ_SHV_COB_OPERACION"));
					} else {
						Long id = idsReutilizables.get(0);
						idsReutilizables.remove(0);
						shvMasRegistro.setIdOperacion(id);
					}
					shvMasRegistro.setIdTransaccion(new Long(1));
				}
			}
			listaShvMasRegistro = registroOperacionMasivaDao.modificar(listaShvMasRegistro);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
		return listaShvMasRegistro;
	}

	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void generarArchivoEntradaMic() throws NegocioExcepcion {
		StringBuffer nombreArchivo = null;
		try {
			List<ShvMasRegistro> listaShvMasRegistro = new ArrayList<ShvMasRegistro>();
			MicOperacionMasivaSalida datosSalida = null;
			Date fechaArchivo = new Date();

			nombreArchivo = new StringBuffer(Constantes.OPERACION_MASIVA_MIC_ENTRADA_APP_NOMBRE_ARCHIVO);
			nombreArchivo.append(".");
			nombreArchivo.append(Constantes.OPERACION_MASIVA_MIC_ENTRADA_NOMBRE_ARCHIVO);
			nombreArchivo.append(".");
			nombreArchivo.append(Utilidad.formatDateAAAAMMDD(fechaArchivo));
			nombreArchivo.append(Constantes.ARCHIVO_TXT);

			Traza.auditoria(getClass(), "Se comienza a armar el archivo de entrada a Mic: " + nombreArchivo.toString());
			List<ShvMasRegistro> listaShvMasRegistroSalida = new ArrayList<ShvMasRegistro>();

			try {
				listaShvMasRegistro.addAll(
					registroOperacionMasivaDao.buscarRegistrosOperacionMasivaByEstadoAndEstadosOperacionMasiva(
						EstadoRegistroOperacionMasivaEnum.PENDIENTE_PROCESAR,
						Arrays.asList(
							new Estado[] {
								Estado.MAS_PROCESO_IMPUTACION
				})));

				Traza.auditoria(getClass(), "Se encontraron " + listaShvMasRegistro.size() + " registros PENDIENTE PROCESAR");

				// Actualizar el estado de las Operacion masiva
				// Actualizar los estados de los registros --> Enviado a MIC
				// Completo los registros a enviar a mic y los modifico
				listaShvMasRegistro = this.actualizarEstadosYcompletarCamposDeRegistroYmodifico(listaShvMasRegistro, nombreArchivo.toString(), fechaArchivo);

				// filtro los registro que no se envian a mic
				for (ShvMasRegistro shvMasRegistro : listaShvMasRegistro) {
					if (EstadoRegistroOperacionMasivaEnum.PROCESO_IMPUTACION.equals(shvMasRegistro.getEstado())) {
						listaShvMasRegistroSalida.add(shvMasRegistro);
					}
				}
				Traza.auditoria(getClass(), "Se cambiaron " + listaShvMasRegistroSalida.size() + " registros a PROCESO IMPUTACION luego de la gestionabilidad");

				//Armar el objeto MicOperacionMasivaSalida a partir de los registros
				datosSalida = this.armarMicOperacionMasivaSalida(listaShvMasRegistroSalida);
				
			} catch (PersistenciaExcepcion e) {
				Traza.error(getClass(), "Se ha producido una exception al guardar", e);
				throw new NegocioExcepcion(e.getMessage(),e);
			}

			//Mapear
			String contenido = micOperacionMasivaMapeoRegistro.serializar(datosSalida);

			//Guardar el archivo
			try {
				String carpetaDestino = Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.operacionesMasivas.entrada");
				ControlArchivo.escribir(contenido, carpetaDestino.toString(), nombreArchivo.toString());
			} catch (ShivaExcepcion e) {
				throw new NegocioExcepcion(e.getMessage(),e);
			}
			Traza.auditoria(getClass(), "La construccion del archivo" + nombreArchivo + ": finalizada");
			
			//Enviar mail dando aviso de inicio del proceso
			this.enviarMailRta(
				OperacionMasivaBatchEmailEnum.HACIA_MIC_INICIO_PROCESO_OK,
				nombreArchivo.toString(),
				String.valueOf(listaShvMasRegistroSalida.size())
			);
			Traza.auditoria(getClass(), "La construccion del archivo" + nombreArchivo + " a finalizada");
		} catch (NegocioExcepcion e) {
			Traza.error(getClass(), "Se ha producido un error en el proceso batch", e);
			Traza.error(getClass(), "Se envia un mail informando el error."); 
			try {
				this.enviarMailRta(
					OperacionMasivaBatchEmailEnum.HACIA_MIC_INICIO_PROCESO_NOK,
					nombreArchivo.toString()
				);
			} catch (Exception e2) {
				Traza.error(getClass(), "No se pudo enviar un mail informando el error.", e); 
			}
			
			throw new NegocioExcepcion(e);
		}
		return;
		
	}
	/********************************************************************************/
	@Override
	public FilenameFilter filtrarArchivosEntradaDeOperacionMasivaMic() {
		return new FilenameFilter() {
			public boolean accept(File dir, String name) {
				if (Validaciones.esNombreArchivoOperacionMasivaMic(name, true)) {
					return true;
				}
				return false;
			}
		};	
	}
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public boolean procesarArchivoInterfazMicSalida(File file) throws NegocioExcepcion, ShivaExcepcion {
		Traza.auditoria(this.getClass(), "- Se procesara el archivo nombre:" + file.getName());
		
		MicOperacionMasivaEntrada reg = new MicOperacionMasivaEntrada();
		try {
			operacionMasivaArchivaServicio.validarConsistencia(file, reg);
		} catch (Exception e) {
			Traza.auditoria(getClass(), "-Se han encontrado errores de validacione en el archivo " + file.getName());
			this.enviarMailRta(
				OperacionMasivaBatchEmailEnum.DESDE_MIC_VALIDACION_NOK,
				file.getName(), 
				ValidarAuxiliarSingleton.getInstance().toString()
			);
			throw new NegocioExcepcion("-Se han encontrado errores de validacione en el archivo " + file.getName());
		}
		
		String[] lineas = ControlArchivo.leerArchivo(file.getAbsolutePath());
		
		try {
			operacionMasivaArchivaServicio.validarRegistrosSalidaMic(lineas, reg, file.getName());
		} catch (NegocioExcepcion e) {
			throw new NegocioExcepcion(e);
		} catch (Exception e) {
			this.enviarMailRta(
				OperacionMasivaBatchEmailEnum.DESDE_MIC_VALIDACION_NOK,
				file.getName(), 
				"Se produjo un error de aplicacion. " + (e.getMessage() != null ? e.getMessage() : "")
			);
			throw new NegocioExcepcion("-Se han encontrado errores de validacione en el archivo " + file.getName(), e);
		}
		
		
		if (ValidarAuxiliarSingleton.getInstance().isEmpty()) {
			int cantidad = 0;
			if (lineas.length > 2) {
				cantidad = lineas.length - 2;
			}
			Traza.auditoria(getClass(), "- No se han encontrado errores de validacione en el archivo " + file.getName());
			
			this.enviarMailRta(
				OperacionMasivaBatchEmailEnum.DESDE_MIC_VALIDACION_OK,
				file.getName(),
				String.valueOf(cantidad)
			);
			
			try {
				reg = operacionMasivaArchivaServicio.procesarRegistrosSalidaMic(lineas, reg, file.getName());
				List<ShvMasRegistro> registrosPersistidos = operacionMasivaRegistroServicio.volcarDatosArchivoEntraMic(reg);

				if (!registrosPersistidos.isEmpty()) {
					Traza.auditoria(getClass(), "Hay cobros para generar: " + registrosPersistidos.size());
					operacionMasivaRegistroServicio.generarCobroByListaShvMasRegistro(registrosPersistidos);
				}
				
				this.enviarMailRta(
					OperacionMasivaBatchEmailEnum.DESDE_MIC_PROCESO_OK,
					file.getName(), //TODO Nombre del archivo
					" "//TODO Cantida de registro
				);
				Traza.auditoria(getClass(), "- Se ha procesado exitosamente el archivo de nombre  " + file.getName());
			
			} catch (NegocioExcepcion e) {
				Traza.auditoria(getClass(), "- No se ha procesado exitosamente el archivo de nombre " + file.getName());
				this.enviarMailRta(
					OperacionMasivaBatchEmailEnum.DESDE_MIC_PROCESO_NOK,
					file.getName(), //TODO Nombre del archivo
					" "//TODO Cantida de registro
				);
				throw new NegocioExcepcion(e);
			} catch (Exception e) {
				this.enviarMailRta(
					OperacionMasivaBatchEmailEnum.DESDE_MIC_PROCESO_NOK,
					file.getName(), 
					"Se produjo un error de aplicacion. " + (e.getMessage() != null ? e.getMessage() : "")
				);
				throw new NegocioExcepcion("-Se han encontrado errores en el proceso en el archivo " + file.getName(), e);
			}
			
		} else {
			Traza.auditoria(getClass(), "-Se han encontrado errores de validacione en el archivo " + file.getName());
			this.enviarMailRta(
				OperacionMasivaBatchEmailEnum.DESDE_MIC_VALIDACION_NOK,
				file.getName(), 
				ValidarAuxiliarSingleton.getInstance().toString()
			);
			throw new NegocioExcepcion("-Se han encontrado errores de validacione en el archivo " + file.getName());
		}
		return false;
	}
//	@Override
//	public boolean procesarArchivosInterfazMicSalida() throws NegocioExcepcion, ShivaExcepcion {
//		try {
//			Traza.auditoria(getClass(), "- Se ha iniciado la busqueda de archivos de Salida Operacion Masiva Mic ");
//			int contadorError = 0;
//			try {
//				// Levanto el archivo del dia actual
//				FilenameFilter filtroArchivo = filtrarArchivosEntradaDeOperacionMasivaMic();
//				File directorio = ControlArchivo.getDirectorio(Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.operacionesMasivas.salida"));
//				if (ControlArchivo.existeArchivosEnUnDirectorio(directorio, filtroArchivo)) {
//					// Ordeno las lineas
//					List<File> archivos = ControlArchivo.listaArchivosEnUnDirectorio(directorio, filtroArchivo);
//					Traza.auditoria(getClass(), "- Se han encontrado " + (archivos != null ? archivos.size() : 0) + " archivos.");
//					for (File archivo : archivos) {
//						try {
//							ValidarAuxiliarSingleton.getInstance().reIniciar();
//							ValidarAuxiliarSingleton.getInstance().setCurrentfileName(archivo.getName());
//							//Se encarga de procesar un archivo de reversion de cobros
//							this.procesarArchivoInterfazMicSalida(archivo);
//
//							ControlArchivo.moverArchivo(archivo.getName(),Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.operacionesMasivas.salida"), 
//									Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.operacionesMasivas.historico"));
//							System.out.println("Se ha procesado el archivo " + archivo.getName() + " correctamente.");
//							Traza.auditoria(getClass(), "Se ha procesado el archivo " + archivo.getName() + " correctamente.");
//
//							
//						} catch (Exception e) {
//							System.err.println("Se ha producido un error al procesar el archivo " + archivo.getName() + " " + e.getMessage());
//							Traza.error(getClass(), "Se ha producido un error al procesar el archivo " + archivo.getName(), e);
//							contadorError++;
//						}
//					}
//					if (contadorError > 0) {
//						return false;
//					}
//					return true;
//				} else {
//					System.out.println("No se ha encontrado ningún archivo con el formato [Nombre Aplicación].OperacionesMasivas.MIC.Salida.[fecha]");
//					Traza.advertencia(getClass(), "No se ha encontrado ningún archivo con el formato [Nombre Aplicación].OperacionesMasivas.MIC.Salida.[fecha]");
//					return false;
//				}
//			} catch (Exception e) {
//				throw new NegocioExcepcion(e);
//			}
//		} catch (Throwable e) {
//			Traza.error(getClass(), "Se ha producido un error en el proceso batch", e);
//			Traza.advertencia(getClass(), "---- Se ha finalizado con error en el proceso de Reversion de Cobros");
//			
//			throw new ShivaExcepcion(e);
//		}
//	}

	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void actualizarContadoresEnOperacionesMasivas() throws NegocioExcepcion {
		try {
			Traza.auditoria(ImputacionCobrosBatchRunner.class, "Se actualizan los contadores de registro de operaciones masivas");
			List<ShvMasOperacionMasivaSimplificado> list = operacionMasivaDao.calcularCantidadesDeRegistros(
				Arrays.asList(
					new Estado[] {
						Estado.MAS_PROCESO_IMPUTACION,
					}
				)
			);

			if (!Validaciones.isObjectNull(list) && !list.isEmpty()) {
				operacionMasivaDao.modificar(list);	
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion("Error al intentar actualizar contadores de registro de operaciones masivas" + e);
		}
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void actualizarEstadoOperacionesMasivasSinRegistrosEnProceso() throws NegocioExcepcion {
		String asunto;
		StringBuffer cuerpo = new StringBuffer();
		boolean enError;
		boolean proceParcial;
		String nombreArchivo = null;
		BigDecimal importeTotal = null;
		
		Traza.auditoria(ImputacionCobrosBatchRunner.class, "- Actulizacion de estado de operaciones masivas");
		
		try {
			List<ShvMasOperacionMasivaSimplificadoWorkFlow> list = operacionMasivaDao.buscarOperacionesMasivasSinRegistroEnProcesoByEstado(
				Arrays.asList(
					new Estado[] {
						Estado.MAS_PROCESO_IMPUTACION
					}
				)
			);
			Traza.auditoria(ImputacionCobrosBatchRunner.class, "- Se han encontrado " + ((list != null) ? list.size() : 0) + " para actulizar");
			
			String userBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
			
			for(ShvMasOperacionMasivaSimplificadoWorkFlow shvMasOperacionMasivaSimplificadoWorkFlow : list) {
				// Si la los contadores dan que todos los ShvMasRegistro tiene un error no se crean ningun cobro y se pasa de estado al work
				ShvWfWorkflow shvWfWorkflow = null;
				
				enError = false;
				proceParcial = false;
				UsuarioLdapDto usuarioLdapAnalista = ldapServicio.buscarUsuarioPorUidEnMemoria(shvMasOperacionMasivaSimplificadoWorkFlow.getIdAnalista());
				UsuarioLdapDto usuarioLdapCopropietario = null;
				String mailCopropietario = "";
				Date fechaModificacion = new Date();
				cuerpo.delete(0, cuerpo.length());
				
				if(!Validaciones.isObjectNull(shvMasOperacionMasivaSimplificadoWorkFlow.getIdCopropietario())){
					usuarioLdapCopropietario = ldapServicio.buscarUsuarioPorUidEnMemoria(shvMasOperacionMasivaSimplificadoWorkFlow.getIdCopropietario());
					if(!Validaciones.isObjectNull(usuarioLdapCopropietario)){
						mailCopropietario = usuarioLdapCopropietario.getMail();
					}
					
				}
				
				importeTotal=new BigDecimal(0);
				
				if (shvMasOperacionMasivaSimplificadoWorkFlow.getCantRegistros().compareTo(shvMasOperacionMasivaSimplificadoWorkFlow.getCantRegistrosError()) == 0) {
					shvWfWorkflow = workflowOperacionesMasivas.registrarOperacionMasivaEnError(
						shvMasOperacionMasivaSimplificadoWorkFlow.getWorkFlow(),
						"" ,
						userBatch
					);
					List<ShvMasRegistro> shvMasRegistros = registroOperacionMasivaDao.buscarRegistrosXOperacionMasivaDistintosAEstado(EstadoRegistroOperacionMasivaEnum.ERROR,shvMasOperacionMasivaSimplificadoWorkFlow.getIdOperacionMasiva());
					
					if(shvMasRegistros.size() > 0){
						nombreArchivo = shvMasRegistros.get(0).getNombreArchivoOriginal();
					}
					for (ShvMasRegistro reg : shvMasRegistros) {
						importeTotal = importeTotal.add(reg.getImporte());
						
					}
					
					enError = true;
					asunto= "Nueva Tarea - Operación Masiva Rechazada: " + shvMasOperacionMasivaSimplificadoWorkFlow.getIdOperacionMasiva() + "/" + nombreArchivo;
					cuerpo.append("Detalle de la operación masiva:" + Constantes.RETORNO_HTML);
					cuerpo.append("Número de Operación Masiva: " + shvMasOperacionMasivaSimplificadoWorkFlow.getIdOperacionMasiva() + Constantes.RETORNO_HTML);
					cuerpo.append("Nombre de Archivo: " + nombreArchivo + Constantes.RETORNO_HTML);
					cuerpo.append("Tipo de Operación Masiva: " + shvMasOperacionMasivaSimplificadoWorkFlow.getTipoOperacionMasiva().getDescripcion() + Constantes.RETORNO_HTML);
					cuerpo.append("Cantidad de registros procesados OK: " + shvMasOperacionMasivaSimplificadoWorkFlow.getCantRegistrosProcesados() + Constantes.RETORNO_HTML);
					cuerpo.append("Cantidad de registros procesados NO OK: " + shvMasOperacionMasivaSimplificadoWorkFlow.getCantRegistrosError());
				} else if (shvMasOperacionMasivaSimplificadoWorkFlow.getCantRegistros().compareTo(shvMasOperacionMasivaSimplificadoWorkFlow.getCantRegistrosProcesados()) == 0) {
					// Si la contadores dan que todo los ShvMasRegistro fueron procesados ok paso de estado
					//operacionMasivaRegistroServicio.generarCobroByOperacionMasivaProcesada(shvMasOperacionMasivaSimplificadoWorkFlow);
					
					shvWfWorkflow = workflowOperacionesMasivas.registrarOperacionMasivaProcesada(shvMasOperacionMasivaSimplificadoWorkFlow.getWorkFlow(),"",userBatch);
					
					List<ShvMasRegistro> shvMasRegistros = registroOperacionMasivaDao.buscarRegistrosXOperacionMasivaDistintosAEstado(EstadoRegistroOperacionMasivaEnum.ERROR,shvMasOperacionMasivaSimplificadoWorkFlow.getIdOperacionMasiva());
					
					if(shvMasRegistros.size() > 0){
						nombreArchivo = shvMasRegistros.get(0).getNombreArchivoOriginal();
					}
				
					asunto= "Operación Masiva Imputada: " + shvMasOperacionMasivaSimplificadoWorkFlow.getIdOperacionMasiva() + "/" + nombreArchivo;
					cuerpo.append("Detalle de la operación masiva:" + Constantes.RETORNO_HTML);
					cuerpo.append("Número de Operación Masiva: " + shvMasOperacionMasivaSimplificadoWorkFlow.getIdOperacionMasiva() + Constantes.RETORNO_HTML);
					cuerpo.append("Nombre de Archivo: " + nombreArchivo + Constantes.RETORNO_HTML);
					cuerpo.append("Tipo de Operación Masiva: " + shvMasOperacionMasivaSimplificadoWorkFlow.getTipoOperacionMasiva().getDescripcion() + Constantes.RETORNO_HTML);
					cuerpo.append("Cantidad de registros procesados OK: " + shvMasOperacionMasivaSimplificadoWorkFlow.getCantRegistrosProcesados() + Constantes.RETORNO_HTML);
					cuerpo.append("Cantidad de registros procesados NO OK: " + shvMasOperacionMasivaSimplificadoWorkFlow.getCantRegistrosError());
					
				} else {
					//operacionMasivaRegistroServicio.generarCobroByOperacionMasivaProcesada(shvMasOperacionMasivaSimplificadoWorkFlow);
					shvWfWorkflow = workflowOperacionesMasivas.registrarOperacionMasivaProcesadaParcialmente(
						shvMasOperacionMasivaSimplificadoWorkFlow.getWorkFlow(),
						"",
						userBatch
					);
					
					
					List<ShvMasRegistro> shvMasRegistros = registroOperacionMasivaDao.buscarRegistrosXOperacionMasivaDistintosAEstado(EstadoRegistroOperacionMasivaEnum.ERROR,shvMasOperacionMasivaSimplificadoWorkFlow.getIdOperacionMasiva());
					
					if(shvMasRegistros.size() > 0){
						nombreArchivo = shvMasRegistros.get(0).getNombreArchivoOriginal();
					}
					
					for (ShvMasRegistro reg : shvMasRegistros) {
						importeTotal = importeTotal.add(reg.getImporte());
					}
					
					proceParcial = true;
				
					asunto= "Nueva Tarea - Operación Masiva Parcialmente Imputada: " + shvMasOperacionMasivaSimplificadoWorkFlow.getIdOperacionMasiva() + "/" + nombreArchivo;
					cuerpo.append("Detalle de la operación masiva:" + Constantes.RETORNO_HTML);
					cuerpo.append("Número de Operación Masiva: " + shvMasOperacionMasivaSimplificadoWorkFlow.getIdOperacionMasiva() + Constantes.RETORNO_HTML);
					cuerpo.append("Nombre de Archivo: " + nombreArchivo + Constantes.RETORNO_HTML);
					cuerpo.append("Tipo de Operación Masiva: " + shvMasOperacionMasivaSimplificadoWorkFlow.getTipoOperacionMasiva().getDescripcion() + Constantes.RETORNO_HTML);
					cuerpo.append("Cantidad de registros procesados OK: " + shvMasOperacionMasivaSimplificadoWorkFlow.getCantRegistrosProcesados() + Constantes.RETORNO_HTML);
					cuerpo.append("Cantidad de registros procesados NO OK: " + shvMasOperacionMasivaSimplificadoWorkFlow.getCantRegistrosError());
					
				}
				
				ShvMasOperacionMasivaArchivo shvOperMasArchivo = archivoOperacionMasivaDao.buscarArchivoPorIdOperacionMasiva(shvMasOperacionMasivaSimplificadoWorkFlow.getIdOperacionMasiva());
				shvOperMasArchivo.setFechaProceso(fechaModificacion);
				shvOperMasArchivo = archivoOperacionMasivaDao.crear(shvOperMasArchivo);
				
				
				shvMasOperacionMasivaSimplificadoWorkFlow.setWorkFlow(shvWfWorkflow);
				shvMasOperacionMasivaSimplificadoWorkFlow.setFechaModificacion(fechaModificacion);
				shvMasOperacionMasivaSimplificadoWorkFlow.setUsuarioModificacion(userBatch);
				
				
				
				if(enError){
					crearTareaRegistrosEnError(shvMasOperacionMasivaSimplificadoWorkFlow,importeTotal,nombreArchivo);
					
				}
				
				if(proceParcial){
					crearTareaRegistrosProcesadosParcialmente(shvMasOperacionMasivaSimplificadoWorkFlow,importeTotal,nombreArchivo);
				}
				
				String contenido = generarArchivoOperacionMasivaRespuesta(shvMasOperacionMasivaSimplificadoWorkFlow.getIdOperacionMasiva());
				byte[] b = contenido.getBytes();
				
				if (nombreArchivo != null && nombreArchivo.length() > 0) {
					nombreArchivo = nombreArchivo.substring(0, nombreArchivo.length()-4);
				}

				
				
				Adjunto adj = new Adjunto(b, nombreArchivo, "txt");
				List<Adjunto> listAdj = new ArrayList<Adjunto>();
				
				listAdj.add(adj);
				
				Mail mail = new Mail(usuarioLdapAnalista.getMail(), mailCopropietario, asunto, cuerpo);
				mail.setAdjuntos(listAdj);
				mailServicio.sendMail(mail);
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion("Error al leer operacione masivas", e);
		}
	}
	
	@Override
	public List<OperacionMasivaDto> listarOperacionesMasivas(
			VistaSoporteOperacionMasivaFiltro operacionMasivaFiltro)
			throws NegocioExcepcion {
		
		List<OperacionMasivaDto> listaOperacionesMasivasDto = new ArrayList<OperacionMasivaDto>();
		try {
			List<VistaSoporteOperacionesMasivas> listaOperacionesMasivas = vistaSoporteServicio.listarOperacionesMasivas(operacionMasivaFiltro);
			for (VistaSoporteOperacionesMasivas operacionMasiva : listaOperacionesMasivas) {
				listaOperacionesMasivasDto.add((OperacionMasivaDto) operacionMasivaVistaMapeo.map(operacionMasiva)); 
			
			}
		} catch (NegocioExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return listaOperacionesMasivasDto;
	}

	@Override
	public ArchivoOperacionMasivaProcesadoDto buscarArchivoOperacionMasivaParaDescargar(Long idOperacionMasiva) throws NegocioExcepcion {
		ArchivoOperacionMasivaProcesadoDto archivo = new ArchivoOperacionMasivaProcesadoDto();
		try {
			ShvMasOperacionMasivaArchivo archivoOperacionMasivaModelo = archivoOperacionMasivaDao.buscarArchivoPorIdOperacionMasiva(idOperacionMasiva);
			
			if(!Validaciones.isObjectNull(archivoOperacionMasivaModelo)){
				archivo.setNombreArchivo(archivoOperacionMasivaModelo.getDocumentoAdjunto().getNombreArchivo());
				archivo.setArchivo(archivoOperacionMasivaModelo.getDocumentoAdjunto().getArchivoAdjunto());
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return archivo;
	}
	
	@Override
	public boolean procesarTareaOperacionesMasivasSiebel(int cantidadRegistros, int cantidadRegistrosPorVuelta) throws NegocioExcepcion, PersistenciaExcepcion {

		List<ShvMasRegistro> listaRegistros = new ArrayList<ShvMasRegistro>();
		List<ShvMasRegistro> listaRegistrosPorVuelta = new ArrayList<ShvMasRegistro>();
		List<ShvMasRegistro> listaRegistrosGestionabilidad = new ArrayList<ShvMasRegistro>();
		Set<ShvMasOperacionMasiva> listaShvMasOperacionMasiva = new HashSet<ShvMasOperacionMasiva>();


		int cantidadVueltas;

		// Este metodo trae todos los registros existentes en dichos estados
		listaRegistrosGestionabilidad.addAll(registroOperacionMasivaDao.buscarRegistrosOperacionMasivaByEstadoAndEstadosOperacionMasiva(
						EstadoRegistroOperacionMasivaEnum.PENDIENTE_DATOS_SIEBEL,Arrays.asList(
								new Estado[] {Estado.MAS_PENDIENTE_PROCESAR,Estado.MAS_PROCESO_IMPUTACION})));

		
		if (!Validaciones.isCollectionNotEmpty(listaRegistrosGestionabilidad)) {
			return true;
		}
		// u578936 Max. Agrego la gestionabilidad de deuda
		// Gestionabilidad de Registro de operacion Masiva
		operacionMasivaRegistroServicio.realizarGestionabiliadRegistrosOperacionMasiva(listaRegistrosGestionabilidad);

		for (ShvMasRegistro shvMasRegistro : listaRegistrosGestionabilidad) {
			if (EstadoRegistroOperacionMasivaEnum.ERROR_SHIVA.equals(shvMasRegistro.getEstado())) {
				Traza.auditoria(this.getClass(), "El Registro Nro "+ shvMasRegistro.getIdRegistro() +" (Tipo: " + shvMasRegistro.getOperacionMasiva().getTipoOperacionMasiva() 
					+ " - Id: " + shvMasRegistro.getOperacionMasiva().getIdOperacionMasiva() + ") no pasa la gestionabilidad queda en estado " + shvMasRegistro.getEstado().getDescripcion());
				registroOperacionMasivaDao.modificar(shvMasRegistro);
				
			} else {
				Traza.auditoria(
					this.getClass(), "El Registro Nro "+ shvMasRegistro.getIdRegistro() +" (Tipo: " + shvMasRegistro.getOperacionMasiva().getTipoOperacionMasiva() 
						+ " - Id: " + shvMasRegistro.getOperacionMasiva().getIdOperacionMasiva() + ") pasa la gestionabilidad");
				listaRegistros.add(shvMasRegistro);
			}
			if (Estado.MAS_PENDIENTE_PROCESAR.equals(shvMasRegistro.getOperacionMasiva().getWorkFlow().getWorkflowEstado().getEstado())) {
				listaShvMasOperacionMasiva.add(shvMasRegistro.getOperacionMasiva());
			}
		}
		String userBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
		for (ShvMasOperacionMasiva shvMasOperacionMasiva : listaShvMasOperacionMasiva) {
			ShvWfWorkflow workflow = workflowOperacionesMasivas.procesarOperacionMasiva(
				shvMasOperacionMasiva.getWorkFlow(),
				"",
				userBatch
			);
			shvMasOperacionMasiva.setWorkFlow(workflow);
		}
		if (!Validaciones.isCollectionNotEmpty(listaRegistros)) {
			Traza.auditoria(this.getClass(), "--- Luego de gestionabilidad: No hay registro gestionables");
			return true;
		}
		//Chequeo que en la lista quede la cantidad de registros a procesar ( pasada por parametro) y no sea superada.
		if(listaRegistros.size() >= cantidadRegistros) {
			listaRegistros.retainAll(listaRegistros.subList(0, cantidadRegistros));
		}

		// Calculo cantidad de vueltas necesarias
		cantidadVueltas = (listaRegistros.size() / cantidadRegistrosPorVuelta);
		
		if ((listaRegistros.size() % cantidadRegistrosPorVuelta) > 0) {
			cantidadVueltas++;
		}
		
		for (int i = 0; i < cantidadVueltas; i++) {
			Traza.auditoria(this.getClass(), "--- Vuelta "+ i +"...");
			
			listaRegistrosPorVuelta.clear();
			
			if(listaRegistros.size() >= cantidadRegistrosPorVuelta) {
				listaRegistrosPorVuelta.addAll(listaRegistros.subList(0, cantidadRegistrosPorVuelta));
				listaRegistros.removeAll(listaRegistrosPorVuelta);
			}else {
				listaRegistrosPorVuelta.addAll(listaRegistros);
			}
			this.procesarRegistrosOperacionesMasivasSiebel(listaRegistrosPorVuelta);
			Traza.auditoria(this.getClass(), "--- Fin - Vuelta "+ i +" ---");
		}
		
		return false;
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public void procesarRegistrosOperacionesMasivasSiebel(List<ShvMasRegistro> listaShvMasRegistro) throws NegocioExcepcion, PersistenciaExcepcion{
		
		for (ShvMasRegistro registro : listaShvMasRegistro) {
			
			try {
				
				if(!Validaciones.isObjectNull(registro.getClientesSiebel().getClienteDuenoDebito()) && !TipoResultadoEnum.OK.equals(registro.getClientesSiebel().getResultadoConsultaSiebelDebito())) {
					this.consultaSiebelClienteDebito(registro);
				}
				if(!Validaciones.isObjectNull(registro.getClientesSiebel().getClienteDuenoCredito()) && !TipoResultadoEnum.OK.equals(registro.getClientesSiebel().getResultadoConsultaSiebelCredito())) {
					this.consultaSiebelClienteCredito(registro);
				}
				if(!Validaciones.isObjectNull(registro.getClientesSiebel().getClienteDuenoAcuerdo()) && !TipoResultadoEnum.OK.equals(registro.getClientesSiebel().getResultadoConsultaSiebelAcuerdo())) {
					this.consultaSiebelClienteAcuerdo(registro);
				}
				
				if(!TipoResultadoEnum.ERROR_SERVICIO.equals(registro.getClientesSiebel().getResultadoConsultaSiebelDebito())
						&& !TipoResultadoEnum.ERROR_SERVICIO.equals(registro.getClientesSiebel().getResultadoConsultaSiebelCredito())
							&& !TipoResultadoEnum.ERROR_SERVICIO.equals(registro.getClientesSiebel().getResultadoConsultaSiebelAcuerdo())) {
					
					registro.setEstado(EstadoRegistroOperacionMasivaEnum.PENDIENTE_PROCESAR);
					Traza.auditoria(this.getClass(), "El Registro Nro "+ registro.getIdRegistro() +" (Tipo: " + registro.getOperacionMasiva().getTipoOperacionMasiva() 
							+ " - Id: " + registro.getOperacionMasiva().getIdOperacionMasiva() + ") finaliza en estado "+registro.getEstado().getDescripcion());
				}
			} catch (NegocioExcepcion e) {
					registro.setEstado(EstadoRegistroOperacionMasivaEnum.ERROR_SIEBEL);
					Traza.auditoria(this.getClass(), "El Registro Nro "+ registro.getIdRegistro() +" (Tipo: " + registro.getOperacionMasiva().getTipoOperacionMasiva() 
							+ " - Id: " + registro.getOperacionMasiva().getIdOperacionMasiva() + ") finaliza en estado "+registro.getEstado().getDescripcion());
			}
			
			registroOperacionMasivaDao.modificar(registro);
		}
	}

	public ShvMasRegistro consultaSiebelClienteDebito(ShvMasRegistro registro) throws NegocioExcepcion {
		try {
			Traza.auditoria(this.getClass(), "* Consulta Cliente Debito: "+ registro.getClientesSiebel().getClienteDuenoDebito().toString() +" ---");

			ClienteBean clienteBean = clienteServicio.consultarCliente(registro.getClientesSiebel().getClienteDuenoDebito().toString());

			if (!Validaciones.isObjectNull(clienteBean)) {
				registro.getClientesSiebel().setRazonSocialClienteDebito(clienteBean.getRazonSocial());
				registro.getClientesSiebel().setCuitDebito(clienteBean.getCuit());
				
				if (!Validaciones.isNullEmptyOrDash(clienteBean.getCodigoHolding())) {
					registro.getClientesSiebel().setNroHoldingDebito(new Long(clienteBean.getCodigoHolding()));
				}
				registro.getClientesSiebel().setDescripcionHoldingDebito(clienteBean.getDescripcionHolding());
				
				if (!Validaciones.isNullEmptyOrDash(clienteBean.getAgenciaNegocio())) {
					registro.getClientesSiebel().setNroAgenciaNegocioDebito(new Long(clienteBean.getAgenciaNegocio()));
				}
				registro.getClientesSiebel().setDescripcionAgenciaNegocioDebito(clienteBean.getDescripcionAgenciaNegocio());
				if (!Validaciones.isObjectNull(clienteBean.getIdClienteLegado())) {
					registro.getClientesSiebel().setNroClientePerfilDebito(clienteBean.getIdClienteLegado());
				}
				registro.getClientesSiebel().setSegmentoAgenciaNegocioDebito(clienteBean.getSegmentoAgencia());
				registro.getClientesSiebel().setCodigoProvinciaDebito(clienteBean.getIdProvincia());
				registro.getClientesSiebel().setResultadoConsultaSiebelDebito(TipoResultadoEnum.OK);
			} else {
				throw new NegocioExcepcion("Cliente debito Inexistente ");
			}
		} catch (NegocioExcepcion e) {
			Traza.error(this.getClass(), e.getMessage() + " para el idRegistro "+registro.getIdRegistro());

			if (e.getCause() instanceof RuntimeException) {
				registro.getClientesSiebel().setResultadoConsultaSiebelDebito(TipoResultadoEnum.ERROR_SERVICIO);
			} else {
				registro.getClientesSiebel().setResultadoConsultaSiebelDebito(TipoResultadoEnum.ERROR);
				registro.getClientesSiebel().setDescripcionErrorSiebelDebito(e.getMessage());
				StringBuffer str = new StringBuffer();
				if (!Validaciones.isObjectNull(registro.getDescripcionErrorRegistro()) && !Validaciones.isEmptyString(registro.getDescripcionErrorRegistro())) {
					str.append(registro.getDescripcionErrorRegistro());
					str.append(" - ");
				}
				str.append("Debito: ");
				str.append(e.getMessage());
				registro.setDescripcionErrorRegistro(str.toString());
				throw new NegocioExcepcion(e.getMessage(), e);
			}
		}

		return registro;
	}

	public ShvMasRegistro consultaSiebelClienteCredito(ShvMasRegistro registro) throws NegocioExcepcion {
		try {
			Traza.auditoria(this.getClass(), "* Consulta Cliente Credito: "+ registro.getClientesSiebel().getClienteDuenoCredito().toString() +" ---");

			ClienteBean clienteBean = clienteServicio.consultarCliente(registro.getClientesSiebel().getClienteDuenoCredito().toString());

			if (!Validaciones.isObjectNull(clienteBean)) {
				registro.getClientesSiebel().setRazonSocialClienteCredito(clienteBean.getRazonSocial());
				registro.getClientesSiebel().setCuitCredito(clienteBean.getCuit());
				
				if (!Validaciones.isNullEmptyOrDash(clienteBean.getCodigoHolding())) {
					registro.getClientesSiebel().setNroHoldingCredito(new Long(clienteBean.getCodigoHolding()));
				}
				registro.getClientesSiebel().setDescripcionHoldingCredito(clienteBean.getDescripcionHolding());
				
				if (!Validaciones.isNullEmptyOrDash(clienteBean.getAgenciaNegocio())) {
					registro.getClientesSiebel().setNroAgenciaNegocioCredito(new Long(clienteBean.getAgenciaNegocio()));
				}
				registro.getClientesSiebel().setDescripcionAgenciaNegocioCredito(clienteBean.getDescripcionAgenciaNegocio());
				if (!Validaciones.isObjectNull(clienteBean.getIdClienteLegado())) {
					registro.getClientesSiebel().setNroClientePerfilCredito(new Long(clienteBean.getIdClienteLegado()));
				}
				registro.getClientesSiebel().setSegmentoAgenciaNegocioCredito(clienteBean.getSegmentoAgencia());
				registro.getClientesSiebel().setCodigoProvinciaCredito(clienteBean.getIdProvincia());
				registro.getClientesSiebel().setResultadoConsultaSiebelCredito(TipoResultadoEnum.OK);
			} else {
				throw new NegocioExcepcion("Cliente credito Inexistente ");
			}
			
		} catch (NegocioExcepcion e) {
			
			Traza.error(this.getClass(), e.getMessage() + "para el idRegistro "+registro.getIdRegistro());
			
			if (e.getCause() instanceof RuntimeException) {
				registro.getClientesSiebel().setResultadoConsultaSiebelCredito(TipoResultadoEnum.ERROR_SERVICIO);
			} else {
				registro.getClientesSiebel().setResultadoConsultaSiebelCredito(TipoResultadoEnum.ERROR);
				//registro.getClientesSiebel().setCodigoErrorSiebelCredito();
				registro.getClientesSiebel().setDescripcionErrorSiebelCredito(e.getMessage());
				StringBuffer str = new StringBuffer();
				if (!Validaciones.isObjectNull(registro.getDescripcionErrorRegistro()) && !Validaciones.isEmptyString(registro.getDescripcionErrorRegistro())) {
					
					str.append(registro.getDescripcionErrorRegistro());
					str.append(" - ");
				}
				str.append("Credito: ");
				str.append(e.getMessage());
				registro.setDescripcionErrorRegistro(str.toString());
				throw new NegocioExcepcion(e.getMessage(), e);
			}
		}
		
		return registro;
	}
	
	
	public ShvMasRegistro consultaSiebelClienteAcuerdo(ShvMasRegistro registro) throws NegocioExcepcion {
		try {
			Traza.auditoria(this.getClass(), "* Consulta Cliente Acuerdo: "+ registro.getClientesSiebel().getClienteDuenoAcuerdo().toString() +" ---");

			ClienteBean clienteBean = clienteServicio.consultarCliente(registro.getClientesSiebel().getClienteDuenoAcuerdo().toString());

			if (!Validaciones.isObjectNull(clienteBean)) {
				registro.getClientesSiebel().setRazonSocialClienteAcuerdo(clienteBean.getRazonSocial());
				registro.getClientesSiebel().setCuitAcuerdo(clienteBean.getCuit());
				
				if (!Validaciones.isNullEmptyOrDash(clienteBean.getCodigoHolding())) {
					registro.getClientesSiebel().setNroHoldingAcuerdo(new Long(clienteBean.getCodigoHolding()));
				}
				registro.getClientesSiebel().setDescripcionHoldingAcuerdo(clienteBean.getDescripcionHolding());
				
				if (!Validaciones.isNullEmptyOrDash(clienteBean.getAgenciaNegocio())) {
					registro.getClientesSiebel().setNroAgenciaNegocioAcuerdo(new Long(clienteBean.getAgenciaNegocio()));
				}
				registro.getClientesSiebel().setDescripcionAgenciaNegocioAcuerdo(clienteBean.getDescripcionAgenciaNegocio());
				if (!Validaciones.isObjectNull(clienteBean.getIdClienteLegado())) {
					registro.getClientesSiebel().setNroClientePerfilAcuerdo(new Long(clienteBean.getIdClienteLegado()));
				}
				registro.getClientesSiebel().setSegmentoAgenciaNegocioAcuerdo(clienteBean.getSegmentoAgencia());
				registro.getClientesSiebel().setCodigoProvinciaAcuerdo(clienteBean.getIdProvincia());
				registro.getClientesSiebel().setResultadoConsultaSiebelAcuerdo(TipoResultadoEnum.OK);
			}else {
				throw new NegocioExcepcion("Cliente acuerdo Inexistente ");
			}
			
		}catch (NegocioExcepcion e) {
			
			Traza.error(this.getClass(), e.getMessage() + "para el idRegistro "+ registro.getIdRegistro());
			
			if(e.getCause() instanceof RuntimeException) {
				registro.getClientesSiebel().setResultadoConsultaSiebelAcuerdo(TipoResultadoEnum.ERROR_SERVICIO);
			}else {
				registro.getClientesSiebel().setResultadoConsultaSiebelAcuerdo(TipoResultadoEnum.ERROR);
				//registro.getClientesSiebel().setCodigoErrorSiebelAcuerdo();
				registro.getClientesSiebel().setDescripcionErrorSiebelAcuerdo(e.getMessage());
				StringBuffer str = new StringBuffer();
				if (!Validaciones.isObjectNull(registro.getDescripcionErrorRegistro()) && !Validaciones.isEmptyString(registro.getDescripcionErrorRegistro())) {
					str.append(registro.getDescripcionErrorRegistro());
					str.append(" - ");
				}
				str.append("Acuerdo: ");
				str.append(e.getMessage());
				registro.setDescripcionErrorRegistro(str.toString());
				throw new NegocioExcepcion(e.getMessage(), e);
			}
		}
		
		return registro;
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * @param cobroDto
	 * @param cobroModelo
	 * @return
	 * @throws NegocioExcepcion
	 */
	public String obtenerObseHistorial(OperacionMasivaDto operacionMasivaDto, ShvMasOperacionMasiva operacionMasivaModelo) throws NegocioExcepcion {
		StringBuffer str = new StringBuffer();
		try {
			String usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
			String usuarioNombreBatch = parametroServicio.getValorTexto(Constantes.USUARIO_NOMBRE_BATCH);

			if (Validaciones.isObjectNull(operacionMasivaModelo)) {
				operacionMasivaModelo = this.buscarOperacionMasivaModelo(operacionMasivaDto.getIdOperacionMasiva());
			}
			List<ShvWfWorkflowEstadoHist> historial = operacionMasivaModelo.getWorkFlow().getListaWorkflowEstadoHistOrdenadaPorFecha();

			for (ShvWfWorkflowEstadoHist historico : historial) {
				str.append(this.returnObservacion(historico, usuarioBatch, usuarioNombreBatch));
			}

			if (!Validaciones.isObjectNull(operacionMasivaModelo.getWorkFlow().getShvWfWorkflowEstado())) {
				if (operacionMasivaModelo.getWorkFlow().getShvWfWorkflowEstado().iterator().hasNext()) {
//					if (str.length() > 0) {
//						str.append(Constantes.RETORNO_HTML);
//					}
					str.append(
						this.returnObservacion(
								operacionMasivaModelo.getWorkFlow().getWorkflowEstado(),
							usuarioBatch,
							usuarioNombreBatch
					));
				}
			}
			
		} catch (LdapExcepcion e) {
			throw new NegocioExcepcion(e); 
		}
		return str.toString();
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * @param wfEstado
	 * @param usuarioBatch
	 * @param usuarioNombreBatch
	 * @return
	 * @throws LdapExcepcion
	 */
	private String returnObservacion(ShvWfWorkflowEstado wfEstado,String usuarioBatch, String usuarioNombreBatch) throws LdapExcepcion {
		return this.returnObservacion(
			wfEstado.getDatosModificados(),
			wfEstado.getFechaModificacion(),
			wfEstado.getUsuarioModificacion(),
			usuarioBatch,
			usuarioNombreBatch
		);
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * @param wfEstado
	 * @param usuarioBatch
	 * @param usuarioNombreBatch
	 * @return
	 * @throws LdapExcepcion
	 */
	private String returnObservacion(ShvWfWorkflowEstadoHist wfEstado,String usuarioBatch, String usuarioNombreBatch) throws LdapExcepcion {
		return this.returnObservacion(
			wfEstado.getDatosModificados(),
			wfEstado.getFechaModificacion(),
			wfEstado.getUsuarioModificacion(),
			usuarioBatch,
			usuarioNombreBatch
		);
	}
	
	/**
	 * @author u572487 Guido.Settecerze
	 * @param datosModificados
	 * @param fecha
	 * @param usuarioModificacion
	 * @param usuarioBatch
	 * @param usuarioNombreBatch
	 * @return
	 * @throws LdapExcepcion
	 */
	private String returnObservacion(
		String datosModificados,
		Date fecha,
		String usuarioModificacion,
		String usuarioBatch,
		String usuarioNombreBatch
			) throws LdapExcepcion {
		StringBuffer str = new StringBuffer();
		boolean tieneObservacion = false;
		if(StringUtils.contains(datosModificados, "[Observaciones](Valor: ")){
			tieneObservacion = true;
			String replace = datosModificados.replace("[Observaciones](Valor: ", "");
			int lastIndexOf = replace.lastIndexOf(")");
			datosModificados =  replace.substring(0, lastIndexOf);
		}
		datosModificados = Utilidad.mostrarDatosModificados(
				datosModificados,
				Utilidad.DATOS_MODIFICADOS_SOLO_DATOS
				);

		if (!Validaciones.isNullOrEmpty(datosModificados)) {
			str.append(Utilidad.formatDateAndTimeFull(fecha));
			str.append(" (");
			if (usuarioBatch.equalsIgnoreCase(usuarioModificacion)) {
				str.append(usuarioBatch);
				str.append(") ");
				str.append(usuarioNombreBatch);
			} else {
				UsuarioLdapDto usuarioLdapAnalista = ldapServicio.buscarUsuarioPorUid(usuarioModificacion);
				if (usuarioLdapAnalista != null) {
					str.append(usuarioLdapAnalista.getTuid());
					str.append(") ");
					str.append(usuarioLdapAnalista.getNombreCompleto());
				}
			}
			str.append(Constantes.RETORNO_HTML);
			if (!Validaciones.isNullOrEmpty(datosModificados)) {
				if (!Validaciones.isNullOrEmpty(datosModificados)) {
					if(tieneObservacion){
						String[] split = datosModificados.split("<br>");
						if(split.length>=2){
							for( int i = 1; i <= split.length - 1; i++)
							{
								str.append(split[i]);
							}
						}else{
							str.append(datosModificados);
						}
					}else{
						str.append(datosModificados);
					}
				}
			}
			str.append(Constantes.RETORNO_HTML);
		}
		return str.toString();
	}
	
	private void crearTareaRegistrosEnError(ShvMasOperacionMasivaSimplificadoWorkFlow shv,BigDecimal imp,String nombreArchivo) throws NegocioExcepcion {
		
		TareaDto tarea = new TareaDto();
		
		ShvWfWorkflow workflow = shv.getWorkFlow();
		
		tarea.setTipoTarea(TipoTareaEnum.OP_MAS_ERROR);
		tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		tarea.setImporte(String.valueOf(imp));
		tarea.setUsuarioCreacion(shv.getIdAnalista());
		tarea.setIdItem(shv.getIdOperacionMasiva());
		tarea.setFechaCreacion(workflow.getFechaUltimaModificacion());
		tarea.setGestionaSobre(TipoTareaGestionaEnum.OPERACION_MASIVA);
		tarea.setRefBandeja(shv.getIdOperacionMasiva() + "/" + nombreArchivo);
		tarea.setEmpresa(shv.getEmpresa().getDescripcion());
		tarea.setSegmento(shv.getSegmento().getDescripcion());
		tarea.setIdOperacionMasiva(shv.getIdOperacionMasiva());
		tarea.setIdWorkflow(workflow.getIdWorkflow());
		tarea.setUsuarioAsignacion(workflow.getUsuarioAlta());
		
		tareaServicio.crearTareaOperacionMasiva(tarea);
	}
			
	private void crearTareaRegistrosProcesadosParcialmente(ShvMasOperacionMasivaSimplificadoWorkFlow shv,BigDecimal imp,String nombreArchivo) throws NegocioExcepcion {
		
		TareaDto tarea = new TareaDto();
		
		ShvWfWorkflow workflow = shv.getWorkFlow();
		
		tarea.setTipoTarea(TipoTareaEnum.OP_MAS_PARCIAL);
		tarea.setEstado(TipoTareaEstadoEnum.PENDIENTE);
		tarea.setImporte(String.valueOf(imp));
		tarea.setUsuarioAsignacion(workflow.getUsuarioAlta());
		tarea.setUsuarioCreacion(shv.getIdAnalista());
		tarea.setIdItem(shv.getIdOperacionMasiva());
		tarea.setFechaCreacion(workflow.getFechaUltimaModificacion());
		tarea.setGestionaSobre(TipoTareaGestionaEnum.OPERACION_MASIVA);
		tarea.setRefBandeja(shv.getIdOperacionMasiva() + "/" + nombreArchivo);
		tarea.setEmpresa(shv.getEmpresa().getDescripcion());
		tarea.setSegmento(shv.getSegmento().getDescripcion());
		tarea.setIdOperacionMasiva(shv.getIdOperacionMasiva());
		tarea.setIdWorkflow(shv.getWorkFlow().getIdWorkflow());
		
		tareaServicio.crearTareaOperacionMasiva(tarea);
		
		
	}
	

	public String generarArchivoOperacionMasivaRespuesta(Long idOperacionMasiva) throws NegocioExcepcion, PersistenciaExcepcion{
		String contenidoArchivo = null;
		ShvMasOperacionMasiva operacionMasivaModelo = buscarOperacionMasivaModelo(idOperacionMasiva);
		
		List<ShvMasRegistro> shvMasRegistros = registroOperacionMasivaDao.buscarRegistrosXOperacionMasivaDistintosAEstado(EstadoRegistroOperacionMasivaEnum.ERROR,operacionMasivaModelo.getIdOperacionMasiva());
		contenidoArchivo = operacionMasivaRegistroServicio.generarArchivoOperacionMasivaRespuesta(shvMasRegistros, operacionMasivaModelo.getTipoOperacionMasiva());
		return contenidoArchivo;
	}
	@Override
	public void enviarMailRta(OperacionMasivaBatchEmailEnum tipoMail, String ... args) throws NegocioExcepcion {
		Mail mail = null;
		Traza.auditoria(getClass(), "Se envia un mail");
		String mensajeCuerpo = null;
		try {
			if (args.length > 1) {
				//List<Class<String>> argumentosTipo = new ArrayList<Class<String>>();
				//List<String> parametros = new ArrayList<String>();
				//parametros.add(Propiedades.MENSAJES_PROPIEDADES.getString(tipoMail.cuerpo));
				//argumentosTipo.add(String.class);
				//for(String ar :args) {
				//	argumentosTipo.add(String.class);
				//	parametros.add(ar);
				//}
//				Method metodo = Utilidad.class.getMethod("reemplazarMensajes", argumentosTipo.toArray(new Class[argumentosTipo.size()]));
//				mensajeCuerpo = (String) metodo.invoke(Utilidad.class, parametros.toArray(new String[parametros.size()]));
				Traza.auditoria(getClass(), "Se reemplaza el asunto");
				mensajeCuerpo = Utilidad.reemplazarMensajes(
					Propiedades.MENSAJES_PROPIEDADES.getString(tipoMail.cuerpo),
					args
				);
			} else {
				mensajeCuerpo = Propiedades.MENSAJES_PROPIEDADES.getString(tipoMail.cuerpo);
			}
			mensajeCuerpo = Utilidad.formateadoMail(mensajeCuerpo);
			StringBuffer cuerpoMail = new StringBuffer(mensajeCuerpo);
			mail = new Mail(
				parametroDao.getValorTexto(tipoMail.to),
				"",
				"",
				Propiedades.MENSAJES_PROPIEDADES.getString(tipoMail.asunto),
				cuerpoMail
			);
			mailServicio.sendMail(mail);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion("No se pudo enviar el eMail [" + tipoMail.asunto +"]", e);
		} 
//		catch (NoSuchMethodException e) {
//			// TODO Auto-generated catch block
//			throw new NegocioExcepcion("No se pudo enviar el eMail [" + tipoMail.asunto +"]", e);
//		} catch (SecurityException e) {
//			// TODO Auto-generated catch block
//			throw new NegocioExcepcion("No se pudo enviar el eMail [" + tipoMail.asunto +"]", e);
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			throw new NegocioExcepcion("No se pudo enviar el eMail [" + tipoMail.asunto +"]", e);
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			throw new NegocioExcepcion("No se pudo enviar el eMail [" + tipoMail.asunto +"]", e);
//		} catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			throw new NegocioExcepcion("No se pudo enviar el eMail [" + tipoMail.asunto +"]", e);
//		} 
	}
	@Override
	public ImportarOperacionesMasivasAuxiliar validarRegistros(OperacionMasivaDto operacionMasiva) throws NegocioExcepcion {
		int nroRegistro = 0;
		int nroDeErrores = 0;
		boolean errorEnCampos = false;
		String[] registros = null;
		File file = ControlArchivo.convert(operacionMasiva.getFileBytes(), operacionMasiva.getFileOperacionMasiva());

		try {
			registros = ControlArchivo.leerArchivo(file.getPath(), Constantes.RETORNO_UNIX);
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}

		ImportarOperacionesMasivasAuxiliar importarOperacionesMasivasAuxiliar = new ImportarOperacionesMasivasAuxiliar();

		switch (operacionMasiva.getTipoOperacionMasiva()) {
			case DEUDA:
				for(String reg : registros) {
					if (nroDeErrores <= 500) {
						String[] campos = this.obtenerCampos(reg);
						errorEnCampos = validarCamposAplicarDeuda(campos, ++nroRegistro, importarOperacionesMasivasAuxiliar);
						if (errorEnCampos) {
							nroDeErrores++;
							Traza.error(getClass(), importarOperacionesMasivasAuxiliar.getResultadoValidaciones().toString());
						}
					} else {
						importarOperacionesMasivasAuxiliar.getResultadoValidaciones().append(EXISTEN_MAS_ERRORES_PERO_NO_SERAN_MOSTRADOS);
						break;
					}
				}
				break;
			case DSIST:
				for(String reg : registros) {
					if (nroDeErrores <= 500) {
						String[] campos = this.obtenerCampos(reg);
						errorEnCampos = validarCamposDesistimiento(campos, ++nroRegistro, importarOperacionesMasivasAuxiliar);
						if (errorEnCampos) {
							nroDeErrores++;
							Traza.error(getClass(), importarOperacionesMasivasAuxiliar.getResultadoValidaciones().toString());
						}
					} else {
						importarOperacionesMasivasAuxiliar.getResultadoValidaciones().append(EXISTEN_MAS_ERRORES_PERO_NO_SERAN_MOSTRADOS);
						break;
					}
				}
				break;
			case GNCIA:
				for(String reg : registros) {
					if (nroDeErrores <= 500) {
						String[] campos = this.obtenerCampos(reg);
						errorEnCampos = validarCamposGanacias(campos, ++nroRegistro, importarOperacionesMasivasAuxiliar);
						if (errorEnCampos) {
							nroDeErrores++;
							Traza.error(getClass(), importarOperacionesMasivasAuxiliar.getResultadoValidaciones().toString());
						}
					} else {
						importarOperacionesMasivasAuxiliar.getResultadoValidaciones().append(EXISTEN_MAS_ERRORES_PERO_NO_SERAN_MOSTRADOS);
						break;
					}
				}
				break;
			case REINT:
				for(String reg : registros) {
					if (nroDeErrores <= 500) {
						String[] campos = this.obtenerCampos(reg);
						errorEnCampos = validarCamposReintegro(campos, ++nroRegistro, importarOperacionesMasivasAuxiliar);
						if (errorEnCampos) {
							nroDeErrores++;
							Traza.error(getClass(), importarOperacionesMasivasAuxiliar.getResultadoValidaciones().toString());
						}
					} else {
						importarOperacionesMasivasAuxiliar.getResultadoValidaciones().append(EXISTEN_MAS_ERRORES_PERO_NO_SERAN_MOSTRADOS);
						break;
					}
				}
				break;
			default:
				break;
		}

		//operacionMasiva.setResultadoValidaciones(importarOperacionesMasivasAuxiliar.getResultadoValidaciones().toString());

		return importarOperacionesMasivasAuxiliar;
	}
	
	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/
//	
	public IOperacionMasivaDao getOperacionMasivaDao() {
		return operacionMasivaDao;
	}
	
	public void setOperacionMasivaDao(IOperacionMasivaDao operacionMasivaDao) {
		this.operacionMasivaDao = operacionMasivaDao;
	}

	public RegistroOperacionMasivaMapeador getRegistroOperacionMasivaMapeador() {
		return registroOperacionMasivaMapeador;
	}

	public void setRegistroOperacionMasivaMapeador(
			RegistroOperacionMasivaMapeador registroOperacionMasivaMapeador) {
		this.registroOperacionMasivaMapeador = registroOperacionMasivaMapeador;
	}

	public ArchivoOperacionMasivaMapeador getArchivoOperacionMasivaMapeador() {
		return archivoOperacionMasivaMapeador;
	}

	public void setArchivoOperacionMasivaMapeador(
			ArchivoOperacionMasivaMapeador archivoOperacionMasivaMapeador) {
		this.archivoOperacionMasivaMapeador = archivoOperacionMasivaMapeador;
	}

	public IRegistroOperacionMasivaDao getRegistroOperacionMasivaDao() {
		return registroOperacionMasivaDao;
	}

	public void setRegistroOperacionMasivaDao(
			IRegistroOperacionMasivaDao registroOperacionMasivaDao) {
		this.registroOperacionMasivaDao = registroOperacionMasivaDao;
	}

	public IWorkflowOperacionesMasivas getWorkflowOperacionesMasivas() {
		return workflowOperacionesMasivas;
	}

	public void setWorkflowOperacionesMasivas(
			IWorkflowOperacionesMasivas workflowOperacionesMasivas) {
		this.workflowOperacionesMasivas = workflowOperacionesMasivas;
	}

	public IOperacionMasivaAdjuntoDao getOperacionMasivaAdjuntoDao() {
		return operacionMasivaAdjuntoDao;
	}

	public void setOperacionMasivaAdjuntoDao(
			IOperacionMasivaAdjuntoDao operacionMasivaAdjuntoDao) {
		this.operacionMasivaAdjuntoDao = operacionMasivaAdjuntoDao;
	}

	public IArchivoOperacionMasivaDao getArchivoOperacionMasivaDao() {
		return archivoOperacionMasivaDao;
	}

	public void setArchivoOperacionMasivaDao(
			IArchivoOperacionMasivaDao archivoOperacionMasivaDao) {
		this.archivoOperacionMasivaDao = archivoOperacionMasivaDao;
	}

	public IDocumentoAdjuntoDao getDocumentoAdjuntoDao() {
		return documentoAdjuntoDao;
	}

	public void setDocumentoAdjuntoDao(IDocumentoAdjuntoDao documentoAdjuntoDao) {
		this.documentoAdjuntoDao = documentoAdjuntoDao;
	}

	public OperacionMasivaMapeador getOperacionMasivaMapeador() {
		return operacionMasivaMapeador;
	}

	public void setOperacionMasivaMapeador(
			OperacionMasivaMapeador operacionMasivaMapeador) {
		this.operacionMasivaMapeador = operacionMasivaMapeador;
	}

	public ITareaServicio getTareaServicio() {
		return tareaServicio;
	}

	public void setTareaServicio(ITareaServicio tareaServicio) {
		this.tareaServicio = tareaServicio;
	}

	public OperacionesMasivasVistaMapeador getOperacionMasivaVistaMapeo() {
		return operacionMasivaVistaMapeo;
	}

	public void setOperacionMasivaVistaMapeo(
			OperacionesMasivasVistaMapeador operacionMasivaVistaMapeo) {
		this.operacionMasivaVistaMapeo = operacionMasivaVistaMapeo;
	}

}