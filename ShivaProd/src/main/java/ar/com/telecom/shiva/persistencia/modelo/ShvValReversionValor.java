package ar.com.telecom.shiva.persistencia.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SHV_VAL_REVERSION_VALOR")
public class ShvValReversionValor extends Modelo{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    private ShvValReversionValorPK shvValReversionValorPK;

	public ShvValReversionValorPK getShvValReversionValorPK() {
		return shvValReversionValorPK;
	}

	public void setShvValReversionValorPK(ShvValReversionValorPK shvValReversionValorPK) {
		this.shvValReversionValorPK = shvValReversionValorPK;
	}

	public ShvValReversionValor (){
		this.shvValReversionValorPK = new ShvValReversionValorPK();
	}
	
}
