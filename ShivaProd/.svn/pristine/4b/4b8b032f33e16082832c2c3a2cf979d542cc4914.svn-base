package ar.com.telecom.shiva.negocio.servicios.validacion.impl;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.TipoConciliacionEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.negocio.conciliacion.definicion.Conciliacion;
import ar.com.telecom.shiva.negocio.conciliacion.definicion.ReglaConciliacionDto;
import ar.com.telecom.shiva.negocio.servicios.validacion.IReglaConciliacionValidacionServicio;

public class ReglaConciliacionValidacionServicioImpl implements IReglaConciliacionValidacionServicio {

	/**
	 * Valida que exista siempre al menos 1 regla de conciliación total, y solo 1.
	 * @param reglas
	 * @return
	 * @throws NegocioExcepcion
	 */
	public boolean validarCantConciliacionTotal(ReglaConciliacionDto reglas) throws NegocioExcepcion {
		List<Conciliacion> reglasConciliacionTotal = obtenerReglasConciliacionTotal(reglas);
		if(reglasConciliacionTotal.size()==1){
			return true;
		}
		return false;
	}

	/**
	 * Valida que exista siempre al menos una regla de conciliación parcial.
	 * @param reglas
	 * @return
	 * @throws NegocioExcepcion
	 */
	public boolean validarCantConciliacionParcial(ReglaConciliacionDto reglas) throws NegocioExcepcion {
		List<Conciliacion> reglasConciliacionSugerida = obtenerReglasConciliacionSugeridas(reglas);
		if(reglasConciliacionSugerida.size()>=1){
			return true;
		}
		return false;
	}

	/**
	 * Valida que el orden de las reglas de conciliación total debe ser mayor a aquellas de conciliación parcial.
	 * @param reglas
	 * @return
	 * @throws NegocioExcepcion
	 */
	public boolean validarOrdenConciliacionTotal(ReglaConciliacionDto reglas) throws NegocioExcepcion {
		Long ordenReglaTotal = obtenerReglasConciliacionTotal(reglas).get(0).getOrdenConciliacion();
		for (Conciliacion reglaSugerida : obtenerReglasConciliacionSugeridas(reglas)) {
			if(reglaSugerida.getOrdenConciliacion()<ordenReglaTotal){
				return false;
			}
		}
		return true;
	}

	/**
	 * Valida que las reglas de conciliación no se dupliquen.
	 * @param reglas
	 * @return
	 * @throws NegocioExcepcion
	 */
	public boolean validarDuplicidadRegla(ReglaConciliacionDto reglas) throws NegocioExcepcion {
		List<Conciliacion> listaConciliacion = reglas.getListaConciliacion();
		List<Conciliacion> listaConciliacionAux = reglas.getListaConciliacion();
		for(int i =0; i<listaConciliacion.size() ; i++){
			for(int j =i+1; j<listaConciliacionAux.size() ; j++){
				listaConciliacion.get(i).getListaCamposCoincidentes();
				listaConciliacionAux.get(j).getListaCamposCoincidentes();
				if(listaConciliacion.get(i).getListaCamposCoincidentes().containsAll(listaConciliacionAux.get(j).getListaCamposCoincidentes()) 
						&& listaConciliacionAux.get(j).getListaCamposCoincidentes().containsAll(listaConciliacion.get(i).getListaCamposCoincidentes())){
					return false;
				}
			}			
		}
		return true;
	}

	/**
	 * Recorre las reglas de conciliacion y devuelve una lista con las del tipo Total.
	 * @param reglas
	 * @return
	 */
	private List<Conciliacion> obtenerReglasConciliacionTotal(
			ReglaConciliacionDto reglas) {
		List<Conciliacion> listaAux = new ArrayList<>();
		for (Conciliacion conciliacion : reglas.getListaConciliacion()) {
			if(TipoConciliacionEnum.TOTAL.equals(conciliacion.getTipoConciliacion())){
				listaAux.add(conciliacion);
			}
		}
		return listaAux;
	}
	
	/**
	 * Recorre las reglas de conciliacion y devuelve una lista con las del tipo Sugerida.
	 * @param reglas
	 * @return
	 */
	private List<Conciliacion> obtenerReglasConciliacionSugeridas(
			ReglaConciliacionDto reglas) {
		List<Conciliacion> listaAux = new ArrayList<>();
		for (Conciliacion conciliacion : reglas.getListaConciliacion()) {
			if(TipoConciliacionEnum.SUGERIDA.equals(conciliacion.getTipoConciliacion())){
				listaAux.add(conciliacion);
			}
		}
		return listaAux;
	}
}
