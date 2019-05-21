package ar.com.telecom.shiva.negocio.mapeos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SubTipoCompensacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.IDocumentoCapDao;
import ar.com.telecom.shiva.persistencia.dao.IProvinciaDao;
import ar.com.telecom.shiva.persistencia.dao.ITipoMedioPagoDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdDocumentoCap;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdMedioPagoCliente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdOtrosMedioPago;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobMedioPagoDocumentoCap;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoMedioPago;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroMedioDePagoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroMedioPagoClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.DocumentoCapDto;

public class CobroOnlineOtroMedioPagoMapeador extends Mapeador {
	
	@Autowired
	private ITipoMedioPagoDao tipoMedioPagoDao;
	
	private CobroOnlineMedioPagoClienteMapeador medioPagoClienteMapeador;
	private CobroOnlineDocumentoCapMapeador medioPagoDocumentoCapMapeador;

	@Autowired
	private IProvinciaDao	provinciaDao;
	@Autowired
	private IDocumentoCapDao documentoCapDao;
	
//	@Autowired
//	private IDocumentoCapDao	documentoCapDao;
//	
//	private CobroOnlineDocumentoCapMapeador documentoCapMapeador;
	
	@SuppressWarnings("unchecked")
	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvCobEdOtrosMedioPago modelo = (ShvCobEdOtrosMedioPago) vo;
		
		CobroMedioDePagoDto dto = new CobroMedioDePagoDto();
		
		dto.setId(modelo.getPk().getIdMedioPago());
		dto.setIdMedioPago(modelo.getPk().getIdMedioPago());
		
		dto.setFecha(Utilidad.formatDatePicker(modelo.getFecha()));
		dto.setImporte(Utilidad.formatCurrency(modelo.getImporte(), false, false));

		dto.setIdMpTipoCredito(modelo.getTipoMedioPago().getIdTipoMedioPago());
		
		TipoCreditoEnum tipoMedioPago = TipoCreditoEnum.getEnumByValor(modelo.getTipoMedioPago().getIdTipoMedioPago());
		dto.setDescMpTipoCredito(tipoMedioPago.getDescripcion());
		if (!Validaciones.isNullOrEmpty(modelo.getNroActa())) {
			if (TipoCreditoEnum.OTRAS_COMPENSACIONES.equals(tipoMedioPago)
					|| TipoCreditoEnum.INTERCOMPANY.equals(tipoMedioPago)
					|| TipoCreditoEnum.LIQUIDOPRODUCTO.equals(tipoMedioPago)
					|| TipoCreditoEnum.PROVEEDORES.equals(tipoMedioPago)
					|| TipoCreditoEnum.IIBB.equals(tipoMedioPago)
					|| TipoCreditoEnum.CESION_CREDITOS.equals(tipoMedioPago)){
				dto.setNroCompensacion(modelo.getNroActa());	
			} else {
				if (TipoCreditoEnum.DESISTIMIENTO.equals(tipoMedioPago)) {
					dto.setNroActa(modelo.getNroActa());
				}
			}
		}
		
		if(!Validaciones.isObjectNull(modelo.getSubTipo())){
			dto.setSubTipo(modelo.getSubTipo().name());
			dto.setSubTipoDescripcion(modelo.getSubTipo().getDescripcion());
		}

		dto.setCheckMedioPagoProceso(modelo.getCheckMedioPagoEnProceso());
		
		if (Validaciones.isCollectionNotEmpty(modelo.getListaMedioPagoCliente())) {
			List<ShvCobEdMedioPagoCliente> lista = new ArrayList<ShvCobEdMedioPagoCliente>(modelo.getListaMedioPagoCliente());
			List<CobroMedioPagoClienteDto> listaMedioPagoCliente = (List<CobroMedioPagoClienteDto>) medioPagoClienteMapeador.map(lista);
			dto.setListaMedioPagoCliente(listaMedioPagoCliente);
			if(Validaciones.isCollectionNotEmpty(listaMedioPagoCliente)){
				String clientesLegados = "";
				for (CobroMedioPagoClienteDto cobroMedioPagoClienteDto : listaMedioPagoCliente) {
					clientesLegados += cobroMedioPagoClienteDto.getIdClienteLegado() + " - ";
				}
				clientesLegados = clientesLegados.substring(0, clientesLegados.length()-3);
				dto.setClientesLegados(clientesLegados);
			}
		} else {
			dto.setListaMedioPagoCliente(new ArrayList<CobroMedioPagoClienteDto>());
		}
		
