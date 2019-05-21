package ar.com.telecom.shiva.negocio.mapeos;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.comparador.ComparatorCodigoOperacionExternaDto;
import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MarcaEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.MotivoAdjuntoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.negocio.servicios.IVistaSoporteServicio;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineDao;
import ar.com.telecom.shiva.persistencia.dao.IEmpresaDao;
import ar.com.telecom.shiva.persistencia.dao.IMotivoCobroDao;
import ar.com.telecom.shiva.persistencia.dao.ISegmentoDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCliente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdClientePk;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobroAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCredito;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCreditoPk;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdDebito;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdDebitoPk;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdOtrosDebito;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdOtrosDebitoPk;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdOtrosMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdOtrosMedioPagoPk;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdTratamientoDiferencia;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflowMarca;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroCreditoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDebitoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroMedioDePagoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroOtrosDebitoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroTratamientoDiferenciaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CodigoOperacionExternaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

public class CobroOnlineMapeador extends Mapeador implements ICobroOnlineMapeador {
	
	@Autowired private IEmpresaDao empresaDao;
	@Autowired private ISegmentoDao segmentoDao;
	@Autowired private IMotivoCobroDao motivoCobroDao;
	@Autowired private ILdapServicio ldapServicio;
	@Autowired ICobroOnlineDao cobroOnlineDao;
	@Autowired IVistaSoporteServicio vistaSoporteServicio;
	@Autowired CobroOnlineTransaccionesMapeador cobroOnlineTransaccionesMapeador;
	@Autowired ICobroOnlineServicio cobroOnlineServicio;
	
	
	/******* Mapeadores que se utilizan *******/
	private CobroOnlineClienteMapeador clienteMapeador;
	private CobroOnlineCreditoMapeador creditoMapeador;
	private CobroOnlineDebitoMapeador debitoMapeador;
	private CobroOnlineOtrosDebitoMapeador otrosDebitoMapeador;
	private CobroOnlineOtroMedioPagoMapeador medioPagoMapeador;
	private CobroOnlineTratamientoDiferenciaMapeador tratamientoDiferenciaMapeador;
	private CobroOnlineCodigoOperacionExternaMapeador cobroOnlineCodigoOperacionExternaMapeador;
	private CobroOnlineDocumentoAdjuntoMapeador adjuntoMapeador;
	
