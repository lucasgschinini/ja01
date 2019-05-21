package ar.com.telecom.shiva.test.modulos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.enumeradores.EstadoRegistroOperacionMasivaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoArchivoOperacionMasivaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoOrigenEnum;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.base.excepciones.otros.WorkflowExcepcion;
import ar.com.telecom.shiva.base.registros.datos.entrada.enumeradores.MicOperacionMasivaCamposEntradaEnum;
import ar.com.telecom.shiva.base.utils.ControlArchivo;
import ar.com.telecom.shiva.negocio.servicios.IOperacionMasivaArchivaServicio;
import ar.com.telecom.shiva.negocio.servicios.IOperacionMasivaRegistroServicio;
import ar.com.telecom.shiva.negocio.servicios.IOperacionMasivaServicio;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.dao.IOperacionMasivaDao;
import ar.com.telecom.shiva.persistencia.dao.IRegistroOperacionMasivaDao;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasOperacionMasiva;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistro;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroDesistimiento;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasRegistroGanancias;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class OperacionMasivaRegistrosBatchTest extends SoporteContextoSpringTest {
	 
	@Autowired
	public IRegistroOperacionMasivaDao registroOperacionMasivaDao;
	@Autowired
	IOperacionMasivaServicio operacionMasivaServicio;
	@Autowired
	IOperacionMasivaRegistroServicio operacionMasivaRegistroServicio;
	@Autowired
	IOperacionMasivaArchivaServicio operacionMasivaArchivaServicio;
	@Autowired
	private IOperacionMasivaDao operacionMasivaDao;
	/**
	 * @throws NegocioExcepcion 
	 * @throws WorkflowExcepcion
	 */
	//@Test
	@Test
	public void test() {
		System.out.println(">----> " + operacionMasivaRegistroServicio.test());
	}
	@Test
	public void _1() throws PersistenciaExcepcion {
		List<Long> ids = registroOperacionMasivaDao.obtenerIdOperacionShivaReutilizable();
		System.out.println(">--111--> " + ids.get(0));
	}
	@Test
	public void testBuscarRegistrosOperacionMasivaByEstadoAndEstadosOperacionMasiva() throws PersistenciaExcepcion {
		List<ShvMasRegistro> listaShvMasRegistro = new ArrayList<ShvMasRegistro>();

//		for(TipoArchivoOperacionMasivaEnum tipo : TipoArchivoOperacionMasivaEnum.values()) {
//			if (
//				!TipoArchivoOperacionMasivaEnum.SALIDA_MIC.equals(tipo) &&
//				!TipoArchivoOperacionMasivaEnum.ENTRADA_MIC.equals(tipo)
//			) {
				listaShvMasRegistro.addAll(
					registroOperacionMasivaDao.buscarRegistrosOperacionMasivaByEstadoAndEstadosOperacionMasiva(
							EstadoRegistroOperacionMasivaEnum.PENDIENTE_DATOS_SIEBEL,
							//tipo,
							Arrays.asList(
								new Estado[] {
									Estado.MAS_PENDIENTE_PROCESAR
									,Estado.MAS_PENDIENTE_APROBACION
				})));
//			}
//		}
		System.out.println("-->Test _ BuscarRegistrosOperacionMasivaByEstadoAndEstadosOperacionMasiva");
		for (ShvMasRegistro sh : listaShvMasRegistro) {
			System.out.println(">-- id operacionMasiva: " +  sh.getOperacionMasiva().getIdOperacionMasiva());
			System.out.println(">-- Wf estado: " +  sh.getOperacionMasiva().getWorkFlow().getWorkflowEstado().getEstado());
			
			System.out.println(">-- id registro: " +  sh.getIdRegistro());
			System.out.println(">-- id classn: " +  sh.getClass());
			System.out.println(">-- id operacion: " +  sh.getIdOperacion());
			System.out.println(">-- Reg estado: " +  sh.getEstado());
		}
 		
	}
	@Test
	public void testGenerarArchivoEntradaMic() throws PersistenciaExcepcion, NegocioExcepcion {
		operacionMasivaServicio.generarArchivoEntradaMic();
	}

	
	@Test
	public void procesarArchivosInterfazMicSalida() throws NegocioExcepcion, ShivaExcepcion {
	//	operacionMasivaServicio.procesarArchivosInterfazMicSalida();
	}
	@Test
	public void buscarOperacionMasivaByEstado() throws PersistenciaExcepcion {
		List<ShvMasOperacionMasiva> mas = operacionMasivaDao.buscarOperacionesMasivasByEstado(Estado.MAS_PROCESO_IMPUTACION);
	
		System.out.println("-->Test _ BuscarRegistrosOperacionMasivaByEstadoAndEstadosOperacionMasiva");
		for (ShvMasOperacionMasiva sh : mas) {
			System.out.println(">-- id operacionMasiva: " +  sh.getIdOperacionMasiva());
			System.out.println(">-- Wf estado: " +  sh.getWorkFlow().getWorkflowEstado().getEstado());
			
			System.out.println(">-- id registro: " +  sh.getRegistros().size());
		}
	}
	@Test
	public void calcular() throws PersistenciaExcepcion, NegocioExcepcion {
	operacionMasivaServicio.actualizarContadoresEnOperacionesMasivas();
	}
	@Test
	@Transactional(readOnly = false, rollbackFor = {Exception.class, WorkflowExcepcion.class}, propagation=Propagation.REQUIRED)
	public void modificarRegistri() throws PersistenciaExcepcion {
//		registroOperacionMasivaDao.modificar(EstadoRegistroOperacionMasivaEnum.PROCESO_IMPUTACION, 2693l, new Date(), "SHIVA");
	}
//	
	
	@Test
	public void modifica() throws ShivaExcepcion {
		ControlArchivo.moverArchivo("SHIVA.OperacionesMasivas.MIC.salida.20160211.txt",Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.operacionesMasivas.salida"), Propiedades.SHIVA_PROPIEDADES.getString("batch.directorio.operacionesMasivas.salida"));
		
	}
	@Test
	public void crear() {
		System.out.println("asas\r\n");
	}
	
	@Test
	public void vercampos() {
		String mensaje = "D|0000000784|01|0200110|00001|000000000071|20160220|0000000100|0000001000|0000010000|0000100000|0000000000|0000000000|0000000000|0000000000|4000067215|001|FAC|  |                    | |                  |1234567890|A|0111|12345698|984894262020121|20160221|0000002000|0000002001|0000002000|00|POTENCIAL |N|N|0000000100|0000000100|0000000100|01|ENV. AL CLIENTE     |D|ANULADO   |Q|QUIEBRA |20160222|101|111|20160223|20160224|Razón social cliente          |16|30709711640|01|A001|51|FACT. FLEXCAB|20160225|1|01234|0000000100|0000001000|0000010000|4000067215|012|CRE|CRE|u|4402|00658518|984894262020122|0|          |0000000200|0000000100|20160420|20160421|20160422|20160423|A|ANULADO   |Q|QUIEBRA |20|AVISO DE INCOMUNIC. |CRIPEREZ PEREZ                |1A|30709711640  |02|1   |52|FACT. BAJAS    |0000000200|20160425|1|12345|0000009032|0000000010|0000000010|01|POTENCIAL |0000009032|OK |          |                                                                                                    |OK |          ||   |          |                                                                                                    ";
		String[] mensaje1 = mensaje.split("\\|");
		System.out.println(">-.--> " + mensaje1.length);
		for (int s = 0; s < mensaje1.length ; s++) {
			System.out.println("--> [" + s + "] " + mensaje1[s] + " [" +mensaje1[s].length() + " ]" );
			if ("".equals(mensaje1[s])) {
				System.out.println("BASIO11111111111111111111111111111111111111111111111111111111111111111111111111111111");
			}
			System.out.println("--> [" + MicOperacionMasivaCamposEntradaEnum.getEnumByPosicion(s).name() + "]");
			
		}
		
	}
	
	
	@Test
	public void testGestionabilidadDesesT() throws NegocioExcepcion {
		
		List<ShvMasRegistro> listaShvMasRegistro = new ArrayList<ShvMasRegistro>();
		
		ShvMasRegistroDesistimiento des = new ShvMasRegistroDesistimiento();
		
		des.setNumeroReferenciaFactura(828727139110146l); // marca cobroen proceso
		
		listaShvMasRegistro.add(des);
		
		des =  new ShvMasRegistroDesistimiento();
		
		des.setNumeroReferenciaFactura(875057008010159l);
		listaShvMasRegistro.add(des);
		List<ShvMasRegistro> lista = operacionMasivaRegistroServicio.realizarGestionabiliadRegistrosOperacionMasiva(listaShvMasRegistro);
		
		
		
		for (ShvMasRegistro r : lista) {
			System.out.println(">---> " + ((ShvMasRegistroDesistimiento)r).getNumeroReferenciaFactura() + " ... " + r.getDescripcionErrorRegistro());
		}
		
	}
	
	
	@Test
	public void testGestionabilidadGanaciaNC() throws NegocioExcepcion {
		
		List<ShvMasRegistro> listaShvMasRegistro = new ArrayList<ShvMasRegistro>();
		
		ShvMasRegistroGanancias des = new ShvMasRegistroGanancias();
		
		des.setNumeroReferenciaNC(750068695420019l);
		listaShvMasRegistro.add(des);
		
		des =  new ShvMasRegistroGanancias();
		
		des.setNumeroReferenciaNC(78737381120014l);
		listaShvMasRegistro.add(des);
		List<ShvMasRegistro> lista = operacionMasivaRegistroServicio.realizarGestionabiliadRegistrosOperacionMasiva(listaShvMasRegistro);
		
		
		
		for (ShvMasRegistro r : lista) {
			System.out.println(">---> " + ((ShvMasRegistroGanancias)r).getNumeroReferenciaNC()+ " ... " + r.getDescripcionErrorRegistro());
		}
		
	}
	
	@Test
	public void testGestionabilidadGanaciaRemamnete() throws NegocioExcepcion {
		
		List<ShvMasRegistro> listaShvMasRegistro = new ArrayList<ShvMasRegistro>();
		
		ShvMasRegistroGanancias des = new ShvMasRegistroGanancias();
		
		des.setTipoRemanente(TipoOrigenEnum.RT1);
		listaShvMasRegistro.add(des);
		
		des =  new ShvMasRegistroGanancias();
		
		des.setNumeroReferenciaNC(750068695420027l);
		listaShvMasRegistro.add(des);
		List<ShvMasRegistro> lista = operacionMasivaRegistroServicio.realizarGestionabiliadRegistrosOperacionMasiva(listaShvMasRegistro);
		
		
		
		for (ShvMasRegistro r : lista) {
			System.out.println(">---> " + ((ShvMasRegistroDesistimiento)r).getNumeroReferenciaFactura() + " ... " + r.getDescripcionErrorRegistro());
		}
	}
	@Test
	public void testGenerarRegistro() {
		OperacionMasivaDummieVo vo = new OperacionMasivaDummieVo();
		
		OperacionMasivaDummie dum = new OperacionMasivaDummie();
		
		
		vo.tipoComprobante = TipoComprobanteEnum.FAC;
		vo.estadoConceptoTercerosDeuda = SiNoEnum.NO;
		vo.clienteDebito = "4000067215";
		vo.clienteCredito = "4000067215";
		vo.numRefMicFac = "984894262020121";

		vo.errordebito = SiNoEnum.SI;
		vo.errorCredito = SiNoEnum.SI;
		
		vo.numRefMicCred = "984894262020122";
		vo.tipo = TipoArchivoOperacionMasivaEnum.DEUDA;
		vo.tipoCreito = "CRE";
		vo.clienteAcuerdo = "9032";
		vo.tipoCreito = "CRE";
		
		
		System.out.println("C|0000000003|20160210\r\n" + dum.crearRegistro(vo) + "\r\nC|0000000003|20160210\r\n");

//		MicOperacionMasivaCamposEntradaEnum key;
//		TipoArchivoOperacionMasivaEnum tipo;
//		TipoComprobanteEnum tipoComprobante;
//		SiNoEnum estadoConceptoTercerosDeuda;
//		String clienteDebito;
//		String clienteCredito;
//		String clienteAcuerdo;
//		String numRefMicFac;
//		int comprobante;
//		boolean marcaReclamoVacia;
//		String tipoCreito = "CRE";
//		String numRefMicCred;
//		SiNoEnum posibilidadDestraferencia;
//		
//		SiNoEnum errordebito;
//		SiNoEnum errorCredito;
//		SiNoEnum errorReintegro;
	}
	
	@Test
	public void testGenerarRegistroAplicarDeudaDuc() {
		OperacionMasivaDummieVo vo = new OperacionMasivaDummieVo();
		
		OperacionMasivaDummie dum = new OperacionMasivaDummie();
		
		
		vo.tipoComprobante = TipoComprobanteEnum.FAC;
		vo.estadoConceptoTercerosDeuda = SiNoEnum.NO;
		vo.clienteDebito = "4000067215";
		vo.clienteCredito = "4000067215";
		vo.numRefMicFac = "984894262020121";

		vo.errordebito = SiNoEnum.SI;
		vo.errorReintegro = SiNoEnum.SI;
		
		vo.numRefMicCred = "984894262020122";
		vo.tipo = TipoArchivoOperacionMasivaEnum.DSIST;
		vo.tipoCreito = "CRE";
		vo.clienteAcuerdo = "9032";
		vo.tipoCreito = "CRE";
		
		
		System.out.println("C|0000000003|20160210\r\n" + dum.crearRegistro(vo) + "\r\nC|0000000003|20160210\r\n");

//		MicOperacionMasivaCamposEntradaEnum key;
//		TipoArchivoOperacionMasivaEnum tipo;
//		TipoComprobanteEnum tipoComprobante;
//		SiNoEnum estadoConceptoTercerosDeuda;
//		String clienteDebito;
//		String clienteCredito;
//		String clienteAcuerdo;
//		String numRefMicFac;
//		int comprobante;
//		boolean marcaReclamoVacia;
//		String tipoCreito = "CRE";
//		String numRefMicCred;
//		SiNoEnum posibilidadDestraferencia;
//		
//		SiNoEnum errordebito;
//		SiNoEnum errorCredito;
//		SiNoEnum errorReintegro;
	}
	
	
	@Test
	public void testGenerarRegistroReintegro() {
		OperacionMasivaDummieVo vo = new OperacionMasivaDummieVo();
		
		OperacionMasivaDummie dum = new OperacionMasivaDummie();
		
		
		vo.tipoComprobante = TipoComprobanteEnum.FAC;
		vo.estadoConceptoTercerosDeuda = SiNoEnum.NO;
		vo.clienteDebito = "4000067215";
		vo.clienteCredito = "4000067215";
		vo.numRefMicFac = "984894262020121";

		vo.errordebito = SiNoEnum.SI;
		vo.errorReintegro = SiNoEnum.SI;
		
		vo.numRefMicCred = "984894262020122";
		vo.tipo = TipoArchivoOperacionMasivaEnum.REINT;
		vo.tipoCreito = "CRE";
		vo.clienteAcuerdo = "9032";
		vo.tipoCreito = "CRE";
		
		
		System.out.println("C|0000000003|20160210\r\n" + dum.crearRegistro(vo) + "\r\nC|0000000003|20160210\r\n");

//		MicOperacionMasivaCamposEntradaEnum key;
//		TipoArchivoOperacionMasivaEnum tipo;
//		TipoComprobanteEnum tipoComprobante;
//		SiNoEnum estadoConceptoTercerosDeuda;
//		String clienteDebito;
//		String clienteCredito;
//		String clienteAcuerdo;
//		String numRefMicFac;
//		int comprobante;
//		boolean marcaReclamoVacia;
//		String tipoCreito = "CRE";
//		String numRefMicCred;
//		SiNoEnum posibilidadDestraferencia;
//		
//		SiNoEnum errordebito;
//		SiNoEnum errorCredito;
//		SiNoEnum errorReintegro;
	}
}
