package br.com.projetobase.arq.modelo;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Classe que representa o modelo de objetos relacionais do sistema para persistência.
 * 
 * @author danilo-barros
 *
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class ModeloPersistencia implements Cloneable, Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModeloPersistencia other = (ModeloPersistencia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
}
