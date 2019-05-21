package ar.com.telecom.shiva.presentacion.controlador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.com.telecom.shiva.base.comparador.UsuarioLdapDtoComparator;
import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.MotivoValorEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.ICombosServicio;
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBanco;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBancoCuenta;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamJurisdiccion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamOrigen;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoComprobante;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoLetraComprobante;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoRetencionImpuesto;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoValor;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.BoletaSinValorDto;
import ar.com.telecom.shiva.presentacion.bean.dto.GestionDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;
import ar.com.telecom.shiva.presentacion.bean.dto.json.SelectOptionJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;

public class Controlador   {

	private static final String ALTA_BOLETA_VIEW = "valor/valores-boletas-alta";
	private static final String ALTA_AVISO_VIEW = "valor/valores-avisos-alta";
	protected static final String BOLETA_ACTUALIZACION_OK_AC = "boleta-actualizacion-ac";
	protected static final String VALOR_ACTUALIZACION_OK_VIEW = "valor/valores-actualizacion-exitosa";
	protected static final String VALOR_ACTUALIZACION_OK_AC = "valores-actualizacion-ac";
	protected static final String VALOR_ACTUALIZACION_BUSQUEDA_OK_VIEW = "valor/valores-actualizacion-busqueda-exitosa";
	protected static final String ACTUALIZACION_OK_VIEW = "template/actualizacion-exitosa";
	protected static final String ERROR_CONCURRENCIA_VIEW = "template/error-concurrencia";
	protected static final String VALOR_AUTORIZACION_OK_VIEW = "valor/valores-autoriza-exitosa";
	protected static final String BANDEJA_ENTRADA_VIEW_GET = "bandeja-de-entrada";
	protected static final String BUSQUEDA_DESCOBRO_VIEW_GET = "descobro-busqueda";
	protected static final String BANDEJA_ENTRADA_VIEW_POST = "regresar-bandeja-de-entrada";
	
	protected static final String SELECT_EMPRESAS = "empresas";
	protected static final String SELECT_SEGMENTOS = "segmentos";
	protected static final String SELECT_ACUERDOS = "acuerdos";
	protected static final String SELECT_BANCOS = "bancos";
	protected static final String SELECT_CUENTAS = "cuentas";
	protected static final String SELECT_ORIGENES = "origenes";
	protected static final String SELECT_COPROPIETARIOS = "copropietarios";
	protected static final String SELECT_MOTIVOS = "motivos";
	protected static final String SELECT_ESTADOS = "estados";
	protected static final String SELECT_TIPO_VALOR = "tipoValores";
	protected static final String SELECT_BANCO_ORIGENES = "bancoOrigenes";

	private static final String VALOR_PARA_BOLETA_CHEQUE = TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoValorString();
	private static final String VALOR_PARA_BOLETA_CHEQUE_PD = TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getIdTipoValorString();
	private static final String VALOR_PARA_BOLETA_EFECTIVO = TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO.getIdTipoValorString();
	
	private static final String ORIGEN_CAJERO_PAGADOR = "1";

	@Autowired
	protected ICombosServicio combosServicio;
	@Autowired
	private IValorServicio boletaConValorServicio;
	
	
	/**
	 * Carga la lista de codigos de legados siebel para autocompletar
	 * 
	 * @param mv
	 * @param listaCompleta
	 */
	protected void cargarCodigoLegadoSiebel(ModelAndView mv,
			String listaCompleta) throws NegocioExcepcion {

		String listaLegadoRazonSocial = "";
		String listaCodigoLegado = "";
		String listaCodigoClienteAgrupador = "";
		String listaRazonSocial = "";

		// String listaCompleta =
		// boletaSinValorServicio.listar10CodigosClienteLegado();
		if (!Validaciones.isNullOrEmpty(listaCompleta)) {
			for (String codigoCompleto : listaCompleta.split("\\"+Constantes.SEPARADOR_PIPE)) {
				
				//Formula("ID_CLIENTE_LEGADO||' - '||RAZON_SOCIAL_CLIENTE_AGRUPADOR||';'||ID_CLIENTE_AGRUPADOR")
				//Ejemplo: 97747 - BECPEREZ SILPEREZ;111111
				String[] codigo = codigoCompleto.split(";");
				if (codigo.length == 2) {
					listaLegadoRazonSocial += "'" + codigo[0] + "',";
					listaCodigoClienteAgrupador += codigo[1] + ",";
	
					String[] codigoLegadoRazonSocial = codigo[0].split("-");
					listaCodigoLegado += codigoLegadoRazonSocial[0].trim() + ",";
					listaRazonSocial += codigoLegadoRazonSocial[1].trim() + ";";
				}
			}
			
			// Separo todos los valores conseguidos
			if (!Validaciones.isNullOrEmpty(listaLegadoRazonSocial)) {
				listaLegadoRazonSocial = listaLegadoRazonSocial.substring(0,
						listaLegadoRazonSocial.length() - 1);
				
				listaCodigoLegado = listaCodigoLegado.substring(0,
						listaCodigoLegado.length() - 1);
				
				listaCodigoClienteAgrupador = listaCodigoClienteAgrupador
						.substring(0, listaCodigoClienteAgrupador.length() - 1);
				
				listaRazonSocial = listaRazonSocial.substring(0,
						listaRazonSocial.length() - 1);
			}
		}

		String cadenaListaLegadoRazonSocial = "[" + listaLegadoRazonSocial + "]";
		mv.addObject("listaLegadoRazonSocial", cadenaListaLegadoRazonSocial);
		mv.addObject("listaCodigoClienteAgrupador", listaCodigoClienteAgrupador);
		mv.addObject("listaCodigoLegado", listaCodigoLegado);
		mv.addObject("listaRazonSocialClienteAgrupador", listaRazonSocial);
	}

