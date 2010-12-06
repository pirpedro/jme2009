package br.ufrj.spemarti.webservice.entity;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public abstract class SimpleInformationElement extends Version{

	private static final long serialVersionUID = 6144455305745720930L;
	
	@OneToMany(mappedBy="simpleInformationElement")
	private java.util.List<OrderElement> orderInFile = new ArrayList<OrderElement>();

	@OneToOne(fetch=FetchType.LAZY)
	private FileSvn file;
	
	public void setOrderInFile(java.util.List<OrderElement> orderInFile) {
		this.orderInFile = orderInFile;
	}

	public java.util.List<OrderElement> getOrderInFile() {
		return orderInFile;
	}
	
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

	public void setFile(FileSvn file) {
		this.file = file;
	}

	public FileSvn getFile() {
		return file;
	}

}
