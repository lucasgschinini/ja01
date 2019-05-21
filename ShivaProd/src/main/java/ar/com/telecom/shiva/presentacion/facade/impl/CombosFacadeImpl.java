package ar.com.telecom.shiva.presentacion.facade.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.telecom.shiva.base.comparador.ComparatorParametroBancoDtoParaAcuerdos;
import ar.com.telecom.shiva.base.comparador.UsuarioLdapDtoComparator;
import ar.com.telecom.shiva.base.enumeradores.ConfCampoTipoEnum;
import ar.com.telecom.shiva.base.enumeradores.ConfFormObligatoriedadEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.dto.AgrupadorParametroBancoDto;
import ar.com.telecom.shiva.negocio.dto.AgrupadorParametroTipoGestionDto;
import ar.com.telecom.shiva.negocio.dto.BancosParametroDto;
import ar.com.telecom.shiva.negocio.dto.ComboAcuerdoBancoCuentaDto;
import ar.com.telecom.shiva.negocio.dto.ComboAcuerdoBancoCuentaGralDto;
import ar.com.telecom.shiva.negocio.dto.ParamConfReglaCampo;
import ar.com.telecom.shiva.negocio.dto.ParametroAcuerdoDto;
import ar.com.telecom.shiva.negocio.dto.ParametroBancoCuentaDto;
import ar.com.telecom.shiva.negocio.dto.ParametroBancoDto;
import ar.com.telecom.shiva.negocio.dto.ParametroOrigenDto;
import ar.com.telecom.shiva.negocio.dto.ProvinciaParametroDto;
import ar.com.telecom.shiva.negocio.dto.TipoValorDto;
import ar.com.telecom.shiva.negocio.servicios.ICombosServicio;
import ar.com.telecom.shiva.negocio.servicios.bean.TipoValorBean;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBanco;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamProvincia;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoGestion;
import ar.com.telecom.shiva.presentacion.bean.dto.EstadoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.json.SelectOptionJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.facade.ICombosFacade;

public class CombosFacadeImpl implements ICombosFacade {