	/**
	 * Carga el combo Segmentos. Si la lista tiene mas de un elemento habilita
	 * la opcion "Seleccione un item..."
	 * 
	 * @param mv
	 * @param dto
	 * @param userSesion
	 * @throws NegocioExcepcion
	 */
	protected void cargarSegmento(ModelAndView mv, GestionDto dto,
			UsuarioSesion userSesion) throws NegocioExcepcion {
		if (!Validaciones.isNullOrEmpty(dto.getIdEmpresa())) {
			List<ShvParamSegmento> listaSegmentos = combosServicio
					.listarSegmentoPorEmpresaYUsuario(dto.getIdEmpresa(),
							userSesion);
			mv.addObject(SELECT_SEGMENTOS, listaSegmentos);
			if (listaSegmentos.size() > 1) {
				dto.setComboSegmento(true);
			} else {
				dto.setComboSegmento(false);
			}
		}
	}

	/**
	 * 
	 * @param mv
	 * @param filtro
	 * @param userSesion
	 * @throws NegocioExcepcion
	 */
	protected void cargarSegmento(ModelAndView mv, Filtro filtro,
			UsuarioSesion userSesion) throws NegocioExcepcion {
		if (!Validaciones.isNullOrEmpty(filtro.getEmpresa())) {
			List<ShvParamSegmento> listaSegmentos = combosServicio
					.listarSegmentoPorEmpresaYUsuario(filtro.getEmpresa(),
							userSesion);
			mv.addObject(SELECT_SEGMENTOS, listaSegmentos);
			if (listaSegmentos.size() > 1) {
				mv.addObject("comboSegmento", true);
			} else {
				mv.addObject("comboSegmento", false);
			}
		}
	}

	/**
	 * Carga el combo Acuerdos. Si la lista tiene mas de un elemento habilita la
	 * opcion "Seleccione un item..."
	 * 
	 * @param mv
	 * @param boletaSinValorDto
	 */
	protected void cargarOrigen(ModelAndView mv,
			BoletaSinValorDto boletaSinValorDto) throws NegocioExcepcion {
		if (!Validaciones.isNullOrEmpty(boletaSinValorDto.getIdOrigen())) {
			List<ShvParamOrigen> listaOrigenes = combosServicio
					.listarOrigenPorSegmento(boletaSinValorDto.getIdEmpresa(),
							boletaSinValorDto.getIdSegmento());
			mv.addObject(SELECT_ORIGENES, listaOrigenes);
			if (listaOrigenes.size() > 1) {
				boletaSinValorDto.setComboOrigen(true);
			} else {
				boletaSinValorDto.setComboOrigen(false);
			}
		}
	}

	/**
	 * Carga el combo Acuerdos. Si la lista tiene mas de un elemento habilita la
	 * opcion "Seleccione un item..."
	 * 
	 * @param mv
	 * @param boletaSinValorDto
	 */
	protected void cargarAcuerdo(ModelAndView mv, BoletaSinValorDto boletaSinValorDto) throws NegocioExcepcion {
		if (!Validaciones.isNullOrEmpty(boletaSinValorDto.getIdOrigen())) {
			List<ShvParamAcuerdo> listaAcuerdo = combosServicio.actualizarAcuerdoPorIdOrigenBoletaSinValor(
																									boletaSinValorDto.getIdEmpresa(),
																									boletaSinValorDto.getIdSegmento(),
																									boletaSinValorDto.getIdOrigen());
			mv.addObject(SELECT_ACUERDOS, listaAcuerdo);
			if (listaAcuerdo.size() > 1) {
				boletaSinValorDto.setComboAcuerdo(true);
			} else {
				boletaSinValorDto.setComboAcuerdo(false);
			}
		}
	}

	/**
	 * Carga el combo Bancos. Si la lista tiene mas de un elemento habilita la
	 * opcion "Seleccione un item..."
	 * 
	 * @param mv
	 * @param boletaSinValorDto
	 */
	protected void cargarBanco(ModelAndView mv,
			BoletaSinValorDto boletaSinValorDto) throws NegocioExcepcion {
		if (!Validaciones.isNullOrEmpty(boletaSinValorDto.getIdOrigen())) {
			List<ShvParamBanco> listaBanco = combosServicio
					.actualizarBancoPorIdOrigenBoletaSinValor(boletaSinValorDto.getIdEmpresa(),
							boletaSinValorDto.getIdSegmento(),
							boletaSinValorDto.getIdOrigen());
			mv.addObject(SELECT_BANCOS, listaBanco);
			if (listaBanco.size() > 1) {
				boletaSinValorDto.setComboBanco(true);
			} else {
				boletaSinValorDto.setComboBanco(false);
			}
		}
	}

