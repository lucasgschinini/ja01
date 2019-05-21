package ar.com.telecom.shiva.presentacion.facade.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import ar.com.telecom.shiva.base.comparador.ComparatorShvLgjNotificacion;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.EstadoNotificacionEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoContactoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoNotificacionEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.LdapExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionChequeAplicadoEnOperacionExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.bean.ArchivoByteArray;
import ar.com.telecom.shiva.negocio.bean.ValidacionesAuxiliar;
import ar.com.telecom.shiva.negocio.mapeos.BusquedaLegajosChequeRechazadoMapeador;
import ar.com.telecom.shiva.negocio.mapeos.ChequeRechazadoMapeador;
import ar.com.telecom.shiva.negocio.mapeos.LegajoChequeRechazadoCobroRelacionadoDocumentoMapeador;
import ar.com.telecom.shiva.negocio.mapeos.LegajoChequeRechazadoCobroRelacionadoMapeador;
import ar.com.telecom.shiva.negocio.mapeos.LegajoChequeRechazadoMapeador;
import ar.com.telecom.shiva.negocio.mapeos.LegajoChequeRechazadoNotificacionMapeador;
import ar.com.telecom.shiva.negocio.servicios.ILegajoChequeRechazadoNotificacionServicio;
import ar.com.telecom.shiva.negocio.servicios.ILegajoChequeRechazadoServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.servicios.IVistaSoporteServicio;
import ar.com.telecom.shiva.negocio.servicios.bean.Bean;
import ar.com.telecom.shiva.negocio.servicios.bean.ClienteBean;
import ar.com.telecom.shiva.negocio.servicios.bean.ConsultaSoporteResultadoBusquedaChequeRechazado;
import ar.com.telecom.shiva.negocio.workflow.definicion.EdicionTipoEnum;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowLegajosChequesRechazados;
import ar.com.telecom.shiva.negocio.workflow.servicios.util.IObservacionesWorkflowServicio;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaLegajoCobrosRelacionado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaLegajoCobrosRelacionadoSimplificado;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaLegajoDetalleDocumento;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjChequeRechazado;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjLegajoChequeRechazado;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjNotificacion;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjNotificacionCarta;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvLgjLegajoChequeRechazadoSimplificado;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvLgjLegajoChequeRechazadoSimplificadoWorkFlow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvLgjNotificacionSimplificado;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ConsultaSoporteResultadoBusquedaChequeRechazadoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.LegajoBusquedaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.LegajoChequeRechazadoCobroRelacionadoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.LegajoChequeRechazadoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.LegajoChequeRechazadoNotificacionDto;
import ar.com.telecom.shiva.presentacion.bean.dto.VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.json.JsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.LegajoChequeRechazadoJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.ConsultaSoporteBusquedaChequeRechazadoFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteBusquedaValoresFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteLegajoChequeRechazadoCobroRelacionadoFiltro;
import ar.com.telecom.shiva.presentacion.facade.ILegajoChequeRechazadoFacade;

public class LegajoChequeRechazadoFacadeImpl extends Facade implements ILegajoChequeRechazadoFacade {

	@Autowired
	private ILegajoChequeRechazadoServicio legajoChequeRechazadoServicio;
	@Autowired
	private ChequeRechazadoMapeador chequeRechazadoMapeador;
	@Autowired
	private IObservacionesWorkflowServicio observacionesWorkflowServicio;
	@Autowired
	private ILdapServicio ldapServicio;
	@Autowired
	private BusquedaLegajosChequeRechazadoMapeador busquedaLegajosChequeRechazadoMapeador;
	@Autowired 
	private IVistaSoporteServicio vistaSoporteServicio;
	@Autowired
	private LegajoChequeRechazadoCobroRelacionadoMapeador legajoChequeRechazadoCobroRelacionadoMapeador;
	@Autowired
	private LegajoChequeRechazadoCobroRelacionadoDocumentoMapeador legajoChequeRechazadoCobroRelacionadoDocumentoMapeador;
	@Autowired
	private 
	ILegajoChequeRechazadoNotificacionServicio legajoChequeRechazadoNotificacionServicio;
	@Autowired 
	private LegajoChequeRechazadoNotificacionMapeador legajoChequeRechazadoNotificacionMapeador;
	@Autowired
	private IWorkflowLegajosChequesRechazados workflowLegajoChequesRechazadosServicio;
	
	@Autowired
	private IParametroServicio parametroServicio; 
	
	private String xls = ".xls";
	public static final String XLS_CONTENT_TYPE = "application/vnd.ms-excel";
	public static final String CONTENT_DISPOSITION = "Content-Disposition";
	public static final String ATTACHMENT_FILENAME = "attachment; filename=";

	@Override
	public List<ConsultaSoporteResultadoBusquedaChequeRechazadoDto> listarCheques(ConsultaSoporteBusquedaChequeRechazadoFiltro chequeRechazadoFiltro, ValidacionesAuxiliar validacionesAuxiliar) throws NegocioExcepcion {
		List<ConsultaSoporteResultadoBusquedaChequeRechazadoDto> listaChequeRechazadoDto = new ArrayList<ConsultaSoporteResultadoBusquedaChequeRechazadoDto>();
		List<Bean> listaChequeRechazado = null;

		listaChequeRechazado = legajoChequeRechazadoServicio.listarCheques(chequeRechazadoFiltro, validacionesAuxiliar);

		for (Bean b : listaChequeRechazado) {
			ConsultaSoporteResultadoBusquedaChequeRechazado chequeRechazado = (ConsultaSoporteResultadoBusquedaChequeRechazado) b;
			try {
				listaChequeRechazadoDto.add(
					(ConsultaSoporteResultadoBusquedaChequeRechazadoDto) Utilidad.guionesNullDto(
						chequeRechazadoMapeador.map(chequeRechazado)
				));
			} catch (ShivaExcepcion e) {
				throw new NegocioExcepcion(e.getMessage(), e);
			}
		}
		return listaChequeRechazadoDto;
	}
	@Override
	public List<ConsultaSoporteResultadoBusquedaChequeRechazadoDto> paginarCheques(ConsultaSoporteBusquedaChequeRechazadoFiltro chequeRechazadoFiltro, ValidacionesAuxiliar validacionesAuxiliar) throws NegocioExcepcion {
		List<ConsultaSoporteResultadoBusquedaChequeRechazadoDto> listaChequeRechazadoDto = new ArrayList<ConsultaSoporteResultadoBusquedaChequeRechazadoDto>();
		List<Bean> listaChequeRechazado = null;

		listaChequeRechazado = legajoChequeRechazadoServicio.paginarCheques(chequeRechazadoFiltro, validacionesAuxiliar);

		for (Bean b : listaChequeRechazado) {
			ConsultaSoporteResultadoBusquedaChequeRechazado chequeRechazado = (ConsultaSoporteResultadoBusquedaChequeRechazado) b;
			try {
				listaChequeRechazadoDto.add(
					(ConsultaSoporteResultadoBusquedaChequeRechazadoDto) Utilidad.guionesNullDto(
						chequeRechazadoMapeador.map(chequeRechazado)
				));
			} catch (ShivaExcepcion e) {
				throw new NegocioExcepcion(e.getMessage(), e);
			}
		}
		return listaChequeRechazadoDto;
	}
	
