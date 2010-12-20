package br.ufrj.spemarti.webservice;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufrj.spemarti.webservice.entity.FragmentDefinition;
import br.ufrj.spemarti.webservice.entity.LabeledText;
import br.ufrj.spemarti.webservice.entity.Question;
import br.ufrj.spemarti.webservice.entity.Text;

@Stateless
public class QuestionHandler implements IQuestionHandler{

	@PersistenceContext(unitName="titan")
	private EntityManager em;
	
	public boolean associate(Question parent, FragmentDefinition fragment) {
		
		if( ! ( (fragment instanceof Text) || (fragment instanceof LabeledText ) ) ){
			throw new RuntimeException("Não é possível associar os artefatos.");
		
		}else{
			
			try{
				
				parent.getTexts().add((Text) fragment);
				((Text)fragment).setQuestion(parent);
				em.merge(fragment);
			}catch (Exception e) {
				throw new RuntimeException();
			}
		}
		return true;
	}

}
