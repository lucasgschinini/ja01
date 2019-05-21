package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.TipoAcuerdoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.base.ws.cliente.servicios.IClienteSiebelServicio;
import ar.com.telecom.shiva.negocio.batch.bean.DepositoBatch;
import ar.com.telecom.shiva.negocio.batch.bean.InterdepositoBatch;
import ar.com.telecom.shiva.negocio.batch.bean.TransferenciaBatch;
import ar.com.telecom.shiva.negocio.batch.mapeos.DepositoMapeadorBatch;
import ar.com.telecom.shiva.negocio.batch.mapeos.InterdepositoMapeadorBatch;
import ar.com.telecom.shiva.negocio.batch.mapeos.TransferenciaMapeadorBatch;
import ar.com.telecom.shiva.negocio.dto.ArchivoAVCDto;
import ar.com.telecom.shiva.negocio.dto.DepositoDto;
import ar.com.telecom.shiva.negocio.dto.RegistroAVCDto;
import ar.com.telecom.shiva.negocio.mapeos.BoletaSinValorMapeador;
import ar.com.telecom.shiva.negocio.mapeos.ConciliacionSugeridaMapeador;
import ar.com.telecom.shiva.negocio.servicios.IArchivoAVCServicio;
import ar.com.telecom.shiva.negocio.servicios.IArchivoAVCSoporteServicio;
import ar.com.telecom.shiva.negocio.servicios.IBoletaSinValorServicio;
import ar.com.telecom.shiva.negocio.servicios.IClienteServicio;
import ar.com.telecom.shiva.negocio.servicios.IConciliacionServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IRegistroAVCServicio;
import ar.com.telecom.shiva.negocio.servicios.ITeamComercialServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;
import ar.com.telecom.shiva.negocio.servicios.IVistaSoporteServicio;
import ar.com.telecom.shiva.negocio.servicios.bean.ClienteBean;
import ar.com.telecom.shiva.negocio.servicios.validacion.IProcesarArchivoValidacionServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaValor;
import ar.com.telecom.shiva.persistencia.dao.IBancoClienteDao;
import ar.com.telecom.shiva.persistencia.dao.IBoletaDao;
import ar.com.telecom.shiva.persistencia.dao.IConciliacionDao;
import ar.com.telecom.shiva.persistencia.dao.IGenericoDao;
import ar.com.telecom.shiva.persistencia.dao.IRegistroAVCDao;
import ar.com.telecom.shiva.persistencia.dao.IValorDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcArchivoAvc;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvc;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvcBoleta;
import ar.com.telecom.shiva.persistencia.modelo.ShvBolBoleta;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBancoCliente;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.BoletaSinValorDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ConciliacionSugeridaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.TeamComercialDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValoresDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.BoletaSinValorFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaValoresFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteRegistrosAvcSinConciliarFiltro;

public class ConciliacionServicioImpl extends Servicio implements IConciliacionServicio{

	@Autowired 
	private IProcesarArchivoValidacionServicio procesarArchivoValidacionServicio;
	
	@Autowired
	private IArchivoAVCServicio archivoAVCServicio;
	
	@Autowired
	private IArchivoAVCSoporteServicio archivoAVCSoporteServicio;
	
	@Autowired
	private IParametroServicio parametroServicio;
	
	@Autowired
	private IRegistroAVCServicio registroAVCServicio;
	
	@Autowired
	private IBoletaSinValorServicio boletaSinValorServicio;
	
	@Autowired
	private IValorServicio valorServicio;

	@Autowired
	private BoletaSinValorMapeador boletaSinValorMapeador;

	@Autowired
	private DepositoMapeadorBatch depositoMapeadorBatch;
	
	@Autowired
	private InterdepositoMapeadorBatch interdepositoMapeadorBatch;
	
	@Autowired
	private TransferenciaMapeadorBatch transferenciaMapeadorBatch;
	
	@Autowired
	private IConciliacionDao conciliacionDao;
	
	@Autowired
	private IRegistroAVCDao registroAVCDao;
	
	@Autowired
	private IGenericoDao genericoDao;
	
	@Autowired
	private IBoletaDao boletaDao;
	
	@Autowired
	private IValorDao valorDao;
	
	@Autowired
	IBancoClienteDao bancoClienteDao;
	
	@Autowired
	private IClienteSiebelServicio siebelServicio;
	
	@Autowired
	MailServicio mailServicio;
	
	@Autowired
	ITeamComercialServicio teamComercialServicio;
	
	private static final String CARPETA_HISTORICO = "historico";
	private int cantRegistro;
	