	/**
	 * 
	 */
	@Override
	public LegajoChequeRechazadoJsonResponse crear(LegajoChequeRechazadoDto legajoChequeDto, UsuarioSesion userSesion) throws NegocioExcepcion {
		
		//Mapeo del Legajo
		ShvLgjLegajoChequeRechazado modeloLegajo = (ShvLgjLegajoChequeRechazado) defaultMapeador.map(legajoChequeDto,null);
		
		//Mapeo del Cheque
		ShvLgjChequeRechazado modeloCheque = new ShvLgjChequeRechazado();
		if (SistemaEnum.USUARIO.getDescripcion().equals(legajoChequeDto.getSistemaOrigen())) {
			
			ConsultaSoporteResultadoBusquedaChequeRechazadoDto chequeDto = new ConsultaSoporteResultadoBusquedaChequeRechazadoDto();
			chequeDto.setSistemaOrigen(SistemaEnum.USUARIO.getDescripcion());
			
			//Mapeo del detalle cliente
			ClienteDto clienteSiebelDto = new ClienteDto();
			clienteSiebelDto.setIdClienteLegado(legajoChequeDto.getIdCliente());
			clienteSiebelDto.setRazonSocial(legajoChequeDto.getDescripcionCliente());
			clienteSiebelDto.setCuit(legajoChequeDto.getCuitCliente());
			
			List<ClienteDto> clientes = new ArrayList<ClienteDto>();
			clientes.add(clienteSiebelDto);
			chequeDto.setClientes(clientes);
			
			legajoChequeDto.setChequeRechazado(chequeDto);
			modeloCheque = (ShvLgjChequeRechazado) chequeRechazadoMapeador.map(chequeDto, null);
		} else {
			if (legajoChequeDto.getValidarAnulacionInmediataDelValor() && SistemaEnum.SHIVA.getDescripcion().equals(legajoChequeDto.getSistemaOrigen())) {
				if (legajoChequeRechazadoServicio.validarChequeAplicadoEnOperacion(Long.parseLong(legajoChequeDto.getChequeRechazado().getIdInternoValor()))) {
					throw new ValidacionChequeAplicadoEnOperacionExcepcion();
				}
			}
			ConsultaSoporteResultadoBusquedaChequeRechazadoDto chequeDto = legajoChequeDto.getChequeRechazado();

			
			modeloCheque = (ShvLgjChequeRechazado) chequeRechazadoMapeador.map(chequeDto, null);
		}

		modeloLegajo.setChequeRechazado(modeloCheque);
		modeloLegajo = (ShvLgjLegajoChequeRechazado) legajoChequeRechazadoServicio.crear(modeloLegajo);

		LegajoChequeRechazadoJsonResponse legajoChequeRechazadoJsonResponse = new LegajoChequeRechazadoJsonResponse();
		LegajoChequeRechazadoDto dto = new LegajoChequeRechazadoDto();
		dto = this.completarBusquedaLegajoDto(modeloLegajo, userSesion);
		
		dto.setIdLegajoChequeRechazado(modeloLegajo.getIdLegajoChequeRechazado());
		dto.setEstado(modeloLegajo.getWorkflow().getEstado());
		dto.setEstadoDescripcion(modeloLegajo.getWorkflow().getEstado().descripcion());
		dto.setIdWorkflow(modeloLegajo.getWorkflow().getIdWorkflow());
		dto.setEdicionTipo(legajoChequeRechazadoServicio.validarEdicionSegunPerfilEstado(modeloLegajo.getIdAnalista(),modeloLegajo.getIdCopropietario(),modeloLegajo.getWorkflow().getEstado(), userSesion));
		dto.setCuitCliente(legajoChequeDto.getCuitCliente());
		dto.setIdbancoDeposito(legajoChequeDto.getIdbancoDeposito());
		legajoChequeRechazadoJsonResponse.setResultado(dto);
		
		
		return legajoChequeRechazadoJsonResponse;
	}

	public List<UsuarioLdapDto> buscarUsuariosPorPerfilSegmento(String empresa, String segmento, String perfil) throws LdapExcepcion {
		String perfilABuscar;
		List<UsuarioLdapDto> listaAnalistaFiltro = new ArrayList<UsuarioLdapDto>();
		if (Constantes.TODOS_LOS_SEGMENTOS.equals(segmento)) {
			perfilABuscar = perfil + "_" + empresa;
		} else {
			perfilABuscar = perfil + "_" + empresa + "_" + segmento;
		}
		Collection<UsuarioLdapDto> listaLdap = ldapServicio
				.buscarUsuariosPorPerfilEnMemoria(perfilABuscar);

		for (UsuarioLdapDto usuario : listaLdap) {
			listaAnalistaFiltro.add(usuario);
		}
		return listaAnalistaFiltro;
	}
	
	@Override
	public LegajoChequeRechazadoDto buscar(Long idLegajoChequeRechazado) throws NegocioExcepcion {
		return buscar(idLegajoChequeRechazado, null);
	}
	
	@Override
	public LegajoChequeRechazadoDto buscar(Long idLegajoChequeRechazado, UsuarioSesion user) throws NegocioExcepcion {
		ShvLgjLegajoChequeRechazado modeloLegajo = new ShvLgjLegajoChequeRechazado();

		modeloLegajo = (ShvLgjLegajoChequeRechazado) legajoChequeRechazadoServicio.buscar(idLegajoChequeRechazado);
		return this.completarBusquedaLegajoDto(modeloLegajo, user);
	}
	
