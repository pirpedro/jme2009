package br.ufrj.spemarti.webservice;

import java.rmi.Remote;

import br.ufrj.spemarti.webservice.entity.ArtifactDefinition;
import br.ufrj.spemarti.webservice.entity.FragmentDefinition;
import br.ufrj.spemarti.webservice.entity.Version;

@javax.ejb.Remote
public interface Svn extends Remote {
        
		String echo(String e);
        
		/**
		 * 
		 * commita um novo fragmento ou uma nova versão dele
		 * 
		 * @param fragment
		 * @param userId
		 * @return retorna o numero de revisão gerado para a nova versão do fragmento.
		 */
	    Version checkIn(FragmentDefinition fragment, Integer userId);
        
	    Version checkIn(ArtifactDefinition artifact, String filePath, String folder, String fileName, Integer userId);
	    Version checkIn(FragmentDefinition fragment, ArtifactDefinition parent, Integer userId);
        
	    Version checkIn(FragmentDefinition parent, FragmentDefinition fragment, Integer userId);
	    
	    boolean remove(String presentationName, Integer userId);
               
        void createUser(String login, String password);
        void removeUser(String login, String password);
        void updateUser(String login, String oldPass, String newPass);
        
        VersionedExtentGroup showSvnRepository(String path);
       VersionedExtentGroup showSvnRepositorySubItem(String path, String folder, String fileName);
    		
       
}
