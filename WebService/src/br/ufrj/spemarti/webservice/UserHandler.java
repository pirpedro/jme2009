package br.ufrj.spemarti.webservice;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufrj.spemarti.webservice.entity.User;

@Stateless
public class UserHandler implements IUserHandler{
	
	@PersistenceContext(unitName="titan")
	private EntityManager em;

	public void createUser(String login, String password) {
		if(recuperarUsuario(login)==null){
			try{
				em.persist(new User(login, password));
				em.flush();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
	}

	public void removeUser(String login, String password) {
		User user = recuperarUsuario(login);
		if(user!=null){
			try{
				if(user.getPassword().equals(password)){
					em.remove(user);
					em.flush();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
	}

	public void updateUser(String login, String oldPass, String newPass) {
		User user = recuperarUsuario(login);
		if(user!=null){
			if(user.getPassword().equals(oldPass)){
				user.setPassword(newPass);
				em.merge(user);
				em.flush();
			}
		}
		
	}
	
	private User recuperarUsuario(String login){
		Query query = em.createNamedQuery("User.recuperarPorLogin");
		query.setParameter("login", login);
		try{
			return (User) query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}catch (Exception e) {
			return null;
		}
	}
	
	public User recuperarPorId(Integer idUsuario){
		return em.find(User.class, idUsuario);
	}

}
