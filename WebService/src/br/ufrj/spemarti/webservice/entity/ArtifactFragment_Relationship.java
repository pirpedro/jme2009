package br.ufrj.spemarti.webservice.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue(value="0")
@NamedQueries({
	@NamedQuery(name="ArtifactFragment.recuperarPorArtifact",
				query="SELECT af FROM ArtifactFragment_Relationship af " +
						"WHERE af.artifact.id=:idArtifact")
})
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
