package ar.com.telecom.shiva.test.soa.threads;

import java.text.DecimalFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.jms.servicios.IMicJmsSyncServicio;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;




public class MicConsultaUsandoThreadsJmsTest extends SoporteContextoSpringTest {

	@Autowired
	private IMicJmsSyncServicio micJmsSyncServicio;
	@Autowired
	private ICobroOnlineServicio cobroOnlineServicio;
	
	
	@Test
	public void consultaThreadsTest() throws NegocioExcepcion {
		double startTime = System.nanoTime();
		
		System.out.println("Teste con lo que esta");
		ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 6; i++) {
            Runnable worker = new WorkerThread(6803l, cobroOnlineServicio);
            executor.execute(worker);
          
            worker = new WorkerThread(4513l, cobroOnlineServicio);
            executor.execute(worker);
            worker = new WorkerThread(4595l, cobroOnlineServicio);
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        
        double stopTime = System.nanoTime();
		double elapsedTime = (stopTime - startTime) / 1000000000.0;
		String detalle = "Resultado con concurrencia: " + new DecimalFormat("#.########").format(elapsedTime);
		System.out.println("Finished all threads - " + detalle);
    }
}


class WorkerThread implements Runnable {
    private String command;
    private Long idCobro;
    private ICobroOnlineServicio cobroOnlineServicio;
    
    public WorkerThread(Long id, ICobroOnlineServicio cobroOnlineServicio){
        this.idCobro = id;
        this.cobroOnlineServicio = cobroOnlineServicio;
    }
 
    @Override
    public void run() {
    	double startTime = System.nanoTime();
    	Thread.currentThread().setName("Hilo del cobro: " + idCobro);
        System.out.println(Thread.currentThread().getName()+" Start. Command = "+command);
        try {
			processCommand();
		} catch (Exception e) {
			System.out.println(command + " - ERROR - consultaCreditoTest (MIC)");
			e.printStackTrace();
		}
        
        
        double stopTime = System.nanoTime();
		double elapsedTime = (stopTime - startTime) / 1000000000.0;
		String detalle = new DecimalFormat("#.########").format(elapsedTime);
		System.out.println(Thread.currentThread().getName()+" End ("+ detalle + ")");
    }
 
    private void processCommand() throws Exception {
    	
//    	MicConsultaCreditoEntrada entrada = new MicConsultaCreditoEntrada();
//		
//		List<BigInteger> listaClientes = new ArrayList<BigInteger>();
//		listaClientes.add(new BigInteger("4000014744"));
//		entrada.setListaClientes(listaClientes);
//		
//		entrada.setTipoMedioPago(TipoMedioPagoEnum.TODOS);
//		entrada.setFechaDesde(Utilidad.restarDiasAFecha(new Date(), 30));
//		entrada.setFechaHasta(new Date());
//		entrada.getInformacionPaginado().setCantidadRegistrosARetornar(100);
//		
//		MicConsultaCreditoSalida salida = this.micJmsSyncServicio.consultarCredito(entrada);
//		
    	System.out.println(Thread.currentThread().getName() + " - Comienzo");
		
    	this.cobroOnlineServicio.exportarCobro(this.idCobro);
		
		System.out.println(Thread.currentThread().getName() + " - FIN");	
    }
 
    @Override
    public String toString(){
        return this.command;
    }
}