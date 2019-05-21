package ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.facturas.ListaFacturasCalipso;
import ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.facturas.ListaFacturasMIC;
import ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.mediospago.ListaCompensaciones;
import ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.mediospago.ListaDesistimientos;
import ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.mediospago.ListaNotasCreditoCalipso;
import ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.mediospago.ListaNotasCreditoMIC;
import ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.mediospago.ListaPagosACuenta;
import ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.mediospago.ListaPlanesDePago;
import ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.mediospago.ListaRemanentes;
import ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.mediospago.ListaTrasladosProximaFactura;
import ar.com.telecom.shiva.base.ws.servidor.datos.imputacionCobros.mediospago.ListaValoresShiva;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "imputacionCobrosEntrada", propOrder = {
		"planillaMovika","listaFacturasMIC","listaFacturasCalipso",
		"listaRemanentes","listaNotasCreditoMIC","listaPagosACuenta",
		"listaNotasCreditoCalipso","listaValoresShiva","listaCompensaciones",
		"listaDesistimientos","listaTrasladosProximaFactura","listaPlanesDePago"})
@XmlRootElement
public class ImputacionCobrosEntrada {

	@XmlElement(name = "planillaMovika", required = true)
	private PlanillaMovika planillaMovika = new PlanillaMovika();
	
	@XmlElement(name = "listaFacturasMIC")
	private ListaFacturasMIC  listaFacturasMIC  = new ListaFacturasMIC();
	
	@XmlElement(name = "listaFacturasCalipso")
	private ListaFacturasCalipso  listaFacturasCalipso  = new ListaFacturasCalipso();
	
	@XmlElement(name = "listaRemanentes")
	private ListaRemanentes  listaRemanentes  = new ListaRemanentes();
	
	@XmlElement(name = "listaNotasCreditoMIC")
	private ListaNotasCreditoMIC  listaNotasCreditoMIC  = new ListaNotasCreditoMIC();
	
	@XmlElement(name = "listaPagosACuenta")
	private ListaPagosACuenta  listaPagosACuenta  = new ListaPagosACuenta();
	
	@XmlElement(name = "listaNotasCreditoCalipso")
	private ListaNotasCreditoCalipso  listaNotasCreditoCalipso  = new ListaNotasCreditoCalipso();
	
	@XmlElement(name = "listaValoresShiva")
	private ListaValoresShiva  listaValoresShiva  = new ListaValoresShiva();
	
	@XmlElement(name = "listaCompensaciones")
	private ListaCompensaciones listaCompensaciones  = new ListaCompensaciones();
	
	@XmlElement(name = "listaDesistimientos")
	private ListaDesistimientos  listaDesistimientos  = new ListaDesistimientos();
	
	@XmlElement(name = "listaTrasladosProximaFactura")
	private ListaTrasladosProximaFactura  listaTrasladosProximaFactura  = new ListaTrasladosProximaFactura();
	
	@XmlElement(name = "listaPlanesDePago")
	private ListaPlanesDePago  listaPlanesDePago  = new ListaPlanesDePago();
	
	/**
	 * @return the planillaMovika
	 */
	public PlanillaMovika getPlanillaMovika() {
		return planillaMovika;
	}
	/**
	 * @param planillaMovika the planillaMovika to set
	 */
	public void setPlanillaMovika(PlanillaMovika planillaMovika) {
		this.planillaMovika = planillaMovika;
	}
	
	public ListaFacturasMIC getListaFacturasMIC() {
		return listaFacturasMIC;
	}
	public void setListaFacturasMIC(ListaFacturasMIC listaFacturasMIC) {
		this.listaFacturasMIC = listaFacturasMIC;
	}
	
	public ListaFacturasCalipso getListaFacturasCalipso() {
		return listaFacturasCalipso;
	}
	public void setListaFacturasCalipso(ListaFacturasCalipso listaFacturasCalipso) {
		this.listaFacturasCalipso = listaFacturasCalipso;
	}
	
	public ListaRemanentes getListaRemanentes() {
		return listaRemanentes;
	}
	public void setListaRemanentes(ListaRemanentes listaRemanentes) {
		this.listaRemanentes = listaRemanentes;
	}
	
	public ListaNotasCreditoMIC getListaNotasCreditoMIC() {
		return listaNotasCreditoMIC;
	}
	public void setListaNotasCreditoMIC(ListaNotasCreditoMIC listaNotasCreditoMIC) {
		this.listaNotasCreditoMIC = listaNotasCreditoMIC;
	}
	
	public ListaPagosACuenta getListaPagosACuenta() {
		return listaPagosACuenta;
	}
	public void setListaPagosACuenta(ListaPagosACuenta listaPagosACuenta) {
		this.listaPagosACuenta = listaPagosACuenta;
	}
	
	public ListaNotasCreditoCalipso getListaNotasCreditoCalipso() {
		return listaNotasCreditoCalipso;
	}
	public void setListaNotasCreditoCalipso(
			ListaNotasCreditoCalipso listaNotasCreditoCalipso) {
		this.listaNotasCreditoCalipso = listaNotasCreditoCalipso;
	}
	
	public ListaValoresShiva getListaValoresShiva() {
		return listaValoresShiva;
	}
	public void setListaValoresShiva(ListaValoresShiva listaValoresShiva) {
		this.listaValoresShiva = listaValoresShiva;
	}
	
	public ListaCompensaciones getListaCompensaciones() {
		return listaCompensaciones;
	}
	public void setListaCompensaciones(ListaCompensaciones listaCompensaciones) {
		this.listaCompensaciones = listaCompensaciones;
	}
	
	public ListaDesistimientos getListaDesistimientos() {
		return listaDesistimientos;
	}
	public void setListaDesistimientos(ListaDesistimientos listaDesistimientos) {
		this.listaDesistimientos = listaDesistimientos;
	}
	
	public ListaTrasladosProximaFactura getListaTrasladosProximaFactura() {
		return listaTrasladosProximaFactura;
	}
	public void setListaTrasladosProximaFactura(
			ListaTrasladosProximaFactura listaTrasladosProximaFactura) {
		this.listaTrasladosProximaFactura = listaTrasladosProximaFactura;
	}
	
	public ListaPlanesDePago getListaPlanesDePago() {
		return listaPlanesDePago;
	}
	public void setListaPlanesDePago(ListaPlanesDePago listaPlanesDePago) {
		this.listaPlanesDePago = listaPlanesDePago;
	}
	
}

