package br.ufrj.spemarti.webservice.entity;

import javax.persistence.Entity;

@Entity
public abstract class ComplexInformationElement extends SimpleInformationElement{

	private static final long serialVersionUID = -3320138959291887425L;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (!getClass().equals(obj.getClass()))
			return false;
		
		ComplexInformationElement other = (ComplexInformationElement) obj;
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
