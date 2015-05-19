package br.com.projetobase.arq.dao.hibernate.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cria a instância do {@link SessionFactory} quando a aplicação for iniciada.
 * 
 * @author danilo-barros
 */
public class SessionFactoryCreator {

	private static final Logger LOGGER = LoggerFactory.getLogger(SessionFactoryCreator.class);
	private Configuration cfg;
	private ServiceRegistry serviceRegistry;

	/**
	 * @deprecated CDI eyes only
	 */
	public SessionFactoryCreator() {
	}

	@Inject
	public SessionFactoryCreator(Configuration cfg, ServiceRegistry serviceRegistry) {
		this.cfg = cfg;
		this.serviceRegistry = serviceRegistry;
	}

	@Produces
	@ApplicationScoped
	public SessionFactory getInstance() {
		LOGGER.debug("Criando o session factory.");
		return cfg.buildSessionFactory(serviceRegistry);
	}

	public void destroy(@Disposes SessionFactory sessionFactory) {
		LOGGER.debug("Destruindo o session factory.");
		sessionFactory.close();
	}
}