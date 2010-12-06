package br.ufrj.spemarti.webservice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class FileSvn implements Serializable{

	private static final long serialVersionUID = -3517236313092224806L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column
	private String fileName;
	
	@Column
	private String filePath;
	
	@Column
	private String folder;
	
	@OneToOne
	private SimpleInformationElement simpleInformationElement;

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getFolder() {
		return folder;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setSimpleInformationElement(SimpleInformationElement simpleInformationElement) {
		this.simpleInformationElement = simpleInformationElement;
	}

	public SimpleInformationElement getSimpleInformationElement() {
		return simpleInformationElement;
	}

}
