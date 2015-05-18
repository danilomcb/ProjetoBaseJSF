package br.com.projetobase.arq.dao;

import java.util.List;

import br.com.projetobase.arq.dao.excecoes.DAOException;
import br.com.projetobase.arq.modelo.ModeloPersistencia;

public interface DAO<T extends ModeloPersistencia> {

	static final int BATCH_SIZE = 20;
	
	T inserir(T entidade) throws DAOException;
	
	boolean inserir(List<T> list) throws DAOException;
	
	T atualizar(T entidade) throws DAOException;
	
	T buscar(Long id) throws DAOException;
	
	List<T> todos() throws DAOException;
	
	void remover(T entidade) throws DAOException;
	
}
