package ar.com.telecom.shiva.negocio.mapeos;

import org.springframework.beans.factory.annotation.Autowired;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.OrigenDocumentoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoClaseComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.ITransaccionCobroDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobroDocumentoRelacionado;
import ar.com.telecom.shiva.presentacion.bean.dto.DescobroDocumentoRelacionadoDto;

public class DescobroDocRelacionadosMapeador extends Mapeador {
	@Autowired
	ITransaccionCobroDao transaccionCobroDao;

	@Override
	public DTO map(Modelo vo) throws NegocioExcepcion {	
		
		ShvCobDescobroDocumentoRelacionado modelo = (ShvCobDescobroDocumentoRelacionado) vo;
		
		DescobroDocumentoRelacionadoDto dto = new DescobroDocumentoRelacionadoDto();
		
		dto.setIdDocumentoRelacionado(modelo.getIdDocumentoRelacionado().toString());
		dto.setIdDescobro(modelo.getDescobro().getIdDescobro().toString());
		dto.setIdOperacion(modelo.getIdOperacion().toString());
		dto.setNroTransaccion(modelo.getNroTransaccion().toString());
		
		if(!Validaciones.isObjectNull(modelo.getIdCobranzaGenerado())){
			dto.setIdCobranzaGenerada(modelo.getIdCobranzaGenerado().toString());
		}
		
		if(!Validaciones.isObjectNull(modelo.getImporteGenerado())){
			dto.setImporteGenerado(modelo.getImporteGenerado().toString());
		}
		
		if(!Validaciones.isObjectNull(modelo.getImporteCapitalGenerado())){
			dto.setImporteCapitalGenereado(modelo.getImporteCapitalGenerado().toString());
		}
		
		if(!Validaciones.isObjectNull(modelo.getImporteImpuestosGenerado())){
			dto.setImporteImpuestosGenerado(modelo.getImporteImpuestosGenerado().toString());
		}
		
		TipoComprobanteEnum comprobante = modelo.getTipoComprobanteGenerado();
		if(!Validaciones.isObjectNull(comprobante)){
			dto.setTipoComprobanteGenerado(comprobante.descripcion());
		}
		
		TipoClaseComprobanteEnum claseComprobante = modelo.getClaseComprobanteGenerado();
		if(!Validaciones.isObjectNull(claseComprobante)){
			dto.setClaseComprobanteGenerado(claseComprobante.name());
		}
		
		dto.setSucursalComprobanteGenerado(modelo.getSucursalComprobanteGenerado());
		dto.setNroComprobanteGenerado(modelo.getNumeroComprobanteGenerado());
		
		if(!Validaciones.isObjectNull(modelo.getIdDocumentoCuentasCobranzaGenerado())){
			dto.setIdDocumentoCuentasCobranzaGenerado(modelo.getIdDocumentoCuentasCobranzaGenerado().toString());
		}
		
		// format date
		if(!Validaciones.isObjectNull(modelo.getFechaVencimientoGenerada())){
			dto.setFechaVencGenerada(Utilidad.formatDatePicker(modelo.getFechaVencimientoGenerada()));
		}
		
		if(!Validaciones.isObjectNull(modelo.getImporteGenerado())){
			dto.setImporteAplicadoGenerado(modelo.getImporteGenerado().toString());
		}
		
		if(!Validaciones.isObjectNull(modelo.getImporteGenerado())){
			dto.setImporteAplicadoOriginal(modelo.getImporteGenerado().toString());
		}
		
		dto.setOrigenDocumentoOriginal(OrigenDocumentoEnum.DC.name().toString());
		dto.setOrigenDocumentoOriginalDesc(OrigenDocumentoEnum.DC.descripcion());
		
		OrigenDocumentoEnum origenDoc = modelo.getOrigenDocumentoGenerado();
		if(!Validaciones.isObjectNull(origenDoc)){
			dto.setOrigenDocumentoGenerado(origenDoc.name().toString());
			dto.setOrigenDocumentoGeneradoDesc(origenDoc.descripcion());
		}
		
		TipoComprobanteEnum comprobanteOriginal = modelo.getTipoComprobanteOriginal();
		if(!Validaciones.isObjectNull(comprobanteOriginal)){
			dto.setTipoComprobanteOriginal(comprobanteOriginal.descripcion());
		}
		
		TipoClaseComprobanteEnum claseComprobanteOriginal = modelo.getClaseComprobanteOriginal();
		if(!Validaciones.isObjectNull(claseComprobanteOriginal)){
			dto.setClaseComprobanteOriginal(claseComprobanteOriginal.name());
		}
		
		dto.setSucursalComprobanteOriginal(modelo.getSucursalComprobanteOriginal());
		dto.setNroComprobanteOriginal(modelo.getNumeroComprobanteOriginal());
		
		if(!Validaciones.isObjectNull(modelo.getIdDocumentoCuentasCobranzaOriginal())){
			dto.setIdDocumentoCuentasCobranzaOriginal(modelo.getIdDocumentoCuentasCobranzaOriginal().toString());
		}	
		
		SistemaEnum sistema = modelo.getSistemaOrigen();
		if(!Validaciones.isObjectNull(sistema)) {
			dto.setSistemaOrigen(sistema.getDescripcion());
		}
		
		//yyyy/mm/dd 
		if(!Validaciones.isObjectNull(modelo.getFechaImputacion())){
			dto.setFechaImputacion(Utilidad.formatDatePicker(modelo.getFechaImputacion()));
		}
	
		Integer nroTransaccionFicticio = transaccionCobroDao.buscarNroTransaccionFicticio(modelo.getIdOperacion(), modelo.getNroTransaccion());
		dto.setNroTransaccionFicticio(nroTransaccionFicticio.toString());
		
//		for(ShvCobTransaccion transaccion : modelo.getDescobro().getOperacion().getTransacciones()){
//			
//			if(!Validaciones.isNullEmptyOrDash(transaccion.get)){
//				if(OrigenDocumentoEnum.DC.name().equals(transaccion.getSubtipoDocumento())){
//					
//					if (!Validaciones.isNullEmptyOrDash(transaccion.getNroDoc())
//							&& transaccion.getNroDoc().equals(docRelac.getNroDocumentoOriginalFormateado())){
//						subtipoDocOriginalValor = transaccion.getSubtipoDocumentoDesc();
//						importeAplicadoOriginal = transaccion.getImporte();
//					}
//				}
//			} else if(!Validaciones.isNullEmptyOrDash(transaccion.getSubtipoMedioPago())){
//				if(OrigenDocumentoEnum.DC.name().equals(transaccion.getSubtipoMedioPago())){
//					if (!Validaciones.isNullEmptyOrDash(transaccion.getNroDoc())
//							&& transaccion.getNroDoc().equals(docRelac.getNroDocumentoOriginalFormateado())){
//						subtipoDocOriginalValor = transaccion.getSubtipoMedioPagoDesc();
//						importeAplicadoOriginal = transaccion.getImporte();
//					}
//				}
//			}
//		}
		
		return dto;
	}

	@Override
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

}
