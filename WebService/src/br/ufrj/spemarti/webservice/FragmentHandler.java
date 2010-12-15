package br.ufrj.spemarti.webservice;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufrj.spemarti.webservice.entity.FragmentDefinition;
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
	
	public FragmentDefinition commit(FragmentDefinition fragment,  Integer idUsuario){
		
		User user = userHandler.recuperarPorId(idUsuario);
		if(user==null){
			return null;
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
			return null;
		}	
		
		return fragment;
		
	}
	
	private FragmentDefinition modifyFragment(FragmentDefinition fragment, User user){
		//recuperamos o historico do fragmento.
		VersionHistory vh = vhHandler.recuperaVersionHistoryAtivo(fragment.getPresentationName());
		if(vh==null){
			return null;
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
		
		//TODO alterar todos os artefatos que contem este fragmento.
		
			em.flush();
		}catch (Exception e) {
			return null;
		}	
		return fragment;
		
	}
	
	public FragmentDefinition commit(FragmentDefinition parent, FragmentDefinition fragment, Integer idUsuario) {
		
		User user = userHandler.recuperarPorId(idUsuario);
		if(user==null){
			return null;
		}
		
		//se o fragmento pai ainda não estiver sendo versionado não é possível criar o fragmento filho.
		if(vhHandler.verificaExistenciaVersionamentoAtivo(parent.getPresentationName()) ){
			return null;
		}
		
		//caso o fragmento já esteja sendo versionado
		if(vhHandler.verificaExistenciaVersionamentoAtivo(fragment.getPresentationName()) ){
			return modifyFragment(parent, fragment, user);
		
		}else{//caso seja um novo fragmento deve-se versioná-lo.
			return createFragment(parent, fragment, user);
		}
	}
	
	private FragmentDefinition createFragment(FragmentDefinition pai, FragmentDefinition fragment, User user){
		
		if(pai!=null && pai.getId()!=null){
			//se o pai nao for um tipo complexo, não posso agregar um outro elemento dentro dele.
			if(!Utils.isComplexInformationInstance(pai)){
				return null;
			}
			
			pai = em.find(FragmentDefinition.class, pai.getId());
		}else{
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
	
	private FragmentDefinition modifyFragment(FragmentDefinition pai, FragmentDefinition fragment, User user){
		return null;
	}

	public boolean remove(String presentationName, Integer idUsuario) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean remove(FragmentDefinition parent, String presentationName,
			Integer idUsuario) {
		// TODO Auto-generated method stub
		return false;
	}
}
