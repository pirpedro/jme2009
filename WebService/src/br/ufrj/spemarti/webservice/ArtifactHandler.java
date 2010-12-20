package br.ufrj.spemarti.webservice;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufrj.spemarti.webservice.entity.ArtifactDefinition;
import br.ufrj.spemarti.webservice.entity.ArtifactFragment_Relationship;
import br.ufrj.spemarti.webservice.entity.FragmentDefinition;
import br.ufrj.spemarti.webservice.entity.User;
import br.ufrj.spemarti.webservice.entity.Version;
import br.ufrj.spemarti.webservice.entity.VersionHistory;

@Stateless
public class ArtifactHandler implements IArtifactHandler{

	@PersistenceContext(unitName="titan")
	private EntityManager em;
	
	@EJB
	IUserHandler userHandler;
	
	@EJB
	IVersionHistoryHandler vhHandler;
	
	@EJB
	IFragmentHandler fragmentHandler;
	
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
		
		Version lastRoot = vh.getRootVersion();
		lastRoot.setNextVersion(artifact);
		artifact.setPreviousVersion(lastRoot);
		Integer lastRootId = vh.getRootVersion().getId();
		vh.getVersions().add(artifact);
		vh.setRootVersion(artifact);
		artifact.setVersionHistory(vh);
		try{
			em.persist(artifact);
			em.merge(lastRoot);
			em.merge(vh);
			em.flush();
			
		}catch (Exception e) {
			throw new RuntimeException("N�o foi poss�vel modificar o artefato "+ artifact.getPresentationName(), e);
		}
		
		//recupero os relacionamentos do artefato antigo e recrio para o novo
		for(ArtifactFragment_Relationship rel : ((ArtifactDefinition)lastRoot).getArtifactFragment()){
			ArtifactFragment_Relationship newRel = new ArtifactFragment_Relationship();
			newRel.setArtifact(artifact);
			newRel.getContainers().addAll(rel.getContainers());
			em.persist(newRel);
		}
		em.flush();
		
		return artifact;
	}

	
	public FragmentDefinition commit(ArtifactDefinition parent,	FragmentDefinition fragment, Integer idUsuario) {
		User user = userHandler.recuperarPorId(idUsuario);
		if(user ==null){
			throw new RuntimeException("Usuario n�o existente");
		}
		parent = validaArtefato(parent, user);
		
		if(parent==null){
			throw new RuntimeException("N�o foi poss�vel completar a opera��o");
		}
		
		fragment = fragmentHandler.commit(fragment, idUsuario);
		ArtifactFragment_Relationship rel = recuperaRelacionamento(parent.getId(), fragment.getPresentationName());
		
		try{
			if(rel!=null){
				//relacionamento direto s� possui um fragmento, posso excluir direto.
				em.remove(rel);
			}
			ArtifactFragment_Relationship newRel = new ArtifactFragment_Relationship();
			newRel.setArtifact(parent);
			newRel.getContainers().add(fragment);
			em.merge(newRel);
		}catch (Exception e) {
			throw new RuntimeException("N�o foi poss�vel realizar a opera��o");
		}
		
		return fragment;
	}
	
	/**
	 * Alterar o m�todo para contemplar a busca por relacionamento de container.
	 * @param idArtefato
	 * @param idFragment
	 * @return
	 */
	private ArtifactFragment_Relationship recuperaRelacionamento(Integer idArtefato, String  fragmentName){
		Query query = em.createNamedQuery("ArtifactFragmentRelationship.recuperarRelacionamento");
		query.setParameter("idArtefato",idArtefato);
		query.setParameter("fragmentName", fragmentName);
		try{
			//considero que por enquanto um fragmento s� pode participar de um relacionamento direto com um artefato.
			return (ArtifactFragment_Relationship) query.getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}
	
	private ArtifactDefinition validaArtefato(ArtifactDefinition artefato, User user){
		artefato = em.find(ArtifactDefinition.class,artefato.getId());
		VersionHistory vh = artefato.getVersionHistory();
		if(vh.getIsDeleted()){
			throw new RuntimeException("o artefato" + artefato.getPresentationName() + "n�o �emais versionado");
		}
		
		if(!vh.getRootVersion().getId().equals(artefato.getId())){
			throw new RuntimeException("Artefato " + artefato.getPresentationName() + " n�o sincronizado com o svn");
		}
		
		if(!artefato.getUser().getId().equals(user.getId())){
			throw new RuntimeException("Artefato " + artefato.getPresentationName() + " n�o sincronizado com o svn");
		}
		
		return artefato;
		
	}
	
	public boolean remove(ArtifactDefinition artifact, Integer idUsuario) {
		
		//para deletar um artefato � apenas necess�rio deletar seu hist�rico.
		VersionHistory vh = vhHandler.recuperaVersionHistoryAtivo(artifact.getPresentationName());
		if(vh==null){
			throw new RuntimeException("N�o existe artefato versionado com o nome "+ artifact.getPresentationName());
		}
		
		if(!Utils.isArtifactInstance(vh.getRootVersion())){
			throw new RuntimeException(artifact.getPresentationName() +" n�o � um artefato");
		}
		
		//se o numero de revis�o do artefato a ser excluido nao for igual a raiz do hist�rico significa que o usu�rio tentou excluir uma vers�o n�o sincronizada.
		if(!vh.getRootVersion().getRevision().equals(artifact.getRevision())){
			throw new RuntimeException("Artefato "+artifact.getPresentationName()+ " n�o sincronizado. N�o foi poss�vel exclu�-lo");
		}
		
		vh.setIsDeleted(true);
		try{
			em.merge(vh);
			em.flush();
		}catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean remove(ArtifactDefinition parent, FragmentDefinition fragment,
			Integer idUsuario) {
		User user = userHandler.recuperarPorId(idUsuario);
		if(user == null){
			throw new RuntimeException("usuario nao encontrado");
		}
		parent = validaArtefato(parent, user);
		if(parent==null){
			throw new RuntimeException("N�o foi poss�vel completar a opera��o");
		}
		
		fragment = validaFragmento(fragment, user);
		if(fragment == null){
			throw new RuntimeException("N�o foi poss�vel completar a opera��o");
		}
		
		ArtifactFragment_Relationship rel = recuperaRelacionamento(parent.getId(), fragment.getPresentationName());
		
		if(rel==null){
			throw new RuntimeException("O fragmento " + fragment.getPresentationName() + "n�o existe no artefato " + parent.getPresentationName());
		}
		
		try{
			em.remove(rel);
		}catch (Exception e) {
			return false;
		}
		
		
		return true;
	}
	

	public List<ArtifactFragment_Relationship> recuperarRelacionamentosArtefato(
			Integer idArtefato) {
		Query query = em.createNamedQuery("ArtifactFragment.recuperarPorArtifact");
		query.setParameter("idArtifact", idArtefato);
		
		try{
			return query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	public FragmentDefinition validaFragmento(FragmentDefinition fragment, User user){
		VersionHistory vh = vhHandler.recuperaVersionHistoryAtivo(fragment.getPresentationName());
		
		Version root = vh.getRootVersion();
		
		if(!root.getId().equals(fragment.getId())){
			throw new RuntimeException("N�o foi poss�vel realizar a opera��o");
		}
		if(!root.getRevision().equals(fragment.getRevision())){
			throw new RuntimeException("N�o foi poss�vel realizar a opera��o");
		}
		
		return fragment;
		
		
	}

}
