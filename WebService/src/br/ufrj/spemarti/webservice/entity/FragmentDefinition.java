package br.ufrj.spemarti.webservice.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public abstract class FragmentDefinition extends WorkProductDefinition{

	private static final long serialVersionUID = -6966909361300239623L;
	
	@OneToOne(mappedBy="fragment")
	private SvnFile file;

	public FragmentDefinition(){
		
	}
	
	public FragmentDefinition(String presentationName, Integer id, Integer revision, String label){
		super(presentationName, id, revision, label);
	}
	
	public void setFile(SvnFile file) {
		this.file = file;
	}

	public SvnFile getFile() {
		return file;
	}

}
