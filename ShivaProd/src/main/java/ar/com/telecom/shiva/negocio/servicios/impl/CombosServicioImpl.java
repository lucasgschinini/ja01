package ar.com.telecom.shiva.negocio.servicios.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.ConfCampoTipoEnum;
import ar.com.telecom.shiva.base.enumeradores.EstadoActivoInactivoEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.MotivoCobroUsoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.negocio.servicios.ICombosServicio;
import ar.com.telecom.shiva.negocio.servicios.ILegajoChequeRechazadoServicio;
import ar.com.telecom.shiva.negocio.servicios.IRegistroAVCServicio;
import ar.com.telecom.shiva.negocio.servicios.ITalonarioServicio;
import ar.com.telecom.shiva.negocio.servicios.bean.TipoValorBean;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.dao.IAcuerdoDao;
import ar.com.telecom.shiva.persistencia.dao.IBancoCuentaDao;
import ar.com.telecom.shiva.persistencia.dao.IBancoDao;
import ar.com.telecom.shiva.persistencia.dao.IEmpresaDao;
import ar.com.telecom.shiva.persistencia.dao.IEmpresaSegmentoDao;
import ar.com.telecom.shiva.persistencia.dao.IGenericoDao;
import ar.com.telecom.shiva.persistencia.dao.IJurisdiccionDao;
import ar.com.telecom.shiva.persistencia.dao.IMotivoCobroDao;
import ar.com.telecom.shiva.persistencia.dao.IMotivoLegajoDao;
import ar.com.telecom.shiva.persistencia.dao.IMotivosSuspensionDao;
import ar.com.telecom.shiva.persistencia.dao.IOrigenDao;
import ar.com.telecom.shiva.persistencia.dao.IParametroConfReglaCampoDao;
import ar.com.telecom.shiva.persistencia.dao.IParametroDao;
import ar.com.telecom.shiva.persistencia.dao.IProvinciaDao;
import ar.com.telecom.shiva.persistencia.dao.ISegmentoDao;
import ar.com.telecom.shiva.persistencia.dao.ITipoComprobanteDao;
import ar.com.telecom.shiva.persistencia.dao.ITipoValorDao;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBanco;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBancoCuenta;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamJurisdiccion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoCobro;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoLegajo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamMotivoSuspension;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamOrigen;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamProvincia;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmento;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmentoEmpresa;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoComprobante;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoGestion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoLetraComprobante;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoRetencionImpuesto;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoValor;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.EmpresaFiltro;

public class CombosServicioImpl implements ICombosServicio {

	private IGenericoDao genericoDao;
	private IParametroDao parametroDao;
	private IEmpresaDao empresaDao;
	private ISegmentoDao segmentoDao;
	private IOrigenDao origenDao;
	private IAcuerdoDao acuerdoDao;
	private IBancoDao bancoDao;
	private IBancoCuentaDao bancoCuentaDao;
	private ITalonarioServicio talonarioServicio;
	private ICobroOnlineServicio cobroOnlineServicio;
	private ILegajoChequeRechazadoServicio legajoChequeRechazadoServicio;
	private ITipoValorDao tipoValorDao;
	private IJurisdiccionDao jurisdiccionDao;
	private ITipoComprobanteDao tipoComprobanteDao;
	private IEmpresaSegmentoDao empresaSegmentoDao;
	private IMotivosSuspensionDao motivoSuspensionDao; 
	private IMotivoCobroDao motivoCobroDao;
	private IMotivoLegajoDao motivoLegajoDao;
	private IProvinciaDao provinciaDao;

	@Autowired
	IRegistroAVCServicio registroAVCServicio;

	@Autowired
	ILdapServicio ldapServicio;
	@Autowired
	private IParametroConfReglaCampoDao parametroConfReglaCampoDaoImpl;

