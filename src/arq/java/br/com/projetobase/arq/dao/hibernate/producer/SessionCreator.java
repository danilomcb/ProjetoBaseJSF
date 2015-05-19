package br.com.projetobase.arq.dao.hibernate.producer;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cria a sessão do Hibernate {@link Session}, quando for requisitado.
 * 
 * @author danilo-barros
 */
public class SessionCreator {

	private static final Logger LOGGER = LoggerFactory.getLogger(SessionCreator.class);
	private SessionFactory factory;

	/**
	 * @deprecated CDI eyes only
	 */
	public SessionCreator() {
	}

	@Inject
	public SessionCreator(SessionFactory factory) {
		this.factory = factory;
	}

	@Produces
	@RequestScoped
	public Session getInstance() {
		Session session = factory.openSession();
		LOGGER.debug("Abrindo a sessão {}", session);
		return session;
	}

	public void destroy(@Disposes Session session) {
		LOGGER.debug("Fechando a session {}", session);
		session.close();
	}
}