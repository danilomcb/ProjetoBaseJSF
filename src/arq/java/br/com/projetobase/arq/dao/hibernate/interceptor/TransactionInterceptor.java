package br.com.projetobase.arq.dao.hibernate.interceptor;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.projetobase.arq.dao.hibernate.interceptor.transaction.Transactional;

@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionInterceptor.class);
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Session session;

	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		Transaction transaction = session.beginTransaction();
		LOGGER.debug("Transação iniciada.");
		
		try {
			if (!transaction.isActive()) {
	            transaction.begin();
	            Object methodResult = context.proceed();
	            transaction.commit();
	    		LOGGER.debug("Transação iniciada.");
	            return methodResult;
			}
			return context.proceed();
		} catch (Exception e) {
			LOGGER.debug("Erro na trasação.");
			if(transaction != null && transaction.isActive()){
				LOGGER.debug("Iniciando Rollback.");
                transaction.rollback();
            }
            throw e;
		}
	}
}
