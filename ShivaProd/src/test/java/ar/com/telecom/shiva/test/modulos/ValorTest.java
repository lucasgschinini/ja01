package ar.com.telecom.shiva.test.modulos;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.negocio.servicios.IValorServicio;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;
import ar.com.telecom.shiva.presentacion.bean.filtro.BoletaConValorFiltro;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;


public class ValorTest extends SoporteContextoSpringTest {

	@Autowired IValorServicio valorServicio;
	
	@Test
	public void testConfirmarAvisoDePago() {
		
		try {
			for (int i = 2966; i < 23484; i++) {
				Long idValor = new Long(i);
				BoletaConValorFiltro filtro = new BoletaConValorFiltro();
				filtro.setIdValorFiltro(idValor.toString());
				
				System.out.println(Utilidad.formatDateAndTimeFull(new Date()) + " Antes de buscar el valor");
				ArrayList<ValorDto> valores = valorServicio.buscarValores(filtro);
				System.out.println(Utilidad.formatDateAndTimeFull(new Date()) + " Despues de buscar el valor");
				
				ValorDto valorDto = valores.get(0);
				valorDto.setUsuarioModificacion("SHV529234");
			
				try {
					System.out.println(Utilidad.formatDateAndTimeFull(new Date()) + " Antes de confirmar el valor");
					valorServicio.confirmarAvisoDePago(valorDto);
					System.out.println(Utilidad.formatDateAndTimeFull(new Date()) + " Despues de confirmar el valor");
				} catch (NegocioExcepcion e) {
					e.printStackTrace();
				}
				
				System.out.println(Utilidad.formatDateAndTimeFull(new Date()) + " Valor confirmado: " + valorDto.getIdValor() + " Tipo: " + valorDto.getTipoValor()); 
				System.out.println(" "); 
			}
			
		} catch (NegocioExcepcion e) {
			e.printStackTrace();
		}
	}
	
	
	//@Test
	//@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void test_InsertarValorEfectivo() throws Exception{
		System.out.println("Insertar un valor Efectivo------");
		
