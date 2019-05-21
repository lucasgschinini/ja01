
package ar.com.telecom.shiva.negocio.mapeos;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdDocumentoCap;
import ar.com.telecom.shiva.presentacion.bean.dto.DocumentoCapDto;
import ar.com.telecom.shiva.presentacion.bean.dto.GestionabilidadDto;
import ar.com.telecom.shiva.presentacion.bean.dto.PartidaDto;

public class CobroOnlineDocumentoCapMapeador extends Mapeador {
	
	@Autowired 
	ICobroOnlineServicio cobroOnlineServicio;
	
	
	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvCobEdDocumentoCap modelo = (ShvCobEdDocumentoCap) vo;
		
		PartidaDto partidaDto = new DocumentoCapDto();
		
		// Mapeo los datos que viene del webService se Sap estos datos se van a guardar como vienen en la base
		partidaDto.setCodigoSociedad(modelo.getIdSociedad());
		partidaDto.setIdNumeroProveedor(modelo.getIdProveedor());
		partidaDto.setAsignacion(modelo.getAsignacion());
		partidaDto.setEjercicioFiscalDocSAP(modelo.getEjercicioFiscalDocSAP());
		partidaDto.setNroDocumentoSap(modelo.getNumeroDocSAP());
		if (!Validaciones.isObjectNull(modelo.getPosicionDocSAP())) {
			partidaDto.setPosicionDocSAP(modelo.getPosicionDocSAP());
		}
		partidaDto.setFechaContableDocSAP(modelo.getFechaContableDocSAP());
		partidaDto.setFechaDocProveedor(modelo.getFechaDocProveedor());
		partidaDto.setFechaRegistroSAP(modelo.getFechaRegistroSAP());
		if (!Validaciones.isObjectNull(modelo.getMonedaDocProveedor())) {
			partidaDto.setMonedaDocProveedor(modelo.getMonedaDocProveedor());
		}
		if (!Validaciones.isObjectNull(modelo.getMonedaLocal())) {
			partidaDto.setMonedaLocal(modelo.getMonedaLocal());
		}
		partidaDto.setTipoCambioRegistradoSAP(modelo.getTipoCambioRegistradoSAP());
		partidaDto.setNumeroLegalDocProveedor(modelo.getNumeroLegalDocProveedor());

		partidaDto.setClaseDocSAP(modelo.getClaseDocSAP());

		partidaDto.setMesFiscalDocSap(modelo.getMesFiscalDocSap());
		partidaDto.setClaveContabilizacionSAP(modelo.getClaveContabilizacionSAP());
		partidaDto.setIndicador(modelo.getIndicador());
		partidaDto.setDivision(modelo.getDivision());
		partidaDto.setImporteDoc(modelo.getImporteDoc());
		partidaDto.setImporteDocMonedaDoc(modelo.getImporteDocMonedaDoc());
		partidaDto.setTextoPosicion(modelo.getTextoPosicion());
		partidaDto.setFechaBase(modelo.getFechaBase());
		partidaDto.setCondPago(modelo.getCondPago());
		partidaDto.setViaPago(modelo.getViaPago());
		partidaDto.setBloqueoPago(modelo.getBloqueoPago());
		partidaDto.setDescripcionBloqueo(modelo.getDescripcionBloqueo());
		partidaDto.setNumeroDocSAPVinculado(modelo.getNumeroDocSAPVinculado());
		partidaDto.setEjercicioFiscalDocSAPVinculado(modelo.getEjercicioFiscalDocSAPVinculado());	
		if (!Validaciones.isObjectNull(modelo.getPosicionDocSAPVinculado())) {
			partidaDto.setPosicionDocSAPVinculado(modelo.getPosicionDocSAPVinculado());
		}
		partidaDto.setClaveRef(modelo.getClaveRef());
		partidaDto.setClaveRef2(modelo.getClaveRef2());
		partidaDto.setFechaTipoCambio(modelo.getFechaTipoCambio());
		partidaDto.setTipoCambioAFechaTipoCambio(modelo.getTipoDeCambioAFechaTipoCambio());
		partidaDto.setEstadoDocSAP(modelo.getEstadoDocSAP());//<ZSTATUS>V</ZSTATUS>

