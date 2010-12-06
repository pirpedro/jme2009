package br.ufrj.spemarti.webservice;

import java.io.Serializable;

import br.ufrj.spemarti.webservice.entity.FileSvn;
import br.ufrj.spemarti.webservice.entity.Version;

public class VersionedExtent implements Serializable{
	
	private static final long serialVersionUID = -7375405599363406790L;
	private Boolean isCheckedOut;
	private String annotation;
	
	private String path;
	
	private String folder;
	
	private String fileName;
	
	private Version baseVersion;
	
	private Version previousVersion;
	
	private FileSvn file;
	
	private Integer simpleInformationElementCount;
		
	public VersionedExtent(){
		this.setSimpleInformationElementCount(0);
	}
	
	public void checkOut(){
		
	}
	
	public void checkIn(){
		
	}
	
	public void unCheckOut(){
		
	}
	
	public void lookUpByVersionId(){
		
	}
	
	public void lookUpByLabel(){
		
	}
	
	public void delete(){
		
	}
	
	public void createExtent(){
		
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}

	public String getAnnotation() {
		return annotation;
	}

	public void setIsCheckedOut(Boolean isCheckedOut) {
		this.isCheckedOut = isCheckedOut;
	}

	public Boolean getIsCheckedOut() {
		return isCheckedOut;
	}

	public void setBaseVersion(Version baseVersion) {
		this.baseVersion = baseVersion;
	}

	public Version getBaseVersion() {
		return baseVersion;
	}

	public void setPreviousVersion(Version previousVersion) {
		this.previousVersion = previousVersion;
	}

	public Version getPreviousVersion() {
		return previousVersion;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getFolder() {
		return folder;
	}

	public void setFile(FileSvn file) {
		this.file = file;
	}

	public FileSvn getFile() {
		return file;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setSimpleInformationElementCount(
			Integer simpleInformationElementCount) {
		this.simpleInformationElementCount = simpleInformationElementCount;
	}

	public Integer getSimpleInformationElementCount() {
		return simpleInformationElementCount;
	}

}
