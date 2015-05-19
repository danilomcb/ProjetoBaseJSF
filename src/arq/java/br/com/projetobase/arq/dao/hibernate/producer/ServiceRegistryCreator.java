package br.com.projetobase.arq.dao.hibernate.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cria uma instancia do Hibernate {@link ServiceRegistry}, quando a aplicação é iniciada.
 * 
 * @author danilo-barros
 */
public class ServiceRegistryCreator {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRegistryCreator.class);
	private Configuration cfg;

	/**
	 * @deprecated CDI eyes only
	 */
	public ServiceRegistryCreator() {
	}

	@Inject
	public ServiceRegistryCreator(Configuration cfg) {
		this.cfg = cfg;
	}

	@Produces
	@ApplicationScoped
	public ServiceRegistry getInstance() {
		LOGGER.debug("Criando o service registry.");
		return new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
	}

	public void destroy(@Disposes ServiceRegistry serviceRegistry) {
		LOGGER.debug("Destruindo o service registry.");
		StandardServiceRegistryBuilder.destroy(serviceRegistry);
	}
}
