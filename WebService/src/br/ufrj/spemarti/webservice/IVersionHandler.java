package br.ufrj.spemarti.webservice;

import javax.ejb.Local;

import br.ufrj.spemarti.webservice.entity.FileSvn;
import br.ufrj.spemarti.webservice.entity.SimpleInformationElement;
import br.ufrj.spemarti.webservice.entity.Version;

@Local
public interface IVersionHandler {
	
	Integer commit(SimpleInformationElement sie);
	
	FileSvn checkOut(Version version);

}