	public Collection<?> listar(Class<?> clase) throws NegocioExcepcion {
		try {
			return genericoDao.listar(clase);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e);
		}
	}
	@SuppressWarnings("unchecked")
	public <T> T buscarPorId(Class<T> clase, String campo, String id) throws NegocioExcepcion {
		if (clase != null && campo != null && id != null){
			try {
				List<T> lista = (List<T>) genericoDao.listarPorValor(clase, campo, id);
				return  (!lista.isEmpty())?lista.get(0):null;
			} catch (PersistenciaExcepcion e) {
				throw new NegocioExcepcion(e);
			}
		}
		return null;
	}

	/**
	 * Devuelve las empresas que puede ver el usuario logueado.
	 */
	public List<ShvParamEmpresa> listarEmpresasPorUsuario(UsuarioSesion userSesion) throws NegocioExcepcion {
		try {
			if (userSesion.esAdminTalonario() || userSesion.esAdminValor() || userSesion.esReferenteCobranza() || userSesion.esReferenteCaja() || userSesion.esReferenteOperacionesExternas()) {
				EmpresaFiltro filtro = new EmpresaFiltro();
				filtro.setEstado(EstadoActivoInactivoEnum.ACTIVO);
				List<ShvParamEmpresa> lista = empresaDao.buscar(filtro);
				return lista;
			} else {
				List<ShvParamEmpresa> listaEmpresas = new ArrayList<ShvParamEmpresa>();
				List<String> listaIdEmpresa = (List<String>) userSesion.getIdEmpresas();
				
				for (String idEmpresa : listaIdEmpresa) {
					EmpresaFiltro filtro = new EmpresaFiltro();
					filtro.setEstado(EstadoActivoInactivoEnum.ACTIVO);
					filtro.setIdEmpresa(idEmpresa);
					listaEmpresas.addAll(empresaDao.buscar(filtro));
				}
				return listaEmpresas;
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}

	}

	public ShvParamEmpresa buscarEmpresa(String idEmpresa) throws NegocioExcepcion{
		try {
			EmpresaFiltro filtro = new EmpresaFiltro();
			filtro.setEstado(EstadoActivoInactivoEnum.ACTIVO);
			filtro.setIdEmpresa(idEmpresa);
			List<ShvParamEmpresa> lista = empresaDao.buscar(filtro);
			return (lista.size()>0)?lista.get(0):null;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	public ShvParamBancoCuenta buscarBancoCuentaPorAcuerdo(String idAcuerdo)
			throws NegocioExcepcion {
		try {
			return bancoCuentaDao.buscarBancoCuentaPorIdAcuerdo(idAcuerdo);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public ShvParamBancoCuenta buscarBancoCuentaPorIdAcuerdo(String idAcuerdo)
			throws NegocioExcepcion {
		try {
			return bancoCuentaDao.buscarBancoCuentaPorIdAcuerdo(idAcuerdo);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public ShvParamBancoCuenta buscarBancoCuentaPorCuenta(String idCuenta)
			throws NegocioExcepcion {
		try {
			return bancoCuentaDao.buscarBancoCuentaPorIdCuenta(idCuenta);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public ShvParamBancoCuenta buscarBancoCuentaPorIdCuenta(String idCuenta)
			throws NegocioExcepcion {
		try {
			return bancoCuentaDao.buscarBancoCuentaPorIdCuenta(idCuenta);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	
	public ShvParamBanco buscarBancoPorIdCuenta(String idCuenta)
			throws NegocioExcepcion {
		try {
			return bancoCuentaDao.buscarBancoPorIdCuenta(idCuenta);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public ShvParamAcuerdo buscarAcuerdoConciliablePorIdCuenta(String idCuenta)
			throws NegocioExcepcion {
		try {
			return acuerdoDao.buscarAcuerdoConciliablePorIdCuenta(idCuenta);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public ShvParamAcuerdo buscarAcuerdoPorIdCuenta(String idCuenta)
			throws NegocioExcepcion {
		try {
			return acuerdoDao.buscarAcuerdoPorIdCuenta(idCuenta);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	
	public ShvParamAcuerdo buscarAcuerdoNoConciliablesYconciliablesPorIdCuenta(String idCuenta, String esConciliable)
			throws NegocioExcepcion {
		try {
			return acuerdoDao.buscarAcuerdoNoConciliablesYconciliablesPorIdCuenta(idCuenta,  esConciliable);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public List<ShvParamBancoCuenta> listarCuentaPorIdBanco(String idBanco,String esConciliable)
			throws NegocioExcepcion {
		try {
			return bancoCuentaDao.listarCuentaPorIdBanco(idBanco, esConciliable);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public List<ShvParamAcuerdo> listarAcuerdoPorBanco(String idBanco)
			throws NegocioExcepcion {
		try {
			return acuerdoDao.listarAcuerdoPorIdBanco(idBanco);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public List<ShvParamAcuerdo> listarAcuerdoNoConciliablePorIdBanco(String idBanco, String esConcialiable)
			throws NegocioExcepcion {
		try {
			return acuerdoDao.listarAcuerdoNoConciliableYconciliablePorIdBanco(idBanco,  esConcialiable);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	/**
	 * 
	 */
	public List<ShvParamSegmento> listarSegmentoPorEmpresaYUsuario(
			String idEmpresa, UsuarioSesion userSesion) throws NegocioExcepcion {

		try {
			List<ShvParamSegmentoEmpresa> listaSegmentoEmpresa = segmentoDao.listarSegmentoPorIdEmpresa(idEmpresa);
			List<ShvParamSegmento> listaSegmento = new ArrayList<ShvParamSegmento>();

			if (!(userSesion.esAdminTalonario() || userSesion.esAdminValor() || userSesion.esReferenteCobranza() || userSesion.esPerfilConsulta() || userSesion.esReferenteCaja() || userSesion.esReferenteOperacionesExternas())) {
				List<String> listaIdSegmento = (List<String>) userSesion.getIdSegmentos();

				for (ShvParamSegmentoEmpresa segmentoEmpresa : listaSegmentoEmpresa) {
					if (listaIdSegmento.contains(segmentoEmpresa.getSegmento()
							.getIdSegmento())) {
						listaSegmento.add(segmentoEmpresa.getSegmento());
					}
				}
			} else {
				for (ShvParamSegmentoEmpresa segmentoEmpresa : listaSegmentoEmpresa) {
					listaSegmento.add(segmentoEmpresa.getSegmento());
				}
			}

			return listaSegmento;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
		public List<ShvParamOrigen> listarOrigenPorEmpresaSegmentoValor(
			String idEmpresa, String idSegmento, String tipoValor)
			throws NegocioExcepcion {
		try {
			return origenDao.listarOrigenPorIdEmpresaYIdSegmentoIdTipoValor(
					idEmpresa, idSegmento, tipoValor);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	public List<ShvParamOrigen> listarOrigenAnalista() throws NegocioExcepcion {
		try {
			return origenDao.listarOrigenAnalista();
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	public ShvParamOrigen buscarOrigenPorId(String idOrigen)
			throws NegocioExcepcion {
		try {
			if(!Validaciones.isNullOrEmpty(idOrigen)){
				return origenDao.buscarOrigen(idOrigen);
			} else {
				return null;
			}
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}


	
	public List<ShvParamAcuerdo> actualizarAcuerdoPorIdOrigenValor(String idEmpresa, String idSegmento, String idOrigen,
			String tipoValor) throws NegocioExcepcion {
		try {
			return acuerdoDao.actualizarAcuerdoPorIdOrigenValor(idEmpresa,	idSegmento, idOrigen, tipoValor);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public List<ShvParamOrigen> listarOrigenPorIdSegmento(String idEmpresa,	String idSegmento) throws NegocioExcepcion {
		try {
			return origenDao.listarOrigenPorIdEmpresaYIdSegmento(idEmpresa,	idSegmento);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public List<ShvParamAcuerdo> actualizarAcuerdoPorIdOrigen(String idEmpresa,
			String idSegmento, String idOrigen) throws NegocioExcepcion {
		try {
			return acuerdoDao.actualizarAcuerdoPorIdOrigen(idEmpresa, idSegmento, idOrigen);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	public List<ShvParamBanco> actualizarBancoPorIdOrigenValor(
			String idEmpresa, String idSegmento, String idOrigen,
			String tipoValor) throws NegocioExcepcion {
		try {
			return bancoCuentaDao.actualizarBancoPorIdOrigenValor(idEmpresa,
					idSegmento, idOrigen, tipoValor);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	public List<ShvParamBanco> actualizarBancoPorIdAcuerdo(String idAcuerdo) throws NegocioExcepcion {
		try {
			return bancoCuentaDao.actualizarBancoPorAcuerdo(idAcuerdo);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	public List<ShvParamBanco> listarBancosNoConciliables(List<ShvParamAcuerdo> listaAcuerdo) throws NegocioExcepcion {
		try {
			return bancoCuentaDao.listarBancosNoConciliables(listaAcuerdo);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	
	
	public List<ShvParamAcuerdo> actualizarAcuerdoPorIdSegmento(String idEmpresa,
			String idSegmento) throws NegocioExcepcion {
		try {
			return acuerdoDao.actualizarAcuerdoPorIdSegmento(idEmpresa,
					idSegmento);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	public List<ShvParamAcuerdo> actualizarAcuerdoNoConciliables(String idEmpresa,
			String idSegmento, String tipoValor) throws NegocioExcepcion {
		try {
			return acuerdoDao.listarAcuerdoNoConciliables(idEmpresa, idSegmento, tipoValor);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public List<ShvParamBanco> actualizarBancoPorIdOrigen(String idEmpresa,
			String idSegmento, String idOrigen) throws NegocioExcepcion {
		try {
			return bancoCuentaDao.actualizarBancoPorIdOrigen(idEmpresa,	idSegmento, idOrigen);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	public List<ShvParamBancoCuenta> actualizarCuentaPorIdOrigenValor(
			String idEmpresa, String idSegmento, String idOrigen,
			String tipoValor) throws NegocioExcepcion {
		try {
			return bancoCuentaDao.actualizarCuentaPorIdOrigenValor(idEmpresa, idSegmento, idOrigen, tipoValor);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	public List<ShvParamBancoCuenta> actualizarCuentaPorAcuerdo(
			 String idAcuerdo) throws NegocioExcepcion {
		try {
			return bancoCuentaDao.actualizarCuentaPorAcuerdo(idAcuerdo);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	
	public List<ShvParamBancoCuenta> listarCuentaNoConciliables(List<ShvParamAcuerdo> listaAcuerdo) throws NegocioExcepcion {
		try {
			return bancoCuentaDao.listarCuentaNoConciliables( listaAcuerdo);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	public List<ShvParamBanco> actualizarBancoPorIdSegmento(String idEmpresa,
			String idSegmento) throws NegocioExcepcion {
		try {
			return bancoCuentaDao.actualizarBancoPorIdSegmento(idEmpresa,
					idSegmento);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public List<ShvParamBanco> listarBancoOrigen() throws NegocioExcepcion {
		try {
			return bancoDao.buscarBancoOrigen();
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	public ShvParamBanco buscarBanco(String idBanco) throws NegocioExcepcion {
		try {
			return bancoDao.buscarBanco(idBanco);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public List<ShvParamBancoCuenta> actualizarCuentaPorIdOrigen(
			String idEmpresa, String idSegmento, String idOrigen)
			throws NegocioExcepcion {
		try {
			return bancoCuentaDao.actualizarCuentaPorIdOrigen(idEmpresa,
					idSegmento, idOrigen);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	public List<ShvParamBancoCuenta> actualizarCuentaPorIdSegmento(
			String idEmpresa, String idSegmento)
			throws NegocioExcepcion {
		try {
			return bancoCuentaDao.actualizarCuentaPorIdSegmento(idEmpresa,
					idSegmento);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public List<ShvParamTipoValor> buscarTipoValorBoletaConValor(ValorDto boletaDto)
			throws NegocioExcepcion {
		try {
			return tipoValorDao.buscarTipoValorBoletaCV( boletaDto);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public List<ShvParamTipoValor> buscarTipoValorAvisoPago(ValorDto boletaDto)
			throws NegocioExcepcion {
		try {
			return tipoValorDao.buscarTipoValorAvisoPag( boletaDto);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public List<ShvParamOrigen> listarOrigenPorSegmento(String idEmpresa,
			String idSegmento) throws NegocioExcepcion {
		try {
			return origenDao.listarOrigenPorIdEmpresaYIdSegmento(idEmpresa,
					idSegmento);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public List<ShvParamAcuerdo> actualizarAcuerdoPorIdOrigenBoletaSinValor(String idEmpresa,
			String idSegmento, String idOrigen) throws NegocioExcepcion {
		try {
			return acuerdoDao.actualizarAcuerdoPorIdOrigenValor(idEmpresa,
					idSegmento, idOrigen,String.valueOf(TipoValorEnum.BOLETA_SIN_VALOR.getIdTipoValor()));
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public List<ShvParamBanco> actualizarBancoPorIdOrigenBoletaSinValor(String idEmpresa,
			String idSegmento, String idOrigen) throws NegocioExcepcion {
		try {
			return bancoCuentaDao.actualizarBancoPorIdOrigenValor(idEmpresa,
					idSegmento, idOrigen,String.valueOf(TipoValorEnum.BOLETA_SIN_VALOR.getIdTipoValor()));
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public List<ShvParamBancoCuenta> actualizarCuentaPorIdOrigenBoletaSinValor(
			String idEmpresa, String idSegmento, String idOrigen)
			throws NegocioExcepcion {
		try {
			return bancoCuentaDao.actualizarCuentaPorIdOrigenValor(idEmpresa,
					idSegmento, idOrigen,String.valueOf(TipoValorEnum.BOLETA_SIN_VALOR.getIdTipoValor()));
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	/**
	 * Retorna la lista de copropietarios para una empresa y segmento dados,
	 * teniendo en cuenta no incluir al analista dueño del valor/boleta, y
	 * presentando siempre usuarios con perfil de Analista
	 * 
	 */
	public Collection<UsuarioLdapDto> listarCopropietarioPorEmpresaYSegmento(
			String idEmpresa, String idSegmento,
			Collection<String> usuariosExcluidos) throws NegocioExcepcion {

		Collection<UsuarioLdapDto> usuariosLdap = ldapServicio
				.buscarUsuariosPorEmpresaSegmento(idEmpresa, idSegmento);
		Collection<UsuarioLdapDto> usuariosCopropietarios = new ArrayList<UsuarioLdapDto>();

		for (UsuarioLdapDto usuarioLdapDto : usuariosLdap) {
			// Filtro por perfil Analista y verifico si no es un usuario a
			// excluir
			if (usuarioLdapDto.esAnalista()
					&& !usuariosExcluidos.contains(usuarioLdapDto.getId())) {
				usuariosCopropietarios.add(usuarioLdapDto);
			}
		}

		return usuariosCopropietarios;
	}

	/**
	 * Retorna una lista de estados del talonario
	 */
	public List<Estado> listarEstadosTalonario() throws NegocioExcepcion {
		List<Estado> lista = talonarioServicio.listarComboEstados();

		return lista;
	}
	/**
	 * Retorna una lista de estados de cobro
	 */
	public List<Estado> listarEstadosCobro() throws NegocioExcepcion {
		List<Estado> lista = cobroOnlineServicio.listarComboEstados();

		return lista;
	}

	/**
	 * Retorna una lista de estados de legajos
	 */
	public List<Estado> listarEstadosLegajo() throws NegocioExcepcion {
		List<Estado> lista = legajoChequeRechazadoServicio.listarComboEstados();
		
		return lista;
	}
	
	/**
	 * Retorna todos los estados posibles de los registros AVC.
	 */
	public List<Estado> listarEstadosRegistroAVC() throws NegocioExcepcion {
		List<Estado> lista = registroAVCServicio.listarComboEstadosRegistrosAVC();
		return lista;
	}
	
	/**
	 * Retorna los estados de AVC: AVC_PENDIENTE, AVC_PENDIENTE_CONFIRMAR_ALTA_VALOR y
	 * AVC_ALTA_VALOR_RECHAZADA. Y de valor por reversion: REV_PENDIENTE, REV_PENDIENTE_CONFIRMAR y
	 * REV_RECHAZADA
	 */
	public List<Estado> listarEstadosRegistroAVCYReversionesPendientes() throws NegocioExcepcion {
		List<Estado> listaEstados = new ArrayList<Estado>();

		listaEstados.add(Estado.AVC_PENDIENTE);
		listaEstados.add(Estado.AVC_PENDIENTE_CONFIRMAR_ALTA_VALOR);
		listaEstados.add(Estado.AVC_ALTA_VALOR_RECHAZADA);
		listaEstados.add(Estado.REV_PENDIENTE);
		listaEstados.add(Estado.REV_PENDIENTE_CONFIRMAR);
		listaEstados.add(Estado.REV_RECHAZADA);
		
		return listaEstados;
	}

	@Override
	public List<TipoValorBean> buscarTiposValor(String idEmpresa, String tipoTipoValor) throws NegocioExcepcion {
		try {
			return tipoValorDao.buscarTiposValor(idEmpresa, tipoTipoValor);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	/**
	 * 
	 */
	public List<ShvParamProvincia> listarProvincias() throws NegocioExcepcion {
		try {
			return provinciaDao.listarProvincias();
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	/**
	 * 
	 */
	public List<ShvParamJurisdiccion> listarProvinciasEnJurisdiccion() throws NegocioExcepcion {
		try {
			return jurisdiccionDao.listarProvincias();
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public List<ShvParamTipoComprobante> listarTipoComprobante()
			throws NegocioExcepcion {
		try {
			List<ShvParamTipoComprobante> listaComprobante = new ArrayList<ShvParamTipoComprobante>();
			
			List<TipoComprobanteEnum> tipoComprobantes = new ArrayList<TipoComprobanteEnum>();
			tipoComprobantes.add(TipoComprobanteEnum.FAC);
			tipoComprobantes.add(TipoComprobanteEnum.DEB);
			
			for(TipoComprobanteEnum tipoComprobante : tipoComprobantes){
			listaComprobante.add(tipoComprobanteDao.listarTipoComprobanteClase(tipoComprobante.getValor()));
			}
		
			return listaComprobante;
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public List<ShvParamTipoLetraComprobante> listarLetraComprobante()
			throws NegocioExcepcion {
		try {
			return tipoComprobanteDao.listarTipoLetraComprobante();
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public List<ShvParamTipoRetencionImpuesto> listarTipoImpuesto()
			throws NegocioExcepcion {
		try {
			return tipoComprobanteDao.listarTipoImpuesto();
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	@Override
	public Collection<UsuarioLdapDto> listarCopropietarioCobroPorEmpresaYSegmento(
				String idEmpresa, String idSegmento, Collection<String> usuariosExcluidos) throws NegocioExcepcion {

		Collection<UsuarioLdapDto> usuariosLdap = ldapServicio.buscarUsuariosPorEmpresaSegmento(idEmpresa, idSegmento);
		Collection<UsuarioLdapDto> usuariosCopropietarios = new ArrayList<UsuarioLdapDto>();

		for (UsuarioLdapDto usuarioLdapDto : usuariosLdap) {
			// Filtro por perfil Analista de Cobranza y verifico si no es un usuario a excluir
			if (usuarioLdapDto.esAnalistaCobranza() && !usuariosExcluidos.contains(usuarioLdapDto.getId())) {
				usuariosCopropietarios.add(usuarioLdapDto);
			}
		}

		return usuariosCopropietarios;
	}
	
	@Override
	public Collection<UsuarioLdapDto> listarCopropietarioOperacionMasivaPorEmpresaYSegmento(
			String idEmpresa, String idSegmento, Collection<String> usuariosExcluidos) throws NegocioExcepcion {

		Collection<UsuarioLdapDto> usuariosLdap = ldapServicio.buscarUsuariosPorEmpresaSegmento(idEmpresa, idSegmento);
		Collection<UsuarioLdapDto> usuariosCopropietarios = new ArrayList<UsuarioLdapDto>();

		for (UsuarioLdapDto usuarioLdapDto : usuariosLdap) {
			// Filtro por perfil Analista de Operacion Masiva y verifico si no es un usuario a excluir
			if (usuarioLdapDto.esAnalistaOperacionMasiva() && !usuariosExcluidos.contains(usuarioLdapDto.getId())) {
				usuariosCopropietarios.add(usuarioLdapDto);
			}
		}

		return usuariosCopropietarios;
	}
	
	@Override
	public Collection<UsuarioLdapDto> listarCopropietarioLegajoChequeRechazadoPorEmpresaYSegmento(String idEmpresa, String idSegmento, Collection<String> usuariosExcluidos) throws NegocioExcepcion {

		Collection<UsuarioLdapDto> usuariosLdap = ldapServicio.buscarUsuariosPorEmpresaSegmento(idEmpresa, idSegmento);
		Collection<UsuarioLdapDto> usuariosCopropietarios = new ArrayList<UsuarioLdapDto>();

		for (UsuarioLdapDto usuarioLdapDto : usuariosLdap) {
			// Filtro por perfil Analista de Operacion Masiva y verifico si no es un usuario a excluir
			if (usuarioLdapDto.esAnalistaLegajosChequesRechazados() && !usuariosExcluidos.contains(usuarioLdapDto.getId())) {
				usuariosCopropietarios.add(usuarioLdapDto);
			}
		}

		return usuariosCopropietarios;
	}

	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/
	public IEmpresaDao getEmpresaDao() {
		return empresaDao;
	}

	public void setEmpresaDao(IEmpresaDao empresaDao) {
		this.empresaDao = empresaDao;
	}

	public ISegmentoDao getSegmentoDao() {
		return segmentoDao;
	}

	public void setSegmentoDao(ISegmentoDao segmentoDao) {
		this.segmentoDao = segmentoDao;
	}

	public IAcuerdoDao getAcuerdoDao() {
		return acuerdoDao;
	}

	public void setAcuerdoDao(IAcuerdoDao acuerdoDao) {
		this.acuerdoDao = acuerdoDao;
	}

	public IBancoCuentaDao getBancoCuentaDao() {
		return bancoCuentaDao;
	}

	public void setBancoCuentaDao(IBancoCuentaDao bancoCuentaDao) {
		this.bancoCuentaDao = bancoCuentaDao;
	}

	public IOrigenDao getOrigenDao() {
		return origenDao;
	}

	public void setOrigenDao(IOrigenDao origenDao) {
		this.origenDao = origenDao;
	}

	public ITalonarioServicio getTalonarioServicio() {
		return talonarioServicio;
	}

	public void setTalonarioServicio(ITalonarioServicio talonarioServicio) {
		this.talonarioServicio = talonarioServicio;
	}
	
	public ICobroOnlineServicio getCobroOnlineServicio() {
		return cobroOnlineServicio;
	}

	public void setCobroOnlineServicio(ICobroOnlineServicio cobroOnlineServicio) {
		this.cobroOnlineServicio = cobroOnlineServicio;
	}

	public ITipoValorDao getTipoValorDao() {
		return tipoValorDao;
	}

	public void setTipoValorDao(ITipoValorDao tipoValorDao) {
		this.tipoValorDao = tipoValorDao;
	}

	public IJurisdiccionDao getJurisdiccionDao() {
		return jurisdiccionDao;
	}

	public void setJurisdiccionDao(IJurisdiccionDao jurisdiccionDao) {
		this.jurisdiccionDao = jurisdiccionDao;
	}

	public ITipoComprobanteDao getTipoComprobanteDao() {
		return tipoComprobanteDao;
	}

	public void setTipoComprobanteDao(ITipoComprobanteDao tipoComprobanteDao) {
		this.tipoComprobanteDao = tipoComprobanteDao;
	}

	public IBancoDao getBancoDao() {
		return bancoDao;
	}

	public void setBancoDao(IBancoDao bancoDao) {
		this.bancoDao = bancoDao;
	}

	@Override
	public List<ShvParamAcuerdo> actualizarAcuerdoPorValor(String idEmpresa,
			String idSegmento, String tipoValor) throws NegocioExcepcion {
		try {
			return empresaSegmentoDao.listarEmpresaSegmento(idEmpresa,
					idSegmento, tipoValor);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	public List<ShvParamBanco> listarBancoOrigen(String idEmpresa) throws NegocioExcepcion {
		try {
			return bancoDao.buscarBancoOrigen(idEmpresa);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	public List<ShvParamBanco> listarBancoOrigenOrdenadoPorDescripcion(String idEmpresa) throws NegocioExcepcion {
		try {
			return bancoDao.buscarBancoOrigenOrdenadoPorDescripcion(idEmpresa);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	@Override
	public ShvParamMotivoSuspension listarMotivoSuspensionPorId(String idMotivoSuspension) throws NegocioExcepcion {
		
		try {
			return motivoSuspensionDao.buscarMotivoSuspensionPorID(idMotivoSuspension);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	@Override
	public List<ShvParamMotivoSuspension> listarMotivoSuspension() throws NegocioExcepcion {
		try {
			
			List<ShvParamMotivoSuspension> listarMotivoSuspension= motivoSuspensionDao.buscarMotivoSuspensiones();
			return listarMotivoSuspension;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
	}
	
	@Override
	public List<ShvParamMotivoCobro> listarMotivosConfiguracionCobro() throws NegocioExcepcion {
		try {
			List<ShvParamMotivoCobro> listaMotivosCobros = new ArrayList<ShvParamMotivoCobro>();
			listaMotivosCobros.addAll(motivoCobroDao.listarMotivosCobrosPorUso(MotivoCobroUsoEnum.COBRO));
			listaMotivosCobros.addAll(motivoCobroDao.listarMotivosCobrosPorUso(MotivoCobroUsoEnum.COBRO_SALIDA_AUTOMATICA_VALOR));
			listaMotivosCobros.addAll(motivoCobroDao.listarMotivosCobrosPorUso(MotivoCobroUsoEnum.OPERACION_MASIVA));
			return listaMotivosCobros;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	@Override
	public List<ShvParamMotivoCobro> listarMotivosOperacionMasiva() throws NegocioExcepcion {
		try {
			List<ShvParamMotivoCobro> listaMotivosCobros = motivoCobroDao.listarMotivosCobrosPorUso(MotivoCobroUsoEnum.OPERACION_MASIVA);
			return listaMotivosCobros;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	@Override
	public List<ShvParamMotivoCobro> listarMotivosBusquedaCobros() throws NegocioExcepcion {
		try {
			List<ShvParamMotivoCobro> listaMotivosCobros = new ArrayList<ShvParamMotivoCobro>();
					listaMotivosCobros.addAll(motivoCobroDao.listarMotivosCobrosPorUso(MotivoCobroUsoEnum.COBRO));
					listaMotivosCobros.addAll(motivoCobroDao.listarMotivosCobrosPorUso(MotivoCobroUsoEnum.COBRO_SALIDA_AUTOMATICA_VALOR));
			return listaMotivosCobros;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	@Override
	public List<ShvParamMotivoLegajo> listarMotivosConfiguracionLegajo() throws NegocioExcepcion {
		try {
			List<ShvParamMotivoLegajo> listaMotivosLegajos = motivoLegajoDao.listarMotivosLegajos();
			return listaMotivosLegajos;
			
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	@Override
	public List<ShvParamAcuerdo> listarAcuerdo(String idEmpresa) throws NegocioExcepcion {
		try {
			return acuerdoDao.listarAcuerdo(idEmpresa);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	@Override
	public List<ShvParamAcuerdo> listarAcuerdo(String idEmpresa, String idSegmento) throws NegocioExcepcion {
		try {
			return acuerdoDao.listarAcuerdo(idEmpresa, idSegmento);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	@Override
	public List<ShvParamTipoGestion> listarShvParamTipoGestion(String idEmpresa, Integer idOrigen, List<TipoValorEnum> listaValores, SiNoEnum consiliable) throws NegocioExcepcion {
		try {
			
			return acuerdoDao.listarParamTipoGestion(
				idEmpresa,
				idOrigen,
				listaValores,
				consiliable
			);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	@Override
	public List<Map<String, Object>>  listarShvParamTipoGestionDb(String idEmpresa, Integer idOrigen, List<TipoValorEnum> listaValores, SiNoEnum consiliable) throws NegocioExcepcion {
		try {
			
			return acuerdoDao.listarParamTipoGestionDb(
				idEmpresa,
				idOrigen,
				listaValores,
				consiliable
			);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	@Override
	public List<Map<String, Object>> obtnerConfiguracionRegla(MonedaEnum monedaOperacion, ConfCampoTipoEnum tipoConfiguracion) throws NegocioExcepcion {
		try {
			return parametroConfReglaCampoDaoImpl.obtnerConfiguracionRegla(monedaOperacion, tipoConfiguracion);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	
	
	/**
	 * @return the motivoSuspensionDao
	 */
	public IMotivosSuspensionDao getMotivoSuspensionDao() {
		return motivoSuspensionDao;
	}

	/**
	 * @param motivoSuspensionDao the motivoSuspensionDao to set
	 */
	public void setMotivoSuspensionDao(IMotivosSuspensionDao motivoSuspensionDao) {
		this.motivoSuspensionDao = motivoSuspensionDao;
	}

	/**
	 * @return the genericoDao
	 */
	public IGenericoDao getGenericoDao() {
		return genericoDao;
	}

	/**
	 * @param genericoDao the genericoDao to set
	 */
	public void setGenericoDao(IGenericoDao genericoDao) {
		this.genericoDao = genericoDao;
	}

	/**
	 * @return the parametroDao
	 */
	public IParametroDao getParametroDao() {
		return parametroDao;
	}

	/**
	 * @param parametroDao the parametroDao to set
	 */
	public void setParametroDao(IParametroDao parametroDao) {
		this.parametroDao = parametroDao;
	}

	/**
	 * @return the empresaSegmentoDao
	 */
	public IEmpresaSegmentoDao getEmpresaSegmentoDao() {
		return empresaSegmentoDao;
	}

	/**
	 * @param empresaSegmentoDao the empresaSegmentoDao to set
	 */
	public void setEmpresaSegmentoDao(IEmpresaSegmentoDao empresaSegmentoDao) {
		this.empresaSegmentoDao = empresaSegmentoDao;
	}

	/**
	 * @return the ldapServicio
	 */
	public ILdapServicio getLdapServicio() {
		return ldapServicio;
	}

	/**
	 * @param ldapServicio the ldapServicio to set
	 */
	public void setLdapServicio(ILdapServicio ldapServicio) {
		this.ldapServicio = ldapServicio;
	}

	/**
	 * @return the motivoCobroDao
	 */
	public IMotivoCobroDao getMotivoCobroDao() {
		return motivoCobroDao;
	}
	/**
	 * @param motivoCobroDao the motivoCobroDao to set
	 */
	public void setMotivoCobroDao(IMotivoCobroDao motivoCobroDao) {
		this.motivoCobroDao = motivoCobroDao;
	}
	/**
	 * @return the provinciaDao
	 */
	public IProvinciaDao getProvinciaDao() {
		return provinciaDao;
	}
	/**
	 * @param provinciaDao the provinciaDao to set
	 */
	public void setProvinciaDao(IProvinciaDao provinciaDao) {
		this.provinciaDao = provinciaDao;
	}
	/**
	 * @return the motivoLegajoDao
	 */
	public IMotivoLegajoDao getMotivoLegajoDao() {
		return motivoLegajoDao;
	}
	/**
	 * @param motivoLegajoDao the motivoLegajoDao to set
	 */
	public void setMotivoLegajoDao(IMotivoLegajoDao motivoLegajoDao) {
		this.motivoLegajoDao = motivoLegajoDao;
	}
	/**
	 * @return the legajoChequeRechazadoServicio
	 */
	public ILegajoChequeRechazadoServicio getLegajoChequeRechazadoServicio() {
		return legajoChequeRechazadoServicio;
	}
	/**
	 * @param legajoChequeRechazadoServicio the legajoChequeRechazadoServicio to set
	 */
	public void setLegajoChequeRechazadoServicio(
			ILegajoChequeRechazadoServicio legajoChequeRechazadoServicio) {
		this.legajoChequeRechazadoServicio = legajoChequeRechazadoServicio;
	}
}
