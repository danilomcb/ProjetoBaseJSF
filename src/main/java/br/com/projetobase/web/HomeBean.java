package br.com.projetobase.web;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.projetobase.modelo.Usuario;
import br.com.projetobase.web.service.UsuarioService;

@Named
@RequestScoped
public class HomeBean extends AbstractBean {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioService usuarioService;
	
	public void testeHibernate() {
		Usuario usuario = new Usuario();
		usuario.setEmail("akdbaksjndkands");
		usuario.setNome("iasdnandjakd");
		usuario.setSenha("aljdnandkajsd");
		usuarioService.salvar(usuario);
	}
	
}
