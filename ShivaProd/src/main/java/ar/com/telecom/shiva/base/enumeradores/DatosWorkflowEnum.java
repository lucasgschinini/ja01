package ar.com.telecom.shiva.base.enumeradores;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.utils.Utilidad;


public enum DatosWorkflowEnum {

	TIMESTAMP(Constantes.TIME_STAMP),

	// Generales
	EMPRESA("Empresa"),
	SEGMENTO("Segmento"),
	CODCLIENTE("Código Cliente Legado"),
	CODCLIENTESIEBEL(""),
	RAZONSOCIAL("Razón Social Cliente Legado"),
	CODCLIENTEPERFIL("Código Cliente Perfil"),
	RAZONSOCIALCLIENTEPERFIL("Razón Social Cliente Perfil"),
	NUMEROHOLDING("Número de Holding"),
	NOMBREHOLDING("Nombre de Holding"),
	NOMBREANALISTA("Analista"),
	COPROPIETARIO("Copropietario"),
	IDCOPROPIETARIO("Copropietario"),
	IMPORTE("Importe"),
	ACUERDO("Acuerdo"),
	BANCODEPOSITO("Banco Depósito"),
	NROCUENTA ("Nro Cuenta"),
	OBSERVACIONES("Observaciones"),
	FECHADEPOSITO("Fecha Depósito"),
	RECIBOPREIMPRESO("Recibo Pre Impreso"),
	FECHAINGRESORECIBO("Fecha Ingreso Recibo"),
	BANCOORIGEN("Banco Origen"),
	FECHAEMISION("Fecha Emisión"),
	
	//Boleta
	EMAIL("E-mail"),
	MOTIVO("Motivo"),
	OPERACIONASOCIADA("Operación Asociada"),
	ORIGEN("Origen"),
	OBSERVACIONMAIL("Observaciones E-mail"),
	
	//Talonario
	IDEMPRESA("Id Empresa"),
	IDSEGMENTO("Id Segmento"),
	RANGODESDE("Rango Desde"),
	RANGOHASTA("Rango Hasta"),
	USUARIOGESTOR("Usuario Gestor"),
	NROSERIE("Número Serie"),
	
	//Valor
	NUMERODOCUMENTOCONTABLE("Nro Documento Contable"),
	DOCUMENTACIONORIGINALRECIBIDA("Documentación Original Recibida"),
	OBSERVACIONCONFIRMACION("Observación Aviso Pago"),
	NROCHEQUEAREEMPLAZAR("Nro. de Cheque a Reemplazar"),
	
	//Deposito
	IDRECINSTRUMENTO("IdRecInstrumento"),
	REND("Rend"),
	FECHAPAGO("Fecha Pago"),
	NROCLIENTE("Nro Cliente"),
	FORMAPAGO("Forma Pago"),
	ESTADOACREDITACION("Estado Acreditación"),
	FECHAACREDITACION("Fecha Acreditación"),
	//NROBOLETA("Nro Boleta"),
	NUMEROBOLETA("Nro Boleta"),
	SUCURSALDEPOSITO("Sucursal Depósito"),
	NOMBRESUCURSAL("Nombre Sucursal"),
	GRUPOACREEDOR("Grupo Acreedor"),
	NOMBRECLIENTE("Nombre Cliente"),
	CODIGORECHAZO("Código Rechazo"),
	CODIGOBANCO("Código Banco"),
	SUCURSAL("Sucursal"),
	CODIGOPOSTAL("Código Postal"),
	NUMEROCHEQUE("Número Cheque"),
	CUENTAEMISORA("Cuenta Emisora"),
	FECHAVENCIMIENTO("Fecha Vencimiento"),
	FECHAVALOR("Fecha Valor"),
	
	//Transferencia
	FECHAINGRESO("Fecha Ingreso"),
	REFERENCIA("Referencia"),
	CODIGO("Código"),
	OBSERVACION("Observación"),
	CUIT("CUIT"),
	SUBTOTAL("Subtotal"),
	FECHATRANSFERENCIA("Fecha Transferencia"),
	NUMEROREFERENCIA("Nro. de Referencia"),
	
	//Interdeposito
	CODIGOCLIENTE("Código Cliente"),
	CONCEPTO("Concepto"),
	CODIGOOPERACION("Código Operación"),
	DEPOSITO("Depósito"),
	COMPROBANTE("Comprobante"),
	CODIGOORGANISMO("Código Organismo"),
	CODIGOINTERDEPOSITO("Código Interdeposito"),
	CODOPBANCO("Cod. Op. Banco"),
	PCC("PCC"),
	
	//Retencion
	TIPOIMPUESTO("Tipo de Impuesto"),
	PROVINCIA("Provincia"),
	CUITIBB("CUIT"),
	TIPOCOMPROBANTE("Tipo de Comprobante"),
	LETRACOMPROBANTE("Letra de Comprobante"),
	NUMEROLEGALCOMPROBANTE("Número Legal de Comprobante"),
	NROCONSTANCIA("Nro. Constancia"),
	
	FECHANOTIFICACIONDISPONIBILIDADRETIROVALOR("Fecha de Notificación de Disponibilidad de Retiro del Valor");
	
	String descripcion;
	
	private DatosWorkflowEnum(String descripcion) {
	    this.descripcion = descripcion;
	}
	
	public String descripcion() {
	    return this.descripcion;
	}
	
	/**
	 * Recibe un String, busca el enum con ese nombre y devuelve la descripcion.
	 * Si no encuentra enum separa el String parametro por las mayusculas.
	 * @param name
	 * @return
	 */
	public static String getDescripcionByName(String name){
		DatosWorkflowEnum wfEnum = getEnumByName(name);
		if (wfEnum != null){
			return wfEnum.descripcion();
		} else {
			return Utilidad.separarPalabrasPorMayus(name);
		}
	}
	
	/**
	 * Recibe un String, busca el enum con ese nombre y devuelve un enum.
	 * @param name
	 * @return
	 */
	public static DatosWorkflowEnum getEnumByName(String name) {
		
		for (DatosWorkflowEnum campo : DatosWorkflowEnum.values()) {
			if (campo.name().equalsIgnoreCase(name)) {
				return campo;
			}
		}
		        
		return null; 
	}
	
}
