package ar.com.telecom.shiva.negocio.executor.util;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import ar.com.telecom.shiva.base.utils.logs.Traza;

/**
 *	Para el futuro
 	@Service
	public class FooServiceImpl implements FooService {
    	@Autowired
    	private TransactionalAsyncTaskExecutor trTaskExecutor;
    	@Autowired
    	private FooDao fooDao;

	    @Transactional
	    @Override
	    public void update(Foo entity)  {
	        fooDao.merge(entity);     // Ejecutar en la transacción creada Spring'om (tr_1).
	        trTaskExecutor.run(new Runnable() {       
	        	// Inicia un nuevo tema, y una nueva transacción (tr_2), 
	        	// correr () método se ejecuta en paralelo 
	        	// con el flujo de corriente en un tr_2 transacción.            
	        	public void run() {
	                someLongTimeOperation();
	            }
	        });
	    }
	    
		@Transactional
	    @Override
	    public void someLongTimeOperation(Foo entity)  {
	        // Aquí un conjunto de operaciones que consumen muchos recursos
	    }
	}
                                                                             
    <bean id="transactionalTaskExecutor" class="ru.habrahabr.support.spring.DelegatedTransactionalAsyncTaskExecutor">
		<property name="delegate">
		    <bean class="org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor">
		        <property name="threadNamePrefix" value="Habrahabr example - "/>
		        <property name="threadGroupName" value="Habrahabr examples Group"/>
		        <property name="corePoolSize" value="10"/>
		        <property name="waitForTasksToCompleteOnShutdown" value="true"/>
	    	</bean>
		</property>
		<property name="transactionManager" ref="transactionManager"/>
	</bean>
	
<!-- 	<bean id="taskExecutor" class="org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor"> -->
<!--        <property name="corePoolSize" value="5" /> -->
<!--        <property name="maxPoolSize" value="10" /> -->
<!--        <property name="queueCapacity" value="25" /> -->
<!--     </bean> -->
 */

public class DelegatedTransactionalAsyncTaskExecutor implements
		InitializingBean, TransactionalAsyncTaskExecutor {

	private PlatformTransactionManager transactionManager;
    private AsyncTaskExecutor delegate;
    private TransactionTemplate sharedTransactionTemplate;

    public DelegatedTransactionalAsyncTaskExecutor() {
    }

    public DelegatedTransactionalAsyncTaskExecutor(PlatformTransactionManager transactionManager, AsyncTaskExecutor delegate) {
        this.transactionManager = transactionManager;
        this.delegate = delegate;
    }

    public void execute(final Runnable task, Integer propagation, Integer isolationLevel) {
        final TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setPropagationBehavior(propagation);
        transactionTemplate.setIsolationLevel(isolationLevel);
        delegate.execute(new Runnable() {
            @Override
            public void run() {
                transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus status) {
                        task.run();
                    }
                });
            }
        });
    }

    @Override
    public void execute(final Runnable task) {
        execute(task, TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_DEFAULT);
    }

    @Override
    public void execute(final Runnable task, long startTimeout) {
        final TransactionTemplate transactionTemplate = getSharedTransactionTemplate();
        delegate.execute(new Runnable() {
            @Override
            public void run() {
                transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus status) {
                        task.run();
                    }
                });
            }
        }, startTimeout);

    }

    @Override
    public Future<?> submit(final Runnable task) {
        final TransactionTemplate transactionTemplate = getSharedTransactionTemplate();
        return delegate.submit(new Runnable() {
            @Override
            public void run() {
                transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus status) {
                        task.run();
                    }
                });
            }
        });
    }

    @Override
    public <T> Future<T> submit(final Callable<T> task) {
        final TransactionTemplate transactionTemplate = getSharedTransactionTemplate();
        return delegate.submit(new Callable<T>() {
            @Override
            public T call() throws Exception {
                return transactionTemplate.execute(new TransactionCallback<T>() {
                    @Override
                    public T doInTransaction(TransactionStatus status) {
                        T result = null;
                        try {
                            result = task.call();
                        } catch (Exception e) {
                            Traza.error(getClass(), e.getMessage(), e);
                            status.setRollbackOnly();
                        }
                        return result;
                    }
                });
            }
        });
    }

    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public AsyncTaskExecutor getDelegate() {
        return delegate;
    }

    public void setDelegate(AsyncTaskExecutor delegate) {
        this.delegate = delegate;
    }

    public TransactionTemplate getSharedTransactionTemplate() {
        return sharedTransactionTemplate;
    }

    public void setSharedTransactionTemplate(TransactionTemplate sharedTransactionTemplate) {
        this.sharedTransactionTemplate = sharedTransactionTemplate;
    }

    @Override
    public void afterPropertiesSet() {
        if (transactionManager == null) {
            throw new IllegalArgumentException("Property 'transactionManager' is required");
        }
        if (delegate == null) {
            delegate = new SimpleAsyncTaskExecutor();
        }
        if (sharedTransactionTemplate == null) {
            sharedTransactionTemplate = new TransactionTemplate(transactionManager);
        }
    }
}

