package br.ufrj.spemarti.webservice;

import javax.ejb.Local;

import br.ufrj.spemarti.webservice.entity.SimpleInformationElement;

@Local
public interface IVersionHandler {
	
	Integer commit(SimpleInformationElement sie);

}
