package br.com.projetobase.arq.dao.excecoes;

/**
 * Classe de exceções referentes as falhas em operações de banco de dados.
 * 
 * @author danilo-barros
 *
 */
@SuppressWarnings("serial")
public class DAOException extends RuntimeException {

	public DAOException(Throwable excecao) {
        super(excecao);
    }
	
	public DAOException(String mensagem) {
		super(mensagem);
	}
}
