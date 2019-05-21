package ar.com.telecom.shiva.negocio.mapeos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.ClienteOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.OrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.IValorConstanciaRecepcionServicio;
import ar.com.telecom.shiva.persistencia.dao.IAcuerdoDao;
import ar.com.telecom.shiva.persistencia.dao.IEmpresaDao;
import ar.com.telecom.shiva.persistencia.dao.IMotivoDao;
import ar.com.telecom.shiva.persistencia.dao.IOrigenDao;
import ar.com.telecom.shiva.persistencia.dao.ISegmentoDao;
import ar.com.telecom.shiva.persistencia.dao.ITipoValorDao;
import ar.com.telecom.shiva.persistencia.dao.IValorDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvDocDocumentoAdjunto;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvValConstanciaRecepcionValorSimplificado;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ComprobanteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

public class ValorMapeador extends Mapeador {

	@Autowired
	IValorDao valorDao;
	
	@Autowired
	private IEmpresaDao empresaDao;
	
	@Autowired
	private ISegmentoDao segmentoDao;
	
	@Autowired
	private IAcuerdoDao acuerdoDao;
	
	@Autowired
	private IMotivoDao motivoDao;

	@Autowired
	private IOrigenDao origenDao;

	@Autowired
	private ITipoValorDao tipoValorDao;

	@Autowired 
	private ILdapServicio ldapServicio;
	
	@Autowired
	IValorConstanciaRecepcionServicio valorConstanciaRecepcionServicio;

	@SuppressWarnings("unused")
	private String SEPARADOR_NUMERO_VALOR = "|";
	private static final String RETENCION_IIBB = "1";

