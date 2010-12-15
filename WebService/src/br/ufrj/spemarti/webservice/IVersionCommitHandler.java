package br.ufrj.spemarti.webservice;

import javax.ejb.Local;

import br.ufrj.spemarti.webservice.entity.FragmentDefinition;

@Local
public interface IVersionCommitHandler {
	
	Integer commit(FragmentDefinition fragment, Integer idUsuario);

}
