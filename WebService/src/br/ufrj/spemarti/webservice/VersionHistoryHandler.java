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
	
	public boolean verificaExistenciaVersionamentoAtivoArtifact(String presentationName, String filePath, String folder, String fileName) {
		
		return recuperaVersionHistoryAtivoArtifact(presentationName, filePath, folder, fileName)!=null;
	}

	public VersionHistory recuperaVersionHistoryAtivoArtifact(String presentationName, String filePath, String folder, String fileName) {
		Query query = em.createNamedQuery("VersionHistory.recuperaAtivoArtifact");
		query.setParameter("presentationName", presentationName);
		query.setParameter("filePath", filePath);
		query.setParameter("folder", folder);
		query.setParameter("fileName", fileName);
		return (VersionHistory) query.getSingleResult();
	}
	
	

}
