package br.ufrj.spemarti.webservice.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value="0")
public class Image extends SimpleInformationElement{

	private static final long serialVersionUID = -4978574476445082791L;
	
	@ManyToOne
	private Diagram diagram;

	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
	}

	public Diagram getDiagram() {
		return diagram;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (!getClass().equals(obj.getClass()))
			return false;
		
		Image other = (Image) obj;
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