		try {
			ValorDto valorEfectivoDto = new ValorDto();
			
			valorEfectivoDto.setIdEmpresa("TA");
			valorEfectivoDto.setEmpresa("Telecom Argentina");
			valorEfectivoDto.setIdSegmento("OCO");
			valorEfectivoDto.setSegmento("Residencial");
			valorEfectivoDto.setCodCliente("1401302924");
			valorEfectivoDto.setCodClienteAgrupador("3030277701");
			valorEfectivoDto.setRazonSocialClienteAgrupador("GONPEREZ, RAMPEREZ");
			valorEfectivoDto.setIdAnalista("SHV182809");
			valorEfectivoDto.setImporte("345,00");
			valorEfectivoDto.setIdAcuerdo("29");
			valorEfectivoDto.setUsuarioModificacion("SHV182809");
			valorEfectivoDto.setNumeroBoleta("32451");
			
			valorEfectivoDto.setIdTipoValor(String.valueOf(TipoValorEnum.EFECTIVO.getIdTipoValor()));
			valorEfectivoDto.setTipoValor(TipoValorEnum.EFECTIVO.getDescripcion());
			
			ShvValValor valor = valorServicio.crearValor(valorEfectivoDto);
			
			System.out.println("Valor Efectivo creado con ID ----->  "+valor.getIdValor());
			
			assert(valor!=null);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	//@Test
	//@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void test_InsertarValorCheque() throws Exception{
		System.out.println("Insertar un valor Cheque------");
		
		try {
			ValorDto valorChequeDto = new ValorDto();
			
			valorChequeDto.setIdEmpresa("TA");
			valorChequeDto.setEmpresa("Telecom Argentina");
			valorChequeDto.setIdSegmento("OCO");
			valorChequeDto.setSegmento("Residencial");
			valorChequeDto.setCodCliente("1401302924");
			valorChequeDto.setCodClienteAgrupador("3030277701");
			valorChequeDto.setRazonSocialClienteAgrupador("GONPEREZ, RAMPEREZ");
			valorChequeDto.setIdAnalista("SHV182809");
			valorChequeDto.setImporte("78.359,05");
			valorChequeDto.setIdAcuerdo("29");
			valorChequeDto.setUsuarioModificacion("SHV182809");
			valorChequeDto.setNumeroBoleta("32451");
			valorChequeDto.setIdBancoOrigen("0072");
			valorChequeDto.setBancoOrigen("SANTANDER RIO");
			valorChequeDto.setNumeroCheque("654781234");
			
			valorChequeDto.setIdTipoValor(String.valueOf(TipoValorEnum.CHEQUE.getIdTipoValor()));
			valorChequeDto.setTipoValor(TipoValorEnum.CHEQUE.getDescripcion());
			
			ShvValValor valor = valorServicio.crearValor(valorChequeDto);
			
			System.out.println("Valor Cheque creado con ID ----->  "+valor.getIdValor());
			
			assert(valor!=null);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	//@Test
	//@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void test_InsertarValorChequeDiferido() throws Exception{
		System.out.println("Insertar un valor Cheque Diferido------");
		
		try {
			ValorDto valorChequeDiferidoDto = new ValorDto();
			
			valorChequeDiferidoDto.setIdEmpresa("TA");
			valorChequeDiferidoDto.setEmpresa("Telecom Argentina");
			valorChequeDiferidoDto.setIdSegmento("OCO");
			valorChequeDiferidoDto.setSegmento("Residencial");
			valorChequeDiferidoDto.setCodCliente("1401302924");
			valorChequeDiferidoDto.setCodClienteAgrupador("3030277701");
			valorChequeDiferidoDto.setRazonSocialClienteAgrupador("GONPEREZ, RAMPEREZ");
			valorChequeDiferidoDto.setIdAnalista("SHV182809");
			valorChequeDiferidoDto.setImporte("95.749,05");
			valorChequeDiferidoDto.setIdAcuerdo("29");
			valorChequeDiferidoDto.setUsuarioModificacion("SHV182809");
			valorChequeDiferidoDto.setNumeroBoleta("487551");
			valorChequeDiferidoDto.setIdBancoOrigen("0072");
			valorChequeDiferidoDto.setBancoOrigen("SANTANDER RIO");
			valorChequeDiferidoDto.setNumeroCheque("30011234");
			valorChequeDiferidoDto.setFechaValorVto(new Date());
			
			valorChequeDiferidoDto.setIdTipoValor(String.valueOf(TipoValorEnum.CHEQUE_DIFERIDO.getIdTipoValor()));
			valorChequeDiferidoDto.setTipoValor(TipoValorEnum.CHEQUE_DIFERIDO.getDescripcion());
			
			ShvValValor valor = valorServicio.crearValor(valorChequeDiferidoDto);
			
			System.out.println("Valor Cheque Diferido creado con ID ----->  "+valor.getIdValor());
			
			assert(valor!=null);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	//@Test
	//@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void test_InsertarValorInterdeposito() throws Exception{
		System.out.println("Insertar un valor Interdeposito------");
		
		try {
			ValorDto valorInterdepositoDto = new ValorDto();
			
			valorInterdepositoDto.setIdEmpresa("TA");
			valorInterdepositoDto.setEmpresa("Telecom Argentina");
			valorInterdepositoDto.setIdSegmento("OCO");
			valorInterdepositoDto.setSegmento("Residencial");
			valorInterdepositoDto.setCodCliente("1401302924");
			valorInterdepositoDto.setCodClienteAgrupador("3030277701");
			valorInterdepositoDto.setRazonSocialClienteAgrupador("GONPEREZ, RAMPEREZ");
			valorInterdepositoDto.setIdAnalista("SHV182809");
			valorInterdepositoDto.setImporte("95.749,05");
			valorInterdepositoDto.setIdAcuerdo("29");
			valorInterdepositoDto.setUsuarioModificacion("SHV182809");
			valorInterdepositoDto.setNumeroBoleta("487551");
			valorInterdepositoDto.setIdBancoOrigen("0072");
			valorInterdepositoDto.setBancoOrigen("SANTANDER RIO");
			valorInterdepositoDto.setNumeroInterdepositoCdif("30011234");
			valorInterdepositoDto.setCodOrganismo("9874213234");
			valorInterdepositoDto.setFechaValorVto(new Date());
			
			valorInterdepositoDto.setIdTipoValor(String.valueOf(TipoValorEnum.INTERDEPÓSITO.getIdTipoValor()));
			valorInterdepositoDto.setTipoValor(TipoValorEnum.INTERDEPÓSITO.getDescripcion());
			
			ShvValValor valor = valorServicio.crearValor(valorInterdepositoDto);
			
			System.out.println("Valor Interdeposito creado con ID ----->  "+valor.getIdValor());
			
			assert(valor!=null);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	//@Test
	//@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void test_InsertarValorTransferencia() throws Exception{
		System.out.println("Insertar un valor Transferencia------");
		
		try {
			ValorDto valorTransferenciaDto = new ValorDto();
			
			valorTransferenciaDto.setIdEmpresa("TA");
			valorTransferenciaDto.setEmpresa("Telecom Argentina");
			valorTransferenciaDto.setIdSegmento("OCO");
			valorTransferenciaDto.setSegmento("Residencial");
			valorTransferenciaDto.setCodCliente("1401302924");
			valorTransferenciaDto.setCodClienteAgrupador("3030277701");
			valorTransferenciaDto.setRazonSocialClienteAgrupador("GONPEREZ, RAMPEREZ");
			valorTransferenciaDto.setIdAnalista("SHV182809");
			valorTransferenciaDto.setImporte("95.749,05");
			valorTransferenciaDto.setIdAcuerdo("29");
			valorTransferenciaDto.setUsuarioModificacion("SHV182809");
			valorTransferenciaDto.setNumeroBoleta("487551");
			valorTransferenciaDto.setIdBancoOrigen("0072");
			valorTransferenciaDto.setBancoOrigen("SANTANDER RIO");
			valorTransferenciaDto.setNumeroReferencia("30011234");
			valorTransferenciaDto.setCodOrganismo("9874213234");
			valorTransferenciaDto.setFechaValorVto(new Date());
			valorTransferenciaDto.setFechaTransferencia(new Date().toString());
			valorTransferenciaDto.setNumeroCuenta("23423423");
			
			valorTransferenciaDto.setIdTipoValor(String.valueOf(TipoValorEnum.TRANSFERENCIA.getIdTipoValor()));
			valorTransferenciaDto.setTipoValor(TipoValorEnum.TRANSFERENCIA.getDescripcion());
			
			ShvValValor valor = valorServicio.crearValor(valorTransferenciaDto);
			
			System.out.println("Valor Transferencia creado con ID ----->  "+valor.getIdValor());
			
			assert(valor!=null);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	//@Test
	//@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void test_InsertarValorBoletaEfectivo() throws Exception{
		System.out.println("Insertar un valor Boleta Efectivo------");
		
		try {
			ValorDto valorBoletaDepositoEfectivoDto = new ValorDto();
			
			valorBoletaDepositoEfectivoDto.setIdEmpresa("TA");
			valorBoletaDepositoEfectivoDto.setEmpresa("Telecom Argentina");
			valorBoletaDepositoEfectivoDto.setIdSegmento("OCO");
			valorBoletaDepositoEfectivoDto.setSegmento("Residencial");
			valorBoletaDepositoEfectivoDto.setCodCliente("1401302924");
			valorBoletaDepositoEfectivoDto.setCodClienteAgrupador("3030277701");
			valorBoletaDepositoEfectivoDto.setRazonSocialClienteAgrupador("GONPEREZ, RAMPEREZ");
			valorBoletaDepositoEfectivoDto.setIdAnalista("SHV182809");
			valorBoletaDepositoEfectivoDto.setImporte("95.749,05");
			valorBoletaDepositoEfectivoDto.setIdAcuerdo("29");
			valorBoletaDepositoEfectivoDto.setUsuarioModificacion("SHV182809");
			valorBoletaDepositoEfectivoDto.setNumeroBoleta("487559");
			valorBoletaDepositoEfectivoDto.setIdBancoOrigen("0072");
			valorBoletaDepositoEfectivoDto.setBancoOrigen("SANTANDER RIO");
			valorBoletaDepositoEfectivoDto.setNumeroReferencia("30011234");
			valorBoletaDepositoEfectivoDto.setCodOrganismo("9874213234");
			valorBoletaDepositoEfectivoDto.setFechaValorVto(new Date());
			valorBoletaDepositoEfectivoDto.setFechaTransferencia(new Date().toString());
			
			valorBoletaDepositoEfectivoDto.setIdBoleta("3700");
			valorBoletaDepositoEfectivoDto.setIdOrigen("1");
			
			valorBoletaDepositoEfectivoDto.setIdTipoValor(String.valueOf(TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO.getIdTipoValor()));
			valorBoletaDepositoEfectivoDto.setTipoValor(TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO.getDescripcion());
			
			ShvValValor valor = valorServicio.crearValor(valorBoletaDepositoEfectivoDto);
			
			System.out.println("Valor Boleta Efectivo creado con ID ----->  "+valor.getIdValor());
			
			assert(valor!=null);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	//@Test
	//@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void test_InsertarValorBoletaDepositoCheque() throws Exception{
		System.out.println("Insertar un valor Boleta Deposito Cheque------");
		
		try {
			ValorDto valorBoletaDepositoChequeDto = new ValorDto();
			
			valorBoletaDepositoChequeDto.setIdEmpresa("TA");
			valorBoletaDepositoChequeDto.setEmpresa("Telecom Argentina");
			valorBoletaDepositoChequeDto.setIdSegmento("OCO");
			valorBoletaDepositoChequeDto.setSegmento("Residencial");
			valorBoletaDepositoChequeDto.setCodCliente("1401302924");
			valorBoletaDepositoChequeDto.setCodClienteAgrupador("3030277701");
			valorBoletaDepositoChequeDto.setRazonSocialClienteAgrupador("GONPEREZ, RAMPEREZ");
			valorBoletaDepositoChequeDto.setIdAnalista("SHV182809");
			valorBoletaDepositoChequeDto.setImporte("95.749,05");
			valorBoletaDepositoChequeDto.setIdAcuerdo("29");
			valorBoletaDepositoChequeDto.setUsuarioModificacion("SHV182809");
			valorBoletaDepositoChequeDto.setNumeroReferencia("30011234");
			valorBoletaDepositoChequeDto.setCodOrganismo("9874213234");
			valorBoletaDepositoChequeDto.setFechaValorVto(new Date());
			valorBoletaDepositoChequeDto.setFechaTransferencia(new Date().toString());
			
			valorBoletaDepositoChequeDto.setIdBoleta("3700");
			valorBoletaDepositoChequeDto.setIdOrigen("1");
			
			valorBoletaDepositoChequeDto.setNumeroBoleta("487559");
			valorBoletaDepositoChequeDto.setIdBancoOrigen("0072");
			valorBoletaDepositoChequeDto.setBancoOrigen("SANTANDER RIO");
			valorBoletaDepositoChequeDto.setNumeroCheque("6874123");
			
			valorBoletaDepositoChequeDto.setIdTipoValor(String.valueOf(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoValor()));
			valorBoletaDepositoChequeDto.setTipoValor(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getDescripcion());
			
			ShvValValor valor = valorServicio.crearValor(valorBoletaDepositoChequeDto);
			
			System.out.println("Valor Boleta Deposito Cheque creado con ID ----->  "+valor.getIdValor());
			
			assert(valor!=null);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	//@Test
	//@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void test_InsertarValorBoletaDepositoChequePD() throws Exception{
		System.out.println("Insertar un valor Boleta Deposito Cheque PD------");
		
		try {
			ValorDto valorBoletaDepositoChequeDiferidoDto = new ValorDto();
			
			valorBoletaDepositoChequeDiferidoDto.setIdEmpresa("TA");
			valorBoletaDepositoChequeDiferidoDto.setEmpresa("Telecom Argentina");
			valorBoletaDepositoChequeDiferidoDto.setIdSegmento("OCO");
			valorBoletaDepositoChequeDiferidoDto.setSegmento("Residencial");
			valorBoletaDepositoChequeDiferidoDto.setCodCliente("1401302924");
			valorBoletaDepositoChequeDiferidoDto.setCodClienteAgrupador("3030277701");
			valorBoletaDepositoChequeDiferidoDto.setRazonSocialClienteAgrupador("GONPEREZ, RAMPEREZ");
			valorBoletaDepositoChequeDiferidoDto.setIdAnalista("SHV182809");
			valorBoletaDepositoChequeDiferidoDto.setImporte("95.749,05");
			valorBoletaDepositoChequeDiferidoDto.setIdAcuerdo("29");
			valorBoletaDepositoChequeDiferidoDto.setUsuarioModificacion("SHV182809");
			valorBoletaDepositoChequeDiferidoDto.setNumeroReferencia("30011234");
			valorBoletaDepositoChequeDiferidoDto.setCodOrganismo("9874213234");
			valorBoletaDepositoChequeDiferidoDto.setFechaValorVto(new Date());
			valorBoletaDepositoChequeDiferidoDto.setFechaTransferencia(new Date().toString());
			
			valorBoletaDepositoChequeDiferidoDto.setIdBoleta("3700");
			valorBoletaDepositoChequeDiferidoDto.setIdOrigen("1");
			
			valorBoletaDepositoChequeDiferidoDto.setNumeroBoleta("487559");
			valorBoletaDepositoChequeDiferidoDto.setIdBancoOrigen("0072");
			valorBoletaDepositoChequeDiferidoDto.setBancoOrigen("SANTANDER RIO");
			valorBoletaDepositoChequeDiferidoDto.setNumeroCheque("6874123");
			valorBoletaDepositoChequeDiferidoDto.setFechaVencimiento(new Date().toString());
			
			
			valorBoletaDepositoChequeDiferidoDto.setIdTipoValor(String.valueOf(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getIdTipoValor()));
			valorBoletaDepositoChequeDiferidoDto.setTipoValor(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getDescripcion());
			
			ShvValValor valor = valorServicio.crearValor(valorBoletaDepositoChequeDiferidoDto);
			
			System.out.println("Valor Boleta Deposito Cheque PD creado con ID ----->  "+valor.getIdValor());
			
			assert(valor!=null);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	
	//@Test
	public void buscarFechaXIDTest () {
		String fecha = "2014-10-31T11:36:22.511";
		Serializable id = '1';
		Long timeStamp ;
		Date date = new Date() ;
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		try {
			 date = formatoDelTexto.parse(fecha);
		} catch (ParseException e1) {
			System.out.println("fallo el parseo "+ e1);
		}
		timeStamp = date.getTime();
			
		try {
			valorServicio.verificarConcurrencia(id, timeStamp);
			System.out.println("Todo OK");

		} catch (NegocioExcepcion e) {
			System.out.println("La fecha ingresada es posterior a la fecha_modificacion de workflow estado ");
			e.printStackTrace();
		}
	}
}
