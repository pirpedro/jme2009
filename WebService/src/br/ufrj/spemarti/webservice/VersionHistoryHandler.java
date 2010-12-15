package br.ufrj.spemarti.webservice;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufrj.spemarti.webservice.entity.VersionHistory;

@Stateless
public class VersionHistoryHandler implements IVersionHistoryHandler{

	@PersistenceContext(unitName="titan")
	private EntityManager em;
	
	public boolean verificaExistenciaVersionamentoAtivo(String presentationName) {
		
		return recuperaVersionHistoryAtivo(presentationName)!=null;
	}

	public VersionHistory recuperaVersionHistoryAtivo(String presentationName) {
		Query query = em.createNamedQuery("VersionHistory.recuperaAtivo");
		query.setParameter("presentationName", presentationName);
		return (VersionHistory) query.getSingleResult();
	}
	
	

}