	@SuppressWarnings("unchecked")
	public DTO map(Modelo vo) throws NegocioExcepcion {
		try {
			ShvCobEdCobro modelo = (ShvCobEdCobro) vo;
			
			CobroDto cobroDto = new CobroDto();
			
			cobroDto.setId(modelo.getIdCobro());
			cobroDto.setIdCobro(modelo.getIdCobro());
			cobroDto.setIdCobroPadre(modelo.getIdCobroPadre());
			if (modelo.getIdCobro()!=null) {
				cobroDto.setIdCobroFormatiado(modelo.getIdCobro().toString());
			}
			cobroDto.setIdOperacion(modelo.getIdOperacion());
			if(!Validaciones.isObjectNull(modelo.getIdOperacionMasiva())) {
				cobroDto.setIdOperacionMasiva(modelo.getIdOperacionMasiva().toString());
			}
			
			//Para la concurrencia
			cobroDto.setFechaUltimaModificacion(modelo.getWorkflow().getFechaUltimaModificacion());
			cobroDto.setTimeStamp(cobroDto.getTimeStampDTO());
			
			cobroDto.setIdEmpresa(modelo.getEmpresa().getIdEmpresa());
			cobroDto.setEmpresa(modelo.getEmpresa().getDescripcion());
			cobroDto.setIdSegmento(modelo.getSegmento().getIdSegmento());
			cobroDto.setSegmento(modelo.getSegmento().getDescripcion());
			cobroDto.setIdMotivoCobro(String.valueOf(modelo.getMotivo().getIdMotivoCobro()));
			cobroDto.setDescripcionMotivoCobro(modelo.getMotivo().getDescripcion());
			
			cobroDto.setFechaAlta(modelo.getFechaAlta());
			cobroDto.setUsuarioAlta(modelo.getUsuarioAlta());
			cobroDto.setFechaDerivacion(modelo.getFechaDerivacion());
			cobroDto.setUsuarioDerivacion(modelo.getUsuarioDerivacion());
			cobroDto.setFechaAprobacionSupervisorCobranza(modelo.getFechaAprobacionSupervisorCobranza());
			cobroDto.setUsuarioAprobacionSupervisorCobranza(modelo.getUsuarioAprobacionSupervisorCobranza());
			cobroDto.setFechaAprobacionReferenteCobranza(modelo.getFechaAprobacionReferenteCobranza());
			
			cobroDto.setUsuarioAprobacionReferenteCobranza(modelo.getUsuarioAprobacionReferenteCobranza());
			
			cobroDto.setUsuarioAprobacionSupervisorOperacionesEspeciales(modelo.getUsuarioAprobadorSuperOperacionesEspeciales());
			cobroDto.setFechaAprobacionOperacionesEspeciales(modelo.getFechaAprobacionOperacionesEspeciales());
			
			cobroDto.setNombreApellidoReferenteCaja(modelo.getNombreApellidoReferenteCaja());
			cobroDto.setIdReferenteCaja(modelo.getIdReferenteCaja());
			cobroDto.setFechaReferenteCaja(modelo.getFechaReferenteCaja());
			cobroDto.setNombreApellidoReferenteOperacionExterna(modelo.getNombreApellidoReferenteOperacionExterna());
			cobroDto.setIdReferenteoperacionexterna(modelo.getIdReferenteoperacionexterna());
			cobroDto.setFechaReferenteOperacionExterna(modelo.getFechaReferenteOperacionExterna());
			cobroDto.setFechaRechazoAplicacionManual(modelo.getFechaRechazoAplicacionManual());
			cobroDto.setUsuarioRechazoAplicacionManual(modelo.getUsuarioRechazoAplicacionManual());
			
			UsuarioLdapDto usuarioLdapReferenteCobranza = ldapServicio.buscarUsuarioPorUidEnMemoria(modelo.getUsuarioAprobacionReferenteCobranza());
			cobroDto.setNombreApellidoUsuarioAprobacionReferenteCobranza(usuarioLdapReferenteCobranza != null ? usuarioLdapReferenteCobranza.getNombreCompleto() : "");
			
			UsuarioLdapDto usuarioLdapSupervisorOperacionesEspeciales = ldapServicio.buscarUsuarioPorUidEnMemoria(modelo.getUsuarioAprobadorSuperOperacionesEspeciales());
			cobroDto.setNombreApellidoUsuarioAprobacionSupervisorOperacionesEspeciales(usuarioLdapSupervisorOperacionesEspeciales != null ? usuarioLdapSupervisorOperacionesEspeciales.getNombreCompleto() : "");
			
			cobroDto.setFechaImputacion(modelo.getFechaImputacion());
			cobroDto.setUsuarioImputacion(modelo.getUsuarioImputacion());
			cobroDto.setFechaUltimaModificacion(modelo.getFechaUltimaModificacion());
			cobroDto.setUsuarioModificacion(modelo.getUsuarioUltimaModificacion());
			cobroDto.setObservacion(modelo.getObservacion());
					
			cobroDto.setEstado(modelo.getWorkflow().getEstado());
			cobroDto.setIdWorkflow(modelo.getWorkflow().getIdWorkflow());
			
			/* Fecha ultimo estado */
			Date fechaUltimoEstado = modelo.getWorkflow().getWorkflowEstado().getFechaModificacion();
			
			for (ShvWfWorkflowMarca marca : modelo.getWorkflow().getWorkflowEstado().getWorkflowMarcas()) {
				if (marca.getFechaCreacion().compareTo(fechaUltimoEstado) > 0 && !MarcaEnum.SIMULACION_VACIA.equals(marca.getMarca())) {
					fechaUltimoEstado = marca.getFechaCreacion();
				}
			}
			
			cobroDto.setFechaUltimoEstado(fechaUltimoEstado);
			
			/* Analista */
			UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(modelo.getIdAnalista());
			cobroDto.setIdAnalista(modelo.getIdAnalista());
			cobroDto.setNombreAnalista(usuarioLdap != null?usuarioLdap.getNombreCompleto():"");
			cobroDto.setMailAnalista(usuarioLdap != null?usuarioLdap.getMail():"");
			
			/* Copropietario */
			usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(modelo.getIdCopropietario());
			cobroDto.setIdCopropietario(usuarioLdap!=null?modelo.getIdCopropietario():"");
			cobroDto.setNombreCopropietario(usuarioLdap!=null?usuarioLdap.getNombreCompleto():"");
			cobroDto.setMailCopropietario(usuarioLdap != null?usuarioLdap.getMail():"");
			
			/* Analista Team Comercial */
			// U586743 se cambio de donde se obtenia el team comercial
			//ahora viene del modelo no mas  de Ldap servicio
			cobroDto.setNombreAnalistaTeamComercial(modelo.getNombreApellidoAnalistaTeamComercial());
			cobroDto.setIdAnalistaTeamComercial(modelo.getIdAnalistaTeamComercial());
			
			if (!Validaciones.isNullOrEmpty(modelo.getIdAnalistaTeamComercial())) {
					String[] atc = modelo.getIdAnalistaTeamComercial().split("\\-");
					
				if (!(atc.length>1)){
					UsuarioLdapDto usuarioAnalistaTeamComercial = ldapServicio.buscarUsuarioPorUidEnMemoria(modelo.getIdAnalistaTeamComercial());
					cobroDto.setMailAnalistaTeamComercial(usuarioAnalistaTeamComercial.getMail());
					cobroDto.setUrlFotoAnalistaTeamComercial(cobroDto.urlFotoUsuario(modelo.getIdAnalistaTeamComercial()));
	
				
				}else {
					cobroDto.setNombreAnalistaTeamComercial(ConstantesCobro.GRUPO_TEAM_COMERCIAL);
					cobroDto.setIdAnalistaTeamComercial(null);
				}
			}
			
			
			
			if (Validaciones.isCollectionNotEmpty(modelo.getDebitos())) {
				cobroDto.setDebitos((Set<CobroDebitoDto>)debitoMapeador.map(modelo.getDebitos()));
			} else {
				cobroDto.setDebitos(new HashSet<CobroDebitoDto>());
			}
			if (Validaciones.isCollectionNotEmpty(modelo.getCreditos())) {
				cobroDto.setCreditos((Set<CobroCreditoDto>)creditoMapeador.map(modelo.getCreditos()));
			} else {
				cobroDto.setCreditos(new HashSet<CobroCreditoDto>());
			}
			if (Validaciones.isCollectionNotEmpty(modelo.getClientes())) {
				cobroDto.setClientes((Set<ClienteDto>)clienteMapeador.map(modelo.getClientes()));
			} else {
				cobroDto.setClientes(new HashSet<ClienteDto>());
			}
			if (Validaciones.isCollectionNotEmpty(modelo.getMediosPagos())) {
				cobroDto.setMedios((Set<CobroMedioDePagoDto>)medioPagoMapeador.map(modelo.getMediosPagos()));
			} else {
				cobroDto.setMedios(new HashSet<CobroMedioDePagoDto>());
			}
			if (!Validaciones.isObjectNull(modelo.getTratamientoDiferencia())) {	
				cobroDto.setTratamientoDiferencia(
						(CobroTratamientoDiferenciaDto)tratamientoDiferenciaMapeador.map(modelo.getTratamientoDiferencia()));
			} else {
				cobroDto.setTratamientoDiferencia(new CobroTratamientoDiferenciaDto());
			} 
			if (Validaciones.isCollectionNotEmpty(modelo.getOtrosDebitos())) {
				cobroDto.setOtrosDebitos((Set<CobroOtrosDebitoDto>) otrosDebitoMapeador.map(modelo.getOtrosDebitos()));
			} else {
				cobroDto.setOtrosDebitos(new HashSet<CobroOtrosDebitoDto>());
			}
			
//			List<ShvDocDocumentoAdjunto> listaAdjuntos = cobroOnlineDao.buscarAdjuntosDelCobroOnline(modelo.getIdCobro());
//			if(Validaciones.isCollectionNotEmpty(listaAdjuntos)){
//				for (ShvDocDocumentoAdjunto docAdjunto : listaAdjuntos) {
//					ComprobanteDto comprobante = new ComprobanteDto();
//					comprobante.setId(docAdjunto.getIdValorAdjunto());
//					comprobante.setIdComprobante(docAdjunto.getIdValorAdjunto());
//					comprobante.setNombreArchivo(docAdjunto.getNombreArchivo());
//					comprobante.setDescripcionArchivo(docAdjunto.getDescripcion());
//					comprobante.setDocumento(docAdjunto.getArchivoAdjunto());
//					comprobante.setUsuarioCreacion(docAdjunto.getUsuarioCreacion());
//					comprobante.setFechaAlta(docAdjunto.getFechaCreacion());
//					
//					listaComprobantes.add(comprobante);
//				}
//			}
//			cobroDto.setListaComprobantes(listaComprobantes);
			
			List<ComprobanteDto> lista = new ArrayList<ComprobanteDto>();
			Set<ComprobanteDto> listaComprobantes = new HashSet<ComprobanteDto>();
			Set<ComprobanteDto> listaComprobantesAplicacionManual = new HashSet<ComprobanteDto>();
			Set<ComprobanteDto> listaComprobantesOtrosDebito = new HashSet<ComprobanteDto>();
			
			lista = cobroOnlineServicio.listarComprobantes(modelo.getIdCobro());
			for (ComprobanteDto comprobanteDto : lista) {
				if (MotivoAdjuntoEnum.COMPROBANTE_COBRO.equals(MotivoAdjuntoEnum.getEnumByName(comprobanteDto.getMotivoAdjunto()))){
					listaComprobantes.add(comprobanteDto);
				} else if (MotivoAdjuntoEnum.APLICACION_MANUAL.equals(MotivoAdjuntoEnum.getEnumByName(comprobanteDto.getMotivoAdjunto())) || MotivoAdjuntoEnum.APLI_MANUAL_CONF.equals(MotivoAdjuntoEnum.getEnumByName(comprobanteDto.getMotivoAdjunto()))){
					listaComprobantesAplicacionManual.add(comprobanteDto);
				} else if (MotivoAdjuntoEnum.OTROS_DEBITO.equals(MotivoAdjuntoEnum.getEnumByName(comprobanteDto.getMotivoAdjunto()))){
					listaComprobantesOtrosDebito.add(comprobanteDto);
				}
			}
			cobroDto.setListaComprobantes(listaComprobantes);
			cobroDto.setListaComprobanteAplicacionManual(listaComprobantesAplicacionManual);
			cobroDto.setListaComprobanteOtrosDebito(listaComprobantesOtrosDebito);
			
			cobroDto.setMonedaOperacion(modelo.getMonedaOperacion() != null? modelo.getMonedaOperacion().getSigno2() : null);
			/* Importe */
			cobroDto.setImporteTotalDeudaFormateado(cobroDto.getMonedaOperacion() + Utilidad.formatCurrency(modelo.getImporteTotal(), false, true));
			cobroDto.setImporteTotal(modelo.getImporteTotal());

			// Cotizacion
			if (!Validaciones.isObjectNull(modelo.getFechaTipoCambio())) {
				cobroDto.setFechaTipoCambio(Utilidad.formatDatePicker(modelo.getFechaTipoCambio()));
			}
			if (!Validaciones.isObjectNull(modelo.getTipoCambio())) {
				cobroDto.setTipoCambio(modelo.getTipoCambio());
			}
			/* Codigos Operaciones Externas  */
			if (Validaciones.isCollectionNotEmpty(modelo.getCodigosOperacionesExternas())) {
				Set<CodigoOperacionExternaDto> set = (Set<CodigoOperacionExternaDto>) cobroOnlineCodigoOperacionExternaMapeador.map(modelo.getCodigosOperacionesExternas());
				cobroDto.setListaCodigoOperacionesExternas(new TreeSet<CodigoOperacionExternaDto>(new ComparatorCodigoOperacionExternaDto()));
				cobroDto.getListaCodigoOperacionesExternas().addAll(set);
			} else {
				cobroDto.setListaCodigoOperacionesExternas(new HashSet<CodigoOperacionExternaDto>());
			}
			
			if (!Validaciones.isObjectNull(modelo.getImporteParcial())){
				cobroDto.setImporteParcial(modelo.getImporteParcial());
			}
			
			return cobroDto;
		
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
	

	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		CobroDto cobroDto = (CobroDto) dto;
		
		ShvCobEdCobro modelo = (ShvCobEdCobro)
				(vo != null ? vo : new ShvCobEdCobro());
		try {
			modelo.setEmpresa(empresaDao.buscar(cobroDto.getIdEmpresa()));
			modelo.setSegmento(segmentoDao.buscarSegmento(cobroDto.getIdSegmento()));
			
			if(!Validaciones.isNullOrEmpty(cobroDto.getIdMotivoCobro())){
				modelo.setMotivo(motivoCobroDao.buscarMotivoCobro(cobroDto.getIdMotivoCobro()));
			} else {
				modelo.setMotivo(null);
			}
			
			modelo.setIdAnalista(cobroDto.getIdAnalista());
			modelo.setNombreApellidoAnalista(ldapServicio.buscarUsuarioPorUidEnMemoria(cobroDto.getIdAnalista()).getNombreCompleto());
			
			modelo.setIdCopropietario(cobroDto.getIdCopropietario());
			if(!Validaciones.isNullOrEmpty(cobroDto.getIdCopropietario())) {
				modelo.setNombreApellidoCopropietario(ldapServicio.buscarUsuarioPorUidEnMemoria(cobroDto.getIdCopropietario()).getNombreCompleto());
			}
			
			if ((cobroDto.getOperation() != null 
					&& (ConstantesCobro.NUEVO_COBRO.compareTo(cobroDto.getOperation()) == 0)) || cobroDto.getSetearFechaAlta()) {
				modelo.setFechaAlta(cobroDto.getFechaAlta());
				modelo.setUsuarioAlta(cobroDto.getUsuarioAlta());
			}
			
			modelo.setFechaDerivacion(cobroDto.getFechaDerivacion());
			modelo.setUsuarioDerivacion(cobroDto.getUsuarioDerivacion());
			modelo.setFechaAprobacionSupervisorCobranza(cobroDto.getFechaAprobacionSupervisorCobranza());
			modelo.setUsuarioAprobacionSupervisorCobranza(cobroDto.getUsuarioAprobacionSupervisorCobranza());
			modelo.setFechaAprobacionReferenteCobranza(cobroDto.getFechaAprobacionReferenteCobranza());
			modelo.setUsuarioAprobacionReferenteCobranza(cobroDto.getUsuarioAprobacionReferenteCobranza());
			
			modelo.setUsuarioAprobadorSuperOperacionesEspeciales(cobroDto.getUsuarioAprobacionSupervisorOperacionesEspeciales());
			modelo.setFechaAprobacionOperacionesEspeciales(cobroDto.getFechaAprobacionOperacionesEspeciales());
			modelo.setNombreAprobadorSuperOperacionesEspeciales(cobroDto.getNombreApellidoUsuarioAprobacionSupervisorOperacionesEspeciales());
			
			modelo.setNombreApellidoReferenteCaja(cobroDto.getNombreApellidoReferenteCaja());
			modelo.setIdReferenteCaja(cobroDto.getIdReferenteCaja());
			modelo.setFechaReferenteCaja(cobroDto.getFechaReferenteCaja());
			modelo.setNombreApellidoReferenteOperacionExterna(cobroDto.getNombreApellidoReferenteOperacionExterna());
			modelo.setIdReferenteoperacionexterna(cobroDto.getIdReferenteoperacionexterna());
			modelo.setFechaReferenteOperacionExterna(cobroDto.getFechaReferenteOperacionExterna());
			modelo.setFechaRechazoAplicacionManual(cobroDto.getFechaRechazoAplicacionManual());
			modelo.setUsuarioRechazoAplicacionManual(cobroDto.getUsuarioRechazoAplicacionManual());
			
			modelo.setFechaImputacion(cobroDto.getFechaImputacion());
			modelo.setUsuarioImputacion(cobroDto.getUsuarioImputacion());
			modelo.setFechaUltimaModificacion(cobroDto.getFechaUltimaModificacion());
			modelo.setUsuarioUltimaModificacion(cobroDto.getUsuarioModificacion());
			modelo.setObservacion(cobroDto.getObservacion());
			
			modelo.setNombreApellidoReferenteAprobador(cobroDto.getNombreApellidoUsuarioAprobacionReferenteCobranza());
			modelo.setNombreApellidoSupervisorAprobador(cobroDto.getNombreApellidoUsuarioAprobacionSupervisorCobranza());
			modelo.setIdCobroPadre(cobroDto.getIdCobroPadre());

			if (!Validaciones.isObjectNull(cobroDto.getTratamientoDiferencia()) && 
					!Validaciones.isNullOrEmpty(cobroDto.getTratamientoDiferencia().getTipoTratamientoDiferencia())) {
				
				ShvCobEdTratamientoDiferencia modeloTratamientoDif = null;
				if (modelo.getTratamientoDiferencia() != null) {
					modeloTratamientoDif = modelo.getTratamientoDiferencia();
				}
				
				modelo.setTratamientoDiferencia(
					(ShvCobEdTratamientoDiferencia)
						tratamientoDiferenciaMapeador.map(cobroDto.getTratamientoDiferencia(), modeloTratamientoDif));
				
				modelo.getTratamientoDiferencia().setCobro(modelo);
				modelo.getTratamientoDiferencia().setReferencia(cobroDto.getTratamientoDiferencia().getReferencia());
			} else {
				modelo.setTratamientoDiferencia(null);
			}
			
			modelo.setMonedaOperacion(MonedaEnum.getEnumBySigno2(cobroDto.getMonedaOperacion()));
			// Cotizacion
			if (!Validaciones.isObjectNull(cobroDto.getFechaTipoCambio())) {
				modelo.setFechaTipoCambio(Utilidad.parseDatePickerString(cobroDto.getFechaTipoCambio()));
			} else {
				modelo.setFechaTipoCambio(null);
			}
//			modelo.setTipoCambio(!Validaciones.isNullEmptyOrDash(cobroDto.getTipoCambio()) ? new BigDecimal(cobroDto.getTipoCambio().replace(",", ".")) : null);
			modelo.setTipoCambio(cobroDto.getTipoCambio());
			
			if (!Validaciones.isObjectNull(cobroDto.getImporteParcial())){
				modelo.setImporteParcial(cobroDto.getImporteParcial());
			}
			
			modelo = (ShvCobEdCobro) mapListas(dto, modelo); 
		
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return modelo;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Modelo mapListas(DTO dto, Modelo vo) throws NegocioExcepcion {
		CobroDto cobroDto = (CobroDto) dto;
		
		ShvCobEdCobro modelo = (ShvCobEdCobro)
				(vo != null ? vo : new ShvCobEdCobro());
		try {
			if (Validaciones.isCollectionNotEmpty(cobroDto.getDebitos())) {
				modelo.setDebitos((Set<ShvCobEdDebito>)debitoMapeador.map(cobroDto.getDebitos()));
				long contador = 0;
				for (ShvCobEdDebito obj: modelo.getDebitos()) {
					contador++;
					ShvCobEdDebitoPk pk = new ShvCobEdDebitoPk();
					pk.setCobro(modelo);
					pk.setIdDebito(contador);
					obj.setPk(pk);
				}
			}
			if (Validaciones.isCollectionNotEmpty(cobroDto.getOtrosDebitos())) {
				modelo.setOtrosDebitos((Set<ShvCobEdOtrosDebito>)otrosDebitoMapeador.map(cobroDto.getOtrosDebitos()));
				long contador = 0;
				for (ShvCobEdOtrosDebito obj: modelo.getOtrosDebitos()) {
					contador++;
					ShvCobEdOtrosDebitoPk pk = new ShvCobEdOtrosDebitoPk();
					pk.setCobro(modelo);
					pk.setIdOtrosDebito(contador);
					obj.setPk(pk);
				}
			}
			if (Validaciones.isCollectionNotEmpty(cobroDto.getCreditos())) {
				modelo.setCreditos((Set<ShvCobEdCredito>)creditoMapeador.map(cobroDto.getCreditos()));
				long contador = 0;
				for (ShvCobEdCredito obj: modelo.getCreditos()) {
					contador++;
					ShvCobEdCreditoPk pk = new ShvCobEdCreditoPk();
					pk.setCobro(modelo);
					pk.setIdCredito(contador);
					obj.setPk(pk);
				}
			}
			if (Validaciones.isCollectionNotEmpty(cobroDto.getClientes())) {
				modelo.setClientes((Set<ShvCobEdCliente>)clienteMapeador.map(cobroDto.getClientes()));
				long contador = 0;
				for (ShvCobEdCliente obj: modelo.getClientes()) {
					contador++;
					ShvCobEdClientePk pk = new ShvCobEdClientePk();
					pk.setCobro(modelo);
					pk.setIdClienteCobro(contador);
					obj.setPk(pk);
				}
			}
			if (Validaciones.isCollectionNotEmpty(cobroDto.getMedios())) {
				modelo.setMediosPagos((Set<ShvCobEdOtrosMedioPago>)medioPagoMapeador.map(cobroDto.getMedios()));
				long contador = 0;
				for (ShvCobEdOtrosMedioPago obj: modelo.getMediosPagos()) {
					contador++;
					ShvCobEdOtrosMedioPagoPk pk = new ShvCobEdOtrosMedioPagoPk();
					pk.setCobro(modelo);
					pk.setIdMedioPago(contador);
					obj.setPk(pk);
				}
			}
			
			if (Validaciones.isCollectionNotEmpty(cobroDto.getListaComprobantes())) {
				modelo.setComprobantes((Set<ShvCobEdCobroAdjunto>)adjuntoMapeador.map(cobroDto.getListaComprobantes()));
				
				for (ShvCobEdCobroAdjunto adjunto : modelo.getComprobantes()) {
					adjunto.getPk().setIdCobro(modelo.getIdCobro());
				}
			}
			
			
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return modelo;
	}

	/**********************************************
	 *             Getters & Setters              *
	 **********************************************/
	
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

	public ILdapServicio getLdapServicio() {
		return ldapServicio;
	}

	public void setLdapServicio(ILdapServicio ldapServicio) {
		this.ldapServicio = ldapServicio;
	}


	public CobroOnlineClienteMapeador getClienteMapeador() {
		return clienteMapeador;
	}


	public void setClienteMapeador(CobroOnlineClienteMapeador clienteMapeador) {
		this.clienteMapeador = clienteMapeador;
	}


	public CobroOnlineCreditoMapeador getCreditoMapeador() {
		return creditoMapeador;
	}


	public void setCreditoMapeador(CobroOnlineCreditoMapeador creditoMapeador) {
		this.creditoMapeador = creditoMapeador;
	}


	public CobroOnlineDebitoMapeador getDebitoMapeador() {
		return debitoMapeador;
	}


	public void setDebitoMapeador(CobroOnlineDebitoMapeador debitoMapeador) {
		this.debitoMapeador = debitoMapeador;
	}


	/**
	 * @return the otrosDebitoMapeador
	 */
	public CobroOnlineOtrosDebitoMapeador getOtrosDebitoMapeador() {
		return otrosDebitoMapeador;
	}


	/**
	 * @param otrosDebitoMapeador the otrosDebitoMapeador to set
	 */
	public void setOtrosDebitoMapeador(
			CobroOnlineOtrosDebitoMapeador otrosDebitoMapeador) {
		this.otrosDebitoMapeador = otrosDebitoMapeador;
	}


	public CobroOnlineOtroMedioPagoMapeador getMedioPagoMapeador() {
		return medioPagoMapeador;
	}


	public void setMedioPagoMapeador(
			CobroOnlineOtroMedioPagoMapeador medioPagoMapeador) {
		this.medioPagoMapeador = medioPagoMapeador;
	}


	public CobroOnlineTratamientoDiferenciaMapeador getTratamientoDiferenciaMapeador() {
		return tratamientoDiferenciaMapeador;
	}


	public void setTratamientoDiferenciaMapeador(
			CobroOnlineTratamientoDiferenciaMapeador tratamientoDiferenciaMapeador) {
		this.tratamientoDiferenciaMapeador = tratamientoDiferenciaMapeador;
	}


	public IMotivoCobroDao getMotivoCobroDao() {
		return motivoCobroDao;
	}


	public void setMotivoCobroDao(IMotivoCobroDao motivoCobroDao) {
		this.motivoCobroDao = motivoCobroDao;
	}


	public CobroOnlineCodigoOperacionExternaMapeador getCobroOnlineCodigoOperacionExternaMapeador() {
		return cobroOnlineCodigoOperacionExternaMapeador;
	}


	public void setCobroOnlineCodigoOperacionExternaMapeador(
			CobroOnlineCodigoOperacionExternaMapeador cobroOnlineCodigoOperacionExternaMapeador) {
		this.cobroOnlineCodigoOperacionExternaMapeador = cobroOnlineCodigoOperacionExternaMapeador;
	}


	/**
	 * @return the adjuntoMapeador
	 */
	public CobroOnlineDocumentoAdjuntoMapeador getAdjuntoMapeador() {
		return adjuntoMapeador;
	}


	/**
	 * @param adjuntoMapeador the adjuntoMapeador to set
	 */
	public void setAdjuntoMapeador(
			CobroOnlineDocumentoAdjuntoMapeador adjuntoMapeador) {
		this.adjuntoMapeador = adjuntoMapeador;
	}
}
