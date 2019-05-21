package ar.com.telecom.shiva.negocio.simulacionCoherencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import ar.com.telecom.shiva.base.comparador.ComparatorShvCobEdDocumentoCapWrapper;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdDocumentoCap;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdMedioPagoCliente;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdOtrosMedioPago;

public class ShvCobEdOtrosMedioPagoWrapper implements Serializable{
	private static final long serialVersionUID = 3926547729881938020L;
	private ShvCobEdOtrosMedioPago medio = null;
	private String tipo = "";
	private String clientes = "";
	private List<ShvCobEdDocumentoCapWrapper> caps = new ArrayList<ShvCobEdDocumentoCapWrapper>();
	
	public ShvCobEdOtrosMedioPagoWrapper() {
	}

	public ShvCobEdOtrosMedioPagoWrapper(ShvCobEdOtrosMedioPago medio) throws NegocioExcepcion {
		super();
		try {
			this.medio = medio;
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage());
		}
		this.medio.setPk(null);
		this.tipo = this.medio.getTipoMedioPago().getIdTipoMedioPago();
		this.medio.setTipoMedioPago(null);
		List<String> lista = new ArrayList<String>();
		for (ShvCobEdMedioPagoCliente cliente : this.medio.getListaMedioPagoCliente()) {
			lista.add(cliente.getIdClienteLegado().trim());
		}
		Collections.sort(lista);
		medio.setListaMedioPagoCliente(null);
		this.clientes = StringUtils.join(lista, '-');
		Traza.auditoria(getClass(), "["+this.clientes+"]");
		if (!Validaciones.isObjectNull(medio.getDocumentosCap())) {
			try {
				for (ShvCobEdDocumentoCap documento : medio.getDocumentosCap()) {
					ShvCobEdDocumentoCapWrapper cap = new ShvCobEdDocumentoCapWrapper(
						(ShvCobEdDocumentoCap) Utilidad.clonarObjeto(documento),
						documento.getIdCapReferencia()
					);
					this.caps.add(cap);
				}
			} catch (Exception e) {
				throw new NegocioExcepcion(e.getMessage(), e, "Error al general wrapper previo al chech sum");
			}
			Collections.sort(this.caps, new ComparatorShvCobEdDocumentoCapWrapper());
		}
		medio.setDocumentosCap(null);
		
	}

	/**
	 * @return the medio
	 */
	public ShvCobEdOtrosMedioPago getMedio() {
		return medio;
	}

	/**
	 * @param medio the medio to set
	 */
	public void setMedio(ShvCobEdOtrosMedioPago medio) {
		this.medio = medio;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @return the clientes
	 */
	public String getClientes() {
		return clientes;
	}
}
