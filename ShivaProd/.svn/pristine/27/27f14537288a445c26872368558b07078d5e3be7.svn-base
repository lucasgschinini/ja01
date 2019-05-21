package ar.com.telecom.shiva.negocio.servicios.validacion.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.ConstantesCobro;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoDocumentoCapEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.ValidacionExcepcion;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.bean.ImportacionDebitosAuxiliar;
import ar.com.telecom.shiva.negocio.servicios.validacion.ICobroOnlineValidacionServicio;
import ar.com.telecom.shiva.presentacion.bean.dto.json.ImportarDebitoJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.filtro.ConfCobroCreditoFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.ConfCobroDebitoFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.ConfCobroDocCapFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.VistaSoporteCobroOnlineFiltro;

public class CobroOnlineValidacionServicioImpl implements ICobroOnlineValidacionServicio {

	private final int[] lengthCampos = {10,3,3,15,15,3,1,1,13,1};


	@SuppressWarnings("deprecation")
	@Override
	public void validarFiltroDebitos(ConfCobroDebitoFiltro confCobroDebitoFiltro) throws ValidacionExcepcion {

		if (Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getIdsClientes())) {
			throw new ValidacionExcepcion("","conf.cobro.mensaje.error.clientes.sin.seleccionar", "#errorClientesDeb");
		} else {
			String ids[] = confCobroDebitoFiltro.getIdsClientes().split(",");
			boolean todosClienteUsuario = true;
			for (String id : ids) {
				if (id.trim().length() <= 10) {
					todosClienteUsuario = false;
					break;
				}
			}
			if (todosClienteUsuario) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.clientes.tabla.interna", "#errorClientesDeb");
			}
		}

		if (!Validaciones.isNullOrNumeric(confCobroDebitoFiltro.getRefMIC())) {
			throw new ValidacionExcepcion("","error.numerico", "#errorRefMIC");
		}

		if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getNroDoc())) {
			if (!Validaciones.validarNroDocumentoDebitos(confCobroDebitoFiltro.getNroDoc()) && 
					!Validaciones.validarNumeroLegalOtrosDebitos(confCobroDebitoFiltro.getNroDoc()) ) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.nroDoc.validacion", "#errorNroDoc");
			}
		}

		if (!Validaciones.isNullOrNumeric(confCobroDebitoFiltro.getAcuerdoFac())) {
			throw new ValidacionExcepcion("","error.numerico", "#errorAcuerdoFac");
		}

		if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getVencDesde())) {
			if (!Validaciones.validarFecha(confCobroDebitoFiltro.getVencDesde())) {
				throw new ValidacionExcepcion("", "error.fechaIncorrecta", "#errorFechaDesde");
			}
		}

		if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getVencHasta())) {
			if (!Validaciones.validarFecha(confCobroDebitoFiltro.getVencHasta())) {
				throw new ValidacionExcepcion("", "error.fechaIncorrecta", "#errorFechaHasta");
			}
		}

		if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getVencDesde())
				&& !Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getVencHasta())) {

			if (Validaciones.validarFecha(confCobroDebitoFiltro.getVencDesde())
					&& Validaciones.validarFecha(confCobroDebitoFiltro.getVencHasta())) {

				if (!Validaciones
						.validarFechaDesdeHasta(confCobroDebitoFiltro.getVencDesde(), confCobroDebitoFiltro.getVencHasta())) {
					throw new ValidacionExcepcion("","error.fechaDesdeHastaIncorrecto", "#errorFechaDesde");
				}
			}
		}



		//----------------------------------------------------------------------------------
		// Consistencias entre filtros

		// Tipo documento
		if (TipoComprobanteEnum.DUC.name().equalsIgnoreCase(
				confCobroDebitoFiltro.getTipoDoc())) {

			if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getSistema()) 
					&& !SistemaEnum.MIC.getDescripcionCorta().equalsIgnoreCase(confCobroDebitoFiltro.getSistema())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.duc.sistema","#errorSistema");
			}
			if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getRefMIC())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.duc.refMic","#errorRefMIC");
			}
			if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getMoneda()) 
					&& (MonedaEnum.DOL.getSigla().equalsIgnoreCase(confCobroDebitoFiltro.getMoneda()) 
							|| MonedaEnum.TOD.getSigla().equalsIgnoreCase(confCobroDebitoFiltro.getMoneda()))) 
			{
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.duc.moneda","#errorMoneda");
			}
			if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getAcuerdoFac())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.duc.acuerdo","#errorAcuerdoFac");
			}
		}

		// Nro. Documento
		if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getNroDoc())) {
			if (TipoComprobanteEnum.DUC.name().equalsIgnoreCase(confCobroDebitoFiltro.getTipoDoc())) {
				if (!Validaciones.validarNroDocumentoDuc(confCobroDebitoFiltro.getNroDoc())) {
					throw new ValidacionExcepcion("","conf.cobro.mensaje.error.nroDoc.validacion.duc", "#errorNroDoc");
				}
			}
			if (Validaciones.validarNroDocumentoMIC_2_debitos(confCobroDebitoFiltro.getNroDoc())) {
				if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getSistema()) 
						&& SistemaEnum.CALIPSO.getDescripcionCorta().equalsIgnoreCase(confCobroDebitoFiltro.getSistema())) {
					throw new ValidacionExcepcion("","conf.cobro.mensaje.error.nroDoc.sin.letra.calipso","#errorNroDoc");
				}
			}
			if (Validaciones.validarNroDocumentoDuc(confCobroDebitoFiltro.getNroDoc())) {
				if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getRefMIC())) {
					throw new ValidacionExcepcion("","conf.cobro.mensaje.error.nroDoc.sin.letra.refMic","#errorNroDoc");
				}
			}
			// valido combinaciones 
			//(La búsqueda utilizando 'Nro. Documento' solo puede ser combinada con los filtros 'Sistema' y 'Tipo Documento')
			StringBuffer str = new StringBuffer();
			if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getAcuerdoFac())) {
				str.append("#errorAcuerdoFac");
			}
			if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getRefMIC())) {
				if (str.length() > 0) {
					str.append("-");
				}
				str.append("#errorRefMIC");
			}
			if (MonedaEnum.PES.getSigno2().equals(confCobroDebitoFiltro.getMonedaImporteACobrar())) {
				if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getMoneda())) {
					if (str.length() > 0) {
						str.append("-");
					}
					str.append("#errorMoneda");
				}
			}
			if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getVencDesde())) {
				if (str.length() > 0) {
					str.append("-");
				}
				str.append("#errorFechaDesde");
			}
			if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getVencHasta())) {
				if (str.length() > 0) {
					str.append("-");
				}
				str.append("#errorFechaHasta"); 
			}
			if (str.length() > 0) {
				if (MonedaEnum.PES.getSigno2().equals(confCobroDebitoFiltro.getMonedaImporteACobrar())) {
					throw new ValidacionExcepcion("","conf.cobro.mensaje.error.nroDoc.validacion.combinacion.pesos", str.toString());
				} else {
					throw new ValidacionExcepcion("","conf.cobro.mensaje.error.nroDoc.validacion.combinacion.dolar", str.toString());
				}
			}
		}

		// Sistema
		if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getSistema()) 
				&& SistemaEnum.MIC.getDescripcionCorta().equalsIgnoreCase(confCobroDebitoFiltro.getSistema())) {

			if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getNroDoc())) {
				if (Validaciones.validarNroDocumentoDebitosCalipsoLetraE(confCobroDebitoFiltro.getNroDoc())) {
					throw new ValidacionExcepcion("","conf.cobro.mensaje.error.nroDoc.validacion.calipso.E", "#errorNroDoc");
				}
			}
			if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getMoneda()) 
					&& (MonedaEnum.DOL.getSigla().equalsIgnoreCase(confCobroDebitoFiltro.getMoneda()) 
							|| MonedaEnum.TOD.getSigla().equalsIgnoreCase(confCobroDebitoFiltro.getMoneda()))) 
			{
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.mic.moneda","#errorMoneda");
			}
		}
		if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getSistema()) 
				&& SistemaEnum.CALIPSO.getDescripcionCorta().equalsIgnoreCase(confCobroDebitoFiltro.getSistema())) {

			if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getNroDoc())) {
				if (!Validaciones.validarNroDocumentoDebitosCalipso(confCobroDebitoFiltro.getNroDoc())) {
					throw new ValidacionExcepcion("","conf.cobro.mensaje.error.nroDoc.validacion.calipso", "#errorNroDoc");
				}
			}
			if (TipoComprobanteEnum.DUC.name().equalsIgnoreCase(
					confCobroDebitoFiltro.getTipoDoc())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.calipso.tipodoc","#errorTipoDoc");
			}
			if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getRefMIC())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.calipso.refMic","#errorRefMIC");
			}
		}

		// Moneda
		if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getMoneda()) 
				&& (MonedaEnum.DOL.getSigla().equalsIgnoreCase(confCobroDebitoFiltro.getMoneda()) 
						|| MonedaEnum.TOD.getSigla().equalsIgnoreCase(confCobroDebitoFiltro.getMoneda()))) 
		{
			if (TipoComprobanteEnum.DUC.name().equalsIgnoreCase(confCobroDebitoFiltro.getTipoDoc())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.moneda.duc","#errorTipoDoc");
			}
			if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getSistema()) 
					&& !SistemaEnum.CALIPSO.getDescripcionCorta().equalsIgnoreCase(confCobroDebitoFiltro.getSistema())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.moneda.calipso","#errorMoneda");
			}
			if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getRefMIC())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.moneda.refMic","#errorRefMIC");
			}
		}

		// Nro. referencia MIC
		if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getRefMIC())) {
			if (TipoComprobanteEnum.DUC.name().equalsIgnoreCase(confCobroDebitoFiltro.getTipoDoc())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.refMic.tipodoc","#errorTipoDoc");
			}
			if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getSistema()) 
					&& !SistemaEnum.MIC.getDescripcionCorta().equalsIgnoreCase(confCobroDebitoFiltro.getSistema())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.refMic.sistema","#errorSistema");
			}
			if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getMoneda()) 
					&& (MonedaEnum.DOL.getSigla().equalsIgnoreCase(confCobroDebitoFiltro.getMoneda()) 
							|| MonedaEnum.TOD.getSigla().equalsIgnoreCase(confCobroDebitoFiltro.getMoneda()))) 
			{
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.refMic.moneda","#errorMoneda");
			}
			// valido combinaciones 
			//(La búsqueda utilizando 'Nro. Documento' solo puede ser combinada con los filtros 'Sistema' y 'Tipo Documento')
			StringBuffer str = new StringBuffer();
			if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getAcuerdoFac())) {
				if (str.length() > 0) {
					str.append("-");
				}
				str.append("#errorAcuerdoFac");
			}
			if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getNroDoc())) {
				if (str.length() > 0) {
					str.append("-");
				}
				str.append("#errorNroDoc");
			}
			if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getMoneda())) {
				if (str.length() > 0) {
					str.append("-");
				}
				str.append("#errorMoneda");
			}
			if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getVencDesde())) {
				if (str.length() > 0) {
					str.append("-");
				}
				str.append("#errorFechaDesde");
			}
			if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getVencHasta())) {
				if (str.length() > 0) {
					str.append("-");
				}
				str.append("#errorFechaHasta");
			}
			if (str.length() > 0) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.refMic.validacion.combinacion", str.toString());
			}
		}

		// Acuerdo
		if (!Validaciones.isNullOrEmpty(confCobroDebitoFiltro.getAcuerdoFac())) {
			if (TipoComprobanteEnum.DUC.name().equalsIgnoreCase(
					confCobroDebitoFiltro.getTipoDoc())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.acuerdo.duc","#errorTipoDoc");
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void validarFiltroBusquedaCobros (VistaSoporteCobroOnlineFiltro cobroFiltro) throws ValidacionExcepcion {

		if(!Validaciones.isObjectNull(cobroFiltro)){

			List<String> camposError = new ArrayList<String>();
			List<String> codigosLeyenda = new ArrayList<String>();

			/*
			 * Validacion Empresa
			 */
			if (Validaciones.isNullOrEmpty(cobroFiltro.getIdEmpresa())) {
				camposError.add("#errorEmpresa");
				codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("cobro.busqueda.mensaje.error.empresa.sin.seleccionar"));
			}

			/*
			 * Validacion Id Operacion Cobro
			 */
			if (!Validaciones.isNullOrEmpty(cobroFiltro.getIdOpCobro())){
				if (!Validaciones.isNumeric(cobroFiltro.getIdOpCobro(),1,7)) {
					camposError.add("#errorIdOpCobro");
					codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.numerico"));
				}
			}
			/*
			 * Validacion Id Operacion Cobro
			 */
			if (!Validaciones.isNullOrEmpty(cobroFiltro.getIdOperacionMasiva())){
				if (!Validaciones.isNumeric(cobroFiltro.getIdOperacionMasiva(),1,7)) {
					camposError.add("#errorIdOperacionMasiva");
					codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.numerico"));
				}
			}




			/*
			 * Validaciones Fecha Desde y Fecha Hasta
			 */
			if (!Validaciones.isNullOrEmpty(cobroFiltro.getFechaDesde())) {
				if (!Validaciones.validarFecha(cobroFiltro.getFechaDesde())) {
					camposError.add("#errorFechaDesde");
					codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.fechaIncorrecta"));
				}
			}

			if (!Validaciones.isNullOrEmpty(cobroFiltro.getFechaHasta())) {
				if (!Validaciones.validarFecha(cobroFiltro.getFechaHasta())) {
					camposError.add("#errorFechaHasta");
					codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.fechaIncorrecta"));
				}
			}

			if (!Validaciones.isNullOrEmpty(cobroFiltro.getFechaDesde())
					&& !Validaciones.isNullOrEmpty(cobroFiltro.getFechaHasta())) {

				if (Validaciones.validarFecha(cobroFiltro.getFechaDesde())
						&& Validaciones.validarFecha(cobroFiltro.getFechaHasta())) {

					if (!Validaciones
							.validarFechaDesdeHasta(cobroFiltro.getFechaDesde(), cobroFiltro.getFechaHasta())) {
						camposError.add("#errorFechaDesde");
						codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.fechaDesdeHastaIncorrecto"));
					}
				}
			}
			/*
			 * Validacion Numero documento
			 * Discrimina de Izq a Der
			 */
			if (!Validaciones.isNullOrEmpty(cobroFiltro.getNroDocumento())){
				if (!Validaciones.validarNroDocumentoDebitos(cobroFiltro.getNroDocumento()) 
						&& !Validaciones.validarNumeroLegalOtrosDebitos(cobroFiltro.getNroDocumento())){
					camposError.add("#errorNroDocumento");
					codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("cobro.busqueda.mensaje.error.formato.numero.documento.debitos"));
				}

				if (!Validaciones.isNullOrEmpty(cobroFiltro.getNroRef())){
					if (!Validaciones.isNumeric(cobroFiltro.getNroRef())) {
						camposError.add("#errorNroRefMic");
						codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.numerico"));
					}else {
						camposError.add("#errorNroRefMic");
						codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("cobro.busqueda.mensaje.error.campo.unico.cliente"));
					}
				}
				/*
				 * Validar Nro de Ref
				 */
			}else if (!Validaciones.isNullOrEmpty(cobroFiltro.getNroRef())){
				if (!Validaciones.isNullOrNumeric(cobroFiltro.getNroRef())) {
					camposError.add("#errorNroRefMic");
					codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.numerico"));
				}
			}
			/*
			 * Validar Referencia de MP
			 */
			if (!Validaciones.isNullOrEmpty(cobroFiltro.getRefMP())){
				if(Validaciones.isNullOrEmpty(cobroFiltro.getIdTipoMedioPago())){
					camposError.add("#errorIdTipoMedioPago");
					codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.obligatorio"));
				} else {
					// Validaciones:
					// Formato Nro legal Creditos (CTA/NC Mic o Calipso)
					if (cobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.NOTACREDITO.getValor()) 
							|| cobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.PAGOACUENTA.getValor())){
						//
						if (!Validaciones.validarNroDocumentoCreditosCalipso(cobroFiltro.getRefMP())
								&& !Validaciones.validarNroDocumentoMIC_1_creditos(cobroFiltro.getRefMP())
								&& !Validaciones.validarNroDocumentoMIC_2_creditos(cobroFiltro.getRefMP())
								&& !Validaciones.isNumeric(cobroFiltro.getRefMP(), 15, 15)){
							if (cobroFiltro.getIdTipoMedioPago().equals(TipoCreditoEnum.NOTACREDITO.getValor())){
								camposError.add("#errorRefMP");
								codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("cobro.busqueda.mensaje.error.formato.numero.documento.creditos"));
							} else {
								camposError.add("#errorRefMP");
								codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("cobro.busqueda.mensaje.error.formato.numero.documento.creditos"));
							}
						}
					}
					// Formato Numérico Max 8
					if (cobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.INTERDEPOSITO.getValor()) // Formato Nro de interdepósito
							|| cobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.CHEQUE.getValor()) // Formato Cheque/ChequeDiferido
							|| cobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.CHEQUEDIF.getValor())
							|| cobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.BOLETA_DEPOSITO_CHEQUEDIF.getValor())
							|| cobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.BOLETA_DEPOSITO_CHEQUE.getValor())){ // Formato Cheque/ChequeDiferido
						if (!Validaciones.isNumeric(cobroFiltro.getRefMP(), 1, 8)){ 
							camposError.add("#errorRefMP");
							codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.numerico"));
						}
					}
					// Formato Numérico Max 16
					if (cobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.TRANSFERENCIA.getValor())
							|| cobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.IMPUESTO_MUNICIPAL_SEGURIDAD_E_HIGIENGE.getValor())
							|| cobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.IMPUESTO_AL_SELLO.getValor())// Formato Nro de transferencia
							|| cobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.RETENCION.getValor())){ // Formato Nro de Constancia (Retención)
						if (!Validaciones.isNumeric(cobroFiltro.getRefMP(), 1, 16)){ 
							camposError.add("#errorRefMP");
							codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.numerico"));
						}
					}
					// Formato Numérico Max 10
					if (cobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.EFECTIVO.getValor())
							|| cobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.PLANPAGO.getValor())){ // Formato Nro de boleta de depósito efectivo
						if (!Validaciones.isNumeric(cobroFiltro.getRefMP(), 1, 10)){ 
							camposError.add("#errorRefMP");
							codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.numerico"));
						}
					}
					// Formato Numérico Max 25
					if (cobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.OTRAS_COMPENSACIONES.getValor()) // Formato: Nro de Compensación
							|| cobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.CESION_CREDITOS.getValor())
							|| cobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.IIBB.getValor())
							|| cobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.PROVEEDORES.getValor())
							|| cobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.INTERCOMPANY.getValor())
							|| cobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.LIQUIDOPRODUCTO.getValor())
							|| cobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.DESISTIMIENTO.getValor())){ // Formato Nro de Acta 
						if ( !Validaciones.isAlphaNumeric(cobroFiltro.getRefMP()) || cobroFiltro.getRefMP().length() > 25){ 
							camposError.add("#errorRefMP");
							codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.formatoTexto"));
						}
					}
					// Formato Id Remanente
					if (cobroFiltro.getIdTipoMedioPago().contentEquals(TipoCreditoEnum.REMANENTE.getValor())){ // Formato 
						if (!Validaciones.validarIdRemanente(cobroFiltro.getRefMP())){
							camposError.add("#errorRefMP");
							codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.numerico"));
						}
					}
				}
			}
			
			if (!Validaciones.isNullOrEmpty(cobroFiltro.getNroTramite())){
				if (!Validaciones.isNumeric(cobroFiltro.getNroTramite())) {
					camposError.add("#errorNroTramite");
					codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.numerico"));
				}
			}
			
			if (!Validaciones.isNullOrEmpty(cobroFiltro.getNroAcuerdoFact())){
				if (!Validaciones.isNumeric(cobroFiltro.getNroAcuerdoFact())) {
					camposError.add("#errorNroAcuerdoFact");
					codigosLeyenda.add(Propiedades.MENSAJES_PROPIEDADES.getString("error.numerico"));
				}
			}

			if (Validaciones.isCollectionNotEmpty(camposError)){
				throw new ValidacionExcepcion(camposError,codigosLeyenda);
			}
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	public void validarFiltroCreditos(
			ConfCobroCreditoFiltro confCobroCreditoFiltro)
					throws ValidacionExcepcion {

		//Validaciones del filtro
		if (Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getIdsClientes())) {
			throw new ValidacionExcepcion("","conf.cobro.mensaje.error.clientes.sin.seleccionar", "#errorClientesCred");
		}

		if (!Validaciones.isNullOrNumeric(confCobroCreditoFiltro.getAcuerdoFac())) {
			throw new ValidacionExcepcion("","error.numerico", "#errorCredAcuerdo");
		}

		if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getCredDesde())) {
			if (!Validaciones.validarFecha(confCobroCreditoFiltro.getCredDesde())) {
				throw new ValidacionExcepcion("", "error.fechaIncorrecta", "#errorCredDesde");
			}
		}

		if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getCredHasta())) {
			if (!Validaciones.validarFecha(confCobroCreditoFiltro.getCredHasta())) {
				throw new ValidacionExcepcion("", "error.fechaIncorrecta", "#errorCredHasta");
			}
		}

		if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getCredDesde())
				&& !Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getCredHasta())) {

			if (Validaciones.validarFecha(confCobroCreditoFiltro.getCredDesde())
					&& Validaciones.validarFecha(confCobroCreditoFiltro.getCredHasta())) {

				if (!Validaciones
						.validarFechaDesdeHasta(confCobroCreditoFiltro.getCredDesde(), confCobroCreditoFiltro.getCredHasta())) {
					throw new ValidacionExcepcion("","error.fechaDesdeHastaIncorrecto", "#errorCredDesde");
				}
			}
		}

		//----------------------------------------------------------------------------------
		// Consistencias entre filtros

		// Tipo de medio de pago
		if (TipoCreditoEnum.EFECTIVO.getValor().equalsIgnoreCase(
				confCobroCreditoFiltro.getTipoMedioPago())) {

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getSistema()) 
					&& !SistemaEnum.SHIVA.getDescripcionCorta().equalsIgnoreCase(confCobroCreditoFiltro.getSistema())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.tipoMedioPago.sistema.0","#errorCredSistema");
			}

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getMoneda()) 
					&& !MonedaEnum.PES.getSigla().equalsIgnoreCase(confCobroCreditoFiltro.getMoneda())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.tipoMedioPago.moneda.0","#errorCredMoneda");
			}

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getAcuerdoFac())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.tipoMedioPago.acuerdo.0","#errorCredAcuerdo");
			}
		}

		if (TipoCreditoEnum.RETENCION.getValor().equalsIgnoreCase(
				confCobroCreditoFiltro.getTipoMedioPago())) {

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getSistema()) 
					&& !SistemaEnum.SHIVA.getDescripcionCorta().equalsIgnoreCase(confCobroCreditoFiltro.getSistema())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.tipoMedioPago.sistema.1","#errorCredSistema");
			}

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getMoneda()) 
					&& !MonedaEnum.PES.getSigla().equalsIgnoreCase(confCobroCreditoFiltro.getMoneda())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.tipoMedioPago.moneda.1","#errorCredMoneda");
			}

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getAcuerdoFac())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.tipoMedioPago.acuerdo.2","#errorCredAcuerdo");
			}
		}

		if (TipoCreditoEnum.REMANENTE.getValor().equalsIgnoreCase(
				confCobroCreditoFiltro.getTipoMedioPago())) {

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getSistema()) 
					&& !SistemaEnum.MIC.getDescripcionCorta().equalsIgnoreCase(confCobroCreditoFiltro.getSistema())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.tipoMedioPago.sistema.2","#errorCredSistema");
			}

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getMoneda()) 
					&& !MonedaEnum.PES.getSigla().equalsIgnoreCase(confCobroCreditoFiltro.getMoneda())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.tipoMedioPago.moneda.2","#errorCredMoneda");
			}

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getAcuerdoFac())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.tipoMedioPago.acuerdo.3","#errorCredAcuerdo");
			}
		}

		if (TipoCreditoEnum.CHEQUEDIF.getValor().equalsIgnoreCase(
				confCobroCreditoFiltro.getTipoMedioPago())) {

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getSistema()) 
					&& !SistemaEnum.SHIVA.getDescripcionCorta().equalsIgnoreCase(confCobroCreditoFiltro.getSistema())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.tipoMedioPago.sistema.7","#errorCredSistema");
			}

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getMoneda()) 
					&& !MonedaEnum.PES.getSigla().equalsIgnoreCase(confCobroCreditoFiltro.getMoneda())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.tipoMedioPago.moneda.6","#errorCredMoneda");
			}

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getAcuerdoFac())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.tipoMedioPago.acuerdo.1","#errorCredAcuerdo");
			}
		}

		if (TipoCreditoEnum.CHEQUE.getValor().equalsIgnoreCase(
				confCobroCreditoFiltro.getTipoMedioPago())) {

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getSistema()) 
					&& !SistemaEnum.SHIVA.getDescripcionCorta().equalsIgnoreCase(confCobroCreditoFiltro.getSistema())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.tipoMedioPago.sistema.6","#errorCredSistema");
			}

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getMoneda()) 
					&& !MonedaEnum.PES.getSigla().equalsIgnoreCase(confCobroCreditoFiltro.getMoneda())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.tipoMedioPago.moneda.5","#errorCredMoneda");
			}

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getAcuerdoFac())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.tipoMedioPago.acuerdo.7","#errorCredAcuerdo");
			}
		}

		if (TipoCreditoEnum.TRANSFERENCIA.getValor().equalsIgnoreCase(
				confCobroCreditoFiltro.getTipoMedioPago())) {

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getSistema()) 
					&& !SistemaEnum.SHIVA.getDescripcionCorta().equalsIgnoreCase(confCobroCreditoFiltro.getSistema())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.tipoMedioPago.sistema.5","#errorCredSistema");
			}

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getMoneda()) 
					&& !MonedaEnum.PES.getSigla().equalsIgnoreCase(confCobroCreditoFiltro.getMoneda())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.tipoMedioPago.moneda.4","#errorCredMoneda");
			}

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getAcuerdoFac())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.tipoMedioPago.acuerdo.6","#errorCredAcuerdo");
			}
		}

		if (TipoCreditoEnum.INTERDEPOSITO.getValor().equalsIgnoreCase(
				confCobroCreditoFiltro.getTipoMedioPago())) {

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getSistema()) 
					&& !SistemaEnum.SHIVA.getDescripcionCorta().equalsIgnoreCase(confCobroCreditoFiltro.getSistema())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.tipoMedioPago.sistema.4","#errorCredSistema");
			}

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getMoneda()) 
					&& !MonedaEnum.PES.getSigla().equalsIgnoreCase(confCobroCreditoFiltro.getMoneda())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.tipoMedioPago.moneda.3","#errorCredMoneda");
			}

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getAcuerdoFac())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.tipoMedioPago.acuerdo.5","#errorCredAcuerdo");
			}
		}

		if (TipoCreditoEnum.PAGOACUENTA.getValor().equalsIgnoreCase(
				confCobroCreditoFiltro.getTipoMedioPago())) {

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getSistema()) 
					&& !SistemaEnum.CALIPSO.getDescripcionCorta().equalsIgnoreCase(confCobroCreditoFiltro.getSistema())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.tipoMedioPago.sistema.3","#errorCredSistema");
			}

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getAcuerdoFac())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.tipoMedioPago.acuerdo.4","#errorCredAcuerdo");
			}
		}

		if (TipoCreditoEnum.NOTACREDITO.getValor().equalsIgnoreCase(
				confCobroCreditoFiltro.getTipoMedioPago())) {

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getSistema()) 
					&& SistemaEnum.SHIVA.getDescripcionCorta().equalsIgnoreCase(confCobroCreditoFiltro.getSistema())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.tipoMedioPago.sistema.8","#errorCredSistema");
			}

		}

		if (TipoCreditoEnum.NOTACREDITO.getValor().equalsIgnoreCase(
				confCobroCreditoFiltro.getTipoMedioPago())) {

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getSistema()) 
					&& SistemaEnum.SHIVA.getDescripcionCorta().equalsIgnoreCase(confCobroCreditoFiltro.getSistema())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.tipoMedioPago.sistema.8","#errorCredSistema");
			}

		}

		if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getSistema()) 
				&& SistemaEnum.SHIVA.getDescripcionCorta().equalsIgnoreCase(confCobroCreditoFiltro.getSistema())) {

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getAcuerdoFac())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.sistema.acuerdo.0","#errorCredAcuerdo");
			}
		}

		if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getMoneda()) 
				&& MonedaEnum.DOL.getSigla().equalsIgnoreCase(confCobroCreditoFiltro.getMoneda())) {

			if (!Validaciones.isNullOrEmpty(confCobroCreditoFiltro.getSistema()) 
					&& !SistemaEnum.CALIPSO.getDescripcionCorta().equalsIgnoreCase(confCobroCreditoFiltro.getSistema())) {
				throw new ValidacionExcepcion("","conf.cobro.mensaje.error.sistema.moneda.0","#errorCredMoneda");
			}
		}
	}

	/***********************************************************************************
	 * Metodos privados que valida campos
	 **********************************************************************************/

	

	@Override
	public void validarComprobantes(MultipartFile file, String descripcion) throws ValidacionExcepcion, NegocioExcepcion {

		if (!Validaciones.esFormatoTexto(descripcion)) {
			throw new ValidacionExcepcion("", "error.formatoTexto", "#errorDescripcionComp");
		} 
		if (file.getOriginalFilename().length() > Constantes.CANT_MAXIMA_CARACTERES_NOMBRE_ARCHIVO) {
			throw new ValidacionExcepcion("","error.nombreArchivoMuyLargo", "#errorArchComprobante");
		} else {
			try {
				if (ControlArchivo.isMultipartFileEmpty(file)) {
					throw new ValidacionExcepcion("","valor.error.archivoVacio", "#errorArchComprobante");
				} else { 
					if (ControlArchivo.esArchivoProhibido(file.getOriginalFilename())) {
						throw new ValidacionExcepcion("","error.archivoProhibido", "#errorArchComprobante");
					} else { 
						if (ControlArchivo.archivoTieneExtensionProhibida(file.getOriginalFilename())) {
							throw new ValidacionExcepcion("","error.extensionProhibida", "#errorArchComprobante");
						} else { 
							if (ControlArchivo.superaPesoMaximoPermitido(file)) {
								throw new ValidacionExcepcion("","error.pesoMaximoSuperado", "#errorArchComprobante");
							} else if (ControlArchivo.archivoTieneCaracteresNoPermitidos(file.getOriginalFilename())) {
								throw new ValidacionExcepcion("","error.caracteresInvalidos","#errorArchComprobante"); 
							}
						}
					}
				}
			} catch (IOException e) {
				throw new NegocioExcepcion(e);
			}
		}
	}

	/***********************************************************************************
	 * Otros
	 **********************************************************************************/

	/**
	 * @author Guido.Settecerze u572487
	 * @param campos
	 * @param nroRegistro
	 * @return
	 */
	public boolean validarFormato (String[] campos, int nroRegistro, ImportacionDebitosAuxiliar importarDebitosAuxiliar) {
		//		if ((campos.length == 9) || campos.length == getLengthCampos().length){
		if (!Validaciones.isObjectNull(campos) && campos.length == getLengthCampos().length) {
			return false;
		}else{
			importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
			importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
			importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
			importarDebitosAuxiliar.getResultadoValidaciones().append("Deben ser " + getLengthCampos().length + 
					" campos y el registro posee " + campos.length + ".");
			importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
			Traza.error(getClass(), "Cantidad de campos erronea en el registro nro: " + nroRegistro + ", " + 
					"Deben ser " + getLengthCampos().length + 
					" campos y el registro posee " + campos.length + ".");
			return true;
		}
	}

	/**
	 * @author Guido.Settecerze u572487
	 * @param campos
	 * @param nroRegistro
	 * @return
	 */
	public boolean validarDuplicidad (String[] campos, int nroRegistro, ImportacionDebitosAuxiliar importacionDebitosAuxiliar) {

		if(!Validaciones.isNullOrEmpty(campos[ConstantesCobro.REFERENCIA_MIC-1].trim()) &&
				importacionDebitosAuxiliar.getListaNroDocumentoYNroRefencia().contains(campos[ConstantesCobro.REFERENCIA_MIC-1].trim())){
			importacionDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
			importacionDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
			importacionDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
			importacionDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_REGISTRO_DUPLICADO);
			importacionDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
			importacionDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
			return true;
		}else{
			if(!Validaciones.isNullOrEmpty(campos[ConstantesCobro.REFERENCIA_MIC-1].trim()))
				importacionDebitosAuxiliar.getListaNroDocumentoYNroRefencia().add(campos[ConstantesCobro.REFERENCIA_MIC-1].trim());
		}
		if(!Validaciones.isNullOrEmpty(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim()) && !Validaciones.isNullOrEmpty(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim()) && 
				(importacionDebitosAuxiliar.getListaNroDocumentoYNroRefencia().contains(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().concat(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim())))){
			importacionDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
			importacionDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
			importacionDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
			importacionDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_REGISTRO_DUPLICADO);
			importacionDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
			importacionDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
			return true;
		}else{
			if(!Validaciones.isNullOrEmpty(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim()) && !Validaciones.isNullOrEmpty(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim()))
				importacionDebitosAuxiliar.getListaNroDocumentoYNroRefencia().add(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().concat(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim()));
		}
		return false;
	}

	/**
	 * @author Guido.Settecerze u572487
	 * @param campos
	 * @param nroRegistro
	 * @return
	 */
	public boolean validarDuplicidadEnCobro (String[] campos, int nroRegistro, ImportacionDebitosAuxiliar importacionDebitosAuxiliar) {
		boolean  salida = false;
		if (!Validaciones.isNullOrEmpty(campos[ConstantesCobro.REFERENCIA_MIC-1].trim())) {
			salida = importacionDebitosAuxiliar.verificarExistenciaNumeroReferenciaMic(
					campos[ConstantesCobro.REFERENCIA_MIC-1].trim()
					);
		} else if (
				!Validaciones.isNullOrEmpty(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim()) && 
				!Validaciones.isNullOrEmpty(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim())
				) {
			salida = importacionDebitosAuxiliar.verificarExistenciaNumeroDocumento(
					campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().concat("_").concat(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim())
					);
		}
		if (salida) {
			importacionDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
			importacionDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
			importacionDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
			importacionDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_REGISTRO_DUPLICADO_EN_COBRO);
			importacionDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
			importacionDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
		}
		return salida;
	}
	/**
	 * @author Guido.Settecerze u572487
	 * @param campos
	 * @param nroRegistro
	 * @return
	 */
	public boolean validarRegistroDuc(String[] campos, int nroRegistro, ImportacionDebitosAuxiliar importacionDebitosAuxiliar) {
		boolean salida = false;
		String mensajeSalida = Utilidad.EMPTY_STRING;
		String comprobante = Utilidad.EMPTY_STRING;

		if (Validaciones.isNullOrEmpty(campos[ConstantesCobro.REFERENCIA_MIC-1].trim())) {
			if (
					!Validaciones.isNullOrEmpty(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim()) &&
					TipoComprobanteEnum.DUC.name().equals(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim())
					) {
				comprobante = TipoComprobanteEnum.DUC.name();
			}
		}
		mensajeSalida = importacionDebitosAuxiliar.verificarDuc(comprobante, nroRegistro);
		if (!Validaciones.isEmptyString(mensajeSalida)) {
			importacionDebitosAuxiliar.getResultadoValidaciones().append(mensajeSalida);
			salida = true;
		}
		return salida;
	}
	/**
	 * @author Guido.Settecerze u572487
	 * @param campos
	 * @param nroRegistro
	 * @return
	 */
	public boolean validarCamposArchivoImportarDebitos(String[] campos,
			int nroRegistro, ImportacionDebitosAuxiliar importarDebitosAuxiliar) throws NegocioExcepcion {

		boolean hadAnError = false;
		MonedaEnum monedaOperacion = MonedaEnum.getEnumBySigno2(importarDebitosAuxiliar.getCobro().getMonedaOperacion());
		campos[campos.length-1] = campos[campos.length-1].trim().replace(" ", "");
		//		if (((campos.length == 9) && campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-2].trim() =="") || campos.length == getLengthCampos().length){
		if (campos.length == getLengthCampos().length){
			// Cliente
			if (Validaciones.isNullOrEmpty(campos[ConstantesCobro.CLIENTE-1].trim())) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_EL_CAMPO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CLIENTE_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_OBLIGATORIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
			} if (campos[ConstantesCobro.CLIENTE-1].trim().length() > getLengthCampos()[ConstantesCobro.CLIENTE-1]) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_LENGTH_CAMPO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CLIENTE_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LONGITUD_CORRECTA_STRING);
				importarDebitosAuxiliar.getResultadoValidaciones().append(getLengthCampos()[ConstantesCobro.CLIENTE-1]);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.CLIENTE-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
			} if (!Validaciones.isNumeric(campos[ConstantesCobro.CLIENTE-1].trim())) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_TIPO_DE_DATO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CLIENTE_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.FORMATO_NUMERICO_STRING);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.CLIENTE-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
			}

			// Sistema
			boolean errorEnSistema = false;
			if (Validaciones.isNullOrEmpty(campos[ConstantesCobro.SISTEMA-1].trim())) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_EL_CAMPO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.SISTEMA_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_OBLIGATORIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
				errorEnSistema = true;
			}  if (!Validaciones.isAlphaNumeric(campos[ConstantesCobro.SISTEMA-1].trim())) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_TIPO_DE_DATO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.SISTEMA_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.FORMATO_ALPHANUMERICO_STRING);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.SISTEMA-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
				errorEnSistema = true;

			} if (!campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.MIC.getDescripcionCorta()) && !campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.CALIPSO.getDescripcionCorta())) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_TIPO_DE_DATO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.SISTEMA_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES_MIC_CLP);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.SISTEMA-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
				errorEnSistema = true;

			}  if (!errorEnSistema && campos[ConstantesCobro.SISTEMA-1].trim().length() > getLengthCampos()[ConstantesCobro.SISTEMA-1]) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_LENGTH_CAMPO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.SISTEMA_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LONGITUD_CORRECTA_STRING);
				importarDebitosAuxiliar.getResultadoValidaciones().append(getLengthCampos()[ConstantesCobro.SISTEMA-1]);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.SISTEMA-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;

			} if (!errorEnSistema && (campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.DUC.name())) && 
					(!(campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.MIC.getDescripcionCorta())))) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.SISTEMA_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_TIPO_DOCUMENTO_DUC);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.MIC);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.SISTEMA-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;

			} if(!errorEnSistema && campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.CALIPSO.getDescripcionCorta()) && !Validaciones.isNullOrEmpty(campos[ConstantesCobro.REFERENCIA_MIC-1].trim())){
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.SISTEMA_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_REFERENCIA_MIC);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.MIC);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.SISTEMA-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;

			} if (!errorEnSistema && ((campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta())) || 
					(campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim().equalsIgnoreCase(SiNoEnum.NO.getDescripcionCorta()))) && 
					(!(campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.MIC.getDescripcionCorta())))) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.SISTEMA_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				if((campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta()))){
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_COBRAR_2_VENCIMIENTO_S);
				} else {
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_COBRAR_2_VENCIMIENTO_N);
				}
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.MIC);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.SISTEMA-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
			} if (!errorEnSistema && ((campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta())) || 
					(campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim().equalsIgnoreCase(SiNoEnum.NO.getDescripcionCorta()))) && 
					(!campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.MIC.getDescripcionCorta()))){
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.SISTEMA_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				if((campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta()))){
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_DESTRANSFERIR_TERCEROS_S);
				} else {
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_DESTRANSFERIR_TERCEROS_N);
				}
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.MIC);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.SISTEMA-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
			}

			//			else if(campos.length == 10) {
			//				// Implementacion del 2016 04 16 Cobro en dólares SIN diferencia de cambio Calipso
			//				if (!errorEnSistema && (campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta())) && 
			//						(!campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.CALIPSO.getDescripcionCorta()))) {
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.SISTEMA_NOMBRE);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_SIN_DIFERENCIA_DE_CAMBIO_S);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CLP);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.SISTEMA-1].trim());
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
			//					hadAnError = true;
			//				}
			//			}

			if (!errorEnSistema && campos[ConstantesCobro.MONEDA-1].trim().equalsIgnoreCase(MonedaEnum.DOL.getSigno2()) && 
					(!(campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.CALIPSO.getDescripcionCorta())))) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.SISTEMA_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_MONEDA_DOLAR);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CLP);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.SISTEMA-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
			}

			// Tipo Documento
			boolean errorTipoDocumento = false;
			if (campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.CALIPSO.getDescripcionCorta())
					&& Validaciones.isNullOrEmpty(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim()) ||
					(campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.MIC.getDescripcionCorta()) 
							&& Validaciones.isNullOrEmpty(campos[ConstantesCobro.REFERENCIA_MIC-1].trim()) && Validaciones.isNullOrEmpty(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim()))) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_EL_CAMPO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.TIPO_DOCUMENTO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_OBLIGATORIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
				errorTipoDocumento = true;

			} if (campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().length() > getLengthCampos()[ConstantesCobro.TIPO_DOCUMENTO-1]) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_LENGTH_CAMPO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.TIPO_DOCUMENTO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LONGITUD_CORRECTA_STRING);
				importarDebitosAuxiliar.getResultadoValidaciones().append(getLengthCampos()[ConstantesCobro.TIPO_DOCUMENTO-1]);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
				errorTipoDocumento = true;

			} if (!Validaciones.isNullOrEmpty(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim()) && !Validaciones.isAlphaNumeric(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim())) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_TIPO_DE_DATO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.TIPO_DOCUMENTO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.FORMATO_ALPHANUMERICO_STRING);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
				errorTipoDocumento = true;

			} if (!Validaciones.isNullOrEmpty(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim()) && !campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.FAC.name()) && !campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.DEB.name()) && !campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.DUC.name())) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_TIPO_DE_DATO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.TIPO_DOCUMENTO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES_FAC_DEB_DUC);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
				errorTipoDocumento = true;
			}

			//			 if(campos.length == 10){
			//				// Implementacion del 2016 04 16 Cobro en dólares SIN diferencia de cambio Calipso
			//				if (!errorTipoDocumento && !Validaciones.isNullOrEmpty(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim()) && 
			//						(campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta())) && 
			//						((!campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.FAC.name())) && (!campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.DEB.name())))){
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.TIPO_DOCUMENTO_NOMBRE);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_SIN_DIFERENCIA_DE_CAMBIO_S);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.FACDEB);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim());
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
			//					hadAnError = true;
			//				}
			//			}

			if (!errorTipoDocumento && !Validaciones.isNullOrEmpty(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim()) && ((campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta())) || 
					(campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim().equalsIgnoreCase(SiNoEnum.NO.getDescripcionCorta()))) && 
					((!campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.FAC.name())) && (!campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.DEB.name())))){
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.TIPO_DOCUMENTO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				if((campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta()))){
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_DESTRANSFERIR_TERCEROS_S);
				} else {
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_DESTRANSFERIR_TERCEROS_N);
				}
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.FACDEB);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
			}

			if (!errorTipoDocumento && !Validaciones.isNullOrEmpty(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim()) && ((campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta())) || 
					(campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim().equalsIgnoreCase(SiNoEnum.NO.getDescripcionCorta()))) && 
					((!campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.FAC.name()) && !campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.DEB.name())))){
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.TIPO_DOCUMENTO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				if((campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta()))){
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_COBRAR_2_VENCIMIENTO_S);
				} else {
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_COBRAR_2_VENCIMIENTO_N);
				}
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.FACDEB);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
			}

			if (!errorTipoDocumento && !Validaciones.isNullOrEmpty(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim()) && 
					((!campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.FAC.name()) && 
							!campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.DEB.name()))) && 
							(campos[ConstantesCobro.MONEDA-1].trim().equalsIgnoreCase(MonedaEnum.DOL.getSigno2()))){
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.TIPO_DOCUMENTO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_MONEDA_DOLAR);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.FACDEB);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
			}

			if (!errorTipoDocumento && !Validaciones.isNullOrEmpty(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim()) && 
					(!campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.FAC.name()) && 
							!campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.DEB.name()) && 
							!campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.DUC.name())) && 
							(campos[ConstantesCobro.MONEDA-1].trim().equalsIgnoreCase(MonedaEnum.PES.getSigno2()))){
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.TIPO_DOCUMENTO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_MONEDA_PESO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.FACDEBDUC);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
			}

			if (!errorTipoDocumento && !Validaciones.isNullOrEmpty(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim()) && ((!campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.FAC.name()) && !campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.DEB.name()))) && 
					(!campos[ConstantesCobro.REFERENCIA_MIC-1].trim().equalsIgnoreCase(""))){
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.TIPO_DOCUMENTO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_REFERENCIA_MIC);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.FACDEB);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
			}
			if (!errorTipoDocumento && !Validaciones.isNullOrEmpty(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim()) && 
					((!campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.FAC.name()) && 
							!campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.DEB.name()))) && 
							(campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.CALIPSO.getDescripcionCorta()))){
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.TIPO_DOCUMENTO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_SISTEMA_CLP);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.FACDEB);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
			}

			//Numero de Documento
			if (campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.CALIPSO.getDescripcionCorta()) 
					&& Validaciones.isNullOrEmpty(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim())) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_EL_CAMPO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.NRO_DOCUMENTO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_OBLIGATORIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_SISTEMA_CLP);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
			}

			if (campos[ConstantesCobro.MONEDA-1].trim().equalsIgnoreCase(MonedaEnum.DOL.getSigno2())
					&& Validaciones.isNullOrEmpty(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim())) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_EL_CAMPO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.NRO_DOCUMENTO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_OBLIGATORIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_MONEDA_DOLAR);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
			}

			//			if(campos.length == 10){
			if (campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta()) 
					&& Validaciones.isNullOrEmpty(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim())) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_EL_CAMPO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.NRO_DOCUMENTO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_OBLIGATORIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_SIN_DIFERENCIA_DE_CAMBIO_S);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
			}
			//			}
			if ((campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.MIC.getDescripcionCorta()) && Validaciones.isNullOrEmpty(campos[ConstantesCobro.REFERENCIA_MIC-1].trim()) && Validaciones.isNullOrEmpty(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim()))) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_EL_CAMPO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.NRO_DOCUMENTO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_OBLIGATORIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
			}

			if(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.DUC.name())
					&& Validaciones.isNullOrEmpty(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim())){
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_EL_CAMPO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.NRO_DOCUMENTO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_TIPO_DOCUMENTO_DUC);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_OBLIGATORIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
			}else if(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.DUC.name()) && 
					campos[ConstantesCobro.NRO_DOCUMENTO-1].trim().length() != ConstantesCobro.cantidadCaracteresTipoDocuConDUC){
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_LENGTH_CAMPO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.NRO_DOCUMENTO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LONGITUD_CORRECTA_STRING);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.cantidadCaracteresTipoDocuConDUC);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
			} 
			//aca valido numero de documento si es DUC
			else if(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.DUC.name())){
				String[] split = campos[ConstantesCobro.NRO_DOCUMENTO-1].trim().split("-");
				String[] numerosSucursalEnSplit2 = split[0].trim().split("",3);
				if(split.length != 2){
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_EL_CAMPO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.NRO_DOCUMENTO_NOMBRE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_FORMATO_NRO_DOCUMENTO_DUC);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim());
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
					hadAnError = true;
				}

				else if(!Validaciones.isNumeric(numerosSucursalEnSplit2[2].trim())){
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_TIPO_DE_DATO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.NRO_DOCUMENTO_NOMBRE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.FORMATO_SUCURSAL2_ALPHANUMERICO_STRING);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim());
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
					hadAnError = true;
				}

				else if (split[0].trim().length() != ConstantesCobro.cantidadCaracteresSucursal){
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_LENGTH_CAMPO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.NRO_DOCUMENTO_NOMBRE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LONGITUD_CORRECTA_SUCURSAL_STRING);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.cantidadCaracteresSucursal);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim());
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
					hadAnError = true;
				}
				else if (!Validaciones.isAlphaNumeric(split[0].trim())){
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_TIPO_DE_DATO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.NRO_DOCUMENTO_NOMBRE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.FORMATO_SUCURSAL_ALPHANUMERICO_STRING);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim());
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
					hadAnError = true;
				}
				else if (!split[0].trim().toUpperCase().startsWith(ConstantesCobro.letraInicialSucursal)){
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_SUCURSAL_CON_U);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim());
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
					hadAnError = true;
				}

				else if (split[1].trim().length() != ConstantesCobro.cantidadCaracteresComprobante) {
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_LENGTH_CAMPO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.COMPROBANTE_NOMBRE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LONGITUD_CORRECTA_STRING);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.cantidadCaracteresComprobante);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim());
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
					hadAnError = true;
				} else if (!Validaciones.isNumeric(split[1].trim())) {
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_TIPO_DE_DATO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.NRO_DOCUMENTO_NOMBRE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.FORMATO_COMPROBANTE_NUMERICO_STRING);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim());
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
					hadAnError = true;
				}
				//aca valido numero de documento si no es DUC
			} if (campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.FAC.name()) || 
					campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.DEB.name())) {
				String[] split = campos[ConstantesCobro.NRO_DOCUMENTO-1].trim().split("-");
				boolean flagEvitoValidacionesInecesarias = false;
				boolean flagSinLetraYconGuion = false;
				boolean flagSinLetra = false;
				boolean flagConLetra = false;
				if(split.length == 2 || split[0].trim().isEmpty()){
					flagSinLetra = true;
				}
				if(split.length == 3){
					flagConLetra = true;
				}
				if(!Validaciones.isNullOrEmpty(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim()) && split.length != 3 && split.length != 2){
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_EL_CAMPO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.NRO_DOCUMENTO_NOMBRE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_FORMATO_NRO_DOCUMENTO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim());
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
					hadAnError = true;
					flagEvitoValidacionesInecesarias = true;
				}
				if(flagSinLetra){
					if(!Validaciones.isNullOrEmpty(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim()) && 
							campos[ConstantesCobro.NRO_DOCUMENTO-1].trim().startsWith("-")){
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
						importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_FALTA_LETRA);
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
						importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim());
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
						flagSinLetraYconGuion = true;
						hadAnError = true;
					}
					//Sucursal
					if (!Validaciones.isNullOrEmpty(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim()) &&
							!flagSinLetraYconGuion && split[0].trim().length() != ConstantesCobro.cantidadCaracteresSucursal){
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
						importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_LENGTH_CAMPO);
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.NRO_DOCUMENTO_NOMBRE);
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LONGITUD_CORRECTA_SUCURSAL_STRING);
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.cantidadCaracteresSucursal);
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
						importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim());
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
						hadAnError = true;
					}
					if (!Validaciones.isNullOrEmpty(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim()) &&
							!flagSinLetraYconGuion && !Validaciones.isNumeric(split[0].trim()) &&
							(!split[0].trim().startsWith("D") && !campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.DEB.name()))){
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
						importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_TIPO_DE_DATO);
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.NRO_DOCUMENTO_NOMBRE);
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.FORMATO_SUCURSAL_NUMERICO_STRING);
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
						importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim());
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
						hadAnError = true;
					}

					//Comprobante
					if (!Validaciones.isNullOrEmpty(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim()) &&
							!flagSinLetraYconGuion && split[1].trim().length() != ConstantesCobro.cantidadCaracteresComprobante) {
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
						importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_LENGTH_CAMPO);
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.COMPROBANTE_NOMBRE);
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LONGITUD_CORRECTA_STRING);
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.cantidadCaracteresComprobante);
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
						importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim());
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
						hadAnError = true;
					} 
					if (!Validaciones.isNullOrEmpty(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim()) &&
							!Validaciones.isNumeric(split[1].trim())) {
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
						importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_TIPO_DE_DATO);
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.NRO_DOCUMENTO_NOMBRE);
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.FORMATO_COMPROBANTE_NUMERICO_STRING);
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
						importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim());
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
						importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
						hadAnError = true;
					}
				}else if(flagConLetra && !Validaciones.isNullOrEmpty(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim())){
					if(!flagEvitoValidacionesInecesarias){
						// Letra
						if (split[0].trim().length() > ConstantesCobro.logitudEsperadaLETRA) {
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
							importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_LENGTH_CAMPO);
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.NRO_DOCUMENTO_NOMBRE);
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LONGITUD_CORRECTA_LETRA_STRING);
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.logitudEsperadaLETRA);
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
							importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim());
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
							hadAnError = true;
						} if(!campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.MIC.getDescripcionCorta()) && (split[0].trim()=="" || split[0].trim()==" ")){
							if (!Validaciones.isAlphaNumeric(split[0].trim())) {
								importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
								importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
								importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
								importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_TIPO_DE_DATO);
								importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
								importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.NRO_DOCUMENTO_NOMBRE);
								importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
								importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
								importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.FORMATO_LETRA_ALPHANUMERICO_STRING);
								importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
								importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim());
								importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
								importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
								importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
								hadAnError = true;
							}
						} if(campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.MIC.getDescripcionCorta())){
							if (!split[0].trim().equalsIgnoreCase("A") && !split[0].trim().equalsIgnoreCase("B") && !split[0].trim().equalsIgnoreCase(" ") && !split[0].trim().equalsIgnoreCase("")) {
								importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
								importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
								importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
								importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_TIPO_DE_DATO);
								importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
								importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.NRO_DOCUMENTO_NOMBRE);
								importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
								importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLE_LETRA_A_B_VACIO);
								importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
								importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim());
								importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
								importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
								importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
								hadAnError = true;
							}
						} if(campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.CALIPSO.getDescripcionCorta())){
							if (!split[0].trim().equalsIgnoreCase("A") && !split[0].trim().equalsIgnoreCase("B") && !split[0].trim().equalsIgnoreCase("E")) {
								importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
								importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
								importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
								importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_TIPO_DE_DATO);
								importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
								importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.NRO_DOCUMENTO_NOMBRE);
								importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
								importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLE_LETRA_A_B_E);
								importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
								importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim());
								importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
								importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
								importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
								hadAnError = true;
							}
						} if(!campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.MIC.getDescripcionCorta())){
							if (Validaciones.isNullOrEmpty(split[0].trim())) {
								importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
								importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
								importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
								importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_EL_CAMPO);
								importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LETRA_NOMBRE);
								importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_OBLIGATORIO);
								importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
								importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
								hadAnError = true;
							}
						}

						//Sucursal
						if (split[1].trim().length() != ConstantesCobro.cantidadCaracteresSucursal){
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
							importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_LENGTH_CAMPO);
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.NRO_DOCUMENTO_NOMBRE);
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LONGITUD_CORRECTA_SUCURSAL_STRING);
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.cantidadCaracteresSucursal);
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
							importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim());
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
							hadAnError = true;
						}
						if (!Validaciones.isNumeric(split[1].trim()) &&
								(!split[1].trim().startsWith("D") && !campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.DEB.name()))){
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
							importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_TIPO_DE_DATO);
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.NRO_DOCUMENTO_NOMBRE);
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.FORMATO_SUCURSAL_NUMERICO_STRING);
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
							importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim());
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
							hadAnError = true;
						}

						//Comprobante
						if (split[2].trim().length() != ConstantesCobro.cantidadCaracteresComprobante) {
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
							importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_LENGTH_CAMPO);
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.COMPROBANTE_NOMBRE);
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LONGITUD_CORRECTA_STRING);
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.cantidadCaracteresComprobante);
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
							importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim());
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
							hadAnError = true;
						} if (!Validaciones.isNumeric(split[2].trim())) {
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
							importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_TIPO_DE_DATO);
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.NRO_DOCUMENTO_NOMBRE);
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
							importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.FORMATO_COMPROBANTE_NUMERICO_STRING);
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
							importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim());
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
							importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
							hadAnError = true;
						}
					}
				}
			}

			//Referencia MIC
			if (campos[ConstantesCobro.REFERENCIA_MIC-1].trim().length() > getLengthCampos()[ConstantesCobro.REFERENCIA_MIC-1]) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_LENGTH_CAMPO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.REFERENCIA_MIC_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LONGITUD_CORRECTA_STRING);
				importarDebitosAuxiliar.getResultadoValidaciones().append(getLengthCampos()[ConstantesCobro.REFERENCIA_MIC-1]);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.REFERENCIA_MIC-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;

			}

			if(!(Validaciones.isNullOrEmpty(campos[ConstantesCobro.REFERENCIA_MIC-1].trim())) && !(campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.CALIPSO.getDescripcionCorta()))){
				if (!Validaciones.isNumeric(campos[ConstantesCobro.REFERENCIA_MIC-1].trim())) {
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_TIPO_DE_DATO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.REFERENCIA_MIC_NOMBRE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.FORMATO_NUMERICO_STRING);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.REFERENCIA_MIC-1].trim());
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
					hadAnError = true;
				}

				if(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.DUC.name()) && 
						!campos[ConstantesCobro.REFERENCIA_MIC-1].trim().equalsIgnoreCase("")){
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.REFERENCIA_MIC_NOMBRE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_TIPO_DOCUMENTO_DUC);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.VACIO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.REFERENCIA_MIC-1].trim());
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
					hadAnError = true;

				}
			} if(campos[ConstantesCobro.MONEDA-1].trim().equalsIgnoreCase(MonedaEnum.DOL.getSigno2()) && 
					(!(campos[ConstantesCobro.REFERENCIA_MIC-1].trim().equalsIgnoreCase("")) && 
							!(campos[ConstantesCobro.REFERENCIA_MIC-1].trim().equalsIgnoreCase(" ")))){ //revisar
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.REFERENCIA_MIC_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_MONEDA_DOLAR);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.VACIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.REFERENCIA_MIC-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;

			} if((campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.CALIPSO.getDescripcionCorta())) && 
					(!(campos[ConstantesCobro.REFERENCIA_MIC-1].trim().equalsIgnoreCase("")) && 
							!(campos[ConstantesCobro.REFERENCIA_MIC-1].trim().equalsIgnoreCase(" ")))){
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.REFERENCIA_MIC_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_SISTEMA_CLP);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.VACIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.REFERENCIA_MIC-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;

			}  //if(campos.length == 10){
			// Implementacion del 2016 04 16 Cobro en dólares SIN diferencia de cambio Calipso
			//				if ((campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta())) && 
			//						(!campos[ConstantesCobro.REFERENCIA_MIC-1].trim().equalsIgnoreCase(""))){
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.REFERENCIA_MIC_NOMBRE);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_SIN_DIFERENCIA_DE_CAMBIO_S);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.VACIO);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.REFERENCIA_MIC-1].trim());
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
			//					hadAnError = true;
			//				}
			//			}

			if(campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.MIC.getDescripcionCorta()) && ((Validaciones.isNullOrEmpty(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim())) && (Validaciones.isNullOrEmpty(campos[ConstantesCobro.NRO_DOCUMENTO-1].trim())))){
				if (Validaciones.isNullOrEmpty(campos[ConstantesCobro.REFERENCIA_MIC-1].trim())) {
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_EL_CAMPO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.REFERENCIA_MIC_NOMBRE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_OBLIGATORIO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
					hadAnError = true;
				}
			}

			// Moneda
			boolean errorMoneda = false;
			if (Validaciones.isNullOrEmpty(campos[ConstantesCobro.MONEDA-1].trim())) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_EL_CAMPO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.MONEDA_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_OBLIGATORIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
				errorMoneda = true;

			}  if (!campos[ConstantesCobro.MONEDA-1].trim().equalsIgnoreCase(MonedaEnum.PES.getSigno2()) && !campos[ConstantesCobro.MONEDA-1].trim().equalsIgnoreCase(MonedaEnum.DOL.getSigno2())) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_TIPO_DE_DATO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.MONEDA_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES_$_U$S);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.MONEDA-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
				errorMoneda = true;

			}  if (!errorMoneda && campos[ConstantesCobro.MONEDA-1].trim().length() > getLengthCampos()[ConstantesCobro.MONEDA-1]) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_LENGTH_CAMPO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.MONEDA_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LONGITUD_CORRECTA_STRING);
				importarDebitosAuxiliar.getResultadoValidaciones().append(getLengthCampos()[ConstantesCobro.MONEDA-1]);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.MONEDA-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;

			} 

			//Si la moneda del cobro es diferente de pesos, verifico que la
			if (!MonedaEnum.PES.equals(monedaOperacion)){

				if (!errorMoneda && !campos[ConstantesCobro.MONEDA-1].trim().equalsIgnoreCase(monedaOperacion.getSigno2())){
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_TIPO_DE_DATO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.MONEDA_NOMBRE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_MONEDA_OPERACION.replace("1",campos[ConstantesCobro.MONEDA-1].trim()));
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(monedaOperacion.getSigno2());
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
					hadAnError = true;
					errorMoneda = true;
				}
			}


			if (!errorMoneda && !campos[ConstantesCobro.MONEDA-1].trim().equalsIgnoreCase(MonedaEnum.PES.getSigno2()) && 
					(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.DUC.name()))){
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.MONEDA_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_TIPO_DOCUMENTO_DUC);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.PESO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.MONEDA-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;

			}   if (!errorMoneda && (!campos[ConstantesCobro.MONEDA-1].trim().equalsIgnoreCase(MonedaEnum.PES.getSigno2())) && 
					(!campos[ConstantesCobro.REFERENCIA_MIC-1].trim().equalsIgnoreCase(""))){
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.MONEDA_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_REFERENCIA_MIC);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.PESO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.MONEDA-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;

			} if (!errorMoneda && ((campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta())) || 
					(campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim().equalsIgnoreCase(SiNoEnum.NO.getDescripcionCorta()))) && 
					(!campos[ConstantesCobro.MONEDA-1].trim().equalsIgnoreCase(MonedaEnum.PES.getSigno2()))){
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.MONEDA_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				if((campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta()))){
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_COBRAR_2_VENCIMIENTO_S);
				} else {
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_COBRAR_2_VENCIMIENTO_N);
				}
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.PESO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.MONEDA-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
			}  

			//			 if(campos.length == 10){
			//				if (!errorMoneda && (campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta())) && 
			//						(!campos[ConstantesCobro.MONEDA-1].trim().equalsIgnoreCase(MonedaEnum.DOL.getSigno2()))){
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.MONEDA_NOMBRE);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_SIN_DIFERENCIA_DE_CAMBIO_S);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.DOLAR);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.MONEDA-1].trim());
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
			//					hadAnError = true;
			//				}
			//				
			//				if (!errorTipoDocumento && !errorEnSistema && !errorMoneda && campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim().isEmpty() && 
			//						(!campos[ConstantesCobro.MONEDA-1].trim().equalsIgnoreCase(MonedaEnum.PES.getSigno2()))){
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.MONEDA_NOMBRE);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_SIN_DIFERENCIA_DE_CAMBIO_VACIO);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.PESO);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.MONEDA-1].trim());
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
			//					hadAnError = true;
			//				}
			//			}

			if (!errorMoneda && ((campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta())) || 
					(campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim().equalsIgnoreCase(SiNoEnum.NO.getDescripcionCorta()))) && 
					(!campos[ConstantesCobro.MONEDA-1].trim().equalsIgnoreCase(MonedaEnum.PES.getSigno2()))){
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.MONEDA_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				if((campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta()))){
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_DESTRANSFERIR_TERCEROS_S);
				} else {
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_DESTRANSFERIR_TERCEROS_N);
				}
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.PESO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.MONEDA-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
			}

			if (!errorMoneda && (!campos[ConstantesCobro.MONEDA-1].trim().equalsIgnoreCase(MonedaEnum.PES.getSigno2())) && 
					(campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.MIC.getDescripcionCorta()))){
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.MONEDA_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_SISTEMA_MIC);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.PESO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.MONEDA-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
			}

			//Cobrar 2° Vencimiento
			boolean errorTipoDeDatoCobrar2Venc = false;
			if (campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.MIC.getDescripcionCorta()) && Validaciones.isNullOrEmpty(campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim()) &&
					(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.FAC.name()) || campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.DEB.name()))) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_EL_CAMPO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.COBRAR_2_VENCIMIENTO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_OBLIGATORIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
				errorTipoDeDatoCobrar2Venc = true;
			}

			if (!Validaciones.isNullOrEmpty(campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim()) && !Validaciones.isAlphaNumeric(campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim())) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_TIPO_DE_DATO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.COBRAR_2_VENCIMIENTO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.FORMATO_ALPHANUMERICO_STRING);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
				errorTipoDeDatoCobrar2Venc = true;

			} if (!campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim().equalsIgnoreCase("") &&
					!campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta()) && 
					!campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim().equalsIgnoreCase(SiNoEnum.NO.getDescripcionCorta())) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_TIPO_DE_DATO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.COBRAR_2_VENCIMIENTO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES_S_N_VACIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
				errorTipoDeDatoCobrar2Venc = true;
			}

			if (campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim().length() > getLengthCampos()[ConstantesCobro.COBRAR_2_VENCIMIENTO-1]) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_LENGTH_CAMPO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.COBRAR_2_VENCIMIENTO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LONGITUD_CORRECTA_STRING);
				importarDebitosAuxiliar.getResultadoValidaciones().append(getLengthCampos()[ConstantesCobro.COBRAR_2_VENCIMIENTO-1]);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
				errorTipoDeDatoCobrar2Venc = true;

			}  /*if ((!errorTipoDeDatoCobrar2Venc) && ((campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.CALIPSO.getDescripcionCorta())) && 
					(!(campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim().equalsIgnoreCase("")) && 
							!(campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim().equalsIgnoreCase(" "))))) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.COBRAR_2_VENCIMIENTO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_SISTEMA_CLP);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.VACIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;

			} if (!errorTipoDeDatoCobrar2Venc && campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta()) && 
					(!(campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim().equalsIgnoreCase("")) && 
							!(campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim().equalsIgnoreCase(" ")))) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.COBRAR_2_VENCIMIENTO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_SIN_DIFERENCIA_DE_CAMBIO_S);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.VACIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;

			}
			 */
			if ((!errorTipoDeDatoCobrar2Venc) && ((campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.DUC.name())) && 
					(!(campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim().equalsIgnoreCase("")) && 
							!(campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim().equalsIgnoreCase(" "))))) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.COBRAR_2_VENCIMIENTO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_TIPO_DOCUMENTO_DUC);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.VACIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;

			} if ((!errorTipoDeDatoCobrar2Venc) && (campos[ConstantesCobro.MONEDA-1].trim().equalsIgnoreCase(MonedaEnum.DOL.getSigno2()) &&  
					(!(campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim().equalsIgnoreCase("")) && 
							!(campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim().equalsIgnoreCase(" "))))) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.COBRAR_2_VENCIMIENTO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_MONEDA_DOLAR);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.VACIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;

			}

			//Destransferir Terceros
			boolean errorDestransTerceros = false;
			if (campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.MIC.getDescripcionCorta()) && Validaciones.isNullOrEmpty(campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim()) &&
					(campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.FAC.name()) || campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.DEB.name()))) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_EL_CAMPO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.DESTRANSFERIR_TERCEROS_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_OBLIGATORIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
				errorDestransTerceros = true;

			} if (campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim().length() > getLengthCampos()[ConstantesCobro.DESTRANSFERIR_TERCEROS-1]) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_LENGTH_CAMPO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.DESTRANSFERIR_TERCEROS_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LONGITUD_CORRECTA_STRING);
				importarDebitosAuxiliar.getResultadoValidaciones().append(getLengthCampos()[ConstantesCobro.DESTRANSFERIR_TERCEROS-1]);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
				errorDestransTerceros = true;

			} if (!campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim().equalsIgnoreCase("") && !Validaciones.isAlphaNumeric(campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim())) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_TIPO_DE_DATO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.DESTRANSFERIR_TERCEROS_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.FORMATO_ALPHANUMERICO_STRING);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
				errorDestransTerceros = true;

			} if (!campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim().equalsIgnoreCase("") &&
					!campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta()) && 
					!campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim().equalsIgnoreCase(SiNoEnum.NO.getDescripcionCorta())) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_TIPO_DE_DATO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.DESTRANSFERIR_TERCEROS_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES_S_N_VACIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
				errorDestransTerceros = true;

			} if (!errorDestransTerceros && (campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.DUC.name())) && 
					(!(campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim().equalsIgnoreCase("")) && 
							!(campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim().equalsIgnoreCase(" ")))) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.DESTRANSFERIR_TERCEROS_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_TIPO_DOCUMENTO_DUC);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.VACIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;

			} if (!errorDestransTerceros && campos[ConstantesCobro.MONEDA-1].trim().equalsIgnoreCase(MonedaEnum.DOL.getSigno2()) && 
					(!(campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim().equalsIgnoreCase("")) && 
							!(campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim().equalsIgnoreCase(" ")))) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.DESTRANSFERIR_TERCEROS_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_MONEDA_DOLAR);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.VACIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;

			} if (!errorDestransTerceros && (campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.CALIPSO.getDescripcionCorta())) && 
					(!(campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim().equalsIgnoreCase("")) && 
							!(campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim().equalsIgnoreCase(" ")))) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.DESTRANSFERIR_TERCEROS_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_SISTEMA_CLP);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.VACIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;

			} //if(campos.length == 10){
			//				if (!errorDestransTerceros && (campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta())) && 
			//						(!campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim().equalsIgnoreCase(""))){
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.DESTRANSFERIR_TERCEROS_NOMBRE);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_SIN_DIFERENCIA_DE_CAMBIO_S);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.VACIO);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim());
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
			//					hadAnError = true;
			//				}
			//			}
			//Importe a Cobrar
			if (Validaciones.isNullOrEmpty(campos[ConstantesCobro.IMPORTE_A_COBRAR-1].trim())) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_EL_CAMPO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.IMPORTE_A_COBRAR_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_OBLIGATORIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
			} if (!Validaciones.isImporteFormatoValor(campos[ConstantesCobro.IMPORTE_A_COBRAR-1].trim())) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_EL_CAMPO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.IMPORTE_A_COBRAR_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_NRO_DOS_DIGITOS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.IMPORTE_A_COBRAR-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
			}

			//------------------------------Sin diferencia de cambio
			boolean errorSinDifCambio = false;

			if (campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim().length() > getLengthCampos()[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1]) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_LENGTH_CAMPO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LONGITUD_CORRECTA_STRING);
				importarDebitosAuxiliar.getResultadoValidaciones().append(getLengthCampos()[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1]);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
				errorSinDifCambio = true;

			}
			if (!campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim().isEmpty() && !Validaciones.isAlphaNumeric(campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim())) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_TIPO_DE_DATO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.FORMATO_ALPHANUMERICO_STRING);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
				errorSinDifCambio = true;

				// Implementacion del 2016 04 16 Cobro en dólares SIN diferencia de cambio = S Calipso
			} 