	@Autowired
	private ICombosServicio combosServicio;
	
	
	@Override
	public ComboAcuerdoBancoCuentaDto listarAcuerdo(String idEmpresa) throws NegocioExcepcion {
		ComboAcuerdoBancoCuentaDto combo = new ComboAcuerdoBancoCuentaDto();

		List<ShvParamAcuerdo> lista = combosServicio.listarAcuerdo(idEmpresa);

		Set<ParametroBancoDto> setBancos = new java.util.HashSet<ParametroBancoDto>();
		
		for (ShvParamAcuerdo shvParamAcuerdo : lista) {
			ParametroAcuerdoDto acuerdo = new ParametroAcuerdoDto();
			acuerdo.setIdAcuerdo(shvParamAcuerdo.getIdAcuerdo());
			acuerdo.setDescripcion(shvParamAcuerdo.getDescripcion());
			acuerdo.setConciliable(shvParamAcuerdo.getConciliable());
			
			ParametroBancoCuentaDto bancoCuenta = new ParametroBancoCuentaDto();
			bancoCuenta.setCuentaBancaria(shvParamAcuerdo.getBancoCuenta().getCuentaBancaria());
			bancoCuenta.setIdBancoCuenta(shvParamAcuerdo.getBancoCuenta().getIdBancoCuenta());
			
			ParametroBancoDto banco = new ParametroBancoDto();
			banco.setIdBanco(shvParamAcuerdo.getBancoCuenta().getBanco().getIdBanco());
			banco.setDescripcion(shvParamAcuerdo.getBancoCuenta().getBanco().getDescripcion());
		
			acuerdo.setBancoCuenta(bancoCuenta);
			bancoCuenta.setBanco(banco);
			acuerdo.setBanco(banco);
			
			combo.getListaAcuerdos().add(acuerdo);
			combo.getListaCuentas().add(bancoCuenta);
			setBancos.add(banco);
		}
		
		combo.getListaBancos().addAll(setBancos);
		Collections.sort(combo.getListaBancos(), new ComparatorParametroBancoDtoParaAcuerdos());
		return combo;
	}

	
	@Override
	public ComboAcuerdoBancoCuentaGralDto listarShvParamTipoGestionDb(String idEmpresa, Integer idOrigen, List<TipoValorEnum> listaValores, SiNoEnum consiliable) throws NegocioExcepcion {
		ComboAcuerdoBancoCuentaGralDto dataSoucers = new ComboAcuerdoBancoCuentaGralDto();

		List<ShvParamTipoGestion> lista = combosServicio.listarShvParamTipoGestion(
			idEmpresa,
			idOrigen,
			listaValores,
			consiliable
		);
		
		Set<ParametroBancoDto> setBancos = new java.util.HashSet<ParametroBancoDto>();
	
		String idSegmento = null;
		Integer origen = null;
		Integer idTipoValor = null;
		AgrupadorParametroTipoGestionDto current = null;
		for (ShvParamTipoGestion shvParamTipoGestion : lista) {
			if (
				!(
					shvParamTipoGestion.getSegmentoEmpresa().getSegmento().getIdSegmento().equals(idSegmento) &&
					shvParamTipoGestion.getOrigen().getIdOrigen().equals(origen) &&
					shvParamTipoGestion.getIdTipoValor().equals(idTipoValor)
				)
			) {
				if (!Validaciones.isObjectNull(current)) {
					current.getCombo().getListaBancos().addAll(setBancos);
					Collections.sort(current.getCombo().getListaBancos(), new ComparatorParametroBancoDtoParaAcuerdos());
					dataSoucers.getLista().add(current);
					setBancos = new java.util.HashSet<ParametroBancoDto>();
				}
				current = new AgrupadorParametroTipoGestionDto();
				current.setIdOrigen(shvParamTipoGestion.getOrigen().getIdOrigen());
				current.setIdSegmento(shvParamTipoGestion.getSegmentoEmpresa().getSegmento().getIdSegmento());
				current.setIdTipoValor(shvParamTipoGestion.getIdTipoValor());
				
				idSegmento = shvParamTipoGestion.getSegmentoEmpresa().getSegmento().getIdSegmento();
				origen = shvParamTipoGestion.getOrigen().getIdOrigen();
				idTipoValor = shvParamTipoGestion.getIdTipoValor();
				
				ParametroAcuerdoDto acuerdo = new ParametroAcuerdoDto();
				ShvParamAcuerdo shvParamAcuerdo = shvParamTipoGestion.getAcuerdo();
				acuerdo.setIdAcuerdo(shvParamAcuerdo.getIdAcuerdo());
				acuerdo.setDescripcion(shvParamAcuerdo.getDescripcion());
				acuerdo.setConciliable(shvParamAcuerdo.getConciliable());
				
				ParametroBancoCuentaDto bancoCuenta = new ParametroBancoCuentaDto();
				bancoCuenta.setCuentaBancaria(shvParamAcuerdo.getBancoCuenta().getCuentaBancaria());
				bancoCuenta.setIdBancoCuenta(shvParamAcuerdo.getBancoCuenta().getIdBancoCuenta());
				
				ParametroBancoDto banco = new ParametroBancoDto();
				banco.setIdBanco(shvParamAcuerdo.getBancoCuenta().getBanco().getIdBanco());
				banco.setDescripcion(shvParamAcuerdo.getBancoCuenta().getBanco().getDescripcion());
			
				acuerdo.setBancoCuenta(bancoCuenta);
				bancoCuenta.setBanco(banco);
				acuerdo.setBanco(banco);
				
				current.getCombo().getListaAcuerdos().add(acuerdo);
				current.getCombo().getListaCuentas().add(bancoCuenta);
				setBancos.add(banco);

			}	else {

				ParametroAcuerdoDto acuerdo = new ParametroAcuerdoDto();
				ShvParamAcuerdo shvParamAcuerdo = shvParamTipoGestion.getAcuerdo();
				acuerdo.setIdAcuerdo(shvParamAcuerdo.getIdAcuerdo());
				acuerdo.setDescripcion(shvParamAcuerdo.getDescripcion());
				acuerdo.setConciliable(shvParamAcuerdo.getConciliable());
				
				ParametroBancoCuentaDto bancoCuenta = new ParametroBancoCuentaDto();
				bancoCuenta.setCuentaBancaria(shvParamAcuerdo.getBancoCuenta().getCuentaBancaria());
				bancoCuenta.setIdBancoCuenta(shvParamAcuerdo.getBancoCuenta().getIdBancoCuenta());
				
				ParametroBancoDto banco = new ParametroBancoDto();
				banco.setIdBanco(shvParamAcuerdo.getBancoCuenta().getBanco().getIdBanco());
				banco.setDescripcion(shvParamAcuerdo.getBancoCuenta().getBanco().getDescripcion());
			
				acuerdo.setBancoCuenta(bancoCuenta);
				bancoCuenta.setBanco(banco);
				acuerdo.setBanco(banco);
				
				current.getCombo().getListaAcuerdos().add(acuerdo);
				current.getCombo().getListaCuentas().add(bancoCuenta);
				setBancos.add(banco);
			
			}
		}
		if (!Validaciones.isObjectNull(current)) {
			current.getCombo().getListaBancos().addAll(setBancos);
			Collections.sort(current.getCombo().getListaBancos(), new ComparatorParametroBancoDtoParaAcuerdos());
			dataSoucers.getLista().add(current);
			setBancos = new java.util.HashSet<ParametroBancoDto>();
		}
		
		return dataSoucers;
	}

