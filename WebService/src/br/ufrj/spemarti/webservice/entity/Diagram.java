package br.ufrj.spemarti.webservice.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value="1")
public class Diagram extends ComplexInformationElement{

	private static final long serialVersionUID = -468398977939294620L;
	
	@OneToMany(fetch=FetchType.LAZY, cascade={}, mappedBy="diagram")
	private List<Image> listaImagem = new ArrayList<Image>();

	public void setListaImagem(List<Image> listaImagem) {
		this.listaImagem = listaImagem;
	}

	public List<Image> getListaImagem() {
		return listaImagem;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (!getClass().equals(obj.getClass()))
			return false;
		
		Diagram other = (Diagram) obj;
		if (this.getId() == null) {
			return false;
		} else {
			return getId().equals(other.getId());
		}
	}
	
	@Override
	public int hashCode() {
		return this.getId();
	}
}
