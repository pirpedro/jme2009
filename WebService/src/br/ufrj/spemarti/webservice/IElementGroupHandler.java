package br.ufrj.spemarti.webservice;

import javax.ejb.Local;

import br.ufrj.spemarti.webservice.entity.ElementGroup;
import br.ufrj.spemarti.webservice.entity.FragmentDefinition;

@Local
public interface IElementGroupHandler {

	boolean associate(ElementGroup parent, FragmentDefinition fragment);
	
}
