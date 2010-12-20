package br.ufrj.spemarti.webservice;

import javax.ejb.Local;

import br.ufrj.spemarti.webservice.entity.Diagram;
import br.ufrj.spemarti.webservice.entity.FragmentDefinition;

@Local
public interface IDiagramHandler {

	boolean associate(Diagram parent, FragmentDefinition fragment);
	
}