	@Override
	public ComboAcuerdoBancoCuentaGralDto listarShvParamTipoGestion(String idEmpresa, Integer idOrigen, List<TipoValorEnum> listaValores, SiNoEnum conciliable) throws NegocioExcepcion {
		ComboAcuerdoBancoCuentaGralDto dataSoucers = new ComboAcuerdoBancoCuentaGralDto();

		List<Map<String, Object>> lista = combosServicio.listarShvParamTipoGestionDb(
			idEmpresa,
			idOrigen,
			listaValores,
			conciliable
		);
		
		
		Set<ParametroBancoDto> setBancos = new java.util.HashSet<ParametroBancoDto>();
		Set<ParametroOrigenDto> setOrigen = new java.util.HashSet<ParametroOrigenDto>();
		
		String idSegmento = null;
		Integer origen = null;
		Integer idTipoValor = null;
		AgrupadorParametroTipoGestionDto current = null;
		
		for (Map<String, Object> reg: lista) {
			String regIdSegmento = (String) reg.get("id_segmento");
			Integer regIdOrigen = ((BigDecimal) reg.get("id_origen")).intValue();
			Integer regIdTipoValor = ((BigDecimal) reg.get("id_tipo_valor")).intValue();

			if (
				!(regIdSegmento.equals(idSegmento) &&
				regIdOrigen.equals(origen) &&
				regIdTipoValor.equals(idTipoValor)
				)
			) {
				if (!Validaciones.isObjectNull(current)) {
					current.getCombo().getListaBancos().addAll(setBancos);
					Collections.sort(current.getCombo().getListaBancos(), new ComparatorParametroBancoDtoParaAcuerdos());
					dataSoucers.getLista().add(current);
					setBancos = new java.util.HashSet<ParametroBancoDto>();
				}
				if (!regIdOrigen.equals(origen)) {
					ParametroOrigenDto parametroOrigen = new ParametroOrigenDto();
					parametroOrigen.setIdOrigen(regIdOrigen);
					parametroOrigen.setDescripcion((String) reg.get("origen_descripcion"));
					setOrigen.add(parametroOrigen);
				}
				current = new AgrupadorParametroTipoGestionDto();
				current.setIdOrigen(regIdOrigen);
				current.setIdSegmento(regIdSegmento);
				current.setIdTipoValor(regIdTipoValor);
				
				idSegmento = regIdSegmento;
				origen = regIdOrigen;
				idTipoValor = regIdTipoValor;
				
				ParametroAcuerdoDto acuerdo = new ParametroAcuerdoDto();
				acuerdo.setIdAcuerdo(((BigDecimal)  reg.get("id_Acuerdo")).intValue());
				acuerdo.setDescripcion((String) reg.get("acuerdoDescripcion"));
				acuerdo.setConciliable(((String) reg.get("es_conciliable")).charAt(0));
				
				ParametroBancoCuentaDto bancoCuenta = new ParametroBancoCuentaDto();
				bancoCuenta.setCuentaBancaria((String) reg.get("cuenta_bancaria"));
				bancoCuenta.setIdBancoCuenta(((BigDecimal)  reg.get("id_banco_cuenta")).intValue());
				
				ParametroBancoDto banco = new ParametroBancoDto();
				banco.setIdBanco((String) reg.get("id_banco"));
				banco.setDescripcion((String) reg.get("banco_descripcion"));
			
				acuerdo.setBancoCuenta(bancoCuenta);
				bancoCuenta.setBanco(banco);
				acuerdo.setBanco(banco);
				
				current.getCombo().getListaAcuerdos().add(acuerdo);
				current.getCombo().getListaCuentas().add(bancoCuenta);
				setBancos.add(banco);

			}	else {

				ParametroAcuerdoDto acuerdo = new ParametroAcuerdoDto();
				acuerdo.setIdAcuerdo(((BigDecimal)  reg.get("id_Acuerdo")).intValue());
				acuerdo.setDescripcion((String) reg.get("acuerdoDescripcion"));
				acuerdo.setConciliable(((String) reg.get("es_conciliable")).charAt(0));
				
				ParametroBancoCuentaDto bancoCuenta = new ParametroBancoCuentaDto();
				bancoCuenta.setCuentaBancaria((String) reg.get("cuenta_bancaria"));
				bancoCuenta.setIdBancoCuenta(((BigDecimal)  reg.get("id_banco_cuenta")).intValue());
				
				ParametroBancoDto banco = new ParametroBancoDto();
				banco.setIdBanco((String) reg.get("id_banco"));
				banco.setDescripcion((String) reg.get("banco_descripcion"));
			
				acuerdo.setBancoCuenta(bancoCuenta);
				bancoCuenta.setBanco(banco);
				acuerdo.setBanco(banco);
				
				current.getCombo().getListaAcuerdos().add(acuerdo);
				current.getCombo().getListaCuentas().add(bancoCuenta);
				setBancos.add(banco);
			
			}
		}
		// ultimo caso!!
		if (!Validaciones.isObjectNull(current)) {
			current.getCombo().getListaBancos().addAll(setBancos);
			Collections.sort(current.getCombo().getListaBancos(), new ComparatorParametroBancoDtoParaAcuerdos());
			dataSoucers.getLista().add(current);
			setBancos = new java.util.HashSet<ParametroBancoDto>();
		}
		dataSoucers.getListaOrigenes().addAll(setOrigen);
		return dataSoucers;
	}
	@Override
	public List<TipoValorDto> buscarTiposValor(String idEmpresa, String tipoTipoValor) throws NegocioExcepcion {
		List<TipoValorBean> listaBeans = combosServicio.buscarTiposValor(idEmpresa, tipoTipoValor);
		List<TipoValorDto> listaDtos = new ArrayList<TipoValorDto>();

		for (TipoValorBean bean : listaBeans) {
			TipoValorDto dto = new TipoValorDto();
			dto.setIdSegmento(bean.getIdSegmento());
			dto.setIdTipoValor(bean.getIdTipoValor());
			dto.setDescripcion(bean.getDescripcion());
			dto.setIdEmpresa(bean.getIdEmpresa());
			listaDtos.add(dto);
		}
		return listaDtos;
	}
	
	
	@Override
	public Mapeador getDefaultMapeador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefaultMapeador(Mapeador defaultMapeador) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public BancosParametroDto listaBancosAgrupadosPorDescipcion(String idEmpresa) throws NegocioExcepcion {
		List<ShvParamBanco> listaShvParamBanco = combosServicio.listarBancoOrigen(idEmpresa);
		
		BancosParametroDto bancosParametroDto = new BancosParametroDto();
		Map<String, AgrupadorParametroBancoDto> mapBanco = new HashMap<String, AgrupadorParametroBancoDto>();
		int idValue = 0;
		
		for (ShvParamBanco shvParamBanco : listaShvParamBanco) {
			ParametroBancoDto parametroBancoDto = new ParametroBancoDto();
			parametroBancoDto.setIdBanco(shvParamBanco.getIdBanco());
			parametroBancoDto.setDescripcion(shvParamBanco.getDescripcion().trim());
			bancosParametroDto.getListaBancos().add(parametroBancoDto);

			AgrupadorParametroBancoDto agrupador = mapBanco.get(parametroBancoDto.getDescripcion());
			if (Validaciones.isObjectNull(agrupador)) {
				agrupador = new AgrupadorParametroBancoDto();
				agrupador.getBancos().add(parametroBancoDto);
				agrupador.setDescripcion(parametroBancoDto.getDescripcion());
				agrupador.setIdValue(idValue);
				parametroBancoDto.setIdAgrupador(idValue);
				idValue++;
				mapBanco.put(agrupador.getDescripcion(), agrupador);
			} else {
				parametroBancoDto.setIdAgrupador(agrupador.getIdValue());
				agrupador.getBancos().add(parametroBancoDto);
			}
		}
		bancosParametroDto.setAgrupadores(new TreeSet<AgrupadorParametroBancoDto>(mapBanco.values()));
		return bancosParametroDto; 
	}
	
