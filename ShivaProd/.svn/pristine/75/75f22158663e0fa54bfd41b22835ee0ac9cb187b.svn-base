package ar.com.telecom.shiva.negocio.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDebitoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroDto;
import ar.com.telecom.shiva.presentacion.bean.dto.CobroOtrosDebitoDto;

public class ImportacionDebitosAuxiliar extends ValidacionesAuxiliar {
	private List<String> listaNroDocumentoYNroRefencia = new ArrayList<String>();
	private Map<String, Integer> listaIdPantalla = new HashMap<String, Integer>();

	private CobroDto cobro;
	private Boolean primerComprobanteEnCobroEsDuc = null;
	private Boolean primerComprobanteEsDuc = null;
	private Boolean otrosDebitos = false;

	public ImportacionDebitosAuxiliar(CobroDto cobro, Boolean vengoDeOtrosDebitos) {
		this.cobro = cobro;
		//Si no vengo de otros debitos hago lo siguiente
		if(!vengoDeOtrosDebitos) {
			Iterator<CobroDebitoDto> iDebitos = cobro.getDebitos().iterator();
			if (iDebitos.hasNext()) {
				if (TipoComprobanteEnum.DUC.equals(iDebitos.next().getTipoComprobanteEnum())) {
					primerComprobanteEnCobroEsDuc = true;
				} else {
					primerComprobanteEnCobroEsDuc = false;
				}
			}
		} else {
			otrosDebitos = true;
		}
		this.setResultadoValidaciones(new StringBuffer());
	}
	/**
	 * @return the listaNroDocumentoYNroRefencia
	 */
	public List<String> getListaNroDocumentoYNroRefencia() {
		return listaNroDocumentoYNroRefencia;
	}
	/**
	 * @param listaNroDocumentoYNroRefencia the listaNroDocumentoYNroRefencia to set
	 */
	public void setListaNroDocumentoYNroRefencia(List<String> listaNroDocumentoYNroRefencia) {
		this.listaNroDocumentoYNroRefencia = listaNroDocumentoYNroRefencia;
	}
	
