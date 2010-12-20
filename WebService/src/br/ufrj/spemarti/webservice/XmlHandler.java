package br.ufrj.spemarti.webservice;

import java.io.File;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import br.ufrj.spemarti.webservice.entity.ArtifactContainer_Relationship;
import br.ufrj.spemarti.webservice.entity.ArtifactDefinition;
import br.ufrj.spemarti.webservice.entity.ContainerDefinition;
import br.ufrj.spemarti.webservice.entity.FragmentDefinition;
import br.ufrj.spemarti.webservice.entity.VersionHistory;
import br.ufrj.spemarti.webservice.entity.WorkProductDefinition;

public class XmlHandler {
	
	public enum WorkProductType{
		ARTIFACT,
		CONTAINER,
		FRAGMENT,
		CONTAINERS,
		FRAGMENTS
	}
	
	public enum WorkProductAttributes{
		PRESENTATION_NAME("presentationName"),
		LOCATION("location");
		
		String type;
		WorkProductAttributes(String type){
			this.type = type;
		}
		
		@Override
		public String toString() {
			return type;
		}
	}
	
	public enum ArtifactAttributes{
		EXTERNAL("isExternal"),
		KIND("kind");
		
		String type;
		ArtifactAttributes(String type){
			this.type = type;
		}
		
		@Override
		public String toString() {
			return type;
		}
	}
	
	public enum FragmentType{
		IMAGE,
		DIAGRAM,
		LIST,
		MATRIX,
		TABLE,
		ELEMENT_GROUP,
		QUESTION,
		TEXT
	}
	
	public static Document exportarWorkProduct(WorkProductDefinition wpd){
		
		Element root = null;
		if(wpd instanceof ArtifactDefinition){
			root = geraEstruturaArtefato((ArtifactDefinition) wpd);
		
		}else if(wpd instanceof ContainerDefinition){
			root = geraEstruturaContainer((ContainerDefinition) wpd);
			
		}else if(wpd instanceof FragmentDefinition){
			root = geraEstruturaFragment((FragmentDefinition) wpd);
		}
		
		Document doc = new Document();
		doc.setRootElement(root);
		return doc;
	}
	
	private static Element geraEstruturaArtefato(ArtifactDefinition artifact , VersionHistory vh){
		Element rootElement = new Element(WorkProductType.ARTIFACT.toString());
		rootElement.setAttribute(ArtifactAttributes.EXTERNAL.toString(), artifact.getIsExternal().toString());
		rootElement.setAttribute(ArtifactAttributes.KIND.toString(), artifact.getaKind().toString());
		rootElement.setAttribute(WorkProductAttributes.PRESENTATION_NAME.toString(), artifact.getPresentationName());
		if(vh!=null){
			String location = vh.getFilePath() + vh.getFolder() + "/" + vh.getFileName();
			rootElement.setAttribute(WorkProductAttributes.LOCATION.toString(), location);
		}
		
		if(artifact.getArtifactContainer().size()!=0){
			Element containers = new Element(WorkProductType.CONTAINERS.toString());
			rootElement.addContent(containers);
			for(ArtifactContainer_Relationship rel : artifact.getArtifactContainer()){
				
			}
			
		}
		
		
		return null;
	}
	
	private static Element geraEstruturaArtefato(ArtifactDefinition artifact){
		
		
		return geraEstruturaArtefato(artifact, null);
	}
	
	private static Element geraEstruturaContainer(ContainerDefinition container){
		return null;
	}
	
	private static Element geraEstruturaFragment(FragmentDefinition fragment){
		return null;
	}
	
	
	
	
	
	private static Document inicializarDocumentoXML(String arquivo) throws JDOMException, IOException{
		File file = new File(arquivo);
		
		SAXBuilder sb = new SAXBuilder(); 
		
		return sb.build(file);
	}
	
	
	
}