	/**
	 * Carga el combo Cuentas. Si la lista tiene mas de un elemento habilita la
	 * opcion "Seleccione un item..."
	 * 
	 * @param mv
	 * @param boletaSinValorDto
	 */
	protected void cargarCuenta(ModelAndView mv,
			BoletaSinValorDto boletaSinValorDto) throws NegocioExcepcion {
		if (!Validaciones.isNullOrEmpty(boletaSinValorDto.getIdOrigen())) {
			List<ShvParamBancoCuenta> listaCuenta = combosServicio
					.actualizarCuentaPorIdOrigenBoletaSinValor(
							boletaSinValorDto.getIdEmpresa(),
							boletaSinValorDto.getIdSegmento(),
							boletaSinValorDto.getIdOrigen());
			mv.addObject(SELECT_CUENTAS, listaCuenta);
			if (listaCuenta.size() > 1) {
				boletaSinValorDto.setComboCuenta(true);
			} else {
				boletaSinValorDto.setComboCuenta(false);
			}
		}
	}

	/**
	 * Carga el combo Copropietario. Siempre habilita la opcion
	 * "Seleccione un item..."
	 * 
	 * @param mv
	 * @param boletaSinValorDto
	 */
	protected void cargarCopropietario(ModelAndView mv,
			BoletaSinValorDto boletaSinValorDto) throws NegocioExcepcion {
		if (!Validaciones.isNullOrEmpty(boletaSinValorDto.getIdSegmento())) {

			Collection<String> usuariosExcluidos = new ArrayList<String>();
			usuariosExcluidos.add(boletaSinValorDto.getIdAnalista());

			mv.addObject(SELECT_COPROPIETARIOS, combosServicio
					.listarCopropietarioPorEmpresaYSegmento(
							boletaSinValorDto.getIdEmpresa(),
							boletaSinValorDto.getIdSegmento(),
							usuariosExcluidos));
		}
	}
	


	/**
	 * LLamar al carga la pagina de Alta de Boletas. Carga la lista "motivos",
	 * la lista "empresas" y la lista "segmentos". De haber una sola empresa,
	 * busca los segmentos y los carga en el combo. Luego si hay un solo
	 * segmento, busca los origenes y los carga. Luego si hay un solo origen,
	 * busca los acuerdos, los bancos y las cuentas y carga los respectivos
	 * combos.
	 * 
	 * @param mv
	 * @throws ShivaExcepcion
	 */
	@SuppressWarnings("unchecked")
	protected void enviarListasCombosAlCargar(ModelAndView mv,
			UsuarioSesion userSesion, BoletaSinValorDto boletaDto)
			throws ShivaExcepcion {
		try {
			List<ShvParamEmpresa> listaEmpresas = (List<ShvParamEmpresa>) combosServicio
					.listarEmpresasPorUsuario(userSesion);
			if (listaEmpresas.size() == 1) {
				String idEmpresa = listaEmpresas.get(0).getIdEmpresa();
				List<ShvParamSegmento> listaSegmentos = combosServicio.listarSegmentoPorEmpresaYUsuario(idEmpresa, userSesion);
				mv.addObject(SELECT_SEGMENTOS, listaSegmentos);
				if (listaSegmentos.size() == 1) {
					
					List<ShvParamOrigen> listaOrigenes = combosServicio.listarOrigenPorEmpresaSegmentoValor(idEmpresa, 
									listaSegmentos.get(0).getIdSegmento(), TipoValorEnum.BOLETA_SIN_VALOR.getIdTipoValorString());
					mv.addObject(SELECT_ORIGENES, listaOrigenes);
					
					if (listaOrigenes.size() == 1) {
						mv.addObject(SELECT_ACUERDOS, combosServicio.actualizarAcuerdoPorIdOrigenBoletaSinValor(idEmpresa,
										listaSegmentos.get(0).getIdSegmento(), String.valueOf(listaOrigenes.get(0).getIdOrigen())));
						mv.addObject(SELECT_BANCOS, combosServicio.actualizarBancoPorIdOrigenBoletaSinValor(idEmpresa,
										listaSegmentos.get(0).getIdSegmento(), String.valueOf(listaOrigenes.get(0).getIdOrigen())));
						mv.addObject(SELECT_CUENTAS, combosServicio.actualizarCuentaPorIdOrigenBoletaSinValor(idEmpresa,
										listaSegmentos.get(0).getIdSegmento(), String.valueOf(listaOrigenes.get(0).getIdOrigen())));
						boletaDto.setComboOrigen(false);
					} else {
						boletaDto.setComboOrigen(true);
					}

					Collection<String> usuariosExcluidos = new ArrayList<String>();
					usuariosExcluidos.add(boletaDto.getIdAnalista());

					mv.addObject(SELECT_COPROPIETARIOS, combosServicio.listarCopropietarioPorEmpresaYSegmento(idEmpresa,
									listaSegmentos.get(0).getIdSegmento(),usuariosExcluidos));
					mv.addObject("comboCopropietario", true);
					boletaDto.setComboSegmento(false);
				} else {
					boletaDto.setComboSegmento(true);
				}
				boletaDto.setComboEmpresa(false);
			} else {
				boletaDto.setComboEmpresa(true);
				cargarSegmento(mv, boletaDto, userSesion);
			}
			mv.addObject(SELECT_EMPRESAS, listaEmpresas);
			
			
			mv.addObject(SELECT_MOTIVOS, listarMotivosSinChequesRechazados());

		} catch (NegocioExcepcion e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		}
		
		
		
	}
	/**
	 * 
	 * @return
	 * @throws NegocioExcepcion
	 */
	protected List<ShvParamMotivo> listarMotivosSinChequesRechazados() throws NegocioExcepcion {
		
		@SuppressWarnings("unchecked")
		List<ShvParamMotivo> listaMotivos = (List<ShvParamMotivo>) combosServicio.listar(ShvParamMotivo.class);
		List<ShvParamMotivo> listaAux = new ArrayList<ShvParamMotivo>();
		
		
		for (ShvParamMotivo motivoValor: listaMotivos) {
			if(MotivoValorEnum.CHEQUE_RECHAZADO.codigo() != motivoValor.getIdMotivo()){
				listaAux.add(motivoValor);
			}
			
		}
		
		return listaAux;
	}