	private LegajoChequeRechazadoDto completarBusquedaLegajoDto(ShvLgjLegajoChequeRechazado modeloLegajo, UsuarioSesion user) throws NegocioExcepcion {
		LegajoChequeRechazadoDto dto = (LegajoChequeRechazadoDto) defaultMapeador.map(modeloLegajo);

		ConsultaSoporteResultadoBusquedaChequeRechazadoDto chequeDto = null;
		if (SistemaEnum.USUARIO.getDescripcion().equals(dto.getSistemaOrigen())) {
			try {
				chequeDto = (ConsultaSoporteResultadoBusquedaChequeRechazadoDto) Utilidad.guionesNullDto(chequeRechazadoMapeador.map(modeloLegajo.getChequeRechazado()));
			} catch (ShivaExcepcion e) {
				Traza.error(getClass(), "Error al poner guiones", e);
				throw new NegocioExcepcion(e.getMessage(), e);
			}
			
			dto.setChequeRechazado(chequeDto);
			
		} else if (SistemaEnum.ICE.getDescripcion().equals(dto.getSistemaOrigen())) {
			
			try {
				chequeDto = (ConsultaSoporteResultadoBusquedaChequeRechazadoDto) Utilidad.guionesNullDto(chequeRechazadoMapeador.map(modeloLegajo.getChequeRechazado()));
			} catch (ShivaExcepcion e) {
				Traza.error(getClass(), "Error al poner guiones", e);
				throw new NegocioExcepcion(e.getMessage(), e);
			}

//				Map<String, BigDecimal> montos = legajoChequeRechazadoServicio.obtenerMontos(modeloLegajo.getChequeRechazado());
//
//				chequeDto .setMontoARevertir(Utilidad.formatCurrency(montos.get("montoARevertir"), true, true, 2));
			dto.setChequeRechazado(chequeDto);
		} else {
			VistaSoporteBusquedaValoresFiltro vistaSoporteBusquedaValoresFiltro = new VistaSoporteBusquedaValoresFiltro();
			vistaSoporteBusquedaValoresFiltro.setCobroOnlineCreditosShiva(false);
			vistaSoporteBusquedaValoresFiltro.setIdValor(modeloLegajo.getChequeRechazado().getIdValor().toString());
			List<VistaSoporteResultadoBusquedaValor> listaCheques = vistaSoporteServicio.buscarValores(vistaSoporteBusquedaValoresFiltro);
			if (listaCheques.isEmpty()) {
				throw new NegocioExcepcion("Legajo de cheque rechazado Shiva sin cheque asociado");
			} else if (listaCheques.size() > 1) {
				throw new NegocioExcepcion("Legajo de cheque rechazado Shiva con mas de 1 cheque asociado");
			} else {
				//TODO Mejorar para siguentes entregas u578936!!!
				ConsultaSoporteResultadoBusquedaChequeRechazado chequeRechazado = new ConsultaSoporteResultadoBusquedaChequeRechazado();
				chequeRechazado.setSistemaOrigen(SistemaEnum.SHIVA);
				chequeRechazado.setTipoCheque(listaCheques.get(0).getTipoValor());
				chequeRechazado.setIdTipoCheque(listaCheques.get(0).getIdTipoValor());
				chequeRechazado.setCodBancoOrigen(listaCheques.get(0).getIdBancoOrigen());
				chequeRechazado.setDescripcionBancoOrigen(listaCheques.get(0).getDescripcionBancoOrigen());
				chequeRechazado.setNroCheque(listaCheques.get(0).getReferenciaValor());
				chequeRechazado.setFechaDeposito(listaCheques.get(0).getFechaDeposito());
				chequeRechazado.setFechaRecepcion(listaCheques.get(0).getFechaIngresoRecibo() );
				chequeRechazado.setFechaVenc(listaCheques.get(0).getFechaVencimiento());
				chequeRechazado.setMoneda(MonedaEnum.PES);
				chequeRechazado.setImporte(listaCheques.get(0).getImporte());
				chequeRechazado.setAcuerdo(listaCheques.get(0).getIdAcuerdo());
				chequeRechazado.setBancoDeposito(listaCheques.get(0).getBancoDeposito());
				chequeRechazado.setCuentaDeposito(listaCheques.get(0).getCuentaDeposito());
				chequeRechazado.setEstado(listaCheques.get(0).getEstadoValor());
				chequeRechazado.setEmpresa(listaCheques.get(0).getEmpresa());
				chequeRechazado.setSegmento(listaCheques.get(0).getSegmento());
				chequeRechazado.setAnalista(listaCheques.get(0).getIdAnalista());
				chequeRechazado.setCopropietario(listaCheques.get(0).getIdCopropietario());
				chequeRechazado.setAnalistaTeamComercial(listaCheques.get(0).getIdAnalistaTeamComercial());
				chequeRechazado.setIdInternoValor(listaCheques.get(0).getIdValor());
				
				chequeRechazado.setSaldoDisponible(listaCheques.get(0).getSaldoDisponible());
				
				// Clientes
				if (!Validaciones.isNullEmptyOrDash(listaCheques.get(0).getIdClienteLegado())) {
					ClienteBean clienteSiebel = new ClienteBean();
					clienteSiebel.setIdClienteLegado(Long.parseLong(listaCheques.get(0).getIdClienteLegado()));
					clienteSiebel.setRazonSocial(listaCheques.get(0).getRazonSocialClienteAgrupador());
					chequeRechazado.getClienteCheques().add(clienteSiebel);
				}
				try {
					dto.setChequeRechazado(
						(ConsultaSoporteResultadoBusquedaChequeRechazadoDto) Utilidad.guionesNullDto(
							chequeRechazadoMapeador.map(chequeRechazado)
					));
				} catch (ShivaExcepcion e) {
					throw new NegocioExcepcion("Error al completar nulos");
				}
			}
			
			
		}
		
		if (!Validaciones.isObjectNull(user)) {
			dto.setEdicionTipo(legajoChequeRechazadoServicio.validarEdicionSegunPerfilEstado(modeloLegajo.getIdAnalista(),modeloLegajo.getIdCopropietario(),modeloLegajo.getWorkflow().getEstado(), user));
		}
		
		try{
			dto.setListaCobrosRelacionados(this.listarCobrosRelacionados(dto));
			dto.setListaDetalleDocumentos(this.listarCobroRelacionadoDocumentos(dto));
			dto.setListaNotificaciones(this.listarNotificaciones(modeloLegajo));
		} catch (NegocioExcepcion e) {
			throw new NegocioExcepcion("Error al completar null");
		}
		
		return dto;
	}

