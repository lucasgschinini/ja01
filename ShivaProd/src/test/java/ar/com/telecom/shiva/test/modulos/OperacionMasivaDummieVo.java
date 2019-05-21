package ar.com.telecom.shiva.test.modulos;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoArchivoOperacionMasivaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.registros.datos.entrada.enumeradores.MicOperacionMasivaCamposEntradaEnum;

public class OperacionMasivaDummieVo {
	MicOperacionMasivaCamposEntradaEnum key;
	TipoArchivoOperacionMasivaEnum tipo;
	TipoComprobanteEnum tipoComprobante;
	SiNoEnum estadoConceptoTercerosDeuda;
	String clienteDebito;
	String clienteCredito;
	String clienteAcuerdo;
	String numRefMicFac;
	int comprobante = 12345698;
	boolean marcaReclamoVacia;
	String tipoCreito = "CRE";
	String numRefMicCred;
	SiNoEnum posibilidadDestraferencia;
	
	SiNoEnum errordebito;
	SiNoEnum errorCredito;
	SiNoEnum errorReintegro;
	
	public OperacionMasivaDummieVo() {
	}
}
