package br.ufrj.spemarti.webservice;

import java.rmi.Remote;

import br.ufrj.spemarti.webservice.entity.SimpleInformationElement;

@javax.ejb.Remote
public interface Svn extends Remote {
        
		String echo(String e);
        
        void checkIn(SimpleInformationElement sie);
        
        void createUser(String login, String password);
        void removeUser(String login, String password);
        void updateUser(String login, String oldPass, String newPass);
        
        VersionedExtentGroup showSvnRepository(String path);
       VersionedExtentGroup showSvnRepositorySubItem(String path, String folder, String fileName);
    		
       
}
