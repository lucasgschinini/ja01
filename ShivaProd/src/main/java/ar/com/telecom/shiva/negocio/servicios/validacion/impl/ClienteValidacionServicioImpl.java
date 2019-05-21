package ar.com.telecom.shiva.negocio.servicios.validacion.impl;

import ar.com.telecom.shiva.base.enumeradores.CriterioBusquedaClienteEnum;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.validacion.IClienteValidacionServicio;
import ar.com.telecom.shiva.presentacion.bean.filtro.ClienteFiltro;

public class ClienteValidacionServicioImpl implements IClienteValidacionServicio {
	@Override
	public void validarFiltroClientes(ClienteFiltro confCobroClienteFiltro) throws ValidacionExcepcion {

		if (Validaciones.isNullOrEmpty(confCobroClienteFiltro.getCriterio())) {
			throw new ValidacionExcepcion("","error.obligatorio", "#errorSelectCriterio");
		}

		this.validacionBusquedaCliente(confCobroClienteFiltro.getBusqueda());

		if (CriterioBusquedaClienteEnum.BUSQUEDA_POR_AGENCIA_NEGOCIO.getNombre().equals(confCobroClienteFiltro.getCriterio())) {
			this.validacionNumeroMax11Digitos(confCobroClienteFiltro.getBusqueda());
		}
		if (CriterioBusquedaClienteEnum.BUSQUEDA_POR_CLIENTE_LEGADO.getNombre().equals(confCobroClienteFiltro.getCriterio())) {
			this.validacionNumeroMax11Digitos(confCobroClienteFiltro.getBusqueda());
		}
		if (CriterioBusquedaClienteEnum.BUSQUEDA_POR_CUIT.getNombre().equals(confCobroClienteFiltro.getCriterio())) {
			this.validacionCuit(confCobroClienteFiltro.getBusqueda());
		}
		if (CriterioBusquedaClienteEnum.BUSQUEDA_POR_HOLDING.getNombre().equals(confCobroClienteFiltro.getCriterio())) {
			this.validacionNumeroMax11Digitos(confCobroClienteFiltro.getBusqueda()); 
		}
	}
	/**
	 * 
	 * @param campo
	 * @throws ValidacionExcepcion
	 */
	private void validacionBusquedaCliente(String campo) throws ValidacionExcepcion {
		if (Validaciones.isNullOrEmpty(campo)) {
			throw new ValidacionExcepcion("","conf.cobro.mensaje.siebel.campo.obligatorio");
		}
	}

	/**
	 * 
	 * @param campo
	 * @throws ValidacionExcepcion
	 */
	private void validacionNumeroMax11Digitos(String campo) throws ValidacionExcepcion {
		int cantMaxDigitos = 11;
		if (!(Validaciones.isNumeric(campo) &&  campo.length() <= cantMaxDigitos)) {
			throw new ValidacionExcepcion("","error.numerico.max.11.digitos");
		}
	}

	/**
	 * 
	 * @param campo
	 * @throws ValidacionExcepcion
	 */
	private void validacionCuit(String campo) throws ValidacionExcepcion {
//		if (!Validaciones.esFormatoCuit(campo)) {
//			throw new ValidacionExcepcion("","error.cuit.formato");
//		}
//		if (!Validaciones.validarDosPrimerodDigitosCuit(campo)) {
//			throw new ValidacionExcepcion("","error.cuit.primeros.digitos");
//		}
//		campo = campo.replaceAll("-", "");
//		if (!Validaciones.validarCuit(campo.toCharArray())) {
//			throw new ValidacionExcepcion("","error.cuit.digito.verificador");
//		}	
		//U562163 - Fusion - Paquete 01
		Validaciones.validarCuit(campo);
	}
}
