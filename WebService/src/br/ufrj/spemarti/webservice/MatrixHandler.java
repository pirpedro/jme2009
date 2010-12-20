package br.ufrj.spemarti.webservice;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufrj.spemarti.webservice.entity.FragmentDefinition;
import br.ufrj.spemarti.webservice.entity.List;
import br.ufrj.spemarti.webservice.entity.Matrix;

@Stateless
public class MatrixHandler implements IMatrixHandler{

	@PersistenceContext(unitName="titan")
	private EntityManager em;
	
	public boolean associate(Matrix parent, FragmentDefinition fragment) {
		if(! (fragment instanceof List)){
			throw new RuntimeException("Não foi possível associar os artefatos.");
		
		}else{
			
			try{
				List ls = (List) fragment;
				if(ls.getType().equals(ListType.HEADER)){
					parent.setHeader(ls);
					
				}else if(ls.getType().equals(ListType.LINE)){
					parent.getLines().add(ls);
					
				}
				
				
				em.merge(parent);
				
			}catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		
		return true;
	}
	
}
