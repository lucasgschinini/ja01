package ar.com.telecom.shiva.negocio.mapeos;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.EstadoAcuerdoFacturacionEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTratamientoDiferenciaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.ITipoMedioPagoServicio;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdTratamientoDiferencia;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroTratamientoDiferenciaDto;

public class CobroOnlineTratamientoDiferenciaMapeador  extends Mapeador {
	
	@Autowired private ITipoMedioPagoServicio tipoMedioPagoServicio;
	
	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvCobEdTratamientoDiferencia modelo = (ShvCobEdTratamientoDiferencia) vo;
		
		CobroTratamientoDiferenciaDto dto = new CobroTratamientoDiferenciaDto();
		
		dto.setAcuerdoFacturacion(modelo.getAcuerdoFacturacion());
		dto.setIdClienteAcuerdoFacturacion(modelo.getIdClienteAcuerdoFacturacion());
		dto.setConCalculoInteres(modelo.getConCalculoInteres() != null ? modelo.getConCalculoInteres().getDescripcion() : null);
		dto.setTipoTratamientoDiferencia(modelo.getTipoTratamientoDiferencia() != null ? modelo.getTipoTratamientoDiferencia().getName() : null);
		dto.setNumeroTramiteReintegro(modelo.getNumeroTramiteReintegro());
		dto.setFechaAltaTramiteReintegro(Utilidad.formatDatePicker(modelo.getFechaAltaTramiteReintegro()));
		dto.setSistemaDestino(modelo.getSistemaDestino() != null ? modelo.getSistemaDestino().getDescripcionCorta() : null);
		dto.setLinea(modelo.getLinea());
		dto.setImporte(Utilidad.formatCurrency(modelo.getImporte(), 2));
		dto.setEstadoAcuerdoFacturacion(modelo.getEstadoAcuerdoFacturacion()!=null?modelo.getEstadoAcuerdoFacturacion().name():null);
		dto.setMoneda(modelo.getMoneda() != null ? modelo.getMoneda().name() : null);
		dto.setReferencia(modelo.getReferencia() != null ? modelo.getReferencia() : null);
		return dto;
	}
	
	
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		CobroTratamientoDiferenciaDto tratamientoDiferenciaDto = (CobroTratamientoDiferenciaDto) dto;
		
		ShvCobEdTratamientoDiferencia modelo = 
				(vo != null ? (ShvCobEdTratamientoDiferencia) vo : new ShvCobEdTratamientoDiferencia());
		try {
			
			modelo.setAcuerdoFacturacion(tratamientoDiferenciaDto.getAcuerdoFacturacion());
			modelo.setIdClienteAcuerdoFacturacion(tratamientoDiferenciaDto.getIdClienteAcuerdoFacturacion());
			modelo.setConCalculoInteres(SiNoEnum.getEnumByDescripcion(tratamientoDiferenciaDto.getConCalculoInteres()));
			modelo.setTipoTratamientoDiferencia(TipoTratamientoDiferenciaEnum.getEnumByName(tratamientoDiferenciaDto.getTipoTratamientoDiferencia()));
			modelo.setNumeroTramiteReintegro(tratamientoDiferenciaDto.getNumeroTramiteReintegro());
			modelo.setFechaAltaTramiteReintegro(!Validaciones.isNullEmptyOrDash(tratamientoDiferenciaDto.getFechaAltaTramiteReintegro())?Utilidad.parseDatePickerString(tratamientoDiferenciaDto.getFechaAltaTramiteReintegro()): null);
			modelo.setSistemaDestino(SistemaEnum.getEnumByDescripcionCorta(tratamientoDiferenciaDto.getSistemaDestino()));
			modelo.setLinea(tratamientoDiferenciaDto.getLinea());
			modelo.setImporte(!Validaciones.isNullEmptyOrDash(tratamientoDiferenciaDto.getImporte()) ? Utilidad.stringToBigDecimal(tratamientoDiferenciaDto.getImporte().replace(Constantes.SIGNO_MENOS, "")) : null);			
			modelo.setEstadoAcuerdoFacturacion(!Validaciones.isNullEmptyOrDash(tratamientoDiferenciaDto.getEstadoAcuerdoFacturacion())?
					EstadoAcuerdoFacturacionEnum.getEnumByName(tratamientoDiferenciaDto.getEstadoAcuerdoFacturacion()):null);
			
			modelo.setTipoMedioPago(tipoMedioPagoServicio.buscarMedioPago(
					TipoMedioPagoEnum.getEnumByIdTipoMedioPago(modelo.getTipoTratamientoDiferencia().getIdTipoMedioPagoAsociado())));
			
			if (!Validaciones.isObjectNull(tratamientoDiferenciaDto.getMoneda())){

				//Si el getEnumBySigla es null, entonces ya tenia seteada La MONEDA IMPORTE A COBRAR, SINO, LO SETEO
				if (!Validaciones.isObjectNull(MonedaEnum.getEnumBySigno2(tratamientoDiferenciaDto.getMoneda()))){
					modelo.setMoneda(MonedaEnum.getEnumBySigno2(tratamientoDiferenciaDto.getMoneda()));
				} else {
					modelo.setMoneda(MonedaEnum.getEnumByName(tratamientoDiferenciaDto.getMoneda()));
				}

			}
			
			modelo.setReferencia(!Validaciones.isNullEmptyOrDash(tratamientoDiferenciaDto.getReferencia()) ? tratamientoDiferenciaDto.getReferencia() : null) ;
			
			
		} catch(Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return modelo;
	}

}