	/**
	 * Sprint 5
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override
	public @ResponseBody List<SelectOptionJsonResponse> listarCopropietarioLegajoChequeRechazadoPorEmpresaYSegmento(String idEmpresa, String idSegmento, Collection<String> usuariosExcluidos) throws Exception {
		
		List<SelectOptionJsonResponse> result = new ArrayList<SelectOptionJsonResponse>();
		SelectOptionJsonResponse jsonResp = null;
		if (!Validaciones.isNullEmptyOrDash(idSegmento)) {
			List<UsuarioLdapDto> copropietarios = (List<UsuarioLdapDto>) combosServicio.listarCopropietarioLegajoChequeRechazadoPorEmpresaYSegmento(idEmpresa, idSegmento, usuariosExcluidos);
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
	@Override
	public @ResponseBody List<SelectOptionJsonResponse> listarCopropietarioPorEmpresaYSegmento(String idEmpresa, String idSegmento, Collection<String> usuariosExcluidos) throws Exception {
		
		List<SelectOptionJsonResponse> result = new ArrayList<SelectOptionJsonResponse>();
		SelectOptionJsonResponse jsonResp = null;
		if (!Validaciones.isNullEmptyOrDash(idSegmento)) {
			List<UsuarioLdapDto> copropietarios = (List<UsuarioLdapDto>) combosServicio.listarCopropietarioPorEmpresaYSegmento(idEmpresa, idSegmento, usuariosExcluidos);
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
	@Override
	public List<EstadoDto> listarEstadosLegajo() throws NegocioExcepcion {
		
		ArrayList<EstadoDto> listaEstados = new ArrayList<EstadoDto>();
		
		for (Estado estado : combosServicio.listarEstadosLegajo()) {
			if(!Estado.LGJ_NO_DISPONIBLE.equals(estado)){
				EstadoDto estadoDto = new EstadoDto();
	
				estadoDto.setDescripcion(estado.descripcion());
				estadoDto.setNombre(estado.name());
				
				listaEstados.add(estadoDto);
			}
		}
		
		return listaEstados;
	}
	@Override
	public List<ProvinciaParametroDto> listarProvincias() throws NegocioExcepcion {
		List<ProvinciaParametroDto> lista = new ArrayList<ProvinciaParametroDto>();

		for (ShvParamProvincia provincia : combosServicio.listarProvincias()) {
			ProvinciaParametroDto dto = new ProvinciaParametroDto();
			dto.setDescripcion(provincia.getDescripcion());
			dto.setIdProvincia(provincia.getIdProvincia());
			lista.add(dto);
		}
		return lista;
	}


	/* (non-Javadoc)
	 * @see ar.com.telecom.shiva.presentacion.facade.ICombosFacade#listar(java.lang.String)
	 */
	@Override
	public ComboAcuerdoBancoCuentaGralDto listar(String idEmpresa)
			throws NegocioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<MonedaEnum, Map<SociedadEnum, Map<SistemaEnum, Map<TipoComprobanteEnum, Map<MonedaEnum, Map<String, ParamConfReglaCampo>>>>>> listarCombosCamposOtrosDebito(MonedaEnum monedaOperacion) throws NegocioExcepcion {
		List<Map<String, Object>> configuracion = this.combosServicio.obtnerConfiguracionRegla(monedaOperacion, ConfCampoTipoEnum.CONF_OTROS_DEBITOS);
		
		MonedaEnum monedaOper = null;
		SociedadEnum sociedad = null;
		MonedaEnum moneda = null;
		SistemaEnum sistema = null;
		TipoComprobanteEnum tipoComprobante = null;
		Map<String, Object> registro = null;
		Iterator<Map<String, Object>> iterador = configuracion.iterator();
		Map<MonedaEnum, Map<SociedadEnum, Map<SistemaEnum, Map<TipoComprobanteEnum, Map<MonedaEnum, Map<String, ParamConfReglaCampo>>>>>> map = 
			new HashMap<MonedaEnum, Map<SociedadEnum, Map<SistemaEnum, Map<TipoComprobanteEnum, Map<MonedaEnum, Map<String, ParamConfReglaCampo>>>>>>();
		Long ordern = -1l;
		boolean salidaLista = false;
		if (iterador.hasNext()) {
			registro = iterador.next();
		} else {
			salidaLista = true;
		}
		while (!salidaLista) {
			boolean salida = false;
			Long orderCurrent = -1l;

			if (!Validaciones.isObjectNull(registro.get("MONEDA_OPERACION"))) {
				monedaOper = MonedaEnum.getEnumByName((String)registro.get("MONEDA_OPERACION"));
			} else {
				throw new NegocioExcepcion("Construccion de campos fallida No existe: MONEDA_OPERACION");
			}
			if (!Validaciones.isObjectNull(registro.get("SOCIEDAD"))) {
				sociedad = SociedadEnum.getEnumByName((String)registro.get("SOCIEDAD"));
			} else {
				throw new NegocioExcepcion("Construccion de campos fallida No existe: SOCIEDAD");
			}
			if (!Validaciones.isObjectNull(registro.get("SISTEMA_ORIGEN"))) {
				sistema = SistemaEnum.getEnumByName((String)registro.get("SISTEMA_ORIGEN"));
			} else {
				throw new NegocioExcepcion("Construccion de campos fallida No existe: SISTEMA_ORIGEN");
			}
			if (!Validaciones.isObjectNull(registro.get("TIPO_COMPROBANTE"))) {
				tipoComprobante = TipoComprobanteEnum.getEnumByName((String)registro.get("TIPO_COMPROBANTE"));
			} else {
				throw new NegocioExcepcion("Construccion de campos fallida No existe: TIPO_COMPROBANTE");
			}
			if (!Validaciones.isObjectNull(registro.get("MONEDA"))) {
				moneda = MonedaEnum.getEnumByName((String)registro.get("MONEDA"));
			} else {
				throw new NegocioExcepcion("Construccion de campos fallida No existe: MONEDA");
			}
			// Deberia ir alprincipi fuera de while
			if (!Validaciones.isObjectNull(registro.get("ORDEN"))) {
				orderCurrent = ((BigDecimal) registro.get("ORDEN")).longValue();
			} else {
				throw new NegocioExcepcion("Construccion de campos fallida No existe: ORDEN");
			}
			Map<String, ParamConfReglaCampo> campo = this.mapearNivel1(
				monedaOper,
				sociedad,
				moneda,
				sistema,
				tipoComprobante,
				map
			);
			while (!salida) {
				// Completo datos de campo
				ParamConfReglaCampo paramConf = new ParamConfReglaCampo();
				if (!Validaciones.isObjectNull(registro.get("NOMBRE"))) {
					paramConf.setNombre((String)registro.get("NOMBRE"));
				} else {
					throw new NegocioExcepcion("Construccion de campos fallida No existe: NOMBRE");
				}
				if (!Validaciones.isObjectNull(registro.get("LONGITUD"))) {
					paramConf.setLongitud((String)registro.get("LONGITUD"));
				} else {
					throw new NegocioExcepcion("Construccion de campos fallida No existe: LONGITUD");
				}
				if (!Validaciones.isObjectNull(registro.get("VALIDACION"))) {
					paramConf.setValidacion((String)registro.get("VALIDACION"));
				}
				if (!Validaciones.isObjectNull(registro.get("VALIDACION_MSG"))) {
					paramConf.setValidacionMsg((String)registro.get("VALIDACION_MSG"));
				}
				if (!Validaciones.isObjectNull(registro.get("TIPO_DE_DATO"))) {
					paramConf.setTipoDeDato((String)registro.get("TIPO_DE_DATO"));
				} else {
					throw new NegocioExcepcion("Construccion de campos fallida No existe: TIPO_DE_DATO");
				}
				if (!Validaciones.isObjectNull(registro.get("OBLIGATORIEDAD"))) {
					paramConf.setObligatoriedad(ConfFormObligatoriedadEnum.getEnumByName((String)registro.get("OBLIGATORIEDAD")));
				} else {
					throw new NegocioExcepcion("Construccion de campos fallida No existe: OBLIGATORIEDAD");
				}
				if (!Validaciones.isObjectNull(registro.get("TIPO_CAMPO"))) {
					paramConf.setTipoCampo((String)registro.get("TIPO_CAMPO"));
				} else {
					throw new NegocioExcepcion("Construccion de campos fallida No existe: TIPO_CAMPO");
				}
				
				campo.put(paramConf.getNombre(), paramConf);
				
				if (iterador.hasNext()) {
					registro = iterador.next();
					
					if (!Validaciones.isObjectNull(registro.get("ORDEN"))) {
						orderCurrent = ((BigDecimal) registro.get("ORDEN")).longValue();
					} else {
						throw new NegocioExcepcion("Construccion de campos fallida No existe: ORDEN");
					}
		
					if (ordern <= orderCurrent) {
						salida = true;
					} else {
						ordern = orderCurrent;
					}
				} else {
					// Salgo de toda la iteracion
					salida = true;
					salidaLista = true;
				}
			}
		}

		return map;
	}

