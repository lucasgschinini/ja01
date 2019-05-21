package ar.com.telecom.shiva.negocio.batch.bean.scard;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlRootElement
@XmlType(propOrder = {"importeTotal", "cantidadFacturas", "factura"})
public class Facturas {

	private String importeTotal;
	private Integer cantidadFacturas = 0;
	private List<Factura> factura = new ArrayList<Factura>();
	
	@XmlElement(name="importe_total")
	public String getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(String importeTotal) {
		this.importeTotal = importeTotal;
	}
	@XmlElement(name="cantidad_facturas")
	public Integer getCantidadFacturas() {
		return cantidadFacturas;
	}
	public void setCantidadFacturas(Integer cantidadFacturas) {
		this.cantidadFacturas = cantidadFacturas;
	}
	@XmlElement
	public List<Factura> getFactura() {
		return factura;
	}
	public void setFactura(List<Factura> factura) {
		this.factura = factura;
	}
}
