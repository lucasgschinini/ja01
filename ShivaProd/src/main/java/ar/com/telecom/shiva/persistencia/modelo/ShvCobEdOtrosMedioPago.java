package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SubTipoCompensacionEnum;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamProvincia;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoMedioPago;


@Entity
@Table(name="SHV_COB_ED_OTROS_MEDIO_PAGO")
public class ShvCobEdOtrosMedioPago extends Modelo  {
	private static final long serialVersionUID = 1L;

	@Id
	private ShvCobEdOtrosMedioPagoPk pk;
	
	@Column(name="FECHA")
	private Date fecha;

	@Column(name="IMPORTE")
	private BigDecimal importe;

	@Column(name="NRO_ACTA")
	private String nroActa;

	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_TIPO_MEDIO_PAGO") 
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamTipoMedioPago tipoMedioPago;

	@Enumerated(EnumType.STRING)
	@Column(name = "CHECK_MEDIO_PAGO_EN_PROCESO")
	private SiNoEnum checkMedioPagoEnProceso;
	
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy="otrosMedioPago")
	private Set<ShvCobEdMedioPagoCliente> listaMedioPagoCliente = new HashSet<ShvCobEdMedioPagoCliente>(0);
	
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy="otrosMedioPago")
	private List<ShvCobEdDocumentoCap> documentosCap = new ArrayList<ShvCobEdDocumentoCap>();
	
	@Enumerated(EnumType.STRING)
	@Column(name="MONEDA_IMPORTE_A_UTILIZAR")
	private MonedaEnum  monedaImporteAUtilizar;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ID_SUBTIPO_OTROS_MEDIO_PAGO")
	private SubTipoCompensacionEnum subTipo;
	

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PROVINCIA", referencedColumnName = "ID_PROVINCIA")
	private ShvParamProvincia provincia;

	public ShvCobEdOtrosMedioPago() {
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getImporte() {
		return this.importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public String getNroActa() {
		return this.nroActa;
	}

	public void setNroActa(String nroActa) {
		this.nroActa = nroActa;
	}

	public Set<ShvCobEdMedioPagoCliente> getListaMedioPagoCliente() {
		return listaMedioPagoCliente;
	}

	public void setListaMedioPagoCliente(
			Set<ShvCobEdMedioPagoCliente> listaMedioPagoCliente) {
		this.listaMedioPagoCliente = listaMedioPagoCliente;
	}

	public SiNoEnum getCheckMedioPagoEnProceso() {
		return checkMedioPagoEnProceso;
	}

	public void setCheckMedioPagoEnProceso(SiNoEnum checkMedioPagoProceso) {
		this.checkMedioPagoEnProceso = checkMedioPagoProceso;
	}

	public ShvCobEdOtrosMedioPagoPk getPk() {
		return pk;
	}

	public void setPk(ShvCobEdOtrosMedioPagoPk pk) {
		this.pk = pk;
	}

	public ShvParamTipoMedioPago getTipoMedioPago() {
		return tipoMedioPago;
	}

	public void setTipoMedioPago(ShvParamTipoMedioPago tipoMedioPago) {
		this.tipoMedioPago = tipoMedioPago;
	}

	public MonedaEnum getMonedaImporteAUtilizar() {
		return monedaImporteAUtilizar;
	}

	public void setMonedaImporteAUtilizar(MonedaEnum monedaImporteAUtilizar) {
		this.monedaImporteAUtilizar = monedaImporteAUtilizar;
	}

	/**
	 * @return the provincia
	 */
	public ShvParamProvincia getProvincia() {
		return provincia;
	}

	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(ShvParamProvincia provincia) {
		this.provincia = provincia;
	}

	/**
	 * @return the documentosCap
	 */
	public List<ShvCobEdDocumentoCap> getDocumentosCap() {
		return documentosCap;
	}

	/**
	 * @param documentosCap the documentosCap to set
	 */
	public void setDocumentosCap(List<ShvCobEdDocumentoCap> documentosCap) {
		this.documentosCap = documentosCap;
	}

	/**
	 * @return the subTipo
	 */
	public SubTipoCompensacionEnum getSubTipo() {
		return subTipo;
	}

	/**
	 * @param subTipo the subTipo to set
	 */
	public void setSubTipo(SubTipoCompensacionEnum subTipo) {
		this.subTipo = subTipo;
	}
}