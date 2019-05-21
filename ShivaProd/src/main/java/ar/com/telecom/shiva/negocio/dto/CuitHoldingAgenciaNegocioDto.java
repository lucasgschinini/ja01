package ar.com.telecom.shiva.negocio.dto;
/**
 * 
 * @author u578936 Max.Uehara
 */
public class CuitHoldingAgenciaNegocioDto {

	private String cuit;
	private String holding;
	private String agenciaNegocios;
	public CuitHoldingAgenciaNegocioDto() {
	}
	
	public CuitHoldingAgenciaNegocioDto(String cuit, String holding, String agenciaNegocios) {
		super();
		this.cuit = cuit;
		this.holding = holding;
		this.agenciaNegocios = agenciaNegocios;
	}

	/**
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}
	/**
	 * @param cuit the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	/**
	 * @return the holding
	 */
	public String getHolding() {
		return holding;
	}
	/**
	 * @param holding the holding to set
	 */
	public void setHolding(String holding) {
		this.holding = holding;
	}
	/**
	 * @return the agenciaNegocios
	 */
	public String getAgenciaNegocios() {
		return agenciaNegocios;
	}
	/**
	 * @param agenciaNegocios the agenciaNegocios to set
	 */
	public void setAgenciaNegocios(String agenciaNegocios) {
		this.agenciaNegocios = agenciaNegocios;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}	
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CuitHoldingAgenciaNegocioDto other = (CuitHoldingAgenciaNegocioDto) obj;
		boolean bHolding = true;
		if (other.getHolding() != null && this.getHolding() != null) {
			bHolding = other.getHolding().equals(this.getHolding());
		} else if (other.getHolding() != null && this.getHolding() == null) {
			bHolding = false;
		} else if (other.getHolding() == null && this.getHolding() == null) {
			bHolding = false;
		} else {
			bHolding = false;
		}
		boolean bCuit = true;
		if (other.getCuit() != null && this.getCuit() != null) {
			bCuit = other.getCuit().equals(this.getCuit());
		} else if (other.getCuit() != null && this.getCuit() == null) {
			bCuit = false;
		} else if (other.getCuit() == null && this.getCuit() == null) {
			bCuit = false;
		} else {
			bCuit = false;
		}
		boolean bAgenciaNegocios = true;
		if (other.getAgenciaNegocios() != null && this.getAgenciaNegocios() != null) {
			bAgenciaNegocios = other.getAgenciaNegocios().equals(this.getAgenciaNegocios());
		} else if (other.getAgenciaNegocios() != null && this.getAgenciaNegocios() == null) {
			bAgenciaNegocios = false;
		} else if (other.getAgenciaNegocios() == null && this.getAgenciaNegocios() == null) {
			bAgenciaNegocios = false;
		} else {
			bAgenciaNegocios = false;
		}
		return bCuit || bHolding || bAgenciaNegocios;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[cuit=" + cuit + ", holding="
			+ holding + ", agenciaNegocios=" + agenciaNegocios + "]";
	}
}
