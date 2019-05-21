/**
 * 
 */
package ar.com.telecom.shiva.negocio.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.dto.DTO;

/**
 * @author pablo.m.ibarrola
 *
 */
public class ComboAcuerdoBancoCuentaDto extends DTO {
	private static final long serialVersionUID = 20170520L;

	private List<ParametroAcuerdoDto> listaAcuerdos = new ArrayList<ParametroAcuerdoDto>();
	private List<ParametroBancoDto> listaBancos = new ArrayList<ParametroBancoDto>();
	private List<ParametroBancoCuentaDto> listaCuentas = new ArrayList<ParametroBancoCuentaDto>();
	/**
	 * @return the listaAcuerdos
	 */
	public List<ParametroAcuerdoDto> getListaAcuerdos() {
		return listaAcuerdos;
	}
	/**
	 * @param listaAcuerdos the listaAcuerdos to set
	 */
	public void setListaAcuerdos(List<ParametroAcuerdoDto> listaAcuerdos) {
		this.listaAcuerdos = listaAcuerdos;
	}
	/**
	 * @return the listaBancos
	 */
	public List<ParametroBancoDto> getListaBancos() {
		return listaBancos;
	}
	/**
	 * @param listaBancos the listaBancos to set
	 */
	public void setListaBancos(List<ParametroBancoDto> listaBancos) {
		this.listaBancos = listaBancos;
	}
	/**
	 * @return the listaCuentas
	 */
	public List<ParametroBancoCuentaDto> getListaCuentas() {
		return listaCuentas;
	}
	/**
	 * @param listaCuentas the listaCuentas to set
	 */
	public void setListaCuentas(List<ParametroBancoCuentaDto> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}
	
}
