package ar.com.telecom.shiva.negocio.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;

public class TotalAcumuladoresTransacciones implements Serializable {
	private static final long serialVersionUID = -6632278318110481636L;
	private BigDecimal totalTraslados = BigDecimal.ZERO;
	private BigDecimal totalBonificados = BigDecimal.ZERO;
	private BigDecimal totalReintegro = BigDecimal.ZERO;
	private BigDecimal totalTrasladosU$S = BigDecimal.ZERO;
	private BigDecimal totalBonificadosU$S = BigDecimal.ZERO;
	private BigDecimal totalReintegroU$S = BigDecimal.ZERO;

	public TotalAcumuladoresTransacciones() {
		// TODO Auto-generated constructor stub
	}

	public void totalTraslados(MonedaEnum moneda, BigDecimal intereses) {
		if (!MonedaEnum.DOL.equals(moneda)) {
			this.totalTraslados = this.totalTraslados.add(intereses);
		} else {
			this.totalTrasladosU$S = this.totalTrasladosU$S.add(intereses);
		}
	}
	public void totalTraslados(MonedaEnum moneda, BigDecimal intereses, BigDecimal importeABonificar) {
		if (!MonedaEnum.DOL.equals(moneda)) {
			this.totalTraslados = this.totalTraslados.add(intereses.subtract(importeABonificar));
		} else {
			this.totalTrasladosU$S = this.totalTrasladosU$S.add(intereses.subtract(importeABonificar));
		}
	}
 
	public void totalBonificados(MonedaEnum moneda, BigDecimal importeABonificar) {
		if (!MonedaEnum.DOL.equals(moneda)) {
			this.totalBonificados = this.totalBonificados.add(importeABonificar);
		} else {
			this.totalBonificadosU$S = this.totalBonificadosU$S.add(importeABonificar);
		}
	}

	public void totalReintegro(MonedaEnum moneda, BigDecimal intereses) {
		if (!MonedaEnum.DOL.equals(moneda)) {
			this.totalReintegro = this.totalReintegro.add(intereses);
		} else {
			this.totalReintegroU$S = this.totalReintegroU$S.add(intereses);
		}
	}
	/**
	 * @return the totalTraslados
	 */
	public BigDecimal getTotalTraslados() {
		return totalTraslados;
	}

	/**
	 * @param totalTraslados the totalTraslados to set
	 */
	public void setTotalTraslados(BigDecimal totalTraslados) {
		this.totalTraslados = totalTraslados;
	}

	/**
	 * @return the totalBonificados
	 */
	public BigDecimal getTotalBonificados() {
		return totalBonificados;
	}

	/**
	 * @param totalBonificados the totalBonificados to set
	 */
	public void setTotalBonificados(BigDecimal totalBonificados) {
		this.totalBonificados = totalBonificados;
	}

	/**
	 * @return the totalReintegro
	 */
	public BigDecimal getTotalReintegro() {
		return totalReintegro;
	}

	/**
	 * @param totalReintegro the totalReintegro to set
	 */
	public void setTotalReintegro(BigDecimal totalReintegro) {
		this.totalReintegro = totalReintegro;
	}

	/**
	 * @return the totalTrasladosU$S
	 */
	public BigDecimal getTotalTrasladosU$S() {
		return totalTrasladosU$S;
	}

	/**
	 * @param totalTrasladosU$S the totalTrasladosU$S to set
	 */
	public void setTotalTrasladosU$S(BigDecimal totalTrasladosU$S) {
		this.totalTrasladosU$S = totalTrasladosU$S;
	}

	/**
	 * @return the totalBonificadosU$S
	 */
	public BigDecimal getTotalBonificadosU$S() {
		return totalBonificadosU$S;
	}

	/**
	 * @param totalBonificadosU$S the totalBonificadosU$S to set
	 */
	public void setTotalBonificadosU$S(BigDecimal totalBonificadosU$S) {
		this.totalBonificadosU$S = totalBonificadosU$S;
	}

	/**
	 * @return the totalReintegroU$S
	 */
	public BigDecimal getTotalReintegroU$S() {
		return totalReintegroU$S;
	}

	/**
	 * @param totalReintegroU$S the totalReintegroU$S to set
	 */
	public void setTotalReintegroU$S(BigDecimal totalReintegroU$S) {
		this.totalReintegroU$S = totalReintegroU$S;
	}

	/**
	 * @return the totalTraslados
	 */
	public String getTotalTrasladosString() {
		return Utilidad.formatCurrency(this.totalTraslados, 2, MonedaEnum.PES.getSigno2());
	}
	public String getTotalBonificadosString() {
		return Utilidad.formatCurrency(this.totalBonificados, 2, MonedaEnum.PES.getSigno2());
	}
	public String getTotalReintegroString() {
		return Utilidad.formatCurrency(this.totalReintegro, 2, MonedaEnum.PES.getSigno2());
	}
	public String getTotalTrasladosU$SString() {
		return Utilidad.formatCurrency(this.totalTrasladosU$S, 2, MonedaEnum.DOL.getSigno2());
	}
	public String getTotalBonificadosU$SString() {
		return Utilidad.formatCurrency(this.totalBonificadosU$S, 2, MonedaEnum.DOL.getSigno2());
	}
	public String getTotalReintegroU$SString() {
		return Utilidad.formatCurrency(this.totalReintegroU$S, 2, MonedaEnum.DOL.getSigno2());
	}

}
