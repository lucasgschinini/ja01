package ar.com.telecom.shiva.negocio.mapeos;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCreditoEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.ldap.ILdapServicio;
import ar.com.telecom.shiva.base.mapeadores.Mapeador;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.negocio.servicios.IClienteServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.negocio.servicios.ITeamComercialServicio;
import ar.com.telecom.shiva.negocio.servicios.bean.Bean;
import ar.com.telecom.shiva.negocio.servicios.bean.ClienteBean;
import ar.com.telecom.shiva.negocio.servicios.bean.ConsultaSoporteResultadoBusquedaChequeRechazado;
import ar.com.telecom.shiva.persistencia.dao.IAcuerdoDao;
import ar.com.telecom.shiva.persistencia.dao.IBancoDao;
import ar.com.telecom.shiva.persistencia.dao.IEmpresaDao;
import ar.com.telecom.shiva.persistencia.dao.IMotivoLegajoDao;
import ar.com.telecom.shiva.persistencia.dao.ISegmentoDao;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjChequeRechazado;
import ar.com.telecom.shiva.persistencia.modelo.ShvLgjChequeRechazadoDetalleCliente;
import ar.com.telecom.shiva.presentacion.bean.dto.ClienteDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ConsultaSoporteResultadoBusquedaChequeRechazadoDto;
import ar.com.telecom.shiva.presentacion.bean.dto.ldap.UsuarioLdapDto;

public class ChequeRechazadoMapeador extends Mapeador {
	
	@Autowired 
	ICobroOnlineServicio cobroOnlineServicio;
	
	@Autowired 
	private IEmpresaDao empresaDao;
	
	@Autowired 
	private ISegmentoDao segmentoDao;
	
	@Autowired
	private IMotivoLegajoDao motivoLegajoDao;
	
	@Autowired
	private IBancoDao bancoDao;
	
	@Autowired
	private IAcuerdoDao acuerdoDao;
	
	@Autowired
	private ITeamComercialServicio teamComercialServicio;
	
	@Autowired 
	private ILdapServicio ldapServicio;
	
	@Autowired 
	private IClienteServicio clienteServicio;
	

	public DTO map(Modelo mo) throws NegocioExcepcion {
	ShvLgjChequeRechazado modelo = (ShvLgjChequeRechazado) mo;

		ConsultaSoporteResultadoBusquedaChequeRechazadoDto dto = new ConsultaSoporteResultadoBusquedaChequeRechazadoDto();
	
		dto.setSistemaOrigen(modelo.getSistemaOrigen().getDescripcion()); 
	
		// Campos exlusivos para cheque asociado SHIVA
		if (SistemaEnum.SHIVA.equals(modelo.getSistemaOrigen())) {
			dto.setIdInternoValor(modelo.getIdValor().toString());
		}
		
		// Campos exlusivos para cheque asociado ICE
		if (SistemaEnum.ICE.equals(SistemaEnum.getEnumByDescripcion(dto.getSistemaOrigen()))) {
			if (!Validaciones.isObjectNull(modelo.getNumeroCheque())) {
				dto.setNroCheque(modelo.getNumeroCheque());
			}
			if (!Validaciones.isObjectNull(modelo.getBancoOrigen())) {
				dto.setCodBancoOrigen(modelo.getBancoOrigen().getIdBanco().toString());
				dto.setDescripcionBancoOrigen(modelo.getBancoOrigen().getDescripcion());
			}
			
			if (!Validaciones.isObjectNull(modelo.getMoneda())) {
				dto.setMoneda(modelo.getMoneda().name());
			}
			if (!Validaciones.isObjectNull(modelo.getImporte())) {
				dto.setImporte(Utilidad.formatCurrency(modelo.getImporte(), true, true, 2));
			}
			if (!Validaciones.isObjectNull(modelo.getImporteCheque())) {
				dto.setImporteCheque(Utilidad.formatCurrency(modelo.getImporteCheque(), true, true, 2));
			}
			if (!Validaciones.isObjectNull(modelo.getFechaDeposito())) {
				dto.setFechaDeposito(Utilidad.formatDatePicker(modelo.getFechaDeposito()));
			}
			
		
			if (!Validaciones.isObjectNull(modelo.getCodigoBancoDeCobro())) {
				dto.setCodBancoDeCobro(modelo.getCodigoBancoDeCobro());
			}
		}
		if (Validaciones.isCollectionNotEmpty(modelo.getClientes())) {
			for (ShvLgjChequeRechazadoDetalleCliente clienteSiebel : modelo.getClientes()) {
				ClienteDto clienteSiebelDto = new ClienteDto();
				if (!Validaciones.isObjectNull(clienteSiebel.getIdClienteLegado())) {
					clienteSiebelDto.setIdClienteLegado(clienteSiebel.getIdClienteLegado().toString());
				}
				
				clienteSiebelDto.setRazonSocial(clienteSiebel.getRazonSocial());
			dto.getClientes().add(clienteSiebelDto);
			}
		}
		return dto;
	}
	
