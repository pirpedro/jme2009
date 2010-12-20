package br.ufrj.spemarti.webservice.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("9")
public class ArtifactDefinition extends WorkProductDefinition{

	private static final long serialVersionUID = 1719272679202129197L;
	
	@Column
	private Boolean isExternal;
	
	@Enumerated(value=EnumType.STRING)
	private ArtifactKinds aKind;
	
	@OneToMany(mappedBy="artifact")
	private Set<ArtifactContainer_Relationship> artifactContainer = new HashSet<ArtifactContainer_Relationship>();
	
	@OneToMany(mappedBy="artifact")
	private Set<ArtifactFragment_Relationship> artifactFragment = new HashSet<ArtifactFragment_Relationship>();
	
	public ArtifactDefinition(){
		this.isExternal = false;
	}
	
	public java.util.List<FragmentDefinition> getFragments(){
		List<FragmentDefinition> listaFragmentos = new ArrayList<FragmentDefinition>();
		for(ArtifactFragment_Relationship rel : getArtifactFragment()){
			listaFragmentos.add(rel.getContainers().get(0));
		}
		
		return listaFragmentos;
	}
	
	
	public void setIsExternal(Boolean isExternal) {
		this.isExternal = isExternal;
	}

	public Boolean getIsExternal() {
		return isExternal;
	}

	public void setaKind(ArtifactKinds aKind) {
		this.aKind = aKind;
	}

	public ArtifactKinds getaKind() {
		return aKind;
	}

	public void setArtifactContainer(Set<ArtifactContainer_Relationship> artifactContainer) {
		this.artifactContainer = artifactContainer;
	}

	public Set<ArtifactContainer_Relationship> getArtifactContainer() {
		return artifactContainer;
	}


	public void setArtifactFragment(Set<ArtifactFragment_Relationship> artifactFragment) {
		this.artifactFragment = artifactFragment;
	}


	public Set<ArtifactFragment_Relationship> getArtifactFragment() {
		return artifactFragment;
	}

}
