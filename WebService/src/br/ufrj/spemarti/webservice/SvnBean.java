package br.ufrj.spemarti.webservice;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufrj.spemarti.webservice.entity.ArtifactDefinition;
import br.ufrj.spemarti.webservice.entity.FragmentDefinition;
import br.ufrj.spemarti.webservice.entity.OperationType;
import br.ufrj.spemarti.webservice.entity.Version;

@Stateless
@WebService
@SOAPBinding(style = Style.RPC)
public class SvnBean implements Svn{
	
	@PersistenceContext(unitName="titan")
	private EntityManager em;
	
	@EJB
	IFragmentHandler fragmentHandler;
	
	@EJB
	IArtifactHandler artifactHandler;
	
	@EJB
	IUserHandler userHandler;
	
		@WebMethod
        public String echo(String e) {
                return "Web Service Echo + " + e;
        }

        @WebMethod(operationName="checkInFragment")
		public Version checkIn(FragmentDefinition fragment, Integer userId) {
			
			return fragmentHandler.commit(fragment, userId);
			
			
        }
        
        @WebMethod
		public void createUser(String login, String password) {
			userHandler.createUser(login, password);
			
		}
		
		@WebMethod
		public void removeUser(String login, String password){
			userHandler.removeUser(login, password);
		}
		
		@WebMethod
		public void updateUser(String login, String oldPass, String newPass){
			userHandler.updateUser(login, oldPass, newPass);
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
				
			}
			return veg;
		}

		@WebMethod(operationName="checkInArtifact")
		public Version checkIn(ArtifactDefinition artifact, String filePath, String folder, String fileName, Integer userId) {
			return artifactHandler.commit(artifact, filePath, folder, fileName, userId);
			
		}

		@WebMethod(operationName="checkInFragmentArtifact")
		public Version checkIn(FragmentDefinition fragment,	ArtifactDefinition parent, Integer userId) {
			// TODO Auto-generated method stub
			return null;
		}

		@WebMethod(operationName="checkInFragmentFragment")
		public Version checkIn(FragmentDefinition parent, FragmentDefinition fragment, Integer userId) {
			// TODO Auto-generated method stub
			return null;
		}

		
}
