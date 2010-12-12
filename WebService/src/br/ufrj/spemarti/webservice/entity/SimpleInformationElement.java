package br.ufrj.spemarti.webservice.entity;

import javax.persistence.Entity;


@Entity
public abstract class SimpleInformationElement extends FragmentDefinition{

	private static final long serialVersionUID = 6144455305745720930L;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (!getClass().equals(obj.getClass()))
			return false;
		
		SimpleInformationElement other = (SimpleInformationElement) obj;
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