	/**
	 * 
	 * @param javaBean
	 * @return
	 * @throws NegocioExcepcion
	 */
	public DTO map(Bean javaBean) throws NegocioExcepcion {
		try {
			ConsultaSoporteResultadoBusquedaChequeRechazadoDto dto = new ConsultaSoporteResultadoBusquedaChequeRechazadoDto();
			ConsultaSoporteResultadoBusquedaChequeRechazado bean = (ConsultaSoporteResultadoBusquedaChequeRechazado) javaBean;

			dto.setSistemaOrigen(bean.getSistemaOrigen().getDescripcion());
			
			TipoCreditoEnum tipoCreditoEnum = TipoCreditoEnum.getEnumByIdTipoValor(bean.getIdTipoCheque());
			
			dto.setTipoCheque(tipoCreditoEnum!=null?tipoCreditoEnum.getDescripcion():null);
			dto.setIdTipoCheque(tipoCreditoEnum!=null?tipoCreditoEnum.getIdTipoValor():null);
			////Lo uso en la pantalla
	
			dto.setCodBancoOrigen(bean.getCodBancoOrigen());
			dto.setDescripcionBancoOrigen(bean.getDescripcionBancoOrigen());
			dto.setNroCheque(bean.getNroCheque());
			
			if (!Validaciones.isObjectNull((bean.getFechaDeposito()))) {
				dto.setFechaDeposito(Utilidad.formatDatePicker(bean.getFechaDeposito()));
			}
			if (!Validaciones.isObjectNull((bean.getFechaRecepcion()))) {
				dto.setFechaRecepcion(Utilidad.formatDatePicker(bean.getFechaRecepcion()));
			}
			if (!Validaciones.isObjectNull((bean.getFechaVenc()))) {
				dto.setFechaVenc(Utilidad.formatDatePicker(bean.getFechaVenc()));
			}
			
			dto.setMoneda(bean.getMoneda().name());
			dto.setImporteCheque(Utilidad.formatCurrency(bean.getImporteCheque(), true, true, 2));
			dto.setImporte(Utilidad.formatCurrency(bean.getImporte(), true, true, 2));
			dto.setAcuerdo(bean.getAcuerdo());
			dto.setBancoDeposito(bean.getBancoDeposito());
			dto.setCuentaDeposito(bean.getCuentaDeposito());
			dto.setEstado(bean.getEstado());
			dto.setEmpresa(bean.getEmpresa());
			dto.setSegmento(bean.getSegmento());
			
			UsuarioLdapDto usuario;
			
			if(!Validaciones.isNullEmptyOrDash(bean.getAnalista())) {
				usuario = ldapServicio.buscarUsuarioPorUidEnMemoria(bean.getAnalista()); 
				dto.setAnalista(usuario.getNombreCompleto());
			}
			
			if(!Validaciones.isNullEmptyOrDash(bean.getCopropietario())) {
				usuario = ldapServicio.buscarUsuarioPorUidEnMemoria(bean.getCopropietario()); 
				dto.setCopropietario(usuario.getNombreCompleto());
			}
			
			if (SistemaEnum.SHIVA.equals(bean.getSistemaOrigen())) {
				dto.setIdInternoValor(String.valueOf(bean.getIdInternoValor()));
			} else {
				dto.setIdInternoIce(String.valueOf(bean.getIdInternoIce()));
			}
			
			dto.setMontoARevertir(Utilidad.formatCurrency(bean.getMontoARevertir(), true, true, 2));

			dto.setCodBancoDeCobro(bean.getCodBancoDeCobro());
			
			if (Validaciones.isCollectionNotEmpty(bean.getClienteCheques())) {
				for (ClienteBean clienteSiebel : bean.getClienteCheques()) {
					ClienteDto clienteSiebelDto = new ClienteDto();
					clienteSiebelDto.setIdClienteLegado(clienteSiebel.getIdClienteLegado().toString());
					clienteSiebelDto.setRazonSocial(clienteSiebel.getRazonSocial());
					dto.getClientes().add(clienteSiebelDto);
				}
				
				Set<ClienteDto> setClientes = new HashSet<ClienteDto>(dto.getClientes());
				UsuarioLdapDto usuarioLdap = teamComercialServicio.obtenerAnalistaTeamComercial(setClientes); 
				
				if(!Validaciones.isObjectNull(usuarioLdap)) {
					dto.setAnalistaTeamComercial(usuarioLdap.getNombreCompleto());
				}
			}

			return dto;
		} catch (Exception e) {
			Traza.error(ChequeRechazadoMapeador.class, e.getMessage());
			throw new NegocioExcepcion(e.getMessage(), e);
		}
	}