	/**
	 * Carga los combos de Empresas y Segmentos en la seccion de filtro en la
	 * pagina de Busqueda.
	 * 
	 * @param mv
	 * @param userSesion
	 * @throws ShivaExcepcion
	 */
	protected void cargarCombosParaBusqueda(ModelAndView mv,
			UsuarioSesion userSesion, Filtro filtro) throws ShivaExcepcion {
		try {
			List<ShvParamEmpresa> listaEmpresas = (List<ShvParamEmpresa>) combosServicio
					.listarEmpresasPorUsuario(userSesion);
			List<ShvParamSegmento> listaSegmentos = new ArrayList<ShvParamSegmento>();

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

			mv.addObject(SELECT_SEGMENTOS, listaSegmentos);
			if (listaSegmentos.size() > 1) {
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

	/**
	 * Carga el combo de estados
	 * 
	 * @param mv
	 * @throws ShivaExcepcion
	 */
	protected void cargarComboParaEstados(ModelAndView mv)
			throws ShivaExcepcion {
		try {
			List<Estado> listaEstados = combosServicio.listarEstadosTalonario();
			mv.addObject(SELECT_ESTADOS, listaEstados);

		} catch (NegocioExcepcion e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * Carga el combo de estados
	 * 
	 * @param mv
	 * @throws ShivaExcepcion
	 */
	protected void cargarComboParaEstadosCobro(ModelAndView mv)
			throws ShivaExcepcion {
		try {
			List<Estado> listaEstados = combosServicio.listarEstadosCobro();
			mv.addObject(SELECT_ESTADOS, listaEstados);

		} catch (NegocioExcepcion e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		}
	}
	
	protected void cargarComboParaEstadosRegistroAVC(ModelAndView mv)
			throws ShivaExcepcion {
		List<Estado> listaEstados = new ArrayList<Estado>();

		listaEstados.add(Estado.AVC_PENDIENTE);
		listaEstados.add(Estado.AVC_PENDIENTE_CONFIRMAR_ALTA_VALOR);
		listaEstados.add(Estado.AVC_ALTA_VALOR_RECHAZADA);
		listaEstados.add(Estado.REV_PENDIENTE);
		listaEstados.add(Estado.REV_PENDIENTE_CONFIRMAR);
		listaEstados.add(Estado.REV_RECHAZADA);
		
		mv.addObject(SELECT_ESTADOS, listaEstados);
	}

	/* METODOS PARA BOLETAS CON VALOR */

	protected void cargarAcuerdo(ModelAndView mv, ValorDto boletaConValorDto)
			throws NegocioExcepcion {
		if (!Validaciones.isNullOrEmpty(boletaConValorDto.getIdOrigen())) {
			List<ShvParamAcuerdo> listaAcuerdo = combosServicio
					.actualizarAcuerdoPorIdOrigen(
							boletaConValorDto.getIdEmpresa(),
							boletaConValorDto.getIdSegmento(),
							boletaConValorDto.getIdOrigen());
			mv.addObject(SELECT_ACUERDOS, listaAcuerdo);
			if (listaAcuerdo.size() > 1) {
				boletaConValorDto.setComboAcuerdo(true);
			} else {
				boletaConValorDto.setComboAcuerdo(false);
			}
		}
	}

	protected void cargarAcuerdoAviso(ModelAndView mv,
			ValorDto boletaConValorDto) throws NegocioExcepcion {
		if (!Validaciones.isNullOrEmpty(boletaConValorDto.getIdEmpresa())
				|| !Validaciones.isNullOrEmpty(boletaConValorDto
						.getIdSegmento())) {
			List<ShvParamAcuerdo> listaAcuerdo = combosServicio
					.actualizarAcuerdoPorIdSegmento(
							boletaConValorDto.getIdEmpresa(),
							boletaConValorDto.getIdSegmento());
			mv.addObject(SELECT_ACUERDOS, listaAcuerdo);
			if (listaAcuerdo.size() > 1) {
				boletaConValorDto.setComboAcuerdo(true);
			} else {
				boletaConValorDto.setComboAcuerdo(false);
			}
		}
	}

	protected void cargarBanco(ModelAndView mv, ValorDto valorDto)
			throws NegocioExcepcion {
		if (!Validaciones.isNullOrEmpty(valorDto.getIdOrigen())) {
			List<ShvParamBanco> listaBanco = combosServicio
					.actualizarBancoPorIdOrigen(
							valorDto.getIdEmpresa(),
							valorDto.getIdSegmento(),
							valorDto.getIdOrigen());
			mv.addObject(SELECT_BANCOS, listaBanco);
			if (listaBanco.size() > 1) {
				valorDto.setComboBanco(true);
			} else {
				valorDto.setComboBanco(false);
			}
		}
	}

	protected void cargarBancoAviso(ModelAndView mv, ValorDto boletaConValorDto)
			throws NegocioExcepcion {
		if (!Validaciones.isNullOrEmpty(boletaConValorDto.getIdEmpresa())
				|| !Validaciones.isNullOrEmpty(boletaConValorDto
						.getIdSegmento())) {
			List<ShvParamBanco> listaBanco = combosServicio
					.actualizarBancoPorIdSegmento(
							boletaConValorDto.getIdEmpresa(),
							boletaConValorDto.getIdSegmento());
			mv.addObject(SELECT_BANCOS, listaBanco);
			if (listaBanco.size() > 1) {
				boletaConValorDto.setComboBanco(true);
			} else {
				boletaConValorDto.setComboBanco(false);
			}
		}
	}

	protected void cargarCuenta(ModelAndView mv, ValorDto boletaConValorDto)
			throws NegocioExcepcion {
		if (!Validaciones.isNullOrEmpty(boletaConValorDto.getIdOrigen())) {
			List<ShvParamBancoCuenta> listaCuenta = combosServicio
					.actualizarCuentaPorIdOrigen(
							boletaConValorDto.getIdEmpresa(),
							boletaConValorDto.getIdSegmento(),
							boletaConValorDto.getIdOrigen());
			mv.addObject(SELECT_CUENTAS, listaCuenta);
			if (listaCuenta.size() > 1) {
				boletaConValorDto.setComboCuenta(true);
			} else {
				boletaConValorDto.setComboCuenta(false);
			}
		}
	}

	protected void cargarCuentaAviso(ModelAndView mv, ValorDto boletaConValorDto)
			throws NegocioExcepcion {
		if (!Validaciones.isNullOrEmpty(boletaConValorDto.getIdEmpresa())
				|| !Validaciones.isNullOrEmpty(boletaConValorDto
						.getIdSegmento())) {
			List<ShvParamBancoCuenta> listaCuenta = combosServicio
					.actualizarCuentaPorIdSegmento(
							boletaConValorDto.getIdEmpresa(),
							boletaConValorDto.getIdSegmento());
			mv.addObject(SELECT_CUENTAS, listaCuenta);
			if (listaCuenta.size() > 1) {
				boletaConValorDto.setComboCuenta(true);
			} else {
				boletaConValorDto.setComboCuenta(false);
			}
		}
	}

	protected void cargarOrigen(ModelAndView mv, ValorDto boletaSinValorDto)
			throws NegocioExcepcion {
		if (!Validaciones.isNullOrEmpty(boletaSinValorDto.getIdOrigen())) {
			List<ShvParamOrigen> listaOrigenes = combosServicio.listarOrigenPorIdSegmento(
				boletaSinValorDto.getIdEmpresa(),
				boletaSinValorDto.getIdSegmento()
			);
			mv.addObject(SELECT_ORIGENES, listaOrigenes);
			if (listaOrigenes.size() > 1) {
				boletaSinValorDto.setComboOrigen(true);
			} else {
				boletaSinValorDto.setComboOrigen(false);
			}
		}
	}

	protected void cargarCopropietario(ModelAndView mv,
			ValorDto valorDto) throws NegocioExcepcion {
		if (!Validaciones.isNullOrEmpty(valorDto.getIdSegmento())) {

			Collection<String> usuariosExcluidos = new ArrayList<String>();
			usuariosExcluidos.add(valorDto.getIdAnalista());

			mv.addObject(SELECT_COPROPIETARIOS, combosServicio
					.listarCopropietarioPorEmpresaYSegmento(
							valorDto.getIdEmpresa(),
							valorDto.getIdSegmento(),
							usuariosExcluidos));
		}
		mv.addObject("comboCopropietario", true);
	}

	/**
	 * LLamar al carga la pagina de Alta de Boletas CON VALOR. Carga la lista
	 * "motivos", la lista "empresas" y la lista "segmentos". De haber una sola
	 * empresa, busca los segmentos y los carga en el combo. Luego si hay un
	 * solo segmento, busca los origenes y los carga. Luego si hay un solo
	 * origen, busca los acuerdos, los bancos y las cuentas y carga los
	 * respectivos combos.
	 * 
	 * @param mv
	 * @throws ShivaExcepcion
	 */
	protected void enviarListasCombosAlCargar(ModelAndView mv,UsuarioSesion userSesion, ValorDto valorDto) throws ShivaExcepcion {
		try {
			// CARGA DE COMBOS AL INGRESAR
			cargarMotivos(mv, valorDto);

			cargaEmpresaDinamica(userSesion, mv,valorDto );
			cargarBancoOrigen(mv, valorDto);
		} catch (NegocioExcepcion e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		}
	}
	
	
	protected List<ShvParamEmpresa> cargaEmpresaDinamica(UsuarioSesion userSesion, ModelAndView mv, ValorDto valorDto) throws ShivaExcepcion {

		try {
			List<ShvParamEmpresa> listaEmpresas = (List<ShvParamEmpresa>) combosServicio.listarEmpresasPorUsuario(userSesion);

			if (listaEmpresas.size() == 1) {
				cargaSegmentoDinamica(userSesion,mv, valorDto,listaEmpresas );
				valorDto.setComboEmpresa(false);
				
			} else {
				if(!Validaciones.isNullOrEmpty((valorDto.getIdEmpresa()))){
					cargaSegmentoDinamica(userSesion,mv, valorDto,listaEmpresas );
				}
				valorDto.setComboEmpresa(true);
			}
			mv.addObject(SELECT_EMPRESAS, listaEmpresas);
			return listaEmpresas;
		} catch (NegocioExcepcion e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		}
	}

	private List<ShvParamSegmento> cargaSegmentoDinamica(UsuarioSesion userSesion, ModelAndView mv, ValorDto valorDto, List<ShvParamEmpresa> listaEmpresa ) throws ShivaExcepcion {

		try {
			String idEmpresa = (listaEmpresa.size() == 1)?listaEmpresa.get(0).getIdEmpresa():valorDto.getIdEmpresa();
			List<ShvParamSegmento> listaSegmentos = combosServicio.listarSegmentoPorEmpresaYUsuario(idEmpresa, userSesion);
			String idSegmento = (listaSegmentos.size() == 1)?listaSegmentos.get(0).getIdSegmento():valorDto.getIdSegmento();
			valorDto.setIdEmpresa(idEmpresa);
			valorDto.setIdSegmento(idSegmento);
			

			if (listaSegmentos.size() == 1) {
				//cargaOrigenDinamica(userSesion, mv,boletaDto );
				cargaTipoValorDinamica(userSesion, mv,valorDto );
				valorDto.setComboSegmento(false);
			} else {
				if(!Validaciones.isNullOrEmpty((valorDto.getIdSegmento()))){
					//cargaOrigenDinamica(userSesion, mv,boletaDto );
					cargaTipoValorDinamica(userSesion, mv,valorDto );
				}
				valorDto.setComboSegmento(true);
			}
			mv.addObject(SELECT_SEGMENTOS, listaSegmentos);
			return listaSegmentos;
		} catch (NegocioExcepcion e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		}
	}
	
	/**
	 * Me permite cargar los tipos de valores en forma dinamica
	 */
	private List<ShvParamTipoValor> cargaTipoValorDinamica(UsuarioSesion userSesion, ModelAndView mv, ValorDto valorDto ) throws ShivaExcepcion {
		try {
			List<ShvParamTipoValor> listaTipoValor = null;
			
			valorDto.setComboTipoValor(true);
			
			if (ALTA_BOLETA_VIEW.equals(mv.getViewName())) {
				listaTipoValor = (List<ShvParamTipoValor>) combosServicio.buscarTipoValorBoletaConValor(valorDto);
				valorDto.setComboTipoValor(false);
			} else 
			if (ALTA_AVISO_VIEW.equals(mv.getViewName())) {
				listaTipoValor = (List<ShvParamTipoValor>) combosServicio.buscarTipoValorAvisoPago( valorDto);
				for (ShvParamTipoValor tipoAviso : listaTipoValor) {
					if (tipoAviso.getIdTipoValor() == 9) {
						listaTipoValor.remove(tipoAviso);
					}
				}
			} 
			if (Validaciones.isNullOrEmpty(valorDto.getIdTipoValor())){
				valorDto.setIdTipoValor(listaTipoValor.get(0).getIdTipoValor().toString());
			}
			cargaOrigenDinamica(userSesion, mv, valorDto);
			mv.addObject(SELECT_TIPO_VALOR, listaTipoValor);
			
			return listaTipoValor;
		} catch (NegocioExcepcion e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		}
	}
	
	
	protected List<ShvParamOrigen> cargaOrigenDinamica(UsuarioSesion userSesion, ModelAndView mv, ValorDto valorDto ) throws ShivaExcepcion {

		try {
			List<ShvParamOrigen> listaOrigenes = null;
			List<ShvParamAcuerdo> listaAcuerdo = null;
			
			// PARA BOLETA CON VALOR
			if (VALOR_PARA_BOLETA_CHEQUE.equals(valorDto.getIdTipoValor())	
					|| VALOR_PARA_BOLETA_CHEQUE_PD.equals(valorDto.getIdTipoValor()) 
					|| VALOR_PARA_BOLETA_EFECTIVO.equals(valorDto.getIdTipoValor())) {
			
				// VERIFICO USUARIOS PARA ORIGEN
				/**
				 * @author fabio.giaquinta.ruiz
				 * Correccion Combo Origen vacio cuando Origen
				 * Cajero Pagador y usuario analista
				 */
				if (userSesion.esCajeroPagador() || (valorDto.getIdOrigen() != null && valorDto.getIdOrigen().equals(ORIGEN_CAJERO_PAGADOR))) {
					listaOrigenes = new ArrayList<ShvParamOrigen>();
					ShvParamOrigen origen = combosServicio.buscarOrigenPorId(ORIGEN_CAJERO_PAGADOR);
					listaOrigenes.add(origen);
					valorDto.setComboOrigen(false);
				} else if (userSesion.esAnalista()) {
					listaOrigenes = combosServicio.listarOrigenAnalista();
				} else {
					listaOrigenes = combosServicio.listarOrigenPorEmpresaSegmentoValor(valorDto.getIdEmpresa(), valorDto.getIdSegmento(), valorDto.getIdTipoValor());
				}
				mv.addObject(SELECT_ORIGENES, listaOrigenes);
				
				if(listaOrigenes.size() == 1){
					valorDto.setIdOrigen(listaOrigenes.get(0).getIdOrigen().toString());
					valorDto.setComboOrigen(false);
				} else {
					valorDto.setComboOrigen(true);
				}
				

				if (!Validaciones.isNullOrEmpty((valorDto.getIdOrigen()))) {
					listaAcuerdo = combosServicio.actualizarAcuerdoPorIdOrigenValor(valorDto.getIdEmpresa(),valorDto.getIdSegmento(),valorDto.getIdOrigen(),valorDto.getIdTipoValor());
					
					if(listaAcuerdo.size() == 1){
						valorDto.setIdAcuerdo(listaAcuerdo.get(0).getIdAcuerdo().toString());
						valorDto.setComboAcuerdo(false);
					} else {
						valorDto.setComboAcuerdo(true);
					}
					cargarBancoCuentaDinamico(mv, listaAcuerdo, valorDto );
					mv.addObject(SELECT_ACUERDOS, listaAcuerdo);
				}
					
				// ORIGEN TRAE UN VALOR--> CARGA
				if (listaOrigenes.size() == 1) {
					valorDto.setIdOrigen(listaOrigenes.get(0).getIdOrigen().toString());
					listaAcuerdo = combosServicio.actualizarAcuerdoPorIdOrigenValor(valorDto.getIdEmpresa(), valorDto.getIdSegmento(), String	.valueOf(listaOrigenes.get(0).getIdOrigen()),valorDto.getIdTipoValor());
					
					
					if(listaAcuerdo.size() == 1){
						valorDto.setIdAcuerdo(listaAcuerdo.get(0).getIdAcuerdo().toString());
						valorDto.setComboAcuerdo(false);
					} else {
						valorDto.setComboAcuerdo(true);
					}
					cargarBancoCuentaDinamico(mv, listaAcuerdo, valorDto);
					mv.addObject(SELECT_ACUERDOS, listaAcuerdo);
					valorDto.setComboOrigen(false);
				} else {
					valorDto.setComboOrigen(true);
				}
				
				Collection<String> usuariosExcluidos = new ArrayList<String>();
				usuariosExcluidos.add(valorDto.getIdAnalista());

				mv.addObject(SELECT_COPROPIETARIOS, combosServicio.listarCopropietarioPorEmpresaYSegmento(valorDto.getIdEmpresa(), valorDto.getIdSegmento(), usuariosExcluidos));
				mv.addObject("comboCopropietario", true);

			} else {// CARGA PARA AVISO DE PAGO
				Collection<String> usuariosExcluidos = new ArrayList<String>();
				usuariosExcluidos.add(valorDto.getIdAnalista());
				
				mv.addObject(SELECT_COPROPIETARIOS, combosServicio.listarCopropietarioPorEmpresaYSegmento(valorDto.getIdEmpresa(), valorDto.getIdSegmento(), usuariosExcluidos));
				mv.addObject("comboCopropietario", true);
				
				//ACUERDO
				listaAcuerdo = combosServicio.actualizarAcuerdoNoConciliables(valorDto.getIdEmpresa(),valorDto.getIdSegmento(), valorDto.getIdTipoValor());
				mv.addObject(SELECT_ACUERDOS, listaAcuerdo);
				
				if(listaAcuerdo.size() == 1){
					valorDto.setIdAcuerdo(listaAcuerdo.get(0).getIdAcuerdo().toString());					
					valorDto.setComboAcuerdo(false);
				} else {					
					valorDto.setComboAcuerdo(true);
				}
								
				cargarBancoCuentaDinamico(mv, listaAcuerdo, valorDto);
			}
			return listaOrigenes;
		} catch (NegocioExcepcion e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		}
	}	
	
	protected void cargarBancoCuentaDinamico(ModelAndView mv,List<ShvParamAcuerdo> listaAcuerdo, ValorDto valorDto) throws NegocioExcepcion {
		
		//CUENTA
		List<ShvParamBancoCuenta> listaCuentas = null;
		listaCuentas = combosServicio.listarCuentaNoConciliables(listaAcuerdo);
		mv.addObject(SELECT_CUENTAS, listaCuentas);
			
		//BANCO
		List<ShvParamBanco> listaBanco = combosServicio.listarBancosNoConciliables(listaAcuerdo);				
		mv.addObject(SELECT_BANCOS, listaBanco);
			
		if(listaBanco.size() == 1){
			valorDto.setIdBancoDeposito(listaBanco.get(0).getIdBanco());
			valorDto.setComboBanco(false);
		} else {
			valorDto.setComboBanco(true);
		}
		if(listaCuentas.size() == 1){
			valorDto.setIdNroCuenta(listaCuentas.get(0).getIdBancoCuenta().toString());
			valorDto.setComboCuenta(false);
		} else {
			valorDto.setComboCuenta(true);
		}				
	}
	
	/**
	 * 
	 * @param mv
	 * @param userSesion
	 * @param valorDto
	 * @throws ShivaExcepcion
	 */
	protected void cargarCombosAvisos(ModelAndView mv,	UsuarioSesion userSesion, ValorDto valorDto) throws ShivaExcepcion {
		try {
			List<ShvParamJurisdiccion> listaProvincia = (List<ShvParamJurisdiccion>) combosServicio.listarProvinciasEnJurisdiccion();
			List<ShvParamTipoComprobante> listaTipoComprobante = (List<ShvParamTipoComprobante>) combosServicio.listarTipoComprobante();
			List<ShvParamTipoLetraComprobante> listaLetraComprobante = (List<ShvParamTipoLetraComprobante>) combosServicio.listarLetraComprobante();
			List<ShvParamTipoRetencionImpuesto> listaTipoImpuesto = (List<ShvParamTipoRetencionImpuesto>) combosServicio.listarTipoImpuesto();
			mv.addObject("provincia", listaProvincia);
			mv.addObject("idTipoComprobante", listaTipoComprobante);
			mv.addObject("idTipoLetraComprobante", listaLetraComprobante);
			mv.addObject("idTipoRetencionImpuesto",	listaTipoImpuesto);
			if(listaProvincia.size() == 1){
				valorDto.setIdProvincia(listaProvincia.get(0).getIdJurisdiccion());
				valorDto.setComboProvincia(false);
			} else {
				valorDto.setComboProvincia(true);
			}
			if(listaTipoComprobante.size() == 1){
				valorDto.setIdTipoComprobante(listaTipoComprobante.get(0).getIdTipoComprobante());
				valorDto.setComboTipoComprobante(false);
			} else {
				valorDto.setComboTipoComprobante(true);
			}
			if(listaLetraComprobante.size() == 1){
				valorDto.setIdLetraComprobante(listaLetraComprobante.get(0).getIdTipoLetraComprobante());
				valorDto.setComboLetraComprobante(false);
			} else {
				valorDto.setComboLetraComprobante(true);
			}
			if(listaTipoImpuesto.size() == 1){
				valorDto.setIdTipoImpuesto(listaTipoImpuesto.get(0).getIdTipoRetencionImpuesto().toString());
				valorDto.setComboTipoImpuesto(false);
			} else {
				valorDto.setComboTipoImpuesto(true);
			}
		} catch (NegocioExcepcion e) {
			throw new ShivaExcepcion(e.getMessage(), e);
		}
	}

	protected void cargarMotivos(ModelAndView mv, ValorDto valorDto)
			throws NegocioExcepcion {
		
		List<ShvParamMotivo> listaAux = listarMotivosSinChequesRechazados();
		
		mv.addObject(SELECT_MOTIVOS, listaAux);
		if(listaAux.size() == 1){
			valorDto.setIdMotivo(listaAux.get(0).getIdMotivo().toString());
			valorDto.setComboMotivo(false);
		} else {
			valorDto.setComboMotivo(true);
		}
	}


	protected void cargarBancoOrigen(ModelAndView mv, ValorDto valorDto)
			throws NegocioExcepcion {
		List<ShvParamBanco> listaBancoOrigen = (List<ShvParamBanco>) combosServicio.listarBancoOrigen(valorDto.getIdEmpresa());
		if (!Validaciones.isNullOrEmpty(valorDto.getIdEmpresa())) {
			mv.addObject(SELECT_BANCO_ORIGENES,	listaBancoOrigen);
			if(listaBancoOrigen.size() == 1){
				valorDto.setIdBancoOrigen(listaBancoOrigen.get(0).getIdBanco());
				valorDto.setComboBancoOrigen(false);
			} else {
				valorDto.setComboBancoOrigen(true);
			}
		}
	}
	


	protected void cargarTipoValor(ModelAndView mv, ValorDto valorDto)
			throws NegocioExcepcion {

		List<ShvParamTipoValor> listaTipoValor = (List<ShvParamTipoValor>) combosServicio
				.buscarTipoValorBoletaConValor(valorDto);
		mv.addObject(SELECT_TIPO_VALOR, listaTipoValor);
	}

	/* FIN METODOS PARA BOLETAS CON VALOR */
	// METODOS UTILIZADOS EN CobroOnlineController y ChequeRechazoController
	
	protected List<SelectOptionJsonResponse> buscarSegmentos(HttpServletRequest request) throws Exception {
		String idEmpresa = request.getParameter("idEmpresa");
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		List<SelectOptionJsonResponse> result = new ArrayList<SelectOptionJsonResponse>();
		SelectOptionJsonResponse jsonResp = null;
		for (ShvParamSegmento segmento : combosServicio.listarSegmentoPorEmpresaYUsuario(idEmpresa, userSesion)) {
			jsonResp = new SelectOptionJsonResponse();
			jsonResp.setValue(segmento.getIdSegmento());
			jsonResp.setText(segmento.getDescripcion());
			result.add(jsonResp);
		}
		return result;
	}
	protected List<SelectOptionJsonResponse> buscarCopropietarios(HttpServletRequest request) throws Exception {
		String idEmpresa = request.getParameter("idEmpresa");
		String idSegmento = request.getParameter("idSegmento");
		UsuarioSesion userSesion = (UsuarioSesion) request.getSession().getAttribute(Constantes.LOGIN);
		
		Collection<String> usuariosExcluidos = new ArrayList<String>();
		usuariosExcluidos.add(userSesion.getIdUsuario());
		
		List<SelectOptionJsonResponse> result = buscarCopropietarios(idEmpresa, idSegmento, usuariosExcluidos);
		return result;
	}
	
	/**
	 * Sprint 5
	 * @param request
	 * @return
	 * @throws Exception
	 */
	protected @ResponseBody List<SelectOptionJsonResponse> buscarCopropietarios(String idEmpresa, String idSegmento, Collection<String> usuariosExcluidos) throws Exception {
		
		List<SelectOptionJsonResponse> result = new ArrayList<SelectOptionJsonResponse>();
		SelectOptionJsonResponse jsonResp = null;
		if (!Validaciones.isNullEmptyOrDash(idSegmento)) {
			List<UsuarioLdapDto> copropietarios = (List<UsuarioLdapDto>) combosServicio.listarCopropietarioCobroPorEmpresaYSegmento(idEmpresa, idSegmento, usuariosExcluidos);
			Collections.sort(
				copropietarios,
				new UsuarioLdapDtoComparator()
			);
			for (UsuarioLdapDto copropietario : copropietarios) {
				jsonResp = new SelectOptionJsonResponse();
				jsonResp.setValue(copropietario.getTuid());
				jsonResp.setText(copropietario.getNombreCompleto());
				result.add(jsonResp);
			}
		}
		return result;
	}
}