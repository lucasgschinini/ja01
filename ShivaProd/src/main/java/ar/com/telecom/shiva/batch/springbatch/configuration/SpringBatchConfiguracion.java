package ar.com.telecom.shiva.batch.springbatch.configuration;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringBatchConfiguracion {

	/** 	 */
	private static final String CONTEXT_BATCH = "spring-batch-context.xml";
	private static ApplicationContext ctxBatch;
	
	public static ApplicationContext ctx;
	
	/**
     * Sobrecarga del método getBean, a efectos de poder levantar una configuración de Spring
	 * solo en el momento en que se va a utilizar 
     * @param string
     * @return
     */
    public static Object getBeanBatch(String string){
		try {
			// Inicializo el contexto de Spring
			if (ctxBatch == null) {
				ctxBatch = new ClassPathXmlApplicationContext(CONTEXT_BATCH);
			}
			
			// Retorno el bean correspondiente
			return ctxBatch.getBean(string);
		} catch (NoSuchBeanDefinitionException e) {
			return null;
		}
	}
    
    /**
     * Make Spring inject the application context
     * and save it on a static variable,
     * so that it can be accessed from any point in the application. 
     */
    @Autowired
    private void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext;       
    }
	
	
}
