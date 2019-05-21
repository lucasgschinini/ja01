package ar.com.telecom.shiva.negocio.mapeos;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.ClienteOrigenEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCliente;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;

public class CobroOnlineClienteMapeador extends Mapeador {
	
	
	@Override
	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvCobEdCliente modelo = (ShvCobEdCliente) vo;
		
		ClienteDto dto = new ClienteDto();
		
		dto.setId(modelo.getPk().getIdClienteCobro().toString());
		dto.setIdCobroCliente(modelo.getPk().getIdClienteCobro().toString());
		
		dto.setIdClienteLegado(modelo.getIdClienteLegado().toString());
		dto.setEmpresasAsociadas(Utilidad.armadoCampoEmpresasAsociadas(modelo.getPermiteUsoTA(), modelo.getPermiteUsoTP(), modelo.getPermiteUsoCV(), modelo.getPermiteUsoNX()));
		dto.setRazonSocial(!Validaciones.isObjectNull(modelo.getRazonSocial()) ? modelo.getRazonSocial(): "-");
		if (!Validaciones.isObjectNull(modelo.getOrigen())) {
			dto.setOrigen(modelo.getOrigen().getCodigo());
		}
		dto.setCodigoHolding(!Validaciones.isObjectNull(modelo.getIdHolding()) ? modelo.getIdHolding().toString(): "-");
		dto.setDescripcionHolding(!Validaciones.isObjectNull(modelo.getDescripcionHolding()) ? modelo.getDescripcionHolding(): "-");
		dto.setCuit(!Validaciones.isObjectNull(modelo.getCuit()) ? modelo.getCuit(): "-");
		
		dto.setAgenciaNegocio(!Validaciones.isObjectNull(modelo.getIdAgenciaNegocio()) ? modelo.getIdAgenciaNegocio().toString(): "-");
		dto.setDescripcionAgenciaNegocio(!Validaciones.isObjectNull(modelo.getDescripcionAgenciaNegocio()) ? modelo.getDescripcionAgenciaNegocio().toString(): "-");
		
	    dto.setSegmentoAgencia(!Validaciones.isObjectNull(modelo.getSegmentoAgenciaNegocio()) ? modelo.getSegmentoAgenciaNegocio(): "-");
	    dto.setIdClientePerfil(!Validaciones.isObjectNull(modelo.getIdClientePerfil()) ? modelo.getIdClientePerfil().toString() : "-");
	    dto.setIdProvincia(modelo.getIdProvincia());
		return dto;
	}
	
	@Override
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		ClienteDto clienteDto = (ClienteDto) dto;
		
		ShvCobEdCliente modelo = (ShvCobEdCliente)
				(vo != null ? vo : new ShvCobEdCliente());
		
		modelo.setIdClienteLegado(new Long(clienteDto.getIdClienteLegado()));
		modelo.setCuit(clienteDto.getCuit());
		modelo.setRazonSocial(clienteDto.getRazonSocial());
		if(!Validaciones.isNullEmptyOrDash(clienteDto.getEmpresasAsociadas())) {
			Utilidad.formateoEmpresasAsociadasAPermiteUsos(clienteDto, modelo);
		}
		
		if (!Validaciones.isObjectNull(clienteDto.getOrigen())) {
			modelo.setOrigen(ClienteOrigenEnum.getEnumByCodigo(clienteDto.getOrigen()));
		}
		if (!Validaciones.isNullEmptyOrDash(clienteDto.getAgenciaNegocio())) {
			modelo.setIdAgenciaNegocio(new Long(clienteDto.getAgenciaNegocio()));
		}
		if (!Validaciones.isNullEmptyOrDash(clienteDto.getDescripcionAgenciaNegocio())) {
			modelo.setDescripcionAgenciaNegocio(clienteDto.getDescripcionAgenciaNegocio());
		}
		if (!Validaciones.isNullEmptyOrDash(clienteDto.getCodigoHolding())) {
			modelo.setIdHolding(new Long(clienteDto.getCodigoHolding()));
		}
		if (!Validaciones.isNullEmptyOrDash(clienteDto.getDescripcionHolding())) {
			modelo.setDescripcionHolding(clienteDto.getDescripcionHolding());
		}
		if (!Validaciones.isNullEmptyOrDash(clienteDto.getSegmentoAgencia())) {
			modelo.setSegmentoAgenciaNegocio(clienteDto.getSegmentoAgencia());
		}
		if (!Validaciones.isNullEmptyOrDash(clienteDto.getIdClientePerfil())) {
			modelo.setIdClientePerfil(new Long(clienteDto.getIdClientePerfil()));
		}
		if (!Validaciones.isNullEmptyOrDash(clienteDto.getIdProvincia())) {
			modelo.setIdProvincia(clienteDto.getIdProvincia());
		}
		
	    return modelo;
	}

}
