package br.ufrj.spemarti.webservice;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufrj.spemarti.webservice.entity.FragmentDefinition;
import br.ufrj.spemarti.webservice.entity.List;
import br.ufrj.spemarti.webservice.entity.SimpleInformationElement;

@Stateless
public class ListHandler implements IListHandler{

	@PersistenceContext(unitName="titan")
	private EntityManager em;
	
	public boolean associate(List parent, FragmentDefinition fragment) {
		if(! Utils.isFragmentInstance(fragment)){
			throw new RuntimeException("Não foi possível associar os artefatos.");
		
		}else{
			
			try{
				SimpleInformationElement sie = (SimpleInformationElement) fragment;
				parent.getContents().add(sie);
				em.merge(parent);
				
			}catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		
		return true;
	}

}
