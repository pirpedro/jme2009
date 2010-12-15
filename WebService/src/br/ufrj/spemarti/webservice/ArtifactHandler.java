package br.ufrj.spemarti.webservice;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufrj.spemarti.webservice.entity.ArtifactDefinition;
import br.ufrj.spemarti.webservice.entity.ArtifactFragment_Relationship;
import br.ufrj.spemarti.webservice.entity.FragmentDefinition;
import br.ufrj.spemarti.webservice.entity.FragmentRelationship;
import br.ufrj.spemarti.webservice.entity.User;
import br.ufrj.spemarti.webservice.entity.VersionHistory;

@Stateless
public class ArtifactHandler implements IArtifactHandler{

	@PersistenceContext(unitName="titan")
	private EntityManager em;
	
	@EJB
	IUserHandler userHandler;
	
	@EJB
	IVersionHistoryHandler vhHandler;
	
	public ArtifactDefinition commit(ArtifactDefinition artifact,
			String filePath, String folder, String fileName,Integer idUsuario) {
		User user = userHandler.recuperarPorId(idUsuario);
		if(user==null){
			return null;
		}
		
		//caso o artefato j� esteja sendo versionado
		if(vhHandler.verificaExistenciaVersionamentoAtivo(artifact.getPresentationName()) ){
			return modifyArtifact(artifact, user);
		
		}else{//caso seja um novo artefato deve-se version�-lo.
			return createArtifact(artifact, filePath, folder,  fileName,user);
		}
	}
	
	private ArtifactDefinition createArtifact(ArtifactDefinition artifact, String filePath, String folder, String fileName, User user){
		artifact.setCreationDate(new Date());
		artifact.setUser(user);
		artifact.setRevision(0);
		
		VersionHistory vh = new VersionHistory();
		vh.setFileName(fileName);
		vh.setFilePath(filePath);
		vh.setFolder(folder);
		vh.getVersions().add(artifact);
		vh.setRootVersion(artifact);
		artifact.setVersionHistory(vh);
		
		try{
			em.persist(artifact);
			em.persist(vh);
			em.flush();
		}catch (Exception e) {
			return null;
		}	
		
		return artifact;
	}
	
	private ArtifactDefinition modifyArtifact(ArtifactDefinition artifact, User user){
		
		VersionHistory vh = vhHandler.recuperaVersionHistoryAtivo(artifact.getPresentationName());
		
		//se a vers�o anterior do artefato que estou tentando commitar n�o for igual a vers�o atual do hist�rico, a opera��o � cancelada.
		if(artifact.getPreviousVersion()==null){
			throw new RuntimeException("N�o foi definida uma vers�o anterior para o artefato que est� sendo modificado");
		}
		
		if(!artifact.getPreviousVersion().getRevision().equals(vh.getRootVersion().getRevision())){
			throw new RuntimeException("O artefato " + artifact.getPresentationName() + "j� foi alterado previamente");
			
		}
		
		artifact.setCreationDate(new Date());
		artifact.setUser(user);
		artifact.setRevision(vh.generateNewRevisionNumber());
		
		Integer lastRootId = vh.getRootVersion().getId();
		vh.getVersions().add(artifact);
		vh.setRootVersion(artifact);
		artifact.setVersionHistory(vh);
		try{
			em.persist(artifact);
			em.merge(vh);
			em.flush();
			
		}catch (Exception e) {
			throw new RuntimeException("N�o foi poss�vel modificar o artefato "+ artifact.getPresentationName(), e);
		}
		
		//recupero os relacionamentos do artefato antigo e recrio para o novo
		for(ArtifactFragment_Relationship rel : recuperarRelacionamentosArtefato(lastRootId)){
			ArtifactFragment_Relationship newRel = new ArtifactFragment_Relationship();
			newRel.setArtifact(artifact);
			newRel.setContainers(rel.getContainers());
			em.persist(newRel);
		}
		em.flush();
		
		return artifact;
	}

	public ArtifactDefinition commit(ArtifactDefinition parent,
			FragmentDefinition fragment, Integer idUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean remove(String presentationName, Integer idUsuario) {
		
		//para deletar um artefato � apenas necess�rio deletar seu hist�rico.
		VersionHistory vh = vhHandler.recuperaVersionHistoryAtivo(presentationName);
		vh.setIsDeleted(true);
		try{
			em.merge(vh);
			em.flush();
		}catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean remove(ArtifactDefinition parent, String presentationName,
			Integer idUsuario) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<ArtifactFragment_Relationship> recuperarRelacionamentosArtefato(
			Integer idArtefato) {
		Query query = em.createNamedQuery("ArtifactFragment.recuperarPorArtifact");
		query.setParameter("idArtifact", idArtefato);
		return query.getResultList();
	}

}
