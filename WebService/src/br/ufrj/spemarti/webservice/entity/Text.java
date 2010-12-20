package br.ufrj.spemarti.webservice.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value="2")
public class Text extends SimpleInformationElement{

	private static final long serialVersionUID = -7365478427048880422L;
	
	@Column
	private String value;
	
	@ManyToOne(optional=true)
	private Question question;
	
	@Column
	@Enumerated(value=EnumType.STRING)
	private TextType textType;
	
	public Text(){
		
	}
	
	public Text(String value, String presentationName, Integer id, Integer revision, String label){
		super(presentationName, id, revision, label);
		this.value= value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Question getQuestion() {
		return question;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (!getClass().equals(obj.getClass()))
			return false;
		
		Text other = (Text) obj;
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

	public void setTextType(TextType textType) {
		this.textType = textType;
	}

	public TextType getTextType() {
		return textType;
	}
	
}
