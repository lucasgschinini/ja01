package ar.com.telecom.shiva.negocio.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamCalendario;

public class ShvParamCalendarioWrapper implements Serializable {
	private static final long serialVersionUID = 20170421L;

	private ShvParamCalendario shvParamCalendario;
	
	public ShvParamCalendario getShvParamCalendario() {
		return this.shvParamCalendario;
	}
	public ShvParamCalendarioWrapper() {}
	
	public ShvParamCalendarioWrapper(ShvParamCalendario shvParamCalendario) {
		this.shvParamCalendario = shvParamCalendario;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result;
		
		if (shvParamCalendario != null) {
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(shvParamCalendario.getFecha());
			result = result + calendar.get(Calendar.MONTH)+calendar.get(Calendar.MONTH);
		}
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
		ShvParamCalendarioWrapper other = (ShvParamCalendarioWrapper) obj;
		if (shvParamCalendario == null) {
			if (other.shvParamCalendario != null) {
				return false;
			}
		} else {
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(this.shvParamCalendario.getFecha());
			Calendar calendarOther = GregorianCalendar.getInstance();
			calendarOther.setTime(other.shvParamCalendario.getFecha());
			if (
				calendar.get(Calendar.DATE) != calendarOther.get(Calendar.DATE) ||
				calendar.get(Calendar.MONTH) != calendarOther.get(Calendar.MONTH) ||
				calendar.get(Calendar.YEAR) != calendarOther.get(Calendar.YEAR)
			) {
				return false;
			}
		}
		return true;
	}

}
