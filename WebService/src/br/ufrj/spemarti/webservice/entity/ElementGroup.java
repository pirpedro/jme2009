package br.ufrj.spemarti.webservice.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
@DiscriminatorValue(value="5")
public class ElementGroup extends br.ufrj.spemarti.webservice.entity.List{

	private static final long serialVersionUID = -4172052773184219107L;

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<SimpleInformationElement> internalContents = new ArrayList<SimpleInformationElement>();

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="parent")
	private List<ElementGroup> childs = new ArrayList<ElementGroup>();
	
	@ManyToOne
	private ElementGroup parent;
	
	@Override
	public List<FragmentDefinition> getChildren() {
		List<FragmentDefinition> listaFragmento = new ArrayList<FragmentDefinition>();
		for(SimpleInformationElement sie :getInternalContents()){
			listaFragmento.add(sie);
		}
		
		for(SimpleInformationElement sie :getContents()){
			listaFragmento.add(sie);
		}
		for(ElementGroup sie :getChilds()){
			listaFragmento.add(sie);
		}
		
		return listaFragmento;
	}
	
	public void setInternalContents(List<SimpleInformationElement> internalContents) {
		this.internalContents = internalContents;
	}

	public List<SimpleInformationElement> getInternalContents() {
		return internalContents;
	}

	public void setParent(ElementGroup parent) {
		this.parent = parent;
	}

	public ElementGroup getParent() {
		return parent;
	}

	public void setChilds(List<ElementGroup> childs) {
		this.childs = childs;
	}

	public List<ElementGroup> getChilds() {
		return childs;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (!getClass().equals(obj.getClass()))
			return false;
		
		ElementGroup other = (ElementGroup) obj;
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
	
}
