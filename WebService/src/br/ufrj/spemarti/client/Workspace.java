package br.ufrj.spemarti.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import br.ufrj.spemarti.webservice.Svn;
import br.ufrj.spemarti.webservice.Utils;
import br.ufrj.spemarti.webservice.VersionedExtent;
import br.ufrj.spemarti.webservice.entity.ArtifactDefinition;
import br.ufrj.spemarti.webservice.entity.ComplexInformationElement;
import br.ufrj.spemarti.webservice.entity.Diagram;
import br.ufrj.spemarti.webservice.entity.ElementGroup;
import br.ufrj.spemarti.webservice.entity.FragmentDefinition;
import br.ufrj.spemarti.webservice.entity.Image;
import br.ufrj.spemarti.webservice.entity.LabeledText;
import br.ufrj.spemarti.webservice.entity.Matrix;
import br.ufrj.spemarti.webservice.entity.Question;
import br.ufrj.spemarti.webservice.entity.Table;
import br.ufrj.spemarti.webservice.entity.Text;
import br.ufrj.spemarti.webservice.entity.Version;


public class Workspace {

	private List<VersionedExtent> versionedExtent = new ArrayList<VersionedExtent>();
	private Svn dao;
	
	private static final Integer USER_ID_TEST = 1;
	
	
	public Workspace(){
		Context jndiContext;
		try {
			jndiContext = getInitialContext();
			Object ref = jndiContext.lookup("SvnBean/remote");
	    	setDao((Svn)PortableRemoteObject.narrow(ref, Svn.class));
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    	
	}
	
	public void createUser(String login, String senha){
		getDao().createUser(login, senha);
		
	}
	
	public void checkIn(Version version, String filePath, String folder, String fileName){
		commit(version, filePath, folder, fileName);
	}
	
	public void commit(Version version, String filePath, String folder, String fileName){
		if(Utils.isArtifactInstance(version)){
			commitArtifact((ArtifactDefinition) version, filePath, folder, fileName);
		}else{
			commitFragment((FragmentDefinition) version);
		}
	}
	
	private void commitArtifact(ArtifactDefinition artifact,  String filePath, String folder, String fileName){
		Version artifactVersioned = getDao().checkIn(generateArtifact(artifact), filePath, folder, fileName, USER_ID_TEST);
		artifact.setId(artifactVersioned.getId());
		artifact.setRevision(artifactVersioned.getRevision());
		artifact.setCreationDate(artifactVersioned.getCreationDate());
		
		for(FragmentDefinition fragment: artifact.getFragments()){
			Version fragmentVersioned = getDao().checkIn(generateFragment(fragment), generateArtifact(artifact), USER_ID_TEST);
			fragment.setId(fragmentVersioned.getId());
			fragment.setRevision(fragmentVersioned.getId());
			fragment.setCreationDate(fragmentVersioned.getCreationDate());
			commitFragment(fragment);
			
			
		}
	}
	
	private void commitFragment(FragmentDefinition fragment){
		
		if(Utils.isComplexInformationInstance(fragment)){
			ComplexInformationElement cie = (ComplexInformationElement) fragment;
			for(FragmentDefinition frag : cie.getChildren()){
				Version fragmentVersioned = getDao().checkIn(generateFragment(fragment), generateFragment(frag), USER_ID_TEST);
				frag.setId(fragmentVersioned.getId());
				frag.setRevision(fragmentVersioned.getId());
				frag.setCreationDate(fragmentVersioned.getCreationDate());
				commitFragment(frag);
			}
		}
		
	}
	
	private ArtifactDefinition generateArtifact(ArtifactDefinition artifact){
		ArtifactDefinition newArtifact = new ArtifactDefinition();
		newArtifact.setaKind(artifact.getaKind());
		newArtifact.setAnnotation(artifact.getAnnotation());
		newArtifact.setDescription(artifact.getDescription());
		newArtifact.setId(artifact.getId());
		newArtifact.setIsExternal(artifact.getIsExternal());
		newArtifact.setLabelVersion(artifact.getLabelVersion());
		newArtifact.setPresentationName(artifact.getPresentationName());
		newArtifact.setRevision(artifact.getRevision());
		newArtifact.setUser(artifact.getUser());
		return newArtifact;
	}
	
	private FragmentDefinition generateFragment(FragmentDefinition fragment){
		FragmentDefinition newFragment= null ;
		
		if(Utils.isComplexInformationInstance(fragment)){
			
			
			if(fragment instanceof Diagram){
				newFragment = new Diagram();
			
			}else if(fragment instanceof Question){
				newFragment = new Question();
			
			}else if(fragment instanceof Table){
				newFragment = new Table();
				
			}else if(fragment instanceof Matrix){
				newFragment = new Matrix();
			
			}else if(fragment instanceof ElementGroup){
				newFragment = new ElementGroup();
			
			}else if(fragment instanceof br.ufrj.spemarti.webservice.entity.List){
				newFragment = new br.ufrj.spemarti.webservice.entity.List();
				((br.ufrj.spemarti.webservice.entity.List) newFragment).setType(((br.ufrj.spemarti.webservice.entity.List) fragment).getType());
			}
			
			
		}else if(fragment instanceof Image){
			newFragment = new Image();
			((Image)newFragment).setPath(((Image) fragment).getPath());
			
		}else if(fragment instanceof LabeledText){
			newFragment = new LabeledText();
			((LabeledText)newFragment).setValue(((LabeledText) fragment).getValue());
			((LabeledText)newFragment).setLabel(((LabeledText) fragment).getLabel());
			((LabeledText)newFragment).setTextType(((LabeledText) fragment).getTextType());
		
		}else if(fragment instanceof Text){
			newFragment = new Text();
			((Text)newFragment).setValue(((Text) fragment).getValue());
			((Text)newFragment).setTextType(((Text) fragment).getTextType());
			
		}
		
		newFragment.setAnnotation(fragment.getAnnotation());
		newFragment.setDescription(fragment.getDescription());
		newFragment.setId(fragment.getId());
		newFragment.setLabelVersion(fragment.getLabelVersion());
		newFragment.setPresentationName(fragment.getPresentationName());
		newFragment.setRevision(fragment.getRevision());
		newFragment.setUser(fragment.getUser());
		return newFragment;
		
		
				
		
	}
	
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
	
	public static Context getInitialContext() throws NamingException{
    	Properties properties = new Properties();
    	properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
    	properties.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
    	properties.put(Context.PROVIDER_URL, "jnp://localhost:1099");
    	return new InitialContext(properties);
    
    }

	public void setDao(Svn dao) {
		this.dao = dao;
	}

	public Svn getDao() {
		if(dao ==null){
			throw new RuntimeException("Não foi possível fazer lookup do ejb");
		}
		
		return dao;
	}
}
