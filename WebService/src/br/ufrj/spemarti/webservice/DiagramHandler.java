package br.ufrj.spemarti.webservice;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufrj.spemarti.webservice.entity.Diagram;
import br.ufrj.spemarti.webservice.entity.FragmentDefinition;
import br.ufrj.spemarti.webservice.entity.Image;

@Stateless
public class DiagramHandler implements IDiagramHandler{

	@PersistenceContext(unitName="titan")
	private EntityManager em;
	
	public boolean associate(Diagram parent, FragmentDefinition fragment) {
		
		if(! (fragment instanceof Image )){
			throw new RuntimeException("Não foi possível associar os artefatos.");
		
		}else{
			
			try{
				Image img = (Image) fragment;
				img.setDiagram(parent);
				parent.getListaImagem().add(img);
				em.merge(parent);
				
			}catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		
		return true;
	}

	
	
}
