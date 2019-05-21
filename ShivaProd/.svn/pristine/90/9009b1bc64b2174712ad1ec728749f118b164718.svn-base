package ar.com.telecom.shiva.negocio.mapeos;

import java.text.ParseException;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaGestionaEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.mapeadores.MapeadorResultadoBusqueda;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusqueda;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaTareaPendiente;
import ar.com.telecom.shiva.presentacion.bean.dto.TareaDto;


public class ResultadoBusquedaTareaPendienteMapeador extends MapeadorResultadoBusqueda {

	public DTO map (VistaSoporteResultadoBusqueda vistaSoporteResultadoBusqueda) throws NegocioExcepcion {
		
		VistaSoporteResultadoBusquedaTareaPendiente tareaResultado = (VistaSoporteResultadoBusquedaTareaPendiente) vistaSoporteResultadoBusqueda;
		TareaDto tareaDto = new TareaDto();
		
		if (Validaciones.isObjectNull(tareaDto)) {
			tareaDto = new TareaDto();
		}
		
		tareaDto.setId(tareaResultado.getIdTarea());
		tareaDto.setIdWorkflow(Integer.valueOf(tareaResultado.getIdWorkflowAsociado()));
		tareaDto.setTipoTarea(TipoTareaEnum.getEnumByName(tareaResultado.getTipoTarea()));
		try {
			tareaDto.setFechaCreacion(Utilidad.parseDateAndTimeFullString(tareaResultado.getFechaCreacion()));
			if (!Validaciones.isObjectNull(tareaResultado.getFechaEjecucion())) {
				tareaDto.setFechaEjecucion(Utilidad.parseDateAndTimeFullString(tareaResultado.getFechaEjecucion()));
			}
		} catch (ParseException pe) {
			throw new NegocioExcepcion(pe.getMessage(), pe);
		}
		tareaDto.setUsuarioCreacion(tareaResultado.getUsuarioCreacion());
		tareaDto.setPerfilAsignacion(tareaResultado.getPerfilAsignacion());
		tareaDto.setUsuarioAsignacion(tareaResultado.getUsuarioAsignacion());
		tareaDto.setUsuarioEjecucion(tareaResultado.getUsuarioEjecucion());
		tareaDto.setGestionaSobre(TipoTareaGestionaEnum.getEnumByName(tareaResultado.getGestionaSobre()));
		tareaDto.setEstado(TipoTareaEstadoEnum.getEnumByName(tareaResultado.getEstadoTarea()));
		
		tareaDto.setEmpresa(tareaResultado.getDescripcionEmpresa());
		tareaDto.setIdEmpresa(tareaResultado.getIdEmpresa());
		tareaDto.setSegmento(tareaResultado.getDescripcionSegmento());
		
		tareaDto.setNroCliente(tareaResultado.getNroCliente());
		tareaDto.setRazonSocial(tareaResultado.getRazonSocial());


		if (
			TipoTareaGestionaEnum.COBRO.name().equals(tareaResultado.getGestionaSobre()) ||
			TipoTareaGestionaEnum.DESCOBRO.name().equals(tareaResultado.getGestionaSobre())	
		) {
			if (!Validaciones.isObjectNull(tareaResultado.getMonedaImporte())) {
				tareaDto.setMonedaImporte(MonedaEnum.getEnumByName(tareaResultado.getMonedaImporte()).getSigno2());
				tareaDto.setImporte(tareaDto.getMonedaImporte() + Utilidad.formatCurrency(tareaResultado.getImporte(), false, true));
			} else {
				tareaDto.setImporte(Utilidad.formatCurrency(tareaResultado.getImporte(), true, true));
			}
		} else {
			tareaDto.setImporte(Utilidad.formatCurrency(tareaResultado.getImporte(), true, true));
		}
		
		if (!Validaciones.isNullOrEmpty(tareaResultado.getIdValor())) {
			tareaDto.setIdItem(Long.valueOf(tareaResultado.getIdValor()));
			tareaDto.setIdValor(Long.valueOf(tareaResultado.getIdValor()));
		}
		if (!Validaciones.isNullOrEmpty(tareaResultado.getIdTalonario())) {
			tareaDto.setIdItem(Long.valueOf(tareaResultado.getIdTalonario()));
			tareaDto.setIdTalonario(Long.valueOf(tareaResultado.getIdTalonario()));
		}
		
		if (!Validaciones.isNullOrEmpty(tareaResultado.getIdRegistroAvc())) {
			tareaDto.setIdItem(Long.valueOf(tareaResultado.getIdRegistroAvc()));
			tareaDto.setIdRegistroAVC(Long.valueOf(tareaResultado.getIdRegistroAvc()));
		}
		if (!Validaciones.isNullOrEmpty(tareaResultado.getIdOperacionMasiva())) {
			tareaDto.setIdItem(Long.valueOf(tareaResultado.getIdOperacionMasiva()));
			tareaDto.setIdOperacionMasiva(Long.valueOf(tareaResultado.getIdOperacionMasiva()));
		}
		
		if (!Validaciones.isNullOrEmpty(tareaResultado.getIdValorPorReversion())) {
			tareaDto.setIdItem(Long.valueOf(tareaResultado.getIdValorPorReversion()));
			tareaDto.setIdValorPorReversion(Long.valueOf(tareaResultado.getIdValorPorReversion()));
		}
		
		
		if (!Validaciones.isNullOrEmpty(tareaResultado.getIdCobro())) {
			tareaDto.setIdItem(Long.valueOf(tareaResultado.getIdCobro()));
			tareaDto.setIdCobro(Long.valueOf(tareaResultado.getIdCobro()));
		}
		
		if (!Validaciones.isNullOrEmpty(tareaResultado.getIdDescobro())) {
			tareaDto.setIdItem(Long.valueOf(tareaResultado.getIdDescobro()));
			tareaDto.setIdDescobro(Long.valueOf(tareaResultado.getIdDescobro()));
		}

		if (!Validaciones.isNullOrEmpty(tareaResultado.getIdOperacion())) {
			tareaDto.setIdOperacion(Long.valueOf(tareaResultado.getIdOperacion()));
		}
		

		//
		// INFORMACION ADICIONAL - CAMPOS SEPARADOS
		//
		if (!Validaciones.isNullOrEmpty(tareaResultado.getNroBoleta())) {
			tareaDto.setNroBoleta(tareaResultado.getNroBoleta());
		}
		if (!Validaciones.isNullOrEmpty(tareaResultado.getDescBanco())) {
			tareaDto.setDescBanco(tareaResultado.getDescBanco());
		}
		if (!Validaciones.isNullOrEmpty(tareaResultado.getVencimiento())) {
			tareaDto.setVencimiento(tareaResultado.getVencimiento());
		}
		if (!Validaciones.isNullOrEmpty(tareaResultado.getTipo())) {
			tareaDto.setTipo(tareaResultado.getTipo());
		}
		
		if (!Validaciones.isNullOrEmpty(tareaResultado.getCuit())) {
			tareaDto.setCuit(tareaResultado.getCuit());
		}
		if (!Validaciones.isNullOrEmpty(tareaResultado.getProvincia())) {
			tareaDto.setProvincia(tareaResultado.getProvincia());
		}
		if (!Validaciones.isNullOrEmpty(tareaResultado.getCodOrganismo())) {
			tareaDto.setCodOrganismo(tareaResultado.getCodOrganismo());
		}
		if (!Validaciones.isNullOrEmpty(tareaResultado.getRango())) {
			tareaDto.setRango(tareaResultado.getRango());
		}else{
			tareaDto.setRango("-");
		}
		
		if (!Validaciones.isNullOrEmpty(tareaResultado.getNroCheque())) {
			tareaDto.setRefBandeja(tareaResultado.getNroCheque());
		} else if (!Validaciones.isNullOrEmpty(tareaResultado.getCodInterdeposito())) {
			tareaDto.setRefBandeja(tareaResultado.getCodInterdeposito());
		} else if (!Validaciones.isNullOrEmpty(tareaResultado.getReferencia())) {
			tareaDto.setRefBandeja(tareaResultado.getReferencia());
		} else if (!Validaciones.isNullOrEmpty(tareaResultado.getNroConstancia())) {
			tareaDto.setRefBandeja(tareaResultado.getNroConstancia());
		} else {
			tareaDto.setRefBandeja("-");
		}
		
		if (!Validaciones.isObjectNull(tareaResultado.getSistema())) {
			tareaDto.setSistema(tareaResultado.getSistema());
		}
		if (!Validaciones.isObjectNull(tareaResultado.getSociedad())) {
			tareaDto.setSociedad(tareaResultado.getSociedad());
		}
		return tareaDto;
	}
	
}
