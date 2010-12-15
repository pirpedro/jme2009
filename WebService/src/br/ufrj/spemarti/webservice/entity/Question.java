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

	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.REMOVE}, mappedBy="question")
	private List<Text> texts = new ArrayList<Text>();
	
	public List<Text> getAsks(){
		List<Text> listText = new ArrayList<Text>();
		for(Text text : getTexts()){
			if(text.getTextType().equals(TextType.ASK)){
				listText.add(text);
			}
		}
		
		return listText;
	}
	
	public List<Text> getAnswers(){
		List<Text> listText = new ArrayList<Text>();
		for(Text text : getTexts()){
			if(text.getTextType().equals(TextType.ANSWER)){
				listText.add(text);
			}
		}
		
		return listText;
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

	public void setTexts(List<Text> texts) {
		this.texts = texts;
	}

	public List<Text> getTexts() {
		return texts;
	}
	
}
