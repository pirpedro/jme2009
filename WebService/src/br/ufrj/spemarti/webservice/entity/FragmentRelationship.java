package br.ufrj.spemarti.webservice.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.ForceDiscriminator;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Frgmnt_In_Tipo", discriminatorType = DiscriminatorType.INTEGER)
@ForceDiscriminator
public abstract class FragmentRelationship implements Serializable{

	private static final long serialVersionUID = -1115909196794977559L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToMany(cascade={})
	@JoinTable(joinColumns={@JoinColumn(name="fragmentRelationship_id")},
				inverseJoinColumns={@JoinColumn(name="fragment_id")})
	private List<FragmentDefinition> containers = new ArrayList<FragmentDefinition>();

	public void setContainers(List<FragmentDefinition> containers) {
		this.containers = containers;
	}

	public List<FragmentDefinition> getContainers() {
		return containers;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

}