	@Override
	public List<LegajoBusquedaDto> listar(Filtro filtro, UsuarioSesion userSesion) throws NegocioExcepcion {
		List<LegajoBusquedaDto> listaLegajos = new ArrayList<LegajoBusquedaDto>();
		List<Bean> resultadoBusquedaListaLegajos = legajoChequeRechazadoServicio.listarBusqueda(filtro);
		for (Bean bean : resultadoBusquedaListaLegajos) {
			try {
				LegajoBusquedaDto legajoBusquedaDto = (LegajoBusquedaDto) Utilidad.guionesNullDto(busquedaLegajosChequeRechazadoMapeador.map(bean));
				
				if( filtro.getUsuarioLogeado().getIdUsuario().equals(legajoBusquedaDto.getIdAnalista()) || filtro.getUsuarioLogeado().getIdUsuario().equals(legajoBusquedaDto.getIdCopropietario()) ){
					
					//Busco el tipo de edicion, si el estado que retorna permite editar, mostramos el boton de edicion
					EdicionTipoEnum tipoEdicion = legajoChequeRechazadoServicio.validarEdicionSegunPerfilEstado(
							legajoBusquedaDto.getIdAnalista(), 
							legajoBusquedaDto.getIdCopropietario(), 
							Estado.getEnumByName(legajoBusquedaDto.getEstado()),
							filtro.getUsuarioLogeado());
					
					if (!EdicionTipoEnum.SIN_EDICION.equals(tipoEdicion)){
						legajoBusquedaDto.setEsModificablePorUsuario(true);
					} else {
						legajoBusquedaDto.setEsModificablePorUsuario(false);
					}
				} else {
					legajoBusquedaDto.setEsModificablePorUsuario(false);
				}
				//Estado actual del legajo
				Estado estado = Estado.getEnumByName(legajoBusquedaDto.getEstado());
				
				legajoBusquedaDto.setEsAnulable(legajoChequeRechazadoServicio.legajoEsAnulable(listarCobrosRelacionados(legajoBusquedaDto.getIdLegajo(),estado),estado)
						&& (userSesion.getIdUsuario().equals(legajoBusquedaDto.getIdAnalista()) || userSesion.getIdUsuario().equals(legajoBusquedaDto.getIdCopropietario())));
				
				listaLegajos.add(legajoBusquedaDto);
			} catch (ShivaExcepcion e) {
				throw new NegocioExcepcion("Error al completar null");
			}
		}
		return listaLegajos;
	}
	
	@Override
	public void anular(Long idLegajoChequeRechazado, UsuarioSesion userSesion) throws NegocioExcepcion {

		legajoChequeRechazadoServicio.anular(idLegajoChequeRechazado, userSesion.getIdUsuario());
	}
		
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public LegajoChequeRechazadoJsonResponse modificar(LegajoChequeRechazadoDto legajoChequeDto, UsuarioSesion userSesion) throws NegocioExcepcion {
		LegajoChequeRechazadoJsonResponse legajoChequeRechazadoJsonResponse = new LegajoChequeRechazadoJsonResponse();
		// El cambio de solapa es desde la solapa de cheques
		if ("0".equals(legajoChequeDto.getIdSolapaDesde())) {
		
			ShvLgjLegajoChequeRechazadoSimplificado modeloLegajoSimplificado = (ShvLgjLegajoChequeRechazadoSimplificado) ((LegajoChequeRechazadoMapeador) defaultMapeador).mapSimplificado(legajoChequeDto,null);
			legajoChequeRechazadoServicio.modificar(modeloLegajoSimplificado);
			legajoChequeRechazadoJsonResponse.setResultado(legajoChequeDto);
		}

		// Se dirige hasta la solapa de cobros relacionados asi que se debe cargar los cobros relacionados
		if (!Validaciones.isNullEmptyOrDash(legajoChequeDto.getIdSolapaHasta())) {
			if ("1".equals(legajoChequeDto.getIdSolapaHasta())) {
				legajoChequeRechazadoJsonResponse.setListaCobrosRelacionados(this.listarCobrosRelacionados(legajoChequeDto));
			} else if ("2".equals(legajoChequeDto.getIdSolapaHasta())) {
				legajoChequeRechazadoJsonResponse.setListaChequeDocumentos(this.listarCobroRelacionadoDocumentos(legajoChequeDto));
			} else if ("3".equals(legajoChequeDto.getIdSolapaHasta())) {
				legajoChequeRechazadoJsonResponse.setListaLegajoChequeRechazadoNotificacionDto(this.listarNotificaciones(legajoChequeDto));
			} else if("5".equals(legajoChequeDto.getIdSolapaHasta())) {
				legajoChequeRechazadoJsonResponse.setListaCobrosRelacionados(this.listarCobrosRelacionados(legajoChequeDto));
				legajoChequeRechazadoJsonResponse.setListaLegajoChequeRechazadoNotificacionDto(this.listarNotificaciones(legajoChequeDto));
				legajoChequeRechazadoJsonResponse.setListaChequeDocumentos(this.listarCobroRelacionadoDocumentos(legajoChequeDto));
			}
		}


		legajoChequeDto.setEdicionTipo(legajoChequeRechazadoServicio.validarEdicionSegunPerfilEstado(
				legajoChequeDto.getIdAnalista(),
				legajoChequeDto.getIdCopropietario(),
				legajoChequeDto.getEstado(),
			userSesion
		)); 
		
		legajoChequeRechazadoJsonResponse.setMontos(legajoChequeRechazadoServicio.obtenerMontos(
			legajoChequeDto.getIdLegajoChequeRechazado(),
			SistemaEnum.getEnumByName(legajoChequeDto.getSistemaOrigen()),
			legajoChequeDto.getEstado()
		));
		legajoChequeRechazadoJsonResponse.setResultado(legajoChequeDto);
		return legajoChequeRechazadoJsonResponse;
	}

