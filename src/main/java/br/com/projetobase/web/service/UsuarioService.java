package br.com.projetobase.web.service;

import javax.inject.Inject;

import br.com.projetobase.arq.dao.hibernate.interceptor.transaction.Transactional;
import br.com.projetobase.dao.UsuarioDAO;
import br.com.projetobase.modelo.Usuario;

public class UsuarioService {

	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Transactional
	public void salvar(Usuario usuario) {
		if (usuario.getId() != null && usuario.getId() != 0) {
			usuarioDAO.inserir(usuario);
		} else {
			usuarioDAO.atualizar(usuario);
		}
	}
}
