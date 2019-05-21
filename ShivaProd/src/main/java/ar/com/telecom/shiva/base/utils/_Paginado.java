package ar.com.telecom.shiva.base.utils;
public class _Paginado {
	private Integer cantidadRegistros=0;
	private Integer paginaNumero = 1;
	private Integer cantidadRegitrosPorHoja = 50;
	public _Paginado() {}
	/**
	 * @return the cantidadRegistros
	 */
	public Integer getCantidadRegistros() {
		return cantidadRegistros;
	}
	/**
	 * @param cantidadRegistros the cantidadRegistros to set
	 */
	public void setCantidadRegistros(Integer cantidadRegistros) {
		this.cantidadRegistros = cantidadRegistros;
	}
	/**
	 * @return the paginaNumero
	 */
	public Integer getPaginaNumero() {
		return paginaNumero;
	}
	/**
	 * @param paginaNumero the paginaNumero to set
	 */
	public void setPaginaNumero(Integer paginaNumero) {
		this.paginaNumero = paginaNumero;
	}
	/**
	 * @return the cantidadRegitrosPorHoja
	 */
	public Integer getCantidadRegitrosPorHoja() {
		return cantidadRegitrosPorHoja;
	}
	/**
	 * @param cantidadRegitrosPorHoja the cantidadRegitrosPorHoja to set
	 */
	public void setCantidadRegitrosPorHoja(Integer cantidadRegitrosPorHoja) {
		this.cantidadRegitrosPorHoja = cantidadRegitrosPorHoja;
	}
}