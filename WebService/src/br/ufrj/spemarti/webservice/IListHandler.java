package br.ufrj.spemarti.webservice;

import javax.ejb.Local;

import br.ufrj.spemarti.webservice.entity.FragmentDefinition;
import br.ufrj.spemarti.webservice.entity.List;

@Local
public interface IListHandler {

	boolean associate(List parent, FragmentDefinition fragment);
	
}
