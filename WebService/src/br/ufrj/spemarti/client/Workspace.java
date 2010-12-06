package br.ufrj.spemarti.client;

import java.util.ArrayList;
import java.util.List;

import br.ufrj.spemarti.webservice.VersionedExtent;


public class Workspace {

	private List<VersionedExtent> versionedExtent = new ArrayList<VersionedExtent>();
	
	
	public void copy(){
		
	}
	
	public void restore(){
		
	}
	
	public void update(){
		
	}
	
	public void merge(){
		
	}
	
	public void lookUpByVersionHistory(){
		
	}
	
	public void getVersionSet(){
		
	}
	
	public void getCheckedOutVersionSet(){
		
	}

	public void setVersionedExtent(List<VersionedExtent> versionedExtent) {
		this.versionedExtent = versionedExtent;
	}

	public List<VersionedExtent> getVersionedExtent() {
		return versionedExtent;
	}
}