	public DTO map(Modelo vo) throws NegocioExcepcion {

		ShvValValor valorSHV = (ShvValValor) vo;

		ValorDto valorDTO = new ValorDto();
		
		ClienteDto clienteDto = new ClienteDto();

		//ID VALOR
		valorDTO.setId(valorSHV.getIdValor());
		valorDTO.setIdValor(valorSHV.getIdValor());
		//Para la concurrencia
		valorDTO.setFechaUltimaModificacion(valorSHV.getWorkFlow().getFechaUltimaModificacion());
		valorDTO.setTimeStamp(valorDTO.getTimeStampDTO());
		
		// Empresa + ID
		valorDTO.setEmpresa(valorSHV.getEmpresa().getDescripcion());
		valorDTO.setIdEmpresa(valorSHV.getEmpresa().getIdEmpresa());
		// Segmento + ID
		valorDTO.setSegmento(valorSHV.getSegmento().getDescripcion());
		valorDTO.setIdSegmento(valorSHV.getSegmento().getIdSegmento());

		// Código de Cliente Legado
		valorDTO.setCodCliente(String.valueOf(valorSHV.getIdClienteLegado()));
		// Codigo Cliente Agrupador
		valorDTO.setCodClienteAgrupador(valorSHV.getIdClientePerfil().toString());
		// Razón Social Cliente Agrupador
		valorDTO.setRazonSocialClienteAgrupador(valorSHV.getRazonSocialClientePerfil());
		valorDTO.setRazonSocialClientePerfil(valorSHV.getRazonSocialClientePerfil());
//		private String razonSocialClienteLegado; private String numeroHolding; private String nombreHolding;
		
		clienteDto.setCuit(!Validaciones.isObjectNull(valorSHV.getCuit()) ? valorSHV.getCuit().toString() : Constantes.SIGNO_MENOS);
		clienteDto.setIdClienteLegado(String.valueOf(valorSHV.getIdClienteLegado()));
		clienteDto.setEmpresasAsociadas(!Validaciones.isObjectNull(Utilidad.armadoCampoEmpresasAsociadas(valorSHV.getPermiteUsoTA(), valorSHV.getPermiteUsoTP(), valorSHV.getPermiteUsoCV(), valorSHV.getPermiteUsoNX())) ? Utilidad.armadoCampoEmpresasAsociadas(valorSHV.getPermiteUsoTA(), valorSHV.getPermiteUsoTP(), valorSHV.getPermiteUsoCV(), valorSHV.getPermiteUsoNX()) : Constantes.SIGNO_MENOS);
		clienteDto.setRazonSocial(!Validaciones.isObjectNull(valorSHV.getRazonSocialClientePerfil()) ? valorSHV.getRazonSocialClientePerfil() : Constantes.SIGNO_MENOS);
		clienteDto.setOrigen(!Validaciones.isObjectNull(valorSHV.getOrigen()) ? valorSHV.getOrigen().getCodigo() : Constantes.SIGNO_MENOS);
		clienteDto.setDescripcionAgenciaNegocio(!Validaciones.isObjectNull(valorSHV.getDescripcionAgenciaNegocio()) ? valorSHV.getDescripcionAgenciaNegocio() : Constantes.SIGNO_MENOS);
		clienteDto.setAgenciaNegocio(!Validaciones.isObjectNull(valorSHV.getIdAgenciaNegocio()) ? valorSHV.getIdAgenciaNegocio() : Constantes.SIGNO_MENOS);
		clienteDto.setDescripcionHolding(!Validaciones.isObjectNull(valorSHV.getDescripcionHolding()) ? valorSHV.getDescripcionHolding() : Constantes.SIGNO_MENOS);
		clienteDto.setCodigoHolding(!Validaciones.isObjectNull(valorSHV.getIdHolding()) ? valorSHV.getIdHolding().toString() : Constantes.SIGNO_MENOS);
		clienteDto.setSegmentoAgencia(!Validaciones.isObjectNull(valorSHV.getSegmentoAgenciaNegocio()) ? valorSHV.getSegmentoAgenciaNegocio() : Constantes.SIGNO_MENOS);
		
		valorDTO.setCliente(clienteDto);
		
		// Email
		valorDTO.setEmail(valorSHV.getEmail());
		// ID Analista
		valorDTO.setIdAnalista(valorSHV.getIdAnalista());
		
		/* Analista */
		if(!Validaciones.isNullOrEmpty(valorSHV.getIdAnalista())){
			UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(valorSHV.getIdAnalista());
			valorDTO.setIdAnalista(usuarioLdap != null?usuarioLdap.getTuid():"");
			valorDTO.setNombreAnalista(usuarioLdap != null?usuarioLdap.getNombreCompleto():"");
			valorDTO.setMailAnalista(usuarioLdap != null?usuarioLdap.getMail():"");
		}
		
		/* Copropietario */
		if(!Validaciones.isNullOrEmpty(valorSHV.getIdCopropietario())){
			UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(valorSHV.getIdCopropietario());
			valorDTO.setIdCopropietario(usuarioLdap!=null?valorSHV.getIdCopropietario():"");
			valorDTO.setCopropietario(usuarioLdap!=null?usuarioLdap.getNombreCompleto():"");
			valorDTO.setMailCopropietario(usuarioLdap!=null?usuarioLdap.getMail():"");
		}

		// Usuario Autorización
		if(!Validaciones.isNullOrEmpty(valorSHV.getUsuarioAutorizacion())){
			UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(valorSHV.getUsuarioAutorizacion());		
			valorDTO.setUsuarioAutorizacion(usuarioLdap!=null?usuarioLdap.getNombreCompleto():"");
			valorDTO.setMailUsuarioAutorizacion(usuarioLdap!=null?usuarioLdap.getMail():"");			
		}
		
		// Supervisor		
		Collection<UsuarioLdapDto> usuarioLdapLista =  ldapServicio.buscarUsuariosPorPerfilEnMemoria(Constantes.SUPERVISOR + "_" + valorSHV.getEmpresa().getIdEmpresa() + "_" + valorSHV.getSegmento().getIdSegmento());
		StringBuffer supervisoresMail = new StringBuffer("");
		StringBuffer supervisoresChat = new StringBuffer("");
	
		for (UsuarioLdapDto usuario: usuarioLdapLista) {			
			if (!Validaciones.isNullOrEmpty(usuario.getMail())) {
				supervisoresMail.append(usuario.getMail()+";");  
				supervisoresChat.append("<sip:"+usuario.getMail()+">");  
			}			
		}
		
		if(usuarioLdapLista.size()>0){			
			valorDTO.setNombreSupervisor((usuarioLdapLista.size()>1)?"Grupo de Supervisores":usuarioLdapLista.iterator().next().getNombreCompleto());	
		}
		valorDTO.setMailSupervisorIconoMail(supervisoresMail.toString());
		valorDTO.setMailSupervisorIconoChat(supervisoresChat.toString());
		

		// Tipo de Valor
		valorDTO.setTipoValor(valorSHV.getTipoValor().getDescripcion());
		valorDTO.setIdTipoValor(String.valueOf(valorSHV.getTipoValor().getIdTipoValor()));
		
		// Importe
	//	valorDTO.setImporte(Utilidad.cambiarPuntoDecimalPorComa(valorSHV.getImporte().toString()));
		valorDTO.setImporte(Utilidad.formatCurrency(valorSHV.getImporte(), 2) );
		// Motivo + ID
		if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getMotivo()))) {
			valorDTO.setMotivo(valorSHV.getMotivo().getDescripcion());
			valorDTO.setIdMotivo(String.valueOf(valorSHV.getMotivo().getIdMotivo()));
		}
		
		// Operación Asociada
		if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getOperacionAsociada()))) {
			valorDTO.setOperacionAsociada(valorSHV.getOperacionAsociada().toString());
		}
		
		// Origen + ID
		if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getParamOrigen()))) {
			valorDTO.setOrigen(valorSHV.getParamOrigen().getDescripcion());
			valorDTO.setIdOrigen(String.valueOf(valorSHV.getParamOrigen().getIdOrigen()));
		}
		
		// Número Documento Contable
		if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getNumeroDocumentoContable()))) {
			valorDTO.setNumeroDocumentoContable(String.valueOf(valorSHV.getNumeroDocumentoContable()));
		}
		
		// Nro. Acuerdo + ID
		if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getAcuerdo()))) {
			valorDTO.setAcuerdo(valorSHV.getAcuerdo().getDescripcion());
			valorDTO.setIdAcuerdo(String.valueOf(valorSHV.getAcuerdo().getIdAcuerdo()));
		}
		
		// Banco Depósito + ID
		if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getAcuerdo()))) {
			valorDTO.setBancoDeposito(valorSHV.getAcuerdo().getBancoCuenta().getBanco().getDescripcion());
			valorDTO.setIdBancoDeposito(valorSHV.getAcuerdo().getBancoCuenta().getBanco().getIdBanco());
			valorDTO.setIdNroCuenta(String.valueOf(valorSHV.getAcuerdo().getBancoCuenta().getIdBancoCuenta()));
			valorDTO.setNumeroCuenta(valorSHV.getAcuerdo().getBancoCuenta().getCuentaBancaria());
		}

		// BD Cheque [IDBanco Origen, Banco Origen, Numero Cheque]
		if(!Validaciones.isObjectNull(valorSHV.getValorBoletaDepositoCheque())){
			if (!Validaciones.isObjectNull(valorSHV.getValorBoletaDepositoCheque().getBancoOrigen())){
				valorDTO.setIdBancoOrigen(valorSHV.getValorBoletaDepositoCheque().getBancoOrigen().getIdBanco());
				valorDTO.setBancoOrigen(valorSHV.getValorBoletaDepositoCheque().getBancoOrigen().getDescripcion());
			}
			if (!Validaciones.isObjectNull(valorSHV.getValorBoletaDepositoCheque().getNumeroCheque())){
				valorDTO.setNumeroCheque(valorSHV.getValorBoletaDepositoCheque().getNumeroCheque().toString());	
			}
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValorBoletaDepositoCheque().getReciboPreImpreso()))) {
				valorDTO.setReciboPreImpreso(String.valueOf(valorSHV.getValorBoletaDepositoCheque().getReciboPreImpreso().getNumeroRecibo()));
			}
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValorBoletaDepositoCheque().getFechaRecibo()))) {
				valorDTO.setFechaIngresoRecibo(Utilidad.formatDatePicker(valorSHV.getValorBoletaDepositoCheque().getFechaRecibo()));
			}
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValorBoletaDepositoCheque().getFechaDeposito()))) {
				valorDTO.setFechaDeposito(Utilidad.formatDateAndTimeYearDos(valorSHV.getValorBoletaDepositoCheque().getFechaDeposito()));
			}
			if (!Validaciones.isObjectNull(valorSHV.getValorBoletaDepositoCheque().getBoleta())){
				valorDTO.setNumeroBoleta(String.valueOf(valorSHV.getValorBoletaDepositoCheque().getBoleta().getNumeroBoleta()));
				valorDTO.setIdBoleta(String.valueOf(valorSHV.getValorBoletaDepositoCheque().getBoleta().getIdBoleta()));
			}
			if (!Validaciones.isObjectNull(valorSHV.getValorBoletaDepositoCheque().getFechaEmision())) {
				valorDTO.setFechaEmision(Utilidad.formatDatePicker(valorSHV.getValorBoletaDepositoCheque().getFechaEmision()));
			} else {
				valorDTO.setFechaEmisionInicialmenteNulo(true);
			}
		}
		// BD Cheque Diferido
		if(!Validaciones.isObjectNull(valorSHV.getValorBoletaDepositoChequePD())){
			if (!Validaciones.isObjectNull(valorSHV.getValorBoletaDepositoChequePD().getBancoOrigen())){
				valorDTO.setIdBancoOrigen(valorSHV.getValorBoletaDepositoChequePD().getBancoOrigen().getIdBanco());
				valorDTO.setBancoOrigen(valorSHV.getValorBoletaDepositoChequePD().getBancoOrigen().getDescripcion());
			}
			if (!Validaciones.isObjectNull(valorSHV.getValorBoletaDepositoChequePD().getNumeroCheque())){
				valorDTO.setNumeroCheque(valorSHV.getValorBoletaDepositoChequePD().getNumeroCheque().toString());
			}
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValorBoletaDepositoChequePD().getFechaVencimiento()))){
				valorDTO.setFechaVencimiento(Utilidad.formatDateAndTimeYearDos(valorSHV.getValorBoletaDepositoChequePD().getFechaVencimiento()));
			}
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValorBoletaDepositoChequePD().getReciboPreImpreso()))) {
				valorDTO.setReciboPreImpreso(String.valueOf(valorSHV.getValorBoletaDepositoChequePD().getReciboPreImpreso().getNumeroRecibo()));
			}
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValorBoletaDepositoChequePD().getFechaRecibo()))) {
				valorDTO.setFechaIngresoRecibo(Utilidad.formatDatePicker(valorSHV.getValorBoletaDepositoChequePD().getFechaRecibo()));
			}
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValorBoletaDepositoChequePD().getFechaDeposito()))) {
				valorDTO.setFechaDeposito(Utilidad.formatDateAndTimeYearDos(valorSHV.getValorBoletaDepositoChequePD().getFechaDeposito()));
			}
			if (!Validaciones.isObjectNull(valorSHV.getValorBoletaDepositoChequePD().getBoleta())){
				valorDTO.setNumeroBoleta(String.valueOf(valorSHV.getValorBoletaDepositoChequePD().getBoleta().getNumeroBoleta()));
				valorDTO.setIdBoleta(String.valueOf(valorSHV.getValorBoletaDepositoChequePD().getBoleta().getIdBoleta()));
			}
			if (!Validaciones.isObjectNull(valorSHV.getValorBoletaDepositoChequePD().getFechaEmision())) {
				valorDTO.setFechaEmision(Utilidad.formatDatePicker(valorSHV.getValorBoletaDepositoChequePD().getFechaEmision()));
			} else {
				valorDTO.setFechaEmisionInicialmenteNulo(true);
			}
		}
		// BD Efectivo
		if(!Validaciones.isObjectNull(valorSHV.getValorBoletaEfectivo())){
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValorBoletaEfectivo().getReciboPreImpreso()))) {
				valorDTO.setReciboPreImpreso(String.valueOf(valorSHV.getValorBoletaEfectivo().getReciboPreImpreso().getNumeroRecibo()));
			}
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValorBoletaEfectivo().getFechaRecibo()))) {
				valorDTO.setFechaIngresoRecibo(Utilidad.formatDatePicker(valorSHV.getValorBoletaEfectivo().getFechaRecibo()));
			}
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValorBoletaEfectivo().getFechaDeposito()))) {
				valorDTO.setFechaDeposito(Utilidad.formatDateAndTimeYearDos(valorSHV.getValorBoletaEfectivo().getFechaDeposito()));
			}
			if (!Validaciones.isObjectNull(valorSHV.getValorBoletaEfectivo().getBoleta())){
				valorDTO.setNumeroBoleta(String.valueOf(valorSHV.getValorBoletaEfectivo().getBoleta().getNumeroBoleta()));
				valorDTO.setIdBoleta(String.valueOf(valorSHV.getValorBoletaEfectivo().getBoleta().getIdBoleta()));
			}
		}
		// Cheque
		if(!Validaciones.isObjectNull(valorSHV.getValValorCheque())){
			if (!Validaciones.isObjectNull(valorSHV.getValValorCheque().getBancoOrigen())){
				valorDTO.setIdBancoOrigen(valorSHV.getValValorCheque().getBancoOrigen().getIdBanco());
				valorDTO.setBancoOrigen(valorSHV.getValValorCheque().getBancoOrigen().getDescripcion());
			}
			if (!Validaciones.isObjectNull(valorSHV.getValValorCheque().getNumeroCheque())){
				valorDTO.setNumeroCheque(valorSHV.getValValorCheque().getNumeroCheque().toString());
			}
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValValorCheque().getFechaDeposito()))) {
				valorDTO.setFechaDeposito(Utilidad.formatDateAndTimeYearDos(valorSHV.getValValorCheque().getFechaDeposito()));
			}
			if (!Validaciones.isObjectNull(valorSHV.getValValorCheque().getNumeroBoleta())){
				valorDTO.setNumeroBoleta(String.valueOf(valorSHV.getValValorCheque().getNumeroBoleta()));
			}
			if (!Validaciones.isObjectNull(valorSHV.getValValorCheque().getDocumentacionOriginalRecibida())){
				valorDTO.setDocumentacionOriginalRecibida(valorSHV.getValValorCheque().getDocumentacionOriginalRecibida().getDescripcion());
			}
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValValorCheque().getReciboPreImpreso()))) {
				valorDTO.setReciboPreImpreso(String.valueOf(valorSHV.getValValorCheque().getReciboPreImpreso().getNumeroRecibo()));
			}

			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValValorCheque().getFechaRecibo()))) {
				valorDTO.setFechaIngresoRecibo(Utilidad.formatDatePicker(valorSHV.getValValorCheque().getFechaRecibo()));
			}
			
			if (!Validaciones.isObjectNull(valorSHV.getValValorCheque().getFechaEmision())) {
				valorDTO.setFechaEmision(Utilidad.formatDatePicker(valorSHV.getValValorCheque().getFechaEmision()));
			} else {
				valorDTO.setFechaEmisionInicialmenteNulo(true);
			}
			
		}
		// Cheque Diferido
		if(!Validaciones.isObjectNull(valorSHV.getValValorChequePD())){
			if (!Validaciones.isObjectNull(valorSHV.getValValorChequePD().getBancoOrigen())){
				valorDTO.setIdBancoOrigen(valorSHV.getValValorChequePD().getBancoOrigen().getIdBanco());
				valorDTO.setBancoOrigen(valorSHV.getValValorChequePD().getBancoOrigen().getDescripcion());
			}
			if (!Validaciones.isObjectNull(valorSHV.getValValorChequePD().getNumeroCheque())){
				valorDTO.setNumeroCheque(valorSHV.getValValorChequePD().getNumeroCheque().toString());
			}
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValValorChequePD().getFechaVencimiento()))) {
				valorDTO.setFechaVencimiento(Utilidad.formatDateAndTimeYearDos(valorSHV.getValValorChequePD().getFechaVencimiento()));
			}
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValValorChequePD().getFechaDeposito()))) {
				valorDTO.setFechaDeposito(Utilidad.formatDateAndTimeYearDos(valorSHV.getValValorChequePD().getFechaDeposito()));
			}
			if (!Validaciones.isObjectNull(valorSHV.getValValorChequePD().getNumeroBoleta())){
				valorDTO.setNumeroBoleta(String.valueOf(valorSHV.getValValorChequePD().getNumeroBoleta()));
			}
			if (!Validaciones.isObjectNull(valorSHV.getValValorChequePD().getDocumentacionOriginalRecibida())){
				valorDTO.setDocumentacionOriginalRecibida(valorSHV.getValValorChequePD().getDocumentacionOriginalRecibida().getDescripcion());
			}
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValValorChequePD().getReciboPreImpreso()))) {
				valorDTO.setReciboPreImpreso(String.valueOf(valorSHV.getValValorChequePD().getReciboPreImpreso().getNumeroRecibo()));
			}
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValValorChequePD().getFechaRecibo()))) {
				valorDTO.setFechaIngresoRecibo(Utilidad.formatDatePicker(valorSHV.getValValorChequePD().getFechaRecibo()));
			}
			if (!Validaciones.isObjectNull(valorSHV.getValValorChequePD().getFechaEmision())) {
				valorDTO.setFechaEmision(Utilidad.formatDatePicker(valorSHV.getValValorChequePD().getFechaEmision()));
			} else {
				valorDTO.setFechaEmisionInicialmenteNulo(true);
			}
		}
		// Efectivo
		if(!Validaciones.isObjectNull(valorSHV.getValValorEfectivo())){
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValValorEfectivo().getFechaDeposito()))) {
				valorDTO.setFechaDeposito(Utilidad.formatDateAndTimeYearDos(valorSHV.getValValorEfectivo().getFechaDeposito()));
			}
			if (!Validaciones.isObjectNull(valorSHV.getValValorEfectivo().getNumeroBoleta())){
				valorDTO.setNumeroBoleta(String.valueOf(valorSHV.getValValorEfectivo().getNumeroBoleta()));
			}
			if (!Validaciones.isObjectNull(valorSHV.getValValorEfectivo().getDocumentacionOriginalRecibida())){
				valorDTO.setDocumentacionOriginalRecibida(valorSHV.getValValorEfectivo().getDocumentacionOriginalRecibida().getDescripcion());
			}
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValValorEfectivo().getReciboPreImpreso()))) {
				valorDTO.setReciboPreImpreso(String.valueOf(valorSHV.getValValorEfectivo().getReciboPreImpreso().getNumeroRecibo()));
			}

			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValValorEfectivo().getFechaRecibo()))) {
				valorDTO.setFechaIngresoRecibo(Utilidad.formatDatePicker(valorSHV.getValValorEfectivo().getFechaRecibo()));
			}
		}
		// Interdeposito
		if(!Validaciones.isObjectNull(valorSHV.getValValorInterdeposito())){
			valorDTO.setCodOrganismo(valorSHV.getValValorInterdeposito().getCodigoOrganismo());
			valorDTO.setFechaInterdeposito(Utilidad.formatDateAndTimeYearDos(valorSHV.getValValorInterdeposito().getFechaInterdeposito()));
			valorDTO.setNumeroInterdepositoCdif(String.valueOf(valorSHV.getValValorInterdeposito().getNumeroInterdeposito()));
		}
		// Retencion
		if(!Validaciones.isObjectNull(valorSHV.getValValorRetencion())){
			valorDTO.setCuitIbb(valorSHV.getValValorRetencion().getCuit());
			
			
			//valorDTO.setNumeroLegalComprobante(valorSHV.getValValorRetencion().getNumeroComprobante());
			// Modificado por Pablo
			if (!Validaciones.isObjectNull(valorSHV.getValValorRetencion().getSucursalComprobante())
					&& !Validaciones.isObjectNull(valorSHV.getValValorRetencion().getNumeroComprobante()) ) {
				valorDTO.setNumeroLegalComprobante(valorSHV.getValValorRetencion().getSucursalComprobante() + "-" + valorSHV.getValValorRetencion().getNumeroComprobante());
			}
			
			valorDTO.setFechaEmision(Utilidad.formatDateAndTimeYearDos(valorSHV.getValValorRetencion().getFechaEmision()));
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValValorRetencion().getReciboPreImpreso()))) {
				valorDTO.setReciboPreImpreso(String.valueOf(valorSHV.getValValorRetencion().getReciboPreImpreso().getNumeroRecibo()));
			}

			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValValorRetencion().getFechaRecibo()))) {
				valorDTO.setFechaIngresoRecibo(Utilidad.formatDatePicker(valorSHV.getValValorRetencion().getFechaRecibo()));
			}
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValValorRetencion().getParamTipoRetencionImpuesto()))) {
				valorDTO.setIdTipoImpuesto(String.valueOf(valorSHV.getValValorRetencion().getParamTipoRetencionImpuesto().getIdTipoRetencionImpuesto()));
				valorDTO.setTipoImpuesto((valorSHV.getValValorRetencion().getParamTipoRetencionImpuesto().getDescripcion()));
			}
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValValorRetencion().getParamJurisdiccion()))) {
				valorDTO.setIdProvincia(valorSHV.getValValorRetencion().getParamJurisdiccion().getProvincia());
				valorDTO.setProvincia(valorSHV.getValValorRetencion().getParamJurisdiccion().getDescripcion());
			}
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValValorRetencion().getParamTipoComprobante()))) {
				valorDTO.setIdTipoComprobante(valorSHV.getValValorRetencion().getParamTipoComprobante().getIdTipoComprobante());
				valorDTO.setTipoComprobante(valorSHV.getValValorRetencion().getParamTipoComprobante().getDescripcion());
			}
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValValorRetencion().getParamTipoLetraComprobante()))) {
				valorDTO.setIdLetraComprobante(valorSHV.getValValorRetencion().getParamTipoLetraComprobante().getIdTipoLetraComprobante());
				valorDTO.setLetraComprobante(valorSHV.getValValorRetencion().getParamTipoLetraComprobante().getDescripcion());
			}
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValValorRetencion().getNroConstanciaRetencion()))) {
				valorDTO.setNroConstancia(valorSHV.getValValorRetencion().getNroConstanciaRetencion());				
			}
		}
		// Transferencia
		if(!Validaciones.isObjectNull(valorSHV.getValValorTransferencia())){
			// Las transferencias nos siempre tienen banco origen
			if(!Validaciones.isObjectNull(valorSHV.getValValorTransferencia().getBancoOrigen())) {
				valorDTO.setIdBancoOrigen(valorSHV.getValValorTransferencia().getBancoOrigen().getIdBanco());
				valorDTO.setBancoOrigen(valorSHV.getValValorTransferencia().getBancoOrigen().getDescripcion());
			}
			valorDTO.setCuit(valorSHV.getValValorTransferencia().getCuit());
			valorDTO.setFechaTransferencia(Utilidad.formatDateAndTimeYearDos(valorSHV.getValValorTransferencia().getFechaTransferencia()));
			valorDTO.setNumeroReferencia(String.valueOf(valorSHV.getValValorTransferencia().getNumeroReferencia()));

			if (!Validaciones.isObjectNull(valorSHV.getValValorTransferencia().getDocumentacionOriginalRecibida())){
				valorDTO.setDocumentacionOriginalRecibida(valorSHV.getValValorTransferencia().getDocumentacionOriginalRecibida().getDescripcion());
			}
		}
		
		if (!Validaciones.isObjectNull(valorSHV.getFechaNotificacionDisponibilidadRetiroValor())) {
			valorDTO.setFechaNotificacionDisponibilidadRetiroValor(Utilidad.formatDatePicker(valorSHV.getFechaNotificacionDisponibilidadRetiroValor()));
		}

		// Observaciones
		valorDTO.setObservaciones(valorSHV.getObservaciones());
		valorDTO.setObservacionesConfirmarAlta(valorSHV.getObservacionesConfirmarAlta());
		
		if(!Validaciones.isObjectNull(valorSHV.getValorDocAdjunto())){
			for (ShvDocDocumentoAdjunto docAdjunto : valorSHV.getValorDocAdjunto()) {
				ComprobanteDto comprobante = new ComprobanteDto();
				comprobante.setId(docAdjunto.getIdValorAdjunto());
				comprobante.setNombreArchivo(docAdjunto.getNombreArchivo());
				comprobante.setDescripcionArchivo(docAdjunto.getDescripcion());
				comprobante.setDocumento(docAdjunto.getArchivoAdjunto());
				comprobante.setUsuarioCreacion(docAdjunto.getUsuarioCreacion());
				if (!valorDTO.getListaComprobantes().contains(comprobante)) {
					valorDTO.getListaComprobantes().add(comprobante);
				}
			}
		}

		if(!Validaciones.isObjectNull(valorSHV.getValorPadre())){
			if(!Validaciones.isObjectNull(valorSHV.getValorPadre().getValorBoletaDepositoCheque())) {
				valorDTO.setNroChequeAReemplazar(valorSHV.getValorPadre().getValorBoletaDepositoCheque().getNumeroCheque().toString());
			} else if(!Validaciones.isObjectNull(valorSHV.getValorPadre().getValorBoletaDepositoChequePD())) {
				valorDTO.setNroChequeAReemplazar(valorSHV.getValorPadre().getValorBoletaDepositoChequePD().getNumeroCheque().toString());
			} else if(!Validaciones.isObjectNull(valorSHV.getValorPadre().getValValorCheque())){
				valorDTO.setNroChequeAReemplazar(valorSHV.getValorPadre().getValValorCheque().getNumeroCheque().toString());
			} else if(!Validaciones.isObjectNull(valorSHV.getValorPadre().getValValorChequePD())){
				valorDTO.setNroChequeAReemplazar(valorSHV.getValorPadre().getValValorChequePD().getNumeroCheque().toString());
			}
		}
		
		// Estado Valor
		valorDTO.setEstadoValor(valorSHV.getWorkFlow().getShvWfWorkflowEstado().iterator().next().getEstado().descripcion());
		valorDTO.setIdEstadoValor(valorSHV.getWorkFlow().getShvWfWorkflowEstado().iterator().next().getEstado().name());
		// Saldo Disponible, ver en caso de uso saldo ?
		if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getSaldoDisponible()))) {
			valorDTO.setSaldoDisponible(Utilidad.formatCurrency(valorSHV.getSaldoDisponible(), 2) );
		}
		// Numero de Valor
		String numeroValorConEnter= valorSHV.getNumeroValor().replace("|", "<br>");			
		valorDTO.setNumeroValor(numeroValorConEnter);
				
		// BD Impresa
		if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValorBoletaDepositoCheque()))) {
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValorBoletaDepositoCheque().getBoleta().getImpresionEstado()))) {
				valorDTO.setBoletaImpresaEstado(valorSHV.getValorBoletaDepositoCheque().getBoleta().getImpresionEstado());
			}
		} else if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValorBoletaDepositoChequePD()))) {
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValorBoletaDepositoChequePD().getBoleta().getImpresionEstado()))) {
				valorDTO.setBoletaImpresaEstado(valorSHV.getValorBoletaDepositoChequePD().getBoleta().getImpresionEstado());
			}
		} else if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValorBoletaEfectivo()))) {
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValorBoletaEfectivo().getBoleta().getImpresionEstado()))) {
				valorDTO.setBoletaImpresaEstado(valorSHV.getValorBoletaEfectivo().getBoleta().getImpresionEstado());
			}
		}
		// BD Enviada Mail
		if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValorBoletaDepositoCheque()))) {
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValorBoletaDepositoCheque().getBoleta().getEmailEnvioEstado()))) {
				valorDTO.setBoletaEnviadaMailEstado(valorSHV.getValorBoletaDepositoCheque().getBoleta().getEmailEnvioEstado());
			}
		} else if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValorBoletaDepositoChequePD()))) {
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValorBoletaDepositoChequePD().getBoleta().getEmailEnvioEstado()))) {
				valorDTO.setBoletaEnviadaMailEstado(valorSHV.getValorBoletaDepositoChequePD().getBoleta().getEmailEnvioEstado());
			}
		} else if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValorBoletaEfectivo()))) {
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValorBoletaEfectivo().getBoleta().getEmailEnvioEstado()))) {
				valorDTO.setBoletaEnviadaMailEstado(valorSHV.getValorBoletaEfectivo().getBoleta().getEmailEnvioEstado());
			}
		}
		// Fecha Alta
		valorDTO.setFechaAltaValor(Utilidad.formatDateAndTimeYear(valorSHV.getWorkFlow().getFechaAlta()));
		valorDTO.setFechaAlta(valorSHV.getWorkFlow().getFechaAlta());

		// Factura Relacionada (contendrá los tres campos cargados en el Alta: Tipo, Letra y Nro. Legal del comprobante)
		if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValValorRetencion()))) {
			if (RETENCION_IIBB.equals(valorSHV.getValValorRetencion().getParamTipoRetencionImpuesto().getIdTipoRetencionImpuesto().toString())) {
				
				// Se debe verificar, ya que la factura relacionada no es obligatoria
				if (valorSHV.getValValorRetencion().getParamTipoComprobante() != null) {
					valorDTO.setFacturaRelacionada(valorSHV.getValValorRetencion().getParamTipoComprobante().getDescripcion()
							+ " "
							+ valorSHV.getValValorRetencion().getParamTipoLetraComprobante().getDescripcion()
							+ " "
							+ valorSHV.getValValorRetencion().getSucursalComprobante()
							+ "-"
							+ valorSHV.getValValorRetencion().getNumeroComprobante());
				}
			}
		}
		
		// Motivo Suspensión
		if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getMotivoSuspension()))) {
			valorDTO.setMotivoSuspension(valorSHV.getMotivoSuspension().getDescripcion());
			valorDTO.setIdMotivoSuspension(Integer.parseInt(valorSHV.getMotivoSuspension().getIdMotivoSuspension()));
		}
		// Ejecutivo---> Son datos que se buscan en el sistema DELTA
		if (!Validaciones.isNullOrEmpty(valorSHV.getUsuarioEjecutivo())) {
			valorDTO.setUsuarioEjecutivo(valorSHV.getUsuarioEjecutivo());
		}
		// Asistente--> Son datos que se buscan en el sistema DELTA
		if (!Validaciones.isNullOrEmpty(valorSHV.getUsuarioEjecutivo())) {
			valorDTO.setUsuarioAsistente(valorSHV.getUsuarioAsistente());
		}		
		// id padre, CHEQUE A REEMPLAZAR
		if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getValorPadre()))) {
			valorDTO.setValorPadre(valorSHV.getValorPadre().getIdValor().toString());
		}
		// Fecha Ultimo Estado
		if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getFechaUltimoEstado()))) {
			valorDTO.setFechaUltimoEstado(Utilidad.formatDateAndTimeYearDos(valorSHV.getFechaUltimoEstado()));
		}
		// Fecha Disponible
		if (!Validaciones.isNullOrEmpty(String.valueOf(valorSHV.getFechaDisponible()))) {
			valorDTO.setFechaDisponible(Utilidad.formatDateAndTimeYearDos(valorSHV.getFechaDisponible()));
		}
		
		// Archivo de Valores a Conciliar--> se setea en vacio lo ven ellos
		valorDTO.setArchivoValoresAconciliar("");

		
		// Nro. Constancia de recepción --> va el id constancia de recepcion	
		//
		// Req 70868 Mejora de Performance en Talonarios
		// Solo voy a buscar la constancia de recepción si el origen es Oficina Constancia Automatica
		if (!Validaciones.isObjectNull(valorSHV.getParamOrigen()) && OrigenEnum.OF_CONSTANCIA_AUTO.codigo().equals(valorSHV.getParamOrigen().getIdOrigen())) {
			ShvValConstanciaRecepcionValorSimplificado constanciaRecepcionValor = valorConstanciaRecepcionServicio.buscarConstanciaRecepcionAsignadaAValorSimplificado(valorSHV.getIdValor());
			if (!Validaciones.isObjectNull(constanciaRecepcionValor)) {
				valorDTO.setConstancia(String.valueOf(constanciaRecepcionValor.getPk().getConstanciaRecepcion().getIdConstanciaRecepcion()));
				valorDTO.setFechaConstancia(Utilidad.formatDateAndTimeYearDos(constanciaRecepcionValor.getPk().getConstanciaRecepcion().getFechaCreacion()));
			}
		}
		
		// Fecha Valor
		valorDTO.setFechaValor(valorSHV.getFechaValor());

		valorDTO.setReferenciaValor(valorSHV.getReferenciaValor());
		valorDTO.setNombreApellidoAnalista(valorSHV.getNombreApellidoAnalista());
		valorDTO.setNombreApellidoCopropietario(valorSHV.getNombreApellidoCopropietario());
		valorDTO.setNombreApellidoAutorizante(valorSHV.getNombreApellidoAutorizante());
		valorDTO.setRazonSocial(valorSHV.getRazonSocial());
		valorDTO.setIdHolding(valorSHV.getIdHolding());

		valorDTO.setNumeroHolding(valorSHV.getIdHolding() != null ? valorSHV.getIdHolding().toString():null);
		valorDTO.setNombreHolding(valorSHV.getDescripcionHolding());
		valorDTO.setDescripcionHolding(valorSHV.getDescripcionHolding());
		valorDTO.setIdAgenciaNegocio(valorSHV.getIdAgenciaNegocio());
		valorDTO.setDescripcionAgenciaNegocio(valorSHV.getDescripcionAgenciaNegocio());
		valorDTO.setSegmentoAgenciaNegocio(valorSHV.getSegmentoAgenciaNegocio());

		return valorDTO;
	}

	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		ValorDto valorDto = (ValorDto) dto;

		ShvValValor valorModelo = (ShvValValor) (vo != null ? vo: new ShvValValor());

		try {
			// Mapeo la tabla Valor
			
			valorModelo.setUsuarioEjecutivo(valorDto.getUsuarioEjecutivo());
			valorModelo.setUsuarioAsistente(valorDto.getUsuarioAsistente());
			valorModelo.setEmpresa(empresaDao.buscar(valorDto.getIdEmpresa()));
			valorModelo.setSegmento(segmentoDao.buscarSegmento(valorDto.getIdSegmento()));
			
			/**
			 * req 67475
			 * Alta de aviso de pago
			 */
			
			/*Se pregunta si el cliente no es nulo por boletas sin valor, que no está seteando un cliente. 
			  Mas adelante deberia modificarse esto, sacando la discriminacion nada mas*/
			
			if (!Validaciones.isObjectNull(valorDto.getCliente()) && !valorDto.esBoletaSinValor()) {
				if (!Validaciones.isNullOrEmpty(valorDto.getCliente().getIdClientePerfil())){
					valorModelo.setIdClientePerfil(Long.valueOf(valorDto.getCliente().getIdClientePerfil()));
				}
	
				valorModelo.setRazonSocial(valorDto.getCliente().getRazonSocial());
				if (!Validaciones.isNullEmptyOrDash(valorDto.getCliente().getCodigoHolding())) {
					valorModelo.setIdHolding(Long.valueOf(valorDto.getCliente().getCodigoHolding()));
				}
				if (!Validaciones.isNullEmptyOrDash(valorDto.getCliente().getDescripcionHolding())) {
					valorModelo.setDescripcionHolding(valorDto.getCliente().getDescripcionHolding());
					
				}
				
				if (!Validaciones.isNullEmptyOrDash(valorDto.getCliente().getAgenciaNegocio())) {
					valorModelo.setIdAgenciaNegocio(valorDto.getCliente().getAgenciaNegocio());
				}
				
				if (!Validaciones.isNullEmptyOrDash(valorDto.getCliente().getDescripcionAgenciaNegocio())) {
					valorModelo.setDescripcionAgenciaNegocio(valorDto.getCliente().getDescripcionAgenciaNegocio());
				}
				
				valorModelo.setSegmentoAgenciaNegocio(valorDto.getCliente().getSegmentoAgencia());
				
				valorModelo.setIdClienteLegado(Long.valueOf(valorDto.getCliente().getIdClienteLegado()));
				valorModelo.setRazonSocialClientePerfil(valorDto.getCliente().getRazonSocial());
				valorModelo.setOrigen(ClienteOrigenEnum.getEnumByCodigo(valorDto.getCliente().getOrigen()));
				
				if (!Validaciones.isNullEmptyOrDash(valorDto.getCliente().getCuit())) {
					valorModelo.setCuit(Long.parseLong(valorDto.getCliente().getCuit()));
				}
				
			} else {
				/*En boleta sin valor deberia entrar acá*/
				valorModelo.setIdClienteLegado(Long.valueOf(valorDto.getCodCliente()));
			}
			/**
			 * req 67475
			 * fin modificacion Alta de aviso de pago
			 */
			
			valorModelo.setEmail(valorDto.getEmail());
			valorModelo.setIdAnalista(valorDto.getIdAnalista());
			if ((!Validaciones.isNullOrEmpty(valorDto.getNumeroValor())) && (valorDto.getNumeroValor().contains("|"))) {
				valorModelo.setNumeroValor(valorDto.getNumeroValor());
			}
			
			valorModelo.setIdCopropietario(valorDto.getIdCopropietario());
			valorModelo.setTipoValor(tipoValorDao.buscarTipoValor(valorDto.getIdTipoValor()));
			if (Utilidad.PESOS_CHAR.equalsIgnoreCase(valorDto.getImporte().substring(0, 1))) {
				valorModelo.setImporte(Utilidad.stringToBigDecimal(valorDto.getImporte().substring(1)));
			} else {
				valorModelo.setImporte(Utilidad.stringToBigDecimal(valorDto.getImporte()));
			}
			
			if (!Validaciones.isNullOrEmpty(valorDto.getSaldoDisponible())) {
				if (Utilidad.PESOS_CHAR.equalsIgnoreCase(valorDto.getSaldoDisponible().substring(0, 1))) {
					valorModelo.setSaldoDisponible(Utilidad.stringToBigDecimal(valorDto.getSaldoDisponible().substring(1)));
				} else {
					valorModelo.setSaldoDisponible(Utilidad.stringToBigDecimal(valorDto.getSaldoDisponible()));
				}
			} else {
				valorModelo.setSaldoDisponible(valorModelo.getImporte());
			}
			
			if (!Validaciones.isNullOrEmpty(valorDto.getIdMotivo())) {
				valorModelo.setMotivo(motivoDao.buscarMotivo(valorDto.getIdMotivo()));
			}else{
				valorModelo.setMotivo(null);
			}
			if (!Validaciones.isNullOrEmpty(valorDto.getOperacionAsociada())) {
				valorModelo.setOperacionAsociada(Integer.valueOf(valorDto.getOperacionAsociada()));
			}else{
				valorModelo.setOperacionAsociada(null);
			}
		
			if (!Validaciones.isNullOrEmpty(valorDto.getObservaciones())) {
				valorModelo.setObservaciones(valorDto.getObservaciones());
			}
				
			valorModelo.setObservacionesConfirmarAlta(valorDto.getObservacionesConfirmarAlta());
			if (!Validaciones.isNullOrEmpty(valorDto.getIdOrigen())) {
				valorModelo.setParamOrigen(origenDao.buscarOrigen(valorDto.getIdOrigen()));
			}
			if (!Validaciones.isNullOrEmpty(valorDto.getIdAcuerdo())) {
				valorModelo.setAcuerdo(acuerdoDao.buscarAcuerdo(valorDto.getIdAcuerdo()));
			}
			if (!Validaciones.isNullOrEmpty(String.valueOf(valorDto.getNroChequeAReemplazar()))) {
				if (!Validaciones.isNullOrEmpty(valorDto.getIdValorAsociadoAlChequeAReemplazar())) {
					valorModelo.setValorPadre(valorDao.buscarValorPadreAsociadoAlValorDelChequeAnulado(
							Long.valueOf(valorDto.getIdValorAsociadoAlChequeAReemplazar())));
				} else {
					valorModelo.setValorPadre(null);
				}
			} else {
				valorModelo.setValorPadre(null);
			}
			if (!Validaciones.isNullOrEmpty(valorDto.getNumeroDocumentoContable())) {
				valorModelo.setNumeroDocumentoContable(Long.parseLong(valorDto.getNumeroDocumentoContable()));
			} else {
				valorModelo.setNumeroDocumentoContable(null);
			}			
			if (Validaciones.isCollectionNotEmpty(valorDto.getListaComprobantes())) {
				List<ShvDocDocumentoAdjunto> documentos = new ArrayList<ShvDocDocumentoAdjunto>();
				for (ComprobanteDto comprobante : valorDto.getListaComprobantes()) {
					ShvDocDocumentoAdjunto docAdjunto = new ShvDocDocumentoAdjunto();
					docAdjunto.setArchivoAdjunto(comprobante.getDocumento());
					docAdjunto.setDescripcion(comprobante.getDescripcionArchivo());
					docAdjunto.setFechaCreacion(new Date());
					docAdjunto.setNombreArchivo(comprobante.getNombreArchivo());
					docAdjunto.setUsuarioCreacion(comprobante.getUsuarioCreacion());
					
					documentos.add(docAdjunto);
				}
				for (ShvDocDocumentoAdjunto shvDocDocumentoAdjunto : documentos) {
					shvDocDocumentoAdjunto.setIdValor(new ArrayList<ShvValValor>());
					shvDocDocumentoAdjunto.getIdValor().add(valorModelo);
				}
				valorModelo.setValorDocAdjunto(documentos);
			} else {
				valorModelo.setValorDocAdjunto(new ArrayList<ShvDocDocumentoAdjunto>());
			}
			
			UsuarioLdapDto usuarioLdapAnalista;
			if (!Validaciones.isNullOrEmpty(valorDto.getIdAnalista())) {
				usuarioLdapAnalista = ldapServicio.buscarUsuarioPorUidEnMemoria(valorDto.getIdAnalista());
				if(!Validaciones.isNullOrEmpty(usuarioLdapAnalista.getNombreCompleto())){
					valorModelo.setNombreApellidoAnalista(usuarioLdapAnalista.getNombreCompleto());
				}
			}
			
			if (!Validaciones.isNullOrEmpty(valorDto.getIdCopropietario())) {
				UsuarioLdapDto usuarioLdap = ldapServicio.buscarUsuarioPorUidEnMemoria(valorDto.getIdCopropietario());
				if(!Validaciones.isNullOrEmpty(usuarioLdap.getNombreCompleto())){
					valorModelo.setNombreApellidoCopropietario(usuarioLdap.getNombreCompleto());
				}
			}

			valorModelo.setReferenciaValor(valorDto.getReferenciaValor());
			valorModelo.setNombreApellidoAutorizante(valorDto.getNombreApellidoAutorizante());
			
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		return valorModelo;
	}
	
	
	public static void mapearEmpresasAsociadas(String[] empresas, Modelo vo) {
		
		SiNoEnum permiteTAEnum = SiNoEnum.NO;
		SiNoEnum permiteTPEnum = SiNoEnum.NO;
		SiNoEnum permiteCVEnum = SiNoEnum.NO;
		SiNoEnum permiteNXEnum = SiNoEnum.NO;
		
		for (int indice = 0; indice < empresas.length; indice++) {
			if(Constantes.EMPRESA_TELECOM_ARGENTINA.equals(empresas[indice])){
				permiteTAEnum = SiNoEnum.SI;
			}
			if(Constantes.EMPRESA_TELECOM_PERSONAL.equals(empresas[indice])){
				permiteTPEnum = SiNoEnum.SI;
			}
			if(Constantes.EMPRESA_CABLEVISION.equals(empresas[indice])){
				permiteCVEnum = SiNoEnum.SI;
			}
			if(Constantes.EMPRESA_NEXTEL.equals(empresas[indice])){
				permiteNXEnum = SiNoEnum.SI;
			}
		}
		
		ShvValValor modelo = (ShvValValor) vo;
		
		modelo.setPermiteUsoTA(permiteTAEnum);
		modelo.setPermiteUsoTP(permiteTPEnum);
		modelo.setPermiteUsoCV(permiteCVEnum);
		modelo.setPermiteUsoNX(permiteNXEnum);
		modelo.setPermiteUsoFT(SiNoEnum.NO);
	}

	/**************************************************************************************
	 * Getters & Setters
	 **************************************************************************************/

	public IEmpresaDao getEmpresaDao() {
		return empresaDao;
	}

	public void setEmpresaDao(IEmpresaDao empresaDao) {
		this.empresaDao = empresaDao;
	}

	public ISegmentoDao getSegmentoDao() {
		return segmentoDao;
	}

	public void setSegmentoDao(ISegmentoDao segmentoDao) {
		this.segmentoDao = segmentoDao;
	}

	public IAcuerdoDao getAcuerdoDao() {
		return acuerdoDao;
	}

	public void setAcuerdoDao(IAcuerdoDao acuerdoDao) {
		this.acuerdoDao = acuerdoDao;
	}

	public IMotivoDao getMotivoDao() {
		return motivoDao;
	}

	public void setMotivoDao(IMotivoDao motivoDao) {
		this.motivoDao = motivoDao;
	}

	public ITipoValorDao getTipoValorDao() {
		return tipoValorDao;
	}

	public void setTipoValorDao(ITipoValorDao tipoValorDao) {
		this.tipoValorDao = tipoValorDao;
	}

	public IValorConstanciaRecepcionServicio getValorConstanciaRecepcionServicio() {
		return valorConstanciaRecepcionServicio;
	}

	public void setValorConstanciaRecepcionServicio(
			IValorConstanciaRecepcionServicio valorConstanciaRecepcionServicio) {
		this.valorConstanciaRecepcionServicio = valorConstanciaRecepcionServicio;
	}

}
