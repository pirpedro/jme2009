package br.ufrj.spemarti.webservice;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufrj.spemarti.webservice.entity.ElementGroup;
import br.ufrj.spemarti.webservice.entity.FragmentDefinition;
import br.ufrj.spemarti.webservice.entity.SimpleInformationElement;

@Stateless
public class ElementGroupHandler implements IElementGroupHandler{

	@PersistenceContext(unitName="titan")
	private EntityManager em;
	
	public boolean associate(ElementGroup parent, FragmentDefinition fragment) {
		if(! Utils.isSimpleInformationInstance(fragment)){
			throw new RuntimeException("Não foi possível associar os artefatos.");
		
		}else{
			
			try{
				if(fragment instanceof ElementGroup){
					ElementGroup eg = (ElementGroup) fragment;
					eg.setParent(parent);
					parent.getChilds().add(eg);
					
				}else{
					SimpleInformationElement sie = (SimpleInformationElement) fragment;
					parent.getInternalContents().add(sie);
				}
				
				em.merge(parent);
				
			}catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		
		return true;
	}
}