	public Map<String, ParamConfReglaCampo> mapearNivel1(
		MonedaEnum monedaOperacion,
		SociedadEnum sociedad,
		MonedaEnum moneda,
		SistemaEnum sistema,
		TipoComprobanteEnum tipoComprobante,
		Map<MonedaEnum, Map<SociedadEnum, Map<SistemaEnum, Map<TipoComprobanteEnum, Map<MonedaEnum, Map<String, ParamConfReglaCampo>>>>>> map
	) {
		Map<SociedadEnum, Map<SistemaEnum, Map<TipoComprobanteEnum, Map<MonedaEnum, Map<String, ParamConfReglaCampo>>>>> mapSociedad = map.get(monedaOperacion);
		// Nivel 1 Moneda Origen
		if (Validaciones.isObjectNull(mapSociedad)) {
			mapSociedad = new HashMap<SociedadEnum, Map<SistemaEnum, Map<TipoComprobanteEnum, Map<MonedaEnum, Map<String, ParamConfReglaCampo>>>>>();
			map.put(monedaOperacion, mapSociedad);
		}
		// nivel 2 Sociedad
		Map<SistemaEnum, Map<TipoComprobanteEnum, Map<MonedaEnum, Map<String, ParamConfReglaCampo>>>> mapMoneda = mapSociedad.get(sociedad);
		if (Validaciones.isObjectNull(mapMoneda)) {
			mapMoneda = new HashMap<SistemaEnum, Map<TipoComprobanteEnum, Map<MonedaEnum, Map<String, ParamConfReglaCampo>>>>();
			mapSociedad.put(sociedad, mapMoneda);
		}
		// Nivel 3 Moneda
		Map<TipoComprobanteEnum, Map<MonedaEnum, Map<String, ParamConfReglaCampo>>> mapSistema = mapMoneda.get(sistema);
		if (Validaciones.isObjectNull(mapSistema)) {
			mapSistema = new HashMap<TipoComprobanteEnum, Map<MonedaEnum, Map<String, ParamConfReglaCampo>>>();
			mapMoneda.put(sistema, mapSistema);
		}
		Map<MonedaEnum, Map<String, ParamConfReglaCampo>> mapTipo = mapSistema.get(tipoComprobante);
		if (Validaciones.isObjectNull(mapTipo)) {
			mapTipo = new HashMap <MonedaEnum, Map<String, ParamConfReglaCampo>>();
			mapSistema.put(tipoComprobante, mapTipo);
		}
		Map<String, ParamConfReglaCampo> marCampo = mapTipo.get(moneda);
		if (Validaciones.isObjectNull(marCampo)) {
			marCampo = new HashMap <String, ParamConfReglaCampo>();
			mapTipo.put(moneda, marCampo);
		}
		return marCampo;
	}
	
	public List<TipoTratamientoDiferenciaEnum> listarComboTratamientoDiferenciaCred() {
		ArrayList<TipoTratamientoDiferenciaEnum> listaFiltradaTratamientoDifCred = new ArrayList<TipoTratamientoDiferenciaEnum>();
		
		for (TipoTratamientoDiferenciaEnum tratamientoDifCred : TipoTratamientoDiferenciaEnum.getEnumComboTratamientoDiferenciaCredito()) {
			if (!TipoTratamientoDiferenciaEnum.APLICACION_MANUAL.equals(tratamientoDifCred)) {
				listaFiltradaTratamientoDifCred.add(tratamientoDifCred);
			}
		}
		return listaFiltradaTratamientoDifCred;
	}
}
