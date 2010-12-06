package br.ufrj.spemarti.webservice.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
@DiscriminatorValue("9")
public class FileElement extends Version{

	private static final long serialVersionUID = 4441352257959860338L;
	
	@OneToMany(mappedBy="fileElement")
	private List<OrderElement> elementList = new ArrayList<OrderElement>();

	@OneToOne
	private SimpleInformationElement simpleInformationElement;
	
	public void setElementList(List<OrderElement> elementList) {
		this.elementList = elementList;
	}

	public List<OrderElement> getElementList() {
		return elementList;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (!getClass().equals(obj.getClass()))
			return false;
		
		FileElement other = (FileElement) obj;
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

	public void setSimpleInformationElement(SimpleInformationElement simpleInformationElement) {
		this.simpleInformationElement = simpleInformationElement;
	}

	public SimpleInformationElement getSimpleInformationElement() {
		return simpleInformationElement;
	}

}
