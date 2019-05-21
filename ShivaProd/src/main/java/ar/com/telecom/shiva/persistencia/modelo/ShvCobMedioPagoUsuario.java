package ar.com.telecom.shiva.persistencia.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "SHV_COB_MED_PAG_USUARIO")
@PrimaryKeyJoinColumn(name="ID_MEDIO_PAGO")
@Inheritance(strategy=InheritanceType.JOINED)
public class ShvCobMedioPagoUsuario extends ShvCobMedioPago {
	
	private static final long serialVersionUID = 1L;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "medioPagoUsuario")
	private Set<ShvCobMedioPagoCliente> listaMedioPagoClientes = new HashSet<ShvCobMedioPagoCliente>(0);

	/**
	 * @return the listaMedioPagoClientes
	 */
	public Set<ShvCobMedioPagoCliente> getListaMedioPagoClientes() {
		return listaMedioPagoClientes;
	}

	/**
	 * @param listaMedioPagoClientes the listaMedioPagoClientes to set
	 */
	public void setListaMedioPagoClientes(
			Set<ShvCobMedioPagoCliente> listaMedioPagoClientes) {
		this.listaMedioPagoClientes = listaMedioPagoClientes;
	}
	
	/**
	 * 
	 * @return
	 */
	public String obtenerPrimerIdClienteLegado() {
		
		// Ordena la lista de clientes, para siempre devolver el mismo.
		Iterator<ShvCobMedioPagoCliente> iterator = getListaMedioPagoClientes().iterator();
		List<String> clientesOrdenados = new ArrayList<String>();
		while (iterator.hasNext()) {
			clientesOrdenados.add(iterator.next().getIdClienteLegado());
		}
		Collections.sort(clientesOrdenados);
		
		return clientesOrdenados.get(0);
	}
}
