package br.ufrj.spemarti.webservice;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufrj.spemarti.webservice.entity.Diagram;
import br.ufrj.spemarti.webservice.entity.ElementGroup;
import br.ufrj.spemarti.webservice.entity.FragmentDefinition;
import br.ufrj.spemarti.webservice.entity.List;
import br.ufrj.spemarti.webservice.entity.Matrix;
import br.ufrj.spemarti.webservice.entity.Question;
import br.ufrj.spemarti.webservice.entity.Table;
import br.ufrj.spemarti.webservice.entity.User;
import br.ufrj.spemarti.webservice.entity.VersionHistory;

@Stateless
public class FragmentHandler implements IFragmentHandler{

	@PersistenceContext(unitName="titan")
	private EntityManager em;
	
	@EJB
	IUserHandler userHandler;
	
	@EJB
	IVersionHistoryHandler vhHandler;
	
	@EJB
	IDiagramHandler diagramHandler;
	
	@EJB
	IQuestionHandler questionHandler;
	
	@EJB
	IListHandler listHandler;
	
	@EJB
	IElementGroupHandler egHandler;
	
	@EJB
	IMatrixHandler matrixHandler;
	
	public FragmentDefinition commit(FragmentDefinition fragment,  Integer idUsuario){
		
		User user = userHandler.recuperarPorId(idUsuario);
		if(user==null){
			throw new RuntimeException("Não foi informado o usuário que commitou o arquivo");
		}
		
		//caso o fragmento já esteja sendo versionado
		if(vhHandler.verificaExistenciaVersionamentoAtivo(fragment.getPresentationName()) ){
			return modifyFragment(fragment, user);
		
		}else{//caso seja um novo fragmento deve-se versioná-lo.
			return createFragment(fragment, user);
		}
	}
	
	private FragmentDefinition createFragment(FragmentDefinition fragment, User user) {
		
		fragment.setCreationDate(new Date());
		fragment.setUser(user);
		fragment.setRevision(0);
		VersionHistory vh = new VersionHistory();
		vh.getVersions().add(fragment);
		vh.setRootVersion(fragment);
		fragment.setVersionHistory(vh);
		
		try{
			em.persist(fragment);
			em.persist(vh);
			em.flush();
		}catch (Exception e) {
			throw new RuntimeException("Não foi possível versionar o fragmento" + fragment.getPresentationName());
		}	
		
		return fragment;
		
	}
	
	private FragmentDefinition modifyFragment(FragmentDefinition fragment, User user){
		//recuperamos o historico do fragmento.
		VersionHistory vh = vhHandler.recuperaVersionHistoryAtivo(fragment.getPresentationName());
		if(vh==null){
			throw new RuntimeException("Não foi possível recuperar o histórico do fragmento " + fragment.getPresentationName());
		}
		
		//o fragmento que vem para ser modificado deve conter o numero de revisão de sua versão anterior para comparação.
		if(vh.getRootVersion().getRevision()!= fragment.getRevision()){
			throw new RuntimeException("Fragmento não sincronizado com o svn.");
		}
		
		fragment.setCreationDate(new Date());
		fragment.setUser(user);
		fragment.setRevision(vh.generateNewRevisionNumber());
		vh.getVersions().add(fragment);
		vh.setRootVersion(fragment);
		fragment.setVersionHistory(vh);
		
		try{
			em.persist(fragment);
			em.merge(vh);
			em.flush();
		}catch (Exception e) {
			throw new RuntimeException("Erro ao modificar o fragmento" + fragment.getPresentationName(), e);
		}	
		return fragment;
		
	}
	
	public FragmentDefinition commit(FragmentDefinition parent, FragmentDefinition fragment, Integer idUsuario) {
		
		User user = userHandler.recuperarPorId(idUsuario);
		if(user==null){
			throw new RuntimeException("Usuário não encontrado");
		}
		
		//se o fragmento pai ainda não estiver sendo versionado não é possível criar o fragmento filho.
		if(!vhHandler.verificaExistenciaVersionamentoAtivo(parent.getPresentationName()) ){
			throw new RuntimeException("O fragmento" + parent.getPresentationName() + "ainda não foi versionado.");
		}
		
		parent = em.find(FragmentDefinition.class, parent.getId());
		
		if(parent!=null && parent.getId()!=null){
			//se o pai nao for um tipo complexo, não posso agregar um outro elemento dentro dele.
			if(!Utils.isComplexInformationInstance(parent)){
				throw new RuntimeException("Não é possível agregar o fragmento " +fragment.getPresentationName()+ " ao fragmento" + parent.getPresentationName());
			}
		}	
		
		//caso o fragmento já esteja sendo versionado
		if(vhHandler.verificaExistenciaVersionamentoAtivo(fragment.getPresentationName()) ){
			return modifyFragment(parent, fragment, user);
		
		}else{//caso seja um novo fragmento deve-se versioná-lo.
			return createFragment(parent, fragment, user);
		}
	}
	
	private FragmentDefinition createFragment(FragmentDefinition pai, FragmentDefinition fragment, User user){
		
		//torno ele uma entidade persistente
		fragment = createFragment(fragment, user);
		
		if(pai instanceof Diagram){
			diagramHandler.associate((Diagram) pai, fragment);
		
		}else if(pai instanceof Question){
			questionHandler.associate((Question) pai, fragment);
		
		}else if(pai instanceof List){
			listHandler.associate((List) pai, fragment);
		
		}else if(pai instanceof ElementGroup){
			egHandler.associate((ElementGroup) pai, fragment);
		
		}else if(pai instanceof Matrix){
			matrixHandler.associate((Matrix) pai, fragment);
		
		}else if(pai instanceof Table){
			matrixHandler.associate((Table) pai, fragment);
		
		}
		
		return fragment;
	}
	
	private FragmentDefinition modifyFragment(FragmentDefinition pai, FragmentDefinition fragment, User user){
		//modificar o fragmento
		fragment = modifyFragment(fragment, user);
		
		return null;
	}

	public boolean remove(FragmentDefinition fragment, Integer idUsuario) {
		//para deletar um fragmento é apenas necessário deletar seu histórico.
		VersionHistory vh = vhHandler.recuperaVersionHistoryAtivo(fragment.getPresentationName());
		if(vh==null){
			throw new RuntimeException("Não existe fragmento versionado com o nome "+ fragment.getPresentationName());
		}
		
		if(!Utils.isFragmentInstance(vh.getRootVersion())){
			throw new RuntimeException(fragment.getPresentationName() +" não é um fragmento válido");
		}
		
		//se o numero de revisão do artefato a ser excluido nao for igual a raiz do histórico significa que o usuário tentou excluir uma versão não sincronizada.
		if(!vh.getRootVersion().getRevision().equals(fragment.getRevision())){
			throw new RuntimeException("Fragmento "+fragment.getPresentationName()+ " não sincronizado. Não foi possível excluí-lo");
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

	public boolean remove(FragmentDefinition parent, String presentationName,
			Integer idUsuario) {
		// TODO Auto-generated method stub
		return false;
	}
}
