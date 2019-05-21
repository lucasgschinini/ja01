package ar.com.telecom.shiva.persistencia.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;

import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.logs.Traza;

@SuppressWarnings("serial")
public class HibernateExtendedJpaDialect extends HibernateJpaDialect {

    @Override
    public Object beginTransaction(final EntityManager entityManager,
        final TransactionDefinition definition)
        throws PersistenceException, SQLException, TransactionException {

      Session session = (Session) entityManager.getDelegate();
      if (definition.getTimeout() != TransactionDefinition.TIMEOUT_DEFAULT) {
        getSession(entityManager).getTransaction().setTimeout(
            definition.getTimeout());
      }
      
      final TransactionData data = new TransactionData();
      
      session.doWork(new Work() {
        @Override
        public void execute(Connection connection) throws SQLException {
           Traza.debug(getClass(), Utilidad.reemplazarMensajes("The connection instance is {0}", connection.toString()));

           /**
            * Connection.TRANSACTION_READ_UNCOMMITTED = 1 
      		  Connection.TRANSACTION_READ_COMMITTED = 2 
      		  Connection.TRANSACTION_REPEATABLE_READ = 4 
      		  Connection.TRANSACTION_SERIALIZABLE = 8; 
      		  Connection.TRANSACTION_NONE = 0;
            */
           Traza.debug(getClass(), Utilidad.reemplazarMensajes("The isolation level of the connection is {0} and the isolation level set on the transaction is {1}",
             		String.valueOf(connection.getTransactionIsolation()), String.valueOf(definition.getIsolationLevel())));
             
           Integer previousIsolationLevel = DataSourceUtils
              .prepareConnectionForTransaction(connection, definition);
           data.setPreviousIsolationLevel(previousIsolationLevel);
           data.setConnection(connection);
        }
      });

      entityManager.getTransaction().begin();
      Traza.debug(getClass(), "Transaction started");
      
      Object springTransactionData = prepareTransaction(entityManager,
          definition.isReadOnly(), definition.getName());

      data.setSpringTransactionData(springTransactionData);

      return data;
    }

    @Override
    public void cleanupTransaction(Object transactionData) {
      super.cleanupTransaction(((TransactionData) transactionData)
          .getSpringTransactionData());
      ((TransactionData) transactionData).resetIsolationLevel();
    }

    private static class TransactionData {

      private Object springTransactionData;
      private Integer previousIsolationLevel;
      private Connection connection;

      public TransactionData() {
      }

      public void resetIsolationLevel() {
        if (this.previousIsolationLevel != null) {
          DataSourceUtils.resetConnectionAfterTransaction(connection,
              previousIsolationLevel);
        }
      }

      public Object getSpringTransactionData() {
        return this.springTransactionData;
      }

      public void setSpringTransactionData(Object springTransactionData) {
        this.springTransactionData = springTransactionData;
      }

      public void setPreviousIsolationLevel(Integer previousIsolationLevel) {
        this.previousIsolationLevel = previousIsolationLevel;
      }

      public void setConnection(Connection connection) {
        this.connection = connection;
      }

    }
}
