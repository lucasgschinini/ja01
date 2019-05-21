package ar.com.telecom.shiva.test.modulos;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroBatchSimulacionServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.negocio.servicios.IParametroServicio;
import ar.com.telecom.shiva.negocio.workflow.servicios.IWorkflowCobros;
import ar.com.telecom.shiva.persistencia.dao.ICobroDao;
import ar.com.telecom.shiva.persistencia.dao.ICobroOnlineDebitoDao;
import ar.com.telecom.shiva.persistencia.dao.IEmpresaDao;
import ar.com.telecom.shiva.persistencia.dao.IMotivoCobroDao;
import ar.com.telecom.shiva.persistencia.dao.ISegmentoDao;
import ar.com.telecom.shiva.persistencia.dao.IWorkflowDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCredito;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCreditoPk;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdTratamientoDiferencia;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class CobroOnlineTest extends SoporteContextoSpringTest {
	
	@Autowired ICobroBatchSimulacionServicio cobroBatchSimulacionServicio;
	@Autowired ICobroDao cobroDao;

	@Autowired ICobroBatchServicio cobroBatchServicio;
	@Autowired IParametroServicio parametroServicio;
	
	@Autowired ICobroOnlineServicio cobroOnlineServicio;
	@Autowired ICobroOnlineDebitoDao debitoDao;
	
	@Autowired IEmpresaDao empresaDao;
	@Autowired ISegmentoDao segmentoDao;
	@Autowired IMotivoCobroDao motivoCobroDao;
	@Autowired IWorkflowDao workflowDao;
	@Autowired IWorkflowCobros workflowCobros;
	
	@Autowired ILdapServicio ldapServicio;

	
	/**
	 * @throws WorkflowExcepcion
	 */
	@Test
	public void crearCobro() {

		ShvCobEdCobro modelo = new ShvCobEdCobro();
		
		try {
			modelo.setEmpresa(empresaDao.buscar(Constantes.EMPRESA_TELECOM_ARGENTINA));
			modelo.setSegmento(segmentoDao.buscarSegmento(Constantes.SEGMENTO_RESIDENCIAL));
			modelo.setMotivo(motivoCobroDao.buscarMotivoCobro("1")); 
			modelo.setIdAnalista("U564030");
			modelo.setNombreApellidoAnalista(ldapServicio.buscarUsuarioPorUidEnMemoria(modelo.getIdAnalista()).getNombreCompleto());
			
			modelo.setIdCopropietario("U564027");
			if(!Validaciones.isNullOrEmpty(modelo.getIdCopropietario())) {
				modelo.setNombreApellidoCopropietario(ldapServicio.buscarUsuarioPorUidEnMemoria(modelo.getIdCopropietario()).getNombreCompleto());
			}
			
			modelo.setFechaAlta(new Date());
			modelo.setUsuarioAlta(modelo.getIdAnalista());
			
//			modelo.setFechaDerivacion(null);
//			modelo.setUsuarioDerivacion(null);
//			modelo.setFechaAprobacionSupervisorCobranza(null);
//			modelo.setUsuarioAprobacionSupervisorCobranza(null);
//			modelo.setFechaAprobacionReferenteCobranza(null);
//			modelo.setUsuarioAprobacionReferenteCobranza(null);
//			
//			modelo.setUsuarioAprobadorSuperOperacionesEspeciales(cobroDto.getUsuarioAprobacionSupervisorOperacionesEspeciales());
//			modelo.setFechaAprobacionOperacionesEspeciales(cobroDto.getFechaAprobacionOperacionesEspeciales());
//			
//			modelo.setNombreApellidoReferenteCaja(cobroDto.getNombreApellidoReferenteCaja());
//			modelo.setIdReferenteCaja(cobroDto.getIdReferenteCaja());
//			modelo.setFechaReferenteCaja(cobroDto.getFechaReferenteCaja());
//			modelo.setNombreApellidoReferenteOperacionExterna(cobroDto.getNombreApellidoReferenteOperacionExterna());
//			modelo.setIdReferenteoperacionexterna(cobroDto.getIdReferenteoperacionexterna());
//			modelo.setFechaReferenteOperacionExterna(cobroDto.getFechaReferenteOperacionExterna());
//			modelo.setFechaRechazoAplicacionManual(cobroDto.getFechaRechazoAplicacionManual());
//			modelo.setUsuarioRechazoAplicacionManual(cobroDto.getUsuarioRechazoAplicacionManual());
//			
//			modelo.setFechaImputacion(cobroDto.getFechaImputacion());
//			modelo.setUsuarioImputacion(cobroDto.getUsuarioImputacion());
			modelo.setFechaUltimaModificacion(new Date());
			modelo.setUsuarioUltimaModificacion(modelo.getIdAnalista());
			modelo.setObservacion("Cobro de prueba generada por Pablo desde un Test");
			
//			modelo.setNombreApellidoReferenteAprobador(cobroDto.getNombreApellidoUsuarioAprobacionReferenteCobranza());
//			modelo.setNombreApellidoSupervisorAprobador(cobroDto.getNombreApellidoUsuarioAprobacionSupervisorCobranza());
//			modelo.setIdCobroPadre(cobroDto.getIdCobroPadre());
			
			modelo.setMonedaOperacion(MonedaEnum.PES);
			
			// Creditos
			ShvCobEdCredito credito = new ShvCobEdCredito();
//			credito.set
//			
//			creditoDto.setIdClienteLegado(modelo.getIdClienteLegado()!=null?modelo.getIdClienteLegado().toString():"");
//			//
//			creditoDto.setClaseComprobante(modelo.getClaseComprobante());
//			creditoDto.setSucursalComprobante(modelo.getSucursalComprobante());
//			creditoDto.setNumeroComprobante(modelo.getNumeroComprobante());
//			//
//			creditoDto.setCuenta(modelo.getCuenta()!=null?modelo.getCuenta().toString():"");
//			creditoDto.setCuit(modelo.getCuit());
//			//
//			creditoDto.setFechaAltaCredito(Utilidad.formatDatePicker(modelo.getFechaAlta()));
//			creditoDto.setFechaEmision(Utilidad.formatDatePicker(modelo.getFechaEmision()));
//			creditoDto.setFechaUltimoMov(Utilidad.formatDatePicker(modelo.getFechaUltimoMovimiento()));
//			creditoDto.setFechaValor(Utilidad.formatDatePicker(modelo.getFechaValor()));
//			creditoDto.setFechaVenc(Utilidad.formatDatePicker(modelo.getFechaVencimiento()));
//			//
//			creditoDto.setImporteAUtilizar(Utilidad.formatCurrency(modelo.getImporteAUtilizar(), false, false));
//			creditoDto.setImpPesificado(Utilidad.formatCurrency(modelo.getImportePesificado(), false, false));
//			creditoDto.setSaldoMonOrigen(Utilidad.formatCurrency(modelo.getSaldoMonedaOrigen(), false, false));
//			creditoDto.setSaldoPesificado(Utilidad.formatCurrency(modelo.getSaldoPesificado(), false, false));
//			creditoDto.setImpMonOrigen(Utilidad.formatCurrency(modelo.getImporteMonedaOrigen(), false, false));
//			//
//			creditoDto.setCodigoTipoRemanente(modelo.getCodigoTipoRemamente());
//			//
//			creditoDto.setMoneda(modelo.getMoneda().getSigno2());
//			creditoDto.setMonedaEnum(modelo.getMoneda());
//			//
//			creditoDto.setProvincia(modelo.getProvincia());
//			creditoDto.setRazonSocialCliente(modelo.getRazonSocialCliente());
//			BigDecimal tipoCambio = modelo.getTipoDeCambio();
//			if(!Validaciones.isObjectNull(tipoCambio)){
//				creditoDto.setTipoCambio(Utilidad.formatDecimales(tipoCambio, 7));
//			}
//			//
//			creditoDto.setSistemaOrigen(modelo.getSistemaOrigen());
//			creditoDto.setSistemaDescripcion(modelo.getSistemaOrigen().getDescripcion());
//			//		
//			/************ VALORES SHIVA **************/
//			creditoDto.setFechaDeposito(Utilidad.formatDatePicker(modelo.getFechaDeposito()));
//			creditoDto.setFechaIngresoRecibo(Utilidad.formatDatePicker(modelo.getFechaIngresoRecibo()));
//			creditoDto.setFechaTrans(Utilidad.formatDatePicker(modelo.getFechaTransferencia()));
//			creditoDto.setDescripcionTipoRetencion(modelo.getTipoRetencion()!=null?modelo.getTipoRetencion().getDescripcion():null);
//			creditoDto.setReferenciaValor(modelo.getReferenciaValor());
//			creditoDto.setIdValor(modelo.getIdValor());
//			
//			/**************************** WS *******************************/
//			creditoDto.setCicloFacturacion(modelo.getCicloFacturacion());
//			creditoDto.setMarcaCyq(modelo.getMarcaCyq());
//			//
//			if(!Validaciones.isObjectNull(modelo.getMarcaReclamo())){
//				MarcaReclamoEnum marcaReclamoEnum = modelo.getMarcaReclamo();
//				creditoDto.setMarcaReclamo(marcaReclamoEnum);
//				creditoDto.setMarcaReclamoDescripcion(marcaReclamoEnum!=null?marcaReclamoEnum.descripcionAMostrar():null);
//			}
//			creditoDto.setMarcaMigradoDeimos(modelo.getMarcaMigradoDeimos());
//			creditoDto.setMarcaMigradaOrigenEnum(modelo.getMarcaMigradoDeimos());
//			//
//			creditoDto.setCodigoTarifa(modelo.getCodigoTarifa());
//			creditoDto.setFechaVencimientoMora(Utilidad.formatDatePicker(modelo.getFechaVencimientoMora()));
//			creditoDto.setHolding(modelo.getHolding());
//			creditoDto.setIdDocumentoCuentaCobranza(modelo.getIdDocumentoCuentaCobranza());
//			creditoDto.setIndicadorPeticionCorte(modelo.getIndicadorPeticionCorte());
//			//
//			creditoDto.setMarketingCustomerGroup(modelo.getMarketingCustomerGroup());
//			creditoDto.setNumeroReferenciaMic(modelo.getNroReferenciaMic()!=null?modelo.getNroReferenciaMic().toString():null);
//			creditoDto.setNumeroAcuerdo(modelo.getNumeroAcuerdo());
//			creditoDto.setTipoCliente(modelo.getTipoCliente());
//			creditoDto.setUnidadOperativa(modelo.getUnidadOperativa());
//			
//			/************DEIMOS***************/
//			creditoDto.setDmosApropiacionEnDeimos(modelo.getDmosApropiacionEnDeimos());
//			creditoDto.setDmosEstadoTramite(modelo.getDmosEstadoTramite());
//			if (Validaciones.isObjectNull(modelo.getDmosApropiacionEnDeimos())) {
//				creditoDto.setEstadoDeimos("-");
//			} else {
//				creditoDto.setEstadoDeimos(modelo.getDmosEstadoTramite().descripcion());
//			}
//			creditoDto.setDmosCantidadDeCuotas(modelo.getDmosCantidadDeCuotas());
//			creditoDto.setDmosCantidadDeCuotasPagas(modelo.getDmosCantidadDeCuotasPagas());
//			creditoDto.setDmosNroConvenio(modelo.getDmosNumeroConvenio()!=null?modelo.getDmosNumeroConvenio().longValue():null);
//			
//			/************ RESULTADO VALIDACION ***************/
//			if (Constantes.ERROR_VALIDACION_EDICION_GRILLAS.equals(modelo.getResultadoValidacionCodigoError())) {
//				creditoDto.setDescripcionErrorValidacion(modelo.getResultadoValidacionDescripcionError());
//			} else {
//				creditoDto.setDescripcionErrorValidacion("-");
//			}
//			creditoDto.setResultadoValidacionEstado(modelo.getResultadoValidacionEstado());
//			creditoDto.setResultadoValidacionCodigoError(modelo.getResultadoValidacionCodigoError());
//			creditoDto.setResultadoValidacionDescripcionError(modelo.getResultadoValidacionDescripcionError());
//			
//			//
//			creditoDto.setTipoCredito(modelo.getTipoCredito()!=null?modelo.getTipoCredito().getDescripcion():null);
//			creditoDto.setTipoCreditoEnum(modelo.getTipoCredito()!=null?modelo.getTipoCredito().name():null);
//			//
//			creditoDto.setIdTipoMedioPago(modelo.getTipoMedioPago()!=null?modelo.getTipoMedioPago().getIdTipoMedioPago():null);
//			//
//			creditoDto.setTipoComprobante(modelo.getTipoComprobante()!=null?modelo.getTipoComprobante().descripcion():"");
//			creditoDto.setTipoComprobanteEnum(modelo.getTipoComprobante());
//			//
//			creditoDto.setEstadoOrigen(modelo.getEstadoOrigen()!=null?modelo.getEstadoOrigen().descripcion():"");
//			creditoDto.setEstadoOrigenEnum(modelo.getEstadoOrigen());
//			//
//			creditoDto.setMotivoDescripcion(modelo.getMotivo()!=null?modelo.getMotivo().descripcion():null);
//			creditoDto.setMotivo(modelo.getMotivo());
//			//
//			//
//			//Importante: Despues de recuperar los datos desde la base de datos, 
//			//los seteos especiales tienen que tener el mismo comportamiento que los servicios de SOA y SHIVA
//			//
//			if (SistemaEnum.MIC.equals(modelo.getSistemaOrigen())) {
//				//MIC-NOTACREDITO
//				if(TipoCreditoEnum.NOTACREDITO.equals(modelo.getTipoCredito())) {
//					//
//					if(!Validaciones.isObjectNull(modelo.getClaseComprobante())
//							&& !Validaciones.isNullOrEmpty(modelo.getSucursalComprobante())
//							&& !Validaciones.isNullOrEmpty(modelo.getNumeroComprobante())){
//						creditoDto.setNroDoc(Utilidad.parsearNroDocNoDuc(modelo.getClaseComprobante(), modelo.getSucursalComprobante(), modelo.getNumeroComprobante()));
//					}
//					//
//					if (!Validaciones.isNullOrEmpty(modelo.getSubtipo())) {
//						TipoFacturaEnum tipoFacturaEnum = TipoFacturaEnum.getEnumByCodigo(new Integer(modelo.getSubtipo()));
//						
//						creditoDto.setSubtipo(modelo.getSubtipo());
//						creditoDto.setSubtipoDocumentoDesc(tipoFacturaEnum!=null?tipoFacturaEnum.descripcion():"-");
//						creditoDto.setTipoFactura(tipoFacturaEnum);
//						creditoDto.setTipoFacturaMicRem(null);
//					}
//					
//					//TODO: Sacar idCreditoPantalla
//					creditoDto.setIdCreditoPantalla((modelo.getTipoCredito()!= null?modelo.getTipoCredito().name():"") 
//							+ "_" + creditoDto.getNroDoc() + "_" + modelo.getNroReferenciaMic());
//					
//					
//					creditoDto.setOrderByFecha(creditoDto.getFechaVenc());
//					creditoDto.setOrderByIdClienteLegado(creditoDto.getIdClienteLegado());
//					creditoDto.setOrderByNumeroIdentificatorio(creditoDto.getNumeroReferenciaMic()!=null?creditoDto.getNumeroReferenciaMic():"");
//				} else {
//					//MIC-REMANENTE
//					if(!Validaciones.isObjectNull(creditoDto.getCodigoTipoRemanente()))
//					{
//						TipoRemanenteEnum tipoRemanenteEnum = 
//								TipoRemanenteEnum.getEnumByCodigo(
//										creditoDto.getCodigoTipoRemanente().longValue());
//						
//						if (tipoRemanenteEnum!=null) {
//							creditoDto.setSubtipo(String.valueOf(tipoRemanenteEnum.codigo()));
//							creditoDto.setSubtipoDocumentoDesc(tipoRemanenteEnum.descripcion());
//							creditoDto.setTipoFactura(null);
//							creditoDto.setTipoFacturaMicRem(tipoRemanenteEnum);
//						}
//					}
//					
//					StringBuffer str = new StringBuffer();
//					str.append(creditoDto.getIdClienteLegado() != null ? creditoDto.getIdClienteLegado().toString():"");
//					String nroDoc = creditoDto.getCuenta() != null ? creditoDto.getCuenta().toString():""; 
//						   nroDoc += creditoDto.getSubtipo() != null ? creditoDto.getSubtipo() : "";
//					str.append(nroDoc);
//					if (TipoRemanenteEnum.TRANSFERIBLE_ACTUALIZABLE.equals(creditoDto.getTipoFacturaMicRem())) {
//						str.append("-");
//						try {
//							str.append(Utilidad.formatDateAAMMDD(Utilidad.parseDatePickerString(creditoDto.getFechaAltaCredito())));
//						} catch (ParseException e) {
//							throw new NegocioExcepcion(e.getMessage(), e);
//						}
//					}
//					//TODO: Sacar idCreditoPantalla
//					creditoDto.setIdCreditoPantalla(str.toString());
//					//
//					creditoDto.setOrderByFecha(creditoDto.getFechaAltaCredito());
//					creditoDto.setOrderByIdClienteLegado(creditoDto.getIdClienteLegado());
//					creditoDto.setOrderByNumeroIdentificatorio(nroDoc!=null?nroDoc:"");
//				}
//				creditoDto.setSinDifDeCambio(false);
//				//
//			}
//			
//			if (SistemaEnum.CALIPSO.equals(modelo.getSistemaOrigen())) {
//				//
//				if(!Validaciones.isObjectNull(modelo.getClaseComprobante())
//						&& !Validaciones.isNullOrEmpty(modelo.getSucursalComprobante())
//						&& !Validaciones.isNullOrEmpty(modelo.getNumeroComprobante())){
//					creditoDto.setNroDoc(Utilidad.parsearNroDocNoDuc(modelo.getClaseComprobante(), modelo.getSucursalComprobante(), modelo.getNumeroComprobante()));
//				}
//				
//				//TODO: Sacar idCreditoPantalla
//				creditoDto.setIdCreditoPantalla(creditoDto.getTipoComprobanteEnum().name() + "_" + creditoDto.getNroDoc());
//				//
//				creditoDto.setOrderByFecha(creditoDto.getFechaVenc());
//				creditoDto.setOrderByIdClienteLegado(creditoDto.getIdClienteLegado());
//				creditoDto.setOrderByNumeroIdentificatorio(creditoDto.getNroDoc()!=null?creditoDto.getNroDoc():"");
//				creditoDto.setSubtipo(modelo.getSubtipo());
//
//				//
//				if(!Validaciones.isObjectNull(modelo.getMoneda())){
//					if (MonedaEnum.DOL.equals(modelo.getMoneda())) {
//						creditoDto.setSinDifDeCambio(true);
//					} else {
//						creditoDto.setSinDifDeCambio(false);
//					}
//				}
//			}
//			if (SistemaEnum.SHIVA.equals(modelo.getSistemaOrigen())) {
//				//
//				//Sirve para el uso del semaforo, pero para la edición no lo necesito
//				//if (Validaciones.isNullOrEmpty(vista.getIdOrigen())) {
//				//	creditoDto.setTipoComprobanteEnumShiva(TipoShivaEnum.AVISO_DE_PAGO);
//				//} else {
//				//	creditoDto.setTipoComprobanteEnumShiva(TipoShivaEnum.getEnumByIdOrigen(vista.getIdOrigen()));
//				//}
//				//
//				creditoDto.setNroDoc(modelo.getReferenciaValor());
//				//
//				TipoRetencionEnum tipoRetencionEnum = TipoRetencionEnum.getEnumByIdTipoMedioPago(creditoDto.getIdTipoMedioPago());
//				if (tipoRetencionEnum!=null) {
//					creditoDto.setSubtipo(tipoRetencionEnum.getIdString());
//					creditoDto.setSubtipoDocumentoDesc(tipoRetencionEnum.getDescripcion());
//					creditoDto.setDescripcionTipoRetencion(tipoRetencionEnum.getDescripcion());
//				}
//				//
//				//TODO: Sacar idCreditoPantalla
//				String idPantalla = creditoDto.getIdValor()
//						+ "_" + (modelo.getTipoCredito()!=null?modelo.getTipoCredito().getIdTipoValor():"0")
//						+ "_" + creditoDto.getReferenciaValor() + "_" + creditoDto.getIdClienteLegado();
//				creditoDto.setIdCreditoPantalla(idPantalla);
//				//
//				creditoDto.setOrderByFecha(!Validaciones.isNullOrEmpty(creditoDto.getFechaValorShiva(modelo.getTipoCredito())) 
//						? creditoDto.getFechaValorShiva(modelo.getTipoCredito()) : null);
//				creditoDto.setOrderByIdClienteLegado(creditoDto.getIdClienteLegado());
//				creditoDto.setOrderByNumeroIdentificatorio(creditoDto.getNroDoc()!=null?creditoDto.getNroDoc():"");
//				//
//				
//				//
//				creditoDto.setSinDifDeCambio(false);
//			}
//			
//			//
//			//TODO: Ahi se usa (ver sacar idCreditoPantalla)
//			if (!Validaciones.isNullEmptyOrDash(modelo.getIdCreditoReferencia())) {
//				creditoDto.setIdCreditoPantalla(modelo.getIdCreditoReferencia());
//			}
//					
//			creditoDto.setMonedaImporteUtilizar(modelo.getMonedaImporteAUtilizar() != null? modelo.getMonedaImporteAUtilizar().name():null);			
//			
			
			
			ShvCobEdCreditoPk creditoPk = new ShvCobEdCreditoPk();
			creditoPk.setCobro(modelo);
			creditoPk.setIdCredito(new Long(1));
			credito.setPk(creditoPk);
			


			// Tratamiento de la Diferencia
			ShvCobEdTratamientoDiferencia tratamientoDiferencia = new ShvCobEdTratamientoDiferencia();
			tratamientoDiferencia.setTipoTratamientoDiferencia(TipoTratamientoDiferenciaEnum.APLICACION_MANUAL);
			tratamientoDiferencia.setSistemaDestino(SistemaEnum.CABLEVISION);
			tratamientoDiferencia.setCobro(modelo);
			tratamientoDiferencia.setImporte(null);
			
			cobroOnlineServicio.crear(modelo);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		System.out.println("FINALIZADO");
	}
}