	@Autowired
	ConciliacionSugeridaMapeador conciliacionSugeridaMapeo;
	@Autowired
	private IClienteServicio clienteServicio;
	
	
	/**
	 * Procesa los archivos de registros AVC. 
	 */
	public boolean procesarArchivosRegistrosAVC() throws NegocioExcepcion {
		String pathOrigen = Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.archivosRegistrosAvc");
		boolean todosBienProcesados = true;
		
		try {
			File[] archivosAProcesar = ControlArchivo.listaArchivosDirectorio(pathOrigen);
			boolean hayArchivosAProcesar = false;
			if (archivosAProcesar != null && archivosAProcesar.length > 0) {
				for (File archivo : archivosAProcesar) {
					if (archivo.isFile()) {
						hayArchivosAProcesar = true;
						break;
					}
				}
			}
			if (!hayArchivosAProcesar) {
				Traza.advertencia(getClass(), "No hay archivos AVC para procesar...");
			}
	    	List<ShvParamBancoCliente> listaBancoCliente;
			try {
				listaBancoCliente = bancoClienteDao.buscarBancoCliente();
			} catch (PersistenciaExcepcion e) {
				throw new NegocioExcepcion(e.getMessage(),e);
			}
			
			// Recorro todos los archivos existentes en el directorio
			for (File archivo : archivosAProcesar) {
				
				try {
					
					if (Validaciones.esNombreArchivoRegistroAVC(archivo.getName())) {
						ShvAvcArchivoAvc archivoAux = archivoAVCSoporteServicio.buscarArchivoAvcPorNombreArchivo(archivo.getName());
						
						if(!Validaciones.isObjectNull(archivoAux)){
							archivoAVCSoporteServicio.enviarMailArchivoProcesadoAnteriormente(archivoAux);
							
							System.out.println("Ya fue procesado anteriormente el archivo " + archivo.getName());
							Traza.advertencia(this.getClass(), "Ya fue procesado anteriormente el archivo " + archivo.getName());
							
						} else {
							Boolean grabarRegistros = true;
							Traza.auditoria(this.getClass(), "Procesando el archivo " + archivo.getName());
							String[] arrayNombreArchivo = archivo.getName().split("_");
							String acuerdo = arrayNombreArchivo[1];
							String secuencialArchivo = arrayNombreArchivo[3].replace(Constantes.ARCHIVO_CSV, "");
							List<RegistroAVCDto> listaRegistrosAVC = new ArrayList<RegistroAVCDto>();
							String logProcesamiento = "";
							
					    	String[] lineas = ControlArchivo.leerArchivo(archivo.getCanonicalPath(), Constantes.RETORNO_UNIX);

					    	if (!Validaciones.isObjectNull(lineas)){
						    	Traza.auditoria(this.getClass(), "Se han detectado '" + lineas.length + "' lineas en el archivo en cuestion.");
						    	
						    	// Comienzo desde la lineas[1] por la linea de cabecera.
						    	// Recorro hasta la linea "LENGTH-2" para no leer la ultima linea de totales.
						    	for(int i = 1;i<lineas.length;i++){
						    		// Recorrer cada linea
						    		try {
						    			RegistroAVCDto dto = this.procesarArchivo(i, lineas, acuerdo, secuencialArchivo, listaBancoCliente);
						    			if (!Validaciones.isObjectNull(dto)) {
						    				logProcesamiento += dto.getLogCaractEspecRemovidos();
						    				listaRegistrosAVC.add(dto);
						    			}
						    		} catch(ValidacionExcepcion e) {
						    			if (e.getCodigoLeyenda().equals(Constantes.NO_GRABAR_REGISTROS_AVC)){
						    				grabarRegistros = false;
						    			}
						    			logProcesamiento += e.getLeyenda();
						    		}
								}
								
						    	if(Validaciones.isNullOrEmpty(logProcesamiento)){
						    		logProcesamiento = Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.archivo.procesado.correctamente");
						    	}
						    	
						    	Traza.auditoria(this.getClass(), "Resultado (" + archivo.getName() + ") : " + logProcesamiento);
					    	} else {
					    		Traza.auditoria(this.getClass(), Mensajes.CUERPO_MAIL_ARCHIVO_AVC_VACIO + " '" + archivo.getName() + "' esta vacio.");
					    		logProcesamiento = Mensajes.CUERPO_MAIL_ARCHIVO_AVC_VACIO + " '" + archivo.getName() + "' esta vacio.";
					    	}

						    	// Mapeo e inserto el archivo
					        ArchivoAVCDto archivoAVC = new ArchivoAVCDto();
					        archivoAVC.setLogProcesamiento(logProcesamiento);
					        archivoAVC.setFechaProcesamiento(new Date());
					        archivoAVC.setNombreArchivo(archivo.getName());
					        archivoAVC.setUsuarioProcesamiento(parametroServicio.getValorTexto(Constantes.USUARIO_BATCH));
					        archivoAVC.setNroAcuerdo(acuerdo);
							archivoAVC.setArchivoAdjunto(Files.readAllBytes(archivo.toPath()));
					        
							// subo el archivo como documento adjunto
   							ShvAvcArchivoAvc archivoAvcInsertado = archivoAVCServicio.crearArchivoConRegistrosAVC(archivoAVC, (grabarRegistros)?listaRegistrosAVC:null);
					        
							// enviar mail con el procesamiento del archivo 
					        archivoAVCServicio.enviarMailConProcesamientoArchivoAVC(archivoAvcInsertado, logProcesamiento,grabarRegistros, new Long(lineas.length));
					        
					        System.out.println("Se ha procesado el archivo " + archivo.getName());
					        Traza.auditoria(this.getClass(), "Se ha procesado el archivo " + archivo.getName());
						}
					} else {
						
						if (!CARPETA_HISTORICO.equals(archivo.getName())){
							// El nombre del archivo es incorrecto
							// Envio un mail con el nombre incorrecto.
							archivoAVCSoporteServicio.enviarMailPorNombreIncorrectoArchivoAVC(archivo.getName());
							
							System.out.println("El archivo " + archivo.getName() + " es incorrecto y no lo puede procesar.");
					        Traza.advertencia(this.getClass(), "Se ha informado que el archivo " + archivo.getName() + " es incorrecto y no lo puede procesar.");
						}
					}
					
				} catch (Exception e) {
					todosBienProcesados = false;
					
					// El nombre del archivo es incorrecto
					// Envio un mail con el nombre incorrecto.
					archivoAVCServicio.enviarMailPorErrorProcesoArchivoAVC(archivo.getName());
					
					System.err.println(Utilidad.getStackTrace(e));
					Traza.error(getClass(), "Se ha producido el error de aplicacion al procesar el archivo " + archivo.getName(), e);
				}
				
				try {
					if (!CARPETA_HISTORICO.equals(archivo.getName())){
						ControlArchivo.moverArchivoACarpetaHistorico(archivo.getName(),pathOrigen);
					}
		        } catch (NegocioExcepcion e) {
		        	System.err.println(Utilidad.getStackTrace(e));
					Traza.error(getClass(), "No se ha podido mover el archivo " + archivo.getName() + " a la carpeta de historicos", e);
		        }
			}
			
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} 
		
		return todosBienProcesados;
	}

