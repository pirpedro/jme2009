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
public class ContainerDefinition_Relationship implements Serializable{

	private static final long serialVersionUID = -347423873977487113L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	private ContainerDefinition parent;
	
	@ManyToMany
	@JoinTable(joinColumns={@JoinColumn(name="Container_Relationship_id")},
			inverseJoinColumns={@JoinColumn(name="container_id")})
	private List<ContainerDefinition> children = new ArrayList<ContainerDefinition>();
	

	public void setParent(ContainerDefinition parent) {
		this.parent = parent;
	}

	public ContainerDefinition getParent() {
		return parent;
	}

	public void setChildren(List<ContainerDefinition> children) {
		this.children = children;
	}

	public List<ContainerDefinition> getChildren() {
		return children;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

}
