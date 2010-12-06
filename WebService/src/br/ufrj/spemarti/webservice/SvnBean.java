package br.ufrj.spemarti.webservice;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufrj.spemarti.webservice.entity.FileElement;
import br.ufrj.spemarti.webservice.entity.FileSvn;
import br.ufrj.spemarti.webservice.entity.OrderElement;
import br.ufrj.spemarti.webservice.entity.SimpleInformationElement;
import br.ufrj.spemarti.webservice.entity.User;
import br.ufrj.spemarti.webservice.entity.Version;

@Stateless
@WebService
@SOAPBinding(style = Style.RPC)
public class SvnBean implements Svn{
	
	@PersistenceContext(unitName="titan")
	private EntityManager em;
	
	@EJB
	IVersionHandler versionHandler;
	
		@WebMethod
        public String echo(String e) {
                return "Web Service Echo + " + e;
        }

        @WebMethod(operationName="checkInSimpleElement")
		public void checkIn(SimpleInformationElement sie) {
			
			versionHandler.commit(sie);
		}
        
        @WebMethod(operationName="checkInFileElement")
        public void checkIn(FileElement fileElement){
        	
        	
        }

		@WebMethod
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
		
		@WebMethod
		public void removeUser(String login, String password){
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
		
		@WebMethod
		public void updateUser(String login, String oldPass, String newPass){
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

		/**
		 * Recupera todos os elementos que pertencem a um caminho específico do svn.
		 * Ex: A raiz do SVN é recuperada a partir de '/' 
		 * 
		 * Não recupera os arquivos apenas a representação deles no svn
		 */
		public VersionedExtentGroup showSvnRepository(String path) {
			if(path == null || path.equals("")){
				return new VersionedExtentGroup();
			}
			
			Query query = em.createNamedQuery("Version.recuperarRootPorPath");
			query.setParameter("path", path);
			VersionedExtentGroup veg = new VersionedExtentGroup();
			
			List<Version> listaVersao = query.getResultList();
			
			for(Version v : listaVersao){
				
				if(v instanceof FileElement){ //somente arquivos podem fazer parte da árvore primária do svn
											  //mesmo que esses arquivos só contenham um SimpleInformationElement
					FileElement fe = (FileElement)v;
					VersionedExtent ve = new VersionedExtent();
					ve.setAnnotation(v.getAnnotation());
					ve.setBaseVersion(v);
					ve.setSimpleInformationElementCount(fe.getElementList().size());
					ve.setFileName(v.getVersionHistory().getFileName());
					ve.setFolder(v.getVersionHistory().getFolder());
					ve.setIsCheckedOut(false);
					ve.setPath(v.getVersionHistory().getFilePath());
					veg.getVersionedExtent().add(ve);
				}
			
			}
			return veg;
		}
		
		/**
		 * Recupera os subItens de um arquivo ou de um simpleInformationElement pai.	
		 * @param completePath
		 * @return
		 */
		public VersionedExtentGroup showSvnRepositorySubItem(String path, String folder, String fileName) {
			if(path == null || path.equals("") || folder == null || folder.equals("") || fileName == null || fileName.equals("") ){
				return new VersionedExtentGroup();
			}
			
			Query query = em.createNamedQuery("Version.recuperarRootPorPathFolderFileName");
			query.setParameter("path", path);
			query.setParameter("folder", folder);
			query.setParameter("fileName", fileName);
			VersionedExtentGroup veg = new VersionedExtentGroup();
			
			Version v;
			try{
			v = (Version) query.getSingleResult();
			
			}catch (Exception e) {
				return veg;
			}
			
			if(v!=null){
				FileElement fe = (FileElement)v;
				for(OrderElement order : fe.getElementList()){
							
					VersionedExtent ve = new VersionedExtent();
					SimpleInformationElement sie = order.getSimpleInformationElement();
					ve.setAnnotation(sie.getAnnotation());
					ve.setBaseVersion(sie);
					ve.setSimpleInformationElementCount(0);
					ve.setFileName(sie.getVersionHistory().getFileName());
					ve.setFolder(sie.getVersionHistory().getFolder());
					ve.setIsCheckedOut(false);
					ve.setPath(sie.getVersionHistory().getFilePath());
					veg.getVersionedExtent().add(ve);
					
				}
			}
			return veg;
		}

		@WebMethod
		public FileSvn checkOut(Version version) {
			return versionHandler.checkOut(version);
			
		}
}
