package br.com.projetobase.web;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Classe responsável por gerenciar as mensagens do sistema.
 * @author danilo-barros
 *
 */
@ApplicationScoped
public class GerenciadorDeMensagens implements Serializable {

	private static final long serialVersionUID = 1L;

	public GerenciadorDeMensagens() {
	}
	
	/**
	 * Informa uma mensagem do arquivo de internacionalização, dado uma chave.
	 * @param chave Chave da mensagem.
	 * @param parametros Parametros para incluir na mensagem.
	 * @return Mensagem internacionalizada.
	 */
	public String getMensagem(String chave, String... parametros) {
	    FacesContext context = FacesContext.getCurrentInstance();
	    ResourceBundle text = context.getApplication().evaluateExpressionGet(context, "#{texto}", ResourceBundle.class);
	    String retorno = text.getString(chave);
	    
	    for (int i = 0; i < parametros.length; i++) {
	    	retorno = retorno.replace("{" + i + "}", parametros[i]);
	    }
	    
	    return retorno;
	}
	
	/**
	 * Adiciona uma mensagem de erro para ser exibida no sistema.
	 */
	public void addMensagemErro(String mensagem) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"", mensagem));
		context.getExternalContext().getFlash().setKeepMessages(true);		
	}
	
	/**
	 * Adiciona uma mensagem de aviso para ser exibida no sistema.
	 */
	public void addMensagemAviso(String mensagem) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"", mensagem));
		context.getExternalContext().getFlash().setKeepMessages(true);
	}

	/**
	 * Adiciona uma mensagem de informação para ser exibida no sistema.
	 */
	public void addMensagemInformacao(String mensagem) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", mensagem));
		context.getExternalContext().getFlash().setKeepMessages(true);
	}

	
	/**
	 * Adiciona uma mensagem de erro para ser exibida no sistema, informando em qual painel de mensagens será exibida.
	 */
	public void addMensagemErroComIdentificador(String identificador, String mensagem) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(identificador, new FacesMessage(FacesMessage.SEVERITY_ERROR,"", mensagem));
		context.getExternalContext().getFlash().setKeepMessages(true);
	}
	
}
