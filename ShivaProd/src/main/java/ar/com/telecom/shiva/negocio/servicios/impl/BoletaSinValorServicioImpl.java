package ar.com.telecom.shiva.negocio.servicios.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Mensajes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.EnviarMailBoletaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.ImprimirBoletaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.CampoMailExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ConcurrenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.MailExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mail.Adjunto;
import ar.com.telecom.shiva.base.mail.Mail;
import ar.com.telecom.shiva.base.mail.MailServicio;
import ar.com.telecom.shiva.base.utils.GeneradorComprobantePago;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.mapeos.BoletaSinValorMapeador;
import ar.com.telecom.shiva.negocio.servicios.IBoletaSinValorServicio;
import ar.com.telecom.shiva.negocio.servicios.ICombosServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowBoletas;
import ar.com.telecom.shiva.persistencia.dao.IBoletaDao;
import ar.com.telecom.shiva.persistencia.dao.IBoletaSinValorDao;
import ar.com.telecom.shiva.persistencia.dao.IValorDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvBolBoleta;
import ar.com.telecom.shiva.persistencia.modelo.ShvBolBoletaSinValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvBolBoletaSinValorSimple;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ActualizacionExitosaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.BoletaSinValorDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.BoletaSinValorFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import java.text.ParseException;

public class BoletaSinValorServicioImpl extends Servicio implements IBoletaSinValorServicio {
	
	@Autowired IBoletaSinValorDao boletaSinValorDao;
	@Autowired IBoletaDao boletaDao;
	@Autowired IWorkflowBoletas workflowBoletas;
	@Autowired IValorDao valorDao;
	@Autowired MailServicio mailServicio;
	@Autowired ILdapServicio ldapServicio;
	@Autowired BoletaSinValorMapeador boletaSinValorMapeador;
	@Autowired IValorServicio valorServicio;
	
	@Autowired
	private IParametroServicio parametroServicio;
	@Autowired
	protected ICombosServicio combosServicio;
	
	protected static final String SELECT_EMPRESAS = "empresas";
	protected static final String SELECT_SEGMENTOS = "segmentos";
	
	
	