	private List<LegajoChequeRechazadoNotificacionDto> listarNotificaciones(LegajoChequeRechazadoDto legajoChequeDto) throws NegocioExcepcion {
		
		List<LegajoChequeRechazadoNotificacionDto> listaLegajoChequeRechazadoNotificacionDto = new ArrayList<LegajoChequeRechazadoNotificacionDto>();
		List<ShvLgjNotificacionSimplificado> listaShvLgjNotificacion = legajoChequeRechazadoNotificacionServicio.listar(
				legajoChequeDto.getIdLegajoChequeRechazado());
		for (ShvLgjNotificacionSimplificado shvLgjNotificacionSimplificado : listaShvLgjNotificacion) {
			try {
				listaLegajoChequeRechazadoNotificacionDto.add(
					(LegajoChequeRechazadoNotificacionDto)Utilidad.guionesNullDto(legajoChequeRechazadoNotificacionMapeador.map(shvLgjNotificacionSimplificado))
				);
			} catch (ShivaExcepcion e) {
				Traza.error(getClass(), e.getMessage());
				throw new NegocioExcepcion(e.getMessage(), e);
			}
		}
		return listaLegajoChequeRechazadoNotificacionDto;
	}
	@Override
	public Map<String, String> obtenerMontos(long idLegajo, SistemaEnum sistema, Estado estado) throws NegocioExcepcion {
		return legajoChequeRechazadoServicio.obtenerMontos(idLegajo, sistema, estado);
	}
	@Override
	public LegajoChequeRechazadoJsonResponse revertirCobrosRelacionadosIce(LegajoChequeRechazadoDto legajoChequeRechazadoDto, UsuarioSesion userSesion)	throws NegocioExcepcion {
		
		LegajoChequeRechazadoJsonResponse legajoChequeRechazadoJsonResponse = new LegajoChequeRechazadoJsonResponse();
		
		List<Long> listaIdDetCobro = new ArrayList<Long>();
		for (LegajoChequeRechazadoCobroRelacionadoDto dto : legajoChequeRechazadoDto.getListaCobrosRelacionados()) {
			listaIdDetCobro.add(dto.getIdChequeRechazadoCobro());
		}
		
		//Revierto los cobros seleccionados ICE
		legajoChequeRechazadoServicio.revertirCobrosRelacionadosIce(listaIdDetCobro,legajoChequeRechazadoDto.getEstado(),legajoChequeRechazadoDto.getIdWorkflow(), userSesion);
		
		//Busco el wf actualizado, para setearlo en el dto
		ShvWfWorkflow wf = legajoChequeRechazadoServicio.buscarWorkflowActual(legajoChequeRechazadoDto.getIdWorkflow());
		legajoChequeRechazadoDto.setEstado(wf.getEstado());
		legajoChequeRechazadoDto.setEstadoDescripcion(wf.getEstado().descripcion());
		legajoChequeRechazadoJsonResponse.setListaCobrosRelacionados(this.listarCobrosRelacionados(legajoChequeRechazadoDto));
		legajoChequeRechazadoJsonResponse.setResultado(legajoChequeRechazadoDto);
		return legajoChequeRechazadoJsonResponse;
	}
	
	
	
