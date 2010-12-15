package br.ufrj.spemarti.webservice;

import javax.ejb.Local;

import br.ufrj.spemarti.webservice.entity.User;

@Local
public interface IUserHandler {

	void createUser(String login, String password);
	
	void removeUser(String login, String password);
	
	void updateUser(String login, String oldPass, String newPass);
	
	User recuperarPorId(Integer idUsuario);
	
}
