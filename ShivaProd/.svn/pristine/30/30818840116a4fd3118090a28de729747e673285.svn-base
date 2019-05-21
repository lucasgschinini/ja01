package ar.com.telecom.shiva.negocio.mapeos;

import java.util.Collection;
import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.TipoConciliacionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.negocio.conciliacion.definicion.Conciliacion;
import ar.com.telecom.shiva.negocio.conciliacion.definicion.ReglaConciliacionDto;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamConciliacion;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamReglaConciliacion;

public class ReglaConciliacionMapeador {


	public List<ReglaConciliacionDto> mapearReglas(List<ReglaConciliacionDto> reglas, Collection<ShvParamReglaConciliacion> listaReglasModelo,
			Collection<ShvParamConciliacion> listaConciliacionesModelo) {
		
		for (ReglaConciliacionDto reglaDto : reglas) {
			for(ShvParamReglaConciliacion reglaModelo : listaReglasModelo){
				if(reglaDto.getIdRegla().equals(reglaModelo.getIdReglaConciliacion())){
					reglaDto.setEstado(reglaModelo.getEstado());
					reglaDto.setTipoValor(TipoValorEnum.getEnumByIdTipoValor(reglaModelo.getTipoValor().getIdTipoValor()));
					
					for(Conciliacion conciliacion : reglaDto.getListaConciliacion()){
						for(ShvParamConciliacion conciModelo : listaConciliacionesModelo){
							
							if(reglaDto.getIdRegla().equals(conciModelo.getReglaConciliacion().getIdReglaConciliacion())
									&& conciliacion.getIdConciliacion().equals(conciModelo.getIdConciliacion())){
								
								conciliacion.setNumeroConciliacion(conciModelo.getNumeroConciliacion());
								conciliacion.setOrdenConciliacion(conciModelo.getOrdenConciliacion());
								conciliacion.setTipoConciliacion(TipoConciliacionEnum.getEnumByDescripcionTipoConciliacion(conciModelo.getIdTipoConciliacion()));
								conciliacion.setEstado(conciModelo.getEstado());
							}
						}
					}
				}
			}
			
			
		}
		
		return reglas; 
	}


}