		partidaDto.setImpDocPES(modelo.getImpDocPES());
		partidaDto.setImpDocUSD(modelo.getImpDocUSD());
		partidaDto.setImpTotalDocMonedaDoc(modelo.getImpTotalDocMonedaDoc());
		partidaDto.setImpTotalDocMonedaLocal(modelo.getImpTotalDocMonedaLocal());
		partidaDto.setImpTotalDocPES(modelo.getImpTotalDocPES());
		partidaDto.setImpTotalDocUSD(modelo.getImpTotalDocUSD());

		partidaDto.setImporteRenglonMonedaDolarAFechaEmision(modelo.getImporteRenglonMonedaDolarAFechaEmision());
		
		partidaDto.setImporteTotalMonedaOrigenDocumentosBloqueoPesos(modelo.getImporteTotalMonedaOrigenDocumentosBloqueoPesos());
		
		partidaDto.setSaldoPesificadoEmisionDocumentosBloqueoNoPesos(modelo.getSaldoPesificadoEmisionDocumentosBloqueoNoPesos());
		partidaDto.setSaldoPesificadoCobroDocumentosBloqueoNoPesos(modelo.getSaldoPesificadoCobroDocumentosBloqueoNoPesos());
		
		DocumentoCapDto docCapDto = (DocumentoCapDto) partidaDto;
		// Datos Comunes!!!
		
		docCapDto.setIdClienteLegado(modelo.getIdClienteLegado());
		
		if (!Validaciones.isObjectNull(modelo.getMonedaImporte())) {
			docCapDto.setMonedaImporte(modelo.getMonedaImporte());
		}
		// Determino si es un documento relacionado
		docCapDto.setTipoRenglon(modelo.getTipoRenglon());
		
