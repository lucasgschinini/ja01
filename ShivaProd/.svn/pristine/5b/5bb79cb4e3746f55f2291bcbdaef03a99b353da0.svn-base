
package ar.com.telecom.shiva.negocio.mapeos;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoChequeEnum;
import ar.com.telecom.shiva.base.enumeradores.UbicacionChequeEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.ILegajoChequeRechazadoServicio;
import ar.com.telecom.shiva.negocio.servicios.ITeamComercialServicio;
import ar.com.telecom.shiva.negocio.workflow.IWorkflowService;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.negocio.workflow.servicios.util.IObservacionesWorkflowServicio;
import ar.com.telecom.shiva.persistencia.dao.IAcuerdoDao;
import ar.com.telecom.shiva.persistencia.dao.IBancoCuentaDao;
import ar.com.telecom.shiva.persistencia.dao.IBancoDao;
import ar.com.telecom.shiva.persistencia.dao.IEmpresaDao;
import ar.com.telecom.shiva.persistencia.dao.IMotivoLegajoDao;
import ar.com.telecom.shiva.persistencia.dao.ISegmentoDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjChequeRechazadoDetalleCliente;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjLegajoChequeRechazado;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfHistorialObservacion;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvLgjLegajoChequeRechazadoSimplificado;
import ar.com.telecom.shiva.presentacion.bean.dto.LegajoChequeRechazadoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.TeamComercialDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

public class LegajoChequeRechazadoMapeador extends Mapeador implements ILegajoChequeRechazadoMapeador {

	@Autowired 
	private IEmpresaDao empresaDao;
	
	@Autowired 
	private ISegmentoDao segmentoDao;
	
	@Autowired
	private IMotivoLegajoDao motivoLegajoDao;
	
	@Autowired
	private IBancoDao bancoDao;
	
	@Autowired
	private IAcuerdoDao acuerdoDao;
	
	@Autowired 
	private ILdapServicio ldapServicio;
	
	@Autowired
	private IBancoCuentaDao bancoCuentaDao;
	
	@Autowired
	private ITeamComercialServicio teamComercialServicio;
	
	@Autowired
	private IObservacionesWorkflowServicio observacionesWorkflowServicio;
	
	@Autowired
	private IWorkflowService workflowService;
	
