/**
 * 
 */
package ar.com.telecom.shiva.presentacion.facade;

import ar.com.telecom.shiva.base.mapeadores.Mapeador;

/**
 * @author u564030
 *
 */
public interface IFacade {

	public Mapeador getDefaultMapeador();
	
	public void setDefaultMapeador(Mapeador defaultMapeador);
}
