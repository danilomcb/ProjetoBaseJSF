package br.com.projetobase.web;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Classe pai para os demais managed bean da aplicação, contém
 * atributos e métodos genéricos.
 * @author danilo-barros
 *
 */
public class AbstractBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	protected Conversation conversation;
	
	@Inject
	protected GerenciadorDeMensagens gerenciadorDeMensagens;
	
	@Inject
	protected NavegacaoPaginasController navegacaoPaginas;
	
	@Inject
	private HttpServletRequest servletRequest;
	
	public AbstractBean() {
	}
	
	public String retornarMesmaPagina() {
		String currentURL = servletRequest.getRequestURL().toString();
		String url = currentURL.substring(0, currentURL.lastIndexOf("."));
		return url;
	}
	
}
