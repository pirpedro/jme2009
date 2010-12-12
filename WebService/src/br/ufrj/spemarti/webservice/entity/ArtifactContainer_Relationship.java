package br.ufrj.spemarti.webservice.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class ArtifactContainer_Relationship implements Serializable{

	private static final long serialVersionUID = -6877666510559234974L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	private ArtifactDefinition artifact;
	
	@ManyToMany
	@JoinTable(joinColumns={@JoinColumn(name="artifact_container_id")},
			inverseJoinColumns={@JoinColumn(name="container_id")})
	private List<ContainerDefinition> containers = new ArrayList<ContainerDefinition>();

	public void setArtifact(ArtifactDefinition artifact) {
		this.artifact = artifact;
	}

	public ArtifactDefinition getArtifact() {
		return artifact;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setContainers(List<ContainerDefinition> containers) {
		this.containers = containers;
	}

	public List<ContainerDefinition> getContainers() {
		return containers;
	}

}