	@Autowired 
	private ILegajoChequeRechazadoServicio legajoChequeRechazadoServicio;
	
	
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {

		ShvLgjLegajoChequeRechazado modelo = (ShvLgjLegajoChequeRechazado)(vo != null ? vo : new ShvLgjLegajoChequeRechazado());

		try {
			LegajoChequeRechazadoDto legajoDto = (LegajoChequeRechazadoDto) dto;
		
			if (!Validaciones.isNullEmptyOrDash(legajoDto.getIdEmpresa())) {
				modelo.setEmpresa(empresaDao.buscar(legajoDto.getIdEmpresa()));
			}
			if (!Validaciones.isNullEmptyOrDash(legajoDto.getIdSegmento())) {
				modelo.setSegmento(segmentoDao.buscarSegmento(legajoDto.getIdSegmento()));
			}
			
			UsuarioLdapDto analista = ldapServicio.buscarUsuarioPorUidEnMemoria(legajoDto.getIdAnalista());
			UsuarioLdapDto copropietario = ldapServicio.buscarUsuarioPorUidEnMemoria(legajoDto.getIdCopropietario());
//			UsuarioLdapDto analistaTeamComercial;
			String idCliente = null;
			
			if(SistemaEnum.USUARIO.getDescripcion().equals(legajoDto.getSistemaOrigen()) && !Validaciones.isObjectNull(legajoDto.getIdCliente())){
				idCliente = legajoDto.getIdCliente();
			} else if (SistemaEnum.SHIVA.getDescripcion().equals(legajoDto.getSistemaOrigen())){
				if(!legajoDto.getChequeRechazado().getClientes().isEmpty()){
					idCliente = legajoDto.getChequeRechazado().getClientes().get(0).getIdClienteLegado();
				}
			} else if (SistemaEnum.ICE.getDescripcion().equals(legajoDto.getSistemaOrigen())) {
				if (legajoDto.getChequeRechazado().getClientes().size() > 0) {
					idCliente = legajoDto.getChequeRechazado().getClientes().get(0).getIdClienteLegado();
				}
			}
			
			TeamComercialDto teamComercial = null;
			UsuarioLdapDto analistaTeamComercial = null;
			if(!Validaciones.isNullEmptyOrDash(idCliente)){
				teamComercial = teamComercialServicio.buscarTeamComercial(idCliente);
			}
			
			if(!Validaciones.isObjectNull(teamComercial)){
				analistaTeamComercial = ldapServicio.buscarUsuarioPorUidEnMemoria(teamComercial.getUserAnalistaCobranzaDatos());
				if(!Validaciones.isObjectNull(analistaTeamComercial)){
					modelo.setIdAnalistaTeamComercial(analistaTeamComercial.getTuid());
					modelo.setAnalistaTeamComercial(analistaTeamComercial.getNombreCompleto());
				}
			}
			
			modelo.setIdAnalista(legajoDto.getIdAnalista());
			modelo.setAnalista(analista.getNombreCompleto());
			
			if(!Validaciones.isObjectNull(copropietario)) {
				modelo.setIdCopropietario(legajoDto.getIdCopropietario());
				modelo.setCopropietario(copropietario.getNombreCompleto());
			}
			
//			if(!Validaciones.isObjectNull(analistaTeamComercial)) {
//				modelo.setIdAnalistaTeamComercial(legajoDto.getIdAnalistaTeamComercial());
//
//			}
			
			if (!Validaciones.isNullEmptyOrDash(legajoDto.getFechaNotificacion())) {
				modelo.setFechaNotificacion(Utilidad.parseDatePickerString(legajoDto.getFechaNotificacion()));
			}
			if (legajoDto.getMotivoLegajo() != 0) {
				modelo.setMotivoLegajo(motivoLegajoDao.buscarMotivoLegajo(String.valueOf(legajoDto.getMotivoLegajo())));
			}
			modelo.setObservaciones(legajoDto.getObservaciones());
			if (!Validaciones.isNullEmptyOrDash(legajoDto.getUbicacion())) {
				modelo.setUbicacion(UbicacionChequeEnum.getEnumByIndice(Integer.valueOf(legajoDto.getUbicacion())));
			}
			if (!Validaciones.isNullEmptyOrDash(legajoDto.getFechaRechazo())) {
				modelo.setFechaRechazo(Utilidad.parseDatePickerString(legajoDto.getFechaRechazo()));
			}
			if (!Validaciones.isNullEmptyOrDash(legajoDto.getIdBancoOrigen())) {
				modelo.setBancoOrigen(bancoDao.buscarBanco(legajoDto.getIdBancoOrigen()));
			}
			if (!Validaciones.isNullEmptyOrDash(legajoDto.getNumeroCheque())) {
				modelo.setNumeroCheque(Long.valueOf(legajoDto.getNumeroCheque()));
			}
			if (!Validaciones.isNullEmptyOrDash(legajoDto.getFechaVencimiento())) {
				modelo.setFechaVencimiento(Utilidad.parseDatePickerString(legajoDto.getFechaVencimiento()));
			}
			if (!Validaciones.isNullEmptyOrDash(legajoDto.getMoneda())) {
				modelo.setMoneda(MonedaEnum.getEnumBySigno(legajoDto.getMoneda()));
			}
			modelo.setImporte(!Validaciones.isNullEmptyOrDash(legajoDto.getImporte()) ? Utilidad.stringToBigDecimal(legajoDto.getImporte()) : null);

			if (!Validaciones.isNullEmptyOrDash(legajoDto.getIdAcuerdo())) {
				modelo.setAcuerdo(acuerdoDao.buscarAcuerdo(legajoDto.getIdAcuerdo()));
			}
			
			if (!Validaciones.isNullEmptyOrDash(legajoDto.getFechaDeposito())) {
				modelo.setFechaDeposito(Utilidad.parseDatePickerString(legajoDto.getFechaDeposito()));
			}
			
			if (!Validaciones.isNullOrEmpty(legajoDto.getSistemaOrigen())){
				modelo.setSistemaOrigen(SistemaEnum.getEnumByDescripcion(legajoDto.getSistemaOrigen()));
			}
			
			//U562163 - estos datos se mapearan en la tabla SHV_LGJ_CHEQUE_RECHA_DET_CLIEN
//			modelo.setIdClienteLegado(!Validaciones.isNullOrEmpty(legajoDto.getIdCliente()) ? Long.valueOf(legajoDto.getIdCliente()) : null);
//			modelo.setRazonSocial(legajoDto.getDescripcionCliente());
			
			if (!Validaciones.isNullEmptyOrDash(legajoDto.getIdbancoDeposito())) {
				modelo.setBancoDeposito(bancoDao.buscarBanco(legajoDto.getIdbancoDeposito()));
			}
			if (!Validaciones.isNullEmptyOrDash(legajoDto.getCuentaDeposito())) {
				modelo.setCuentaDeposito(bancoCuentaDao.buscarBancoCuentaPorIdCuenta(legajoDto.getCuentaDeposito()));
			}
			if( !Validaciones.isNullEmptyOrDash(legajoDto.getTipoCheque())){
				modelo.setTipoCheque(TipoChequeEnum.getEnumByIndice(Integer.valueOf(legajoDto.getTipoCheque())));
			}
			if( !Validaciones.isObjectNull(legajoDto.getFechaEmision()) ){
				modelo.setFechaEmision(Utilidad.parseDatePickerString(legajoDto.getFechaEmision()));
			}
			
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return modelo;
	}
	
	/**
	 * 
	 */
	public DTO map(Modelo vo) throws NegocioExcepcion {
		LegajoChequeRechazadoDto dto = new LegajoChequeRechazadoDto();

		ShvLgjLegajoChequeRechazado modelo = (ShvLgjLegajoChequeRechazado) vo;
	
		if (!Validaciones.isObjectNull(modelo.getIdLegajoChequeRechazado())){
			dto.setIdLegajoChequeRechazado(modelo.getIdLegajoChequeRechazado());
		}
		if (!Validaciones.isObjectNull(modelo.getEmpresa())) {
			dto.setIdEmpresa(modelo.getEmpresa().getIdEmpresa());
			dto.setEmpresa(modelo.getEmpresa().getDescripcion());
		}
		if (!Validaciones.isObjectNull(modelo.getSegmento())) {
			dto.setIdSegmento(modelo.getSegmento().getIdSegmento());
			dto.setSegmento(modelo.getSegmento().getDescripcion());
		}
		
		dto.setIdAnalista(modelo.getIdAnalista());
		dto.setAnalista(modelo.getAnalista());
		dto.setIdCopropietario(modelo.getIdCopropietario());
		dto.setCopropietario(modelo.getCopropietario());
		
		
		dto.setIdAnalistaTeamComercial(modelo.getIdAnalistaTeamComercial());
		dto.setAnalistaTeamComercial(modelo.getAnalistaTeamComercial());
		
		if (!Validaciones.isObjectNull(modelo.getFechaNotificacion())) {
			dto.setFechaNotificacion(Utilidad.formatDatePicker(modelo.getFechaNotificacion()));
		}
		if (!Validaciones.isObjectNull(modelo.getFechaRechazo())) {
			dto.setFechaRechazo(Utilidad.formatDatePicker(modelo.getFechaRechazo()));
		}
		if (!Validaciones.isObjectNull(modelo.getFechaVencimiento())) {
			dto.setFechaVencimiento(Utilidad.formatDatePicker(modelo.getFechaVencimiento()));
		}
		
		if (!Validaciones.isObjectNull(modelo.getMotivoLegajo())) {
			dto.setMotivoLegajo(modelo.getMotivoLegajo().getIdMotivoLegajo());
			dto.setMotivoLegajoDescripcion(modelo.getMotivoLegajo().getDescripcion());
		}
		//modelo.setObservaciones(legajoDto.getObservaciones());
		if (!Validaciones.isObjectNull(modelo.getUbicacion())) {
			dto.setUbicacion(String.valueOf(modelo.getUbicacion().getIndice()));
			dto.setUbicacionDesc(modelo.getUbicacion().getDescripcion());
		}
		// Editados 
	
		if (!Validaciones.isObjectNull(modelo.getMoneda())) {
			dto.setMoneda(modelo.getMoneda().getSigno2());
			dto.setMondeDesc(modelo.getMoneda().getDescripcion());
		}
	
		dto.setImporte(Utilidad.formatCurrency(modelo.getImporte(), true, true, 2));
		
		if (!Validaciones.isObjectNull(modelo.getSistemaOrigen())) {
			dto.setSistemaOrigen(modelo.getSistemaOrigen().getDescripcion());
		}
		
		if (!Validaciones.isObjectNull(modelo.getFechaDeposito())) {
			dto.setFechaDeposito(Utilidad.formatDatePicker(modelo.getFechaDeposito()));
		}
		if (!Validaciones.isObjectNull(modelo.getFechaEmision())) {
			dto.setFechaEmision(Utilidad.formatDatePicker(modelo.getFechaEmision()));
		}

		if (!Validaciones.isObjectNull(modelo.getBancoOrigen())) {
			dto.setIdBancoOrigen(modelo.getBancoOrigen().getIdBanco());
			dto.setBancoOrigenDescripcion(modelo.getBancoOrigen().getDescripcion());
		}
		if (!Validaciones.isObjectNull(modelo.getNumeroCheque())) {
			dto.setNumeroCheque(modelo.getNumeroCheque().toString());
		}
		if (!Validaciones.isObjectNull(modelo.getAcuerdo())) {
			dto.setIdAcuerdo(modelo.getAcuerdo().getIdAcuerdo().toString());
		}
		
		if(Validaciones.isCollectionNotEmpty(modelo.getChequeRechazado().getClientes())) {
			
			Set<ShvLgjChequeRechazadoDetalleCliente> listaClientes = modelo.getChequeRechazado().getClientes();
			ShvLgjChequeRechazadoDetalleCliente cliente = listaClientes.iterator().next();
			if (!Validaciones.isObjectNull(cliente.getIdClienteLegado())) {
				dto.setIdCliente(cliente.getIdClienteLegado().toString());
			}
			dto.setDescripcionCliente(cliente.getRazonSocial());
		}

		if (!Validaciones.isObjectNull(modelo.getBancoDeposito())) {
			dto.setBancoDeposito(modelo.getBancoDeposito().getIdBanco());
			dto.setBancoDepositoDescripcion(modelo.getBancoDeposito().getDescripcion());
		}
		if (!Validaciones.isObjectNull(modelo.getCuentaDeposito())) {
			dto.setCuentaDeposito(modelo.getCuentaDeposito().getIdBancoCuenta().toString());
			dto.setNumeroCuentaDeposito(modelo.getCuentaDeposito().getCuentaBancaria().toString());
		}
		if (!Validaciones.isObjectNull(modelo.getTipoCheque())) {
			dto.setTipoCheque(Integer.toString(modelo.getTipoCheque().getIdTipoCheque()));
			dto.setTipoChequeDescripcion(modelo.getTipoCheque().getDescripcion());
		}
		
		// Datos del estado
		dto.setEstado(modelo.getWorkflow().getEstado());
		dto.setEstadoDescripcion(modelo.getWorkflow().getEstado().descripcion());
		
		
		//listaObservaciones = observacionesWorkflowServicio.listarObservacionesActualesByIdWorkflowOrdenadoPorFecha(modelo.getWorkflow().getIdWorkflow());
		
		Map<String, String> mapObservaciones = observacionesWorkflowServicio.listarObservacionesHistoricasYActuales(modelo.getWorkflow().getIdWorkflow(), true);

		if (!Validaciones.isNullEmptyOrDash(mapObservaciones.get("current"))) {
			dto.setObservaciones(mapObservaciones.get("current"));
		}
		if (!Validaciones.isNullEmptyOrDash(mapObservaciones.get("historicos"))) {
			dto.setObservacionesAnteriores(mapObservaciones.get("historicos"));
		}

		dto.setIdWorkflow(modelo.getWorkflow().getIdWorkflow());

		dto.setListaComprobantes(legajoChequeRechazadoServicio.listarComprobantes(modelo.getIdLegajoChequeRechazado()));
		
		return dto;
	}
	
	public Modelo mapSimplificado(DTO dto, Modelo vo) throws NegocioExcepcion {
		
		ShvLgjLegajoChequeRechazadoSimplificado modelo = (ShvLgjLegajoChequeRechazadoSimplificado)(vo != null ? vo : new ShvLgjLegajoChequeRechazadoSimplificado());

		try {
			LegajoChequeRechazadoDto legajoDto = (LegajoChequeRechazadoDto) dto;
			
			modelo.setIdLegajoChequeRechazado(legajoDto.getIdLegajoChequeRechazado());
	
			
			if (!Validaciones.isNullEmptyOrDash(legajoDto.getIdCopropietario())){
				modelo.setIdCopropietario(legajoDto.getIdCopropietario());
				// TODO VER SI NO HACE FALTA u578936 M Uehara
				UsuarioLdapDto copropietario = ldapServicio.buscarUsuarioPorUidEnMemoria(legajoDto.getIdCopropietario());
				modelo.setCopropietario(copropietario.getNombreCompleto());
			}
			
//			if (!Validaciones.isNullEmptyOrDash(legajoDto.getCopropietario())){
//				modelo.setCopropietario(legajoDto.getCopropietario());
//			}
			
			
			if (!Validaciones.isNullEmptyOrDash(legajoDto.getUbicacion())) {
				modelo.setUbicacion(UbicacionChequeEnum.getEnumByIndice(Integer.valueOf(legajoDto.getUbicacion())));
			}
			
				
			List<ShvWfHistorialObservacion> obs = observacionesWorkflowServicio.listarObservacionesActualesByIdWorkflowOrdenadoPorFecha(legajoDto.getIdWorkflow());

			if (
				(
					Validaciones.isNullEmptyOrDash(legajoDto.getEstadoDescripcion()) ||
					(
						!Validaciones.isObjectNull(legajoDto.getEstado()) &&
						Estado.LGJ_ABIERTO.equals(legajoDto.getEstado().name())
					)
				) &&
				!obs.isEmpty()
			) {
				if (!Validaciones.isNullEmptyOrDash(legajoDto.getObservaciones())) {
					obs.get(0).setObservacion(legajoDto.getObservaciones());
					observacionesWorkflowServicio.modificarObservacionCorrienteBy(obs.get(0));
				} else {
					ShvWfWorkflow shvWfWorkflow = workflowService.buscarWorkflow(legajoDto.getIdWorkflow());
					observacionesWorkflowServicio.borrarObservacionCorrienteBy(shvWfWorkflow.getWorkflowEstado());
				}
			} else {
				ShvWfWorkflow shvWfWorkflow = workflowService.buscarWorkflow(legajoDto.getIdWorkflow());
				if (!Validaciones.isNullEmptyOrDash(legajoDto.getObservaciones())) {
					observacionesWorkflowServicio.insertarHistorialObservacion(shvWfWorkflow.getWorkflowEstado(), legajoDto.getObservaciones());
				}
			}
			
				
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return modelo;
	}
}
