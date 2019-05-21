package ar.com.telecom.shiva.negocio.mapeos;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistro;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroAplicarDeuda;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroDesistimiento;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroGanancias;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroReintegro;
import ar.com.telecom.shiva.presentacion.bean.dto.RegistroOperacionMasivaAplicarDeudaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.RegistroOperacionMasivaDesistimientoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.RegistroOperacionMasivaDto;
import ar.com.telecom.shiva.presentacion.bean.dto.RegistroOperacionMasivaGananciasDto;
import ar.com.telecom.shiva.presentacion.bean.dto.RegistroOperacionMasivaReintegroDto;

public class RegistroOperacionMasivaMapeador extends Mapeador {

	@Autowired
	private RegistroAplicarDeudaMapeador registroAplicarDeudaMapeador;
	
	@Autowired
	private RegistroCuotaConvenioMapeador registroCuotaConvenioMapeador;
	
	@Autowired
	private RegistroDesistimientoMapeador registroDesistimientoMapeador;
	
	@Autowired
	private RegistroGananciasMapeador registroGananciasMapeador;
	
	@Autowired
	private RegistroReintegroMapeador registroReintegroMapeador;
	
	@Override
	public DTO map(Modelo vo) throws NegocioExcepcion {
		ShvMasRegistro modelo = (ShvMasRegistro) vo;
		RegistroOperacionMasivaDto registroOperacionMasivaDto = new RegistroOperacionMasivaDto();
		
		try {

			if (modelo instanceof ShvMasRegistroAplicarDeuda) {
				ShvMasRegistroAplicarDeuda aplicarDeudaModelo = (ShvMasRegistroAplicarDeuda) modelo;
				RegistroOperacionMasivaAplicarDeudaDto registroAplicarDeudaDto = new RegistroOperacionMasivaAplicarDeudaDto();
				
				registroAplicarDeudaDto = (RegistroOperacionMasivaAplicarDeudaDto) registroAplicarDeudaMapeador.map(aplicarDeudaModelo);
				registroAplicarDeudaDto = (RegistroOperacionMasivaAplicarDeudaDto) setValoresComunesModeloADto(registroAplicarDeudaDto, aplicarDeudaModelo);
				
				return registroAplicarDeudaDto;
				
			} else if (modelo instanceof ShvMasRegistroDesistimiento) {
				RegistroOperacionMasivaDesistimientoDto registroDesistimientoDto = new RegistroOperacionMasivaDesistimientoDto();
				ShvMasRegistroDesistimiento desistimientoModelo = (ShvMasRegistroDesistimiento) modelo;
				
				registroDesistimientoDto = (RegistroOperacionMasivaDesistimientoDto) registroDesistimientoMapeador.map(desistimientoModelo);
				registroDesistimientoDto = (RegistroOperacionMasivaDesistimientoDto) setValoresComunesModeloADto(registroDesistimientoDto, desistimientoModelo);
				
				return registroDesistimientoDto;
			} else if (modelo instanceof ShvMasRegistroGanancias) {
				RegistroOperacionMasivaGananciasDto registroGananciasDto = new RegistroOperacionMasivaGananciasDto();
				ShvMasRegistroGanancias gananciasModelo = (ShvMasRegistroGanancias) modelo;
				
				registroGananciasDto = (RegistroOperacionMasivaGananciasDto) registroGananciasMapeador.map(gananciasModelo);
				registroGananciasDto = (RegistroOperacionMasivaGananciasDto) setValoresComunesModeloADto(registroGananciasDto, gananciasModelo);
				
				return registroGananciasDto;
			} else if (modelo instanceof ShvMasRegistroReintegro) {
				RegistroOperacionMasivaReintegroDto registroReintegroDto = new RegistroOperacionMasivaReintegroDto();
				ShvMasRegistroReintegro reintegroModelo = (ShvMasRegistroReintegro) modelo;
				
				registroReintegroDto = (RegistroOperacionMasivaReintegroDto) registroReintegroMapeador.map(reintegroModelo);
				registroReintegroDto = (RegistroOperacionMasivaReintegroDto) setValoresComunesModeloADto(registroReintegroDto, reintegroModelo);
				
				return registroReintegroDto;
			}
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		return setValoresComunesModeloADto(registroOperacionMasivaDto, modelo);
	}

	@Override
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		RegistroOperacionMasivaDto registroOperacionMasivaDto = (RegistroOperacionMasivaDto) dto;
		
		ShvMasRegistro registroOperacionMasivaModelo = (ShvMasRegistro)
				(vo != null ? vo : new ShvMasRegistro());
		try {

			if (dto instanceof RegistroOperacionMasivaAplicarDeudaDto) {
				
				
				RegistroOperacionMasivaAplicarDeudaDto registroOperacionMasivaAplicarDeudaDto = (RegistroOperacionMasivaAplicarDeudaDto) registroOperacionMasivaDto;
				ShvMasRegistroAplicarDeuda registroAplicarDeudaModelo = new ShvMasRegistroAplicarDeuda();
				
				registroAplicarDeudaModelo = (ShvMasRegistroAplicarDeuda) setValoresComunesDtoAModelo(registroAplicarDeudaModelo, registroOperacionMasivaAplicarDeudaDto);
				registroAplicarDeudaModelo = (ShvMasRegistroAplicarDeuda) registroAplicarDeudaMapeador.map(registroOperacionMasivaAplicarDeudaDto, registroAplicarDeudaModelo);
				
				return registroAplicarDeudaModelo;
				
			} else if (dto instanceof RegistroOperacionMasivaDesistimientoDto) {
				RegistroOperacionMasivaDesistimientoDto registroDesistimientoDto = (RegistroOperacionMasivaDesistimientoDto) registroOperacionMasivaDto;
				ShvMasRegistroDesistimiento desistimientoModelo =  new ShvMasRegistroDesistimiento();
				
				desistimientoModelo = (ShvMasRegistroDesistimiento) setValoresComunesDtoAModelo(desistimientoModelo, registroDesistimientoDto);
				desistimientoModelo = (ShvMasRegistroDesistimiento) registroDesistimientoMapeador.map(registroDesistimientoDto, desistimientoModelo);
				
				return desistimientoModelo;
			} else if (dto instanceof RegistroOperacionMasivaGananciasDto) {
				RegistroOperacionMasivaGananciasDto registroGananciasDto = (RegistroOperacionMasivaGananciasDto) registroOperacionMasivaDto;
				ShvMasRegistroGanancias gananciasModelo = new ShvMasRegistroGanancias();
				
				gananciasModelo = (ShvMasRegistroGanancias) setValoresComunesDtoAModelo(gananciasModelo, registroOperacionMasivaDto);
				gananciasModelo = (ShvMasRegistroGanancias) registroGananciasMapeador.map(registroGananciasDto, gananciasModelo);
				
				return gananciasModelo;
			} else if (dto instanceof RegistroOperacionMasivaReintegroDto) {
				RegistroOperacionMasivaReintegroDto registroReintegroDto = (RegistroOperacionMasivaReintegroDto) registroOperacionMasivaDto;
				ShvMasRegistroReintegro reintegroModelo = new ShvMasRegistroReintegro();
				
				reintegroModelo = (ShvMasRegistroReintegro) setValoresComunesDtoAModelo(reintegroModelo, registroOperacionMasivaDto);
				reintegroModelo = (ShvMasRegistroReintegro) registroReintegroMapeador.map(registroReintegroDto, reintegroModelo);
				
				return reintegroModelo;
			}
			
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		return setValoresComunesDtoAModelo(registroOperacionMasivaModelo, registroOperacionMasivaDto);
	}
	
	
	/**
	 * Setea los valores del modelo padre <code>ShvMasRegistro</code>
	 * @param registro
	 * @param registroOperacionMasivaDto
	 * @return Un modelo con sus parametros seteados.
	 */
	private ShvMasRegistro setValoresComunesDtoAModelo(ShvMasRegistro registro, RegistroOperacionMasivaDto registroOperacionMasivaDto) {
		registro.setIdRegistro(registroOperacionMasivaDto.getIdRegistro());
		registro.setOperacionMasiva(registroOperacionMasivaDto.getOperacionMasiva());
		registro.setIdCobro(registroOperacionMasivaDto.getIdCobro());
		registro.setEstado(registroOperacionMasivaDto.getEstado());
		registro.setFechaCreacion(registroOperacionMasivaDto.getFechaCreacion());
		registro.setUsuarioCreacion(registroOperacionMasivaDto.getUsuarioCreacion());
		registro.setFechaModificacion(registroOperacionMasivaDto.getFechaModificacion());
		registro.setUsuarioModificacion(registroOperacionMasivaDto.getUsuarioModificacion());
		registro.setNombreArchivoOriginal(registroOperacionMasivaDto.getNombreArchivo());
		return registro;
	}
	
	/**
	 * Setea los valores del Dto padre <code>RegistroOperacionMasivaDto</code>
	 * @param registroOperacionMasivaDto
	 * @param registro
	 * @return Un Dto con sus parametros seteados.
	 */
	private RegistroOperacionMasivaDto setValoresComunesModeloADto(RegistroOperacionMasivaDto registroOperacionMasivaDto, ShvMasRegistro registro) {
		registroOperacionMasivaDto.setIdRegistro(registro.getIdRegistro());
		registroOperacionMasivaDto.setOperacionMasiva(registro.getOperacionMasiva());
		registroOperacionMasivaDto.setIdCobro(registro.getIdCobro());
		registroOperacionMasivaDto.setEstado(registro.getEstado()!=null?registro.getEstado():null);
		registroOperacionMasivaDto.setFechaCreacion(registro.getFechaCreacion());
		registroOperacionMasivaDto.setUsuarioCreacion(registro.getUsuarioCreacion());
		registroOperacionMasivaDto.setFechaModificacion(registro.getFechaModificacion());
		registroOperacionMasivaDto.setUsuarioModificacion(registro.getUsuarioModificacion());
		return registroOperacionMasivaDto;
	}
	
}