		dto.setMonedaImporteUtilizar(modelo.getMonedaImporteAUtilizar() != null ? modelo.getMonedaImporteAUtilizar().name() : null);
		dto.setMonedaImporteUtilizarSigno2(modelo.getMonedaImporteAUtilizar() != null ? modelo.getMonedaImporteAUtilizar().getSigno2() : null);
		if (!Validaciones.isObjectNull(modelo.getProvincia())) {
			dto.setProvincia(modelo.getProvincia().getDescripcion());
			dto.setIdProvincia(modelo.getProvincia().getIdProvincia());
		}
		
		
		if (TipoCreditoEnum.LIQUIDOPRODUCTO.equals(tipoMedioPago) || TipoCreditoEnum.PROVEEDORES.equals(tipoMedioPago)) {
			if (!Validaciones.isObjectNull(modelo.getDocumentosCap()) && !modelo.getDocumentosCap().isEmpty()) {
				dto.setListaDocumentoCap(new ArrayList<DocumentoCapDto>());
				for (ShvCobEdDocumentoCap documentoCap : modelo.getDocumentosCap()) {
					DocumentoCapDto documentoCapDto = (DocumentoCapDto) medioPagoDocumentoCapMapeador.map(documentoCap);
					dto.getListaDocumentoCap().add(documentoCapDto);
				}
				//Collections.sort(dto.getListaDocumentoCap(), new ComparatorDocumentoCapDto());
			}
			
			// obtengo el numero Interno sap
			ShvCobMedioPagoDocumentoCap shvCobMedioPagoDocumentoCap;
			try {
				shvCobMedioPagoDocumentoCap = documentoCapDao.buscarDocumentoCapPorIdCobro(modelo.getPk().getCobro().getIdCobro());
				if (!Validaciones.isObjectNull(shvCobMedioPagoDocumentoCap) && !Validaciones.isObjectNull(shvCobMedioPagoDocumentoCap.getIdDocumento())) {
					dto.setNroDeDocumentoInterno(shvCobMedioPagoDocumentoCap.getIdDocumento().toString());
				}
			} catch (PersistenciaExcepcion e) {
				throw new NegocioExcepcion(e.getMessage(), e);
			}

			
		}
	
