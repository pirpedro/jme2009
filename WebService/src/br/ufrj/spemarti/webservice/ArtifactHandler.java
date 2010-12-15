package br.ufrj.spemarti.webservice;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufrj.spemarti.webservice.entity.ArtifactDefinition;
import br.ufrj.spemarti.webservice.entity.FragmentDefinition;
import br.ufrj.spemarti.webservice.entity.User;

@Stateless
public class ArtifactHandler implements IArtifactHandler{

	@PersistenceContext(unitName="titan")
	private EntityManager em;
	
	@EJB
	IUserHandler userHandler;
	
	@EJB
	IVersionHistoryHandler vhHandler;
	
	public ArtifactDefinition commit(ArtifactDefinition fragment,
			Integer idUsuario) {
		return null;
	}
	
	private ArtifactDefinition createArtefact(ArtifactDefinition fragment, User user){
		return null;
	}

	public ArtifactDefinition commit(ArtifactDefinition parent,
			FragmentDefinition fragment, Integer idUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean remove(String presentationName, Integer idUsuario) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean remove(ArtifactDefinition parent, String presentationName,
			Integer idUsuario) {
		// TODO Auto-generated method stub
		return false;
	}

}