	public List<LegajoChequeRechazadoCobroRelacionadoDto> listarCobrosRelacionados(LegajoChequeRechazadoDto legajoChequeDto) throws NegocioExcepcion {
		List<LegajoChequeRechazadoCobroRelacionadoDto> listaLegajoChequeRechazadoCobrosRelacionadosDto = new ArrayList<LegajoChequeRechazadoCobroRelacionadoDto>();
		try{
			VistaSoporteLegajoChequeRechazadoCobroRelacionadoFiltro filtro = new VistaSoporteLegajoChequeRechazadoCobroRelacionadoFiltro();
			filtro.setIdLegajoChequeRechazado(legajoChequeDto.getIdLegajoChequeRechazado());
			filtro.setEstado(legajoChequeDto.getEstado());
			List<VistaSoporteResultadoBusquedaLegajoCobrosRelacionado> listaVistaSoporteResultadoBusquedaLegajoCobrosRelacionado = legajoChequeRechazadoServicio.listarCobrosRelacionados(filtro);

			
			for (VistaSoporteResultadoBusquedaLegajoCobrosRelacionado cobroRelacionado : listaVistaSoporteResultadoBusquedaLegajoCobrosRelacionado) {
				listaLegajoChequeRechazadoCobrosRelacionadosDto.add((LegajoChequeRechazadoCobroRelacionadoDto) Utilidad.guionesNullDto(legajoChequeRechazadoCobroRelacionadoMapeador.map(cobroRelacionado)));
			}
			return listaLegajoChequeRechazadoCobrosRelacionadosDto;
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion("Error al completar null");
		}
	}
	/**
	 * Retorna cobros relacionados en una lista de cobros relacionados Dto
	 * @param legajoChequeDto
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto> listarCobroRelacionadoDocumentos(LegajoChequeRechazadoDto legajoChequeDto) throws NegocioExcepcion {
		List<VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto> listaVistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto = new ArrayList<VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto>();
		try{
			List<VistaSoporteResultadoBusquedaLegajoDetalleDocumento> listaVistaSoporteResultadoBusquedaLegajoDetalleDocumento =
				legajoChequeRechazadoServicio.listarCobroRelacionadoDocumentosBy(legajoChequeDto.getIdLegajoChequeRechazado());



			for (VistaSoporteResultadoBusquedaLegajoDetalleDocumento vistaSoporteResultadoBusquedaLegajoDetalleDocumento : listaVistaSoporteResultadoBusquedaLegajoDetalleDocumento) {
				VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto vistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto =
				(VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto) Utilidad.guionesNullDto(
					legajoChequeRechazadoCobroRelacionadoDocumentoMapeador.map(
						vistaSoporteResultadoBusquedaLegajoDetalleDocumento
				));
				listaVistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto.add(vistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto);
			}
			
			return listaVistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto;
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion("Error al recuperar documentos de cobros relacionados");
		}
	}
	@Override
	public ComprobanteDto crearAdjuntoLegajo(Long idLegajo, ComprobanteDto comprobante) throws NegocioExcepcion {
			return legajoChequeRechazadoServicio.insertarDocumentoAdjunto(idLegajo,comprobante);
	}
	
	@Override
	public void eliminarAdjuntoLegajo(Long idComprobante) throws NegocioExcepcion {
		legajoChequeRechazadoServicio.eliminarDocumentoAdjuntoDelLegajo(idComprobante);
	}
	@Override
	public ComprobanteDto buscarAdjuntoLegajo(Long idLegajo) throws NegocioExcepcion {
		return legajoChequeRechazadoServicio.buscarAdjuntoLegajo(idLegajo);
	}
	@Override
	public JsonResponse verificarReversionShivaEnProceso(Long idOperacion) throws NegocioExcepcion {
		JsonResponse jsonResponse = new JsonResponse();

		VistaSoporteResultadoBusquedaLegajoCobrosRelacionadoSimplificado simplificado = legajoChequeRechazadoServicio.obtenerLegajoChequeRechazadoCobrosRelacionadosEstadoReversionShivaBy(idOperacion);

		if (
			!Validaciones.isNullEmptyOrDash(simplificado.getEstadoReversionShiva()) &&
			!Estado.DES_ANULADO.name().equals(simplificado.getEstadoReversionShiva())
		) {
			jsonResponse.setSuccess(false);
			jsonResponse.setErrors(Utilidad.reemplazarMensajes(
				Propiedades.MENSAJES_PROPIEDADES.getString("legajo.cheques.rechazados.legajo.cobroRelacionado.no.revertir"), 
				simplificado.getIdOperacion(),
				simplificado.getIdOperacionDescobro(),
				Estado.getEnumByName(simplificado.getEstadoReversionShiva()).descripcion()
			));
		} else {
			jsonResponse.setSuccess(true);
		}
		return jsonResponse;
	}
	@Override
	public LegajoChequeRechazadoJsonResponse confirmarFinReversion(LegajoChequeRechazadoDto legajoChequeRechazadoDto, UsuarioSesion userSesion) throws NegocioExcepcion {

		LegajoChequeRechazadoJsonResponse legajoChequeRechazadoJsonResponse = new LegajoChequeRechazadoJsonResponse();

		List<VistaSoporteResultadoBusquedaLegajoCobrosRelacionado> listaCobrosRelacionados;
		
		listaCobrosRelacionados = listarCobrosRelacionados(legajoChequeRechazadoDto.getIdLegajoChequeRechazado(), legajoChequeRechazadoDto.getEstado());
		
		legajoChequeRechazadoServicio.confirmarFinReversion(legajoChequeRechazadoDto.getIdLegajoChequeRechazado(),listaCobrosRelacionados,userSesion);
		
		ShvWfWorkflow wf = legajoChequeRechazadoServicio.buscarWorkflowActual(legajoChequeRechazadoDto.getIdWorkflow());
		
		Estado estado = wf.getEstado();
	
		legajoChequeRechazadoDto.setEstado(estado);
		legajoChequeRechazadoDto.setEstadoDescripcion(estado.descripcion());
		legajoChequeRechazadoJsonResponse.setListaCobrosRelacionados(listarCobrosRelacionados(legajoChequeRechazadoDto));
		legajoChequeRechazadoJsonResponse.setResultado(legajoChequeRechazadoDto);
		/// Ver 
		legajoChequeRechazadoDto.setEdicionTipo(legajoChequeRechazadoServicio.validarEdicionSegunPerfilEstado(
			legajoChequeRechazadoDto.getIdAnalista(),
			legajoChequeRechazadoDto.getIdCopropietario(),
			estado,
			userSesion
		)); 
		return legajoChequeRechazadoJsonResponse;
	}
	
	/**
	 * Retorna los cobros relacionados.
	 * @param idLegajo
	 * @param estado
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<VistaSoporteResultadoBusquedaLegajoCobrosRelacionado> listarCobrosRelacionados (Long idLegajo, Estado estado) throws NegocioExcepcion{
		
		VistaSoporteLegajoChequeRechazadoCobroRelacionadoFiltro filtro = new VistaSoporteLegajoChequeRechazadoCobroRelacionadoFiltro();
		filtro.setIdLegajoChequeRechazado(idLegajo);
		filtro.setEstado(estado);
		List<VistaSoporteResultadoBusquedaLegajoCobrosRelacionado> listaCobrosRelacionados;
		try {
			listaCobrosRelacionados = legajoChequeRechazadoServicio.listarCobrosRelacionados(filtro);
		} catch (ShivaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return listaCobrosRelacionados;
	}

	/**
	 * Retorna las notificaciones.
	 * @param idLegajo
	 * @param estado
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<LegajoChequeRechazadoNotificacionDto> listarNotificaciones (ShvLgjLegajoChequeRechazado modelo) throws NegocioExcepcion{
		List<LegajoChequeRechazadoNotificacionDto> listaNotificaciones = new ArrayList<LegajoChequeRechazadoNotificacionDto>();
		List<ShvLgjNotificacion> lista = new ArrayList<ShvLgjNotificacion>(modelo.getNotificaciones());
		
		Collections.sort(lista, new ComparatorShvLgjNotificacion());
		for (ShvLgjNotificacion notificacion : lista) {
			
			if (EstadoNotificacionEnum.ACTIVA.equals(notificacion.getEstado())) {
				try {
					listaNotificaciones.add((LegajoChequeRechazadoNotificacionDto) Utilidad.guionesNullDto(legajoChequeRechazadoNotificacionMapeador.map(notificacion)));
				} catch (ShivaExcepcion e) {
					throw new NegocioExcepcion(e.getMessage(), e);
				}
			}
		}
		return listaNotificaciones;
	}

	/**
	 * Retorna cobros relacionados en una lista de cobros relacionados Dto
	 * @param legajoChequeDto
	 * @return
	 * @throws NegocioExcepcion
	 */
	@Override
	public List<VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto> listarDocumentos(LegajoChequeRechazadoDto legajoChequeDto) throws NegocioExcepcion {
//		List<VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto> listaLegajoChequeRechazadoDetalleDocumentoDto = new ArrayList<VistaSoporteResultadoBusquedaLegajoDetalleDocumentoDto>();
//		try{
////			List<VistaSoporteResultadoBusquedaLegajoDetalleDocumento> listaVistaSoporteResultadoBusquedaLegajoDetalleDocumento = 
////				legajoChequeRechazadoServicio.listarDocumentosRelacionados(legajoChequeDto.getIdLegajoChequeRechazado());
////
////			
////			for (VistaSoporteResultadoBusquedaLegajoCobrosRelacionado cobroRelacionado : listaVistaSoporteResultadoBusquedaLegajoCobrosRelacionado) {
////				listaLegajoChequeRechazadoCobrosRelacionadosDto.add((LegajoChequeRechazadoCobroRelacionadoDto) Utilidad.guionesNullDto(legajoChequeRechazadoCobroRelacionadoMapeador.map(cobroRelacionado)));
////			}
////			return listaLegajoChequeRechazadoCobrosRelacionadosDto;
//			return null;
//		} catch (ShivaExcepcion e) {
//			throw new NegocioExcepcion("Error al completar null");
//		}
		return null;
	}
	public LegajoChequeRechazadoDto enviarALegales(Long idLegajoChequeRechazado, UsuarioSesion userSesion) throws NegocioExcepcion{
		ShvLgjLegajoChequeRechazado legajoChequeRechazado = legajoChequeRechazadoServicio.enviarALegales(idLegajoChequeRechazado, userSesion.getIdUsuario());
		LegajoChequeRechazadoDto legajoChequeRechazadoDto = new LegajoChequeRechazadoDto();

		if(!Validaciones.isObjectNull(legajoChequeRechazado)){
			legajoChequeRechazadoDto.setEstado(legajoChequeRechazado.getWorkflow().getEstado());
			legajoChequeRechazadoDto.setEstadoDescripcion(legajoChequeRechazado.getWorkflow().getEstado().descripcion());
			legajoChequeRechazadoDto.setUbicacion(String.valueOf(legajoChequeRechazado.getUbicacion().getIndice()));
			legajoChequeRechazadoDto.setUbicacionDesc(legajoChequeRechazado.getUbicacion().getDescripcion());
			legajoChequeRechazadoDto.setEdicionTipo(legajoChequeRechazadoServicio.validarEdicionSegunPerfilEstado(
					legajoChequeRechazado.getIdAnalista(),
					legajoChequeRechazado.getIdCopropietario(),
					legajoChequeRechazado.getWorkflow().getEstado(),
					userSesion
				)); 
			}
		return legajoChequeRechazadoDto;
	}
	
