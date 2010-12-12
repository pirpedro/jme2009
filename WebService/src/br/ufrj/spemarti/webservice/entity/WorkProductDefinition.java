package br.ufrj.spemarti.webservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public abstract class WorkProductDefinition extends Version{

	private static final long serialVersionUID = -6259576616599831349L;
	
	@Column
	private String presentationName;
	
	@Column
	private String description;

	public void setPresentationName(String presentationName) {
		this.presentationName = presentationName;
	}

	public String getPresentationName() {
		return presentationName;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
