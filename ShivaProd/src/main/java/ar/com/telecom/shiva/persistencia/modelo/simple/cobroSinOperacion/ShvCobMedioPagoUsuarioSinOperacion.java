package ar.com.telecom.shiva.persistencia.modelo.simple.cobroSinOperacion;

import java.util.HashSet;
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
public class ShvCobMedioPagoUsuarioSinOperacion extends ShvCobMedioPagoSinOperacion {
	
	private static final long serialVersionUID = 1L;
	
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy="medioPagoUsuario")
	private Set<ShvCobMedioPagoClienteSinOperacion> listaMedioPagoClientes = new HashSet<ShvCobMedioPagoClienteSinOperacion>();

	/**
	 * @return the listaMedioPagoClientes
	 */
	public Set<ShvCobMedioPagoClienteSinOperacion> getListaMedioPagoClientes() {
		return listaMedioPagoClientes;
	}

	/**
	 * @param listaMedioPagoClientes the listaMedioPagoClientes to set
	 */
	public void setListaMedioPagoClientes(
			Set<ShvCobMedioPagoClienteSinOperacion> listaMedioPagoClientes) {
		this.listaMedioPagoClientes = listaMedioPagoClientes;
	}
}