	@Override
	public LegajoChequeRechazadoDto reembolsarCheque(Long idLegajoChequeRechazado, UsuarioSesion userSesion) throws NegocioExcepcion{
		ShvLgjLegajoChequeRechazado legajoChequeRechazado = legajoChequeRechazadoServicio.reembolsarCheque(idLegajoChequeRechazado, userSesion.getIdUsuario());
		LegajoChequeRechazadoDto legajoChequeRechazadoDto = new LegajoChequeRechazadoDto();
		
		if(!Validaciones.isObjectNull(legajoChequeRechazado)){
			legajoChequeRechazadoDto.setEstado(legajoChequeRechazado.getWorkflow().getEstado());
			legajoChequeRechazadoDto.setEstadoDescripcion(legajoChequeRechazado.getWorkflow().getEstado().descripcion());
			legajoChequeRechazadoDto.setUbicacion(String.valueOf(legajoChequeRechazado.getUbicacion().getIndice()));
			legajoChequeRechazadoDto.setUbicacionDesc(legajoChequeRechazado.getUbicacion().getDescripcion());
			legajoChequeRechazadoDto.setEdicionTipo(legajoChequeRechazadoServicio.validarEdicionSegunPerfilEstado(
				legajoChequeRechazado.getIdAnalista(),
				legajoChequeRechazado.getIdCopropietario(),
				legajoChequeRechazado.getWorkflow().getEstado(),
				userSesion
			)); 
		}
		return legajoChequeRechazadoDto;
	}
	
