package br.ufrj.spemarti.webservice;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufrj.spemarti.webservice.entity.Diagram;
import br.ufrj.spemarti.webservice.entity.FragmentDefinition;
import br.ufrj.spemarti.webservice.entity.Image;
import br.ufrj.spemarti.webservice.entity.User;
import br.ufrj.spemarti.webservice.entity.VersionHistory;

@Stateless
public class VersionCommitHandler implements IVersionCommitHandler{

	@PersistenceContext(unitName="titan")
	private EntityManager em;
	
	@EJB
	IUserHandler userHandler;
	
	@EJB
	IVersionHistoryHandler vhHandler;
	
	public Integer commit(FragmentDefinition fragment,  Integer idUsuario){
		
		
		createFragment(fragment, idUsuario);
		
		
		
		
		return null;
	}
	
	private Integer createFragment(FragmentDefinition fragment, Integer idUsuario) {
		User user = userHandler.recuperarPorId(idUsuario);
		
		if(user ==null){
			return null;
		}
		
		if(vhHandler.verificaExistenciaVersionamentoAtivo(fragment.getPresentationName()) ){
			return null;
		}
		
		fragment.setCreationDate(new Date());
		fragment.setUser(user);
		fragment.setRevision(0);
		VersionHistory vh = new VersionHistory();
		vh.getVersions().add(fragment);
		vh.setRootVersion(fragment);
		fragment.setVersionHistory(vh);
		em.persist(fragment);
		em.persist(vh);
		em.flush();
		
		return fragment.getRevision();
		
	}
	
	private Integer createFragment(FragmentDefinition pai, FragmentDefinition fragment, Integer idUsuario){
		em.refresh(pai);
		User user = userHandler.recuperarPorId(idUsuario);
		if(user ==null){
			return null;
		}
		fragment.setCreationDate(new Date());
		fragment.setUser(user);
		fragment.setRevision(0);
		VersionHistory vh = new VersionHistory();
		vh.getVersions().add(fragment);
		vh.setRootVersion(fragment);
		em.persist(fragment);
		em.persist(vh);
		em.flush();
		
		
		return null;
	}

	private Integer checkIn(FragmentDefinition fragment){
		
		return null;
		
		
	}
	
	public Integer commitDiagram(Diagram diagram){
		int maiorValorRevisao = 0;
		for(Image im : diagram.getListaImagem()){
		
		}
		
		VersionHistory vh = null;
		if(diagram.getPreviousVersion()!=null){
			vh = recuperarVersionHistory(diagram.getPreviousVersion().getId());
			if(vh==null){
				vh = new VersionHistory();
			}
		}else{
			vh = new VersionHistory();
		}
		
		
		return null;
		
	}
	
	private VersionHistory recuperarVersionHistory(Integer versionId){
		Query query = em.createNamedQuery("VersionHistory.recuperarPorVersao");
		query.setParameter("id", versionId);
		try{
			return (VersionHistory) query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}catch(Exception e){
			return null;
		}
		
		
	}

	
	
}
