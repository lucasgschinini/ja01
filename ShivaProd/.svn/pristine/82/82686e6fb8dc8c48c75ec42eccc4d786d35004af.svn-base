package ar.com.telecom.shiva.negocio.servicios.impl;

import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.IProveedorCapServicio;

public class ProveedorCapServicioImpl implements IProveedorCapServicio {

	@Override
	public boolean esProveedorInhabilitado(String codigoBloqueoProveedor) {
		boolean salida = true;

		if (// Segun el XLS de 1,5 es no bloquedo cuando biene BLANCO
			Validaciones.isNullEmptyOrDash(codigoBloqueoProveedor) ||
			" ".equals(codigoBloqueoProveedor)
		) {
			salida = false;
		} else {
			switch (codigoBloqueoProveedor) {
				//B, C, 0, 1, 2, 3, 4, 5 y 6
			// Se podria sacar el Switch dado que todos los casos que sean diferencte a basi
			case "B":
				salida = true;
				break;
			case "C":
				salida = true;
				break;
			case "0":
				salida = true;
				break;	
			case "1":
				salida = true;
				break;
			case "2":
				salida = true;
				break;
			case "3":
				salida = true;
				break;
			case "4":
				salida = true;
				break;
			case "5":
				salida = true;
				break;
			case "6":
				salida = true;
				break;
			case "8":
				salida = true;
				break;	
			case "9":
				salida = true;
				break;	
			default:
				salida = true;
				break;
			}
		}
		return salida;
	}
}