//			if (!campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim().equalsIgnoreCase("") && 
//					!campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta())) {
//
//				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
//				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
//				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
//				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_TIPO_DE_DATO);
//				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
//				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO_NOMBRE);
//				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
//				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
//				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES_S_VACIO);
//				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
//				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim());
//				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
//				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
//				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
//				hadAnError = true;
//				errorSinDifCambio = true;
//
//			}

			
			if ((!importarDebitosAuxiliar.getCobro().getIdMotivoCobro().equals(ConstantesCobro.ID_MOTIVO_COBRO_COMPENSACION))
					&&  (!campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim().equalsIgnoreCase(""))) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.VACIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
				errorSinDifCambio = true;
				
			}

			if (importarDebitosAuxiliar.getCobro().getIdMotivoCobro().equals(ConstantesCobro.ID_MOTIVO_COBRO_COMPENSACION) ) {
				if(campos[ConstantesCobro.MONEDA-1].trim().equalsIgnoreCase(MonedaEnum.PES.getSigno2())
						&& (!(campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim().equalsIgnoreCase("")))) {	
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO_NOMBRE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_MONEDA_PESO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.VACIO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim());
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
					hadAnError = true;
					errorSinDifCambio = true;
				}
				if(campos[ConstantesCobro.MONEDA-1].trim().equalsIgnoreCase(MonedaEnum.DOL.getSigno2())
						&& (!campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim().equalsIgnoreCase(SiNoEnum.NO.getDescripcionCorta()) &&
								!campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta()))) {
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO_NOMBRE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_MONEDA_DOLAR);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.DOS_PUNTOS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES_S_N);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim());
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
					hadAnError = true;
					errorSinDifCambio = true;
				}
			}
			
			if (campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.CALIPSO.getDescripcionCorta()) &&
					Validaciones.isNullOrEmpty(campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim())
					&& !importarDebitosAuxiliar.getCobro().getMonedaOperacion().trim().equalsIgnoreCase(campos[ConstantesCobro.MONEDA-1].trim()) 
					&& (importarDebitosAuxiliar.getCobro().getIdMotivoCobro().equals(ConstantesCobro.ID_MOTIVO_COBRO_COMPENSACION))) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_EL_CAMPO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_OBLIGATORIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
				errorSinDifCambio = true;
			}

			if (!errorSinDifCambio && (campos[ConstantesCobro.TIPO_DOCUMENTO-1].trim().equalsIgnoreCase(TipoComprobanteEnum.DUC.name())) && 
					(!(campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim().equalsIgnoreCase("")) && 
							!(campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim().equalsIgnoreCase(" ")))
					) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_TIPO_DOCUMENTO_DUC);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.VACIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
				errorSinDifCambio = true;

			}  if (!errorSinDifCambio && !campos[ConstantesCobro.REFERENCIA_MIC-1].trim().equalsIgnoreCase("") && 
					!campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim().equalsIgnoreCase("")) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_REFERENCIA_MIC);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.VACIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;
				errorSinDifCambio = true;

			} 
			/*				if (!errorSinDifCambio && ((campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta())) || 
						(campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim().equalsIgnoreCase(SiNoEnum.NO.getDescripcionCorta()))) && 
						(!(campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim().equalsIgnoreCase("")) && 
								!(campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim().equalsIgnoreCase(" ")))) {
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO_NOMBRE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
					if((campos[ConstantesCobro.COBRAR_2_VENCIMIENTO-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta()))){
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_COBRAR_2_VENCIMIENTO_S);
					} else {
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_COBRAR_2_VENCIMIENTO_N);
					}
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.VACIO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim());
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
					hadAnError = true;

				} 

if (!errorSinDifCambio && ((campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta())) || 
						(campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim().equalsIgnoreCase(SiNoEnum.NO.getDescripcionCorta()))) && 
						(!campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim().equalsIgnoreCase(""))){
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO_NOMBRE);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
					if((campos[ConstantesCobro.DESTRANSFERIR_TERCEROS-1].trim().equalsIgnoreCase(SiNoEnum.SI.getDescripcionCorta()))){
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_DESTRANSFERIR_TERCEROS_S);
					} else {
						importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_DESTRANSFERIR_TERCEROS_N);
					}
					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.VACIO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim());
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
					hadAnError = true;

				}
			 */
			if (!errorSinDifCambio && (campos[ConstantesCobro.SISTEMA-1].trim().equalsIgnoreCase(SistemaEnum.MIC.getDescripcionCorta())) && 
					(!(campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim().equalsIgnoreCase("")) && 
							!(campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim().equalsIgnoreCase(" ")))) {
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO_NOMBRE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_SISTEMA_MIC);
				importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.VACIO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim());
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
				importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
				hadAnError = true;

			}  
			//				if (!errorSinDifCambio && campos[ConstantesCobro.MONEDA-1].trim().equalsIgnoreCase(MonedaEnum.PES.getSigno2()) &&
			//						(!(campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim().equalsIgnoreCase("")) && 
			//								!(campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim().equalsIgnoreCase(" ")))) {
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_VALORES_POSIBLES);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO_NOMBRE);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.COMMA);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CON_MONEDA_PESO);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.VACIO);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.WHITESPACE_AND_OPEN_PARENTESIS);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(campos[ConstantesCobro.SIN_DIFERENCIA_DE_CAMBIO-1].trim());
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CLOSE_PARENTESIS);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.PUNTO);
			//					importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
			//					hadAnError = true;
			//
			//				}



		} else {
			hadAnError = true;
			importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.LINEA_NRO);
			importarDebitosAuxiliar.getResultadoValidaciones().append(nroRegistro);
			importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.CAUSA);
			importarDebitosAuxiliar.getResultadoValidaciones().append("Deben ser " + getLengthCampos().length + 
					" campos y el registro posee " + campos.length+".");
			importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
			Traza.error(getClass(), "Cantidad de campos erronea en el registro nro: " + nroRegistro + ", " + 
					"Deben ser " + getLengthCampos().length + 
					" campos y el registro posee " + campos.length+".");
		}

		return hadAnError;
	}


	@Override
	public StringBuffer validarRestriccionesArchivo(MultipartFile file) throws NegocioExcepcion {

		StringBuffer resultadoValidaciones = new StringBuffer();
		//Validaciones de archivo.
		resultadoValidaciones.delete(0, resultadoValidaciones.length());
		if (file.getOriginalFilename().length() > Constantes.CANT_MAXIMA_CARACTERES_NOMBRE_ARCHIVO) {
			resultadoValidaciones.append(Propiedades.MENSAJES_PROPIEDADES.getString("error.nombreArchivoMuyLargo"));					
		} else {
			try {
				String fileExtension = ControlArchivo.getFileExtension(file.getOriginalFilename());
				if (ControlArchivo.isMultipartFileEmpty(file)) {
					resultadoValidaciones.append(Propiedades.MENSAJES_PROPIEDADES.getString("valor.error.archivoVacio"));
				} else { 
					//if (ControlArchivo.esArchivoProhibido(file.getOriginalFilename())) {
					//	resultadoValidaciones.append("error.archivoProhibido");
					//} else { 
					if (!Validaciones.esNombreArchivoDebitos(file.getOriginalFilename())) {
						resultadoValidaciones.append(Propiedades.MENSAJES_PROPIEDADES.getString("error.ImportarDebitos.formatArchivo"));
					} else { 
						if (ControlArchivo.archivoTieneExtensionProhibida(file.getOriginalFilename())) {
							resultadoValidaciones.append(Propiedades.MENSAJES_PROPIEDADES.getString("error.extensionProhibida"));
						} else { 
							if (ControlArchivo.superaPesoMaximoPermitido(file)) {
								resultadoValidaciones.append(Propiedades.MENSAJES_PROPIEDADES.getString("error.pesoMaximoSuperado"));
							} else { 
								if (!fileExtension.equals("txt")) {
									resultadoValidaciones.append(Propiedades.MENSAJES_PROPIEDADES.getString("error.extenciontxtEsperada"));
							}else if (ControlArchivo.archivoTieneCaracteresNoPermitidos(file.getOriginalFilename())) {
								resultadoValidaciones.append( "error.caracteresInvalidos");
								}
							}
						}
					}
					//}
				}
			} catch (IOException e) {
				throw new NegocioExcepcion(e);
			}
		}
		return resultadoValidaciones;
	}

	public ImportarDebitoJsonResponse setErrorsImportarDebitoDto(ImportarDebitoJsonResponse importarDebitoDto, ImportacionDebitosAuxiliar importacionDebitosAuxiliar) {
		Traza.error(getClass(), importacionDebitosAuxiliar.getResultadoValidaciones().toString());
		importarDebitoDto.setSuccess(false);
		importarDebitoDto.setErrors(importacionDebitosAuxiliar.getResultadoValidaciones().toString());
		return importarDebitoDto;
	}	

	public boolean validacionCantDebitosMaximos(int nroRegistroAux, String cantDebitosGrilla, ImportacionDebitosAuxiliar importarDebitosAuxiliar) {
		Long cantDebitosEnGrilla = Long.valueOf(cantDebitosGrilla);
		boolean aux = false;
		if(nroRegistroAux > ConstantesCobro.cantMaxRegistrosImportarDebitos){
			importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_CANT_MAX_REGISTROS);
			importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
			aux = true;
		}else if((nroRegistroAux + cantDebitosEnGrilla) > ConstantesCobro.cantMaxRegistrosImportarDebitos){
			importarDebitosAuxiliar.getResultadoValidaciones().append(ConstantesCobro.ERROR_CANT_MAX_REGISTROS_MUCHOS_ARCH);
			importarDebitosAuxiliar.getResultadoValidaciones().append(Constantes.CARRIAGE_RETURN);
			aux = true;
		}
		return aux;
	}
	public int[] getLengthCampos() {
		return lengthCampos;
	}

	@Override
	public void validarFiltroDocumentosCap(ConfCobroDocCapFiltro confCobroDocCapFiltro) throws ValidacionExcepcion {
		// Valido Clientes
		List<String> campos = new ArrayList<String>();
		List<String> leyendas = new ArrayList<String>();

		if (Validaciones.isObjectNull(confCobroDocCapFiltro.getClientes()) ||
				(!Validaciones.isObjectNull(confCobroDocCapFiltro.getClientes()) && confCobroDocCapFiltro.getClientes().isEmpty())
				) {
			this.setError("conf.cobro.mensaje.error.clientes.sin.seleccionar", "#errorClientesMP", leyendas, campos);
		}
		// •	SHV - Permitir buscar por tipo de comprobante SAP confCobroDocCapFiltro.getTipoComprobanteSap()
		if (!Validaciones.isObjectNull(confCobroDocCapFiltro.getTipoComprobanteSap())) {
			if (!TipoDocumentoCapEnum.getEnums().contains(confCobroDocCapFiltro.getTipoComprobanteSap())) {
				this.setError(
						"error.tiposDeDocumentosACompensar",
						"errorTipoDocCap",
						leyendas,
						campos,
						confCobroDocCapFiltro.getTipoComprobanteSap().name()
						);
			}
		}
		// •	SHV - Permitir buscar por moneda confCobroDocCapFiltro.getMoneda()
		if (!Validaciones.isNullEmptyOrDash(confCobroDocCapFiltro.getMoneda())) {
			MonedaEnum moneda = MonedaEnum.getEnumBySigla(confCobroDocCapFiltro.getMoneda()); 
			if (Validaciones.isObjectNull(moneda)) {
				this.setError("error.moneda", "#errorMonedaCap", leyendas, campos, confCobroDocCapFiltro.getMoneda());
			}
		}

		// •	SHV - Permitir buscar por ID de documento CAP confCobroDocCapFiltro.getIdDocCap();
		if (!Validaciones.isNullOrNumeric(confCobroDocCapFiltro.getIdDocCap())) {
			this.setError("error.numerico", "#errorIdDocCap", leyendas, campos);
		} else if (!Validaciones.isObjectNull(confCobroDocCapFiltro.getIdDocCap()) && confCobroDocCapFiltro.getIdDocCap().length() > 10) {
			this.setError("error.tamañoMayorADiez", "#errorIdDocCap", leyendas, campos);
		}

		// •	SHV - Permitir buscar por número legal de documento CAP confCobroDocCapFiltro.getNumeroLegal()
		if (!Validaciones.isNullEmptyOrDash(confCobroDocCapFiltro.getNumeroLegal())) {
			if (!Validaciones.validarNroDocumentoCap(confCobroDocCapFiltro.getNumeroLegal())) {
				this.setError("conf.cobro.mensaje.error.nroDoc.validacion.cap", "#errorNroDocCap", leyendas, campos);
			}
		}

		// •	SHV - Permitir buscar por rango de fecha de emisión de documento CAP
		// confCobroDocCapFiltro.getFechaDesde()
		// confCobroDocCapFiltro.getFechaHasta()
		if (
				!Validaciones.isNullEmptyOrDash(confCobroDocCapFiltro.getFechaDesde()) &&
				!Validaciones.isNullEmptyOrDash(confCobroDocCapFiltro.getFechaHasta())
				) {
			boolean hasError = false;
			if (!Validaciones.validarFechaConParse(confCobroDocCapFiltro.getFechaDesde())) {
				this.setError("error.fechaIncorrecta", "#errorFechaDesdeCap", leyendas, campos);
				hasError = true;
			}
			if (!Validaciones.validarFechaConParse(confCobroDocCapFiltro.getFechaHasta())) {
				this.setError("error.fechaIncorrecta", "#errorFechaHastaCap", leyendas, campos);
				hasError = true;
			}

			if (!hasError && !Validaciones.validarFechaDesdeHasta(confCobroDocCapFiltro.getFechaDesde(), confCobroDocCapFiltro.getFechaHasta())) {
				this.setError("error.fechaDesdeHastaIncorrecto", "#errorFechaDesdeCap", leyendas, campos);
			}

		} else if (!Validaciones.isNullEmptyOrDash(confCobroDocCapFiltro.getFechaDesde())) {
			if (!Validaciones.validarFechaConParse(confCobroDocCapFiltro.getFechaDesde())) {
				this.setError("error.fechaIncorrecta", "#errorFechaDesdeCap", leyendas, campos);
			}
		} else if (!Validaciones.isNullEmptyOrDash(confCobroDocCapFiltro.getFechaHasta())) {
			if (!Validaciones.validarFechaConParse(confCobroDocCapFiltro.getFechaHasta())) {
				this.setError("error.fechaIncorrecta", "#errorFechaHastaCap", leyendas, campos);
			}
		}
		if (!leyendas.isEmpty()) {
			throw new ValidacionExcepcion(campos, leyendas);
		}
	}
	private void setError(String leyenda, String campo, List<String> leyendas, List<String> campos, String... args) {
		if (args.length > 0) {
			HashMap<String, String> map = new HashMap<String, String>();
			for (int indice = 0; indice < args.length; indice++) {
				map.put("{" + indice +"}", args[indice]);
			}
			leyendas.add(
					Utilidad.reemplazarMensajes(
							Propiedades.MENSAJES_PROPIEDADES.getString(
									leyenda
									),
									map
							));
			campos.add(campo);
		} else {
			leyendas.add(Propiedades.MENSAJES_PROPIEDADES.getString(leyenda));
			campos.add(campo);
		}
	}
}
