package br.ufrj.spemarti.webservice.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value="1")
public class ContainerFragment_Relationship extends FragmentRelationship{

	private static final long serialVersionUID = 7234397161251367055L;
	
	@ManyToOne
	private ContainerDefinition container;

	public void setContainer(ContainerDefinition container) {
		this.container = container;
	}

	public ContainerDefinition getContainer() {
		return container;
	}

}
