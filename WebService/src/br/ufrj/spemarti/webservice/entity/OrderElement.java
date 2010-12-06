package br.ufrj.spemarti.webservice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class OrderElement implements Serializable{
	
	private static final long serialVersionUID = -7719595732950531514L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn
	private FileElement fileElement;
	
	@ManyToOne
	@JoinColumn
	private SimpleInformationElement simpleInformationElement;
	
	@Column(name="order_position")
	private Integer position;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getPosition() {
		return position;
	}

	public void setFileElement(FileElement fileElement) {
		this.fileElement = fileElement;
	}

	public FileElement getFileElement() {
		return fileElement;
	}

	public void setSimpleInformationElement(SimpleInformationElement simpleInformationElement) {
		this.simpleInformationElement = simpleInformationElement;
	}

	public SimpleInformationElement getSimpleInformationElement() {
		return simpleInformationElement;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (!getClass().equals(obj.getClass()))
			return false;
		
		OrderElement other = (OrderElement) obj;
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
