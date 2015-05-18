package br.com.projetobase.web;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.projetobase.arq.dao.hibernate.interceptor.transaction.Transactional;
import br.com.projetobase.dao.UsuarioDAO;
import br.com.projetobase.modelo.Usuario;

@Named
@RequestScoped
public class HomeBean extends AbstractBean {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	public void testeHibernate() {
		Usuario usuario = new Usuario();
		usuario.setEmail("akdbaksjndkands");
		usuario.setNome("iasdnandjakd");
		usuario.setSenha("aljdnandkajsd");
		inserirUsuario(usuario);
	}
	
	@Transactional
	private void inserirUsuario(Usuario usuario) {
		usuarioDAO.inserir(usuario);
	}
}
