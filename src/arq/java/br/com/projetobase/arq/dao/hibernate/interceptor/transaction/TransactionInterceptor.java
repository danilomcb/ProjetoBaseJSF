package br.com.projetobase.arq.dao.hibernate.interceptor.transaction;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionInterceptor.class);
	
	@Inject
	private Session session;

	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		Transaction transaction = null;
		try {
			transaction = session.getTransaction();
			LOGGER.debug("Transação foi iniciada.");
			
			if (!transaction.isActive()) {
	            transaction.begin();
	            Object methodResult = context.proceed();
	            transaction.commit();
				LOGGER.debug("Commit realizado com sucesso.");
	            return methodResult;
			}
			return context.proceed();
		} catch (Exception e) {
			if(transaction != null && transaction.isActive()) {
				LOGGER.debug("Ocorreu um erro inesperado.", e);
				LOGGER.debug("Executando Rollback.");
                transaction.rollback();
            }
            throw e;
		}
	}
}
