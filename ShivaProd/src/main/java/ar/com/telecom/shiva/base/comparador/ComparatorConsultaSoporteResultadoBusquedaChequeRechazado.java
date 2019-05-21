package ar.com.telecom.shiva.base.comparador;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

import ar.com.telecom.shiva.negocio.servicios.bean.Bean;
import ar.com.telecom.shiva.negocio.servicios.bean.ConsultaSoporteResultadoBusquedaChequeRechazado;

public class ComparatorConsultaSoporteResultadoBusquedaChequeRechazado implements Comparator<Bean> {

	public int compare(Bean b01, Bean b02) {
		ConsultaSoporteResultadoBusquedaChequeRechazado c01 = (ConsultaSoporteResultadoBusquedaChequeRechazado) b01;
		ConsultaSoporteResultadoBusquedaChequeRechazado c02 = (ConsultaSoporteResultadoBusquedaChequeRechazado) b02;
		
		// TOD u578936 despues lo voy dejar mas presentable
		Date fecha01 = null;
		Date fecha02 = null;

		fecha01 = c01.getFechaDeposito();
		fecha02 = c02.getFechaDeposito();

 
		if (fecha01 == null && fecha02 != null) {
			return -1;
		} else if (fecha01 != null && fecha02 == null) {
			return 1;
		} else if (fecha01 == null && fecha02 == null) {
			return 0;
		}
		Calendar c1 = GregorianCalendar.getInstance();
		Calendar c2 = GregorianCalendar.getInstance();
		c1.setTime(fecha01);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.HOUR_OF_DAY, 0);

		c2.setTime(fecha02);
		c2.set(Calendar.SECOND, 0);
		c2.set(Calendar.MILLISECOND, 0);
		c2.set(Calendar.MINUTE, 0);
		c2.set(Calendar.HOUR_OF_DAY, 0);

		int salida = c1.compareTo(c2);
		// Como fecha se ordena desendente
		salida = -salida;
		if (salida == 0) {
			salida = c01.getCodBancoOrigen().compareTo(c02.getCodBancoOrigen());
			//salida = -salida;
			if (salida == 0) {
				Long nroCheque1 = Long.parseLong(c01.getNroCheque());
				Long nroCheque2 = Long.parseLong(c02.getNroCheque());
				salida = nroCheque1.compareTo(nroCheque2);
				//salida = -salida;
			}
		}
		return salida;
	} 
}
