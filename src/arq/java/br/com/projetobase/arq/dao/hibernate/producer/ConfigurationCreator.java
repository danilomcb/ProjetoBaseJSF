package br.com.projetobase.arq.dao.hibernate.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Carrega as configurações do Hibernate {@link Configuration}, quando a aplicação 
 * for iniciada.
 * 
 * @author danilo-barros
 */
public class ConfigurationCreator {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationCreator.class);

	public ConfigurationCreator() {
	}

	@Produces
	@ApplicationScoped
	public Configuration getInstance() {
		Configuration configuration = new Configuration();

		extraConfigurations(configuration);

		LOGGER.debug("Carregando configurações com o arquivo {}", "/hibernate.cfg.xml");
		return configuration.configure("/hibernate.cfg.xml");
	}

	protected void extraConfigurations(Configuration configuration) {
	}
}
