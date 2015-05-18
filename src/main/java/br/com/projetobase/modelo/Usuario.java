package br.com.projetobase.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.projetobase.arq.modelo.ModeloPersistencia;

/**
 * Classe que representa o usuário do sistema.
 * 
 * @author danilo-barros
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "usuario")
public class Usuario extends ModeloPersistencia {

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "senha")
	private String senha;

	@Transient
	private String novaSenha;

	public Usuario() {
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

}