	@Override
	public ArchivoByteArray generarPdfCartaLegajo(Long id) throws NegocioExcepcion{
		return legajoChequeRechazadoNotificacionServicio.generarCartaLegajoPdf(id);
	}	
	@Override
	public LegajoChequeRechazadoJsonResponse guardarContacto(LegajoChequeRechazadoNotificacionDto legajoChequeRechazadoNotificacionDto, UsuarioSesion userSesion) throws NegocioExcepcion {
		
		LegajoChequeRechazadoJsonResponse legajoChequeRechazadoJsonResponse = new LegajoChequeRechazadoJsonResponse();
		ShvLgjNotificacionSimplificado modelo = (ShvLgjNotificacionSimplificado) legajoChequeRechazadoNotificacionMapeador.map(legajoChequeRechazadoNotificacionDto, null);

		if (
			!Validaciones.isObjectNull(legajoChequeRechazadoNotificacionDto.getCarta()) &&
			TipoContactoEnum.CARTA.equals(modelo.getTipoContacto()) &&
			TipoNotificacionEnum.NOTIFICACION_REEMBOLSO.equals(modelo.getTipoNotificacion())
		) {
			modelo.setCarta((ShvLgjNotificacionCarta)legajoChequeRechazadoNotificacionMapeador.mapCarta(legajoChequeRechazadoNotificacionDto.getCarta(), null));
			modelo.getCarta().setAnalistaFirmante(
				this.parametroServicio.getValorTexto(
					Utilidad.reemplazarMensajes(
						Propiedades.MENSAJES_PROPIEDADES.getString(ConstantesCobro.FIRMANTE_DOCUMENTO_SEGMENTO),
						legajoChequeRechazadoNotificacionDto.getSegmentoLegajo()
			)));
		}
		modelo.setUsuarioAlta(userSesion.getIdUsuario());
		modelo.setUsuarioDesc(userSesion.getNombreCompleto());
		
		modelo = legajoChequeRechazadoNotificacionServicio.crear(modelo, legajoChequeRechazadoNotificacionDto.getEstadoLegajo());
		
		List<ShvLgjNotificacionSimplificado> listaShvLgjNotificacion = legajoChequeRechazadoNotificacionServicio.listar(
			legajoChequeRechazadoNotificacionDto.getIdLegajoChequeRechazado()
		);
		
		legajoChequeRechazadoJsonResponse.setListaLegajoChequeRechazadoNotificacionDto(
			new ArrayList<LegajoChequeRechazadoNotificacionDto>()
		);
		
		for (ShvLgjNotificacionSimplificado shvLgjNotificacionSimplificado : listaShvLgjNotificacion) {
			if(EstadoNotificacionEnum.ACTIVA.equals(shvLgjNotificacionSimplificado.getEstado())){
				try {
					
					legajoChequeRechazadoJsonResponse.getListaLegajoChequeRechazadoNotificacionDto().add(
						(LegajoChequeRechazadoNotificacionDto)Utilidad.guionesNullDto(legajoChequeRechazadoNotificacionMapeador.map(shvLgjNotificacionSimplificado))
					);
				} catch (ShivaExcepcion e) {
					Traza.error(getClass(), e.getMessage());
					throw new NegocioExcepcion(e.getMessage(), e);
				}
			}
		}
		ShvLgjLegajoChequeRechazadoSimplificadoWorkFlow shvLgjLegajoChequeRechazadoSimplificadoWorkFlow = 
			(ShvLgjLegajoChequeRechazadoSimplificadoWorkFlow) this.legajoChequeRechazadoServicio.buscarSimplificadoConWorkFlow(
			legajoChequeRechazadoNotificacionDto.getIdLegajoChequeRechazado()
		);
		Estado estado = shvLgjLegajoChequeRechazadoSimplificadoWorkFlow.getWorkflow().getEstado();
		legajoChequeRechazadoJsonResponse.getResultado().setEstado(estado);
		legajoChequeRechazadoJsonResponse.getResultado().setEstadoDescripcion(estado.descripcion());
		
		legajoChequeRechazadoJsonResponse.getResultado().setEdicionTipo(legajoChequeRechazadoServicio.validarEdicionSegunPerfilEstado(
			shvLgjLegajoChequeRechazadoSimplificadoWorkFlow.getIdAnalista(),
			shvLgjLegajoChequeRechazadoSimplificadoWorkFlow.getIdCopropietario(),
			estado,
			userSesion
		));
		legajoChequeRechazadoJsonResponse.getResultado().setUbicacion(String.valueOf(shvLgjLegajoChequeRechazadoSimplificadoWorkFlow.getUbicacion().getIndice()));
		legajoChequeRechazadoJsonResponse.getResultado().setUbicacionDesc(shvLgjLegajoChequeRechazadoSimplificadoWorkFlow.getUbicacion().getDescripcion());
		return legajoChequeRechazadoJsonResponse;
	}
	
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public LegajoChequeRechazadoJsonResponse desistir(Long idLegajoChequeRechazado, UsuarioSesion userSesion) throws NegocioExcepcion {
		
		LegajoChequeRechazadoJsonResponse legajoChequeRechazadoJsonResponse = new LegajoChequeRechazadoJsonResponse();
		LegajoChequeRechazadoDto legajoChequeRechazadoDto = new LegajoChequeRechazadoDto();
		
		ShvLgjLegajoChequeRechazado legajoChequeRechazado = legajoChequeRechazadoServicio.desistir(idLegajoChequeRechazado, userSesion.getIdUsuario());
		
		legajoChequeRechazadoDto.setEdicionTipo(legajoChequeRechazadoServicio.validarEdicionSegunPerfilEstado(
				legajoChequeRechazado.getIdAnalista(),
				legajoChequeRechazado.getIdCopropietario(),
				legajoChequeRechazado.getWorkflow().getEstado(),
				userSesion
				)); 
		legajoChequeRechazadoDto.setEstado(legajoChequeRechazado.getWorkflow().getEstado());
		legajoChequeRechazadoDto.setEstadoDescripcion(legajoChequeRechazado.getWorkflow().getEstado().descripcion());
		legajoChequeRechazadoJsonResponse.setResultado(legajoChequeRechazadoDto);
		return legajoChequeRechazadoJsonResponse;
	}

	
	@Transactional(readOnly = false, rollbackFor = { Exception.class, WorkflowExcepcion.class }, propagation = Propagation.REQUIRED)
	public LegajoChequeRechazadoJsonResponse cerrar(Long idLegajoChequeRechazado, UsuarioSesion userSesion) throws NegocioExcepcion {
		
		LegajoChequeRechazadoJsonResponse legajoChequeRechazadoJsonResponse = new LegajoChequeRechazadoJsonResponse();
		LegajoChequeRechazadoDto legajoChequeRechazadoDto = new LegajoChequeRechazadoDto();
		
		ShvLgjLegajoChequeRechazado legajoChequeRechazado = legajoChequeRechazadoServicio.cerrar(idLegajoChequeRechazado, userSesion.getIdUsuario());
		
		legajoChequeRechazadoDto.setEdicionTipo(legajoChequeRechazadoServicio.validarEdicionSegunPerfilEstado(
				legajoChequeRechazado.getIdAnalista(),
				legajoChequeRechazado.getIdCopropietario(),
				legajoChequeRechazado.getWorkflow().getEstado(),
				userSesion
				)); 
		legajoChequeRechazadoDto.setEstado(legajoChequeRechazado.getWorkflow().getEstado());
		legajoChequeRechazadoDto.setEstadoDescripcion(legajoChequeRechazado.getWorkflow().getEstado().descripcion());
		legajoChequeRechazadoJsonResponse.setResultado(legajoChequeRechazadoDto);
		
		return legajoChequeRechazadoJsonResponse;
	}
	@Override
	public LegajoChequeRechazadoJsonResponse anularContacto(Long idNotificacion, Long idLegajoChequeRechazado) throws NegocioExcepcion {
		LegajoChequeRechazadoJsonResponse legajoChequeRechazadoJsonResponse = new LegajoChequeRechazadoJsonResponse();
		legajoChequeRechazadoNotificacionServicio.anular(idNotificacion);
		
		List<ShvLgjNotificacionSimplificado> listaShvLgjNotificacion = legajoChequeRechazadoNotificacionServicio.listar(
			idLegajoChequeRechazado
		);
		
		legajoChequeRechazadoJsonResponse.setListaLegajoChequeRechazadoNotificacionDto(
			new ArrayList<LegajoChequeRechazadoNotificacionDto>()
		);
		
		for (ShvLgjNotificacionSimplificado shvLgjNotificacionSimplificado : listaShvLgjNotificacion) {
			if(EstadoNotificacionEnum.ACTIVA.equals(shvLgjNotificacionSimplificado.getEstado())){
				try {
					legajoChequeRechazadoJsonResponse.getListaLegajoChequeRechazadoNotificacionDto().add(
						(LegajoChequeRechazadoNotificacionDto)Utilidad.guionesNullDto(legajoChequeRechazadoNotificacionMapeador.map(shvLgjNotificacionSimplificado))
					);
				} catch (ShivaExcepcion e) {
					Traza.error(getClass(), e.getMessage());
					throw new NegocioExcepcion(e.getMessage(), e);
				}
			}
		}
		return legajoChequeRechazadoJsonResponse;
	}
	
	public void exportarDetalleLegajo(HttpServletResponse response,Long idLegajoChequeRechazado) throws NegocioExcepcion {
		
		try {
			ByteArrayOutputStream salida = new ByteArrayOutputStream();

			LegajoChequeRechazadoDto legajoChequeRechazadoDto = this.buscar(idLegajoChequeRechazado);
			
			HSSFWorkbook workbook = legajoChequeRechazadoServicio.exportarDetalleLegajo(legajoChequeRechazadoDto);;

			workbook.write(salida);

			response.setContentType(XLS_CONTENT_TYPE);
			response.setContentLength(salida.size());
			response.setHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME + "ID Legajo " + workbook.getSheetName(0) + xls);

			FileCopyUtils.copy(salida.toByteArray(), response.getOutputStream());

		} catch (IOException e) {
			new NegocioExcepcion(e.getMessage(),e);	
		}
	}
}
