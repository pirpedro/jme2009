package br.ufrj.spemarti.webservice.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value="0")
public class ArtifactFragment_Relationship extends FragmentRelationship{

	private static final long serialVersionUID = -1834840404864842853L;
	
	@ManyToOne
	private ArtifactDefinition artifact;

	public void setArtifact(ArtifactDefinition artifact) {
		this.artifact = artifact;
	}

	public ArtifactDefinition getArtifact() {
		return artifact;
	}

}
