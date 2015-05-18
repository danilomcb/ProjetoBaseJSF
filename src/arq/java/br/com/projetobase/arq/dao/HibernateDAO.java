package br.com.projetobase.arq.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;

import br.com.projetobase.arq.dao.excecoes.DAOException;
import br.com.projetobase.arq.modelo.ModeloPersistencia;

public abstract class HibernateDAO<T extends ModeloPersistencia> implements DAO<T>, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Session session;
	
	protected Class<T> clazz;
	
	public Session getSession() {
		return session;
	}
	
	public HibernateDAO() {
		Class<T> clazz = inferGenericType();
		this.clazz = clazz;
	}
	
	public T inserir(T entidade) throws DAOException {
		try {
			session.persist(entidade);
		} catch (Exception hibernateException) {
			throw new DAOException(hibernateException);
		}
		return entidade;
	}
	
	public boolean inserir(List<T> list) throws DAOException {
		try {
			int count = 0;
			
			for (T objeto : list) {
				session.persist(objeto);
				
				if (count % BATCH_SIZE == 0) {
					session.flush();
					session.clear();
				}
				count++;
			}
		} catch (Exception hibernateException) {
			throw new DAOException(hibernateException);
		}
		
		return true;
	}
	
	public T atualizar(T entidade) throws DAOException {
		try {
			session.merge(entidade);
			return entidade;
		} catch (Exception hibernateException) {
			throw new DAOException(hibernateException);
		}
	}
	
	public void remover(T entidade) throws DAOException {
		try {
			session.delete(entidade);
		} catch (Exception hibernateException) {
			throw new DAOException(hibernateException);
		}
	}
	
	@SuppressWarnings("unchecked")
	public T buscar(Long id) throws DAOException  {
		try {
			return (T) session.get(clazz, id);
		} catch (Exception hibernateException) {
			throw new DAOException(hibernateException);
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> todos() throws DAOException {
		try {
			return session.createCriteria(clazz).list();
		} catch (Exception hibernateException) {
			throw new DAOException(hibernateException);
		}
	} 
	
	@SuppressWarnings("unchecked")
	private Class<T> inferGenericType() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
