package br.ufrj.spemarti.webservice.entity;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


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
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private java.util.List<SimpleInformationElement> contents = new ArrayList<SimpleInformationElement>();

	@ManyToMany(fetch=FetchType.LAZY, cascade={})
	@JoinTable(joinColumns={@JoinColumn(name="list_id")}, 
			    inverseJoinColumns={@JoinColumn(name="matrix_id")})
	private java.util.List<Matrix> matrix = new ArrayList<Matrix>();


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

	public void setMatrix(java.util.List<Matrix> matrix) {
		this.matrix = matrix;
	}

	public java.util.List<Matrix> getMatrix() {
		return matrix;
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

}