	/**
	 * Verificar concurrencia
	 * 
	 */
	public void  verificarConcurrencia(Serializable id, Long timeStamp) throws NegocioExcepcion {
		
		try {
			BoletaSinValorFiltro boletaFiltro = new BoletaSinValorFiltro(id.toString());
			Collection<ShvBolBoletaSinValor> listaBoletaModelo = boletaSinValorDao.buscarBoletasSinValor(boletaFiltro);
			if (Validaciones.isCollectionNotEmpty(listaBoletaModelo)) {
				ShvBolBoletaSinValor boletaSinValor = listaBoletaModelo.iterator().next();
				
				ShvBolBoleta boleta = boletaSinValor.getBoleta(); 
				ShvWfWorkflow wf = boleta.getWorkFlow();
				
				//Si la ultima modificacion es distinta a la actual
				if (wf.getFechaUltimaModificacion().getTime() != timeStamp.longValue()) {
					throw new ConcurrenciaExcepcion(boletaSinValor.getBoleta().getNumeroBoleta());
				}
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * Retorna el numero de boleta creado
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public Long crear(DTO dto) throws NegocioExcepcion{
		BoletaSinValorDto boletaSinValorDto = (BoletaSinValorDto) dto;
		
		Date fechaCreacion = new Date();
		
		try {
			ShvBolBoletaSinValor boletaSinValor = (ShvBolBoletaSinValor) 
					boletaSinValorMapeador.map(boletaSinValorDto, null);
			boletaSinValorDto.setImporte(boletaSinValorDto.getImporteParaComparacion());
			String datosOriginales = Utilidad.datosOriginales(boletaSinValorDto,getListaDatosOriginales());
			
			ShvBolBoleta boleta = new ShvBolBoleta();
			ShvWfWorkflow workflow = workflowBoletas.crearWorkflow(datosOriginales, boletaSinValorDto.getUsuarioModificacion());
			boleta.setWorkFlow(workflow);
			if (boletaSinValorDto.isMigracion()){
				boleta.setNumeroBoleta(Long.valueOf(boletaSinValorDto.getNumeroBoleta()));
			} else {
			boleta.setNumeroBoleta(boletaDao.proximoValorNumeroBoleta());
			}
			boleta.setEmailEnvioEstado(boletaSinValorDto.getBoletaEnviadaMailEstado());
			boleta.setImpresionEstado(boletaSinValorDto.getBoletaImpresaEstado());
			boleta.setEmailObservaciones(boletaSinValorDto.getObservacionMail());
			boleta.setFechaAlta(fechaCreacion);
			
			if (boletaSinValorDto.getCheckEnviarMailBoleta()) {
					boleta.setEmailEnvioEstado(EnviarMailBoletaEstadoEnum.SI);
					boleta.setEmail(boletaSinValorDto.getEmail());
					boleta.setEmailEnvioUsuario(boletaSinValorDto.getCheckEnviarMailBoleta()?boletaSinValorDto.getUsuarioModificacion():"");
					boleta.setEmailEnvioDestino(boletaSinValorDto.getEmail());
					
					boleta.setEmailEnvioObservaciones(boletaSinValorDto.getObservacionMail());
					boleta.setEmailEnvioFecha(fechaCreacion);				
			}else {
					boleta.setImpresionEstado(ImprimirBoletaEstadoEnum.SI);
					boleta.setImpresionUsuario(boletaSinValorDto.getCheckImprimirBoleta()?boletaSinValorDto.getUsuarioModificacion():"");
					boleta.setImpresionFecha(fechaCreacion);
			}
				
			boleta.setBoletaSinValor(boletaSinValor);
			boletaSinValor.setBoleta(boleta);
				
			ShvBolBoleta bol = boletaDao.insertarBoleta(boleta);
		
			return bol.getNumeroBoleta();
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} 
	}

	@Override
	public DTO buscar(Integer id) throws NegocioExcepcion {
		return null;
	}
	
	public BoletaSinValorDto buscarPorNumeroBoleta(Long numeroBoleta) throws NegocioExcepcion {
		try {
			ShvBolBoletaSinValor boletaModelo = boletaSinValorDao.buscarBoletaSinValorPorNumeroBoleta(String.valueOf(numeroBoleta));
			BoletaSinValorDto boletaDTO = (BoletaSinValorDto)boletaSinValorMapeador.map(boletaModelo);
			
			return boletaDTO;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	public BoletaSinValorDto buscarPorIdBoleta(Integer id) throws NegocioExcepcion {
		try {
			ShvBolBoletaSinValor boletaModelo = (ShvBolBoletaSinValor)boletaSinValorDao.buscarBoletaSinValor(id.longValue());
			BoletaSinValorDto boletaDTO = (BoletaSinValorDto)boletaSinValorMapeador.map(boletaModelo);
			
			return boletaDTO;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	public Collection<DTO> listar(Filtro filtro) throws NegocioExcepcion {
		BoletaSinValorFiltro boletaFiltro = (BoletaSinValorFiltro) filtro;
		
		try {
			Collection<ShvBolBoletaSinValor> listaBoletaModelo = boletaSinValorDao.buscarBoletasSinValor(boletaFiltro);
			Collection<DTO> listaBoletaDTO = boletaSinValorMapeador.map(listaBoletaModelo);
			
			return listaBoletaDTO;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	public Collection<DTO> listarBoletasSinValor(Filtro filtro, UsuarioSesion userSesion) throws NegocioExcepcion {
		BoletaSinValorFiltro boletaFiltro = (BoletaSinValorFiltro) filtro;
		
		try {
			Collection<ShvBolBoletaSinValor> listaBoletaModelo = boletaSinValorDao.buscarBoletasSinValor(boletaFiltro,userSesion);
			Collection<DTO> listaBoletaDTO = boletaSinValorMapeador.map(listaBoletaModelo);
			
			return listaBoletaDTO;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	/**
	 * Método que carga el combo de segmento con la opción "Todos"
	 * @param mv
	 * @param userSesion
	 * @param filtro
	 * @throws ShivaExcepcion
	 */
	public void cargarCombosParaBusquedaBoletaSinValor(ModelAndView mv,
			UsuarioSesion userSesion, Filtro filtro) throws ShivaExcepcion {
		try {
			List<ShvParamEmpresa> listaEmpresas = (List<ShvParamEmpresa>) combosServicio
					.listarEmpresasPorUsuario(userSesion);
			List<ShvParamSegmento> listaSegmentos = new ArrayList<ShvParamSegmento>();
			ShvParamSegmento todos = new ShvParamSegmento();
			todos.setIdSegmento("todos");
			todos.setId("todos");
			todos.setDescripcion("Todos");
			
			if (listaEmpresas.size() == 1) {
				String idEmpresa = listaEmpresas.get(0).getIdEmpresa();
				filtro.setEmpresa(idEmpresa);
				listaSegmentos = combosServicio
						.listarSegmentoPorEmpresaYUsuario(idEmpresa, userSesion);

				mv.addObject("comboEmpresa", false);
			} else {
				if (!Validaciones.isNullOrEmpty(filtro.getEmpresa())) {
					listaSegmentos = combosServicio
							.listarSegmentoPorEmpresaYUsuario(
									filtro.getEmpresa(), userSesion);
				}
				mv.addObject("comboEmpresa", true);
			}
			listaSegmentos.add(todos);
			mv.addObject(SELECT_SEGMENTOS, listaSegmentos);
			if (listaSegmentos.size() > 2) {
				mv.addObject("comboSegmento", true);
			} else {
				mv.addObject("comboSegmento", false);
				if (listaSegmentos.size() == 1) {
					filtro.setSegmento(listaSegmentos.get(0).getIdSegmento());
				}
			}

			mv.addObject(SELECT_EMPRESAS, listaEmpresas);
		} catch (NegocioExcepcion e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		}
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void anular(DTO dto) throws NegocioExcepcion{
		BoletaSinValorDto boletaSinValorDto = (BoletaSinValorDto) dto;
		
		try {
			BoletaSinValorFiltro boletaFiltro = new BoletaSinValorFiltro(boletaSinValorDto.getId().toString());
			Collection<ShvBolBoletaSinValor> listaBoletaModelo = boletaSinValorDao.buscarBoletasSinValor(boletaFiltro);
			if (Validaciones.isCollectionNotEmpty(listaBoletaModelo)) {
				ShvBolBoletaSinValor boletaSinValor = listaBoletaModelo.iterator().next();
				ShvBolBoleta boleta = boletaSinValor.getBoleta(); 
				ShvWfWorkflow wf = boleta.getWorkFlow();
				
				//Verifico concurrencia
				verificarConcurrencia(boletaSinValorDto.getId(), Long.valueOf(boletaSinValorDto.getTimeStamp()));
				
				//Anula la boleta
				ShvWfWorkflow workflowActualizado = workflowBoletas.solicitarBoletaAnulacion(wf, "", boletaSinValorDto.getUsuarioModificacion());
				
				boleta.setWorkFlow(workflowActualizado);
				
				boletaDao.actualizarBoleta(boleta);
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
		
	}
	
	@Override
	public void anularBoletas(String listaBoletas, UsuarioSesion usuarioLogueado)
			throws NegocioExcepcion {
		
		String listaIdInconcurrentes = "";
		
		if (!Validaciones.isNullOrEmpty(listaBoletas)) {
			for (String idBoletaAAnular: listaBoletas.split(",")) {
				String id = idBoletaAAnular.split("_")[0];
				String timeStamp = idBoletaAAnular.split("_")[1];
				
				BoletaSinValorDto boletaSinValorDto = new BoletaSinValorDto();
				boletaSinValorDto.setUsuarioModificacion(usuarioLogueado.getUsuario());
				boletaSinValorDto.setId(id);
				boletaSinValorDto.setTimeStamp(timeStamp);
				
				try {
					anular(boletaSinValorDto);
				} catch (ConcurrenciaExcepcion e) {
					listaIdInconcurrentes += e.getIdInconcurrente() +",";
				}
			}
		}
		
		if (!Validaciones.isNullOrEmpty(listaIdInconcurrentes)) {
			listaIdInconcurrentes = listaIdInconcurrentes.substring(0, listaIdInconcurrentes.length()-1); 
			throw new ConcurrenciaExcepcion(listaIdInconcurrentes);
		}
		
	}
	
	
	public void modificar(DTO dto) throws NegocioExcepcion {
		BoletaSinValorDto boletaSinValorDto = (BoletaSinValorDto) dto;
		
		try {
			BoletaSinValorFiltro boletaFiltro = new BoletaSinValorFiltro(boletaSinValorDto.getId().toString());
			Collection<ShvBolBoletaSinValor> listaBoletaModelo = boletaSinValorDao.buscarBoletasSinValor(boletaFiltro);
			ShvBolBoletaSinValor boletaSinValor;
			if (Validaciones.isCollectionNotEmpty(listaBoletaModelo)) {
				boletaSinValor = listaBoletaModelo.iterator().next();
				
				//Datos Modificados
				BoletaSinValorDto boletaSinValorDtoViejo = (BoletaSinValorDto) boletaSinValorMapeador.map(boletaSinValor);
				String datosModificados = Utilidad.generarDatosModificados(
						boletaSinValorDtoViejo,boletaSinValorDto,getListaModificarBoletaSinValor());
				
				//Boleta Sin Valor Modificado mapeada
				ShvBolBoletaSinValor boletaSinValorNuevo =
						(ShvBolBoletaSinValor) boletaSinValorMapeador.map(boletaSinValorDto, boletaSinValor);
				
				//Verifico concurrencia
				verificarConcurrencia(boletaSinValorDto.getId(), Long.valueOf(boletaSinValorDto.getTimeStamp()));
				
				/* Actualizar Boleta */
				ShvBolBoleta boleta = boletaSinValor.getBoleta();
				boleta.setBoletaSinValor(boletaSinValorNuevo);
				boleta.setEmail(boletaSinValorDto.getEmail());
				boleta.setEmailObservaciones(boletaSinValorDto.getObservacionMail());
				
				/* Actualizar Workflow */
				ShvWfWorkflow wf = boleta.getWorkFlow();
				ShvWfWorkflow workflowActualizado = 
						workflowBoletas.actualizarWorkflow(wf, datosModificados, boletaSinValorDto.getUsuarioModificacion());
				boleta.setWorkFlow(workflowActualizado);
				
				boletaDao.actualizarBoleta(boleta);
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} 
	}
	
	/**
	 * Genero de la siguiente manera
	 * Por ejemplo:
	 * "[1401097870|1401022360|1401202724|1401227300]"
	 */
	@Override
	public String listar10CodigosClienteLegado(String usuarioLogueado) throws NegocioExcepcion {
		String cadena="";
		try {
			List<ShvBolBoletaSinValorSimple> lista = 
					boletaSinValorDao.listarCodigosClienteLegado(usuarioLogueado);
			
			Map<String, String> unsortMap = new HashMap<String, String>();
			for (ShvBolBoletaSinValorSimple bsv: lista) {
				if (unsortMap.size() >= 10) {
					break;
				} else {
					unsortMap.put(bsv.getIdClienteLegado().toString(), bsv.getCodigoClienteSiebelFormula());
				}
			}
			
			Map<String, String> listaOrdenada = new TreeMap<String, String>(unsortMap);
			for (Map.Entry<String, String> entry : listaOrdenada.entrySet()) {
				cadena += entry.getValue()+Constantes.SEPARADOR_PIPE;
			}
			
			//Saco la ultima ","
			if (!Validaciones.isNullOrEmpty(cadena)) {
				cadena=cadena.substring(0,cadena.length()-1);
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
		
		return cadena;
	}
	
	
	/**
	 * Retorna las boletas con valor correspondientes al valor del TipoValorEnum y
	 * en estado BOL_PENDIENTE y el valor asociado en estado VAL_BOLETA_PENDIENTE_CONCILIACION. 
	 * Ademas, retorna las boletas sin valor en estado BOL_PENDIENTE.
	 * @param tipoValor
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<BoletaSinValorDto> listarBoletasPendientesConciliar(TipoValorEnum tipoValor) throws NegocioExcepcion {
		List<BoletaSinValorDto> listaBoletasDto = new ArrayList<BoletaSinValorDto>();
		
		try {
			if(!Validaciones.isObjectNull(tipoValor)){
				// si tipoValor es null debo retornar una lista de boletas sin valor
				Collection<ShvBolBoleta> listaBoletaModelo = boletaSinValorDao.listarBoletasSinValorPendientesConciliar();
				if(Validaciones.isCollectionNotEmpty(listaBoletaModelo)){
					for (ShvBolBoleta shvBolBoleta : listaBoletaModelo) {
							listaBoletasDto.add((BoletaSinValorDto) boletaSinValorMapeador.map(shvBolBoleta.getBoletaSinValor()));
					}
				}
				
				// si tipoValor no es null debo retornar una lista de boletas con valor
				List<ShvValValor> valores = boletaSinValorDao.listarBoletasConValorPendientesConciliar(tipoValor);
				// mapeo de ShvValValor a boletaSinValorDto
				listaBoletasDto.addAll(boletaSinValorMapeador.mapearBoletaConValor(valores));
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		return listaBoletasDto;
	}
	
	/**
	 * Modifica el estado de la boleta a Conciliado.
	 */
	public ShvBolBoleta establecerRegistroConciliado(BoletaSinValorDto boletaDto) throws NegocioExcepcion {
		try {
			ShvBolBoleta boletaModelo = boletaDao.buscarBoleta(Long.valueOf(boletaDto.getIdBoleta().toString()));
			if (!Validaciones.isObjectNull(boletaModelo)) {
				Traza.auditoria(BoletaSinValorServicioImpl.class, "Se actualiza la boleta id: "+boletaModelo.getIdBoleta()+ " con estado: "+boletaModelo.getWorkFlow().getEstado());
				ShvWfWorkflow wf = boletaModelo.getWorkFlow();
				
				//Verifico concurrencia
				verificarConcurrencia(boletaDto.getIdBoleta(), Long.valueOf(boletaDto.getTimeStamp()));
				
				//Cambiar el estado de la boleta a Conciliado
				ShvWfWorkflow workflowActualizado = workflowBoletas.establecerBoletaConciliado(wf, " ", boletaDto.getUsuarioModificacion());
				
				boletaModelo.setWorkFlow(workflowActualizado);
				
				ShvBolBoleta boletaActualizada = boletaDao.actualizarBoleta(boletaModelo);
				Traza.auditoria(BoletaSinValorServicioImpl.class, "Se actualizo la boleta id: "+boletaActualizada.getIdBoleta()+ " estado actual: "+boletaActualizada.getWorkFlow().getEstado());
				return boletaActualizada;
			}
			
			return null;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * Modifica el estado de la boleta a Conciliacion sugerida.
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public ShvBolBoleta establecerRegistroConciliacionSugerida(BoletaSinValorDto boletaDto) throws NegocioExcepcion {
		try {
			ShvBolBoleta boletaModelo = boletaDao.buscarBoleta(Long.valueOf(boletaDto.getIdBoleta().toString()));
			if (!Validaciones.isObjectNull(boletaModelo)) {
				Traza.auditoria(BoletaSinValorServicioImpl.class, "Se actualiza la boleta id: "+boletaModelo.getIdBoleta()+ " con estado: "+boletaModelo.getWorkFlow().getEstado());
				ShvWfWorkflow wf = boletaModelo.getWorkFlow();
				
				//Verifico concurrencia
				verificarConcurrencia(boletaDto.getIdBoleta(), Long.valueOf(boletaDto.getTimeStamp()));
				
				//Cambiar el estado de la boleta a Conciliacion Sugerida
				ShvWfWorkflow workflowActualizado = workflowBoletas.establecerBoletaConciliacionSugerida(wf, " ", boletaDto.getUsuarioModificacion());
				
				boletaModelo.setWorkFlow(workflowActualizado);
				
				ShvBolBoleta boletaActualizada = boletaDao.actualizarBoleta(boletaModelo);
				Traza.auditoria(BoletaSinValorServicioImpl.class, "Se actualizo la boleta id: "+boletaActualizada.getIdBoleta()+ " estado actual: "+boletaActualizada.getWorkFlow().getEstado());
				return boletaActualizada;
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		return null;
	}
	
	/**
	 * Busca en la base si la boleta que recibe por parametro tiene un valor asociado.
	 * Si tiene un valor asociado retorna el id de dicho valor. Si no tiene ningun valor asociado
	 * retorna null.
	 */
	public Long getIdValorAsociadoABoleta(BoletaSinValorDto boletaDto, String tipoValor) throws NegocioExcepcion {
		try {
			return valorDao.getIdValorAsociadoABoleta(String.valueOf(boletaDto.getIdBoleta()),tipoValor);
		}catch(PersistenciaExcepcion e ){
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/**
	 * Cambiar el estado de la boleta a Conciliacion Con Diferencias.
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void establecerRegistroConciliadoConDiferencia(BoletaSinValorDto boletaDto) throws NegocioExcepcion {
		
		try {
			BoletaSinValorFiltro boletaFiltro = new BoletaSinValorFiltro(boletaDto.getIdBoleta().toString());
			Collection<ShvBolBoletaSinValor> listaBoletaModelo = boletaSinValorDao.buscarBoletasSinValor(boletaFiltro);
			if (Validaciones.isCollectionNotEmpty(listaBoletaModelo)) {
				ShvBolBoletaSinValor boletaSinValor = listaBoletaModelo.iterator().next();
				ShvBolBoleta boleta = boletaSinValor.getBoleta(); 
				ShvWfWorkflow wf = boleta.getWorkFlow();
				
				boletaDto.setFechaUltimaModificacion(boletaSinValor.getBoleta().getWorkFlow().getFechaUltimaModificacion());
				boletaDto.setTimeStamp(boletaDto.getTimeStampDTO());
				
				//Verifico concurrencia
				verificarConcurrencia(boletaDto.getIdBoleta(), Long.valueOf(boletaDto.getTimeStamp()));
				
				//Cambiar el estado de la boleta a Conciliacion Con Diferencias
				ShvWfWorkflow workflowActualizado = workflowBoletas.conciliarBoletaConDiferencias(wf, " ", boletaDto.getUsuarioModificacion());
				
				boleta.setWorkFlow(workflowActualizado);
				
				boletaDao.actualizarBoleta(boleta);
			}
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
	}
	
	/**
	 * Cambiar el estado de la boleta a Pendiente de Conciliacion. Es decir Deshace la conciliacion sugerida.
	 * @param boletaDto
	 * @throws NegocioExcepcion
	 */
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void deshacerConciliacionSugerida(BoletaSinValorDto boletaDto) throws NegocioExcepcion {
		try {
			
			ShvBolBoleta boletaModelo = boletaDao.buscarBoleta(Long.valueOf(boletaDto.getIdBoleta().toString()));
			if (!Validaciones.isObjectNull(boletaModelo)) {
				ShvWfWorkflow wf = boletaModelo.getWorkFlow();
				
				boletaDto.setFechaUltimaModificacion(wf.getFechaUltimaModificacion());
				boletaDto.setTimeStamp(boletaDto.getTimeStampDTO());
				
				//Verifico concurrencia
				verificarConcurrencia(boletaDto.getIdBoleta(), Long.valueOf(boletaDto.getTimeStampDTO()));		
				
				//Cambiar el estado de la boleta a Pendiente de Conciliacion. Es decir Deshace la conciliacion sugerida
				ShvWfWorkflow workflowActualizado = workflowBoletas.deshacerBoletaConciliacionSugerida(wf, " ", boletaDto.getUsuarioModificacion());
				boletaModelo.setWorkFlow(workflowActualizado);
				boletaDao.actualizarBoleta(boletaModelo);
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void anularBoletasPendientes(String fechaHasta) throws NegocioExcepcion {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(Utilidad.parseDatePickerString(fechaHasta));
			calendar.add(Calendar.DAY_OF_MONTH, - (parametroServicio.getValorNumerico(Constantes.DIAS_ANTERIORIDAD_DEPURACION_BOLETA).intValue()));
			
			Date fechaParametro=calendar.getTime();
			
			BoletaSinValorFiltro boletaFiltro = new BoletaSinValorFiltro();
			boletaFiltro.setFechaHasta(Utilidad.formatDatePicker(fechaParametro));
			
			// Busco las boletas en estado pendiente. Si tienen valores asociados que estos esten
			// en estado Boleta Rechazada o Boleta Pendiente de Conciliacion
			List<ShvBolBoleta> listaBoletaModelo = (List<ShvBolBoleta>)boletaDao.buscarBoletasPendientesYRechazadasParaDepurar(boletaFiltro);
			
			if (Validaciones.isCollectionNotEmpty(listaBoletaModelo)) {
				String usuarioBatch = parametroServicio.getValorTexto(Constantes.USUARIO_BATCH);
				for (ShvBolBoleta shvBolBoleta : listaBoletaModelo) {
					if(!Validaciones.isObjectNull(shvBolBoleta.getBoletaSinValor())){
						//Boleta sin valor
						BoletaSinValorDto boletaDto = (BoletaSinValorDto) boletaSinValorMapeador.map(shvBolBoleta.getBoletaSinValor());
						boletaDto.setUsuarioModificacion(usuarioBatch);
						anular(boletaDto);
						Traza.auditoria(this.getClass(), "Se ha anulado la boleta con Id: " + shvBolBoleta.getIdBoleta() + " (WF: " + shvBolBoleta.getWorkFlow().getIdWorkflow() +  ")");
					}else{
						//Boleta con valor
						ShvValValor valor = valorServicio.buscarValorPorIdBoleta(shvBolBoleta.getIdBoleta());
						valorServicio.anularValorBoletaPendienteConciliacion(valor, " ",usuarioBatch, null );
						Traza.auditoria(this.getClass(), Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("traza.anulacion.boleta"), String.valueOf(shvBolBoleta.getIdBoleta()), String.valueOf(shvBolBoleta.getWorkFlow().getIdWorkflow())));
						Traza.auditoria(this.getClass(), Utilidad.reemplazarMensajes(Propiedades.MENSAJES_PROPIEDADES.getString("traza.anulacion.valor"), String.valueOf(valor.getIdValor()), String.valueOf(valor.getWorkFlow().getIdWorkflow())));
					}
				}
				
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (ParseException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
	}
	
	/***************************************************************************
	 * Lista de Metodos para envio de Mail y creacion de PDF
	 * ************************************************************************/
	
	/**
	 * Retorna los mensajes para mostrar en la pantalla segun los resultados del envio de
	 * los mails.
	 * @throws PersistenciaExcepcion 
	 */
	public List<String> enviarMail(String[] listaIdBoletas, UsuarioSesion userSesion) throws NegocioExcepcion, PersistenciaExcepcion {
		List<String> mensajesMostrarEnvioMail = new ArrayList<String>();
		if(!Validaciones.isObjectNull(listaIdBoletas)){
			mensajesMostrarEnvioMail = generarPdfBoletaMail(mapListFromStringToBolBoletaSinValor(listaIdBoletas),userSesion.getIdUsuario());
		}
		return mensajesMostrarEnvioMail;
	}
	
	/**
	 * Metodo para imprimir las boletas sin valor. Se invoca desde BoletaController.
	 * @param listaIdBoletas Lista con los id de boletas.
	 * @param userSesion Usuario sesion.
	 * @throws PersistenciaExcepcion 
	 */
	public ActualizacionExitosaDto imprimirBoletas(String[] listaIdBoletas,UsuarioSesion userSesion) throws NegocioExcepcion, PersistenciaExcepcion{
		ActualizacionExitosaDto exitosaDto = new ActualizacionExitosaDto();
		/*Manejo de PDF*/
		List<byte[]> byteARetornarBoleta = new ArrayList<byte[]>();
		List<String> numerosBoletasAlta = new ArrayList<String>();
		
		Boolean ocurrioError = false;
		List<ShvBolBoletaSinValor> boletasModeloImprimible = mapListFromStringToBolBoletaSinValor(listaIdBoletas);
		if(Validaciones.isCollectionNotEmpty(boletasModeloImprimible)){
			try{
				byteARetornarBoleta.add(generarPdfBoletaImprimir(boletasModeloImprimible));
			}catch(NegocioExcepcion e){
				ocurrioError = true;
			}
		}
		
		try{
			for (ShvBolBoletaSinValor boletaSinValor : boletasModeloImprimible) {
				if(ocurrioError){
					boletaSinValor.getBoleta().setImpresionEstado(ImprimirBoletaEstadoEnum.ERR);
				}else{
					boletaSinValor.getBoleta().setImpresionEstado(ImprimirBoletaEstadoEnum.SI);
				}
				boletaDao.actualizarBoleta(boletaSinValor.getBoleta());
				
				numerosBoletasAlta.add(String.valueOf(boletaSinValor.getBoleta().getNumeroBoleta()));
			}
		}catch(PersistenciaExcepcion e){
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
		exitosaDto.setNumerosBoletasAlta(numerosBoletasAlta );
		exitosaDto.setArchivosImprimirBoleta(byteARetornarBoleta);
		
		return exitosaDto;	
	}
	
	/**
	 * Retorna una lista de ShvBolBoletaSinValor, buscando los idBoleta de
	 * listaBoletas.
	 * @param listaIdBoletas
	 * @return List<ShvBolBoletaSinValor>
	 * @throws NegocioExcepcion
	 */
	private List<ShvBolBoletaSinValor> mapListFromStringToBolBoletaSinValor(String[] listaIdBoletas) throws NegocioExcepcion{
		try {
			List<ShvBolBoletaSinValor> listaBoletasModelo = new ArrayList<ShvBolBoletaSinValor>();
			for (String idBoleta : listaIdBoletas) {
					listaBoletasModelo.add(boletaSinValorDao.buscarBoletaSinValor(Long.valueOf(idBoleta)));
			}
			return listaBoletasModelo;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * Crea el PDF lo envia el Mail. Si ocurre un error en el envio de mail, graba
	 * en la boleta en envio_mail_estado con ERR. Si
	 * @param boletaSinValorWrappers
	 * @throws NegocioExcepcion 
	 * @throws PersistenciaExcepcion 
	 */
	private List<String> generarPdfBoletaMail(List<ShvBolBoletaSinValor> boletasSinValorModelo, String usuario) 
			 throws NegocioExcepcion, PersistenciaExcepcion {
		
		List<Long> boletasExito = new ArrayList<Long>();
		List<Long> boletasErrorPara = new ArrayList<Long>();
		List<Long> boletasErrorMail = new ArrayList<Long>();
		List<Long> boletasErrorDocumento = new ArrayList<Long>();
		List<String> boletasADevolver = new ArrayList<String>();
		
		for (ShvBolBoletaSinValor shvBolBoletaSinValor : boletasSinValorModelo) {
			Document documento = new Document();
			ByteArrayOutputStream baos = null;
			try {
				GeneradorComprobantePago generador = new GeneradorComprobantePago();
				List<ShvBolBoletaSinValor> listaModelo = new ArrayList<ShvBolBoletaSinValor>();
				listaModelo.add(shvBolBoletaSinValor);
				generador.setearListaBoletasSinValorModelo(listaModelo);
				
				baos = new ByteArrayOutputStream();
				PdfWriter pw = PdfWriter.getInstance(documento, baos);
				documento.open();
				generador.crearContenidoPDF(documento, pw);
				documento.close();
				
				mailDocumento(baos.toByteArray(), shvBolBoletaSinValor,usuario);
				boletasExito.add(Long.valueOf(shvBolBoletaSinValor.getBoleta().getNumeroBoleta()));
				shvBolBoletaSinValor.getBoleta().setEmailEnvioEstado(EnviarMailBoletaEstadoEnum.SI);

			} catch (DocumentException e) {
				Traza.error(getClass(), "Error al generarPdfBoletaMail", e);
				boletasErrorDocumento.add(Long.valueOf(shvBolBoletaSinValor.getBoleta().getNumeroBoleta()));
				shvBolBoletaSinValor.getBoleta().setEmailEnvioEstado(EnviarMailBoletaEstadoEnum.ERR);
			} catch (IOException e) {
				Traza.error(getClass(), "Error al generarPdfBoletaMail", e);
				boletasErrorDocumento.add(Long.valueOf(shvBolBoletaSinValor.getBoleta().getNumeroBoleta()));
				shvBolBoletaSinValor.getBoleta().setEmailEnvioEstado(EnviarMailBoletaEstadoEnum.ERR);
			} catch (SQLException e) {
				Traza.error(getClass(), "Error al generarPdfBoletaMail", e);
				boletasErrorDocumento.add(Long.valueOf(shvBolBoletaSinValor.getBoleta().getNumeroBoleta()));
				shvBolBoletaSinValor.getBoleta().setEmailEnvioEstado(EnviarMailBoletaEstadoEnum.ERR);
			} catch (CampoMailExcepcion e) {
				Traza.error(getClass(), "Error al generarPdfBoletaMail", e);
				boletasErrorPara.add(Long.valueOf(shvBolBoletaSinValor.getBoleta().getNumeroBoleta()));
				shvBolBoletaSinValor.getBoleta().setEmailEnvioEstado(EnviarMailBoletaEstadoEnum.ERR);
			} catch (MailExcepcion e) {
				Traza.error(getClass(), "Error al generarPdfBoletaMail", e);
				boletasErrorMail.add(Long.valueOf(shvBolBoletaSinValor.getBoleta().getNumeroBoleta()));
				shvBolBoletaSinValor.getBoleta().setEmailEnvioEstado(EnviarMailBoletaEstadoEnum.ERR);
			} catch (NegocioExcepcion e) {
				Traza.error(getClass(), "Error al generarPdfBoletaMail", e);
				boletasErrorMail.add(Long.valueOf(shvBolBoletaSinValor.getBoleta().getNumeroBoleta()));
				shvBolBoletaSinValor.getBoleta().setEmailEnvioEstado(EnviarMailBoletaEstadoEnum.ERR);
			}
			
			try {
				boletaDao.actualizarBoleta(shvBolBoletaSinValor.getBoleta());
			} catch (PersistenciaExcepcion e) {
				throw new NegocioExcepcion(e);
			}
		}

		if(boletasErrorDocumento.size() == boletasSinValorModelo.size()){
			if(boletasErrorDocumento.size() == 1){
				boletasADevolver.add(Mensajes.MAIL_BOLETA_ERROR_DOCUMENTO);
			}else{
				boletasADevolver.add(Mensajes.MAIL_BOLETA_ERROR_DOCUMENTO_PLURAL);
			}
		} else if(boletasErrorMail.size() == boletasSinValorModelo.size()){
			if(boletasErrorMail.size() == 1){
				boletasADevolver.add(Mensajes.MAIL_BOLETA_ERROR_ENVIO);
			}else{
				boletasADevolver.add(Mensajes.MAIL_BOLETA_ERROR_ENVIO_PLURAL);
			}
		} else if(boletasErrorPara.size() == boletasSinValorModelo.size()){
			if(boletasErrorPara.size() == 1){
				boletasADevolver.add(Mensajes.MAIL_BOLETA_ERROR_PARA);
			}else{
				boletasADevolver.add(Mensajes.MAIL_BOLETA_ERROR_PARA_PLURAL);
			}
		} else if(boletasExito.size() == boletasSinValorModelo.size()){
			if(boletasExito.size() == 1){
				boletasADevolver.add(Mensajes.MAIL_ENVIO_EXITO);
			}else{
				boletasADevolver.add(Mensajes.MAIL_ENVIO_EXITO_PLURAL);
			}
		} else {

			if (!boletasErrorDocumento.isEmpty()) {
				if(boletasErrorDocumento.size() == 1){
					boletasADevolver.add(Utilidad.reemplazarMensajes(Mensajes.MAIL_BOLETAS_ERRORES_DOCUMENTO, boletasErrorDocumento.toString()));
				}else{
					boletasADevolver.add(Utilidad.reemplazarMensajes(Mensajes.MAIL_BOLETAS_ERRORES_DOCUMENTO_PLURAL, boletasErrorDocumento.toString()));
				}
			}

			if (!boletasErrorMail.isEmpty()) {
				if(boletasErrorMail.size() == 1){
					boletasADevolver.add(Utilidad.reemplazarMensajes(Mensajes.MAIL_BOLETAS_ERRORES_ENVIO_MAIL, boletasErrorMail.toString()));
				}else{
					boletasADevolver.add(Utilidad.reemplazarMensajes(Mensajes.MAIL_BOLETAS_ERRORES_ENVIO_MAIL_PLURAL, boletasErrorMail.toString()));
				}
			}
			
			if (!boletasErrorPara.isEmpty()) {
				if(boletasErrorPara.size() == 1){
					boletasADevolver.add(Utilidad.reemplazarMensajes(Mensajes.MAIL_BOLETAS_ERRORES_PARA, boletasErrorPara.toString()));
				}else{
					boletasADevolver.add(Utilidad.reemplazarMensajes(Mensajes.MAIL_BOLETAS_ERRORES_PARA_PLURAL, boletasErrorPara.toString()));
				}
			}
			
			if (!boletasExito.isEmpty()) {
				if(boletasExito.size() == 1){
					boletasADevolver.add(Utilidad.reemplazarMensajes(Mensajes.MAIL_BOLETAS_ENVIOS_EXITO, boletasExito.toString()));
				}else{
					boletasADevolver.add(Utilidad.reemplazarMensajes(Mensajes.MAIL_BOLETAS_ENVIOS_EXITO_PLURAL, boletasExito.toString()));
				}
			}
		}
		
		return boletasADevolver;
	}
	
	/**
	 * Se encarga de armar el mail y de enviarlo.
	 * @param documento
	 * @param shvBolBoletaSinValor
	 * @param idUsuarioEnvio
	 * @throws NegocioExcepcion
	 */
	public void mailDocumento(byte[] documento, ShvBolBoletaSinValor shvBolBoletaSinValor, String idUsuarioEnvio) throws NegocioExcepcion {
		
		if(Validaciones.isNullOrEmpty(shvBolBoletaSinValor.getBoleta().getEmail())){
			throw new CampoMailExcepcion(Mensajes.MAIL_ERROR_DESTINATARIO_PARA);
		}
		
		StringBuffer cuerpo = new StringBuffer("");
		ShvBolBoleta bolBoleta = shvBolBoletaSinValor.getBoleta();
		
		if(!Validaciones.isNullOrEmpty(bolBoleta.getEmailObservaciones())) {
			cuerpo.append(bolBoleta.getEmailObservaciones());
		}

		String asunto = shvBolBoletaSinValor.getEmpresa().getDescripcion() + " - " + Mensajes.MAIL_ASUNTO + String.valueOf(bolBoleta.getNumeroBoleta());
		
		String cc = "";
		UsuarioLdapDto usuarioLdapAnalista;
		if(!Validaciones.isNullOrEmpty(shvBolBoletaSinValor.getIdAnalista())){
			usuarioLdapAnalista = ldapServicio.buscarUsuarioPorUidEnMemoria(shvBolBoletaSinValor.getIdAnalista());
			if(!Validaciones.isNullOrEmpty(usuarioLdapAnalista.getMail())){
				cc = usuarioLdapAnalista.getMail() + ";";
			}
		}
		UsuarioLdapDto usuarioLdapCopropietario;
		if(!Validaciones.isNullOrEmpty(shvBolBoletaSinValor.getIdCopropietario())){
			usuarioLdapCopropietario = ldapServicio.buscarUsuarioPorUidEnMemoria(shvBolBoletaSinValor.getIdCopropietario());
			if(!Validaciones.isNullOrEmpty(usuarioLdapCopropietario.getMail())){
				cc += usuarioLdapCopropietario.getMail();
			}
		}

		Mail mail = new Mail(shvBolBoletaSinValor.getBoleta().getEmail(), asunto, cuerpo);
		List<Adjunto> listaAdjuntos = new ArrayList<Adjunto>();
		listaAdjuntos.add(new Adjunto(documento, asunto, Constantes.EXTENSION_ARCHIVO_ADJUNTO));
		mail.setAdjuntos(listaAdjuntos);
		mail.setDestinatarioCC(cc);
		bolBoleta.setEmail(shvBolBoletaSinValor.getBoleta().getEmail());
		if(!Validaciones.isNullOrEmpty(bolBoleta.getEmailObservaciones())){
			bolBoleta.setEmailEnvioObservaciones(bolBoleta.getEmailObservaciones());
		}
		bolBoleta.setEmailEnvioFecha(new Date());
		bolBoleta.setEmailEnvioDestino(shvBolBoletaSinValor.getBoleta().getEmail());

		UsuarioLdapDto usuarioEnvioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(idUsuarioEnvio);
		if(!Validaciones.isNullOrEmpty(usuarioEnvioLdap.getMail())){
			bolBoleta.setEmailEnvioUsuario(usuarioEnvioLdap.getUsuarioModificacion());
		}

		try {
			mailServicio.enviarMail(mail);
			bolBoleta.setEmailEnvioEstado(EnviarMailBoletaEstadoEnum.SI);
		} catch (MailException e) {
			bolBoleta.setEmailEnvioEstado(EnviarMailBoletaEstadoEnum.ERR);
			throw new MailExcepcion(Mensajes.MAIL_ERROR);
		} finally {
			shvBolBoletaSinValor.setBoleta(bolBoleta);
		}
	}
	
	/**
	 * Metodo para generar el PDF
	 * @throws NegocioExcepcion 
	 * @throws PersistenciaExcepcion 
	 */
	private byte[] generarPdfBoletaImprimir(List<ShvBolBoletaSinValor> listaBoletas) throws NegocioExcepcion, PersistenciaExcepcion{
		Document documento = new Document();
		ByteArrayOutputStream baos = null;
		try {
			
			GeneradorComprobantePago generador = new GeneradorComprobantePago();
			generador.setearListaBoletasSinValorModelo(listaBoletas);
			baos = new ByteArrayOutputStream();
			PdfWriter pw = PdfWriter.getInstance(documento, baos);
			documento.open();
			generador.crearContenidoPDF(documento, pw);
			documento.close();
			
		} catch (DocumentException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (IOException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (SQLException e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		}
		
		return baos.toByteArray();
	}
	
	/***************************************************************************
	 * Lista de datos modificados para el workflow
	 * ************************************************************************/
	public String getDatosModificados(DTO dto)  throws NegocioExcepcion {
		BoletaSinValorDto boletaSinValorDto = (BoletaSinValorDto) dto;
		
		try {
			BoletaSinValorFiltro boletaFiltro = new BoletaSinValorFiltro(boletaSinValorDto.getId().toString());
			Collection<ShvBolBoletaSinValor> listaBoletaModelo = boletaSinValorDao.buscarBoletasSinValor(boletaFiltro);
			ShvBolBoletaSinValor boletaSinValor = listaBoletaModelo.iterator().next();
				
			//Datos Modificados
			BoletaSinValorDto boletaSinValorDtoViejo = (BoletaSinValorDto) boletaSinValorMapeador.map(boletaSinValor);
			boletaSinValorDto.setImporte(boletaSinValorDto.getImporteParaComparacion());
			return Utilidad.generarDatosModificados(boletaSinValorDtoViejo,boletaSinValorDto,getListaModificarBoletaSinValor());
		
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(),e);
		} 
	}
	
	/**
	 * Creacion de Boleta
	 * Retorna la lista de atributos del Dto que debe mostrar para el alta de Boletas Sin Valor
	 * @return
	 */
	private List<String> getListaDatosOriginales(){
		List<String> lista = new ArrayList<String>();
		lista.add("empresa");
		lista.add("segmento");
		lista.add("codCliente");
		lista.add("codClienteSiebel");
		lista.add("razonSocial");
		lista.add("codClienteAgrupador");
		lista.add("razonSocialClienteAgrupador");
		lista.add("numeroHolding");
		lista.add("nombreHolding");
		lista.add("email");
		lista.add("nombreAnalista");
		lista.add("copropietario");
		lista.add("importe");
		lista.add("motivo");
		lista.add("operacionAsociada");
		lista.add("origen");
		lista.add("acuerdo");
		lista.add("bancoDeposito");
		lista.add("nroCuenta");
		lista.add("observaciones");
		lista.add("observacionMail");
		
		return lista;
	}
	
	/**
	 * Modificacion de boletas
	 * Retorna la lista de atributos del Dto que debe comparar para la modificacion de Boletas Sin Valor
	 * @return
	 */
	private List<String> getListaModificarBoletaSinValor() {
		List<String> lista = new ArrayList<String>();
		lista.add("timeStamp");
		lista.add("email");
		lista.add("copropietario");
		lista.add("motivo");
		lista.add("operacionAsociada");
		lista.add("observaciones");
		lista.add("observacionMail");
		
		return lista;
	}
	
	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/
	public IWorkflowBoletas getWorkflowBoletas() {
		return workflowBoletas;
	}

	public void setWorkflowBoletas(IWorkflowBoletas workflowBoletas) {
		this.workflowBoletas = workflowBoletas;
	}

	public IBoletaSinValorDao getBoletaSinValorDao() {
		return boletaSinValorDao;
	}

	public void setBoletaSinValorDao(IBoletaSinValorDao boletaSinValorDao) {
		this.boletaSinValorDao = boletaSinValorDao;
	}

	public IBoletaDao getBoletaDao() {
		return boletaDao;
	}

	public void setBoletaDao(IBoletaDao boletaDao) {
		this.boletaDao = boletaDao;
	}

	public IValorDao getValorDao() {
		return valorDao;
	}

	public void setValorDao(IValorDao valorDao) {
		this.valorDao = valorDao;
	}

	public BoletaSinValorMapeador getBoletaSinValorMapeador() {
		return boletaSinValorMapeador;
	}

	public void setBoletaSinValorMapeador(
			BoletaSinValorMapeador boletaSinValorMapeador) {
		this.boletaSinValorMapeador = boletaSinValorMapeador;
	}

	public IValorServicio getValorServicio() {
		return valorServicio;
	}

	public void setValorServicio(IValorServicio valorServicio) {
		this.valorServicio = valorServicio;
	}

	public IParametroServicio getParametroServicio() {
		return parametroServicio;
	}

	public void setParametroServicio(IParametroServicio parametroServicio) {
		this.parametroServicio = parametroServicio;
	}

}