		docCapDto.setSistemaOrigen(SistemaEnum.SAP);
		docCapDto.setIdDocumentoCapReferencia(docCapDto.contruirIdReferencia());
		// Contrullo un semaforo minimo
		if (!Validaciones.isNullEmptyOrDash(modelo.getvSemaforo())) {
			docCapDto.setSemaforo(new GestionabilidadDto(GestionabilidadDto.esGestinonle(modelo.getvSemaforo()), modelo.getvSemaforo(), "Mapeador"));
		}
		docCapDto.setTipoCambio(Utilidad.formatDecimales(partidaDto.getTipoCambioRegistradoSAP(), 7));
		if (docCapDto.isEsReglonAgrupador()) {
			docCapDto.setImporteMonedaOrigen(Utilidad.formatCurrency(partidaDto.getImpTotalDocMonedaDoc(), false, false, 2));
			if (!docCapDto.isEsMismaMoneda()) {
				
				docCapDto.setImporteGestionableAFechaEmisionTotalBd(modelo.getImporteGestionableAFechaEmisionTotal());
				docCapDto.setImporteGestionableAFechaShivaTotalBd(modelo.getImporteGestionableAFechaShivaTotal());
				docCapDto.setSaldoPesificadoTotalAFechaShivaBd(modelo.getSaldoPesificadoTotalAFechaShiva());
				docCapDto.setSaldoPesificadoTotalAFechaEmisionBd(modelo.getSaldoPesificadoTotalAFechaEmision());
				if (SiNoEnum.SI.equals(modelo.getCheckSinDiferenciaCambio())) {
					docCapDto.setSinDifDeCambio(true);
					docCapDto.setSaldoPesificado(Utilidad.formatCurrency(modelo.getSaldoPesificadoTotalAFechaEmision(), false, false, 2));
				} else {
					docCapDto.setSinDifDeCambio(false);
					docCapDto.setSaldoPesificado(Utilidad.formatCurrency(modelo.getSaldoPesificadoTotalAFechaShiva(), false, false, 2));
				}
				//docCapDto.setImporteMonedaOrigenDocAsociadoBd(modelo.getImportePesificadoDocAsociadoBd());
				
				docCapDto.setImporteGestionable(Utilidad.formatCurrency(modelo.getImporteGestionable(), false, false, 2));
				
			} else {
				docCapDto.setSinDifDeCambio(false);
				docCapDto.setTipoCambio("-");
				docCapDto.setSaldoPesificado("-");
				docCapDto.setImporteGestionable("-");
			}
			docCapDto.setSaldoMonedaOrigen(Utilidad.formatCurrency(modelo.getSaldoMonedaOrigen(), false, false, 2));
			docCapDto.setSaldoMonedaOrigenBd(modelo.getSaldoMonedaOrigen());
			docCapDto.setImporteGestionableBg(modelo.getImporteGestionable());
			docCapDto.setImporteGestionable(Utilidad.formatCurrency(modelo.getImporteGestionable(), false, false, 2));
			docCapDto.setImportePesificadoDocAsociadoBd(modelo.getImportePesificadoDocAsociadoBd());
			docCapDto.setImportePesificadoDocAsociado(Utilidad.formatCurrency(modelo.getImportePesificadoDocAsociadoBd(),false, false, 2));

		} else if (docCapDto.isEsReglonCap()) {
			if (!docCapDto.getMonedaImporte().equals(docCapDto.getMonedaDocProveedor())) {
				docCapDto.setSaldoPesificado(Utilidad.formatCurrency(modelo.getImporteDoc(), false, false, 2));
				if (SiNoEnum.SI.equals(modelo.getCheckSinDiferenciaCambio())) {
					docCapDto.setSinDifDeCambio(true);
					
					docCapDto.setSaldoPesificado(Utilidad.formatCurrency(modelo.getImporteDoc(), false, false, 2));
				} else {
					docCapDto.setSinDifDeCambio(false);
					
					docCapDto.setSaldoPesificado(Utilidad.formatCurrency(modelo.getImpDocPES(),false, false, 2));	
				}
			} else {
				docCapDto.setSinDifDeCambio(false);
			}
			docCapDto.setSaldoMonedaOrigen(Utilidad.formatCurrency(modelo.getImporteDocMonedaDoc(), false, false, 2));
			docCapDto.setSaldoMonedaOrigenBd(modelo.getImporteDocMonedaDoc());
			
		} else if (docCapDto.isEsReglonAsociado()) {
			if (SiNoEnum.SI.equals(modelo.getCheckSinDiferenciaCambio())) {
				docCapDto.setSinDifDeCambio(true);
				docCapDto.setImportePesificadoDocAsociado(Utilidad.formatCurrency(modelo.getImporteDoc(), false, false, 2));
			} else {
				docCapDto.setSinDifDeCambio(false);
				docCapDto.setImportePesificadoDocAsociado(Utilidad.formatCurrency(modelo.getImpDocPES(),false, false, 2));	
			}
			docCapDto.setSaldoMonedaOrigen("-");
			docCapDto.setSaldoPesificado("-");
		} else {
			docCapDto.setSaldoPesificado("-");
			
		}
		return docCapDto;
	}
	
	
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		ShvCobEdDocumentoCap modelo = (ShvCobEdDocumentoCap) (vo != null ? vo : new ShvCobEdDocumentoCap());
		try {
			
			PartidaDto partidaDto = (DocumentoCapDto) dto;
			
			 //Mapeo los datos que viene del webService se Sap estos datos se van a guardar como vienen en la base
			if (!Validaciones.isNullEmptyOrDash(partidaDto.getCodigoSociedad())) {
				modelo.setIdSociedad(partidaDto.getCodigoSociedad());
			} else {
				modelo.setIdSociedad(null);
			}
			if (!Validaciones.isNullEmptyOrDash(partidaDto.getIdNumeroProveedor())) {
				modelo.setIdProveedor(partidaDto.getIdNumeroProveedor());
			} else {
				modelo.setIdProveedor(null);
			}
			if (!Validaciones.isNullEmptyOrDash(partidaDto.getAsignacion())) {
				modelo.setAsignacion(partidaDto.getAsignacion());
			} else {
				modelo.setAsignacion(null);
			}
			
			modelo.setEjercicioFiscalDocSAP(partidaDto.getEjercicioFiscalDocSAP());
			
			if (!Validaciones.isNullEmptyOrDash(partidaDto.getNroDocumentoSap())) {
				modelo.setNumeroDocSAP(partidaDto.getNroDocumentoSap());
			} else  {
				modelo.setNumeroDocSAP(null);
			}
			
			modelo.setPosicionDocSAP(partidaDto.getPosicionDocSAP());
			modelo.setFechaContableDocSAP(partidaDto.getFechaContableDocSAP());
			modelo.setFechaDocProveedor(partidaDto.getFechaDocProveedor());
			modelo.setFechaRegistroSAP(partidaDto.getFechaRegistroSAP());
// TODO !
			modelo.setMonedaDocProveedor(partidaDto.getMonedaDocProveedor());
			modelo.setMonedaLocal(partidaDto.getMonedaLocal());
			modelo.setTipoCambioRegistradoSAP(partidaDto.getTipoCambioRegistradoSAP());
//			
			if (!Validaciones.isNullEmptyOrDash(partidaDto.getNumeroLegalDocProveedor())) {
				modelo.setNumeroLegalDocProveedor(partidaDto.getNumeroLegalDocProveedor());
			} else if (("-").equals(partidaDto.getNumeroLegalDocProveedor())) {
				modelo.setNumeroLegalDocProveedor(null);
			}
//			
//
			modelo.setClaseDocSAP(partidaDto.getClaseDocSAP());
			modelo.setMesFiscalDocSap(partidaDto.getMesFiscalDocSap());
//			
			if (!Validaciones.isNullEmptyOrDash(partidaDto.getClaveContabilizacionSAP())) {
				modelo.setClaveContabilizacionSAP(partidaDto.getClaveContabilizacionSAP());
			} else {
				modelo.setClaveContabilizacionSAP(null);
			}
			if (!Validaciones.isNullEmptyOrDash(partidaDto.getIndicador())) {
				modelo.setIndicador(partidaDto.getIndicador());
			} else {
				modelo.setIndicador(null);
			}
			if (!Validaciones.isNullEmptyOrDash(partidaDto.getDivision())) {
				modelo.setDivision(partidaDto.getDivision());
			} else {
				modelo.setDivision(null);
			}
//			
			
			
			modelo.setTipoDeCambioAFechaTipoCambio(partidaDto.getTipoCambioAFechaTipoCambio());
		
			
			modelo.setImporteDoc(partidaDto.getImporteDoc());
			modelo.setImporteDocMonedaDoc(partidaDto.getImporteDocMonedaDoc());
			if (!Validaciones.isNullEmptyOrDash(partidaDto.getTextoPosicion())) {
				modelo.setTextoPosicion(partidaDto.getTextoPosicion());
			} else {
				modelo.setTextoPosicion(null);
			}
			modelo.setFechaBase(partidaDto.getFechaBase());
			if (!Validaciones.isNullEmptyOrDash(partidaDto.getCondPago())) {
				modelo.setCondPago(partidaDto.getCondPago());
			} else  {
				modelo.setCondPago(null);
			}
			if (!Validaciones.isNullEmptyOrDash(partidaDto.getViaPago())) {
				modelo.setViaPago(partidaDto.getViaPago());
			} else {
				modelo.setViaPago(null);
			}
			if (!Validaciones.isObjectNull(partidaDto.getBloqueoPago())) {
				modelo.setBloqueoPago(partidaDto.getBloqueoPago());
			} else {
				modelo.setBloqueoPago(null);
			}
			if (!Validaciones.isNullEmptyOrDash(partidaDto.getDescripcionBloqueo())) {
				modelo.setDescripcionBloqueo(partidaDto.getDescripcionBloqueo());
			} else {
				modelo.setDescripcionBloqueo(null);
			}
			if (!Validaciones.isNullEmptyOrDash(partidaDto.getNumeroDocSAPVinculado())) {
				modelo.setNumeroDocSAPVinculado(partidaDto.getNumeroDocSAPVinculado());
			} else {
				modelo.setNumeroDocSAPVinculado(null);
			}
			modelo.setEjercicioFiscalDocSAPVinculado(partidaDto.getEjercicioFiscalDocSAPVinculado());
			modelo.setPosicionDocSAPVinculado(partidaDto.getPosicionDocSAPVinculado());
			if (!Validaciones.isNullEmptyOrDash(partidaDto.getClaveRef())) {
				modelo.setClaveRef(partidaDto.getClaveRef());
			} else {
				modelo.setClaveRef(null);
			}
			if (!Validaciones.isNullEmptyOrDash(partidaDto.getClaveRef2())) {
				modelo.setClaveRef2(partidaDto.getClaveRef2());
			} else {
				modelo.setClaveRef2(null);
			}
			modelo.setFechaTipoCambio(partidaDto.getFechaTipoCambio());
//
			modelo.setEstadoDocSAP(partidaDto.getEstadoDocSAP());//<ZSTATUS>V</ZSTATUS>
//
			modelo.setImpDocPES(partidaDto.getImpDocPES());
			modelo.setImpDocUSD(partidaDto.getImpDocUSD());
			modelo.setImpTotalDocMonedaDoc(partidaDto.getImpTotalDocMonedaDoc());
			modelo.setImpTotalDocMonedaLocal(partidaDto.getImpTotalDocMonedaLocal());
			modelo.setImpTotalDocPES(partidaDto.getImpTotalDocPES());
			modelo.setImpTotalDocUSD(partidaDto.getImpTotalDocUSD());

			modelo.setImporteRenglonMonedaDolarAFechaEmision(partidaDto.getImporteRenglonMonedaDolarAFechaEmision());
//			
			DocumentoCapDto docCapDto = (DocumentoCapDto) partidaDto;
			// Datos Comunes!!!
			modelo.setIdClienteLegado(docCapDto.getIdClienteLegado());
			
//			
			modelo.setMonedaImporte(docCapDto.getMonedaImporte());
			// Determino si es un documento relacionado
			modelo.setTipoRenglon(docCapDto.getTipoRenglon());
		
			modelo.setSistemaOrigen(docCapDto.getSistemaOrigen());
			
			modelo.setImporteTotalMonedaOrigenDocumentosBloqueoPesos(docCapDto.getImporteTotalMonedaOrigenDocumentosBloqueoPesos());
			
			modelo.setSaldoPesificadoEmisionDocumentosBloqueoNoPesos(docCapDto.getSaldoPesificadoEmisionDocumentosBloqueoNoPesos());
			modelo.setSaldoPesificadoCobroDocumentosBloqueoNoPesos(docCapDto.getSaldoPesificadoCobroDocumentosBloqueoNoPesos());
			
			
			if (!Validaciones.isNullEmptyOrDash(docCapDto.getSemaforo().getSemaforo())) {
				modelo.setvSemaforo(docCapDto.getSemaforo().getSemaforo());
			} else {
				modelo.setvSemaforo(null);
			}
			if (!Validaciones.isNullEmptyOrDash(docCapDto.getIdDocumentoCapReferencia())) {
				modelo.setIdCapReferencia(docCapDto.getIdDocumentoCapReferencia());
			} else {
				modelo.setIdCapReferencia(null);
			}
			
			
			if (docCapDto.isEsReglonAgrupador()) {
				if (!docCapDto.isEsMismaMoneda()) {
					if (!Validaciones.isObjectNull(docCapDto.getSinDifDeCambio()) && docCapDto.getSinDifDeCambio()) {
						modelo.setCheckSinDiferenciaCambio(SiNoEnum.SI);
						modelo.setImporteGestionable(docCapDto.getImporteGestionableAFechaEmisionTotalBd());
					} else {
						modelo.setCheckSinDiferenciaCambio(SiNoEnum.NO);
						modelo.setImporteGestionable(docCapDto.getImporteGestionableAFechaShivaTotalBd());
					}
					modelo.setImporteGestionableAFechaEmisionTotal(docCapDto.getImporteGestionableAFechaEmisionTotalBd());
					modelo.setImporteGestionableAFechaShivaTotal(docCapDto.getImporteGestionableAFechaShivaTotalBd());
					
				} else {
					modelo.setImporteGestionable(docCapDto.getImporteGestionableBg());
					modelo.setCheckSinDiferenciaCambio(SiNoEnum.NO);
				}
				modelo.setSaldoMonedaOrigen(docCapDto.getSaldoMonedaOrigenBd());
				
				modelo.setImportePesificadoDocAsociadoBd(docCapDto.getImportePesificadoDocAsociadoBd());
				modelo.setSaldoPesificadoTotalAFechaEmision(docCapDto.getSaldoPesificadoTotalAFechaEmisionBd());
				modelo.setSaldoPesificadoTotalAFechaShiva(docCapDto.getSaldoPesificadoTotalAFechaShivaBd());
			} else {
				if (!docCapDto.isEsMismaMoneda()) {
					if (!Validaciones.isObjectNull(docCapDto.getSinDifDeCambio()) && docCapDto.getSinDifDeCambio()) {
						modelo.setCheckSinDiferenciaCambio(SiNoEnum.SI);
					} else {
						modelo.setCheckSinDiferenciaCambio(SiNoEnum.NO);
					}
				
				} else {
					modelo.setCheckSinDiferenciaCambio(SiNoEnum.NO);
				}
			}
			
			return modelo;
		} catch(Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}
}

