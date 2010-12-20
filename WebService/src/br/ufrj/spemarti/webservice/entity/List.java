package br.ufrj.spemarti.webservice.entity;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import br.ufrj.spemarti.webservice.ListType;


@Entity
@DiscriminatorValue(value="6")
public class List extends ComplexInformationElement{
	
	private static final long serialVersionUID = 3036319379523081120L;
	
	@Column
	private Boolean isLabeled;
	
	@Column
	private Boolean isOrderedAsc;
	
	@Column
	private Boolean isOrderedDesc;
	
	@Column
	private Boolean isEnumerated;
	
	@OneToMany(fetch=FetchType.LAZY, cascade={})
	@JoinTable(name="LIST_FRAGMENT",
				joinColumns={@JoinColumn(name="LIST_ID")},
				inverseJoinColumns={@JoinColumn(name="FRAGMENT_ID")})
	private java.util.List<SimpleInformationElement> contents = new ArrayList<SimpleInformationElement>();

	@Transient
	private ListType type;
	
	public List(){
		this.isLabeled = false;
		this.isOrderedAsc = false;
		this.isOrderedDesc = false;
		this.isEnumerated = false;
	}
	
	@Override
	public java.util.List<FragmentDefinition> getChildren() {
		java.util.List<FragmentDefinition> listaFragmento = new ArrayList<FragmentDefinition>();
		for(SimpleInformationElement sie : getContents()){
			listaFragmento.add(sie);
		}
		
		return listaFragmento;
	}
	
	public Boolean isOrdered(){
		if(isOrderedAsc==true || isOrderedDesc==true){
			return true;
		}else{
			return false;
		}
	}
	
	public void orderByAZ(){
		//TODO
	}
	
	public void orderByZA(){
		//TODO
	}
	
	public void setIsLabeled(Boolean isLabeled) {
		this.isLabeled = isLabeled;
	}

	public Boolean getIsLabeled() {
		return isLabeled;
	}

	public void setIsOrderedAsc(Boolean isOrderedAsc) {
		this.isOrderedAsc = isOrderedAsc;
	}

	public Boolean getIsOrderedAsc() {
		return isOrderedAsc;
	}

	public void setIsOrderedDesc(Boolean isOrderedDesc) {
		this.isOrderedDesc = isOrderedDesc;
	}

	public Boolean getIsOrderedDesc() {
		return isOrderedDesc;
	}

	public void setIsEnumerated(Boolean isEnumerated) {
		this.isEnumerated = isEnumerated;
	}

	public Boolean getIsEnumerated() {
		return isEnumerated;
	}

	public void setContents(java.util.List<SimpleInformationElement> contents) {
		this.contents = contents;
	}

	public java.util.List<SimpleInformationElement> getContents() {
		return contents;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (!getClass().equals(obj.getClass()))
			return false;
		
		List other = (List) obj;
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

	public void setType(ListType type) {
		this.type = type;
	}

	public ListType getType() {
		return type;
	}

}
