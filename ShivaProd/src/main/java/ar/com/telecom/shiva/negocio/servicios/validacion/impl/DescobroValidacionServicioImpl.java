package ar.com.telecom.shiva.negocio.servicios.validacion.impl;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.validacion.IDescobroValidacionServicio;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteDescobroFiltro;

public class DescobroValidacionServicioImpl implements IDescobroValidacionServicio {

	@Override
	public void validarFiltroBusquedaDescobros(
			VistaSoporteDescobroFiltro descobroFiltro)
			throws ValidacionExcepcion {

		List<String> camposError = new ArrayList<String>();
		List<String> codigosLeyenda = new ArrayList<String>();
		
		/*
		 * Validacion Empresa
		 */
		if (Validaciones.isNullOrEmpty(descobroFiltro.getIdEmpresa())) {
			camposError.add("#errorEmpresa");
			codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.busqueda.mensaje.error.empresa.sin.seleccionar"));
		}

		/*
		 * Validacion Id Operacion Cobro
		 */
		if (!Validaciones.isNullOrEmpty(descobroFiltro.getIdOpCobro())){
			if (!Validaciones.isNumeric(descobroFiltro.getIdOpCobro(),1,7)) {
				camposError.add("#errorIdOpCobro");
				codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.numerico"));
			}
		}

		/*
		 * Validacion Id Reversion Cobro
		 */
		if (!Validaciones.isNullOrEmpty(descobroFiltro.getIdOperacionReversion())){
			if (!Validaciones.isNumeric(descobroFiltro.getIdOperacionReversion(),1,7)) {
				camposError.add("#errorIdReversionCobro");
				codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.numerico"));
			}
		}
		/*
		 * Validaciones Fecha Desde y Fecha Hasta
		 */
		if (!Validaciones.isNullOrEmpty(descobroFiltro.getFechaDesde())) {
			if (!Validaciones.validarFecha(descobroFiltro.getFechaDesde())) {
				camposError.add("#errorFechaDesde");
				codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.fechaIncorrecta"));
			}
		}
		
		if (!Validaciones.isNullOrEmpty(descobroFiltro.getFechaHasta())) {
			if (!Validaciones.validarFecha(descobroFiltro.getFechaHasta())) {
				camposError.add("#errorFechaHasta");
				codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.fechaIncorrecta"));
				//throw new ValidacionExcepcion("", "error.fechaIncorrecta", "#errorFechaHasta");
			}
		}
		
		if (!Validaciones.isNullOrEmpty(descobroFiltro.getFechaDesde())
				&& !Validaciones.isNullOrEmpty(descobroFiltro.getFechaHasta())) {

			if (Validaciones.validarFecha(descobroFiltro.getFechaDesde())
					&& Validaciones.validarFecha(descobroFiltro.getFechaHasta())) {

				if (!Validaciones
						.validarFechaDesdeHasta(descobroFiltro.getFechaDesde(), descobroFiltro.getFechaHasta())) {
					camposError.add("#errorFechaDesde");
					codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.fechaDesdeHastaIncorrecto"));
				}
			}
		}
		/*
		 * Validacion Numero documento
		 * Discrimina de Izq a Der
		 */
		if (!Validaciones.isNullOrEmpty(descobroFiltro.getNroDocumento())){
			if (!Validaciones.validarNroDocumentoDebitos(descobroFiltro.getNroDocumento())){
				camposError.add("#errorNroDocumento");
				codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.busqueda.mensaje.error.formato.numero.documento.debitos"));
				//throw new ValidacionExcepcion("","cobro.busqueda.mensaje.error.formato.numero.documento.debitos", "#errorNroDocumento");
			}
			
			if (!Validaciones.isNullOrEmpty(descobroFiltro.getNroRef())){
				if (!Validaciones.isNumeric(descobroFiltro.getNroRef())) {
					camposError.add("#errorNroRefMic");
					codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.numerico"));
					//throw new ValidacionExcepcion("","error.numerico", "#errorNroRefMic");
				}else {
					camposError.add("#errorNroRefMic");
					codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.busqueda.mensaje.error.campo.unico.cliente"));
				}
		}
		/*
		 * Validar Nro de Ref Mic
		 */
		}else if (!Validaciones.isNullOrEmpty(descobroFiltro.getNroRef())){
				if (!Validaciones.isNullOrNumeric(descobroFiltro.getNroRef())) {
				camposError.add("#errorNroRefMic");
				codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.numerico"));
				//throw new ValidacionExcepcion("","error.numerico", "#errorNroRefMic");
			}
		}
		/*
		 * Validar Referencia de MP
		 */
		if (!Validaciones.isNullOrEmpty(descobroFiltro.getRefMP())){
			if(Validaciones.isNullOrEmpty(descobroFiltro.getIdTipoMedioPago())){
				camposError.add("#errorIdTipoMedioPago");
				codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.obligatorio"));
				//throw new ValidacionExcepcion("","error.obligatorio", "#errorIdTipoMedioPago");
			} else {
				// Validaciones:
				// Formato Nro legal Creditos (CTA/NC Mic o Calipso)
				if (descobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.NOTACREDITO.getValor()) 
						|| descobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.PAGOACUENTA.getValor())){
					//
					if (!Validaciones.validarNroDocumentoCreditosCalipso(descobroFiltro.getRefMP())
							&& !Validaciones.validarNroDocumentoMIC_1_creditos(descobroFiltro.getRefMP())
							&& !Validaciones.validarNroDocumentoMIC_2_creditos(descobroFiltro.getRefMP())
							&& !Validaciones.isNumeric(descobroFiltro.getRefMP(), 15, 15)
							){
						if (descobroFiltro.getIdTipoMedioPago().equals(TipoCreditoEnum.NOTACREDITO.getValor())){
							camposError.add("#errorRefMP");
							codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("descobro.busqueda.mensaje.error.formato.numero.documento.creditos"));
						} else {
							camposError.add("#errorRefMP");
							codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("cobro.busqueda.mensaje.error.formato.numero.documento.creditos"));
						}
					}
				}
				// Formato Numérico Max 8
				if (descobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.INTERDEPOSITO.getValor()) // Formato Nro de interdepósito
						|| descobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.CHEQUE.getValor()) // Formato Cheque/ChequeDiferido
						|| descobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.CHEQUEDIF.getValor())
						|| descobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.BOLETA_DEPOSITO_CHEQUEDIF.getValor())
						|| descobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.BOLETA_DEPOSITO_CHEQUE.getValor())){ // Formato Cheque/ChequeDiferido
					if (!Validaciones.isNumeric(descobroFiltro.getRefMP(), 1, 8)){ 
						camposError.add("#errorRefMP");
						codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.numerico"));
					}
				}
				// Formato Numérico Max 16
				if (descobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.TRANSFERENCIA.getValor())
						|| descobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.IMPUESTO_MUNICIPAL_SEGURIDAD_E_HIGIENGE.getValor())
						|| descobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.IMPUESTO_AL_SELLO.getValor())// Formato Nro de transferencia
						|| descobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.RETENCION.getValor())){ // Formato Nro de Constancia (Retención)
					if (!Validaciones.isNumeric(descobroFiltro.getRefMP(), 1, 16)){ 
						camposError.add("#errorRefMP");
						codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.numerico"));
					}
				}
				// Formato Numérico Max 10
				if (descobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.EFECTIVO.getValor())
						|| descobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.PLANPAGO.getValor())){ // Formato Nro de boleta de depósito efectivo
					if (!Validaciones.isNumeric(descobroFiltro.getRefMP(), 1, 10)){ 
						camposError.add("#errorRefMP");
						codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.numerico"));
					}
				}
				// Formato Numérico Max 25
				if (descobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.OTRAS_COMPENSACIONES.getValor()) // Formato: Nro de Compensación
						|| descobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.CESION_CREDITOS.getValor())
						|| descobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.IIBB.getValor())
						|| descobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.PROVEEDORES.getValor())
						|| descobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.INTERCOMPANY.getValor())
						|| descobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.LIQUIDOPRODUCTO.getValor())
						|| descobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.DESISTIMIENTO.getValor())){ // Formato Nro de Acta 
					if ( !Validaciones.isAlphaNumeric(descobroFiltro.getRefMP()) || descobroFiltro.getRefMP().length() > 25){ 
						camposError.add("#errorRefMP");
						codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.formatoTexto"));
					}
				}
				// Formato Id Remanente
				if (descobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.REMANENTE.getValor())){ // Formato 
					if (!Validaciones.validarIdRemanente(descobroFiltro.getRefMP())){
						camposError.add("#errorRefMP");
						codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.numerico"));
					}
				}
			}
		}
		
		if(!Validaciones.isNullOrEmpty(descobroFiltro.getRefNroTramite())) {
			if(!Validaciones.isNumeric(descobroFiltro.getRefNroTramite())) {
				camposError.add("#errorRefNroTramite");
				codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.numerico"));
			}
		}
		
		if(!Validaciones.isNullOrEmpty(descobroFiltro.getRefNroAcuerdoFact())) {
			if(!Validaciones.isNumeric(descobroFiltro.getRefNroAcuerdoFact())) {
				camposError.add("#errorRefNroAcuerdoFact");
				codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.numerico"));
			}
		}
		
		if (Validaciones.isCollectionNotEmpty(camposError)){
			throw new ValidacionExcepcion(camposError,codigosLeyenda);
		}
	}

}
