package br.ufrj.spemarti.webservice;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufrj.spemarti.webservice.entity.ComplexInformationElement;
import br.ufrj.spemarti.webservice.entity.Diagram;
import br.ufrj.spemarti.webservice.entity.FileElement;
import br.ufrj.spemarti.webservice.entity.FileSvn;
import br.ufrj.spemarti.webservice.entity.Image;
import br.ufrj.spemarti.webservice.entity.List;
import br.ufrj.spemarti.webservice.entity.Matrix;
import br.ufrj.spemarti.webservice.entity.Question;
import br.ufrj.spemarti.webservice.entity.SimpleInformationElement;
import br.ufrj.spemarti.webservice.entity.Version;
import br.ufrj.spemarti.webservice.entity.VersionHistory;

@Stateless
public class VersionHandler implements IVersionHandler{

	@PersistenceContext(unitName="titan")
	private EntityManager em;
	
	public Integer commit(SimpleInformationElement sie){
		if(sie instanceof ComplexInformationElement){
			if(sie instanceof Diagram){
				commitDiagram((Diagram) sie);
			}else if(sie instanceof Matrix){
				
				
			}else if(sie instanceof List){
				
				
			}else if(sie instanceof Question){
				
			}
				
				
			
			
		}
		return null;
	}
	
	public Integer commitDiagram(Diagram diagram){
		int maiorValorRevisao = 0;
		for(Image im : diagram.getListaImagem()){
			int valorRevisao = commit(im);
			if(valorRevisao > maiorValorRevisao){
				maiorValorRevisao= valorRevisao;
			}
		}
		
		VersionHistory vh = null;
		if(diagram.getPreviousVersion()!=null){
			vh = recuperarVersionHistory(diagram.getPreviousVersion().getId());
			if(vh==null){
				vh = new VersionHistory();
			}
		}else{
			vh = new VersionHistory();
		}
		
		
		return null;
		
	}
	
	private VersionHistory recuperarVersionHistory(Integer versionId){
		Query query = em.createNamedQuery("VersionHistory.recuperarPorVersao");
		query.setParameter("id", versionId);
		try{
			return (VersionHistory) query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}catch(Exception e){
			return null;
		}
		
		
	}

	public FileSvn checkOut(Version version) {
		Version realVersion = em.find(Version.class, version.getId());
		
		if(realVersion instanceof FileElement){ //um fileElement não possui um arquivo, somente seus filhos.
			return new FileSvn();
		}else{
			SimpleInformationElement sie = (SimpleInformationElement)realVersion;
			return sie.getFile();
		}
		
	}
	
}
