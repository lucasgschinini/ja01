package ar.com.telecom.shiva.test.threads;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.negocio.servicios.ICobroOnlineServicio;
import ar.com.telecom.shiva.negocio.servicios.IDescobroServicio;
import ar.com.telecom.shiva.negocio.servicios.IServicio;
import ar.com.telecom.shiva.test.SoporteContextoSpringTest;

public class ServiciosBeanSpringThreadsTest extends SoporteContextoSpringTest {

	@Autowired
	private ICobroOnlineServicio cobroOnlineServicio;
	@Autowired
	private IDescobroServicio descobroServicio;

	@Test
	public void threadsConcurrenciaServiciosSpringTest() throws NegocioExcepcion {
		double startTime = System.nanoTime();

		System.out.println("Comienza ejecucion de llamadas a servicios spring");
		System.out.println(" test de exportacion de cobros grandes ");

		ExecutorService executor = Executors.newFixedThreadPool(10);
		List<Long> listaCobros = new ArrayList<>();
		listaCobros.add(6803l);
		listaCobros.add(4513l);
		listaCobros.add(4595l);

        for (int i = 0; i < 3; i++) {
        	for (Long id : listaCobros) {
        		Runnable worker = new WorkerThread(id , cobroOnlineServicio);
                executor.execute(worker);	
        	}
            
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        
        double stopTime = System.nanoTime();
		double elapsedTime = (stopTime - startTime) / 1000000000.0;
		String detalle = "Resultado con concurrencia: " + new DecimalFormat("#.########").format(elapsedTime);
		System.out.println("Finished all threads - " + detalle);
    }
	@Test
	public void threadsConcurrenciaServiciosDescobroSpringTest() throws NegocioExcepcion {
		double startTime = System.nanoTime();

		System.out.println("Comienza ejecucion de llamadas a servicios spring");
		System.out.println(" test de exportacion de cobros grandes ");

		ExecutorService executor = Executors.newFixedThreadPool(10);
		List<Long[]> listaCobros = new ArrayList<>();
		
		
		listaCobros.add(new Long[]{423l, 4702l});
		
		listaCobros.add(new Long[]{403l,4641l});
		
		listaCobros.add(new Long[]{ 427l, 4701l});

        for (int i = 0; i < 3; i++) {
        	for (Long[] id : listaCobros) {
        		Runnable worker = new WorkerThread(id[1] , id[0], descobroServicio);
                executor.execute(worker);	
        	}
            
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
    private Long idCobro;
    private Long idDescobro;
    private IServicio cobroOnlineServicio;
    
    public WorkerThread(Long id, ICobroOnlineServicio cobroOnlineServicio){
        this.idCobro = id;
        this.cobroOnlineServicio = cobroOnlineServicio;
    }
    public WorkerThread(Long idCobro, Long idDescobro, IServicio cobroOnlineServicio){
        this.idCobro = idCobro;
        this.idDescobro = idDescobro;
        this.cobroOnlineServicio = cobroOnlineServicio;
    }
 
    @Override
    public void run() {
    	double startTime = System.nanoTime();
    	Thread.currentThread().setName("Hilo del cobro: " + idCobro);
        System.out.println(Thread.currentThread().getName());
        try {
			processCommand();
		} catch (Exception e) {
			System.out.println(idCobro + " - ERROR");
			e.printStackTrace();
		}
        
        
        double stopTime = System.nanoTime();
		double elapsedTime = (stopTime - startTime) / 1000000000.0;
		String detalle = new DecimalFormat("#.########").format(elapsedTime);
		System.out.println(Thread.currentThread().getName()+" End ("+ detalle + ")");
    }
 
    private void processCommand() throws Exception {
  
    	System.out.println(Thread.currentThread().getName() + " - Comienzo");
		
    	if (this.cobroOnlineServicio instanceof ICobroOnlineServicio) {
    		((ICobroOnlineServicio) this.cobroOnlineServicio).exportarCobro(this.idCobro);
    	} else if (this.cobroOnlineServicio instanceof IDescobroServicio) {
    		((IDescobroServicio) this.cobroOnlineServicio).exportarDescobro(this.idDescobro, this.idCobro);
    	}
    	
		
		System.out.println(Thread.currentThread().getName() + " - FIN");	
    }
 
    @Override
    public String toString(){
        return "" + this.idCobro;
    }
}