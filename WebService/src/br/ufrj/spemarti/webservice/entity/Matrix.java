package br.ufrj.spemarti.webservice.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
@DiscriminatorValue(value="7")
public class Matrix extends ComplexInformationElement{

	private static final long serialVersionUID = -5156679932082919173L;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	private List<br.ufrj.spemarti.webservice.entity.List> lines = new ArrayList<br.ufrj.spemarti.webservice.entity.List>();

	@ManyToOne
	private br.ufrj.spemarti.webservice.entity.List header;
	
	public void setLines(List<br.ufrj.spemarti.webservice.entity.List> lines) {
		this.lines = lines;
	}

	public List<br.ufrj.spemarti.webservice.entity.List> getLines() {
		return lines;
	}

	public void setHeader(br.ufrj.spemarti.webservice.entity.List header) {
		this.header = header;
	}

	public br.ufrj.spemarti.webservice.entity.List getHeader() {
		return header;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (!getClass().equals(obj.getClass()))
			return false;
		
		Matrix other = (Matrix) obj;
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
