package ar.com.telecom.shiva.base.comparador;

import java.util.Comparator;

import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

public class UsuarioLdapDtoComparator implements Comparator<UsuarioLdapDto> {

	public int compare(UsuarioLdapDto dto01, UsuarioLdapDto dto02) {
		int salida = 0;
		if (
			Validaciones.isObjectNull(dto01.getNombreCompleto()) &&
			Validaciones.isObjectNull(dto02.getNombreCompleto())
		) {
			salida = 0;
		} else if (
			Validaciones.isObjectNull(dto01.getNombreCompleto()) &&
			!Validaciones.isObjectNull(dto02.getNombreCompleto())
		) {
			salida = 1;
		} else if (
			Validaciones.isObjectNull(dto01.getNombreCompleto()) &&
			Validaciones.isObjectNull(dto02.getNombreCompleto())
		) {
			salida = -1;
		} else {
			salida = dto01.getNombreCompleto().compareTo(dto02.getNombreCompleto());
		}
		return salida;
	}
}