	/**
	 * Procesa una linea de un archivo AVC mapeando y validando a un bean Batch. 
	 * Luego mapea ese bean batch a un bean Dto.
	 * @param i numero de linea a procesar
	 * @param lineas
	 * @param acuerdo
	 * @return
	 * @throws NegocioExcepcion 
	 */
	private RegistroAVCDto procesarArchivo(int i, String[] lineas, String acuerdo, String secuencialArchivo, List<ShvParamBancoCliente> listaBancoCliente) throws NegocioExcepcion {
		RegistroAVCDto registro = null;
		if(esDeposito(acuerdo)){
			// Deposito
			// Validar linea
			DepositoBatch deposito = procesarArchivoValidacionServicio.procesarDepositos(String.valueOf(i), lineas[i],acuerdo);
			if(!Validaciones.isObjectNull(deposito.getImporte())){
				
				// Mapear Batch al DTO
				registro = (RegistroAVCDto) depositoMapeadorBatch.map(deposito);
			} else {
				String log = procesarArchivoValidacionServicio.agregarAlLogError(String.valueOf(i), Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.ImporteNegativo"));
				throw new ValidacionExcepcion(Constantes.GRABAR_REGISTROS_AVC,log);
			}
		} else if (esInterdeposito(acuerdo)){
			// Interdeposito
			// Validar linea
			InterdepositoBatch interdeposito = procesarArchivoValidacionServicio.procesarInterdepositos(String.valueOf(i), lineas[i],acuerdo, listaBancoCliente, secuencialArchivo);
			if(!Validaciones.isObjectNull(interdeposito.getImporte())){
				// Mapear Batch al DTO
				if(TipoAcuerdoEnum.INTERDEPOSITO_6.descripcion().equalsIgnoreCase(interdeposito.getAcuerdo())){
					registro = (RegistroAVCDto) interdepositoMapeadorBatch.map(interdeposito);
				} else {
					if(TipoAcuerdoEnum.INTERDEPOSITO_94.descripcion().equalsIgnoreCase(interdeposito.getAcuerdo())){
						//seteo el numero de comprobante
						interdeposito.setComprobante(
								 Utilidad.formatDateAAMMDD(new Date())
								+Utilidad.rellenarCerosIzquierda(secuencialArchivo, 2)
								+Utilidad.rellenarCerosIzquierda(String.valueOf(i), 4));
					}
					//mapeo la transferencia
					registro = (RegistroAVCDto) transferenciaMapeadorBatch.map(interdeposito);
				}
			} else {
				String log = procesarArchivoValidacionServicio.agregarAlLogError(String.valueOf(i), Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.ImporteNegativo"));
				throw new ValidacionExcepcion(Constantes.GRABAR_REGISTROS_AVC,log);
			}
		} else {
			// Transferencia
			// Recorro hasta la linea "LENGTH-1" para no leer la ultima linea de totales.
			if(i!=lineas.length-1){
				TransferenciaBatch transferencia = procesarArchivoValidacionServicio.procesarTransferencias(String.valueOf(i), lineas[i],acuerdo, listaBancoCliente);
				if(!Validaciones.isObjectNull(transferencia.getImporte())){
					// Mapear Batch al DTO
					registro = (RegistroAVCDto) transferenciaMapeadorBatch.map(transferencia);
				} else {
					String log = procesarArchivoValidacionServicio.agregarAlLogError(String.valueOf(i), Propiedades.MENSAJES_PROPIEDADES.getString("conciliacion.validacion.ImporteNegativo"));
					throw new ValidacionExcepcion(Constantes.GRABAR_REGISTROS_AVC,log);
				}
			}
		}
		
		if (!Validaciones.isObjectNull(registro)) {
			if (!Validaciones.isObjectNull(registro.getCodigoCliente())) {
				ClienteBean clienteBean = this.clienteServicio.consultarCliente(registro.getCodigoCliente());

				if (!Validaciones.isObjectNull(clienteBean)) {
					registro.setRazonSocialClientePerfil(clienteBean.getRazonSocialClienteAgrupador());
					if (!Validaciones.isNullOrEmpty(clienteBean.getIdClienteAgrupador())) {
						registro.setIdClientePerfil(Long.valueOf(clienteBean.getIdClienteAgrupador()));
					}
				}
			}
		}
		return registro;
		
	}
	
	/**
	 * Retorna true si el acuerdo que recibe por parametro coincide con uno de los cuatro acuerdos de 
	 * depositos (28,29,33 o 34).
	 * @param acuerdo
	 * @return
	 */
	public static boolean esDeposito(String acuerdo) {
		if(TipoAcuerdoEnum.ACUERDO_28.descripcion().equalsIgnoreCase(acuerdo) || 
				TipoAcuerdoEnum.ACUERDO_29.descripcion().equalsIgnoreCase(acuerdo)||
				TipoAcuerdoEnum.ACUERDO_33.descripcion().equalsIgnoreCase(acuerdo)||
				TipoAcuerdoEnum.ACUERDO_34.descripcion().equalsIgnoreCase(acuerdo)){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Retorna true si el acuerdo que recibe por parametro coincide con uno de los cuatro acuerdos de 
	 * interdepositos (6,87 o 94).
	 * @param acuerdo
	 * @return
	 */
	public static boolean esInterdeposito(String acuerdo) {
		if(TipoAcuerdoEnum.INTERDEPOSITO_6.descripcion().equalsIgnoreCase(acuerdo) || 
				TipoAcuerdoEnum.INTERDEPOSITO_94.descripcion().equalsIgnoreCase(acuerdo) ||
				TipoAcuerdoEnum.INTERDEPOSITO_87.descripcion().equalsIgnoreCase(acuerdo)){
			return true;
		}else{
			return false;
		}
	}

	
	@Autowired
	private IVistaSoporteServicio vistaSoporteServicio;
	
	public IVistaSoporteServicio getVistaSoporteServicio() {
		return vistaSoporteServicio;
	}


	public void setVistaSoporteServicio(IVistaSoporteServicio vistaSoporteServicio) {
		this.vistaSoporteServicio = vistaSoporteServicio;
	}
	
	@SuppressWarnings("unchecked")
	public List<ConciliacionSugeridaDto> listarConciliacionesSugeridas() throws NegocioExcepcion {
		
		try {
			Collection<ShvAvcRegistroAvcBoleta> listaConciliacionesModelo = conciliacionDao.listarConciliacionesSugeridas();
			
			if (Validaciones.isCollectionNotEmpty(listaConciliacionesModelo)){
				VistaSoporteBusquedaValoresFiltro filtro = new VistaSoporteBusquedaValoresFiltro();
				for(ShvAvcRegistroAvcBoleta registroAvc : listaConciliacionesModelo){
					filtro.getListaBoletas().add(registroAvc.getShvAvcRegistroAvcBoletaPk().getBoleta().getIdBoleta().toString());
				}
				Map<Long, VistaSoporteResultadoBusquedaValor> resultado = vistaSoporteServicio.getListaValoresPorIdsBoletas(filtro);
	
				//Guardo el resultado al mapeo
				conciliacionSugeridaMapeo.setObjeto(resultado);
				
				//Empiezo a mapear las conciliaciones sugeridas
				List<ConciliacionSugeridaDto> listaConciliacionDTO = (List<ConciliacionSugeridaDto>) conciliacionSugeridaMapeo.map(listaConciliacionesModelo);
				Collections.sort(listaConciliacionDTO, new Comparator<ConciliacionSugeridaDto>(){
	
					public int compare(ConciliacionSugeridaDto o1, ConciliacionSugeridaDto o2) {
						return o2.getFechaOrdenamiento().compareTo(o1.getFechaOrdenamiento());
					}
					
				});
				
				return listaConciliacionDTO;
			} else {
				return new ArrayList<ConciliacionSugeridaDto>();
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public List<RegistroAVCDto> listarRegistrosAvcSinConciliar() throws NegocioExcepcion {
		VistaSoporteRegistrosAvcSinConciliarFiltro filtro = new VistaSoporteRegistrosAvcSinConciliarFiltro();
		List<String> estados = new ArrayList<String>();
		estados.add(Estado.AVC_PENDIENTE.toString());
		estados.add(Estado.AVC_ALTA_VALOR_RECHAZADA.toString());
		filtro.setListaEstados(estados);
		return registroAVCServicio.listarRegistrosAVCSinConciliar(filtro, false);
	}

	/**
	 * Confirma todas las conciliaciones sugeridas que recibe en la lista.
	 * Por lo tanto, la boleta y el registro AVC de cada conciliacion sugerida pasan a estado
	 * CONCILIADO.
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void confirmarConciliacionesSugeridas(String[] listaConciliacionesSugeridas, String usuarioModificacion) throws NegocioExcepcion, PersistenciaExcepcion {
		
		HashMap<String, List<String>> cuerpoMailMap = new HashMap<String, List<String>>();
		setNumero(listaConciliacionesSugeridas.length);
		
		String asunto = Constantes.EMPTY_STRING;
		
		for (String conciliacionSugerida : listaConciliacionesSugeridas) {
			DepositoDto registroAvc = (DepositoDto)registroAVCServicio.buscarRegistroAVC(conciliacionSugerida.split("-")[0]);
			ShvBolBoleta boleta = boletaDao.buscarBoleta(Long.valueOf(conciliacionSugerida.split("-")[1]));
			
			// seteo el objeto BoletaSinValorDto
			BoletaSinValorDto boletaDto;
			if(Validaciones.isObjectNull(boleta.getBoletaConValor())){
				boletaDto = (BoletaSinValorDto)boletaSinValorMapeador.map(boleta.getBoletaSinValor());
			}else{
//				TODO reemplazar con vista de valores
				Long idValor = valorDao.obtenerIdValorAsociadoABoleta(boleta.getIdBoleta());
				boletaDto = boletaSinValorMapeador.mapearBoletaConValor(valorDao.buscarBoletaConValor(idValor));
			}
			boletaDto.setNumeroBoleta(String.valueOf(boleta.getNumeroBoleta()));
			
			registroAvc.setUsuarioModificacion(usuarioModificacion);
			boletaDto.setUsuarioModificacion(usuarioModificacion);
			
			// registro -> CONCILIADO CON DIFERENCIAS
			registroAVCServicio.establecerRegistroConciliadoConDiferencia(registroAvc,boletaDto.getCodCliente());

			BoletaSinValorFiltro filtro = new BoletaSinValorFiltro();
			filtro.setIdBoleta(boletaDto.getIdBoleta().toString());
			
			if (!Validaciones.isCollectionNotEmpty(boletaSinValorServicio.listar(filtro))) {
			
				// boleta con valor-> CONCILIADO CON DIFERENCIA
				// a la boleta y al valor los cambio de estados
				ShvValValor valor = valorServicio.establecerValorConciliadoConDiferencia(boletaDto, usuarioModificacion,registroAvc); 
				/**
				 * @author u573005, sprint3, se reutiliza el metodo
				 * de valorServicio
				 */
				valorServicio.mailValorConciliacion(valor, cuerpoMailMap);
				asunto = "Conciliacion" + mailServicio.armarAsuntoValorStringBuffer(valor).toString();
			} else {
				//igualo el registro con la boleta sigun corresponda
				igualarDatosRegistroAVCyBoleta(registroAvc,boletaDto);
				
				// boleta sin valor-> CONCILIADO
				boletaSinValorServicio.establecerRegistroConciliadoConDiferencia(boletaDto);
				
				// crea ValorDto
				// Si no tiene valor asociado lo creo
				ValorDto valorDto = crearValorDtoApartirDeRegistroAvcYBoleta(registroAvc,boletaDto);
				valorDto.setGenerarValorDispoblePorDefecto(true);
				valorDto.setUsuarioModificacion(usuarioModificacion);
				valorDto.setFechaDisponible(Utilidad.formatDateAndTime(new Date()));
				/**
				 * @author u562163, IM0543878, se agrega que se pise la fecha vencimiento del registro al valor.
				 */
				valorDto.setFechaVencimiento(Utilidad.formatDateAndTime(registroAvc.getFechaVencimiento()));
				valorDto.setMigracion(true);
				
				// esto debo hacerlo porque el metodo de validar duplicidad recibe estos parametros
				ValoresDto valoresDto = new ValoresDto();
				valoresDto.getListaValoresDto().add(valorDto);
				UsuarioSesion usuarioSesion = new UsuarioSesion(usuarioModificacion, usuarioModificacion, null);
				if (!valorServicio.validarDuplicidadAlta(valoresDto , usuarioSesion)) {
					Long idValor = valorServicio.crear(valorDto);
					ShvValValor valorBBDD = valorDao.buscarBoletaConValor(idValor);
					
					Date fechaDepositoRegistroAvc = registroAvc.getFechaPago();
					//seteo la fecha deposito
					if (!Validaciones.isObjectNull(valorBBDD.getValorBoletaDepositoCheque())) {
						valorBBDD.getValorBoletaDepositoCheque().setFechaDeposito(fechaDepositoRegistroAvc);
					} else {
						if (!Validaciones.isObjectNull(valorBBDD.getValorBoletaDepositoChequePD())) {
							valorBBDD.getValorBoletaDepositoChequePD().setFechaDeposito(fechaDepositoRegistroAvc);
						} else { 
							if (!Validaciones.isObjectNull(valorBBDD.getValorBoletaEfectivo())) {
								valorBBDD.getValorBoletaEfectivo().setFechaDeposito(fechaDepositoRegistroAvc);
							}
						}
					}
					valorDto.setFechaValor(valorServicio.calcularFechaValor(valorBBDD,fechaDepositoRegistroAvc));
					valorDao.actualizarValor(valorBBDD);
					
					valorDto.setNumeroValor(valorBBDD.getNumeroValor());
					//obtengo el id del valor que acabo de crear. Lo hago asi porque el metodo
					//crear de valorServicio, no siempre te devuelve el id de valor
					
					//enviarMailValorDisponible(valorDto);
					/**
					 * @author u573005, sprint3, se reutiliza el metodo
					 * de valorServicio
					 */
					valorServicio.mailValorConciliacion(valorBBDD, cuerpoMailMap);
					asunto = "Conciliacion" + mailServicio.armarAsuntoValorStringBuffer(valorBBDD).toString();
				} else {
					
					throw new ValidacionExcepcion("No se puede proceder al alta del registro debido a la existencia de duplicidades: "
							+ valorServicio.armarMensajeDuplicado(valorDto));
				}
				
			}
		}
		mailServicio.enviarMailMasivo(asunto, cuerpoMailMap);
	}
	
	/**
	 * @deprecated
	 * hace lo mismo que {@link IValorServicio}.mailValorConciliacion
	 * @param shvValValor
	 * @param cuerpoMailMap
	 * @throws NegocioExcepcion
	 */
	public void mailValorConciliacion(ShvValValor shvValValor, HashMap<String, List<String>> cuerpoMailMap) throws NegocioExcepcion{
		try {
			String cuerpo = mailServicio.armarLineaCuerpoValor(shvValValor);
			TeamComercialDto teamComercial = teamComercialServicio.buscarTeamComercial(String.valueOf(shvValValor.getIdClienteLegado()));
			String usuarioAnalistaCobranza= "";
			if (!Validaciones.isObjectNull(teamComercial) && !Validaciones.isNullOrEmpty(teamComercial.getAnalistaCobranzaDatos())) {
				usuarioAnalistaCobranza = teamComercial.getAnalistaCobranzaDatos();
			}
			String key = mailServicio.formarKeyAgrupador(shvValValor.getIdAnalista(), shvValValor.getIdCopropietario(), usuarioAnalistaCobranza);
			if(cuerpoMailMap.containsKey(key)){
				cuerpoMailMap.get(key).add(cuerpo);
			} else {
				ArrayList<String> nuevaLista = new ArrayList<String>();
				nuevaLista.add(cuerpo);
				cuerpoMailMap.put(key,nuevaLista);
			}
		} catch (NegocioExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * Actualiza los estados del Registro AVC y la Boleta. Para esto: en caso de diferir el “Nro. Cliente”,
	 * se actualizará del lado del Registro AVC con el dato que se indique en la Boleta.
	 * De lo contrario se debe actualizar del lado de la Boleta, con los datos que se indiquen en el Registro AVC. 
	 * @param registroAvc
	 * @param boletaDto
	 */
	private void igualarDatosRegistroAVCyBoleta(DepositoDto registroAvc,BoletaSinValorDto boletaDto) {
		if(!String.valueOf(registroAvc.getCodigoCliente()).equals(String.valueOf(boletaDto.getCodCliente()))){
			if(!Validaciones.isObjectNull(boletaDto.getCodCliente())){
				registroAvc.setCodigoCliente(boletaDto.getCodCliente());
			}
		}
		
		boletaDto.setAcuerdo(registroAvc.getAcuerdo());
		if(!Validaciones.isObjectNull(registroAvc.getCodigoCliente())){
			boletaDto.setCodCliente(String.valueOf(registroAvc.getCodigoCliente()));
		}
		if(!Validaciones.isObjectNull(registroAvc.getNroBoleta())){
			boletaDto.setNumeroBoleta(String.valueOf(registroAvc.getNroBoleta()));
		}
		if(!Validaciones.isObjectNull(registroAvc.getNumeroCheque())){
			boletaDto.setNumeroCheque(String.valueOf(registroAvc.getNumeroCheque()));
		}
		if(!Validaciones.isObjectNull(registroAvc.getImporte())){
			boletaDto.setImporte(Utilidad.formatCurrency(registroAvc.getImporteParaComparar(),2));
		}
	}

	/**
	 * Deshace todas las conciliaciones sugeridas que recibe en la lista.
	 * Por lo tanto, la boleta y el registro AVC de cada conciliacion sugerida pasan a estado
	 * PENDIENTE DE CONCILIACION.
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void deshacerConciliacionesSugeridas(String[] listaConciliacionesSugeridas, String usuarioModificacion) throws NegocioExcepcion {
		try{
			setNumero(listaConciliacionesSugeridas.length);
			for (int i = 0; i < listaConciliacionesSugeridas.length; i++) {
				
				BoletaSinValorDto boletaDto = new BoletaSinValorDto(Long.valueOf(listaConciliacionesSugeridas[i].split("-")[1]));
				DepositoDto registroAvc = new DepositoDto();
				registroAvc.setIdRegistro(Long.valueOf(listaConciliacionesSugeridas[i].split("-")[0]));
				
				registroAvc.setUsuarioModificacion(usuarioModificacion);
				boletaDto.setUsuarioModificacion(usuarioModificacion);
				
				// boleta -> PENDIENTE DE CONCILIACION
				boletaSinValorServicio.deshacerConciliacionSugerida(boletaDto);
				// registro -> PENDIENTE DE CONCILIACION
				registroAVCServicio.deshacerConciliacionSugerida(registroAvc);
				// elimino de la tabla ShvAvcRegistroAvcBoleta la conciliacion
				conciliacionDao.eliminarConciliacionSugerida(String.valueOf(registroAvc.getIdRegistro()), boletaDto.getIdBoleta().toString());
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
	}
	
	/**
	 * Crea un Valor Dto y setea sus atributos obteniendo los valores del registro AVC
	 * o de la boleta que recibe por parametro, segun corresponda.
	 * @param registroAvc
	 * @param boletaDto
	 * @return valorDto
	 */
	public ValorDto crearValorDtoApartirDeRegistroAvcYBoleta(DepositoDto registroAvc, BoletaSinValorDto boletaDto) throws NegocioExcepcion {
		ValorDto valorDto = new ValorDto();
		
		valorDto.setEmpresa(boletaDto.getIdEmpresa());
		valorDto.setIdEmpresa(boletaDto.getIdEmpresa());
		valorDto.setIdSegmento(boletaDto.getIdSegmento());
		valorDto.setSegmento(boletaDto.getIdSegmento());
		valorDto.setCodCliente(boletaDto.getCodCliente());
		valorDto.setCodClienteAgrupador(boletaDto.getCodClienteAgrupador());
		valorDto.setRazonSocialClienteAgrupador(boletaDto.getRazonSocialClienteAgrupador());
		valorDto.setIdAnalista(boletaDto.getIdAnalista());
		valorDto.setCopropietario(boletaDto.getCopropietario());
		valorDto.setImporte(Utilidad.cambiarPuntoDecimalPorComa(String.valueOf(registroAvc.getImporte())));
		valorDto.setFechaDeposito(Utilidad.formatDatePicker(registroAvc.getFechaPago()));
		
		/*Se setea true porque boleta sin valor no setea un objeto cliente, mas adelante deberia quitarse esto, y setear un cliente*/
		valorDto.setEsBoletaSinValor(true);
		/*fin del seteo auxiliar*/

		valorDto.setIdMotivo(boletaDto.getIdMotivo());
		valorDto.setOperacionAsociada(boletaDto.getOperacionAsociada());
		valorDto.setIdOrigen(Constantes.ORIGEN_CONCILIACION_ID);
		valorDto.setIdAcuerdo(registroAvc.getIdAcuerdo());
		valorDto.setIdBancoOrigen(Utilidad.rellenarCerosIzquierda(String.valueOf(registroAvc.getCodigoBanco()),4));
		valorDto.setNumeroCheque(String.valueOf(registroAvc.getNumeroCheque()));
		valorDto.setFechaVencimiento(Utilidad.formatDatePicker(registroAvc.getFechaVencimiento()));
		valorDto.setNumeroBoleta(String.valueOf(registroAvc.getNroBoleta()));
		
		//MAR
//		ClienteBean clienteBean = null;	 
//		
//		clienteBean = this.clienteServicio.consultarCliente(boletaDto.getCodCliente());
//		valorDto.setCliente(this.clienteServicio.mapear(clienteBean)); 
//		valorDto.setRazonSocialClienteAgrupador(boletaDto.getRazonSocialClienteAgrupador());
//		valorDto.setRazonSocialClientePerfil(boletaDto.getRazonSocial());
		
		switch (TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(registroAvc.getTipoValor()))) {
	    case BOLETA_DEPOSITO_CHEQUE:
	    	valorDto.setIdTipoValor(String.valueOf(TipoValorEnum.CHEQUE.getIdTipoValor()));
	    	break;
	    case BOLETA_DEPOSITO_CHEQUE_DIFERIDO:
	    	valorDto.setIdTipoValor(String.valueOf(TipoValorEnum.CHEQUE_DIFERIDO.getIdTipoValor()));
	    	break;
	    case BOLETA_DEPOSITO_EFECTIVO:
	    	valorDto.setIdTipoValor(String.valueOf(TipoValorEnum.EFECTIVO.getIdTipoValor()));
	    	break;
	    default:
		};

		return valorDto;
	}
	
	public void guardarAsociacionDeConciliacion(ShvBolBoleta boleta, ShvAvcRegistroAvc registroAvc) throws NegocioExcepcion {
		try {
			Traza.advertencia(ConciliacionServicioImpl.class, "Se guarda la relacion registro: "+registroAvc.getIdRegistroAvc()+" con estado: "+registroAvc.getWorkFlow().getEstado()
					+" y boleta "+boleta.getIdBoleta()+" con estado: "+boleta.getWorkFlow().getEstado());
			conciliacionDao.insertarConciliacionEnRegistroAvcBoleta(boleta, registroAvc);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	public int cantRegistros(){
		return cantRegistro;
	}

	public DTO buscar(Integer id) throws NegocioExcepcion {
		return null;
	}

	/**
	 * Lista todos los Archivos AVC segun el filtro que recibe por parametro.
	 */
	public Collection<DTO> listar(Filtro filtro) throws NegocioExcepcion {
		return null;
	}

	public Long crear(DTO dto) throws NegocioExcepcion {
		return null;
	}

	public void modificar(DTO dto) throws NegocioExcepcion {
	}

	public void anular(DTO dto) throws NegocioExcepcion {
	}

	public String getDatosModificados(DTO dto) throws NegocioExcepcion {
		return null;
	}

	public void verificarConcurrencia(Serializable id, Long timeStamp)
			throws NegocioExcepcion {
	}
	
	
	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/
	
	public IProcesarArchivoValidacionServicio getProcesarArchivoValidacionServicio() {
		return procesarArchivoValidacionServicio;
	}

	public void setProcesarArchivoValidacionServicio(
			IProcesarArchivoValidacionServicio procesarArchivoValidacionServicio) {
		this.procesarArchivoValidacionServicio = procesarArchivoValidacionServicio;
	}

	public IParametroServicio getParametroServicio() {
		return parametroServicio;
	}

	public void setParametroServicio(IParametroServicio parametroServicio) {
		this.parametroServicio = parametroServicio;
	}

	public DepositoMapeadorBatch getDepositoMapeadorBatch() {
		return depositoMapeadorBatch;
	}

	public void setDepositoMapeadorBatch(DepositoMapeadorBatch depositoMapeadorBatch) {
		this.depositoMapeadorBatch = depositoMapeadorBatch;
	}

	public InterdepositoMapeadorBatch getInterdepositoMapeadorBatch() {
		return interdepositoMapeadorBatch;
	}

	public void setInterdepositoMapeadorBatch(
			InterdepositoMapeadorBatch interdepositoMapeadorBatch) {
		this.interdepositoMapeadorBatch = interdepositoMapeadorBatch;
	}

	public TransferenciaMapeadorBatch getTransferenciaMapeadorBatch() {
		return transferenciaMapeadorBatch;
	}

	public void setTransferenciaMapeadorBatch(
			TransferenciaMapeadorBatch transferenciaMapeadorBatch) {
		this.transferenciaMapeadorBatch = transferenciaMapeadorBatch;
	}

	public IArchivoAVCServicio getArchivoAVCServicio() {
		return archivoAVCServicio;
	}

	public void setArchivoAVCServicio(IArchivoAVCServicio archivoAVCServicio) {
		this.archivoAVCServicio = archivoAVCServicio;
	}

	public IConciliacionDao getConciliacionDao() {
		return conciliacionDao;
	}

	public void setConciliacionDao(IConciliacionDao conciliacionDao) {
		this.conciliacionDao = conciliacionDao;
	}

	public IRegistroAVCServicio getRegistroAVCServicio() {
		return registroAVCServicio;
	}

	public void setRegistroAVCServicio(IRegistroAVCServicio registroAVCServicio) {
		this.registroAVCServicio = registroAVCServicio;
	}

	public IBoletaSinValorServicio getBoletaSinValorServicio() {
		return boletaSinValorServicio;
	}

	public void setBoletaSinValorServicio(
			IBoletaSinValorServicio boletaSinValorServicio) {
		this.boletaSinValorServicio = boletaSinValorServicio;
	}

	public IValorServicio getValorServicio() {
		return valorServicio;
	}

	public void setValorServicio(IValorServicio valorServicio) {
		this.valorServicio = valorServicio;
	}

	public IRegistroAVCDao getRegistroAVCDao() {
		return registroAVCDao;
	}

	public void setRegistroAVCDao(IRegistroAVCDao registroAVCDao) {
		this.registroAVCDao = registroAVCDao;
	}

	public IBoletaDao getBoletaDao() {
		return boletaDao;
	}

	public void setBoletaDao(IBoletaDao boletaDao) {
		this.boletaDao = boletaDao;
	}

	public int getNumero() {
		return cantRegistro;
	}
	public void setNumero(int numero) {
		this.cantRegistro = numero;
	}

	public ConciliacionSugeridaMapeador getConciliacionSugeridaMapeo() {
		return conciliacionSugeridaMapeo;
	}

	public void setConciliacionSugeridaMapeo(
			ConciliacionSugeridaMapeador conciliacionSugeridaMapeo) {
		this.conciliacionSugeridaMapeo = conciliacionSugeridaMapeo;
	}
}
