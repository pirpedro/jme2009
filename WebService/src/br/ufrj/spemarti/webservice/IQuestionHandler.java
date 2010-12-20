package br.ufrj.spemarti.webservice;

import javax.ejb.Local;

import br.ufrj.spemarti.webservice.entity.FragmentDefinition;
import br.ufrj.spemarti.webservice.entity.Question;

@Local
public interface IQuestionHandler {

	boolean associate(Question parent, FragmentDefinition fragment);
	
}
