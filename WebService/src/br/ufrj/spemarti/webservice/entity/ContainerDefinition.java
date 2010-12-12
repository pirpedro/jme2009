package br.ufrj.spemarti.webservice.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value="10")
public class ContainerDefinition extends WorkProductDefinition{

	private static final long serialVersionUID = -6495938856662420956L;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="parent")
	private List<ContainerDefinition_Relationship> containerRelationship = new ArrayList<ContainerDefinition_Relationship>();

	public void setContainerRelationship(List<ContainerDefinition_Relationship> containerRelationship) {
		this.containerRelationship = containerRelationship;
	}

	public List<ContainerDefinition_Relationship> getContainerRelationship() {
		return containerRelationship;
	}
	
	
	

}