		return dto;
	}
	
	
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		CobroMedioDePagoDto medioPagoDto = (CobroMedioDePagoDto) dto;
		
		ShvCobEdOtrosMedioPago medioPagoModelo = (ShvCobEdOtrosMedioPago)
				(vo != null ? vo : new ShvCobEdOtrosMedioPago());
		try {
			medioPagoModelo.setFecha(Utilidad.parseDatePickerString(medioPagoDto.getFecha()));
			if (Utilidad.PESOS_CHAR.equalsIgnoreCase(medioPagoDto.getImporte().substring(0, 1))) {
				medioPagoModelo.setImporte(Utilidad.stringToBigDecimal(medioPagoDto.getImporte().substring(1)));
			} else {
				medioPagoModelo.setImporte(Utilidad.stringToBigDecimal(medioPagoDto.getImporte()));
			}
			
			if (!Validaciones.isNullEmptyOrDash(medioPagoDto.getNroActa())) {
				medioPagoModelo.setNroActa(medioPagoDto.getNroActa());
			} else {
				if (!Validaciones.isNullEmptyOrDash(medioPagoDto.getNroCompensacion())) {
					medioPagoModelo.setNroActa(medioPagoDto.getNroCompensacion());
				} else {
					medioPagoModelo.setNroActa(null);
				}
			}
			
			if (!Validaciones.isNullOrEmpty(medioPagoDto.getIdMpTipoCredito())) {
				Modelo tipoMedPagoModelo = tipoMedioPagoDao.buscar(medioPagoDto.getIdMpTipoCredito());
				medioPagoModelo.setTipoMedioPago(tipoMedPagoModelo!=null? (ShvParamTipoMedioPago)tipoMedPagoModelo : null);
			}else{
				medioPagoModelo.setTipoMedioPago(null);
			}
			
			medioPagoModelo.setCheckMedioPagoEnProceso(medioPagoDto.getCheckMedioPagoEnProceso());
			
			if (Validaciones.isCollectionNotEmpty(medioPagoDto.getListaMedioPagoCliente())) {
				for (CobroMedioPagoClienteDto medioPagoClienteDto : medioPagoDto.getListaMedioPagoCliente()) {
					
					ShvCobEdMedioPagoCliente medioPagoClienteModelo = (ShvCobEdMedioPagoCliente)
								(vo != null ? vo : new ShvCobEdMedioPagoCliente());
					
					medioPagoClienteModelo.setIdClienteLegado(medioPagoClienteDto.getIdClienteLegado());
					medioPagoClienteModelo.setOtrosMedioPago(medioPagoModelo);
					
					medioPagoModelo.getListaMedioPagoCliente().add(medioPagoClienteModelo);
				}
			}
			
			if (!Validaciones.isObjectNull(MonedaEnum.getEnumBySigno2(medioPagoDto.getMonedaImporteUtilizar()))){
				
				medioPagoModelo.setMonedaImporteAUtilizar(MonedaEnum.getEnumBySigno2(medioPagoDto.getMonedaImporteUtilizar()));
			} else {
				medioPagoModelo.setMonedaImporteAUtilizar(MonedaEnum.getEnumByName(medioPagoDto.getMonedaImporteUtilizar()));
				
			}
			if (!Validaciones.isNullEmptyOrDash(medioPagoDto.getIdProvincia())) {
				medioPagoModelo.setProvincia(provinciaDao.listarProvinciasPorId(medioPagoDto.getIdProvincia()));
			} else {
				medioPagoModelo.setProvincia(null);
			}
			
			
			TipoCreditoEnum tipoMedioPago = medioPagoDto.getMpTipoCredito();
			if (TipoCreditoEnum.LIQUIDOPRODUCTO.equals(tipoMedioPago) || TipoCreditoEnum.PROVEEDORES.equals(tipoMedioPago)) {
				if (!Validaciones.isObjectNull(medioPagoDto.getListaDocumentoCap()) && !medioPagoDto.getListaDocumentoCap().isEmpty()) {
					medioPagoModelo.setDocumentosCap(new ArrayList<ShvCobEdDocumentoCap>());
					for (DocumentoCapDto documentoCap : medioPagoDto.getListaDocumentoCap()) {
						ShvCobEdDocumentoCap shvCobEdDocumentoCap = (ShvCobEdDocumentoCap) medioPagoDocumentoCapMapeador.map(documentoCap, null);
						shvCobEdDocumentoCap.setOtrosMedioPago(medioPagoModelo);
						medioPagoModelo.getDocumentosCap().add(shvCobEdDocumentoCap);
					}
					//Collections.sort(medioPagoModelo.getDocumentosCap(), new ComparatorDocumentoCapShvCobEdDocumentoCap());
				}
			}
			tipoMedioPago = null;
			
			
			if(!Validaciones.isObjectNull(medioPagoDto.getSubTipo())){
				medioPagoModelo.setSubTipo(SubTipoCompensacionEnum.getEnumByCodigo(medioPagoDto.getSubTipo()));
			}
			
		} catch(Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return medioPagoModelo;
	}


	public CobroOnlineMedioPagoClienteMapeador getMedioPagoClienteMapeador() {
		return medioPagoClienteMapeador;
	}


	public void setMedioPagoClienteMapeador(
			CobroOnlineMedioPagoClienteMapeador medioPagoClienteMapeador) {
		this.medioPagoClienteMapeador = medioPagoClienteMapeador;
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
	 * @return the medioPagoDocumentoCapMapeador
	 */
	public CobroOnlineDocumentoCapMapeador getMedioPagoDocumentoCapMapeador() {
		return medioPagoDocumentoCapMapeador;
	}


	/**
	 * @param medioPagoDocumentoCapMapeador the medioPagoDocumentoCapMapeador to set
	 */
	public void setMedioPagoDocumentoCapMapeador(
			CobroOnlineDocumentoCapMapeador medioPagoDocumentoCapMapeador) {
		this.medioPagoDocumentoCapMapeador = medioPagoDocumentoCapMapeador;
	}
	

}