	public boolean verificarExistenciaNumeroReferenciaMic(String numeroReferenciaMic) {
		for (CobroDebitoDto debito : this.cobro.getDebitos()) {
			if (!Validaciones.isNullOrEmpty(numeroReferenciaMic)){
				if (numeroReferenciaMic.equals(debito.getNumeroReferenciaMic())) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Verifica por id referencia (idPantalla)
	 * @param numeroDocumento
	 * @return
	 */
	public boolean verificarExistenciaNumeroDocumento(String numeroDocumento) {
		if (otrosDebitos) {
			for (CobroOtrosDebitoDto otroDebito : this.cobro.getOtrosDebitos()) {
				if (otroDebito.getIdPantalla().trim().equals(numeroDocumento)) {
					return true;
				}
			}
		} else {
			for (CobroDebitoDto debito : this.cobro.getDebitos()) {
				if (debito.getIdPantalla().trim().equals(numeroDocumento)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public String verificarDuc(String comprobante, int nroRegistro) {
		StringBuffer  str = new StringBuffer();
		if (TipoComprobanteEnum.DUC.name().equals(comprobante)) {
			if (
				Validaciones.isObjectNull(primerComprobanteEnCobroEsDuc) &&
				(
					Validaciones.isObjectNull(cobro.getTratamientoDiferencia()) ||
					(
						!Validaciones.isObjectNull(cobro.getTratamientoDiferencia()) &&
						Validaciones.isObjectNull(cobro.getTratamientoDiferencia().getTipoTratamientoDiferencia())
					)
				)
			) {
				if (nroRegistro > 1) {
					if (!Validaciones.isObjectNull(primerComprobanteEsDuc) && !primerComprobanteEsDuc) {
						str.append(ConstantesCobro.LINEA_NRO);
						str.append(nroRegistro);
						str.append(ConstantesCobro.CAUSA);
						str.append(Propiedades.MENSAJES_PROPIEDADES.getString(ConstantesCobro.ERROR_COBRO_CON_DOCUMENTO_NO_DUC));
						//str.append(Constantes.PUNTO);
						str.append(Constantes.CARRIAGE_RETURN);
					}
				}
			} else {
				if (!Validaciones.isObjectNull(primerComprobanteEnCobroEsDuc) && !primerComprobanteEnCobroEsDuc){
					str.append(ConstantesCobro.LINEA_NRO);
					str.append(nroRegistro);
					str.append(ConstantesCobro.CAUSA);
					str.append(Propiedades.MENSAJES_PROPIEDADES.getString(ConstantesCobro.ERROR_COBRO_CON_DOCUMENTO_NO_DUC));
					//str.append(Constantes.PUNTO);
					str.append(Constantes.CARRIAGE_RETURN);
				}
				if (
					!Validaciones.isObjectNull(cobro.getTratamientoDiferencia()) &&
					!Validaciones.isObjectNull(cobro.getTratamientoDiferencia().getTipoTratamientoDiferencia())
				) {
					str.append(ConstantesCobro.LINEA_NRO);
					str.append(nroRegistro);
					str.append(ConstantesCobro.CAUSA);
					str.append(Propiedades.MENSAJES_PROPIEDADES.getString(ConstantesCobro.ERROR_COBRO_CON_DOCUMENTO_DUC_CON_TRATAMIENTO));
					//str.append(Constantes.PUNTO);
					str.append(Constantes.CARRIAGE_RETURN);
				}
			}
		} else {
			if (Validaciones.isObjectNull(primerComprobanteEnCobroEsDuc)) {
				if (nroRegistro > 1) {
					if (!Validaciones.isObjectNull(primerComprobanteEsDuc) && primerComprobanteEsDuc) {
						str.append(ConstantesCobro.LINEA_NRO);
						str.append(nroRegistro);
						str.append(ConstantesCobro.CAUSA);
						str.append(Propiedades.MENSAJES_PROPIEDADES.getString(ConstantesCobro.ERROR_COBRO_CON_DOCUMENTO_DUC));
						//str.append(Constantes.PUNTO);
						str.append(Constantes.CARRIAGE_RETURN);
					}
				}
			} else {
				if (!Validaciones.isObjectNull(primerComprobanteEnCobroEsDuc) && primerComprobanteEnCobroEsDuc){
					str.append(ConstantesCobro.LINEA_NRO);
					str.append(nroRegistro);
					str.append(ConstantesCobro.CAUSA);
					str.append(Propiedades.MENSAJES_PROPIEDADES.getString(ConstantesCobro.ERROR_COBRO_CON_DOCUMENTO_DUC));
					//str.append(Constantes.PUNTO);
					str.append(Constantes.CARRIAGE_RETURN);
				}
			}
		}
		return str.toString();
	}
	public void setPrimerComprobanteEsDuc(String comprobante) {
		if (Validaciones.isObjectNull(this.primerComprobanteEsDuc)) {
			if (TipoComprobanteEnum.DUC.name().equals(comprobante)) {
				if (
					Validaciones.isObjectNull(primerComprobanteEsDuc) &&
					(
						!Validaciones.isObjectNull(cobro.getTratamientoDiferencia()) &&
						!Validaciones.isObjectNull(cobro.getTratamientoDiferencia().getTipoTratamientoDiferencia())
					)
				) {
					this.primerComprobanteEsDuc = false;
				} else {
					this.primerComprobanteEsDuc = true;
				}
				
			} else {
				this.primerComprobanteEsDuc = false;
			}
		}
	}
	public CobroDto getCobro() {
		return cobro;
	}
	public void setCobro(CobroDto cobro) {
		this.cobro = cobro;
	}
	public Map<String, Integer> getListaIdPantalla() {
		return listaIdPantalla;
	}
	public void setListaIdPantalla(Map<String, Integer> listaIdPantalla) {
		this.listaIdPantalla = listaIdPantalla;
	}
	
}