	@Override
	public Modelo map(DTO dto, Modelo vo) throws NegocioExcepcion {
		
		ShvLgjChequeRechazado modelo = (ShvLgjChequeRechazado)(vo != null ? vo : new ShvLgjChequeRechazado());
		
		try {
			
			ConsultaSoporteResultadoBusquedaChequeRechazadoDto chequeDto = (ConsultaSoporteResultadoBusquedaChequeRechazadoDto) dto;

			modelo.setSistemaOrigen(SistemaEnum.getEnumByDescripcion(chequeDto.getSistemaOrigen())); 

			// Campos exlusivos para cheque asociado SHIVA
			if (SistemaEnum.SHIVA.equals(SistemaEnum.getEnumByDescripcion(chequeDto.getSistemaOrigen()))) {
				modelo.setIdValor(Long.valueOf(chequeDto.getIdInternoValor()));
				for (ClienteDto cliente : chequeDto.getClientes()) {
					try{
						//SalidaSiebelConsultarClienteWS datos = clienteSiebelServicio.consultarClienteSiebel(cliente.getIdClienteLegado());
						ClienteBean clienteBean = this.clienteServicio.consultarCliente(cliente.getIdClienteLegado());
						if (!Validaciones.isObjectNull(clienteBean)) {
							cliente.setCuit(clienteBean.getCuit());
						}
					} catch( NegocioExcepcion e) {
						throw new NegocioExcepcion("Error en Siebel", e);
					}
				}
			}
			
			// Campos exlusivos para cheque asociado ICE
			if (SistemaEnum.ICE.equals(SistemaEnum.getEnumByDescripcion(chequeDto.getSistemaOrigen()))) {
				modelo.setNumeroCheque(chequeDto.getNroCheque());
				modelo.setBancoOrigen(bancoDao.buscarBanco(chequeDto.getCodBancoOrigen()));
	
				modelo.setMoneda(MonedaEnum.getEnumByName(chequeDto.getMoneda()));
				modelo.setImporte(Utilidad.stringToBigDecimal(chequeDto.getImporte()));
				modelo.setImporteCheque(Utilidad.stringToBigDecimal(chequeDto.getImporteCheque()));
				modelo.setFechaDeposito(Utilidad.parseDatePickerString(chequeDto.getFechaDeposito()));
				
				modelo.setIdInternoIce(Long.valueOf(chequeDto.getIdInternoIce()));
	
				if (!Validaciones.isNullEmptyOrDash(chequeDto.getCodBancoDeCobro())) {
					modelo.setCodigoBancoDeCobro(chequeDto.getCodBancoDeCobro());
				}
				
				if (Validaciones.isCollectionNotEmpty(chequeDto.getClientes())) {
					
					for (ClienteDto clienteSiebelDto : chequeDto.getClientes()) {
					
						ShvLgjChequeRechazadoDetalleCliente detalleCliente = new ShvLgjChequeRechazadoDetalleCliente();
						
						if(!Validaciones.isNullOrEmpty(clienteSiebelDto.getIdClienteLegado())){
							detalleCliente.setIdClienteLegado(new Long(clienteSiebelDto.getIdClienteLegado()));
							//SalidaSiebelConsultarClienteWS cliente = clienteSiebelServicio.consultarClienteSiebel(clienteSiebelDto.getIdClienteLegado());
							ClienteBean clienteBean = this.clienteServicio.consultarCliente(clienteSiebelDto.getIdClienteLegado());
							if (!Validaciones.isObjectNull(clienteBean)) {
								detalleCliente.setCuit(clienteBean.getCuit());
							}
						}
						detalleCliente.setRazonSocial(clienteSiebelDto.getRazonSocial());
						detalleCliente.setChequeRechazado(modelo);
						
						modelo.getClientes().add(detalleCliente);
					}
					
				}
			}
			
			// Mapeo de ShvLgjChequeRechazadoDetalleCliente
			if (Validaciones.isCollectionNotEmpty(chequeDto.getClientes()) && !SistemaEnum.ICE.equals(SistemaEnum.getEnumByDescripcion(chequeDto.getSistemaOrigen()))) {

				for (ClienteDto clienteSiebelDto : chequeDto.getClientes()) {
					ShvLgjChequeRechazadoDetalleCliente detalleCliente = new ShvLgjChequeRechazadoDetalleCliente();
					if(!Validaciones.isNullOrEmpty(clienteSiebelDto.getIdClienteLegado())){
						detalleCliente.setIdClienteLegado(new Long(clienteSiebelDto.getIdClienteLegado()));
					}
					detalleCliente.setRazonSocial(clienteSiebelDto.getRazonSocial());
					detalleCliente.setCuit(clienteSiebelDto.getCuit());
					detalleCliente.setChequeRechazado(modelo);
					
					modelo.getClientes().add(detalleCliente);
				}
			}

		} catch (PersistenciaExcepcion e) {
			Traza.error(ChequeRechazadoMapeador.class, e.getMessage());
			throw new NegocioExcepcion(e.getMessage(), e);
		} catch (ParseException e) {
			Traza.error(ChequeRechazadoMapeador.class, e.getMessage());
			throw new NegocioExcepcion(e.getMessage(), e);
		}
		
		return modelo;
	}
}
