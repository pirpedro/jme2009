package br.ufrj.spemarti.webservice.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value="4")
public class Question extends ComplexInformationElement{

	private static final long serialVersionUID = -1746403931744068455L;

	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.MERGE, CascadeType.PERSIST}, mappedBy="question")
	private List<Text> answers = new ArrayList<Text>();
	
	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.MERGE, CascadeType.PERSIST}, mappedBy="question")
	private List<Text> asks = new ArrayList<Text>();

	public void setAnswers(List<Text> answers) {
		this.answers = answers;
	}

	public List<Text> getAnswers() {
		return answers;
	}

	public void setAsks(List<Text> asks) {
		this.asks = asks;
	}

	public List<Text> getAsks() {
		return asks;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (!getClass().equals(obj.getClass()))
			return false;
		
		Question other = (Question) obj;
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
