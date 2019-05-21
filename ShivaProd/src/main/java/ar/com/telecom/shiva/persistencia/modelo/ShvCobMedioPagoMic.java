package ar.com.telecom.shiva.persistencia.modelo;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "SHV_COB_MED_PAG_MIC")
@PrimaryKeyJoinColumn(name="ID_MEDIO_PAGO")
@Inheritance(strategy=InheritanceType.JOINED)
public class ShvCobMedioPagoMic extends ShvCobMedioPago{
	
	private static final long serialVersionUID = 1L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
